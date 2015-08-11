/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This service interface provides Login services
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.service;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.ActivationException;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.web.model.UserSettingsModel;


public interface LoginService {
	public User authenticateUserLogin(String email, String password)throws AuthenticationException;
	public boolean authenticateAdminUserLogin(String email, String password)throws AuthenticationException;
	
	/*
	 * type = EMAIL / CODE
	 */
	public User getUserDetails(String val, String type);
	public User getUserDetailByEmailOrUsername(String email, String username);
	public boolean activateUserAccount(String email, String code)throws ActivationException;
	
	public UserSettingsModel getUserSettingsDetails(Long userId) throws Exception;

}
