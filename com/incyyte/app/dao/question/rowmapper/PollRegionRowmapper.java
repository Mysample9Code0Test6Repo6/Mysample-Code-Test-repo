package com.incyyte.app.dao.question.rowmapper;



/*
 * Created on Dec 2012
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.Person;



/**
 * @author Timi Boboye
 *
 * A RowMapper implementation for mapping to User Registered.
 * 
 * Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 * please be cautious if modifying this class!
 */

public class PollRegionRowmapper implements RowMapper
{
    /**
     * 
     */
    public PollRegionRowmapper()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow)
    throws SQLException
    {
    	Person person = new Person();
    	person.setId(rs.getLong("userid")) ;
    	person.setEmail(rs.getString("email")) ;
    	    	
        return (person);
    }
} //end, 
