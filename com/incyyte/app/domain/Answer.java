/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class represents an Answer option to an InCyyte
 * For example:
 * <pre>
 *
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.domain;

import com.incyyte.app.service.util.Logger;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String answerOption;
    private Long incyyteId;
    private String uploadType;
    private String uploadName;
    private String cdnFileName;
    private String uploadExt;
    private String uploadCDN_url;
    private String urlLink;
    private boolean photoIncluded;

    private String maleCount;
    private String femaleCount;
    private String unspecifiedCount;
    private List<User> maleRecord;
    private List<User> femaleRecord;
    private List<User> unSpecifiedRecord;

    private String youtubeURLAnswer;


    public String getYoutubeURLAnswer() {
        return youtubeURLAnswer;
    }

    public void setYoutubeURLAnswer(String youtubeURLAnswer) {
        this.youtubeURLAnswer = youtubeURLAnswer;
    }

    public boolean isPhotoIncluded() {
        return StringUtils.isNotBlank(getUploadCDN_url());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }


    public Long getIncyyteId() {
        return incyyteId;
    }

    public void setIncyyteId(Long incyyteId) {
        this.incyyteId = incyyteId;
    }


    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getUploadExt() {
        return uploadExt;
    }

    public void setUploadExt(String uploadExt) {
        this.uploadExt = uploadExt;
    }

    public String getUploadCDN_url() {
        return uploadCDN_url;
    }

    public void setUploadCDN_url(String uploadCDN_url) {
        this.uploadCDN_url = uploadCDN_url;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getCdnFileName() {
        return cdnFileName;
    }

    public void setCdnFileName(String cdnFileName) {
        this.cdnFileName = cdnFileName;
    }

    public String getMaleCount() {
        return StringUtils.defaultString(maleCount, "0");
    }

    public void setMaleCount(String maleCount) {
        this.maleCount = maleCount;
    }

    public String getFemaleCount() {
        return StringUtils.defaultString(femaleCount, "0");
    }

    public void setFemaleCount(String femaleCount) {
        this.femaleCount = femaleCount;
    }

    public String getUnspecifiedCount() {
        return StringUtils.defaultString(unspecifiedCount, "0");
    }

    public void setUnspecifiedCount(String unspecifiedCount) {
        this.unspecifiedCount = unspecifiedCount;
    }

    public List<User> getMaleRecord() {
        return maleRecord;
    }

    public void setMaleRecord(List<User> maleRecord) {
        this.maleRecord = maleRecord;
    }

    public List<User> getFemaleRecord() {
        return femaleRecord;
    }

    public void setFemaleRecord(List<User> femaleRecord) {
        this.femaleRecord = femaleRecord;
    }

    public List<User> getUnSpecifiedRecord() {
        return unSpecifiedRecord;
    }

    public void setUnSpecifiedRecord(List<User> unSpecifiedRecord) {
        this.unSpecifiedRecord = unSpecifiedRecord;
    }

    @Override
    public String toString() {
        return "Answer [id=" + id + ", answerOption=" + answerOption
                + ", incyyteId=" + incyyteId + ", uploadType=" + uploadType
                + ", uploadName=" + uploadName + ", cdnFileName=" + cdnFileName
                + ", uploadExt=" + uploadExt + ", uploadCDN_url="
                + uploadCDN_url + ", urlLink=" + urlLink + ", photoIncluded="
                + photoIncluded + ", maleCount=" + maleCount + ", femaleCount="
                + femaleCount + ", unspecifiedCount=" + unspecifiedCount
                + ", maleRecord=" + maleRecord + ", femaleRecord="
                + femaleRecord + ", unSpecifiedRecord=" + unSpecifiedRecord
                + ", youtubeURLAnswer=" + youtubeURLAnswer + "]";
    }

    public Map<String, Object> toJSONMap() {
        Map<String, Object> jsonValues = new HashMap<String, Object>();
        jsonValues.put("id", id);
        jsonValues.put("answerOption", answerOption);
        jsonValues.put("uploadExt", uploadExt);
        jsonValues.put("uploadCDN_url", uploadCDN_url);
        return jsonValues;
    }
}
