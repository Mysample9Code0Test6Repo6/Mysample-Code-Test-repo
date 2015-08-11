package com.incyyte.app.dao.forgotpwd;

public interface ForgotPwdDao {
	public boolean isValidUserEmail(String email);
	public boolean isAccountActive(String email);
	public boolean resetUserPassword(String email, String newPassword);

	public boolean isValidOriginalPassword(String userid, String oldPassword);
	public boolean resetNewUserPassword(Long userid, String newPassword);

}
