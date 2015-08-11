/**
 * 
 */
package com.incyyte.app.service.util;

/**
 * @author Remi Oseni
 *
 */
public enum AgeGroupEnumType {
	GroupA("08-12", Constants.YOUNG_AGE),
	GroupB("13-18", Constants.YOUNG_AGE),
	GroupC("19-25", Constants.OLD_AGE),
	GroupD("26-30", Constants.OLD_AGE),
	GroupE("31-40", Constants.OLD_AGE),
	GroupF("41-50", Constants.OLD_AGE),
	GroupG("51-Over", Constants.OLD_AGE);
	
	private String range;
	private String type;
	
	AgeGroupEnumType(String r, String t){
		range = r;
		type = t;
	}

	public String getRange() {
		return range;
	}

	public String getType() {
		return type;
	}

}
