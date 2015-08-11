package com.incyyte.app.web.controller;

import com.incyyte.app.domain.Group;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.GroupService;
import com.incyyte.app.service.UserSettingsService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class ContactPageController {

    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RefData referenceData;

    @Autowired
    private UserSettingsService userSettingsService;

    private UserContactlist userContactlst;

    private UserContactlist userContactsearchlst;

    @Autowired
    private ContactService contactsSrv;

    @Autowired
    private GroupService groupSrv;

    @RequestMapping("/contactsHome")
    public String mycontacthomepage(@ModelAttribute("addContactForm") UserContactModel userModel, BindingResult result, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        String selected = request.getParameter("cids");
        Logger.debug("userModel::" + selected);
        int page = 1;
        String param = "";
        String search = "";
        int recordsPerPage = referenceData.getPageLimit();
        int noOfRecords = 0;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        if (request.getParameter("search") != null)
            search = request.getParameter("search");

        //  recordsPerPage=2;
        if (request.getParameter("param") != null)
            param = request.getParameter("param");
        User user = (User) session.getAttribute("user");
        int noOfPages = 0;
        if (user != null) {
            try {
                userContactlst = contactsSrv.getUserContacts(user.getId(), (page - 1) * recordsPerPage, recordsPerPage, param, search);
                Logger.debug("contact ids::" + userContactlst.getUserContactlist());
                noOfRecords = contactsSrv.getnoOfRecords(user.getId());
                noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                if (selected != null) {
                    StringTokenizer st = new StringTokenizer(selected, ",");
                    while (st.hasMoreElements()) {
                        String cid = (st.nextElement().toString().replace("'", ""));
                        Logger.debug("cid " + cid);
                        for (int i = 0; i < userContactlst.getUserContactlist().size(); i++) {
                            if (cid.equalsIgnoreCase(Long.toString(userContactlst.getUserContactlist().get(i).getContactid()))) {
                                Logger.debug("Checked  " + cid);
                                userContactlst.getUserContactlist().get(i).setContactid(Long.parseLong(cid));
                                userContactlst.getUserContactlist().get(i).setChecked("checked");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Logger.error("exception", e);
            }

            String serverURL = Utility.getServerURL(request);
            for (UserContactModel contactModel : userContactlst.getUserContactlist()) {
                if (contactModel.getUserid() != null) {
                    String uniquename = userSettingsService.getUniquePageName(Long.valueOf(contactModel.getUserid()));
                    contactModel.setUniquename(uniquename);

                    StringBuilder urlbuilder = new StringBuilder(serverURL).append("/");
                    if (uniquename != null && !uniquename.isEmpty()) {
                        urlbuilder.append(uniquename).append("/home.cyt");
                    } else {
                        urlbuilder.append(contactModel.getUsername()).append("/home.cyt");
                    }
                    contactModel.setPollHomePage(urlbuilder.toString());

                    Logger.debug("%%%%%%%%%5 postscript url -> " + urlbuilder.toString());
                }
            }
            int userContactCount = contactsSrv.getUserContactCount(user.getId());
            Logger.debug("contact ids" + userContactlst.getUserContactlist());
            // command object
            model.addAttribute("UserContactlist", userContactlst.getUserContactlist());
            model.addAttribute("addContactForm", userModel);
            model.addAttribute("ViewContactForm", userModel);
            model.addAttribute("SearchContactForm", userModel);
            model.addAttribute("noOfPages", noOfPages);
            model.addAttribute("currentPage", page);
            model.addAttribute("text", param);
            model.addAttribute("selcid", selected);
            model.addAttribute("userContactCount", userContactCount);
            model.addAttribute("noOfRecords", noOfRecords);
            // return form view
            return "/contacts/contacts_view_all";
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/getLoadedContacts", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getLoadedContacts(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String pageNumber = request.getParameter("pageNumber");
        Logger.debug("pageNumber::" + pageNumber);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int offset = Integer.parseInt(pageNumber) - 1;
        offset = offset * 10;
        Logger.debug("offset::" + offset);
        int noOfRecords = 10;
        Logger.debug("noOfRecords::" + noOfRecords);
        String param = request.getParameter("param");
        Logger.debug("param:from Jsp: " + param);
        String search = request.getParameter("search");
        Logger.debug("search from Jsp :: " + search);

        if (StringUtils.equals(search, "Enter your search") || StringUtils.equals(search, "null")) {
            search = "";
        }
        if (StringUtils.equals(param, "null")) {
            param = "";
        }
        Logger.debug("search after Filter :: " + search);
        Logger.debug("param:after filter: " + param);
        UserContactlist contacts = contactsSrv.getUserContacts(user.getId(), offset, noOfRecords, param, search);
        Logger.debug("contactsList:: " + contacts.getUserContactlist());
        Logger.debug("contactsList:size: " + contacts.getUserContactlist().size());
        if (contacts != null && (contacts.getUserContactlist() != null) && (contacts.getUserContactlist().size() == 0)) {
            modelMap.put("endOfFile", "TRUE");
        } else {
            modelMap.put("endOfFile", "FALSE");
        }
        List<Map<String, String>> contactList = new ArrayList<Map<String, String>>();
        for (UserContactModel userContact : contacts.getUserContactlist()) {
            Map<String, String> userContactInfo = new HashMap<String, String>();
            if (userContact != null && StringUtils.equals(userContact.getChecked(), "checked")) {
                userContactInfo.put("checked", "checked");
            } else {
                userContactInfo.put("checked", "");
            }
            if (userContact != null) {
                userContactInfo.put("contactId", String.valueOf(userContact.getContactid()));
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getProfile_img())) {
                userContactInfo.put("profileImgUrl", userContact.getProfile_img());
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getUsername())) {
                userContactInfo.put("username", userContact.getUsername());
            } else {
                userContactInfo.put("username", "");
            }

            if (userContact != null && StringUtils.isNotBlank(userContact.getPostalcode())) {
                userContactInfo.put("postcode", userContact.getPostalcode().split(" ").toString());
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getEmail())) {
                userContactInfo.put("contactEmail", userContact.getEmail());
            }
            userContactInfo.put("displayForBlock", "none");
            userContactInfo.put("displayForUnBlock", "none");
            userContactInfo.put("displayForInvite", "none");
            userContactInfo.put("displayForReInvite", "none");
            userContactInfo.put("displayForSendMessage", "none");
            if (userContact != null && StringUtils.equals(userContact.getSent_invite(), "Y") && StringUtils.equals(userContact.getAccept_inv(), "N")) {
                userContactInfo.put("displayForBlock", "none");
                userContactInfo.put("displayForUnBlock", "none");
            }
            if (userContact != null && StringUtils.equals(userContact.getAccept_inv(), "Y") && StringUtils.equals(userContact.getBlocked(), "N")) {
                userContactInfo.put("displayForBlock", "");
                userContactInfo.put("displayForUnBlock", "none");
            }
            if (userContact != null && StringUtils.equals(userContact.getAccept_inv(), "Y") && StringUtils.equals(userContact.getBlocked(), "Y")) {
                userContactInfo.put("displayForUnBlock", "");
                userContactInfo.put("displayForBlock", "none");
            }
            if (userContact != null && StringUtils.equals(userContact.getSent_invite(), "N")) {
                userContactInfo.put("displayForInvite", "");
                userContactInfo.put("displayForReInvite", "none");
                userContactInfo.put("displayForSendMessage", "none");
            }
            if (userContact != null && StringUtils.equals(userContact.getSent_invite(), "Y") && StringUtils.equals(userContact.getAccept_inv(), "N")) {
                userContactInfo.put("displayForInvite", "none");
                userContactInfo.put("displayForReInvite", "");
                userContactInfo.put("displayForSendMessage", "none");
            }
            if (userContact != null && StringUtils.equals(userContact.getSent_invite(), "Y") && StringUtils.equals(userContact.getAccept_inv(), "Y")) {
                userContactInfo.put("displayForSendMessage", "");
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getStatus())) {
                userContactInfo.put("memberStatus", userContact.getStatus());
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getBlocked())) {
                userContactInfo.put("blockedStatus", userContact.getBlocked());
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getSent_invite())) {
                userContactInfo.put("sendInviteStatus", userContact.getSent_invite());
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getAccept_inv())) {
                userContactInfo.put("acceptInviteStatus", userContact.getAccept_inv());
            }
            if (userContact != null && StringUtils.equals(userContact.getAccept_inv(), "Y")) {
                userContactInfo.put("connectedStatus", "");
                userContactInfo.put("notConnectedStatus", "none");
            } else {
                userContactInfo.put("connectedStatus", "none");
                userContactInfo.put("notConnectedStatus", "");
            }
            if (userContact != null && !StringUtils.equals(userContact.getNickname(), null)) {
                userContactInfo.put("nickName", userContact.getNickname());
            } else {
                userContactInfo.put("nickName", "");
            }
            if (userContact != null && !StringUtils.equals(userContact.getFirstname(), null)) {
                userContactInfo.put("firstName", userContact.getFirstname());
            } else {
                userContactInfo.put("firstName", "");
            }
            if (userContact != null && !StringUtils.equals(userContact.getLastname(), null)) {
                userContactInfo.put("lastName", userContact.getLastname());
            } else {
                userContactInfo.put("lastName", "");
            }
            if (userContact != null && !StringUtils.equals(userContact.getMobile(), null)) {
                userContactInfo.put("mobile", userContact.getMobile());
            } else {
                userContactInfo.put("mobile", "");
            }
            if (userContact != null && !StringUtils.equals(userContact.getDefiningQuestion(), null)) {
                userContactInfo.put("definingQuestion", userContact.getDefiningQuestion());
            } else {
                userContactInfo.put("definingQuestion", "");
            }
            if (userContact != null && !StringUtils.equals(userContact.getTmpPostcode(), null)) {
                userContactInfo.put("tmpPostcode", userContact.getTmpPostcode());
            } else {
                userContactInfo.put("tmpPostcode", "");
            }
            if (userContact != null && !StringUtils.equals(userContact.getFormattedMessageDate(), null)) {
                userContactInfo.put("formattedMessageDate", userContact.getFormattedMessageDate());
            } else {
                userContactInfo.put("formattedMessageDate", "");
            }
            if (userContact != null && !StringUtils.equals(userContact.getPollHomePage(), null)) {
                userContactInfo.put("pollHomePage", userContact.getPollHomePage());
            } else {
                userContactInfo.put("pollHomePage", "");
            }
            Logger.debug("userContactInfo :: " + userContactInfo);
            contactList.add(userContactInfo);
        }
        Logger.debug("contactList:: " + contactList);
        modelMap.put("contacts", contactList);
        return modelMap;
    }
    
    
    @RequestMapping(value = "/getScrolledContacts", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getScrolledContacts(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String pageNumber = request.getParameter("pageNumber");
        Logger.debug("pageNumber::" + pageNumber);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int offset = Integer.parseInt(pageNumber) - 1;
        offset = offset * 10;
        Logger.debug("offset::" + offset);
        int noOfRecords = 10;
        Logger.debug("noOfRecords::" + noOfRecords);
        
        String param = StringUtils.defaultString(request.getParameter("param"));
        Logger.debug("param:from Jsp: " + param);
        String search = StringUtils.defaultString(request.getParameter("search"));
        Logger.debug("search from Jsp :: " + search);

        
        String grpId = StringUtils.defaultString(request.getParameter("grpId"));
        if(StringUtils.isNotBlank(grpId) ){
        modelMap.put("grpId", grpId);
        Logger.debug("search from grpId :: " + grpId );
        
        
        boolean isGrpPolled = groupSrv.hasGroupBeenPolled(new Long(grpId));
        modelMap.put("GrpPolled", isGrpPolled);
        }
        
        if (StringUtils.equals(search, "Enter your search") || StringUtils.equals(search, "null")) {
            search = "";
        }
        if (StringUtils.equals(param, "null")) {
            param = "";
        }
        Logger.debug("search after Filter :: " + search);
        Logger.debug("param:after filter: " + param);
        UserContactlist contacts = contactsSrv.getUserAndGrpContacts(user.getId(), Long.valueOf(grpId), offset, noOfRecords, param, search);
        Logger.debug("contactsList:::::::::::::::: " + contacts.getUserContactlist());
        Logger.debug("contactsList:size::::::::::: " + contacts.getUserContactlist().size());
        if (contacts != null && (contacts.getUserContactlist() != null) && (contacts.getUserContactlist().size() == 0)) {
        	modelMap.put("endOfFile", "TRUE");
        } else {
            modelMap.put("endOfFile", "FALSE");
        }
        List<Map<String, String>> contactList = new ArrayList<Map<String, String>>();
        for (UserContactModel userContact : contacts.getUserContactlist()) {

            Map<String, String> userContactInfo = new HashMap<String, String>();
            if (userContact != null) {
                userContactInfo.put("checked", userContact.getChecked());
            } else {
                userContactInfo.put("checked", "");
            }
            if (userContact != null) {
                userContactInfo.put("contactId", String.valueOf(userContact.getContactid()));
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getProfile_img())) {
                userContactInfo.put("profileImgUrl", userContact.getProfile_img());
            }
            if (userContact != null && StringUtils.isNotBlank(userContact.getUsername())) {
                userContactInfo.put("username", userContact.getUsername());
            } else if (userContact != null && StringUtils.isNotBlank(userContact.getEmail())) {
                userContactInfo.put("username","" );
            }

            if (userContact != null && StringUtils.isNotBlank(userContact.getEmail())) {
                userContactInfo.put("contactEmail", userContact.getEmail());
            }
           
            Logger.debug("userContactInfo :: " + userContactInfo);
            contactList.add(userContactInfo);
        }
        Logger.debug("contactList::: " + contactList);
        modelMap.put("contacts", contactList);
        return modelMap;
    }

    @RequestMapping("/importcontacts")
    public String importContacts(@ModelAttribute("addContactForm") UserContactModel userModel, BindingResult result, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            userModel = new UserContactModel();
            // command object
            model.addAttribute("addContactForm", userModel);
            // return form view
            return "/contacts/contacts_import";
        } else {
            return "redirect:welcome.cyt";
        }
    }

    /*
     * Testing cloud Sponge
     */
    @RequestMapping("/testcloudsponge")
    public String importCloudSpong(@ModelAttribute("addContactForm") UserContactModel userModel, BindingResult result, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            userModel = new UserContactModel();
            // command object
            model.addAttribute("addContactForm" , userModel);
            // return form view
            return "/contacts/contacts_import_cs";
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping("/contacts_new")
    public String contacts_new(@ModelAttribute("addContactForm") UserContactModel userModel, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        UserContactModel userModelSession = (UserContactModel) session.getAttribute("addContactForm");
        String importFrom = request.getParameter("importFrom");
        if (user != null) {
            if (userModelSession == null) {
                userModelSession = new UserContactModel();
            } else if (!StringUtils.equals(importFrom, "importEmails")) {
                userModelSession.setEmail(null);
            }
            // command object
            model.addAttribute("addContactForm" , userModelSession);
            return "/contacts/contacts_new";
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/search_home" , method = RequestMethod.GET)
    public String searchhome(HttpServletRequest request,
                             @ModelAttribute("SearchContactForm") UserContactModel usercontModel,
                             BindingResult result, SessionStatus status, HttpSession session, ModelMap model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("addContactForm", usercontModel);
            model.addAttribute("ViewContactForm", usercontModel);
            model.addAttribute("SearchContactForm", usercontModel);
            return "/contacts/contacts_advanced_search";
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchresult(HttpServletRequest request,
                               @ModelAttribute("SearchContactForm") UserContactModel usercontModelAttribute,
                               BindingResult result, SessionStatus status, HttpSession session, ModelMap model) {

        User user = (User) session.getAttribute("user");
        UserContactModel usercontModel = (UserContactModel) session.getAttribute("ViewContactForm");

        if (usercontModelAttribute.getKeywords() == null)
            usercontModelAttribute.setKeywords(usercontModel.getKeywords());
        if (usercontModelAttribute.getFirstname() == null)
            usercontModelAttribute.setFirstname(usercontModel.getFirstname());
        if (usercontModelAttribute.getLastname() == null)
            usercontModelAttribute.setLastname(usercontModel.getLastname());
        if (usercontModelAttribute.getCountry() == null)
            usercontModelAttribute.setCountry(usercontModel.getCountry());
        if (usercontModelAttribute.getMobile() == null)
            usercontModelAttribute.setMobile(usercontModel.getMobile());
        if (usercontModelAttribute.getCity() == null)
            usercontModelAttribute.setCity(usercontModel.getCity());
        if (usercontModelAttribute.getGender() == null)
            usercontModelAttribute.setGender(usercontModel.getGender());
        if (usercontModelAttribute.getPostalcode() == null)
            usercontModelAttribute.setPostalcode(usercontModel.getPostalcode());

        if (user != null) {
            int page = 1;
            String param = "";
            int recordsPerPage = referenceData.getPageLimit();
            int noOfRecords = 0;
            if (request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            //     recordsPerPage=2;
            if (request.getParameter("param") != null)
                param = request.getParameter("param");
            String search = "";
            if (request.getParameter("search") != null)
                search = request.getParameter("search");
            try {
                userContactsearchlst = contactsSrv.SearchContact(usercontModelAttribute, user.getId(), param, (page - 1) * recordsPerPage, recordsPerPage, search);
                noOfRecords = userContactsearchlst.getNoOfRecords();
                Logger.debug("noOfRecord:::" + noOfRecords);
            } catch (Exception e) {
                Logger.error("Exception::", e);
            }

            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            String selected = request.getParameter("cids");
            if (selected != null)
                try {
                    StringTokenizer st = new StringTokenizer(selected, ",");
                    while (st.hasMoreElements()) {
                        String cid = (st.nextElement().toString().replace("'", ""));
                        Logger.debug("cid " + cid);
                        for (int i = 0; i < userContactsearchlst.getUserContactlist().size(); i++) {
                            Logger.debug("========================" + userContactsearchlst.getUserContactlist().get(i).getEmail());
                            if (cid.equalsIgnoreCase(Long.toString(userContactsearchlst.getUserContactlist().get(i).getContactid()))) {
                                Logger.debug("Checked  " + cid);
                                userContactsearchlst.getUserContactlist().get(i).setContactid(Long.parseLong(cid));
                                userContactsearchlst.getUserContactlist().get(i).setChecked("checked");
                            }
                        }
                    }
                } catch (Exception e) {
                    Logger.error("exception", e);
                }

            String serverURL = Utility.getServerURL(request);
            for (UserContactModel contactModel : userContactlst.getUserContactlist()) {
                if (contactModel.getUserid() != null) {
                    String uniquename = userSettingsService.getUniquePageName(Long.valueOf(contactModel.getUserid()));
                    contactModel.setUniquename(uniquename);

                    StringBuilder urlbuilder = new StringBuilder(serverURL).append("/");
                    if (uniquename != null && !uniquename.isEmpty()) {
                        urlbuilder.append(uniquename).append("/home.cyt");
                    } else {
                        urlbuilder.append(contactModel.getUsername()).append("/home.cyt");
                    }
                    contactModel.setPollHomePage(urlbuilder.toString());

                    Logger.debug("%%%%%%%%%5 postscript url -> " + urlbuilder.toString());
                }
            }
            model.addAttribute("addContactForm", usercontModelAttribute);
            model.addAttribute("ViewContactForm", usercontModelAttribute);
            model.addAttribute("SearchContactForm", usercontModelAttribute);
            model.addAttribute("UserContactlist", userContactsearchlst.getUserContactlist());
            model.addAttribute("noOfPages", noOfPages);
            model.addAttribute("currentPage", page);
            model.addAttribute("text", param);
            model.addAttribute("selcid", selected);
            session.setAttribute("ViewContactForm", usercontModelAttribute);
            return "/contacts/contacts_search_result";
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/mycontacts/submit.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processSubmit(HttpServletRequest request,
                                @ModelAttribute("addContactForm") UserContactModel usercModel,
                                BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String importEmails = request.getParameter("importEmails");

        String groupID = request.getParameter("grpID");
        String newGroupName = request.getParameter("newgrpName");

        Logger.debug("importEmails::" + importEmails);
        Logger.info("Selected Group ID" + groupID);
        Logger.info("Entered new Group name" + newGroupName);

        List<Long> contactIds = new ArrayList<Long>();
        Group group = null;
        if (StringUtils.isNotBlank(groupID) || StringUtils.isNotBlank(newGroupName)) {
            Logger.info("Importing to Group Task....");

            group = new Group();

            if (!groupID.equals("")) group.setGroupId(new Long(groupID));
            if (!newGroupName.equals("")) {
                group.setGroupName(newGroupName);
                group.setDescription("Group created while importing contacts.");
            }
        }


        if (user != null) {
            try {
                String emailids = usercModel.getEmail();
                emailids = emailids.replace("\"", "");
                Logger.debug("emails::::::::" + emailids);
                if (emailids.toLowerCase().contains(user.getEmail().toLowerCase())) {
                    if (!StringUtils.equalsIgnoreCase(importEmails, "importing")) {
                        return "error";
                    }
                }
                String[] temp;
                /* delimiter */
                String pattern = "[,;\\s]+";
                Pattern splitter = Pattern.compile(pattern);
                temp = splitter.split(emailids);
                Logger.debug("email ::" + temp);
                boolean statusof = false;
                for (int i = 0; i < temp.length; i++) {
                    try {
                    	String emailAddress = temp[i].toLowerCase();                  	
                        if (emailAddress.contains(user.getEmail().toLowerCase())) {
                            if (StringUtils.equalsIgnoreCase(importEmails, "importing")) {
                                continue;
                            } else {
                                return "error";
                            }
                        }
                        statusof = false;
                        Logger.debug("email ::" + temp[i] + "status" + statusof);
                        if (StringUtils.isNotBlank(temp[i])) {
                            UserContactModel contact;
                            try {
                                contact = contactsSrv.contactOfUser(temp[i], user.getId());
                                contactIds.add(contact.getContactid());
                            } catch (Exception e) {
                                contact = null;
                            }
                            Logger.debug("contactExists::" + contact);
                            if (contact != null && StringUtils.equals(contact.getActive_ind(), "A")) {
                                if (StringUtils.equalsIgnoreCase(importEmails, "importing")) {
                                    continue;
                                } else {
                                    return "isContactError";
                                }
                            }
                            if (contact == null) {
                                contact = new UserContactModel();
                                contact.setEmail(temp[i]);
                                if (StringUtils.equalsIgnoreCase(importEmails, "importing")) {
                                    contact.setSent_invite("N");
                                } else {
                                    contact.setSent_invite("Y");
                                }
                                Long cid = contactsSrv.addContact(contact, user.getId());
                                contactIds.add(cid);
                            } else {
                                contactsSrv.updateContact(temp[i], user.getId());
                                contact.setEmail(temp[i]);
                            }
                            if (!StringUtils.equalsIgnoreCase(importEmails, "importing")) {
                                contactsSrv.SendInvite(contact, user.getId());
                            }
                            statusof = true;
                        }
                    } catch (Exception e) {
                        Logger.error("email is not sent to contact:", e);
                    }
                }
                if (group != null) {
                    Logger.info(" statusof: Contacts Size: " + contactIds.size() + " First Val: " + contactIds.get(0));
                    group.setSelectedGroupContactsList(contactIds);
                	try{
                		addContactsToGroup(user.getId(), group);
                	}catch(Exception e){
                        Logger.warn("Exception:", e);
            		   if (e.getMessage().equals("Group name already exists"))
            			   return "groupNameExists"; 
            		   else
            			   return "error";
                	}
                }

                if (statusof) {
                    usercModel.setUploadedDocFile(null);
                    usercModel.setEmail(null);
                    usercModel.setFileName(null);
                    session.setAttribute("addContactForm", usercModel);
                    return "/contacts/contacts_new";
                } else if (StringUtils.equalsIgnoreCase(importEmails, "importing")) {
                    usercModel.setUploadedDocFile(null);
                    usercModel.setEmail(null);
                    usercModel.setFileName(null);
                    session.setAttribute("addContactForm", usercModel);
                    return "/contacts/contacts_new";
                } else {
                    return "error";
                }
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
            return "error";
        } else {
            return "redirect:welcome.cyt";
        }
    }

    /*
     * PLS NOTE: This method is based on having either GROUPNAME / GROUPID
     * and NOT both
     */
    private void addContactsToGroup(Long userId, Group group) throws Exception {
        Logger.info("addContactsToGroup called...");
        if (group.getGroupId() != null && StringUtils.isNotBlank(group.getGroupId().toString())) {
            Logger.info("EDIT Mode");
            Group originalGrp = groupSrv.getGroup(userId, group.getGroupId());
            originalGrp.setSelectedGroupContactsList(group.getSelectedGroupContactsList());
            groupSrv.editGroup(userId, originalGrp);
        } else {
            Logger.info("NEW Mode");
            groupSrv.addGroup(userId, group);
        }

    }


    @RequestMapping(value = "/mycontacts/edit.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processeditSubmit(HttpServletRequest request,
                                    @ModelAttribute("ViewContactForm") UserContactModel usercModel,
                                    BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                contactsSrv.modifyContact(usercModel, user.getId());
                return "contactsHome";
            } catch (Exception e) {
                Logger.error("processeditSubmit:unable to process ", e);
                return "error";
            }
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/mycontacts/block.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processedBlock(HttpServletRequest request,
                                 @ModelAttribute("ViewContactForm") UserContactModel usercModel,
                                 BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Logger.debug("User:" + user);
        if (user != null) {
            int addResult = 1;
            try {
                addResult = contactsSrv.BlockContact(usercModel, user.getId());
                Logger.debug("addResult is" + addResult);

                if (addResult == 0) {
                    Logger.debug("addResult in if block" + addResult);
                    return "error";
                } else {
                    Logger.debug("addResult in else block" + addResult);
                    return "contact " + usercModel.getEmail() + " Blocked Successfully...";
                }
            } catch (Exception e) {
                Logger.error("Exception:", e);
                return "error";
            }
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/mycontacts/deleteMultiple.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processedDeleteMultiple(HttpServletRequest request,
                                          @ModelAttribute("ViewContactForm") UserContactModel usercModel,
                                          BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Logger.debug("inside delete checked " + usercModel.getChecked());
        if (user != null) {
            try {
                contactsSrv.DeleteMultiple(usercModel, user.getId());
                return "contactsHome";
            } catch (Exception e) {
                logger.error("processedDeleteMultiple:Exception:", e);
                return "error";
            }
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/mycontacts/BlockMultiple.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processedBlockMultiple(HttpServletRequest request,
                                         @ModelAttribute("ViewContactForm") UserContactModel usercModel,
                                         BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                contactsSrv.BlockMultiple(usercModel, user.getId());
                return "contactsHome";
            } catch (Exception e) {
                logger.error("processedBlockMultiple:Exception:", e);
                return "error";
            }

        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/mycontacts/unblock.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processedUnBlock(HttpServletRequest request,
                                   @ModelAttribute("ViewContactForm") UserContactModel usercModel,
                                   BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                int addResult = contactsSrv.UnBlockContact(usercModel, user.getId());
                Logger.debug("addResult is:" + addResult);
                if (addResult > 0)
                    return "error";
                else
                    return "contact " + usercModel.getEmail() + " Un-Blocked Successfully...";
            } catch (Exception e) {
                logger.error("processedUnBlock:Exception:", e);
                return "error";
            }
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/mycontacts/delete.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processDelete(HttpServletRequest request,
                                @ModelAttribute("ViewContactForm") UserContactModel usercModel,
                                BindingResult result, SessionStatus status, HttpSession session) {
        int deleteContact = 1;
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                deleteContact = contactsSrv.DeletContact(usercModel, user.getId());
                if (deleteContact > 0) {
                    return "error";
                } else {
                    return "contact " + usercModel.getEmail() + " Deleted Successfully...";
                }
            } catch (Exception e) {
                logger.error("processDelete:Exception:", e);
                return "error";
            }
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/mycontacts/sendinvites", method = RequestMethod.POST)
    @ResponseBody
    public String processedIncyyteInvite(HttpServletRequest request,
                                         @ModelAttribute("ViewContactForm") UserContactModel usercModel,
                                         BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int addResult = 1;
            try {
                if (null != usercModel.getEmail() && usercModel.getEmail().contains(user.getEmail()))
                    usercModel.setEmail(usercModel.getEmail().replace(user.getEmail(), ""));
                addResult = contactsSrv.SendInvite(usercModel, user.getId());
                Logger.debug("addResult::" + addResult);
                if (addResult == 0) {
                    return "contact " + usercModel.getEmail() + " Invited Successfully...";
                } else {
                    return "error";
                }
            } catch (Exception e) {
                Logger.error("Invitation Failed::" + e);
                return "error";
            }
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/mycontacts/GetUserContactByFname", method = RequestMethod.GET)
    @ResponseBody
    public List<String> GetMemberListByName(@RequestParam("term") String query, HttpSession session, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        List<String> memberList = contactsSrv.getMemberListByName(user.getId());
        memberList = getCntList(query, memberList);
        response.setContentType("application/json");
        return memberList;
    }

    public List<String> getCntList(String query, List<String> memberList) {
        String cnt = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for (int i = 0; i < memberList.size(); i++) {
            cnt = memberList.get(i).toLowerCase();
            if (cnt.startsWith(query)) {
                matched.add(memberList.get(i));
            }
        }
        return matched;
    }

    @RequestMapping(value = "/mycontacts/GetInvitedList", method = RequestMethod.GET)
    @ResponseBody
    public List<String> GetInvitedList(HttpServletRequest request,
                                       @ModelAttribute("ViewContactForm") UserContactModel usercModel,
                                       BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<String> memberList = contactsSrv.getInvitedContactsList(user.getId());
        return memberList;
    }

    @RequestMapping(value = "/mycontacts/InviteMultiple", method = RequestMethod.POST)
    @ResponseBody
    public String InviteMultiple(HttpServletRequest request,
                                 @ModelAttribute("ViewContactForm") UserContactModel usercModel,
                                 BindingResult result, SessionStatus status, HttpSession session) {
        Logger.debug("UserContactModel::::::::::" + usercModel);
        User user = (User) session.getAttribute("user");
        Logger.debug("user:::::::" + user);
        if (user != null) {
            try {
                contactsSrv.InviteMultiple(user.getId(), usercModel);
                return "contactsHome";
            } catch (Exception e) {
                Logger.error("InviteMultiple::unable to invite ", e);
                return "error";
            }
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/mycontacts/uploadCSVFile.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String uploadCSVFile(HttpServletRequest request, @ModelAttribute("addContactForm") UserContactModel usercModel, BindingResult result, SessionStatus status, HttpSession session, ModelMap model) {
        Logger.debug("usercModel:::" + usercModel);
        User user = (User) session.getAttribute("user");
        Logger.debug("user:::" + user);
        if (user != null && usercModel == null) {
            usercModel = new UserContactModel();
        } else {
            try {
                Reader reader = new InputStreamReader(usercModel.getUploadedDocFile().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                int temp = -1;
                ArrayList<String> emailPositions = new ArrayList<String>();
                String emailsToImport = "";
                while (bufferedReader.ready()) {
                    String data = bufferedReader.readLine();
                    data = data.toLowerCase();
                    Logger.debug("data::" + data);
                    String[] array = data.split(",");
                    if (temp == -1) {
                        //checking column name in First row.
                        for (int i = 0; i < array.length; i++) {
                            array[i] = array[i].trim();
                            Logger.debug("column name ::" + array[i]);
                            if (array[i].contains("mail")) {
                                temp = i;
                                Logger.debug("column num::" + temp);
                                emailPositions.add(String.valueOf(i));
                            }
                            Logger.debug("emailPositions::" + emailPositions);
                        }
                        //If we are not finding any number for required column name throwing error to Ui
                        if (temp == -1) {
                            return "noColumnError";
                        }
                    } else {
                        try {
                            Logger.debug("emailPositions::" + emailPositions);
                            for (String position : emailPositions) {
                                Logger.debug("position:" + position);
                                if (StringUtils.contains(array[Integer.parseInt(position)], "@") && !StringUtils.contains(emailsToImport, array[Integer.parseInt(position)])) {
                                    emailsToImport = emailsToImport + ";" + array[Integer.parseInt(position)];
                                }
                            }
                            Logger.debug("emailsToImport::" + emailsToImport);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            //Suppress the issue and proceed with next value
                        }
                    }
                }
                Logger.debug("all the emails::" + emailsToImport);
                emailsToImport = StringUtils.stripStart(emailsToImport, ";");
                usercModel.setEmail(emailsToImport);
                session.setAttribute("addContactForm", usercModel);
                model.put("emailsToImport", emailsToImport);
                return "success";
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
        }
        return "failure";
    }

    public void setContactsSrv(ContactService contactsSrv) {
        this.contactsSrv = contactsSrv;
    }

    public void setUserContactsearchlst(UserContactlist userContactsearchlst) {
        this.userContactsearchlst = userContactsearchlst;
    }

    public UserContactlist getUserContactsearchlst() {
        return userContactsearchlst;
    }
}
