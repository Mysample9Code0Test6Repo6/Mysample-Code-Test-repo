package com.incyyte.app.domain;

import java.util.Date;

public class Response implements java.io.Serializable {

    private Long answerId;
    private Long memberId;
    private Long userId;
    private Long sharedId;
    private Long questionId;

    private String gender;
    private String ageGroup;
    private Date responseDate;

    private Long postResponseId;
    private Long regionResponseId;

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Long getSharedId() {
        return sharedId;
    }

    public void setSharedId(Long sharedId) {
        this.sharedId = sharedId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getPostResponseId() {
        return postResponseId;
    }

    public void setPostResponseId(Long postResponseId) {
        this.postResponseId = postResponseId;
    }

    public Long getRegionResponseId() {
        return regionResponseId;
    }

    public void setRegionResponseId(Long regionResponseId) {
        this.regionResponseId = regionResponseId;
    }

    @Override
    public String toString() {
        return "Response{" +
                "answerId=" + answerId +
                ", memberId=" + memberId +
                ", userId=" + userId +
                ", sharedId=" + sharedId +
                ", questionId=" + questionId +
                ", gender='" + gender + '\'' +
                ", ageGroup='" + ageGroup + '\'' +
                ", responseDate=" + responseDate +
                ", postResponseId=" + postResponseId +
                ", regionResponseId=" + regionResponseId +
                '}';
    }
}