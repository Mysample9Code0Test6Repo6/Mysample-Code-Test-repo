package com.incyyte.app.dao.messages;

import com.incyyte.app.service.util.Logger;
import org.h2.util.StringUtils;

public class MessagesDaoQueries {

    public static String getMessagesQuery(boolean withLimit) {
        StringBuilder getMessages = new StringBuilder();
        //Skipping of Deleted messages display is taken care by MSG_USER_DEL and MSG_CONTACT_DEL columns
        //So no need of explicit conditions mentioning status != DELETED in this query
        getMessages.append(" select msg.id ");
        getMessages.append(" ,msg.composed_msg_id ");
        getMessages.append(" ,msg.user_id ");
        getMessages.append(" ,msg.contact_id ");
        getMessages.append(" ,msg2.msg_date ");
        getMessages.append(" ,msg.type ");
        getMessages.append(" ,msg.status ");
        getMessages.append(" ,msg.sent_by ");
        getMessages.append(" ,msg2.message_text ");
        getMessages.append(" ,ifnull(users.firstname,users.username) user_name ");
        getMessages.append(" ,msg_user.username contact_name ");
        getMessages.append(" ,msg_user.upload_location ");
        getMessages.append(" ,msg_user.cdn_file_name ");
        getMessages.append(" from messages msg ");
        getMessages.append(" ,messages msg2 ");
        getMessages.append(" ,users ");
        getMessages.append(" ,users msg_user ");
        getMessages.append(" where msg.user_id = ? ");
        getMessages.append(" and msg.type = 'NEW' ");
        getMessages.append(" and msg2.id = (select max(msg1.id) ");
        getMessages.append("                  from messages msg1 ");
        getMessages.append("        where msg1.status !='ARCHIVED' ");
        getMessages.append("                and ((msg1.sent_by = users.email and msg1.msg_user_del is null) ");
        getMessages.append("                   or (msg1.sent_by != users.email and msg1.msg_contact_del is null)) ");
        getMessages.append(" and ifnull(msg1.composed_msg_id,msg1.id) = msg.id) ");
        getMessages.append(" and msg.user_id = users.userid ");
        getMessages.append(" and msg2.user_id = msg_user.userid ");
        getMessages.append(" UNION ALL ");
        getMessages.append(" select msg.id  ");
        getMessages.append(" ,msg.composed_msg_id ");
        getMessages.append(" ,cnt_user.userid ");
        getMessages.append(" ,cnts_cnt.contactid ");
        getMessages.append(" ,msg2.msg_date ");
        getMessages.append(" ,msg.type ");
        getMessages.append(" ,msg.status ");
        getMessages.append(" ,msg.sent_by ");
        getMessages.append(" ,msg2.message_text ");
        getMessages.append(" ,ifnull(cnt_user.firstname,cnt_user.username) user_name ");
        getMessages.append(" , msg_user.username contact_name ");
        getMessages.append(" ,msg_user.upload_location ");
        getMessages.append(" ,msg_user.cdn_file_name ");
        getMessages.append("  from messages msg ");
        getMessages.append(" ,messages msg2 ");
        getMessages.append(" ,users cnt_user ");
        getMessages.append(" ,contacts ");
        getMessages.append(" ,contacts cnts_cnt ");
        getMessages.append(" ,users msg_user  ");
        getMessages.append(" where cnt_user.userid = ? ");
        getMessages.append(" and msg.type = 'NEW' ");
        getMessages.append("and msg2.id = (select max(msg1.id) ");
        getMessages.append("		       from messages msg1 ");
        getMessages.append("              where msg1.status !='ARCHIVED' ");
        getMessages.append("                and ((msg1.sent_by = cnt_user.email and msg1.msg_user_del is null) ");
        getMessages.append("                   or (msg1.sent_by != cnt_user.email and msg1.msg_contact_del is null)) ");
        getMessages.append("              and ifnull(msg1.composed_msg_id,msg1.id) = msg.id) ");
        getMessages.append(" and cnt_user.email = contacts.email ");
        getMessages.append(" and cnts_cnt.blocked!='Y' and cnts_cnt.active_ind !='D' ");
        getMessages.append(" and contacts.contactid  = msg.contact_id ");
        getMessages.append(" and msg2.user_id = msg_user.userid ");
        getMessages.append(" and cnt_user.userid = cnts_cnt.fk_userid ");
        getMessages.append(" and msg.sent_by = cnts_cnt.email ");
        getMessages.append(" order by msg_date desc  ");

        if (withLimit) {
            getMessages.append(" limit ? , ? ");
        }
        return getMessages.toString();
    }

