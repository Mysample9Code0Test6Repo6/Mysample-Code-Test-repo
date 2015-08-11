package com.incyyte.app.domain;

import java.util.Date;

public class GroupSharedInCyyte {

    private Long fkMemberId;
    private Long fkQuestionId;

    private String createdBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;

    public Long getFkMemberId() {
        return fkMemberId;
    }

    public void setFkMemberId(Long fkMemberId) {
        this.fkMemberId = fkMemberId;
    }

    public Long getFkQuestionId() {
        return fkQuestionId;
    }

    public void setFkQuestionId(Long fkQuestionId) {
        this.fkQuestionId = fkQuestionId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "GroupSharedInCyyte{" +
                "fkMemberId=" + fkMemberId +
                ", fkQuestionId=" + fkQuestionId +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}