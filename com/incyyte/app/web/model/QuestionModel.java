package com.incyyte.app.web.model;

public class QuestionModel {

    private String questionid;
    private String fk_userid;
    private String question;
    private String category;
    private String upload;
    private String upload_type;
    private String upload_name;
    private String randomize;
    private String multi_selection;
    private String upload_ext;
    private String content_type;
    private String xmlString;
    private String background;
    private String viewchart_code;
    private String emailResponses;
    private String link;
    private String sendType;
    private String pageName;
    /**
     * @param questionid the questionid to set
     */
    /**
     * @return the questionid
     */
    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getFk_userid() {
        return fk_userid;
    }

    public void setFk_userid(String fk_userid) {
        this.fk_userid = fk_userid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getUpload_type() {
        return upload_type;
    }

    public void setUpload_type(String upload_type) {
        this.upload_type = upload_type;
    }

    public String getUpload_name() {
        return upload_name;
    }

    public void setUpload_name(String upload_name) {
        this.upload_name = upload_name;
    }

    public String getRandomize() {
        return randomize;
    }

    public void setRandomize(String randomize) {
        this.randomize = randomize;
    }

    public String getMulti_selection() {
        return multi_selection;
    }

    public void setMulti_selection(String multi_selection) {
        this.multi_selection = multi_selection;
    }

    public String getUpload_ext() {
        return upload_ext;
    }

    public void setUpload_ext(String upload_ext) {
        this.upload_ext = upload_ext;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getXmlString() {
        return xmlString;
    }

    public void setXmlString(String xmlString) {
        this.xmlString = xmlString;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getViewchart_code() {
        return viewchart_code;
    }

    public void setViewchart_code(String viewchart_code) {
        this.viewchart_code = viewchart_code;
    }

    public String getEmailResponses() {
        return emailResponses;
    }

    public void setEmailResponses(String emailResponses) {
        this.emailResponses = emailResponses;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	@Override
	public String toString() {
		return "QuestionModel [questionid=" + questionid + ", fk_userid="
				+ fk_userid + ", question=" + question + ", category="
				+ category + ", upload=" + upload + ", upload_type="
				+ upload_type + ", upload_name=" + upload_name + ", randomize="
				+ randomize + ", multi_selection=" + multi_selection
				+ ", upload_ext=" + upload_ext + ", content_type="
				+ content_type + ", xmlString=" + xmlString + ", background="
				+ background + ", viewchart_code=" + viewchart_code
				+ ", emailResponses=" + emailResponses + ", link=" + link
				+ ", sendType=" + sendType + ", pageName=" + pageName + "]";
	}

}
