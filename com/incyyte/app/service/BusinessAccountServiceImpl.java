package com.incyyte.app.service;

import com.incyyte.app.domain.BusinessAccount;
import com.incyyte.app.manager.BusinessAccountManager;
import com.incyyte.app.service.Exceptions.AccessException;

public class BusinessAccountServiceImpl implements BusinessAccountService {

	private BusinessAccountManager businessAccountManager;
	
	public void setBusinessAccountManager(BusinessAccountManager businessAccountManager){
		this.businessAccountManager = businessAccountManager;
	}
	
	public boolean storeBussinessAccountInfo(BusinessAccount bussinessAccount) throws Exception,AccessException{
		return businessAccountManager.storeBussinessAccountInfo(bussinessAccount);
	}

	@Override
	public BusinessAccount getBussinessAccountInfo(long userid) {
		return businessAccountManager.getBussinessAccountInfo(userid);
	}

	@Override
	public boolean updateBusinessAccountInfo(BusinessAccount businessAccount) throws Exception {
		return businessAccountManager.updateBusinessAccountInfo(businessAccount);
		
	}
   @Override
   public void updateUserType(long userid) throws Exception{
	    businessAccountManager.updateUserType(userid);
   }

   @Override
   public String getUserType(long userid) {
	return businessAccountManager.getUserType(userid);
  }
}
