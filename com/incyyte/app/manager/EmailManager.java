package com.incyyte.app.manager;

import com.incyyte.app.dao.login.RegisterDao;
import com.incyyte.app.dao.profile.UserSettingsDao;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.ReportPoll;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.util.*;
import com.incyyte.app.util.InCyyteUtil;
import com.incyyte.app.web.model.PaymentModel;
import com.incyyte.app.web.model.QuestionModel;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserSettingsModel;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailManager implements Runnable {
    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    private UserDao userDaoImpl;
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private LoginService loginSrv;

    @Autowired
    private UserSettingsDao userSettingsDao;

    private static final String VM_TEMPLATE_LOC = "vmTemplate";
    private static String VM_TEMPLATE_PATH = VM_TEMPLATE_LOC + "/";
    private String runFlag = "invoke method";
    private User user = null;
    private UserContactModel cuser = null;
    private String newPassword = null;
    private String postUrl;
    private String context;
    private String contactName;
    private String messageText;
    private Contact contact;
    private PaymentModel paymentModel;
    private String websiteURL;
    private QuestionModel questionModel;

    public PaymentModel getPaymentModel() {
        return paymentModel;
    }

    public void setPaymentModel(PaymentModel paymentModel) {
        this.paymentModel = paymentModel;
    }

    public QuestionModel getQuestionModel() {
        return questionModel;
    }

    public void setQuestionModel(QuestionModel questionModel) {
        this.questionModel = questionModel;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    private List<Contact> contacts = null;
    private boolean anonymous;
    private InCyyte incyyte = null;
    private String locality = null;
    private ReportPoll reportPoll = null;

    private List<String> mailRecipients;

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @return the velocityEngine
     */
    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setUserDaoImpl(UserDao userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public List<String> getMailRecipients() {
        return mailRecipients;
    }

    public void setMailRecipients(List<String> mailRecipients) {
        this.mailRecipients = mailRecipients;
    }

    public void sendWelcomeEmail(final User user) throws MailException, ConfigurationException {
        Logger.debug("inside: sendWelcomeEmail ");
        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        String emailSubjectvar = null;
        websiteURL = InCyyteUtil.getWebURL();

        final String activationSvrURL = icfg.getProperty(ConfigProperties.WEBSITE_URL) + icfg.getProperty(ConfigProperties.ACTIVATION_URL);
        emailSubjectvar = icfg.getProperty(ConfigProperties.WELCOME_MAIL_SUBJECT);

        final String emailSubject = emailSubjectvar;
        Logger.debug("emailSubject = " + emailSubject);
        final String fromEmailAddress = icfg.getProperty(ConfigProperties.WELCOME_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String userEmailAddress = user.getEmail();
        final String[] toEmailAddresses = {userEmailAddress};
        Logger.debug("Sending mail to user = " + userEmailAddress);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddress, "inCyyte");
                message.setSubject(emailSubject);
                Map<String, Object> emailModel = new HashMap<String, Object>();
                emailModel.put("username", user.getUsername());

                if (user.getResetPwdFlag() != null && user.getResetPwdFlag().equals("Y")) {
                    emailModel.put("password", user.getPassword());
                } else {
                    emailModel.put("password", null);
                }

                emailModel.put("activationCode", activationSvrURL + user.getActivationCode() + (user.getQsInCyyteID() == null ? "" : "&iid=" + user.getQsInCyyteID().toString()));
                emailModel.put("webURL", websiteURL);

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, VM_TEMPLATE_PATH + "welcome_email_v4.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + userEmailAddress + "...");
    }

    public void sendPaymentEmail(final PaymentModel paymentModel, final User user, final InCyyte incyyte) throws MailException, ConfigurationException, ParseException {
        Logger.debug("inside: sendPaymentEmail :paymentModel:" + paymentModel);
        Logger.debug("user::" + user);
        Logger.debug("incyyte::" + incyyte);
        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        websiteURL = InCyyteUtil.getWebURL();
        String emailSubjectvar = null;
        emailSubjectvar = icfg.getProperty(ConfigProperties.PAYMENT_EMAIL_SUBJECT);
        final String emailSubject = emailSubjectvar;
        Logger.debug("emailSubject = " + emailSubject);

        final String fromEmailAddress = icfg.getProperty(ConfigProperties.WELCOME_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String userEmailAddress = user.getEmail();
        final String[] toEmailAddresses = {userEmailAddress};
        Logger.debug("Sending mail to user = " + userEmailAddress);

        String orderIdDateTime = paymentModel.getOrderID();
        int length = orderIdDateTime.length();
        String date = orderIdDateTime.substring((length - 14), length);
        Logger.debug("date:" + date);
        DateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date startDate = sdf.parse(date);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final String parsedDate = sdf2.format(startDate);
        Logger.debug("parsedDate:" + parsedDate);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddress, "inCyyte");
                message.setSubject(emailSubject);
                Map<String, Object> emailModel = new HashMap<String, Object>();

                emailModel.put("orderDateTime", parsedDate);
                emailModel.put("orderId", paymentModel.getOrderID());
                emailModel.put("orderReference", paymentModel.getPaymentReference());
                emailModel.put("username", user.getUsername());

                emailModel.put("title", paymentModel.getTitle());
                emailModel.put("firstName", paymentModel.getPaymentFirstName());
                emailModel.put("lastName", paymentModel.getPaymentLastName());
                emailModel.put("address1", paymentModel.getAddressPaymentBilling1());
                emailModel.put("address2", paymentModel.getAddressPaymentBilling2());
                emailModel.put("city", paymentModel.getCityBilling());
                emailModel.put("country", paymentModel.getCountryBilling());
                emailModel.put("postcode", paymentModel.getPostcodeBilling());
                emailModel.put("orderDetailsSection", (incyyte == null) ? "Subscription" : "pollPayment");
                emailModel.put("poll", (incyyte != null) ? incyyte.getIncyyte() : null);
                if (incyyte != null) {
                    if (StringUtils.equals(incyyte.getLocality(), "Postcode")) {
                        emailModel.put("regionLabel", "Postcode");
                        emailModel.put("region", (incyyte != null) ? incyyte.getPostcode() : null);
                    } else if (StringUtils.equals(incyyte.getLocality(), "Region")) {
                        emailModel.put("regionLabel", "Region");
                        emailModel.put("region", (incyyte != null) ? incyyte.getRegion() : null);
                    } else {
                        emailModel.put("regionLabel", "County");
                        emailModel.put("region", (incyyte != null) ? incyyte.getCounty() : null);
                    }
                }
                emailModel.put("ageRange", (incyyte != null) ? incyyte.getAgeRange() : null);
                emailModel.put("gender", (incyyte != null) ? (StringUtils.isNotBlank(incyyte.getGender()) && !StringUtils.equalsIgnoreCase(incyyte.getGender(), "select") ? incyyte.getGender() : "Both") : null);
                emailModel.put("recipients", (incyyte != null) ? incyyte.getTotalInCyyted() : null);

                final String amountWOTax;
                final String amount;
                String tax;

                if ((new BigDecimal(paymentModel.getAmountWOTax())).compareTo(new BigDecimal("100")) >= 0) {
                    amountWOTax = " � " + paymentModel.getDisplayAmountWOTax();
                } else {
                    amountWOTax = paymentModel.getDisplayAmountWOTax() + "p";
                }
                if ((new BigDecimal(paymentModel.getAmount())).compareTo(new BigDecimal("100")) >= 0) {
                    amount = " � " + paymentModel.getDisplayAmount();
                } else {
                    amount = paymentModel.getDisplayAmount() + "p";
                }
                tax = String.valueOf((new BigDecimal(paymentModel.getAmount())).subtract((new BigDecimal(paymentModel.getAmountWOTax()))));
                if ((new BigDecimal(tax)).compareTo(new BigDecimal("100")) >= 0) {
                    BigDecimal taxValue = new BigDecimal(tax);
                    taxValue = taxValue.divide(new BigDecimal(100));
                    taxValue = taxValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    tax = " � " + taxValue;
                } else {
                    tax = tax + "p";
                }

                final String taxWithSign = tax;
                emailModel.put("itemSubtotal", amountWOTax);
                emailModel.put("orderTotal", amount);
                emailModel.put("tax", taxWithSign);
                emailModel.put("webURL", websiteURL);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, VM_TEMPLATE_PATH + "Confirmation_reciept.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + user.getEmail() + "...");
    }

    public void sendReactivationEmail(final User user) throws MailException, ConfigurationException {
        Logger.debug("inside: sendReactivationEmail ");
        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        String emailSubjectvar = null;
        websiteURL = InCyyteUtil.getWebURL();

        final String reactivationSvrURL = icfg.getProperty(ConfigProperties.WEBSITE_URL) + icfg.getProperty(ConfigProperties.ACTIVATION_URL);
        emailSubjectvar = icfg.getProperty(ConfigProperties.REACTIVATION_EMAIL_SUBJECT);
        final String emailSubject = emailSubjectvar;
        Logger.debug("emailSubject = " + emailSubject);
        final String fromEmailAddress = icfg.getProperty(ConfigProperties.WELCOME_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String userEmailAddress = user.getEmail();
        final String[] toEmailAddresses = {userEmailAddress};
        Logger.debug("Sending mail to user = " + userEmailAddress);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddress, "inCyyte");
                message.setSubject(emailSubject);
                Map<String, Object> emailModel = new HashMap<String, Object>();
                emailModel.put("username", user.getUsername());
                emailModel.put("activationCode", reactivationSvrURL + user.getActivationCode() + (user.getQsInCyyteID() == null ? "" : "&iid=" + user.getQsInCyyteID().toString()));
                emailModel.put("webURL", websiteURL);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, VM_TEMPLATE_PATH + "Reactive_email.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + userEmailAddress + "...");
    }

    public void sendContactUsMessageEmail(final User user, final String messageText) throws MailException, ConfigurationException {
        Logger.debug("inside: sendEmail " + contactName);

        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        websiteURL = InCyyteUtil.getWebURL();
        final String toEmailAddresses = icfg.getProperty(ConfigProperties.CONTACT_US_MAIL_TO);
        Logger.debug("toEmailAddress = " + toEmailAddresses);
        final String fromEmailAddresses = user.getEmail();
        Logger.debug("Sending mail from user = " + user.getUsername());

        final String emailSubjectvar = icfg.getProperty(ConfigProperties.CONTACT_US_MAIL_SUBJECT);
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddresses);
                message.setSubject(emailSubjectvar);

                Map<String, Object> emailModel = new HashMap<String, Object>();
                emailModel.put("username", user.getUsername());
                emailModel.put("message", messageText);
                emailModel.put("webURL", websiteURL);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, VM_TEMPLATE_PATH + "message_email_v3.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + contactName + "...");
    }

    public void sendNotificationEmail(final User user, final QuestionModel question, final String notificationType) throws MailException, ConfigurationException {
        Logger.debug("inside: sendNotificationEmail:" + user);
        Logger.debug("inside: sendNotificationEmail:" + question);

        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        websiteURL = InCyyteUtil.getWebURL();
        final String fromEmailAddress = icfg.getProperty(ConfigProperties.PROP_EMAIL_FROM_NOTIFY);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String[] toEmailAddresses = {user.getEmail()};
        Logger.debug("Sending mail to user = " + toEmailAddresses);
        final String imageURL = icfg.getProperty(ConfigProperties.RACKSPACE_IMAGE_URL);
        final String defaultAvatar = imageURL + "default_avatar.png";
        final String emailSubject = icfg.getProperty(ConfigProperties.NOTIFICATION_MAIL_SUBJECT);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddress, "inCyyte");
                message.setSubject(emailSubject);
                Map<String, Object> emailModel = new HashMap<String, Object>();
                if (StringUtils.isNotBlank(user.getProfilePicture())) {
                    emailModel.put("profilePic", user.getProfilePicture());
                } else {
                    emailModel.put("profilePic", defaultAvatar);
                }
                emailModel.put("username", user.getUsername());
                emailModel.put("question", question.getQuestion());
                emailModel.put("notificationType", notificationType);
                emailModel.put("webURL", websiteURL);
                emailModel.put("viewResult", question.getLink());
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, VM_TEMPLATE_PATH + "notification_email_v1.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + contactName + "...");
    }

    public void sendEmail(final User user, String contactName, final String messageText) throws MailException, ConfigurationException {
        Logger.debug("inside: sendEmail " + contactName);

        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);

        final String fromEmailAddress = icfg.getProperty(ConfigProperties.MESSAGE_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String[] toEmailAddresses = {contactName};
        Logger.debug("Sending mail to user = " + contactName);

        final String emailSubjectvar = icfg.getProperty(ConfigProperties.MESSAGE_MAIL_SUBJECT);


        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddress, "inCyyte");
                message.setSubject(emailSubjectvar);

                Map<String, Object> emailModel = new HashMap<String, Object>();
                emailModel.put("username", user.getUsername());
                emailModel.put("message", messageText);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, VM_TEMPLATE_PATH + "message_email_v3.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + contactName + "...");
    }

    public void sendPollMessageEmail(final User user, final List<String> mailRecipients, final String messageText, final UserContactModel usercModel) throws MailException, ConfigurationException {
        Logger.debug("inside sendPollMessageEmail = ");
        Logger.debug("mailRecipients ::" + mailRecipients);
        Logger.debug(" messageText::" + messageText);
        Logger.debug(" usercModel::" + usercModel);

        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        websiteURL = InCyyteUtil.getWebURL();
        final String imageURL = icfg.getProperty(ConfigProperties.RACKSPACE_IMAGE_URL);
        final String defaultAvatar = imageURL + "default_avatar.png";

        final String fromEmailAddress = icfg.getProperty(ConfigProperties.MESSAGE_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);

        for (final String mailRecipient : mailRecipients) {
            Logger.debug("Sending mail to user = " + mailRecipient);
            final String emailSubjectvar = icfg.getProperty(ConfigProperties.MESSAGE_MAIL_SUBJECT);
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    User recipient = null;
                    try {
                        recipient = registerDao.getUserDetails(mailRecipient, Constants.GET_BY_MAIL);
                    } catch (Exception e) {
                        Logger.error("Exception:", e);
                    }
                    message.setTo(mailRecipient);
                    message.setFrom(fromEmailAddress, "inCyyte");
                    message.setSubject(emailSubjectvar);
                    Logger.debug("user = " + user);
                    Logger.debug("message = " + messageText);
                    Map<String, Object> emailModel = new HashMap<String, Object>();
                    emailModel.put("username", user.getUsername());
                    if (StringUtils.isNotBlank(user.getProfilePicture())) {
                        emailModel.put("profilePicture", user.getProfilePicture());
                    } else {
                        emailModel.put("profilePicture", defaultAvatar);
                    }
                    if (recipient != null) {
                        emailModel.put("recipientUsername", recipient.getUsername());
                    }
                    emailModel.put("message", messageText);
                    emailModel.put("UploadedFileURL", imageURL + "photo1.png");
                    emailModel.put("question_ref", websiteURL);
                    if (isImageFile(usercModel.getUploadedFileLocation())) {
                        emailModel.put("UploadedFileURL", (StringUtils.defaultString(usercModel.getUploadedFileLocation())));
                        emailModel.put("question_ref", "javascript:void(0);");
                    } else if (isVideoFile(usercModel.getUploadedFileLocation())) {
                        emailModel.put("UploadedFileURL", imageURL + "default_vid_img.png");
                        emailModel.put("question_ref", (StringUtils.defaultString(usercModel.getUploadedFileLocation())));
                    } else if (isYoutubeVideoFile(usercModel.getUploadedFileLocation())) {
                        emailModel.put("UploadedFileURL", imageURL + "default_vid_img.png");
                        emailModel.put("question_ref", (StringUtils.defaultString(usercModel.getUploadedFileLocation())));
                    } else if (isDocFile(usercModel.getUploadedFileLocation())) {
                        emailModel.put("UploadedFileURL", imageURL + "default_doc_img.png");
                        emailModel.put("question_ref", (StringUtils.defaultString(usercModel.getUploadedFileLocation())));
                    }
                    emailModel.put("webURL", websiteURL);
                    String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, VM_TEMPLATE_PATH + "voting_response_flyer_v1.vm", emailModel);
                    message.setText(text, true);
                }
            };
            this.mailSender.send(preparator);
            Logger.debug("Email sent successfully to " + mailRecipient + "...");
        }
    }

    public void sendResetPasswordEmail(final User user, final String newPassword) throws MailException, ConfigurationException {
        Logger.debug("inside: sendResetPasswordEmail ");
        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        String emailSubjectvar = null;
        websiteURL = InCyyteUtil.getWebURL();
        emailSubjectvar = icfg.getProperty(ConfigProperties.PROP_EMAIL_FORGOTTEN_PASS_SUBJECT);

        final String emailSubject = emailSubjectvar;
        Logger.debug("emailSubject = " + emailSubject);
        final String fromEmailAddress = icfg.getProperty(ConfigProperties.PROP_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String userEmailAddress = user.getEmail();
        final String[] toEmailAddresses = {userEmailAddress};
        Logger.debug("Sending mail to user = " + userEmailAddress);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddress, "inCyyte");

                message.setSubject(emailSubject);
                Map<String, Object> emailModel = new HashMap<String, Object>();
                emailModel.put("username", user.getUsername());
                emailModel.put("newPassword", newPassword);
                emailModel.put("webURL", websiteURL);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, VM_TEMPLATE_PATH + "forgotPassword_email_v3.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + userEmailAddress + "...");
    }

    private String getCDNBaseURL() throws ConfigurationException {
        ConfigManager icfg = ConfigManager.getInstance();
        String url = icfg.getProperty(ConfigProperties.PROP_CDN_IMAGES_URL);
        return url;
    }

    public String getRunFlag() {
        return runFlag;
    }

    public void setRunFlag(String runFlag) {
        this.runFlag = runFlag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void run() {
        Logger.debug("Email Manager Starter");
        if (getRunFlag().equals(Constants.SEND_WELCOME_EMAIL)) {
            try {
                sendWelcomeEmail(getUser());
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_WELCOME_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_WELCOME_EMAIL);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_CONTACT_US_EMAIL)) {
            try {
                sendContactUsMessageEmail(getUser(), getMessageText());
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_CONTACT_US_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_CONTACT_US_EMAIL);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_POLL_COMMENTS_NOTIFICATION)) {
            try {
                sendNotificationEmail(getUser(), getQuestionModel(), "COMMENTS");
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_POLL_COMMENTS_NOTIFICATION);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_POLL_COMMENTS_NOTIFICATION);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_POLL_VOTES_NOTIFICATION)) {
            try {
                sendNotificationEmail(getUser(), getQuestionModel(), "VOTES");
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_POLL_VOTES_NOTIFICATION);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_POLL_VOTES_NOTIFICATION);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_REACTIVATION_EMAIL)) {
            try {
                sendReactivationEmail(getUser());
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_REACTIVATION_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_REACTIVATION_EMAIL);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_ACTIVATION_EMAIL)) {
            try {
                sendResetPasswordEmail(getUser(), getNewPassword());
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_ACTIVATION_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_ACTIVATION_EMAIL);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_INVITE_EMAIL)) {
            try {
                sendInviteEmail(getCuser());
            } catch (MailException e) {
                SendiviteFailed(getCuser());
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                SendiviteFailed(getCuser());
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_POSTSCRIPT_EMAIL)) {
            try {
                sendPostScriptEmail(getUser(), getPostUrl());
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_POSTSCRIPT_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_POSTSCRIPT_EMAIL);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_SHARE_POLL_PAGE_EMAIL)) {
            try {
                Logger.debug("in if condition in run in Email manager");
                sendPollPageEmail(contact, isAnonymous(), Constants.SEND_SHARE_POLL_PAGE_EMAIL);
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_SHARE_POLL_PAGE_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_SHARE_POLL_PAGE_EMAIL);
                Logger.error("Exception:", e);
            }

        } else if (getRunFlag().equals(Constants.SEND_SHARE_NEWS_LETTER_EMAIL)) {
            try {
                Logger.debug("in if condition in run in Email manager");
                sendPollPageEmail(contact, isAnonymous(), Constants.SEND_SHARE_NEWS_LETTER_EMAIL);
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_SHARE_NEWS_LETTER_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_SHARE_NEWS_LETTER_EMAIL);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_REPORT_THIS_POLL_EMAIL)) {
            try {
                Logger.debug("For Report This Poll In run");
                sendReportThisPollEmail();
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_REPORT_THIS_POLL_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_REPORT_THIS_POLL_EMAIL);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_INCYYTE_EMAIL)) {
            try {
                sendInCyyteEmail2(getContacts(), isAnonymous());
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_INCYYTE_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_INCYYTE_EMAIL);
                Logger.error("Exception:", e);
            } catch (Exception e) {
                Logger.error("Exception:", e);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_BY_AREA)) {
            try {
                sendInCyyteEmailToRegion(getContacts(), getLocality());
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_BY_AREA);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_BY_AREA);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_POLL_MESSAGE)) {
            try {
                sendPollMessageEmail(getUser(), getMailRecipients(), getMessageText(), getCuser());
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_POLL_MESSAGE);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_POLL_MESSAGE);
                Logger.error("Exception:", e);
            }
        } else if (getRunFlag().equals(Constants.SEND_PAYMENT_EMAIL)) {
            try {
                Logger.debug("This Logger is required for time delay of this thread");
                sendPaymentEmail(paymentModel, user, incyyte);
            } catch (MailException e) {
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                Logger.error("Exception:", e);
            } catch (ParseException e) {
                Logger.error("Exception:", e);
            }
        } else {
            try {
                sendEmail(getUser(), getContactName(), getMessageText());
            } catch (MailException e) {
                logMailError2DB(getUser(), Constants.SEND_EMAIL);
                Logger.error("Exception:", e);
            } catch (ConfigurationException e) {
                logMailError2DB(getUser(), Constants.SEND_EMAIL);
                Logger.error("Exception:", e);
            }
        }
    }

    private void logMailError2DB(User user, String emailType) {
        //User Email Failed, Log into DB for Auto Resend
        userDaoImpl.logFailedEmails(user, emailType);
    }

    private void SendiviteFailed(UserContactModel cuser2) {
        //User Email Failed, Log into DB for Auto Resend
        userDaoImpl.logFailedEmails(cuser2);
    }

    public void sendInviteEmail(final UserContactModel cuser) throws MailException, ConfigurationException {
        Logger.debug("inside: sendInviteEmail " + cuser);
        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        websiteURL = InCyyteUtil.getWebURL();
        String emailSubjectvar = null;
        final boolean memberStatus = cuser == null ? false : cuser.getStatus() == null ? false : cuser.getStatus().equals("M") ? true : false;

        final String invitationSvrURL = icfg.getProperty(ConfigProperties.WEBSITE_URL) + icfg.getProperty(ConfigProperties.INVITATION_URL);
        if (memberStatus)
            emailSubjectvar = (user == null ? "A Friend's" : user.getEmail()) + " " + icfg.getProperty(ConfigProperties.PROP_EMAIL_MEMBER_INVITE_SUBJECT);
        else
            emailSubjectvar = icfg.getProperty(ConfigProperties.PROP_EMAIL_INVITE_SUBJECT);

        final String emailSubject = emailSubjectvar;
        Logger.debug("emailSubject = " + emailSubject);
        final String fromEmailAddress = icfg.getProperty(ConfigProperties.PROP_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String userEmailAddress = cuser.getEmail();
        final String[] toEmailAddresses = {userEmailAddress};
        Logger.debug("Sending mail to user = " + userEmailAddress);
        String inviteeName = null;
        if (null == user)
            inviteeName = "A Friend";
        else
            inviteeName = (null != user.getFirstname() ? user.getFirstname() :
                    (null != user.getUsername() ? user.getUsername() : "A Friend"));

        final String name = inviteeName;
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddress, "inCyyte");
                message.setSubject(emailSubject);
                Map<String, Object> emailModel = new HashMap<String, Object>();
                emailModel.put("inviteeName", name);
                emailModel.put("invUrl", invitationSvrURL + cuser.getInvitationid());
                emailModel.put("memberStatus", memberStatus);
                emailModel.put("webURL", websiteURL);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, VM_TEMPLATE_PATH + "invite_email_v3.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + userEmailAddress + "...");
    }

    public void setCuser(UserContactModel cuser) {
        this.cuser = cuser;
    }

    public UserContactModel getCuser() {
        return cuser;
    }

    public void sendPostScriptEmail(final User user, final String postUrl) throws MailException, ConfigurationException {
        Logger.debug("inside: sendPostScriptEmail ");
        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        String emailSubjectvar = null;
        websiteURL = InCyyteUtil.getWebURL();

        emailSubjectvar = icfg.getProperty(ConfigProperties.POSTSCRIPT_MAIL_SUBJECT);

        final String emailSubject = emailSubjectvar;
        Logger.debug("emailSubject = " + emailSubject);
        final String fromEmailAddress = icfg.getProperty(ConfigProperties.MESSAGE_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String userEmailAddress = user.getEmail();
        final String[] toEmailAddresses = {userEmailAddress};
        Logger.debug("Sending mail to user = " + userEmailAddress);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddress);
                message.setSubject(emailSubject);
                Map<String, Object> emailModel = new HashMap<String, Object>();
                emailModel.put("username", user.getUsername());
                emailModel.put("pollPageUrlURL", postUrl);
                emailModel.put("webURL", websiteURL);

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, VM_TEMPLATE_PATH + "postscript_email_v2.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + userEmailAddress + "...");
    }

    public void sendInCyyteEmail2(List<Contact> contacts, final boolean anonymous) throws Exception {
        Logger.debug("inside: send inCyyte Email ");

        IncyyteProperties ip = null;
        try {
            ip = new IncyyteProperties(null);
        } catch (ConfigurationException e) {
            Logger.error("Exception:", e);
        }

        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        websiteURL = InCyyteUtil.getWebURL();
        final String fromEmailAddress = icfg.getProperty(ConfigProperties.INCYYTE_QUESTION_POLL_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String imageURL = icfg.getProperty(ConfigProperties.RACKSPACE_IMAGE_URL);
        final String emailSubjectvar = icfg.getProperty(ConfigProperties.INCYYTE_MAIL_SUBJECT);
        final String anonymousAvatar = imageURL + "annonymous_icon.png";
        final String defaultAvatar = imageURL + "default_avatar.png";
        final String profilepic = user.getProfilePicture();
        final String votingURL = icfg.getProperty(ConfigProperties.WEBSITE_URL) + icfg.getProperty(ConfigProperties.INCYYTE_VOTING_URL);

        if (!contacts.isEmpty()) {
            for (final Contact contact : contacts) {
                try {
                    if (contact.getBlocked() != null && contact.getBlocked().equals("Y"))
                        continue; //Do not email Blocked contacts
                    if ((contact.getStatus() == null || contact.getStatus().equals("NM")) && anonymous)
                        continue; //Do not send anonymous incyyte to non member contacts
                    final String toAddress = contact.getEmail();
                    Logger.debug("Recipients Email addresses " + toAddress);
                    final User contactDetail = loginSrv.getUserDetailByEmailOrUsername(contact.getEmail(), null);
                    Logger.debug("Recipients contactDetail " + contactDetail);
                    //Do not send  incyyte  email to Deactivated
                    if (contactDetail != null && StringUtils.equalsIgnoreCase(contactDetail.getStatus(), "DEACTIVATED")) {
                        continue;
                    }
                    //Non-anonymous incyytes -
                    //the recipient receives an invitation tagged with the question.
                    if (contact.getStatus() == null || contact.getStatus().equals("NM") && !anonymous) {
                        // send an invite
                        Logger.debug("Send invite to " + contact);
                        sendInviteWithQuestionEmail(contact);
                    } else {
                        //Send EMAIL for each contact
                        MimeMessagePreparator preparator = new MimeMessagePreparator() {
                            public void prepare(MimeMessage mimeMessage) throws Exception {
                                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                                message.setTo(toAddress);
                                message.setFrom(fromEmailAddress, "inCyyte");
                                message.setSubject(emailSubjectvar);

                                Map<String, Object> emailModel = new HashMap<String, Object>();
                                emailModel.put("username", anonymous ? "anonymous" : user.getUsername());
                                emailModel.put("profilePic", (anonymous ? anonymousAvatar : profilepic == null ? defaultAvatar : profilepic.equals("") ? defaultAvatar : profilepic));

                                emailModel.put("created_date", (getIncyyte().getCreatedDate() == null ? "" : getIncyyte().getCreatedDate()));
                                emailModel.put("poll_ref", (getIncyyte().getIncyyteCode() == null ? "" : getIncyyte().getIncyyteCode()));
                                emailModel.put("group_name", (getIncyyte().getGroup() == null ? "" : getIncyyte().getGroup().getGroupName() == null ? "" : getIncyyte().getGroup().getGroupName()));
                                emailModel.put("closing_date", (getIncyyte().getClosureDate() == null ? "" : getIncyyte().getClosureDate()));
                                emailModel.put("question", (getIncyyte().getIncyyte() == null ? "" : getIncyyte().getIncyyte()));
                                emailModel.put("question_file", imageURL + "photo1.png");
                                emailModel.put("question_ref", websiteURL);
                                if (isImageFile(getIncyyte().getUploadLocation())) {
                                    emailModel.put("question_file", (StringUtils.defaultString(getIncyyte().getUploadLocation())));
                                    emailModel.put("question_ref", "javascript:void(0);");
                                } else if (isVideoFile(getIncyyte().getUploadLocation())) {
                                    emailModel.put("question_file", imageURL + "video_thumb.png");
                                    emailModel.put("question_ref", (StringUtils.defaultString(getIncyyte().getUploadLocation())));
                                } else if (isDocFile(getIncyyte().getUploadLocation())) {
                                    emailModel.put("question_file", imageURL + "view_docs_thumb.png");
                                    emailModel.put("question_ref", (StringUtils.defaultString(getIncyyte().getUploadLocation())));
                                } else if (getIncyyte().getYoutubeUrl() != null) {
                                    emailModel.put("question_file", imageURL + "video_thumb.png");
                                    emailModel.put("question_ref", (StringUtils.defaultString(getIncyyte().getYoutubeUrl())));
                                }
                                emailModel.put("answers", getIncyyte().getAnswers());
                                String fmtID = null;
                                if (getIncyyte().isShared()) {
                                    Logger.debug("get Shared ID:  for QuestionID: " + getIncyyte().getId() + " ContactID: " + contact.getId() + " for Email: " + contact.getEmail());
                                    fmtID = userDaoImpl.getShareRecord(getIncyyte().getId(), contact.getEmail()).getFmtId();
                                }
                                emailModel.put("votingURL", votingURL);
                                emailModel.put("email", contact.getEmail());
                                emailModel.put("activationCode", contactDetail.getActivationCode());
                                emailModel.put("incyyteID", getIncyyte().getId());
                                emailModel.put("contactId", (getIncyyte().isShared() ? fmtID : contact.getMemberId() == null ?
                                        userDaoImpl.getPollMemberId(contact.getEmail(), getIncyyte().getId()) :
                                        contact.getMemberId().toString()));
                                emailModel.put("webURL", websiteURL);
                                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, VM_TEMPLATE_PATH + "vote_email_v4.vm", emailModel);
                                message.setText(text, true);
                            }
                        };
                        try {
                            UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(contactDetail.getId());
                            Logger.debug("userSettings::" + userSettings);
                            if (StringUtils.equals("Y", userSettings.getNotifypolls())) {
                                Logger.debug("email is sending to::" + contact.getEmail());
                                this.mailSender.send(preparator);
                            }
                        } catch (Exception e) {
                            //The Exception can be raised in case of User doesn't exist in the system
                            // Even in that case we need to send email for the User
                            this.mailSender.send(preparator);
                        }
                    }
                } catch (Exception e) {
                    Logger.error("email is not sent to contact:" + contact.getEmail(), e);
                }
            }
        }
    }

    public void sendPollPageEmail(final Contact contact, final boolean anonymous, final String shareType) throws MailException, ConfigurationException {
        Logger.debug("inside: send share poll page Email ");
        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        websiteURL = InCyyteUtil.getWebURL();

        final String fromEmailAddress = icfg.getProperty(ConfigProperties.INCYYTE_QUESTION_POLL_EMAIL_FROM);
        final String pollPageURl = icfg.getProperty(ConfigProperties.WEBSITE_URL);
        final String createAccountURL = icfg.getProperty(ConfigProperties.WEBSITE_URL) + icfg.getProperty(ConfigProperties.CREATE_ACCOUNT_URL);
        final String emailSubjectvar = getUser().getUsername() + " would like your response...";
        final User contactDetail = loginSrv.getUserDetailByEmailOrUsername(contact.getEmail(), null);
        Logger.debug("Recepients contactDetail " + contactDetail);

        //Do not send  incyyte  email to Deactivated
        if (contactDetail != null && !StringUtils.equalsIgnoreCase(contactDetail.getStatus(), "DEACTIVATED")) {
            //Do not email Blocked contacts
            if (StringUtils.equals(contact.getBlocked(), "N")) {
                //Do not send anonymous incyyte to non member contacts
                if (StringUtils.equals(contact.getStatus(), "M") || !anonymous) {
                    try {
                        Logger.debug("Contact: " + contact.getEmail() + " is " + contact.getStatus());
                        final String toAddress = contact.getEmail();
                        Logger.debug("Recipients Email addresses " + toAddress);
                        final String str = ".cyt";
                        String userDomainPageName = null;

                        if (getIncyyte().getUserId() != null) {
                            userDomainPageName = userSettingsDao.getUniquePageName(getIncyyte().getUserId());
                        }

                        if (userDomainPageName == null || userDomainPageName.isEmpty()) {
                            userDomainPageName = getIncyyte().getCreatedBy();
                        }

                        final StringBuilder pollPageUrl = new StringBuilder().append(pollPageURl).append(userDomainPageName).append('/').append(getIncyyte().getPageName()).append(str);
                        //Send EMAIL for each contact
                        MimeMessagePreparator preparator = new MimeMessagePreparator() {
                            public void prepare(MimeMessage mimeMessage) throws Exception {
                                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                                message.setTo(toAddress);
                                message.setFrom(fromEmailAddress, "inCyyte");
                                message.setSubject(emailSubjectvar);
                                Map<String, Object> emailModel = new HashMap<String, Object>();
                                //Message changes whether User is sending his / her poll or someone's poll
                                String displayText;
                                if (StringUtils.equals(getIncyyte().getCreatedBy(), getUser().getUsername())) {
                                    displayText = getUser().getUsername() + " has SHARED a ";
                                } else {
                                    displayText = getUser().getUsername() + " has SHARED someone's ";
                                }

                                String emailTemplate = "user_setup_poll_email_v1.vm";

                                if (shareType != null && shareType.equals(Constants.SEND_SHARE_NEWS_LETTER_EMAIL)) {
                                    emailTemplate = "preregistered.vm";
                                    //displayText="";
                                    emailModel.put("name", contact.getFirstName() + " " + contact.getLastName());
                                    emailModel.put("email", contact.getEmail());
                                    emailModel.put("password", contact.getPassword());
                                }


                                if (StringUtils.equals(contact.getStatus(), "NM")) {
                                    emailTemplate = "user_setup_poll_email_nonmember_v1.vm";
                                    emailModel.put("pollPageUrlURL", createAccountURL);
                                } else {
                                    emailModel.put("pollPageUrlURL", pollPageUrl);
                                }
                                Logger.debug("displayText:" + displayText);
                                Logger.debug("emailTemplate:" + emailTemplate);
                                Logger.debug("getIncyyte()" + getIncyyte().toString());
                                Logger.debug("question name" + getIncyyte().getIncyyte());
                                emailModel.put("message", displayText);
                                emailModel.put("question", getIncyyte().getIncyyte());
                                emailModel.put("webURL", websiteURL);

                                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, VM_TEMPLATE_PATH + emailTemplate, emailModel);
                                message.setText(text, true);
                            }
                        };
                        this.mailSender.send(preparator);
                    } catch (Exception e) {
                        Logger.error("email is not sent to contact:" + contact.getEmail(), e);
                    }
                }
            }
        }
    }

    public void sendReportThisPollEmail() throws MailException, ConfigurationException {
        Logger.debug("inside:sendReportThisPollEmail");
        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        String fromEmailAddress = icfg.getProperty(ConfigProperties.REPORT_POLL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        String emailSubject = icfg.getProperty(ConfigProperties.REPORT_POLL_SUBJECT);
        String toAddress = icfg.getProperty(ConfigProperties.REPORT_POLL_TO);
        Logger.debug("Recipients Email address:: " + toAddress);
        String message = getReportPoll().toString();
        sendInternalMail(toAddress, fromEmailAddress, emailSubject, message);
    }

    public void sendInCyyteEmailToRegion(List<Contact> contacts, final String locality) throws MailException, ConfigurationException {
        Logger.debug("inside: send inCyyte Email ");
        IncyyteProperties ip = null;
        try {
            ip = new IncyyteProperties(null);
        } catch (ConfigurationException e) {
            Logger.error("Exception:", e);
        }

        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        websiteURL = InCyyteUtil.getWebURL();

        final String fromEmailAddress = icfg.getProperty(ConfigProperties.INCYYTE_QUESTION_POLL_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);
        final String imageURL = icfg.getProperty(ConfigProperties.RACKSPACE_IMAGE_URL);
        final String emailSubjectvar = icfg.getProperty(ConfigProperties.INCYYTE_MAIL_SUBJECT);
        final String anonymousAvatar = imageURL + "annonymous_icon.png";
        final String defaultAvatar = imageURL + "default_avatar.png";
        final String profilepic = user.getProfilePicture();
        final String votingURL = icfg.getProperty(ConfigProperties.WEBSITE_URL) + icfg.getProperty(ConfigProperties.INCYYTE_REGION_VOTING_URL);

        if (!contacts.isEmpty()) {
            for (final Contact contact : contacts) {
                try {
                    if (contact.getBlocked() != null && contact.getBlocked().equals("Y"))
                        continue; //Do not email Blocked contacts

                    final String toAddress = contact.getEmail();
                    Logger.debug("Recepients Email addresses " + toAddress);
                    final User contactDetail = loginSrv.getUserDetailByEmailOrUsername(contact.getEmail(), null);
                    Logger.debug("Recepients contactDetail " + contactDetail);
                    //Do not send  incyyte  email to Deactivated contacts
                    if (contactDetail != null && StringUtils.equalsIgnoreCase(contactDetail.getStatus(), "DEACTIVATED")) {
                        continue;
                    }
                    Logger.debug("userin email::" + contactDetail);

                    //Send EMAIL for each contact
                    MimeMessagePreparator preparator = new MimeMessagePreparator() {
                        @SuppressWarnings("deprecation")
                        public void prepare(MimeMessage mimeMessage) throws Exception {
                            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                            message.setTo(toAddress);
                            message.setFrom(fromEmailAddress, "inCyyte");
                            message.setSubject(emailSubjectvar);

                            Map<String, Object> emailModel = new HashMap<String, Object>();
                            emailModel.put("region", locality);
                            emailModel.put("profilePic", (anonymous ? anonymousAvatar : profilepic == null ? defaultAvatar : profilepic.equals("") ? defaultAvatar : profilepic));

                            emailModel.put("created_date", (getIncyyte().getCreatedDate() == null ? "" : getIncyyte().getCreatedDate()));
                            emailModel.put("poll_ref", (getIncyyte().getIncyyteCode() == null ? "" : getIncyyte().getIncyyteCode()));
                            emailModel.put("group_name", (getIncyyte().getGroup() == null ? "" : getIncyyte().getGroup().getGroupName() == null ? "" : getIncyyte().getGroup().getGroupName()));
                            emailModel.put("closing_date", (getIncyyte().getClosureDate() == null ? "" : getIncyyte().getClosureDate()));
                            emailModel.put("question", (getIncyyte().getIncyyte() == null ? "" : getIncyyte().getIncyyte()));
                            emailModel.put("question_file", imageURL + "photo1.png");
                            emailModel.put("question_ref", websiteURL);
                            if (isImageFile(getIncyyte().getUploadLocation())) {
                                emailModel.put("question_file", (StringUtils.defaultString(getIncyyte().getUploadLocation())));
                                emailModel.put("question_ref", "javascript:void(0);");
                            } else if (isVideoFile(getIncyyte().getUploadLocation())) {
                                emailModel.put("question_file", imageURL + "video_thumb.png");
                                emailModel.put("question_ref", (StringUtils.defaultString(getIncyyte().getUploadLocation())));
                            } else if (isDocFile(getIncyyte().getUploadLocation())) {
                                emailModel.put("question_file", imageURL + "view_docs_thumb.png");
                                emailModel.put("question_ref", (StringUtils.defaultString(getIncyyte().getUploadLocation())));
                            } else if (getIncyyte().getYoutubeUrl() != null) {
                                emailModel.put("question_file", imageURL + "video_thumb.png");
                                emailModel.put("question_ref", (StringUtils.defaultString(getIncyyte().getYoutubeUrl())));
                            }
                            emailModel.put("answers", getIncyyte().getAnswers());
                            emailModel.put("votingURL", votingURL);
                            emailModel.put("incyyteID", getIncyyte().getId());
                            emailModel.put("contactId", contact.getUserId());
                            emailModel.put("activationCode", contactDetail.getActivationCode());
                            emailModel.put("email", toAddress);
                            emailModel.put("webURL", websiteURL);

                            String text = VelocityEngineUtils.mergeTemplateIntoString(
                                    velocityEngine, VM_TEMPLATE_PATH + "vote_region_email_v1.vm", emailModel);

                            message.setText(text, true);
                        }
                    };
                    try {
                        UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(contactDetail.getId());
                        Logger.debug("userSettings::" + userSettings);
                        if (StringUtils.equals("Y", userSettings.getNotifypolls())) {
                            Logger.debug("email is sending to::" + contact.getEmail());
                            this.mailSender.send(preparator);
                        }
                    } catch (Exception e) {
                        //The Exception can be raised in case of User doesn't exist in the system
                        // Even in that case we need to send email for the User
                        this.mailSender.send(preparator);
                    }
                } catch (Exception e) {
                    Logger.error("email is not sent to contact:" + contact.getEmail(), e);
                }
            }
        }
    }

    public void sendInviteWithQuestionEmail(final Contact contact) throws MailException, ConfigurationException {
        Logger.debug("inside: sendInviteWithQuestionEmail ");
        IncyyteProperties ip = new IncyyteProperties(null);
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        websiteURL = InCyyteUtil.getWebURL();
        final String imageURL = icfg.getProperty(ConfigProperties.RACKSPACE_IMAGE_URL);
        final String anonymousAvatar = imageURL + "annonymous_icon.png";
        final String defaultAvatar = imageURL + "default_avatar.png";
        final String profilepic = user.getProfilePicture();
        final String votingURL = icfg.getProperty(ConfigProperties.WEBSITE_URL) + icfg.getProperty(ConfigProperties.INCYYTE_NONMEMBER_VOTING_URL);
        final String fromEmailAddress = icfg.getProperty(ConfigProperties.INCYYTE_QUESTION_POLL_EMAIL_FROM);
        Logger.debug("fromEmailAddress = " + fromEmailAddress);

        final String userEmailAddress = contact.getEmail();

        final String[] toEmailAddresses = {userEmailAddress};
        Logger.debug("Sending mail to user = " + userEmailAddress);

        String inviteeName = null;
        if (null == user)
            inviteeName = "A Friend";
        else
            inviteeName = (null != user.getFirstname() ? user.getFirstname() :
                    (null != user.getUsername() ? user.getUsername() : "A Friend"));

        Logger.debug("invitee Nme:" + inviteeName);
        String emailSubjectvar = inviteeName + " has asked for your opinion on..";
        final String emailSubject = emailSubjectvar;
        Logger.debug("emailSubject = " + emailSubject);

        final String name = inviteeName;
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                message.setTo(toEmailAddresses);
                message.setFrom(fromEmailAddress, "inCyyte");
                message.setSubject(emailSubject);
                Map<String, Object> emailModel = new HashMap<String, Object>();
                emailModel.put("inviteeName", name);
                emailModel.put("votingURL", votingURL);
                emailModel.put("invitationCode", StringUtils.defaultString(contact.getInvitationid()));
                emailModel.put("incyyteID", getIncyyte().getId());
                emailModel.put("email", contact.getEmail());

                String fmtID = null;
                if (getIncyyte().isShared()) {
                    Logger.debug("get Shared ID:  for QuestionID: " + getIncyyte().getId() + " ContactID: " + contact.getId() + " for Email: " + contact.getEmail());
                    fmtID = userDaoImpl.getShareRecord(getIncyyte().getId(), contact.getEmail()).getFmtId();
                }
                emailModel.put("contactId", (getIncyyte().isShared() ? fmtID : contact.getMemberId() == null ?
                        userDaoImpl.getPollMemberId(contact.getEmail(), getIncyyte().getId()) :
                        contact.getMemberId().toString()));

                emailModel.put("question", (getIncyyte().getIncyyte() == null ? "" : getIncyyte().getIncyyte()));
                emailModel.put("question_file", imageURL + "photo1.png");
                emailModel.put("question_ref", websiteURL);
                if (isImageFile(getIncyyte().getUploadLocation())) {
                    emailModel.put("question_file", (StringUtils.defaultString(getIncyyte().getUploadLocation())));
                    emailModel.put("question_ref", "javascript:void(0);");
                } else if (isVideoFile(getIncyyte().getUploadLocation())) {
                    emailModel.put("question_file", imageURL + "video_thumb.png");
                    emailModel.put("question_ref", (StringUtils.defaultString(getIncyyte().getUploadLocation())));
                } else if (isDocFile(getIncyyte().getUploadLocation())) {
                    emailModel.put("question_file", imageURL + "view_docs_thumb.png");
                    emailModel.put("question_ref", (StringUtils.defaultString(getIncyyte().getUploadLocation())));
                } else if (getIncyyte().getYoutubeUrl() != null) {
                    emailModel.put("question_file", imageURL + "video_thumb.png");
                    emailModel.put("question_ref", (StringUtils.defaultString(getIncyyte().getYoutubeUrl())));
                }
                emailModel.put("profilePic", (anonymous ? anonymousAvatar : profilepic == null ? defaultAvatar : profilepic.equals("") ? defaultAvatar : profilepic));
                emailModel.put("answers", getIncyyte().getAnswers());
                emailModel.put("webURL", websiteURL);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, VM_TEMPLATE_PATH + "invite_with_poll_v4.vm", emailModel);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
        Logger.debug("Email sent successfully to " + userEmailAddress + "...");
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public InCyyte getIncyyte() {
        return incyyte;
    }

    public void setIncyyte(InCyyte incyyte) {
        this.incyyte = incyyte;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public ReportPoll getReportPoll() {
        return reportPoll;
    }

    public void setReportPoll(ReportPoll reportPoll) {
        this.reportPoll = reportPoll;
    }

    public void sendInternalMail(final String emailRecipients, final String from, final String subject, final String messageContent) {
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                    message.setTo(emailRecipients);
                    message.setFrom(from, "inCyyte");
                    message.setSubject(subject);
                    message.setText(messageContent, true);
                }
            };
            this.mailSender.send(preparator);
            Logger.debug("Sent Internal Mail successfully");
        } catch (Exception e) {
            Logger.error("Exception sending email::", e);
        }
    }

    private boolean isImageFile(String fileName) {
        String ansImages[] = {".gif", ".png", ".jpg", ".jpeg", ".bmp"};
        for (String image : ansImages) {
            if (StringUtils.contains(fileName, image)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVideoFile(String fileName) {
        String ansVideos[] = {".flv", ".mp4", ".mpg", ".swf", ".wmv"};
        for (String video : ansVideos) {
            if (StringUtils.contains(fileName, video)) {
                return true;
            }
        }
        return false;
    }

    private boolean isYoutubeVideoFile(String fileName) {
        if (fileName != null && fileName.length() == 41) {
            return true;
        }
        return false;
    }

    private boolean isDocFile(String fileName) {
        String ansDocs[] = {".wpd", ".wps", ".xml", ".xlr", ".pdf", ".doc", ".docx", ".log", ".rtf", ".txt", ".csv", ".pps", ".ppt", ".xls", ".xlsx"};
        for (String doc : ansDocs) {
            if (StringUtils.contains(fileName, doc)) {
                return true;
            }
        }
        return false;
    }
}
