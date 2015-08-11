/**
 * 
 */
package com.incyyte.app.service.util;

/**
 * @author Remi Oseni
 *
 */
import com.incyyte.app.service.util.Constants;

public enum PrivacyEnumType implements Constants{
	PUBLIC(SEARCHABLE_PUBLIC),	
	PRIVATE(SEARCHABLE_PRIVATE);
	
	private String privacy;
	
	private PrivacyEnumType(String privacy) {
		this.privacy = privacy;
	}

	public String getPrivacy() {
		return privacy;
	}	
	
}
