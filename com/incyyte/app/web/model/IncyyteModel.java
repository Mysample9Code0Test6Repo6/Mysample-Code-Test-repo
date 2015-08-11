package com.incyyte.app.web.model;

import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

public class IncyyteModel {

	//Question variables
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

	private Long id;
	private String incyyte;
	private String incyyteCode;
	private String category;
	private boolean randomize;
	private boolean multiSelection;
	private String style;
	private String answerArr;
	private String eLink;
	private String grpId;
	private String grpName;
	private String grpType;
	private String emailArr;
	private String closureDate;
	private String closureTime;
	private boolean addFile;
	private boolean addLink;
	private boolean pollResultHidden = false;
	private boolean anonymity = false;
	private String sendType;
	private String pageName;
	private String createdBy;
	private String quesType;
	private String public_poll = "N";
	private String sendToGroup = "N";
	private String senderfname;
	private String senderlname;
	private CommonsMultipartFile uploadedLogo;
	private String uploadedLogoLocation;
	private String strapline;
	private boolean allowComment = true;
	private String protectPage = "N";
	private String pollRef;
	private String createdDate;
	private String ageRange;
	private String ageMin;
	private String ageMax;
	private String gender;
	private String region;
	private String county;
	private String postcode;
	private String locality;
	private String country;
	private Long pollRecipients;
	private int pollCharge;
	private String templateId;
	
	private String youTubeQuestionVideoId;
	private String youTubeAnswer_1_VideoId;
	private String youTubeAnswer_2_VideoId;
	private String youTubeAnswer_3_VideoId;
	private String youTubeAnswer_4_VideoId;
	private String youTubeAnswer_5_VideoId;
	private String youTubeAnswer_6_VideoId;
	private String youTubeAnswer_7_VideoId;
	private String youTubeAnswer_8_VideoId;
	private String youTubeAnswer_9_VideoId;
	private String youTubeAnswer_10_VideoId;
	
	public String getYouTubeQuestionVideoId() {
		return youTubeQuestionVideoId;
	}

	public void setYouTubeQuestionVideoId(String youTubeQuestionVideoId) {
		this.youTubeQuestionVideoId = youTubeQuestionVideoId;
	}

	public String getYouTubeAnswer_1_VideoId() {
		return youTubeAnswer_1_VideoId;
	}

	public void setYouTubeAnswer_1_VideoId(String youTubeAnswer_1_VideoId) {
		this.youTubeAnswer_1_VideoId = youTubeAnswer_1_VideoId;
	}

	public String getYouTubeAnswer_2_VideoId() {
		return youTubeAnswer_2_VideoId;
	}

	public void setYouTubeAnswer_2_VideoId(String youTubeAnswer_2_VideoId) {
		this.youTubeAnswer_2_VideoId = youTubeAnswer_2_VideoId;
	}

	public String getYouTubeAnswer_3_VideoId() {
		return youTubeAnswer_3_VideoId;
	}

	public void setYouTubeAnswer_3_VideoId(String youTubeAnswer_3_VideoId) {
		this.youTubeAnswer_3_VideoId = youTubeAnswer_3_VideoId;
	}

	public String getYouTubeAnswer_4_VideoId() {
		return youTubeAnswer_4_VideoId;
	}

	public void setYouTubeAnswer_4_VideoId(String youTubeAnswer_4_VideoId) {
		this.youTubeAnswer_4_VideoId = youTubeAnswer_4_VideoId;
	}

	public String getYouTubeAnswer_5_VideoId() {
		return youTubeAnswer_5_VideoId;
	}

	public void setYouTubeAnswer_5_VideoId(String youTubeAnswer_5_VideoId) {
		this.youTubeAnswer_5_VideoId = youTubeAnswer_5_VideoId;
	}

	public String getYouTubeAnswer_6_VideoId() {
		return youTubeAnswer_6_VideoId;
	}

	public void setYouTubeAnswer_6_VideoId(String youTubeAnswer_6_VideoId) {
		this.youTubeAnswer_6_VideoId = youTubeAnswer_6_VideoId;
	}

	public String getYouTubeAnswer_7_VideoId() {
		return youTubeAnswer_7_VideoId;
	}

	public void setYouTubeAnswer_7_VideoId(String youTubeAnswer_7_VideoId) {
		this.youTubeAnswer_7_VideoId = youTubeAnswer_7_VideoId;
	}

	public String getYouTubeAnswer_8_VideoId() {
		return youTubeAnswer_8_VideoId;
	}

	public void setYouTubeAnswer_8_VideoId(String youTubeAnswer_8_VideoId) {
		this.youTubeAnswer_8_VideoId = youTubeAnswer_8_VideoId;
	}

	public String getYouTubeAnswer_9_VideoId() {
		return youTubeAnswer_9_VideoId;
	}

	public void setYouTubeAnswer_9_VideoId(String youTubeAnswer_9_VideoId) {
		this.youTubeAnswer_9_VideoId = youTubeAnswer_9_VideoId;
	}

	public String getYouTubeAnswer_10_VideoId() {
		return youTubeAnswer_10_VideoId;
	}

	public void setYouTubeAnswer_10_VideoId(String youTubeAnswer_10_VideoId) {
		this.youTubeAnswer_10_VideoId = youTubeAnswer_10_VideoId;
	}

	public int getPollCharge() {
		return pollCharge;
	}

	public void setPollCharge(int pollCharge) {
		this.pollCharge = pollCharge;
	}

    private Long pollCost;

	//Generic for Answers
	private List<AnswerModel> answers = new ArrayList<AnswerModel>();
	private int answer_count;
	private String answer_upload_opt;
	private String answer_upload_type;
	private String answerFileName;
	private String answerFileURL;
	private CommonsMultipartFile ans_uploaded_File;

	private String answerChoice1;
	private String answer_opt_1;
	private String answer_link_1;
	private String answerType1;
	private String answerFileName1;
	private String answerUploadedType1;
	private String answerUploadedFileName1;
	private String answer_uploaded_url_1;
	private CommonsMultipartFile answer_file_1;
	private CommonsMultipartFile answer_file_video_1;
	private CommonsMultipartFile answer_file_photo_1;
	private CommonsMultipartFile answer_file_doc_1;

	private String answerChoice2;
	private String answer_opt_2;
	private String answer_link_2;
	private String answerType2;
	private String answerFileName2;
	private String answerUploadedType2;
	private String answerUploadedFileName2;
	private String answer_uploaded_url_2;
	private CommonsMultipartFile answer_file_2;
	private CommonsMultipartFile answer_file_video_2;
	private CommonsMultipartFile answer_file_photo_2;
	private CommonsMultipartFile answer_file_doc_2;

	private String answerChoice3;
	private String answer_opt_3;
	private String answer_link_3;
	private String answerType3;
	private String answerFileName3;
	private String answerUploadedType3;
	private String answerUploadedFileName3;
	private String answer_uploaded_url_3;
	private CommonsMultipartFile answer_file_3;
	private CommonsMultipartFile answer_file_video_3;
	private CommonsMultipartFile answer_file_photo_3;
	private CommonsMultipartFile answer_file_doc_3;

	private String answerChoice4;
	private String answer_opt_4;
	private String answer_link_4;
	private String answerType4;
	private String answerFileName4;
	private String answerUploadedType4;
	private String answerUploadedFileName4;
	private String answer_uploaded_url_4;
	private CommonsMultipartFile answer_file_4;
	private CommonsMultipartFile answer_file_video_4;
	private CommonsMultipartFile answer_file_photo_4;
	private CommonsMultipartFile answer_file_doc_4;

	private String answerChoice5;
	private String answer_opt_5;
	private String answer_link_5;
	private String answerType5;
	private String answerFileName5;
	private String answerUploadedType5;
	private String answerUploadedFileName5;
	private String answer_uploaded_url_5;
	private CommonsMultipartFile answer_file_5;
	private CommonsMultipartFile answer_file_video_5;
	private CommonsMultipartFile answer_file_photo_5;
	private CommonsMultipartFile answer_file_doc_5;

	private String answerChoice6;
	private String answer_opt_6;
	private String answer_link_6;
	private String answerType6;
	private String answerFileName6;
	private String answerUploadedType6;
	private String answerUploadedFileName6;
	private String answer_uploaded_url_6;
	private CommonsMultipartFile answer_file_6;
	private CommonsMultipartFile answer_file_video_6;
	private CommonsMultipartFile answer_file_photo_6;
	private CommonsMultipartFile answer_file_doc_6;

