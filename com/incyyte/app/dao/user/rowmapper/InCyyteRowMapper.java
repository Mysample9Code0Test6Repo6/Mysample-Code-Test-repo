package com.incyyte.app.dao.user.rowmapper;


/*
* Created on Jun 2011
*
* Window - Preferences - Java - Code Style - Code Templates
*/

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.util.FileManagementUtil;


/**
 * @author Timi Boboye
 *         <p/>
 *         A RowMapper implementation for mapping to User Registered.
 *         <p/>
 *         Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 *         please be cautious if modifying this class!
 */

@SuppressWarnings("rawtypes")
public class InCyyteRowMapper implements RowMapper {
    /**
     *
     */
    public InCyyteRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
        InCyyte incyyte = new InCyyte();
        try {
            incyyte.setId(rs.getLong("QUESTIONID"));
            incyyte.setUserId(rs.getLong("FK_USERID"));
            incyyte.setIncyyte(rs.getString("QUESTION"));
            incyyte.setCategory(rs.getString("CATEGORY"));
            Blob uploadBlob = rs.getBlob("UPLOAD");
            if (uploadBlob != null)
                incyyte.setUpload(uploadBlob.getBytes(1, (int) uploadBlob.length()));
            incyyte.setUpload_type(rs.getString("UPLOAD_TYPE"));
            incyyte.setUpload_name(rs.getString("UPLOAD_NAME"));
            incyyte.setCdnFileName(rs.getString("CDN_FILE_NAME"));
            incyyte.setUploadLocation(FileManagementUtil.getFileURL(rs.getString("UPLOAD_LOCATION"), rs.getString("CDN_FILE_NAME")));
            incyyte.setContent_type(rs.getString("CONTENT_TYPE"));

            incyyte.setRandomize(rs.getBoolean("RANDOMIZE"));
            incyyte.setMultiSelection(rs.getBoolean("MULTI_SELECTION"));
            incyyte.setXmlString(rs.getString("XMLSTRING"));
            incyyte.setBackgroundId(rs.getString("BACKGROUND"));
            incyyte.setIncyyteCode(rs.getString("VIEWCHART_CODE"));
            incyyte.setLink(rs.getString("LINK"));
            incyyte.setTotalInCyyted(rs.getInt("TOTAL_INCYYTED"));
            incyyte.setTotalResponded(rs.getInt("TOTAL_RESPONDED"));
            incyyte.setAnonymity(rs.getInt("ANONYMITY") == 1 ? true : false);
            incyyte.setPageName(rs.getString("PAGE_NAME"));
            incyyte.setQuesType(rs.getString("TYPE"));

            incyyte.setAllowComment(rs.getString("ALLOW_COMMENT"));
            incyyte.setPollResultHidden(rs.getString("POLL_RESULT_HIDDEN"));
            incyyte.setYoutubeUrl(rs.getString("youtube_url"));
            incyyte.setUpload_logo_location(rs.getString("UPLOAD_LOGO_LOCATION"));
            incyyte.setStrapline(rs.getString("STRAPLINE"));
            incyyte.setProtectPage(rs.getString("PROTECTPAGE"));
            incyyte.setPublic_poll(rs.getString("PUBLIC_POLL"));
            incyyte.setAccess_code(rs.getString("ACCESS_CODE"));

            incyyte.setCreatedDate(rs.getDate("SENT_DATE") != null ? rs.getDate("SENT_DATE").toString() : null);
            incyyte.setIncyyteCreatedDate(rs.getDate("SENT_DATE"));
            
            incyyte.setCreatedDatePeriod(Utility.formatElapsedTime(Utility.getCommentTimeinSecs(rs.getString("SENT_DATE") != null ? rs.getString("SENT_DATE").toString() : getTodaysDate() )));
            incyyte.setIncyyteCreatedDateFmt(getSentDateFmt((java.util.Date)rs.getDate("SENT_DATE")));


            Timestamp timestamp = rs.getTimestamp("CLOSURE_DATE");
            incyyte.setClosureDate(timestamp != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp) : null);
            incyyte.setIncyyteClosedDate(rs.getDate("CLOSURE_DATE"));

            incyyte.setDeleted(rs.getString("DELETE_IND").equals("N") ? false : true);
            incyyte.setPublished(rs.getString("PUBLISH_IND").equals("N") ? false : true);
            incyyte.setCreatedBy(rs.getString("CREATED_BY"));
            incyyte.setGrpId(rs.getLong("FK_GROUPID"));
            incyyte.setSendMethod(rs.getString("SEND_METHOD"));
            incyyte.setSendZone(rs.getString("SEND_ZONE"));
            incyyte.setSendType(rs.getString("SENDTYPE"));
            incyyte.setTemplateId(rs.getString("POLL_PAGE_TEMPLATE"));
        } catch (Exception e) {
            throw new SQLException("Exception:", e);
        }
        return (incyyte);
    }
    
    private String getTodaysDate(){
    	String formattedDate = null;
    	try {    	     
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            formattedDate = dateformat.format(new Date());
            Logger.debug("Date: " + formattedDate);
    	} catch (Exception e) {
            Logger.error("Exception:", e);
        }
    	return formattedDate;
    }

    private String getSentDateFmt(java.util.Date date){
    	String formattedDate = null;
    	try {    	     
            DateFormat dateformat = new SimpleDateFormat("d MMM yy");
            formattedDate = dateformat.format(date);
            Logger.debug("Fmt date:  "+ formattedDate);
    	} catch (Exception e) {
            Logger.error("Exception:", e);
        }
    	return formattedDate;
    }
} //end, 
