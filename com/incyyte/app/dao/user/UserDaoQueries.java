package com.incyyte.app.dao.user;

import com.incyyte.app.service.util.Constants;

public class UserDaoQueries {

    private static StringBuffer registerUserQuery = new StringBuffer(
            "INSERT INTO users " +
                    "(userid,username,password,email,mobile,status,gender,ageGroup, acceptTerms, created_by, created_date, defining_question) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?, sysdate(), 'I use inCyyte to get opinions on my thoughts and questions from groups of friends, workmates & my communities') ");

    private static StringBuffer insertQuestionQuery = new StringBuffer(
            "INSERT INTO questions " +
                    "(questionid,fk_userid,question,category,background,viewchart_code,created_by," +
                    "upload,upload_name,upload_ext,upload_type,content_type,type,upload_location,link," +
                    "anonymity,page_name,sendType,public_poll,allow_comment,protectPage,strapline," +
                    "upload_logo_location,access_code,created_date,cdn_file_name, poll_Result_Hidden, poll_page_template, youtube_url) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate(),?,?,?,?) ");

    private static StringBuffer insertGroupQuery = new StringBuffer(
            "INSERT INTO GROUPS (groupid, fk_userid, name, type ,description) VALUES (?, ?, ?, ?, ?)");

    private static StringBuffer insertGroupRegionQuery = new StringBuffer(
            "INSERT INTO GROUPS (groupid, fk_userid, name, type ,description, postal_region) VALUES (?, ?, ?, ?, ?, ?)");

    private static StringBuffer insertNewContactQuery = new StringBuffer(
            "INSERT INTO CONTACTS " +
                    "(CONTACTID, FK_USERID, NICKNAME, FIRSTNAME, LASTNAME, EMAIL, MOBILE, CREATED_BY, MODIFIED_BY, SENT_INVITE, NOTE, INVITATIONID, ACCEPT_INV, SN_ID, SN_FROM, STATUS,CREATED_DATE, MODIFIED_DATE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate(), sysdate())");

    private static StringBuffer insertInCyyteQuery = new StringBuffer(
            "INSERT INTO INCYYTE (fk_groupid, fk_questionid, total_incyyted, closure_date, created_date, sent_date) " +
                    "VALUES (?, ?, ?, ?, sysdate(), sysdate())");

    private static StringBuffer insertInCyyteRegionQuery = new StringBuffer(
            "INSERT INTO INCYYTE (fk_groupid, fk_questionid, total_incyyted, closure_date, country_code, send_method, send_zone, created_date, sent_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, sysdate(), sysdate())");

    private static StringBuffer mailListQuery = new StringBuffer(
            "SELECT DISTINCT C.CONTACTID, C.FK_USERID, C.NICKNAME, C.FIRSTNAME " +
                    ", C.LASTNAME, C.EMAIL, C.MOBILE, Q.xmlString , GC.MEMBERID " +
                    ", C.BLOCKED, C.STATUS, C.INVITATIONID " +
                    " FROM CONTACTS C, QUESTIONS Q, GRP_CONTACTS GC, INCYYTE IC, grp_shared_incyyte gsi  " +
                    " WHERE C.FK_USERID = Q.FK_USERID   " +
                    "   AND GC.FK_CONTACTID = C.CONTACTID " +
                    "   AND IC.fk_groupid = GC.fk_groupid " +
                    "   AND gsi.fk_member_id = gc.memberid " +
                    "   AND gsi.fk_question_id = ic.fk_questionid " +
                    "   AND IC.fk_questionid = Q.questionid " +
                    "   AND GC.active_ind = 'A' " +
                    "   AND IC.fk_questionid = ? ");

    private static StringBuffer logFailedEmailQuery = new StringBuffer(
            "INSERT INTO FAILEDEMAIL_AUDIT (userid, email_type, status, process_num) " +
                    "VALUES (?, ?, ?, ?)");

    private static StringBuffer xmlStringQuery = new StringBuffer(
            "UPDATE QUESTIONS SET xmlString = ?, modified_date = sysdate() WHERE questionid = ? ");

