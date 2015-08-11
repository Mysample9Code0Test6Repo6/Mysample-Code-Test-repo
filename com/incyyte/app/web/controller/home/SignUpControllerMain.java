package com.incyyte.app.web.controller.home;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/signup")
public class SignUpControllerMain {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RegistrationService registrationSrv;

    @Autowired
    private ContactService contactsSrv;

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        // return form view
        return "main/home";
    }

    @RequestMapping(value = "/new_account.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processSubmit(@ModelAttribute("signUpForm") UserModel userModel, ModelMap model, HttpSession session) {
        Logger.info("Username: " + userModel);
        session.setAttribute("SIGNUP_MODEL", userModel);
        // command object
        model.put("signUpForm", userModel);
        // return form success view
        return "createAcct";
    }

    @RequestMapping(value = "/createAcct.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processSubmit(HttpServletRequest request, @ModelAttribute("signUpForm") UserModel userModel, BindingResult result, SessionStatus status, HttpSession session) {
        try {
            Logger.info("inside createAcct.cyt");
            Logger.info("Username: " + userModel);
            session.setAttribute("SIGNUP_MODEL", userModel);
            //signup the user first
            User user = registrationSrv.signUpUser(MapUserProperty.copyUserSignUpDetails(userModel, request));

            //add contact if via invitation
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
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        Logger.debug("outside createAcct.cyt");
        return "failure";
    }

    @RequestMapping(value = "/authenticateSignup.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String authenticateSignup(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            Logger.info("inside authenticateSignup");
            //signup the user first
            UserModel userModel = new UserModel();
            MapUserProperty.buildSignUpDetailsByRequest(userModel, request);
            session.setAttribute("SIGNUP_MODEL", userModel);
            Logger.debug("userModel::" + userModel);
            return "success";
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        Logger.debug("outside authenticateSignup");
        return "failure";
    }
}