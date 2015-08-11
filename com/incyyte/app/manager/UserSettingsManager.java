package com.incyyte.app.manager;

import com.incyyte.app.domain.User;
import com.incyyte.app.web.model.UserSettingsModel;

/**
 * @author Dev1
 *
 */
public interface UserSettingsManager {
	
	/**
	 * @param userName
	 * @return
	 */
	public UserSettingsModel getUserSettings(Long userId);
	

	
	/**
	 * @param pageName
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public boolean saveUniquePageName(String pageName, Long userId) throws Exception;
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public boolean saveDisplayRating(String ch, Long userId); 
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public boolean saveDisplayPollComments(String ch, Long userId); 
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public boolean saveDisplayRecentIncyytes(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public boolean saveRetrictComments(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public boolean savePriceTagPolling(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public boolean saveNotifyPoll(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public boolean saveNotifyFollower(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public boolean saveNotifyNews(String ch, Long userId);
	
	/**
	 *
     * @param userId
     * @return
	 * @throws Exception 
	 */
	public boolean deactivateAccount(Long userId) throws Exception;
	
	/**
	 * @param password
	 * @param password2 
	 * @param userId
	 * @return
	 */
	public boolean saveUserPassword(String newPassword, String password, User user);

	public boolean isPageNameUnique(String pageName) throws Exception;



	String getUniquePageName(Long userId);

}
