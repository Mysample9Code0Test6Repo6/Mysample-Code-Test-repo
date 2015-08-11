package com.incyyte.app.web.controller;

import com.incyyte.app.domain.*;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.GroupService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.*;

/**
 * @author prakash
 */
@Controller
public class GroupController {

    @Autowired
    private GroupValidator groupValidator;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RefData referenceData;

    @Autowired
    private ContactService contactsSrv;

    @RequestMapping(value = "/grouphome.cyt")
    public String grouphomepage(@ModelAttribute("group") Group group, BindingResult result, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
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

        List<Group> userGroups = groupService.getUserGroups(user.getId());
        List<Group> userGroupslst = groupService.getUserGroups(user.getId(), (page - 1) * recordsPerPage, recordsPerPage, search, search, false);

        Forum forum = new Forum();
        noOfRecords = userGroups.size();
        if (search != null && !search.isEmpty()) {
            noOfRecords = userGroupslst.size();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        model.addAttribute("forum", forum);
        model.addAttribute("group", group);
        model.addAttribute("userGroups", userGroupslst);
        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("text", param);
        model.addAttribute("paginate", "false");

        //reset earlier list if any
        List<Long> selectedContacts = new ArrayList<Long>();
        request.getSession().setAttribute("selectedContacts", selectedContacts);

        return "/groups/groups_my";
    }

    @RequestMapping(value = "/getLoadedGroups", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getLoadedGroups(HttpServletRequest request, HttpServletResponse response) {
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
        List<Group> userGroupslst = groupService.getUserGroups(user.getId(), offset, noOfRecords, search, search, false);
        Logger.debug("userGroupslst:: " + userGroupslst);
        Logger.debug("userGroupslst:size: " + userGroupslst.size());
        if (userGroupslst != null && (userGroupslst.size() == 0)) {
            modelMap.put("endOfFile", "TRUE");
        } else {
            modelMap.put("endOfFile", "FALSE");
        }
        List<Map<String, String>> groupList = new ArrayList<Map<String, String>>();
        for (Group group : userGroupslst) {
            Map<String, String> userGroupInfo = new HashMap<String, String>();
            if (group != null && StringUtils.equals(group.getChecked(), "checked")) {
                userGroupInfo.put("checked", "checked");
            } else {
                userGroupInfo.put("checked", "");
            }
            if (group != null) {
                userGroupInfo.put("groupId", String.valueOf(group.getGroupId()));
            } else {
                userGroupInfo.put("groupId", "");
            }
            if (group != null && StringUtils.isNotBlank(group.getGroupName())) {
                userGroupInfo.put("groupName", group.getGroupName());
            } else {
                userGroupInfo.put("groupName", "");
            }

            if (group != null && StringUtils.isNotBlank(String.valueOf(group.getGroupSize()))) {
                userGroupInfo.put("groupSize", String.valueOf(group.getGroupSize()));
            } else {
                userGroupInfo.put("groupSize", "");
            }
            if (group != null && StringUtils.isNotBlank(group.getDescription())) {
                userGroupInfo.put("description", group.getDescription());
            } else {
                userGroupInfo.put("description", "");
            }
            if (group != null && StringUtils.isNotBlank(group.getPostCode())) {
                userGroupInfo.put("postCode", group.getPostCode());
            } else {
                userGroupInfo.put("postCode", "");
            }

            Logger.debug("userGroupInfo :: " + userGroupInfo);
            groupList.add(userGroupInfo);
        }
        Logger.debug("contactList:: " + groupList);
        modelMap.put("groups", groupList);
        return modelMap;
    }

    @RequestMapping(value = "/groupCreate.cyt")
    public String showAddGroupForm(@ModelAttribute("group") Group group, BindingResult result, HttpSession session, Model model, HttpServletRequest request) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }

        String param = StringUtils.defaultString(request.getParameter("param"));
        String search = StringUtils.equalsIgnoreCase(request.getParameter("search"), "Enter your search") ? "" : StringUtils.defaultString(request.getParameter("search"));

        List<Long> selectedContactsList = (List<Long>) request.getSession().getAttribute("selectedContacts");
        group.setSelectedGroupContactsList(selectedContactsList);
        UserContactlist contacts = contactsSrv.getUserContacts(user.getId(), page, 10, param, search);
        model.addAttribute("groupSize",contacts.getUserContactlist().size());
        model.addAttribute("group", group);
        Logger.info("contacts::" + contacts);
        model.addAttribute("UserContactlist", contacts.getUserContactlist());
        model.addAttribute("text", param);
        return "/groups/groups_create";
    }

