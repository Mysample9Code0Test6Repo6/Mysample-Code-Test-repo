package com.incyyte.app.dao.user.rowmapper;

import com.incyyte.app.domain.GroupContact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupContactRowMapper implements RowMapper {
    @Override
    public GroupContact mapRow(ResultSet rs, int currentRow) throws SQLException {
        GroupContact groupContact = new GroupContact();
        groupContact.setMemberId(rs.getLong("MEMBERID"));
        groupContact.setGroupId(rs.getLong("FK_GROUPID"));
        groupContact.setContactId(rs.getLong("FK_CONTACTID"));
        groupContact.setActiveInd(rs.getString("ACTIVE_IND"));
        groupContact.setRole(rs.getString("ROLE"));
        return groupContact;
    }
}
