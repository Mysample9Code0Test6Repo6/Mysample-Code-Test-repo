package com.incyyte.app.web.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CommentsModel {

    private String commentid;
    private String userprofileimg;
    private String quesid;
    private String commentdate;
    private String comment;
    private String commentby;
    private String active;
    private String commentPeriod;
    private String commentPicture;
    private String uploadCommentType;
    private String uploadCommentLocation;
    private String uploadCommentCdnFileName;
    private CommonsMultipartFile uploadCommentPhotoFile;

    private String searchedFileNameComment;
    private String searchedFileURLComment;
    private String youtubeCommentVideoId;
    private String youtubeCommentVideoURL;
    
    public String getYoutubeCommentVideoURL() {
		return youtubeCommentVideoURL;
	}

	public void setYoutubeCommentVideoURL(String youtubeCommentVideoURL) {
		this.youtubeCommentVideoURL = youtubeCommentVideoURL;
	}

	public String getYoutubeCommentVideoId() {
		return youtubeCommentVideoId;
	}

	public void setYoutubeCommentVideoId(String youtubeCommentVideoId) {
		this.youtubeCommentVideoId = youtubeCommentVideoId;
	}

	public String getQuesid() {
        return quesid;
    }

    public void setQuesid(String quesid) {
        this.quesid = quesid;
    }

    public String getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate;
    }

    public String getCommentby() {
        return commentby;
    }

    public void setCommentby(String commentby) {
        this.commentby = commentby;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getComment() {
        return comment;
    }

    /**
     * @return the commentid
     */
    public String getCommentid() {
        return commentid;
    }

    /**
     * @param commentid the commentid to set
     */
    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUserprofileimg(String userprofileimg) {
        this.userprofileimg = userprofileimg;
    }

    public String getUserprofileimg() {
        return userprofileimg;
    }

    public String getCommentPeriod() {
        return commentPeriod;
    }

    public void setCommentPeriod(String commentPeriod) {
        this.commentPeriod = commentPeriod;
    }

    public String getCommentPicture() {
        return commentPicture;
    }

    public void setCommentPicture(String commentPicture) {
        this.commentPicture = commentPicture;
    }

    public String getUploadCommentType() {
        return uploadCommentType;
    }

    public void setUploadCommentType(String uploadCommentType) {
        this.uploadCommentType = uploadCommentType;
    }

    public String getUploadCommentLocation() {
        return uploadCommentLocation;
    }

    public void setUploadCommentLocation(String uploadCommentLocation) {
        this.uploadCommentLocation = uploadCommentLocation;
    }

    public String getUploadCommentCdnFileName() {
        return uploadCommentCdnFileName;
    }

    public void setUploadCommentCdnFileName(String uploadCommentCdnFileName) {
        this.uploadCommentCdnFileName = uploadCommentCdnFileName;
    }

    public CommonsMultipartFile getUploadCommentPhotoFile() {
        return uploadCommentPhotoFile;
    }

    public void setUploadCommentPhotoFile(CommonsMultipartFile uploadCommentPhotoFile) {
        this.uploadCommentPhotoFile = uploadCommentPhotoFile;
    }

    public String getSearchedFileNameComment() {
        return searchedFileNameComment;
    }

    public void setSearchedFileNameComment(String searchedFileNameComment) {
        this.searchedFileNameComment = searchedFileNameComment;
    }

    public String getSearchedFileURLComment() {
        return searchedFileURLComment;
    }

    public void setSearchedFileURLComment(String searchedFileURLComment) {
        this.searchedFileURLComment = searchedFileURLComment;
    }

    @Override
	public String toString() {
		return "CommentsModel [commentid=" + commentid + ", userprofileimg="
				+ userprofileimg + ", quesid=" + quesid + ", commentdate="
				+ commentdate + ", comment=" + comment + ", commentby="
				+ commentby + ", active=" + active + ", commentPeriod="
				+ commentPeriod + ", commentPicture=" + commentPicture
				+ ", uploadCommentType=" + uploadCommentType
				+ ", uploadCommentLocation=" + uploadCommentLocation
				+ ", uploadCommentCdnFileName=" + uploadCommentCdnFileName
				+ ", uploadCommentPhotoFile=" + uploadCommentPhotoFile
				+ ", searchedFileNameComment=" + searchedFileNameComment
				+ ", searchedFileURLComment=" + searchedFileURLComment
				+ ", youtubeCommentVideoId=" + youtubeCommentVideoId
				+ ", youtubeCommentVideoURL=" + youtubeCommentVideoURL + "]";
	}
}