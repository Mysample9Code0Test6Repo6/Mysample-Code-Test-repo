package com.incyyte.app.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class BusinessAccount {
	
	private long id;
	private long userid;
	private String companyName;
	private String address1;
	private String address2;
	private String city;
	private String postalCode;
	private String country;
	private String contactEmail;
	private String websiteUrl;
	private String phone;
	private String companyLogoUrl;
	private String companyInfoPara1;
	private String companyInfoPara2;
	private String bannerUrl;
	
	public String bizAcctDetails;
	private CommonsMultipartFile uploadedLogo;
	private CommonsMultipartFile uploadedBannerLogo;
	private String fileName;
	private String bannerFileName; 
	private int makeDefault;
	
	//process action
	private String saveChanges;
	
	
	public String getSaveChanges() {
		return saveChanges;
	}
	public void setSaveChanges(String saveChanges) {
		this.saveChanges = saveChanges;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public CommonsMultipartFile getUploadedLogo() {
		return uploadedLogo;
	}
	public void setUploadedLogo(CommonsMultipartFile uploadedLogo) {
		this.uploadedLogo = uploadedLogo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getMakeDefault() {
		return makeDefault;
	}
	public void setMakeDefault(int makeDefault) {
		this.makeDefault = makeDefault;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getBizAcctDetails() {
		return bizAcctDetails;
	}
	public void setBizAcctDetails(String bizAcctDetails) {
		this.bizAcctDetails = bizAcctDetails;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompanyLogoUrl() {
		return companyLogoUrl;
	}
	public void setCompanyLogoUrl(String companyLogoUrl) {
		this.companyLogoUrl = companyLogoUrl;
	}
	public String getCompanyInfoPara1() {
		return companyInfoPara1;
	}
	public void setCompanyInfoPara1(String companyInfoPara1) {
		this.companyInfoPara1 = companyInfoPara1;
	}
	public String getCompanyInfoPara2() {
		return companyInfoPara2;
	}
	public void setCompanyInfoPara2(String companyInfoPara2) {
		this.companyInfoPara2 = companyInfoPara2;
	}
	public CommonsMultipartFile getUploadedBannerLogo() {
		return uploadedBannerLogo;
	}
	public void setUploadedBannerLogo(CommonsMultipartFile uploadedBannerLogo) {
		this.uploadedBannerLogo = uploadedBannerLogo;
	}
	

	@Override
	public String toString() {
		return "BusinessAccount [id=" + id + ", userid=" + userid
				+ ", companyName=" + companyName + ", address1=" + address1
				+ ", address2=" + address2 + ", city=" + city + ", postalCode="
				+ postalCode + ", country=" + country + ", contactEmail="
				+ contactEmail + ", websiteUrl=" + websiteUrl + ", phone="
				+ phone + ", companyLogoUrl=" + companyLogoUrl
				+ ", companyInfoPara1=" + companyInfoPara1
				+ ", companyInfoPara2=" + companyInfoPara2 + ", bannerUrl="
				+ bannerUrl + ", bizAcctDetails=" + bizAcctDetails
				+ ", uploadedLogo=" + uploadedLogo + ", uploadedBannerLogo="
				+ uploadedBannerLogo + ", fileName=" + fileName
				+ ", makeDefault=" + makeDefault + "]";
	}
	public String getBannerFileName() {
		return bannerFileName;
	}
	public void setBannerFileName(String bannerFileName) {
		this.bannerFileName = bannerFileName;
	}

	}

	