    private static StringBuffer inCyyteQuery = new StringBuffer(
            "SELECT que.questionid, que.fk_userid,que.question,que.category, " +
                    "que.upload,que.upload_type,que.upload_name,que.upload_location,que.content_type,que.randomize, " +
                    "que.multi_selection,que.xmlString, " +
                    "que.background,que.viewchart_code,que.link,inc.total_incyyted,inc.total_responded,que.anonymity,que.page_name,que.type,  " +
                    "que.allow_comment,que.upload_logo_location,que.strapline,que.protectPage,que.public_poll,que.access_code, " +
                    "inc.sent_date,inc.closure_date,que.delete_ind,que.publish_ind, que.created_by, " +
                    "comp.company_name,comp.company_logo_url,comp.banner_url,comp.company_info_para1,comp.company_info_para2, fk_groupid " +
                    " ,que.cdn_file_name, que.poll_Result_Hidden , que.youtube_url, que.sendType, user.upload_location as user_file_type, user.cdn_file_name as sender_profile_pic " +
                    " FROM questions que " +
                    " join incyyte inc on que.questionid = inc.fk_questionid " +
                    " left join company_acct comp on que.fk_userid = comp.fk_userid " +
                    " join users user on que.fk_userid = user.userid " +
                    "WHERE que.questionid = ? ");
    
    private static StringBuffer inCyyteQueryByCode = new StringBuffer(
            "SELECT que.questionid, que.fk_userid,que.question,que.category, " +
                    "que.upload,que.upload_type,que.upload_name,que.upload_location,que.content_type,que.randomize, " +
                    "que.multi_selection,que.xmlString, " +
                    "que.background,que.viewchart_code,que.link,inc.total_incyyted,inc.total_responded,que.anonymity,que.page_name,que.type,  " +
                    "que.allow_comment,que.upload_logo_location,que.strapline,que.protectPage,que.public_poll,que.access_code, " +
                    "inc.sent_date,inc.closure_date,que.delete_ind,que.publish_ind, que.created_by, " +
                    "comp.company_name,comp.company_logo_url,comp.banner_url,comp.company_info_para1,comp.company_info_para2, fk_groupid " +
                    " ,que.cdn_file_name, que.poll_Result_Hidden , que.youtube_url, que.sendType, user.upload_location as user_file_type, user.cdn_file_name as sender_profile_pic " +
                    " FROM questions que " +
                    " join incyyte inc on que.questionid = inc.fk_questionid " +
                    " left join company_acct comp on que.fk_userid = comp.fk_userid " +
                    " join users user on que.fk_userid = user.userid " +
                    "WHERE que.viewchart_code = ? ");
    
    private static StringBuffer userInCyyteQuery = new StringBuffer(
            "SELECT questionid, fk_userid, question, category, upload, upload_type, " +
                    "upload_name, upload_location, content_type, randomize, multi_selection, " +
                    "xmlString, background, viewchart_code, link, total_incyyted, total_responded, " +
                    "anonymity, page_name, type,  allow_comment, upload_logo_location, strapline,  " +
                    "protectPage, public_poll, access_code, que.cdn_file_name, " +
                    "que.poll_Result_Hidden, que.youtube_url, sent_date, closure_date, delete_ind, " +
                    "publish_ind, que.created_by, fk_groupid, send_method, send_zone, sendType, que.poll_page_template " +
                    "FROM questions que INNER JOIN incyyte ic  " +
                    "ON ic.fk_questionid = que.questionid " +
                    "WHERE sendType = ?  " +
                    "  AND fk_userid = ? " +
                    "  AND ic.closure_date > sysdate() " +
                    "ORDER BY que.created_date DESC ");

    private static StringBuffer inCyyteByCodeQuery = new StringBuffer(
            "SELECT questionid, fk_userid, question, category, " +
                    "upload, upload_type, upload_name, upload_location, content_type, randomize, " +
                    "multi_selection, xmlString, " +
                    "background, viewchart_code, link, total_incyyted, total_responded, anonymity, page_name, type,  " +
                    "allow_comment, upload_logo_location, strapline, protectPage, public_poll, access_code, que.cdn_file_name, que.poll_Result_Hidden, que.youtube_url,  " +
                    "sent_date, closure_date, delete_ind, publish_ind, que.created_by, fk_groupid, send_method, send_zone, sendType, poll_page_template  " +
                    "FROM questions que, incyyte " +
                    "WHERE fk_questionid = questionid  AND " +
                    "viewchart_code = ? ");

