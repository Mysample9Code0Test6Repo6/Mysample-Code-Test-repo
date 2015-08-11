package com.incyyte.app.dao.user.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.Contact;

public class ContactNewRowMapper implements RowMapper{
	
	  public ContactNewRowMapper()
	    {
	        super();
	    }

	 public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
	    	Contact contact = new Contact();
	    	
	    	contact.setId(rs.getLong("CONTACTID")) ;
	    	contact.setUserId(rs.getLong("FK_USERID")) ;
	    	contact.setNickname(rs.getString("NICKNAME")) ;
	    	contact.setFirstName(rs.getString("FIRSTNAME")) ;
	    	contact.setLastName(rs.getString("LASTNAME")) ;
	    	contact.setEmail(rs.getString("EMAIL"));   
	    	contact.setMobile(rs.getString("MOBILE"));
	    	contact.setActive_ind(rs.getString("ACTIVE_IND"));
	    	
	    	
	       	contact.setCreated_date(rs.getDate("CREATED_DATE")) ;
	    	contact.setModified_date(rs.getDate("MODIFIED_DATE")) ;
	    	contact.setCreated_by(rs.getString("CREATED_BY")) ;
	    	contact.setModified_by(rs.getString("MODIFIED_BY")) ;
	    	contact.setInvitationid(rs.getString("SENT_INVITE")) ;
	    	contact.setNote(rs.getString("NOTE"));   
	    	contact.setStatus(rs.getString("STATUS"));
	    	contact.setInvitationid(rs.getString("INVITATIONID"));
	    	contact.setAccept_inv(rs.getString("ACCEPT_INV"));
         	contact.setBlocked(rs.getString("BLOCKED"));
	    

	        return (contact);
	    }

	

	

}
