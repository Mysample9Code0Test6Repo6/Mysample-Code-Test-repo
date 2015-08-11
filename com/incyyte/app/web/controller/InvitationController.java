package com.incyyte.app.web.controller;

import com.incyyte.app.domain.User;
import com.incyyte.app.security.InCyyteSecurity;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class InvitationController extends BaseContoller {

    @Autowired
    private ContactService contactsSrv;

    @Autowired
    private LoginService loginSrv;

    @RequestMapping(value = "/invitation", method = RequestMethod.GET)
    public String initForm(ModelMap model, HttpServletRequest request) {
        UserModel userModel = new UserModel();

        // command object
        model.put("signUpForm", userModel);
        model.put("loginForm", userModel);


        Logger.debug("InvitationController tested...... - > " + request.getQueryString() + " : code - " + request.getParameter("cd"));
        String invitationCd = request.getParameter("cd");
        HttpSession session = request.getSession();
        try {

            if (invitationCd != null) {
                Logger.debug("Redirect Invitee accordingly...... - > ");
                UserContactModel contact = contactsSrv.getContactByInv(invitationCd);//activateCd

                if (contact.getAccept_inv().equals(Constants.CONTACT_INVITATION_ACCEPTED)) {
                    return "redirect:welcome.cyt";
                } else if (contact.getAccept_inv().equals(Constants.CONTACT_INVITATION_NOT_ACCEPTED)) {
                    if (contact.getStatus().equals(Constants.CONTACT_MEMBER)) {
                        //log user into dashboard page & display message
                        User user = loginSrv.getUserDetails(contact.getEmail(), Constants.GET_BY_MAIL);

                        if (null != user) {
                            Logger.debug("Session Id before - " + session.getId());
                            session = InCyyteSecurity.getInstance().changeSessionIdentifier(request);
                            Logger.debug("Session Id after - " + session.getId());
                            session.setAttribute(SessionKeys.LOGIN_USER, user);
                            session.setAttribute(SessionKeys.USER_MESSAGE, "You are now connected to: " + contact.getUserid());
                            //accept invitation
                            contactsSrv.acceptInvitation(invitationCd);
                            return "redirect:dash.cyt";
                        }

                    } else if (contact.getStatus().equals(Constants.CONTACT_NOT_MEMBER)) {

                        //navigate Invited Contact to Signup Page
                        Logger.debug("%%%%%% Get Contact to Signup -> ");
                        session.setAttribute(SessionKeys.INVITATION_KEY, contact);
                        return "newSignup";
                    } else return "redirect:welcome.cyt";
                }
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        // return form view
        return "redirect:welcome.cyt";
    }

    @RequestMapping(value = "/newMember.cyt", method = RequestMethod.GET)
    public String processNewMember(HttpServletRequest request, HttpSession session) throws Exception {
        //NOTE: This method is just a temp. placeHolder, and will probably best be moved to /login/signup.cyt (Within the LoginController)

        UserContactModel contact = (UserContactModel) session.getAttribute(SessionKeys.INVITATION_KEY);
        Logger.debug("%%%%%% Process new Signup -> " + contact.getInvitationid());
        //1. signup this User
        // TO BE DONE

        //2. Accept Invitation
        contactsSrv.acceptInvitation(contact.getInvitationid());


        //3. navigate user to Dashboard & display Message
        session.setAttribute(SessionKeys.USER_MESSAGE, "You are now connected to: " + contact.getUserid());
        session.removeAttribute(SessionKeys.INVITATION_KEY);
        //TODO change to dashboard when login
        return "redirect:welcome.cyt";
    }

}
