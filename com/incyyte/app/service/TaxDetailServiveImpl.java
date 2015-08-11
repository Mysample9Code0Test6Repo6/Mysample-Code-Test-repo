package com.incyyte.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.manager.TaxDetailManager;
public class TaxDetailServiveImpl implements TaxDetailService {
	@Autowired
	TaxDetailManager taxDetailMgr;
	
	@Override
	public String getTaxRate(String description, String countryCode)throws Exception {
		return taxDetailMgr.getTaxRate(description,countryCode);
	}

}
