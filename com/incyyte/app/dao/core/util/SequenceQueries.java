package com.incyyte.app.dao.core.util;

import com.incyyte.app.service.util.Logger;


public class SequenceQueries {
	
	public static String getSequenceNextMultipleValueQuery()
	{
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT ")
			 .append("MULTIPLE_SEQUENCE_GENERATOR (?, ?, ?) ")	
			 .append("FROM DUAL");
			 
		Logger.debug(query.toString());
		return query.toString();    	
	}
}
