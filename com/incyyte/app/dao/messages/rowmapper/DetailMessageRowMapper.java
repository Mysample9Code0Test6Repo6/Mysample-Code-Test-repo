package com.incyyte.app.dao.messages.rowmapper;

import com.incyyte.app.domain.Message;
import com.incyyte.app.util.FileManagementUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailMessageRowMapper implements RowMapper<Message> {

    public DetailMessageRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public Message mapRow(ResultSet rs, int arg1) throws SQLException {
        Message message = new Message();
        try {
            message.setId(rs.getLong("ID"));
            message.setContactName(rs.getString("DISPLAY_NAME"));
            message.setUserName(rs.getString("DISPLAY_USER"));
            message.setMessageText(rs.getString("MESSAGE_TEXT"));
            message.setMessageDate(rs.getTimestamp("MSG_DATE"));
            message.setPhotoUrl(FileManagementUtil.getFileURL(rs.getString("UPLOAD_LOCATION"), rs.getString("CDN_FILE_NAME")));
        } catch (Exception e) {
            throw new SQLException("Exception:", e);
        }
        return (message);
    }
}