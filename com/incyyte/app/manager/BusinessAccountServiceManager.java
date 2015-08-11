package com.incyyte.app.manager;

import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.incyyte.app.dao.businessaccount.BusinessAccountDao;
import com.incyyte.app.domain.BusinessAccount;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.service.util.Logger;

public class BusinessAccountServiceManager implements BusinessAccountManager  {

	private BusinessAccountDao businessAccountDao;
	
	public void setBusinessAccountDao(BusinessAccountDao businessAccountDao){
		this.businessAccountDao = businessAccountDao;
	}
	
	public boolean storeBussinessAccountInfo(BusinessAccount bussinessAccount) throws Exception,AccessException{
		Logger.debug("inside storeBussinessAccountInfo::"+bussinessAccount);
		if( StringUtils.isBlank(bussinessAccount.getCompanyName())){
			throw new Exception ("companyName can not be null");
		 }if( StringUtils.isBlank(bussinessAccount.getAddress1())){
			throw new Exception ("address_1 can not be null");
		}
	   if(StringUtils.isBlank(bussinessAccount.getPostalCode())){
		   throw new Exception ("postCode can not be null");
		}
	   if( StringUtils.isBlank(bussinessAccount.getWebsiteUrl())){
			throw new Exception ("websiteurl can not be null");
		}else{
		return businessAccountDao.storeBussinessAccountInfo(bussinessAccount);
		}
	}

	@Override
	public BusinessAccount getBussinessAccountInfo(long userid) {
        Logger.debug("inside getBussinessAccountInfo::");
		return businessAccountDao.getBussinessAccountInfo(userid);
	}

	@Override
	public boolean updateBusinessAccountInfo(BusinessAccount businessAccount) throws Exception {
        Logger.debug("inside updateBusinessAccountInfo::"+businessAccount);
		 return businessAccountDao.updateBusinessAccountInfo(businessAccount);
		
	}
	@Override
	public void updateUserType(long userid) throws Exception {
        Logger.debug("inside updateUserType::"+userid);
		 businessAccountDao.updateUserType(userid);
		
	}

	@Override
	public String getUserType(long userid) {
		return businessAccountDao.getUserType(userid);
	}
}
