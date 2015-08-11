package com.incyyte.app.web.controller.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.incyyte.app.domain.User;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserModel;

@Controller
public class LoginRetryController {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private RegistrationService registrationSrv;


/*	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {		
		// return form view
		return "main/login";
	}*/

	@RequestMapping("/relogin")
	public String reloginpage
	(@ModelAttribute("loginForm") UserModel userModel,BindingResult result,HttpSession session,Model model) {	
		return "main/relogin";
	}	
	
	@RequestMapping("/login")
	public String loginpage
	(@ModelAttribute("loginForm") UserModel userModel,BindingResult result,HttpSession session,Model model) {	
		return "main/login";
	}	

}
