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

import org.brickred.socialauth.Contact;
//importcom.incyyte.app.domain.Group;
import com.incyyte.app.domain.User;


/**
 * @author Timi Boboye
 *
 * A RowMapper implementation for mapping to User Contacts.
 * 
 * Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 * please be cautious if modifying this class!
 */

public class SnContactRowMapper implements RowMapper
{
    /**
     * 
     */
    public SnContactRowMapper()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow)
    throws SQLException
    {
    	Contact contact = new Contact();
    	

    	contact.setEmail(rs.getString("EMAIL"));   
        return (contact);
    }
} //end, 
