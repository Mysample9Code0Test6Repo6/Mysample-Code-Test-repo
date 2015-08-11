/**
 *
 */
package com.incyyte.app.web.controller.dashboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incyyte.app.domain.BusinessAccount;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserDomain;
import com.incyyte.app.domain.UserLocation;
import com.incyyte.app.service.BusinessAccountService;
import com.incyyte.app.service.HomeService;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.Exceptions.InCyyteExceptions;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.service.util.UserIPLocator;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.MandatoryInfoModel;
import com.incyyte.app.web.model.UserSettingsModel;

/**
 * @author Remi Oseni
 */
@Controller
public class DashboardPageController extends DashboardBasePage implements Constants {

	final static int OFFSET = 10;

	/**
	 * Logger for this class and subclasses
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private HomeService homeSrv;

	@Autowired
	private QuickStartService quickStartSrv;

	@Autowired
	private BusinessAccountService businessAccountSrv;

	@Autowired
	private RefData referenceData;

	@Autowired
	private RegistrationService registrationSrv;

	@Autowired
	private LoginService loginSrv;

	private User user;

	@RequestMapping(value = "/dash.cyt", method = RequestMethod.GET)
	public String initForm(ModelMap model, HttpServletRequest request) {

		user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);

		String activationCode  = request.getParameter("actcode");    
		String email  = request.getParameter("email");
		if(user == null && activationCode != null)
			user = registrationSrv.getUserDetailsByEmailAndCode(email, activationCode);

		if (user == null || user.getId() == null) {
			//Logger.debug("%%%%%%%%%%%% Error: user not in session - return to login page");
			return "redirect:welcome.cyt";
		}

		request.getSession().setAttribute(SessionKeys.LOGIN_USER, user);

		model.put("detailsform",getMandatoryInfoModel(user));

		ModelMap map = checkMandatoryFields(user);
		if(map != null){
			model.addAllAttributes(map);
			return "dashboard/mandatory_info";
		}

		UserSettingsModel userSettings = (UserSettingsModel)request.getSession().getAttribute(SessionKeys.LOGIN_USER_SETTINGS);
		if(userSettings == null){
			try{
				userSettings = loginSrv.getUserSettingsDetails(user.getId());
				Logger.debug("userSettings::" + userSettings);
				request.getSession().setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
			}catch(Exception e){
				Logger.error("Failed userSettings : ",e);
			}
		}

		String param = request.getParameter("page");
		String sn = request.getParameter("sn"); 
		if (param != null && param.equals("details")) {
			String code = request.getParameter("code");
			String from = request.getParameter("frm");
			if (StringUtils.isNotEmpty(code)) {
				return "redirect:dashdetails.cyt?code="+code+"&frm="+from+"";
			}
		} else {
			if(null==sn)
				return "redirect:dashincomming.cyt";
			else
				return "redirect:dashincomming.cyt?sn";	
		}
		return "dashboard/dashboard";
	}

	@RequestMapping(value = "/aboutbiz.cyt", method = RequestMethod.GET)
	public String initAboutBizForm(ModelMap model, HttpServletRequest request) {

		user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);

		if (user == null || user.getId() == null) {
			//Logger.debug("%%%%%%%%%%%% Error: user not in session - return to login page");
			return "redirect:welcome.cyt";
		}


		MandatoryInfoModel infoModel =  new MandatoryInfoModel(); 

		if(user.getBirthYear() != null && !user.getBirthYear().equals(0))
			infoModel.setBirthYear(user.getBirthYear().toString());

		infoModel.setCountryCode(user.getCountryCode());
		infoModel.setGender(user.getGender());
		infoModel.setPostalCodeArea(user.getPostalCodeArea());
		infoModel.setUserId(user.getId());
		model.put("detailsform",infoModel);
		model.put("page", "aboutBizAcct");
		model.put("inCyyteForm", new IncyyteModel());

		return "dashboard/dashboard";
	}

	@RequestMapping(value = "/createBizAcct.cyt", method = RequestMethod.GET)
	public String initCreateBizForm(@ModelAttribute("bizAccountForm") BusinessAccount modelBusinessAccount, ModelMap model, HttpServletRequest request, HttpSession session) {
		user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
		if (user == null || user.getId() == null) {
			//Logger.debug("%%%%%%%%%%%% Error: user not in session - return to login page");
			return "redirect:welcome.cyt";
		}
		//Logger.debug("dashboard page controller:user object:::" + user);

		BusinessAccount businessAccount = new BusinessAccount();
		if (StringUtils.equals(user.getUserType(), "BUSINESS")) {
			//By passing User Id value get the Business Account object
			businessAccount = businessAccountSrv.getBussinessAccountInfo(user.getId());
			//Logger.debug("dashboard page controller:businessaccount:::" + businessAccount);
			model.addAttribute("bizAccountForm", businessAccount);
			session.setAttribute("bizAccount", businessAccount);
		}
		MandatoryInfoModel infoModel =  new MandatoryInfoModel();

		if(user.getBirthYear() != null && !user.getBirthYear().equals(0))
			infoModel.setBirthYear(user.getBirthYear().toString());

		infoModel.setCountryCode(user.getCountryCode());
		infoModel.setGender(user.getGender());
		infoModel.setPostalCodeArea(user.getPostalCodeArea());
		infoModel.setUserId(user.getId());
		model.put("detailsform",infoModel);

		model.put("page", "createBizAcct");

		if (StringUtils.equals(user.getUserType(), "USER")) {
			BusinessAccount sesionBusinessAccount = (BusinessAccount) session.getAttribute("bizAccount");
			/*Logger.debug("dashboard page ::::::::::::::::::::modelBusinessAccount:::" + modelBusinessAccount);
            Logger.debug("dashboard page ::::::::::::::::::::sesionBusinessAccount:::" + sesionBusinessAccount);*/
			if (sesionBusinessAccount == null) {
				sesionBusinessAccount = modelBusinessAccount;
			}
			sesionBusinessAccount.setUserid(user.getId());
			session.setAttribute("bizAccount", sesionBusinessAccount);
			model.addAttribute("bizAccountForm", sesionBusinessAccount);
		}

		/*Logger.debug("businessAccount::::::::::::::::::::::" + businessAccount);
        Logger.debug("modelBusinessAccount:::::::::::::::::" + modelBusinessAccount);*/
		return "dashboard/dashboard";
	}




	@RequestMapping(value = "/ask_question.cyt", method = RequestMethod.GET)
	public String askQuestion(ModelMap model, HttpSession session) {
		session.setAttribute(SessionKeys.INCYYTE_MODEL, new IncyyteModel());
		user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		if (user != null && user.getId() != null) {
			if(user.getCountryCode() == null || user.getCountyName() == null){
				UserDomain domain = quickStartSrv.getUserDomain(user.getId());
				if(domain != null){
					user.setCountryCode(domain.getCountryCode().toString());
					user.setCountyName(domain.getCountyName());
					user.setPostalCodeArea(domain.getPostalCode());
					session.setAttribute(SessionKeys.LOGIN_USER, user);
				}
			}
		}
		// return form success view
		return "redirect:create_question.cyt";
	}

	@ModelAttribute("myInCyytes")
	public List<InCyyteChart> mySentInCyytes(HttpSession session) {
		return this.getMyInCyytes(session);
	}

	@ModelAttribute("myPollPages")
	public List<InCyyte> myPostInCyytes(HttpSession session) throws InCyyteExceptions {
		return this.getMyPollInCyytes(session);
	}

	@ModelAttribute("regionIncyytes")
	public List<InCyyteChart> myRegionCyyte(HttpSession session) {
		return this.getRegionInCyytes(session);
	}

	@RequestMapping(value = "/deleteincyyte.cyt", method = RequestMethod.GET)
	public String deleteIncyyte(HttpServletRequest request) {
		String code = request.getParameter("code");
		String pageName = request.getParameter("pageName");
		user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);

		if (code != null && user != null && user.getId() != null) {
			homeSrv.deleteIncyyte(Long.valueOf(code));
		}

		if(StringUtils.equalsIgnoreCase(pageName, "sent")){
			request.getSession().setAttribute("mySentInCyytes", null);
			request.getSession().removeAttribute("mySentInCyytes");
			return "redirect:dashsent.cyt";
		} else if(StringUtils.equalsIgnoreCase(pageName, "mypolls")){
			request.getSession().setAttribute("myPostInCyytes", null);
			request.getSession().removeAttribute("myPostInCyytes");
			return "redirect:dashpost.cyt";
		} else {
			return "redirect:dash.cyt";
		}
	}

	@RequestMapping(value = "/updtincyyte.cyt", method = RequestMethod.GET)
	public String updateincyyteType(HttpServletRequest request) {
		String code = request.getParameter("code");
		String type = request.getParameter("type");
		user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
		if (code == null || user == null || user.getId() == null) {
			return "redirect:dash.cyt";
		}
		homeSrv.updateincyyteType(Long.valueOf(code),type);
		return "redirect:dashsent.cyt";

	}    

	@RequestMapping(value = "/updatedatetime.cyt", method = RequestMethod.POST)   
	public String updateDateTime(@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
			ModelMap model, HttpServletRequest request) {

		//Logger.debug("%%%%%%%% updatedatetime :  id - " + incyyteModel.getId() + "   closure date - " + incyyteModel.getClosureDate());
		try {
			if (incyyteModel.getClosureDate() != null && incyyteModel.getId() != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date = dateFormat.parse(incyyteModel.getClosureDate());
				homeSrv.editClosingDateTime(incyyteModel.getId(), date);                
			}
		} catch (Exception e) {
			Logger.error(e);
		}

		String param = request.getParameter("page");
		if(param !=null && param.equals("mypolls")){
			request.getSession().setAttribute("myPostInCyytes", null);
			request.getSession().removeAttribute("myPostInCyytes");
			return "redirect:dashpost.cyt";
		}else if(param !=null && param.equals("sent")){
			request.getSession().setAttribute("mySentInCyytes", null);
			request.getSession().removeAttribute("mySentInCyytes");
			return "redirect:dashsent.cyt";
		}else {
			return "redirect:dash.cyt";
		}
	}

	@RequestMapping(value = "/submitMandatoryInfo.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String submitMandatoryInfo(@ModelAttribute("detailsform") MandatoryInfoModel infoModel,
			ModelMap model, HttpSession session , HttpServletRequest request) {
		Logger.debug("infoModel::"+infoModel);
		try {
			user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
			Logger.debug("user::"+user);
			if (user != null && user.getId() != null && infoModel.getPostalCodeArea() != null) {
				infoModel.setUserId(user.getId());
				if(user.getUsername() == null && infoModel.getUsername() != null){				
					try {
						user.setUsername(infoModel.getUsername());
						registrationSrv.insertUserEmail(user.getId(), user.getUsername(), user.getEmail());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}	
				user.setPassword(infoModel.getSu_password());
				user.setFirstname(infoModel.getFirstname());
				user.setLastname(infoModel.getLastname());
				user.setBirthYear(Integer.valueOf(infoModel.getBirthYear()));
				user.setGender(infoModel.getGender());
				user.setPostalCodeArea(infoModel.getPostalCodeArea());
				user.setCountryCode(infoModel.getCountryCode());                
				user.setOccupation(infoModel.getOccupation());
				user.setIncome(infoModel.getIncome());
				user.setEthnicity(infoModel.getEthnicity());
				user.setEducationLevel(infoModel.getEducationLevel());
				user.setAdultsInHouseHold(infoModel.getAdultsInHouseHold());
				user.setChildrenInHouseHold(infoModel.getChildrenInHouseHold());

				quickStartSrv.updateUsersMandatoryInfo(user);
				quickStartSrv.insertUserDomain(user.getId(), user.getPostalCodeArea(),user.getCountryCode());

				session.setAttribute(SessionKeys.LOGIN_USER, user);
				return "success";
			}else{
				Logger.debug("indside Else");
				boolean exists = registrationSrv.usernameExists(infoModel.getUsername());
				Logger.debug("exists ::"+exists);
				if(exists){
					return "username already exists";
				}

				User user = new User();
				user.setUsername(infoModel.getUsername());
				user.setEmail(infoModel.getEmail());
				user.setPassword(infoModel.getSu_password()); //TODO
				user.setCountryCode(infoModel.getCountryCode());
				user.setPostalCodeArea(infoModel.getPostalCodeArea());
				user.setGender(infoModel.getGender());
				user.setBirthYear(Integer.parseInt(infoModel.getBirthYear()));
				user.setFirstname(infoModel.getFirstname());
				user.setLastname(infoModel.getLastname());
				user.setStatus("ACTIVE");
				user.setAcceptTerms("1");
				user.setSignupmode(infoModel.getSn_mode());
				user.setProfilePicture(infoModel.getCdnFileName());
				user = getSignUpLocal(user, request);
				try {
					user = registrationSrv.signUpUserSN(user);
					Logger.debug("user:from reg Srv:"+user);
					if (null != user) {
						session.setAttribute("user", user);
						return "SNsuccess";
					}
				} catch (Exception e) {
					Logger.error("Exception:",e);
				}
				session.removeAttribute("user");
				// return form success view
				return "failure";
			}
		} catch (Exception e) {
			Logger.error(e);
		}
		return "failure";
	}

	private static User getSignUpLocal(User user,HttpServletRequest request){
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
			user.setLocation(location);
		}
		return user;

	}

	private static UserLocation getUserLocation(String hostIP, HttpServletRequest request){
		UserIPLocator obj = UserIPLocator.getInstance();
		UserLocation location = obj.getLocation(hostIP, request);
		if (location != null) location.setIpAddress(hostIP);
		return location;
	}

	@RequestMapping(value = "/dashCheckList.cyt",method = {RequestMethod.POST, RequestMethod.GET})   
	public  String dashCheck(ModelMap model, HttpSession session) throws Exception {
		Logger.debug("enter into contorller");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("user::"+user);
		try{
			registrationSrv.updateCheckedList(user);
			user.setDisplayCheckList("Y");
			session.setAttribute(SessionKeys.LOGIN_USER, user);
			return "redirect:dash.cyt";
		}catch (Exception e) {
			Logger.error("Updating of checked list has been failed",e);
		}
		return "redirect:dash.cyt";
	}
}