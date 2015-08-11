package com.incyyte.app.service;

import com.incyyte.app.domain.BusinessAccount;
import com.incyyte.app.service.Exceptions.AccessException;

public interface BusinessAccountService {
	boolean storeBussinessAccountInfo(BusinessAccount bussinessAccount) throws Exception,AccessException;
	
	public BusinessAccount getBussinessAccountInfo(long userid);
	
	boolean updateBusinessAccountInfo(BusinessAccount businessAccount) throws Exception;

	public void updateUserType(long userid) throws Exception;
	
	public String getUserType(long userid);
}
