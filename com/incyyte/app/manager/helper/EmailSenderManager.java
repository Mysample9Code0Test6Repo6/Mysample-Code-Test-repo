package com.incyyte.app.manager.helper;

import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.util.*;
import org.apache.commons.lang.StringUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Properties;

public class EmailSenderManager implements Runnable, MailSender {

    protected static final boolean DEBUG = true;
    List<Contact> contacts = null;
    String serverURL = null;
    ServletContext servletContext = null;
    User user = null;
    boolean anonymous;

    public EmailSenderManager(User user, boolean anonymous, List<Contact> contacts, String serverURL, ServletContext servletContext) {
        this.contacts = contacts;
        this.serverURL = serverURL;
        this.servletContext = servletContext;
        this.user = user;
        this.anonymous = anonymous;
    }

    public void sendInCyyteEmail(List<Contact> contacts, String serverURL, ServletContext servletContext) {

        if (!contacts.isEmpty()) {
            for (Contact contact : contacts) {
                if (contact.getBlocked().equals("Y")) continue; //Do not email Blocked contacts

                if (contact.getMemberId() == null && anonymous)
                    continue; //Do not send anonymous incyyte to non member contacts

                try {
                    EmailParams emailParam = configEmailSetup();
                    Session session1 = Session.getDefaultInstance(emailParam.getProps(), null);
                    session1.setDebug(DEBUG);

                    String from = emailParam.getFrom();
                    MimeMessage message = new MimeMessage(session1);

                    //Non-anonymous incyytes -
                    //the receipient receives an invitation tagged with the question.
                    if (contact.getMemberId() == null && !anonymous) {
                        // send an invite
                        Logger.debug("******* Send invite to " + contact.getEmail());
                        sendInCyyteInvitation(message, contact);
                    } else {
                        String subjectInCyyte = "You have Received an InCyyte";
                        if (!anonymous) {
                            if (StringUtils.isNotEmpty(user.getFirstname()) || StringUtils.isNotEmpty(user.getLastname())) {
                                subjectInCyyte = "You have Received an InCyyte from " + user.getFirstname() + " " + user.getLastname();
                            }
                        }

                        message.setSubject(subjectInCyyte);
                        message.setFrom(new InternetAddress(from));

                        StringBuffer msg = new StringBuffer();
                        // message BODY
                        msg.append(contact.getXmlString());
                        String htmlStr = Utility.replace(msg.toString(), "[vote_button]", "cid:vote_button");
                        htmlStr = Utility.replace(htmlStr, "[background_image]", "cid:background_image");
                        htmlStr = Utility.replace(htmlStr, "[contactId]", new Long(contact.getMemberId()).toString());
                        htmlStr = Utility.replace(htmlStr, "[serverURL]", serverURL);

                        Logger.debug("HTML: " + htmlStr);

                        String toAddress = "";
                        toAddress = contact.getEmail();
                        Logger.debug("Recepients Email addresses " + toAddress);

                        message.addRecipient(Message.RecipientType.BCC, new InternetAddress(toAddress));

                        MimeMultipart multipart = new MimeMultipart("InCyyteMail");
                        BodyPart messageBodyPart = new MimeBodyPart();

                        messageBodyPart.setContent(htmlStr, "text/html");
                        multipart.addBodyPart(messageBodyPart);

                        Logger.debug("IMG PATH: " + servletContext.getRealPath("/images/logo_email.gif"));

                        // second part (the image)
                        messageBodyPart = new MimeBodyPart();
                        DataSource image2 = new FileDataSource(servletContext.getRealPath("/images/vote_button1.gif"));
                        messageBodyPart.setDataHandler(new DataHandler(image2));
                        messageBodyPart.setHeader("Content-ID", "<vote_button>");
                        multipart.addBodyPart(messageBodyPart);

                        messageBodyPart = new MimeBodyPart();
                        DataSource image3 = new FileDataSource(servletContext.getRealPath("/images/default_preview.gif"));
                        messageBodyPart.setDataHandler(new DataHandler(image3));
                        messageBodyPart.setHeader("Content-ID", "<background_image>");
                        multipart.addBodyPart(messageBodyPart);
                        message.setContent(multipart);
                    }

                    Transport transport = session1.getTransport("smtp");
                    transport.connect(emailParam.getHost(), emailParam.getUsername(), emailParam.getPassword());
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                    if (DEBUG) {
                        Logger.debug("Message sent OK.");
                    }
                } catch (Exception e) {
                   Logger.error("This is an error",e);
                }
            }
        }
    }

    private void sendInCyyteInvitation(MimeMessage message, Contact contact) throws Exception {
        ConfigManager cfgMgr = getConfigManager();

        StringBuilder subjectInCyyte = new StringBuilder(cfgMgr.getProperty(ConfigProperties.PROP_EMAIL_INVITE_SUBJECT));
        subjectInCyyte.append(" from " + user.getFirstname() + " " + user.getLastname());

        message.setSubject(subjectInCyyte.toString());
        String from = cfgMgr.getProperty(ConfigProperties.WELCOME_EMAIL_FROM);
        message.setFrom(new InternetAddress(from));
        StringBuffer msg = new StringBuffer();
        // message BODY
        msg.append(contact.getXmlString());
        String htmlStr = Utility.replace(msg.toString(), "[vote_button]", "cid:vote_button");
        htmlStr = Utility.replace(htmlStr, "[background_image]", "cid:background_image");
        htmlStr = Utility.replace(htmlStr, "[contactId]", new Long(contact.getMemberId()).toString());
        htmlStr = Utility.replace(htmlStr, "[serverURL]", serverURL);

        Logger.debug("HTML: " + htmlStr);

        String toAddress = "";
        toAddress = contact.getEmail();
        Logger.debug("Recepients Email addresses " + toAddress);

        message.addRecipient(Message.RecipientType.BCC, new InternetAddress(toAddress));

        MimeMultipart multipart = new MimeMultipart("InCyyteMail");

        final String invitationSvrURL = cfgMgr.getProperty(ConfigProperties.WEBSITE_URL) + cfgMgr.getProperty(ConfigProperties.INVITATION_URL);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(invitationSvrURL + " " + user.getId(), "text/html");
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlStr, "text/html");
        multipart.addBodyPart(messageBodyPart);

