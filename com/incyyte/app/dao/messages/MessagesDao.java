package com.incyyte.app.dao.messages;

import com.incyyte.app.domain.Message;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.web.model.UserContactModel;

import java.util.List;

public interface MessagesDao {

    List<Message> getMessages(Long userId, int offSet, int recordsPerPage);

    int deleteConversation(long id, String deletedBy);

    long sendMessage(User user, Message message) throws AccessException, Exception;

    List<String> searchContacts(Long id, String contactName);

    List getDetailMessage(Long id, String usersEmail);

    void deleteMessage(long msgId, String deletedBy);

    long getContactId(long id, String contactName);

    Contact memberExists(String email, long userid) throws Exception;

	int getMessagesCount(Long userId);
	
	public Contact getContact(long contactId);

	void markAsRead(long messageId,String email);

	int getUnreadMessageCount(String email);

	String getSentBy(long msgId);

	Contact getContactsContact(long userId, long contactId, String contactEmail) throws Exception;
}