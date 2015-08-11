/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class implements the registration services
 * This class will delegate services to theservice manager
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.manager;


import java.util.List;

import com.incyyte.app.service.util.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.dao.forgotpwd.ForgotPwdDao;
import com.incyyte.app.dao.login.RegisterDao;
import com.incyyte.app.dao.profile.UserSettingsDao;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddress;
import com.incyyte.app.service.Exceptions.AccountExistException;
import com.incyyte.app.service.Exceptions.ActivationException;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.Exceptions.CreateUserException;
import com.incyyte.app.service.Exceptions.ExceptionMessages;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.web.model.UserSettingsModel;

public class RegistrationServiceManager implements RegistrationManager {
    private RegisterDao registerDao;
    private ForgotPwdDao forgotPwdDao;
    private EmailManager emailMgr;
    @Autowired
    private UserSettingsDao userSettingsDao;
    

    public User registerUser(User user) throws AccountExistException {
        //1. check HomeEmail Address Exists
        UserAddress userAddress = registerDao.findUserAddress(user.getUserAddress().getCyyteHomeEmail());

        //2. if exists - register user and link userAddress
        if (userAddress != null) {
            //(User must enter PIN to activate Account)
            //userAddress.setMessage(Pin Exists for this Address: Enter PIN);
            user.setUserAddress(userAddress);
            registerDao.addUser(user);
        } else {//3. if not exists -
            //3.1. Generate new Auth. Pin
            String pin = Utility.generateAddrPin(Constants.DEFAULT_PIN_LENGTH);
            int countChecks = 0;
            //3.1.1. check database if Auth Pin exists (if it does, regenerate)
            while (registerDao.addressPinExists(pin)) {
                pin = Utility.generateAddrPin(pin.length());
                countChecks++;
                if (countChecks == 10) {
                    pin = Utility.generateAddrPin(pin.length() + 1);
                    countChecks = 0;
                }
            }
            //3.2. create new userAddress,
            userAddress = new UserAddress();
            userAddress.setAuthPin(pin);
            userAddress.setCyyteHomeEmail(user.getUserAddress().getCyyteHomeEmail());//email address entered from front end
            //userAddress.setMessage(Pin has been Emailed to your personal email address);
            Long ua = registerDao.addUserAddress(userAddress);
            //3.3. register user & link both
            userAddress.setId(ua.longValue());
            user.setUserAddress(userAddress);
            registerDao.addUser(user);
            //email PIN to user to Activate Account
            //EmailManager.sendEmail(user.getEmail(), "inCyyte Address PIN", "Your Address PIN: "+pin);
        }
        return null;
    }

