package com.incyyte.app.dao.user.rowmapper;

import com.incyyte.app.domain.Answer;
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

public class AnswerRowMapper implements RowMapper {
    /**
     *
     */
    public AnswerRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow)
            throws SQLException {
        Answer answer = new Answer();

        answer.setId(rs.getLong("ANSWERID"));
        answer.setIncyyteId(rs.getLong("FK_QUESTIONID"));
        answer.setAnswerOption(rs.getString("RESPONSE"));

        answer.setUploadType(rs.getString("UPLOAD_TYPE"));
        answer.setUploadName(rs.getString("UPLOAD_NAME"));
        answer.setUploadExt(rs.getString("UPLOAD_EXT"));
        answer.setUploadCDN_url(FileManagementUtil.getFileURL(rs.getString("UPLOAD_LOCATION"), rs.getString("CDN_FILE_NAME")));
        answer.setUrlLink(rs.getString("URL_LINK"));
        answer.setYoutubeURLAnswer(rs.getString("youtube_url"));

        return (answer);
    }
}