	private String answerChoice7;
	private String answer_opt_7;
	private String answer_link_7;
	private String answerType7;
	private String answerFileName7;
	private String answerUploadedType7;
	private String answerUploadedFileName7;
	private String answer_uploaded_url_7;
	private CommonsMultipartFile answer_file_7;
	private CommonsMultipartFile answer_file_video_7;
	private CommonsMultipartFile answer_file_photo_7;
	private CommonsMultipartFile answer_file_doc_7;

	private String answerChoice8;
	private String answer_opt_8;
	private String answer_link_8;
	private String answerType8;
	private String answerFileName8;
	private String answerUploadedType8;
	private String answerUploadedFileName8;
	private String answer_uploaded_url_8;
	private CommonsMultipartFile answer_file_8;
	private CommonsMultipartFile answer_file_video_8;
	private CommonsMultipartFile answer_file_photo_8;
	private CommonsMultipartFile answer_file_doc_8;

	private String answerChoice9;
	private String answer_opt_9;
	private String answer_link_9;
	private String answerType9;
	private String answerFileName9;
	private String answerUploadedType9;
	private String answerUploadedFileName9;
	private String answer_uploaded_url_9;
	private CommonsMultipartFile answer_file_9;
	private CommonsMultipartFile answer_file_video_9;
	private CommonsMultipartFile answer_file_photo_9;
	private CommonsMultipartFile answer_file_doc_9;

	private String answerChoice10;
	private String answer_opt_10;
	private String answer_link_10;
	private String answerType10;
	private String answerFileName10;
	private String answerUploadedType10;
	private String answerUploadedFileName10;
	private String answer_uploaded_url_10;
	private CommonsMultipartFile answer_file_10;
	private CommonsMultipartFile answer_file_video_10;
	private CommonsMultipartFile answer_file_photo_10;
	private CommonsMultipartFile answer_file_doc_10;

	//temporary objects to store information for searched images load
	private String searchedFileName;
	private String searchedFileURL;

	public List<AnswerModel> getAnswerList() {
		Iterator<AnswerModel> itr = getAnswers().iterator();
		List<AnswerModel> answers = new ArrayList<AnswerModel>();
		while (itr.hasNext()) {
			answers.add(itr.next());
		}
		return answers;
	}

	public String getCreatedbyImageLink() {
		return createdbyImageLink;
	}

	public void setCreatedbyImageLink(String createdbyImageLink) {
		this.createdbyImageLink = createdbyImageLink;
	}

	public CommonsMultipartFile getAns_uploaded_File() {
		return ans_uploaded_File;
	}

	public void setAns_uploaded_File(CommonsMultipartFile ans_uploaded_File) {
		this.ans_uploaded_File = ans_uploaded_File;
	}

	public CommonsMultipartFile getAnswer_file_video_1() {
		return answer_file_video_1;
	}

	public void setAnswer_file_video_1(CommonsMultipartFile answer_file_video_1) {
		this.answer_file_video_1 = answer_file_video_1;
	}

	public CommonsMultipartFile getAnswer_file_photo_1() {
		return answer_file_photo_1;
	}

	public void setAnswer_file_photo_1(CommonsMultipartFile answer_file_photo_1) {
		this.answer_file_photo_1 = answer_file_photo_1;
	}

	public CommonsMultipartFile getAnswer_file_doc_1() {
		return answer_file_doc_1;
	}

	public void setAnswer_file_doc_1(CommonsMultipartFile answer_file_doc_1) {
		this.answer_file_doc_1 = answer_file_doc_1;
	}


	public CommonsMultipartFile getAnswer_file_video_2() {
		return answer_file_video_2;
	}

	public void setAnswer_file_video_2(CommonsMultipartFile answer_file_video_2) {
		this.answer_file_video_2 = answer_file_video_2;
	}

	public CommonsMultipartFile getAnswer_file_photo_2() {
		return answer_file_photo_2;
	}

	public void setAnswer_file_photo_2(CommonsMultipartFile answer_file_photo_2) {
		this.answer_file_photo_2 = answer_file_photo_2;
	}

	public CommonsMultipartFile getAnswer_file_doc_2() {
		return answer_file_doc_2;
	}

	public void setAnswer_file_doc_2(CommonsMultipartFile answer_file_doc_2) {
		this.answer_file_doc_2 = answer_file_doc_2;
	}

	public CommonsMultipartFile getAnswer_file_video_3() {
		return answer_file_video_3;
	}

	public void setAnswer_file_video_3(CommonsMultipartFile answer_file_video_3) {
		this.answer_file_video_3 = answer_file_video_3;
	}

	public CommonsMultipartFile getAnswer_file_photo_3() {
		return answer_file_photo_3;
	}

	public void setAnswer_file_photo_3(CommonsMultipartFile answer_file_photo_3) {
		this.answer_file_photo_3 = answer_file_photo_3;
	}

	public CommonsMultipartFile getAnswer_file_doc_3() {
		return answer_file_doc_3;
	}

	public void setAnswer_file_doc_3(CommonsMultipartFile answer_file_doc_3) {
		this.answer_file_doc_3 = answer_file_doc_3;
	}

	public CommonsMultipartFile getAnswer_file_video_4() {
		return answer_file_video_4;
	}

	public void setAnswer_file_video_4(CommonsMultipartFile answer_file_video_4) {
		this.answer_file_video_4 = answer_file_video_4;
	}

	public CommonsMultipartFile getAnswer_file_photo_4() {
		return answer_file_photo_4;
	}

	public void setAnswer_file_photo_4(CommonsMultipartFile answer_file_photo_4) {
		this.answer_file_photo_4 = answer_file_photo_4;
	}

	public CommonsMultipartFile getAnswer_file_doc_4() {
		return answer_file_doc_4;
	}

	public void setAnswer_file_doc_4(CommonsMultipartFile answer_file_doc_4) {
		this.answer_file_doc_4 = answer_file_doc_4;
	}

	public CommonsMultipartFile getAnswer_file_video_5() {
		return answer_file_video_5;
	}

	public void setAnswer_file_video_5(CommonsMultipartFile answer_file_video_5) {
		this.answer_file_video_5 = answer_file_video_5;
	}

	public CommonsMultipartFile getAnswer_file_photo_5() {
		return answer_file_photo_5;
	}

	public void setAnswer_file_photo_5(CommonsMultipartFile answer_file_photo_5) {
		this.answer_file_photo_5 = answer_file_photo_5;
	}

	public CommonsMultipartFile getAnswer_file_doc_5() {
		return answer_file_doc_5;
	}

	public void setAnswer_file_doc_5(CommonsMultipartFile answer_file_doc_5) {
		this.answer_file_doc_5 = answer_file_doc_5;
	}

	public CommonsMultipartFile getAnswer_file_video_6() {
		return answer_file_video_6;
	}

	public void setAnswer_file_video_6(CommonsMultipartFile answer_file_video_6) {
		this.answer_file_video_6 = answer_file_video_6;
	}

	public CommonsMultipartFile getAnswer_file_photo_6() {
		return answer_file_photo_6;
	}

	public void setAnswer_file_photo_6(CommonsMultipartFile answer_file_photo_6) {
		this.answer_file_photo_6 = answer_file_photo_6;
	}

	public CommonsMultipartFile getAnswer_file_doc_6() {
		return answer_file_doc_6;
	}

	public void setAnswer_file_doc_6(CommonsMultipartFile answer_file_doc_6) {
		this.answer_file_doc_6 = answer_file_doc_6;
	}

	public CommonsMultipartFile getAnswer_file_video_7() {
		return answer_file_video_7;
	}

	public void setAnswer_file_video_7(CommonsMultipartFile answer_file_video_7) {
		this.answer_file_video_7 = answer_file_video_7;
	}

	public CommonsMultipartFile getAnswer_file_photo_7() {
		return answer_file_photo_7;
	}

	public void setAnswer_file_photo_7(CommonsMultipartFile answer_file_photo_7) {
		this.answer_file_photo_7 = answer_file_photo_7;
	}

	public CommonsMultipartFile getAnswer_file_doc_7() {
		return answer_file_doc_7;
	}

	public void setAnswer_file_doc_7(CommonsMultipartFile answer_file_doc_7) {
		this.answer_file_doc_7 = answer_file_doc_7;
	}

	public boolean isAnonymity() {
		return anonymity;
	}