    private static StringBuffer answerQuery = new StringBuffer(
            "SELECT answerid, fk_questionid, response, upload_type, upload_name, upload_ext, upload_location, cdn_file_name, url_link , youtube_url FROM answers WHERE fk_questionid = ? ");

    private static StringBuffer isContactRespondedQuery = new StringBuffer(
            "SELECT count(*)  " +
                    "FROM answers a, responses b " +
                    "WHERE a.answerid = b.fk_answerid " +
                    "AND a.fk_questionid = ? " +
                    "AND b.fk_memberid = ? ");

    private static StringBuffer isRegionalContactRespondedQuery = new StringBuffer(
            "SELECT count(*)   " +
                    "FROM answers a STRAIGHT_JOIN regional_responses b " +
                    "ON a.answerid = b.fk_answerid " +
                    "WHERE a.fk_questionid = ? " +
                    "AND b.fk_userid = ? ");

    private static StringBuffer isSharedContactRespondedQuery = new StringBuffer(
            "SELECT count(*)  " +
                    "FROM answers a, responses b " +
                    "WHERE a.answerid = b.fk_answerid " +
                    "AND a.fk_questionid = ? " +
                    "AND b.fk_sharedid = ? ");

    private static StringBuffer isRecipientRespondedQuery = new StringBuffer(
            "SELECT count(*)  " +
                    "FROM answers a, posted_responses b " +
                    "WHERE a.answerid = b.fk_answerid " +
                    "AND a.fk_questionid = ? " +
                    "AND b.fk_userid = ? ");

    private static StringBuffer inCyyteResponsesQuery = new StringBuffer(
            "SELECT resp.fk_answerid, resp.fk_questionid, resp.fk_memberid, resp.gender, resp.ageGroup, resp.fk_sharedid  " +
                    "FROM answers aws STRAIGHT_JOIN responses resp " +
                    "ON resp.fk_answerid = aws.answerid " +
                    "WHERE aws.fk_questionid = ?  "
    );

    private static StringBuffer inCyyteRegionalResponsesQuery = new StringBuffer(
            " SELECT resp.fk_answerid, resp.fk_userid, resp.gender, resp.ageGroup " +
                    " FROM answers aws STRAIGHT_JOIN regional_responses resp " +
                    " ON resp.fk_answerid = aws.answerid " +
                    " WHERE aws.fk_questionid = ?  "
    );

    private static StringBuffer inCyytePostedResponsesQuery = new StringBuffer(
            " SELECT resp.fk_userid, resp.fk_answerid, u.gender, u.ageGroup " +
                    " FROM posted_responses resp, answers aws, users u   " +
                    " WHERE resp.fk_answerid = aws.answerid " +
                    " AND resp.fk_userid = u.userid " +
                    " AND resp.fk_questionid = ? "
    );

    private static StringBuffer incrementTotalInCyytedQuery = new StringBuffer(
            " UPDATE incyyte " +
                    " SET total_incyyted = total_incyyted + 1 " +
                    " where fk_groupid = ? ");

    private static StringBuffer incrementRegionalTotalInCyytedQuery = new StringBuffer(
            " UPDATE incyyte " +
                    " SET total_incyyted = total_incyyted + 1 " +
                    " WHERE send_zone = ? " + //county Name
                    " OR CASE WHEN send_method = '" + Constants.POST_TO_POSTCODE + "' THEN send_zone = ? " +
                    "         WHEN send_method = '" + Constants.POST_TO_REGION + "' THEN send_zone = substring(?, 1,locate(' ', ?)) END ");


    private static StringBuffer incrementSharedInCyytedQuery = new StringBuffer(
            " UPDATE incyyte " +
                    " SET total_incyyted = total_incyyted + ? " +
                    " where fk_groupid = ? and fk_questionid = ? ");

    private static StringBuffer incrementGeneralInCyyteResponseQuery = new StringBuffer(
            " UPDATE incyyte " +
                    " SET total_responded = total_responded + 1, " +
                    "         total_incyyted = total_incyyted + 1 " +
                    " WHERE fk_questionid = ? ");

