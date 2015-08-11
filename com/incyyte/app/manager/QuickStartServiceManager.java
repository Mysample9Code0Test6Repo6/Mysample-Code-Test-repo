/**
 * <p>Title: T</p>
 * <p>Description: </p>
 * This class implements the addressLocatorManager
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 30 Dec 2010
 */
package com.incyyte.app.manager;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.groups.GroupDao;
import com.incyyte.app.dao.login.RegisterDao;
import com.incyyte.app.dao.messages.MessagesDao;
import com.incyyte.app.dao.profile.UserSettingsDao;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.*;
import com.incyyte.app.service.Exceptions.*;
import com.incyyte.app.service.PollEmailCountService;
import com.incyyte.app.service.util.*;
import com.incyyte.app.util.InCyyteUtil;
import com.incyyte.app.web.model.QuestionModel;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserSettingsModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.util.*;

public class QuickStartServiceManager implements QuickStartManager {
    @Autowired
    private AbstractDao abstractDao;
    @Autowired
    private UserSettingsDao userSettingsDao;
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private PollEmailCountService pollEmailCountSrv;
    private UserDao userDaoImpl;
    private GroupDao groupDao;
    private EmailManager emailMgr;

    @Autowired
    private ResponseManager responseManager;

    @Autowired
    private MessagesDao messagesDao;

    public void setEmailMgr(EmailManager emailMgr) {
        this.emailMgr = emailMgr;
    }

    public Long saveUserDetails(User user, long addressId, String cyyteHomeEmail) throws CreateUserException {
        Address address = new Address();
        address.setId(addressId);

        UserAddress userAddr = new UserAddress();
        userAddr.setAddress(address);
        userAddr.setCyyteHomeEmail(cyyteHomeEmail);

        Long userAddressId = userDaoImpl.addUserAddress(userAddr);
        userAddr.setId(userAddressId);

        user.setUserAddress(userAddr);
        return (long) userDaoImpl.addUser(user);
    }

    public Long newUserSignUp(User user) throws CreateUserException {
        return (long) userDaoImpl.addUser(user);
    }

    public Long createUserInCyyte(long userId, InCyyte incyyte) throws CreateInCyyteException {
        // generate viewing code
        String code = getNextInCyyteCode();
        incyyte.setUserId(userId);
        incyyte.setIncyyteCode(code);
        return userDaoImpl.addInCyyte(incyyte);
    }

