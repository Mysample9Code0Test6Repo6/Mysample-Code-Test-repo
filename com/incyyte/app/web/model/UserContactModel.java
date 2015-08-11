package com.incyyte.app.web.model;

import com.incyyte.app.service.util.Utility;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UserContactModel {

    private long contactid;
    private String nickname;
    private String firstname;
    private String lastname;
    private String email;
    private String mobile;
    private String postalcode;
    private String sent_invite;
    private String note;
    private String checked = null;
    private String status;
    private String invitationid;
    private String accept_inv;
    private String userid;
    private String active_ind;
    private String blocked;
    private String hidden;
    private String membersince;
    private String formattedMessageDate;
    private String sn_id;

    private String sn_site;
    private String keywords;
    private String profile_img;
    private String username;
    private String uniquename;
    private String pollHomePage;
    private Date createdDate;
    private String tmpPostcode;
    private String definingQuestion;
    private boolean receivedIncyyte;
    private String pollMessageContent;

    private String fileName;
    private String createdbyImageLink;
    private String uploadedType;
    private CommonsMultipartFile uploadedFile;
    private CommonsMultipartFile uploadedVideoFile;
    private CommonsMultipartFile uploadedPhotoFile;
    private CommonsMultipartFile uploadedDocFile;
    private String uploadedFileName;
    private String uploadedFileType;
    private String uploadedFileLocation;
    private String contentType;
    //temporary objects to store information for searched images load
    private String searchedFileName;
    private String searchedFileURL;
    private String incyyteCode;
    private String youTubeVideoIdPromotion;

    public String getYouTubeVideoIdPromotion() {
        return youTubeVideoIdPromotion;
    }

    public void setYouTubeVideoIdPromotion(String youTubeVideoIdPromotion) {
        this.youTubeVideoIdPromotion = youTubeVideoIdPromotion;
    }

    public CommonsMultipartFile getUploadedPhotoFile() {
        return uploadedPhotoFile;
    }

    public void setUploadedPhotoFile(CommonsMultipartFile uploadedPhotoFile) {
        this.uploadedPhotoFile = uploadedPhotoFile;
    }

    public String getTmpPostcode() {
        return tmpPostcode;
    }

    public void setTmpPostcode(String tmpPostcode) {
        this.tmpPostcode = tmpPostcode;
    }

    public String getDefiningQuestion() {
        return definingQuestion;
    }

    public void setDefiningQuestion(String definingQuestion) {
        this.definingQuestion = definingQuestion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        this.formattedMessageDate = Utility.getDate(createdDate);

    }

    public String getFormattedMessageDate() {
        return formattedMessageDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String country;
    private String city;
    private String text;
    private String gender;


    public long getContactid() {
        return contactid;
    }

    public void setContactid(long contactid) {
        this.contactid = contactid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getInvitationid() {
        return invitationid;
    }

    public void setInvitationid(String invitationid) {
        this.invitationid = invitationid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getActive_ind() {
        return active_ind;
    }

    public void setActive_ind(String active_ind) {
        this.active_ind = active_ind;
    }

    public String getAccept_inv() {
        return accept_inv;
    }

    public void setAccept_inv(String accept_inv) {
        this.accept_inv = accept_inv;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setMembersince(String membersince) {
        this.membersince = membersince;

    }

    public String getMembersince() {
        return membersince;
    }

    /**
     * @param sn_id the sn_id to set
     */
    public void setSn_id(String sn_id) {
        this.sn_id = sn_id;
    }

    /**
     * @return the sn_id
     */
    public String getSn_id() {
        return sn_id;
    }

    /**
     * @param sn_site the sn_site to set
     */
    public void setSn_site(String sn_site) {
        this.sn_site = sn_site;
    }

    /**
     * @return the sn_site
     */
    public String getSn_site() {
        return sn_site;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * @return the keywords
     */
    public String getKeywords() {
        return keywords;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public boolean isReceivedIncyyte() {
        return receivedIncyyte;
    }

    public void setReceivedIncyyte(boolean receivedIncyyte) {
        this.receivedIncyyte = receivedIncyyte;
    }

    public String getPollMessageContent() {
        return pollMessageContent;
    }

    public void setPollMessageContent(String pollMessageContent) {
        this.pollMessageContent = pollMessageContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CommonsMultipartFile getUploadedVideoFile() {
        return uploadedVideoFile;
    }

    public void setUploadedVideoFile(CommonsMultipartFile uploadedVideoFile) {
        this.uploadedVideoFile = uploadedVideoFile;
    }

    public CommonsMultipartFile getUploadedDocFile() {
        return uploadedDocFile;
    }

    public void setUploadedDocFile(CommonsMultipartFile uploadedDocFile) {
        this.uploadedDocFile = uploadedDocFile;
    }

    public String getSearchedFileName() {
        return searchedFileName;
    }

    public void setSearchedFileName(String searchedFileName) {
        this.searchedFileName = searchedFileName;
    }

    public String getSearchedFileURL() {
        return searchedFileURL;
    }

    public void setSearchedFileURL(String searchedFileURL) {
        this.searchedFileURL = searchedFileURL;
    }

    public String getUploadedType() {
        return uploadedType;
    }

    public void setUploadedType(String uploadedType) {
        this.uploadedType = uploadedType;
    }

    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public String getUploadedFileType() {
        return uploadedFileType;
    }

    public void setUploadedFileType(String uploadedFileType) {
        this.uploadedFileType = uploadedFileType;
    }

    public String getUploadedFileLocation() {
        return uploadedFileLocation;
    }

    public void setUploadedFileLocation(String uploadedFileLocation) {
        this.uploadedFileLocation = uploadedFileLocation;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCreatedbyImageLink() {
        return createdbyImageLink;
    }

    public void setCreatedbyImageLink(String createdbyImageLink) {
        this.createdbyImageLink = createdbyImageLink;
    }

    public CommonsMultipartFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(CommonsMultipartFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getIncyyteCode() {
        return incyyteCode;
    }

    public void setIncyyteCode(String incyyteCode) {
        this.incyyteCode = incyyteCode;
    }

    /**
     * @return the uniquename
     */
    public String getUniquename() {
        return uniquename;
    }

    /**
     * @param uniquename the uniquename to set
     */
    public void setUniquename(String uniquename) {
        this.uniquename = uniquename;
    }

    /**
     * @return the pollHomePage
     */
    public String getPollHomePage() {
        return pollHomePage;
    }

    /**
     * @param pollHomePage the pollHomePage to set
     */
    public void setPollHomePage(String pollHomePage) {
        this.pollHomePage = pollHomePage;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "UserContactModel{" +
                "contactid=" + contactid +
                ", nickname='" + nickname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", postalcode='" + postalcode + '\'' +
                ", sent_invite='" + sent_invite + '\'' +
                ", note='" + note + '\'' +
                ", checked='" + checked + '\'' +
                ", status='" + status + '\'' +
                ", invitationid='" + invitationid + '\'' +
                ", accept_inv='" + accept_inv + '\'' +
                ", userid='" + userid + '\'' +
                ", active_ind='" + active_ind + '\'' +
                ", blocked='" + blocked + '\'' +
                ", hidden='" + hidden + '\'' +
                ", membersince='" + membersince + '\'' +
                ", formattedMessageDate='" + formattedMessageDate + '\'' +
                ", sn_id='" + sn_id + '\'' +
                ", sn_site='" + sn_site + '\'' +
                ", keywords='" + keywords + '\'' +
                ", profile_img='" + profile_img + '\'' +
                ", username='" + username + '\'' +
                ", uniquename='" + uniquename + '\'' +
                ", pollHomePage='" + pollHomePage + '\'' +
                ", createdDate=" + createdDate +
                ", tmpPostcode='" + tmpPostcode + '\'' +
                ", definingQuestion='" + definingQuestion + '\'' +
                ", receivedIncyyte=" + receivedIncyyte +
                ", pollMessageContent='" + pollMessageContent + '\'' +
                ", fileName='" + fileName + '\'' +
                ", createdbyImageLink='" + createdbyImageLink + '\'' +
                ", uploadedType='" + uploadedType + '\'' +
                ", uploadedFile=" + uploadedFile +
                ", uploadedVideoFile=" + uploadedVideoFile +
                ", uploadedPhotoFile=" + uploadedPhotoFile +
                ", uploadedDocFile=" + uploadedDocFile +
                ", uploadedFileName='" + uploadedFileName + '\'' +
                ", uploadedFileType='" + uploadedFileType + '\'' +
                ", uploadedFileLocation='" + uploadedFileLocation + '\'' +
                ", contentType='" + contentType + '\'' +
                ", searchedFileName='" + searchedFileName + '\'' +
                ", searchedFileURL='" + searchedFileURL + '\'' +
                ", incyyteCode='" + incyyteCode + '\'' +
                ", youTubeVideoIdPromotion='" + youTubeVideoIdPromotion + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", text='" + text + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}