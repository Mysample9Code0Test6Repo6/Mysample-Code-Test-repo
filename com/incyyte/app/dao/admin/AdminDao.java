/**
 * 
 */
package com.incyyte.app.dao.admin;

import java.util.List;

import com.incyyte.app.domain.User;

/**
 * @author RemiOseni
 *
 */
public interface AdminDao {
	
	public int getTotalContacts(Long userId)throws Exception;

	public int getTotalUsers()throws Exception;

	public int getTotalGroups(Long userId)throws Exception;

	public int getTotalIncyyte(Long userId)throws Exception;
	
	public List<User> getTopIncyyteUsers()throws Exception;

}
