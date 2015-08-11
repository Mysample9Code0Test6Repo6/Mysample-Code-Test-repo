package com.incyyte.app.manager;

import com.incyyte.app.domain.*;
import com.incyyte.app.web.model.QuestionModel;
import com.incyyte.app.web.model.Questionlist;

import java.util.List;
import java.util.Map;

public interface QuestionManager {

    Questionlist searchQuest(QuestionModel quesModel, Long id);

    QuestionModel getQuestionsdtls(String questionId);

    public List<Person> getMembersByPostcode(String postcode, PeopleFilter filter);

    public List<Person> getMembersByRegion(String postalRegion, PeopleFilter filter);

    public List<Person> getMembersByCounty(String county, PeopleFilter filter);

    public Long countMembersByPostcode(String postcode, PeopleFilter filter);

    public Long countMembersByRegion(String postalRegion, PeopleFilter filter);

    public Long countMembersByCounty(String county, PeopleFilter filter);

    String getProfilePicUrl(String email);

    Long getRecipientsCount(Long questionId) throws Exception;
	
	void getRecipientsGenderCount(InCyyte incyyte, User loggedInUser) throws Exception;

    Map<String, Map<String, String>> getPublicPolls() throws Exception;

    List<InCyyteChart> getPublicPolls(int offset, int recordsPerPage, String searchString) throws Exception;

    List<InCyyteChart> getPollsByCategory(int offset, int recordsPerPage, String category) throws Exception;

    List<InCyyteChart> getLatestPolls() throws Exception;

    List<InCyyteChart> getTopPollsByCategory(String category, int count) throws Exception;

    void shareInCyyteToGroupContacts(InCyyte incyyte) throws Exception;

}
