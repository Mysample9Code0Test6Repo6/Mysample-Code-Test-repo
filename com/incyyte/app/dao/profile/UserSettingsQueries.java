package com.incyyte.app.dao.profile;

import com.incyyte.app.service.util.Logger;

/**
 * @author Dev1
 *
 */
public class UserSettingsQueries {
	
	private static StringBuffer selectUserSettingsQuery = new StringBuffer()
	.append("SELECT ")
	.append("  us.settingid, us.userid,us.price_tag,us.display_rating,us.default_polls, ")
	.append("  us.unique_page_name, us.password,us.DISABLE_POLL_COMMENTS, ")
	.append("  us.disable_incyytes,  us.restrict_comments,us.opt_in, us.notify_poll, ")
	.append("  us.notify_follower, us.notify_news, user.status ")
	.append("  FROM user_settings us STRAIGHT_JOIN users user ")
	.append("  ON us.userid = user.userid ")
	.append("  WHERE user.userid=? ");

	private static StringBuffer uniquePageNameQuery = new StringBuffer()
	.append("SELECT ")	
	.append(" us.unique_page_name ")	
	.append(" FROM user_settings us,users user ")
	.append(" WHERE user.STATUS = 'ACTIVE' AND us.userid =user.userid and user.userid=? ");
	
	private static StringBuffer updatePasswordQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("password = ? ")
	.append("WHERE userid = ? AND password = ? ");
	
	private static StringBuffer updateUserPasswordQuery = new StringBuffer()
	.append("UPDATE USERS SET ")
	.append("password = ? ")
	.append("WHERE userid = ? AND password = ? ");
	
	private static StringBuffer updatePageNameQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("unique_page_name = ? ")
	.append("WHERE userid = ? ");
	
	private static StringBuffer updateDisablePollQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("DISABLE_POLL_COMMENTS = ? ")
	.append("WHERE userid = ? ");
	
	private static StringBuffer updateIncyytesQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("disable_incyytes = ? ")
	.append("WHERE userid = ? ");
	
	private static StringBuffer updateRestrictCommentsQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("restrict_comments = ? ")
	.append("WHERE userid = ? ");
	
	private static StringBuffer updateOptinQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("opt_in = ? ")
	.append("WHERE userid = ? ");
	
	private static StringBuffer updateRatingQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("display_rating = ? ")
	.append("WHERE userid = ? ");
	
	private static StringBuffer updateNotifyPollQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("notify_poll = ? ")
	.append("WHERE userid = ? ");
	
	private static StringBuffer updateNotifyFollowerQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("notify_follower = ? ")
	.append("WHERE userid = ? ");
	
	private static StringBuffer updateNotifyNewsQuery = new StringBuffer()
	.append("UPDATE USER_SETTINGS SET ")
	.append("notify_news = ? ")
	.append("WHERE userid = ? ");
	
	private static StringBuffer updateDeactivateQuery = new StringBuffer()
	.append("UPDATE USERS SET ")
	.append("STATUS = 'DEACTIVATED' ")
	.append("WHERE userid = ? ");

	
	public static String getSelectUserSettingsQuery() {
		return selectUserSettingsQuery.toString();
	}
	
	public static String getUpdatePasswordQuery() {
		return updatePasswordQuery.toString();
	}
	
	public static String getUserUpdatePasswordQuery() {
		return updateUserPasswordQuery.toString();
	}
	
	public static String getUpdateRatingQuery() {
		return updateRatingQuery.toString();
	}
	
	public static String getUpdateUniquePageQuery() {
		return updatePageNameQuery.toString();
	}
	
	public static String getUpdateDisablePollQuery() {
		return updateDisablePollQuery.toString();
	}
	
	public static String getUpdateIncyytesQuery() {
		return updateIncyytesQuery.toString();
	}
	
	public static String getUpdateRestrictCommentsQuery() {
		return updateRestrictCommentsQuery.toString();
	}
	
	public static String getUpdateOptinQuery() {
		return updateOptinQuery.toString();
	}
	
	public static String getUpdateNotifyPollQuery() {
		return updateNotifyPollQuery.toString();
	}
	
	public static String getUpdateNotifyFollowerQuery() {
		return updateNotifyFollowerQuery.toString();
	}
	
	public static String getUpdateNotifyNewsQuery() {
		return updateNotifyNewsQuery.toString();
	}
	
	public static String getUpdateDeactivateQuery() {
		return updateDeactivateQuery.toString();
	}

	/**
	 * @return the uniquePageNameQuery
	 */
	public static String getUniquePageNameQuery() {
		return uniquePageNameQuery.toString();
	}
}