/**
 * 
 */
package com.incyyte.app.web.model;

/**
 * @author RemiOseni
 *
 */
public class EmailModel {
	
	private String emailTo;
	private String emailFrom;
	private String senderName;
	private String telephone;
	private String message;
	/**
	 * @return the emailTo
	 */
	public String getEmailTo() {
		return emailTo;
	}
	/**
	 * @param emailTo the emailTo to set
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	/**
	 * @return the emailFrom
	 */
	public String getEmailFrom() {
		return emailFrom;
	}
	/**
	 * @param emailFrom the emailFrom to set
	 */
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}
	/**
	 * @param senderName the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
