/*
 ===========================================================================
 Copyright (c) 2010 BrickRed Technologies Limited

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sub-license, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 ===========================================================================

 */

package org.brickred.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.incyyte.app.web.model.MandatoryInfoModel;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.incyyte.app.dao.contacts.ContactDaoImpl;
import com.incyyte.app.domain.User;
import com.incyyte.app.security.InCyyteSecurity;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserModel;

@Controller
public class SuccessController {

	@Autowired
	private SocialAuthTemplate socialAuthTemplate;
	@Autowired
	private LoginService loginSrv;
	@Autowired
	private ContactService contactsSrv;

	@Autowired
	private RefData referenceData;

	@RequestMapping(value = "/authSuccess")
	public ModelAndView getRedirectURL(final HttpServletRequest request , HttpSession session)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Contact> contactsList = new ArrayList<Contact>();
		SocialAuthManager manager = (SocialAuthManager) session.getAttribute("snuser"); //socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider =  manager.getCurrentAuthProvider();
		contactsList = provider.getContactList();
		if (contactsList != null && contactsList.size() > 0) {
			for (Contact p : contactsList) {
				if (!StringUtils.hasLength(p.getFirstName())
						&& !StringUtils.hasLength(p.getLastName())) {
					p.setFirstName(p.getDisplayName());
				}
			}
		}
		UserModel  um = new UserModel() ;
		if(provider.getProviderId().equals("twitter"))
		{
			String[] result = provider.getUserProfile().getFullName().split(" "); 
			provider.getUserProfile().setFirstName(result[0]);
			provider.getUserProfile().setLastName(result[1]);

		}	
		um.setFirstname(provider.getUserProfile().getFirstName()) ;
		um.setGender(provider.getUserProfile().getGender()) ;
		um.setLogin_email(provider.getUserProfile().getEmail()) ;
		um.setUsername(provider.getUserProfile().getDisplayName());
		um.setLastname(provider.getUserProfile().getLastName());
		um.setProfilePicture(provider.getUserProfile().getProfileImageURL());

		um.setCountry(provider.getUserProfile().getCountry());
		//um.set
		um.setSn_mode(provider.getProviderId()) ;
		mv.addObject("profile", provider.getUserProfile());
		mv.addObject("contacts", contactsList);
		MandatoryInfoModel infoModel = new MandatoryInfoModel();
		infoModel.setUsername(um.getUsername());
		infoModel.setLastname(um.getLastname());
		infoModel.setGender(um.getGender());
		infoModel.setCountryCode(um.getCountry());
		infoModel.setCountry(referenceData.getCountries().get(um.getCountry()));
		infoModel.setFirstname(um.getFirstname());
		infoModel.setEmail(um.getLogin_email());
        infoModel.setSn_mode(um.getSn_mode());
        Logger.info("infoModel::" + infoModel);
		mv.addObject("detailsform", infoModel);

		Calendar calendar = new GregorianCalendar();
		int ageLimit = calendar.get(Calendar.YEAR) - referenceData.getBirthYearLimit();
		mv.addObject("birthYearLimit", ageLimit);
		mv.addObject("ethnicities", referenceData.getEthnicity());
		mv.addObject("educationLevels", referenceData.getEducation_level()); 
		mv.addObject("adultsInHouseHolds", referenceData.getAdults_in_houseHold());
		mv.addObject("childrenInHouseHolds", referenceData.getChildren_in_houseHold()); 
		mv.setViewName("dashboard/mandatory_info");
		if( null!=request.getParameter("mode")&& request.getParameter("mode").equals("import")) {
			return mv  ;
		}

		User user = loginSrv.getUserDetails(provider.getUserProfile().getEmail(),  Constants.GET_BY_MAIL);
		if(null==user)
			user = loginSrv.getUserDetailByEmailOrUsername(provider.getUserProfile().getEmail(),um.getUsername() );
		if(null != user) {
			session=InCyyteSecurity.getInstance().changeSessionIdentifier(request);
			session.setAttribute(SessionKeys.LOGIN_USER, user);
			UserModel	usermdl = new UserModel() ;
			usermdl.setFirstname(user.getFirstname()) ;
			if(user.getResetPwdFlag().equalsIgnoreCase("N")){							
				mv.addObject("sendInCyyteForm", new IncyyteModel());		
				mv.addObject("searchUserForm", new UserModel());
				mv.addObject("searchIncyyteForm", new IncyyteModel());
				mv.addObject("editIncyyteForm", new IncyyteModel());	
				mv.addObject("loginForm", usermdl);
				mv.setViewName("redirect:dash.cyt?sn");
			}
		}


		//	provider.logout();