    public void setUserDaoImpl(UserDao userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public InCyyte initChart(long incyyteId) {
    	boolean getAnswers = true;
        return userDaoImpl.getInCyyte(incyyteId, getAnswers);
    }
    
    public InCyyte initChart(String incyyteCode) {
    	boolean getAnswers = true;
        return userDaoImpl.getInCyyte(incyyteCode, getAnswers);
    }

    /*
      * (non-Javadoc)
      *
      * @see com.incyyte.app.manager.QuickStartManager#updateChart(long, long,
      * long)
      */
    public InCyyteChart updateChart(long incyyteId, long memberId, long selectedAnswerId, boolean shared) throws ResponderException {
        return getInCyyteChartResponse(incyyteId, memberId, selectedAnswerId, shared);
    }

    public InCyyteChart updateRegionalChart(long incyyteId, long userId, long selectedAnswerId) throws ResponderException {
        return getInCyyteChartRegionalResponse(incyyteId, userId, selectedAnswerId);
    }

    @Override
    public InCyyteChart updatePostedChart(long incyyteId, long userId,
                                          long selectedAnswerId, String gender, String ageGroup, Date responseDate) throws ResponderException {
        Logger.debug("quickstart service manager:::::");
        return getInCyyteChartPostedResponse(incyyteId, userId, selectedAnswerId, gender, ageGroup, responseDate);
    }

    public void addXmlToInCyyte(InCyyte incyyte) throws InCyyteExceptions {
        userDaoImpl.addInCyyteXmlString(incyyte);
    }

    public InCyyteChart getInCyyteResponse(String incyyteCode) {
        InCyyte incyyte = userDaoImpl.getInCyyte(incyyteCode);
        List<Response> responses = null;
        if (incyyte.getSendMethod() == null)
            responses = userDaoImpl.getResponses(incyyte.getId());
        else
            responses = userDaoImpl.getRegionalResponses(incyyte.getId());

        Logger.debug("getCyyteResponse :  Question : " + incyyte.getIncyyte());
        Logger.debug("getCyyteResponse :  Response : " + responses.size());
        InCyyteChart chart = new InCyyteChart(incyyte, responses);
        return chart;
    }

    public InCyyteChart getInCyyteRegionalResponse(String incyyteCode) {
        InCyyte incyyte = userDaoImpl.getInCyyte(incyyteCode);
        List<Response> responses = userDaoImpl.getRegionalResponses(incyyte.getId());
        Logger.debug("getCyyteResponse :  Question : " + incyyte.getIncyyte());
        Logger.debug("getCyyteResponse :  Response : " + responses.size());
        InCyyteChart chart = new InCyyteChart(incyyte, responses);
        return chart;
    }

    @Override
    public InCyyteChart getPostedInCyyteResponse(String incyyteCode) {
        InCyyte incyyte = userDaoImpl.getInCyyte(incyyteCode);
        List<Response> responses = userDaoImpl.getPostedResponses(incyyte.getId());
        Logger.debug("getCyyteResponse :  Question : " + incyyte.getIncyyte());
        Logger.debug("getCyyteResponse :  Response : " + responses.size());
        InCyyteChart chart = new InCyyteChart(incyyte, responses);
        return chart;
    }

    public List<CyyteResponse> getInitChartResponse(long incyyteId) {
        InCyyte incyyte = userDaoImpl.getInCyyte(incyyteId);
        List<CyyteResponse> responses = new ArrayList<CyyteResponse>();
        double value = 0.0;

        for (Answer answer : incyyte.getAnswers()) {
            CyyteResponse response = new CyyteResponse(answer.getId(), answer.getAnswerOption(), value);
            responses.add(response);
        }

        Logger.debug("response.size - " + responses.size());
        return responses;
    }

    private String getNextInCyyteCode() {
        String code = Utility.generateInCyyteCode(Constants.DEFAULT_CODE_LENGTH);
        int countChecks = 0;
        // check database if Code exists (if it does, regenerate)
        while (userDaoImpl.checkIfInCyyteExist(code) != null) {
            code = Utility.generateInCyyteCode(code.length());
            countChecks++;
            if (countChecks == 10) {
                code = Utility.generateInCyyteCode(code.length() + 1);
                countChecks = 0;
            }
        }
        return code;
    }

    public void sendInCyyte(User user, boolean anonymous, long incyyteId, String serverURL, ServletContext servletContext) {
        boolean getAnswers = true;
        emailMgr.setUser(user);
        emailMgr.setAnonymous(anonymous);
        emailMgr.setContacts(userDaoImpl.getInCyyteMailList(incyyteId));
        emailMgr.setIncyyte(userDaoImpl.getInCyyte(incyyteId, getAnswers));
        emailMgr.setRunFlag(Constants.SEND_INCYYTE_EMAIL);
        new Thread(emailMgr).start();
    }

    public void sendInCyyte(User user, InCyyte inCyyte, List<Contact> contacts) {
        if (contacts != null && !contacts.isEmpty()) {
            emailMgr.setContacts(contacts);
        } else {
            emailMgr.setContacts(userDaoImpl.getInCyyteMailList(inCyyte.getId()));
        }

        Logger.debug("shared inCyyte: " + inCyyte.isShared());

        emailMgr.setUser(user);
        emailMgr.setAnonymous(inCyyte.isAnonymity());
        emailMgr.setIncyyte(inCyyte);
        emailMgr.setRunFlag(Constants.SEND_INCYYTE_EMAIL);
        new Thread(emailMgr).start();
    }

    public void sendInCyyteToRegion(User user, boolean anonymous, long incyyteId, List<Contact> contacts, ServletContext servletContext, String sendZone) {
        boolean getAnswers = true;
        emailMgr.setUser(user);
        emailMgr.setAnonymous(anonymous);
        emailMgr.setLocality(sendZone);
        emailMgr.setContacts(contacts);
        emailMgr.setIncyyte(userDaoImpl.getInCyyte(incyyteId, getAnswers));
        emailMgr.setRunFlag(Constants.SEND_BY_AREA);
        new Thread(emailMgr).start();
    }

    public void sendContactUsMessage(User user, String messageText) {
        emailMgr.setUser(user);
        emailMgr.setMessageText(messageText);
        emailMgr.setRunFlag(Constants.SEND_CONTACT_US_EMAIL);
        new Thread(emailMgr).start();
    }

    public void sendCommentsNotification(User user, QuestionModel question) {
        emailMgr.setUser(user);
        emailMgr.setQuestionModel(question);
        emailMgr.setRunFlag(Constants.SEND_POLL_COMMENTS_NOTIFICATION);
        new Thread(emailMgr).start();
    }

    public void sendVotesNotification(User user, QuestionModel question) {
        emailMgr.setUser(user);
        emailMgr.setQuestionModel(question);
        emailMgr.setRunFlag(Constants.SEND_POLL_VOTES_NOTIFICATION);
        new Thread(emailMgr).start();
    }

    public boolean isContactResponded(long incyyteId, long memberId) {
        return userDaoImpl.isContactResponded(incyyteId, memberId);
    }

    public boolean isRegionalContactResponded(long incyyteId, long userId) {
        return userDaoImpl.isRegionalContactResponded(incyyteId, userId);
    }

    public boolean isSharedContactResponded(long incyyteId, long sharedId) {
        return userDaoImpl.isSharedContactResponded(incyyteId, sharedId);
    }

    public boolean isPostResponded(long incyyteID, long memberId) {
        return userDaoImpl.isPostResponded(incyyteID, memberId);
    }

    public boolean isRecipientResponded(long incyyteId, long userId) {
        return userDaoImpl.isRecipientResponded(incyyteId, userId);
    }

    @Override
    public InCyyteChart updatePostInCyyte(long incyyteId, String memberId, String selectedAnswerId, String gender, String ageGroup, Date responseDate) throws ResponderException {
        return getInCyyteChartPostedResponse(incyyteId, Long.valueOf(memberId), Long.valueOf(selectedAnswerId), gender, ageGroup, responseDate);
    }

    private InCyyteChart getInCyyteChartResponse(long incyyteId, long memberId, long selectedAnswerId, boolean shared) throws ResponderException {
    	if (isContactResponded(incyyteId, memberId)) {
            throw new ResponderException(ExceptionMessages.RSP_CONTACT_RESPONDED_MSG);
        }
        Logger.debug("iscontactresponded::incyyteId::" + incyyteId + "memberId::" + memberId);
        //Insert Response
        responseManager.addResponse(incyyteId, memberId, selectedAnswerId, shared);
        List<Response> responses = userDaoImpl.getResponses(incyyteId);
        InCyyte incyyte = userDaoImpl.getInCyyte(incyyteId);
        return new InCyyteChart(incyyte, responses);
    }

    private InCyyteChart getInCyyteChartRegionalResponse(long incyyteId, long userId, long selectedAnswerId) throws ResponderException {
        if (isRegionalContactResponded(incyyteId, userId)) {
            throw new ResponderException(ExceptionMessages.RSP_CONTACT_RESPONDED_MSG);
        }
        Logger.debug("iscontactresponded::incyyteId::" + incyyteId + "memberId::" + userId);
        //Insert Response
        responseManager.addRegionalResponse(incyyteId, userId, selectedAnswerId);
        List<Response> responses = userDaoImpl.getRegionalResponses(incyyteId);
        InCyyte incyyte = userDaoImpl.getInCyyte(incyyteId);
        return new InCyyteChart(incyyte, responses);
    }

    private InCyyteChart getInCyyteChartPostedResponse(long incyyteId, long userId, long selectedAnswerId, String gender, String ageGroup, Date responseDate) throws ResponderException {
        Logger.debug("quickstart service manager getposted responses:::::" + responseDate);
        if (isPostResponded(incyyteId, userId))
            throw new ResponderException(ExceptionMessages.RSP_CONTACT_RESPONDED_MSG);
        //Insert Response
        responseManager.addPostedResponse(incyyteId, userId, selectedAnswerId, gender, ageGroup, responseDate);
        Logger.debug("quickstart service manager after add posted responses:::::" + responseDate);
        List<Response> responses = userDaoImpl.getPostedResponses(incyyteId);
        InCyyte incyyte = userDaoImpl.getInCyyte(incyyteId);
        return new InCyyteChart(incyyte, responses);
    }

    @Override
    public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType, int offset, int recordsPerPage, String param, String criteria) throws InCyyteExceptions {
        return userDaoImpl.getUserInCyytesByUserId(userID, sendType, offset, recordsPerPage, param, criteria);
    }

