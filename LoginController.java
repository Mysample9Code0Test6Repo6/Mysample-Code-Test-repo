package com.incyyte.app.web.controller;

import com.incyyte.app.domain.User;
import com.incyyte.app.security.InCyyteSecurity;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.service.ForgotPasswordService;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.UserModel;
import com.incyyte.app.web.model.UserSettingsModel;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private LoginService loginSrv;
    @Autowired
    private ForgotPasswordService forgotPwdSrv;
    @Autowired
    private RegistrationService registrationSrv;

    @RequestMapping(value = "/retry.cyt" , method = RequestMethod.GET)
    public String initForm(ModelMap model, HttpSession session) {

        Logger.debug("$$$$ LoginController:  initForm");
        model.put("loginForm" , new UserModel());
        model.put("signUpForm" , new UserModel());

        // return form success view
        return "main/login";
    }


    @RequestMapping(value = "/submit.cyt" , method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String processLogin(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("loginForm") UserModel userModel, Model model, HttpSession session) throws Exception {
        String invId = request.getParameter("cd");
        Logger.debug("login controller::::::::invitationId" + invId);
        String email = userModel.getLogin_email();
        String password = userModel.getLogin_pwd();
        Logger.debug("%%%%%%%%%%%%%%% Login username - " + email + " - " + password);
        model.addAttribute("signUpForm" , userModel);
        try {
            if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password)) {
            	Cookie loginCookie = new Cookie("email",email);
            	//setting cookie to expiry in 30 mins
                loginCookie.setMaxAge(900);
                loginCookie.setPath("/");
                response.addCookie(loginCookie);
                User authenticatedUser = loginSrv.authenticateUserLogin(email, password);
                Logger.debug("user.getStatus()::" + authenticatedUser);
                if (authenticatedUser != null) {
                    Logger.debug("%%%%%%%%%%%%%%% Login user - " + authenticatedUser);
                        UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(authenticatedUser.getId());
                        Logger.debug("userSettings::" + userSettings);
                        Logger.debug("login controller:::::::::::invId:user not null" + invId);
                        Logger.debug("After Login: Invitation Id:" + session.getAttribute("invId"));
                        Logger.debug("Session Id before - " + session.getId());
                        session = InCyyteSecurity.getInstance().changeSessionIdentifier(request);
                        Logger.debug("Session Id after - " + session.getId());
                        session.setAttribute(SessionKeys.LOGIN_USER, authenticatedUser);
                        session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
                        if (StringUtils.isNotBlank(invId)) {
                            Logger.debug("login controller:::::::::::invId is not null:" + invId);
                            return "contacthome";
                        } else {
                            if (authenticatedUser.getResetPwdFlag().equalsIgnoreCase("N")) {
                                return "dash";
                            }
                            return "resetPassword";
                        }
                } else {
                    Logger.debug("authenticateUserLogin: false -  Redirect to LOGIN...");
                    return "login";
                }
            }
        } catch (AuthenticationException e) {
            //while login details are incorrect we will get Authentication exception
            return "login";
        }
        session.removeAttribute(SessionKeys.LOGIN_USER);
        // return form success view
        throw new Exception();
    }

    @RequestMapping(value = "/authenticateLogin.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String authenticateLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        Logger.info("Start authenticateLogin Process");
        String email = request.getParameter("email");
        String password = request.getParameter("pwd");
        session.setAttribute(SessionKeys.LOGIN_ADMIN_USER, null);
        try {
            if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password)) {
                User authenticatedUser = loginSrv.authenticateUserLogin(email, password);
                Logger.info("authenticatedUser::" + authenticatedUser);
                if (StringUtils.equalsIgnoreCase(authenticatedUser.getStatus(), "DEACTIVATED")) {
                    Logger.info("account is deactivated");
                    return "Deactivated";
                }
                UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(authenticatedUser.getId());
                Logger.info("userSettings::" + userSettings);
                Logger.info("Session Id before - " + session.getId());
                session = InCyyteSecurity.getInstance().changeSessionIdentifier(request);
                Logger.info("Session Id after - " + session.getId());
                Logger.info("Session: authenticatedUser - " + authenticatedUser);
                Logger.info("Session: userSettings - " + userSettings);
                session.setAttribute(SessionKeys.LOGIN_USER, authenticatedUser);
                session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
                Logger.info("Session Id after setting values- ");
                if (authenticatedUser.getResetPwdFlag().equalsIgnoreCase("N")) {
                    Logger.info("Before Return");
                    return "dash";
                }
                return "resetPassword";
            }
        } catch (AuthenticationException e) {
            //while login details are incorrect we will get Authentication exception
            return "login";
        }
        session.removeAttribute(SessionKeys.LOGIN_USER);
        // return form success view
        throw new Exception();
    }

    @RequestMapping(value = "/signup.cyt" , method = RequestMethod.POST)
    @ResponseBody
    public String processSubmit(HttpServletRequest request,
                                @ModelAttribute("signUpForm") UserModel userModel,
                                BindingResult result, SessionStatus status, HttpSession session) {

        String email = userModel.getSu_email();
        String password = userModel.getSu_password();
        String age = userModel.getAgeGroup();
        String postcode = userModel.getPostcode();
        String country = userModel.getAc_country();

        Logger.debug("%%%%%%%%%%%%%%% Login signUp - " + email + " - " + age + " - " + postcode + " - " + country);

        try {
            User user = registrationSrv.signUpUser(MapUserProperty.copyUserSignUpDetails(userModel, request));

            if (null != user) {
                session.setAttribute(SessionKeys.LOGIN_USER, user);
                return "success";
            }
        } catch (Exception e) {
            Logger.error("Exception:" , e);
        }
        session.removeAttribute(SessionKeys.LOGIN_USER);

        // return form success view
        return "failure";
    }

    @RequestMapping(value = "/activate.cyt" , method = RequestMethod.POST)
    @ResponseBody
    public String processAjaxActivate(
            @ModelAttribute("signUpForm") UserModel userModel,
            BindingResult result, SessionStatus status, HttpSession session) {

        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        String activate = userModel.getActivate_act();
        Logger.debug("%%%%%%%%%%%%%%% processAjaxActivate id - " + user.getId() + " - " + activate);

        try {
            //TODO : validate activated code

            if (activate != null) {
                if (null != user) {
                    boolean activated = registrationSrv.activateUser(user.getId(), activate);

                    if (activated) {
                        //TODO - send mail
                        return "redirect:dash.cyt";
                    }
                }
            }
        } catch (Exception e) {
            Logger.error("Exception:" , e);
        }

        // return form success view
        return "failure";
    }


    @RequestMapping(value = "/reActivate.cyt" , method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String ReSendActivationEmail(@ModelAttribute("loginForm") UserModel userModel,
                                        BindingResult result, HttpServletRequest request, SessionStatus status, HttpSession session) {
        Logger.debug("Usermodel===== " + userModel);
        try {
            User user = loginSrv.getUserDetails(userModel.getUser_email(), Constants.GET_BY_MAIL);
            Logger.debug("user " + user);
            if (user != null && (user.getStatus().equals("DEACTIVATED") || user.getStatus().equals("NON_ACTIVE"))) {
                registrationSrv.sendReactivationEmail(user);
                return "success";
            }
        } catch (Exception e) {
            Logger.error("ReSendActivationEmail ERROR - " , e);
            return e.getMessage();
        }
        // return form success view
        return "failure";
    }

    @RequestMapping(value = "/checkEmail.cyt" , method = RequestMethod.POST)
    @ResponseBody
    public String processCheckEmail(
            @ModelAttribute("signUpForm") UserModel userModel,
            BindingResult result, SessionStatus status, HttpSession session) {

        String email = userModel.getSu_email();
        String password = userModel.getSu_password();
        try {
            User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);

            if (user != null && user.getEmail() != null) {
                return "failure";
            }
        } catch (Exception e) {
            Logger.error("Exception:" , e);
            return "failure";
        }
        // return form success view
        return "success";
    }

    @RequestMapping(value = "/fpassword.cyt" , method = RequestMethod.POST)
    @ResponseBody
    public String processForgotPassword(
            @ModelAttribute("loginForm") UserModel userModel,
            BindingResult result, SessionStatus status, HttpSession session) {
        Logger.debug("processForgotPassword - " + userModel.getUser_email());
        try {
            User user = loginSrv.getUserDetails(userModel.getUser_email(), Constants.GET_BY_MAIL);
            if (user != null && user.getEmail() != null) {
                forgotPwdSrv.requestNewPassword(userModel.getUser_email());
                return "success";
            }
        } catch (AuthenticationException e) {
            //Reset new password failed, will throw authentication exception
            // with respective message, hence returning exception message
            return e.getMessage();
        } catch (Exception e) {
            Logger.error("processForgotPassword ERROR - " , e);
            return e.getMessage();
        }
        // return form success view
        return "failure";
    }

    @RequestMapping(value = "/sendActivationEmail.cyt" , method = RequestMethod.POST)
    @ResponseBody
    public String processReSendActivationEmail(@ModelAttribute("loginForm") UserModel userModel, BindingResult result, SessionStatus status, HttpSession session) {
        Logger.debug("%%%%%%%%%%%%%%% processReSendActivationEmail - " + userModel);
        try {
            User user = loginSrv.getUserDetails(userModel.getUser_email(), Constants.GET_BY_MAIL);
            if (user != null && user.getStatus().equals("NON_ACTIVE")) {
                registrationSrv.sendWelcomeEmail(user);
                return "success";
            }
        } catch (Exception e) {
            Logger.error("%%%%%%%%%%%%%%% processReSendActivationEmail ERROR - " , e);
            return e.getMessage();
        }
        // return form success view
        return "failure";
    }

    @RequestMapping(value = "/fgtpwd.cyt" , method = RequestMethod.GET)
    public String navigateForgotPassword(
            @ModelAttribute("loginForm") UserModel userModel,
            BindingResult result, SessionStatus status, HttpSession session) {
        Logger.debug("$$$$ navigateForgotPassword:  ");
        return "main/fgtpwd";
    }

    @RequestMapping(value = "/androidsubmit.cyt" , method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String processAndroidLogin(HttpServletRequest request, Model model, HttpSession session) throws Exception {
        String invId = request.getParameter("cd");
        Logger.info("login controller::::::::invitationId" + invId);
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserModel userModel = new UserModel();
        userModel.setLogin_email(email);
        userModel.setLogin_pwd(password);

        Logger.info("%%%%%%%%%%%%%%% Login username - " + email + " - " + password);

        try {
            if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password)) {
                User authenticatedUser = loginSrv.authenticateUserLogin(email, password);
                if (authenticatedUser != null) {
                    User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
                    Logger.info("user.getStatus()::" + user);
                    if (StringUtils.equalsIgnoreCase(user.getStatus(), "DEACTIVATED")) {
                        Logger.info("account is deactivated");
                        return "Deactivated";
                    }
                    Logger.info("%%%%%%%%%%%%%%% Login user - " + user);
                    if (null != user) {
                        UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(user.getId());
                        Logger.info("userSettings::" + userSettings);
                        Logger.info("login controller:::::::::::invId:user not null" + invId);
                        Logger.info("After Login: Invitation Id:" + session.getAttribute("invId"));
                        Logger.info("Session Id before - " + session.getId());
                        session = InCyyteSecurity.getInstance().changeSessionIdentifier(request);
                        Logger.info("Session Id after - " + session.getId());
                        session.setAttribute(SessionKeys.LOGIN_USER, user);
                        session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
                        if (StringUtils.isNotBlank(invId)) {
                            Logger.info("login controller:::::::::::invId is not null:" + invId);
                            return "contacthome";
                        } else {
                            if (user.getResetPwdFlag().equalsIgnoreCase("N")) {
                                return "dash";
                            }
                            return "resetPassword";
                        }
                    }
                } else {
                    Logger.info("authenticateUserLogin: false -  Redirect to LOGIN...");
                    return "login";
                }
            }
        } catch (AuthenticationException e) {
            //while login details are incorrect we will get Authentication exception
            return "login";
        }

        session.removeAttribute(SessionKeys.LOGIN_USER);

        // return form success view
        throw new Exception();
    }

    @RequestMapping(value = "/getUserDetailsString.cyt" , method = {RequestMethod.GET})
    @ResponseBody
    public String getUserInfoInString(HttpServletRequest request, Model model, HttpSession session) throws Exception {
        String email = request.getParameter("email");
        Logger.info("login::" + email);
        try {
            User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
            Map<String, String> userInfo = new HashMap<String, String>();
            userInfo.put("email" , user.getEmail());
            userInfo.put("userid" , String.valueOf(user.getId()));
            userInfo.put("username" , user.getUsername());
            return user.getUsername();
        } catch (Exception e) {
            //while login details are incorrect we will get Authentication exception
            return "login";
        }
    }

    @RequestMapping(value = "/getUserDetails.cyt" , method = {RequestMethod.GET})
    @ResponseBody
    public String getUserInfo(HttpServletRequest request, Model model, HttpSession session) throws Exception {
        String email = request.getParameter("email");
        Logger.info("login::" + email);
        try {
            User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
            Map<String, String> userInfo = new HashMap<String, String>();
            userInfo.put("email" , user.getEmail());
            userInfo.put("userid" , String.valueOf(user.getId()));
            userInfo.put("username" , user.getUsername());
            JSONObject json = JSONObject.fromObject(userInfo);
            return json.toString();
        } catch (Exception e) {
            //while login details are incorrect we will get Authentication exception
        	Logger.error("Exception::", e);
            return null;
        }
    }
    

    @RequestMapping(value = "/androidSendActivationEmail.cyt" , method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String processReSendActivationEmail( HttpServletRequest request) {
    	String email = request.getParameter("email");

    	try {
    		User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
    		if (user != null && user.getStatus().equals("NON_ACTIVE")) {
    			registrationSrv.sendWelcomeEmail(user);
    			return "success";
    		}
    	} catch (Exception e) {
    		Logger.error("%%%%%%%%%%%%%%% processReSendActivationEmail ERROR - " , e);
    		return e.getMessage();
    	}
    	// return form success view
    	return "failure";
    }


    @RequestMapping(value = "/androidReActivate.cyt" , method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String ReSendActivationEmail(HttpServletRequest request) {
    	String email = request.getParameter("email");

    	try {
    		User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
    		Logger.debug("user " + user);
    		if (user != null && (user.getStatus().equals("DEACTIVATED") || user.getStatus().equals("NON_ACTIVE"))) {
    			registrationSrv.sendReactivationEmail(user);
    			return "success";
    		}
    	} catch (Exception e) {
    		Logger.error("ReSendActivationEmail ERROR - " , e);
    		return e.getMessage();
    	}
    	// return form success view
    	return "failure";
    }
}