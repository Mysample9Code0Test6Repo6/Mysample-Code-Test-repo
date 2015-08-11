package com.incyyte.app.dao.login;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.incyyte.app.dao.contacts.ContactDaoQueries;
import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.Dao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameterFactory;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.dao.groups.GroupDao;
import com.incyyte.app.dao.groups.GroupDaoQueries;
import com.incyyte.app.dao.login.rowmapper.UserLocationRowMapper;
import com.incyyte.app.dao.login.rowmapper.UserRowMapper;
import com.incyyte.app.dao.question.QuestionDao;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.dao.user.UserDaoQueries;
import com.incyyte.app.domain.GroupContact;
import com.incyyte.app.domain.GroupSharedInCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddress;
import com.incyyte.app.domain.UserLocation;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.service.Exceptions.CreateUserException;
import com.incyyte.app.service.Exceptions.ExceptionMessages;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.UIDCodeGenerator;
import com.incyyte.app.util.GroupUtil;
import com.incyyte.app.web.model.UserContactModel;

public class RegisterDaoJTImpl extends Dao implements RegisterDao {

	AbstractDao abstractDao;
	@Autowired
	private ContactService contactsSrv;

	@Autowired
	private UserDao userDaoImpl;

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private QuestionDao questionDao;

	@SuppressWarnings("unused")
	private final int FAILED = 1;

