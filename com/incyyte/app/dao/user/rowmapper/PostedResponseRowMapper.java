package com.incyyte.app.dao.user.rowmapper;



/*
 * Created on Jun 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.Answer;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.Group;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.Response;
import com.incyyte.app.domain.User;


/**
 * @author Timi Boboye
 *
 * A RowMapper implementation for mapping to User Registered.
 * 
 * Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 * please be cautious if modifying this class!
 */

public class PostedResponseRowMapper implements RowMapper
{
    /**
     * 
     */
    public PostedResponseRowMapper()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow)
    throws SQLException
    {
    	Response response = new Response();
    	
    	response.setUserId(rs.getLong("FK_USERID")) ;
    	response.setAnswerId(rs.getLong("FK_ANSWERID")) ;
    	response.setGender(rs.getString("GENDER")) ;
    	response.setAgeGroup(rs.getString("AGEGROUP")) ;
    	//response.setResponseDate(rs.getTimestamp("RESPONSEDATE"));
    	
    	
        return (response);
    }
} //end, 
