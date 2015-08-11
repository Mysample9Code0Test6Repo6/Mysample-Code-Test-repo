package com.incyyte.app.web.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.incyyte.app.domain.Answer;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserLocation;
import com.incyyte.app.manager.helper.ImageLoader;
import com.incyyte.app.service.Exceptions.FileUploadException;
import com.incyyte.app.service.util.AgeGroupEnumType;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.UserIPLocator;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.model.AnswerModel;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.UserModel;

/**
 * @author Remi Oseni
 */
public class MapUserProperty {

    public static User copyUserSignUpDetails(UserModel model, HttpServletRequest request) {
    	model = getSignUpLocal(model, request);
        User user = new User();
        user.setUsername(model.getUsername());
        user.setEmail(model.getSu_email());
        
        if(StringUtils.isNotEmpty(model.getSu_password())){
        	user.setPassword(model.getSu_password());
        }else{
        	//generate new password
            String newPassword = Utility.generateNewPassword();
            user.setPassword(newPassword);
        	user.setResetPwdFlag("Y");
        }
        
        if(model.getGender() != null)
        	user.setGender(model.getGender());
        
        if(model.getBirthYear() > 0)
        	user.setBirthYear(model.getBirthYear());
        
        if (model.getLocation() != null)user.setLocation(model.getLocation());
        
        return user;
    }

    public static User buildSignUpDetailsByRequest(UserModel model, HttpServletRequest request) {
        User user = new User();
        String userName = request.getParameter("user");
        String password =request.getParameter("pwd");
        String emailAddress = request.getParameter("email");
        Logger.info("userName::" + userName);
        Logger.info("password::" + password);
        Logger.info("emailAddress::" + emailAddress);

        user.setUsername(userName);
        user.setEmail(emailAddress);
        model.setUsername(userName);
        model.setSu_email(emailAddress);

        if(StringUtils.isNotEmpty(password)){
            user.setPassword(password);
            model.setSu_password(password);
        }else{
            //generate new password
            String newPassword = Utility.generateNewPassword();
            user.setPassword(newPassword);
            user.setResetPwdFlag("Y");
        }

        model = getSignUpLocal(model, request);
        if (model.getLocation() != null) {
            user.setLocation(model.getLocation());
        }
        return user;
    }

    private static UserModel getSignUpLocal(UserModel model,HttpServletRequest request){
		//Get User Location from IP
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
			   Logger.debug("No LoadBalancer : ");	
			   ipAddress = request.getRemoteAddr();  
		   }
		if (ipAddress == null || ipAddress.contains("0:0:0:0") 
				|| ipAddress.equals("127.0.0.1"))ipAddress = "86.25.13.91";//Default to UK IP
		Logger.debug("SignUp ipAddress : " + ipAddress);	
		