    private static StringBuffer incrementInCyyteResponseQuery = new StringBuffer(
            " UPDATE incyyte " +
                    " SET total_responded = total_responded + 1, " +
                    "         closed = IF (total_incyyted = total_responded, 'Y', 'N') " +
                    " WHERE fk_questionid = ? ");
    private static StringBuffer userIncomingQuery = new StringBuffer(
            " SELECT ( " +
                    " (SELECT count(*) " + //RECEIVED
                    " 	FROM contacts c, incyyte ic, grp_contacts gc,grp_shared_incyyte gsi " +
                    " 	WHERE c.contactid = gc.fk_contactid " +
                    " 	AND gc.fk_groupid = ic.fk_groupid " +
                    "    AND gsi.fk_member_id = gc.memberid " +
                    "    AND gsi.fk_question_id = ic.fk_question_id " +
                    " 	AND c.email = ?) " +
                    "  - " +//MINUS
                    " (SELECT count(*) " + //RESPONDED
                    " 	FROM contacts c, grp_contacts gc, responses r " +
                    " 	WHERE c.contactid = gc.fk_contactid " +
                    " 	AND gc.memberid = r.fk_memberid " +
                    " 	AND c.email = ?) " +
                    " ) AS INCOMING FROM DUAL");

    private static StringBuffer userSentQuery = new StringBuffer(
            " SELECT count(q.questionid) FROM users u, questions q, incyyte ic " +
                    " WHERE u.userid = q.fk_userid " +
                    " AND q.questionid = ic.fk_questionid " +
                    " AND u.email = ?");

    private static StringBuffer userCompletedQuery = new StringBuffer(
            " SELECT count(q.questionid) FROM users u, questions q, incyyte ic " +
                    " WHERE u.userid = q.fk_userid " +
                    " AND q.questionid = ic.fk_questionid " +
                    " AND u.email = ? " +
                    " AND ic.closed = 'Y'");

    private static StringBuffer userPetitionQuery = new StringBuffer(
            " SELECT count(q.questionid) FROM users u, questions q " +
                    " WHERE u.userid = q.fk_userid " +
                    " AND u.email = ? " +
                    " AND q.type = 'P'");

    private static StringBuffer closeInCyyteQuery = new StringBuffer(
            "UPDATE incyyte " +
                    "SET closed = 'Y' " +
                    "WHERE fk_groupid = ? " +
                    "AND fk_questionid = ? ");

    private static StringBuffer myInCyytesQuery = new StringBuffer(
            " SELECT questionid, total_incyyted, total_responded, send_method,send_zone  " +
                    " FROM  users u STRAIGHT_JOIN questions q STRAIGHT_JOIN incyyte ic  " +
                    " ON q.fk_userid  =u.userid  " +
                    " WHERE q.questionid = ic.fk_questionid   " +
                    "   AND ic.closure_date > sysdate() " +
                    " AND u.email =? " +
                    " AND q.sendtype =? " +
                    " ORDER BY q.created_date DESC ");

    private static StringBuffer myInCyytesCriteriaQuery = new StringBuffer(
            " SELECT questionid, total_incyyted, total_responded, send_method, send_zone  " +
                    " FROM users u, questions q, incyyte ic " +
                    " WHERE u.userid = q.fk_userid " +
                    " AND q.questionid = ic.fk_questionid " +
                    " AND u.email = ? " +
                    " AND q.question like  ? " +
                    " ORDER BY q.created_date DESC ");

    private static StringBuffer viewRegionalAllQuery = new StringBuffer(
            " SELECT distinct  ic.fk_questionid as questionid, ic.total_incyyted, ic.total_responded, ic.created_date " +
                    " FROM incyyte  ic  STRAIGHT_JOIN   questions q " +
                    " ON ic.fk_questionid  = q.questionid " +
                    " WHERE q.delete_ind <> 'Y' " +
                    " AND  ic.closure_date > sysdate() " +
                    " and (ic.send_zone = ?  " +
                    " OR CASE WHEN ic.send_method = 'POSTCODE' THEN ic.send_zone = ? " +
                    " WHEN ic.send_method = 'REGION'  THEN ic.send_zone = substring(? , 1,locate(' ', ? )) END  ) " +
                    " ORDER BY ic.created_date DESC "
    );

