package com.incyyte.app.dao.user.rowmapper;

import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.model.CommentsModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Timi Boboye
 *         <p/>
 *         A RowMapper implementation for mapping to User Registered.
 *         <p/>
 *         Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 *         please be cautious if modifying this class!
 */

public class CommentsModelRowMapper implements RowMapper {
    public CommentsModelRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
        CommentsModel comment = new CommentsModel();
        try {
            comment.setCommentid(rs.getString("forumid"));
            comment.setActive(rs.getString("active_ind"));
            
            String commentsBy = rs.getString("created_by");
            commentsBy = commentsBy.contains("@")?commentsBy.substring(0, commentsBy.indexOf("@")):commentsBy;
            
            //comment.setCommentby(rs.getString("created_by"));
            comment.setCommentby(commentsBy);
            comment.setQuesid(rs.getString("fk_questionid"));
            comment.setComment(rs.getString("comment"));
            comment.setUploadCommentCdnFileName(rs.getString("COMMENT_CDN_FILE_NAME"));
            comment.setYoutubeCommentVideoURL(rs.getString("youtube_url"));
            comment.setUploadCommentLocation(rs.getString("COMMENT_UPLOAD_LOCATION"));
            comment.setUserprofileimg(FileManagementUtil.getFileURL(rs.getString("UPLOAD_LOCATION"), rs.getString("CDN_FILE_NAME")));
            comment.setCommentdate(rs.getString("created_date"));
            comment.setCommentPeriod(Utility.formatElapsedTime(getCommentTimeinSecs(rs.getString("created_date"))));
            comment.setCommentPicture(FileManagementUtil.getFileURL(rs.getString("COMMENT_UPLOAD_LOCATION"), rs.getString("COMMENT_CDN_FILE_NAME")));
        } catch (Exception e) {
            throw new SQLException("Exception:", e);
        }
        return (comment);
    }

    private long getCommentTimeinSecs(String dt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dt);
        long timeInMillisSinceEpoch = date.getTime() / 1000;
        return timeInMillisSinceEpoch;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse("2013-09-09 14:17:05");
        } catch (ParseException e) {
            Logger.error("Exception:", e);
        }
        String commentsBy = "timi@hotmail.com";
        Logger.debug("PRINT: "+ (commentsBy.contains("@")?commentsBy.substring(0, commentsBy.indexOf("@")):commentsBy));
    }
}