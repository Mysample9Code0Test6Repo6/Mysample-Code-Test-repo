/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This service interface provides fogotten Passowrd services
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.service;

import com.incyyte.app.service.Exceptions.AuthenticationException;

public interface ForgotPasswordService {
	public String requestNewPassword(String email) throws AuthenticationException;
	public boolean resetPassword(long userId, String oldPassword,String newPassword)throws AuthenticationException;	
}
