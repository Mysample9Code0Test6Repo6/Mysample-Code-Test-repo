package com.incyyte.app.web.controller.dashboard;

import com.incyyte.app.domain.*;
import com.incyyte.app.service.*;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author RemiOseni
 */
@Controller
public class DashDetailIncyyteController extends DashboardBasePage {

    private Long IMAGE_MAX_SIZE = 2097152L;
    private Long DOC_MAX_SIZE = 2097152L;
    private Long VIDEO_MAX_SIZE = 31457280L;

    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private HomeService homeSrv;

    @Autowired
    private QuickStartService quickStartSrv;

    @Autowired
    private GroupService groupService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ContactService contactsSrv;

    @Autowired
    private RegistrationService registrationSrv;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RefData referenceData;

    private User user;

    @RequestMapping(value = "/dashdetails.cyt", method = RequestMethod.GET)
    public String initDetailsForm(ModelMap model, HttpServletRequest request) {
        user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
        String activationCode = request.getParameter("actcode");
        String email = request.getParameter("email");
        if (user != null && activationCode != null) {
            user = registrationSrv.getUserDetailsByEmailAndCode(email, activationCode);
            request.getSession().setAttribute(SessionKeys.LOGIN_USER, user);
        }
        if (user == null || user.getId() == null) {
            return "redirect:login.cyt";
        }
        String search = "";
        if (request.getParameter("search") != null)
            search = request.getParameter("search");
        MandatoryInfoModel infoModel = new MandatoryInfoModel();
        if (user.getBirthYear() != null && !user.getBirthYear().equals(0))
            infoModel.setBirthYear(user.getBirthYear().toString());
        infoModel.setCountryCode(user.getCountryCode());
        infoModel.setGender(user.getGender());
        infoModel.setPostalCodeArea(user.getPostalCodeArea());
        infoModel.setUserId(user.getId());
        model.put("detailsform", infoModel);
        String code = request.getParameter("code");
        String from = request.getParameter("frm");
        InCyyteChart chart = null;
        if (StringUtils.isNotEmpty(code)) {
            if (from != null && from.equals("region")) {
                chart = quickStartSrv.getInCyyteRegionalResponse(code);
                Logger.debug("chart:::::::" + chart.getIncyyte());
            } else {
                chart = quickStartSrv.getInCyyteResponse(code);
                Logger.debug("chart:::else::::" + chart);
            }

            List<CommentsModel> commentlist = homeSrv.getPollComments(chart.getIncyyte().getId(), chart.getIncyyteId());
            model.put("comments", commentlist);
            model.put("size", commentlist.size());
            model.put("code", code);
            model.put("inCyyteForm", new IncyyteModel());

            if (chart != null && chart.getIncyyte() != null) {
                request.getSession().setAttribute(SessionKeys.INCYYTE_CHART, chart);
                Logger.debug("chart.getIncyyte()::" + chart.getIncyyte().toString());
                model.put("chart", chart);
                model.put("page", "details");
                model.put("sharedPoll", "false");
                model.put("emailContactList", null);
                try {
                    Logger.debug("chart.getIncyyte().getAnswers()::::::::" + chart.getIncyyte());
                    questionService.getRecipientsGenderCount(chart.getIncyyte(), user);
                    Logger.debug("-------Answers---------------" + chart.getIncyyte().getAnswers());
                    UserContactModel userModel = (UserContactModel) request.getSession().getAttribute(SessionKeys.POLL_MESSAGE_FORM);
                    if (userModel == null) {
                        userModel = new UserContactModel();
                    }
                    model.addAttribute("PollMesageForm", userModel);
                } catch (Exception e) {
                    Logger.error("Exception: While fetching Gender Recipient Count:", e);
                }

                Long recipientsCount = new Long("0");
                try {
                    recipientsCount = questionService.getRecipientsCount(chart.getIncyyte().getId());
                } catch (Exception e) {
                    Logger.error("Exception: While fetching Recipient Count:", e);
                }
                model.put("recipientsCount", recipientsCount);
                if (chart.getIncyyte().getPublic_poll().equals("N")) {
                    if (chart.getIncyyte().getUserId().equals(user.getId())) {
                        if (chart.getIncyyte().getSendMethod() == null) {
                            model.put("sharedPoll", "true");
                            model.put("emailContactList", new ArrayList<UserContactModel>());
                        }
                    }
                } else {
                    if (chart.getIncyyte().getSendMethod() == null) {
                        model.put("sharedPoll", "true");
                        model.put("emailContactList", new ArrayList<UserContactModel>());
                    }
                }
                if (null != from) {
                    request.getSession().setAttribute("returnPage", from);
                    if (from.equals("incomming"))
                        request.getSession().setAttribute("backto", "dash.cyt");
                    else if (from.equals("region"))
                        request.getSession().setAttribute("backto", "dashregion.cyt");
                    else
                        request.getSession().setAttribute("backto", "dashsent.cyt");
                }
            }
        }
        CommentsModel CommentsModel = new CommentsModel();
        model.put("CommentsModel", CommentsModel);
        return "dashboard/dashboard";
    }

    @SuppressWarnings({"rawtypes", "unused"})
    private void debugChart(InCyyteChart chart) {
        //DEBUG Chart1
        Map<String, Double> genders = new HashMap<String, Double>();
        genders = chart.getGenderChart();
        Iterator it = genders.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            logger.debug(pairs.getKey() + " = " + pairs.getValue());
            it.remove();
        }

