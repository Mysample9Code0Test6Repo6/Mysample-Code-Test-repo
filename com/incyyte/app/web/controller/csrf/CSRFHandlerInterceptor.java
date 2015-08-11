package com.incyyte.app.web.controller.csrf;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.SessionKeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A Spring MVC <code>HandlerInterceptor</code> which is responsible to enforce CSRF token validity on incoming posts requests. The interceptor
 * should be registered with Spring MVC servlet using the following syntax:
 * <pre>
 *   &lt;mvc:interceptors&gt;
 *        &lt;bean class="com.eyallupu.blog.springmvc.controller.csrf.CSRFHandlerInterceptor"/&gt;
 *   &lt;/mvc:interceptors&gt;
 *   </pre>
 *
 * @author Eyal Lupu
 * @see CSRFRequestDataValueProcessor
 */
public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private RegistrationService registrationSrv;

    String[] protectedPages = {"editProfile", "settings"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	Logger.info("Inside preHandle method");
    	User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
        Logger.info("user::"+user);
        String userEmail=null;
        if (user == null) {
        	Cookie[] cookie = request.getCookies();
        	Logger.info("cookie::"+cookie.toString());
        	for (int i = 0; i < cookie.length; i++) {
        		   if(cookie[i].getName().equals("email")) {
        			   userEmail=cookie[i].getValue();
        		   }
        		 }
        	if (userEmail != null) {
        		User newUserSession = registrationSrv.getUserDetailByEmailOrUsername(userEmail);
                Logger.debug("newUserSession::"+newUserSession);
                request.getSession().setAttribute(SessionKeys.LOGIN_USER, newUserSession);
        	}
        }
        
    	String requestUri = request.getRequestURI();
        Logger.debug("context:: " + requestUri);

        if (requestUri.contains("editProfile")
                || requestUri.contains("settings")
                || requestUri.contains("uploadlogo")
                || requestUri.contains("deletelogo")
                || requestUri.contains("saveopinion")
                || requestUri.contains("saveusername")
                || requestUri.contains("savefirstname")
                || requestUri.contains("savelastname")
                || requestUri.contains("savecountry")
                || requestUri.contains("savelocation")
                || requestUri.contains("savepostcode")
                || requestUri.contains("saveemail1")
                || requestUri.contains("saveemail2")
                || requestUri.contains("createAcct")
                || requestUri.contains("new_account")
                || requestUri.contains("saveincome")
                || requestUri.contains("savecategory")
                || requestUri.contains("savedob")
                || requestUri.contains("savesexuality")
                || requestUri.contains("savedob")
                || requestUri.contains("savesexuality")
                || requestUri.contains("savereligion")
                || requestUri.contains("saveoccupation")
                || requestUri.contains("savegender")
                || requestUri.contains("makedefault")
                || requestUri.contains("importsocialauth")) {
            if (!request.getMethod().equalsIgnoreCase("POST")) {
                // Not a POST - allow the request
                return true;
            } else {
                // This is a POST request - need to check the CSRF token
                String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
                String requestToken = CSRFTokenManager.getTokenFromRequest(request);
                if (sessionToken.equals(requestToken)) {
                    return true;
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
                    return false;
                }
            }
        } else {
            return true;
        }
    }


}