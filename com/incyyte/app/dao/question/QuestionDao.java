package com.incyyte.app.dao.question;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.web.model.QuestionModel;
import com.incyyte.app.web.model.Questionlist;

import java.util.List;
import java.util.Map;

public interface QuestionDao {

	Questionlist searchQuest(QuestionModel quesModel, Long id);

	QuestionModel getQuestionsdtls(String questionId);

	List<Long> getQuestionsByGroupId(Long groupId) throws Exception;

	Long getRecipientsCount(Long questionId) throws Exception;

	void getRecipientsGenderCount(InCyyte incyyte, User loggedInUser) throws Exception;

	Map<String, Map<String, String>> getPublicPolls() throws Exception;

	List<InCyyte> getAllPolls(int offset , int recordsPerPage) throws Exception;

	void updateQuestion(String questionId, String name) throws Exception;

	List<InCyyte> getPublicPolls(int offset, int recordsPerPage, String searchString) throws Exception;

	List<InCyyte> getPollsByCategory(int offset, int recordsPerPage, String category) throws Exception;

}