package com.incyyte.app.dao.login.rowmapper;

import com.incyyte.app.domain.UserLocation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timi Boboye
 *         <p/>
 *         A RowMapper implementation for mapping to User Registered.
 *         <p/>
 *         Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 *         please be cautious if modifying this class!
 */

@SuppressWarnings("rawtypes")
public class UserLocationRowMapper implements RowMapper {
    /**
     *
     */
    public UserLocationRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
        UserLocation location = new UserLocation();
        try {        	 
        	location.setIpAddress(rs.getString("ip_address"));
        	location.setCountryCode(rs.getString("country_code"));
        	location.setCountryName(rs.getString("country_name"));
            location.setRegion(rs.getString("region"));
            location.setRegionName(rs.getString("region_name"));
            location.setCity(rs.getString("city"));
            location.setPostalCode(rs.getString("postal_code"));
            location.setLatitude(rs.getString("latitude"));
            location.setLongitude(rs.getString("longitude"));
            
        } catch (Exception e) {
            throw new SQLException("Exception:", e);
        }
        return (location);
    }
} //end, 
