package com.incyyte.app.service.util;

import com.incyyte.app.service.Exceptions.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class IncyyteProperties {
    private final String clzz = "IncyyteProperties.class";

    // Name of the properties file, if none was set, a default will be searched
    private String propsFileName = "";

    private static final String DEFAULT_ICT_PROPERTIES = "/conf/incyyte.properties";

    private Properties incyyteProps = null;

    /**
     *
     */
    private IncyyteProperties() {
        super();
    }


    public IncyyteProperties(String propsResourceName, InputStream is) throws ConfigurationException {
        super();
        if (propsResourceName != null) {
            this.propsFileName = propsResourceName;
        } else {
            this.propsFileName = DEFAULT_ICT_PROPERTIES;
        }
        loadIt(is);
    }

    public IncyyteProperties(String propsResourceName) throws ConfigurationException {
        super();
        if (propsResourceName != null) {
            this.propsFileName = propsResourceName;
        } else {
            this.propsFileName = DEFAULT_ICT_PROPERTIES;
        }
        loadIt();
    }


    private void loadIt() throws ConfigurationException {
        String propsFile = getPropsFileName();
        printMsg(" >> Loading properties: " + propsFile);
        InputStream is = getClass().getResourceAsStream(propsFile);
        loadIt(is);
    }


    private void loadIt(InputStream is) throws ConfigurationException {
        String propsFile = getPropsFileName();

        try {
            printMsg(" >>>> Loading properties: " + propsFile);
            if (is == null) {
                // Resource not found!
                printMsg("ERROR: Unable to find resource: " + propsFile);
                throw new ConfigurationException("Property no found");
            }
            incyyteProps = new Properties();    // Initialize!
            incyyteProps.load(is);
        } catch (IOException ioe) {
            printMsg("ERROR: Could not load properties from resource [" + propsFile + "]");
            throw new ConfigurationException(ioe.getMessage());
        } catch (Exception e) {
            printMsg("ERROR: Could not load properties from resource [" + propsFile + "]");
            throw new ConfigurationException(e.getCause().getMessage());
        }
    }


    public String getPropsFileName() {
        return propsFileName;
    }


    public void setPropsFileName(String propertyFileName) {
        propsFileName = propertyFileName;
    }


    public String getProperty(String propertyName) {
        return (getIncyyteProps().getProperty(propertyName, ""));
    }


    public String getProperty(String propertyName, String defaultValue) {
        return (getIncyyteProps().getProperty(propertyName, defaultValue));
    }

    private void printMsg(String msg) {
        if (msg != null) {
            Logger.debug(clzz + ": " + msg);
        }
    }

    public Properties getIncyyteProps() {
        return (incyyteProps == null ? null : (Properties) incyyteProps.clone());
    }

    public void setIncyyteProps(Properties properties) {
        incyyteProps = properties;
    }

    public boolean isEmpty() {
        return (getIncyyteProps() == null ? true : getIncyyteProps().isEmpty());
    }

    public void printProps() {
        if (!this.isEmpty()) {
            printMsg("*** Dumping Properties ***");
            printMsg("Properties loaded are: " + asText());
        }
    }

    public String asText() {
        Properties p = this.getIncyyteProps();
        return (p.toString());
    }
} //end, class CpSrvProperties
