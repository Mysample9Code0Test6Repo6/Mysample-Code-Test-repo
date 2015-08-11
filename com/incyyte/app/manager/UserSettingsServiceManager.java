package com.incyyte.app.manager;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.incyyte.app.dao.contacts.ContactDao;
import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryParameterFactory;
import com.incyyte.app.dao.forgotpwd.ForgotPwdDao;
import com.incyyte.app.dao.profile.UserSettingsDao;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;
import com.incyyte.app.web.model.UserSettingsModel;

/**
 * @author Dev1
 *
 */
@Service
public class UserSettingsServiceManager implements UserSettingsManager {
	
	@Autowired
	private UserSettingsDao userSettingsDao;
	@Autowired
	private	ForgotPwdDao forgotPwdDao;
	@Autowired
	private AbstractDao abstractDao;
	
	@Autowired
	private ContactDao contactDao;

	@Override
	public UserSettingsModel getUserSettings(Long userId) {
		UserSettingsModel model = null;
		
		try {
			model = userSettingsDao.getUserSettings(userId);
		}
		catch(EmptyResultDataAccessException e) {
			model = new UserSettingsModel();
		}
		return model;
	}

	@Override
	public boolean saveUserPassword(String newPassword, String password,User user)  {
	   if(StringUtils.equals(QueryParameterFactory.encryptPwd(password),user.getPassword())) {
		   boolean passwordUpdate=forgotPwdDao.resetNewUserPassword(user.getId(), newPassword);
           Logger.debug("passwordUpdate" + passwordUpdate);
		   if(passwordUpdate) {
			   return true;
		   }
	   }
	return false;
	}
	
	@Override
	public String getUniquePageName(Long userId){
		return userSettingsDao.getUniquePageName(userId);		
	}
	
	@Override
	public boolean saveUniquePageName(String pageName, Long userId)throws Exception{
		boolean updated = false;
		
		int updateCount = userSettingsDao.saveUniquePageName(pageName, userId);
		
		if(updateCount > 0) {
			updated = true;
		}
		return updated;
	}

	@Override
	public boolean saveDisplayRating(String ch, Long userId) {
		boolean updated = false;
		
		int updateCount = userSettingsDao.saveDisplayRating(ch, userId);
		
		if(updateCount > 0) {
			updated = true;
		}
		return updated;
	}

	@Override
	public boolean saveDisplayPollComments(String ch, Long userId) {
		boolean updated = false;
		
		int updateCount = userSettingsDao.saveDisplayPollComments(ch, userId);
		
		if(updateCount > 0) {
			updated = true;
		}
		return updated;
	}

	@Override
	public boolean saveDisplayRecentIncyytes(String ch, Long userId) {
		boolean updated = false;
		
		int updateCount = userSettingsDao.saveDisplayRecentIncyytes(ch, userId);
		
		if(updateCount > 0) {
			updated = true;
		}
		return updated;
	}

	@Override
	public boolean saveRetrictComments(String ch, Long userId) {
		boolean updated = false;
		
		int updateCount = userSettingsDao.saveRetrictComments(ch, userId);
		
		if(updateCount > 0) {
			updated = true;
		}
		return updated;
	}

	@Override
	public boolean savePriceTagPolling(String ch, Long userId) {
		boolean updated = false;
		
		int updateCount = userSettingsDao.savePriceTagPolling(ch, userId);
		
		if(updateCount > 0) {
			updated = true;
		}
		return updated;
	}

	@Override
	public boolean saveNotifyPoll(String ch, Long userId) {
		boolean updated = false;
		
		int updateCount = userSettingsDao.saveNotifyPoll(ch, userId);
		
		if(updateCount > 0) {
			updated = true;
		}
		return updated;
	}

	@Override
	public boolean saveNotifyFollower(String ch, Long userId) {
		boolean updated = false;
		
		int updateCount = userSettingsDao.saveNotifyFollower(ch, userId);
		
		if(updateCount > 0) {
			updated = true;
		}
		return updated;
	}

	@Override
	public boolean saveNotifyNews(String ch, Long userId) {
		boolean updated = false;
		
		int updateCount = userSettingsDao.saveNotifyNews(ch, userId);
		
		if(updateCount > 0) {
			updated = true;
		}
		return updated;
	}

	@Override
	public boolean deactivateAccount(Long userId) throws Exception{
		Logger.debug("userId::::"+userId);
		boolean updated = false;
		JdbcTemplate template = null;
		try{
			abstractDao.getTxnHelper().beginTxn();
			template = abstractDao.getJdbcTemplate("deactivateAccountInfo");
			int updateCount = userSettingsDao.deactivateAccount(userId);
			Logger.debug("updateCount::::"+updateCount);
			UserContactlist userContactList = contactDao.getUserContacts(userId, 0, 0, null, null);
			Logger.debug("userContactList::"+userContactList);
			int blockCount = 0;
			for (UserContactModel usercontact: userContactList.getUserContactlist()){
				blockCount =	contactDao.BlockContact(usercontact,userId);
				Logger.debug("blockCount::::"+blockCount);
			}
			if(updateCount > 0 && blockCount >0) {
				updated = true;
			}
			Logger.debug("updated::::"+updated);
			abstractDao.getTxnHelper().commitTxn();
			return updated;
		}catch(Exception e){
			Logger.error("deactivating is: Failed " , e);	
			abstractDao.getTxnHelper().rollbackTxn();
			return false;
		} finally {
			abstractDao.freedbpool(template.getDataSource(), "deactivateAccountInfo");
		}
	}

	@Override
	public boolean isPageNameUnique(String pageName) throws Exception {
		return userSettingsDao.isPageNameUnique(pageName);
	}	

}