    public void setRegisterDao(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    public void setForgotPwdDao(ForgotPwdDao forgotPwdDao) {
        this.forgotPwdDao = forgotPwdDao;
    }

    public User signUpUser(User user) throws CreateUserException, ConfigurationException {
        User usr = registerDao.signUpUser(user);
        sendWelcomeEmail(user);
        return usr;
    }
    
    @Override
	public User signUpPreRegisteredUser(User user) throws CreateUserException, ConfigurationException {
        User usr = registerDao.signUpUserSN(user);        
        return usr;
    }

    public String getInvitedemailByInvId(String invId) {
        return registerDao.getInvitedemailByInvId(invId);
    }

    public User signUpUserSN(User user) throws CreateUserException, ConfigurationException {
        User usr = registerDao.signUpUserSN(user);
        return usr;
    }

    public void postScriptEmail(User user, String url) throws CreateUserException, ConfigurationException {
        sendPostScriptEmail(user, url);
    }


    public boolean activateUser(Long userId, String activationCode) {
        return registerDao.activateUser(userId, activationCode);
    }

    @Override
    public boolean activateUser(String activationCode, String status) {
        return registerDao.activateUserByCode(activationCode, status);
    }

    public User authenticateUserLogin(String email, String password) throws AuthenticationException {
        User user = registerDao.authenticateUserLogin(email, password);
        //If the user is not active then return exception rather than user object
        if (!StringUtils.equalsIgnoreCase(user.getStatus(),"ACTIVE")) {
            throw new AuthenticationException(ExceptionMessages.LI_INACTIVE_ACT_MSG);
        } else {
            return user;
        }
    }

    @Override
    public boolean authenticateAdminUserLogin(String email, String password)
            throws AuthenticationException {
        return registerDao.authenticateAdminUserLogin(email, password);
    }

    /*
      * type = EMAIL / CODE
      */
    public User getUserDetails(String val, String type) {
        Logger.debug("val::" + val);
        Logger.debug("type::" + type);
        return registerDao.getUserDetails(val, type);
    }
    
    public UserSettingsModel getUserSettingsDetails(Long userId) throws Exception {
        return userSettingsDao.getUserSettings(userId);
    }

    public User getUserDetailByUsername(String username) {
        return registerDao.getUserDetailByUsername(username);
    }

    public User getUserDetailByUsernameOrUniqueName(String username){
    	return registerDao.getUserDetailByUsernameOrUniqueName(username);
    }
    
    public User getUserDetailByEmailOrUsername(String email, String username) {
        return registerDao.getUserDetailByEmailOrUsername(email, username);
    }

    public List<User> getUsersByEmailOrUsername(String email, String username) {
        return registerDao.getUsersByEmailOrUsername(email, username);
    }

    public String requestNewPassword(String email) throws AuthenticationException {
        //check email exists on system
        if (!forgotPwdDao.isValidUserEmail(email))
            throw new AuthenticationException(ExceptionMessages.FP_NO_EMAIL_FOUND_MSG);
        //check account active
        if (!forgotPwdDao.isAccountActive(email)) {
            throw new AuthenticationException(ExceptionMessages.FP_INACTIVE_ACCT_MSG);
        }
        //generate new password
        String newPassword = Utility.generateNewPassword();
        if (forgotPwdDao.resetUserPassword(email, newPassword)) {
            //send email
            User userDetail = getUserDetails(email, Constants.GET_BY_MAIL);
/*			try {
				emailMgr.sendResetPasswordEmail(userDetail, newPassword);
			} catch (Exception e) {
				throw new AuthenticationException(ExceptionMessages.FP_SEND_EMAIL_FAILED_MSG + e.getMessage());
			}
			
*/
            sendResetPasswordEmail(userDetail, newPassword);
            return newPassword;
        }
        return null;
    }

    public void sendWelcomeEmail(User user) {
        emailMgr.setUser(user);
        emailMgr.setRunFlag(Constants.SEND_WELCOME_EMAIL);
        new Thread(emailMgr).start();
    }

    public void sendReactivationEmail(User user){
    	emailMgr.setUser(user);
        emailMgr.setRunFlag(Constants.SEND_REACTIVATION_EMAIL);
        new Thread(emailMgr).start();
    }

    private void sendResetPasswordEmail(User user, String newPassword) {
        emailMgr.setUser(user);
        emailMgr.setNewPassword(newPassword);
        emailMgr.setRunFlag(Constants.SEND_ACTIVATION_EMAIL);
        new Thread(emailMgr).start();
    }


    public boolean resetPassword(long userId, String oldPassword, String newPassword) throws AuthenticationException {
        //if invalid password - throw exception
        if (!forgotPwdDao.isValidOriginalPassword(String.valueOf(userId), oldPassword))
            throw new AuthenticationException(ExceptionMessages.FP_PWD_NOT_FOUND_MSG);

        //reset Password
        return forgotPwdDao.resetNewUserPassword(userId, newPassword);
    }

    public void setEmailMgr(EmailManager emailMgr) {
        this.emailMgr = emailMgr;
    }

    @Override
    public boolean activateUserAccount(String email, String code) throws ActivationException {
        boolean isActivated = false;
        //check email exists on system
        if (!forgotPwdDao.isValidUserEmail(email))
            throw new ActivationException(ExceptionMessages.AVT_NO_EMAIL_FOUND_MSG);
        //check if valid acivation code
        if (!registerDao.isValidActivationCode(email, code))
            throw new ActivationException(ExceptionMessages.AVT_INVALID_ACTIVATION_CODE_MSG);
        //if account already active - throw exception
        if (forgotPwdDao.isAccountActive(email))
            throw new ActivationException(ExceptionMessages.AVT_ACCOUNT_ACTIVE_MSG);

        //activate Account
        isActivated = registerDao.activateUser(email);

        return isActivated;
    }


    @Override
    public User getUser(Long userId) {
        return registerDao.getUser(userId);
    }

    private void sendPostScriptEmail(User user, String url) {
        emailMgr.setUser(user);
        emailMgr.setPostUrl(url);
        emailMgr.setRunFlag(Constants.SEND_POSTSCRIPT_EMAIL);
        new Thread(emailMgr).start();
    }

    @Override
    public boolean usernameExists(String username) {
        return registerDao.usernameExists(username);
    }

    @Override
    public boolean emailExists(String email) {
        return registerDao.emailExists(email);
    }

    @Override
    public String getemailFrominviteid(String inviteid) {
        return registerDao.getemailFrominviteid(inviteid);
    }

    @Override
    public long getInviterId(String invId) {
        return registerDao.getInviterId(invId);
    }

	@Override
	public User getUserDetailsByEmailAndCode(String email,
			String activationCode) {
		return registerDao.getUserDetailsByEmailAndCode(email, activationCode);
	}

	@Override
	public void updateCheckedList(User user) throws Exception {
		registerDao.updateCheckedList(user);
	}
	
	@Override
	public void insertUserEmail(long userId, String username, String email){
		registerDao.insertUserEmail(userId, username, email);
	}
}