package com.incyyte.app.service;

import com.incyyte.app.domain.Message;
import com.incyyte.app.domain.User;
import com.incyyte.app.manager.MessagesManager;
import com.incyyte.app.service.Exceptions.AccessException;

import java.util.List;

public class MessagesServiceImpl implements MessagesService {

    private MessagesManager messagesManager;

    public void setMessagesManager(MessagesManager messagesManager) {
        this.messagesManager = messagesManager;
    }

    @Override
    public List<Message> getMessages(Long userId,int offSet,int recordsPerPage) {
        return messagesManager.getMessages(userId,offSet,recordsPerPage);
    }

    @Override
    public int deleteConversation(long id, String deletedBy) {
        return messagesManager.deleteConversation(id, deletedBy);

    }

    @Override
    public void sendMessage(User user, String contactName, String messageText) throws Exception {
        messagesManager.sendMessage(user, contactName, messageText);
    }

    @Override
    public List<String> searchContacts(Long id, String contactName) {
        return messagesManager.searchContacts(id, contactName);
    }

    @Override
    public List<Message> getDetailMessage(Long id, String userEmail) {
        return messagesManager.getDetailMessage(id, userEmail);
    }

    @Override
    public void deleteMessage(long msgId, String deletedBy) {
        messagesManager.deleteMessage(msgId, deletedBy);
    }

    @Override
    public void sendReply(User user, long contactId, String messageContent, long messageId) throws AccessException, Exception {
        messagesManager.sendReply(user, contactId, messageContent, messageId);
    }

	@Override
	public int getMessagesCount(Long userId) {
	return messagesManager.getMessagesCount(userId);
	}

	@Override
	public void markAsRead(long messageId,String email) {
		messagesManager.markAsRead(messageId,email);
	}

	@Override
	public int getUnreadMessageCount(String email) {
		return messagesManager.getUnreadMessageCount(email);
	}

	@Override
	public String getSentBy(long msgId) {
		return messagesManager.getSentBy(msgId);
	}

}