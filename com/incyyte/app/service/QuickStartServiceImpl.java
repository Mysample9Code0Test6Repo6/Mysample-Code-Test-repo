/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class implements the addressLocator services
 * This class will delegate services to the addressLocator manager
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.PeopleFilter;
import com.incyyte.app.domain.Person;
import com.incyyte.app.domain.ReportPoll;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserDomain;
import com.incyyte.app.manager.ContactManager;
import com.incyyte.app.manager.QuickStartManager;
import com.incyyte.app.service.Exceptions.CreateInCyyteException;
import com.incyyte.app.service.Exceptions.CreateUserException;
import com.incyyte.app.service.Exceptions.InCyyteExceptions;
import com.incyyte.app.service.Exceptions.ResponderException;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserContactModel;

public class QuickStartServiceImpl implements QuickStartService {

	@Autowired
	QuickStartManager quickStartMgr;

	@Autowired
	ContactManager contactMgr;

	@Autowired
	private QuestionService questnSrv;
	@Autowired
	private PollEmailCountService pollEmailCountSrv;

	@Autowired
	private UserDao userDaoImpl;

	public void setQuickStartMgr(QuickStartManager quickStartMgr) {
		this.quickStartMgr = quickStartMgr;
	}

	public void setContactMgr(ContactManager contactMgr) {
		this.contactMgr = contactMgr;
	}

	@Override
	public Long createUserInCyyte(long userId, InCyyte incyyte) throws CreateInCyyteException {
		return quickStartMgr.createUserInCyyte(userId, incyyte);
	}

	@Override
	public void sendInCyyte(User user, boolean anonymous, long incyyteId, String serverURL, ServletContext servletContext) {
		quickStartMgr.sendInCyyte(user, anonymous, incyyteId, serverURL, servletContext);
	}

	@Override
	public void addXmlToInCyyte(InCyyte incyyte) throws InCyyteExceptions {
		quickStartMgr.addXmlToInCyyte(incyyte);
	}

	@Override
	public InCyyte initChart(long incyyteId) {
		return quickStartMgr.initChart(incyyteId);
	}

	@Override
	public InCyyte initChart(String incyyteCode) {
		return quickStartMgr.initChart(incyyteCode);
	}
	
	@Override
	public InCyyteChart updateChart(long incyyteId, long memberId,
			long selectedAnswerId, boolean shared) throws ResponderException {
		return quickStartMgr.updateChart(incyyteId, memberId, selectedAnswerId, shared);
	}

	@Override
	public InCyyteChart updatePostedChart(long incyyteId, long userId,
			long selectedAnswerId, String gender, String ageGroup, Date responseDate) throws ResponderException {
		Logger.debug("quickstart service:::::" + responseDate);
		return quickStartMgr.updatePostedChart(incyyteId, userId, selectedAnswerId, gender, ageGroup, responseDate);
	}

	@Override
	public InCyyteChart getInCyyteResponse(String incyyteCode) {
		return quickStartMgr.getInCyyteResponse(incyyteCode);
	}

	@Override
	public InCyyteChart getPostedInCyyteResponse(String incyyteCode) {
		return quickStartMgr.getPostedInCyyteResponse(incyyteCode);
	}

	@Override
	public boolean isContactResponded(long incyyteId, long memberId) {
		return quickStartMgr.isContactResponded(incyyteId, memberId);
	}

	@Override
	public boolean isSharedContactResponded(long incyyteId, long sharedId) {
		return quickStartMgr.isSharedContactResponded(incyyteId, sharedId);
	}

	@Override
	public boolean isPostResponded(long incyyteID, long memberId) {
		return quickStartMgr.isPostResponded(incyyteID, memberId);
	}

