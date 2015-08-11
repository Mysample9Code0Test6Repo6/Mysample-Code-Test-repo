/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class represents a Cyyte question Posted.
 * For example:
 * <pre>
 * 		Which is better in the following list of cars?
 * </pre>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 01 Feb 2011
 */
package com.incyyte.app.domain;

import com.incyyte.app.service.util.Logger;

import java.io.Serializable;
import java.util.Arrays;
import java.util.*;


public class InCyyte implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String incyyte; // the question

    private String category;
    private boolean randomize;
    private boolean multiSelection;
    private byte[] upload; // this could be a file, image or video
    private String upload_name;  //This will have the exact name of the file loaded
    private String cdnFileName; // This will CDN Name of the uploaded to server
    private String upload_ext;
    private String content_type;
    private String upload_type;
    private String uploadLocation;
    private String extension;
    private String style;
    private String xmlString;
    private String incyyteCode;
    private String backgroundId;
    private String link;
    private List<Answer> answers;
    private List<Contact> contacts;
    private Long grpId;
    private Group group;
    private String closureDate;
    private Date incyyteClosedDate;
    private String createdDate;
    private String createdDatePeriod;
    private Date incyyteCreatedDate;
    private String incyyteCreatedDateFmt;
    private int totalInCyyted;
    private int totalResponded;
    private boolean anonymity;
    private String sendType;
    private String pageName;
    private String createdBy;
    private String quesType;
    private String public_poll;
    private String strapline;
    private String allowComment;
    private String pollResultHidden;
    private String protectPage;
    private String upload_logo_location;
    private String access_code;
    private String senderfname;
    private String senderlname;
    private String senderProfilePic;
    private String ageRange;
    private String gender;
    private String locality;
    private String region;
    private String county;
    private String postcode;
    private boolean deleted;
    private boolean published;
    private boolean shared;

    private String creationType;// this MUST be Set from Constants.INCYYTE_CREATION_TYPE

    private String companyName;
    private String companyLogoUrl;
    private String bannerUrl;
    private String companyInfoPara1;
    private String companyInfoPara2;

    private String country;
    private String sendMethod;
    private String sendZone;
    private String templateId;

    private int pollEmailCount;
    private String youtubeUrl;

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public int getPollEmailCount() {
        return pollEmailCount;
    }

    public void setPollEmailCount(int pollEmailCount) {
        this.pollEmailCount = pollEmailCount;
    }

    public String getIncyyteCreatedDateFmt() {
        return incyyteCreatedDateFmt;
    }

    public void setIncyyteCreatedDateFmt(String incyyteCreatedDateFmt) {
        this.incyyteCreatedDateFmt = incyyteCreatedDateFmt;
    }

    public String getCreatedDatePeriod() {
        return createdDatePeriod;
    }

    public void setCreatedDatePeriod(String createdDatePeriod) {
        this.createdDatePeriod = createdDatePeriod;
    }

    public String getCompanyInfoPara1() {
        return companyInfoPara1;
    }

    public void setCompanyInfoPara1(String companyInfoPara1) {
        this.companyInfoPara1 = companyInfoPara1;
    }

    public String getCompanyInfoPara2() {
        return companyInfoPara2;
    }

    public void setCompanyInfoPara2(String companyInfoPara2) {
        this.companyInfoPara2 = companyInfoPara2;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getCreationType() {
        return creationType;
    }

    public void setCreationType(String creationType) {
        this.creationType = creationType;
    }

    public int getTotalInCyyted() {
        return totalInCyyted;
    }

    public void setTotalInCyyted(int totalInCyyted) {
        this.totalInCyyted = totalInCyyted;
    }

    public int getTotalResponded() {
        return totalResponded;
    }

    public void setTotalResponded(int totalResponded) {
        this.totalResponded = totalResponded;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIncyyte() {
        return incyyte;
    }

    public void setIncyyte(String incyyte) {
        this.incyyte = incyyte;
    }

    public byte[] getUpload() {
        return upload;
    }

    public void setUpload(byte[] upload) {
        this.upload = upload;
    }

    public String getUpload_name() {
        return upload_name;
    }

    public void setUpload_name(String upload_name) {
        this.upload_name = upload_name;
    }

    public String getUpload_ext() {
        return upload_ext;
    }

    public void setUpload_ext(String upload_ext) {
        this.upload_ext = upload_ext;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isRandomize() {
        return randomize;
    }

    public void setRandomize(boolean randomize) {
        this.randomize = randomize;
    }

    public boolean isMultiSelection() {
        return multiSelection;
    }

    public void setMultiSelection(boolean multiSelection) {
        this.multiSelection = multiSelection;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    /**
     * @return Returns the style.
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style The style to set.
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return Returns the xmlString.
     */
    public String getXmlString() {
        return xmlString;
    }

    /**
     * @param xmlString The xmlString to set.
     */
    public void setXmlString(String xmlString) {
        this.xmlString = xmlString;
    }

    public String getIncyyteCode() {
        return incyyteCode;
    }

    public void setIncyyteCode(String incyyteCode) {
        this.incyyteCode = incyyteCode;
    }

    public String getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(String backgroundId) {
        this.backgroundId = backgroundId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(String closureDate) {
        this.closureDate = closureDate;
    }

    public String getUpload_type() {
        return upload_type;
    }

    public void setUpload_type(String upload_type) {
        this.upload_type = upload_type;
    }

    public String getUploadLocation() {
        return uploadLocation;
    }

    public void setUploadLocation(String uploadLocation) {
        this.uploadLocation = uploadLocation;
    }

    public boolean isAnonymity() {
        return anonymity;
    }

    public void setAnonymity(boolean anonymity) {
        this.anonymity = anonymity;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType;
    }

    public String getStrapline() {
        return strapline;
    }

    public void setStrapline(String strapline) {
        this.strapline = strapline;
    }

    public String getUpload_logo_location() {
        return upload_logo_location;
    }

    public void setUpload_logo_location(String upload_logo_location) {
        this.upload_logo_location = upload_logo_location;
    }

    public String getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(String allowComment) {
        this.allowComment = allowComment;
    }

    public String getPollResultHidden() {
        return pollResultHidden;
    }

    public void setPollResultHidden(String pollResultHidden) {
        this.pollResultHidden = pollResultHidden;
    }

    public String getProtectPage() {
        return protectPage;
    }

    public void setProtectPage(String protectPage) {
        this.protectPage = protectPage;
    }

    public String getPublic_poll() {
        return public_poll;
    }

    public void setPublic_poll(String public_poll) {
        this.public_poll = public_poll;
    }

    public String getAccess_code() {
        return access_code;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }

    public String getSenderfname() {
        return senderfname;
    }

    public void setSenderfname(String senderfname) {
        this.senderfname = senderfname;
    }

    public String getSenderlname() {
        return senderlname;
    }

    public void setSenderlname(String senderlname) {
        this.senderlname = senderlname;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Date getIncyyteClosedDate() {
        return incyyteClosedDate;
    }

    public void setIncyyteClosedDate(Date incyyteClosedDate) {
        this.incyyteClosedDate = incyyteClosedDate;
    }

    public Date getIncyyteCreatedDate() {
        return incyyteCreatedDate;
    }

    public void setIncyyteCreatedDate(Date incyyteCreatedDate) {
        this.incyyteCreatedDate = incyyteCreatedDate;
    }

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }


    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getSenderProfilePic() {
        return senderProfilePic;
    }

    public void setSenderProfilePic(String senderProfilePic) {
        this.senderProfilePic = senderProfilePic;
    }

    public String getCdnFileName() {
        return cdnFileName;
    }

    public void setCdnFileName(String cdnFileName) {
        this.cdnFileName = cdnFileName;
    }

    @Override
    public String toString() {
        return "InCyyte{" +
                "id=" + id +
                ", userId=" + userId +
                ", incyyte='" + incyyte + '\'' +
                ", category='" + category + '\'' +
                ", randomize=" + randomize +
                ", multiSelection=" + multiSelection +
                ", upload=" + upload +
                ", upload_name='" + upload_name + '\'' +
                ", cdnFileName='" + cdnFileName + '\'' +
                ", upload_ext='" + upload_ext + '\'' +
                ", content_type='" + content_type + '\'' +
                ", upload_type='" + upload_type + '\'' +
                ", uploadLocation='" + uploadLocation + '\'' +
                ", extension='" + extension + '\'' +
                ", style='" + style + '\'' +
                //", xmlString='" + xmlString + '\'' +
                ", incyyteCode='" + incyyteCode + '\'' +
                ", backgroundId='" + backgroundId + '\'' +
                ", link='" + link + '\'' +
                ", answers=" + answers +
                ", contacts=" + contacts +
                ", grpId=" + grpId +
                ", group=" + group +
                ", closureDate='" + closureDate + '\'' +
                ", incyyteClosedDate=" + incyyteClosedDate +
                ", createdDate='" + createdDate + '\'' +
                ", incyyteCreatedDate=" + incyyteCreatedDate +
                ", totalInCyyted=" + totalInCyyted +
                ", totalResponded=" + totalResponded +
                ", anonymity=" + anonymity +
                ", sendType='" + sendType + '\'' +
                ", pageName='" + pageName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", quesType='" + quesType + '\'' +
                ", public_poll='" + public_poll + '\'' +
                ", strapline='" + strapline + '\'' +
                ", allowComment='" + allowComment + '\'' +
                ", protectPage='" + protectPage + '\'' +
                ", upload_logo_location='" + upload_logo_location + '\'' +
                ", access_code='" + access_code + '\'' +
                ", senderfname='" + senderfname + '\'' +
                ", senderlname='" + senderlname + '\'' +
                ", senderProfilePic='" + senderProfilePic + '\'' +
                ", ageRange='" + ageRange + '\'' +
                ", gender='" + gender + '\'' +
                ", locality='" + locality + '\'' +
                ", region='" + region + '\'' +
                ", county='" + county + '\'' +
                ", postcode='" + postcode + '\'' +
                ", deleted=" + deleted +
                ", published=" + published +
                ", creationType='" + creationType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyLogoUrl='" + companyLogoUrl + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", companyInfoPara1='" + companyInfoPara1 + '\'' +
                ", companyInfoPara2='" + companyInfoPara2 + '\'' +
                ", youtubeUrl=" + youtubeUrl + '\'' +
                '}';
	}

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSendMethod() {
        return sendMethod;
    }

    public void setSendMethod(String sendMethod) {
        this.sendMethod = sendMethod;
    }

    public String getSendZone() {
        return sendZone;
    }

    public void setSendZone(String sendZone) {
        this.sendZone = sendZone;
    }

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

    public Map<String, Object> toJSONMap() {
        Map<String, Object> jsonValues = new HashMap<String, Object>();
        jsonValues.put("id", String.valueOf(id));
        jsonValues.put("incyyteCode", incyyteCode);
        jsonValues.put("senderProfilePic", senderProfilePic);
        jsonValues.put("createdBy", createdBy);
        jsonValues.put("createdDatePeriod", createdDatePeriod);
        jsonValues.put("closureDate", closureDate);
        jsonValues.put("uploadLocation", uploadLocation);
        jsonValues.put("upload_name", upload_name);
        if (group != null) {
            jsonValues.put("group", group.toJSONMap());
        } else {
            jsonValues.put("group", "");
        }
        if (answers != null) {
            jsonValues.put("answers", answersMapList());
        } else {
            jsonValues.put("answers", "");
        }
        return jsonValues;
    }

    private List<Map<String, Object>> answersMapList() {
        List<Map<String, Object>> answersMap = new ArrayList<Map<String, Object>>();
        for (Answer answer : answers) {
            Map<String, Object> answerMap = new HashMap<String, Object>();
            answerMap.put("answer", answer.toJSONMap());
            answersMap.add(answerMap);
        }
        return answersMap;
    }
}