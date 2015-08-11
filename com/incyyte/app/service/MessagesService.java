package com.incyyte.app.service;

import com.incyyte.app.domain.Message;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.AccessException;

import java.util.List;

public interface MessagesService {

    public List<Message> getMessages(Long userId, int offSet, int recordsPerPage);

    public int deleteConversation(long id, String deletedBy);

    public void sendMessage(User user, String contactName, String messageText) throws Exception;

    public List<String> searchContacts(Long id, String contactName);

    public List<Message> getDetailMessage(Long id, String userEmail);

    public void deleteMessage(long msgId, String deletedBy);

    public void sendReply(User user, long contactId, String messageContent, long messageId) throws AccessException, Exception;
    
    public int getMessagesCount(Long userId);
    
	void markAsRead(long messageId,String email);

	int getUnreadMessageCount(String email);

	public String getSentBy(long msgId);

}
