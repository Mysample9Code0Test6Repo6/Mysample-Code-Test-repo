package com.incyyte.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.domain.User;
import com.incyyte.app.exception.PasswordException;
import com.incyyte.app.manager.UserSettingsManager;
import com.incyyte.app.web.model.UserSettingsModel;

/**
 * @author Dev1
 * 
 */
public class UserSettingsServiceImpl implements UserSettingsService {

	@Autowired
	private UserSettingsManager userSettingsManager;
	
	
	@Override
	public UserSettingsModel getUserSettings(Long userId) {
		return userSettingsManager.getUserSettings(userId);
	}
	
//TODO: Need to call from manager	
	public boolean saveUserPassword(String newPassword, String password,User user) throws PasswordException {
		return userSettingsManager.saveUserPassword(newPassword, password, user);
	}
	
	@Override
	public boolean saveUniquePageName(String pageName, Long userId) throws Exception{
		return userSettingsManager.saveUniquePageName(pageName, userId);
	}
	
	@Override
	public String getUniquePageName(Long userId){
		return userSettingsManager.getUniquePageName(userId);
	}

	@Override
	public boolean saveDisplayRating(String ch, Long userId) {
		return userSettingsManager.saveDisplayRating(ch, userId);
	}

	@Override
	public boolean saveDisplayPollComments(String ch, Long userId) {
		return userSettingsManager.saveDisplayPollComments(ch, userId);
	}

	@Override
	public boolean saveDisplayRecentIncyytes(String ch, Long userId) {
		return userSettingsManager.saveDisplayRecentIncyytes(ch, userId);
	}

	@Override
	public boolean saveRetrictComments(String ch, Long userId) {
		return userSettingsManager.saveRetrictComments(ch, userId);
	}

	@Override
	public boolean savePriceTagPolling(String ch, Long userId) {
		return userSettingsManager.savePriceTagPolling(ch, userId);
	}

	@Override
	public boolean saveNotifyPoll(String ch, Long userId) {
		return userSettingsManager.saveNotifyPoll(ch, userId);
	}

	@Override
	public boolean saveNotifyFollower(String ch, Long userId) {
		return userSettingsManager.saveNotifyFollower(ch, userId);
	}

	@Override
	public boolean saveNotifyNews(String ch, Long userId) {
		return userSettingsManager.saveNotifyNews(ch, userId);
	}

	@Override
	public boolean deactivateAccount(Long userId) throws Exception {
		return userSettingsManager.deactivateAccount(userId);
	}

	@Override
	public boolean isPageNameUnique(String pageName) throws Exception {
		
		return userSettingsManager.isPageNameUnique(pageName);
	}
}