		UserLocation location = getUserLocation(ipAddress, request);
		if (location != null){
			Logger.debug(location.toString());	
			model.setLocation(location);
		}
		return model;
    }

	private static UserLocation getUserLocation(String hostIP, HttpServletRequest request){
    	UserIPLocator obj = UserIPLocator.getInstance();
		UserLocation location = obj.getLocation(hostIP, request);
		if (location != null) location.setIpAddress(hostIP);
		return location;
	}

    public static InCyyte copyInCyyteDetails(IncyyteModel model) {
        Logger.debug("Model Object:" + model);
        InCyyte incyyte = new InCyyte();
        try {
        	incyyte.setId(model.getId());
            incyyte.setIncyyte(model.getIncyyte());
            incyyte.setAnonymity(model.isAnonymity());
            incyyte.setAnswers(getAnswers(model.getAnswers()));
            incyyte.setCategory(model.getCategory());
            incyyte.setLink(model.geteLink());
            //this is used to store youtube url 
            String youtubeUrl = null ; 
            if(StringUtils.isNotBlank(model.getYouTubeQuestionVideoId())){
            	youtubeUrl = "https://www.youtube.com/embed/"+model.getYouTubeQuestionVideoId();
            }
            incyyte.setYoutubeUrl(youtubeUrl);
            incyyte.setTemplateId(model.getTemplateId());            

            if(model.getPageName() != null && !model.getPageName().isEmpty()){
            	String page = model.getPageName().trim();
            	page = page.replaceAll(" ", "_");
            	incyyte.setPageName(page);
            }
            
            incyyte.setSendType(model.getSendType());
            incyyte.setCreatedBy(model.getCreatedBy());
            incyyte.setQuesType(model.getQuesType());

            incyyte.setPublic_poll(model.getPublic_poll());
            incyyte.setClosureDate(model.getClosureDate());
            incyyte.setAllowComment(model.isAllowComment() ? "Y" : "N");
            incyyte.setPollResultHidden(model.isPollResultHidden() ? "Y" : "N");
            incyyte.setProtectPage(model.getProtectPage());

            incyyte.setStrapline(model.getStrapline());

            incyyte.setSenderfname(model.getSenderfname());
            incyyte.setSenderlname(model.getSenderlname());

            if (model.getUploadedLogo() != null) {
                incyyte.setUpload_logo_location(model.getUploadedLogoLocation());
            }

            //if send by region
            if(!StringUtils.equals(model.getAgeRange(), null)){
            	incyyte.setAgeRange(model.getAgeRange());
            }else{
            	String ageRange =model.getAgeMin() +  "-" + model.getAgeMax();
            	incyyte.setAgeRange(ageRange);
            }
            incyyte.setGender(model.getGender());
            incyyte.setLocality(model.getLocality());
            incyyte.setRegion(model.getRegion());
            incyyte.setCounty(model.getCounty());
            incyyte.setCountry(model.getCountry());
            incyyte.setPostcode(model.getPostcode());

            if (model.getSendToGroup().equals("N") && StringUtils.isNotEmpty(model.getEmailArr())) {
                List<Contact> contacts = new ArrayList<Contact>();
                contacts.addAll(getContacts(model.getEmailArr()));
                incyyte.setContacts(contacts);
            }

            if (model.getSendToGroup().equals("Y") && (model.getGrpId() != null && !model.getGrpId().equals(""))) {
                incyyte.setGrpId(Long.valueOf(model.getGrpId()));
            }

            if (model.getUploadedFile() != null) {
                incyyte.setUpload_name(model.getFileName());
                incyyte.setCdnFileName(model.getUploadedFileName());
                incyyte.setContent_type(model.getUploadedFile().getContentType());
                String fname = model.getUploadedFile().getOriginalFilename();
                incyyte.setUpload_ext(fname.substring(fname.lastIndexOf(".") + 1, fname.length()));
                //Locations are used to store File type while storing the information
                //While retrieving from database they will be converted as a URL to pick the file
                incyyte.setUploadLocation(model.getUploadedFileType());
                incyyte.setUpload_type(model.getUploadedFileType());
            } else if (model.getSearchedFileName() != null) {
                incyyte.setUpload_name(model.getSearchedFileName());
                incyyte.setCdnFileName(model.getUploadedFileName());
                incyyte.setContent_type(FileManagementUtil.getMimeType(model.getSearchedFileURL()));
                String fname = model.getSearchedFileName();
                incyyte.setUpload_ext(fname.substring(fname.lastIndexOf(".") + 1, fname.length()));
                //Locations are used to store File type while storing the information
                //While retrieving from database they will be converted as a URL to pick the file
                incyyte.setUploadLocation(model.getUploadedFileType());
                incyyte.setUpload_type(model.getUploadedFileType());
            }
            Logger.debug("Loaded Object:" + incyyte);
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return incyyte;
    }

    private static List<Answer> getAnswers(List<AnswerModel> ansmodels) {
        List<Answer> answers = new ArrayList<Answer>();
        Logger.debug("%%%%% answer size => " + ansmodels.size());

        for (AnswerModel model : ansmodels) {
            Answer ans = new Answer();
            ans.setId(model.getAnsId());
            ans.setAnswerOption(model.getAnswerChoice());
            ans.setIncyyteId(model.getQuesId());
            ans.setUploadCDN_url(model.getAnswerUploadedUrl());
            ans.setUploadName(model.getAnswerFileName());
            ans.setCdnFileName(model.getCdnFileName());
            ans.setUploadType(model.getAnswerType());
            ans.setUrlLink(model.getAnswerlink());
            ans.setUploadExt(model.getAnswerUploadExt());
            String youtubeAnswerUrl = "" ;
            if(model != null && StringUtils.isNotBlank(model.getUploadedAnsFileYoutubeVideo())){
            	youtubeAnswerUrl = "https://www.youtube.com/embed/"+model.getUploadedAnsFileYoutubeVideo();
            }else {
            	youtubeAnswerUrl = null;
            }
            ans.setYoutubeURLAnswer(youtubeAnswerUrl);
            answers.add(ans);
        }
        return answers;
    }

    private static Set<Contact> getContacts(String arr) {
        Set<Contact> contacts = new HashSet<Contact>();
        for (String email : arr.split("[\\s,;]+")) {
            Logger.debug("%%%%%%%% contact  email -> " + email);
            Contact contact = new Contact();
            contact.setEmail(email);
            contacts.add(contact);
        }
        return contacts;
    }

    public static IncyyteModel getIncyyteModel(InCyyte inCyyte) {

        IncyyteModel model = new IncyyteModel();

        long quesId = inCyyte.getId();
        model.setAnonymity(inCyyte.isAnonymity());
        model.setCategory(inCyyte.getCategory());
        model.setId(inCyyte.getId());
        model.setIncyyte(inCyyte.getIncyyte());
        model.setMultiSelection(inCyyte.isMultiSelection());
        model.setRandomize(inCyyte.isRandomize());
        model.setStyle(inCyyte.getStyle());
        model.seteLink(inCyyte.getLink());
        model.setUploadedFileLocation(inCyyte.getUploadLocation());
        model.setFileName(inCyyte.getUpload_name());
        model.setUploadedFileType(inCyyte.getUpload_type());
        model.setContentType(inCyyte.getContent_type());
        model.setPublic_poll(inCyyte.getPublic_poll());
        model.setSendType(inCyyte.getSendType());
        model.setClosureDate(inCyyte.getClosureDate());
        model.setAllowComment(inCyyte.getAllowComment() != null && inCyyte.getAllowComment().equals("Y"));
        model.setProtectPage(inCyyte.getProtectPage());
        model.setStrapline(inCyyte.getStrapline());
        model.setUploadedLogoLocation(inCyyte.getUpload_logo_location());
        model.setTemplateId(inCyyte.getTemplateId());
        /*model.setCategory(inCyyte.getCategory());*/
        model.setCreatedDate(inCyyte.getCreatedDate());
        model.setCreatedBy(inCyyte.getCreatedBy());
        model.setCreatedbyImageLink(inCyyte.getSenderProfilePic());
        List<AnswerModel> answers = new ArrayList<AnswerModel>();

        for (Answer answer : inCyyte.getAnswers()) {
            AnswerModel answerModel = new AnswerModel();
            answerModel.setAnsId(answer.getId());
            answerModel.setQuesId(quesId);
            answerModel.setAnswerOption(answer.getAnswerOption());
            answerModel.setAnswerUploadedUrl(answer.getUploadCDN_url());
            answerModel.setAnswerFileName(answer.getUploadName());
            answerModel.setAnswerType(answer.getUploadType());
            answerModel.setAnswerlink(answer.getUrlLink());
            answerModel.setAnswerUploadExt(answer.getUploadExt());
            answers.add(answerModel);
        }
        model.setAnswers(answers);
        return model;
    }

    @SuppressWarnings("unused")
    private static byte[] getProfilePhoto(UserModel model, HttpServletRequest request) throws FileUploadException {
        String type = null;
        String serverPath = request.getSession().getServletContext().getRealPath("/");
        StringBuilder path = new StringBuilder(serverPath + File.separator + "images" + File.separator);
        for (AgeGroupEnumType enumType : AgeGroupEnumType.values()) {
            if (enumType.getRange().equals(model.getAgeGroup())) {
                type = enumType.getType();
                break;
            }
        }
        if (model.getGender() != null) {
            if (model.getGender().equals("Male")) {
                if (type != null && Constants.YOUNG_AGE.equals(type)) {
                    path.append("boy.png");
                } else {
                    path.append("man2.png");
                }
            } else if (model.getGender().equals("Female")) {
                if (type != null && Constants.YOUNG_AGE.equals(type)) {
                    path.append("girl.png");
                } else {
                    path.append("woman.png");
                }
            }
        } else {
            path.append("man2.png");
        }
        return ImageLoader.uploadFile(path.toString());
    }
}