    @Override
    public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType) throws InCyyteExceptions {
        return userDaoImpl.getUserInCyytesByUserId(userID, sendType);
    }

    public void updateUsersName(String email, String firstname, String lastname) {
        userDaoImpl.updateUsersName(email, firstname, lastname);
    }

    @Override
    public void updateUsersMandatoryInfo(User user) throws CreateUserException {
        userDaoImpl.updateUsersMandatoryInfo(user);
    }

    @Override
    public Long addInCyyteByEmails(InCyyte incyyte) throws CreateInCyyteException {
        // generate viewing code
        String code = getNextInCyyteCode();
        incyyte.setIncyyteCode(code);
        return userDaoImpl.addInCyyteByEmails(incyyte);
    }

    @Override
    public Long addInCyyteByGroup(InCyyte incyyte, long grpId) throws CreateInCyyteException {
        // generate viewing code
        String code = getNextInCyyteCode();
        incyyte.setIncyyteCode(code);

        return userDaoImpl.addInCyyteByGroup(incyyte, grpId);
    }

    @Override
    public Long addInCyyteByPosting(InCyyte incyyte)
            throws CreateInCyyteException {
        // generate viewing code
        String code = getNextInCyyteCode();
        incyyte.setIncyyteCode(code);

        return userDaoImpl.addInCyyteByPosting(incyyte);
    }

    @Override
    public Long addInCyyteByPostRegion(InCyyte incyyte)
            throws CreateInCyyteException {
        // generate viewing code
        String code = getNextInCyyteCode();
        incyyte.setIncyyteCode(code);

        return userDaoImpl.addInCyyteByPostRegion(incyyte);
    }

    @Override
    public Long getPollMemberId(String UserEmail, long questionID) {
        return userDaoImpl.getPollMemberId(UserEmail, questionID);
    }

    @Override
    public List<Long> getAlreadyVotedPolls(String email) {
        return userDaoImpl.getAlreadyVotedPolls(email);
    }
    @Override
    public Long checkPollShared(String UserEmail, long questionID) {
        return userDaoImpl.checkPollShared(UserEmail, questionID);
    }

    @Override
    public void insertDeletedIncyyte(Integer questionId, Integer userId) {
        userDaoImpl.insertDeletedIncyyte(questionId.longValue(), userId.longValue());
    }

    @Override
    public boolean isIncyyteDeleted(Integer questionId, Integer userId) {
        return userDaoImpl.isIncyyteDeleted(questionId, userId);
    }

    @Override
    public boolean shareInCyyte(User user, InCyyte inCyyte, Hashtable shareContacts, boolean incyyteClosed) throws Exception {
        /*
           *AMEND RETREIVE INCYYTE INCLUDING THE NEW TABLE (SHARED_INCYTE)
           */
        Long groupId = inCyyte.getGrpId();
        Long sharerUserId = user.getId();
        Long ownerUserId = inCyyte.getUserId();
        Long questionId = inCyyte.getId();

        //1. Add new contacts to Users Contact List
        //Function filters contacts if already exists
        List<Contact> contactList = (List<Contact>) shareContacts.get("LIST");
        Long[] ids = userDaoImpl.insertNewContacts(sharerUserId, contactList);
        List<Long> contactIds = Arrays.asList(ids);

        Logger.debug("inCyyte creator UserID: " + ownerUserId + " Sharer UserID: " + sharerUserId);
        //2. share inCyyte
        //(if User is creator - Add contacts to GRP_CNT)
        //(if User is NOT creator - Add contacts to SHARED_INCYTE)
        if (ownerUserId.equals(sharerUserId)) {//CREATOR
            //Add new members to existing group
            Group grp = new Group();
            grp.setGroupId(groupId);
            grp.setUserId(sharerUserId);
            grp.setSelectedGroupContactsList(contactIds);
            Logger.debug("grpgrp " + grp);
            contactList = groupDao.addShareGroupContacts(grp, shareContacts, questionId);
            Logger.debug("contactListcontactList " + contactList);
            List<GroupContact> groupContacts = groupDao.getGroupContactMembers(groupId);
            Logger.debug("groupContacts " + groupContacts);
            List<GroupSharedInCyyte> groupSharedInCyytes = InCyyteUtil.buildSharedInCyyte(String.valueOf(sharerUserId), questionId, groupContacts);
            Logger.debug("groupSharedInCyytes " + groupSharedInCyytes);
            groupDao.insertGrpSharedInCyyte(groupSharedInCyytes);
            Logger.debug("valid Grp ContactList size: " + contactList.size());
        } else {//NOT CREATOR
            //Insert Shared inCyyte
            Logger.debug("share");
            Share share = new Share();
            Logger.debug("shareshare " + share);
            share.setQuestionId(questionId);
            share.setGroupId(groupId);
            share.setOwnerUserId(ownerUserId);
            share.setSharerUserId(sharerUserId);
            share.setCreatedDate(new Date());
            share.setCreatedBy(user.getUsername());
            share.setSelectedShareContactsList(contactIds);
            share.setShareContacts(shareContacts);
            Logger.debug("shareshare " + share);
            inCyyte.setShared(true);
            contactList = groupDao.shareInCyyte(share);
            Logger.debug("valid ContactList shared size: " + contactList.size());
        }

        //3. increment total incyyted
        groupDao.incrementTotalIncyyted(groupId, questionId, contactList.size());

        //4. send inCyyte to shared ContactList By Email


        //This is used to calculate the poll email count and then sending email
        ConfigManager icfg = ConfigManager.getInstance();
        int oldCountOfPollemails = pollEmailCountSrv.getPollEmailCountInfo(user);
        Logger.debug("oldCountOfPollemails:::" + oldCountOfPollemails);
        inCyyte.setPollEmailCount(oldCountOfPollemails);
        List<String> validEmails = pollEmailCountSrv.validatePollEmails(contactList, inCyyte.isAnonymity(), user);
        Logger.debug("validEmails:::" + validEmails);
        int count = validEmails.size();
        int cntInclPresentEmails = oldCountOfPollemails + count;
        Logger.debug("cntInclPresentEmails:: " + cntInclPresentEmails);
        if (oldCountOfPollemails == 0 || StringUtils.isBlank(String.valueOf(oldCountOfPollemails))) {
            Logger.debug("user tried first time sending emails");
            pollEmailCountSrv.storePollEmailCount(user, inCyyte, count);
            int newCount = pollEmailCountSrv.getPollEmailCountInfo(user);
            Logger.debug("first count included:::" + newCount);
            if (!incyyteClosed && !contactList.isEmpty())
                sendInCyyte(user, inCyyte, contactList);
            return true;
        } else if (oldCountOfPollemails != 0 && (cntInclPresentEmails > Integer.valueOf(icfg.getProperty(ConfigProperties.POLL_EMAIL_COUNT_LIMIT)))) {
            Logger.debug("Count already reached the limit::");
            return false;
        } else if (cntInclPresentEmails <= Integer.valueOf(icfg.getProperty(ConfigProperties.POLL_EMAIL_COUNT_LIMIT))) {
            Logger.debug("sending mails second or other times :::");
            pollEmailCountSrv.storePollEmailCount(user, inCyyte, count);
            int newCount = pollEmailCountSrv.getPollEmailCountInfo(user);
            Logger.debug("count for after sending emails::" + newCount);
            if (!incyyteClosed && !contactList.isEmpty())
                sendInCyyte(user, inCyyte, contactList);
            return true;
        }
        return false;

    }

    @Override
    public boolean verifyContactalreadyMember(String cemail) {
        return userDaoImpl.verifyContactalreadyMember(cemail);
    }

    @Override
    public void insertUserDomain(long userId, String postcode, String countryCode) {
        userDaoImpl.insertUserDomain(userId, postcode, countryCode);
    }

    @Override
    public UserDomain getUserDomain(long userId) {
        return userDaoImpl.getUserDomain(userId);
    }

    @Override
    public void shareUserPollPageQues(User user, InCyyte inCyyte, List<Contact> contacts) {
        Logger.debug("shared inCyyte: " + inCyyte);
        Hashtable contactList = new Hashtable();
        Long[] ids = userDaoImpl.insertNewContacts(user.getId(), contacts);
        List<Long> contactIds = Arrays.asList(ids);
        Logger.debug("contactIds: " + contactIds);
        Long sharerUserId = user.getId();
        Long ownerUserId = inCyyte.getUserId();
        Long questionId = inCyyte.getId();
        for (Contact contact1 : contacts) {
            contactList.put(String.valueOf(contact1.getContactId()), contact1);
        }
        Logger.debug("inCyyte creator UserID: " + ownerUserId + " Sharer UserID: " + sharerUserId);
        Share share = new Share();
        share.setQuestionId(questionId);
        share.setGroupId(null);
        share.setOwnerUserId(ownerUserId);
        share.setSharerUserId(sharerUserId);
        share.setCreatedDate(new Date());
        share.setCreatedBy(user.getUsername());
        share.setSelectedShareContactsList(contactIds);
        share.setShareContacts(contactList);
        Logger.debug("shareshare " + share);
        inCyyte.setShared(true);
        List contactsFromDao = groupDao.shareInCyyte(share);
        Logger.debug("valid ContactList contacts:: " + contactsFromDao);
        Logger.debug("contacts::::::::::" + contactList);
        for (Contact contact : contacts) {
            Logger.debug("contact: " + contact.getEmail());
            User user1 = new User();
            if (registerDao.emailExists(contact.getEmail())) {
                user1 = registerDao.getUserDetails(contact.getEmail(), Constants.GET_BY_MAIL);
                Logger.debug("user1: " + user1);
                UserSettingsModel model = userSettingsDao.getUserSettings(user1.getId());
                Logger.debug("model: " + model);
                if (StringUtils.equals(model.getNotifypolls(), "Y")) {
                    emailMgr.setContact(contact);
                    emailMgr.setUser(user);
                    emailMgr.setAnonymous(inCyyte.isAnonymity());
                    emailMgr.setIncyyte(inCyyte);
                    emailMgr.setRunFlag(Constants.SEND_SHARE_POLL_PAGE_EMAIL);
                    new Thread(emailMgr).start();
                }
            } else {
                emailMgr.setContact(contact);
                emailMgr.setUser(user);
                emailMgr.setAnonymous(inCyyte.isAnonymity());
                emailMgr.setIncyyte(inCyyte);
                emailMgr.setRunFlag(Constants.SEND_SHARE_POLL_PAGE_EMAIL);
                new Thread(emailMgr).start();
            }
        }

    }

    @Override
    public void shareNewsLetter(User user, InCyyte inCyyte, List<Contact> contacts) {
        Logger.debug("shared inCyyte: " + inCyyte);
        for (Contact contact : contacts) {
            Logger.debug("contact: " + contact.getEmail());
            User user1 = new User();
            if (registerDao.emailExists(contact.getEmail())) {
                user1 = registerDao.getUserDetails(contact.getEmail(), Constants.GET_BY_MAIL);
                Logger.debug("user1: " + user1);
                UserSettingsModel model = userSettingsDao.getUserSettings(user1.getId());
                Logger.debug("model: " + model);
                if (StringUtils.equals(model.getNotifypolls(), "Y")) {
                    emailMgr.setContact(contact);
                    emailMgr.setUser(user);
                    emailMgr.setAnonymous(inCyyte.isAnonymity());
                    emailMgr.setIncyyte(inCyyte);
                    emailMgr.setRunFlag(Constants.SEND_SHARE_NEWS_LETTER_EMAIL);
                    new Thread(emailMgr).start();
                }
            } else {
                emailMgr.setContact(contact);
                emailMgr.setUser(user);
                emailMgr.setAnonymous(inCyyte.isAnonymity());
                emailMgr.setIncyyte(inCyyte);
                emailMgr.setRunFlag(Constants.SEND_SHARE_NEWS_LETTER_EMAIL);
                new Thread(emailMgr).start();
            }
        }

    }

    @Override
    public void sharePollToGroup(InCyyte inCyyte, List<GroupContact> contacts) {
        for (GroupContact contact : contacts) {
            try {
                User user = registerDao.getUserDetails(String.valueOf(contact.getContactId()), Constants.GET_BY_CONTACT_ID);
                Logger.debug("user: " + user);
                if (user != null) {
                    UserSettingsModel model = userSettingsDao.getUserSettings(user.getId());
                    Contact userContact = messagesDao.getContact(contact.getContactId());
                    Logger.debug("model: " + model);
                    if (StringUtils.equals(model.getNotifypolls(), "Y")) {
                        emailMgr.setContact(userContact);
                        emailMgr.setUser(user);
                        emailMgr.setAnonymous(inCyyte.isAnonymity());
                        emailMgr.setIncyyte(inCyyte);
                        emailMgr.setRunFlag(Constants.SEND_SHARE_POLL_PAGE_EMAIL);
                        new Thread(emailMgr).start();
                    }
                }
            } catch (Exception e) {
                Logger.error("Exception:", e);
            }
        }
    }

    @Override
    public void sendEmailForReportThisPoll(ReportPoll reportPoll) {
        Logger.debug("ReportThisPoll:reportPoll:" + reportPoll);
        emailMgr.setReportPoll(reportPoll);
        emailMgr.setRunFlag(Constants.SEND_REPORT_THIS_POLL_EMAIL);
        new Thread(emailMgr).start();
    }

    @Override
    public void sendMessageToPollRecipients(User user, UserContactModel usercModel, InCyyteChart chart) {
        Logger.debug("IncyyteChart::::::" + chart.getIncyyte());
        StringBuilder ids = new StringBuilder();
        StringTokenizer st = new StringTokenizer(usercModel.getChecked(), ",");
        while (st.hasMoreTokens()) {
            if (ids.length() == 0)
                ids = ids.append(st.nextToken().replace("'", ""));
            else
                ids = ids.append(",").append(st.nextToken().replace("'", ""));
        }
        String multipleRecipients = null;
        if (StringUtils.equals(chart.getIncyyte().getSendType(), "post")) {
            multipleRecipients = "select u.email " +
                    "  from posted_responses resp, users u " +
                    " where resp.fk_userid = u.userid " +
                    "   and resp.fk_answerid in (" + ids + ") ";
        } else if (StringUtils.equals(chart.getIncyyte().getSendType(), "area")) {
            multipleRecipients = "select u.email " +
                    "  from regional_responses resp, users u " +
                    " where resp.fk_userid = u.userid " +
                    "   and resp.fk_answerid in (" + ids + ") ";
        } else if (StringUtils.equals(chart.getIncyyte().getSendType(), "mail")) {
            multipleRecipients = "select cnt.email " +
                    " from responses resp, grp_contacts grp, contacts cnt " +
                    " where resp.fk_memberid = grp.memberid " +
                    "   and grp.fk_contactid = cnt.contactid " +
                    "   and resp.fk_answerid in (" + ids + ") ";
        }
        Logger.debug("multipleRecipients--Query -- : " + multipleRecipients);
        List<String> recipientEmails = QueryHelper.doQuery(abstractDao, "sendMessageToPollRecipients", multipleRecipients, null, String.class);
        Logger.debug("recipientEmail--mails -- : " + recipientEmails);
        emailMgr.setRunFlag(Constants.SEND_POLL_MESSAGE);
        emailMgr.setUser(user);
        emailMgr.setMailRecipients(recipientEmails);
        emailMgr.setMessageText(usercModel.getPollMessageContent());
        emailMgr.setCuser(usercModel);
        new Thread(emailMgr).start();
    }
}