        Logger.debug("IMG PATH: " + servletContext.getRealPath("/images/logo_email.gif"));

        messageBodyPart = new MimeBodyPart();
        DataSource image2 = new FileDataSource(servletContext.getRealPath("/images/vote_button1.gif"));
        messageBodyPart.setDataHandler(new DataHandler(image2));
        messageBodyPart.setHeader("Content-ID", "<vote_button>");
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        DataSource image3 = new FileDataSource(servletContext.getRealPath("/images/default_preview.gif"));
        messageBodyPart.setDataHandler(new DataHandler(image3));
        messageBodyPart.setHeader("Content-ID", "<background_image>");
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
    }

    public void sendInCyyteResponseEmail(long inCyyteID, long contactResponseID, String serverURL, ServletContext servletContex) {
        //1. If InCyyte option selects send email of each response - (send email of all contact responses)
        //2. send Final email indicating all have responded
        try {
            String subjectInCyyte = "You have Received an InCyyte";
            EmailParams emailParam = configEmailSetup();

            Session session1 = Session.getDefaultInstance(emailParam.getProps(), null);
            session1.setDebug(DEBUG);

            String from = emailParam.getFrom();
            MimeMessage message = new MimeMessage(session1);

            message.setSubject(subjectInCyyte);
            message.setFrom(new InternetAddress(from));

            StringBuffer msg = new StringBuffer();
            // message BODY
            String toAddress = "";
            toAddress = "";//grp.getContact().getEmail();

            Logger.debug("Recepients Email addresses " + toAddress);

            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(toAddress));

            MimeMultipart multipart = new MimeMultipart("InCyyteMail");
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent(msg.toString(), "text/html");
            multipart.addBodyPart(messageBodyPart);

            Logger.debug("IMG PATH: " + servletContex.getRealPath("/images/logo_email.gif"));

            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource image1 = new FileDataSource(servletContex.getRealPath("/images/logo_email.gif"));
            messageBodyPart.setDataHandler(new DataHandler(image1));
            messageBodyPart.setHeader("Content-ID", "<logo_email>");
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource image2 = new FileDataSource(servletContex.getRealPath("/images/vote_button.gif"));
            messageBodyPart.setDataHandler(new DataHandler(image2));
            messageBodyPart.setHeader("Content-ID", "<vote_button>");
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport transport = session1.getTransport("smtp");
            transport.connect(emailParam.getHost(), emailParam.getUsername(), emailParam.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            if (DEBUG) {
                Logger.debug("Message sent OK.");
            }

        } catch (Exception e) {
            Logger.error("This is an error",e);
        }

    }

    public static void main(String args[]) {
        User form = new User();
        form.setFirstname("Timi");
        form.setEmail("tboboye@hotmail.com");
    }

    private EmailParams configEmailSetup() {
        EmailParams params = new EmailParams();
        try {
            Properties props = System.getProperties();
            ConfigManager cfgMgr = getConfigManager();
            String port = cfgMgr.getProperty(ConfigProperties.PROP_EMAIL_SMTP_PORT);
            String host = cfgMgr.getProperty(ConfigProperties.PROP_EMAIL_SMTP_HOST);
            String password = cfgMgr.getProperty(ConfigProperties.PROP_EMAIL_SMTP_PSWD);
            String user = cfgMgr.getProperty(ConfigProperties.PROP_EMAIL_SMTP_USER);
            String connectiontimeout = cfgMgr.getProperty(ConfigProperties.PROP_SESSION_TIMEOUT_MINS);
            String fromMailer = cfgMgr.getProperty(ConfigProperties.INCYYTE_QUESTION_POLL_EMAIL_FROM);

            Logger.debug("port--------->" + port);
            Logger.debug("host--------->" + host);
            Logger.debug("password--------->" + password);
            Logger.debug("user--------->" + user);
            Logger.debug("from--------->" + fromMailer);
            Logger.debug("connectiontimeout--------->" + connectiontimeout);

            params.setHost(host);
            params.setPassword(password);
            params.setPort(port);
            params.setUsername(user);
            params.setFrom(fromMailer);

            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", user);
            props.put("mail.smtp.password", password);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.connectiontimeout", "0");
            props.put("mail.smtp.timeout", "0");
            props.put("mail.debug", "true");

            params.setProps(props);

        } catch (Exception e) {
          
            Logger.error("This is an error",e);
        }
        return params;
    }

    private ConfigManager getConfigManager() {
        ConfigManager cfgMgr = null;
        try {
            cfgMgr = ConfigManager.getInstance();
            IncyyteProperties ip = new IncyyteProperties(null);
            cfgMgr.setIncyyteProperties(ip);
        } catch (ConfigurationException e) {
           Logger.warn("Configuration problem",e);
        }
        return cfgMgr;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void run() {
        Logger.debug("run: Contacts - " + getContacts().size());
        sendInCyyteEmail(getContacts(), getServerURL(), getServletContext());
    }

    public void sendWelcomeEmail(Long userId) {
        // TODO Auto-generated method stub
    }
}