	public Long addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserAddress findUserAddress(String cyyteHomeEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long addUserAddress(UserAddress userAddr) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addressPinExists(String pin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getInvitedemailByInvId(String invId) {
		String signUpUserQuery = RegisterDaoQueries.getInvitedemailByInvitationId().toString();
		QueryParameters params = new QueryParameters();
		params.addParam(invId);
		return (String) QueryHelper.doQueryForObject(abstractDao, "getInvitedemailByInvId", signUpUserQuery, params, String.class);
	}

	public User signUpUser(User user) throws CreateUserException {
		String signUpUserQuery = RegisterDaoQueries.getSignupUserQuery().toString();
		String userSettingsQuery = RegisterDaoQueries.getUserSettingsQuery().toString();
		String userEmailsQuery = RegisterDaoQueries.getUserEmailsQuery().toString();
		String userSULocatorQuery = RegisterDaoQueries.getUserSULocatorQuery().toString();
		long userId = 0;
		JdbcTemplate template = abstractDao.getJdbcTemplate("signUpUser");
		Logger.debug("Start SignUp....");
		try {
			//get ID
			abstractDao.getTxnHelper().beginTxn();
			userId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("USERS_PK", true);
			Logger.debug("Get Signup UserID....");
			UIDCodeGenerator generator = new UIDCodeGenerator();
			generator.setDao(this);
			String activationCode = generator.getActivationCode();
			user.setId(userId);
			user.setActivationCode(activationCode);
			Logger.debug("Set Activation Code");
			Logger.debug("signUpUserQuery: " + signUpUserQuery);
			QueryHelper.doUpdate(template, signUpUserQuery, QueryParameterFactory.getSignupParams(user));
			QueryHelper.doUpdate(template, userSettingsQuery, QueryParameterFactory.getUserIdParams(userId));
			if (user.getLocation() != null)
				QueryHelper.doUpdate(template, userSULocatorQuery, QueryParameterFactory.getSULocatorParams(userId,user.getLocation()));
			
			if(user.getUsername() != null && user.getEmail() != null){
				//Update the USERS_EMAILS table
				QueryParameters params = new QueryParameters();
				params.addParam(userId);
				params.addParam(user.getUsername());
				params.addParam(user.getEmail());
				QueryHelper.doUpdate(template, userEmailsQuery, params);
			}
			abstractDao.getTxnHelper().commitTxn();
			return user;
		} catch (Exception e) {
			Logger.error("signUpUser: Failed ", e);
			abstractDao.getTxnHelper().rollbackTxn();
			throw new CreateUserException(e.getMessage());
		} finally {
			abstractDao.freedbpool(template.getDataSource(), "signUpUser");
			addNewMemberToInCyyteSupport(user);
		}
	}

	private void addNewMemberToInCyyteSupport(User newUser) {
		String email = "support@incyyte.com";
		User support = getUserDetails(email, Constants.GET_BY_MAIL);
		if (support != null) {
			long contactID = addContactToSupport(support.getId(), newUser);
			addContactToSupportGroup(support.getId(), contactID);
		}
	}

	public void addContactToSupportGroup(Long userSupportID, long contactID) {
		JdbcTemplate template = abstractDao.getJdbcTemplate("addContactToSupportGroup");
		try {
			Long grpId = getGroupId(userSupportID);
			if (null == grpId) {
				throw new Exception("SUPPORT GROUP DOES NOT EXIST!!!");
			}

			List<GroupContact> groupContacts = new ArrayList<GroupContact>();
			GroupContact groupContact = new GroupContact();

			groupContact.setContactId(contactID);
			groupContact.setGroupId(grpId);
			groupContact.setCreatedBy(String.valueOf(userSupportID));
			groupContact.setLastUpdatedBy(String.valueOf(userSupportID));
			groupContact.setRole("");
			groupContacts.add(groupContact);
			List<Long> memberIds = groupDao.insertBatchGrpContacts(groupContacts, template);
			List<Long> questionIds = questionDao.getQuestionsByGroupId(grpId);
			List<GroupSharedInCyyte> sharedInCyytes = GroupUtil.getGroupSharedInCyytes(String.valueOf(userSupportID), memberIds, questionIds);
			groupDao.insertGrpSharedInCyyte(sharedInCyytes);
			Logger.debug("Created Shared InCyyte entry");
			//Update & increment incyyte count
			incrementTotalIncyyted(grpId);
		} catch (Exception e) {
			Logger.error("addContactToSupport: Failed ", e);
		} finally {
			abstractDao.freedbpool(template.getDataSource(), "addContactToSupportGroup");
		}
	}

	public void incrementTotalIncyyted(long grpId) {
		Logger.debug("incrementTotalIncyyted: grpId - > " + grpId);
		String incrementTotalInCyytedQuery = UserDaoQueries.getIncrementTotalInCyytedQuery().toString();
		Logger.debug("incrementTotalInCyytedQuery: " + incrementTotalInCyytedQuery);
		QueryParameters params = new QueryParameters();
		params.addParam(grpId);
		try {
			QueryHelper.doUpdate(abstractDao, "incrementTotalIncyyted", incrementTotalInCyytedQuery, params);
		} catch (Exception e) {
			Logger.error("incrementTotalIncyyted: Failed ", e);
		}
	}


	public Long getGroupId(Long userSupportID) {
		Long groupId = null;
		String groupIdQuery = GroupDaoQueries.getGroupIdQuery();
		QueryParameters params = new QueryParameters();
		params.addParam(Constants.ALL_MEMEBER_GROUP);
		params.addParam(userSupportID);
		groupId = (Long) QueryHelper.doQueryForObject(abstractDao, "verifyContactalreadyMember", groupIdQuery, params, Long.class);

		return groupId;
	}


	//INCOMPLETE
	public long addContactToSupport(Long userSupportID, User newUser) {
		long contactid = 0;
		UserContactModel cm = new UserContactModel();
		String addContactQuery = ContactDaoQueries.getAddContact().toString();
		try {
			Logger.debug("addContactQuery: " + addContactQuery);
			contactid = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("CONTACTS_PK", false);
			cm.setContactid(contactid);
			cm.setEmail(newUser.getEmail());
			cm.setActive_ind("A");
			cm.setInvitationid("SUPPORT");
			cm.setAccept_inv("Y");
			cm.setSent_invite("Y");
			cm.setStatus("M");

			QueryHelper.doUpdate(abstractDao, "addContactToSupport", addContactQuery, QueryParameterFactory.getaddContactParams(cm, userSupportID));

		} catch (Exception e) {
			Logger.error("addContactToSupport: Failed ", e);
		}
		return contactid;
	}

	public User signUpUserSN(User user) throws CreateUserException {
		String signUpUserQuery = RegisterDaoQueries.getSignupUserQuerySN().toString();
		String userSettingsQuery = RegisterDaoQueries.getUserSettingsQuery().toString();
		String userEmailsQuery = RegisterDaoQueries.getUserEmailsQuery().toString();
		String userSULocatorQuery = RegisterDaoQueries.getUserSULocatorQuery().toString();        
		long userId = 0;
		JdbcTemplate template = abstractDao.getJdbcTemplate("signUpUserSN");
		Logger.debug("Start SignUp....");
		try {
			abstractDao.getTxnHelper().beginTxn();
			//get ID
			userId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("USERS_PK", true);
			Logger.debug("Get Signup UserID....");

			UIDCodeGenerator generator = new UIDCodeGenerator();
			generator.setDao(this);
			String activationCode = generator.getActivationCode();

			user.setId(userId);
			user.setActivationCode(activationCode);
			Logger.debug("Set Activation Code");

			Logger.debug("signUpUserQuery: " + signUpUserQuery);
			QueryHelper.doUpdate(template, signUpUserQuery, QueryParameterFactory.getSignupParamsSN(user));
			QueryHelper.doUpdate(template, userSettingsQuery, QueryParameterFactory.getUserIdParams(userId));
			if (user.getLocation() != null)
				QueryHelper.doUpdate(template, userSULocatorQuery, QueryParameterFactory.getSULocatorParams(userId,user.getLocation()));

			if(user.getUsername() != null && user.getEmail() != null){
				//Update the USERS_EMAILS table
				QueryParameters params = new QueryParameters();
				params.addParam(userId);
				params.addParam(user.getUsername());
				params.addParam(user.getEmail());
				QueryHelper.doUpdate(template, userEmailsQuery, params);
			}
			//If everything is fine then we will store post code in user domain
			userDaoImpl.insertUserDomain(userId, user.getPostalCodeArea(), user.getCountryCode());
			abstractDao.getTxnHelper().commitTxn();
			return user;
		} catch (Exception e) {
			Logger.error("signUpUser: Failed ", e);
			abstractDao.getTxnHelper().rollbackTxn();
			throw new CreateUserException(e.getMessage());
		} finally {
			abstractDao.freedbpool(template.getDataSource(), "signUpUserSN");
			Logger.debug("SNSignup Completed: ID " + user);
			addNewMemberToInCyyteSupport(user);
		}
	}
	
	@Override
	public void insertUserEmail(long userId, String username, String email){
		JdbcTemplate template = abstractDao.getJdbcTemplate("insertUserEmail");
		Logger.debug("Start insertUserEmail....");
		try {
			abstractDao.getTxnHelper().beginTxn();
			String userEmailsQuery = RegisterDaoQueries.getUserEmailsQuery().toString();
			//Update the USERS_EMAILS table
			QueryParameters params = new QueryParameters();
			params.addParam(userId);
			params.addParam(username);
			params.addParam(email);
			QueryHelper.doUpdate(template, userEmailsQuery, params);
			abstractDao.getTxnHelper().commitTxn();

		} catch (Exception e) {
			Logger.error("insertUserEmail: Failed ", e);
			abstractDao.getTxnHelper().rollbackTxn();		
		} finally {
			abstractDao.freedbpool(template.getDataSource(), "insertUserEmail");
		}

	}

	public boolean activateUser(Long userId, String activationCode) {
		String validActivateCodeQuery = RegisterDaoQueries.isValidActivateCodeQuery().toString();
		String activateUserQuery = RegisterDaoQueries.getActivateUserQuery().toString();
		try {
			//check activation code is valid
			Logger.debug("validActivateCodeQuery: " + validActivateCodeQuery);
			QueryParameters param = new QueryParameters();
			param.addParam(userId.toString());
			param.addParam(activationCode);
			int i = QueryHelper.doQueryForInt(abstractDao, "activateUser", validActivateCodeQuery, param);
			if (i == 0) {
				return false;
			}
			//activate Account
			Logger.debug("signUpUserQuery: " + activateUserQuery);
			QueryHelper.doUpdate(abstractDao, "activateUser", activateUserQuery, QueryParameterFactory.getUserIdParams(userId));
			String email = GetEmailbyActivation(activationCode);
			activateUserMembership(email);
			return true;
		} catch (Exception e) {
			Logger.error("activateUser: Failed ", e);
			return false;
		}
	}

	@Override
	public boolean activateUser(String email) {
		String activateUserByEmailQuery = RegisterDaoQueries.getActivateUserByEmailQuery().toString();
		try {
			//activate Account
			Logger.debug("activateUserByEmailQuery: " + activateUserByEmailQuery);
			QueryHelper.doUpdate(abstractDao, "activateUserByEmail", activateUserByEmailQuery, QueryParameterFactory.getActivationParams(email));
		} catch (Exception e) {
			Logger.error("activateUserByEmail: Failed ", e);
			return false;
		}
		activateUserMembership(email);
		return true;
	}


	public void activateUserMembership(String email) {
		String activateUserByEmailQuery = RegisterDaoQueries.getActivateMemberByEmailQuery().toString();
		try {
			//activate Account
			Logger.debug("getActivateMemberByEmailQuery: " + activateUserByEmailQuery);
			QueryHelper.doUpdate(abstractDao, "activateUserMembership", activateUserByEmailQuery, QueryParameterFactory.getActivationParams(email));
		} catch (Exception e) {
			Logger.error("activateUserByEmail: Failed ", e);
		}
	}

	public void setAbstractDao(AbstractDao abstractDao) {
		this.abstractDao = abstractDao;
	}

	@Override
	public boolean authenticateAdminUserLogin(String email, String password)
			throws AuthenticationException {
		boolean result = false;
		int count = -1;

		String validAdminEmailPasswordQuery = RegisterDaoQueries.isValidAdminEmailPasswordQuery().toString();
		QueryParameters params = new QueryParameters();
		params.addParam(email);
		params.addParam(QueryParameterFactory.encryptPwd(password));
		Logger.debug("validAdminEmailPasswordQuery: " + validAdminEmailPasswordQuery);
		count = QueryHelper.doQueryForInt(abstractDao, "authenticateAdminUserLogin", validAdminEmailPasswordQuery, params);
		if (count == 0) {
			throw new AuthenticationException(ExceptionMessages.LI_INVALID_CREDENTIALS_MSG);
		}
		result = true;
		Logger.debug("validAdminEmailPasswordQuery: " + result);
		return result;
	}

	public User authenticateUserLogin(String email, String password) throws AuthenticationException {
        Logger.info("Authentication Start for email::" + email);
		String validEmailPasswordQuery = "SELECT * FROM users u left join user_domain on u.userid = user_id WHERE u.email = ? AND u.password = ? ";
		QueryParameters params = new QueryParameters();
		params.addParam(email);
		params.addParam(QueryParameterFactory.encryptPwd(password));
		Logger.info("validEmailPasswordQuery: " + validEmailPasswordQuery);
		try {
         return ((User) QueryHelper.doQueryForObject(abstractDao, "authenticateUserLogin", validEmailPasswordQuery, params, new UserRowMapper()));
        } catch (Exception e) {
            Logger.error("Exception:", e);
            throw new AuthenticationException(ExceptionMessages.LI_INVALID_CREDENTIALS_MSG);
        }
	}

	public User getUserDetails(String val, String type) {
        String query = null;
		if (type.equals(Constants.GET_BY_MAIL)) {
			query = RegisterDaoQueries.getUserDetailsByEmailQuery().toString();
		}
		if (type.equals(Constants.GET_BY_CODE)) {
			query = RegisterDaoQueries.getUserDetailsByCodeQuery().toString();
		}

        if (type.equals(Constants.GET_BY_CONTACT_ID)) {
            query = RegisterDaoQueries.userDetailsByContactId.toString();
        }

        User userDetails = null;
		try {
			QueryParameters params = new QueryParameters();
			params.addParam(val);
			userDetails = (User) QueryHelper.doQueryForObject(abstractDao, "getUserDetails", query, params, new UserRowMapper());
			if(userDetails != null && userDetails.getId() != null ){
				query = RegisterDaoQueries.getUserSULocatorByIdQuery().toString();
				params = new QueryParameters();
				params.addParam(userDetails.getId());
                UserLocation location = (UserLocation) QueryHelper.doQueryForObject(abstractDao, "getUserDetails", query, params, new UserLocationRowMapper());
				if(location != null) {
					userDetails.setLocation(location);
				}
			}
		}catch (EmptyResultDataAccessException er) {
            //User does not have location mapped. This can be ignored.
            //Logger.warn("getUserDetails: Location not exists for user:" + userDetails);
        } catch (Exception e) {
			Logger.error("getUserDetails: Failed ", e);
		}
		return userDetails;
	}


	public User getUserDetailByUsername(String username) {
		String userDetailsQuery = RegisterDaoQueries.getUserByUserNameQuery().toString();
		User user = null;
		try {
			QueryParameters params = new QueryParameters();
			params.addParam(username.toUpperCase(Locale.ENGLISH));
			user = (User) QueryHelper.doQueryForObject(abstractDao, "getUserDetailByUsername", userDetailsQuery, params, new UserRowMapper());

			if(user != null && user.getId() != null ){
				userDetailsQuery = RegisterDaoQueries.getUserSULocatorByIdQuery().toString();
				params = new QueryParameters();
				params.addParam(user.getId());
				UserLocation location = (UserLocation) QueryHelper.doQueryForObject(abstractDao, "getUserDetailByUsername", userDetailsQuery, params, new UserLocationRowMapper());
				if(location != null) user.setLocation(location);
			}
		} catch (Exception e) {
			Logger.error("getUserDetails: Failed ", e);
		}
		return user;
	}
	
	public User getUserDetailByUsernameOrUniqueName(String username) {
		String userDetailsQuery = RegisterDaoQueries.getUserByUserNameORUniqueNameQuery().toString();
		User user = null;
		try {
			QueryParameters params = new QueryParameters();
			params.addParam(username.toUpperCase(Locale.ENGLISH));
			params.addParam(username.toUpperCase(Locale.ENGLISH));
			user = (User) QueryHelper.doQueryForObject(abstractDao, "getUserDetailByUsernameOrUniqueName", userDetailsQuery, params, new UserRowMapper());
			try {
				if(user != null && user.getId() != null) {
					userDetailsQuery = RegisterDaoQueries.getUserSULocatorByIdQuery().toString();
					params = new QueryParameters();
					params.addParam(user.getId());
					UserLocation location = (UserLocation) QueryHelper.doQueryForObject(abstractDao, "getUserDetailByUsernameOrUniqueName", userDetailsQuery, params, new UserLocationRowMapper());
					if(location != null) user.setLocation(location);
				}
			} catch (EmptyResultDataAccessException e) {
				//Suppress the exception as it is not mandatory that for all users location info exists.
			}
		} catch (Exception e) {
			Logger.error("getUserDetailByUsernameOrUniqueName: Failed " , e);
		}
		return user;

	}

	public User getUserDetailsByEmailAndCode(String email, String activationCode) {
		String query = RegisterDaoQueries.getUserDetailsByEmalAndCodeQuery().toString(); 
		Logger.debug(query);
		User userDetails = null;
		try {
			QueryParameters params = new QueryParameters();
			params.addParam(email);
			params.addParam(activationCode);
			userDetails = (User) QueryHelper.doQueryForObject(abstractDao, "getUserDetailsByEmailAndActivationCode", query, params, new UserRowMapper());

			if(userDetails != null && userDetails.getId() != null ){
				query = RegisterDaoQueries.getUserSULocatorByIdQuery().toString();
				params = new QueryParameters();
				params.addParam(userDetails.getId());
				UserLocation location = (UserLocation) QueryHelper.doQueryForObject(abstractDao, "getUserDetailsByEmailAndActivationCode", query, params, new UserLocationRowMapper());
				if(location != null) userDetails.setLocation(location);
			}
		} catch (Exception e) {
			Logger.error("getUserDetails: Failed ", e);
		}
		return userDetails;
	}

	@SuppressWarnings("unchecked")
	public User getUserDetailByEmailOrUsername(String email, String username) {
		String userDetailsByEmailQuery = RegisterDaoQueries.getUserDetailsByEmailOrUsernameQuery().toString();
		List<User> userDetails = new ArrayList<User>();
		User user = null;
		try {
			QueryParameters params = new QueryParameters();
			params.addParam(email);
			params.addParam(username);
			userDetails = QueryHelper.doQuery(abstractDao, "getUserDetailByEmailOrUsername", userDetailsByEmailQuery, params, new UserRowMapper());

			if(userDetails != null && !userDetails.isEmpty() && userDetails.get(0).getId() != null ){
				user = userDetails.get(0);
				String query = RegisterDaoQueries.getUserSULocatorByIdQuery().toString();
				params = new QueryParameters();
				params.addParam(user.getId());
				UserLocation location = (UserLocation) QueryHelper.doQueryForObject(abstractDao, "getUserDetailByEmailOrUsername", query, params, new UserLocationRowMapper());
				if(location != null) user.setLocation(location);
			}           
		} catch (EmptyResultDataAccessException er) {
			//Logger.warn("Location is not defined for userid:" + user.getId());
			//Location is not mandatory and hence if not exists, supressing the warning
		} catch (Exception e) {
			Logger.error("getUserDetails: Failed ", e);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByEmailOrUsername(String email, String username) {
		List<User> userDetails = new ArrayList<User>();
		Logger.debug("email => " + email + "  username => " + username);
		try {
			if (StringUtils.isNotBlank(email) || StringUtils.isNotBlank(username)) {
				String userDetailsByEmailQuery = RegisterDaoQueries.getUserDetailsByEmailOrUsernameQuery().toString();
				QueryParameters params = new QueryParameters();
				params.addParam(email);
				params.addParam(username);
				Logger.debug("userDetailsByEmailQuery: " + userDetailsByEmailQuery);
				userDetails = QueryHelper.doQuery(abstractDao, "getUsersByEmailOrUsername", userDetailsByEmailQuery, params, new UserRowMapper());
			} else {
				String userDetailsQuery = RegisterDaoQueries.getUserDetailsQuery().toString();
				Logger.debug("userDetailsQuery: " + userDetailsQuery);
				userDetails = QueryHelper.doQuery(abstractDao, "getUsersByEmailOrUsername", userDetailsQuery, null, new UserRowMapper());
			}
		} catch (Exception e) {
			Logger.error("getUserDetails: Failed ", e);
		}
		Logger.debug("userDetails size - > " + userDetails );
		return userDetails;
	}

	public boolean isResetPasswordRequired(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidActivationCode(String email, String code) {
		String validActivationCodeQuery = RegisterDaoQueries.isValidActivationCodeQuery().toString();
		try {
			//check activation code is valid
			Logger.debug("validActivationCodeQuery: " + validActivationCodeQuery);
			QueryParameters params = new QueryParameters();
			params.addParam(email);
			params.addParam(code);
			int i = QueryHelper.doQueryForInt(abstractDao, "isValidActivationCode", validActivationCodeQuery, params);
			if (i == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			Logger.error("isValidActivationCode: Failed ", e);
			return false;
		}
	}

	public boolean verifyUniqueActivationCode(String actvCode) {
		String actvCodeQuery = RegisterDaoQueries.getUniqueActivationCodeQuery().toString();
		QueryParameters param = new QueryParameters();
		param.addParam(actvCode);
		int i = QueryHelper.doQueryForInt(abstractDao, "verifyUniqueActivationCode", actvCodeQuery, param);
		if (i == 1) {
			return true;
		}
		return false;
	}

	/*
	 * Verification checks for
	 * 1. Valid ActvCode
	 * 2. User is not already activated
	 * A valid (true), means this account has not been activated
	 */
	public boolean verifyActivation(String actvCode,String status) {
		String actvCodeQuery = RegisterDaoQueries.getVerifyActivationQuery().toString();
		QueryParameters param = new QueryParameters();
		param.addParam(actvCode);
		param.addParam(status);
		int i = QueryHelper.doQueryForInt(abstractDao, "verifyActivation", actvCodeQuery, param);
		return (i == 1);
	}

	public String GetEmailbyActivation(String actvCode) {
		String actvCodeQuery = RegisterDaoQueries.getEmailActivationQuery();
		QueryParameters param = new QueryParameters();
		param.addParam(actvCode);
		return (String) QueryHelper.doQueryForObject(abstractDao, "GetEmailbyActivation", actvCodeQuery, param, String.class);
	}

	@Override
	public boolean activateUserByCode(String activationCode, String status) {
		if (verifyActivation(activationCode,status)) {
			//activate user
			String activateUserByActvCodeQuery = RegisterDaoQueries.getActivateUserByActvCodeQuery().toString();
			try {
				//activate Account
				Logger.debug("activateUserByActvCodeQuery: " + activateUserByActvCodeQuery);
				QueryHelper.doUpdate(abstractDao, "activateUserByCode", activateUserByActvCodeQuery, QueryParameterFactory.getActivationParams(activationCode));
			} catch (Exception e) {
				Logger.error("activateUserByCode: Failed ", e);
				return false;
			}
			String email = GetEmailbyActivation(activationCode);
			activateUserMembership(email);
			return true;
		} else
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(Long userId) {
		String query = RegisterDaoQueries.getUserByIdQuery().toString();
		try {
			QueryParameters params = new QueryParameters();
			params.addParam(userId);
			return (User) QueryHelper.doQueryForObject(abstractDao, "getUser", query, params, new UserRowMapper());
		} catch (Exception e) {
			Logger.error("getUser: Failed ", e);
		}
		return null;
	}

	@Override
	public boolean usernameExists(String username) {
		String usernameExistsQuery = "SELECT (SELECT COUNT(username) FROM users WHERE username = ?) + " +
				"(SELECT COUNT(unique_page_name) from user_settings WHERE unique_page_name = ?) as count" ;
		QueryParameters params = new QueryParameters();
		params.addParam(username);
		params.addParam(username);
		int i = QueryHelper.doQueryForInt(abstractDao, "usernameExists", usernameExistsQuery, params);
		if (i != 0 ) {
			return true;
		}
		return false;
	}

	@Override
	public boolean emailExists(String email) {
		String emailExistsQuery = RegisterDaoQueries.getEmailExistsQuery().toString();
		QueryParameters params = new QueryParameters();
		params.addParam(email);
		int i = QueryHelper.doQueryForInt(abstractDao, "emailExists", emailExistsQuery, params);
		if (i == 1) {
			return true;
		}
		return false;
	}

	@Override
	public String getemailFrominviteid(String inviteid) {
		String emailQuery = RegisterDaoQueries.getUserEmailByInviteidQuery().toString();
		String inviter = RegisterDaoQueries.getInviteridQuery().toString();
		String email = null;
		JdbcTemplate template = null;
		try {
			QueryParameters params = new QueryParameters();
			params.addParam(inviteid);
			Object emailObj = QueryHelper.doQueryForObject(abstractDao, "getemailFrominviteid", emailQuery, params, String.class);

			if(emailObj != null){	        	

				email = (String)emailObj;

				if (emailExists(email)) {
					int userId = QueryHelper.doQueryForInt(abstractDao, "getemailFrominviteid", inviter, params);
					User userobj = getUser((long) userId);	           

					long contactid = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("CONTACTS_PK", false);
					long invitedUserId = getInviterId(inviteid);
					UserContactModel contactModel = new UserContactModel();
					contactModel.setContactid(contactid);
					contactModel.setUserid(String.valueOf(invitedUserId));
					contactModel.setFirstname(userobj.getFirstname());
					contactModel.setLastname(userobj.getLastname());
					contactModel.setEmail(userobj.getEmail());
					contactModel.setStatus("M");
					contactModel.setAccept_inv("Y");
					contactModel.setActive_ind("A");
					QueryParameters param2 = new QueryParameters();
					param2.addParam(inviteid);
					String sqlQuery = "Update contacts set accept_inv = 'Y' where invitationid = ? ";
					template = abstractDao.getJdbcTemplate("getemailFrominviteid");
					abstractDao.getTxnHelper().beginTxn();

					String addContactQuery = ContactDaoQueries.getAddContact().toString();
					Logger.debug("addContactQuery: " + addContactQuery);
					Logger.debug("User:" + userobj);
					Logger.debug("invitedUserId:" + invitedUserId);
					Logger.debug("Contact Model:" + contactModel);
					int count = contactsSrv.getContactExist(contactModel, invitedUserId);
					if (count > 0) {
						contactsSrv.updateContact(contactModel, invitedUserId);
					} else {
						QueryHelper.doUpdate(template, addContactQuery, QueryParameterFactory.getaddContactParamsAccept(contactModel));
					}
					QueryHelper.doUpdate(template, sqlQuery, param2);
					abstractDao.getTxnHelper().commitTxn();
				}
			}
		} catch (Exception e) {
			Logger.error("isValidUserEmail: Failed ", e);
			try{
				abstractDao.getTxnHelper().rollbackTxn();
			}
			catch (Exception exception) {
				Logger.error("isValidUserEmail: Failed  to roll back  - " + exception.getMessage());	        	
			}
		} finally {
			if (template != null) {
				abstractDao.freedbpool(template.getDataSource(), "getemailFrominviteid");
			}
		}

		return email;
	}

	@Override
	public long getInviterId(String invId) {
		String inviter = RegisterDaoQueries.getInvitedIdByInvitationId().toString();
		QueryParameters params = new QueryParameters();
		params.addParam(invId);
		return QueryHelper.doQueryForLong(abstractDao, "getInviterId", inviter, params);

	}

	@Override
	public void updateCheckedList(User user) throws Exception{
		String updateCheckedListQuery = "update users set display_checklist = ? where userid = ?";
		Logger.debug(updateCheckedListQuery);
		JdbcTemplate template = abstractDao.getJdbcTemplate("updateCheckedList");
		try {
			QueryParameters params = new QueryParameters();
			params.addParam("Y");
			params.addParam(user.getId());
			QueryHelper.doUpdate(template, updateCheckedListQuery, params);
		} catch (Exception e) {
			Logger.error("Exception", e);
			throw e;
		}
	}
}
