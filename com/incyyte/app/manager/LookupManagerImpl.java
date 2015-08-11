package com.incyyte.app.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.dao.core.LookupDao;

public class LookupManagerImpl implements LookupManager {
	
	@Autowired
	LookupDao lookupDao;
		
	@Override
    public String getLookupValue(String lookupType, String lookupCode) throws Exception {
        return lookupDao.getLookupValue(lookupType,lookupCode);
    }
}