	public void setAnonymity(boolean anonymity) {
		this.anonymity = anonymity;
	}

	
	public boolean isPollResultHidden() {
		return pollResultHidden;
	}

	public void setPollResultHidden(boolean pollResultHidden) {
		this.pollResultHidden = pollResultHidden;
	}

	/**
	 * @return Returns the id.
	 */
	 public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	 public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the incyyte.
	 */
	 public String getIncyyte() {
		return incyyte;
	}

	/**
	 * @param incyyte The incyyte to set.
	 */
	 public void setIncyyte(String incyyte) {
		this.incyyte = incyyte;
	}

	/**
	 * @return Returns the category.
	 */
	 public String getCategory() {
		return category;
	}

	/**
	 * @param category The category to set.
	 */
	 public void setCategory(String category) {
		 this.category = category;
	 }

	 /**
	  * @return Returns the randomize.
	  */
	 public boolean isRandomize() {
		 return randomize;
	 }

	 /**
	  * @param randomize The randomize to set.
	  */
	 public void setRandomize(boolean randomize) {
		 this.randomize = randomize;
	 }

	 /**
	  * @return Returns the multiSelection.
	  */
	 public boolean isMultiSelection() {
		 return multiSelection;
	 }

	 /**
	  * @param multiSelection The multiSelection to set.
	  */
	 public void setMultiSelection(boolean multiSelection) {
		 this.multiSelection = multiSelection;
	 }

	 /**
	  * @return Returns the answers.
	  */
	 public List<AnswerModel> getAnswers() {
		 return answers;
	 }

