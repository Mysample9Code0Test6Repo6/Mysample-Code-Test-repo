package com.incyyte.app.web.controller;

import com.incyyte.app.domain.Message;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.MessagesService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.web.SessionKeys;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class MessagesController {
    @Autowired
    private MessagesService messagesService;

    @Autowired
    private RegistrationService registrationSrv;

    @Autowired
    private RefData referenceData;

    @RequestMapping(value = "/messages")
    public String getMessages(HttpServletRequest req, HttpSession session, Model model) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        int recordsPerPage = referenceData.getPageLimit();
        int noOfRecords = 0;
        int page = 1;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        Logger.debug("page::" + page);
        Logger.debug("noOfRecords::" + noOfRecords);
        if (user != null) {
            noOfRecords = messagesService.getMessagesCount(user.getId());
            List<Message> messages = messagesService.getMessages(user.getId(), (page - 1) * recordsPerPage, recordsPerPage);
            Logger.debug("noOfRecords22::" + noOfRecords);
            Logger.debug("recordsPerPage" + recordsPerPage);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            Logger.debug("noOfPages::" + noOfPages);
            if (messages != null) {
                Logger.debug("inside the controller" + messages.toString());
                WebUtils.setSessionAttribute(req, "messages", messages);
            }
            // command object
            model.addAttribute("noOfPages", noOfPages);
            model.addAttribute("currentPage", page);

            return "messages/messages_view_all";
        } else {
            return "redirect:welcome.cyt";
        }
    }

    @RequestMapping(value = "/composeNew")
    public String getCompose(ModelMap model, HttpSession session) {
        Logger.debug("contactName From Session::::::::" + (String) session.getAttribute("contactName"));
        session.setAttribute("contactName", null);
        model.put("messageModel", new Message());
        return "/messages/messages_compose_new";
    }

    @RequestMapping(value = "/composeNewMessage", method = {RequestMethod.GET, RequestMethod.POST}, headers = "Accept=*/*")
    public String getComposeNewMessage(ModelMap model, HttpServletRequest req) {
        Logger.debug("inside controller contactName - " + req.getParameter("contactName"));
        WebUtils.setSessionAttribute(req, "contactName", req.getParameter("contactName"));
        Message msg = new Message();
        msg.setContactName(req.getParameter("contactName"));
        model.put("messageModel", msg);
        return "/messages/messages_compose_new";
    }

    @RequestMapping(value = "/success_message")
    public String successMessage() {
        return "/messages/success_message";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(@ModelAttribute("messageModel") Message message, HttpSession session) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        String contactName = message.getContactName();
        String messageText = message.getMessageText();

        if (messageText != null)
            messageText = messageText.replaceAll("\n", "\r\n");

        Logger.debug("inside controller contactName" + contactName + "messagetext" + messageText);
        if (user != null) {
            if (contactName.equals(user.getUsername())) {
                return contactName + " Contact Email as same as yours.";
            }

            String contactNameFromSession = (String) session.getAttribute("contactName");
            String messageTextFromSession = (String) session.getAttribute("messageText");
            String sender = (String) session.getAttribute("sender");
            Logger.debug("messageTextFromSession" + session.getLastAccessedTime());
            Logger.debug("current time in seconds" + new Date().getTime());
            long time = new Date().getTime() - session.getLastAccessedTime();
            Logger.debug("current time in seconds" + time);

            if (StringUtils.equals(messageText, messageTextFromSession)
                    && StringUtils.equals(contactName, contactNameFromSession)
                    && StringUtils.equals(user.getUsername(), sender)
                    && time <= 10000) {
                return "You Can't Send Same Message Multiple Times";
            } else {
                session.setAttribute("messageText", messageText);
                session.setAttribute("contactName", contactName);
                session.setAttribute("sender", user.getUsername());
            }
            try {
                Logger.debug("inside controller before send message");
                messagesService.sendMessage(user, contactName, messageText);
                Logger.debug("inside controller after send message ");
                return "success:";
            } catch (Exception e) {
                Logger.error("sendMessage:Exception", e);
                return e.getMessage();
            }
        } else {
            return "redirect";
        }
    }

    @RequestMapping(value = "/deleteConversation")
    public String deleteConversation(HttpServletRequest request, HttpSession session) {
        long messageId = Long.valueOf(request.getParameter("messageId"));
        Logger.debug("inside the controller update" + messageId);
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        messagesService.deleteConversation(messageId, user.getEmail());
        return "/messages/messages_view_all";
    }

    @RequestMapping(value = "/searchMyContacts", method = RequestMethod.GET, headers = "Accept=*/*")
    public
    @ResponseBody
    List<String> searchContacts(@RequestParam("contactId") String contactName, HttpSession session) {
        Logger.debug("=======================================" + contactName);
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        Logger.debug("=======================================" + user.getId());
        List<String> username = null;
        if (user != null) {
            username = messagesService.searchContacts(user.getId(), contactName);
            Logger.debug("username:" + username);
            Logger.debug("inside controller - " + username);
        }
        return username;
    }

    @RequestMapping(value = "/deleteMessage")
    public String deleteMessage(ModelMap model, HttpServletRequest req, HttpSession session) {
        long msgId = Integer.parseInt(req.getParameter("messageId"));
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        String sentBy = messagesService.getSentBy(msgId);
        String deletedBy = null;
        if (StringUtils.equals(sentBy, user.getEmail())) {
            deletedBy = "USER";
        } else {
            deletedBy = "CONTACT";
        }
        Logger.debug("sentBy and deletedBy " + deletedBy + "  " + sentBy);
        messagesService.deleteMessage(msgId, deletedBy);
        model.put("messageModel", new Message());
        return "/messages/messages_detail_view";
    }

    @RequestMapping(value = "/messagesDetail")
    public String getMessageDetail(ModelMap model, HttpServletRequest req, HttpSession session) {
        String messageId = req.getParameter("messageId");
        String contactId = req.getParameter("contactId");
        String activationCode = req.getParameter("actcode");
        String email = req.getParameter("email");
        Logger.debug("before read method " + messageId);
        Logger.debug(" after read method:" + messageId);
        Logger.debug("contactId messagesDetail:" + contactId);
        Logger.debug("email messagesDetail:" + email);
        WebUtils.setSessionAttribute(req, "messageId", messageId);
        WebUtils.setSessionAttribute(req, "contactId", contactId);

        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);

        if (user == null && activationCode != null)
            user = registrationSrv.getUserDetailsByEmailAndCode(email, activationCode);

        if (user != null && StringUtils.equals(email, user.getEmail())) {
            Logger.debug("Logged In");
            session.setAttribute(SessionKeys.LOGIN_USER, user);

            messagesService.markAsRead(Long.parseLong(messageId), user.getEmail());
            List<Message> detailMessage = messagesService.getDetailMessage(Long.valueOf(messageId), user.getEmail());
            Logger.debug("controller detailMessage::" + detailMessage);
            WebUtils.setSessionAttribute(req, "detailMessage", detailMessage);

            Message msg = new Message();
            msg.setContactId(Long.valueOf(contactId));
            msg.setId(Long.valueOf(messageId));
            model.put("messageModel", msg);
            return "/messages/messages_detail_view";
        } else {
            Logger.debug("going to Log In");
            return "redirect:login.cyt";
        }

    }


    @RequestMapping(value = "/unreadCount", method = RequestMethod.POST)
    @ResponseBody
    public String getUnreadMessageCount(HttpSession session) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        Logger.debug("user email###########################" + user);
        String unreadCount = String.valueOf(messagesService.getUnreadMessageCount(user.getEmail()));
        Logger.debug("unreadCount::incontroller" + unreadCount);
        return unreadCount;
    }

    @RequestMapping(value = "/sendReply")
    @ResponseBody
    public String sendReply(@ModelAttribute("messageModel") Message message, HttpSession session, HttpServletRequest req) {
        long contactId = message.getContactId(); //Long.valueOf(req.getParameter("contactId"));
        long messageId = message.getId(); //Long.valueOf(req.getParameter("messageId"));
        String messageContent = message.getMessageText(); //req.getParameter("messageContent");

        if (messageContent != null)
            messageContent = messageContent.replaceAll("\n", "\r\n");

        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        Logger.debug("sendReply MessagesController " + messageContent + contactId + messageId);
        if (user != null) {
            try {
                messagesService.sendReply(user, contactId, messageContent, messageId);
                return "success";
            } catch (Exception e) {
                Logger.error("Exception:", e);
                return e.getMessage();

            }
        } else {
            return "redirect:welcome.cyt";
        }
    }
}