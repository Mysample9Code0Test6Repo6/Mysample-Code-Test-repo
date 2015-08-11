package com.incyyte.app.service.util;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class Logger
{
    public static Log getLogger(){
        return LogFactory.getLog(Constants.DEFAULT_LOGGER);
    }


    public static void debug(String message){
        getLogger().debug(prefix(message));
    }

 
    public static void debug(String message, Throwable t){
        getLogger().debug(prefix(message), t);
    }

    /**
     * This is a log method with logLevel == INFO, printing is done by
     * the default logger
     *
     */
    public static void info(String message){
        getLogger().info(prefix(message));
    }

    /**
     * This is a log method with logLevel == INFO, printing is done by
     * the default logger
     *
     */
    public static void info(String message, Throwable t){
        getLogger().info(prefix(message), t);
    }

    /**
     * This is a log method with logLevel == WARN, printing is done by
     * the default logger
     *
     */
    public static void warn(String message){
        getLogger().warn(prefix(message));
    }

    /**
     * This is a log method with logLevel == WARN, printing is done by
     * the default logger
     *
     */
    public static void warn(String message, Throwable t){
        getLogger().warn(prefix(message), t);
    }

    /**
     * This is a log method with logLevel == ERROR, printing is done by
     * the default logger
     *
     */
    public static void error(String message){
        getLogger().error(prefix(message));
    }

    /**
     * This is a log method with logLevel == ERROR, printing is done by
     * the default logger
     *
     */
    public static void error(String message, Throwable t){
        getLogger().error(prefix(message), t);
    }

    /**
     * This is a log method with logLevel == ERROR, printing is done by
     * the default logger
     *
     */
    public static void error(Throwable t){
        error("", t);
    }
    
    public static void logger(String message){
   		
    }
    
  
    private static String prefix(String txt){
    	if (txt != null && !txt.equals("")){
    		return ("[" + getCurrentMethodName() + "]:" + txt);
    	}else{
    		return ("");
    	}
    }
    
	private static String getCurrentMethodName(){
		return getCurrentMethodName(4);
	}
	
	private static String getCurrentMethodName(int level) {
		StringBuffer callingMethod = new StringBuffer("");
	
		//long beforeTime = Calendar.getInstance().getTimeInMillis();
	
		Throwable t = new Throwable();
	
		t.fillInStackTrace();
		StackTraceElement ste[] = t.getStackTrace();
		if( ste != null && ste.length > level ) {			
			String s = ste[level].getClassName();
			callingMethod.append(s.substring(ste[level].getClassName().lastIndexOf(".")+1));
			callingMethod.append(".").append(ste[level].getMethodName()).append("()");
		}
		
		
		return callingMethod.toString();
	}
} //end, class Log
