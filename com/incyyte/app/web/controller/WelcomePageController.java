package com.incyyte.app.web.controller;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.*;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.InCyyteConstants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.util.InCyyteUtil;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserModel;
import com.incyyte.app.web.model.VoteModel;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WelcomePageController {

    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RefData referenceData;
    @Autowired
    private LoginService loginSrv;
    @Autowired
    private RegistrationService registrationSrv;
    @Autowired
    private ForgotPasswordService forgotPwdSrv;

    @Value("${incyyte.ques.max.char}")
    private String questionMaxChar;

    @Value("${incyyte.ans.max.option}")
    private String answerMaxOption;

    @Autowired
    private QuestionService questionSrv;

    @Autowired
    private QuickStartService quickStartSrv;

    @Autowired
    private ContactService contactsSrv;

/*
    @RequestMapping(value = "/welcome.cyt", method = RequestMethod.GET)
    public String initForm(ModelMap model, HttpSession session, HttpServletRequest request) {
        String invId = request.getParameter("cd");
        if (invId != null) {
            String invitedEmail = registrationSrv.getInvitedemailByInvId(invId);
            long inviterId = registrationSrv.getInviterId(invId);
            UserContactModel cm = new UserContactModel();
            cm.setEmail(invitedEmail);
            cm.setAccept_inv("Y");
            try {
                int count = contactsSrv.getContactExist(cm, inviterId);
                if (count > 0) {
                    contactsSrv.updateContact(cm, inviterId);
                } else {
                    contactsSrv.addContact(cm, inviterId);
                }
            } catch (Exception e) {
            Logger.error("Exception:", e);
            }
        }
        UserModel userModel = new UserModel();
        IncyyteModel incyyteModel = new IncyyteModel();

        // command object
        model.put("inCyyteForm", incyyteModel);
        model.put("signUpForm", userModel);
        model.put("loginForm", userModel);

        session.setAttribute("questionMaxChar", questionMaxChar);
        session.setAttribute("answerMaxOption", answerMaxOption);

        //Load Tenant Values and set the tenant based on url
        try {
            StringBuffer hostUrl = request.getRequestURL();
            URL url = new URL(hostUrl.toString());
            String strHost = url.getHost();
            String tenantId = referenceData.getTenantDetails().get(strHost);
            session.setAttribute("tenantId", tenantId);
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }

        if (session.getAttribute(SessionKeys.INCYYTE) != null) {
            session.removeAttribute(SessionKeys.INCYYTE);
        }
        // return form view
        return "main/home";
    }
*/

    @RequestMapping(value = "/welcome.cyt", method = RequestMethod.GET)
    public String initFormNewPage(ModelMap model, HttpSession session, HttpServletRequest request) {
    	String invId = request.getParameter("cd");
        if (invId != null) {
            String invitedEmail = registrationSrv.getInvitedemailByInvId(invId);
            long inviterId = registrationSrv.getInviterId(invId);
            UserContactModel cm = new UserContactModel();
            cm.setEmail(invitedEmail);
            cm.setAccept_inv("Y");
            try {
                int count = contactsSrv.getContactExist(cm, inviterId);
                if (count > 0) {
                    contactsSrv.updateContact(cm, inviterId);
                } else {
                    contactsSrv.addContact(cm, inviterId);
                }
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
        }
        UserModel userModel = new UserModel();
        IncyyteModel incyyteModel = new IncyyteModel();

        // command object
        model.put("inCyyteForm", incyyteModel);
        model.put("signUpForm", userModel);
        model.put("loginForm", userModel);
        
        //Load Tenant Values and set the tenant based on url
        try {
            List<InCyyteChart> musicPollsByCategory = questionSrv.getTopPollsByCategory("Music", 4);
            model.put("musicPollsByCategory", musicPollsByCategory);
            Logger.debug("musicPollsByCategory::::" + musicPollsByCategory);

            // to get latest  Entertainment polls
            List<InCyyteChart> entertainmentPollsByCategory = questionSrv.getTopPollsByCategory("Entertainment", 4);
            model.put("entertainmentPollsByCategory", entertainmentPollsByCategory);
            Logger.debug("entertainmentPollsByCategory::::" + entertainmentPollsByCategory);

            String search = "";
            // To get latest polls
            List<InCyyteChart> PublicIncyytes = questionSrv.getPublicPolls(12, 0, search);
            Logger.debug("PublicIncyytes" + PublicIncyytes);
            model.put("PublicIncyytes", PublicIncyytes);
            Map<String, Map<String, String>> publicPolls = questionSrv.getPublicPolls();
            Logger.debug("publicPolls:::" + publicPolls);
            List<String> categories = new ArrayList<String>(publicPolls.keySet());
            Map<String, String> footerPolls = InCyyteUtil.getPollsByCategory(publicPolls);
            Logger.debug("pollsByCategory:::" + footerPolls);
            model.put("pollsByCategory", footerPolls);
            model.put("polls", categories);
            model.put("noOfCategories", categories.size());
            //todo: need to know where we are using the tenantId
            StringBuffer hostUrl = request.getRequestURL();
            URL url = new URL(hostUrl.toString());
            String strHost = url.getHost();
            String tenantId = referenceData.getTenantDetails().get(strHost);
            session.setAttribute("tenantId", tenantId);
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        if (session.getAttribute(SessionKeys.INCYYTE) != null) {
            session.removeAttribute(SessionKeys.INCYYTE);
        }
        //return "main/home";
        return "welcomeNew";
    }

    @RequestMapping(value = "/getVoteOnPolls.cyt", method = RequestMethod.GET)
    @ResponseBody
    public String getVoteOnPolls(ModelMap model) throws Exception {
        Logger.info("Inide getVoteOnPolls");
        Map<String, Map<String, String>> publicpolls = questionSrv.getPublicPolls();
        List<String> polls = new ArrayList<String>(publicpolls.keySet());
        model.put("polls", polls);
        return "sucess";
    }

    @RequestMapping(value = "/filteredPolls.cyt", method = RequestMethod.GET)
    public String newfilterpolls(ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {
        Logger.info("Inide newfilterpolls");

        Map<String, Map<String, String>> publicPolls = questionSrv.getPublicPolls();
        List<String> categories = new ArrayList<String>(publicPolls.keySet());
        Map<String, String> footerPolls = InCyyteUtil.getPollsByCategory(publicPolls);

        model.put("pollsByCategory", footerPolls);
        Logger.info("pollsByCategory:::" + footerPolls);

        model.put("polls", categories);
        model.put("noOfCategories", categories.size());
        String categoryPolls = "";
        if (request.getParameter("category") != null)
            categoryPolls = request.getParameter("category");
        // to get latest  Entertainment polls
        List<InCyyteChart> musicPollsByCategory = questionSrv.getTopPollsByCategory("Music", 4);
        model.put("musicPollsByCategory", musicPollsByCategory);
        Logger.debug("musicPollsByCategory::::" + musicPollsByCategory);

        // to get latest  Entertainment polls
        List<InCyyteChart> entertainmentPollsByCategory = questionSrv.getTopPollsByCategory("Entertainment", 4);
        model.put("entertainmentPollsByCategory", entertainmentPollsByCategory);
        Logger.debug("entertainmentPollsByCategory::::" + entertainmentPollsByCategory);
        model.put("loginForm", new UserModel());
        model.put("signUpForm", new UserModel());
        model.put("voteForm", new VoteModel());

        UserContactModel userModel = (UserContactModel) request.getSession().getAttribute(SessionKeys.POLL_MESSAGE_FORM);
        if (userModel != null) {
            userModel.setPollMessageContent(null);
            userModel.setUploadedFileLocation(null);
            userModel.setChecked(null);
            session.setAttribute(SessionKeys.POLL_MESSAGE_FORM, userModel);
        }

        int page = 1;
        String search = "";
        int recordsPerPage = referenceData.getPageLimit();
        //this will give the value in which page the user is
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        //Used when we search polls with a search string
        if (request.getParameter("search") != null)
            search = request.getParameter("search");

        //Used when we select a category for viewing list of polls
        String category = "";
        if (request.getParameter("category") != null)
            category = request.getParameter("category");

        if (StringUtils.isNotEmpty(search)) {
            processFilteredPollsBySearch(model, session, page, recordsPerPage, search);
        } else if (StringUtils.isNotEmpty(category)) {
            processFilteredPollsByCategory(model, session, page, recordsPerPage, category);
        }
        return "dashboard/filteredDashboard";
    }

    private void processFilteredPollsBySearch(ModelMap model, HttpSession session, int page, int recordsPerPage, String search) throws Exception {
        try {
            List<InCyyteChart> filteredIncyytes = questionSrv.getPublicPolls((page - 1) * recordsPerPage, recordsPerPage, search);
            //Will pass 0 for offset and recordsPerPage when we require entire data
            //todo: need to fine tune for reducing no of calls to DB
            //Integer noOfPages = (Integer) session.getAttribute(SessionKeys.MY_FILTER_NO_OF_PAGE);
            //Logger.info("noOfPages:::"+noOfPages);
            //if (noOfPages == null || noOfPages == 0 || noOfPages == 1) {
            List<InCyyteChart> filteredPublicIncyytes = questionSrv.getPublicPolls(InCyyteConstants.NO_OFFSET_LIMIT, InCyyteConstants.NO_RECORDS_PER_PAGE_LIMIT, search);
            int noOfRecords = filteredPublicIncyytes.size();
            int pageCount = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            session.setAttribute(SessionKeys.MY_FILTER_NO_OF_PAGE, pageCount);
            //}

            model.put("inCyyteResults", filteredIncyytes);
            //Logger.info("inCyyteResults :::"+filteredIncyytes.toString());
            model.put("page", "filtered");
            if (noOfRecords > recordsPerPage) {
                model.put("paginationNeeded", "Y");
            } else {
                model.put("paginationNeeded", "N");
            }            //Logger.info("page :::"+page);
            model.put("inCyyteForm", new IncyyteModel());
            model.put("voteForm", new VoteModel());
            model.addAttribute("currentPage", page);
            model.addAttribute("text", search);
            model.put("loginForm", new UserModel());
            model.put("signUpForm", new UserModel());
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
    }

    @RequestMapping(value = "/viewPoll.cyt", method = RequestMethod.GET)
    public String newIncyyte(ModelMap modelMap, HttpSession session, HttpServletRequest request) {

        //If Poll ID is
        String incyyteCode = StringUtils.defaultString(request.getParameter("code"));
        Logger.info("incyyteCode:::" + incyyteCode);
        if (StringUtils.isBlank(incyyteCode)) {
            return "redirect:/welcome.cyt";
        }
        InCyyte cyyte = quickStartSrv.initChart(incyyteCode);
        Logger.info("cyyte::::" + cyyte);

        try {
            //to get top 3 polls by category
            Logger.info("category:::" + cyyte.getCategory());
            if (StringUtils.isNotBlank(cyyte.getCategory())) {
                //TODO: cleanup of Entering into getInCyyte():UserDaoJTImpl 3 times
                List<InCyyteChart> getPollsByCategory = questionSrv.getTopPollsByCategory(cyyte.getCategory(), 5);
                modelMap.put("getPollsByCategory", getPollsByCategory);
                Logger.info("getPollsByCategory::::" + getPollsByCategory);
            }

            //We use following calls to get info on Header ("Vote on Polls")
            String search = "";
            // To get latest polls
            //TODO: cleanup of Entering into getInCyyte():UserDaoJTImpl 6 times
            List<InCyyteChart> PublicIncyytes = questionSrv.getPublicPolls(12, 0, search);
            Logger.info("PublicIncyytes" + PublicIncyytes);
            modelMap.put("PublicIncyytes", PublicIncyytes);
            Map<String, Map<String, String>> publicPolls = questionSrv.getPublicPolls();
            Logger.debug("publicPolls:::" + publicPolls.values());
            List<String> categories = new ArrayList<String>(publicPolls.keySet());
            Map<String, String> footerPolls = InCyyteUtil.getPollsByCategory(publicPolls);
            Logger.debug("pollsByCategory:::" + footerPolls);
            modelMap.put("pollsByCategory", footerPolls);
            modelMap.put("polls", categories);
            modelMap.put("noOfCategories", categories.size());

            //Used for Footer
            // to get latest Music polls
            List<InCyyteChart> musicPollsByCategory = questionSrv.getTopPollsByCategory("Music", 4);
            modelMap.put("musicPollsByCategory", musicPollsByCategory);
            Logger.debug("musicPollsByCategory::::" + musicPollsByCategory);

            // to get latest  Entertainment polls
            List<InCyyteChart> entertainmentPollsByCategory = questionSrv.getTopPollsByCategory("Entertainment", 4);
            modelMap.put("entertainmentPollsByCategory", entertainmentPollsByCategory);
            Logger.debug("entertainmentPollsByCategory::::" + entertainmentPollsByCategory);
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        Logger.debug("YoutubeUrl::::" + cyyte.getYoutubeUrl());
        session.setAttribute(SessionKeys.INCYYTE, cyyte);

        if (StringUtils.equalsIgnoreCase(cyyte.getSendType(), "post")) {
            return "redirect:/" + cyyte.getCreatedBy() + "/" + cyyte.getPageName() + ".cyt";
        } else {
            modelMap.put("cyyte", cyyte);
            //Convert the InCyyte to InCyyte Model to display Owner information
            //Assign the same to Session and Model object
            IncyyteModel model = MapUserProperty.getIncyyteModel(cyyte);
            session.setAttribute(SessionKeys.INCYYTE_MODEL, model);
            Logger.debug("model::::" + model);
            modelMap.put("category", model.getCategory());
            modelMap.put("model", model);
            Logger.debug("profilepic::::" + model.getCreatedbyImageLink());
            Logger.debug("category::" + model.getCategory());
            Logger.debug("getUploadedType::" + model.getUploadedType());
            Logger.debug("getUploadedFileLocation::" + model.getUploadedFileLocation());

            //Vote Modal windows
            modelMap.put("voteForm", new VoteModel());
            modelMap.put("loginForm", new UserModel());
            modelMap.put("signUpForm", new UserModel());
            //Creating a New Question
            modelMap.put("inCyyteForm", new IncyyteModel());
            session.setAttribute("questionMaxChar", questionMaxChar);
            return "main/singlePollPage";
        }
    }

    private void processFilteredPollsByCategory(ModelMap model, HttpSession session, int page, int recordsPerPage, String category) throws Exception {
        try {
            List<InCyyteChart> pollsByCategory = questionSrv.getPollsByCategory((page - 1) * recordsPerPage, recordsPerPage, category);
            model.put("inCyyteResults", pollsByCategory);
            Logger.info("inCyyteResults :::" + pollsByCategory.toString());
            //todo:
            //Integer noOfPages = (Integer) session.getAttribute(SessionKeys.MY_FILTER_NO_OF_PAGE);
            //Logger.info("SessionKeys.MY_FILTER_NO_OF_PAGE:::"+session.getAttribute(SessionKeys.MY_FILTER_NO_OF_PAGE));
            //Logger.info("noOfPages :::" + noOfPages);
            //if (noOfPages == null || noOfPages == 0 || noOfPages == 1) {
            List<InCyyteChart> totalPollsInCategory = questionSrv.getPollsByCategory(InCyyteConstants.NO_OFFSET_LIMIT, InCyyteConstants.NO_RECORDS_PER_PAGE_LIMIT, category);

            int noOfRecords = totalPollsInCategory.size();
            int pageCount = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            session.setAttribute(SessionKeys.MY_FILTER_NO_OF_PAGE, pageCount);
            //}
            model.put("page", "filtered");
            if (noOfRecords > recordsPerPage) {
                model.put("paginationNeeded", "Y");
            } else {
                model.put("paginationNeeded", "N");
            }
            Logger.info("page :::" + page);
            model.put("inCyyteForm", new IncyyteModel());

            model.put("voteForm", new VoteModel());
            model.addAttribute("currentPage", page);
            model.addAttribute("category", category);
            model.put("loginForm", new UserModel());
            model.put("signUpForm", new UserModel());
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
    }

    @RequestMapping(value = "/incyyteVideoPage.cyt", method = RequestMethod.GET)
    public String incyyteVideoPage(ModelMap model) {
        try {
            Map<String, Map<String, String>> publicPolls = questionSrv.getPublicPolls();
            Logger.info("publicPolls:::" + publicPolls.values());
            List<String> categories = new ArrayList<String>(publicPolls.keySet());
            model.put("polls", categories);
        } catch (Exception e) {
            Logger.error("Exception::", e);
        }
        Logger.info("inside :  incyyteVideoPage : ");
        return "incyyte_video_page";
    }

    @RequestMapping(value = "/enter_question.cyt", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, ModelMap model, HttpSession session) {

        Logger.debug("processSubmit :  incyyte : " + incyyteModel.getIncyyte());
        session.removeAttribute(SessionKeys.INCYYTE_MODEL);
        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteModel);
        // return form success view
        return "redirect:create_question.cyt";
    }

    /*@RequestMapping(value = "/new_enter_question.cyt", method = RequestMethod.POST)
    public String newProcessSubmit(@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, ModelMap model, HttpSession session) {
        Logger.info("Inside controller");
    	Logger.info("processSubmit :  incyyte : " + incyyteModel.getIncyyte());
        session.removeAttribute(SessionKeys.INCYYTE_MODEL);
        session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteModel);
        // return form success view
        return "redirect:create_question.cyt";
    }*/

    @RequestMapping(value = "/login.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processLogin(@ModelAttribute("loginForm") UserModel userModel, Model model, HttpSession session) throws Exception {
        String email = userModel.getLogin_email();
        String password = userModel.getLogin_pwd();
        model.addAttribute("signUpForm", userModel);
        try {
            if (session.getAttribute(SessionKeys.INCYYTE) != null) {
                session.removeAttribute(SessionKeys.INCYYTE);
            }

            if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password)) {
                User authenticatedUser = loginSrv.authenticateUserLogin(email, password);
                if (authenticatedUser != null) {
                    User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
                    if (null != user) {
                        session.setAttribute(SessionKeys.LOGIN_USER, user);
                        if (user.getResetPwdFlag().equalsIgnoreCase("N")) {
                            return "redirect:home.cyt";
                        }
                        return "resetPassword";
                    }
                }
            }
        } catch (AuthenticationException e) {
            Logger.error("Exception:", e);
        }
        session.removeAttribute(SessionKeys.LOGIN_USER);
        throw new Exception();
    }

    @RequestMapping(value = "/checkEmail.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processCheckEmail(@ModelAttribute("signUpForm") UserModel userModel, BindingResult result, SessionStatus status, HttpSession session) {
        Logger.debug("inside checkEmail.cyt");
        String email = userModel.getSu_email();
        Logger.debug(" email: " + email);
        try {
            User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
            if (user != null) {
                if (user.getEmail() != null && user.getEmail().equalsIgnoreCase(email)) {
                    return "The email is already exist.";
                }
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
            return "failure";
        }
        // return form success view
        return "success";
    }

    @RequestMapping(value = "/signup.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processSubmit(HttpServletRequest request, @ModelAttribute("signUpForm") UserModel userModel, BindingResult result, SessionStatus status, HttpSession session) {
        try {
            Logger.debug("inside signup.cyt");
            String email = userModel.getSu_email();
            Logger.debug(" email: " + email);

            //check user email before signup
            User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);

            if (null != user) {
                Logger.debug("User not null..");

                if (user.getEmail() != null && user.getEmail().equalsIgnoreCase(email)) {
                    Logger.debug("The email is already exist.");
                    return "The email is already exist.";
                }
            } else
                Logger.debug("Success data");
            registrationSrv.signUpUser(MapUserProperty.copyUserSignUpDetails(userModel, request));
            return "success";

        } catch (Exception e) {
            Logger.error("Error:", e);
        }
        Logger.debug("outside signup.cyt");
        // return form success view
        return "failure";
    }

    @RequestMapping(value = "/complete.cyt", method = RequestMethod.GET)
    public String processComplete(HttpServletRequest request, HttpSession session) throws Exception {
        Logger.debug("%%%%%% Signup Completed -> ");
        return "completeSignup";
    }

    @RequestMapping(value = "/activate.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processAjaxActivate(@ModelAttribute("signUpForm") UserModel userModel, BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        String activate = userModel.getActivate_act();
        try {
            if (activate != null) {
                if (null != user) {
                    boolean activated = registrationSrv.activateUser(user.getId(), activate);
                    if (activated) {
                        return "redirect:home.cyt";
                    }
                }
            }
        } catch (Exception e) {
            Logger.error("Exception::", e);
        }
        // return form success view
        return "failure";
    }

    @RequestMapping(value = "/fpassword.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processForgotPassword(@ModelAttribute("loginForm") UserModel userModel) {
        try {
            User user = loginSrv.getUserDetails(userModel.getUser_email(), Constants.GET_BY_MAIL);
            if (user != null && user.getEmail() != null) {
                forgotPwdSrv.requestNewPassword(userModel.getUser_email());
                return "success";
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        // return form success view
        return "failure";
    }

    //implemented method for Forget password,Reactive,Active and Non_Active.
    @RequestMapping(value = "/androidForgotPassword.cyt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String androidProcessForgotPassword(HttpServletRequest request) {
        Logger.debug("inside controller");
        try {

            String email = request.getParameter("email");
            User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
            if (user != null && user.getEmail() != null) {
                if (StringUtils.equalsIgnoreCase(user.getStatus(), "ACTIVE")) {
                    forgotPwdSrv.requestNewPassword(email);
                    return "success";
                } else if (StringUtils.equalsIgnoreCase(user.getStatus(), "DEACTIVATED")) {
                    return "deactivated";
                } else if (StringUtils.equalsIgnoreCase(user.getStatus(), "NON_ACTIVE")) {
                    return "nonactive";
                }
            }
        } catch (Exception e) {
            Logger.info("Exeption:::", e);
        }
        // return form success view
        return "failure";
    }

    @ModelAttribute("genders")
    public Map<String, String> populateGenderList() {
        // Data referencing for categories list box
        return referenceData.getGender();
    }

    @ModelAttribute("ageGroups")
    public Map<String, String> populateAgeGroupList() {
        // Data referencing for categories list box
        return referenceData.getAgeGroup();
    }
}