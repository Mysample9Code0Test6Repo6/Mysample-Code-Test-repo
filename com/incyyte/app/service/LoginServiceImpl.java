/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class implements the login services
 * This class will delegate services to the service manager
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
import com.incyyte.app.manager.RegistrationManager;
import com.incyyte.app.service.Exceptions.ActivationException;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserSettingsModel;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginServiceImpl implements LoginService {

    @Autowired
    RegistrationManager registrationMgr;

    public User authenticateUserLogin(String email, String password) throws AuthenticationException {
        Logger.info("authenticate: Email::" + email);
        return registrationMgr.authenticateUserLogin(email, password);
    }

    @Override
    public boolean authenticateAdminUserLogin(String email, String password) throws AuthenticationException {
        return registrationMgr.authenticateAdminUserLogin(email, password);
    }

    /*
      * type = EMAIL / CODE
      */
    public User getUserDetails(String val, String type) {
    	Logger.info("Entering into getUserDetails():LoginServiceImpl");
    	Logger.debug("val::" + val);
        Logger.debug("type::" + type);
        Logger.info("Exiting from getUserDetails():LoginServiceImpl");
        return registrationMgr.getUserDetails(val, type);
    }

    public UserSettingsModel getUserSettingsDetails(Long userId) throws Exception {
        return registrationMgr.getUserSettingsDetails(userId);
    }

    public User getUserDetailByEmailOrUsername(String email, String username) {
        return registrationMgr.getUserDetailByEmailOrUsername(email, username);
    }

    @Override
    public boolean activateUserAccount(String email, String code) throws ActivationException {
        return registrationMgr.activateUserAccount(email, code);
    }
}