    public static String deleteMessage(String deletedBy) {
        StringBuilder deleteConversation = new StringBuilder();
        deleteConversation.append(" update messages set ");
        if (StringUtils.equals(deletedBy, "USER")) {
            deleteConversation.append(" MSG_USER_DEL = 'Y' ");
        } else {
            deleteConversation.append(" MSG_CONTACT_DEL = 'Y' ");
        }
        deleteConversation.append(" where id = ? ");
        return deleteConversation.toString();
    }

    public static String sendMessages() {
        StringBuilder sendMessage = new StringBuilder();
        sendMessage.append(" insert into messages( ");
        sendMessage.append(" id, ");
        sendMessage.append(" composed_msg_id, ");
        sendMessage.append(" user_id, ");
        sendMessage.append(" contact_id,");
        sendMessage.append(" msg_date, ");
        sendMessage.append(" type, ");
        sendMessage.append(" status, ");
        sendMessage.append(" sent_by, ");
        sendMessage.append(" message_text) ");
        sendMessage.append(" values(?,?,?,?,SYSDATE(),?,?,?,?)");
        return sendMessage.toString();
    }

    public static String getContactIdQuery() {
        StringBuilder contactIdQuery = new StringBuilder();
        contactIdQuery.append(" select contactid from ");
        contactIdQuery.append(" contacts c,users u ");
        contactIdQuery.append(" where ");
        contactIdQuery.append(" c.email=u.email ");
        contactIdQuery.append(" and c.fk_userid=? ");
        contactIdQuery.append(" and u.username=? ");
        return contactIdQuery.toString();
    }

    public static String getSentByQuery() {
        StringBuilder contactIdQuery = new StringBuilder();
        contactIdQuery.append(" select sent_by from ");
        contactIdQuery.append(" messages where  ");
        contactIdQuery.append(" id=? ");
        return contactIdQuery.toString();
    }

    public static String searchContacts() {
        StringBuilder searchContacts = new StringBuilder();
        searchContacts.append(" select u.username ");
        searchContacts.append(" from contacts c , ");
        searchContacts.append(" users u ");
        searchContacts.append(" where c.fk_userid = ? ");
        searchContacts.append(" and c.email = u.email ");
        searchContacts.append(" and c.active_ind!='D' ");
        searchContacts.append(" and c.accept_inv!='N'  ");
        searchContacts.append(" and c.blocked !='Y' ");
        searchContacts.append(" and c.status !='NM' ");
        searchContacts.append(" and u.username like '%' || ? || '%' ");

        return searchContacts.toString();
    }

    public static String getDetailMessageQuery() {
        StringBuilder contactIdQuery = new StringBuilder();
        contactIdQuery.append(" select  msg.id ");
        contactIdQuery.append("   ,usr.firstname || ' ' || usr.lastname display_name ");
        contactIdQuery.append("  ,usr.username display_user ");
        contactIdQuery.append("  ,msg.message_text ");
        contactIdQuery.append("  ,msg.msg_date ");
        contactIdQuery.append("  ,usr.upload_location ");
        contactIdQuery.append("  ,usr.cdn_file_name ");
        contactIdQuery.append("  from messages msg ");
        contactIdQuery.append(" ,users usr ");
        contactIdQuery.append("  where ifnull(msg.composed_msg_id, msg.id) = ? ");
        contactIdQuery.append("    and msg.status !='ARCHIVED'  ");
        contactIdQuery.append("  and msg.user_id = usr.userid ");
        contactIdQuery.append("  and ((msg.sent_by = ? and msg_user_del is null) ");
        contactIdQuery.append("      or (msg.sent_by != ? and msg_contact_del is null)) ");
        contactIdQuery.append(" order by msg.id asc ");
        Logger.debug("contactIdQuery:::" + contactIdQuery);
        return contactIdQuery.toString();
    }

