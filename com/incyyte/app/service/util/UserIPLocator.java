package com.incyyte.app.service.util;

import com.incyyte.app.domain.UserLocation;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class UserIPLocator {

    private static final String DEFAULT_GEO_PATH = "WEB-INF/classes/conf/GeoLiteCity.dat";
    //private static final String DEFAULT_GEO_PATH = "C://Users//Timi//Downloads//GeoLiteCity.dat";

    private static UserIPLocator _instance = null;

    public static UserIPLocator getInstance() {
        if (_instance == null) {
            _instance = new UserIPLocator();
        }
        return (_instance);
    }

    private UserIPLocator() {
    }

    public UserLocation getLocation(String ipAddress, HttpServletRequest request) {
        String serverPath = request.getSession().getServletContext().getRealPath("/");
        Logger.debug("Get Path: " + serverPath);
        File file = new File(serverPath + DEFAULT_GEO_PATH);
        return getLocation(ipAddress, file);
    }

    public UserLocation getLocation(String ipAddress, File file) {
        UserLocation serverLocation = null;
        try {
            serverLocation = new UserLocation();
            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);

            if (locationServices == null) return null;
            serverLocation.setCountryCode(locationServices.countryCode);
            serverLocation.setCountryName(locationServices.countryName);
            serverLocation.setRegion(locationServices.region);
            serverLocation.setRegionName(regionName.regionNameByCode(locationServices.countryCode, locationServices.region));
            serverLocation.setCity(locationServices.city);
            serverLocation.setPostalCode(locationServices.postalCode);
            serverLocation.setLatitude(String.valueOf(locationServices.latitude));
            serverLocation.setLongitude(String.valueOf(locationServices.longitude));
        } catch (IOException e) {
            Logger.error("IOException:" , e);
        }
        return serverLocation;
    }

    public static void main(String[] args) {
/*    	UserIPLocator obj = UserIPLocator.getInstance();
		UserLocation location = obj.getLocation("112.121.48.1");
		Logger.debug("location::" + location);*/
    }
}