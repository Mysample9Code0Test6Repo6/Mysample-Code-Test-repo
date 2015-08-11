package com.incyyte.app.web.controller;

import com.incyyte.app.domain.*;
import com.incyyte.app.security.InCyyteSecurity;
import com.incyyte.app.service.*;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.util.UserPollUtil;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PostScriptController implements Constants {
    @Autowired
    private MessagesService messagesService;

    @Autowired
    private SendInCyyteController sendInCyyteController;

    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(PostScriptController.class);

    @Autowired
    private RefData referenceData;

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuickStartService quickStartSrv;
    @Autowired
    private RegistrationService registrationSrv;
    @Autowired
    private HomeService homeSrv;
    @Autowired
    private GroupService groupService;

    @Autowired
    private LoginService loginSrv;

    @Autowired
    private UserPollService userPollSrv;

    @Autowired
    private ContactService contactsSrv;

    @Autowired
    private UserSettingsService userSettingsService;

    // Have to comment this code in order to make Logout functioning and other Social media login processes
    // Please make sure all above mentioned works fine before we enable this piece of code
    @RequestMapping(value = "/{username}.cyt", method = RequestMethod.GET)
    public String initHome(@PathVariable String username, ModelMap model, HttpServletRequest request) {
        StringBuilder url = new StringBuilder("redirect:/");
        url.append(username).append("/").append("home").append(".cyt");
        return url.toString();
    }

    @RequestMapping(value = "/{username}/home", method = RequestMethod.GET)
    public String initHomeForm(@PathVariable String username, ModelMap model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Logger.debug("%%%%%%%% username - > " + username);

        User user = registrationSrv.getUserDetailByUsernameOrUniqueName(username);
        try {
            if (user != null) {

                UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(user.getId());
                Logger.debug("userSettings::" + userSettings);
                Logger.debug("%%%%%%%% Username - > " + user.getUsername());

                if (user.getUsername().equalsIgnoreCase(username) || (userSettings != null && userSettings.getUniquePageName().equalsIgnoreCase(username))) {

                    Logger.debug("%%%%%%%% User Id - > " + user);

                    List<InCyyte> incyytes = quickStartSrv.getUserInCyytesByUserId(user.getId(), Constants.SEND_BY_POST);
                    List<InCyyteChart> charts = new ArrayList<InCyyteChart>();
                    for (InCyyte cyyte : incyytes) {
                        if (cyyte != null && !cyyte.isDeleted() && cyyte.isPublished()) {
                            InCyyteChart chart = quickStartSrv.getPostedInCyyteResponse(cyyte.getIncyyteCode());
                            if (chart != null && chart.getIncyyte() != null) {
                                Logger.debug("%%%%%%%% chart - > " + chart.getQuestion());
                                charts.add(chart);
                            }
                        }
                    }

                    model.put("chart", charts);
                    model.put("username", user.getUsername());
                    model.put("loginForm", new UserModel());
                    model.put("signUpForm", new UserModel());
                    model.put("voteForm", new VoteModel());

                    String userDomainPageName = userSettingsService.getUniquePageName(user.getId());
                    if (userDomainPageName == null || userDomainPageName.isEmpty()) {
                        userDomainPageName = user.getUsername();
                    }

                    model.put("userDomainPageName", userDomainPageName);

                    UserPollPage userPollPage = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.PAGE);
                    Logger.debug("------------------userPollPage---------------" + userPollPage);
                    UserPollPageModel userPollPageModelDB = UserPollUtil.convertUserPollToModel(user.getId(), userPollPage);
                    Logger.debug("------------------userPollPageModelDB---------------" + userPollPageModelDB);

                    if (!StringUtils.isBlank(userPollPageModelDB.getLogoCdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getLogoCdnFileName());
                        userPollPageModelDB.setLogoUrl(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getBannerCdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getBannerCdnFileName());
                        userPollPageModelDB.setBannerUrl(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage1CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage1CdnFileName());
                        userPollPageModelDB.setImageURL1(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage2CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage2CdnFileName());
                        userPollPageModelDB.setImageURL2(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage3CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage3CdnFileName());
                        userPollPageModelDB.setImageURL3(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage4CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage4CdnFileName());
                        userPollPageModelDB.setImageURL4(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage5CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage5CdnFileName());
                        userPollPageModelDB.setImageURL5(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage6CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage6CdnFileName());
                        userPollPageModelDB.setImageURL6(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage7CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage7CdnFileName());
                        userPollPageModelDB.setImageURL7(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage8CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage8CdnFileName());
                        userPollPageModelDB.setImageURL8(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage9CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage9CdnFileName());
                        userPollPageModelDB.setImageURL9(remoteURL);
                    }
                    if (!StringUtils.isBlank(userPollPageModelDB.getImage10CdnFileName())) {
                        String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage10CdnFileName());
                        userPollPageModelDB.setImageURL10(remoteURL);
                    }

                    session.setAttribute("pollSetup", userPollPageModelDB);
                    session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
                    session.setAttribute("incyyteCreator", user);

                    session.setAttribute("viewcode", "");
                    session.removeAttribute("viewcode");

                    //return "posthomepage";
                    return "businessaccount/postPollhome";

                }
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
            session.setAttribute("pollSetup", new UserPollPageModel());
            model.put("pollSetup", new UserPollPageModel());
        }

        String serverURL = Utility.getServerURL(request);
        StringBuilder urlbuilder = new StringBuilder(serverURL).append("/welcome.cyt");
        Logger.debug("%%%%%%%%%5 postscript url -> " + urlbuilder.toString());

        // command object
        model.put("welcomeUrl", urlbuilder.toString());

        return "pagenotfound";
    }

    @RequestMapping(value = "/postscript/{username}/{code}/{pageName}", method = RequestMethod.GET)
    public String initForm(@PathVariable String username, @PathVariable String code, @PathVariable String pageName, ModelMap model, HttpServletRequest request) throws Exception {
        Logger.debug("%%%%%%%% username - > " + username + " %%%%%%%% code - > " + code + " %%%%%%%% page name - > " + pageName);
        StringBuilder url = new StringBuilder("redirect:../../../");
        url.append(username).append("/").append(pageName).append(".cyt");
        return url.toString();
        //return initForm(username, pageName,model, request);
    }

    @RequestMapping(value = "/{username}/{pageName}", method = RequestMethod.GET)
    public String initForm(@PathVariable String username, @PathVariable String pageName, ModelMap model, HttpServletRequest request) throws Exception {
        Logger.debug("%%%%%%%% username - > " + username + " %%%%%%%% page name - > " + pageName);

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        Logger.debug("%%%%%%%% loggedInUser - > " + loggedInUser);

        User user = registrationSrv.getUserDetailByUsernameOrUniqueName(username);
        if (user != null && pageName != null) {
            List<InCyyte> cyytes = quickStartSrv.getUserInCyytesByUserId(user.getId(), SEND_BY_POST);
            InCyyte cyyte = null;

            for (InCyyte question : cyytes) {
                if (question.getPageName() != null && question.getPageName().equalsIgnoreCase(pageName)) {
                    cyyte = question;
                    break;
                }
            }

            if (cyyte != null && !cyyte.isDeleted() && cyyte.isPublished()) {

                Logger.debug("%%%%%%%% PageName - > " + cyyte.getPageName());
                cyyte = quickStartSrv.initChart(cyyte.getId());
                session.setAttribute("isUserVoted", isUserVotedInCyyte(loggedInUser, cyyte.getId().toString()));

                if (cyyte.getPageName() != null && cyyte.getPageName().equalsIgnoreCase(pageName)) {

                    if (user != null) {

                        UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(user.getId());
                        Logger.debug("userSettings::" + userSettings);
                        Logger.debug("%%%%%%%% Username - > " + user.getUsername());

                        if (user.getUsername().equalsIgnoreCase(username) || (userSettings != null && userSettings.getUniquePageName().equalsIgnoreCase(username))) {

                            model.put("chartCode", cyyte.getIncyyteCode());
                            model.put("strapline", cyyte.getStrapline());
                            model.put("logo", cyyte.getUpload_logo_location());
                            model.put("username", user.getUsername());

                            session.setAttribute(SessionKeys.LOGIN_USER_SETTINGS, userSettings);
                            session.setAttribute("incyyteCreator", user);
                            session.setAttribute("incyyteCode", cyyte.getIncyyteCode());
                            session.setAttribute("incyyteId", cyyte.getId().toString());
                            session.setAttribute("memberId", user.getId().toString());
                            session.setAttribute(SessionKeys.INCYYTE, cyyte);
                            Logger.debug("%%%%%%%% IncyyteCode - > " + cyyte.getIncyyteCode() + "  %%%%%%%% MemberId - > " + user.getId());

                            InCyyteChart chart = quickStartSrv.getPostedInCyyteResponse(cyyte.getIncyyteCode());
                            if (chart != null && chart.getIncyyte() != null) {
                                Logger.debug("%%%%%%%% chart - > " + chart.getQuestion());
                                request.getSession().setAttribute(SessionKeys.INCYYTE_CHART, chart);
                                model.put("chart", chart);
                            }
                            try {
                                model.put("loginForm", new UserModel());
                                model.put("signUpForm", new UserModel());
                                model.put("voteForm", new VoteModel());
                                Logger.debug("%%%%%%%% signUpForm - > " + new UserModel());
                                UserContactModel userModel = null;
                                try {
                                    questionService.getRecipientsGenderCount(chart.getIncyyte(), user);
                                    userModel = (UserContactModel) request.getSession().getAttribute(SessionKeys.POLL_MESSAGE_FORM);
                                    if (userModel == null) {
                                        userModel = new UserContactModel();
                                    }
                                } catch (Exception e) {
                                    Logger.error("Exception: While fetching Gender Recipient Count:", e);
                                }
                                model.addAttribute("PollMesageForm", userModel);
                                model.put("chart2", chart);

                                UserPollPage userPollPage = null;

                                if (chart.getIncyyte().getTemplateId() != null) {
                                    List<UserPollPage> pollPages = userPollSrv.getUserPollPagesInformation(user.getId(), AddressType.PAGE);
                                    for (UserPollPage page : pollPages) {
                                        Long pageId = page.getPollPage().getPageId();
                                        if (pageId != null && pageId.toString().equals(chart.getIncyyte().getTemplateId())) {
                                            userPollPage = page;
                                            break;
                                        }
                                    }
                                }

                                if (userPollPage == null) {
                                    userPollPage = userPollSrv.getUserPollPageInfo(user.getId(), AddressType.PAGE);
                                }

                                Logger.debug("------------------userPollPage---------------" + userPollPage);
                                UserPollPageModel userPollPageModelDB = UserPollUtil.convertUserPollToModel(user.getId(), userPollPage);
                                Logger.debug("------------------userPollPageModelDB---------------" + userPollPageModelDB);

                                session.setAttribute("pollSetup", userPollPageModelDB);
                                if (!StringUtils.isBlank(userPollPageModelDB.getLogoCdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getLogoCdnFileName());
                                    userPollPageModelDB.setLogoUrl(remoteURL);
                                }

                                if (!StringUtils.isBlank(userPollPageModelDB.getBannerCdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getBannerCdnFileName());
                                    userPollPageModelDB.setBannerUrl(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage1CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage1CdnFileName());
                                    userPollPageModelDB.setImageURL1(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage2CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage2CdnFileName());
                                    userPollPageModelDB.setImageURL2(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage3CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage3CdnFileName());
                                    userPollPageModelDB.setImageURL3(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage4CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage4CdnFileName());
                                    userPollPageModelDB.setImageURL4(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage5CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage5CdnFileName());
                                    userPollPageModelDB.setImageURL5(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage6CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage6CdnFileName());
                                    userPollPageModelDB.setImageURL6(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage7CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage7CdnFileName());
                                    userPollPageModelDB.setImageURL7(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage8CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage8CdnFileName());
                                    userPollPageModelDB.setImageURL8(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage9CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage9CdnFileName());
                                    userPollPageModelDB.setImageURL9(remoteURL);
                                }
                                if (!StringUtils.isBlank(userPollPageModelDB.getImage10CdnFileName())) {
                                    String remoteURL = FileManagementUtil.getFileURL(Constants.UPLOAD_TYPE_IMAGE, userPollPageModelDB.getImage10CdnFileName());
                                    userPollPageModelDB.setImageURL10(remoteURL);
                                }

                                String from = request.getParameter("frm");
                                if (StringUtils.isNotEmpty(cyyte.getIncyyteCode())) {
                                    if (from != null && from.equals("region"))
                                        chart = quickStartSrv.getInCyyteRegionalResponse(cyyte.getIncyyteCode());
                                    else
                                        chart = quickStartSrv.getPostedInCyyteResponse(cyyte.getIncyyteCode());
                                    List<CommentsModel> commentlist = homeSrv.getPollComments(chart.getIncyyte().getId(), chart.getIncyyteId());
                                    model.put("comments", commentlist);
                                    model.put("inCyyteForm", new IncyyteModel());
                                    model.put("size", commentlist != null ? commentlist.size() : 0);
                                    if (chart != null && chart.getIncyyte() != null) {
                                        request.getSession().setAttribute(SessionKeys.INCYYTE_CHART, chart);
                                        model.put("chart", chart);
                                        model.put("page", "details");

                                        if (chart.getIncyyte().getPublic_poll().equals("N")) {
                                            if (loggedInUser != null && chart.getIncyyte().getUserId().equals(loggedInUser.getId())) {
                                                model.put("sharedPoll", "true");
                                                model.put("emailContactList", new ArrayList<UserContactModel>());
                                            } else {
                                                model.put("sharedPoll", "false");
                                                model.put("emailContactList", null);
                                            }
                                        } else {
                                            model.put("sharedPoll", "true");
                                            model.put("emailContactList", new ArrayList<UserContactModel>());
                                        }
                                    }
                                }
                                CommentsModel CommentsModel = new CommentsModel();
                                model.put("CommentsModel", CommentsModel);
                                model.put("messageModel", new Message());
                                UserContactModel userContactModel = new UserContactModel();
                                if (loggedInUser != null) {
                                    userContactModel.setFirstname(loggedInUser.getFirstname());
                                    userContactModel.setLastname(loggedInUser.getLastname());
                                    userContactModel.setEmail(loggedInUser.getEmail());
                                }
                                model.put("UserContactModel", userContactModel);
                                //Setting values for displaying ConnectToUs and Messages
                                model.put("displayConnectUs", "NO");
                                model.put("displaySendMessage", "NO");

                                if (loggedInUser != null && userPollPage.getPollPage() != null && !(loggedInUser.getId().equals(userPollPage.getPollPage().getUserId()))) {
                                    UserContactModel contact = contactsSrv.contactOfUser(loggedInUser.getEmail(), userPollPage.getPollPage().getUserId());
                                    Logger.debug("firstname" + loggedInUser.getFirstname());
                                    if (contact == null) {
                                        Logger.debug("contacts::" + contact);
                                        model.put("displayConnectUs", "YES");
                                    } else if (StringUtils.equals(contact.getAccept_inv(), "N") || StringUtils.isBlank(contact.getAccept_inv())) {
                                        model.put("displayConnectUs", "YES");
                                    } else if (contact.getBlocked().equals("N")) {
                                        model.put("displaySendMessage", "YES");
                                    }
                                }

                                return "businessaccount/viewUserPoll";
                            } catch (Exception e) {
                                Logger.error("Exception:", e);
                                session.setAttribute("pollSetup", new UserPollPageModel());
                                model.put("pollSetup", new UserPollPageModel());
                            }
                        }
                    }
                }
            }
        }
        String serverURL = Utility.getServerURL(request);
        StringBuilder urlbuilder = new StringBuilder(serverURL).append("/welcome.cyt");
        Logger.debug("%%%%%%%%%5 postscript url -> " + urlbuilder.toString());
        // command object
        model.put("welcomeUrl", urlbuilder.toString());
        return urlbuilder.toString();
    }

    @RequestMapping(value = "/postscript/access.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processForgotPassword(@ModelAttribute("accessform") PostScriptModel postScriptModel,
                                        ModelMap model, HttpSession session) {

        try {
            String code = (String) session.getAttribute("incyyteId");

            Logger.debug("%%%%%%%%% postscript code -> " + code + "  access code - > " + postScriptModel);

            InCyyte cyyte = quickStartSrv.initChart(Long.valueOf(code));

            if (cyyte != null && postScriptModel.getAccess_code() != null) {
                if (cyyte.getAccess_code().equals(postScriptModel.getAccess_code())) {

                    session.setAttribute("viewcode", postScriptModel.getAccess_code());

                    return cyyte.getPageName().concat(".cyt");
                }
            }

        } catch (Exception e) {
            Logger.error("Exception:", e);
        }

        // return form success view
        return "failure";
    }

    private boolean isUserVotedInCyyte(User user, String questionId) {
        Logger.debug("isUservotedIncyyte:::postscript controller");
        InCyyteChart chart = null;
        //Poll that we need to pass
        if (user == null || user.getId() == null) {
            return false;
        }
        try {
            chart = homeSrv.getMyInCyyte(Long.valueOf(questionId));
            Logger.debug("postscript controller:chart::" + chart);
            if (chart == null) {
                Logger.debug("postscript controller:chart IF:::::" + chart);
                return false;
            }

            //If voting is closed then we need to display the result
            if (chart.isIncyyteClosed()) {
                return true;
            }
            Logger.debug("postscript controller: Before isVoted::");
            boolean isVoted = quickStartSrv.isPostResponded(chart.getIncyyteId(), user.getId());
            Logger.debug("postscript controller: isVoted::" + isVoted);
            return isVoted;
        } catch (Exception e) {
            return false;
        }
    }

    public int birthYearLimit() {
        Calendar calendar = new GregorianCalendar();
        Logger.debug("calendar:" + calendar);
        int ageLimit = calendar.get(Calendar.YEAR) - referenceData.getBirthYearLimit();
        Logger.debug("ageLimit:" + ageLimit);
        return ageLimit;
    }

    @RequestMapping(value = "/createAcctPollPage.cyt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String processSubmit(HttpServletRequest request, @ModelAttribute("signUpForm") UserModel userModel, BindingResult result, SessionStatus status, HttpSession session) {
        Logger.debug("userModel::" + userModel);
        try {
            //Logger.debug("Username: " + userModel);
            boolean exists = false;
            try {
                exists = registrationSrv.emailExists(userModel.getSu_email());
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }

            if (!exists) {
                //signup the user first
                User user = registrationSrv.signUpUser(MapUserProperty.copyUserSignUpDetails(userModel, request));
                Logger.debug("user::::" + user);
                User userCreated = loginSrv.getUserDetails(userModel.getSu_email(), Constants.GET_BY_MAIL);
                Logger.debug("userCreated::::" + userCreated);
                if (null != userCreated) {
                    session = InCyyteSecurity.getInstance().changeSessionIdentifier(request);
                    session.setAttribute(SessionKeys.LOGIN_USER, userCreated);
                    VoteModel voteModel = (VoteModel) session.getAttribute("voteModel");
                    Logger.debug("voteModel::" + voteModel);
                    if (userCreated != null && userCreated.getId() != null) {
                        voteModel.setUserId(userCreated.getId());
                        voteModel.setGender(userCreated.getGender());
                        return updateIncyyteChart(voteModel);
                    }
                    Logger.debug("Success data");
                    return "success";
                }
            } else {
                return "userAlreadyExist";
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);

        }
        Logger.debug("outside createAcct.cyt");
        return "failure";
    }

    private String updateIncyyteChart(VoteModel voteModel) {
        try {
            long incyyteId = voteModel.getIncyyteId();
            long memberId = voteModel.getUserId();
            int selectedAnswerId = Integer.parseInt(voteModel.getSelectedAnswer());
            String gender = voteModel.getGender();
            String ageGroup = voteModel.getAgeGroup();
            Date responseDate = voteModel.getResponseDate();
            Logger.debug("vote result controller:::responseDate######" + responseDate + "incyyteId::" + incyyteId);

            InCyyte cyyte = quickStartSrv.initChart(incyyteId);
            Logger.debug("cyyte:::" + cyyte);
            if (quickStartSrv.isContactResponded(incyyteId, memberId)) {
                Logger.debug("cyyte:incyyteId::" + incyyteId);
                Logger.debug("cyyte::memberId:" + memberId);
                return "Sorry ... You have already voted!";
            } else {
                Date closureDate = null;
                Calendar currentDate = null;
                if (cyyte != null && cyyte.getClosureDate() != null) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    closureDate = format.parse(cyyte.getClosureDate());
                    currentDate = Calendar.getInstance();
                    currentDate.setTime(new Date());
                }
                if (closureDate != null && closureDate.before(currentDate.getTime())) {
                    return "Sorry ... The poll is now expired!";
                } else {
                    InCyyteChart cyyteChart = quickStartSrv.updatePostedChart(incyyteId, memberId, selectedAnswerId, gender, ageGroup, responseDate);
                    cyyte = cyyteChart.getIncyyte();
                    Logger.debug("$$$$$$4voteresult controller::::cyyte$$$$$" + cyyteChart);
                    return "success";
                }
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return "failure";
    }

    @RequestMapping(value = "/pollpageInvitation.cyt", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String processInvitation(HttpServletRequest request,
                                    @ModelAttribute("ConnectForm") UserContactModel usercModel,
                                    BindingResult result, SessionStatus status, HttpSession session) {
        Logger.debug("userContactModel::" + usercModel);
        return "success";
    }

    @RequestMapping(value = "/sendPollMessage.cyt", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendingmessage(HttpServletRequest req, @ModelAttribute("sendMsg") Message message, HttpSession session) throws Exception {
        try {
            User incyyteUser = (User) session.getAttribute("incyyteCreator");
            Logger.debug("incyyteUser:" + incyyteUser);

            User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
            Logger.debug("user:" + user);

            Logger.debug("message:" + message.getMessageText());
            Logger.debug("message:" + message.toString());
            messagesService.sendMessage(user, incyyteUser.getUsername(), message.getMessageText());
            return "success:";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(value = "/myPollContact.cyt", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sendPollContact(@ModelAttribute("ContactForm") UserContactModel usercModel, HttpSession session) {
        Logger.debug("enter to psc");
        User user = (User) session.getAttribute("user");
        Logger.debug("user:" + user);

        User incyyteUser = (User) session.getAttribute("incyyteCreator");
        Logger.debug("incyyteUser:" + incyyteUser);
        UserContactModel contact;
        try {
            contact = contactsSrv.contactOfUser(incyyteUser.getEmail(), user.getId());
        } catch (Exception e) {
            contact = null;
        }
        Logger.debug("contactExists::" + contact);

        try {
            if (contact == null) {
                Logger.debug("Its temp::::::::" + incyyteUser);
                contact = new UserContactModel();
                contact.setEmail(incyyteUser.getEmail());
                contactsSrv.addContact(contact, user.getId());
                Logger.debug("if contact::::::::" + contact);
                contactsSrv.SendInvite(contact, user.getId());
            } else {
                Logger.debug("contact::::::::" + contact);
                contactsSrv.updateContact(contact.getEmail(), user.getId());
                contactsSrv.SendInvite(contact, user.getId());
            }
            return "success";
        } catch (Exception e) {
            Logger.error("Exception::", e);
            return "error";
        }
    }

    @RequestMapping(value = "/sharePollToGroup.cyt", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String sharePollToGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Logger.debug("enter sharePollToGroup");
        User user = (User) session.getAttribute("user");
        Logger.debug("user:" + user);
        String incyyteId = request.getParameter("incyyteId");
        String groupId = request.getParameter("groupId");
        InCyyte inCyyte = new InCyyte();
        inCyyte.setGrpId(Long.valueOf(groupId));
        inCyyte.setId(Long.valueOf(incyyteId));
        inCyyte.setUserId(user.getId());
        Logger.debug("inCyyte::" + inCyyte);
        try {
            questionService.shareInCyyteToGroupContacts(inCyyte);
            return "success";
        } catch (Exception e) {
            Logger.error("Exception:", e);
            return "fail";
        }
    }
}