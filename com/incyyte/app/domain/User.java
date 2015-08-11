/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class represents a User personal details
 * For example:
 * <pre>
 * 		-A378HHSG
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private UserAddress userAddress;
	private String username;	
	private String password;
	private String firstname;
	private String lastname;
	private String nickname;
	private String email;
	private String gender;
	private String cyytePersonalEmail;
	private String secondaryPin;
	private String ageGroup;
	private Integer birthYear;
	private String acceptTerms;
	private String postalCodeArea;
	private String countyName;
	private String countryCode;
	private String signUpCountryCode;
	private String activationCode;
	private String signupmode ;
	private byte[] photo;
	
	private String status = "NON_ACTIVE"; // ENUM('ACTIVE', 'NON_ACTIVE', 'SUSPENDED') 
	private String mobile;
	private String fileName;
	
	private String profilePicture;
	private Date createdDate;
	private Date postCodeDate;
	private String userType;
	private int incyyteNo;
	private Integer occupation;
	private Integer income;
	private String displayCheckList;
	
	private Integer ethnicity;
	private Integer educationLevel;
	private String adultsInHouseHold;
	private String childrenInHouseHold;
	
	//internal object used for Voted recipients display
	private String isContact;

    public String getIsContact() {
		return isContact;
	}
	public void setIsContact(String isContact) {
		this.isContact = isContact;
	}
	public String getDisplayCheckList() {
		return displayCheckList;
	}
	public void setDisplayCheckList(String displayCheckList) {
		this.displayCheckList = displayCheckList;
	}
	private UserLocation location;

	public UserLocation getLocation() {
		return location;
	}
	public void setLocation(UserLocation location) {
		this.location = location;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public enum userType {USER,BUSINESS,ADMIN};
	
	
	public Date getPostCodeDate() {
		return postCodeDate;
	}
	public void setPostCodeDate(Date postCodeDate) {
		this.postCodeDate = postCodeDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	private String definingQuestion;
	private String resetPwdFlag;
	
	private String role;
	private Long qsInCyyteID;
	
	private List<Group> groups;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCyytePersonalEmail() {
		return cyytePersonalEmail;
	}
	public void setCyytePersonalEmail(String cyytePersonalEmail) {
		this.cyytePersonalEmail = cyytePersonalEmail;
	}
	public String getSecondaryPin() {
		return secondaryPin;
	}
	public void setSecondaryPin(String secondaryPin) {
		this.secondaryPin = secondaryPin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getAcceptTerms() {
		return acceptTerms;
	}
	public void setAcceptTerms(String acceptTerms) {
		this.acceptTerms = acceptTerms;
	}
	
	
	public String getPostalCodeArea() {
		return postalCodeArea;
	}
	public void setPostalCodeArea(String postalCodeArea) {
		this.postalCodeArea = postalCodeArea;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
	
	public String getDefiningQuestion() {
		return definingQuestion;
	}
	public void setDefiningQuestion(String definingQuestion) {
		this.definingQuestion = definingQuestion;
	}
	public String getResetPwdFlag() {
		return resetPwdFlag;
	}
	public void setResetPwdFlag(String resetPwdFlag) {
		this.resetPwdFlag = resetPwdFlag;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userAddress=" + userAddress
				+ ", username=" + username + ", password=" + password
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", nickname=" + nickname + ", email=" + email + ", gender="
				+ gender + ", cyytePersonalEmail=" + cyytePersonalEmail
				+ ", secondaryPin=" + secondaryPin + ", ageGroup=" + ageGroup
				+ ", birthYear=" + birthYear + ", acceptTerms=" + acceptTerms
				+ ", postalCodeArea=" + postalCodeArea + ", countyName="
				+ countyName + ", countryCode=" + countryCode
				+ ", signUpCountryCode=" + signUpCountryCode
				+ ", activationCode=" + activationCode + ", signupmode="
				+ signupmode + ", photo=" + Arrays.toString(photo)
				+ ", status=" + status + ", mobile=" + mobile + ", fileName="
				+ fileName + ", profilePicture=" + profilePicture
				+ ", createdDate=" + createdDate + ", postCodeDate="
				+ postCodeDate + ", userType=" + userType + ", incyyteNo="
				+ incyyteNo + ", occupation=" + occupation + ", income="
				+ income + ", displayCheckList=" + displayCheckList
				+ ", location=" + location + ", definingQuestion="
				+ definingQuestion + ", resetPwdFlag=" + resetPwdFlag
				+ ", role=" + role + ", qsInCyyteID=" + qsInCyyteID
				+ ", groups=" + groups + "]";
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getQsInCyyteID() {
		return qsInCyyteID;
	}
	public void setQsInCyyteID(Long qsInCyyteID) {
		this.qsInCyyteID = qsInCyyteID;
	}
	/**
	 * @param signupmode the signupmode to set
	 */
	public void setSignupmode(String signupmode) {
		this.signupmode = signupmode;
	}
	/**
	 * @return the signupmode
	 */
	public String getSignupmode() {
		return signupmode;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public int getIncyyteNo() {
		return incyyteNo;
	}
	public void setIncyyteNo(int incyyteNo) {
		this.incyyteNo = incyyteNo;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public Integer getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}
	public String getSignUpCountryCode() {
		return signUpCountryCode;
	}
	public void setSignUpCountryCode(String signUpCountryCode) {
		this.signUpCountryCode = signUpCountryCode;
	}	
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getOccupation() {
		return occupation;
	}
	public void setOccupation(Integer occupation) {
		this.occupation = occupation;
	}
	/**
	 * @return the ethnicity
	 */
	public Integer getEthnicity() {
		return ethnicity;
	}
	/**
	 * @param ethnicity the ethnicity to set
	 */
	public void setEthnicity(Integer ethnicity) {
		this.ethnicity = ethnicity;
	}
	/**
	 * @return the educationLevel
	 */
	public Integer getEducationLevel() {
		return educationLevel;
	}
	/**
	 * @param educationLevel the educationLevel to set
	 */
	public void setEducationLevel(Integer educationLevel) {
		this.educationLevel = educationLevel;
	}
	/**
	 * @return the adultsInHouseHold
	 */
	public String getAdultsInHouseHold() {
		return adultsInHouseHold;
	}
	/**
	 * @param adultsInHouseHold the adultsInHouseHold to set
	 */
	public void setAdultsInHouseHold(String adultsInHouseHold) {
		this.adultsInHouseHold = adultsInHouseHold;
	}
	/**
	 * @return the childrenInHouseHold
	 */
	public String getChildrenInHouseHold() {
		return childrenInHouseHold;
	}
	/**
	 * @param childrenInHouseHold the childrenInHouseHold to set
	 */
	public void setChildrenInHouseHold(String childrenInHouseHold) {
		this.childrenInHouseHold = childrenInHouseHold;
	}

}
