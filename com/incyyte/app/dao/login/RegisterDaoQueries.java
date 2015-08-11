package com.incyyte.app.dao.login;

public class RegisterDaoQueries {

    private static String USERS_SELECT = "SELECT u.userid " +
            ",u.username " +
            ",u.password " +
            ",u.firstname " +
            ",u.lastname " +
            ",u.nickname " +
            ",u.email " +
            ",u.gender " +
            ",u.cyyte_personal_mail " +
            ",u.secondary_pin " +
            ",u.status " +
            ",u.mobile " +
            ",u.cdn_file_name " +
            ",u.created_date " +
            ",u.modified_date " +
            ",u.created_by " +
            ",u.modified_by " +
            ",u.ageGroup " +
            ",u.acceptTerms " +
            ",user_domain.postcode " +
            ",u.postcode_date " +
            ",user_domain.country_code " +
            ",user_domain.county_name " +
            ",u.activationID " +
            ",u.defining_question " +
            ",u.reset_pwd_flag " +
            ",u.upload_location " +
            ",u.upload_name " +
            ",u.user_type " +
            ",u.sn_profile_url " +
            ",u.birthYear " +
            ",u.occupation " +
            ",u.income " +            
			",u.ethnicity " +
			",u.education_level " +
			",u.adults_in_houseHold " +
			",u.children_in_houseHold " +            
            ",u.display_checklist"
            ;

    private static StringBuffer signupUserQuery2 = new StringBuffer(
			"INSERT INTO USERS " +
			"(userid, username, email, password, reset_pwd_flag, " +
			"status, activationID , created_by, categories, gender, " +
			"created_date, defining_question,user_type) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,sysdate(), 'I use inCyyte to get opinions on my thoughts and questions from groups of friends, workmates & my communities','USER') " );
	
	
	private static StringBuffer signupUserQuerySN = new StringBuffer(			
			"INSERT INTO USERS " +
			"(userid, username, email, password, " +
			"status, activationID , created_by, " +
			"created_date,signup_mode,firstname,lastname,gender,birthYear,categories,defining_question,sn_profile_url,user_type) " +
			"VALUES(?,?,?,?,?, ?,?, sysdate(),?,?,?,?,?,?,?,?,'USER') " );
	
	private static StringBuffer userSettingsQuery = new StringBuffer(		
			"INSERT INTO USER_SETTINGS (userid) VALUES(?) " );
	
	private static StringBuffer userEmailsQuery = new StringBuffer(		
			"INSERT INTO USERS_EMAILS (fk_userid,username,email,default_email) VALUES(?,?,?,1) " );
	
	private static StringBuffer userSULocatorQuery = new StringBuffer(		
		"INSERT INTO users_signup_location " +
		"(fk_userID, ip_address, country_code, country_name, region, region_name, city, postal_code, latitude, longitude) " +
		"VALUES (?,?,?,?,?,?,?,?,?,?) " );
	
	private static StringBuffer userSULocatorByIdQuery = new StringBuffer(		
			"SELECT ip_address, country_code, country_name, region, region_name, city, postal_code, latitude, longitude " +
			"FROM users_signup_location " +
			"WHERE fk_userID = ? " );
	
	private static StringBuffer activateUserQuery = new StringBuffer(			
			"UPDATE USERS SET status = 'ACTIVE', modified_date = sysdate() WHERE userid = ?" );

	private static StringBuffer activateUserByEmailQuery = new StringBuffer(			
		"UPDATE USERS SET status = 'ACTIVE', modified_date = sysdate() WHERE email = ?" );
	
	private static StringBuffer activateMemberByEmailQuery = new StringBuffer(			
	"UPDATE contacts SET status = 'M', modified_date = sysdate() WHERE email = ?" );

	private static StringBuffer activateUserByActvCodeQuery = new StringBuffer(			
		"UPDATE USERS SET status = 'ACTIVE', modified_date = sysdate() WHERE activationID = ?" );
	
	private static StringBuffer emailActivationQuery = new StringBuffer(			
			"SELECT EMAIL FROM USERS WHERE  activationID = ?" );

    private static StringBuffer validActivateCodeQuery = new StringBuffer(
	"SELECT COUNT(*) FROM USERS WHERE userid = ? AND activationID = ?" );
	
	private static StringBuffer loginQuery = new StringBuffer(
			"SELECT COUNT(EMAIL) FROM USERS WHERE " +
			"EMAIL = ? AND PASSWORD=? AND STATUS ='ACTIVE' ");
	
