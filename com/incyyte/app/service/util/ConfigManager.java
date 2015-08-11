
package com.incyyte.app.service.util;

import java.io.InputStream;

import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.Exceptions.InCyyteExceptions;


public class ConfigManager implements CustomProperties{
    // Attribute Declarations
	private static ConfigManager _instance = null;

	private IncyyteProperties incyyteProperties = null;
	
	public static ConfigManager getInstance(){
		if (_instance == null){
			_instance = new ConfigManager();
		}		
		return (_instance);
	}
	
    private ConfigManager(){
    }
    
    /**
     * Loads the properties from the specified propsFile and sets the
     * properties in an instance of InCyyteProperties.
     * 
     * @param propsFile
     */
    public void loadConfig(String propsFile, InputStream is) throws ConfigurationException{
		IncyyteProperties props = null;
    	try{
    		props = new IncyyteProperties(propsFile, is);      		
    		if (props.isEmpty()){
                Logger.warn("No properties loaded!");
    		}else{
    			Logger.debug(props.getIncyyteProps().toString());		// Debug info
    			setIncyyteProperties(props);
    		}    		
    	}catch (ConfigurationException ce){
    		throw (ce);
    	}catch (Exception e){
    		throw new ConfigurationException(Constants.INTERNAL_PROGRAM_ERROR +" "+e.getMessage());
    	}
    	
    	return;
    }
    
    
    public boolean validateConfiguration(){
        boolean isValidConfiguration = false;
                
        return isValidConfiguration;
    }
    
	/**
	 * @return
	 */
	public IncyyteProperties getIncyyteProperties(){
		return incyyteProperties;
	}

	/**
	 * @param properties
	 */
	public void setIncyyteProperties(IncyyteProperties properties){
		if (properties != null){
			incyyteProperties = properties;
		}else{
			incyyteProperties = null; 
		}
	}
	
	public String getProperty(String propertyName){
		return (getIncyyteProperties().getProperty(propertyName));
	}

	public String getProperty(String propertyName, String defaultValue){
		return (getIncyyteProperties().getProperty(propertyName, defaultValue));
	}
	
	public final Class getCustomPropertiesClass() throws InCyyteExceptions{
		Class c = null;
		try{
			c = Class.forName(getCustomPropertiesClassName());
		}catch (ClassNotFoundException e){
			throw new InCyyteExceptions("Custom property class not found: " +e.getMessage());
		}

		return (c);
	}
	

	public final String getCustomPropertiesClassName(){
		return (getProperty(CustomProperties.PROP_INCYYTE_CUSTOM_PROPERTIES_CLASS));
	}
	

} 
