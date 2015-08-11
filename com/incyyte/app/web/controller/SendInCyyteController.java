package com.incyyte.app.web.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.Group;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.security.InCyyteSecurity;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.ForgotPasswordService;
import com.incyyte.app.service.GroupService;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.PollEmailCountService;
import com.incyyte.app.service.QuestionService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.UserSettingsService;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.QuestionEnumType;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.service.util.UIDCodeGenerator;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.AnswerModel;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserModel;

@Controller
@RequestMapping("/send_question")
public class SendInCyyteController extends BaseContoller implements Constants {

	/**
	 * Logger for this class and subclasses
	 */
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ContactService contactsSrv;
	@Autowired
	private RefData referenceData;
	@Autowired
	private LoginService loginSrv;
	@Autowired
	private RegistrationService registrationSrv;
	@Autowired
	private ForgotPasswordService forgotPwdSrv;
	@Autowired
	private QuickStartService quickStartSrv;
	@Autowired
	private GroupService groupService;
	@Autowired
	private QuestionService questnSrv;
	@Autowired
	private PollEmailCountService pollEmailCountSrv;
	@Autowired
	private UserDao userDaoImpl;


	@Autowired
	private UserSettingsService userSettingsService;

	@Value("${strapline.max.char}")
	private String straplineMaxChar;

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		IncyyteModel incyyteModel = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);

		Logger.debug("%%%%%%%%%% initForm answers - > " + incyyteModel.getAnswerArr());
		Logger.debug("%%%%%%%%%% initForm answer size - > " + incyyteModel.getAnswers().size());

		UserModel userModel = new UserModel();
		userModel.setIncyyteModel(incyyteModel);

		session.setAttribute("straplineMaxChar", straplineMaxChar);

		model.put("inCyyteForm", incyyteModel);
		model.put("signUpForm", userModel);
		model.put("loginForm", userModel);

		// return form view
		return "send_question";
	}
	@RequestMapping(value = "/createAcct.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String processSubmit(HttpServletRequest request, @ModelAttribute("signUpForm") UserModel userModel, BindingResult result, SessionStatus status, HttpSession session) {
		try {
			Logger.debug("inside createAcct.cyt");
			Logger.debug("userModel: " + userModel);
			//signup the user first
			User user = registrationSrv.signUpUser(MapUserProperty.copyUserSignUpDetails(userModel, request));
			User userCreated = loginSrv.getUserDetails(userModel.getSu_email(), Constants.GET_BY_MAIL);
			if (null != userCreated && userCreated.getResetPwdFlag().equalsIgnoreCase("N")) {
				session = InCyyteSecurity.getInstance().changeSessionIdentifier(request);
				session.setAttribute(SessionKeys.LOGIN_USER, userCreated);
				InCyyte incyyte = (InCyyte) session.getAttribute(SessionKeys.INCYYTE);
				if (incyyte != null) {
					if (processInCyyte(userCreated, incyyte, request)) {
						String invitationId = (String) session.getAttribute("id");
						if (invitationId != null) {
							String email = registrationSrv.getInvitedemailByInvId(invitationId);

							UserContactModel cm = new UserContactModel();
							cm.setEmail(email);
							cm.setAccept_inv("Y");

							contactsSrv.addContact(cm, user.getId());
							contactsSrv.addAsContact(invitationId);
						}
						Logger.debug("Success data");	
						return "success";
					}
					else{
						return "failure";
					}

				}
				return "success";
			}

			//add contact if via invitation
		} catch (Exception e) {
			Logger.error("Exception:",e);
		}
		Logger.debug("outside createAcct.cyt");
		return "failure";
	}

	@RequestMapping(value = "/preview", method = RequestMethod.POST)
	@ResponseBody
	public String processPreview(
			@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
			BindingResult result, SessionStatus status, HttpSession session , HttpServletRequest request ) {
		try {
			Logger.debug("$$$$$$ processPreview $$$$$$");
			setInCytteModel(incyyteModel, session);
			return "success ";
		} catch (Exception e) {
			Logger.error("Exception:",e);
		}
		return "failure";
	}

	@RequestMapping(value = "/displaypreview", method = RequestMethod.GET)
	public String displayPreview(ModelMap model, HttpSession session) {
		Logger.debug("$$$$$$ displayPreview $$$$$$");
		// return form view
		return "main/preview_incyyte";
	}

	@RequestMapping(value = "/submit.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String processSubmit(HttpServletRequest request,
			@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
			BindingResult result, SessionStatus status, HttpSession session) throws Exception {
		Logger.debug("incyyteModel::" + incyyteModel);
		setInCytteModel(incyyteModel, session);
		InCyyte incyyte = MapUserProperty.copyInCyyteDetails(incyyteModel);
		session.setAttribute(SessionKeys.INCYYTE, incyyte);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);

		session.setAttribute("myIncomeinCyyte", null);
		session.setAttribute("mySentInCyytes", null);
		session.setAttribute("myRegionInCyytes", null);

		session.removeAttribute("myIncomeinCyyte");
		session.removeAttribute("mySentInCyytes");
		session.removeAttribute("myRegionInCyytes");

		if (user != null && (StringUtils.equalsIgnoreCase(user.getStatus(), "DEACTIVATED") || StringUtils.equalsIgnoreCase(user.getStatus(), "NON_ACTIVE"))){
			Logger.debug("account is not activated or is deactivated");
			return "deactivated";			
		}

		if (user != null && user.getId() != null) {
			try {
				if (processInCyyte(user, incyyte, request)) {
					// return form success view
					if (SEND_BY_POST.equals(incyyte.getSendType())){
						String generatedURL = getGeneratedPostedUrl(user, incyyte, request);
						return "POST "+generatedURL+"?p=share";
					}
					
					return "dash";
				} else if (incyyte.getPollEmailCount() != 0)  {
					return "pollCountError";
				} else {
					return "no_msg_sent";
				}
			} catch (Exception e) {
				Logger.error("error while sending question:", e);
			}
		}
		return "send_question";
	}

	@RequestMapping(value = "/complete.cyt", method = RequestMethod.GET)
	public String processComplete(HttpServletRequest request, HttpSession session) throws Exception {
		Logger.debug("%%%%%% Signup Completed -> ");
		return "completeSignup";
	}

	@RequestMapping(value = "/login.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String processAjaxLogin(HttpServletRequest request,
			@ModelAttribute("loginForm") UserModel userModel,
			BindingResult result, SessionStatus status, HttpSession session) throws Exception {

		User user = null;
		String email = userModel.getLogin_email();
		String password = userModel.getLogin_pwd();
		Logger.debug("%%%%%%%%%%%%%%% Login Email - " + email + "  password - ");
		try {
			if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password)) {
                User authenticatedUser = loginSrv.authenticateUserLogin(email, password);
				if (authenticatedUser != null) {
					user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
					Logger.debug("user.getStatus()::"+user.getStatus());
					if(StringUtils.equalsIgnoreCase(user.getStatus(), "DEACTIVATED") || StringUtils.equalsIgnoreCase(user.getStatus(), "NON_ACTIVE")){
						Logger.debug("account is not activated or is deactivated");
						return "Deactivated";
					}

					if (null != user && user.getResetPwdFlag().equalsIgnoreCase("N")) {
						session = InCyyteSecurity.getInstance().changeSessionIdentifier(request);
						session.setAttribute(SessionKeys.LOGIN_USER, user);
						InCyyte incyyte = (InCyyte) session.getAttribute(SessionKeys.INCYYTE);
						if (incyyte != null) {
							if (processInCyyte(user, incyyte, request)) {
								if (SEND_BY_POST.equals(incyyte.getSendType())){
									String generatedURL = getGeneratedPostedUrl(user, incyyte, request);
									return "POST "+generatedURL+"?p=share";
								}

								return "success";
							} else {
								return "failure";
							}
						}
						return "success";
					}
				}else {
					Logger.debug("authenticateUserLogin: false -  Redirect to LOGIN...");
					return "login";
				}
			}
		} catch (AuthenticationException ae) {
			Logger.warn("ERROR: Login Email - "+ email);
			return "login";
		}catch (Exception e) {
			Logger.error("ERROR: Login Email - " + email);
			return "failure";		
		}
		session.removeAttribute("user");
		// return form success view
		return "failure";
	}

	@RequestMapping(value = "/fpassword.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String processForgotPassword(@ModelAttribute("loginForm") UserModel userModel) {

		try {
			User user = loginSrv.getUserDetails(userModel.getUser_email(), Constants.GET_BY_MAIL);

			if (user != null && user.getEmail() != null) {
				forgotPwdSrv.requestNewPassword(userModel.getUser_email());
				return "success";
			}
		} catch (Exception e) {
			Logger.error("Exception:",e);
		}
		// return form success view
		return "failure";
	}


	@ModelAttribute("groupTypes")
	public Map<String, String> populateGroupTypeList() {
		// Data referencing for categories list box
		return referenceData.getGroupType();
	}

	@ModelAttribute("genders")
	public Map<String, String> populateGenderList() {
		// Data referencing for categories list box
		return referenceData.getGender();
	}

	@ModelAttribute("ageGroups")
	public Map<String, String> populateAgeGroupList() {
		// Data referencing for categories list box
		return referenceData.getAgeGroup();
	}


	private boolean processInCyyte(User user, InCyyte incyyte, HttpServletRequest request) throws Exception {
		if (SEND_BY_MAIL.equals(incyyte.getSendType())) {
			return processEmail(user, incyyte, request);
		} else if (SEND_BY_POST.equals(incyyte.getSendType())) {
			return processPostScript(user, incyyte, request);
		} else if (SEND_BY_AREA.equals(incyyte.getSendType())) {
			Long incyyteId = quickStartSrv.processArea(user, incyyte, (ServletContext) request.getSession().getServletContext());
			Logger.debug("incyyteId:inSendincyytecntr:" + incyyteId);
			request.getSession().setAttribute(SessionKeys.INCYYTE_ID, incyyteId);
			return (incyyteId != null);
		}
		return false;
	}

	private boolean processPostScript(User user, InCyyte incyyte, HttpServletRequest request) throws Exception {

		Logger.debug("%%%%%%%%%% page name - > " + incyyte.getPageName());
		Logger.debug("%%%%%%%%%% processPostScript answer size - > " + incyyte.getAnswers().size());

		if (incyyte.getProtectPage() != null && incyyte.getProtectPage().equals("Y")) {
			UIDCodeGenerator generator = new UIDCodeGenerator();
			incyyte.setAccess_code(generator.getActivationCode());
		}

		incyyte.setCreatedBy(user.getUsername());

		request.getSession().setAttribute(SessionKeys.INCYYTE, incyyte);
		addInCyyteByPosting(user, incyyte, request);

		String buildedURL =  getGeneratedPostedUrl( user, incyyte, request);
		//registrationSrv.postScriptEmail(user, urlbuilder.toString());
		registrationSrv.postScriptEmail(user,  buildedURL);

		return true;
	}
	
	private String getGeneratedPostedUrl(User user, InCyyte incyyte, HttpServletRequest request){
		Logger.debug("%%%%%%%%%% page name - > " + incyyte.getPageName());

		request.getSession().setAttribute(SessionKeys.INCYYTE, incyyte);

		String serverURL = Utility.getServerURL(request);

		String pageName = userSettingsService.getUniquePageName(user.getId());

		StringBuilder urlbuilder = new StringBuilder(serverURL).append("/");
		if(pageName != null && !pageName.isEmpty()){
			urlbuilder.append(pageName).append("/");			
		}
		else{
			urlbuilder.append(user.getUsername()).append("/");
		}
		urlbuilder.append(incyyte.getPageName()).append(".cyt");

		Logger.debug("%%%%%%%%%5 postscript url -> " + urlbuilder.toString());

		return urlbuilder.toString();
	}

	public boolean processEmail(User user, InCyyte incyyte, HttpServletRequest request) throws Exception {
		if (incyyte.getGrpId() != null && !incyyte.getGrpId().equals("")) {
			Group group = groupService.getGroup(user.getId(), incyyte.getGrpId());
			incyyte.setGroup(group);
			incyyte.setContacts(Arrays.asList(group.getSelectedGroupContacts()));
		}

		Logger.debug("%%%%% Senderfname - " + incyyte.getSenderfname() + "   Senderlname - " + incyyte.getSenderlname());
		if (StringUtils.isNotEmpty(incyyte.getSenderfname()) || StringUtils.isNotEmpty(incyyte.getSenderlname())) {
			if (StringUtils.isEmpty(user.getFirstname())) {
				user.setFirstname(incyyte.getSenderfname());
			}
			if (StringUtils.isEmpty(user.getLastname())) {
				user.setLastname(incyyte.getSenderlname());
			}
			quickStartSrv.updateUsersName(user.getEmail(), user.getFirstname(), user.getLastname());
		}

		if (incyyte.isAnonymity())
			incyyte.setCreatedBy(Constants.ANONYMOUS);
		else
			incyyte.setCreatedBy(user.getUsername());

		Long incyyteId = null;

		if (incyyte.getGrpId() != null && !incyyte.getGrpId().equals("")) {
			incyyteId = addInCyyteByGroup(user, incyyte, request);
		} else {
			incyyteId = addInCyyteByEmails(user, incyyte, request);
		}

		//TODO:: Track the  poll Emails Here and Insert them.
		ConfigManager icfg = ConfigManager.getInstance();
		int oldCountOfPollemails = pollEmailCountSrv.getPollEmailCountInfo(user);
		Logger.debug("oldCountOfPollemails:::"+oldCountOfPollemails);
		incyyte.setPollEmailCount(oldCountOfPollemails);
		List<Contact> contacts = userDaoImpl.getInCyyteMailList(incyyteId);
		List<String>  validEmails = pollEmailCountSrv.validatePollEmails(contacts, incyyte.isAnonymity(), user);
		Logger.debug("validEmails:::"+validEmails);
		int count = validEmails.size();
		int cntInclPresentEmails  = oldCountOfPollemails + count;
		Logger.debug("cntInclPresentEmails:: "+cntInclPresentEmails);
		if(oldCountOfPollemails == 0 || StringUtils.isBlank(String.valueOf(oldCountOfPollemails))){
			Logger.debug("user sending first time emails::");
			pollEmailCountSrv.storePollEmailCount(user, incyyte, count);
			int newCount = pollEmailCountSrv.getPollEmailCountInfo(user);
			Logger.debug("first count :1::"+newCount);
			sendIncyyteEmail(user, incyyte.isAnonymity(), incyyteId, request);
			return true;
		} else if (oldCountOfPollemails != 0 && (cntInclPresentEmails > Integer.valueOf(icfg.getProperty(ConfigProperties.POLL_EMAIL_COUNT_LIMIT))) ){
			Logger.debug("user reached his limit of emails:::");
			return false;	
		}else if(cntInclPresentEmails <= Integer.valueOf(icfg.getProperty(ConfigProperties.POLL_EMAIL_COUNT_LIMIT)) ){
			Logger.debug("sending second or other times::");
			pollEmailCountSrv.storePollEmailCount(user, incyyte, count);
			int newCount = pollEmailCountSrv.getPollEmailCountInfo(user);
			Logger.debug("count:3::"+newCount);
			sendIncyyteEmail(user, incyyte.isAnonymity(), incyyteId, request);
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/uploadLogoFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadMultipartFile(HttpServletRequest request, @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, BindingResult result, SessionStatus status, HttpSession session) throws Exception {

		Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile");
		IncyyteModel model = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
		if (!incyyteModel.getUploadedLogo().isEmpty()) {
			Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile name -> " + incyyteModel.getUploadedLogo().getOriginalFilename());
			model.setUploadedLogo(incyyteModel.getUploadedLogo());

			String remoteLocation = uploadFileToRackSpace(incyyteModel.getUploadedLogo());
			model.setUploadedLogoLocation(remoteLocation);

			Logger.debug("%%%%%%%%%%%%%%% here - uploadMultipartFile location -> " + model.getUploadedLogoLocation());
			request.setAttribute("originalFilename", incyyteModel.getUploadedLogo().getOriginalFilename());

			session.setAttribute(SessionKeys.INCYYTE_MODEL, model);
			return "success " + incyyteModel.getUploadedLogo().getOriginalFilename();
		}
		return null;
	}

	@RequestMapping(value="/paymentProcess.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String paymentInformation( @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, HttpSession session, HttpServletRequest request) {
		Logger.debug("$$$$$$$$$ paymentInformation Begin $$$$$$$$$ "+incyyteModel);
		setInCytteModel(incyyteModel, session);
		Logger.debug("incyyteModel::incyyteModel"+incyyteModel);
		IncyyteModel incyyteSessionModel = (IncyyteModel) session.getAttribute("inCyyteForm");
		Logger.debug("$$$$$$$$$ paymentInformation Begin $$$$$$$$$ "+incyyteSessionModel);
		session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteSessionModel);
		return "success";
	}

	private IncyyteModel setInCytteModel(IncyyteModel incyyteModel, HttpSession session) {
		Logger.debug("$$$$$$$$$ setInCytteModel Begin $$$$$$$$$ ");
		IncyyteModel model = (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
		incyyteModel.setPollCharge(model.getPollCharge());
		Logger.debug("InCyyte from session:" + model);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		if (model != null && model.getUploadedFile() != null && model.getUploadedFile().getSize() > 0) {
			Logger.debug("$$$$$$$$$ filename - > " + model.getUploadedFile().getOriginalFilename());
			incyyteModel.setUploadedFile(model.getUploadedFile());
			incyyteModel.setFileName(model.getFileName());
			incyyteModel.setUploadedFileLocation(model.getUploadedFileLocation());
			incyyteModel.setContentType(model.getContentType());
			incyyteModel.setUploadedFileName(model.getUploadedFileName());
			incyyteModel.setUploadedFileType(model.getUploadedFileType());
		} else if (model != null && model.getFileName() != null) {
			Logger.debug("Searched Image Load");
			incyyteModel.setUploadedFile(model.getUploadedPhotoFile());
			incyyteModel.setFileName(model.getFileName());
			incyyteModel.setUploadedFileLocation(model.getUploadedFileLocation());
			incyyteModel.setContentType(model.getContentType());
			incyyteModel.setUploadedFileName(model.getUploadedFileName());
			incyyteModel.setUploadedFileType(model.getUploadedFileType());
			Logger.debug("$$$$$$$$$ incyyteModel:" + incyyteModel);
		} else {
			Logger.debug("$$$$$$$$$ filename is empty ");
		}
		if (user != null) {
			if (incyyteModel.isAnonymity())
				incyyteModel.setCreatedBy(Constants.ANONYMOUS);
			else
				incyyteModel.setCreatedBy(user.getUsername());
			String profile_picture_url = questnSrv.getProfilePicUrl(user.getEmail());
			incyyteModel.setCreatedbyImageLink(profile_picture_url);
		} else {
			if (incyyteModel.isAnonymity())
				incyyteModel.setCreatedBy(Constants.ANONYMOUS);
			else
				incyyteModel.setCreatedBy("GUEST");

		}
		incyyteModel.setQuesType(QuestionEnumType.QType_Q.getType());

		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMMM yyyy");
		incyyteModel.setCreatedDate(dateFormat.format(today));

		UIDCodeGenerator generator = new UIDCodeGenerator();
		incyyteModel.setPollRef(StringUtils.defaultString(generator.getPollRefCode()));
		prefixLink(incyyteModel);
		List<AnswerModel> answers = getAnswers(incyyteModel);
		incyyteModel.setAnswers(answers);

		if (model != null)
			incyyteModel.setAnswer_count(model.getAnswer_count());

		session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteModel);
		Logger.debug("incyyteModel:at end of set incyyte model: "+incyyteModel);
		return incyyteModel;
	}
}