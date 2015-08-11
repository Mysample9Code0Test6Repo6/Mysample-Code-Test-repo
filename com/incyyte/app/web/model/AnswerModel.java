package com.incyyte.app.web.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class AnswerModel {

    private long ansId;

    private long quesId;

    private String answerChoice;

    private String answerOption;

    private String answerType;

    private String answerlink;

    private String answerFileName;

    private String answerUploadedUrl;

    private String answerUploadExt;

    private CommonsMultipartFile uploadedAnsFile;

    private String cdnFileName;
    
    public String getUploadedAnsFileYoutubeVideo() {
		return uploadedAnsFileYoutubeVideo;
	}

	public void setUploadedAnsFileYoutubeVideo(String uploadedAnsFileYoutubeVideo) {
		this.uploadedAnsFileYoutubeVideo = uploadedAnsFileYoutubeVideo;
	}

	private String uploadedAnsFileYoutubeVideo;

    /**
     * @return Returns the ansId.
     */
    public long getAnsId() {
        return ansId;
    }

    /**
     * @param ansId The ansId to set.
     */
    public void setAnsId(long ansId) {
        this.ansId = ansId;
    }

    /**
     * @return Returns the quesId.
     */
    public long getQuesId() {
        return quesId;
    }

    /**
     * @param quesId The quesId to set.
     */
    public void setQuesId(long quesId) {
        this.quesId = quesId;
    }

    /**
     * @return Returns the answerOption.
     */
    public String getAnswerOption() {
        return answerOption;
    }

    /**
     * @param answerOption The answerOption to set.
     */
    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getAnswerlink() {
        return answerlink;
    }

    public void setAnswerlink(String answerlink) {
        this.answerlink = answerlink;
    }

    public CommonsMultipartFile getUploadedAnsFile() {
        return uploadedAnsFile;
    }

    public void setUploadedAnsFile(CommonsMultipartFile uploadedAnsFile) {
        this.uploadedAnsFile = uploadedAnsFile;
    }

    public String getAnswerFileName() {
        return answerFileName;
    }

    public void setAnswerFileName(String answerFileName) {
        this.answerFileName = answerFileName;
    }

    public String getAnswerUploadedUrl() {
        return answerUploadedUrl;
    }

    public void setAnswerUploadedUrl(String answerUploadedUrl) {
        this.answerUploadedUrl = answerUploadedUrl;
    }

    public String getAnswerUploadExt() {
        return answerUploadExt;
    }

    public void setAnswerUploadExt(String answerUploadExt) {
        this.answerUploadExt = answerUploadExt;
    }

    public String getAnswerChoice() {
        return answerChoice;
    }

    public void setAnswerChoice(String answerChoice) {
        this.answerChoice = answerChoice;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (ansId ^ (ansId >>> 32));
        result = prime * result
                + ((answerChoice == null) ? 0 : answerChoice.hashCode());
        result = prime * result
                + ((answerFileName == null) ? 0 : answerFileName.hashCode());
        result = prime * result
                + ((cdnFileName == null) ? 0 : cdnFileName.hashCode());
        result = prime * result
                + ((answerOption == null) ? 0 : answerOption.hashCode());
        result = prime * result
                + ((answerType == null) ? 0 : answerType.hashCode());
        result = prime * result
                + ((answerUploadExt == null) ? 0 : answerUploadExt.hashCode());
        result = prime
                * result
                + ((answerUploadedUrl == null) ? 0 : answerUploadedUrl
                .hashCode());
        result = prime * result
                + ((answerlink == null) ? 0 : answerlink.hashCode());
        result = prime * result + (int) (quesId ^ (quesId >>> 32));
        result = prime * result
                + ((uploadedAnsFile == null) ? 0 : uploadedAnsFile.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AnswerModel other = (AnswerModel) obj;
        if (ansId != other.ansId)
            return false;
        if (answerChoice == null) {
            if (other.answerChoice != null)
                return false;
        } else if (!answerChoice.equals(other.answerChoice))
            return false;
        if (answerFileName == null) {
            if (other.answerFileName != null)
                return false;
        } else if (!answerFileName.equals(other.answerFileName))
            return false;
        if (answerOption == null) {
            if (other.answerOption != null)
                return false;
        } else if (!answerOption.equals(other.answerOption))
            return false;
        if (answerType == null) {
            if (other.answerType != null)
                return false;
        } else if (!answerType.equals(other.answerType))
            return false;
        if (answerUploadExt == null) {
            if (other.answerUploadExt != null)
                return false;
        } else if (!answerUploadExt.equals(other.answerUploadExt))
            return false;
        if (answerUploadedUrl == null) {
            if (other.answerUploadedUrl != null)
                return false;
        } else if (!answerUploadedUrl.equals(other.answerUploadedUrl))
            return false;
        if (answerlink == null) {
            if (other.answerlink != null)
                return false;
        } else if (!answerlink.equals(other.answerlink))
            return false;
        if (quesId != other.quesId)
            return false;
        if (uploadedAnsFile == null) {
            if (other.uploadedAnsFile != null)
                return false;
        } else if (!uploadedAnsFile.equals(other.uploadedAnsFile))
            return false;
        return true;
    }

    public String getCdnFileName() {
        return cdnFileName;
    }

    public void setCdnFileName(String cdnFileName) {
        this.cdnFileName = cdnFileName;
    }

    @Override
    public String toString() {
        return "AnswerModel{" +
                "ansId=" + ansId +
                ", quesId=" + quesId +
                ", answerChoice='" + answerChoice + '\'' +
                ", answerOption='" + answerOption + '\'' +
                ", answerType='" + answerType + '\'' +
                ", answerlink='" + answerlink + '\'' +
                ", answerFileName='" + answerFileName + '\'' +
                ", answerUploadedUrl='" + answerUploadedUrl + '\'' +
                ", answerUploadExt='" + answerUploadExt + '\'' +
                ", uploadedAnsFile=" + uploadedAnsFile +
                ", cdnFileName='" + cdnFileName + '\'' +
                '}';
    }
}