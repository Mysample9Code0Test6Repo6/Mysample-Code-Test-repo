package com.incyyte.app.domain;

public class ReportPoll {

    private Long reportId;
    private Long userId;
    private Long questionId;
    private String reportType;
    private String comments;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ReportPoll{" +
                "reportId=" + reportId +
                ", userId=" + userId +
                ", questionId=" + questionId +
                ", reportType='" + reportType + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}