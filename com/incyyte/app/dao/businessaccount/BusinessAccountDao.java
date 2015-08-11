package com.incyyte.app.dao.businessaccount;

import com.incyyte.app.domain.BusinessAccount;
import com.incyyte.app.service.Exceptions.AccessException;

public interface BusinessAccountDao {
	
	boolean storeBussinessAccountInfo(BusinessAccount bussinessAccount) throws Exception,AccessException;
	
	BusinessAccount getBussinessAccountInfo(long userid);
	
	boolean updateBusinessAccountInfo(BusinessAccount businessAccount) throws Exception;
	
	void updateUserType(long userid) throws Exception;
	
	public String getUserType(long userid);

}
