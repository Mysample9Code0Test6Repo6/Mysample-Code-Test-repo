package com.incyyte.app.manager;

import com.incyyte.app.dao.messages.MessagesDao;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.Message;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class MessagesManagerImpl implements MessagesManager {

    private MessagesDao messagesDao;
    private EmailManager emailMgr;
    
    @Autowired
    private RegistrationService registrationSrv;
    
    @Autowired
	private LoginService loginSrv;

    @Value("${incyyte.msg.url}")
    private String incyyteURL;

    @Value("${website.url}")
    private String websiteURL;

    public void setMessagesDao(MessagesDao messagesDao) {
        this.messagesDao = messagesDao;
    }

    public void setEmailMgr(EmailManager emailMgr) {
        this.emailMgr = emailMgr;
    }

    @Override
    public List<Message> getMessages(Long userId, int offSet, int recordsPerPage) {
        return messagesDao.getMessages(userId, offSet, recordsPerPage);
    }

    @Override
    public int deleteConversation(long id, String deletedBy) {
        return messagesDao.deleteConversation(id, deletedBy);
    }

    private void isContactValid(Contact contact) throws Exception {
        if (contact.getStatus().equals("NM")) {
            throw new Exception(contact.getEmail() + " is not a member of incyyte, please re-invite");
        }
        if (contact.getActive_ind().equals("D")) {
            throw new Exception(contact.getEmail() + " has been deleted from your contact list");
        }
        if (contact.getBlocked().equals("Y")) {
            throw new Exception("You have blocked contact " + contact.getEmail() + ", unblock before sending message");
        }
        if (contact.getAccept_inv().equals("N")) {
            throw new Exception(contact.getEmail() + " has not yet accepted your invitation");
        }
        if (!contact.getActive_ind().equals("A")) {
            throw new Exception(contact.getEmail() + " was not a active contact");
        }
    }

    @Override
    public void sendMessage(User user, String contactUserName, String messageText) throws Exception {

        Contact contact = messagesDao.memberExists(contactUserName, user.getId());
        Logger.debug("------contactExist-----" + contact);
        contact.setUsername(contactUserName);
        isContactValid(contact);

        Message message = new Message();
        message.setUserId(user.getId());
        long contactId = messagesDao.getContactId(user.getId(), contactUserName);
        message.setContactId(contactId);
        message.setType(Message.MessageType.NEW);
        message.setStatus(Message.MessageStatus.UNREAD);
        message.setSentBy(user.getEmail());
        message.setMessageText(messageText);
        Logger.debug("contactId::" + contact);
        Contact contactForReplyMail = messagesDao.getContactsContact(user.getId(), contactId, contactUserName);
        long messageId = messagesDao.sendMessage(user, message);
        Logger.debug("after messageId::" + messageId);
        Logger.debug("before exception::" + contactForReplyMail);
        if (messageId == 0) {
            throw new Exception("Internal error message can't be sent");
        }
        User details = getUserDetailByUsername(contactUserName);
       
        messageText = messageText + "<br><br>" +
                "<p class=\"unsublink\" align=\"center\">" +
                "<a href=\"" + websiteURL + incyyteURL + "?" + "actcode=" + details.getActivationCode() + "&messageId=" + messageId + "&contactId=" + contactForReplyMail.getId() + "&email=" + details.getEmail() + "\">" +
                "<img src=\"http://94e1f0b89e5310d14a74-4f0ee7521d78a5eb3e12120d36c3b41b.r87.cf3.rackcdn.com/reply_button.png\" width=\"182\" height=\"72\" style=\"border:none;\"/></a></p>";

        if (!contactForReplyMail.getBlocked().equals("Y")) {
            sendMessageEmail(user, details.getEmail(), messageText);
        }
    }

    @Override
    public List<String> searchContacts(Long id, String contactName) {
        return messagesDao.searchContacts(id, contactName);
    }

    @Override
    public List<Message> getDetailMessage(Long id, String usersEmail) {
        return messagesDao.getDetailMessage(id, usersEmail);
    }

    @Override
    public void deleteMessage(long msgId, String deletedBy) {
        messagesDao.deleteMessage(msgId, deletedBy);
    }

    @Override
    public void sendReply(User user, long contactId, String messageContent, long messageId) throws Exception {

        Contact contactsContact = messagesDao.getContactsContact(user.getId(), contactId, user.getEmail());
        Contact contact = messagesDao.getContact(contactId);
        isContactValid(contact);
        Message replyMessage = new Message();
        replyMessage.setComposeMessageId(messageId);
        replyMessage.setUserId(user.getId());
        replyMessage.setContactId(contactId);
        replyMessage.setType(Message.MessageType.REPLY);
        replyMessage.setStatus(Message.MessageStatus.UNREAD);
        replyMessage.setSentBy(user.getEmail());
        replyMessage.setMessageText(messageContent);
        long latestMessageId = messagesDao.sendMessage(user, replyMessage);
        if (latestMessageId == 0) {
            throw new Exception("Internal error message can't be sent");
        }
        
        User details = getUserDetailByEmail(contact.getEmail());
        
        messageContent = messageContent + "<br><br>" +
                "<p class=\"unsublink\" align=\"center\">" +
                "<a href=\"" + websiteURL + incyyteURL + "?" + "actcode=" + details.getActivationCode() + "&messageId=" + messageId + "&contactId=" + contactsContact.getId() + "&email=" + details.getEmail() + "\">" +
                "<img src=\"http://94e1f0b89e5310d14a74-4f0ee7521d78a5eb3e12120d36c3b41b.r87.cf3.rackcdn.com/reply_button.png\" width=\"182\" height=\"72\" style=\"border:none;\"/></a></p>";
        if (!contactsContact.getBlocked().equals("Y")) {
            sendMessageEmail(user, contact.getEmail(), messageContent);
        }

    }

    public int getMessagesCount(Long userId) {
        return messagesDao.getMessagesCount(userId);
    }

    @Override
    public void markAsRead(long messageId, String email) {
        messagesDao.markAsRead(messageId, email);
    }

    @Override
    public int getUnreadMessageCount(String email) {
        return messagesDao.getUnreadMessageCount(email);
    }

    @Override
    public String getSentBy(long msgId) {
        return messagesDao.getSentBy(msgId);
    }

    public void sendMessageEmail(User user, String contactName, String messageText) {
        emailMgr.setUser(user);
        emailMgr.setRunFlag(Constants.SEND_EMAIL);
        emailMgr.setContactName(contactName);
        emailMgr.setMessageText(messageText);
        new Thread(emailMgr).start();
    }

    private User getUserDetailByUsername(String contactUserName) {
        User user = registrationSrv.getUserDetailByUsername(contactUserName);
        Logger.debug("user name::::::" + user);
        return user;
    }
    
    private User getUserDetailByEmail(String email) {
    	User user = loginSrv.getUserDetailByEmailOrUsername(email, null);
        Logger.debug("user name::::::" + user);
        return user;
    }
    
}