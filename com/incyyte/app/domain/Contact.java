/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class represents a User's Contact details.
 * For example:
 * <pre>
 * 		
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long userId;
	private Set<Group> groups = new HashSet<Group>();
	
	private String nickname;	
	private String firstName;
	private String lastName;	
	private String email;
	private String password;
	private String mobile;	
	private String xmlString;
	private Long memberId;
	
	private Long contactId;
	private Long groupId;
	private String active_ind;
	private Date created_date;
	private Date modified_date;
	private String created_by;
	private String modified_by;
	private String sent_invite;
	private String note;
	private String status;
	private String invitationid;
	private String  accept_inv;
	private String blocked;
	
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Set getGroups() {
		return groups;
	}
	public void setGroups(Set groups) {
		this.groups = groups;
	}

	public String getXmlString() {
		return xmlString;
	}
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * @return the contactId
	 */
	public Long getContactId() {
		return contactId;
	}
	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	public String getBlocked() {
		return blocked;
	}
	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}
	
	public String getActive_ind() {
		return active_ind;
	}
	public void setActive_ind(String active_ind) {
		this.active_ind = active_ind;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getSent_invite() {
		return sent_invite;
	}
	public void setSent_invite(String sent_invite) {
		this.sent_invite = sent_invite;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInvitationid() {
		return invitationid;
	}
	public void setInvitationid(String invitationid) {
		this.invitationid = invitationid;
	}
	public String getAccept_inv() {
		return accept_inv;
	}
	public void setAccept_inv(String accept_inv) {
		this.accept_inv = accept_inv;
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", userId=" + userId + ", groups="
				+ groups + ", nickname=" + nickname + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobile=" + mobile + ", xmlString=" + xmlString
				+ ", memberId=" + memberId + ", contactId=" + contactId
				+ ", groupId=" + groupId + ", active_ind=" + active_ind
				+ ", created_date=" + created_date + ", modified_date="
				+ modified_date + ", created_by=" + created_by
				+ ", modified_by=" + modified_by + ", sent_invite="
				+ sent_invite + ", note=" + note + ", status=" + status
				+ ", invitationid=" + invitationid + ", accept_inv="
				+ accept_inv + ", blocked=" + blocked + ", username=" + username + "]";
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	

}
