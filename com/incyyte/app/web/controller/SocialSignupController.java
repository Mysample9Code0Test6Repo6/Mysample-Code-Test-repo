package com.incyyte.app.web.controller;

import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserLocation;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.UserIPLocator;
import com.incyyte.app.web.model.UserModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller

public class SocialSignupController {

    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RegistrationService registrationSrv;

    @RequestMapping(value = "/snSignup", method = RequestMethod.POST)
    @ResponseBody
    public String processSubmit(HttpServletRequest request,
                                @ModelAttribute("snsignUpForm") UserModel model,
                                BindingResult result, SessionStatus status, HttpSession session) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setEmail(model.getSu_email());
        user.setPassword(model.getSu_password());
        user.setCountryCode(model.getCountry());
        user.setPostalCodeArea(model.getPostcode());
        user.setGender(model.getGender());
        user.setBirthYear(model.getBirthYear());
        user.setFirstname(model.getFirstname());
        user.setLastname(model.getLastname());
        user.setStatus("ACTIVE");
        user.setAcceptTerms("1");
        user.setSignupmode(model.getSn_mode());
        //user.setGender(model.getGender());
        //user.setAgeGroup(model.getAgeGroup());
        user.setProfilePicture(model.getCdnFileName());
        user = getSignUpLocal(user, request);
        Logger.debug("%%%%%%%%%%%%%%%%%   " + model.getUsername());
        boolean exists = registrationSrv.usernameExists(model.getUsername());
        Logger.debug("%%%%%%%%%%%%%%%%%   "+exists);
        if(exists){
        	 return "username already exists";
        }
        try {
            user = registrationSrv.signUpUserSN(user);
            if (null != user) {
                session.setAttribute("user", user);
                return "redirect:dash.cyt?sn";
            }
        } catch (Exception e) {
        	Logger.error("Exception:",e);
        }
        session.removeAttribute("user");
        // return form success view
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

}