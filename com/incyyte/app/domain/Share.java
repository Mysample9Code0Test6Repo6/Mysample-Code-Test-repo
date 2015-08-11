package com.incyyte.app.domain;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class Share {
	
	  
	  private Long questionId;
	  private Long groupId;
	  private Long ownerUserId;
	  private Long sharerUserId;
	  private Long contactId;
	  private Date createdDate;
	  private String createdBy;
	  private List<Long> selectedShareContactsList;
	  private Hashtable shareContacts;
	  
	  private long id;
	  private String fmtId;
	  
	  
	public Hashtable getShareContacts() {
		return shareContacts;
	}
	public void setShareContacts(Hashtable shareContacts) {
		this.shareContacts = shareContacts;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFmtId() {
		return fmtId;
	}
	public void setFmtId(String fmtId) {
		this.fmtId = fmtId;
	}
	
	
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(Long ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	public Long getSharerUserId() {
		return sharerUserId;
	}
	public void setSharerUserId(Long sharerUserId) {
		this.sharerUserId = sharerUserId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public List<Long> getSelectedShareContactsList() {
		return selectedShareContactsList;
	}
	public void setSelectedShareContactsList(List<Long> selectedShareContactsList) {
		this.selectedShareContactsList = selectedShareContactsList;
	}
	@Override
	public String toString() {
		return "Share [questionId=" + questionId + ", groupId=" + groupId
				+ ", ownerUserId=" + ownerUserId + ", sharerUserId="
				+ sharerUserId + ", contactId=" + contactId + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy
				+ ", selectedShareContactsList=" + selectedShareContactsList
				+ ", shareContacts=" + shareContacts + ", id=" + id
				+ ", fmtId=" + fmtId + "]";
	}
	  

}