    @RequestMapping(value = "grouphome/addgroup.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String addGroup(@ModelAttribute("group") Group group, BindingResult result, SessionStatus status, HttpSession session, Model model, HttpServletRequest request) {
        String checked = request.getParameter("checked");
        List<Long> selectedContactsList = (List<Long>) request.getSession().getAttribute("selectedContacts");
        StringTokenizer st2 = new StringTokenizer(checked, ",");
        while (st2.hasMoreElements()) {
            Long contactId = new Long((String) st2.nextElement());
            if (!selectedContactsList.contains(contactId))
                selectedContactsList.add(new Long(contactId));
        }

        groupValidator.validate(group, result);
        Logger.debug("Result:" + result);
        if (result.hasErrors()) {
            //Skip these errors
        }
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        group.setSelectedGroupContactsList(selectedContactsList);
        try {
            groupService.addGroup(user.getId(), group);
        } catch (Exception e) {
            Logger.error("Exception:", e);
            return "Error:";
        }
        model.addAttribute("group", group);
        return "redirect:/groups/groups_my.cyt";
    }

    @RequestMapping(value = "/groupModify.cyt")
    public String showEditGroupFrendForm(@ModelAttribute("group") Group group, BindingResult result, HttpSession session, Model model, HttpServletRequest request) {
    	User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        List<Long> selectedContactsList = new ArrayList<Long>();
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }
        String param = StringUtils.defaultString(request.getParameter("param"));
        String radio = StringUtils.defaultString(request.getParameter("radio"));
        String search = StringUtils.defaultString(request.getParameter("search"));
        int recordsPerPage = referenceData.getSubPageLimit();
        int noOfRecords = 0;

        String grpid = request.getParameter("grpid");
        String grpname = request.getParameter("grpname");
        String grpdesc = request.getParameter("grpdesc");
        String selected = request.getParameter("cids");
        
        int offset = page - 1;
        offset = offset * 10;
        
        //Todo: Recheck whether both are same or not
        try{
        	UserContactlist userContactlst =  contactsSrv.getUserAndGrpContacts(user.getId(), Long.valueOf(grpid), offset, recordsPerPage, param, search);
        	Logger.info("userContactlst::" + userContactlst.getUserContactlist());
        	model.addAttribute("UserContactlist", userContactlst.getUserContactlist());
        	model.addAttribute("allContactsLength",userContactlst.getUserContactlist().size());
        } catch(Exception e){
        	Logger.error("Exception:", e);
        }

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        long l = Long.parseLong(grpid);
        group = groupService.getGroup(user.getId(), l);
        Logger.debug("groupService::" + group);

        //check if Group has been polled
        boolean isGrpPolled = groupService.hasGroupBeenPolled(new Long(grpid));
        group.setPolled(isGrpPolled);
        String selectedContactCheckBoxMode = "false";
        if (group.isPolled()) selectedContactCheckBoxMode = "true";

        if (null != grpname || null != grpdesc) {
            group.setGroupName(grpname);
            group.setDescription(grpdesc);
        }

        UserContactlist selectedUserContactlst = groupService.editContacts(new Long(grpid), offset, recordsPerPage, param, search, true);
        Logger.info("selectContactsList Size=" + selectedContactsList.size());
        List<Long> selectedEditGroupContacts = null;

        //reset all earlier selected contacts if its new landing on add group page
        String paginate = request.getParameter("paginate");
        if ((paginate == null || paginate.isEmpty() || !paginate.equals("true"))) {
            selectedEditGroupContacts = new ArrayList<Long>();
            for (Contact c : group.getSelectedGroupContacts()) {
                selectedEditGroupContacts.add(c.getContactId());
            }
            request.getSession().setAttribute("selectedContacts", selectedEditGroupContacts);
            model.addAttribute("paginate", "true");
        }
        selectedEditGroupContacts = (List<Long>) request.getSession().getAttribute("selectedContacts");

