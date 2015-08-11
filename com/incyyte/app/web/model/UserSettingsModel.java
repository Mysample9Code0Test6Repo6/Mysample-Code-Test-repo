package com.incyyte.app.web.model;

/**
 * @author Dev1
 *
 */
public class UserSettingsModel {
	
	private String uniquePageName;
	
	/**
	 * 
	 */
	private String password;
	
	/**
	 * 
	 */
	private String newPassword;
	
	/**
	 * 
	 */
	private int userId;
	
	/**
	 * 
	 */
	private String optInPriceTag;
	
	/**
	 * 
	 */
	private String displayRating = "Y";
	
	/**
	 * 
	 */
	private String displaypolls;
	
	/**
	 * 
	 */
	private String disbaleIncyytes;
	
	/**
	 * 
	 */
	private String disableComments;
	
	/**
	 * 
	 */
	private String restrictComments;
	
	/**
	 * 
	 */
	private String notifypolls;
	
	/**
	 * 
	 */
	private String notifyNews;
	
	/**
	 * 
	 */
	private String notifyFollower;

	/**
	 * @return the uniquePageName
	 */
	public String getUniquePageName() {
		return uniquePageName;
	}

	/**
	 * @param uniquePageName the uniquePageName to set
	 */
	public void setUniquePageName(String uniquePageName) {
		this.uniquePageName = uniquePageName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the optInPriceTag
	 */
	public String getOptInPriceTag() {
		return optInPriceTag;
	}

	/**
	 * @param optInPriceTag the optInPriceTag to set
	 */
	public void setOptInPriceTag(String optInPriceTag) {
		this.optInPriceTag = optInPriceTag;
	}

	/**
	 * @return the displayRating
	 */
	public String getDisplayRating() {
		return displayRating;
	}

	/**
	 * @param displayRating the displayRating to set
	 */
	public void setDisplayRating(String displayRating) {
		this.displayRating = displayRating;
	}

	/**
	 * @return the displaypolls
	 */
	public String getDisplaypolls() {
		return displaypolls;
	}

	/**
	 * @param displaypolls the displaypolls to set
	 */
	public void setDisplaypolls(String displaypolls) {
		this.displaypolls = displaypolls;
	}

	/**
	 * @return the disbaleIncyytes
	 */
	public String getDisbaleIncyytes() {
		return disbaleIncyytes;
	}

	/**
	 * @param disbaleIncyytes the disbaleIncyytes to set
	 */
	public void setDisbaleIncyytes(String disbaleIncyytes) {
		this.disbaleIncyytes = disbaleIncyytes;
	}

	/**
	 * @return the disableComments
	 */
	public String getDisableComments() {
		return disableComments;
	}

	/**
	 * @param disableComments the disableComments to set
	 */
	public void setDisableComments(String disableComments) {
		this.disableComments = disableComments;
	}

	/**
	 * @return the restrictComments
	 */
	public String getRestrictComments() {
		return restrictComments;
	}

	/**
	 * @param restrictComments the restrictComments to set
	 */
	public void setRestrictComments(String restrictComments) {
		this.restrictComments = restrictComments;
	}

	/**
	 * @return the notifypolls
	 */
	public String getNotifypolls() {
		return notifypolls;
	}

	/**
	 * @param notifypolls the notifypolls to set
	 */
	public void setNotifypolls(String notifypolls) {
		this.notifypolls = notifypolls;
	}

	/**
	 * @return the notifyNews
	 */
	public String getNotifyNews() {
		return notifyNews;
	}

	/**
	 * @param notifyNews the notifyNews to set
	 */
	public void setNotifyNews(String notifyNews) {
		this.notifyNews = notifyNews;
	}

	/**
	 * @return the notifyFollower
	 */
	public String getNotifyFollower() {
		return notifyFollower;
	}

	/**
	 * @param notifyFollower the notifyFollower to set
	 */
	public void setNotifyFollower(String notifyFollower) {
		this.notifyFollower = notifyFollower;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	@Override
	public String toString() {
		return "UserSettingsModel [uniquePageName=" + uniquePageName
				+ ", password=" + password + ", newPassword=" + newPassword
				+ ", userId=" + userId + ", optInPriceTag=" + optInPriceTag
				+ ", displayRating=" + displayRating + ", displaypolls="
				+ displaypolls + ", disbaleIncyytes=" + disbaleIncyytes
				+ ", disableComments=" + disableComments
				+ ", restrictComments=" + restrictComments + ", notifypolls="
				+ notifypolls + ", notifyNews=" + notifyNews
				+ ", notifyFollower=" + notifyFollower + "]";
	}

}
