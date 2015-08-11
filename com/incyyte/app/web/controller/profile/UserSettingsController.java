package com.incyyte.app.web.controller.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.incyyte.app.dao.core.util.QueryParameterFactory;
import com.incyyte.app.domain.User;
import com.incyyte.app.exception.PasswordException;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.UserSettingsService;
import com.incyyte.app.service.Exceptions.UserNotFoundException;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.UserSettingsModel;

/**
 * @author Dev1
 *
 */
@Controller
public class UserSettingsController {

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private UserSettingsService userSettingsService;
	@Autowired
	private LoginService loginSrv;

	@RequestMapping("/editSettings")
	public String editProfilePage(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result,HttpSession session,Model model) throws UserNotFoundException {
		log.debug("Edit user profile...");
		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		userSettingsModel = userSettingsService.getUserSettings(user.getId());
		model.addAttribute("editSettingsForm", userSettingsModel);
		return "settings/settings_edit";
	}

	@RequestMapping(value = "/savepagename", method = RequestMethod.POST)
	@ResponseBody
	public String savePageName(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) {
		String pageName = userSettingsModel.getUniquePageName();
		log.info("Updating pageName..." + pageName);
		String statusMessage=null;
		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		try {
			if(user.getStatus().equals("ACTIVE")){
				boolean  isPageNameUnique = false;
				if(StringUtils.equals(pageName, user.getUsername())){
					isPageNameUnique = true;
				}else{
					isPageNameUnique = userSettingsService.isPageNameUnique(pageName);
				}
				if(isPageNameUnique) {
					log.info("here condition succeeded");
					boolean pageNameUpdated = userSettingsService.saveUniquePageName(pageName, user.getId());
					Logger.debug("Pagename :: " + pageName + " updated successfully:: " + pageNameUpdated);
					if(pageNameUpdated) {
						statusMessage="success-Pagename :: " + pageName + " updated successfully "   ;
					} else {
						statusMessage="error-Invalid Page Name";
					}
				}else{
					Logger.debug("PageName is some other's user name or page name.");
					statusMessage="error-Invalid Page Name";
				}
			}else{
				statusMessage="error-In-Active User";
			}
			return statusMessage;
		}
		catch(Exception se){
			String str = String.valueOf(se);
			log.info(" e..." + se.getMessage());
			log.info(" eLocal..." + se.getLocalizedMessage());
			log.info(" eCause..." + se.getCause());
			log.info(" str..." + str);
			return "error-"+se.getMessage();
		}
		//	return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}
	@Value("${password.success}") 
	private String updatePassword;

	@Value("${password.error}") 
	private String errorPassword;

	@Value("${password.err}") 
	private String errPassword;


	@RequestMapping(value = "/savepassword", method = RequestMethod.POST)
	@ResponseBody public String savePassword(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) throws PasswordException {
		String password = userSettingsModel.getPassword();
		String newPassword = userSettingsModel.getNewPassword();
		String statusMessage=null;
		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		if(StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)){
			return errPassword;
		}
		boolean passwordUpdated=false;
		if(user.getStatus().equals("ACTIVE")){
			passwordUpdated= userSettingsService.saveUserPassword(newPassword, password, user);
		}
		else{
			statusMessage="error-In-Active User";
		}
		if(passwordUpdated) {
			user.setPassword(QueryParameterFactory.encryptPwd(newPassword));
			//session.setAttribute(password,password);
			session.setAttribute(SessionKeys.LOGIN_USER,user);
			statusMessage= updatePassword ;
		}
		else {
			statusMessage="error-You have entered incorrect current password";
		}

		return statusMessage;
		//	return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}

	@RequestMapping(value = "/saverating", method = RequestMethod.POST)
	public ModelAndView saveRating(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) {
		log.info("Updating rating prefs...");
		String rating = userSettingsModel.getDisplayRating();

		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		boolean ratingUpdated=false;
		if(user.getStatus().equals("ACTIVE")){
			ratingUpdated = userSettingsService.saveDisplayRating(rating, user.getId() );
		}

		if(log.isDebugEnabled()) {
			log.debug("rating :: " + rating + " updated successfully:: " + ratingUpdated);
		}
		if(ratingUpdated) {
			log.info("success-rating :: " + rating + " updated successfully ");
		} else {
			log.info("error-Invalid rating");
		}
		return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}

	@RequestMapping(value = "/savedisablepoll", method = RequestMethod.POST)
	public ModelAndView saveDisablePollComments(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) throws Exception {
		log.debug("Updating disable poll prefs...");
		String pollPref = userSettingsModel.getDisableComments();

		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		UserSettingsModel userSettingModel=(UserSettingsModel) session.getAttribute(SessionKeys.LOGIN_USER_SETTINGS);
		Logger.debug("userSettingModel before saving::"+userSettingModel);
		boolean pollPrefUpdated=false;
		if(user.getStatus().equals("ACTIVE")){
			pollPrefUpdated = userSettingsService.saveDisplayPollComments(pollPref, user.getId() );
			UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(user.getId());
			Logger.debug("userSettings::"+userSettings);
			session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
		}
		if(log.isDebugEnabled()) {
			log.debug("Poll pref :: " + pollPref + " updated successfully:: " + pollPrefUpdated);
		}
		return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}

	@RequestMapping(value = "/savedisableincyytes", method = RequestMethod.POST)
	public ModelAndView saveDisableIncyytes(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) {
		log.info("Updating incyyte prefs...");
		String incyytePref = userSettingsModel.getDisbaleIncyytes();

		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		boolean incyytePrefUpdated=false;
		if(user.getStatus().equals("ACTIVE")){
			incyytePrefUpdated = userSettingsService.saveDisplayRecentIncyytes(incyytePref, user.getId() );
		}
		if(log.isDebugEnabled()) {
			log.debug("Incyyte pref :: " + incyytePref + " updated successfully:: " + incyytePrefUpdated);
		}

		return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}
	@RequestMapping(value = "/saverestrictcomments", method = RequestMethod.POST)
	public ModelAndView saveRestrictComments(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) {
		log.info("Updating restrict poll prefs...");
		String restrictPref = userSettingsModel.getRestrictComments();

		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		boolean restrictPrefUpdated=false;
		if(user.getStatus().equals("ACTIVE")){
			restrictPrefUpdated = userSettingsService.saveRetrictComments(restrictPref, user.getId() );
		}
		if(log.isDebugEnabled()) {
			log.debug("restrictPref :: " + restrictPref + " updated successfully:: " + restrictPrefUpdated);
		}

		return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}

	@RequestMapping(value = "/saveoptins", method = RequestMethod.POST)
	public ModelAndView saveOptIn(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) {
		log.info("Updating opt-in prefs...");
		String optInPref = userSettingsModel.getOptInPriceTag();

		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		boolean optInPrefUpdated=false;
		if(user.getStatus().equals("ACTIVE")){
			optInPrefUpdated = userSettingsService.savePriceTagPolling(optInPref, user.getId() );
		}
		if(log.isDebugEnabled()) {
			log.debug("OptIn pref :: " + optInPref + " updated successfully:: " + optInPrefUpdated);
		}

		return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}

	@RequestMapping(value = "/savenotifypolls", method = RequestMethod.POST)
	public ModelAndView saveNotifyPolls(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) throws Exception {
		log.info("Updating poll prefs...");
		String pollPref = userSettingsModel.getNotifypolls();

		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		UserSettingsModel userSettingModel=(UserSettingsModel) session.getAttribute(SessionKeys.LOGIN_USER_SETTINGS);
		Logger.debug("userSettingModel before saving::"+userSettingModel);

		boolean pollPrefUpdated=false;

		if(user.getStatus().equals("ACTIVE")){
			pollPrefUpdated = userSettingsService.saveNotifyPoll(pollPref, user.getId() );
			UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(user.getId());
			Logger.debug("userSettings::"+userSettings);
			session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
		}
		if(log.isDebugEnabled()) {
			log.debug("rating :: " + pollPref + " updated successfully:: " + pollPrefUpdated);
		}
		UserSettingsModel userSettingModelAfterSave=(UserSettingsModel) session.getAttribute(SessionKeys.LOGIN_USER_SETTINGS);
	    Logger.debug("userSettingModelAfterSave ::"+userSettingModelAfterSave);
		session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettingModelAfterSave);

		return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}

