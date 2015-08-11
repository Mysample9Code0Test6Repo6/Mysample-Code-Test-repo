package com.incyyte.app.web.controller.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.UserModel;
import com.incyyte.app.web.model.UserSettingsModel;

@Controller
public class CreateAccountPageController {

	/**
	 * Logger for this class and subclasses
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private RegistrationService registrationSrv;

	@Autowired
	private LoginService loginSrv;

	@RequestMapping("/createAcct")
	public String createAccountpage(@ModelAttribute("signUpForm") UserModel userModel, BindingResult result, HttpSession session, Model model) {
		userModel = (UserModel) session.getAttribute("SIGNUP_MODEL");
        Logger.debug("Create Account controller: Start");
		if (null == userModel)
			userModel = new UserModel();

		Logger.debug("Username: " + userModel);
		// command object
		model.addAttribute("signUpForm", userModel);
		model.addAttribute("loginForm", new UserModel());

		// return form view
		return "main/createAccount";
	}

    @RequestMapping(value = "/noneActivateLogin.cyt", method = {RequestMethod.POST, RequestMethod.GET})
    public String noneAcivateLoginpage(@ModelAttribute("signUpForm") UserModel userModel, BindingResult result, HttpSession session, Model model) {
        userModel = (UserModel) session.getAttribute("SIGNUP_MODEL");
        String email = userModel.getSu_email();
        String password = userModel.getSu_password();
        Logger.debug("%%%%%%%%%%%%%%% Login username -## " + email + " - " + password);
        model.addAttribute("signUpForm", userModel);

        try {
            if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password)) {
                    User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
                    Logger.debug("%%%%%%%%%%%%%%% Login user - " + user);
                    UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(user.getId());
                    Logger.debug("userSettings::" + userSettings);
                    if (null != user) {
                        session.setAttribute(SessionKeys.LOGIN_USER, user);
                        session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
                    }
            }
        } catch (Exception e) {
            Logger.error("authenticateUserLogin: Failed " ,e);
            return "login";
        }

        return "dash";

    }


    @RequestMapping("/createAcctinvited")
    public String createAccountpageByinvite(@RequestParam("cd") String id, HttpSession session, Model model) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        if (id.equals("null")) {
            return "redirect:welcome.cyt";
        }

        String su_email = registrationSrv.getemailFrominviteid(id);
        boolean exists = false;
        if (null != su_email) {
            exists = registrationSrv.emailExists(su_email);
        }else{
        	return "error/expired_invitation_error_page";
        }
        
        if (exists && user == null) {
        	user = registrationSrv.getUserDetails(su_email, Constants.GET_BY_MAIL);
        	if(user != null)
        		session.setAttribute(SessionKeys.LOGIN_USER, user);
        }
        
        if (exists && user != null) {
            return "redirect:contactsHome.cyt?cd=" + id;
        } else if (exists) {
            return "redirect:login.cyt?cd=" + id;
        }
        UserModel userModel = new UserModel();
        userModel.setSu_email(su_email);
        // command object
        model.addAttribute("signUpForm", userModel);
        session.setAttribute("id", id);
        // return form view
        return "main/createAccount";
    }

    @RequestMapping(value = "/processUname.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processUsername(HttpServletRequest request, HttpSession session) throws Exception {
        Logger.debug("%%%%%% Signup processUname -> " + request.getParameter("action"));
        //check that Username is unique
        String username = request.getParameter("action");
        if (username == null)
            return "error-please provide a username";

        boolean exists = registrationSrv.usernameExists(username);
        if (exists)
            return "error-User name is NOT available";
        else
            return "success-User name is available";
    }

	@RequestMapping(value = "/processEmail.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String processEmail(HttpServletRequest request, HttpSession session) throws Exception {
		Logger.debug("%%%%%% Signup processEmail -> " + request.getParameter("action"));
		//check if email already exists
		String email = request.getParameter("action");
		if (email == null) {
			return "error-please provide an email";
		}
		boolean exists = registrationSrv.emailExists(email);
		if (exists)
			return "error-Email address is NOT available";
		else
			return "success-Email address is available";
	}

	@RequestMapping(value = "/processPwd.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String processPassword(HttpServletRequest request, HttpSession session) throws Exception {
		Logger.debug("%%%%%% Signup processPwd -> " + request.getParameter("action"));
		return "error-Password is NOT available";
	}

	@RequestMapping(value = "/androidCreateAcct.cyt",method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String androidCreateAcct(HttpServletRequest request, HttpSession session) throws Exception {
		//check if email already exists
		Logger.info("inside controller:::");
		String email = request.getParameter("email");
		String username = request.getParameter("username"); 
		String password = request.getParameter("password");
		
		Logger.info("username:"+username+":emai:"+email+":password:"+password);

		boolean usernameExists = registrationSrv.usernameExists(username);
		boolean emailExists = registrationSrv.emailExists(email);
		if (usernameExists) {
			return "usernamenotavailable";
		}else if (emailExists) {
			return "emailnotavailable";
		}

		try {
			Logger.info("inside createAcct.cyt");

			UserModel userModel = new UserModel();
			userModel.setUsername(username);
			userModel.setSu_email(email);
			userModel.setSu_password(password);
			//signup the user first
			User user = registrationSrv.signUpUser(MapUserProperty.copyUserSignUpDetails(userModel, request));
			//TODO add contact if via invitation

			Logger.info("Success data");
			return "success";
		} catch (Exception e) {
			Logger.error("Exception:",e);
			return "failure";
		}
	}

}
