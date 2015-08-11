package com.incyyte.app.manager;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.IncyyteProperties;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserContactModel;

public class EmailSender implements Runnable{


	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private UserDao userDaoImpl;

	private static final String VM_TEMPLATE_LOC	= "vmTemplate";
	private static String VM_TEMPLATE_PATH	= VM_TEMPLATE_LOC + "/"; 
	private String runFlag = "invoke method";
	private User user = null;
	private UserContactModel cuser = null;
	private String newPassword = null;
	private String postUrl;
	private String context;
	
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

	public void sendNotificationsEmail(final User user,String mode,String emailaddress) throws MailException, ConfigurationException{		
		Logger.debug("inside: sendNotificationsEmail ");
		IncyyteProperties ip = new IncyyteProperties(null);
		ConfigManager icfg = ConfigManager.getInstance();
		icfg.setIncyyteProperties(ip);
		String emailSubjectvar=null;
				
		
		emailSubjectvar = icfg.getProperty(ConfigProperties.NOTIFICATION_MAIL_SUBJECT);
		
		final String emailSubject = emailSubjectvar;
		Logger.debug("emailSubject = "+emailSubject);
		
		final String fromEmailAddress = icfg.getProperty(ConfigProperties.PROP_EMAIL_FROM_NOTIFY);
		Logger.debug("fromEmailAddress = "+fromEmailAddress);
		
		final String userEmailAddress = user.getEmail();
				
		//final String[] bccEmailAddresses ={"xxx@incyyte.com"};
		final String[] toEmailAddresses = {emailaddress}; 		
		Logger.debug("Sending mail to user = "+userEmailAddress);
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
		         public void prepare(MimeMessage mimeMessage) throws Exception {
		            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);			            
		            
		            message.setTo(toEmailAddresses);
		            message.setFrom(fromEmailAddress); 
		            //message.setBcc(bccEmailAddresses);
		            message.setSubject(emailSubject);
		            Map emailModel = new HashMap();
		            emailModel.put("cdnUrl",getCDNBaseURL());
		            emailModel.put("username",user.getUsername()); 
		            
					String text = VelocityEngineUtils.mergeTemplateIntoString(
		               velocityEngine, VM_TEMPLATE_PATH + "welcome_email_v4.vm", emailModel);
					
					
		            message.setText(text, true);
		         }	
		      };
		      this.mailSender.send(preparator);		      
		      Logger.debug("Email sent successfully to "+userEmailAddress+"...");
	}
	
	
		
		private String getCDNBaseURL() throws ConfigurationException{
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
			if (getRunFlag().equals(Constants.SEND_WELCOME_EMAIL)){
				try {
					sendNotificationsEmail(getUser(), null,null);
				} catch (MailException e) {
					logMailError2DB(getUser(), Constants.SEND_WELCOME_EMAIL);
					Logger.warn("MailException",e);
				} catch (ConfigurationException e) {
					logMailError2DB(getUser(), Constants.SEND_WELCOME_EMAIL);
					Logger.warn("ConfigurationException",e);
				}
			
		}
		
		}
		private void logMailError2DB(User user, String emailType){
			//User Email Failed, Log into DB for Auto Resend
			userDaoImpl.logFailedEmails(user, emailType);
		}

		
		
		
		public void sendInviteEmail(final UserContactModel user) throws MailException, ConfigurationException{	
			Logger.debug("inside: sendInviteEmail ");
			IncyyteProperties ip = new IncyyteProperties(null);
			ConfigManager icfg = ConfigManager.getInstance();
			icfg.setIncyyteProperties(ip);
			String emailSubjectvar=null;
					
			emailSubjectvar = icfg.getProperty(ConfigProperties.PROP_EMAIL_INVITE_SUBJECT);
			
			final String emailSubject = emailSubjectvar;
			Logger.debug("emailSubject = "+emailSubject);
			
			final String fromEmailAddress = icfg.getProperty(ConfigProperties.PROP_EMAIL_FROM);
			Logger.debug("fromEmailAddress = "+fromEmailAddress);
			
			final String userEmailAddress = user.getEmail();
					
			//final String[] bccEmailAddresses ={"xxx@incyyte.com"};
			final String[] toEmailAddresses = {userEmailAddress}; 		
			Logger.debug("Sending mail to user = "+userEmailAddress);
			
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
			         public void prepare(MimeMessage mimeMessage) throws Exception {
			            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);			            
			            
			            message.setTo(toEmailAddresses);
			            message.setFrom(fromEmailAddress); 
			            //message.setBcc(bccEmailAddresses);
			            message.setSubject(emailSubject);
			            Map emailModel = new HashMap();
			            emailModel.put("cdnUrl",getCDNBaseURL());
			            emailModel.put("username",user.getFirstname()); 
			         
			            
						String text = VelocityEngineUtils.mergeTemplateIntoString(
			               velocityEngine, VM_TEMPLATE_PATH + "incyyte_invite.vm", emailModel);
						
						
			            message.setText(text, true);
			         }	
			      };
			      this.mailSender.send(preparator);		      
			      Logger.debug("Email sent successfully to "+userEmailAddress+"...");
		}
		public void setCuser(UserContactModel cuser) {
			this.cuser = cuser;
		}
		public UserContactModel getCuser() {
			return cuser;
		}
		
		public void sendPostScriptEmail(final User user, final String postUrl) throws MailException, ConfigurationException{		
			Logger.debug("inside: sendPostScriptEmail ");
			IncyyteProperties ip = new IncyyteProperties(null);
			ConfigManager icfg = ConfigManager.getInstance();
			icfg.setIncyyteProperties(ip);
			String emailSubjectvar=null;

			emailSubjectvar = icfg.getProperty(ConfigProperties.POSTSCRIPT_MAIL_SUBJECT);

			final String emailSubject = emailSubjectvar;
			Logger.debug("emailSubject = "+emailSubject);

			final String fromEmailAddress = icfg.getProperty(ConfigProperties.PROP_EMAIL_FROM);
			Logger.debug("fromEmailAddress = "+fromEmailAddress);

			final String userEmailAddress = user.getEmail();

			final String[] toEmailAddresses = {userEmailAddress}; 		
			Logger.debug("Sending mail to user = "+userEmailAddress);

			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);			            

					message.setTo(toEmailAddresses);
					message.setFrom(fromEmailAddress); 
					//message.setBcc(bccEmailAddresses);
					message.setSubject(emailSubject);
					Map<String, Object> emailModel = new HashMap<String,Object>();
					emailModel.put("cdnUrl",getCDNBaseURL());
					emailModel.put("username",user.getUsername()); 
					emailModel.put("postUrl",postUrl);

					String text = VelocityEngineUtils.mergeTemplateIntoString(
							velocityEngine, VM_TEMPLATE_PATH + "postscript_email.vm", emailModel);


					message.setText(text, true);
				}	
			};
			this.mailSender.send(preparator);		      
			Logger.debug("Email sent successfully to "+userEmailAddress+"...");
		}
		
		
		

}


