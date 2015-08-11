
package com.incyyte.app.service.util;

import com.incyyte.app.service.Exceptions.InCyyteExceptions;;


public interface CustomProperties
{

	public static final String PROP_INCYYTE_CUSTOM_PROPERTIES_CLASS	 = "incyyte.custom.properties.class";

	public Class getCustomPropertiesClass() throws InCyyteExceptions;
	public String getCustomPropertiesClassName();
		
}