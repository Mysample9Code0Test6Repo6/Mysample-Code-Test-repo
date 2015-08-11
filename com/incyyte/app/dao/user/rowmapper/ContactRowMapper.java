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

import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.Group;
import com.incyyte.app.domain.User;


/**
 * @author Timi Boboye
 *
 * A RowMapper implementation for mapping to User Registered.
 * 
 * Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 * please be cautious if modifying this class!
 */

public class ContactRowMapper implements RowMapper
{
    /**
     * 
     */
    public ContactRowMapper()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow)
    throws SQLException {
    	Contact contact = new Contact();
    	byte[] bdata = null;
    	
    	contact.setId(rs.getLong("CONTACTID")) ;
    	contact.setUserId(rs.getLong("FK_USERID")) ;
    	contact.setNickname(rs.getString("NICKNAME")) ;
    	contact.setFirstName(rs.getString("FIRSTNAME")) ;
    	contact.setLastName(rs.getString("LASTNAME")) ;
    	contact.setEmail(rs.getString("EMAIL"));   
    	contact.setMobile(rs.getString("MOBILE"));   
    	Blob xmlBlob = rs.getBlob("XMLSTRING");
    	if (xmlBlob != null){
    		bdata = xmlBlob.getBytes(1, (int)xmlBlob.length());       	
    		contact.setXmlString(new String(bdata));  
    	}
    	contact.setMemberId(rs.getLong("MEMBERID"));
    	contact.setBlocked(rs.getString("BLOCKED"));
    	contact.setStatus(rs.getString("STATUS"));
    	contact.setInvitationid(rs.getString("INVITATIONID"));

        return (contact);
    }
} //end, 