    private static StringBuffer viewRegionalAllCriteriaQuery = new StringBuffer(
            " SELECT distinct fk_questionid as questionid, total_incyyted, total_responded, i.created_date  FROM  incyyte i, questions q  " +
                    " WHERE send_zone = ?  " +
                    " OR CASE WHEN send_method = '" + Constants.POST_TO_POSTCODE + "' THEN send_zone = ? " +
                    "    WHEN send_method = '" + Constants.POST_TO_REGION + "' THEN send_zone = substring(?, 1,locate(' ', ?)) END  " +
                    " AND q.questionid = fk_questionid " +
                    " AND q.question like  ? " +
                    " ORDER BY i.created_date DESC "
    );

    private static StringBuffer viewRegionalPostcodeQuery = new StringBuffer(
            " SELECT distinct fk_questionid as questionid, total_incyyted, total_responded, created_date  FROM  incyyte  " +
                    " WHERE send_zone = ?  " +
                    " AND send_method = '" + Constants.POST_TO_POSTCODE + "' " +
                    " ORDER BY created_date DESC ");

    private static StringBuffer viewRegionalCountyQuery = new StringBuffer(
            " SELECT distinct fk_questionid as questionid, total_incyyted, total_responded, created_date  FROM  incyyte  " +
                    " WHERE send_zone = ?  " +
                    " AND send_method = '" + Constants.POST_TO_COUNTY + "' " +
                    " ORDER BY created_date DESC ");

    private static StringBuffer viewRegionalQuery = new StringBuffer(
            " SELECT distinct fk_questionid as questionid, total_incyyted, total_responded, created_date  FROM  incyyte  " +
                    " WHERE send_zone = substring(?, 1,locate(' ', ?))  " +
                    " AND send_method = '" + Constants.POST_TO_REGION + "' " +
                    " ORDER BY created_date DESC ");

    private static StringBuffer viewPostedResultsQuery = new StringBuffer(
            " SELECT resp.fk_questionid as questionid, total_incyyted, total_responded " +
                    " FROM posted_responses resp, incyyte ic, users u " +
                    " WHERE resp.fk_questionid = ic.fk_questionid " +
                    " AND resp.fk_userid = u.userid " +
                    " AND u.email = ? ");

    private static StringBuffer inCyyteByCreatedBy = new StringBuffer(
            "SELECT questionid, fk_userid, question, category, " +
                    "upload, upload_type, upload_name, upload_location, content_type, randomize, " +
                    "multi_selection, xmlString, " +
                    "background, viewchart_code, link, total_incyyted, total_responded, anonymity, page_name, type,  " +
                    "allow_comment, upload_logo_location, strapline, protectPage, public_poll, access_code, que.cdn_file_name, que.poll_Result_Hidden, que.youtube_url, " +
                    "sent_date, closure_date, delete_ind, publish_ind, que.created_by, fk_groupid, send_method, send_zone, sendType, que.poll_page_template " +
                    "FROM questions que, incyyte " +
                    "WHERE fk_questionid = questionid " +
                    "AND que.created_by = ? " +
                    "AND type = ? ");

    private static StringBuffer updateUsersNameQuery = new StringBuffer(
            " UPDATE users SET firstname = ? , lastname = ? WHERE email = ? ");

    private static StringBuffer deleteIncyyteQuery = new StringBuffer()
            .append("UPDATE questions ")
            .append("SET ")
            .append("delete_ind = 'Y' ")
            .append("WHERE questionid = ? ");


    private static StringBuffer updateIncyyteQuery = new StringBuffer()
            .append("UPDATE questions ")
            .append("SET ")
            .append("public_poll = ? , protectPage = ? ")
            .append("WHERE questionid = ? ");


    private static StringBuffer publishPollQuery = new StringBuffer()
            .append("UPDATE questions ")
            .append("SET ")
            .append("publish_ind = ? ")
            .append("WHERE questionid = ? ");

    private static StringBuffer updateClosureDateTimeQuery = new StringBuffer()
            .append("UPDATE incyyte ")
            .append("SET ")
            .append("closure_date = ? ")
            .append("WHERE fk_questionid = ? ");

    //TODO filter by category
    private static StringBuffer regionalRecipientQuery = new StringBuffer()
            .append("SELECT userid, email FROM users ")
            .append("left join USER_DOMAIN on userid = user_id ")
            .append("where postcode like ? ");

