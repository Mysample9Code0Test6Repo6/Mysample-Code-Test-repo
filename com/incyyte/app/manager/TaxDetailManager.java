package com.incyyte.app.manager;

public interface TaxDetailManager {
	public String getTaxRate(String description, String countryCode) throws Exception;

}
