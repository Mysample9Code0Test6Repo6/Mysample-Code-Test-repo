package com.incyyte.app.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.ForgotPasswordService;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserModel;

@Controller
@RequestMapping("/resetPassword")
public class ResetPasswordController {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private ForgotPasswordService forgotPwdSrv;	

    @Autowired
    private LoginService loginSrv;
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model, HttpSession session) {		
		model.put("loginForm", new UserModel());		
		// return form success view
		//return "resetPassword";
		return "main/resetpwd";
	}
	
	@RequestMapping(value = "/submit.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String processLogin(
			@ModelAttribute("loginForm") UserModel userModel, Model model, 
			HttpSession session){

		String curr_pwd = userModel.getLogin_pwd();
		String new_pwd = userModel.getSu_password();
			
		User user = (User)session.getAttribute("user");
		Logger.debug("reset Pwd for: ID "+ user);

		
		if(user != null){			
			try{				
				forgotPwdSrv.resetPassword(user.getId(), curr_pwd, new_pwd);				
				return "success"; 
				
			}catch (Exception e) {
				Logger.error("Exception:",e);
				return "failure"; 
			}
		}
		return "failure"; 

	}
	
	//implemented resetPassword for App
	@RequestMapping(value = "/androidResetPassword.cyt", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String androidProcessForgotPassword(HttpServletRequest request) {
		Logger.debug("inside controller");
		String curr_pwd = request.getParameter("curr_pwd");
		String new_pwd = request.getParameter("new_pwd");
		String email = request.getParameter("email");
		User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);

		if(user != null){			
			try{				
				forgotPwdSrv.resetPassword(user.getId(), curr_pwd, new_pwd);				
				return "success"; 

			}catch (Exception e) {
				Logger.error("Exception:",e);
				return "failure"; 
			}
		}
		return "success"; 
	}

}
