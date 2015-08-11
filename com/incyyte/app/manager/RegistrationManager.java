/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This service interface provides registration services
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.manager;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.*;
import com.incyyte.app.web.model.UserSettingsModel;

import java.util.List;

public interface RegistrationManager {
    public User registerUser(User user) throws AccountExistException;

    public User signUpUser(User user) throws CreateUserException, ConfigurationException;

    public void sendWelcomeEmail(User user);

    public User getUser(Long userId);

    public UserSettingsModel getUserSettingsDetails(Long userId) throws Exception;

    public boolean activateUser(Long userId, String activationCode);

    public boolean activateUser(String activationCode, String status);

    public boolean usernameExists(String username);

    public boolean emailExists(String email);

    //Login 
    public User authenticateUserLogin(String email, String password) throws AuthenticationException;

    public boolean authenticateAdminUserLogin(String email, String password) throws AuthenticationException;

    /*
      * type = EMAIL / CODE
      */
    public User getUserDetails(String val, String type);

    public User getUserDetailByUsername(String username);

    public User getUserDetailByUsernameOrUniqueName(String username);

    public User getUserDetailByEmailOrUsername(String email, String username);

    public List<User> getUsersByEmailOrUsername(String email, String username);

    public boolean activateUserAccount(String email, String code) throws ActivationException;

    public User getUserDetailsByEmailAndCode(String email, String activationCode);

    //forgotten password
    public String requestNewPassword(String email) throws AuthenticationException;

    public boolean resetPassword(long userId, String oldPassword, String newPassword) throws AuthenticationException;

    public void postScriptEmail(User user, String url) throws CreateUserException, ConfigurationException;

    public User signUpUserSN(User user) throws CreateUserException, ConfigurationException;

    public String getemailFrominviteid(String inviteid);

    public String getInvitedemailByInvId(String invId);

    public long getInviterId(String invId);

    public void sendReactivationEmail(User user);

    public void updateCheckedList(User user) throws Exception;

    public User signUpPreRegisteredUser(User user)
            throws CreateUserException, ConfigurationException;

    public void insertUserEmail(long userId, String username, String email);
}
