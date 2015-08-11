package com.incyyte.app.domain;

import java.util.Date;

public class GroupContact {

    private Long memberId;
    private Long contactId;
    private Long groupId;

    private Date creationDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    private String activeInd;
    private String role;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getActiveInd() {
        return activeInd;
    }

    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "GroupContact{" +
                "memberId=" + memberId +
                ", contactId=" + contactId +
                ", groupId=" + groupId +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", activeInd='" + activeInd + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}