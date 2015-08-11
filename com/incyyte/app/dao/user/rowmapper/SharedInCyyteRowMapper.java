package com.incyyte.app.dao.user.rowmapper;

import com.incyyte.app.domain.Share;
import com.incyyte.app.util.FileManagementUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timi Boboye
 *         <p/>
 *         A RowMapper implementation for mapping to User Registered.
 *         <p/>
 *         Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 *         please be cautious if modifying this class!
 */

public class SharedInCyyteRowMapper implements RowMapper {
    /**
     *
     */
    public SharedInCyyteRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow)
            throws SQLException {
        Share share = new Share();

        share.setId(rs.getLong("share_id"));
        share.setFmtId(rs.getString("share_fmt_ID"));
        share.setQuestionId(rs.getLong("fk_questionid"));
        share.setGroupId(rs.getLong("fk_original_groupid"));
        share.setOwnerUserId(rs.getLong("poll_owner_userid"));
        share.setSharerUserId(rs.getLong("poll_sharer_userid"));
        share.setContactId(rs.getLong("fk_contactid"));
        return (share);
    }
}