	@Override
	public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType, int offset, int recordsPerPage, String param, String criteria) throws InCyyteExceptions {
		return quickStartMgr.getUserInCyytesByUserId(userID, sendType, offset, recordsPerPage, param, criteria);
	}

	@Override
	public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType) throws InCyyteExceptions {
		return quickStartMgr.getUserInCyytesByUserId(userID, sendType);
	}

	@Override
	public void updateUsersName(String email, String firstname, String lastname) {
		quickStartMgr.updateUsersName(email, firstname, lastname);
	}

	@Override
	public void updateUsersMandatoryInfo(User user) throws CreateUserException {
		quickStartMgr.updateUsersMandatoryInfo(user);
	}

	@Override
	public Long addInCyyteByEmails(InCyyte incyyte) throws CreateInCyyteException {
		return quickStartMgr.addInCyyteByEmails(incyyte);
	}

	@Override
	public Long addInCyyteByGroup(InCyyte incyyte, long grpId) throws CreateInCyyteException {
        Logger.debug("addInCyyteByGroup: Start:");
		return quickStartMgr.addInCyyteByGroup(incyyte, grpId);
	}

	@Override
	public Long addInCyyteByPosting(InCyyte incyyte) throws CreateInCyyteException {
		return quickStartMgr.addInCyyteByPosting(incyyte);
	}

	@Override
	public Long addInCyyteByPostRegion(InCyyte incyyte) throws CreateInCyyteException {
		return quickStartMgr.addInCyyteByPostRegion(incyyte);
	}

	@Override
	public Long getPollMemberId(String UserEmail, long questionID) {
		return quickStartMgr.getPollMemberId(UserEmail, questionID);
	}

	@Override
	public List<Long> getAlreadyVotedPolls(String email) {
		return quickStartMgr.getAlreadyVotedPolls(email);
	}

	@Override
	public Long checkPollShared(String UserEmail, long questionID) {
		return quickStartMgr.checkPollShared(UserEmail, questionID);
	}

	@Override
	public void insertDeletedIncyyte(Integer questionId, Integer userId) {
		quickStartMgr.insertDeletedIncyyte(questionId, userId);
	}

	@Override
	public boolean isIncyyteDeleted(Integer questionId, Integer userId) {
		return quickStartMgr.isIncyyteDeleted(questionId, userId);
	}

	@Override
	public void sendInCyyteToRegion(User user, boolean anonymous, long incyyteId, List<Contact> contacts, ServletContext servletContext, String sendZone) {
		quickStartMgr.sendInCyyteToRegion(user, anonymous, incyyteId, contacts, servletContext, sendZone);
	}

	@Override
	public boolean shareInCyyte(User user, InCyyte inCyyte, Hashtable contacts, boolean incyyteClosed) throws Exception {
		return quickStartMgr.shareInCyyte(user, inCyyte, contacts, incyyteClosed);
	}

	@Override
	public boolean verifyContactalreadyMember(String cemail) {
		return quickStartMgr.verifyContactalreadyMember(cemail);
	}

	@Override
	public String getInvitationCode() {
		return contactMgr.getInvitationCode();
	}

	@Override
	public void insertUserDomain(long userId, String postcode, String countryCode) {
		quickStartMgr.insertUserDomain(userId, postcode, countryCode);
	}

	@Override
	public UserDomain getUserDomain(long userId) {
		return quickStartMgr.getUserDomain(userId);
	}

	@Override
	public InCyyteChart updateRegionalChart(long incyyteId, long userId, long selectedAnswerId) throws ResponderException {
		return quickStartMgr.updateRegionalChart(incyyteId, userId, selectedAnswerId);
	}

	@Override
	public InCyyteChart getInCyyteRegionalResponse(String incyyteCode) {
		return quickStartMgr.getInCyyteRegionalResponse(incyyteCode);
	}

	@Override
	public boolean isRegionalContactResponded(long incyyteId, long userId) {
		return quickStartMgr.isRegionalContactResponded(incyyteId, userId);
	}
    
    @Override
    public void shareNewsLetter(User user, InCyyte inCyyte, List<Contact> contacts) {
        quickStartMgr.shareNewsLetter(user, inCyyte, contacts);
    }

	@Override
	public void shareUserPollPageQues(User user, InCyyte inCyyte, List<Contact> contacts) {
		quickStartMgr.shareUserPollPageQues(user, inCyyte, contacts);
	}

	@Override
	public void sendEmailForReportThisPoll(ReportPoll reportPoll) {
		quickStartMgr.sendEmailForReportThisPoll(reportPoll);
	}

	@Override
	public Long processArea(User user, InCyyte incyyte, ServletContext servletContext) throws Exception {
		if (incyyte.isAnonymity())
			incyyte.setCreatedBy(Constants.ANONYMOUS);
		else
			incyyte.setCreatedBy(user.getUsername());

		PeopleFilter filter = new PeopleFilter();
		filter.setAgeGroup(incyyte.getAgeRange() != null && !incyyte.getAgeRange().equals("select") ? incyyte.getAgeRange() : null);
		filter.setGender(incyyte.getGender() != null && !incyyte.getGender().equals("select") ? incyyte.getGender() : null);
		try {
			String ageRange = incyyte.getAgeRange();
			ageRange = ageRange.replace(" ", "");
			Logger.debug("ageRanges:1:" + ageRange.toString());
			String[] ageRanges = ageRange.split("-");
			Logger.debug("ageRanges:2:" + ageRanges.toString());
			filter.setMinAge(Integer.parseInt(ageRanges[0]));
			if (StringUtils.equals(ageRanges[1], "Over")) {
				filter.setMaxAge(null);
			} else {
				filter.setMaxAge(Integer.parseInt(ageRanges[1]));
			}
		} catch (Exception e) {
			Logger.info("Exception Raised while Processing the Range, Hence sending the Poll to all Age Groups (13 - Over)");
			filter.setMaxAge(null);
			filter.setMinAge(13);
		}
		Logger.debug("filter age min ::" + filter.getMinAge() + "max" + filter.getMaxAge());
		filter.setCategory(incyyte.getCategory() != null ? incyyte.getCategory() : null);

		List<Contact> contacts = new ArrayList<Contact>();
		List<Person> personList = new ArrayList<Person>();

		if (incyyte.getLocality().equalsIgnoreCase(Constants.POST_TO_POSTCODE)) {
			incyyte.setSendMethod(Constants.POST_TO_POSTCODE);
			incyyte.setSendZone(incyyte.getPostcode());
			personList = questnSrv.getMembersByPostcode(incyyte.getPostcode(), filter);
		} else if (incyyte.getLocality().equalsIgnoreCase(Constants.POST_TO_REGION)) {
			incyyte.setSendMethod(Constants.POST_TO_REGION);
			incyyte.setSendZone(incyyte.getRegion());
			personList = questnSrv.getMembersByRegion(incyyte.getRegion(), filter);
		} else if (incyyte.getLocality().equalsIgnoreCase(Constants.POST_TO_COUNTY)) {
			incyyte.setSendMethod(Constants.POST_TO_COUNTY);
			incyyte.setSendZone(incyyte.getCounty());
			personList = questnSrv.getMembersByCounty(incyyte.getCounty(), filter);
		}

		if (personList != null && !personList.isEmpty()) {

			for (Person person : personList) {
				Contact contact = new Contact();
				contact.setUserId(person.getId());
				contact.setEmail(person.getEmail());
				contacts.add(contact);
			}
			incyyte.setTotalInCyyted(contacts != null ? contacts.size() : 0);
			Long incyyteId = addInCyyteByPostingToRegion(user, incyyte);

			//TODO:: Track the  poll Emails Here and Insert them.
			ConfigManager icfg = ConfigManager.getInstance();
			int oldCountOfPollemails = pollEmailCountSrv.getPollEmailCountInfo(user);
			Logger.debug("oldCountOfPollemails:::"+oldCountOfPollemails);
			incyyte.setPollEmailCount(oldCountOfPollemails);
			Logger.debug("contacts:::"+contacts);
			Logger.debug("incyyte.isAnonymity():::"+incyyte.isAnonymity());
			List<String>  validEmails = pollEmailCountSrv.validatePollEmails(contacts, incyyte.isAnonymity(), user);
			Logger.debug("validEmails:::"+validEmails);
			int count = validEmails.size();
			int cntInclPresentEmails  = oldCountOfPollemails + count;
			Logger.debug("cntInclPresentEmails:: "+cntInclPresentEmails);
			if(oldCountOfPollemails == 0 || StringUtils.isBlank(String.valueOf(oldCountOfPollemails))){
				Logger.debug("user sending 1st time email to others::");
				pollEmailCountSrv.storePollEmailCount(user, incyyte, count);
				int newCount = pollEmailCountSrv.getPollEmailCountInfo(user);
				Logger.debug("first time count:1::"+newCount);
				if (contacts != null && !contacts.isEmpty())
					sendInCyyteToRegion(user, incyyte.isAnonymity(), incyyteId, contacts, servletContext, incyyte.getSendZone());
				return incyyteId;
			} else if (oldCountOfPollemails != 0 && (cntInclPresentEmails > Integer.valueOf(icfg.getProperty(ConfigProperties.POLL_EMAIL_COUNT_LIMIT))) ){
				Logger.debug("crossed the no of limits.:::");
				return null;	
			}else if(cntInclPresentEmails <= Integer.valueOf(icfg.getProperty(ConfigProperties.POLL_EMAIL_COUNT_LIMIT)) ){
				Logger.debug("second or other times sending emails::");
				pollEmailCountSrv.storePollEmailCount(user, incyyte, count);
				int newCount = pollEmailCountSrv.getPollEmailCountInfo(user);
				Logger.debug("new poll email count is :::"+newCount);
				if (contacts != null && !contacts.isEmpty())
					sendInCyyteToRegion(user, incyyte.isAnonymity(), incyyteId, contacts, servletContext, incyyte.getSendZone());
				return incyyteId;
			}
		}

		return null;
	}

	private Long addInCyyteByPostingToRegion(User user, InCyyte incyyte) throws Exception {
		incyyte.setUserId(user.getId());
		Long incyyteId = addInCyyteByPostRegion(incyyte);
		Logger.debug("incyyteId - " + incyyteId);
		return incyyteId;
	}

	@Override
	public void sendMessageToPollRecipients(User user, UserContactModel usercModel, InCyyteChart chart) {
		quickStartMgr.sendMessageToPollRecipients(user, usercModel,chart);
	}

	@Override
	public void sendContactUsMessage(User user, String messageText) {
		quickStartMgr.sendContactUsMessage(user, messageText);
	}
}