package com.incyyte.app.web.model;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UserProfileModel {
	
	/**
	 * 
	 */
	private String filename;
	
    /**
     * 
     */
    private CommonsMultipartFile fileData;
    
    /**
     * 
     */
    private String name;
    
    /**
     * 
     */
    private String userName;
    
    /**
     * 
     */
    private String opinion;
    
    /**
     * 
     */
    private String location;
    
    /**
     * 
     */
    private String postCode;
    
    /**
     * 
     */
    private String mobile;
    
    /**
     * 
     */
    private String[] emails;
    
    /**
     * 
     */
    private Date dob;
    
    /**
     * 
     */
    private String gender;
    
    /**
     * 
     */
    private String occupation;
    
    /**
     * 
     */
    private String religion;
    
    /**
     * 
     */
    private String sexuality;
    
    /**
     * 
     */
    private String income;
    
    /**
     * 
     */
    private String[] likings;

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the fileData
	 */
	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the opinion
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * @param opinion the opinion to set
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the emails
	 */
	public String[] getEmails() {
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(String[] emails) {
		this.emails = emails;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the sexuality
	 */
	public String getSexuality() {
		return sexuality;
	}

	/**
	 * @param sexuality the sexuality to set
	 */
	public void setSexuality(String sexuality) {
		this.sexuality = sexuality;
	}

	/**
	 * @return the income
	 */
	public String getIncome() {
		return income;
	}

	/**
	 * @param income the income to set
	 */
	public void setIncome(String income) {
		this.income = income;
	}

	/**
	 * @return the likings
	 */
	public String[] getLikings() {
		return likings;
	}

	/**
	 * @param likings the likings to set
	 */
	public void setLikings(String[] likings) {
		this.likings = likings;
	}
}
