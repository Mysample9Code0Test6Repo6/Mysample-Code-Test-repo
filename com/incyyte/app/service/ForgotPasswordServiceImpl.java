/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class implements the forgotPassword services
 * This class will delegate services to the forgotPassword manager
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.service;

import com.incyyte.app.manager.RegistrationManager;
import com.incyyte.app.service.Exceptions.AuthenticationException;

public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	RegistrationManager registrationMgr;


	public String requestNewPassword(String email) throws AuthenticationException{
		return registrationMgr.requestNewPassword(email);
	}


	public boolean resetPassword(long userId, String oldPassword, String newPassword) throws AuthenticationException{
		return registrationMgr.resetPassword(userId, oldPassword, newPassword);
	}

	public void setRegistrationMgr(RegistrationManager registrationMgr) {
		this.registrationMgr = registrationMgr;
	}

	
}
