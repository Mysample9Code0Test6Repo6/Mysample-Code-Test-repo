package com.incyyte.app.service;

import com.incyyte.app.domain.*;
import com.incyyte.app.manager.QuestionManager;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.QuestionModel;
import com.incyyte.app.web.model.Questionlist;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class QuestionServiceimpl implements QuestionService {

    @Autowired
    QuestionManager quesMgr;

    @Override
    public Questionlist searchQuest(QuestionModel quesModel, Long id) {
        return quesMgr.searchQuest(quesModel, id);
    }

    @Override
    public QuestionModel getQuestionsdtls(String questionId) {
        return quesMgr.getQuestionsdtls(questionId);
    }

    @Override
    public List<Person> getMembersByPostcode(String postcode, PeopleFilter filter) {
        return quesMgr.getMembersByPostcode(postcode, filter);
    }

    @Override
    public List<Person> getMembersByRegion(String postalRegion, PeopleFilter filter) {
        return quesMgr.getMembersByRegion(postalRegion, filter);
    }

    @Override
    public Long countMembersByPostcode(String postcode, PeopleFilter filter) {
        return quesMgr.countMembersByPostcode(postcode, filter);
    }

    @Override
    public Long countMembersByRegion(String postalRegion, PeopleFilter filter) {
        return quesMgr.countMembersByRegion(postalRegion, filter);
    }

    @Override
    public List<Person> getMembersByCounty(String county, PeopleFilter filter) {
        return quesMgr.getMembersByCounty(county, filter);
    }

    @Override
    public Long countMembersByCounty(String county, PeopleFilter filter) {
        return quesMgr.countMembersByCounty(county, filter);
    }

    @Override
    public String getProfilePicUrl(String email) {
        return quesMgr.getProfilePicUrl(email);
    }

    @Override
    public Long getRecipientsCount(Long questionId) throws Exception {
        return quesMgr.getRecipientsCount(questionId);
    }

    @Override
    public void getRecipientsGenderCount(InCyyte incyyte, User loggedInUser) throws Exception {
        quesMgr.getRecipientsGenderCount(incyyte, loggedInUser);
    }

    @Override
    public Map<String, Map<String, String>> getPublicPolls() throws Exception {
      	return quesMgr.getPublicPolls();
    }

    @Override
    public List<InCyyteChart> getPublicPolls(int offset, int recordsPerPage, String searchString) throws Exception {
        return quesMgr.getPublicPolls(offset, recordsPerPage, searchString);
    }

    @Override
    public List<InCyyteChart> getPollsByCategory(int offset, int recordsPerPage, String category) throws Exception {
        return quesMgr.getPollsByCategory(offset, recordsPerPage, category);
    }

    @Override
    public List<InCyyteChart> getLatestPolls() throws Exception {
        return quesMgr.getLatestPolls();
    }

    @Override
    public List<InCyyteChart> getTopPollsByCategory(String category, int count) throws Exception {
        return quesMgr.getTopPollsByCategory(category, count);
    }

    @Override
    public void shareInCyyteToGroupContacts(InCyyte incyyte) throws Exception {
        Logger.info("shareInCyyteToGroupContacts:Start:");
        quesMgr.shareInCyyteToGroupContacts(incyyte);
    }
}