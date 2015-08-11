package com.incyyte.app.dao.admin.rowmapper;



/*
 * Created on Jun 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.User;


/**
 * @author Timi Boboye
 *
 * A RowMapper implementation for mapping to User Registered.
 * 
 * Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 * please be cautious if modifying this class!
 */

@SuppressWarnings("rawtypes")
public class AdminUserRowMapper implements RowMapper
{
    /**
     * 
     */
    public AdminUserRowMapper()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow)
    throws SQLException
    {
    	User user = new User();
     	
    	user.setId(rs.getLong("USERID")) ;
    	user.setUsername(rs.getString("USERNAME")) ;    	
    	user.setEmail(rs.getString("EMAIL")) ;  
    	user.setIncyyteNo(rs.getInt("INCYYTENO"));	    	

        return (user);
    }
} //end, 
