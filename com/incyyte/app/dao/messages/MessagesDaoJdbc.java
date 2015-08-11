package com.incyyte.app.dao.messages;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.dao.messages.rowmapper.DetailMessageRowMapper;
import com.incyyte.app.dao.messages.rowmapper.MessagesRowMapper;
import com.incyyte.app.dao.user.rowmapper.ContactNewRowMapper;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.Message;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.service.util.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessagesDaoJdbc implements MessagesDao {

    private AbstractDao abstractDao;

    @Override
    public List<Message> getMessages(Long userId, int offSet, int noOfRecordsPerPage) {
        List<Message> message = null;
        String messagesQuery = MessagesDaoQueries.getMessagesQuery(true);
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(userId);
        params.addParam(offSet);
        params.addParam(noOfRecordsPerPage);
        Logger.debug("userName" + userId + "Query=========" + messagesQuery);
        try {
            message = QueryHelper.doQuery(abstractDao, "getMessages", messagesQuery, params, new MessagesRowMapper());
        } catch (Exception e) {
            Logger.error("Unable to get Messages: Failed ",e);
        }
        return message;
    }

    @Override
    public int getMessagesCount(Long userId) {
        List<Message> message = null;
        String messagesQuery = MessagesDaoQueries.getMessagesQuery(false);
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(userId);
        Logger.debug("userName" + userId + "Query=========" + messagesQuery);
        try {
            message = QueryHelper.doQuery(abstractDao, "getMessageCount", messagesQuery, params, new MessagesRowMapper());
            return message.size();
        } catch (Exception e) {
            Logger.error("Unable to get Messages: Failed ",e);
        }
        return 0;
    }

    @Override
    public int getUnreadMessageCount(String email) {
        String unreadMessageCountQuery = MessagesDaoQueries.getUnreadMessageCount();
        QueryParameters params = new QueryParameters();
        params.addParam(email);
        params.addParam(email);
        int unreadCount = 0;
        try {
            unreadCount = QueryHelper.doQueryForInt(abstractDao, "getUnreadMessageCount", unreadMessageCountQuery, params);
            Logger.debug("unreadCount#############indao" + unreadCount);
            return unreadCount;
        } catch (Exception e) {
            Logger.error("Unable to get Unread Messages Count: Failed ",e);
        }
        return 0;
    }

    @Override
    public void markAsRead(long messageId, String email) {
        String markAsReadQuery = MessagesDaoQueries.markUnreadAsRead();
        Logger.debug("update read query::" + markAsReadQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(messageId);
        params.addParam(email);
        try {
            int n = QueryHelper.doUpdate(abstractDao, "markAsRead", markAsReadQuery, params);
            Logger.debug("rows marked as read" + n);
        } catch (Exception e) {
            Logger.error("Unable to Mark as Read: Failed ",e);
        }
    }

    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public int deleteConversation(long userId, String deletedBy) {
        //If sent by does matches with the email address of the person
        // then he is the user of the message conversation
        StringBuilder userUpdate = new StringBuilder();
        userUpdate.append(" update messages ");
        userUpdate.append("    set MSG_USER_DEL ='Y' ");
        userUpdate.append(" where IFNULL(composed_msg_id,id) = ? ");
        userUpdate.append("   and sent_by = ? ");
        //If sent by does not match with the email address of the person
        // then he is the contact of the message conversation
        StringBuilder contactUpdate = new StringBuilder();
        contactUpdate.append(" update messages");
        contactUpdate.append("    set MSG_CONTACT_DEL ='Y' ");
        contactUpdate.append("  where IFNULL(composed_msg_id,id) = ? ");
        contactUpdate.append("    and sent_by <> ? ");

        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(deletedBy);
        JdbcTemplate template = null;
        try {
            abstractDao.getTxnHelper().beginTxn();
            template = abstractDao.getJdbcTemplate("deleteConversation");
            QueryHelper.doUpdate(template, userUpdate.toString(), params);
            QueryHelper.doUpdate(template, contactUpdate.toString(), params);
            abstractDao.getTxnHelper().commitTxn();
        } catch (Exception e) {
            Logger.error("Unable to delete conversation  ",e);
            abstractDao.getTxnHelper().rollbackTxn();
            return 0;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "deleteConversation");
        }
        return 1;
    }

    @Override
    public long sendMessage(User user, Message message) throws AccessException {
        long messageId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("MESSAGES_PK", false);
        String messagesQuery = MessagesDaoQueries.sendMessages();
        Logger.debug("query for reply" + messagesQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(messageId);
        params.addParam(message.getComposeMessageId() == 0 ? null : message.getComposeMessageId());
        params.addParam(message.getUserId());
        params.addParam(message.getContactId());
        params.addParam(message.getType().toString());
        params.addParam(message.getStatus().toString());
        params.addParam(message.getSentBy());
        params.addParam(message.getMessageText());
        try {
            int n = QueryHelper.doUpdate(abstractDao, "sendMessage", messagesQuery, params);
            Logger.debug("rows inserted for reply and send" + n);
            return messageId;
        } catch (Exception e) {
            Logger.error("Internal error message can't be sent",e);
            return 0;
        }
    }

    public long getContactId(long id, String contactUserName) {
        long contactId = 0;
        String contactIdQuery = MessagesDaoQueries.getContactIdQuery();
        QueryParameters params = new QueryParameters();
        Logger.debug("contactid::" + contactUserName + "userId" + id);
        params.addParam(id);
        params.addParam(contactUserName);
        try {
            contactId = (Long) QueryHelper.doQueryForObject(abstractDao, "getContactId", contactIdQuery, params, Long.class);
        } catch (Exception e) {
            Logger.error("Unable to get ContactId:  ",e);
        }
        return contactId;
    }

    public List<String> searchContacts(Long id, String contactName) {
        Logger.debug("inside dao" + contactName);
        JdbcTemplate template = abstractDao.getJdbcTemplate("searchContacts");
        String query = MessagesDaoQueries.searchContacts();
        List<String> contactsList = new ArrayList<String>();
        try {
            contactsList = (List<String>) template.query(query, new Object[]{id, contactName}, new ResultSetExtractor() {
                @Override
                public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    List<String> contactIds = new ArrayList<String>();
                    while (rs.next()) {
                        String contact = rs.getString("username");
                        contactIds.add(contact);
                    }
                    Logger.debug("before return" + contactIds);
                    return contactIds;
                }
            });
        } catch (Exception e) {
            Logger.error("Unable to get contactName:  ",e);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "searchContacts");
        }
        return contactsList;
    }

    @Override
    public List<Message> getDetailMessage(Long id, String usersEmail) {
        Logger.debug("inside dao" + id);
        QueryParameters params = new QueryParameters();
        params.addParam(id);
        params.addParam(usersEmail);
        params.addParam(usersEmail);

        String messageDetailQuery = MessagesDaoQueries.getDetailMessageQuery();
        Logger.debug("detail messge query::" + messageDetailQuery);
        try {
            return QueryHelper.doQuery(abstractDao, "getDetailMessage", messageDetailQuery, params, new DetailMessageRowMapper());
        } catch (Exception e) {
            Logger.error("Unable to get contactName:  ", e);
            return null;
        }
    }

    @Override
    public void deleteMessage(long msgId, String deletedBy) {
        Logger.debug("inside dao" + msgId);
        QueryParameters params = new QueryParameters();
        params.addParam(msgId);
        String deleteMessageQuery = MessagesDaoQueries.deleteMessage(deletedBy);
        try {
            QueryHelper.doUpdate(abstractDao, "deleteMessage", deleteMessageQuery, params);
        } catch (Exception e) {
            Logger.error("Unable to get contactName:  ", e);
        }
    }

    @Override
    public Contact memberExists(String username, long userid) throws Exception {
        Logger.debug("inside memberExists" + userid + "," + username);
        String memberExistsQuery = MessagesDaoQueries.getMemberExistsQuery();
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        params.addParam(username);
        Logger.debug("inside memberExists ------" + userid + "," + username);
        Logger.debug("value of userid::" + String.valueOf(userid));
        Contact contact = null;
        try {
            contact = (Contact) QueryHelper.doQueryForObject(abstractDao, "memberExists", memberExistsQuery, params, new ContactNewRowMapper() {
                @Override
                public Contact mapRow(ResultSet rs, int arg1) throws SQLException {
                    Contact contact = new Contact();
                    contact.setEmail(rs.getString("email"));
                    contact.setStatus(rs.getString("status"));
                    contact.setBlocked(rs.getString("blocked"));
                    contact.setAccept_inv(rs.getString("accept_inv"));
                    contact.setActive_ind(rs.getString("active_ind"));
                    return contact;
                }
            });
            return contact;
        } catch (EmptyResultDataAccessException e) {
        	//while sending a message to invalid contacts from compose_new_message in  messages section
            throw new Exception(username + " is not a valid contact. Please re-enter");
        } catch (Exception e) {
            Logger.error("memberExists:Exception:", e);
            throw e;
        }
    }

    @Override
    public Contact getContactsContact(long userId, long contactId, String contactEmail) throws Exception {
        Contact contact = null;
        String contactsQuery = MessagesDaoQueries.getContactsContact();
        Logger.debug("query for contactid" + contactsQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(contactId);
        try {
            contact = (Contact) QueryHelper.doQueryForObject(abstractDao, "getContactsContact", contactsQuery, params, new ContactNewRowMapper());
        } catch (Exception e) {
            Logger.error("Exception:",e);
            throw new Exception("You are not a contact of " + contactEmail);
        }
        return contact;
    }

    @Override
    public Contact getContact(long contactId) {
        Contact contact = null;
        String contactsQuery = MessagesDaoQueries.getContact();
        Logger.debug("query for contactid" + contactsQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(contactId);
        try {
            contact = (Contact) QueryHelper.doQueryForObject(abstractDao, "getContact", contactsQuery, params, new ContactNewRowMapper());
        } catch (Exception e) {
            Logger.error("Exception:",e);
        }
        return contact;
    }

    @Override
    public String getSentBy(long msgId) {
        String sentBy = null;
        String sentByQuery = MessagesDaoQueries.getSentByQuery();
        QueryParameters params = new QueryParameters();
        params.addParam(msgId);
        try {
            sentBy = (String) QueryHelper.doQueryForObject(abstractDao, "getSentBy", sentByQuery, params, String.class);
        } catch (Exception e) {
            Logger.error("Unable to get ContactId:  ",e);
        }
        return sentBy;
    }
}