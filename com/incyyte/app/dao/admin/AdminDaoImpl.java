/**
 * 
 */
package com.incyyte.app.dao.admin;

import java.util.ArrayList;
import java.util.List;

import com.incyyte.app.dao.admin.rowmapper.AdminUserRowMapper;
import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.login.rowmapper.UserRowMapper;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.util.Logger;

/**
 * @author RemiOseni
 *
 */
public class AdminDaoImpl implements AdminDao {

	private AbstractDao abstractDao;
	
	 public void setAbstractDao(AbstractDao abstractDao) {
		 this.abstractDao = abstractDao;
	 }
	 
	@Override
	public int getTotalContacts(Long userId) throws Exception {
		Logger.debug("inside contacts count::");
		String contactsCountQuery = null;
		QueryParameters params = null;

		if(userId != null){
			contactsCountQuery = AdminDaoQueries.getTotalUserContacts().toString();
			params = new QueryParameters();
			params.addParam(userId);
		}
		else{
			contactsCountQuery = AdminDaoQueries.getTotalSysContacts().toString();
			params = new QueryParameters();
		}
		int count=QueryHelper.doQueryForInt(abstractDao, "getContactCount", contactsCountQuery, params);
		Logger.debug("contact count:AdminDaoImpl::"+count);
		return count;
	}

	@Override
	public int getTotalUsers() throws Exception {
		Logger.debug("inside users count::");
		   String userCountQuery = AdminDaoQueries.getTotalUsers().toString();
		   QueryParameters params = new QueryParameters();
	       
	       int count=QueryHelper.doQueryForInt(abstractDao, "getUserCount", userCountQuery, params);
	       Logger.debug("contact count:AdminDaoImpl::"+count);
		return count;
	}
	
	@Override
	public int getTotalGroups(Long userId) throws Exception {
		Logger.debug("inside groups count::");
		String groupCountQuery = null;
		QueryParameters params = null;

		if(userId != null){
			groupCountQuery = AdminDaoQueries.getTotalUserGroups().toString();
			params = new QueryParameters();
			params.addParam(userId);
		}else{
			groupCountQuery = AdminDaoQueries.getTotalSysGroups().toString();
			params = new QueryParameters();
		}

		int count=QueryHelper.doQueryForInt(abstractDao, "getGroupCount", groupCountQuery, params);
		Logger.debug("contact count:AdminDaoImpl::"+count);
		return count;
	}
	
	@Override
	public int getTotalIncyyte(Long userId) throws Exception {
		Logger.debug("inside contacts count::");
		String incyyteCountQuery = null;
		QueryParameters params = null;

		if(userId != null){
			incyyteCountQuery = AdminDaoQueries.getTotalUserIncyyte().toString();
			params = new QueryParameters();
			params.addParam(userId);

		}else{
			incyyteCountQuery = AdminDaoQueries.getTotalSysIncyyte().toString();
			params = new QueryParameters();
		}

		int count=QueryHelper.doQueryForInt(abstractDao, "getIncyyteCount", incyyteCountQuery, params);
		Logger.debug("contact count:AdminDaoImpl::"+count);
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getTopIncyyteUsers()throws Exception {
		String query = AdminDaoQueries.getTopIncyyteUsersQuery().toString();
		List<User> userDetails = new ArrayList<User>();
		try {
			QueryParameters params = new QueryParameters();
			//params.addParam(userId);
			userDetails = QueryHelper.doQuery(abstractDao, "getTopIncyyteUsers", query, params, new AdminUserRowMapper());
		} catch (Exception e) {
			Logger.error("getUser: Failed ",e);
		}
		return userDetails;
	}

}