	private static StringBuffer userEmailByInviteidQuery = new StringBuffer("Select email from contacts where invitationid = ?");

	private static StringBuffer inviteridQuery = new StringBuffer("Select fk_userid  from contacts where invitationid = ?");
	
	private static StringBuffer userDetailsByEmailQuery = new StringBuffer(USERS_SELECT + " FROM USERS u left join USER_DOMAIN  on u.userid = user_id WHERE email = ? " );
	
	private static StringBuffer userDetailsByEmalAndCodeQuery = new StringBuffer(
            USERS_SELECT + " FROM USERS u left join USER_DOMAIN on u.userid = user_id " + " WHERE email = ? AND activationID = ? " );
	
	private static StringBuffer userDetailsByCodeQuery = new StringBuffer(
            USERS_SELECT + " FROM USERS u left join USER_DOMAIN on u.userid = user_id " + " WHERE activationID = ? " );

    public static StringBuffer userDetailsByContactId = new StringBuffer(
            USERS_SELECT + " FROM USERS u left join USER_DOMAIN on u.userid = user_id join contacts c on c.email = u.email WHERE c.contactid = ? " );

    private static StringBuffer userByIdQuery = new StringBuffer(
            USERS_SELECT + " FROM USERS u left join USER_DOMAIN on u.userid = user_id " + " WHERE userid = ? " );
	
	private static StringBuffer userByUserNameQuery = new StringBuffer(
            USERS_SELECT + " FROM USERS u left join USER_DOMAIN on u.userid = user_id " + " WHERE UPPER(username) = ? " );
	
	private static StringBuffer userDetailsByEmailOrUsernameQuery = new StringBuffer(
            USERS_SELECT + " FROM users u left join user_domain on u.userid = user_id " + " WHERE email like ? OR username like ? " );
	
	private static StringBuffer userDetailsQuery = new StringBuffer(
            USERS_SELECT + " FROM USERS u left join USER_DOMAIN on u.userid = user_id ");
	
	private static StringBuffer userByUserNameORUniqueNameQuery = new StringBuffer(
			USERS_SELECT + " FROM USERS u left join USER_DOMAIN ON u.userid = user_id left join USER_SETTINGS s ON u.userid = s.userid WHERE u.username = ? OR s.unique_page_name = ? ");
	
	private static StringBuffer invitedemailByInvitationId = new StringBuffer(
				" select u.email from contacts c,users u  " +
				" where invitationid= ? " +
			    " and u.userid=c.fk_userid ") ;
	public static StringBuffer getInvitedemailByInvitationId() {
		return invitedemailByInvitationId;
	}
	private static StringBuffer invitedIdByInvitationId = new StringBuffer(
	"select u.userid from users u,contacts c "+
	"where c.invitationid=? and  u.email=c.email");
	
	public static StringBuffer getInvitedIdByInvitationId() {
		return invitedIdByInvitationId;
	}
	private static StringBuffer validEmailPasswordComboQuery = new StringBuffer(
		"SELECT COUNT(*) FROM USERS WHERE email = ? AND password = ? " );

	private static StringBuffer validAdminEmailPasswordComboQuery = new StringBuffer(
		"SELECT COUNT(*) FROM USERS WHERE email = ? AND password = ? AND user_type = 'ADMIN' " );

	private static StringBuffer accountActiveQuery = new StringBuffer(
		"SELECT COUNT(*) FROM USERS WHERE email = ? AND status = 'ACTIVE' " );

	private static StringBuffer validActivationCodeQuery = new StringBuffer(
		"SELECT count(*) FROM users WHERE email = ? AND activationID = ? " );
	
	private static StringBuffer uniqueActivationCodeQuery = new StringBuffer(			
		"SELECT COUNT(activationID) FROM USERS WHERE activationID=? ");

	public static StringBuffer getUniqueActivationCodeQuery() {
		return uniqueActivationCodeQuery;
	}


	private static StringBuffer verifyActivationQuery = new StringBuffer(			
	"SELECT COUNT(activationID) FROM USERS WHERE activationID= ? AND STATUS=? ");

	private static StringBuffer emailExistsQuery = new StringBuffer(			
	"SELECT COUNT(email) FROM USERS WHERE email= ?");

	public static StringBuffer getEmailExistsQuery() {
		return emailExistsQuery;
	}

	
	public static StringBuffer getUserSettingsQuery() {
		return userSettingsQuery;
	}
	
