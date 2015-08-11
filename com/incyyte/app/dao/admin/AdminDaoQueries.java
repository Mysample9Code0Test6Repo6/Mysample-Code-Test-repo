/**
 * 
 */
package com.incyyte.app.dao.admin;

/**
 * @author RemiOseni
 *
 */
public class AdminDaoQueries {
	
	private static StringBuffer totalSysContacts = new StringBuffer(			
			"SELECT  count(*)   FROM contacts WHERE active_ind='A' ");
	
	private static StringBuffer totalUserContacts = new StringBuffer(			
			"SELECT  count(*)   FROM contacts WHERE active_ind='A'  AND fk_userid=? ");
	
	private static StringBuffer totalUsers = new StringBuffer(			
			"SELECT  count(*)   FROM users WHERE status='ACTIVE' ");
	
	private static StringBuffer totalSysGroups = new StringBuffer(			
			"SELECT  count(*)   FROM groups WHERE active_ind='A' ");
	
	private static StringBuffer totalUserGroups = new StringBuffer(			
			"SELECT  count(*)   FROM groups WHERE active_ind='A' AND fk_userid=? ");
	
	private static StringBuffer totalSysIncyyte = new StringBuffer(			
			"SELECT  count(*)   FROM incyyte i, questions q " +
			"WHERE i.fk_questionid=q.questionid  " +
			"AND i.active_ind='A' ");
	
	private static StringBuffer totalUserIncyyte = new StringBuffer(			
			"SELECT  count(*)   FROM incyyte i, questions q " +
			"WHERE i.fk_questionid=q.questionid  " +
			"AND i.active_ind='A' " +
			"AND q.fk_userId=? ");
	
	
	
	private static StringBuffer topIncyyteUsersQuery = new StringBuffer(
			"SELECT userid, username,  email, " + 
				"(SELECT  count(*)  " +  
				"FROM incyyte i, questions q  " +
				"WHERE i.fk_questionid=q.questionid  " +
				"AND i.active_ind='A' " +
				"AND q.fk_userId=u.userid) AS incyyteNo " +
			"FROM USERS  u ");	
			

	public static StringBuffer getTotalSysContacts() {
		return totalSysContacts;
	}

	public static StringBuffer getTotalUsers() {
		return totalUsers;
	}

	public static StringBuffer getTotalSysGroups() {
		return totalSysGroups;
	}

	public static StringBuffer getTotalSysIncyyte() {
		return totalSysIncyyte;
	}

	public static StringBuffer getTotalUserIncyyte() {
		return totalUserIncyyte;
	}

	public static StringBuffer getTotalUserContacts() {
		return totalUserContacts;
	}

	public static StringBuffer getTotalUserGroups() {
		return totalUserGroups;
	}

	public static StringBuffer getTopIncyyteUsersQuery() {
		return topIncyyteUsersQuery;
	}

}
