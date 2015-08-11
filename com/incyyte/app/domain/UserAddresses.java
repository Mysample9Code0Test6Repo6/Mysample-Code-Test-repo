package com.incyyte.app.domain;

public class UserAddresses {
	private Long  userId;
    private AddressType addressType;
    private String address1;
    private String address2;
    private String city;
	private String postcode;
	private String country;
	private String companyName;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public AddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
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
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "UserAddresses [userId=" + userId + ", addressType="
				+ addressType + ", address1=" + address1 + ", address2="
				+ address2 + ", city=" + city + ", postcode=" + postcode
				+ ", country=" + country + ", companyName=" + companyName + "]";
	}
}