
package com.incyyte.app.dao.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.service.util.Logger;

public class LookupDaoImpl implements LookupDao {

	@Autowired
	private AbstractDao abstractDao;

	@Override
	public String getLookupValue(String lookupType, String lookupCode) throws Exception{
		 String Query="SELECT LOOKUP_VALUE FROM BILLING_LOOKUP WHERE lOOKUP_TYPE=? AND LOOKUP_CODE=?";
		 String lookupValue = "0.0";
		 JdbcTemplate template = null;
		 try{
			 QueryParameters param = new QueryParameters();
			 param.addParam(lookupType);
			 param.addParam(lookupCode);
			 template = abstractDao.getJdbcTemplate("getLookupValue");
			 lookupValue = (String) QueryHelper.doQueryForObject(template, Query, param,String.class);
		 }
		 catch(Exception e){
			Logger.error("Exception:",e);
		 }
		 Logger.debug("lookupvalue::" + lookupValue);
		 return lookupValue;
	}
}
