package com.incyyte.app.dao.forgotpwd;


public class ForgotPwdDaoQueries {

	private static StringBuffer validEmailQuery = new StringBuffer(			
		"SELECT COUNT(*) FROM USERS WHERE email = ? " );

	private static StringBuffer accountActiveQuery = new StringBuffer(			
		"SELECT COUNT(*) FROM USERS WHERE email = ? AND status = 'ACTIVE' " );

	
	private static StringBuffer resetPasswordQuery = new StringBuffer(			
		"UPDATE USERS SET password = ?, reset_pwd_flag = 'Y', modified_date = sysdate() WHERE email = ?" );

	private static StringBuffer validOriginalPwdQuery = new StringBuffer(			
		"SELECT COUNT(*) FROM USERS WHERE userid = ? and password = ? " );

	private static StringBuffer resetNewPasswordQuery = new StringBuffer(			
		"UPDATE USERS SET password = ? , reset_pwd_flag = 'N', modified_date = sysdate() WHERE userid = ? " );
	
	
	public static StringBuffer isValidEmailQuery() {
		return validEmailQuery;
	}

	
	public static StringBuffer getAccountActiveQuery() {
		return accountActiveQuery;
	}

	public static StringBuffer getResetPasswordQuery() {
		return resetPasswordQuery;
	}
	
	public static StringBuffer isValidOriginalPwdQuery() {
		return validOriginalPwdQuery;
	}
	
	public static StringBuffer getResetNewPasswordQuery() {
		return resetNewPasswordQuery;
	}
	

}