    private static StringBuffer postalCountyQuery = new StringBuffer()
            .append("select c.name as county_name, r.fk_countryid as country_code, p.code as postcode  ")
            .append("from counties c, postal_area p, regional_states r  ")
            .append("where p.fk_countyid = c.countyid  ")
            .append("and c.fk_reg_stateid = r.reg_stateID  ")
            .append("and p.code = ? ");

    private static StringBuffer userDomainByIdQuery = new StringBuffer(
            "SELECT county_name, country_code, postcode FROM user_domain WHERE user_id =? ");

    private static StringBuffer userDomainByEmailQuery = new StringBuffer(
            " SELECT county_name, country_code, postcode " +
                    " FROM user_domain ud, users u  " +
                    " WHERE ud.user_id = u.userid " +
                    " AND u.email = ? ");

    private static StringBuffer checkIfinCyyteExistQuery = new StringBuffer(
            "SELECT questionid, fk_userid, question, category, " +
                    "upload, upload_type, upload_name, upload_location, content_type, randomize, " +
                    "multi_selection, xmlString, " +
                    "background, viewchart_code, link, total_incyyted, total_responded, anonymity, page_name, type,  " +
                    "allow_comment, upload_logo_location, strapline, protectPage, public_poll, access_code, que.cdn_file_name, que.poll_Result_Hidden, que.youtube_url, " +
                    "sent_date, closure_date, delete_ind, publish_ind, que.created_by, fk_groupid, send_method, send_zone, sendType, poll_page_template    " +
                    "FROM questions que, incyyte " +
                    "WHERE fk_questionid = questionid  AND " +
                    "viewchart_code = ? ");

    private static StringBuffer pollMemberIdQuery = new StringBuffer(
            "   select memberid " +
                    "    from grp_contacts  gc " +
                    "    STRAIGHT_JOIN contacts CNT ON gc.fk_contactid = CNT.contactid AND CNT.email = ? " +
                    "    STRAIGHT_JOIN incyyte ICT ON gc.fk_groupid = ICT.fk_groupid AND ICT.fk_questionid = ? ");

    private static StringBuffer checkPollSharedQuery = new StringBuffer(
            " select distinct share_id from shared_incyyte  " +
                    " where fk_contactid in  " +
                    " (" +
                    " SELECT CNT.contactid from contacts CNT " +
                    " where CNT.email = ? " +
                    " )  " +
                    " and fk_questionid = ? ");

    private static StringBuffer isDeletedIncyyteQuery = new StringBuffer(
            "SELECT questionId FROM DELETED_INCYYTE WHERE questionId = ?  and userId = ?");

    private static StringBuffer ShareIDQuery = new StringBuffer(
            " SELECT share_id, share_fmt_ID, fk_questionid, fk_original_groupid, fk_contactid, poll_owner_userid, poll_sharer_userid " +
                    "  FROM shared_incyyte, contacts  " +
                    "  WHERE fk_questionid = ? " +
                    "  AND fk_contactid = contactid " +
                    " and email = ?  ");


    private static StringBuffer alreadyPolledQuery = new StringBuffer(
            " SELECT ( " +
                    " (select count(*) from grp_contacts a, incyyte b " +
                    " where a.fk_groupid = b.fk_groupid " +
                    " and a.fk_contactid = ? " +
                    " and b.fk_questionid = ? ) " +
                    " + " +
                    " (select count(*) from incyyte a, shared_incyyte b " +
                    " where b.fk_original_groupid = a.fk_groupid  " +
                    " and b.fk_contactid =  ?  " +
                    " and b.fk_questionid = ? ) " +
                    " ) AS ALREADY_POLLED FROM DUAL ");

    private static StringBuffer alreadyVotedQuery =new StringBuffer(
    		" SELECT si.fk_questionid " +
            " FROM answers a, responses b , shared_incyyte si " +
    		" STRAIGHT_JOIN contacts CNT ON si.fk_contactid = CNT.contactid " +
            " WHERE a.answerid = b.fk_answerid " +
    		" AND a.fk_questionid = si.fk_questionid " +
            " AND b.fk_sharedid = si.share_id " +
    		" AND CNT.email = ? " +
            " UNION ALL " +
    		" SELECT ICT.fk_questionid " +
    		" FROM answers a, responses b , grp_contacts  gc " +
    		" STRAIGHT_JOIN contacts CNT ON gc.fk_contactid = CNT.contactid " +
    		" STRAIGHT_JOIN incyyte ICT ON gc.fk_groupid = ICT.fk_groupid  " +
    		" WHERE a.answerid = b.fk_answerid " +
    		" AND a.fk_questionid = ICT.fk_questionid " +
    		" AND b.fk_memberid = gc.memberid " +
    		" AND CNT.email = ? "  ); 

