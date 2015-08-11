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
import com.incyyte.app.web.model.UserContactModel;


/**
 * @author Timi Boboye
 *
 * A RowMapper implementation for mapping to User Contacts.
 * 
 * Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 * please be cautious if modifying this class!
 */

public class UserContactInvRowMapper implements RowMapper
{
    /**
     * 
     */
    public UserContactInvRowMapper()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow)
    throws SQLException
    {
    	UserContactModel contact = new UserContactModel();
    	
    	contact.setContactid(rs.getLong("CONTACTID")) ;    	
    	contact.setUserid(rs.getString("FK_USERID")) ;
    	contact.setEmail(rs.getString("EMAIL"));   
    	contact.setActive_ind(rs.getString("ACTIVE_IND")) ;
    	contact.setSent_invite(rs.getString("SENT_INVITE")) ;
    	contact.setNote(rs.getString("NOTE")) ;
    	contact.setStatus(rs.getString("STATUS"));
    	contact.setInvitationid(rs.getString("INVITATIONID")) ;
    	contact.setAccept_inv(rs.getString("ACCEPT_INV"));   
    	contact.setBlocked(rs.getString("BLOCKED"));   
        return (contact);
    }
} //end, 
