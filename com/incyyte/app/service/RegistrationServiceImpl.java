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
package com.incyyte.app.service;

import com.incyyte.app.domain.User;
import com.incyyte.app.manager.RegistrationManager;
import com.incyyte.app.service.Exceptions.AccountExistException;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.Exceptions.CreateUserException;

public class RegistrationServiceImpl implements RegistrationService {

	RegistrationManager registrationMgr;
	
	public User signUpUser(User user) throws CreateUserException, ConfigurationException {
		return registrationMgr.signUpUser(user);
	}
	
	@Override
	public User signUpPreRegisteredUser(User user) throws CreateUserException, ConfigurationException {
		return registrationMgr.signUpPreRegisteredUser(user);
	}
	
	public User signUpUserSN(User user) throws CreateUserException, ConfigurationException {
		return registrationMgr.signUpUserSN(user);
	}
	
	public void sendWelcomeEmail(User user){
		registrationMgr.sendWelcomeEmail(user);		
	}
	@Override
	public void sendReactivationEmail(User user){
		registrationMgr.sendReactivationEmail(user);		
	}

	public void postScriptEmail(User user, String url) throws CreateUserException, ConfigurationException {
		registrationMgr.postScriptEmail(user, url);
	}
	
	public void setRegistrationMgr(RegistrationManager registrationMgr) {
		this.registrationMgr = registrationMgr;
	}


	public User registerUser(User user) throws AccountExistException {
		return registrationMgr.registerUser(user);
	}



	public boolean activateUser(Long userId, String activationCode) {
		return registrationMgr.activateUser(userId, activationCode);
	}

	@Override
	public User getUser(Long userId) {
		return registrationMgr.getUser(userId);
	}

	public boolean activateUser(String activationCode,String status) {
		return registrationMgr.activateUser(activationCode, status);
	}
	
	@Override
	public User getUserDetailByUsername(String username) {
		return registrationMgr.getUserDetailByUsername(username);
	}

    @Override
    public User getUserDetailByEmailOrUsername(String emailOrUserName) {
        return registrationMgr.getUserDetailByEmailOrUsername(emailOrUserName, emailOrUserName);
    }

    public User getUserDetailByUsernameOrUniqueName(String username){
		return registrationMgr.getUserDetailByUsernameOrUniqueName(username);
	}
	
	@Override
	public boolean usernameExists(String username) {
		return registrationMgr.usernameExists(username);
	}

	@Override
	public boolean emailExists(String email) {
		return registrationMgr.emailExists(email);
	}

	@Override
	public String getemailFrominviteid(String inviteid) {
		return registrationMgr.getemailFrominviteid(inviteid);
	}
	@Override
	public String getInvitedemailByInvId(String invId) {
      	return registrationMgr.getInvitedemailByInvId(invId);		
	}

	@Override
	public long getInviterId(String invId) {
	 return registrationMgr.getInviterId(invId);
	}

	@Override
	public User getUserDetails(String val, String type) {
		return registrationMgr.getUserDetails(val, type);
	}

	@Override
	public User getUserDetailsByEmailAndCode(String email, String activationCode) {		
		return registrationMgr.getUserDetailsByEmailAndCode(email, activationCode);
	} 
	
	@Override
	public void updateCheckedList(User user) throws Exception {		
		 registrationMgr.updateCheckedList(user);
	} 
	
	@Override
	public void insertUserEmail(long userId, String username, String email){
		registrationMgr.insertUserEmail(userId, username, email);
	}
}