    public static StringBuffer getAlreadyPolledQuery() {
        return alreadyPolledQuery;
    }

    public static StringBuffer getIncrementRegionalTotalInCyytedQuery() {
        return incrementRegionalTotalInCyytedQuery;
    }

    public static StringBuffer getIncrementTotalInCyytedQuery() {
        return incrementTotalInCyytedQuery;
    }

    public static StringBuffer getIncrementSharedInCyytedQuery() {
        return incrementSharedInCyytedQuery;
    }

    public static StringBuffer getAlreadyVotedPollsQuery() {
        return alreadyVotedQuery;
    }

    public static StringBuffer getPollMemberIdQuery() {
        return pollMemberIdQuery;
    }

    public static StringBuffer getCheckPollSharedQuery() {
        return checkPollSharedQuery;
    }

    public static String getViewResultsQuery(boolean showArchivedPolls) {
        String viewResultsQuery = " SELECT question, questionid, total_incyyted, total_responded, created_date FROM( " +
        " SELECT STRAIGHT_JOIN q.question, ic.fk_questionid as questionid, total_incyyted, total_responded, q.created_date   " +
        " FROM questions q, incyyte ic, grp_shared_incyyte gsi, grp_contacts gc, contacts c  " +
        " WHERE c.contactid = gc.fk_contactid ";
      //Add this check if we want to hide Archived polls
        if (!showArchivedPolls) {
        	viewResultsQuery += " AND  ic.closure_date > sysdate() ";
        }
        viewResultsQuery += " AND gc.fk_groupid = ic.fk_groupid  " +       
        " AND gc.memberid = gsi.fk_member_id " +
        " AND gsi.fk_question_id = ic.fk_questionid " +
        " AND q.questionid = ic.fk_questionid " +
        " AND q.delete_ind  = 'N' " +
        " AND q.sendType  = 'mail' " +
        " AND c.email = ? " +
        "  UNION ALL " +
        " SELECT STRAIGHT_JOIN  q.question, ic.fk_questionid as questionid, total_incyyted, total_responded, q.created_date " +
        " FROM questions q,  incyyte ic,  shared_incyyte sh,  contacts c " +
        " WHERE c.contactid = sh.fk_contactid ";
        //Add this check if we want to hide Archived polls
        if (!showArchivedPolls) {
        	viewResultsQuery += " AND  ic.closure_date > sysdate() ";
        }
        viewResultsQuery += " AND sh.fk_questionid = q.questionid  " +
        " AND q.questionid = ic.fk_questionid " +
        " AND q.delete_ind  = 'N' " +
        "  AND q.sendType  = 'mail' " +
        " AND c.email = ? " +
        " ORDER BY created_date DESC " +
        " ) as t ";
        return viewResultsQuery;
    }

    public static StringBuffer getViewRegionalAllQuery() {
        return viewRegionalAllQuery;
    }

    public static StringBuffer getViewRegionalPostcodeQuery() {
        return viewRegionalPostcodeQuery;
    }

    public static StringBuffer getViewRegionalCountyQuery() {
        return viewRegionalCountyQuery;
    }

    public static StringBuffer getViewRegionalQuery() {
        return viewRegionalQuery;
    }

    public static StringBuffer getViewPostedResultsQuery() {
        return viewPostedResultsQuery;
    }

    public static StringBuffer getMyInCyytesQuery() {
        return myInCyytesQuery;
    }

    public static StringBuffer getCloseInCyyteQuery() {
        return closeInCyyteQuery;
    }

    public static StringBuffer getUserIncomingQuery() {
        return userIncomingQuery;
    }

    public static StringBuffer getUserSentQuery() {
        return userSentQuery;
    }

    public static StringBuffer getUserCompletedQuery() {
        return userCompletedQuery;
    }

