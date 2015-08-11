package com.incyyte.app.service.Exceptions;

public interface ExceptionMessages {
	//FP - Forgotten Password
	public final static String FP_INACTIVE_ACCT_MSG = "This account in not activated.";	
	public final static String FP_DUPLICATE_EMAIL_FOUND_MSG = "This email address already exist.";
	public final static String FP_NO_EMAIL_FOUND_MSG = "An Account does not exist with this email address.";
	public final static String FP_SEND_EMAIL_FAILED_MSG = "An Error was thrown while sending email to user: MSG - ";
	public final static String FP_PWD_NOT_FOUND_MSG = "This password is invalid for this user";
	
	//LI - LOGIN
	public final static String LI_INVALID_CREDENTIALS_MSG = "Entered Email or Password are invalid";
	public final static String LI_INACTIVE_ACT_MSG = "This account is Not activated.";
	public final static String LI_DEACTIVED_ACCT_MSG = "This account is Deactivated. Please Reactivate the Account.";
	
	//IC8 - INCYYTE
	public final static String IC8_MISSING_ID_MSG = "InCyyte ID is missing";
	public final static String IC8_MISSING_XMLSTRING_MSG = "XmlString data is missing";
	
	//RSP - RESPONSE
	public final static String RSP_CONTACT_RESPONDED_MSG = "This contact has already responded to this InCyyte.";
	
	//AVT - ACTIVATION
	public final static String AVT_NO_EMAIL_FOUND_MSG = "An Account does not exist with this email address.";
	public final static String AVT_ACCOUNT_ACTIVE_MSG = "This account is already activated.";
	public final static String AVT_INVALID_ACTIVATION_CODE_MSG = "This activation code is not valid for this email address.";


}
