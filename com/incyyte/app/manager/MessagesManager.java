package com.incyyte.app.manager;

import com.incyyte.app.domain.Message;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.AccessException;

import java.util.List;

public interface MessagesManager {

    List<Message> getMessages(Long userId, int offSet, int recordsPerPage);

    int deleteConversation(long id, String deletedBy);

    void sendMessage(User user, String contactName, String messageText) throws Exception;

    List<String> searchContacts(Long id, String contactName);

    List<Message> getDetailMessage(Long id, String usersEmail);

    void deleteMessage(long msgId, String deletedBy);

    void sendReply(User user, long contactId, String messageContent, long messageId) throws AccessException, Exception;
    
    public int getMessagesCount(Long userId);
    
	void markAsRead(long messageId, String email);

	int getUnreadMessageCount(String email);

	String getSentBy(long msgId);

	

}