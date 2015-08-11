package com.incyyte.app.domain;

import com.incyyte.app.service.util.Utility;

import java.util.Date;

public class Message {
    private long id;
    private long composeMessageId;
    private long userId;
    private long contactId;
    private Date messageDate;
    private String formattedMessageDate;
    private MessageType type;
    private MessageStatus status;
    private String sentBy;
    private String messageText;
    private String userName;
    private String contactName;
    private String photoUrl;

    public enum MessageType {NEW, REPLY}

    public enum MessageStatus {READ, UNREAD, ARCHIVED, DELETED}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getComposeMessageId() {
        return composeMessageId;
    }

    public void setComposeMessageId(long composeMessageId) {
        this.composeMessageId = composeMessageId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
        this.formattedMessageDate = Utility.getDate(messageDate);
    }

    public String getFormattedMessageDate() {
        return formattedMessageDate;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public String getPhotoUrl() {
 		return photoUrl;
 	}

 	public void setPhotoUrl(String photoUrl) {
 		this.photoUrl = photoUrl;
 	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", composeMessageId=" + composeMessageId
				+ ", userId=" + userId + ", contactId=" + contactId
				+ ", messageDate=" + messageDate + ", formattedMessageDate="
				+ formattedMessageDate + ", type=" + type + ", status="
				+ status + ", sentBy=" + sentBy + ", messageText="
				+ messageText + ", userName=" + userName + ", contactName="
				+ contactName + ", photoUrl=" + photoUrl + "]";
	}

}
