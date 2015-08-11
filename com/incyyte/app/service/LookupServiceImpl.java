package com.incyyte.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.manager.LookupManager;

public class LookupServiceImpl implements LookupService{
	@Autowired
	LookupManager lookupMgr;

	@Override
	public String getLookupValue(String lookupType, String lookupCode)throws Exception {
		return lookupMgr.getLookupValue(lookupType,lookupCode);
	}

}