	@RequestMapping(value = "/savenotifyfollowers", method = RequestMethod.POST)
	public ModelAndView saveNotifyFollowers(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) {
		log.info("Updating followers prefs...");
		String followerPref = userSettingsModel.getNotifyFollower();

		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		boolean followerPrefUpdated=false; 
		if(user.getStatus().equals("ACTIVE")){
			followerPrefUpdated = userSettingsService.saveNotifyFollower(followerPref, user.getId() );
		}
		if(log.isDebugEnabled()) {
			log.debug("follower Pref :: " + followerPref + " updated successfully:: " + followerPrefUpdated);
		}

		return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}

	@RequestMapping(value = "/savenotifynews", method = RequestMethod.POST)
	public ModelAndView saveNotifyNews(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session) {
		log.info("Updating news prefs...");
		String newsPref = userSettingsModel.getNotifyNews();
		log.info("save Notify News:" + userSettingsModel.toString());
		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		boolean newsPrefUpdated=false;
		if(user.getStatus().equals("ACTIVE")){
			newsPrefUpdated  = userSettingsService.saveNotifyNews(newsPref, user.getId() );
		}
		if(log.isDebugEnabled()) {
			log.debug("News preference :: " + newsPref + " updated successfully:: " + newsPrefUpdated);
		}

		return new ModelAndView("redirect:editSettings.cyt","editSettingsForm", userSettingsModel) ;
	}

	@RequestMapping(value = "/deactivateacc", method = RequestMethod.POST)
	public ModelAndView deactivateAccount(@ModelAttribute("editSettingsForm") UserSettingsModel userSettingsModel, BindingResult result, HttpSession session ,HttpServletRequest request) throws Exception {
		log.info("Deactivating user account...");
		User user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("user :: "+user);
		boolean accUpdated=false;
		try{
		if(user.getStatus().equals("ACTIVE")){
			accUpdated = userSettingsService.deactivateAccount(user.getId());
			Logger.debug("accUpdated :: "+accUpdated);
		}
		log.info("Account :: deactivated successfully:: " + accUpdated);
		if(log.isDebugEnabled()) {
			log.debug("Account :: deactivated successfully:: " + accUpdated);
		}
		return new ModelAndView("redirect:logout.cyt","editSettingsForm", userSettingsModel) ;
	}catch(Exception e){
		Logger.error("Deactivating was Failed",e);
		return new ModelAndView("redirect:logout.cyt","editSettingsForm", userSettingsModel) ;
	}
	}
}
