package com.incyyte.app.web.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.incyyte.app.service.util.IncyyteCategory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.incyyte.app.domain.UserAddress;
import com.incyyte.app.domain.UserLocation;

public class UserModel {

	private long id;

	private UserAddress userAddress;

	private String username;

	private String su_password;

	private String firstname;

	private String lastname;

	private String nickname;

	private String su_email;

	private String gender;

	private String cyytePersonalEmail;

	private String secondaryPin;

	private String status = "NON_ACTIVE";

	private String mobile;

	private MultipartFile uploadedFile;

	private String confirm_email;

	private String confirmpassword;

	private String ageGroup;

	private String category;

	private boolean multi_selection;

	private boolean randomize;

	private String style;

	private String anonymity;

	private List<String> emailList = new ArrayList<String>();

	private String incyyteCode;

	private String ac_country;

	private String postcode;

	private String postal_area;

	private String login_email;

	private String login_pwd;

	private String user_email;

	private String processType;

	private IncyyteModel incyyteModel;

	private boolean chkTerms;

	private String activate_act;

	private String opinion;

	private String country;

	private String occupation = "19";

	private String sexuality;

	private String religion;

	private int income;

	private int birthYear;

	private int birthDay = 1;

	private String sn_mode;

	private int birthMonth = 1;

	private IncyyteCategory[] incyyteCategories;

	private IncyyteCategory incyyteCategory;

	private CommonsMultipartFile uploadedLogo;

	private String profilePicture;

	private int makeDefault;

	private int incyyteNo;

    private String cdnFileName;
    private String uploadName;
    private String uploadLocation;
    private UserLocation location;
    
    private Integer ethnicity;
	private Integer educationLevel;
	private String adultsInHouseHold;
	private String childrenInHouseHold;
    
    private String searchedFileNameProfile;
    
    public String getSearchedFileNameProfile() {
		return searchedFileNameProfile;
	}

	public void setSearchedFileNameProfile(String searchedFileNameProfile) {
		this.searchedFileNameProfile = searchedFileNameProfile;
	}

	public String getSearchedFileURLProfile() {
		return searchedFileURLProfile;
	}

	public void setSearchedFileURLProfile(String searchedFileURLProfile) {
		this.searchedFileURLProfile = searchedFileURLProfile;
	}

	private String searchedFileURLProfile;

    /**
	 * Updated email. Could be email1/email2
	 */
	private String updatedEmail;
	
	/**
	 * Original email. Could be email1/email2
	 */
	private String emailToChange;
	
	/**
	 * This is second email after the default email(su_email)
	 */
	private String email1;
	
	private String occupationId;
	
	private String religionId;
	
	private String cd;
	
	/**
	 * This is third email after the default email(su_email)
	 */
	private String email2;
	
	private List<String> categories;
	
	
	
	
	public UserLocation getLocation() {
		return location;
	}

