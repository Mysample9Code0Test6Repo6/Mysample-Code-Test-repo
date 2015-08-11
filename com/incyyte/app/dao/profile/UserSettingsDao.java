package com.incyyte.app.dao.profile;

import org.springframework.dao.EmptyResultDataAccessException;

import com.incyyte.app.web.model.UserSettingsModel;

/**
 * @author Dev1
 *
 */
public interface UserSettingsDao {
	
	/**
	 * @param userName
	 * @return
	 */
	public UserSettingsModel getUserSettings(Long userId) throws EmptyResultDataAccessException;
	
	
	
	/**
	 * @param pageName
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public int saveUniquePageName(String pageName, Long userId) throws Exception;
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public int saveDisplayRating(String ch, Long userId); 
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public int saveDisplayPollComments(String ch, Long userId); 
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public int saveDisplayRecentIncyytes(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public int saveRetrictComments(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public int savePriceTagPolling(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public int saveNotifyPoll(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public int saveNotifyFollower(String ch, Long userId);
	
	/**
	 * @param ch
	 * @param userId
	 * @return
	 */
	public int saveNotifyNews(String ch, Long userId);
	
	/**
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public int deactivateAccount(Long userId) throws Exception;
	
	/**
	 * @param password
	 * @param userId
	 * @return
	 */

	public int saveUserPassword(String newPassword, String password, Long userId);

	public boolean isPageNameUnique(String pageName) throws Exception;
	
	public String getUniquePageName(Long userId);
}