        if (group.getSelectedGroupContactsList() == null) {
            group.setSelectedGroupContactsList(new ArrayList<Long>());
        }
        for (Long id : selectedEditGroupContacts) {
            group.getSelectedGroupContactsList().add(id);
        }
        if (StringUtils.equals(radio, "showAll")) {
            group.setShowAllContacts("Y");
        }
        model.addAttribute("groupContactsLength",selectedUserContactlst.getUserContactlist().size());
        model.addAttribute("group", group);
        model.addAttribute("groupTypes", referenceData.getGroupType());
        model.addAttribute("SelectedUserContactlist", selectedUserContactlst.getUserContactlist());
        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("text", param);
        model.addAttribute("userGroupNames", "");
        model.addAttribute("grpid", grpid);
        model.addAttribute("selcid", selected);
        model.addAttribute("checkStat", selectedContactCheckBoxMode);
        model.addAttribute("GrpPolled", isGrpPolled);

        return "/groups/groups_details";
    }
    
    @RequestMapping(value = "/getScrolledGroupMembers.cyt")
    @ResponseBody
    public Map<String, Object> getScrolledGroupMembers(@ModelAttribute("group") Group group, BindingResult result, HttpSession session, Model model, HttpServletRequest request) {
    	 Logger.info("getScrolledGroupMembers::" );
    	 
    	 
    	 int page;
         try {
             page = Integer.parseInt(request.getParameter("pageNumber"));
         } catch (Exception e) {
             page = 1;
         }
         int recordsPerPage = referenceData.getSubPageLimit();
         String pageNumber = request.getParameter("pageNumber");
         Logger.debug("pageNumber::" + pageNumber);
         Map<String, Object> modelMap = new HashMap<String, Object>();
         String param = StringUtils.defaultString(request.getParameter("param"));
         Logger.debug("param:from Jsp: " + param);
         String search = StringUtils.defaultString(request.getParameter("search"));
         Logger.debug("search from Jsp :: " + search);

         
         String grpId = StringUtils.defaultString(request.getParameter("grpId"));
         if(grpId != null){
         modelMap.put("grpId", grpId);
         Logger.debug("search from grpId :: " + grpId );
         
         
         boolean isGrpPolled = groupService.hasGroupBeenPolled(new Long(grpId));
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
         int offset = page - 1;
         offset = offset * 10;
          UserContactlist contacts = groupService.editContacts(new Long(grpId), offset, recordsPerPage, param, search, true);
         if (contacts != null && (contacts.getUserContactlist() != null) && (contacts.getUserContactlist().size() == 0)) {
         	modelMap.put("endOfFile", "TRUE");
         } else {
             modelMap.put("endOfFile", "FALSE");
         }
         List<Map<String, String>> contactList = new ArrayList<Map<String, String>>();
         for (UserContactModel userContact : contacts.getUserContactlist()) {
             Logger.info("contactsList:: " + userContact);

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
             } else if (userContact != null && StringUtils.isNotBlank(userContact.getEmail())) {
                 userContactInfo.put("username","" );
             }

             if (userContact != null && StringUtils.isNotBlank(userContact.getEmail())) {
                 userContactInfo.put("contactEmail", userContact.getEmail());
             }
            
             Logger.debug("userContactInfo :: " + userContactInfo);
             contactList.add(userContactInfo);
         }
         Logger.debug("contactList:: " + contactList);
         modelMap.put("contacts", contactList);
         return modelMap;
}

    @RequestMapping(value = "/editgroup.cyt", method = RequestMethod.POST)
    public String editGroup(@ModelAttribute("group") Group group, HttpSession session, Model model) {

        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        long groupId = group.getGroupId();
        try {
            List<Long> selectedEditGroupContacts = (List<Long>) session.getAttribute("selectedContacts");
            group.setSelectedGroupContactsList(selectedEditGroupContacts);
            groupService.editGroup(user.getId(), group);
            group = groupService.getGroup(user.getId(), groupId);
        } catch (Exception e) {
            Logger.error("editGroup:Exception:", e);
        }
        List<Group> userGroups = new ArrayList<Group>();//groupService.getUserGroups(user.getId());

        userGroups.add(group);
        model.addAttribute("group", group);
        model.addAttribute("userGroups", userGroups);
        return "userGroups";
    }

    @RequestMapping(value = "/removegroup.cyt")
    public String removeGroup(@ModelAttribute("group") Group group, HttpSession session, Model model) {

        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        Logger.debug("Remove group called >> " + group.getGroupId());
        List<Group> userGroups = null;
        try {
            groupService.removeGroup(user.getId(), group.getGroupId());
            userGroups = groupService.getUserGroups(user.getId());
        } catch (Exception e) {
            Logger.error("removeGroup:Exception:", e);
        }
        model.addAttribute("group", group);
        model.addAttribute("userGroups", userGroups);
        return "userGroups";
    }

    @RequestMapping(value = "/removegroups.cyt")
    public String removeGroups(@ModelAttribute("group") Group group, HttpSession session, Model model) {

        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        Logger.debug("Removing groups " + group.getSelectedGroupIds().size());
        List<Group> userGroups = null;
        try {
            for (Long groupId : group.getSelectedGroupIds()) {
                groupService.removeGroup(user.getId(), groupId);
            }
            userGroups = groupService.getUserGroups(user.getId());
        } catch (Exception e) {
            Logger.error("removeGroups:Exception:", e);
        }
        Forum forum = new Forum();

        model.addAttribute("forum", forum);
        model.addAttribute("group", group);
        model.addAttribute("userGroups", userGroups);
        return "grouphome";
    }

    @RequestMapping("/imagecontent")
    public void streamImageContent(@RequestParam("grpId") Long groupId, HttpSession session, OutputStream outputStream) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        groupService.streamGroupImage(user.getId(), groupId, outputStream);
    }

    @RequestMapping(value = "/usergroups.cyt")
    public String manageGroups(@ModelAttribute("group") Group group, BindingResult result, SessionStatus status, HttpSession session, Model model) {

        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        long groupId = group.getGroupId();
        group = groupService.getGroup(user.getId(), groupId);
        List<Group> userGroups = new ArrayList<Group>();
        userGroups.add(group);
        model.addAttribute("group", group);
        model.addAttribute("userGroups", userGroups);
        return "userGroups";
    }

    @RequestMapping(value = "/searchgroupbyname.cyt")
    public String searchGroupNamePage(@ModelAttribute("searchCriterion") SearchCriterion searchCriterion, Model model) {
        model.addAttribute("searchCriterion", searchCriterion);
        return "searchgroupbyname";
    }

    @RequestMapping(value = "/searchgroupbycode.cyt")
    public String searchPostCodePage(@ModelAttribute("searchCriterion") SearchCriterion searchCriterion, Model model) {
        model.addAttribute("searchCriterion", searchCriterion);
        return "searchgroupbypostcode";
    }

    @RequestMapping(value = "/searchgroupbyname.cyt", method = RequestMethod.POST)
    public String searchGroupByName(@ModelAttribute("searchCriterion") SearchCriterion searchCriterion, Model model, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);

        List<Group> userGroups = groupService.searchByGroupName(user.getId(), searchCriterion.getSearchCriterion());
        Logger.debug("Groups found >> " + userGroups.size());

        model.addAttribute("userGroups", userGroups);
        model.addAttribute("searchCriterion", searchCriterion);

        if (userGroups == null || userGroups.size() == 0 && !searchCriterion.getSearchCriterion().equals("")) {
            request.setAttribute("noGrpMsg", "true");
            return "searchgroupbyname";
        }
        return "searchgroupbyname";
    }

    @RequestMapping(value = "/searchgroupbycode.cyt", method = RequestMethod.POST)
    public String searchGroupByPostCode(@ModelAttribute("searchCriterion") SearchCriterion searchCriterion, Model model, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);

        List<Group> userGroups = groupService.searchByGroupPostcode(user.getId(), searchCriterion.getSearchCriterion());

        Logger.debug("Groups found >> " + userGroups.size());
        model.addAttribute("userGroups", userGroups);
        model.addAttribute("searchCriterion", searchCriterion);

        if (userGroups == null || userGroups.size() == 0 && !searchCriterion.getSearchCriterion().equals("")) {
            request.setAttribute("noGrpMsg", "true");
            return "searchgroupbypostcode";
        }

        return "searchgroupbypostcode";
    }

    @RequestMapping(value = "/grouphome/deleteMultiple.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processedDeleteMultiple(HttpServletRequest request,
                                          @ModelAttribute("group") Group group,
                                          BindingResult result, SessionStatus status, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            groupService.DeleteMultiple(group, user.getId());
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return "grouphome";
    }

    @RequestMapping(value = "/grouphome/GetUserGroupByname", method = RequestMethod.GET)
    @ResponseBody
    public List<String> GetMemberListByName(@RequestParam("term") String query, HttpSession session, HttpServletResponse response) {

        User user = (User) session.getAttribute("user");
        List<String> memberList = groupService.getMemberListByName(user.getId());
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

    @RequestMapping(value = "/submitnewcontact.cyt", method = RequestMethod.GET)
    public void submitNewContact(HttpServletRequest request, HttpServletResponse response) {
        String contactIdStr = request.getParameter("contactId");
        String checked = request.getParameter("checked");

        Logger.debug("Storing new contact id:: " + contactIdStr + ": " + checked);

        Long contactId = 0L;
        if (contactIdStr != null && !contactIdStr.isEmpty()) {
            contactId = Long.parseLong(contactIdStr);
        }

        List<Long> selectedContactsList = (List<Long>) request.getSession().getAttribute("selectedContacts");
        if (checked.equals("true") || checked.equals("checked")) {
            selectedContactsList.add(contactId);
        }

        if (checked.equals("false") || checked.equals("undefined")) {
            selectedContactsList.remove(contactId);
        }
        request.getSession().setAttribute("selectedContacts", selectedContactsList);
        Logger.debug("selectedContactsList:size()" + selectedContactsList.size());
    }

    @RequestMapping(value = "/submiteditcontact.cyt", method = RequestMethod.GET)
    public void submitEditContact(HttpServletRequest request, HttpServletResponse response) {
        String contactIdStr = request.getParameter("contactId");
        String checked = request.getParameter("checked");

        Logger.debug("Storing new contact id:: " + contactIdStr + ": " + checked);

        Long contactId = 0L;
        if (contactIdStr != null && !contactIdStr.isEmpty()) {
            contactId = Long.parseLong(contactIdStr);
        }

        List<Long> selectedContactsList = (List<Long>) request.getSession().getAttribute("selectedContacts");


        if (checked.equals("true") || checked.equals("checked")) {
            selectedContactsList.add(contactId);
        }

        if (checked.equals("false") || checked.equals("undefined")) {
            selectedContactsList.remove(contactId);
        }
        request.getSession().setAttribute("selectedContacts", selectedContactsList);
        Logger.debug("selectedContactsList:size()" + selectedContactsList.size());
    }
    
   	@RequestMapping(value = "/submitmultipleeditcontact.cyt", method = RequestMethod.GET)
       public void submitMultipleEditContact(HttpServletRequest request, @ModelAttribute("group") Group group) {
    	
   	 String groupIds =  StringUtils.defaultString(request.getParameter("groupIds"));
   	 String checked =  StringUtils.defaultString(request.getParameter("checked"));
     if(checked == "None"){
         request.getSession().setAttribute("selectedContacts", "");      
     }else{
      List<Long> selectedContacts = new ArrayList<Long>();
      String [] splitArr=StringUtils.split(groupIds, ",");
      try {
      for(int i=0; i<splitArr.length; i++ ) {
         selectedContacts.add(new Long((String) splitArr[i]));
      }
        request.getSession().setAttribute("selectedContacts",  selectedContacts); 
    }  catch(Exception e){
         Logger.error("Error::"+e);    	   
       }
   	}
  } 

    @RequestMapping(value = "/searchUserGroupList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> searchUserGroupList(HttpServletRequest request, HttpServletResponse response) {
        Logger.debug("inside controller");
        User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Map<String, String>> groupList = new ArrayList<Map<String, String>>();
        try {
            Logger.debug("inside controller USER: " + user.getId());
            List<Group> userGroups = groupService.getUserGroups(user.getId());
            for (Group group : userGroups) {
                Map<String, String> userGroupInfo = new HashMap<String, String>();
                if (group != null && StringUtils.isNotBlank(group.getGroupName())) {
                    userGroupInfo.put("groupName", group.getGroupName());
                    userGroupInfo.put("groupID", group.getGroupId().toString());
                } else {
                    userGroupInfo.put("groupName", "");
                }
                groupList.add(userGroupInfo);
            }
        } catch (Exception e) {
            Logger.error("exception:: ", e);
        }
        modelMap.put("groups", groupList);
        return modelMap;
    }
}