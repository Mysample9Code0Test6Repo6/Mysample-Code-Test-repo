package com.incyyte.app.dao.messages.rowmapper;

import com.incyyte.app.domain.Message;
import com.incyyte.app.domain.Message.MessageStatus;
import com.incyyte.app.domain.Message.MessageType;
import com.incyyte.app.util.FileManagementUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MessagesRowMapper implements RowMapper<Message> {
    public MessagesRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Message mapRow(ResultSet rs, int currentRow) throws SQLException {
        Message message = new Message();
        try {
            message.setId(rs.getLong("ID"));
            message.setComposeMessageId(rs.getLong("COMPOSED_MSG_ID"));
            message.setUserId(rs.getLong("USER_ID"));
            message.setContactId(rs.getLong("CONTACT_ID"));
            message.setMessageDate(rs.getTimestamp("MSG_DATE"));
            message.setType(MessageType.valueOf(StringUtils.upperCase(rs.getString("TYPE"))));
            message.setStatus(MessageStatus.valueOf(StringUtils.upperCase(rs.getString("STATUS"))));
            message.setSentBy(rs.getString("SENT_BY"));
            message.setMessageText(rs.getString("MESSAGE_TEXT"));
            message.setUserName(rs.getString("USER_NAME"));
            message.setContactName(rs.getString("CONTACT_NAME"));
            message.setPhotoUrl(FileManagementUtil.getFileURL(rs.getString("UPLOAD_LOCATION"), rs.getString("CDN_FILE_NAME")));
        } catch (Exception e) {
            throw new SQLException("Exception:", e);
        }
        return (message);
    }
}