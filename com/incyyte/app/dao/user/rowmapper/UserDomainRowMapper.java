package com.incyyte.app.dao.user.rowmapper;



/*
 * Created on Jun 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.UserDomain;


/**
 * @author Timi Boboye
 *
 * A RowMapper implementation for mapping to User Registered.
 * 
 * Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 * please be cautious if modifying this class!
 */

public class UserDomainRowMapper implements RowMapper
{
    /**
     * 
     */
    public UserDomainRowMapper()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow)
    throws SQLException
    {
    	UserDomain user = new UserDomain();     	
    	
    	user.setCountyName(rs.getString("county_name")) ; 
    	user.setCountryCode(rs.getString("country_code")) ;    	
    	user.setPostalCode(rs.getString("postcode")) ;
            		
        return (user);
    }
} //end, 
