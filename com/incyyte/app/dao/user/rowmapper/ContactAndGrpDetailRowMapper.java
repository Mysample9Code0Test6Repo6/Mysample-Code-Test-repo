package com.incyyte.app.dao.user.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.model.UserContactModel;

public class ContactAndGrpDetailRowMapper implements RowMapper {

	public ContactAndGrpDetailRowMapper() {
        super();
    }

    public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
    	
    	UserContactModel contact = new UserContactModel();
    	
    	contact.setContactid(rs.getLong("CONTACTID")) ; 
    	contact.setNickname(rs.getString("NICKNAME")) ;
    	contact.setFirstname(rs.getString("FIRSTNAME")) ;
    	contact.setLastname(rs.getString("LASTNAME")) ;
    	contact.setEmail(rs.getString("EMAIL"));
    	contact.setMobile(rs.getString("MOBILE"));
    	contact.setNote(rs.getString("NOTE")) ;
    	contact.setSent_invite(rs.getString("sent_invite")) ;
    	contact.setStatus(rs.getString("status"));
    	contact.setAccept_inv(rs.getString("accept_inv")) ;
    	contact.setMembersince(rs.getString("ACTIVATIONDATE")) ;
    	contact.setPostalcode(rs.getString("POSTCODE")) ;
    	contact.setBlocked(rs.getString("blocked")) ;
    	contact.setInvitationid(rs.getString("invitationid")) ;
    	contact.setUsername(rs.getString("username")) ;
    	contact.setUserid(rs.getString("USERID"));
    	if(rs.getDate("created_date") != null){
    	contact.setCreatedDate(rs.getDate("created_date")) ;
    	}
    	if(rs.getString("memberid") != null) {
        	contact.setChecked("Y");
        } else {
        	contact.setChecked("N");
        }
    	contact.setTmpPostcode(rs.getString("postcode")) ;
    	contact.setDefiningQuestion(rs.getString("defining_question")) ;
    	contact.setProfile_img(FileManagementUtil.getProfileURL(rs.getString("UPLOAD_LOCATION"), rs.getString("CDN_FILE_NAME")));
    	return contact;
    }
}