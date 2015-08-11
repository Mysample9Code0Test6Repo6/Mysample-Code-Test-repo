package com.incyyte.app.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.incyyte.app.service.util.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.security.InCyyteSecurity;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;
import com.incyyte.app.web.model.UserModel;
import com.incyyte.app.web.model.UserSettingsModel;


@Controller
public class ActivationController extends BaseContoller{

	@Autowired
	private RegistrationService registrationSrv;

	@Autowired
	private LoginService loginSrv;
	@Autowired
	private ContactService contactsSrv;

	@RequestMapping(value = "/activation", method = RequestMethod.GET)
	public String initForm(ModelMap model, HttpServletRequest request) {
		UserModel userModel = new UserModel();
		IncyyteModel incyyteModel = new IncyyteModel();
		InCyyte incyyte =null;

		// command object
		model.put("inCyyteForm", incyyteModel);
		model.put("signUpForm", userModel);
		model.put("loginForm", userModel);

		Logger.debug("ActivationController tested- > " + request.getQueryString() + " : code - " + request.getParameter("cd"));
		Logger.debug("ActivationController tested- > " + request.getQueryString() + " : inCyyteID - " + request.getParameter("iid"));
		String activateCd = request.getParameter("cd");
		String inCyyteID = request.getParameter("iid");
		HttpSession session = request.getSession();
		try{
			Logger.debug("activateCdactivateCd::"+activateCd);
			if(activateCd != null){
				User user = loginSrv.getUserDetails(activateCd, Constants.GET_BY_CODE);//activateCd
				Logger.debug("activateCd:user::"+user);
				boolean activated = false;
				//verify code
				activated = registrationSrv.activateUser(activateCd,user.getStatus());
				Logger.debug("activated----" +activated );
				if(activated){
					//log user into dashboard page
					if (inCyyteID != null)
						incyyte = getQSInCyyte(new Long(inCyyteID));
					if(null != user) {
						//This is for unblocking the contacts of the user , deactivated earlier.
						UserContactlist userContactList =  contactsSrv.getUserContacts(user.getId(), 0, 0, null	, null);
						Logger.debug("userContactList::"+userContactList);
						int blockCount = 0;
						for (UserContactModel usercontact: userContactList.getUserContactlist()){
							blockCount =	contactsSrv.UnBlockContact(usercontact, user.getId());
							Logger.debug("blockCount::::"+blockCount);
						}
						Logger.debug("Session Id before - "	+ session.getId());
						session=InCyyteSecurity.getInstance().changeSessionIdentifier(request);
						Logger.debug("Session Id after - "	+ session.getId());
						user.setStatus("ACTIVE");
						session.setAttribute(SessionKeys.LOGIN_USER, user);
						UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(user.getId());
						Logger.debug("userSettings::"+userSettings);
						session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
						session.setAttribute(SessionKeys.INCYYTE, incyyte);
						if (inCyyteID != null){
							processEmail(user,incyyte, request);
						}
						return "redirect:dash.cyt";
					}
				}else{//code not valid / already activated
					return "redirect:welcome.cyt";
				}
			}		
		}
		catch (Exception e) {
			Logger.error("Exception:", e);
		}
		// return form view
		return "redirect:welcome.cyt";
	}

	private void processEmail(User user, InCyyte incyyte, HttpServletRequest request)throws Exception{
		sendIncyyteEmail(user,incyyte.isAnonymity(), incyyte.getId(), request);
	}	
}