		return mv;
	}


	@ModelAttribute("countries")
	public  Map<String, String>  getCountries(){
		return referenceData.getCountries();
	}



	@RequestMapping(value = "/importSuccess")
	public ModelAndView getRedirectURLs(final HttpServletRequest request , HttpSession session)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		User lgnuser = (User) session.getAttribute("user");
		Logger.debug("lgnuser :::::::::"+lgnuser);
		List<Contact> contactsList = new ArrayList<Contact>();
		List<Contact> contactsList2 = new ArrayList<Contact>();
		List<Contact> contactsListexisting = new ArrayList<Contact>();

		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();
		//provider.logout();
		contactsList = provider.getContactList();
		Logger.debug("contactsList :::::::::"+contactsList);

		ContactDaoImpl  dao  =  new ContactDaoImpl() ;
		try{
			contactsListexisting = 	contactsSrv.getuserContactListEmailids(lgnuser.getId()) ;
			Logger.debug("contactsListexisting :::::::::"+contactsListexisting);
		}
		catch(Exception e) {
            Logger.error("Exception:", e);
        }
		if (contactsList != null && contactsList.size() > 0) {
			for (Contact p : contactsList) {
				Logger.debug("p :::::::::"+p);
				UserContactModel user  = new UserContactModel () ;
				if (!StringUtils.hasLength(p.getFirstName())
						&& !StringUtils.hasLength(p.getLastName())) 
					p.setFirstName(p.getDisplayName());


				if (!StringUtils.hasLength(p.getEmail()))
					user.setEmail(p.getEmail()) ;
				Logger.debug("p.getEmail() :::::::::"+p.getEmail());

				user.setFirstname(p.getFirstName()) ;
				user.setLastname(p.getLastName());
				user.setEmail(p.getEmail()) ;	
				user.setAccept_inv("N") ;
				user.setSn_id(p.getId()) ;
				user.setSn_site(provider.getProviderId());
				p.setEmailHash(provider.getProviderId()) ;
				//int addresult = 0 ;
				boolean flg = false ;	

				if(null!=provider.getProviderId()&& (provider.getProviderId().equalsIgnoreCase("googleplus") || provider.getProviderId().equalsIgnoreCase("yahoo"))) { 
					if( !p.getEmail().equals(lgnuser.getEmail())) {

						if(null!=contactsListexisting)
							for (Contact Q : contactsListexisting) {
								flg = false ;	

								if(Q.getEmail().equalsIgnoreCase(p.getEmail())) {
									flg=true ;
									contactsSrv.updateContact(Q.getEmail(), lgnuser.getId());
									break;
								}
							}

						if(!flg) {
							contactsSrv.addContact(user, lgnuser.getId()) ;
						}
						contactsList2.add( p)  ;
					}
				}
			}
		}
		UserModel  um = new UserModel() ;

		um.setFirstname(provider.getUserProfile().getFirstName()) ;
		um.setGender(provider.getUserProfile().getGender()) ;
		um.setLogin_email(provider.getUserProfile().getEmail()) ;
		UserContactModel  userModel  = new 		UserContactModel() ;

		mv.addObject("profile", provider.getUserProfile());
		mv.addObject("contacts", contactsList2);
		mv.addObject("ViewContactForm", userModel) ;
		mv.setViewName("./contacts/contacts_imported");

		return mv;
	}



	@RequestMapping(value = "/snLogout")
	public String snLogout(final HttpServletRequest request , HttpSession session,HttpServletResponse resp)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		User lgnuser = (User) session.getAttribute("user");
		List<Contact> contactsList = new ArrayList<Contact>();
		SocialAuthManager manager = (SocialAuthManager) session.getAttribute("snuser"); //socialAuthTemplate.getSocialAuthManager();

		//provider.
		try{
			AuthProvider provider = manager.getCurrentAuthProvider();

			manager.disconnectProvider(provider.getProviderId()); 

			session.removeAttribute("snuser");

			Cookie[] cookies = request.getCookies();
			if (cookies != null)
				for (int i = 0; i < cookies.length; i++) {
					cookies[i].setValue("");
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					resp.addCookie(cookies[i]);
				}




		}
		catch(Exception e)
		{
			try{
				Cookie[] cookies = request.getCookies();
				if (cookies != null)
					for (int i = 0; i < cookies.length; i++) {
						cookies[i].setValue("");
						cookies[i].setPath("/");
						cookies[i].setMaxAge(0);
						resp.addCookie(cookies[i]);
					}


				session.removeAttribute("snuser");
			}catch (Exception ie) {
				Logger.error("Exception while Logged Out." , ie);
			}
		}


		return "redirect:welcome.cyt";

	}







}