	 /**
	  * @param answers The answers to set.
	  */
	 public void setAnswers(List<AnswerModel> answers) {
		 this.answers = answers;
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

	 public String getAnswerArr() {
		 return answerArr;
	 }

	 public void setAnswerArr(String answerArr) {
		 this.answerArr = answerArr;
	 }

	 public CommonsMultipartFile getUploadedFile() {
		 return uploadedFile;
	 }

	 public void setUploadedFile(CommonsMultipartFile uploadedFile) {
		 this.uploadedFile = uploadedFile;
	 }

	 public String geteLink() {
		 return eLink;
	 }

	 public void seteLink(String eLink) {
		 if (eLink != null && !eLink.equalsIgnoreCase("http://...")) {
			 this.eLink = eLink;
		 }
	 }

	 public String getEmailArr() {
		 return emailArr;
	 }

	 public void setEmailArr(String emailArr) {
		 this.emailArr = emailArr;
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

	 public String getFileName() {
		 return fileName;
	 }

	 public void setFileName(String fileName) {
		 this.fileName = fileName;
	 }

	 public String getClosureDate() {
		 if (closureDate == null) {
			 SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(new Date());
			 cal.add(Calendar.DATE, Constants.CLOSURE_DAYS);

			 closureDate = ft.format(cal.getTime());
		 }
		 return closureDate;
	 }

	 public void setClosureDate(String closureDate) {
		 this.closureDate = closureDate;
	 }

	 public String getContentType() {
		 return contentType;
	 }

	 public void setContentType(String contentType) {
		 this.contentType = contentType;
	 }

	 public boolean isAddFile() {
		 return addFile;
	 }

	 public void setAddFile(boolean addFile) {
		 this.addFile = addFile;
	 }

	 public boolean isAddLink() {
		 return addLink;
	 }

	 public void setAddLink(boolean addLink) {
		 this.addLink = addLink;
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

	 public String getAnswerChoice1() {
		 return answerChoice1;
	 }

	 public void setAnswerChoice1(String answerChoice1) {
		 this.answerChoice1 = answerChoice1;
	 }

	 public String getAnswerChoice2() {
		 return answerChoice2;
	 }

	 public void setAnswerChoice2(String answerChoice2) {
		 this.answerChoice2 = answerChoice2;
	 }

	 public String getAnswerChoice3() {
		 return answerChoice3;
	 }

	 public void setAnswerChoice3(String answerChoice3) {
		 this.answerChoice3 = answerChoice3;
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

	 public CommonsMultipartFile getUploadedLogo() {
		 return uploadedLogo;
	 }

	 public void setUploadedLogo(CommonsMultipartFile uploadedLogo) {
		 this.uploadedLogo = uploadedLogo;
	 }

	 public String getStrapline() {
		 return strapline;
	 }

	 public void setStrapline(String strapline) {
		 this.strapline = strapline;
	 }

	 public boolean isAllowComment() {
		 return allowComment;
	 }

	 public void setAllowComment(boolean allowComment) {
		 this.allowComment = allowComment;
	 }

	 public String getUploadedLogoLocation() {
		 return uploadedLogoLocation;
	 }

	 public void setUploadedLogoLocation(String uploadedLogoLocation) {
		 this.uploadedLogoLocation = uploadedLogoLocation;
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

	 public String getPollRef() {
		 return pollRef;
	 }

	 public void setPollRef(String pollRef) {
		 this.pollRef = pollRef;
	 }

	 public String getCreatedDate() {
		 return createdDate;
	 }

	 public void setCreatedDate(String createdDate) {
		 this.createdDate = createdDate;
	 }

	 public CommonsMultipartFile getUploadedVideoFile() {
		 return uploadedVideoFile;
	 }

	 public void setUploadedVideoFile(CommonsMultipartFile uploadedVideoFile) {
		 this.uploadedVideoFile = uploadedVideoFile;
	 }

	 public CommonsMultipartFile getUploadedPhotoFile() {
		 return uploadedPhotoFile;
	 }

	 public void setUploadedPhotoFile(CommonsMultipartFile uploadedPhotoFile) {
		 this.uploadedPhotoFile = uploadedPhotoFile;
	 }

	 public CommonsMultipartFile getUploadedDocFile() {
		 return uploadedDocFile;
	 }

	 public void setUploadedDocFile(CommonsMultipartFile uploadedDocFile) {
		 this.uploadedDocFile = uploadedDocFile;
	 }

	 public String getSendToGroup() {
		 return sendToGroup;
	 }

	 public void setSendToGroup(String sendToGroup) {
		 this.sendToGroup = sendToGroup;
	 }

	 public String getUploadedType() {
		 return uploadedType;
	 }

	 public void setUploadedType(String uploadedType) {
		 this.uploadedType = uploadedType;
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

	 public int getAnswer_count() {
		 return answer_count;
	 }

	 public void setAnswer_count(int answer_count) {
		 this.answer_count = answer_count;
	 }

	 public String getAnswer_opt_1() {
		 return answer_opt_1;
	 }

	 public void setAnswer_opt_1(String answer_opt_1) {
		 this.answer_opt_1 = answer_opt_1;
	 }

	 public String getAnswer_link_1() {
		 return answer_link_1;
	 }

	 public void setAnswer_link_1(String answer_link_1) {
		 this.answer_link_1 = answer_link_1;
	 }

	 public String getAnswerType1() {
		 return answerType1;
	 }

	 public void setAnswerType1(String answerType1) {
		 this.answerType1 = answerType1;
	 }

	 public String getAnswer_opt_2() {
		 return answer_opt_2;
	 }

	 public void setAnswer_opt_2(String answer_opt_2) {
		 this.answer_opt_2 = answer_opt_2;
	 }

	 public String getAnswer_link_2() {
		 return answer_link_2;
	 }

	 public void setAnswer_link_2(String answer_link_2) {
		 this.answer_link_2 = answer_link_2;
	 }

	 public String getAnswerType2() {
		 return answerType2;
	 }

	 public void setAnswerType2(String answerType2) {
		 this.answerType2 = answerType2;
	 }

	 public String getAnswer_opt_3() {
		 return answer_opt_3;
	 }

	 public void setAnswer_opt_3(String answer_opt_3) {
		 this.answer_opt_3 = answer_opt_3;
	 }

	 public String getAnswer_link_3() {
		 return answer_link_3;
	 }

	 public void setAnswer_link_3(String answer_link_3) {
		 this.answer_link_3 = answer_link_3;
	 }

	 public String getAnswerType3() {
		 return answerType3;
	 }

	 public void setAnswerType3(String answerType3) {
		 this.answerType3 = answerType3;
	 }

	 public String getAnswer_opt_4() {
		 return answer_opt_4;
	 }

	 public void setAnswer_opt_4(String answer_opt_4) {
		 this.answer_opt_4 = answer_opt_4;
	 }

	 public String getAnswer_link_4() {
		 return answer_link_4;
	 }

	 public void setAnswer_link_4(String answer_link_4) {
		 this.answer_link_4 = answer_link_4;
	 }

	 public String getAnswerType4() {
		 return answerType4;
	 }

	 public void setAnswerType4(String answerType4) {
		 this.answerType4 = answerType4;
	 }

	 public String getAnswer_opt_5() {
		 return answer_opt_5;
	 }

	 public void setAnswer_opt_5(String answer_opt_5) {
		 this.answer_opt_5 = answer_opt_5;
	 }

	 public String getAnswer_link_5() {
		 return answer_link_5;
	 }

	 public void setAnswer_link_5(String answer_link_5) {
		 this.answer_link_5 = answer_link_5;
	 }

	 public String getAnswerType5() {
		 return answerType5;
	 }

	 public void setAnswerType5(String answerType5) {
		 this.answerType5 = answerType5;
	 }

	 public String getAnswer_opt_6() {
		 return answer_opt_6;
	 }

	 public void setAnswer_opt_6(String answer_opt_6) {
		 this.answer_opt_6 = answer_opt_6;
	 }

	 public String getAnswer_link_6() {
		 return answer_link_6;
	 }

	 public void setAnswer_link_6(String answer_link_6) {
		 this.answer_link_6 = answer_link_6;
	 }

	 public String getAnswerType6() {
		 return answerType6;
	 }

	 public void setAnswerType6(String answerType6) {
		 this.answerType6 = answerType6;
	 }

	 public String getAnswer_opt_7() {
		 return answer_opt_7;
	 }

	 public void setAnswer_opt_7(String answer_opt_7) {
		 this.answer_opt_7 = answer_opt_7;
	 }

	 public String getAnswer_link_7() {
		 return answer_link_7;
	 }

	 public void setAnswer_link_7(String answer_link_7) {
		 this.answer_link_7 = answer_link_7;
	 }

	 public String getAnswerType7() {
		 return answerType7;
	 }

	 public void setAnswerType7(String answerType7) {
		 this.answerType7 = answerType7;
	 }


	 public CommonsMultipartFile getAnswer_file_1() {
		 return answer_file_1;
	 }

	 public void setAnswer_file_1(CommonsMultipartFile answer_file_1) {
		 this.answer_file_1 = answer_file_1;
	 }

	 public CommonsMultipartFile getAnswer_file_2() {
		 return answer_file_2;
	 }

	 public void setAnswer_file_2(CommonsMultipartFile answer_file_2) {
		 this.answer_file_2 = answer_file_2;
	 }

	 public CommonsMultipartFile getAnswer_file_3() {
		 return answer_file_3;
	 }

	 public void setAnswer_file_3(CommonsMultipartFile answer_file_3) {
		 this.answer_file_3 = answer_file_3;
	 }

	 public CommonsMultipartFile getAnswer_file_4() {
		 return answer_file_4;
	 }

	 public void setAnswer_file_4(CommonsMultipartFile answer_file_4) {
		 this.answer_file_4 = answer_file_4;
	 }

	 public CommonsMultipartFile getAnswer_file_5() {
		 return answer_file_5;
	 }

	 public void setAnswer_file_5(CommonsMultipartFile answer_file_5) {
		 this.answer_file_5 = answer_file_5;
	 }

	 public CommonsMultipartFile getAnswer_file_6() {
		 return answer_file_6;
	 }

	 public void setAnswer_file_6(CommonsMultipartFile answer_file_6) {
		 this.answer_file_6 = answer_file_6;
	 }

	 public CommonsMultipartFile getAnswer_file_7() {
		 return answer_file_7;
	 }

	 public void setAnswer_file_7(CommonsMultipartFile answer_file_7) {
		 this.answer_file_7 = answer_file_7;
	 }

	 public String getAnswer_upload_opt() {
		 return answer_upload_opt;
	 }

	 public void setAnswer_upload_opt(String answer_upload_opt) {
		 this.answer_upload_opt = answer_upload_opt;
	 }

	 public String getAnswerFileName1() {
		 return answerFileName1;
	 }

	 public void setAnswerFileName1(String answerFileName1) {
		 this.answerFileName1 = answerFileName1;
	 }

	 public String getAnswer_uploaded_url_1() {
		 return answer_uploaded_url_1;
	 }

	 public void setAnswer_uploaded_url_1(String answer_uploaded_url_1) {
		 this.answer_uploaded_url_1 = answer_uploaded_url_1;
	 }

	 public String getAnswerFileName2() {
		 return answerFileName2;
	 }

	 public void setAnswerFileName2(String answerFileName2) {
		 this.answerFileName2 = answerFileName2;
	 }

	 public String getAnswer_uploaded_url_2() {
		 return answer_uploaded_url_2;
	 }

	 public void setAnswer_uploaded_url_2(String answer_uploaded_url_2) {
		 this.answer_uploaded_url_2 = answer_uploaded_url_2;
	 }

	 public String getAnswerFileName3() {
		 return answerFileName3;
	 }

	 public void setAnswerFileName3(String answerFileName3) {
		 this.answerFileName3 = answerFileName3;
	 }

	 public String getAnswer_uploaded_url_3() {
		 return answer_uploaded_url_3;
	 }

	 public void setAnswer_uploaded_url_3(String answer_uploaded_url_3) {
		 this.answer_uploaded_url_3 = answer_uploaded_url_3;
	 }

	 public String getAnswerFileName4() {
		 return answerFileName4;
	 }

	 public void setAnswerFileName4(String answerFileName4) {
		 this.answerFileName4 = answerFileName4;
	 }

	 public String getAnswer_uploaded_url_4() {
		 return answer_uploaded_url_4;
	 }

	 public void setAnswer_uploaded_url_4(String answer_uploaded_url_4) {
		 this.answer_uploaded_url_4 = answer_uploaded_url_4;
	 }

	 public String getAnswerFileName5() {
		 return answerFileName5;
	 }

	 public void setAnswerFileName5(String answerFileName5) {
		 this.answerFileName5 = answerFileName5;
	 }

	 public String getAnswer_uploaded_url_5() {
		 return answer_uploaded_url_5;
	 }

	 public void setAnswer_uploaded_url_5(String answer_uploaded_url_5) {
		 this.answer_uploaded_url_5 = answer_uploaded_url_5;
	 }

	 public String getAnswerFileName6() {
		 return answerFileName6;
	 }

	 public void setAnswerFileName6(String answerFileName6) {
		 this.answerFileName6 = answerFileName6;
	 }

	 public String getAnswer_uploaded_url_6() {
		 return answer_uploaded_url_6;
	 }

	 public void setAnswer_uploaded_url_6(String answer_uploaded_url_6) {
		 this.answer_uploaded_url_6 = answer_uploaded_url_6;
	 }

	 public String getAnswerFileName7() {
		 return answerFileName7;
	 }

	 public void setAnswerFileName7(String answerFileName7) {
		 this.answerFileName7 = answerFileName7;
	 }

	 public String getAnswer_uploaded_url_7() {
		 return answer_uploaded_url_7;
	 }

	 public void setAnswer_uploaded_url_7(String answer_uploaded_url_7) {
		 this.answer_uploaded_url_7 = answer_uploaded_url_7;
	 }

	 public String getAnswer_upload_type() {
		 return answer_upload_type;
	 }

	 public void setAnswer_upload_type(String answer_upload_type) {
		 this.answer_upload_type = answer_upload_type;
	 }

	 public String getAnswerFileName() {
		 return answerFileName;
	 }

	 public void setAnswerFileName(String answerFileName) {
		 this.answerFileName = answerFileName;
	 }

	 public String getAnswerUploadedType1() {
		 return answerUploadedType1;
	 }

	 public void setAnswerUploadedType1(String answerUploadedType1) {
		 this.answerUploadedType1 = answerUploadedType1;
	 }

	 public String getAnswerUploadedFileName1() {
		 return answerUploadedFileName1;
	 }

	 public void setAnswerUploadedFileName1(String answerUploadedFileName1) {
		 this.answerUploadedFileName1 = answerUploadedFileName1;
	 }

	 public String getAnswerFileURL() {
		 return answerFileURL;
	 }

	 public void setAnswerFileURL(String answerFileURL) {
		 this.answerFileURL = answerFileURL;
	 }

	 public String getAnswerUploadedType2() {
		 return answerUploadedType2;
	 }

	 public void setAnswerUploadedType2(String answerUploadedType2) {
		 this.answerUploadedType2 = answerUploadedType2;
	 }

	 public String getAnswerUploadedFileName2() {
		 return answerUploadedFileName2;
	 }

	 public void setAnswerUploadedFileName2(String answerUploadedFileName2) {
		 this.answerUploadedFileName2 = answerUploadedFileName2;
	 }

	 public String getAnswerUploadedType3() {
		 return answerUploadedType3;
	 }

	 public void setAnswerUploadedType3(String answerUploadedType3) {
		 this.answerUploadedType3 = answerUploadedType3;
	 }

	 public String getAnswerUploadedFileName3() {
		 return answerUploadedFileName3;
	 }

	 public void setAnswerUploadedFileName3(String answerUploadedFileName3) {
		 this.answerUploadedFileName3 = answerUploadedFileName3;
	 }

	 public String getAnswerUploadedType4() {
		 return answerUploadedType4;
	 }

	 public void setAnswerUploadedType4(String answerUploadedType4) {
		 this.answerUploadedType4 = answerUploadedType4;
	 }

	 public String getAnswerUploadedFileName4() {
		 return answerUploadedFileName4;
	 }

	 public void setAnswerUploadedFileName4(String answerUploadedFileName4) {
		 this.answerUploadedFileName4 = answerUploadedFileName4;
	 }

	 public String getAnswerUploadedType5() {
		 return answerUploadedType5;
	 }

	 public void setAnswerUploadedType5(String answerUploadedType5) {
		 this.answerUploadedType5 = answerUploadedType5;
	 }

	 public String getAnswerUploadedFileName5() {
		 return answerUploadedFileName5;
	 }

	 public void setAnswerUploadedFileName5(String answerUploadedFileName5) {
		 this.answerUploadedFileName5 = answerUploadedFileName5;
	 }

	 public String getAnswerUploadedType6() {
		 return answerUploadedType6;
	 }

	 public void setAnswerUploadedType6(String answerUploadedType6) {
		 this.answerUploadedType6 = answerUploadedType6;
	 }

	 public String getAnswerUploadedFileName6() {
		 return answerUploadedFileName6;
	 }

	 public void setAnswerUploadedFileName6(String answerUploadedFileName6) {
		 this.answerUploadedFileName6 = answerUploadedFileName6;
	 }

	 public String getAnswerUploadedType7() {
		 return answerUploadedType7;
	 }

	 public void setAnswerUploadedType7(String answerUploadedType7) {
		 this.answerUploadedType7 = answerUploadedType7;
	 }

	 public String getAnswerUploadedFileName7() {
		 return answerUploadedFileName7;
	 }

	 public void setAnswerUploadedFileName7(String answerUploadedFileName7) {
		 this.answerUploadedFileName7 = answerUploadedFileName7;
	 }

	 public String getAnswerChoice4() {
		 return answerChoice4;
	 }

	 public void setAnswerChoice4(String answerChoice4) {
		 this.answerChoice4 = answerChoice4;
	 }

	 public String getAnswerChoice5() {
		 return answerChoice5;
	 }

	 public void setAnswerChoice5(String answerChoice5) {
		 this.answerChoice5 = answerChoice5;
	 }

	 public String getAnswerChoice6() {
		 return answerChoice6;
	 }

	 public void setAnswerChoice6(String answerChoice6) {
		 this.answerChoice6 = answerChoice6;
	 }

	 public String getAnswerChoice7() {
		 return answerChoice7;
	 }

	 public void setAnswerChoice7(String answerChoice7) {
		 this.answerChoice7 = answerChoice7;
	 }

	 public String getAnswerChoice8() {
		 return answerChoice8;
	 }

	 public void setAnswerChoice8(String answerChoice8) {
		 this.answerChoice8 = answerChoice8;
	 }

	 public String getAnswer_opt_8() {
		 return answer_opt_8;
	 }

	 public void setAnswer_opt_8(String answer_opt_8) {
		 this.answer_opt_8 = answer_opt_8;
	 }

	 public String getAnswer_link_8() {
		 return answer_link_8;
	 }

	 public void setAnswer_link_8(String answer_link_8) {
		 this.answer_link_8 = answer_link_8;
	 }

	 public String getAnswerType8() {
		 return answerType8;
	 }

	 public void setAnswerType8(String answerType8) {
		 this.answerType8 = answerType8;
	 }

	 public String getAnswerFileName8() {
		 return answerFileName8;
	 }

	 public void setAnswerFileName8(String answerFileName8) {
		 this.answerFileName8 = answerFileName8;
	 }

	 public String getAnswerUploadedType8() {
		 return answerUploadedType8;
	 }

	 public void setAnswerUploadedType8(String answerUploadedType8) {
		 this.answerUploadedType8 = answerUploadedType8;
	 }

	 public String getAnswerUploadedFileName8() {
		 return answerUploadedFileName8;
	 }

	 public void setAnswerUploadedFileName8(String answerUploadedFileName8) {
		 this.answerUploadedFileName8 = answerUploadedFileName8;
	 }

	 public String getAnswer_uploaded_url_8() {
		 return answer_uploaded_url_8;
	 }

	 public void setAnswer_uploaded_url_8(String answer_uploaded_url_8) {
		 this.answer_uploaded_url_8 = answer_uploaded_url_8;
	 }

	 public CommonsMultipartFile getAnswer_file_8() {
		 return answer_file_8;
	 }

	 public void setAnswer_file_8(CommonsMultipartFile answer_file_8) {
		 this.answer_file_8 = answer_file_8;
	 }

	 public CommonsMultipartFile getAnswer_file_video_8() {
		 return answer_file_video_8;
	 }

	 public void setAnswer_file_video_8(CommonsMultipartFile answer_file_video_8) {
		 this.answer_file_video_8 = answer_file_video_8;
	 }

	 public CommonsMultipartFile getAnswer_file_photo_8() {
		 return answer_file_photo_8;
	 }

	 public void setAnswer_file_photo_8(CommonsMultipartFile answer_file_photo_8) {
		 this.answer_file_photo_8 = answer_file_photo_8;
	 }

	 public CommonsMultipartFile getAnswer_file_doc_8() {
		 return answer_file_doc_8;
	 }

	 public void setAnswer_file_doc_8(CommonsMultipartFile answer_file_doc_8) {
		 this.answer_file_doc_8 = answer_file_doc_8;
	 }

	 public String getAnswerChoice9() {
		 return answerChoice9;
	 }

	 public void setAnswerChoice9(String answerChoice9) {
		 this.answerChoice9 = answerChoice9;
	 }

	 public String getAnswer_opt_9() {
		 return answer_opt_9;
	 }

	 public void setAnswer_opt_9(String answer_opt_9) {
		 this.answer_opt_9 = answer_opt_9;
	 }

	 public String getAnswer_link_9() {
		 return answer_link_9;
	 }

	 public void setAnswer_link_9(String answer_link_9) {
		 this.answer_link_9 = answer_link_9;
	 }

	 public String getAnswerType9() {
		 return answerType9;
	 }

	 public void setAnswerType9(String answerType9) {
		 this.answerType9 = answerType9;
	 }

	 public String getAnswerFileName9() {
		 return answerFileName9;
	 }

	 public void setAnswerFileName9(String answerFileName9) {
		 this.answerFileName9 = answerFileName9;
	 }

	 public String getAnswerUploadedType9() {
		 return answerUploadedType9;
	 }

	 public void setAnswerUploadedType9(String answerUploadedType9) {
		 this.answerUploadedType9 = answerUploadedType9;
	 }

	 public String getAnswerUploadedFileName9() {
		 return answerUploadedFileName9;
	 }

	 public void setAnswerUploadedFileName9(String answerUploadedFileName9) {
		 this.answerUploadedFileName9 = answerUploadedFileName9;
	 }

	 public String getAnswer_uploaded_url_9() {
		 return answer_uploaded_url_9;
	 }

	 public void setAnswer_uploaded_url_9(String answer_uploaded_url_9) {
		 this.answer_uploaded_url_9 = answer_uploaded_url_9;
	 }

	 public CommonsMultipartFile getAnswer_file_9() {
		 return answer_file_9;
	 }

	 public void setAnswer_file_9(CommonsMultipartFile answer_file_9) {
		 this.answer_file_9 = answer_file_9;
	 }

	 public CommonsMultipartFile getAnswer_file_video_9() {
		 return answer_file_video_9;
	 }

	 public void setAnswer_file_video_9(CommonsMultipartFile answer_file_video_9) {
		 this.answer_file_video_9 = answer_file_video_9;
	 }

	 public CommonsMultipartFile getAnswer_file_photo_9() {
		 return answer_file_photo_9;
	 }

	 public void setAnswer_file_photo_9(CommonsMultipartFile answer_file_photo_9) {
		 this.answer_file_photo_9 = answer_file_photo_9;
	 }

	 public CommonsMultipartFile getAnswer_file_doc_9() {
		 return answer_file_doc_9;
	 }

	 public void setAnswer_file_doc_9(CommonsMultipartFile answer_file_doc_9) {
		 this.answer_file_doc_9 = answer_file_doc_9;
	 }

	 public String getAnswerChoice10() {
		 return answerChoice10;
	 }

	 public void setAnswerChoice10(String answerChoice10) {
		 this.answerChoice10 = answerChoice10;
	 }

	 public String getAnswer_opt_10() {
		 return answer_opt_10;
	 }

	 public void setAnswer_opt_10(String answer_opt_10) {
		 this.answer_opt_10 = answer_opt_10;
	 }

	 public String getAnswer_link_10() {
		 return answer_link_10;
	 }

	 public void setAnswer_link_10(String answer_link_10) {
		 this.answer_link_10 = answer_link_10;
	 }

	 public String getAnswerType10() {
		 return answerType10;
	 }

	 public void setAnswerType10(String answerType10) {
		 this.answerType10 = answerType10;
	 }

	 public String getAnswerFileName10() {
		 return answerFileName10;
	 }

	 public void setAnswerFileName10(String answerFileName10) {
		 this.answerFileName10 = answerFileName10;
	 }

	 public String getAnswerUploadedType10() {
		 return answerUploadedType10;
	 }

	 public void setAnswerUploadedType10(String answerUploadedType10) {
		 this.answerUploadedType10 = answerUploadedType10;
	 }

	 public String getAnswerUploadedFileName10() {
		 return answerUploadedFileName10;
	 }

	 public void setAnswerUploadedFileName10(String answerUploadedFileName10) {
		 this.answerUploadedFileName10 = answerUploadedFileName10;
	 }

	 public String getAnswer_uploaded_url_10() {
		 return answer_uploaded_url_10;
	 }

	 public void setAnswer_uploaded_url_10(String answer_uploaded_url_10) {
		 this.answer_uploaded_url_10 = answer_uploaded_url_10;
	 }

	 public CommonsMultipartFile getAnswer_file_10() {
		 return answer_file_10;
	 }

	 public void setAnswer_file_10(CommonsMultipartFile answer_file_10) {
		 this.answer_file_10 = answer_file_10;
	 }

	 public CommonsMultipartFile getAnswer_file_video_10() {
		 return answer_file_video_10;
	 }

	 public void setAnswer_file_video_10(CommonsMultipartFile answer_file_video_10) {
		 this.answer_file_video_10 = answer_file_video_10;
	 }

	 public CommonsMultipartFile getAnswer_file_photo_10() {
		 return answer_file_photo_10;
	 }

	 public void setAnswer_file_photo_10(CommonsMultipartFile answer_file_photo_10) {
		 this.answer_file_photo_10 = answer_file_photo_10;
	 }

	 public CommonsMultipartFile getAnswer_file_doc_10() {
		 return answer_file_doc_10;
	 }

	 public void setAnswer_file_doc_10(CommonsMultipartFile answer_file_doc_10) {
		 this.answer_file_doc_10 = answer_file_doc_10;
	 }

	 public String getGrpId() {
		 return grpId;
	 }

	 public void setGrpId(String grpId) {
		 this.grpId = grpId;
	 }

	 public String getGrpName() {
		 return grpName;
	 }

	 public void setGrpName(String grpName) {
		 this.grpName = grpName;
	 }

	 public String getGrpType() {
		 return grpType;
	 }

	 public void setGrpType(String grpType) {
		 this.grpType = grpType;
	 }

	 public String getLocality() {
		 return locality;
	 }

	 public void setLocality(String locality) {
		 this.locality = locality;
	 }

	 public void copyValues(IncyyteModel incyyteModel) {

		 Logger.debug("Copying values----");
		 this.incyyte = incyyteModel.getIncyyte();
		 this.searchedFileName = incyyteModel.getSearchedFileName();
		 this.searchedFileURL= incyyteModel.getSearchedFileURL();
		 this.uploadedVideoFile = incyyteModel.getUploadedVideoFile();
		 this.uploadedPhotoFile = incyyteModel.getUploadedPhotoFile();
		 this.uploadedDocFile = incyyteModel.getUploadedDocFile();
		 this.category = incyyteModel.getCategory();
		 this.answer_upload_opt = incyyteModel.getAnswer_upload_opt();
		 this.ans_uploaded_File = incyyteModel.getAns_uploaded_File();
		 this.emailArr = incyyteModel.getEmailArr(); 
		 if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "1")) {
			 this.answerChoice1 = incyyteModel.getAnswerChoice1();
			 this.answerType1 = incyyteModel.getAnswerType1();
			 this.answerFileName1 = incyyteModel.getAnswerFileName1();
			 this.answerUploadedType1 = incyyteModel.getAnswerUploadedType1();
			 this.answerUploadedFileName1 = incyyteModel.getAnswerUploadedFileName1();
			 this.answer_uploaded_url_1 = incyyteModel.getAnswer_uploaded_url_1();
			 this.answer_file_1 = incyyteModel.getAnswer_file_1();
			 this.answer_file_video_1 = incyyteModel.getAnswer_file_video_1();
			 this.answer_file_photo_1 = incyyteModel.getAnswer_file_photo_1();
			 this.answer_file_doc_1 = incyyteModel.getAnswer_file_doc_1();

		 } else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "2")) {
			 this.answerChoice2 = incyyteModel.getAnswerChoice2();
			 this.answerType2 = incyyteModel.getAnswerType2();
			 this.answerFileName2 = incyyteModel.getAnswerFileName2();
			 this.answerUploadedType2 = incyyteModel.getAnswerUploadedType2();
			 this.answerUploadedFileName2 = incyyteModel.getAnswerUploadedFileName2();
			 this.answer_uploaded_url_2 = incyyteModel.getAnswer_uploaded_url_2();
			 this.answer_file_2 = incyyteModel.getAnswer_file_2();
			 this.answer_file_video_2 = incyyteModel.getAnswer_file_video_2();
			 this.answer_file_photo_2 = incyyteModel.getAnswer_file_photo_2();
			 this.answer_file_doc_2 = incyyteModel.getAnswer_file_doc_2();

		 } else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "3")) {
			 this.answerChoice3 = incyyteModel.getAnswerChoice3();
			 this.answerType3 = incyyteModel.getAnswerType3();
			 this.answerFileName3 = incyyteModel.getAnswerFileName3();
			 this.answerUploadedType3 = incyyteModel.getAnswerUploadedType3();
			 this.answerUploadedFileName3 = incyyteModel.getAnswerUploadedFileName3();
			 this.answer_uploaded_url_3 = incyyteModel.getAnswer_uploaded_url_3();
			 this.answer_file_3 = incyyteModel.getAnswer_file_3();
			 this.answer_file_video_3 = incyyteModel.getAnswer_file_video_3();
			 this.answer_file_photo_3 = incyyteModel.getAnswer_file_photo_3();
			 this.answer_file_doc_3 = incyyteModel.getAnswer_file_doc_3();

		 } else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "4")) {
			 this.answerChoice4 = incyyteModel.getAnswerChoice4();
			 this.answerType4 = incyyteModel.getAnswerType4();
			 this.answerFileName4 = incyyteModel.getAnswerFileName4();
			 this.answerUploadedType4 = incyyteModel.getAnswerUploadedType4();
			 this.answerUploadedFileName4 = incyyteModel.getAnswerUploadedFileName4();
			 this.answer_uploaded_url_4 = incyyteModel.getAnswer_uploaded_url_4();
			 this.answer_file_4 = incyyteModel.getAnswer_file_4();
			 this.answer_file_video_4 = incyyteModel.getAnswer_file_video_4();
			 this.answer_file_photo_4 = incyyteModel.getAnswer_file_photo_4();
			 this.answer_file_doc_4 = incyyteModel.getAnswer_file_doc_4();

		 } else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "5")) {
			 this.answerChoice5 = incyyteModel.getAnswerChoice5();
			 this.answerType5 = incyyteModel.getAnswerType5();
			 this.answerFileName5 = incyyteModel.getAnswerFileName5();
			 this.answerUploadedType5 = incyyteModel.getAnswerUploadedType5();
			 this.answerUploadedFileName5 = incyyteModel.getAnswerUploadedFileName5();
			 this.answer_uploaded_url_5 = incyyteModel.getAnswer_uploaded_url_5();
			 this.answer_file_5 = incyyteModel.getAnswer_file_5();
			 this.answer_file_video_5 = incyyteModel.getAnswer_file_video_5();
			 this.answer_file_photo_5 = incyyteModel.getAnswer_file_photo_5();
			 this.answer_file_doc_5 = incyyteModel.getAnswer_file_doc_5();

		 } else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "6")) {
			 this.answerChoice6 = incyyteModel.getAnswerChoice6();
			 this.answerType6 = incyyteModel.getAnswerType6();
			 this.answerFileName6 = incyyteModel.getAnswerFileName6();
			 this.answerUploadedType6 = incyyteModel.getAnswerUploadedType6();
			 this.answerUploadedFileName6 = incyyteModel.getAnswerUploadedFileName6();
			 this.answer_uploaded_url_6 = incyyteModel.getAnswer_uploaded_url_6();
			 this.answer_file_6 = incyyteModel.getAnswer_file_6();
			 this.answer_file_video_6 = incyyteModel.getAnswer_file_video_6();
			 this.answer_file_photo_6 = incyyteModel.getAnswer_file_photo_6();
			 this.answer_file_doc_6 = incyyteModel.getAnswer_file_doc_6();

		 } else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "7")) {
			 this.answerChoice7 = incyyteModel.getAnswerChoice7();
			 this.answerType7 = incyyteModel.getAnswerType7();
			 this.answerFileName7 = incyyteModel.getAnswerFileName7();
			 this.answerUploadedType7 = incyyteModel.getAnswerUploadedType7();
			 this.answerUploadedFileName7 = incyyteModel.getAnswerUploadedFileName7();
			 this.answer_uploaded_url_7 = incyyteModel.getAnswer_uploaded_url_7();
			 this.answer_file_7 = incyyteModel.getAnswer_file_7();
			 this.answer_file_video_7 = incyyteModel.getAnswer_file_video_7();
			 this.answer_file_photo_7 = incyyteModel.getAnswer_file_photo_7();
			 this.answer_file_doc_7 = incyyteModel.getAnswer_file_doc_7();

		 } else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "8")) {
			 this.answerChoice8 = incyyteModel.getAnswerChoice8();
			 this.answerType8 = incyyteModel.getAnswerType8();
			 this.answerFileName8 = incyyteModel.getAnswerFileName8();
			 this.answerUploadedType8 = incyyteModel.getAnswerUploadedType8();
			 this.answerUploadedFileName8 = incyyteModel.getAnswerUploadedFileName8();
			 this.answer_uploaded_url_8 = incyyteModel.getAnswer_uploaded_url_8();
			 this.answer_file_8 = incyyteModel.getAnswer_file_8();
			 this.answer_file_video_8 = incyyteModel.getAnswer_file_video_8();
			 this.answer_file_photo_8 = incyyteModel.getAnswer_file_photo_8();
			 this.answer_file_doc_8 = incyyteModel.getAnswer_file_doc_8();

		 } else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "9")) {
			 this.answerChoice9 = incyyteModel.getAnswerChoice9();
			 this.answerType9 = incyyteModel.getAnswerType9();
			 this.answerFileName9 = incyyteModel.getAnswerFileName9();
			 this.answerUploadedType9 = incyyteModel.getAnswerUploadedType9();
			 this.answerUploadedFileName9 = incyyteModel.getAnswerUploadedFileName9();
			 this.answer_uploaded_url_9 = incyyteModel.getAnswer_uploaded_url_9();
			 this.answer_file_9 = incyyteModel.getAnswer_file_9();
			 this.answer_file_video_9 = incyyteModel.getAnswer_file_video_9();
			 this.answer_file_photo_9 = incyyteModel.getAnswer_file_photo_9();
			 this.answer_file_doc_9 = incyyteModel.getAnswer_file_doc_9();

		 } else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "10")) {
			 this.answerChoice10 = incyyteModel.getAnswerChoice10();
			 this.answerType10 = incyyteModel.getAnswerType10();
			 this.answerFileName10 = incyyteModel.getAnswerFileName10();
			 this.answerUploadedType10 = incyyteModel.getAnswerUploadedType10();
			 this.answerUploadedFileName10 = incyyteModel.getAnswerUploadedFileName10();
			 this.answer_uploaded_url_10 = incyyteModel.getAnswer_uploaded_url_10();
			 this.answer_file_10 = incyyteModel.getAnswer_file_10();
			 this.answer_file_video_10 = incyyteModel.getAnswer_file_video_10();
			 this.answer_file_photo_10 = incyyteModel.getAnswer_file_photo_10();
			 this.answer_file_doc_10 = incyyteModel.getAnswer_file_doc_10();

		 }
	 }

	 public String getCountry() {
		 return country;
	 }

	 public void setCountry(String country) {
		 this.country = country;
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

	 public String getAgeMin() {
		 return ageMin;
	 }

	 public void setAgeMin(String ageMin) {
		 this.ageMin = ageMin;
	 }

	 public String getAgeMax() {
		 return ageMax;
	 }

	 public void setAgeMax(String ageMax) {
		 this.ageMax = ageMax;
	 }

	 public String getClosureTime() {
		 return closureTime;
	 }

	 public void setClosureTime(String closureTime) {
		 this.closureTime = closureTime;
	 }

	 public Long getPollRecipients() {
		 return pollRecipients;
	 }

	 public void setPollRecipients(Long count) {
		 this.pollRecipients = count;
	 }

	 public Long getPollCost() {
		 return pollCost;
	 }

	 public void setPollCost(Long pollCost) {
		 this.pollCost = pollCost;
	 }

	@Override
	public String toString() {
		return "IncyyteModel [fileName=" + fileName + ", createdbyImageLink="
				+ createdbyImageLink + ", uploadedType=" + uploadedType
				+ ", uploadedFile=" + uploadedFile + ", uploadedVideoFile="
				+ uploadedVideoFile + ", uploadedPhotoFile="
				+ uploadedPhotoFile + ", uploadedDocFile=" + uploadedDocFile
				+ ", uploadedFileName=" + uploadedFileName
				+ ", uploadedFileType=" + uploadedFileType
				+ ", uploadedFileLocation=" + uploadedFileLocation
				+ ", contentType=" + contentType + ", id=" + id + ", incyyte="
				+ incyyte + ", incyyteCode=" + incyyteCode + ", category="
				+ category + ", randomize=" + randomize + ", multiSelection="
				+ multiSelection + ", style=" + style + ", answerArr="
				+ answerArr + ", eLink=" + eLink + ", grpId=" + grpId
				+ ", grpName=" + grpName + ", grpType=" + grpType
				+ ", emailArr=" + emailArr + ", closureDate=" + closureDate
				+ ", closureTime=" + closureTime + ", addFile=" + addFile
				+ ", addLink=" + addLink + ", pollResultHidden="
				+ pollResultHidden + ", anonymity=" + anonymity + ", sendType="
				+ sendType + ", pageName=" + pageName + ", createdBy="
				+ createdBy + ", quesType=" + quesType + ", public_poll="
				+ public_poll + ", sendToGroup=" + sendToGroup
				+ ", senderfname=" + senderfname + ", senderlname="
				+ senderlname + ", uploadedLogo=" + uploadedLogo
				+ ", uploadedLogoLocation=" + uploadedLogoLocation
				+ ", strapline=" + strapline + ", allowComment=" + allowComment
				+ ", protectPage=" + protectPage + ", createdDate="
				+ createdDate + ", ageRange=" + ageRange + ", ageMin=" + ageMin
				+ ", ageMax=" + ageMax + ", gender=" + gender + ", region="
				+ region + ", county=" + county + ", postcode=" + postcode
				+ ", locality=" + locality + ", country=" + country
				+ ", youTubeQuestionVideoId=" + youTubeQuestionVideoId
				+ ", youTubeAnswer_1_VideoId=" + youTubeAnswer_1_VideoId
				+ ", youTubeAnswer_2_VideoId=" + youTubeAnswer_2_VideoId
				+ ", youTubeAnswer_3_VideoId=" + youTubeAnswer_3_VideoId
				+ ", youTubeAnswer_4_VideoId=" + youTubeAnswer_4_VideoId
				+ ", youTubeAnswer_5_VideoId=" + youTubeAnswer_5_VideoId
				+ ", youTubeAnswer_6_VideoId=" + youTubeAnswer_6_VideoId
				+ ", youTubeAnswer_7_VideoId=" + youTubeAnswer_7_VideoId
				+ ", youTubeAnswer_8_VideoId=" + youTubeAnswer_8_VideoId
				+ ", youTubeAnswer_9_VideoId=" + youTubeAnswer_9_VideoId
				+ ", youTubeAnswer_10_VideoId=" + youTubeAnswer_10_VideoId
				+ ", answers=" + answers + ", answer_count=" + answer_count
				+ ", answer_upload_opt=" + answer_upload_opt
				+ ", answer_upload_type=" + answer_upload_type
				+ ", answerFileName=" + answerFileName + ", answerFileURL="
				+ answerFileURL + ", ans_uploaded_File=" + ans_uploaded_File
				+ ", answerChoice1=" + answerChoice1 + ", answer_opt_1="
				+ answer_opt_1 + ", answer_link_1=" + answer_link_1
				+ ", answerType1=" + answerType1 + ", answerFileName1="
				+ answerFileName1 + ", answerUploadedType1="
				+ answerUploadedType1 + ", answerUploadedFileName1="
				+ answerUploadedFileName1 + ", answer_uploaded_url_1="
				+ answer_uploaded_url_1 + ", answer_file_1=" + answer_file_1
				+ ", answer_file_video_1=" + answer_file_video_1
				+ ", answer_file_photo_1=" + answer_file_photo_1
				+ ", answer_file_doc_1=" + answer_file_doc_1
				+ ", answerChoice2=" + answerChoice2 + ", answer_opt_2="
				+ answer_opt_2 + ", answer_link_2=" + answer_link_2
				+ ", answerType2=" + answerType2 + ", answerFileName2="
				+ answerFileName2 + ", answerUploadedType2="
				+ answerUploadedType2 + ", answerUploadedFileName2="
				+ answerUploadedFileName2 + ", answer_uploaded_url_2="
				+ answer_uploaded_url_2 + ", answer_file_2=" + answer_file_2
				+ ", answer_file_video_2=" + answer_file_video_2
				+ ", answer_file_photo_2=" + answer_file_photo_2
				+ ", answer_file_doc_2=" + answer_file_doc_2
				+ ", answerChoice3=" + answerChoice3 + ", answer_opt_3="
				+ answer_opt_3 + ", answer_link_3=" + answer_link_3
				+ ", answerType3=" + answerType3 + ", answerFileName3="
				+ answerFileName3 + ", answerUploadedType3="
				+ answerUploadedType3 + ", answerUploadedFileName3="
				+ answerUploadedFileName3 + ", answer_uploaded_url_3="
				+ answer_uploaded_url_3 + ", answer_file_3=" + answer_file_3
				+ ", answer_file_video_3=" + answer_file_video_3
				+ ", answer_file_photo_3=" + answer_file_photo_3
				+ ", answer_file_doc_3=" + answer_file_doc_3
				+ ", answerChoice4=" + answerChoice4 + ", answer_opt_4="
				+ answer_opt_4 + ", answer_link_4=" + answer_link_4
				+ ", answerType4=" + answerType4 + ", answerFileName4="
				+ answerFileName4 + ", answerUploadedType4="
				+ answerUploadedType4 + ", answerUploadedFileName4="
				+ answerUploadedFileName4 + ", answer_uploaded_url_4="
				+ answer_uploaded_url_4 + ", answer_file_4=" + answer_file_4
				+ ", answer_file_video_4=" + answer_file_video_4
				+ ", answer_file_photo_4=" + answer_file_photo_4
				+ ", answer_file_doc_4=" + answer_file_doc_4
				+ ", answerChoice5=" + answerChoice5 + ", answer_opt_5="
				+ answer_opt_5 + ", answer_link_5=" + answer_link_5
				+ ", answerType5=" + answerType5 + ", answerFileName5="
				+ answerFileName5 + ", answerUploadedType5="
				+ answerUploadedType5 + ", answerUploadedFileName5="
				+ answerUploadedFileName5 + ", answer_uploaded_url_5="
				+ answer_uploaded_url_5 + ", answer_file_5=" + answer_file_5
				+ ", answer_file_video_5=" + answer_file_video_5
				+ ", answer_file_photo_5=" + answer_file_photo_5
				+ ", answer_file_doc_5=" + answer_file_doc_5
				+ ", answerChoice6=" + answerChoice6 + ", answer_opt_6="
				+ answer_opt_6 + ", answer_link_6=" + answer_link_6
				+ ", answerType6=" + answerType6 + ", answerFileName6="
				+ answerFileName6 + ", answerUploadedType6="
				+ answerUploadedType6 + ", answerUploadedFileName6="
				+ answerUploadedFileName6 + ", answer_uploaded_url_6="
				+ answer_uploaded_url_6 + ", answer_file_6=" + answer_file_6
				+ ", answer_file_video_6=" + answer_file_video_6
				+ ", answer_file_photo_6=" + answer_file_photo_6
				+ ", answer_file_doc_6=" + answer_file_doc_6
				+ ", answerChoice7=" + answerChoice7 + ", answer_opt_7="
				+ answer_opt_7 + ", answer_link_7=" + answer_link_7
				+ ", answerType7=" + answerType7 + ", answerFileName7="
				+ answerFileName7 + ", answerUploadedType7="
				+ answerUploadedType7 + ", answerUploadedFileName7="
				+ answerUploadedFileName7 + ", answer_uploaded_url_7="
				+ answer_uploaded_url_7 + ", answer_file_7=" + answer_file_7
				+ ", answer_file_video_7=" + answer_file_video_7
				+ ", answer_file_photo_7=" + answer_file_photo_7
				+ ", answer_file_doc_7=" + answer_file_doc_7
				+ ", answerChoice8=" + answerChoice8 + ", answer_opt_8="
				+ answer_opt_8 + ", answer_link_8=" + answer_link_8
				+ ", answerType8=" + answerType8 + ", answerFileName8="
				+ answerFileName8 + ", answerUploadedType8="
				+ answerUploadedType8 + ", answerUploadedFileName8="
				+ answerUploadedFileName8 + ", answer_uploaded_url_8="
				+ answer_uploaded_url_8 + ", answer_file_8=" + answer_file_8
				+ ", answer_file_video_8=" + answer_file_video_8
				+ ", answer_file_photo_8=" + answer_file_photo_8
				+ ", answer_file_doc_8=" + answer_file_doc_8
				+ ", answerChoice9=" + answerChoice9 + ", answer_opt_9="
				+ answer_opt_9 + ", answer_link_9=" + answer_link_9
				+ ", answerType9=" + answerType9 + ", answerFileName9="
				+ answerFileName9 + ", answerUploadedType9="
				+ answerUploadedType9 + ", answerUploadedFileName9="
				+ answerUploadedFileName9 + ", answer_uploaded_url_9="
				+ answer_uploaded_url_9 + ", answer_file_9=" + answer_file_9
				+ ", answer_file_video_9=" + answer_file_video_9
				+ ", answer_file_photo_9=" + answer_file_photo_9
				+ ", answer_file_doc_9=" + answer_file_doc_9
				+ ", answerChoice10=" + answerChoice10 + ", answer_opt_10="
				+ answer_opt_10 + ", answer_link_10=" + answer_link_10
				+ ", answerType10=" + answerType10 + ", answerFileName10="
				+ answerFileName10 + ", answerUploadedType10="
				+ answerUploadedType10 + ", answerUploadedFileName10="
				+ answerUploadedFileName10 + ", answer_uploaded_url_10="
				+ answer_uploaded_url_10 + ", answer_file_10=" + answer_file_10
				+ ", answer_file_video_10=" + answer_file_video_10
				+ ", answer_file_photo_10=" + answer_file_photo_10
				+ ", answer_file_doc_10=" + answer_file_doc_10
				+ ", searchedFileName=" + searchedFileName
				+ ", searchedFileURL=" + searchedFileURL + "]";
	}

	/**
	  * @return the incyyteCode
	  */
	 public String getIncyyteCode() {
		 return incyyteCode;
	 }

	 /**
	  * @param incyyteCode the incyyteCode to set
	  */
	 public void setIncyyteCode(String incyyteCode) {
		 this.incyyteCode = incyyteCode;
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



}