    public static StringBuffer getUserPetitionQuery() {
        return userPetitionQuery;
    }

    public static StringBuffer getIncrementInCyyteResponseQuery() {
        return incrementInCyyteResponseQuery;
    }

    public static StringBuffer getInCyyteByCodeQuery() {
        return inCyyteByCodeQuery;
    }

    public static StringBuffer getInCyyteResponsesQuery() {
        return inCyyteResponsesQuery;
    }

    public static StringBuffer getInCyyteRegionalResponsesQuery() {
        return inCyyteRegionalResponsesQuery;
    }

    public static StringBuffer getInCyytePostedResponsesQuery() {
        return inCyytePostedResponsesQuery;
    }

    public static StringBuffer getLogFailedEmailQuery() {
        return logFailedEmailQuery;
    }

    public static StringBuffer getRegisterUserQuery() {
        return registerUserQuery;
    }

    public static StringBuffer getInsertQuestionQuery() {
        return insertQuestionQuery;
    }

    public static StringBuffer getInsertGroupQuery() {
        return insertGroupQuery;
    }

    public static StringBuffer getInsertGroupRegionQuery() {
        return insertGroupRegionQuery;
    }

    public static StringBuffer getInsertInCyyteQuery() {
        return insertInCyyteQuery;
    }

    public static StringBuffer getMailListQuery() {
        return mailListQuery;
    }

    public static StringBuffer getUpdateXmlStringQuery() {
        return xmlStringQuery;
    }

    public static StringBuffer getInCyyteQuery() {
        return inCyyteQuery;
    }

    
    public static StringBuffer getInCyyteQueryByCode() {
        return inCyyteQueryByCode;
    }

    public static StringBuffer getAnswerQuery() {
        return answerQuery;
    }

    public static StringBuffer getIsContactRespondedQuery() {
        return isContactRespondedQuery;
    }

    public static StringBuffer getIsRegionalContactRespondedQuery() {
        return isRegionalContactRespondedQuery;
    }

    public static StringBuffer getIsSharedContactRespondedQuery() {
        return isSharedContactRespondedQuery;
    }

    public static StringBuffer getIsRecipientRespondedQuery() {
        return isRecipientRespondedQuery;
    }

    public static StringBuffer getInCyyteByCreatedBy() {
        return inCyyteByCreatedBy;
    }

    public static StringBuffer getIncrementGeneralInCyyteResponseQuery() {
        return incrementGeneralInCyyteResponseQuery;
    }

    public static StringBuffer getUpdateUsersNameQuery() {
        return updateUsersNameQuery;
    }

    public static StringBuffer getUserInCyyteQuery() {
        return userInCyyteQuery;
    }

    public static StringBuffer getDeleteIncyyteQuery() {
        return deleteIncyyteQuery;
    }

    public static StringBuffer getPublishPollQuery() {
        return publishPollQuery;
    }

    public static StringBuffer getUpdateClosureDateTimeQuery() {
        return updateClosureDateTimeQuery;
    }

    public static StringBuffer getRegionalRecipientQuery() {
        return regionalRecipientQuery;
    }

    public static StringBuffer getCheckIfinCyyteExistQuery() {
        return checkIfinCyyteExistQuery;
    }

    public static StringBuffer getIsDeletedIncyyteQuery() {
        return isDeletedIncyyteQuery;
    }

    public static StringBuffer getInsertNewContactQuery() {
        return insertNewContactQuery;
    }

    public static StringBuffer getShareIDQuery() {
        return ShareIDQuery;
    }

    public static StringBuffer getPostalCountyQuery() {
        return postalCountyQuery;
    }

    public static StringBuffer getUserDomainByIdQuery() {
        return userDomainByIdQuery;
    }

    public static StringBuffer getUserDomainByEmailQuery() {
        return userDomainByEmailQuery;
    }

    public static StringBuffer getInsertInCyyteRegionQuery() {
        return insertInCyyteRegionQuery;
    }

    public static StringBuffer getUpdateIncyyteQuery() {
        return updateIncyyteQuery;
    }

    public static StringBuffer getMyInCyytesCriteriaQuery() {
        return myInCyytesCriteriaQuery;
    }

    public static StringBuffer getViewRegionalAllCriteriaQuery() {
        return viewRegionalAllCriteriaQuery;
    }
}