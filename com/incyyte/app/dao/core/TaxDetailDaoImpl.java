package com.incyyte.app.dao.core;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.service.util.Logger;

public class TaxDetailDaoImpl implements TaxDetailDao{
	@Autowired
	private AbstractDao abstractDao;

	@Override
	public String getTaxRate(String description, String countryCode) throws Exception{
		String query="SELECT RATE FROM TAX_DETAILS WHERE DESCRIPTION=? ";
		if(StringUtils.isNotBlank(countryCode)) {
			query = query +  " AND COUNTRY_CODE=?";
		}
		
		String taxRate = null;
		QueryParameters param = new QueryParameters();
		param.addParam(description);
		if(StringUtils.isNotBlank(countryCode)) {
			param.addParam(countryCode);
		}
		taxRate = (String) QueryHelper.doQueryForObject(abstractDao, "getTaxRate", query, param,String.class);
		Logger.debug("TaxRate::" + taxRate);
		return taxRate;
	}
}