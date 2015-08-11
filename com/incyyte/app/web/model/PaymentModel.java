package com.incyyte.app.web.model;

import java.math.BigDecimal;

import com.incyyte.app.service.util.Logger;

public class PaymentModel {
	private String title;
	private String paymentFirstName;
	private String paymentLastName;
	private String addressPayment1;
	private String addressPayment2;
	private String addressPaymentBilling1;
	private String addressPaymentBilling2;
	private String city;
	private String postcode;
	private String country;
	private String email;
	private String mobileNumber;
	private String companyName;
	private String cityBilling;
	private String postcodeBilling;
	private String countryBilling;

	private String  PSPID;
	private String  orderID;
	private String  amount;
	private String  currency;
	private String  amountWOTax;
	private String  language;
	private String  SHASign;
	private String  customerEmail;
	private String  paymentReference;
	private String  paymentStatus;
	private String  errorReason ;
	private String  transactionDate;
	private String  SHASignOut;
	private String displayAmountWOTax;
	private String displayAmount;

	public String getAmountWOTax() {
		return amountWOTax;
	}
	public void setAmountWOTax(String amountWOTax) {
		this.amountWOTax = amountWOTax;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPaymentFirstName() {
		return paymentFirstName;
	}
	public void setPaymentFirstName(String paymentFirstName) {
		this.paymentFirstName = paymentFirstName;
	}
	public String getPaymentLastName() {
		return paymentLastName;
	}
	public void setPaymentLastName(String paymentLastName) {
		this.paymentLastName = paymentLastName;
	}
	public String getAddressPayment1() {
		return addressPayment1;
	}
	public void setAddressPayment1(String addressPayment1) {
		this.addressPayment1 = addressPayment1;
	}
	public String getAddressPayment2() {
		return addressPayment2;
	}
	public void setAddressPayment2(String addressPayment2) {
		this.addressPayment2 = addressPayment2;
	}
	public String getAddressPaymentBilling1() {
		return addressPaymentBilling1;
	}
	public void setAddressPaymentBilling1(String addressPaymentBilling1) {
		this.addressPaymentBilling1 = addressPaymentBilling1;
	}
	public String getAddressPaymentBilling2() {
		return addressPaymentBilling2;
	}
	public void setAddressPaymentBilling2(String addressPaymentBilling2) {
		this.addressPaymentBilling2 = addressPaymentBilling2;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCityBilling() {
		return cityBilling;
	}
	public void setCityBilling(String cityBilling) {
		this.cityBilling = cityBilling;
	}
	public String getPostcodeBilling() {
		return postcodeBilling;
	}
	public void setPostcodeBilling(String postcodeBilling) {
		this.postcodeBilling = postcodeBilling;
	}
	public String getCountryBilling() {
		return countryBilling;
	}
	public void setCountryBilling(String countryBilling) {
		this.countryBilling = countryBilling;
	}
	public String getPSPID() {
		return PSPID;
	}
	public void setPSPID(String pSPID) {
		PSPID = pSPID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSHASign() {
		return SHASign;
	}
	public void setSHASign(String sHASign) {
		SHASign = sHASign;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getPaymentReference() {
		return paymentReference;
	}
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getErrorReason() {
		return errorReason;
	}
	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getSHASignOut() {
		return SHASignOut;
	}
	public void setSHASignOut(String sHASignOut) {
		SHASignOut = sHASignOut;
	}
	public String getDisplayAmountWOTax() {
        BigDecimal amt = new BigDecimal(amountWOTax);
        amt = amt.divide(new BigDecimal("100"));
        if (amt.compareTo(BigDecimal.ONE) >= 0) {
            amt = amt.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            displayAmountWOTax = String.valueOf(amt);
        } else {
            displayAmountWOTax = amountWOTax;
        }
        return displayAmountWOTax;
    }

    public String getDisplayAmount() {
        BigDecimal amt = new BigDecimal(amount);
        amt = amt.divide(new BigDecimal("100"));
        if (amt.compareTo(BigDecimal.ONE) >= 0) {
            amt = amt.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            displayAmount = String.valueOf(amt);
        } else {
            displayAmount = amount;
        }
        return displayAmount; 
    } 

	@Override
	public String toString() {
		return "PaymentModel [title=" + title + ", paymentFirstName="
				+ paymentFirstName + ", paymentLastName=" + paymentLastName
				+ ", addressPayment1=" + addressPayment1 + ", addressPayment2="
				+ addressPayment2 + ", addressPaymentBilling1="
				+ addressPaymentBilling1 + ", addressPaymentBilling2="
				+ addressPaymentBilling2 + ", city=" + city + ", postcode="
				+ postcode + ", country=" + country + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", companyName="
				+ companyName + ", cityBilling=" + cityBilling
				+ ", postcodeBilling=" + postcodeBilling + ", countryBilling="
				+ countryBilling + ", PSPID=" + PSPID + ", orderID=" + orderID
				+ ", amount=" + amount + ", currency=" + currency
				+ ", amountWOTax=" + amountWOTax + ", language=" + language
				+ ", SHASign=" + SHASign + ", customerEmail=" + customerEmail
				+ ", paymentReference=" + paymentReference + ", paymentStatus="
				+ paymentStatus + ", errorReason=" + errorReason
				+ ", transactionDate=" + transactionDate + ", SHASignOut="
				+ SHASignOut + ", DisplayAmountWOTax=" + displayAmountWOTax
				+ ", DisplayAmount=" + displayAmount + "]";
	}
}