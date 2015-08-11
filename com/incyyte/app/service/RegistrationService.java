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
package com.incyyte.app.service;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.AccountExistException;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.Exceptions.CreateUserException;

public interface RegistrationService {
    public User registerUser(User user) throws AccountExistException;

    public User signUpUser(User user) throws CreateUserException, ConfigurationException;

    public void sendWelcomeEmail(User user);

    public boolean activateUser(String activationCode, String status);

    public boolean activateUser(Long userId, String activationCode);

    public void postScriptEmail(User user, String url) throws CreateUserException, ConfigurationException;

    public User getUser(Long userId);

    public User getUserDetailByUsername(String username);

    User getUserDetailByEmailOrUsername(String emailOrUserName);

    public User getUserDetailByUsernameOrUniqueName(String username);

    public boolean usernameExists(String username);

    public boolean emailExists(String email);

    public String getemailFrominviteid(String inviteid);

    public User getUserDetails(String val, String type);

    public User getUserDetailsByEmailAndCode(String email, String activationCode);

    public User signUpUserSN(User user) throws CreateUserException, ConfigurationException;

    public String getInvitedemailByInvId(String invId);

    public long getInviterId(String invId);

    public void sendReactivationEmail(User user);

    void updateCheckedList(User user) throws Exception;

    public User signUpPreRegisteredUser(User user) throws CreateUserException, ConfigurationException;

    void insertUserEmail(long userId, String username, String email);
}
