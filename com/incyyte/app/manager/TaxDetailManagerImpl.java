package com.incyyte.app.manager;

import org.springframework.beans.factory.annotation.Autowired;
import com.incyyte.app.dao.core.TaxDetailDao;

public class TaxDetailManagerImpl implements TaxDetailManager {
	@Autowired
	TaxDetailDao taxDetailDao;
		
	@Override
	public String getTaxRate(String description, String countryCode) throws Exception {
        return taxDetailDao.getTaxRate(description,countryCode);
    }

}
