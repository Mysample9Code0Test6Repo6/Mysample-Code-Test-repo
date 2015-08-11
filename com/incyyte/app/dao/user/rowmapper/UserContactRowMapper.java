package com.incyyte.app.dao.user.rowmapper;

/*
* Created on Jun 2011
*
* TODO To change the template for this generated file go to
* Window - Preferences - Java - Code Style - Code Templates
*/

import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.model.UserContactModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author chandan
 *         <p/>
 *         A RowMapper implementation for mapping to User Contacts.
 *         <p/>
 *         Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 *         please be cautious if modifying this class!
 */

public class UserContactRowMapper implements RowMapper {
    /**
     *
     */
    public UserContactRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
        UserContactModel contact = new UserContactModel();
        contact.setContactid(rs.getLong("CONTACTID"));
        contact.setNickname(rs.getString("NICKNAME"));
        contact.setFirstname(rs.getString("FIRSTNAME"));
        contact.setLastname(rs.getString("LASTNAME"));
        contact.setEmail(rs.getString("EMAIL"));
        contact.setMobile(rs.getString("MOBILE"));
        contact.setNote(rs.getString("NOTE"));
        contact.setSent_invite(rs.getString("sent_invite"));
        contact.setStatus(rs.getString("status"));
        contact.setAccept_inv(rs.getString("accept_inv"));
        contact.setMembersince(rs.getString("ACTIVATIONDATE"));
        contact.setPostalcode(rs.getString("POSTCODE"));
        contact.setBlocked(rs.getString("blocked"));
        contact.setInvitationid(rs.getString("invitationid"));
        contact.setUsername(rs.getString("username"));
        contact.setActive_ind(rs.getString("active_ind"));
        contact.setProfile_img(FileManagementUtil.getProfileURL(rs.getString("UPLOAD_LOCATION"), rs.getString("CDN_FILE_NAME")));
        return (contact);
    }
}