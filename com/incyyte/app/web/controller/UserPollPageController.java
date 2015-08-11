package com.incyyte.app.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.ImageType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.PagePhoto;
import com.incyyte.app.domain.ReportPoll;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserPollPage;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.HomeService;
import com.incyyte.app.service.PollEmailCountService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.UserPollService;
import com.incyyte.app.service.UserSettingsService;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.util.UserPollUtil;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.CommentsModel;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.Photo;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserPollPageModel;
import com.incyyte.app.web.model.UserSettingsModel;

@Controller
public class UserPollPageController {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private QuickStartService quickStartSrv;

	@Autowired
	private ContactService contactsSrv;

	@Autowired
	private HomeService homeSrv;

	@Autowired
	private UserPollService userPollSrv;

	@Autowired
	private UserSettingsService userSettingsService;

	@Autowired
	private RefData referenceData;
	@Autowired
	private PollEmailCountService pollEmailCountSrv;

	private Long IMAGE_MAX_SIZE = 2097152L;

	@RequestMapping(value = "/pollSetup.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	public String pollSetup(@ModelAttribute("pollSetupForm") UserPollPageModel userPollPageModel, ModelMap model, BindingResult result, SessionStatus status, HttpSession session) {
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		try {
			UserPollPageModel userPollPageSessionModel = (UserPollPageModel) session.getAttribute(SessionKeys.SETUP_POLL);
			Logger.debug("----Model----" + userPollPageModel);
			Logger.debug("-------Session-------" + userPollPageSessionModel);
			if (userPollPageSessionModel == null || (!(user.getId().equals(userPollPageSessionModel.getUserId())))) {
				Logger.debug("user.getId:"+user);
				UserPollPage userPollPage = userPollSrv.getUserPollPageInfo(user.getId() , AddressType.PAGE);
				userPollPageSessionModel = loadPollPage(userPollPageSessionModel,user, userPollPage);
			}
			
			List<UserPollPage> userPollPages = userPollSrv.getUserPollPagesInformation(user.getId() , AddressType.PAGE);
			Map<String, String> templates = new HashMap<String, String>();
			
			if(userPollPages != null && !userPollPages.isEmpty()){				
				for(UserPollPage userPollPage : userPollPages){
					if(userPollPage.getPollPage()!= null){
						templates.put(userPollPage.getPollPage().getPageId().toString(), userPollPage.getPollPage().getPageHeader() + " Template");				
					}
				}				
			}
			model.put("templates", templates);			
			model.put("pollSetupForm", userPollPageSessionModel);
			model.put("countries", getCountries());
			session.setAttribute(SessionKeys.SETUP_POLL, userPollPageSessionModel);
			Logger.debug("------------------$$model$$---------------" + userPollPageModel);
			Logger.debug("------------------Session---------------" + userPollPageSessionModel);

			UserSettingsModel userSettingsModel = userSettingsService.getUserSettings(user.getId());
			model.addAttribute("editSettingsForm", userSettingsModel);

			return "businessaccount/pollSetup";
		} catch (Exception e) {
			Logger.error("Exception:",e);
		}
		return "businessaccount/pollSetup";
	}

