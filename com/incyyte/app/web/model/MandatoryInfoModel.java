/**
 * 
 */
package com.incyyte.app.web.model;

/**
 * @author Remi Oseni
 *
 */
public class MandatoryInfoModel {
	
	private Long userId;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String location;
	private String postalCodeArea;
	private String countryCode;
	private String country;
	private String gender;
	private String ageGroup;
	private String birthYear;
	private Integer birthYearVal;
	private Integer occupation;
	private Integer income;
	private String incomeVal;
	
	private Integer ethnicity;
	private Integer educationLevel;
	private String adultsInHouseHold;
	private String childrenInHouseHold;
	
	private String su_password;
    private String cdnFileName;
	private String sn_mode;
	
	public String getSu_password() {
		return su_password;
	}
	public void setSu_password(String su_password) {
		this.su_password = su_password;
	}
	public String getCdnFileName() {
		return cdnFileName;
	}
	public void setCdnFileName(String cdnFileName) {
		this.cdnFileName = cdnFileName;
	}
	public String getSn_mode() {
		return sn_mode;
	}
	public void setSn_mode(String sn_mode) {
		this.sn_mode = sn_mode;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPostalCodeArea() {
		return postalCodeArea;
	}
	public void setPostalCodeArea(String postalCodeArea) {
		this.postalCodeArea = postalCodeArea;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the incomeVal
	 */
	public String getIncomeVal() {
		return incomeVal;
	}
	/**
	 * @param incomeVal the incomeVal to set
	 */
	public void setIncomeVal(String incomeVal) {
		this.incomeVal = incomeVal;
	}
	/**
	 * @return the birthYearVal
	 */
	public Integer getBirthYearVal() {
		return birthYearVal;
	}
	/**
	 * @param birthYearVal the birthYearVal to set
	 */
	public void setBirthYearVal(Integer birthYearVal) {
		this.birthYearVal = birthYearVal;
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

    @Override
    public String toString() {
        return "MandatoryInfoModel{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", postalCodeArea='" + postalCodeArea + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", ageGroup='" + ageGroup + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", birthYearVal=" + birthYearVal +
                ", occupation=" + occupation +
                ", income=" + income +
                ", incomeVal='" + incomeVal + '\'' +
                ", ethnicity=" + ethnicity +
                ", educationLevel=" + educationLevel +
                ", adultsInHouseHold='" + adultsInHouseHold + '\'' +
                ", childrenInHouseHold='" + childrenInHouseHold + '\'' +
                //", su_password='" + su_password + '\'' + -- Must not log passwords
                ", cdnFileName='" + cdnFileName + '\'' +
                ", sn_mode='" + sn_mode + '\'' +
                '}';
    }
}