    public static String getUnreadMessageCount() {
        StringBuilder unreadMessageCountQuery = new StringBuilder();
        unreadMessageCountQuery.append(" SELECT count(*) ");
        unreadMessageCountQuery.append(" FROM messages m ");
        unreadMessageCountQuery.append(" WHERE m.sent_by in ");
        unreadMessageCountQuery.append(" (select email from contacts  ");
        unreadMessageCountQuery.append(" where blocked!='Y' and active_ind!='D' and ");
        unreadMessageCountQuery.append(" fk_userid=(select userid from users where email= ? )) ");
        unreadMessageCountQuery.append(" and m.contact_id in (select contactid from contacts where email = ?) ");
        unreadMessageCountQuery.append(" and m.msg_contact_del is null ");
        unreadMessageCountQuery.append(" and status='UNREAD' ");
        return unreadMessageCountQuery.toString();
    }

    public static String markUnreadAsRead() {
        StringBuilder markUnreadAsRead = new StringBuilder();
        markUnreadAsRead.append(" update messages set status='READ' where ifnull(composed_msg_id,id) = ? and status = 'UNREAD' and sent_by !=? ");

        return markUnreadAsRead.toString();
    }

    public static String getMemberExistsQuery() {
        StringBuilder memberExistsQuery = new StringBuilder();
        memberExistsQuery.append(" SELECT c.email, c.STATUS,c.BLOCKED,c.ACCEPT_INV,c.ACTIVE_IND FROM contacts c,users u ");
        memberExistsQuery.append(" WHERE c.email=u.email AND c.fk_userid=? and u.username=? ");
        return memberExistsQuery.toString();
    }

    public static String getContactsContact() {
        StringBuilder contactIdQry = new StringBuilder();
        contactIdQry.append(" select c2.CONTACTID, c2.FK_USERID, c2.NICKNAME, c2.FIRSTNAME, c2.LASTNAME, c2.EMAIL, c2.MOBILE ");
        contactIdQry.append(" ,c2.ACTIVE_IND, c2.CREATED_DATE, c2.MODIFIED_DATE, c2.CREATED_BY, c2.MODIFIED_BY, c2.SENT_INVITE, c2.NOTE, c2.STATUS ");
        contactIdQry.append(" ,c2.INVITATIONID, c2.ACCEPT_INV, c2.BLOCKED ");
        contactIdQry.append("  from users u1 ");
        contactIdQry.append("  ,contacts c1 ");
        contactIdQry.append("  ,users u2 ");
        contactIdQry.append("  ,contacts c2 ");
        contactIdQry.append("  where u1.userid = ? ");
        contactIdQry.append("    and c1.fk_userid = u1.userid ");
        contactIdQry.append("    and c1.contactid = ? ");
        contactIdQry.append("    and c1.email = u2.email ");
        contactIdQry.append("    and c2.fk_userid = u2.userid ");
        contactIdQry.append("    and c2.email = u1.email ");
        contactIdQry.append("    and c2.active_ind ='A' ");
        return contactIdQry.toString();
    }

    public static String getContact() {
        StringBuilder contactIdQry = new StringBuilder();
        contactIdQry.append(" select c2.CONTACTID, c2.FK_USERID, c2.NICKNAME, c2.FIRSTNAME, c2.LASTNAME, c2.EMAIL, c2.MOBILE ");
        contactIdQry.append(" ,c2.ACTIVE_IND, c2.CREATED_DATE, c2.MODIFIED_DATE, c2.CREATED_BY, c2.MODIFIED_BY, c2.SENT_INVITE, c2.NOTE, c2.STATUS ");
        contactIdQry.append(" ,c2.INVITATIONID, c2.ACCEPT_INV, c2.BLOCKED ");
        contactIdQry.append("  from contacts c2 ");
        contactIdQry.append("  where c2.contactid = ? ");
        return contactIdQry.toString();
    }
}