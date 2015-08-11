package com.incyyte.app.manager;

import com.incyyte.app.dao.contacts.ContactDao;
import com.incyyte.app.dao.question.PollDao;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.*;
import com.incyyte.app.service.util.InCyyteConstants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.QuestionModel;
import com.incyyte.app.web.model.Questionlist;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class QuestionServiceManager implements QuestionManager {

    @Autowired
    private com.incyyte.app.dao.question.QuestionDao questionDao;

    @Autowired
    private PollDao pollDao;

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private HomeManager homeMgr;

    @Autowired
    private UserDao userDao;
 public com.incyyte.app.dao.question.QuestionDao getQuestionDao() {
        return questionDao;
    }

    public void setQuestionDao(com.incyyte.app.dao.question.QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public PollDao getPollDao() {
        return pollDao;
    }

    public void setPollDao(PollDao pollDao) {
        this.pollDao = pollDao;
    }

    public ContactDao getContactDao() {
        return contactDao;
    }

    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Questionlist searchQuest(QuestionModel quesModel, Long id) {
        return questionDao.searchQuest(quesModel, id);
    }

    @Override
    public QuestionModel getQuestionsdtls(String questionId) {
        return questionDao.getQuestionsdtls(questionId);
    }

    @Override
    public List<Person> getMembersByPostcode(String postcode, PeopleFilter filter) {
        return pollDao.getMembersByPostcode(postcode, filter);
    }

    @Override
    public List<Person> getMembersByRegion(String postalRegion, PeopleFilter filter) {
        return pollDao.getMembersByRegion(postalRegion, filter);
    }

    @Override
    public Long countMembersByPostcode(String postcode, PeopleFilter filter) {
        return pollDao.countMembersByPostcode(postcode, filter);
    }

    @Override
    public Long countMembersByRegion(String postalRegion, PeopleFilter filter) {
        return pollDao.countMembersByRegion(postalRegion, filter);
    }

    @Override
    public List<Person> getMembersByCounty(String county, PeopleFilter filter) {
        return pollDao.getMembersByCounty(county, filter);
    }

    @Override
    public Long countMembersByCounty(String county, PeopleFilter filter) {
        return pollDao.countMembersByCounty(county, filter);
    }

    @Override
    public String getProfilePicUrl(String email) {
        return contactDao.getProfileImgURL(email);
    }

    @Override
    public Long getRecipientsCount(Long questionId) throws Exception {
        return questionDao.getRecipientsCount(questionId);
    }

    @Override
    public void getRecipientsGenderCount(InCyyte incyyte, User loggedInUser) throws Exception {
        questionDao.getRecipientsGenderCount(incyyte, loggedInUser);
    }

    @Override
    public Map<String, Map<String, String>> getPublicPolls() throws Exception {
        return questionDao.getPublicPolls();
    }

    @Override
    public List<InCyyteChart> getPublicPolls(int offset, int recordsPerPage, String searchString) throws Exception {
        List<InCyyte> inCyytes = questionDao.getPublicPolls(offset, recordsPerPage, searchString);
        return homeMgr.getChartFromInCyyte(inCyytes, null);
    }

    @Override
    public List<InCyyteChart> getPollsByCategory(int offset, int recordsPerPage, String category) throws Exception {
        List<InCyyte> inCyytes = questionDao.getPollsByCategory(offset, recordsPerPage, category);
        return homeMgr.getChartFromInCyyte(inCyytes, null);
    }

    @Override
    public List<InCyyteChart> getLatestPolls() throws Exception {
        List<InCyyte> incyytes = questionDao.getPollsByCategory(InCyyteConstants.NO_OFFSET_LIMIT, InCyyteConstants.NO_RECORDS_PER_PAGE_LIMIT, null);
        if (incyytes.size() >= 12) {
            return homeMgr.getChartFromInCyyte(incyytes.subList(0, 11), null);
        }
        return homeMgr.getChartFromInCyyte(incyytes, null);
    }

    @Override
    public List<InCyyteChart> getTopPollsByCategory(String category, int count) throws Exception {
        List<InCyyte> incyytes = questionDao.getPollsByCategory(InCyyteConstants.NO_OFFSET_LIMIT, InCyyteConstants.NO_RECORDS_PER_PAGE_LIMIT, category);
        if (incyytes.size() >= count && count > 0) {
            return homeMgr.getChartFromInCyyte(incyytes.subList(0, (count - 1)), null);
        }
        return homeMgr.getChartFromInCyyte(incyytes, null);
    }

    @Override
    public void shareInCyyteToGroupContacts(InCyyte incyyte) throws Exception {
        Logger.debug("shareInCyyteToGroupContacts:Start:");
        userDao.shareInCyyteToGroupContacts(incyyte);
    }
}