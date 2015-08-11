package com.incyyte.app.web.controller;

import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.AnswerModel;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.Photo;
import com.incyyte.app.web.xml.QuestionStyle;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseContoller {

	@Autowired
	private QuickStartService quickStartSrv;
	@Autowired
	private QuestionStyle questionStyle;

	//UploadFile method is used to upload the object when Question images are selected
	public IncyyteModel uploadFile(IncyyteModel incyyteModel) {
		CommonsMultipartFile multipartFile = incyyteModel.getUploadedFile();
		if (multipartFile != null && multipartFile.getSize() > 0) {
			Logger.debug("OriginalFilename - " + multipartFile.getOriginalFilename());
			Logger.debug("ContentType - " + multipartFile.getContentType());
			Logger.debug("Name - " + multipartFile.getName());
			Logger.debug("Size - " + multipartFile.getSize());
			Logger.debug("StorageDescription - " + multipartFile.getStorageDescription());
			try {
				incyyteModel.setFileName(multipartFile.getOriginalFilename());
				incyyteModel.setContentType(multipartFile.getContentType());
				String remoteFile = generateFileName(multipartFile.getOriginalFilename());
				String type = getType(multipartFile.getContentType());
				incyyteModel.setUploadedFileName(remoteFile);
				incyyteModel.setUploadedFileType(type);
				String uploadURL = FileManagementUtil.uploadFile(incyyteModel.getUploadedFileType(), incyyteModel.getUploadedFileName(), multipartFile, null);
				Logger.debug("remoteFile url - " + uploadURL);
				incyyteModel.setUploadedFileLocation(uploadURL);
			} catch (Exception e) {
                Logger.error("Exception:", e);
            }
		} else {
			Logger.debug("mulipartfile is null ");
		}
		return incyyteModel;
	}

	public void uploadQuestionPhotos(IncyyteModel incyyteModel, Photo searchedPicture) {
		if (searchedPicture != null && searchedPicture.getSize() > 0) {
			Logger.debug("OriginalFilename - " + searchedPicture.getFileName());
			Logger.debug("ContentType - " + searchedPicture.getContentType());
			Logger.debug("Size - " + searchedPicture.getSize());
			try {
				incyyteModel.setFileName(searchedPicture.getFileName());
				incyyteModel.setContentType(searchedPicture.getContentType());
				String remoteFile = generateFileName(searchedPicture.getFileName());
				String type = getType(searchedPicture.getContentType());
				incyyteModel.setUploadedFileName(remoteFile);
				incyyteModel.setUploadedFileType(type);
				searchedPicture.setFileName(remoteFile);
				searchedPicture.setContentType(type);
				String uploadURL = FileManagementUtil.uploadFile(incyyteModel.getUploadedFileType(), incyyteModel.getUploadedFileName(), null, searchedPicture);
				Logger.debug("remoteFile url - " + uploadURL);
				incyyteModel.setUploadedFileLocation(uploadURL);
			} catch (Exception e) {
                Logger.error("Exception:", e);
            }
		} else {
			Logger.debug("mulipartfile is null ");
		}
	}

	//UploadAnsFile method is used to upload the object when Answer images are selected
	public void uploadAnsFile(IncyyteModel incyyteModel) {
		CommonsMultipartFile multipartFile = incyyteModel.getAns_uploaded_File();
		if (multipartFile != null && multipartFile.getSize() > 0) {
			Logger.debug("OriginalFilename - " + multipartFile.getOriginalFilename());
			Logger.debug("ContentType - " + multipartFile.getContentType());
			Logger.debug("Name - " + multipartFile.getName());
			Logger.debug("Size - " + multipartFile.getSize());
			Logger.debug("StorageDescription - " + multipartFile.getStorageDescription());
			try {
				String remoteFile = generateFileName(multipartFile.getOriginalFilename());
				String type = getType(multipartFile.getContentType());
				String uploadURL = FileManagementUtil.uploadFile(type, remoteFile, multipartFile, null);
				Logger.debug("remoteFile url - " + uploadURL);
				if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "1")) {
					incyyteModel.setAnswer_opt_1("1");
					incyyteModel.setAnswerFileName1(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType1(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName1(remoteFile);
					incyyteModel.setAnswerUploadedType1(type);
					incyyteModel.setAnswer_uploaded_url_1(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "2")) {
					incyyteModel.setAnswer_opt_2("2");
					incyyteModel.setAnswerFileName2(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType2(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName2(remoteFile);
					incyyteModel.setAnswerUploadedType2(type);
					incyyteModel.setAnswer_uploaded_url_2(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "3")) {
					incyyteModel.setAnswer_opt_3("3");
					incyyteModel.setAnswerFileName3(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType3(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName3(remoteFile);
					incyyteModel.setAnswerUploadedType3(type);
					incyyteModel.setAnswer_uploaded_url_3(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "4")) {
					incyyteModel.setAnswer_opt_4("4");
					incyyteModel.setAnswerFileName4(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType4(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName4(remoteFile);
					incyyteModel.setAnswerUploadedType4(type);
					incyyteModel.setAnswer_uploaded_url_4(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "5")) {
					incyyteModel.setAnswer_opt_5("5");
					incyyteModel.setAnswerFileName5(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType5(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName5(remoteFile);
					incyyteModel.setAnswerUploadedType5(type);
					incyyteModel.setAnswer_uploaded_url_5(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "6")) {
					incyyteModel.setAnswer_opt_6("6");
					incyyteModel.setAnswerFileName6(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType6(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName6(remoteFile);
					incyyteModel.setAnswerUploadedType6(type);
					incyyteModel.setAnswer_uploaded_url_6(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "7")) {
					incyyteModel.setAnswer_opt_7("7");
					incyyteModel.setAnswerFileName7(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType7(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName7(remoteFile);
					incyyteModel.setAnswerUploadedType7(type);
					incyyteModel.setAnswer_uploaded_url_7(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "8")) {
					incyyteModel.setAnswer_opt_8("8");
					incyyteModel.setAnswerFileName8(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType8(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName8(remoteFile);
					incyyteModel.setAnswerUploadedType8(type);
					incyyteModel.setAnswer_uploaded_url_8(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "9")) {
					incyyteModel.setAnswer_opt_9("9");
					incyyteModel.setAnswerFileName9(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType9(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName9(remoteFile);
					incyyteModel.setAnswerUploadedType9(type);
					incyyteModel.setAnswer_uploaded_url_9(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "10")) {
					incyyteModel.setAnswer_opt_10("10");
					incyyteModel.setAnswerFileName10(multipartFile.getOriginalFilename());
					incyyteModel.setAnswerType10(multipartFile.getContentType());
					incyyteModel.setAnswerUploadedFileName10(remoteFile);
					incyyteModel.setAnswerUploadedType10(type);
					incyyteModel.setAnswer_uploaded_url_10(uploadURL);
				}
				Logger.debug("InCyyteModel:UploadAnswerFile: - " + incyyteModel);
			} catch (Exception e) {
                Logger.error("Exception:", e);
            }
		} else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
			Logger.debug("URL:" + incyyteModel.getSearchedFileURL());
			Logger.debug("ContentType:" + incyyteModel.getSearchedFileName());
			try {
				//Downloads the file and keeps in temp location
				URL url = new URL(incyyteModel.getSearchedFileURL());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + incyyteModel.getSearchedFileName();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(incyyteModel.getSearchedFileURL());
				searchedImage.setContentType(fileType);
				searchedImage.setContainerName(fileType);
				searchedImage.setSize(file.getTotalSpace());
				Logger.debug("%Searched Image%" + searchedImage);

				String remoteFile = generateFileName(searchedImage.getFileName());
				String type = getType(searchedImage.getContentType());
				searchedImage.setFileName(remoteFile);
				searchedImage.setContentType(type);
				String uploadURL = FileManagementUtil.uploadFile(type, remoteFile, null, searchedImage);
				Logger.debug("remoteFile url - " + uploadURL);
				if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "1")) {
					incyyteModel.setAnswer_opt_1("1");
					incyyteModel.setAnswerFileName1(file.getName());
					incyyteModel.setAnswerType1(fileType);
					incyyteModel.setAnswerUploadedFileName1(remoteFile);
					incyyteModel.setAnswerUploadedType1(type);
					incyyteModel.setAnswer_uploaded_url_1(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "2")) {
					incyyteModel.setAnswer_opt_2("2");
					incyyteModel.setAnswerFileName2(file.getName());
					incyyteModel.setAnswerType2(fileType);
					incyyteModel.setAnswerUploadedFileName2(remoteFile);
					incyyteModel.setAnswerUploadedType2(type);
					incyyteModel.setAnswer_uploaded_url_2(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "3")) {
					incyyteModel.setAnswer_opt_3("3");
					incyyteModel.setAnswerFileName3(file.getName());
					incyyteModel.setAnswerType3(fileType);
					incyyteModel.setAnswerUploadedFileName3(remoteFile);
					incyyteModel.setAnswerUploadedType3(type);
					incyyteModel.setAnswer_uploaded_url_3(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "4")) {
					incyyteModel.setAnswer_opt_4("4");
					incyyteModel.setAnswerFileName4(file.getName());
					incyyteModel.setAnswerType4(fileType);
					incyyteModel.setAnswerUploadedFileName4(remoteFile);
					incyyteModel.setAnswerUploadedType4(type);
					incyyteModel.setAnswer_uploaded_url_4(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "5")) {
					incyyteModel.setAnswer_opt_5("5");
					incyyteModel.setAnswerFileName5(file.getName());
					incyyteModel.setAnswerType5(fileType);
					incyyteModel.setAnswerUploadedFileName5(remoteFile);
					incyyteModel.setAnswerUploadedType5(type);
					incyyteModel.setAnswer_uploaded_url_5(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "6")) {
					incyyteModel.setAnswer_opt_6("6");
					incyyteModel.setAnswerFileName6(file.getName());
					incyyteModel.setAnswerType6(fileType);
					incyyteModel.setAnswerUploadedFileName6(remoteFile);
					incyyteModel.setAnswerUploadedType6(type);
					incyyteModel.setAnswer_uploaded_url_6(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "7")) {
					incyyteModel.setAnswer_opt_7("7");
					incyyteModel.setAnswerFileName7(file.getName());
					incyyteModel.setAnswerType7(fileType);
					incyyteModel.setAnswerUploadedFileName7(remoteFile);
					incyyteModel.setAnswerUploadedType7(type);
					incyyteModel.setAnswer_uploaded_url_7(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "8")) {
					incyyteModel.setAnswer_opt_8("8");
					incyyteModel.setAnswerFileName8(file.getName());
					incyyteModel.setAnswerType8(fileType);
					incyyteModel.setAnswerUploadedFileName8(remoteFile);
					incyyteModel.setAnswerUploadedType8(type);
					incyyteModel.setAnswer_uploaded_url_8(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "9")) {
					incyyteModel.setAnswer_opt_9("9");
					incyyteModel.setAnswerFileName9(file.getName());
					incyyteModel.setAnswerType9(fileType);
					incyyteModel.setAnswerUploadedFileName9(remoteFile);
					incyyteModel.setAnswerUploadedType9(type);
					incyyteModel.setAnswer_uploaded_url_9(uploadURL);
				} else if (StringUtils.equals(incyyteModel.getAnswer_upload_opt(), "10")) {
					incyyteModel.setAnswer_opt_10("10");
					incyyteModel.setAnswerFileName10(file.getName());
					incyyteModel.setAnswerType10(fileType);
					incyyteModel.setAnswerUploadedFileName10(remoteFile);
					incyyteModel.setAnswerUploadedType10(type);
					incyyteModel.setAnswer_uploaded_url_10(uploadURL);
				}
				Logger.debug("InCyyteModel:UploadAnswerFile (Search file load):" + incyyteModel);
			} catch (Exception e) {
                Logger.error("Exception:", e);
            }
		} else {
			Logger.debug("mulipart file is null ");
		}
	}

	public void deleteUploadFile(IncyyteModel incyyteModel) {
		if (StringUtils.isNotEmpty(incyyteModel.getUploadedFileName()) && StringUtils.isNotEmpty(incyyteModel.getUploadedFileType())) {
			Logger.debug("UploadedFileName - " + incyyteModel.getUploadedFileName());
			Logger.debug("ContentType - " + incyyteModel.getUploadedFileType());
			try {
				FileManagementUtil.deleteRemoteFile(incyyteModel.getUploadedFileType(), incyyteModel.getUploadedFileName());
			} catch (Exception e) {
                Logger.error("Exception:", e);
            }
		} else {
			Logger.debug("mulipartfile is null ");
		}
	}

	public void deleteAnsUploadFile(IncyyteModel incyyteModel) {
		if (StringUtils.isNotEmpty(incyyteModel.getAnswerFileName()) && StringUtils.isNotEmpty(incyyteModel.getAnswer_upload_type())) {
			Logger.debug("getAnswer_upload_type - " + incyyteModel.getAnswer_upload_type());
			Logger.debug("getAnswerFileName - " + incyyteModel.getAnswerFileName());
			try {
				FileManagementUtil.deleteRemoteFile(incyyteModel.getAnswer_upload_type(), incyyteModel.getAnswerFileName());
			} catch (Exception e) {
                Logger.error("Exception:", e);
            }
		} else {
			Logger.debug("mulipartfile is null ");
		}
	}

	public String uploadFileToRackSpace(CommonsMultipartFile multipartFile) {
		String uploadURL = null;
		if (multipartFile != null && multipartFile.getSize() > 0) {
			Logger.debug("OriginalFilename - " + multipartFile.getOriginalFilename());
			Logger.debug("ContentType - " + multipartFile.getContentType());
			Logger.debug("Name - " + multipartFile.getName());
			Logger.debug("Size - " + multipartFile.getSize());
			Logger.debug("StorageDescription - " + multipartFile.getStorageDescription());
			try {
				String type = getType(multipartFile.getContentType());
				uploadURL = FileManagementUtil.uploadFile(type, multipartFile.getOriginalFilename(), multipartFile, null);
				Logger.debug("remoteFile url - " + uploadURL);
			} catch (Exception e) {
                Logger.error("Exception:", e);
            }
		} else {
			Logger.debug("mulipartfile is null ");
		}
		return uploadURL;
	}

	protected void sendIncyyteEmail(User user, boolean anonymity, Long incyyteId, HttpServletRequest request) throws Exception {
		String serverURL = Utility.getServerURL(request);
		ServletContext servletContext = request.getSession().getServletContext();
		// send email
		quickStartSrv.sendInCyyte(user, anonymity, incyyteId, serverURL, servletContext);
	}

	protected InCyyte getQSInCyyte(Long id) {
		return quickStartSrv.initChart(id);
	}

	public Long addInCyyteByEmails(User user, InCyyte incyyte, HttpServletRequest request) throws Exception {
        Logger.debug("addInCyyteByEmails: Start::" + incyyte);
        incyyte.setUserId(user.getId());
		Long incyyteId = quickStartSrv.addInCyyteByEmails(incyyte);
		Logger.debug("incyyteId - " + incyyteId);
		request.getSession().setAttribute(SessionKeys.INCYYTE_ID, incyyteId);
		return incyyteId;
	}

	public Long addInCyyteByGroup(User user, InCyyte incyyte, HttpServletRequest request) throws Exception {
		incyyte.setUserId(user.getId());
		Long incyyteId = quickStartSrv.addInCyyteByGroup(incyyte, incyyte.getGrpId());
		Logger.debug("incyyteId - " + incyyteId);
		request.getSession().setAttribute(SessionKeys.INCYYTE_ID, incyyteId);
		return incyyteId;
	}

	public Long addInCyyteByPosting(User user, InCyyte incyyte, HttpServletRequest request) throws Exception {
		incyyte.setUserId(user.getId());
		Long incyyteId = quickStartSrv.addInCyyteByPosting(incyyte);
		Logger.debug("incyyteId - " + incyyteId);
		request.getSession().setAttribute(SessionKeys.INCYYTE_ID, incyyteId);
		return incyyteId;
	}

	protected Long addIncyyte(User user, InCyyte incyyte, HttpServletRequest request) throws Exception {
		if (incyyte.getContacts() == null || incyyte.getContacts().isEmpty()) {
			Contact contact = new Contact();
			contact.setEmail(user.getEmail());
			List<Contact> contacts = new ArrayList<Contact>();
			contacts.add(contact);
			incyyte.setContacts(contacts);
		}
		Logger.debug("user.getId() - " + user.getId());
		Long incyyteId = quickStartSrv.createUserInCyyte(user.getId(), incyyte);
		Logger.debug("incyyteId - " + incyyteId);
		request.getSession().setAttribute(SessionKeys.INCYYTE_ID, incyyteId);
		Logger.debug("incyyteId 2- " + incyyteId);
		questionStyle.getQuestionXml(incyyteId);
		Logger.debug("incyyteId 3- " + incyyteId);
		return incyyteId;
	}

	protected void prefixLink(IncyyteModel incyyteModel) {
		if (StringUtils.isNotBlank(incyyteModel.geteLink())) {
			if (incyyteModel.geteLink().indexOf("http://") == -1) {
				incyyteModel.seteLink("http://" + incyyteModel.geteLink());
			}
		}
	}

	private String getFileExtension(String fileName) {
		try {
			return fileName.substring(fileName.lastIndexOf("."), fileName.length());
		} catch (Exception e) {
			return null;
		}
	}


	protected List<AnswerModel> getAnswers(IncyyteModel model) {
		List<AnswerModel> answers = new ArrayList<AnswerModel>();
		int count = model.getAnswer_count();
		Logger.debug("ans size - > " + answers.size());
		Logger.debug("Inside getAnswers()");
		Logger.debug("count :::" + count + "  " + model.getAnswer_upload_opt());
		AnswerModel ans = new AnswerModel();

		ans.setAnswerOption("1");
		ans.setAnswerChoice(model.getAnswerChoice1());
		ans.setAnswerlink(model.getAnswer_link_1());
		ans.setAnswerType(model.getAnswerType1());
		ans.setAnswerFileName(model.getAnswerFileName1());
		ans.setCdnFileName(model.getAnswerUploadedFileName1());
		ans.setAnswerUploadedUrl(model.getAnswerUploadedType1());
		Logger.debug("Answer1 :model: " + model);
		Logger.debug("Answer1 :ans: " + ans);
		if (StringUtils.isNotBlank(model.getAnswerFileName1())) {
			Logger.debug("extension1" + getFileExtension(model.getAnswerFileName1()));
			ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName1()));
		}
		ans.setUploadedAnsFile(model.getAnswer_file_1());
		ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_1_VideoId());
		answers.add(ans);
		Logger.debug("ans size - > " + answers.size());
		Logger.debug("Inside getAnswer_upload_opt 2");
		ans = new AnswerModel();
		ans.setAnswerOption("2");
		ans.setAnswerChoice(model.getAnswerChoice2());
		ans.setAnswerlink(model.getAnswer_link_2());
		ans.setAnswerType(model.getAnswerType2());
		ans.setAnswerFileName(model.getAnswerFileName2());
		ans.setCdnFileName(model.getAnswerUploadedFileName2());
		ans.setAnswerUploadedUrl(model.getAnswerUploadedType2());
		Logger.debug("Answer2 :: " + ans);
		if (StringUtils.isNotBlank(model.getAnswerFileName2())) {
			ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName2()));
		}
		ans.setUploadedAnsFile(model.getAnswer_file_2());
		ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_2_VideoId());

		answers.add(ans);
		Logger.debug("ans size - > " + answers.size());

		Logger.debug("Inside getAnswer_upload_opt 3");
		ans = new AnswerModel();
		ans.setAnswerOption("3");
		ans.setAnswerChoice(model.getAnswerChoice3());
		ans.setAnswerlink(model.getAnswer_link_3());
		ans.setAnswerType(model.getAnswerType3());
		ans.setAnswerFileName(model.getAnswerFileName3());
		ans.setCdnFileName(model.getAnswerUploadedFileName3());
		ans.setAnswerUploadedUrl(model.getAnswerUploadedType3());
		Logger.debug("Answer3 :: " + ans);
		if (StringUtils.isNotBlank(model.getAnswerFileName3())) {
			ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName3()));
		}
		ans.setUploadedAnsFile(model.getAnswer_file_3());
		ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_3_VideoId());

		answers.add(ans);
		Logger.debug("ans size - > " + answers.size());

		if (model.getAnswer_count() >= 4) {
			Logger.debug("Inside getAnswer_upload_opt 4" + model.getAnswer_count());
			ans = new AnswerModel();
			ans.setAnswerOption("4");
			ans.setAnswerChoice(model.getAnswerChoice4());
			ans.setAnswerlink(model.getAnswer_link_4());
			ans.setAnswerType(model.getAnswerType4());
			ans.setAnswerFileName(model.getAnswerFileName4());
			ans.setCdnFileName(model.getAnswerUploadedFileName4());
			ans.setAnswerUploadedUrl(model.getAnswerUploadedType4());
			Logger.debug("Answer4 :: " + ans);
			if (StringUtils.isNotBlank(model.getAnswerFileName4())) {
				ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName4()));
			}
			ans.setUploadedAnsFile(model.getAnswer_file_4());
			ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_4_VideoId());

			answers.add(ans);
			Logger.debug("ans size - > " + answers.size());
		}
		if (model.getAnswer_count() >= 5) {
			Logger.debug("Inside getAnswer_upload_opt 5" + model.getAnswer_count());
			ans = new AnswerModel();
			ans.setAnswerOption("5");
			ans.setAnswerChoice(model.getAnswerChoice5());
			ans.setAnswerlink(model.getAnswer_link_5());
			ans.setAnswerType(model.getAnswerType5());
			ans.setAnswerFileName(model.getAnswerFileName5());
			ans.setCdnFileName(model.getAnswerUploadedFileName5());
			ans.setAnswerUploadedUrl(model.getAnswerUploadedType5());
			Logger.debug("Answer5 :: " + ans);
			if (StringUtils.isNotBlank(model.getAnswerFileName5())) {
				ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName5()));
			}
			ans.setUploadedAnsFile(model.getAnswer_file_5());
			ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_5_VideoId());

			answers.add(ans);
		}
		if (model.getAnswer_count() >= 6) {
			Logger.debug("Inside getAnswer_upload_opt 6" + model.getAnswer_count());
			ans = new AnswerModel();
			ans.setAnswerOption("6");
			ans.setAnswerChoice(model.getAnswerChoice6());
			ans.setAnswerlink(model.getAnswer_link_6());
			ans.setAnswerType(model.getAnswerType6());
			ans.setAnswerFileName(model.getAnswerFileName6());
			ans.setCdnFileName(model.getAnswerUploadedFileName6());
			ans.setAnswerUploadedUrl(model.getAnswerUploadedType6());
			Logger.debug("Answer6 :: " + ans);
			if (StringUtils.isNotBlank(model.getAnswerFileName6())) {
				ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName6()));
			}
			ans.setUploadedAnsFile(model.getAnswer_file_6());
			ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_6_VideoId());

			answers.add(ans);
		}
		if (model.getAnswer_count() >= 7) {
			Logger.debug("========Inside getAnswer_upload_opt 7========" + model.getAnswer_count());
			ans = new AnswerModel();
			ans.setAnswerOption("7");
			ans.setAnswerChoice(model.getAnswerChoice7());
			ans.setAnswerlink(model.getAnswer_link_7());
			ans.setAnswerType(model.getAnswerType7());
			ans.setAnswerFileName(model.getAnswerFileName7());
			ans.setCdnFileName(model.getAnswerUploadedFileName7());
			ans.setAnswerUploadedUrl(model.getAnswerUploadedType7());
			Logger.debug("Answer7 :: " + ans);
			if (StringUtils.isNotBlank(model.getAnswerFileName7())) {
				ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName7()));
			}
			ans.setUploadedAnsFile(model.getAnswer_file_7());
			ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_7_VideoId());

			answers.add(ans);
		}
		if (model.getAnswer_count() >= 8) {
			Logger.debug("========Inside getAnswer_upload_opt 8========" + model.getAnswer_count());
			ans = new AnswerModel();
			ans.setAnswerOption("8");
			ans.setAnswerChoice(model.getAnswerChoice8());
			ans.setAnswerlink(model.getAnswer_link_8());
			ans.setAnswerType(model.getAnswerType8());
			ans.setAnswerFileName(model.getAnswerFileName8());
			ans.setCdnFileName(model.getAnswerUploadedFileName8());
			ans.setAnswerUploadedUrl(model.getAnswerUploadedType8());
			Logger.debug("model.getAnswer_file_8()- " + model.getAnswer_file_8() + "---" + model.getAnswerFileName8());
			if (StringUtils.isNotBlank(model.getAnswerFileName8())) {
				ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName8()));
			}
			ans.setUploadedAnsFile(model.getAnswer_file_8());
			ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_8_VideoId());

			answers.add(ans);
		}
		if (model.getAnswer_count() >= 9) {
			Logger.debug("========Inside getAnswer_upload_opt 9========" + model.getAnswer_count());
			ans = new AnswerModel();
			ans.setAnswerOption("9");
			ans.setAnswerChoice(model.getAnswerChoice9());
			ans.setAnswerlink(model.getAnswer_link_9());
			ans.setAnswerType(model.getAnswerType9());
			ans.setAnswerFileName(model.getAnswerFileName9());
			ans.setCdnFileName(model.getAnswerUploadedFileName9());
			ans.setAnswerUploadedUrl(model.getAnswerUploadedType9());
			Logger.debug("model.getAnswer_file_9()- " + model.getAnswer_file_9() + "---" + model.getAnswerFileName9());
			if (StringUtils.isNotBlank(model.getAnswerFileName9())) {
				ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName9()));
			}
			ans.setUploadedAnsFile(model.getAnswer_file_9());
			ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_9_VideoId());

			answers.add(ans);
		}
		if (model.getAnswer_count() >= 10) {
			Logger.debug("========Inside getAnswer_upload_opt 10========" + model.getAnswer_count());
			ans = new AnswerModel();
			ans.setAnswerOption("10");
			ans.setAnswerChoice(model.getAnswerChoice10());
			ans.setAnswerlink(model.getAnswer_link_10());
			ans.setAnswerType(model.getAnswerType10());
			ans.setAnswerFileName(model.getAnswerFileName10());
			ans.setCdnFileName(model.getAnswerUploadedFileName10());
			ans.setAnswerUploadedUrl(model.getAnswerUploadedType10());
			Logger.debug("model.getAnswer_file_10()- " + model.getAnswer_file_10() + "---" + model.getAnswerFileName10());
			if (StringUtils.isNotBlank(model.getAnswerFileName10())) {
				ans.setAnswerUploadExt(getFileExtension(model.getAnswerFileName10()));
			}
			ans.setUploadedAnsFile(model.getAnswer_file_10());
			ans.setUploadedAnsFileYoutubeVideo(model.getYouTubeAnswer_10_VideoId());

			answers.add(ans);
		}
		Logger.debug("ans size - > " + answers.size());
		Logger.debug("ans- > " + answers);
		return answers;
	}

	protected Set<AnswerModel> getAnswers(String arr) {
		Set<AnswerModel> answers = new HashSet<AnswerModel>();
		Logger.debug("ans array - > " + arr);

		String[] answerArr = arr.split("\\|");
		for (int x = 0; x < answerArr.length; x++) {
			Logger.debug("ans -> " + answerArr[x]);
			AnswerModel model = new AnswerModel();
			model.setAnswerOption(answerArr[x]);
			answers.add(model);
		}
		Logger.debug("ans size - > " + answers.size());
		return answers;
	}

	protected String getType(String cType) throws IOException {
		return cType.substring(0, cType.indexOf("/"));
	}

	protected String generateFileName(String fileName) {

		Logger.debug("File name = " + fileName);

		if (fileName != null) {

			StringBuilder fName = new StringBuilder();
			String ext = "";
			int mid = fileName.lastIndexOf(".");
			fName.append(fileName.substring(0, mid));

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy hhmmss");
			String fDate = sdf.format(date);
			Logger.debug("Today's Date - " + fDate);

			fName.append(" ");
			fName.append(fDate);

			Logger.debug("File name prefix = " + fName.toString());

			ext = fileName.substring(mid, fileName.length());
			Logger.debug("Extension = " + ext);

			fName.append(ext);

			Logger.debug("File name = " + fName.toString().replace(" ", "_"));

			return fName.toString().replace(" ", "_");
		}

		return fileName;
	}

}