	private UserPollPageModel loadPollPage(UserPollPageModel userPollPageSessionModel,User user, UserPollPage userPollPage){
		
		Logger.debug("--------userPollPage---------------" + userPollPage);
		UserPollPageModel userPollPageModelDB = UserPollUtil.convertUserPollToModel(user.getId(), userPollPage);
		Logger.debug("------------------userPollPageModelDB---------------" + userPollPageModelDB);
		userPollPageSessionModel = userPollPageModelDB;
		Logger.debug("------------------Session--after applying-------------" + userPollPageSessionModel);
		if (!StringUtils.isBlank(userPollPageSessionModel.getLogoCdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getLogoCdnFileName());
			Logger.debug("remote"+remoteURL);
			userPollPageSessionModel.setLogoUrl(remoteURL);
		}

		if (!StringUtils.isBlank(userPollPageSessionModel.getBannerCdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getBannerCdnFileName());
			userPollPageSessionModel.setBannerUrl(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage1CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage1CdnFileName());
			userPollPageSessionModel.setImageURL1(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage2CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage2CdnFileName());
			userPollPageSessionModel.setImageURL2(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage3CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage3CdnFileName());
			userPollPageSessionModel.setImageURL3(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage4CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage4CdnFileName());
			userPollPageSessionModel.setImageURL4(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage5CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage5CdnFileName());
			userPollPageSessionModel.setImageURL5(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage6CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage6CdnFileName());
			userPollPageSessionModel.setImageURL6(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage7CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage7CdnFileName());
			userPollPageSessionModel.setImageURL7(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage8CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage8CdnFileName());
			userPollPageSessionModel.setImageURL8(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage9CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage9CdnFileName());
			userPollPageSessionModel.setImageURL9(remoteURL);
		}
		if (!StringUtils.isBlank(userPollPageSessionModel.getImage10CdnFileName())) {
			String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage10CdnFileName());
			userPollPageSessionModel.setImageURL10(remoteURL);
		}
		return userPollPageSessionModel;
	}
	
	@RequestMapping(value = "/loadTemplate.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	public String loadTemplate(@ModelAttribute("pollSetupForm") UserPollPageModel userPollPageModel, ModelMap model, BindingResult result, SessionStatus status, HttpSession session) {
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		try {
			String templateId = userPollPageModel.getTemplateId();
			UserPollPageModel userPollPageSessionModel =  new UserPollPageModel();
			List<UserPollPage> userPollPages = userPollSrv.getUserPollPagesInformation(user.getId() , AddressType.PAGE);
			
			if(!StringUtils.isEmpty(templateId)){			
				for(UserPollPage userPollPage : userPollPages){
					if(userPollPage.getPollPage() != null && userPollPage.getPollPage().getPageId().toString().equals(templateId)){
						userPollPageSessionModel = loadPollPage(new UserPollPageModel(),user, userPollPage);
						break;
					}
				}
				userPollPageSessionModel.setTemplateId(templateId);
			}else{
				userPollPageSessionModel =  (UserPollPageModel) session.getAttribute(SessionKeys.SETUP_POLL);
			}
			
			Map<String, String> templates = new HashMap<String, String>();
			
			if(userPollPages != null && !userPollPages.isEmpty()){				
				for(UserPollPage userPollPage : userPollPages){
					if(userPollPage.getPollPage()!= null){
						templates.put(userPollPage.getPollPage().getPageId().toString(), userPollPage.getPollPage().getPageHeader() + " Template");				
					}
				}				
			}
			
			model.put("templates", templates);
			model.put("pollSetupForm", userPollPageSessionModel);
			model.put("countries", getCountries());
			session.setAttribute(SessionKeys.SETUP_POLL, userPollPageSessionModel);
			Logger.debug("------------------$$model$$---------------" + userPollPageModel);
			Logger.debug("------------------Session---------------" + userPollPageSessionModel);

			UserSettingsModel userSettingsModel = userSettingsService.getUserSettings(user.getId());
			model.addAttribute("editSettingsForm", userSettingsModel);

			
			return "businessaccount/pollSetup";
		} catch (Exception e) {
			Logger.error("Exception:",e);
		}
		return "businessaccount/pollSetup";
	}
	
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadMultipartFile(HttpServletRequest request,
			@ModelAttribute("pollSetupForm") UserPollPageModel userPollPageModel, ModelMap model,
			BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		UserPollPageModel userPollPageSessionModel = (UserPollPageModel) session.getAttribute(SessionKeys.SETUP_POLL);
		//Copy Existing values into the session object and then process further information
		Logger.debug("uploadMultipartFile :: userPollPageModel" + userPollPageModel.toString());
		userPollPageSessionModel.copyValues(userPollPageModel);
		if ((userPollPageModel.getImage1().getName().equalsIgnoreCase("image1")) && StringUtils.isNotBlank(userPollPageModel.getImage1().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage1();
			Logger.debug("multipartFile"+multipartFile);
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage1().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage1().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage1(), null);
			userPollPageModel.setImageURL1(fileURL);
			userPollPageModel.setImage1CdnFileName(fileName);

			userPollPageSessionModel.setImageName1(userPollPageModel.getImage1().getOriginalFilename());
			userPollPageSessionModel.setImage1(userPollPageModel.getImage1());
			userPollPageSessionModel.setImage1CdnFileName(fileName);
			userPollPageSessionModel.setImageURL1(fileURL);
			userPollPageSessionModel.setImageLink1(userPollPageModel.getImageLink1());
			Logger.debug("-content type-" + userPollPageModel.getImage1().getContentType());
		} else if ((userPollPageModel.getLogoImage().getName().equalsIgnoreCase("logoImage")) && StringUtils.isNotBlank(userPollPageModel.getLogoImage().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getLogoImage();
			Logger.debug("In logo image multipartFile"+multipartFile);
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}

			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getLogoImage().getContentType());
			Logger.debug("containerName:"+containerName);
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getLogoImage().getOriginalFilename());
			Logger.debug("fileName:"+fileName);
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getLogoImage(), null);
			Logger.debug("fileUrl:"+fileURL);
			userPollPageModel.setLogoUrl(fileURL);
			userPollPageModel.setLogoCdnFileName(fileName);

			userPollPageSessionModel.setLogoImageName(userPollPageModel.getLogoImage().getOriginalFilename());
			userPollPageSessionModel.setLogoImage(userPollPageModel.getLogoImage());
			userPollPageSessionModel.setLogoCdnFileName(fileName);
			userPollPageSessionModel.setLogoUrl(fileURL);
			userPollPageSessionModel.setLogoLink(userPollPageModel.getLogoLink());
			Logger.debug("------------------uploaded filetype is--logo------ " + userPollPageModel);
		} else if ((userPollPageModel.getBannerImage().getName().equalsIgnoreCase("bannerImage")) && StringUtils.isNotBlank(userPollPageModel.getBannerImage().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getBannerImage();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getBannerImage().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getBannerImage().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getBannerImage(), null);
			userPollPageModel.setBannerUrl(fileURL);
			userPollPageModel.setBannerCdnFileName(fileName);

			userPollPageSessionModel.setBannerImageName(userPollPageModel.getBannerImage().getOriginalFilename());
			userPollPageSessionModel.setBannerImage(userPollPageModel.getBannerImage());
			userPollPageSessionModel.setBannerCdnFileName(fileName);
			userPollPageSessionModel.setBannerUrl(fileURL);
			userPollPageSessionModel.setBannerLink(userPollPageModel.getBannerLink());
		} else if ((userPollPageModel.getImage2().getName().equalsIgnoreCase("image2")) && StringUtils.isNotBlank(userPollPageModel.getImage2().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage2();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage2().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage2().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage2(), null);
			userPollPageModel.setImageURL2(fileURL);
			userPollPageModel.setImage2CdnFileName(fileName);
			userPollPageSessionModel.setImageName2(userPollPageModel.getImage2().getOriginalFilename());
			userPollPageSessionModel.setImage2(userPollPageModel.getImage2());
			userPollPageSessionModel.setImage2CdnFileName(fileName);
			userPollPageSessionModel.setImageURL2(fileURL);
			userPollPageSessionModel.setImageLink2(userPollPageModel.getImageLink2());
		} else if ((userPollPageModel.getImage3().getName().equalsIgnoreCase("image3")) && StringUtils.isNotBlank(userPollPageModel.getImage3().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage3();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage3().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage3().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage3(), null);
			userPollPageModel.setImageURL3(fileURL);
			userPollPageModel.setImage3CdnFileName(fileName);

			userPollPageSessionModel.setImageName3(userPollPageModel.getImage3().getOriginalFilename());
			userPollPageSessionModel.setImage3(userPollPageModel.getImage3());
			userPollPageSessionModel.setImage3CdnFileName(fileName);
			userPollPageSessionModel.setImageURL3(fileURL);
			userPollPageSessionModel.setImageLink3(userPollPageModel.getImageLink3());
		} else if ((userPollPageModel.getImage4().getName().equalsIgnoreCase("image4")) && StringUtils.isNotBlank(userPollPageModel.getImage4().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage4();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage4().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage4().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage4(), null);
			userPollPageModel.setImageURL4(fileURL);
			userPollPageModel.setImage4CdnFileName(fileName);

			userPollPageSessionModel.setImageName4(userPollPageModel.getImage4().getOriginalFilename());
			userPollPageSessionModel.setImage4(userPollPageModel.getImage4());
			userPollPageSessionModel.setImage4CdnFileName(fileName);
			userPollPageSessionModel.setImageURL4(fileURL);
			userPollPageSessionModel.setImageLink4(userPollPageModel.getImageLink4());
		} else if ((userPollPageModel.getImage5().getName().equalsIgnoreCase("image5")) && StringUtils.isNotBlank(userPollPageModel.getImage5().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage5();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage5().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage5().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage5(), null);
			userPollPageModel.setImageURL5(fileURL);
			userPollPageModel.setImage5CdnFileName(fileName);

			userPollPageSessionModel.setImageName5(userPollPageModel.getImage5().getOriginalFilename());
			userPollPageSessionModel.setImage5(userPollPageModel.getImage5());
			userPollPageSessionModel.setImage5CdnFileName(fileName);
			userPollPageSessionModel.setImageURL5(fileURL);
			userPollPageSessionModel.setImageLink5(userPollPageModel.getImageLink5());
		} else if ((userPollPageModel.getImage6().getName().equalsIgnoreCase("image6")) && StringUtils.isNotBlank(userPollPageModel.getImage6().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage6();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage6().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage6().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage6(), null);
			userPollPageModel.setImageURL6(fileURL);
			userPollPageModel.setImage6CdnFileName(fileName);

			userPollPageSessionModel.setImageName6(userPollPageModel.getImage6().getOriginalFilename());
			userPollPageSessionModel.setImage6(userPollPageModel.getImage6());
			userPollPageSessionModel.setImage6CdnFileName(fileName);
			userPollPageSessionModel.setImageURL6(fileURL);
			userPollPageSessionModel.setImageLink6(userPollPageModel.getImageLink6());
		} else if ((userPollPageModel.getImage7().getName().equalsIgnoreCase("image7")) && StringUtils.isNotBlank(userPollPageModel.getImage7().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage7();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage7().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage7().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage7(), null);
			userPollPageModel.setImageURL7(fileURL);
			userPollPageModel.setImage7CdnFileName(fileName);

			userPollPageSessionModel.setImageName7(userPollPageModel.getImage7().getOriginalFilename());
			userPollPageSessionModel.setImage7(userPollPageModel.getImage7());
			userPollPageSessionModel.setImage7CdnFileName(fileName);
			userPollPageSessionModel.setImageURL7(fileURL);
			userPollPageSessionModel.setImageLink7(userPollPageModel.getImageLink7());

		} else if ((userPollPageModel.getImage8().getName().equalsIgnoreCase("image8")) && StringUtils.isNotBlank(userPollPageModel.getImage8().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage8();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage8().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage8().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage8(), null);
			userPollPageModel.setImageURL8(fileURL);
			userPollPageModel.setImage8CdnFileName(fileName);

			userPollPageSessionModel.setImageName8(userPollPageModel.getImage8().getOriginalFilename());
			userPollPageSessionModel.setImage8(userPollPageModel.getImage8());
			userPollPageSessionModel.setImage8CdnFileName(fileName);
			userPollPageSessionModel.setImageURL8(fileURL);
			userPollPageSessionModel.setImageLink8(userPollPageModel.getImageLink8());
		} else if ((userPollPageModel.getImage9().getName().equalsIgnoreCase("image9")) && StringUtils.isNotBlank(userPollPageModel.getImage9().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage9();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage9().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage9().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage9(), null);
			userPollPageModel.setImageURL9(fileURL);
			userPollPageModel.setImage9CdnFileName(fileName);

			userPollPageSessionModel.setImageName9(userPollPageModel.getImage9().getOriginalFilename());
			userPollPageSessionModel.setImage9(userPollPageModel.getImage9());
			userPollPageSessionModel.setImage9CdnFileName(fileName);
			userPollPageSessionModel.setImageURL9(fileURL);
			userPollPageSessionModel.setImageLink9(userPollPageModel.getImageLink9());
		} else if ((userPollPageModel.getImage10().getName().equalsIgnoreCase("image10")) && StringUtils.isNotBlank(userPollPageModel.getImage10().getOriginalFilename())) {
			CommonsMultipartFile multipartFile = userPollPageModel.getImage10();
			if (multipartFile != null && multipartFile.getSize() == 0 || multipartFile.getSize() > IMAGE_MAX_SIZE) {
				return "failure";
			}
			String containerName = FileManagementUtil.getContainerType(userPollPageModel.getImage10().getContentType());
			String fileName = FileManagementUtil.generateFileName(userPollPageModel.getImage10().getOriginalFilename());
			String fileURL = FileManagementUtil.uploadFile(containerName, fileName, userPollPageModel.getImage10(), null);
			userPollPageModel.setImageURL10(fileURL);
			userPollPageModel.setImage10CdnFileName(fileName);

			userPollPageSessionModel.setImageName10(userPollPageModel.getImage10().getOriginalFilename());
			userPollPageSessionModel.setImage10(userPollPageModel.getImage10());
			userPollPageSessionModel.setImage10CdnFileName(fileName);
			userPollPageSessionModel.setImageURL10(fileURL);
			userPollPageSessionModel.setImageLink10(userPollPageModel.getImageLink10());
		} else if (StringUtils.isNotBlank(userPollPageModel.getSearchedFileNamePollPage())) {
			Logger.debug("userPollPageModel-dd " + userPollPageModel);
			if(StringUtils.isNotBlank(userPollPageModel.getLogoImageName()) && StringUtils.isBlank(userPollPageModel.getLogoCdnFileName())) {
				Logger.debug("userPollPageModel" +userPollPageModel);
				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setLogoImageName(userPollPageModel.getSearchedFileNamePollPage());
				userPollPageModel.setLogoUrl(uploadURL);
				userPollPageModel.setLogoCdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setLogoImageName(userPollPageModel.getSearchedFileNamePollPage());
				userPollPageSessionModel.setLogoUrl(uploadURL);
				userPollPageSessionModel.setLogoCdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setLogoLink(userPollPageModel.getLogoLink());

			} if(StringUtils.isNotBlank(userPollPageModel.getBannerImageName()) && StringUtils.isBlank(userPollPageModel.getBannerCdnFileName())) {
				Logger.debug("userPollPageModel" +userPollPageModel);

				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setBannerImageName(userPollPageModel.getBannerImageName());
				userPollPageModel.setBannerUrl(uploadURL);
				userPollPageModel.setBannerCdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setBannerImageName(userPollPageModel.getBannerImageName());
				userPollPageSessionModel.setBannerUrl(uploadURL);
				userPollPageSessionModel.setBannerCdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setBannerLink(userPollPageModel.getBannerLink());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName1()) && StringUtils.isBlank(userPollPageModel.getImage1CdnFileName())){
				Logger.debug("userPollPageModel" + userPollPageModel);
				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName1(userPollPageModel.getImageName1());
				userPollPageModel.setImageURL1(uploadURL);
				userPollPageModel.setImage1CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName1(userPollPageModel.getImageName1());
				userPollPageSessionModel.setImageURL1(uploadURL);
				userPollPageSessionModel.setImage1CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink1(userPollPageModel.getImageLink1());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName2()) && StringUtils.isBlank(userPollPageModel.getImage2CdnFileName())){
				Logger.debug("Image 2 name::"+userPollPageModel);	
				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName2(searchedImage.getFileName());
				userPollPageModel.setImageURL2(uploadURL);
				userPollPageModel.setImage2CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName2(searchedImage.getFileName());
				userPollPageSessionModel.setImageURL2(uploadURL);
				userPollPageSessionModel.setImage2CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink2(userPollPageModel.getImageLink2());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName3()) && StringUtils.isBlank(userPollPageModel.getImage3CdnFileName())){
				Logger.debug("URL:" + userPollPageModel.getSearchedFileURLPollPage());
				Logger.debug("filename:" + userPollPageModel.getSearchedFileNamePollPage());

				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName3(searchedImage.getFileName());
				userPollPageModel.setImageURL3(uploadURL);
				userPollPageModel.setImage3CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName3(searchedImage.getFileName());
				userPollPageSessionModel.setImageURL3(uploadURL);
				userPollPageSessionModel.setImage3CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink3(userPollPageModel.getImageLink3());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName4()) && StringUtils.isBlank(userPollPageModel.getImage4CdnFileName())){
				Logger.debug("URL:" + userPollPageModel.getSearchedFileURLPollPage());
				Logger.debug("filename:" + userPollPageModel.getSearchedFileNamePollPage());

				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName4(searchedImage.getFileName());
				userPollPageModel.setImageURL4(uploadURL);
				userPollPageModel.setImage4CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName4(searchedImage.getFileName());
				userPollPageSessionModel.setImageURL4(uploadURL);
				userPollPageSessionModel.setImage4CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink4(userPollPageModel.getImageLink4());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName5()) && StringUtils.isBlank(userPollPageModel.getImage5CdnFileName())){
				Logger.debug("URL:" + userPollPageModel.getSearchedFileURLPollPage());
				Logger.debug("filename:" + userPollPageModel.getSearchedFileNamePollPage());

				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName5(searchedImage.getFileName());
				userPollPageModel.setImageURL5(uploadURL);
				userPollPageModel.setImage5CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName5(searchedImage.getFileName());
				userPollPageSessionModel.setImageURL5(uploadURL);
				userPollPageSessionModel.setImage5CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink5(userPollPageModel.getImageLink5());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName6()) && StringUtils.isBlank(userPollPageModel.getImage6CdnFileName())){
				Logger.debug("URL:" + userPollPageModel.getSearchedFileURLPollPage());
				Logger.debug("filename:" + userPollPageModel.getSearchedFileNamePollPage());

				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName6(searchedImage.getFileName());
				userPollPageModel.setImageURL6(uploadURL);
				userPollPageModel.setImage6CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName6(searchedImage.getFileName());
				userPollPageSessionModel.setImageURL6(uploadURL);
				userPollPageSessionModel.setImage6CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink6(userPollPageModel.getImageLink6());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName7()) && StringUtils.isBlank(userPollPageModel.getImage7CdnFileName())){
				Logger.debug("URL:" + userPollPageModel.getSearchedFileURLPollPage());
				Logger.debug("filename:" + userPollPageModel.getSearchedFileNamePollPage());

				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName7(searchedImage.getFileName());
				userPollPageModel.setImageURL7(uploadURL);
				userPollPageModel.setImage7CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName7(searchedImage.getFileName());
				userPollPageSessionModel.setImageURL7(uploadURL);
				userPollPageSessionModel.setImage7CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink7(userPollPageModel.getImageLink7());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName8()) && StringUtils.isBlank(userPollPageModel.getImage8CdnFileName())){
				Logger.debug("URL:" + userPollPageModel.getSearchedFileURLPollPage());
				Logger.debug("filename:" + userPollPageModel.getSearchedFileNamePollPage());

				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName8(searchedImage.getFileName());
				userPollPageModel.setImageURL8(uploadURL);
				userPollPageModel.setImage8CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName8(searchedImage.getFileName());
				userPollPageSessionModel.setImageURL8(uploadURL);
				userPollPageSessionModel.setImage8CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink8(userPollPageModel.getImageLink8());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName9()) && StringUtils.isBlank(userPollPageModel.getImage9CdnFileName())){
				Logger.debug("URL:" + userPollPageModel.getSearchedFileURLPollPage());
				Logger.debug("filename:" + userPollPageModel.getSearchedFileNamePollPage());

				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName9(searchedImage.getFileName());
				userPollPageModel.setImageURL9(uploadURL);
				userPollPageModel.setImage9CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName9(searchedImage.getFileName());
				userPollPageSessionModel.setImageURL9(uploadURL);
				userPollPageSessionModel.setImage9CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink9(userPollPageModel.getImageLink9());

			} if(StringUtils.isNotBlank(userPollPageModel.getImageName10()) && StringUtils.isBlank(userPollPageModel.getImage10CdnFileName())){
				Logger.debug("URL:" + userPollPageModel.getSearchedFileURLPollPage());
				Logger.debug("filename:" + userPollPageModel.getSearchedFileNamePollPage());

				//Downloads the file and keeps in temp location
				URL url = new URL(userPollPageModel.getSearchedFileURLPollPage());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userPollPageModel.getSearchedFileNamePollPage();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				//Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userPollPageModel.getSearchedFileURLPollPage());
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
				Logger.debug("userPollPageModel- " + userPollPageModel);

				userPollPageModel.setImageName10(searchedImage.getFileName());
				userPollPageModel.setImageURL10(uploadURL);
				userPollPageModel.setImage10CdnFileName(remoteFile);
				userPollPageModel.setUploadedFileType(fileType);

				userPollPageSessionModel.setImageName10(searchedImage.getFileName());
				userPollPageSessionModel.setImageURL10(uploadURL);
				userPollPageSessionModel.setImage10CdnFileName(remoteFile);
				userPollPageSessionModel.setUploadedFileType(fileType);
				userPollPageSessionModel.setImageLink10(userPollPageModel.getImageLink10());

			}
		}
		session.setAttribute(SessionKeys.SETUP_POLL, userPollPageSessionModel);
		Logger.debug("----------after uploading session object--------" + userPollPageSessionModel);
		Logger.debug("------------------uploadede filetype is-------- " + userPollPageModel.getUploadedFileType());
		//return "/pollSetup";
		return "redirect:displayPollSetup.cyt";
	}

	@RequestMapping(value = "/deleteUploadedImages", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUploadedImages(HttpServletRequest request,
			@ModelAttribute("pollSetupForm") UserPollPageModel userPollPageModel,
			BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		Logger.debug("*************userPollPageModel*****************" + userPollPageModel);
		UserPollPageModel userPollPageSessionModel = (UserPollPageModel) session.getAttribute(SessionKeys.SETUP_POLL);
		userPollPageSessionModel.copyValues(userPollPageModel);
		UserPollPage userPollPage = UserPollUtil.convertUserPollFromModel(userPollPageSessionModel);
		PagePhoto deletedPagePhoto = null;
		Logger.debug("*************userPollPageSessionModel*****************" + userPollPageSessionModel);
		try {
			FileManagementUtil.deleteRemoteFile("image", userPollPageModel.getUploadedFileName());

			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.LOGO.toString())) {
				userPollPageSessionModel.setLogoUrl(null);
				userPollPageSessionModel.setLogoCdnFileName(null);
				userPollPageSessionModel.setLogoImageName(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.LOGO);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.BANNER.toString())) {
				userPollPageSessionModel.setBannerUrl(null);
				userPollPageSessionModel.setBannerCdnFileName(null);
				userPollPageSessionModel.setBannerImageName(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.BANNER);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE1.toString())) {
				userPollPageSessionModel.setImageURL1(null);
				userPollPageSessionModel.setImage1CdnFileName(null);
				userPollPageSessionModel.setImageName1(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE1);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE2.toString())) {
				userPollPageSessionModel.setImageURL2(null);
				userPollPageSessionModel.setImage2CdnFileName(null);
				userPollPageSessionModel.setImageName2(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE2);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE3.toString())) {
				userPollPageSessionModel.setImageURL3(null);
				userPollPageSessionModel.setImage3CdnFileName(null);
				userPollPageSessionModel.setImageName3(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE3);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE4.toString())) {
				userPollPageSessionModel.setImageURL4(null);
				userPollPageSessionModel.setImage4CdnFileName(null);
				userPollPageSessionModel.setImageName4(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE4);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE5.toString())) {
				userPollPageSessionModel.setImageURL5(null);
				userPollPageSessionModel.setImage5CdnFileName(null);
				userPollPageSessionModel.setImageName5(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE5);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE6.toString())) {
				userPollPageSessionModel.setImageURL6(null);
				userPollPageSessionModel.setImage6CdnFileName(null);
				userPollPageSessionModel.setImageName6(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE6);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE7.toString())) {
				userPollPageSessionModel.setImageURL7(null);
				userPollPageSessionModel.setImage7CdnFileName(null);
				userPollPageSessionModel.setImageName7(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE7);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE8.toString())) {
				userPollPageSessionModel.setImageURL8(null);
				userPollPageSessionModel.setImage8CdnFileName(null);
				userPollPageSessionModel.setImageName8(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE8);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE9.toString())) {
				userPollPageSessionModel.setImageURL9(null);
				userPollPageSessionModel.setImage9CdnFileName(null);
				userPollPageSessionModel.setImageName9(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE9);
			}
			if (StringUtils.equalsIgnoreCase(userPollPageModel.getUploadedFileType(), ImageType.IMAGE10.toString())) {
				userPollPageSessionModel.setImageURL10(null);
				userPollPageSessionModel.setImage10CdnFileName(null);
				userPollPageSessionModel.setImageName10(null);
				deletedPagePhoto = UserPollUtil.getPagePhoto(userPollPage, ImageType.IMAGE10);
			}
			Logger.debug("deleteUploadedImages::deletedPagePhoto" + deletedPagePhoto);
			userPollSrv.deletePollPagePhoto(deletedPagePhoto);
			session.setAttribute(SessionKeys.SETUP_POLL, userPollPageSessionModel);
			return "businessaccount/pollSetup";
		} catch (Exception e) {
			Logger.error("Delete Uploaded image exception:", e);
			return "failure";
		}
	}

	@RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
	public String saveChanges(HttpServletRequest request,
			@ModelAttribute("pollSetupForm") UserPollPageModel userPollPageModel,
			BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		savePollInfo(userPollPageModel, session);
		return "redirect:pollSetup.cyt";
	}

	private void savePollInfo(UserPollPageModel userPollPageModel, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		UserPollPageModel userPollPageSessionModel = (UserPollPageModel) session.getAttribute(SessionKeys.SETUP_POLL);
		Logger.debug("---------------in Saving---userPollPageSessionModel-----" + userPollPageSessionModel);
		Logger.debug("---------------in Saving---UserPollPageModel------------" + userPollPageModel);
		try {
			Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name -> " + userPollPageSessionModel);
			userPollPageModel.setUserId(user.getId());
			userPollPageModel.setAddressType(String.valueOf(AddressType.PAGE));
			userPollPageSessionModel.setPageHeader(userPollPageModel.getPageHeader());
			userPollPageSessionModel.setUserId(user.getId());
			userPollPageSessionModel.setAddressType(String.valueOf(AddressType.PAGE));
			userPollPageSessionModel.setContactEmail(userPollPageModel.getContactEmail());
			userPollPageSessionModel.setWebsiteUrl(userPollPageModel.getWebsiteUrl());
			userPollPageSessionModel.setContactPhone1(userPollPageModel.getContactPhone1());
			userPollPageSessionModel.setContactPhone2(userPollPageModel.getContactPhone2());
			userPollPageSessionModel.setInformation(userPollPageModel.getInformation());
			userPollPageSessionModel.setAddress1(userPollPageModel.getAddress1());
			userPollPageSessionModel.setAddress2(userPollPageModel.getAddress2());
			userPollPageSessionModel.setCity(userPollPageModel.getCity());
			userPollPageSessionModel.setPostcode(userPollPageModel.getPostcode());
			userPollPageSessionModel.setCountry(userPollPageModel.getCountry());
			Logger.debug("Before converting to Poll Object");
			Logger.debug("-session---userPollPageSessionModel--" + userPollPageSessionModel);
			Logger.debug("--model---UserPollPageModel---" + userPollPageModel);
			UserPollPage userPollPage = UserPollUtil.convertUserPollFromModel(userPollPageSessionModel);
			Logger.debug("After converting to Poll Object");
			Logger.debug("----in Saving---userPollPgae-" + userPollPage.toString());
			Logger.debug("setPageId--" + userPollPage.toString());
			userPollSrv.saveUserPollPageInfo(userPollPage);
			Logger.debug("PollPage Id::" + userPollPageModel.getPageId());
			Logger.debug("PollPage Id::" + userPollPage.getPollPage().getPageId());
			if (userPollPage.getPollPage().getPageId() != null) {
				userPollPageSessionModel.setPageId(userPollPage.getPollPage().getPageId());
				userPollPageSessionModel.setTemplateId(userPollPage.getPollPage().getPageId().toString());
			}
			Logger.debug("-After Saving---userPollPgae--" + userPollPage.toString());
		} catch (DuplicateKeyException dke) {
			Logger.debug("Duplicate value of pageHeader. "+userPollPageModel);
			throw dke;
		}catch (Exception e) {
			Logger.error("%%%%%%%%%%%%%%% uploadMultipartFile - Exception:", e);
			throw e;
		}
	}

	@RequestMapping(value = "/cancelSetup.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	public String cancelSetup(@ModelAttribute("pollSetupForm") UserPollPageModel userPollPageModel, ModelMap model, BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		session.setAttribute(SessionKeys.SETUP_POLL, null);
		return "redirect:dashincomming.cyt";
	}

	
	@RequestMapping(value = "/newPageTemplate.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	public String newTemplate(@ModelAttribute("pollSetupForm") UserPollPageModel userPollPageModel, ModelMap model, BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		try {
			
			List<UserPollPage> userPollPages = userPollSrv.getUserPollPagesInformation(user.getId() , AddressType.PAGE);
			Map<String, String> templates = new HashMap<String, String>();
			
			UserPollPageModel pollPageModel = new UserPollPageModel();
			pollPageModel.setUserId(user.getId());
			templates.put("", "New Template");
			if(userPollPages != null && !userPollPages.isEmpty()){				
				for(UserPollPage userPollPage : userPollPages){
					if(userPollPage.getPollPage()!= null){
						templates.put(userPollPage.getPollPage().getPageId().toString(), userPollPage.getPollPage().getPageHeader() + " Template");				
					}
				}
				
				UserPollPage userPollPage = userPollPages.get(0);
				if (userPollPage.getPollPage() != null) {
					//pollPageModel.setPageId(userPollPage.getPollPage().getPageId());
					pollPageModel.setUserId(userPollPage.getPollPage().getUserId());
					//pollPageModel.setPageHeader(userPollPage.getPollPage().getPageHeader());
					pollPageModel.setContactEmail(userPollPage.getPollPage().getContactEmail());
					pollPageModel.setWebsiteUrl(userPollPage.getPollPage().getWebsiteUrl());
					pollPageModel.setContactPhone1(userPollPage.getPollPage().getContactPhone1());
					pollPageModel.setContactPhone2(userPollPage.getPollPage().getContactPhone2());
					pollPageModel.setInformation(userPollPage.getPollPage().getInformation());
				}
				if (userPollPage.getUserAddress() != null) { 
					pollPageModel.setUserId(userPollPage.getUserAddress().getUserId());
					pollPageModel.setAddressType(userPollPage.getUserAddress().getAddressType().toString());
					pollPageModel.setAddress1(userPollPage.getUserAddress().getAddress1());
					pollPageModel.setAddress2(userPollPage.getUserAddress().getAddress2());
					pollPageModel.setCity(userPollPage.getUserAddress().getCity());
					pollPageModel.setPostcode(userPollPage.getUserAddress().getPostcode());
					pollPageModel.setCountry(userPollPage.getUserAddress().getCountry());
				}
			}
			pollPageModel.setTemplates(templates);			
			session.setAttribute(SessionKeys.SETUP_POLL, pollPageModel);		

			return "redirect:displayPollSetup.cyt";
		} catch (Exception e) {
			Logger.error("Exception:",e);
		}
		return "redirect:displayPollSetup.cyt";
	}
	
	@RequestMapping(value = "/displayPollSetup.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	public String displayPollSetup(@ModelAttribute("pollSetupForm") UserPollPageModel userPollPageModel, ModelMap model, BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		
		UserPollPageModel pollPageModel = (UserPollPageModel) session.getAttribute(SessionKeys.SETUP_POLL);
		
		if(pollPageModel.getTemplates() != null &&  !pollPageModel.getTemplates().isEmpty()){
			model.put("templates", pollPageModel.getTemplates());
		}
		else{
			List<UserPollPage> userPollPages = userPollSrv.getUserPollPagesInformation(user.getId() , AddressType.PAGE);
			Map<String, String> templates = new HashMap<String, String>();
			for(UserPollPage userPollPage : userPollPages){
				if(userPollPage.getPollPage()!= null){
					templates.put(userPollPage.getPollPage().getPageId().toString(), userPollPage.getPollPage().getPageHeader() + " Template");				
				}
			}
			pollPageModel.setTemplates(templates);
			model.put("templates", pollPageModel.getTemplates());
		}
		
		model.put("pollSetupForm", pollPageModel);
		model.put("countries", getCountries());
		
		UserSettingsModel userSettingsModel = userSettingsService.getUserSettings(user.getId());
		model.addAttribute("editSettingsForm", userSettingsModel);
		
		return "businessaccount/pollSetup";
	}
	
	@RequestMapping(value = "/pollPreview.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	public String pollPreview(@ModelAttribute("pollSetupForm") UserPollPageModel userPollPageModel, ModelMap model, BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		try {
			//Save Poll Information
			savePollInfo(userPollPageModel, session);
			//Start viewing the information
			UserPollPageModel userPollPageSessionModel = (UserPollPageModel) session.getAttribute(SessionKeys.SETUP_POLL);
			Logger.debug("--------------------Model---------------" + userPollPageModel);
			Logger.debug("------------------Session---------------" + userPollPageSessionModel);
			if (userPollPageSessionModel == null) {
				UserPollPage userPollPage = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.PAGE);
				Logger.debug("------------------userPollPage---------------" + userPollPage);
				UserPollPageModel userPollPageModelDB = UserPollUtil.convertUserPollToModel(user.getId(), userPollPage);
				Logger.debug("------------------userPollPageModelDB---------------" + userPollPageModelDB);
				userPollPageSessionModel = userPollPageModelDB;
				Logger.debug("------------------Session--after applying-------------" + userPollPageSessionModel);
				if (!StringUtils.isBlank(userPollPageSessionModel.getLogoCdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getLogoCdnFileName());
					userPollPageSessionModel.setLogoUrl(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getBannerCdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getBannerCdnFileName());
					userPollPageSessionModel.setBannerUrl(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage1CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage1CdnFileName());
					userPollPageSessionModel.setImageURL1(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage2CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage2CdnFileName());
					userPollPageSessionModel.setImageURL2(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage3CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage3CdnFileName());
					userPollPageSessionModel.setImageURL3(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage4CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage4CdnFileName());
					userPollPageSessionModel.setImageURL4(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage5CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage5CdnFileName());
					userPollPageSessionModel.setImageURL5(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage6CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage6CdnFileName());
					userPollPageSessionModel.setImageURL6(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage7CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage7CdnFileName());
					userPollPageSessionModel.setImageURL7(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage8CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage8CdnFileName());
					userPollPageSessionModel.setImageURL8(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage9CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage9CdnFileName());
					userPollPageSessionModel.setImageURL9(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage10CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage10CdnFileName());
					userPollPageSessionModel.setImageURL10(remoteURL);
				}
			}
			model.put("pollSetupForm", userPollPageSessionModel);
			session.setAttribute(SessionKeys.SETUP_POLL, userPollPageSessionModel);
			Logger.debug("--------------------Model---------------" + userPollPageModel);
			Logger.debug("------------------Session---------------" + userPollPageSessionModel);
		} catch (DuplicateKeyException dke) {
			Logger.debug("Duplicate value of pageHeader. "+userPollPageModel);
			model.put("countries", getCountries());
			model.put("pageHeaderError","Page header is already used. Provide a unique page header.");
			UserSettingsModel userSettingsModel = userSettingsService.getUserSettings(user.getId());
			model.addAttribute("editSettingsForm", userSettingsModel);
			return "businessaccount/pollSetup";
		}catch (Exception e) {
			Logger.error("pollPreview:Exception:", e);
			throw e;
		}
		return "businessaccount/pollPreview";
	}

	@RequestMapping(value = "/viewUserPoll.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	public String viewUserPoll(ModelMap model, BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		try {
			UserPollPageModel userPollPageSessionModel = (UserPollPageModel) session.getAttribute(SessionKeys.SETUP_POLL);
			Logger.debug("------------------Session---------------" + userPollPageSessionModel);
			if (userPollPageSessionModel == null) {
				UserPollPage userPollPage = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.PAGE);
				Logger.debug("------------------userPollPage---------------" + userPollPage);
				UserPollPageModel userPollPageModelDB = UserPollUtil.convertUserPollToModel(user.getId(), userPollPage);
				Logger.debug("------------------userPollPageModelDB---------------" + userPollPageModelDB);
				userPollPageSessionModel = userPollPageModelDB;
				Logger.debug("------------------Session--after applying-------------" + userPollPageSessionModel);

				if (!StringUtils.isBlank(userPollPageSessionModel.getLogoCdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getLogoCdnFileName());
					userPollPageSessionModel.setLogoUrl(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getBannerCdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getBannerCdnFileName());
					userPollPageSessionModel.setBannerUrl(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage1CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage1CdnFileName());
					userPollPageSessionModel.setImageURL1(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage2CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage2CdnFileName());
					userPollPageSessionModel.setImageURL2(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage3CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage3CdnFileName());
					userPollPageSessionModel.setImageURL3(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage4CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage4CdnFileName());
					userPollPageSessionModel.setImageURL4(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage5CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage5CdnFileName());
					userPollPageSessionModel.setImageURL5(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage6CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage6CdnFileName());
					userPollPageSessionModel.setImageURL6(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage7CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage7CdnFileName());
					userPollPageSessionModel.setImageURL7(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage8CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage8CdnFileName());
					userPollPageSessionModel.setImageURL8(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage9CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage9CdnFileName());
					userPollPageSessionModel.setImageURL9(remoteURL);
				}
				if (!StringUtils.isBlank(userPollPageSessionModel.getImage10CdnFileName())) {
					String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageSessionModel.getImage10CdnFileName());
					userPollPageSessionModel.setImageURL10(remoteURL);
				}
			}
			session.setAttribute(SessionKeys.SETUP_POLL, userPollPageSessionModel);
			Logger.debug("------------------Session---------------" + userPollPageSessionModel);
		} catch (Exception e) {
			Logger.error("viewUserPoll:Exception:", e);
			throw e;
		}

		model.put("CommentsModel", new CommentsModel());
		return "businessaccount/viewUserPoll";
	}

	@RequestMapping(value = "/addPollComments.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String addComments(@ModelAttribute("CommentsModel") CommentsModel cmntModel, HttpSession session, HttpServletRequest request) {
		Logger.debug(" addComments :  addComments"+cmntModel.getComment());
		try {	
			User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
			if (user != null && user.getId() != null) {				
				Logger.debug("commentsModel::" + cmntModel);
				homeSrv.addComments(cmntModel, user);
				session.setAttribute(SessionKeys.LOGIN_USER, user);
				return "success";
			}
		} catch (Exception e) {
			Logger.error("Exception:", e);
		}
		return "failure";
	}

	@RequestMapping(value = "/emailSharedPoll.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String emailSharedPoll(@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, HttpSession session, HttpServletRequest request) {
		Logger.debug("Inside the emailSharedPoll ");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		if (user != null) {
			Logger.debug("%%%%%%%%%%% Email - " + incyyteModel.getEmailArr());
			if (incyyteModel.getEmailArr() != null) {
				try {					
					Logger.debug("user:::" + user);
					String emailArr = incyyteModel.getEmailArr();
					Logger.debug("emailArr::" + emailArr);
					List<Contact> contacts = getContacts(emailArr, user.getId());
					Logger.debug("contacts::" + contacts);

					InCyyteChart chart = quickStartSrv.getPostedInCyyteResponse(incyyteModel.getIncyyteCode());

					//fetching the shared poll info from database 
					String sendType = "post";
					List<InCyyte> myPollPageContacts =  userPollSrv.getMyPollPageShareContacts(user,sendType);
					Logger.debug("myPollPageContacts::::::::::::::::"+myPollPageContacts);
					Logger.debug("myPollPageContacts::::::::::::::::"+myPollPageContacts.size());

					InCyyte incyyte = chart.getIncyyte();
					incyyte.setContacts(contacts);
					Logger.debug("incyyte::" + incyyte);
					//TODO:: Track the  poll Emails Here and Insert them.
					ConfigManager icfg = ConfigManager.getInstance();
					int oldCountOfPollemails = pollEmailCountSrv.getPollEmailCountInfo(user);
					Logger.debug("oldCountOfPollemails:::"+oldCountOfPollemails);
					incyyte.setPollEmailCount(oldCountOfPollemails);
					List<String>  validEmails = pollEmailCountSrv.validatePollEmails(contacts, incyyte.isAnonymity(), user);
					Logger.debug("validEmails:::"+validEmails);
					int count = validEmails.size();
					int cntInclPresentEmails  = oldCountOfPollemails + count;
					Logger.debug("cntInclPresentEmails:: "+cntInclPresentEmails);
					if(oldCountOfPollemails == 0 || StringUtils.isBlank(String.valueOf(oldCountOfPollemails))){
						Logger.debug("user sending emails first time::");
						pollEmailCountSrv.storePollEmailCount(user, incyyte, count);
						int newCount = pollEmailCountSrv.getPollEmailCountInfo(user);
						Logger.debug("first email count:1::"+newCount);
						quickStartSrv.shareUserPollPageQues(user, incyyte, contacts);
						return "success";
					} else if (oldCountOfPollemails != 0 && (cntInclPresentEmails > Integer.valueOf(icfg.getProperty(ConfigProperties.POLL_EMAIL_COUNT_LIMIT))) ){
						Logger.debug("crossed the limit of emails:::");
						return "pollEmailCountError";	
					}else if(cntInclPresentEmails <= Integer.valueOf(icfg.getProperty(ConfigProperties.POLL_EMAIL_COUNT_LIMIT)) ){
						Logger.debug("secxod attempt or more :::");
						pollEmailCountSrv.storePollEmailCount(user, incyyte, count);
						int newCount = pollEmailCountSrv.getPollEmailCountInfo(user);
						Logger.debug("count :3::"+newCount);

						quickStartSrv.shareUserPollPageQues(user, incyyte, contacts);
						return "success";
					}

				}catch (Exception e) {
					Logger.error("Exception: ", e);
					return "ERROR:" + e.getMessage();
				}
			}
		} else {
			return "redirect:welcome.cyt";
		}
		return null;
	}

	private List<Contact> getContacts(String arr, Long userId) {
		List<Contact> contacts = new ArrayList<Contact>();
		for (String email : arr.split("[\\s,;]+")) {
			Logger.debug("%%%%%%%% contact  email -> " + email + "  UserID: " + userId);
			try {
				UserContactModel model = contactsSrv.contactOfUser(email, userId);
				Logger.debug("model "+model);

				Contact contact = new Contact();
				contact.setEmail(email);
				contact.setUserId(userId);
				if (model != null && model.getContactid() != 0) {
					contact.setStatus(model.getStatus());
					contact.setBlocked(model.getBlocked());
					contact.setContactId(model.getContactid());

				} else {
					String invitationCode = quickStartSrv.getInvitationCode();
					contact.setInvitationid(invitationCode);
					contact.setStatus(quickStartSrv.verifyContactalreadyMember(contact.getEmail()) ? "M" : "NM");
					contact.setAccept_inv("N");
					contact.setBlocked("N");
					contact.setSent_invite("N");
					long contactId = contactsSrv.addContact(getUserContactModel(contact), userId);
					Logger.debug("contact id" + contactId);

					contact.setId(contactId);
					contact.setContactId(Long.valueOf(contactId));
				}
				contacts.add(contact);
			} catch (Exception e) {
				Logger.error("Exception:",e);
			}
		}
		return contacts;
	}

	private UserContactModel getUserContactModel(Contact contact) {
		UserContactModel model = new UserContactModel();
		model.setAccept_inv(contact.getAccept_inv());
		model.setActive_ind(contact.getActive_ind());
		model.setBlocked(contact.getBlocked());
		model.setEmail(contact.getEmail());
		model.setInvitationid(contact.getInvitationid());
		model.setSent_invite(contact.getSent_invite());
		model.setStatus(contact.getStatus());
		model.setUserid(String.valueOf(contact.getUserId()));
		return model;
	}

	@RequestMapping(value = "/reportThisPoll.cyt", method = {RequestMethod.POST})
	@ResponseBody
	public String reportPoll(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		Long questionId = Long.valueOf(request.getParameter("questionId"));
		String reportType = request.getParameter("reportType");
		String comments = request.getParameter("comments");
		ReportPoll reportPoll = new ReportPoll();
		reportPoll.setUserId(user.getId());
		reportPoll.setQuestionId(questionId);
		reportPoll.setReportType(reportType);
		reportPoll.setComments(comments);
		Logger.debug("reportPoll::" + reportPoll);
		try {
			String reportPollStatus = userPollSrv.reportPoll(reportPoll);
			if (StringUtils.equals(reportPollStatus, "inserted") || StringUtils.equals(reportPollStatus, "limitReached")) {
				quickStartSrv.sendEmailForReportThisPoll(reportPoll);
			}
			return reportPollStatus;
		} catch (Exception e) {
			Logger.error("Exception:", e);
			return "error:" + e.getMessage();
		}
	}

	private String generateFileName(String fileName) {

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

	private String getType(String cType) throws IOException {
		return cType.substring(0, cType.indexOf("/"));
	}

	public  Map<String, String>  getCountries(){
		return referenceData.getCountries();
	}
}