	public static StringBuffer getUserEmailsQuery() {
		return userEmailsQuery;
	}

	public static StringBuffer getUserSULocatorQuery() {
		return userSULocatorQuery;
	}

	
	public static StringBuffer getVerifyActivationQuery() {
		return verifyActivationQuery;
	}
	
	public static StringBuffer isValidActivationCodeQuery() {
		return validActivationCodeQuery;
	}
	
	public static StringBuffer isValidEmailPasswordQuery() {
		return validEmailPasswordComboQuery;
	}

	public static StringBuffer isValidAdminEmailPasswordQuery() {
		return validAdminEmailPasswordComboQuery;
	}

	public static StringBuffer getAccountActiveQuery() {
		return accountActiveQuery;
	}
	
	public static StringBuffer getSignupUserQuery() {
		return signupUserQuery2;
	}
	
	public static StringBuffer getActivateUserQuery() {
		return activateUserQuery;
	}

	public static StringBuffer getActivateUserByEmailQuery() {
		return activateUserByEmailQuery;
	}
	
	public static StringBuffer getActivateUserByActvCodeQuery() {
		return activateUserByActvCodeQuery;
	}
	
	public static StringBuffer isValidActivateCodeQuery() {
		return validActivateCodeQuery;
	}
	public static StringBuffer getLoginQuery() {
		return loginQuery;
	}
	public static StringBuffer getUserDetailsByEmailQuery() {
		return userDetailsByEmailQuery;
	}
	
	public static StringBuffer getUserDetailsByCodeQuery() {
		return userDetailsByCodeQuery;
	}

	public static StringBuffer getUserDetailsByEmailOrUsernameQuery() {
		return userDetailsByEmailOrUsernameQuery;
	}	

	public static StringBuffer getUserDetailsQuery() {
		return userDetailsQuery;
	}

	public static StringBuffer getUserByIdQuery() {
		return userByIdQuery;
	}

	public static void setActivateMemberByEmailQuery(
			StringBuffer activateMemberByEmailQuery) {
		RegisterDaoQueries.activateMemberByEmailQuery = activateMemberByEmailQuery;
	}

	public static StringBuffer getActivateMemberByEmailQuery() {
		return activateMemberByEmailQuery;
	}

	public static String  getEmailActivationQuery() {
		// TODO Auto-generated method stub
		return emailActivationQuery.toString();
	}

	public static void setEmailActivationQuery(StringBuffer emailActivationQuery) {
		RegisterDaoQueries.emailActivationQuery = emailActivationQuery;
	}
	public static StringBuffer getUserByUserNameQuery() {
		return userByUserNameQuery;
	}
	/**
	 * @param signupUserQuerySN the signupUserQuerySN to set
	 */
	public static void setSignupUserQuerySN(StringBuffer signupUserQuerySN) {
		RegisterDaoQueries.signupUserQuerySN = signupUserQuerySN;
	}
	/**
	 * @return the signupUserQuerySN
	 */
	public static StringBuffer getSignupUserQuerySN() {
		return signupUserQuerySN;
	}
	/**
	 * @param userEmailByInviteidQuery the userEmailByInviteidQuery to set
	 */

	/**
	 * @return the userEmailByInviteidQuery
	 */
	public static StringBuffer getUserEmailByInviteidQuery() {
		return userEmailByInviteidQuery;
	}
	/**
	 * @param inviteridQuery the inviteridQuery to set
	 */
	public static void setInviteridQuery(StringBuffer inviteridQuery) {
		RegisterDaoQueries.inviteridQuery = inviteridQuery;
	}
	/**
	 * @return the inviteridQuery
	 */
	public static StringBuffer getInviteridQuery() {
		return inviteridQuery;
	}
	public static StringBuffer getUserSULocatorByIdQuery() {
		return userSULocatorByIdQuery;
	}
	public static void setUserSULocatorByIdQuery(StringBuffer userSULocatorByIdQuery) {
		RegisterDaoQueries.userSULocatorByIdQuery = userSULocatorByIdQuery;
	}


	/**
	 * @return the userDetailsByEmalAndCodeQuery
	 */
	public static StringBuffer getUserDetailsByEmalAndCodeQuery() {
		return userDetailsByEmalAndCodeQuery;
	}


	/**
	 * @return the userByUserNameORUniqueNameQuery
	 */
	public static StringBuffer getUserByUserNameORUniqueNameQuery() {
		return userByUserNameORUniqueNameQuery;
	}
}
