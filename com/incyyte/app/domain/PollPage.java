package com.incyyte.app.domain;

public class PollPage {

    private Long pageId;
    private Long userId;
    private String pageHeader;
    private String contactEmail;
    private String websiteUrl;
    private String contactPhone1;
    private String contactPhone2;
    private String information;

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPageHeader() {
        return pageHeader;
    }

    public void setPageHeader(String pageHeader) {
        this.pageHeader = pageHeader;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getContactPhone1() {
        return contactPhone1;
    }

    public void setContactPhone1(String contactPhone1) {
        this.contactPhone1 = contactPhone1;
    }

    public String getContactPhone2() {
        return contactPhone2;
    }

    public void setContactPhone2(String contactPhone2) {
        this.contactPhone2 = contactPhone2;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "PollPage [pageId=" + pageId + ", userId=" + userId
                + ", pageHeader=" + pageHeader + ", contactEmail="
                + contactEmail + ", websiteUrl=" + websiteUrl
                + ", contactPhone1=" + contactPhone1 + ", contactPhone2="
                + contactPhone2 + ", information=" + information + "]";
    }
}