	public void setLocation(UserLocation location) {
		this.location = location;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
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
	 * @return the updatedEmail
	 */
	public String getUpdatedEmail() {
		return updatedEmail;
	}

	/**
	 * @param updatedEmail the updatedEmail to set
	 */
	public void setUpdatedEmail(String updatedEmail) {
		this.updatedEmail = updatedEmail;
	}

	/**
	 * @return the emailToChange
	 */
	public String getEmailToChange() {
		return emailToChange;
	}

	/**
	 * @param emailToChange the emailToChange to set
	 */
	public void setEmailToChange(String emailToChange) {
		this.emailToChange = emailToChange;
	}
	
	/**
	 * @return the email1
	 */
	public String getEmail1() {
		return email1;
	}

	/**
	 * @param email1 the email1 to set
	 */
	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	/**
	 * @return the email2
	 */
	public String getEmail2() {
		return email2;
	}

	/**
	 * @param email2 the email2 to set
	 */
	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	/**
	 * @return Returns the category.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            The category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return Returns the ageGroup.
	 */
	public String getAgeGroup() {
		return ageGroup;
	}

	/**
	 * @param ageGroup
	 *            The ageGroup to set.
	 */
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	/**
	 * @return Returns the uploadedFile.
	 */
	public MultipartFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile
	 *            The uploadedFile to set.
	 */
	public void setUploadedFile(MultipartFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * @return Returns the multi_selection.
	 */
	public boolean isMulti_selection() {
		return multi_selection;
	}

	/**
	 * @param multi_selection
	 *            The multi_selection to set.
	 */
	public void setMulti_selection(boolean multi_selection) {
		this.multi_selection = multi_selection;
	}

	/**
	 * @return Returns the randomize.
	 */
	public boolean isRandomize() {
		return randomize;
	}

	/**
	 * @param randomize
	 *            The randomize to set.
	 */
	public void setRandomize(boolean randomize) {
		this.randomize = randomize;
	}

	/**
	 * @return Returns the style.
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style
	 *            The style to set.
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return Returns the anonymity.
	 */
	public String getAnonymity() {
		return anonymity;
	}

	/**
	 * @param anonymity
	 *            The anonymity to set.
	 */
	public void setAnonymity(String anonymity) {
		this.anonymity = anonymity;
	}

	/**
	 * @return Returns the incyyteCode.
	 */
	public String getIncyyteCode() {
		return incyyteCode;
	}

	/**
	 * @param incyyteCode
	 *            The incyyteCode to set.
	 */
	public void setIncyyteCode(String incyyteCode) {
		this.incyyteCode = incyyteCode;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public List<String> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}

	public IncyyteModel getIncyyteModel() {
		return incyyteModel;
	}

	public void setIncyyteModel(IncyyteModel incyyteModel) {
		this.incyyteModel = incyyteModel;
	}
	
	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public boolean isChkTerms() {
		return chkTerms;
	}

	public void setChkTerms(boolean chkTerms) {
		this.chkTerms = chkTerms;
	}

	public String getSu_password() {
		return su_password;
	}

	public void setSu_password(String su_password) {
		this.su_password = su_password;
	}

	public String getSu_email() {
		return su_email;
	}

	public void setSu_email(String su_email) {
		this.su_email = su_email;
	}

	public String getConfirm_email() {
		return confirm_email;
	}

	public void setConfirm_email(String confirm_email) {
		this.confirm_email = confirm_email;
	}

	public String getAc_country() {
		return ac_country;
	}

	public void setAc_country(String ac_country) {
		this.ac_country = ac_country;
	}

	public String getPostal_area() {
		return postal_area;
	}

	public void setPostal_area(String postal_area) {
		this.postal_area = postal_area;
	}

	public String getLogin_email() {
		return login_email;
	}

	public void setLogin_email(String login_email) {
		this.login_email = login_email;
	}

	public String getLogin_pwd() {
		return login_pwd;
	}

	public void setLogin_pwd(String login_pwd) {
		this.login_pwd = login_pwd;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getActivate_act() {
		return activate_act;
	}

	public void setActivate_act(String activate_act) {
		this.activate_act = activate_act;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getSexuality() {
		return sexuality;
	}

	public void setSexuality(String sexuality) {
		this.sexuality = sexuality;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}
	
	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public IncyyteCategory[] getIncyyteCategories() {
		return incyyteCategories;
	}

	public void setIncyyteCategories(IncyyteCategory[] incyyteCategories) {
		this.incyyteCategories = incyyteCategories;
	}
	
	public IncyyteCategory getIncyyteCategory() {
		return incyyteCategory;
	}

	public void setIncyyteCategory(IncyyteCategory incyyteCategory) {
		this.incyyteCategory = incyyteCategory;
	}

	


	/**
	 * @param sn_mode the sn_mode to set
	 */
	public void setSn_mode(String sn_mode) {
		this.sn_mode = sn_mode;
	}

	/**
	 * @return the sn_mode
	 */
	public String getSn_mode() {
		return sn_mode;
	}

	/**
	 * @return the uploadedLogo
	 */
	public CommonsMultipartFile getUploadedLogo() {
		return uploadedLogo;
	}

	/**
	 * @param uploadedLogo the uploadedLogo to set
	 */
	public void setUploadedLogo(CommonsMultipartFile uploadedLogo) {
		this.uploadedLogo = uploadedLogo;
	}

	/**
	 * @return the makeDefault
	 */
	public int getMakeDefault() {
		return makeDefault;
	}

	/**
	 * @param makeDefault the makeDefault to set
	 */
	public void setMakeDefault(int makeDefault) {
		this.makeDefault = makeDefault;
	}

	/**
	 * @return the categories
	 */
	public List<String> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * @return the occupationId
	 */
	public String getOccupationId() {
		return occupationId;
	}

	/**
	 * @param occupationId the occupationId to set
	 */
	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}

	/**
	 * @return the religionId
	 */
	public String getReligionId() {
		return religionId;
	}

	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}


    public String getCdnFileName() {
        return cdnFileName;
    }

    public void setCdnFileName(String cdnFileName) {
        this.cdnFileName = cdnFileName;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getUploadLocation() {
        return uploadLocation;
    }

    public void setUploadLocation(String uploadLocation) {
        this.uploadLocation = uploadLocation;
    }

	public int getIncyyteNo() {
		return incyyteNo;
	}

	public void setIncyyteNo(int incyyteNo) {
		this.incyyteNo = incyyteNo;
	}

    @Override
	public String toString() {
		return "UserModel [id=" + id + ", userAddress=" + userAddress
				+ ", username=" + username + ", su_password=" + su_password
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", nickname=" + nickname + ", su_email=" + su_email
				+ ", gender=" + gender + ", cyytePersonalEmail="
				+ cyytePersonalEmail + ", secondaryPin=" + secondaryPin
				+ ", status=" + status + ", mobile=" + mobile
				+ ", uploadedFile=" + uploadedFile + ", confirm_email="
				+ confirm_email + ", confirmpassword=" + confirmpassword
				+ ", ageGroup=" + ageGroup + ", category=" + category
				+ ", multi_selection=" + multi_selection + ", randomize="
				+ randomize + ", style=" + style + ", anonymity=" + anonymity
				+ ", emailList=" + emailList + ", incyyteCode=" + incyyteCode
				+ ", ac_country=" + ac_country + ", postcode=" + postcode
				+ ", postal_area=" + postal_area + ", login_email="
				+ login_email + ", login_pwd=" + login_pwd + ", user_email="
				+ user_email + ", processType=" + processType
				+ ", incyyteModel=" + incyyteModel + ", chkTerms=" + chkTerms
				+ ", activate_act=" + activate_act + ", opinion=" + opinion
				+ ", country=" + country + ", occupation=" + occupation
				+ ", sexuality=" + sexuality + ", religion=" + religion
				+ ", income=" + income + ", birthYear=" + birthYear
				+ ", birthDay=" + birthDay + ", sn_mode=" + sn_mode
				+ ", birthMonth=" + birthMonth + ", incyyteCategories="
				+ Arrays.toString(incyyteCategories) + ", incyyteCategory="
				+ incyyteCategory + ", uploadedLogo=" + uploadedLogo
				+ ", profilePicture=" + profilePicture + ", makeDefault="
				+ makeDefault + ", incyyteNo=" + incyyteNo + ", cdnFileName="
				+ cdnFileName + ", uploadName=" + uploadName
				+ ", uploadLocation=" + uploadLocation + ", location="
				+ location + ", searchedFileNameProfile="
				+ searchedFileNameProfile + ", searchedFileURLProfile="
				+ searchedFileURLProfile + ", updatedEmail=" + updatedEmail
				+ ", emailToChange=" + emailToChange + ", email1=" + email1
				+ ", occupationId=" + occupationId + ", religionId="
				+ religionId + ", cd=" + cd + ", email2=" + email2
				+ ", categories=" + categories + "]";
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