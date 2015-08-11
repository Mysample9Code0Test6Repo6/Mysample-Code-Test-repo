package com.incyyte.app.web.controller;

import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.Group;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.PeopleFilter;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserPollPage;
import com.incyyte.app.service.GroupService;
import com.incyyte.app.service.LookupService;
import com.incyyte.app.service.QuestionService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.UserPollService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CreateInCyyteController extends BaseContoller implements HandlerExceptionResolver {
    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());
    private Long IMAGE_MAX_SIZE = 2097152L;
    private Long DOC_MAX_SIZE = 2097152L;
    private Long VIDEO_MAX_SIZE = 31457280L; //5242880L; 


    @Autowired
    private RefData referenceData;
    @Autowired
    private QuestionService questnSrv;

    @Autowired
    private GroupService groupService;

    @Autowired
    private QuickStartService quickStartSrv;

    @Autowired
    private LookupService lookupSrv;
    
    @Autowired
	private UserPollService userPollSrv;

    @Value("${incyyte.ans.max.option}")
    private String answerMaxOption;

    @Value("${send.postal.region.display.flag}")
    private String displayPostalRegion;

    @Value("${post.incyyte.page.display.flag}")
    private String displayPostIncyyte;


    @RequestMapping(value = "/create_grp_incyyte", method = {RequestMethod.GET, RequestMethod.POST})
    public String createGroupInCyyte(ModelMap model, HttpSession session, HttpServletRequest request) {
        IncyyteModel incyyteModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        if (incyyteModel == null) {
            incyyteModel = new IncyyteModel();
        }
        String source = request.getParameter("source");
        if (StringUtils.equals(source, "GROUPS")) {
            incyyteModel.setGrpId(request.getParameter("groupId"));
            incyyteModel.setGrpName(request.getParameter("groupName"));
        }
        Logger.debug("createGroupInCyyte:incyyteModel:" + incyyteModel);
        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteModel);
        model.put("inCyyteForm", incyyteModel);
        return "redirect:create_question.cyt";
    }

    @RequestMapping(value = "/create_question", method = {RequestMethod.GET, RequestMethod.POST})
    public String initForm(ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {
        session.setAttribute("answerMaxOption", answerMaxOption);
        session.setAttribute("displayPostalRegion", displayPostalRegion);
        session.setAttribute("displayPostIncyyte", displayPostIncyyte);
        IncyyteModel incyyteModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        if (incyyteModel == null) {
            incyyteModel = (IncyyteModel) model.get("inCyyteForm");
            if (incyyteModel == null) {
                incyyteModel = new IncyyteModel();
            }
        }
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);

        double lookupValueDb = 0;        
        Map<String, String> templates = new HashMap<String, String>();
        
        if(user != null){
        	 lookupValueDb =Double.parseDouble(lookupSrv.getLookupValue(user.getUserType(), "POLL_CHARGE"));        	 
        	 List<UserPollPage> userPollPages = userPollSrv.getUserPollPagesInformation(user.getId() , AddressType.PAGE);        	 
 			
 			if(userPollPages != null && !userPollPages.isEmpty()){				
 				for(UserPollPage userPollPage : userPollPages){
 					if(userPollPage.getPollPage()!= null){
 						templates.put(userPollPage.getPollPage().getPageId().toString(), userPollPage.getPollPage().getPageHeader() + " Template");				
 					}
 				}				
 			}

        }
        model.put("templates", templates);
        
        Logger.debug("lookupValue::......." + lookupValueDb);
        int lookupValue = (int) (lookupValueDb * 100);
        Logger.debug("lookupValue::" + lookupValue);

        incyyteModel.setPollCharge(lookupValue);
        Logger.debug("incyyteModel:" + incyyteModel);
        model.put("inCyyteForm", incyyteModel);
        model.put("loginForm", new UserModel());
        model.put("signUpForm", new UserModel());
        model.put("agegroups", referenceData.getAgeGroup());
        model.put("categories", referenceData.getInCyyteCategories());
        Logger.debug("model objectlllllllll:" + incyyteModel);
        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteModel);
        Logger.debug("model object:" + incyyteModel);
        // return form view
        return "main/create_inCyyte";
    }

    @ModelAttribute("categories")
    public Map<String, String> populateAgeGroupList() {
        // Data referencing for categories list box
        return referenceData.getInCyyteCategories();
    }


    @ModelAttribute("ageMin")
    public Map<String, String> getAgeMin() {
        return referenceData.getAgeMin();
    }

    @ModelAttribute("ageMax")
    public Map<String, String> getAgeMax() {
        return referenceData.getAgeMax();
    }

    @RequestMapping(value = "/getClosureDate.cyt")
    @ResponseBody
    public void getClosureDate(ModelMap model, @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, HttpSession session) {
        Logger.debug("=====Inside controller in change date=====" + incyyteModel);
        IncyyteModel incyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        Logger.debug("=====Inside controller in Create Question===== incyteSessionModel" + incyteSessionModel);
        if (StringUtils.isNotBlank(incyyteModel.getClosureDate())) {
            incyteSessionModel.setClosureDate(incyyteModel.getClosureDate());
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyteSessionModel);
        }
    }

    @RequestMapping(value = "/send_poll.cyt")
    @ResponseBody
    public void sendPoll(ModelMap model, @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, HttpSession session) {
        Logger.debug("=====Inside controller in sendPoll=====" + incyyteModel);
        IncyyteModel incyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        Logger.debug("=====Inside controller in sendPoll==== incyteSessionModel" + incyteSessionModel);
        if (StringUtils.isNotBlank(incyyteModel.getPublic_poll())) {
            incyteSessionModel.setPublic_poll((incyyteModel.getPublic_poll()));
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyteSessionModel);
        }
    }

    @RequestMapping(value = "/addEmailAddress.cyt")
    @ResponseBody
    public void addEmailAddress(ModelMap model, @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, HttpSession session) {
        Logger.debug("=====Inside controller in addEmailAddress=====" + incyyteModel);
        IncyyteModel incyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        Logger.debug("=====Inside controller in addEmailAddress===== incyteSessionModel" + incyteSessionModel);
        if (StringUtils.isNotBlank(incyyteModel.getEmailArr())) {
            incyteSessionModel.setEmailArr((incyyteModel.getEmailArr()));
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyteSessionModel);
        }
    }

    @RequestMapping(value = "/pollSettings.cyt")
    @ResponseBody
    public void pollSettings(ModelMap model, @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, HttpSession session) {
        Logger.debug("=====Inside controller in pollSettings=====" + incyyteModel);
        IncyyteModel incyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        Logger.debug("=====Inside controller in pollSettings===== incyteSessionModel" + incyteSessionModel);
        if (incyyteModel.isAnonymity()) {
            incyteSessionModel.setAnonymity((incyyteModel.isAnonymity()));
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyteSessionModel);
        } else {
            incyteSessionModel.setAnonymity((incyyteModel.isAnonymity()));
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyteSessionModel);
        }
        if (incyyteModel.isAllowComment()) {
            incyteSessionModel.setAllowComment((incyyteModel.isAllowComment()));
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyteSessionModel);
        } else {
            incyteSessionModel.setAllowComment((incyyteModel.isAllowComment()));
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyteSessionModel);
        }
        if (incyyteModel.isPollResultHidden()) {
            incyteSessionModel.setPollResultHidden((incyyteModel.isPollResultHidden()));
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyteSessionModel);
        } else {
            incyteSessionModel.setPollResultHidden((incyyteModel.isPollResultHidden()));
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyteSessionModel);
        }
    }

    @RequestMapping(value = "/multiPartFileSingle", method = RequestMethod.POST)
    @ResponseBody
    public String uploadMultipartFile(HttpServletRequest request,
                                      @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
                                      BindingResult result, SessionStatus status, HttpSession session) throws Exception {

        try {
            if (result.hasErrors()) {
                for (ObjectError error : result.getAllErrors()) {
                    Logger.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
                }
            } else {
                IncyyteModel incyyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
                Logger.debug("incyyteModel::" + incyyteModel);
                Logger.debug("incyyteSessionModel::" + incyyteSessionModel);

                //Only in case of photos we have two modes to select a file
                if (!incyyteModel.getUploadedDocFile().isEmpty()
                        || (!incyyteModel.getUploadedPhotoFile().isEmpty() || StringUtils.isNotBlank(incyyteModel.getSearchedFileName()))
                        || (!incyyteModel.getUploadedVideoFile().isEmpty() || StringUtils.isNotBlank(incyyteModel.getYouTubeQuestionVideoId()))) {

                    Logger.debug("uploadMultipartFile - upload not empty");
                    if (incyyteModel.getUploadedType().equals(Constants.UPLOAD_TYPE_DOC) && !incyyteModel.getUploadedDocFile().isEmpty()
                            && incyyteModel.getUploadedDocFile().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setUploadedDocFile(incyyteModel.getUploadedDocFile());
                        incyyteSessionModel.setUploadedFile(incyyteModel.getUploadedDocFile());
                    } else if (incyyteModel.getUploadedType().equals(Constants.UPLOAD_TYPE_IMAGE) && !incyyteModel.getUploadedPhotoFile().isEmpty()
                            && incyyteModel.getUploadedPhotoFile().getSize() < IMAGE_MAX_SIZE) {
                        incyyteSessionModel.setUploadedPhotoFile(incyyteModel.getUploadedPhotoFile());
                        incyyteSessionModel.setUploadedFile(incyyteModel.getUploadedPhotoFile());
                    } else if (incyyteModel.getUploadedType().equals(Constants.UPLOAD_TYPE_VIDEO) && !incyyteModel.getUploadedVideoFile().isEmpty()
                            && incyyteModel.getUploadedVideoFile().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setUploadedVideoFile(incyyteModel.getUploadedVideoFile());
                        incyyteSessionModel.setUploadedFile(incyyteModel.getUploadedVideoFile());
                    } //This portion is again for Photos - Which are selected from Google images
                    else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        //photoFile.
                        try {
                            URL url = new URL(incyyteModel.getSearchedFileURL());
                            String tDir = System.getProperty("java.io.tmpdir");
                            String path = tDir + incyyteModel.getSearchedFileName();
                            File file = new File(path);
                            FileUtils.copyURLToFile(url, file);
                            Logger.debug("%INCYYTE_MODEL%" + incyyteModel);
                            incyyteSessionModel.setUploadedType(incyyteModel.getUploadedType());
                            incyyteSessionModel.setIncyyte(incyyteModel.getIncyyte());
                            Photo searchedImage = new Photo();
                            searchedImage.setData(new FileInputStream(file));
                            searchedImage.setFileName(file.getName());
                            searchedImage.setContentType(FileManagementUtil.getMimeType(incyyteModel.getSearchedFileURL()));
                            searchedImage.setContainerName(FileManagementUtil.getMimeType(incyyteModel.getSearchedFileURL()));
                            searchedImage.setSize(file.getTotalSpace());
                            Logger.debug("%Searched Image%" + searchedImage);
                            uploadQuestionPhotos(incyyteSessionModel, searchedImage);
                            session.setAttribute("uploadedFileLocation", incyyteSessionModel.getUploadedFileLocation());
                            session.setAttribute("filename", incyyteSessionModel.getSearchedFileName());
                            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSessionModel);
                            Logger.debug("INCYYTE_MODEL::" + incyyteSessionModel);
                            return incyyteSessionModel.getUploadedFileLocation();
                        } catch (Exception e) {
                            Logger.error("Exception:", e);
                        }
                    } else if (StringUtils.isNotBlank(incyyteModel.getYouTubeQuestionVideoId())) {
                        Logger.debug("entered for Youtube Upload");
                        incyyteSessionModel.setYouTubeQuestionVideoId(incyyteModel.getYouTubeQuestionVideoId());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile - upload is empty or too big");
                        throw new Exception();
                    }
                    incyyteSessionModel.setUploadedType(incyyteModel.getUploadedType());
                    incyyteSessionModel.setIncyyte(incyyteModel.getIncyyte());

                    if (incyyteSessionModel.getUploadedFile() != null && !incyyteSessionModel.getUploadedFile().isEmpty()) {
                        Logger.debug("here :::: - uploadMultipartFile name -> " + incyyteSessionModel.getUploadedFile());
                        Logger.debug("here :::: - uploadMultipartFile size -> " + incyyteSessionModel.getUploadedFile().getSize());
                        uploadFile(incyyteSessionModel);
                        session.setAttribute("uploadedFileLocation", incyyteSessionModel.getUploadedFileLocation());
                        session.setAttribute("filename", incyyteSessionModel.getFileName());
                        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSessionModel);
                        Logger.debug("%INCYYTE_MODEL%" + incyyteSessionModel);
                        return incyyteSessionModel.getUploadedFileLocation();
                    } else if (incyyteSessionModel != null && StringUtils.isNotBlank(incyyteSessionModel.getYouTubeQuestionVideoId())) {
                        Logger.debug("%INCYYTE_MODEL%" + incyyteSessionModel);
                        String fileLocation = "https://www.youtube.com/embed/" + incyyteSessionModel.getYouTubeQuestionVideoId();
                        incyyteSessionModel.setUploadedFileLocation(fileLocation);
                        incyyteModel.setUploadedFileLocation(fileLocation);
                        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSessionModel);
                        return incyyteSessionModel.getUploadedFileLocation();
                    }
                }
            }
        } catch (Exception e) {
            Logger.error("%%%%%%%%%%%%%%% uploadMultipartFile - upload is empty or too big", e);
        }
        return "failure";
    }

    @RequestMapping(value = "/displayUploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String displayUploadFile(HttpServletRequest request, HttpSession session) throws Exception {
        IncyyteModel incyyteModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile");
        if (!incyyteModel.getUploadedFile().isEmpty()) {
            StringBuilder dash = new StringBuilder();
            dash.append("<ul id='videos'>");
            dash.append("<li>");
            if (incyyteModel.getUploadedType().equals(Constants.UPLOAD_TYPE_IMAGE)) {
                dash.append("<a href='" + incyyteModel.getUploadedFileLocation() + "' title='" + incyyteModel.getFileName() + "' class='group1'>");
                dash.append("<img src='" + incyyteModel.getUploadedFileLocation() + "' class='photos_thumb' alt='tour' />");
                dash.append("</a>");
            } else if (incyyteModel.getUploadedType().equals(Constants.UPLOAD_TYPE_VIDEO)) {
                dash.append("<a href='" + incyyteModel.getUploadedFileLocation() + "' title='" + incyyteModel.getFileName() + "' class='video_link'>");
                dash.append("<img src='ui/images/dg_video_thumb.png' class='photos_thumb' alt='tour' />");
                dash.append("</a>");
            } else {
                dash.append("<a href='" + incyyteModel.getUploadedFileLocation() + "' title='" + incyyteModel.getFileName() + "'>");
                dash.append("<img src='ui/images/view_docs_thumb.png' class='photos_thumb' alt='tour' />");
                dash.append("</a>");
            }
            dash.append("</li>");
            dash.append("</ul>");

            return dash.toString();
        }
        return null;
    }


    @RequestMapping(value = "/deleteuploadedfile", method = RequestMethod.POST)
    @ResponseBody
    public String deleteuploadedfile(HttpServletRequest request,
                                     @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
                                     BindingResult result, SessionStatus status, HttpSession session) throws Exception {
        IncyyteModel incyyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        Logger.debug("%%%%%%%%%%%%%%% here - deleteuploadedfile");
        Logger.debug("========================" + incyyteSessionModel.getUploadedFile());
        Logger.debug("========================" + incyyteModel.getUploadedFile());
        Logger.debug("________________________" + incyyteModel.getUploadedType());
        Logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + incyyteModel.getUploadedFileName());

        try {
            if (incyyteSessionModel != null && StringUtils.isNotBlank(incyyteSessionModel.getUploadedFileLocation())) {
                deleteUploadFile(incyyteSessionModel);
                incyyteSessionModel.setUploadedFile(null);
                incyyteSessionModel.setUploadedDocFile(null);
                incyyteSessionModel.setUploadedVideoFile(null);
                incyyteSessionModel.setUploadedPhotoFile(null);
                incyyteSessionModel.setUploadedType(null);
                incyyteSessionModel.setUploadedFileLocation(null);

                incyyteSessionModel.setFileName(null);
                incyyteSessionModel.setUploadedFileName(null);
                incyyteSessionModel.setContentType(null);

                session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSessionModel);

                return "success ";
            }

        } catch (Exception e) {
            Logger.error("%%%%%%%%%%%%%%% uploadMultipartFile - upload is empty", e);
            throw new Exception();
        }
        return null;
    }

    @RequestMapping(value = "/createNewRow", method = RequestMethod.POST)
    @ResponseBody
    public String createNewRow(@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, HttpSession session) {
        IncyyteModel incyyteSessionModel = null;
        incyyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        Logger.debug("incyyteSessionModel::" + incyyteSessionModel);
        Logger.debug("incyyteModel::" + incyyteModel);
        if (StringUtils.isNotBlank(incyyteModel.getIncyyte())) {
            incyyteSessionModel.setIncyyte(incyyteModel.getIncyyte());
        }
        if (StringUtils.isNotBlank(incyyteModel.getEmailArr())) {
            incyyteSessionModel.setEmailArr(incyyteModel.getEmailArr());
        }
        
        incyyteSessionModel.setAnswer_count(incyyteModel.getAnswer_count());
        incyyteSessionModel.setAnswer_upload_opt(incyyteModel.getAnswer_upload_opt());
        incyyteSessionModel = setAnswerValues(incyyteModel, incyyteSessionModel);
        Logger.debug("======incyyteModel===============" + incyyteModel.getAnswerChoice3());
        Logger.debug("======incyyteSessionModel========" + incyyteSessionModel.getAnswerChoice3());
        Logger.debug("incyyteModel row count ::::" + incyyteModel.getAnswer_count());
        Logger.debug("incyyteSessionModel row count ::::" + incyyteSessionModel.getAnswer_count());
        List<AnswerModel> model = getAnswers(incyyteSessionModel);
        Logger.debug("size of incyytemodel" + model.size());
        incyyteSessionModel.setAnswers(model);
        Logger.debug("======incyyteModel===============" + incyyteModel.getAnswer_upload_opt());
        Logger.debug("======incyyteSessionModel========" + incyyteSessionModel.getAnswer_upload_opt());

        //populate
        incyyteSessionModel.setCategory(incyyteModel.getCategory());

        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSessionModel);
        return "success";
    }

    private IncyyteModel setAnswerValues(IncyyteModel incyyteModel, IncyyteModel incyyteSessionModel) {
        if (incyyteModel.getAnswerChoice1() != null) {
            incyyteSessionModel.setAnswerChoice1(incyyteModel.getAnswerChoice1());
        }
        if (incyyteModel.getAnswerChoice2() != null) {
            incyyteSessionModel.setAnswerChoice2(incyyteModel.getAnswerChoice2());
        }
        if (incyyteModel.getAnswerChoice3() != null) {
            incyyteSessionModel.setAnswerChoice3(incyyteModel.getAnswerChoice3());
        }
        if (incyyteModel.getAnswerChoice4() != null) {
            incyyteSessionModel.setAnswerChoice4(incyyteModel.getAnswerChoice4());
        }
        if (incyyteModel.getAnswerChoice5() != null) {
            incyyteSessionModel.setAnswerChoice5(incyyteModel.getAnswerChoice5());
        }
        if (incyyteModel.getAnswerChoice6() != null) {
            incyyteSessionModel.setAnswerChoice6(incyyteModel.getAnswerChoice6());
        }
        if (incyyteModel.getAnswerChoice7() != null) {
            incyyteSessionModel.setAnswerChoice7(incyyteModel.getAnswerChoice7());
        }
        if (incyyteModel.getAnswerChoice8() != null) {
            incyyteSessionModel.setAnswerChoice8(incyyteModel.getAnswerChoice8());
        }
        if (incyyteModel.getAnswerChoice9() != null) {
            incyyteSessionModel.setAnswerChoice9(incyyteModel.getAnswerChoice9());
        }
        if (incyyteModel.getAnswerChoice10() != null) {
            incyyteSessionModel.setAnswerChoice10(incyyteModel.getAnswerChoice10());
        }
        return incyyteSessionModel;
    }

    @RequestMapping(value = "/deleteNewRow", method = RequestMethod.POST)
    public String deleteNewRow(@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, HttpSession session) {
        IncyyteModel incyyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        incyyteSessionModel.setAnswer_count(incyyteModel.getAnswer_count());
        incyyteSessionModel.setAnswer_upload_opt(incyyteModel.getAnswer_upload_opt());
        Logger.debug("==D====incyyteModel===============" + incyyteModel);
        Logger.debug("==D====incyyteSessionModel========" + incyyteSessionModel);
        Logger.debug("==D====incyyteModel row count ::::" + incyyteModel.getAnswer_count());
        Logger.debug("==D====incyyteSessionModel row count ::::" + incyyteSessionModel.getAnswer_count());
        Logger.debug("==D====incyyteModel row count ::::" + incyyteModel.getAnswer_upload_opt());
        Logger.debug("==D====incyyteSessionModel row count ::::" + incyyteSessionModel.getAnswer_upload_opt());
        List<AnswerModel> model = getAnswers(incyyteSessionModel);
        Logger.debug("size of incyytemodel" + model.size());
        incyyteSessionModel.setAnswers(model);
        nullValues(incyyteSessionModel, true);

        //populate
        incyyteSessionModel.setCategory(incyyteModel.getCategory());

        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSessionModel);
        return "success";
    }

    @RequestMapping(value = "/deleteAnsUploadedFile", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAnsUploadedFile(HttpServletRequest request,
                                        @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
                                        BindingResult result, SessionStatus status, HttpSession session) throws Exception {

        IncyyteModel incyyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        incyyteSessionModel.setAnswer_upload_opt(incyyteModel.getAnswer_upload_opt());
        try {
            if (incyyteSessionModel.getAnswer_upload_opt() != null) {
                Logger.debug("%%%%%%%%%%%%%%%incyyteSessionModel uploadAnsFile1 - upload opt - " + incyyteSessionModel.getAnswer_upload_opt());
                Logger.debug("%%%%%%%%%%%%%%%incyyteModel uploadAnsFile - upload opt - " + incyyteModel.getAnswer_upload_opt());
                Logger.debug("IncyyteSessionModel:DeleteAnswerFile: - " + incyyteSessionModel);
                Logger.debug("InCyyteModel:DeleteAnswerFile: - " + incyyteModel);
                //Delete info will come from the answer common variables
                nullValues(incyyteSessionModel, false);
                deleteAnsUploadFile(incyyteSessionModel);
                List<AnswerModel> model = getAnswers(incyyteSessionModel);
                incyyteSessionModel.setAnswers(model);
                session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSessionModel);
                return "success";
            }

        } catch (Exception e) {
            Logger.error("%%%%%%%%%%%%%%% uploadMultipartFile - upload is empty", e);
            throw e;
        }
        return null;
    }

    @RequestMapping(value = "/uploadAnsFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadAnsFile(HttpServletRequest request,
                                @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
                                BindingResult result, SessionStatus status, HttpSession session) throws Exception {
        IncyyteModel incyyteSessionModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
        Logger.debug("IncyyteModel - " + incyyteModel);
        Logger.debug("incyyteSessionModel - " + incyyteSessionModel);
        try {
            if (incyyteSessionModel != null && incyyteModel.getAnswer_upload_opt() != null) {
                incyyteSessionModel.copyValues(incyyteModel);
                incyyteSessionModel.setSearchedFileName(incyyteModel.getSearchedFileName());
                Logger.debug("IncyyteSessionModel - " + incyyteSessionModel);
                Logger.debug("uploadAnsFile - upload opt - " + incyyteModel.getAnswer_upload_opt() + "" + incyyteModel.getAnswer_file_1() + "  " + incyyteModel.getAnswer_file_photo_1().getOriginalFilename());
                if (incyyteModel.getAnswer_upload_opt().equals("1")) {
                    Logger.debug("Inside option 1 -> " + incyyteModel.getAnswer_file_photo_1().getName());
                    Logger.debug("Inside option 1 -> " + incyyteModel.getAnswer_file_photo_1().getContentType());
                    Logger.debug("Inside option 1 -> " + incyyteModel.getAnswer_file_photo_1().getStorageDescription());
                    Logger.debug("Inside option 1 -> " + incyyteModel.getAnswer_opt_1());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_1_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 1 ");
                        incyyteSessionModel.setYouTubeAnswer_1_VideoId(incyyteModel.getYouTubeAnswer_1_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_1().isEmpty() && incyyteModel.getAnswer_file_photo_1().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("Photo :: Inside option 1 -> Inside IF");
                        Logger.debug("here - uploadMultipartFile name 1 -> " + incyyteModel.getAnswer_file_photo_1().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_1());
                        incyyteSessionModel.setAnswerChoice1(incyyteModel.getAnswerChoice1());
                        incyyteSessionModel.setAnswer_file_1(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_1().isEmpty() && incyyteModel.getAnswer_file_video_1().getSize() < VIDEO_MAX_SIZE) {
                        Logger.debug("Video:: here - uploadMultipartFile name 1 -> " + incyyteModel.getAnswer_file_photo_1().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_1());
                        incyyteSessionModel.setAnswerChoice1(incyyteModel.getAnswerChoice1());
                        incyyteSessionModel.setAnswer_file_1(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_1().isEmpty() && incyyteModel.getAnswer_file_doc_1().getSize() < DOC_MAX_SIZE) {
                        Logger.debug("Doc:: here > ");
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_1());
                        incyyteSessionModel.setAnswerChoice1(incyyteModel.getAnswerChoice1());
                        incyyteSessionModel.setAnswer_file_1(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        Logger.debug("Google Searched Image");
                        incyyteSessionModel.setAnswerChoice1(incyyteModel.getAnswerChoice1());
                    } else {
                        Logger.debug("uploadMultipartFile 1 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName1(null);
                        incyyteSessionModel.setAnswer_file_1(null);

                        throw new Exception();
                    }
                } else if (incyyteModel.getAnswer_upload_opt().equals("2")) {
                    Logger.debug("Inside option 2 -> " + incyyteModel.getAnswer_file_photo_2().getName());
                    Logger.debug("Inside option 2 -> " + incyyteModel.getAnswer_file_photo_2().getContentType());
                    Logger.debug("Inside option 2 -> " + incyyteModel.getAnswer_file_photo_2().getStorageDescription());
                    Logger.debug("Inside option 2 -> " + incyyteModel.getAnswer_opt_2());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_2_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 2 ");
                        incyyteSessionModel.setYouTubeAnswer_2_VideoId(incyyteModel.getYouTubeAnswer_2_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_2().isEmpty() && incyyteModel.getAnswer_file_photo_2().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("Inside option 2 -> Inside IF");
                        Logger.debug("here - uploadMultipartFile name 2 -> " + incyyteModel.getAnswer_file_photo_2().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_2());
                        incyyteSessionModel.setAnswerChoice2(incyyteModel.getAnswerChoice2());
                        incyyteSessionModel.setAnswer_file_2(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_2().isEmpty() && incyyteModel.getAnswer_file_video_2().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_2());
                        incyyteSessionModel.setAnswerChoice2(incyyteModel.getAnswerChoice2());
                        incyyteSessionModel.setAnswer_file_2(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_2().isEmpty() && incyyteModel.getAnswer_file_doc_2().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_2());
                        incyyteSessionModel.setAnswerChoice2(incyyteModel.getAnswerChoice2());
                        incyyteSessionModel.setAnswer_file_2(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        incyyteSessionModel.setAnswerChoice2(incyyteModel.getAnswerChoice2());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile 2 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName2(null);
                        incyyteSessionModel.setAnswer_file_2(null);

                        throw new Exception("Upload File is empty or too big");
                    }
                } else if (incyyteModel.getAnswer_upload_opt().equals("3")) {
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 3 -> " + incyyteModel.getAnswer_file_photo_3().getName());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 3 -> " + incyyteModel.getAnswer_file_photo_3().getContentType());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 3 -> " + incyyteModel.getAnswer_file_photo_3().getStorageDescription());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 3 -> " + incyyteModel.getAnswer_opt_3());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_3_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 3 ");
                        incyyteSessionModel.setYouTubeAnswer_3_VideoId(incyyteModel.getYouTubeAnswer_3_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_3().isEmpty() && incyyteModel.getAnswer_file_photo_3().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("%%%%%%%%%%%%%%% Inside option 3 -> Inside IF");
                        Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name 3 -> " + incyyteModel.getAnswer_file_photo_3().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_3());
                        incyyteSessionModel.setAnswerChoice3(incyyteModel.getAnswerChoice3());
                        incyyteSessionModel.setAnswer_file_3(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_3().isEmpty() && incyyteModel.getAnswer_file_video_3().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_3());
                        incyyteSessionModel.setAnswerChoice3(incyyteModel.getAnswerChoice3());
                        incyyteSessionModel.setAnswer_file_3(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_3().isEmpty() && incyyteModel.getAnswer_file_doc_3().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_3());
                        incyyteSessionModel.setAnswerChoice3(incyyteModel.getAnswerChoice3());
                        incyyteSessionModel.setAnswer_file_3(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        incyyteSessionModel.setAnswerChoice3(incyyteModel.getAnswerChoice3());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile 3 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName3(null);
                        incyyteSessionModel.setAnswer_file_3(null);
                        throw new Exception();
                    }
                } else if (incyyteModel.getAnswer_upload_opt().equals("4")) {
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 4 -> " + incyyteModel.getAnswer_file_photo_4().getName());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 4 -> " + incyyteModel.getAnswer_file_photo_4().getContentType());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 4 -> " + incyyteModel.getAnswer_file_photo_4().getStorageDescription());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 4 -> " + incyyteModel.getAnswer_opt_4());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_4_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 4 ");
                        incyyteSessionModel.setYouTubeAnswer_4_VideoId(incyyteModel.getYouTubeAnswer_4_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_4().isEmpty() && incyyteModel.getAnswer_file_photo_4().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("%%%%%%%%%%%%%%% Inside option 4 -> Inside IF");
                        Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name 4 -> " + incyyteModel.getAnswer_file_photo_4().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_4());
                        incyyteSessionModel.setAnswerChoice4(incyyteModel.getAnswerChoice4());
                        incyyteSessionModel.setAnswer_file_4(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_4().isEmpty() && incyyteModel.getAnswer_file_video_4().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_4());
                        incyyteSessionModel.setAnswerChoice4(incyyteModel.getAnswerChoice4());
                        incyyteSessionModel.setAnswer_file_4(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_4().isEmpty() && incyyteModel.getAnswer_file_doc_4().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_4());
                        incyyteSessionModel.setAnswerChoice4(incyyteModel.getAnswerChoice4());
                        incyyteSessionModel.setAnswer_file_4(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        incyyteSessionModel.setAnswerChoice4(incyyteModel.getAnswerChoice4());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile 4 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName4(null);
                        incyyteSessionModel.setAnswer_file_4(null);
                        throw new Exception();
                    }
                } else if (incyyteModel.getAnswer_upload_opt().equals("5")) {
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 5 -> " + incyyteModel.getAnswer_file_photo_5().getName());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 5 -> " + incyyteModel.getAnswer_file_photo_5().getContentType());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 5 -> " + incyyteModel.getAnswer_file_photo_5().getStorageDescription());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 5 -> " + incyyteModel.getAnswer_opt_5());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_5_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 5 ");
                        incyyteSessionModel.setYouTubeAnswer_5_VideoId(incyyteModel.getYouTubeAnswer_5_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_5().isEmpty() && incyyteModel.getAnswer_file_photo_5().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("%%%%%%%%%%%%%%% Inside option 5 -> Inside IF");
                        Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name 5 -> " + incyyteModel.getAnswer_file_photo_5().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_5());
                        incyyteSessionModel.setAnswerChoice5(incyyteModel.getAnswerChoice5());
                        incyyteSessionModel.setAnswer_file_5(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_5().isEmpty() && incyyteModel.getAnswer_file_video_5().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_5());
                        incyyteSessionModel.setAnswerChoice5(incyyteModel.getAnswerChoice5());
                        incyyteSessionModel.setAnswer_file_5(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_5().isEmpty() && incyyteModel.getAnswer_file_doc_5().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_5());
                        incyyteSessionModel.setAnswerChoice5(incyyteModel.getAnswerChoice5());
                        incyyteSessionModel.setAnswer_file_5(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        incyyteSessionModel.setAnswerChoice5(incyyteModel.getAnswerChoice5());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile 5 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName5(null);
                        incyyteSessionModel.setAnswer_file_5(null);
                        throw new Exception();
                    }
                } else if (incyyteModel.getAnswer_upload_opt().equals("6")) {
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 6 -> " + incyyteModel.getAnswer_file_photo_6().getName());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 6 -> " + incyyteModel.getAnswer_file_photo_6().getContentType());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 6 -> " + incyyteModel.getAnswer_file_photo_6().getStorageDescription());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 6 -> " + incyyteModel.getAnswer_opt_6());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_6_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 6 ");
                        incyyteSessionModel.setYouTubeAnswer_6_VideoId(incyyteModel.getYouTubeAnswer_6_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_6().isEmpty() && incyyteModel.getAnswer_file_photo_6().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("%%%%%%%%%%%%%%% Inside option 6 -> Inside IF");
                        Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name 6 -> " + incyyteModel.getAnswer_file_photo_6().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_6());
                        incyyteSessionModel.setAnswerChoice6(incyyteModel.getAnswerChoice6());
                        incyyteSessionModel.setAnswer_file_6(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_6().isEmpty() && incyyteModel.getAnswer_file_video_6().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_6());
                        incyyteSessionModel.setAnswerChoice6(incyyteModel.getAnswerChoice6());
                        incyyteSessionModel.setAnswer_file_6(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_6().isEmpty() && incyyteModel.getAnswer_file_doc_6().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_6());
                        incyyteSessionModel.setAnswerChoice6(incyyteModel.getAnswerChoice6());
                        incyyteSessionModel.setAnswer_file_6(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        incyyteSessionModel.setAnswerChoice6(incyyteModel.getAnswerChoice6());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile 6 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName6(null);
                        incyyteSessionModel.setAnswer_file_6(null);
                        throw new Exception();
                    }
                } else if (incyyteModel.getAnswer_upload_opt().equals("7")) {
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 7 -> " + incyyteModel.getAnswer_file_photo_7().getName());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 7 -> " + incyyteModel.getAnswer_file_photo_7().getContentType());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 7 -> " + incyyteModel.getAnswer_file_photo_7().getStorageDescription());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 7 -> " + incyyteModel.getAnswer_opt_7());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_7_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 7 ");
                        incyyteSessionModel.setYouTubeAnswer_7_VideoId(incyyteModel.getYouTubeAnswer_7_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_7().isEmpty() && incyyteModel.getAnswer_file_photo_7().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("%%%%%%%%%%%%%%% Inside option 7 -> Inside IF");
                        Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name 7 -> " + incyyteModel.getAnswer_file_photo_7().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_7());
                        incyyteSessionModel.setAnswerChoice7(incyyteModel.getAnswerChoice7());
                        incyyteSessionModel.setAnswer_file_7(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_7().isEmpty() && incyyteModel.getAnswer_file_video_7().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_7());
                        incyyteSessionModel.setAnswerChoice7(incyyteModel.getAnswerChoice7());
                        incyyteSessionModel.setAnswer_file_7(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_7().isEmpty() && incyyteModel.getAnswer_file_doc_7().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_7());
                        incyyteSessionModel.setAnswerChoice7(incyyteModel.getAnswerChoice7());
                        incyyteSessionModel.setAnswer_file_7(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        incyyteSessionModel.setAnswerChoice7(incyyteModel.getAnswerChoice7());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile 7 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName7(null);
                        incyyteSessionModel.setAnswer_file_7(null);
                        throw new Exception();
                    }
                } else if (incyyteModel.getAnswer_upload_opt().equals("8")) {
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 8 -> " + incyyteModel.getAnswer_file_photo_8().getName());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 8 -> " + incyyteModel.getAnswer_file_photo_8().getContentType());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 8 -> " + incyyteModel.getAnswer_file_photo_8().getStorageDescription());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 8 -> " + incyyteModel.getAnswer_opt_8());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_8_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 8 ");
                        incyyteSessionModel.setYouTubeAnswer_8_VideoId(incyyteModel.getYouTubeAnswer_8_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_8().isEmpty() && incyyteModel.getAnswer_file_photo_8().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("%%%%%%%%%%%%%%% Inside option 8 -> Inside IF");
                        Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name 8 -> " + incyyteModel.getAnswer_file_photo_8().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_8());
                        incyyteSessionModel.setAnswerChoice8(incyyteModel.getAnswerChoice8());
                        incyyteSessionModel.setAnswer_file_8(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_8().isEmpty() && incyyteModel.getAnswer_file_video_8().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_8());
                        incyyteSessionModel.setAnswerChoice8(incyyteModel.getAnswerChoice8());
                        incyyteSessionModel.setAnswer_file_8(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_8().isEmpty() && incyyteModel.getAnswer_file_doc_8().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_8());
                        incyyteSessionModel.setAnswerChoice8(incyyteModel.getAnswerChoice8());
                        incyyteSessionModel.setAnswer_file_8(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        incyyteSessionModel.setAnswerChoice8(incyyteModel.getAnswerChoice8());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile 8 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName8(null);
                        incyyteSessionModel.setAnswer_file_8(null);
                        throw new Exception();
                    }
                } else if (incyyteModel.getAnswer_upload_opt().equals("9")) {
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 9 -> " + incyyteModel.getAnswer_file_photo_9().getName());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 9 -> " + incyyteModel.getAnswer_file_photo_9().getContentType());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 9 -> " + incyyteModel.getAnswer_file_photo_9().getStorageDescription());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 9 -> " + incyyteModel.getAnswer_opt_9());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_9_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 9 ");
                        incyyteSessionModel.setYouTubeAnswer_9_VideoId(incyyteModel.getYouTubeAnswer_9_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_9().isEmpty() && incyyteModel.getAnswer_file_photo_9().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("%%%%%%%%%%%%%%% Inside option 9 -> Inside IF");
                        Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name 9 -> " + incyyteModel.getAnswer_file_photo_9().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_9());
                        incyyteSessionModel.setAnswerChoice9(incyyteModel.getAnswerChoice9());
                        incyyteSessionModel.setAnswer_file_9(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_9().isEmpty() && incyyteModel.getAnswer_file_video_9().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_9());
                        incyyteSessionModel.setAnswerChoice9(incyyteModel.getAnswerChoice9());
                        incyyteSessionModel.setAnswer_file_9(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_9().isEmpty() && incyyteModel.getAnswer_file_doc_9().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_9());
                        incyyteSessionModel.setAnswerChoice9(incyyteModel.getAnswerChoice9());
                        incyyteSessionModel.setAnswer_file_9(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        incyyteSessionModel.setAnswerChoice9(incyyteModel.getAnswerChoice9());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile 9 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName9(null);
                        incyyteSessionModel.setAnswer_file_9(null);
                        throw new Exception();
                    }
                } else if (incyyteModel.getAnswer_upload_opt().equals("10")) {
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 10 -> " + incyyteModel.getAnswer_file_photo_10().getName());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 10 -> " + incyyteModel.getAnswer_file_photo_10().getContentType());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 10 -> " + incyyteModel.getAnswer_file_photo_10().getStorageDescription());
                    Logger.debug("%%%%%%%%%%%%%%% Inside option 10 -> " + incyyteModel.getAnswer_opt_10());
                    if (StringUtils.isNotBlank(incyyteModel.getYouTubeAnswer_10_VideoId())) {
                        Logger.debug("This is for YouTubeVideoUpload For answer 10");
                        incyyteSessionModel.setYouTubeAnswer_10_VideoId(incyyteModel.getYouTubeAnswer_10_VideoId());
                    } else if (!incyyteModel.getAnswer_file_photo_10().isEmpty() && incyyteModel.getAnswer_file_photo_10().getSize() < IMAGE_MAX_SIZE) {
                        Logger.debug("%%%%%%%%%%%%%%% Inside option 10 -> Inside IF");
                        Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name 10 -> " + incyyteModel.getAnswer_file_photo_10().getOriginalFilename());
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_photo_10());
                        incyyteSessionModel.setAnswerChoice10(incyyteModel.getAnswerChoice10());
                        incyyteSessionModel.setAnswer_file_10(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_video_10().isEmpty() && incyyteModel.getAnswer_file_video_10().getSize() < VIDEO_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_video_10());
                        incyyteSessionModel.setAnswerChoice10(incyyteModel.getAnswerChoice10());
                        incyyteSessionModel.setAnswer_file_10(incyyteModel.getAns_uploaded_File());
                    } else if (!incyyteModel.getAnswer_file_doc_10().isEmpty() && incyyteModel.getAnswer_file_doc_10().getSize() < DOC_MAX_SIZE) {
                        incyyteSessionModel.setAns_uploaded_File(incyyteModel.getAnswer_file_doc_10());
                        incyyteSessionModel.setAnswerChoice10(incyyteModel.getAnswerChoice10());
                        incyyteSessionModel.setAnswer_file_10(incyyteModel.getAns_uploaded_File());
                    } else if (StringUtils.isNotBlank(incyyteModel.getSearchedFileName())) {
                        incyyteSessionModel.setAnswerChoice10(incyyteModel.getAnswerChoice10());
                    } else {
                        Logger.debug("%%%%%%%%%%%%%%% uploadMultipartFile 10 - upload is empty or too big");
                        incyyteSessionModel.setAnswerFileName10(null);
                        incyyteSessionModel.setAnswer_file_10(null);
                        throw new Exception();
                    }
                }
                Logger.debug("=====Before Uploading=====" + incyyteModel.getAns_uploaded_File());
                uploadAnsFile(incyyteSessionModel);
                incyyteSessionModel = setAnswerValues(incyyteModel, incyyteSessionModel);
                List<AnswerModel> model = getAnswers(incyyteSessionModel);
                Logger.debug("we are geting answers" + model.toString());
                incyyteSessionModel.setAnswers(model);
                Logger.debug("before returning incyyteModel answers" + incyyteModel.toString());
                Logger.debug("before returning  incyyteSessionModel answers" + incyyteSessionModel.toString());
            }
            //Reset the common values after upload is successful
            incyyteSessionModel.setAns_uploaded_File(null);
            incyyteSessionModel.setAnswer_upload_opt(null);
            incyyteSessionModel.setAnswer_upload_type(null);
            incyyteSessionModel.setAnswerFileName(null);
            incyyteSessionModel.setAnswerFileURL(null);
            incyyteSessionModel.setSearchedFileName(null);
            incyyteSessionModel.setSearchedFileURL(null);
            Logger.debug("session :::"+incyyteSessionModel);
            session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSessionModel);
            return "success";
        } catch (Exception e) {
            Logger.error("%%%%%%%%%%%%%%% uploadMultipartFile - upload is empty or too big", e);
            return "failure";
        }
    }

    @RequestMapping(value = "/countbypostcode", method = RequestMethod.POST)
    @ResponseBody
    public String countMembersByPostcode(HttpServletRequest request,
                                         @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, ModelMap modelMap, Model model,
                                         BindingResult result, SessionStatus status, HttpSession session) throws Exception {
        PeopleFilter filter = new PeopleFilter();

        IncyyteModel incyyteSession = (IncyyteModel) request.getSession().getAttribute(SessionKeys.INCYYTE_MODEL);
        filter.setMinAge(incyyteModel.getAgeMin() != null && !incyyteModel.getAgeMin().equals("select") ? Integer.valueOf(incyyteModel.getAgeMin()) : null);
        filter.setMaxAge(incyyteModel.getAgeMax() != null && !incyyteModel.getAgeMax().equals("select")
                && !incyyteModel.getAgeMax().equals("Over") ? Integer.valueOf(incyyteModel.getAgeMax()) : null);

        filter.setGender(incyyteModel.getGender() != null && !incyyteModel.getGender().equals("select") ? incyyteModel.getGender() : null);
        filter.setCategory(incyyteModel.getCategory() != null ? incyyteModel.getCategory() : null);

        Long count = questnSrv.countMembersByPostcode(incyyteModel.getPostcode(), filter);
        incyyteModel.setPollRecipients(count);
        incyyteSession.setPollRecipients(count);
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        Long pollCost = new Long("0");
        if (user != null && user.getId() != null) {
            String[] userPostCode = user.getPostalCodeArea().split(" ");
            String pCode = incyyteModel.getPostcode().toUpperCase();
            if (pCode.indexOf(userPostCode[0].toUpperCase()) == -1) {
                pollCost = count * 5;
                incyyteModel.setPollCost(count * 5);
                incyyteSession.setPollCost(count * 5);
            }
        }
        model.addAttribute("inCyyteForm", incyyteModel);
        modelMap.put("inCyyteForm", incyyteModel);
        incyyteSession.setAgeRange(incyyteModel.getAgeRange());
        incyyteSession.setRegion(incyyteModel.getRegion());
        incyyteSession.setCounty(incyyteModel.getCounty());
        incyyteSession.setLocality(incyyteModel.getLocality());
        incyyteSession.setCountry(incyyteModel.getCountry());
        incyyteSession.setSendType(incyyteModel.getSendType());
        incyyteSession.setPublic_poll(incyyteModel.getPublic_poll());
        incyyteSession.setAgeMin(incyyteModel.getAgeMin());
        incyyteSession.setAgeMax(incyyteModel.getAgeMax());
        incyyteSession.setGender(incyyteModel.getGender());
        incyyteSession.setCategory(incyyteModel.getCategory());
        incyyteSession.setPostcode(incyyteModel.getPostcode());
        Logger.debug("incyyteModel:Loaded:" + incyyteModel);
        Logger.debug("incyyteSession:Loaded:" + incyyteSession);
        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSession);
        return String.valueOf(count + "|" + pollCost);
    }

    @RequestMapping(value = "/countbyregion", method = RequestMethod.POST)
    @ResponseBody
    public String countMembersByRegion(HttpServletRequest request,
                                       @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, ModelMap modelMap, Model model,
                                       BindingResult result, SessionStatus status, HttpSession session) throws Exception {
        IncyyteModel incyyteSession = (IncyyteModel) request.getSession().getAttribute(SessionKeys.INCYYTE_MODEL);
        PeopleFilter filter = new PeopleFilter();
        filter.setMinAge(incyyteModel.getAgeMin() != null && !incyyteModel.getAgeMin().equals("select") ? Integer.valueOf(incyyteModel.getAgeMin()) : null);
        filter.setMaxAge(incyyteModel.getAgeMax() != null && !incyyteModel.getAgeMax().equals("select")
                && !incyyteModel.getAgeMax().equals("Over") ? Integer.valueOf(incyyteModel.getAgeMax()) : null);

        filter.setGender(incyyteModel.getGender() != null && !incyyteModel.getGender().equals("select") ? incyyteModel.getGender() : null);
        filter.setCategory(incyyteModel.getCategory() != null ? incyyteModel.getCategory() : null);
        Long pollCost = new Long("0");
        Long count = questnSrv.countMembersByRegion(incyyteModel.getRegion().toUpperCase(), filter);
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        incyyteSession.setPollRecipients(count);
        if (user != null && user.getId() != null) {
            String[] userPostCode = user.getPostalCodeArea().split(" ");
            String region = incyyteModel.getRegion().toUpperCase();
            if (region.indexOf(userPostCode[0].toUpperCase()) == -1) {
                pollCost = count * 5;
                incyyteModel.setPollCost(count * 5);
                incyyteSession.setPollCost(count * 5);
            }
        }
        model.addAttribute("inCyyteForm", incyyteModel);
        modelMap.put("inCyyteForm", incyyteModel);
        incyyteSession.setAgeRange(incyyteModel.getAgeRange());
        incyyteSession.setRegion(incyyteModel.getRegion());
        incyyteSession.setCounty(incyyteModel.getCounty());
        incyyteSession.setLocality(incyyteModel.getLocality());
        incyyteSession.setCountry(incyyteModel.getCountry());
        incyyteSession.setSendType(incyyteModel.getSendType());
        incyyteSession.setPublic_poll(incyyteModel.getPublic_poll());
        incyyteSession.setAgeMin(incyyteModel.getAgeMin());
        incyyteSession.setAgeMax(incyyteModel.getAgeMax());
        incyyteSession.setGender(incyyteModel.getGender());
        incyyteSession.setCategory(incyyteModel.getCategory());
        incyyteSession.setPostcode(incyyteModel.getPostcode());
        Logger.debug("incyyteModel:Loaded:" + incyyteModel);
        Logger.debug("incyyteSession:Loaded:" + incyyteSession);
        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSession);
        return String.valueOf(count + "|" + pollCost);
    }

    @RequestMapping(value = "/countbycounty", method = RequestMethod.POST)
    @ResponseBody
    public String countMembersByCounty(HttpServletRequest request,
                                       @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, ModelMap modelMap, Model model,
                                       BindingResult result, SessionStatus status, HttpSession session) throws Exception {
        IncyyteModel incyyteSession = (IncyyteModel) request.getSession().getAttribute(SessionKeys.INCYYTE_MODEL);
        PeopleFilter filter = new PeopleFilter();
        filter.setMinAge(incyyteModel.getAgeMin() != null && !incyyteModel.getAgeMin().equals("select") ? Integer.valueOf(incyyteModel.getAgeMin()) : null);
        filter.setMaxAge(incyyteModel.getAgeMax() != null && !incyyteModel.getAgeMax().equals("select")
                && !incyyteModel.getAgeMax().equals("Over") ? Integer.valueOf(incyyteModel.getAgeMax()) : null);

        filter.setGender(incyyteModel.getGender() != null && !incyyteModel.getGender().equals("select") ? incyyteModel.getGender() : null);
        filter.setCategory(incyyteModel.getCategory() != null ? incyyteModel.getCategory() : null);
        Long count = questnSrv.countMembersByCounty(incyyteModel.getCounty(), filter);
        incyyteSession.setPollRecipients(count);
        incyyteModel.setPollCost(count * 5);
        incyyteSession.setPollCost(count * 5);
        Long pollCost = count * 5;
        model.addAttribute("inCyyteForm", incyyteModel);
        modelMap.put("inCyyteForm", incyyteModel);
        incyyteSession.setAgeRange(incyyteModel.getAgeRange());
        incyyteSession.setRegion(incyyteModel.getRegion());
        incyyteSession.setCounty(incyyteModel.getCounty());
        incyyteSession.setLocality(incyyteModel.getLocality());
        incyyteSession.setCountry(incyyteModel.getCountry());
        incyyteSession.setSendType(incyyteModel.getSendType());
        incyyteSession.setPublic_poll(incyyteModel.getPublic_poll());
        incyyteSession.setAgeMin(incyyteModel.getAgeMin());
        incyyteSession.setAgeMax(incyyteModel.getAgeMax());
        incyyteSession.setGender(incyyteModel.getGender());
        incyyteSession.setCategory(incyyteModel.getCategory());
        incyyteSession.setPostcode(incyyteModel.getPostcode());
        Logger.debug("incyyteModel:Loaded:" + incyyteModel);
        Logger.debug("incyyteSession:Loaded:" + incyyteSession);
        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSession);
        return String.valueOf(count + "|" + pollCost);
    }


    /**
     * Trap Exceptions during the upload and show errors back in view form **
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        ModelAndView mv = new ModelAndView("error/maxupload_error_page");
        if (exception instanceof MaxUploadSizeExceededException) {
            mv.addObject("errors", "Maximum upload size of (" + ((MaxUploadSizeExceededException) exception).getMaxUploadSize() + ") bytes exceeded");
            Logger.debug("Maximum upload size of (" + ((MaxUploadSizeExceededException) exception).getMaxUploadSize() + ") bytes exceeded");
        } else {
            mv.addObject("errors", "Unexpected error: " + exception.getMessage());
            Logger.debug("Unexpected error: " + exception.getMessage());
        }

        IncyyteModel incyyteModel = (IncyyteModel) request.getSession().getAttribute(SessionKeys.INCYYTE_MODEL);
        mv.addObject("inCyyteForm", incyyteModel);
        mv.addObject("loginForm", new UserModel());
        mv.addObject("signUpForm", new UserModel());
        mv.addObject("categories", referenceData.getInCyyteCategories());
        mv.addObject("agegroups", referenceData.getAgeGroup());

        return mv;
    }

    @RequestMapping(value = "/postedpoll", method = RequestMethod.GET)
    public String postedPoll(ModelMap model, HttpSession session) {

        model.put("inCyyteForm", new IncyyteModel());
        model.put("loginForm", new UserModel());
        model.put("signUpForm", new UserModel());
        session.setAttribute(SessionKeys.INCYYTE, new InCyyte());

        // return form view
        return "main/posted_poll";
    }

    @ModelAttribute("groupList")
    public List<Group> getGroupList(HttpSession session) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        if (user != null && user.getId() != null) {
            List<Group> userGroups = groupService.getUserGroups(user.getId());
            return userGroups;
        }
        return null;
    }


    public void nullValues(IncyyteModel incyyteModel, boolean clearOptionValues) {
        if (incyyteModel.getAnswer_upload_opt().equals("1")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 1 getAnswerUploadedType1 -> " + incyyteModel.getAnswerUploadedType1());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 1 getAnswerUploadedFileName1-> " + incyyteModel.getAnswerUploadedFileName1());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName1());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType1());
            incyyteModel.setAnswerType1(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_1(null);
                incyyteModel.setAnswerChoice1(null);
            }
            incyyteModel.setAnswerFileName1(null);
            incyyteModel.setAnswerUploadedType1(null);
            incyyteModel.setAnswerUploadedFileName1(null);
            incyyteModel.setAnswer_uploaded_url_1(null);
            incyyteModel.setAnswer_file_1(null);
            incyyteModel.setAnswer_file_video_1(null);
            incyyteModel.setAnswer_file_photo_1(null);
            incyyteModel.setAnswer_file_doc_1(null);
            incyyteModel.setYouTubeAnswer_1_VideoId(null);
        } else if (incyyteModel.getAnswer_upload_opt().equals("2")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 2 getAnswerUploadedType2 -> " + incyyteModel.getAnswerUploadedType2());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 2 getAnswerUploadedFileName2-> " + incyyteModel.getAnswerUploadedFileName2());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName2());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType2());
            incyyteModel.setAnswerType2(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_2(null);
                incyyteModel.setAnswerChoice2(null);
            }
            incyyteModel.setAnswerFileName2(null);
            incyyteModel.setAnswerUploadedType2(null);
            incyyteModel.setAnswerUploadedFileName2(null);
            incyyteModel.setAnswer_uploaded_url_2(null);
            incyyteModel.setAnswer_file_2(null);
            incyyteModel.setAnswer_file_video_2(null);
            incyyteModel.setAnswer_file_photo_2(null);
            incyyteModel.setAnswer_file_doc_2(null);
            incyyteModel.setYouTubeAnswer_2_VideoId(null);
        } else if (incyyteModel.getAnswer_upload_opt().equals("3")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 3 getAnswerUploadedType3 -> " + incyyteModel.getAnswerUploadedType3());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 3 getAnswerUploadedFileName3-> " + incyyteModel.getAnswerUploadedFileName3());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName3());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType3());
            incyyteModel.setAnswerType3(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_3(null);
                incyyteModel.setAnswerChoice3(null);
            }
            incyyteModel.setAnswerFileName3(null);
            incyyteModel.setAnswerUploadedType3(null);
            incyyteModel.setAnswerUploadedFileName3(null);
            incyyteModel.setAnswer_uploaded_url_3(null);
            incyyteModel.setAnswer_file_3(null);
            incyyteModel.setAnswer_file_video_3(null);
            incyyteModel.setAnswer_file_photo_3(null);
            incyyteModel.setAnswer_file_doc_3(null);
            incyyteModel.setYouTubeAnswer_3_VideoId(null);
        } else if (incyyteModel.getAnswer_upload_opt().equals("4")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 4 getAnswerUploadedType4 -> " + incyyteModel.getAnswerUploadedType4());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 4 getAnswerUploadedFileName4-> " + incyyteModel.getAnswerUploadedFileName4());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName4());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType4());
            incyyteModel.setAnswerType4(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_4(null);
                incyyteModel.setAnswerChoice4(null);
            }
            incyyteModel.setAnswerFileName4(null);
            incyyteModel.setAnswerUploadedType4(null);
            incyyteModel.setAnswerUploadedFileName4(null);
            incyyteModel.setAnswer_uploaded_url_4(null);
            incyyteModel.setAnswer_file_4(null);
            incyyteModel.setAnswer_file_video_4(null);
            incyyteModel.setAnswer_file_photo_4(null);
            incyyteModel.setAnswer_file_doc_4(null);
            incyyteModel.setYouTubeAnswer_4_VideoId(null);
        } else if (incyyteModel.getAnswer_upload_opt().equals("5")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 5 getAnswerUploadedType5 -> " + incyyteModel.getAnswerUploadedType5());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 5 getAnswerUploadedFileName5-> " + incyyteModel.getAnswerUploadedFileName5());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName5());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType5());
            incyyteModel.setAnswerType5(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_5(null);
                incyyteModel.setAnswerChoice5(null);
            }
            incyyteModel.setAnswerFileName5(null);
            incyyteModel.setAnswerUploadedType5(null);
            incyyteModel.setAnswerUploadedFileName5(null);
            incyyteModel.setAnswer_uploaded_url_5(null);
            incyyteModel.setAnswer_file_5(null);
            incyyteModel.setAnswer_file_video_5(null);
            incyyteModel.setAnswer_file_photo_5(null);
            incyyteModel.setAnswer_file_doc_5(null);
            incyyteModel.setYouTubeAnswer_5_VideoId(null);
        } else if (incyyteModel.getAnswer_upload_opt().equals("6")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 6 getAnswerUploadedType6 -> " + incyyteModel.getAnswerUploadedType6());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 6 getAnswerUploadedFileName6-> " + incyyteModel.getAnswerUploadedFileName6());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName6());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType6());
            incyyteModel.setAnswerType6(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_6(null);
                incyyteModel.setAnswerChoice6(null);
            }
            incyyteModel.setAnswerFileName6(null);
            incyyteModel.setAnswerUploadedType6(null);
            incyyteModel.setAnswerUploadedFileName6(null);
            incyyteModel.setAnswer_uploaded_url_6(null);
            incyyteModel.setAnswer_file_6(null);
            incyyteModel.setAnswer_file_video_6(null);
            incyyteModel.setAnswer_file_photo_6(null);
            incyyteModel.setAnswer_file_doc_6(null);
            incyyteModel.setYouTubeAnswer_6_VideoId(null);
        } else if (incyyteModel.getAnswer_upload_opt().equals("7")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 7 getAnswerUploadedType7 -> " + incyyteModel.getAnswerUploadedType7());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 7 getAnswerUploadedFileName7-> " + incyyteModel.getAnswerUploadedFileName7());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName7());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType7());
            incyyteModel.setAnswerType7(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_7(null);
                incyyteModel.setAnswerChoice7(null);
            }
            incyyteModel.setAnswerFileName7(null);
            incyyteModel.setAnswerUploadedType7(null);
            incyyteModel.setAnswerUploadedFileName7(null);
            incyyteModel.setAnswer_uploaded_url_7(null);
            incyyteModel.setAnswer_file_7(null);
            incyyteModel.setAnswer_file_video_7(null);
            incyyteModel.setAnswer_file_photo_7(null);
            incyyteModel.setAnswer_file_doc_7(null);
            incyyteModel.setYouTubeAnswer_7_VideoId(null);
        } else if (incyyteModel.getAnswer_upload_opt().equals("8")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 8 getAnswerUploadedType8 -> " + incyyteModel.getAnswerUploadedType8());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 8 getAnswerUploadedFileName8-> " + incyyteModel.getAnswerUploadedFileName8());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName8());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType8());
            incyyteModel.setAnswerType8(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_8(null);
                incyyteModel.setAnswerChoice8(null);
            }
            incyyteModel.setAnswerFileName8(null);
            incyyteModel.setAnswerUploadedType8(null);
            incyyteModel.setAnswerUploadedFileName8(null);
            incyyteModel.setAnswer_uploaded_url_8(null);
            incyyteModel.setAnswer_file_8(null);
            incyyteModel.setAnswer_file_video_8(null);
            incyyteModel.setAnswer_file_photo_8(null);
            incyyteModel.setAnswer_file_doc_8(null);
            incyyteModel.setYouTubeAnswer_8_VideoId(null);
        } else if (incyyteModel.getAnswer_upload_opt().equals("9")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 9 getAnswerUploadedType9 -> " + incyyteModel.getAnswerUploadedType9());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 9 getAnswerUploadedFileName9-> " + incyyteModel.getAnswerUploadedFileName9());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName9());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType9());
            incyyteModel.setAnswerType9(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_9(null);
                incyyteModel.setAnswerChoice9(null);
            }
            incyyteModel.setAnswerFileName9(null);
            incyyteModel.setAnswerUploadedType9(null);
            incyyteModel.setAnswerUploadedFileName9(null);
            incyyteModel.setAnswer_uploaded_url_9(null);
            incyyteModel.setAnswer_file_9(null);
            incyyteModel.setAnswer_file_video_9(null);
            incyyteModel.setAnswer_file_photo_9(null);
            incyyteModel.setAnswer_file_doc_9(null);
            incyyteModel.setYouTubeAnswer_9_VideoId(null);
        } else if (incyyteModel.getAnswer_upload_opt().equals("10")) {
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 10 getAnswerUploadedType10 -> " + incyyteModel.getAnswerUploadedType10());
            Logger.debug("%%%%%%%%%%%%%%%Null values for Inside option 10 getAnswerUploadedFileName10-> " + incyyteModel.getAnswerUploadedFileName10());
            incyyteModel.setAnswerFileName(incyyteModel.getAnswerUploadedFileName10());
            incyyteModel.setAnswer_upload_type(incyyteModel.getAnswerUploadedType10());
            incyyteModel.setAnswerType10(null);
            if (clearOptionValues) {
                incyyteModel.setAnswer_opt_10(null);
                incyyteModel.setAnswerChoice10(null);
            }
            incyyteModel.setAnswerFileName10(null);
            incyyteModel.setAnswerUploadedType10(null);
            incyyteModel.setAnswerUploadedFileName10(null);
            incyyteModel.setAnswer_uploaded_url_10(null);
            incyyteModel.setAnswer_file_10(null);
            incyyteModel.setAnswer_file_video_10(null);
            incyyteModel.setAnswer_file_photo_10(null);
            incyyteModel.setAnswer_file_doc_10(null);
            incyyteModel.setYouTubeAnswer_10_VideoId(null);
        }
    }

    @RequestMapping(value = "/loadcontactlist", method = {RequestMethod.GET, RequestMethod.POST})
    public String loadContactList(ModelMap model, HttpSession session) {
        return "main/displayContactList";
    }

    @RequestMapping(value = "/validatepagename.cyt")
    @ResponseBody
    public String validatePageName(ModelMap model, @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, HttpSession session) {

        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        boolean existFlag = false;
        if (incyyteModel.getPageName() != null && user != null) {
            try {
                String pageName = incyyteModel.getPageName();
                List<InCyyte> cyytes = quickStartSrv.getUserInCyytesByUserId(user.getId(), Constants.SEND_BY_POST);

                for (InCyyte question : cyytes) {
                    if (question.getPageName() != null && question.getPageName().equalsIgnoreCase(pageName)) {
                        existFlag = true;
                        break;
                    }
                }
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
        }
        if (!existFlag)
            return "success";
        return "fail";
    }
}
