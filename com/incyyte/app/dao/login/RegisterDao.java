package com.incyyte.app.dao.login;

import java.util.List;

import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddress;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.service.Exceptions.CreateUserException;

public interface RegisterDao {
	
    public Long addUser(User user); 
    public User getUser(Long userId);
    public User signUpUser(User user)throws CreateUserException;
    public boolean activateUser(Long userId, String activationCode); 
    public boolean activateUser(String email); 
    public boolean activateUserByCode(String activationCode, String status); 
    public boolean usernameExists(String username); 
    public boolean emailExists(String email);    

    
	public User authenticateUserLogin(String email, String password)throws AuthenticationException;
	public boolean authenticateAdminUserLogin(String email, String password)throws AuthenticationException;
	public User getUserDetails(String val, String type);
	public User getUserDetailByUsername(String username);
	public User getUserDetailByUsernameOrUniqueName(String username);
	public User getUserDetailByEmailOrUsername(String email, String username) ;
	public List<User> getUsersByEmailOrUsername(String email, String username);
	public boolean isResetPasswordRequired(String email);
	public User getUserDetailsByEmailAndCode(String email, String activationCode);
    
    public UserAddress findUserAddress(String cyyteHomeEmail);    
    public Long addUserAddress(UserAddress userAddr);    
    public boolean addressPinExists(String pin); 
    
    public boolean isValidActivationCode(String email, String code);
	public User signUpUserSN(User user) throws CreateUserException;
	public String getemailFrominviteid(String inviteid);
	String getInvitedemailByInvId(String invId);
	public long getInviterId(String invId);
	public void updateCheckedList(User user) throws Exception;
	public void insertUserEmail(long userId, String username, String email);
}