        //DEBUG Chart2
        Map<Answer, Double> checkchart = new HashMap<Answer, Double>();
        checkchart = chart.getChart();
        Iterator it2 = checkchart.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pairs = (Map.Entry) it2.next();
            logger.debug((Answer) pairs.getKey() + " = " + pairs.getValue());
            it2.remove();
        }

        //DEBUG 3
        Iterator<CyyteResponse> iterator = chart.getCyyteResponses().iterator();
    }

    @RequestMapping(value = "/loadincyytedetail", method = RequestMethod.GET)
    public String loadIncyyteDetail(ModelMap model, HttpServletRequest request) throws Exception {
        return "dashboard/dash_load_detail";
    }

    @RequestMapping(value = "/getnextdetail", method = RequestMethod.GET)
    public String getNextDetail(ModelMap model, HttpServletRequest request) throws Exception {
        return "dashboard/dash_load_detail";
    }

    @RequestMapping(value = "/getprevdetail", method = RequestMethod.GET)
    public String getPrevDetail(ModelMap model, HttpServletRequest request) throws Exception {
        return "dashboard/dash_load_detail";
    }

    @RequestMapping(value = "/loadnextdetail", method = RequestMethod.GET)
    public String loadNextDetail(ModelMap model, HttpServletRequest request) throws Exception {
        Logger.debug("loadnextdetail");
        detailPagination(model, request, true);
        return "dashboard/dash_load_detail";
    }

    @RequestMapping(value = "/loadprevdetail", method = RequestMethod.GET)
    public String loadPrevDetail(ModelMap model, HttpServletRequest request) throws Exception {
        Logger.debug("loadprevdetail");
        detailPagination(model, request, false);
        return "dashboard/dash_load_detail";
    }

    @RequestMapping(value = "/searchSharedContacts", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getsearchSharedContacts(HttpServletRequest request, HttpServletResponse response) {
    	Logger.info("inside getsearchSharedContacts ");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        
        int page = Integer.parseInt(StringUtils.defaultString(request.getParameter("pageNumber"),"1"));
		String param = "";
		String search = "";
		int recordsPerPage = referenceData.getPageLimit();
		int noOfRecords = 0;
		if (request.getParameter("param") != null)
			param = request.getParameter("param");
		if (request.getParameter("search") != null)
			search = request.getParameter("search");   
        
        String code = request.getParameter("code");
        Logger.debug("code from Jsp :: " + code);
       if (StringUtils.equals(param, "Enter your search") || StringUtils.equals(param, "null")) {
            param = "";
        }
        List<Map<String, String>> contactList = new ArrayList<Map<String, String>>();
        InCyyteChart chart = quickStartSrv.getInCyyteResponse(code);

        List<UserContactModel> userContactList = displayContactList((page - 1) * recordsPerPage, recordsPerPage,request.getSession(), chart, search, param);
        Logger.debug("userContactList:: " + userContactList);

        if (userContactList.isEmpty()) {
            modelMap.put("endOfFile", "TRUE");
        } else {
            modelMap.put("endOfFile", "FALSE");
        }
        for (UserContactModel userContact : userContactList) {
            Map<String, String> userContactInfo = new HashMap<String, String>();

            if (userContact != null && userContact.isReceivedIncyyte()) {
                userContactInfo.put("displayForCheckedCheckBox", "");
                userContactInfo.put("displayForNormalCheckBox", "none");
            } else {
                userContactInfo.put("displayForCheckedCheckBox", "none");
                userContactInfo.put("displayForNormalCheckBox", "");
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getProfile_img())) {
                userContactInfo.put("profileImgUrl", userContact.getProfile_img());
            } else {
                userContactInfo.put("profileImgUrl", "");
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getEmail())) {
                userContactInfo.put("contactEmail", userContact.getEmail());
            } else {
                userContactInfo.put("contactEmail", "");
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getUsername())) {
                userContactInfo.put("username", userContact.getUsername());
            } else {
                userContactInfo.put("username", "");
            }
            if (userContact != null && StringUtils.isNotBlank(String.valueOf(userContact.getContactid()))) {
                userContactInfo.put("contactIdValue", String.valueOf(userContact.getContactid()));
            } else {
                userContactInfo.put("contactIdValue", "");
            }
            contactList.add(userContactInfo);
        }
        modelMap.put("contacts", contactList);
        return modelMap;
    }

    @RequestMapping(value = "/searchAddContactsGetIncyyte", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> searchAddContactsGetIncyyte(HttpServletRequest request, HttpServletResponse response) {
        Logger.info("inside searchAddContactsGetIncyyte():DashDetailsIncyyteController ");
        User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
        Logger.info("user::" + user);
        Map<String, Object> modelMap = new HashMap<String, Object>();

        int page = 1;
        String param = "";
        String search = "";
        int recordsPerPage = referenceData.getPageLimit();
        int noOfRecords = 0;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        if (request.getParameter("param") != null)
            param = request.getParameter("param");
        if (request.getParameter("search") != null)
            search = request.getParameter("search");

        Logger.debug("search from Jsp :: " + param);
        if (StringUtils.equals(param, "Enter your search") || StringUtils.equals(param, "null")) {
            param = "";
        }
        List<Map<String, String>> contactList = new ArrayList<Map<String, String>>();

        try {
            UserContactlist userContactList = contactsSrv.getUserContacts(user.getId(), (page - 1) * recordsPerPage, recordsPerPage, param, search);
            Logger.info("userContactList::" + userContactList);
            for (UserContactModel userContact : userContactList.getUserContactlist()) {
                Logger.info("userContact::"+userContact);
                Map<String, String> userContactInfo = new HashMap<String, String>();
                if (userContact != null && userContact.isReceivedIncyyte()) {
                    userContactInfo.put("displayForCheckedCheckBox", "");
                    userContactInfo.put("displayForNormalCheckBox", "none");
                } else {
                    userContactInfo.put("displayForCheckedCheckBox", "none");
                    userContactInfo.put("displayForNormalCheckBox", "");
                }
                if (userContact != null && StringUtils.isNotBlank(userContact.getProfile_img())) {
                    userContactInfo.put("profileImgUrl", userContact.getProfile_img());
                } else {
                    userContactInfo.put("profileImgUrl", "");
                }
                if (userContact != null && StringUtils.isNotBlank(userContact.getEmail())) {
                    userContactInfo.put("contactEmail", userContact.getEmail());
                } else {
                    userContactInfo.put("contactEmail", "");
                }
                if (userContact != null && StringUtils.isNotBlank(userContact.getUsername())) {
                    userContactInfo.put("username", userContact.getUsername());
                } else {
                    userContactInfo.put("username", "");
                }
                if (userContact != null && StringUtils.isNotBlank(String.valueOf(userContact.getContactid()))) {
                    userContactInfo.put("contactIdValue", String.valueOf(userContact.getContactid()));
                } else {
                    userContactInfo.put("contactIdValue", "");
                }

                contactList.add(userContactInfo);
            }
            modelMap.put("contactsLength", userContactList.getUserContactlist().size());
        } catch (Exception e) {
            // TODO: handle exception
            Logger.error("exception:: ", e);
        }
        modelMap.put("contacts", contactList);
        return modelMap;
    }

    //New Methods

    @RequestMapping(value = "/loadedContactsIncyyte.cyt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> loadedContactsIncyyte(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String pageNumber = request.getParameter("pageNumber");
        int offset = Integer.parseInt(pageNumber) - 1;
        offset = offset * 10;
        int noOfRecords = 10;
        String param = request.getParameter("param");
        String search = request.getParameter("search");
        if (StringUtils.equals(search, "Enter your search") || StringUtils.equals(search, "null")) {
            search = "";
        }
        if (StringUtils.equals(param, "null")) {
            param = "";
        }
        UserContactlist contacts = contactsSrv.getUserContacts(user.getId(), offset, noOfRecords, param, search);
        Logger.info("contactsList:: " + contacts.getUserContactlist());
        if (contacts != null && (contacts.getUserContactlist() != null) && (contacts.getUserContactlist().size() == 0)) {
            modelMap.put("endOfFile", "TRUE");
        } else {
            modelMap.put("endOfFile", "FALSE");
        }

        List<Map<String, String>> contactList = new ArrayList<Map<String, String>>();

        for (UserContactModel userContact : contacts.getUserContactlist()) {
            Map<String, String> userContactInfo = new HashMap<String, String>();

            if (userContact != null && userContact.isReceivedIncyyte()) {
                userContactInfo.put("displayForCheckedCheckBox", "");
                userContactInfo.put("displayForNormalCheckBox", "none");
            } else {
                userContactInfo.put("displayForCheckedCheckBox", "none");
                userContactInfo.put("displayForNormalCheckBox", "");
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getProfile_img())) {
                userContactInfo.put("profileImgUrl", userContact.getProfile_img());
            } else {
                userContactInfo.put("profileImgUrl", "");
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getEmail())) {
                userContactInfo.put("contactEmail", userContact.getEmail());
            } else {
                userContactInfo.put("contactEmail", "");
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getUsername())) {
                userContactInfo.put("username", userContact.getUsername());
            } else {
                userContactInfo.put("username", "");
            }
            if (userContact != null && StringUtils.isNotBlank(String.valueOf(userContact.getContactid()))) {
                userContactInfo.put("contactIdValue", String.valueOf(userContact.getContactid()));
            } else {
                userContactInfo.put("contactIdValue", "");
            }

            contactList.add(userContactInfo);
        }

        modelMap.put("contacts", contactList);
        Logger.info("modelMap::" + modelMap);
        return modelMap;
    }
    //End

    @SuppressWarnings("unchecked")
    private String detailPagination(ModelMap model, HttpServletRequest request, boolean isNext) {

        String frm = (String) request.getSession().getAttribute("backto");
        int count = 0;
        HttpSession session = request.getSession();
        InCyyteChart chart = (InCyyteChart) session.getAttribute(SessionKeys.INCYYTE_CHART);
        setDetailPage(model, request, chart);

        if (frm.equals("dash.cyt")) {
            List<InCyyteChart> incomingList = (List<InCyyteChart>) session.getAttribute("myIncomeinCyyte");
            if (incomingList == null || incomingList.isEmpty())
                incomingList = getInCyyteResults(session);
            for (InCyyteChart incoming : incomingList) {
                if (incoming.getIncyyteId().equals(chart.getIncyyteId())) {
                    if (isNext) {
                        if (incomingList.size() > count + 1) {
                            InCyyteChart incyyteChart = incomingList.get(++count);
                            setDetailPage(model, request, incyyteChart);
                            return "success";
                        }
                    } else {
                        if (count - 1 > 0) {
                            InCyyteChart incyyteChart = incomingList.get(--count);
                            setDetailPage(model, request, incyyteChart);
                            return "success";
                        }
                    }
                    break;
                }
                count++;
            }
        } else {
            List<InCyyteChart> sentList = (List<InCyyteChart>) session.getAttribute("mySentInCyytes");
            if (sentList == null || sentList.isEmpty())
                sentList = getMyInCyytes(session);
            for (InCyyteChart sent : sentList) {
                if (sent.getIncyyteId().equals(chart.getIncyyteId())) {
                    if (isNext) {
                        if (sentList.size() > count + 1) {
                            InCyyteChart incyyteChart = sentList.get(++count);
                            setDetailPage(model, request, incyyteChart);
                            return "success";
                        }
                    } else {
                        if (count - 1 >= 0) {
                            InCyyteChart incyyteChart = sentList.get(--count);
                            setDetailPage(model, request, incyyteChart);
                            return "success";
                        }
                    }
                    break;
                }
                count++;
            }
        }
        return "failure";
    }

    private void setDetailPage(ModelMap model, HttpServletRequest request, InCyyteChart incyyteChart) {
        request.getSession().setAttribute(SessionKeys.INCYYTE_CHART, incyyteChart);
        String search = "";
        List<CommentsModel> commentlist = homeSrv.getPollComments(incyyteChart.getIncyyte().getId(), incyyteChart.getIncyyteId());
        model.put("comments", commentlist);
        model.put("code", incyyteChart.getIncyyte().getIncyyteCode());
        model.put("size", commentlist.size());
        model.put("chart", incyyteChart);
        model.put("page", "details");
        model.put("inCyyteForm", new IncyyteModel());
        model.put("CommentsModel", new CommentsModel());

        model.put("sharedPoll", "false");
        if (incyyteChart.getIncyyte().getPublic_poll().equals("N")) {
            if (incyyteChart.getIncyyte().getUserId().equals(user.getId())) {
                if (incyyteChart.getIncyyte().getSendMethod() == null) {
                    model.put("sharedPoll", "true");
                    model.put("emailContactList", new ArrayList<UserContactModel>());
                }
            }
        } else {
            if (incyyteChart.getIncyyte().getSendMethod() == null) {
                model.put("sharedPoll", "true");
                model.put("emailContactList", new ArrayList<UserContactModel>());
            }
        }
    }

    public List<UserContactModel> displayContactList(int offSet, int recordsPerPage, HttpSession session, InCyyteChart incyyteChart, String search, String param ) {
        Logger.debug("=====Inside controller in Create Question : displayContactList =====");
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        if (user != null && user.getId() != null) {
            UserContactlist contactList = contactsSrv.getUserContactsFromSearch(offSet, recordsPerPage, user.getId(), search, param);
            if (contactList != null && contactList.getUserContactlist() != null) {
                List<UserContactModel> modelList = new ArrayList<UserContactModel>();
                List<Contact> incyyteContacts = incyyteChart.getIncyyte().getContacts();
                List<Contact> shareContacts = groupService.getUserShareContacts(incyyteChart.getIncyyteId(), user.getId());
                Logger.info("shareContacts"+shareContacts);
                if (incyyteContacts == null) {
                    incyyteContacts = new ArrayList<Contact>();
                }
                incyyteContacts.addAll(shareContacts);
                if (incyyteContacts != null && !incyyteContacts.isEmpty()) {
                    for (UserContactModel usercontact : contactList.getUserContactlist()) {
                        String userEmail = usercontact.getEmail();
                        for (Contact contact : shareContacts) {
                            if (StringUtils.equalsIgnoreCase(userEmail, contact.getEmail())) {
                                Logger.debug(usercontact.getEmail() + " == " + contact.getEmail());
                                usercontact.setReceivedIncyyte(true);
                                break;
                            }
                        }
                        modelList.add(usercontact);
                    }
                    return modelList;
                } else {
                    return contactList.getUserContactlist();
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/addComments.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String addComments(@ModelAttribute("CommentsModel") CommentsModel cmntModel, ModelMap model, HttpSession session) {
        Logger.debug(" addComments :  addComments" + cmntModel.getComment());
        try {
            user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
            if (user != null && user.getId() != null) {
                homeSrv.addComments(cmntModel, user);
                session.setAttribute(SessionKeys.LOGIN_USER, user);
                return "success";
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return "failure";
    }

    @RequestMapping(value = "/friendRequest.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String requestFriend(@ModelAttribute("requestForm") CommentsModel cmntModel, ModelMap model, HttpSession session) {
        Logger.debug(" friend Request :....");
        try {
            user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
            String userName = StringUtils.defaultString(user.getUsername(), user.getEmail());
            if (user != null && user.getId() != null) {
                //1. check if sending request to self
                if (cmntModel.getCommentby().equals(userName))
                    return "error cannot send request to self.";

                //2. check if already contact
                UserContactModel linkedContact = contactsSrv.personLinkedToUser(cmntModel.getCommentby(), user.getEmail());
                Logger.debug("linkedContact::" + linkedContact);
                //Contact exists and is not deleted or not hidden then return error
                if (linkedContact != null
                        && !StringUtils.equals(linkedContact.getActive_ind(),"D")
                        && !StringUtils.equals(linkedContact.getHidden(),"Y")) {
                    return "error you are already linked to this user, please check your contact list.";
                }

                //3. add as contact and send request
                User personInfo = registrationSrv.getUserDetailByEmailOrUsername(cmntModel.getCommentby());
                UserContactModel cm = new UserContactModel();
                if (linkedContact != null) {
                    if (StringUtils.equals(linkedContact.getActive_ind(),"D")) {
                        //Reactivate Contact
                        contactsSrv.reactivateDeletedContact(linkedContact.getContactid());
                        cm.setContactid(linkedContact.getContactid());
                    } else if (StringUtils.equals(linkedContact.getHidden(),"Y")) {
                        //Make the contact available from Hidden
                        contactsSrv.makeHiddenContactsActive(linkedContact);
                        cm.setContactid(linkedContact.getContactid());
                    }
                } else {//add new contact
                    cm.setEmail(personInfo.getEmail());
                    cm.setAccept_inv("Y");
                    contactsSrv.addContact(cm, user.getId());
                    long contactId = contactsSrv.contactExistForUser(user.getId(), personInfo.getEmail());
                    cm.setContactid(contactId);
                }

                contactsSrv.SendInvite(cm, user.getId());
                Logger.debug(" process friend request for user complete: linked- " + cmntModel.getCommentby() + "  TO  " + userName);
                session.setAttribute(SessionKeys.LOGIN_USER, user);
                return "success";
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return "error";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/shareIncyyte.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String shareIncyyte(@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel, ModelMap model, HttpServletRequest request) {
        Logger.debug("Email - " + incyyteModel.getEmailArr());
        if (incyyteModel.getEmailArr() != null) {

            user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
            String emailArr = incyyteModel.getEmailArr();
            @SuppressWarnings("rawtypes")
            Hashtable contacts = getContacts(emailArr, user.getId());

            InCyyteChart chart = (InCyyteChart) request.getSession().getAttribute(SessionKeys.INCYYTE_CHART);
            InCyyte incyyte = chart.getIncyyte();
            incyyte.setContacts((List<Contact>) contacts.get("LIST"));

            //share inCyytes
            try {
                boolean shared = quickStartSrv.shareInCyyte(user, incyyte, contacts, chart.isIncyyteClosed());
                Logger.debug("shared::" + shared);
                if (shared == false) {
                    Logger.debug("inside return::");
                    return "emailPollCountError";
                }
            } catch (DuplicateKeyException e) {
                Logger.debug("InCyyte already shared and hence skipped:" + incyyte);
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Hashtable getContacts(String arr, Long userId) {
        List<Contact> contacts = new ArrayList<Contact>();
        Hashtable contactList = new Hashtable();

        for (String email : arr.split("[\\s,;]+")) {
            Logger.debug(" contact  email -> " + email + "  UserID: " + userId);

            try {
                UserContactModel model = contactsSrv.contactOfUser(email, userId);
                Contact contact = new Contact();
                contact.setEmail(email);
                contact.setUserId(userId);
                if (model != null && model.getContactid() != 0) {
                    contact.setStatus(model.getStatus());
                    contact.setBlocked(model.getBlocked());
                    contact.setContactId(model.getContactid());
                } else {
                    String invitationCode = quickStartSrv.getInvitationCode();
                    contact.setInvitationid(invitationCode);
                    contact.setStatus(quickStartSrv.verifyContactalreadyMember(contact.getEmail()) ? "M" : "NM");
                    contact.setAccept_inv("N");
                    contact.setBlocked("N");
                    contact.setSent_invite("Y");

                    long contactId = contactsSrv.addContact(getUserContactModel(contact), userId);
                    contact.setId(contactId);
                    contact.setContactId(Long.valueOf(contactId));
                }
                contacts.add(contact);
                contactList.put(contact.getContactId(), contact);
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
        }
        contactList.put("LIST", contacts);
        return contactList;
    }

    private UserContactModel getUserContactModel(Contact contact) {
        UserContactModel model = new UserContactModel();
        model.setAccept_inv(contact.getAccept_inv());
        model.setActive_ind(contact.getActive_ind());
        model.setBlocked(contact.getBlocked());
        model.setEmail(contact.getEmail());
        model.setInvitationid(contact.getInvitationid());
        model.setSent_invite(contact.getSent_invite());
        model.setStatus(contact.getStatus());
        model.setUserid(String.valueOf(contact.getUserId()));

        return model;
    }

    @RequestMapping(value = "/sendMessageToPollRecipients.cyt", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String sendPollMessage(@ModelAttribute("PollMesageForm") UserContactModel usercModel, ModelMap modelmap, HttpSession session, HttpServletRequest request) {
        Logger.debug("usercModel - " + usercModel);
        user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
        UserContactModel usercModelSession = (UserContactModel) session.getAttribute("PollMesageForm");
        Logger.debug("usercModel2::" + usercModelSession);
        if (usercModelSession == null) {
            usercModelSession = usercModel;
        }
        UserContactModel emailUserContact = new UserContactModel();
        emailUserContact.setChecked(usercModel.getChecked());
        emailUserContact.setPollMessageContent(usercModel.getPollMessageContent());
        emailUserContact.setUploadedFileLocation(usercModelSession.getUploadedFileLocation());
        InCyyteChart chart = (InCyyteChart) request.getSession().getAttribute(SessionKeys.INCYYTE_CHART);
        Logger.debug("chart:::::::" + chart.getIncyyte());
        quickStartSrv.sendMessageToPollRecipients(user, emailUserContact, chart);
        usercModelSession.setChecked(null);
        usercModelSession.setPollMessageContent(null);
        usercModelSession.setUploadedFileLocation(null);
        // usercModelSession.setUploadedFileName("");
        session.setAttribute(SessionKeys.POLL_MESSAGE_FORM, usercModelSession);
        modelmap.put("PollMesageForm", usercModelSession);
        return "success";
    }

    @RequestMapping(value = "/uploadimageforcomment", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImageForComment(@ModelAttribute("CommentsModel") CommentsModel cmntModel, BindingResult result, SessionStatus status, HttpSession session, Model model) throws Exception {
        Logger.debug("enter into uploadImageForComment controller");
        Logger.debug("CommentModel" + cmntModel);
        if (!cmntModel.getUploadCommentPhotoFile().isEmpty() ||
                StringUtils.isNotBlank(cmntModel.getSearchedFileNameComment()) || (StringUtils.isNotBlank(cmntModel.getYoutubeCommentVideoId()))) {
            CommonsMultipartFile multipartFile = cmntModel.getUploadCommentPhotoFile();
            if (multipartFile != null && multipartFile.getSize() > 0 && multipartFile.getSize() < IMAGE_MAX_SIZE && StringUtils.isEmpty(cmntModel.getSearchedFileNameComment())) {
                Logger.debug("In Normal Image Upload" + cmntModel);
                Logger.debug("Uploading logo:" + multipartFile.getOriginalFilename());
                String remoteFile = generateFileName(multipartFile.getOriginalFilename());
                Logger.debug("cdn file name:remoteFile:" + remoteFile);
                String type = getType(multipartFile.getContentType());
                String storageURL = FileManagementUtil.uploadFile(type, remoteFile, multipartFile, null);
                Logger.debug("storageURL" + storageURL);
                cmntModel.setUploadCommentType(multipartFile.getContentType());
                cmntModel.setUploadCommentLocation(type);
                cmntModel.setUploadCommentCdnFileName(remoteFile);
                try {
                    commentService.updateComment(cmntModel);
                    return "success";
                } catch (Exception e) {
                    Logger.error("unable to update comments " + e);
                }
            } else if (StringUtils.isNotBlank(cmntModel.getSearchedFileNameComment())) {
                Logger.debug("In GoogleSearch Image Upload" + cmntModel);
                // Downloads the file and keeps in temp location
                URL url = new URL(cmntModel.getSearchedFileURLComment());
                String tDir = System.getProperty("java.io.tmpdir");
                String path = tDir + cmntModel.getSearchedFileNameComment();
                File file = new File(path);
                FileUtils.copyURLToFile(url, file);
                // Build photo object for uploading the image
                Photo searchedImage = new Photo();
                searchedImage.setData(new FileInputStream(file));
                searchedImage.setFileName(file.getName());
                String fileType = FileManagementUtil.getMimeType(cmntModel.getSearchedFileURLComment());
                searchedImage.setContentType(fileType);
                searchedImage.setContainerName(fileType);
                searchedImage.setSize(file.getTotalSpace());
                Logger.debug("Searched Image:" + searchedImage);

                String remoteFile = generateFileName(searchedImage.getFileName());
                String type = getType(searchedImage.getContentType());
                searchedImage.setFileName(remoteFile);
                searchedImage.setContentType(type);
                String uploadURL = FileManagementUtil.uploadFile(type, remoteFile, null, searchedImage);
                Logger.debug("uploaded Url from Google Search " + uploadURL);
                cmntModel.setUploadCommentType(type);
                cmntModel.setUploadCommentLocation(type);
                cmntModel.setUploadCommentCdnFileName(remoteFile);
                cmntModel.setSearchedFileURLComment(uploadURL);
                Logger.debug("PollMesageFormModel- " + cmntModel);
                model.addAttribute("PollMesageForm", cmntModel);
                Logger.debug("usercModel::after uploading from search" + cmntModel);
                try {
                    commentService.updateComment(cmntModel);
                    return "success";
                } catch (Exception e) {
                    Logger.error("unable to update comments " + e);
                }
            } else if (StringUtils.isNotBlank(cmntModel.getYoutubeCommentVideoId())) {

                String youtubeUrl = "https://www.youtube.com/embed/" + cmntModel.getYoutubeCommentVideoId();
                cmntModel.setYoutubeCommentVideoURL(youtubeUrl);
                Logger.debug("include youtubeUrl to comments. ");
                try {
                    commentService.updateComment(cmntModel);
                    return "success";
                } catch (Exception e) {
                    Logger.error("unable to update comments " + e);
                }
            }
        }
        return "failure";
    }

    @RequestMapping(value = "/deleteCommentPhotos", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCommentPhotos(@ModelAttribute("CommentsModel") CommentsModel cmntModel, BindingResult result, SessionStatus status, HttpSession session, Model model) throws Exception {
        Logger.debug("cmntModel::" + cmntModel);
        try {
            if (cmntModel != null) {
                CommentsModel commentsModel = commentService.getCommentDetails(cmntModel);
                Logger.debug("commentsModel:::" + commentsModel);

                if (commentsModel != null && StringUtils.isBlank(commentsModel.getYoutubeCommentVideoURL())) {
                    String containerName = commentsModel.getUploadCommentLocation();
                    String fileName = commentsModel.getUploadCommentCdnFileName();
                    Logger.debug("fileName :::" + fileName);
                    Logger.debug("containerName :::" + containerName);
                    FileManagementUtil.deleteRemoteFile(containerName, fileName);

                    cmntModel.setCommentPicture(null);
                    cmntModel.setUploadCommentType(null);
                    cmntModel.setUploadCommentLocation(null);
                    cmntModel.setUploadCommentCdnFileName(null);
                } else if (StringUtils.isNotBlank(commentsModel.getYoutubeCommentVideoURL())) {
                    cmntModel.setYoutubeCommentVideoURL(null);
                    cmntModel.setYoutubeCommentVideoId(null);
                }

                Logger.debug("cmntModel:::" + cmntModel);
                commentService.updateComment(cmntModel);
                return "success";
            }
        } catch (Exception e) {
            Logger.error("unable to update comments ", e);
        }

        return "failure";
    }

    @RequestMapping(value = "/dashdetailuploadlogo", method = RequestMethod.POST)
    @ResponseBody
    public String uploadMultipartFile(HttpServletRequest request,
                                      @ModelAttribute("PollMesageForm") UserContactModel usercModel, ModelMap modelmap,
                                      BindingResult result, SessionStatus status, HttpSession session) throws Exception {
        Logger.debug("UserContactModel:::::::::" + usercModel);
        try {
            if (result.hasErrors()) {
                for (ObjectError error : result.getAllErrors()) {
                    Logger.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
                }
            } else {
                UserContactModel usercSessionModel = (UserContactModel) session.getAttribute(SessionKeys.POLL_MESSAGE_FORM);
                Logger.debug("usercSessionModel:: " + usercSessionModel);

                if (usercSessionModel == null || StringUtils.isBlank(usercSessionModel.getPollMessageContent())) {
                    Logger.debug("usercSession::" + usercSessionModel);
                    usercSessionModel = usercModel;
                }

                //Only in case of photos we have two modes to select a file
                if (!usercModel.getUploadedDocFile().isEmpty()
                        || (!usercModel.getUploadedPhotoFile().isEmpty()
                        || StringUtils.isNotBlank(usercModel.getSearchedFileName()))
                        || (!usercModel.getUploadedVideoFile().isEmpty() || StringUtils.isNotBlank(usercModel.getYouTubeVideoIdPromotion()))) {

                    Logger.debug("uploadMultipartFile - upload not empty");

                    if (usercModel.getUploadedType().equals(Constants.UPLOAD_TYPE_DOC)
                            && !usercModel.getUploadedDocFile().isEmpty()
                            && usercModel.getUploadedDocFile().getSize() < DOC_MAX_SIZE) {
                        usercSessionModel.setUploadedDocFile(usercModel.getUploadedDocFile());
                        usercSessionModel.setUploadedFile(usercModel.getUploadedDocFile());
                    } else if (usercModel.getUploadedType().equals(Constants.UPLOAD_TYPE_IMAGE)
                            && !usercModel.getUploadedPhotoFile().isEmpty()
                            && usercModel.getUploadedPhotoFile().getSize() < IMAGE_MAX_SIZE) {
                        usercSessionModel.setUploadedPhotoFile(usercModel.getUploadedPhotoFile());
                        usercSessionModel.setUploadedFile(usercModel.getUploadedPhotoFile());
                    } else if (usercModel.getUploadedType().equals(Constants.UPLOAD_TYPE_VIDEO)
                            && !usercModel.getUploadedVideoFile().isEmpty()
                            && usercModel.getUploadedVideoFile().getSize() < VIDEO_MAX_SIZE) {
                        usercSessionModel.setUploadedVideoFile(usercModel.getUploadedVideoFile());
                        usercSessionModel.setUploadedFile(usercModel.getUploadedVideoFile());
                    } //This portion is again for Photos - Which are selected from Google images
                    else if (StringUtils.isNotBlank(usercModel.getSearchedFileName())) {
                        //photoFile.
                        try {
                            URL url = new URL(usercModel.getSearchedFileURL());
                            String tDir = System.getProperty("java.io.tmpdir");
                            String path = tDir + usercModel.getSearchedFileName();
                            File file = new File(path);
                            FileUtils.copyURLToFile(url, file);
                            Logger.debug("USER_C_MODEL:" + usercModel);
                            usercSessionModel.setUploadedType(usercModel.getUploadedType());
                            Photo searchedImage = new Photo();
                            searchedImage.setData(new FileInputStream(file));
                            searchedImage.setFileName(file.getName());
                            searchedImage.setContentType(FileManagementUtil.getMimeType(usercModel.getSearchedFileURL()));
                            searchedImage.setContainerName(FileManagementUtil.getMimeType(usercModel.getSearchedFileURL()));
                            searchedImage.setSize(file.getTotalSpace());
                            Logger.debug("Searched Image:" + searchedImage);
                            uploadMessagePhotos(usercSessionModel, searchedImage);
                            session.setAttribute("uploadedFileLocation", usercSessionModel.getUploadedFileLocation());
                            session.setAttribute("filename", usercSessionModel.getSearchedFileName());
                            session.setAttribute(SessionKeys.POLL_MESSAGE_FORM, usercSessionModel);
                            modelmap.put("PollMesageForm", usercSessionModel);
                            Logger.debug("USER_C_MODEL:" + usercSessionModel);
                            return usercSessionModel.getUploadedFileLocation();
                        } catch (Exception e) {
                            Logger.error("Exception:", e);
                        }
                    } else if (StringUtils.isNotBlank(usercModel.getYouTubeVideoIdPromotion())) {
                        Logger.debug("UploadYoutubeVideos to promotional messages.");
                        String youtubeUrl = "https://www.youtube.com/embed/" + usercModel.getYouTubeVideoIdPromotion();
                        usercSessionModel.setUploadedFileLocation(youtubeUrl);
                        session.setAttribute("uploadedFileLocation", usercSessionModel.getUploadedFileLocation());
                        session.setAttribute(SessionKeys.POLL_MESSAGE_FORM, usercSessionModel);
                        modelmap.put("PollMesageForm", usercSessionModel);
                        return usercSessionModel.getUploadedFileLocation();

                    } else {
                        Logger.debug("uploadMultipartFile - upload is empty or too big");
                        throw new Exception();
                    }
                    usercSessionModel.setUploadedType(usercModel.getUploadedType());

                    if (!usercSessionModel.getUploadedFile().isEmpty()) {
                        Logger.debug("here :::: - uploadMultipartFile name -> " + usercSessionModel.getUploadedFile());
                        Logger.debug("here :::: - uploadMultipartFile size -> " + usercSessionModel.getUploadedFile().getSize());
                        uploadFile(usercSessionModel);
                        session.setAttribute("uploadedFileLocation", usercSessionModel.getUploadedFileLocation());
                        session.setAttribute("filename", usercSessionModel.getFileName());
                        Logger.debug("Before returning :Session:" + usercSessionModel);
                        session.setAttribute(SessionKeys.POLL_MESSAGE_FORM, usercSessionModel);
                        modelmap.put("PollMesageForm", usercSessionModel);
                        Logger.debug("USER_C_MODEL:" + usercSessionModel);
                        return usercSessionModel.getUploadedFileLocation();
                    }
                }
            }
        } catch (Exception e) {
            Logger.error("uploadMultipartFile - upload is empty or too big", e);
        }
        return "failure";
    }

    public void uploadMessagePhotos(UserContactModel usercModel, Photo searchedPicture) {
        if (searchedPicture != null && searchedPicture.getSize() > 0) {
            Logger.debug("OriginalFilename - " + searchedPicture.getFileName());
            Logger.debug("ContentType - " + searchedPicture.getContentType());
            Logger.debug("Size - " + searchedPicture.getSize());
            try {
                usercModel.setFileName(searchedPicture.getFileName());
                usercModel.setContentType(searchedPicture.getContentType());
                String remoteFile = generateFileName(searchedPicture.getFileName());
                String type = getType(searchedPicture.getContentType());
                usercModel.setUploadedFileName(remoteFile);
                usercModel.setUploadedFileType(type);
                searchedPicture.setFileName(remoteFile);
                searchedPicture.setContentType(type);
                String uploadURL = FileManagementUtil.uploadFile(usercModel.getUploadedFileType(), usercModel.getUploadedFileName(), null, searchedPicture);
                Logger.debug("remoteFile url - " + uploadURL);
                usercModel.setUploadedFileLocation(uploadURL);
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
        } else {
            Logger.debug("mulipartfile is null ");
        }
    }

    //UploadFile method is used to upload the object when Question images are selected
    public UserContactModel uploadFile(UserContactModel usercModel) {
        CommonsMultipartFile multipartFile = usercModel.getUploadedFile();
        if (multipartFile != null && multipartFile.getSize() > 0) {
            Logger.debug("OriginalFilename - " + multipartFile.getOriginalFilename());
            Logger.debug("ContentType - " + multipartFile.getContentType());
            Logger.debug("Name - " + multipartFile.getName());
            Logger.debug("Size - " + multipartFile.getSize());
            Logger.debug("StorageDescription - " + multipartFile.getStorageDescription());
            try {
                usercModel.setFileName(multipartFile.getOriginalFilename());
                usercModel.setContentType(multipartFile.getContentType());
                String remoteFile = generateFileName(multipartFile.getOriginalFilename());
                String type = getType(multipartFile.getContentType());
                usercModel.setUploadedFileName(remoteFile);
                usercModel.setUploadedFileType(type);
                String uploadURL = FileManagementUtil.uploadFile(usercModel.getUploadedFileType(), usercModel.getUploadedFileName(), multipartFile, null);
                Logger.debug("remoteFile url - " + uploadURL);
                usercModel.setUploadedFileLocation(uploadURL);
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
        } else {
            Logger.debug("mulipartfile is null ");
        }
        return usercModel;
    }

    protected String getType(String cType) throws IOException {
        return cType.substring(0, cType.indexOf("/"));
    }

    protected String generateFileName(String fileName) {
        Logger.debug("File name = " + fileName);
        if (fileName != null) {
            StringBuilder fName = new StringBuilder();
            String ext = "";
            int mid = fileName.lastIndexOf(".");
            fName.append(fileName.substring(0, mid));
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy hhmmss");
            String fDate = sdf.format(date);
            Logger.debug("Today's Date - " + fDate);

            fName.append(" ");
            fName.append(fDate);

            Logger.debug("File name prefix = " + fName.toString());

            ext = fileName.substring(mid, fileName.length());
            Logger.debug("Extension = " + ext);
            fName.append(ext);
            Logger.debug("File name = " + fName.toString().replace(" ", "_"));
            return fName.toString().replace(" ", "_");
        }
        return fileName;
    }

    @RequestMapping(value = "/deleteSilverMemeberuploadedfile", method = RequestMethod.POST)
    @ResponseBody
    public String deleteuploadedfile(HttpServletRequest request,
                                     @ModelAttribute("PollMesageForm") UserContactModel usercModel,
                                     BindingResult result, SessionStatus status, HttpSession session) throws Exception {
        UserContactModel usercSessionModel = (UserContactModel) session.getAttribute("PollMesageForm");
        Logger.debug("In delete  session:::" + usercSessionModel);
        Logger.debug("File::" + usercSessionModel.getUploadedFile());
        Logger.debug("UploadedType" + usercSessionModel.getUploadedType());
        Logger.debug("filename::" + usercSessionModel.getUploadedFileName());
        try {
            if (usercSessionModel != null && StringUtils.isBlank(usercSessionModel.getSearchedFileURL())) {
                deleteUploadFile(usercSessionModel);
                usercSessionModel.setUploadedFile(null);
                usercSessionModel.setUploadedDocFile(null);
                usercSessionModel.setUploadedVideoFile(null);
                usercSessionModel.setUploadedPhotoFile(null);
                usercSessionModel.setUploadedType(null);
                usercSessionModel.setUploadedFileLocation(null);
                usercSessionModel.setFileName(null);
                usercSessionModel.setUploadedFileName(null);
                usercSessionModel.setContentType(null);
                session.setAttribute("PollMesageForm", usercSessionModel);
            } else if ((usercSessionModel.getUploadedFile() == null) && StringUtils.isNotBlank(usercSessionModel.getSearchedFileName()) && StringUtils.isNotBlank(usercSessionModel.getSearchedFileURL())) {
                Logger.debug("To deleteGoogle search Images  usercSessionModel::" + usercSessionModel);
                deleteUploadFile(usercSessionModel);
                usercSessionModel.setSearchedFileName(null);
                usercSessionModel.setSearchedFileURL(null);
                usercSessionModel.setUploadedFileName(null);
                usercModel.setUploadedType(null);
                usercSessionModel.setUploadedFileLocation(null);
                session.setAttribute("PollMesageForm", usercSessionModel);
            } else if (usercSessionModel != null && StringUtils.isNotBlank(usercSessionModel.getYouTubeVideoIdPromotion()) && StringUtils.isNotBlank(usercSessionModel.getUploadedFileLocation())) {
                usercSessionModel.setYouTubeVideoIdPromotion(null);
                usercSessionModel.setUploadedFileLocation(null);
                usercModel.setUploadedType(null);
                usercSessionModel.setUploadedType(null);
                session.setAttribute("PollMesageForm", usercSessionModel);
            }
            return "success";
        } catch (Exception e) {
            Logger.error("uploadMultipartFile - upload is empty", e);
            throw e;
        }
    }

    public void deleteUploadFile(UserContactModel usercModel) {
        if (StringUtils.isNotEmpty(usercModel.getUploadedFileName()) && StringUtils.isNotEmpty(usercModel.getUploadedFileType())) {
            try {
                FileManagementUtil.deleteRemoteFile(usercModel.getUploadedFileType(), usercModel.getUploadedFileName());
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
        } else {
            Logger.debug("mulipartfile is null ");
        }
    }
}