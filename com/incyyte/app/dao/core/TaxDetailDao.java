package com.incyyte.app.dao.core;

public interface TaxDetailDao {
	 public String getTaxRate(String description, String countryCode) throws Exception;
}
