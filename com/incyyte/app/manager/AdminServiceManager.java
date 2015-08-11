package com.incyyte.app.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.dao.admin.AdminDao;
import com.incyyte.app.dao.question.QuestionDao;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.Answer;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.util.Logger;

public class AdminServiceManager implements AdminManager{
	private UserDao userDaoImpl;
	private AdminDao adminDaoImpl;
	
	@Autowired
	private QuestionDao questionDaoImpl;

	public void setUserDaoImpl(UserDao userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}

	public void setAdminDaoImpl(AdminDao adminDaoImpl) {
		this.adminDaoImpl = adminDaoImpl;
	}

	@Override
	public List<InCyyte> getInCyyteByCreatedBy(String createdby, String questionType) {
		return userDaoImpl.getInCyyteByCreatedBy(createdby, questionType);
	}

	@Override
	public int getTotalContacts(Long userId) throws Exception {
		return adminDaoImpl.getTotalContacts(userId);
	}

	@Override
	public int getTotalUsers() throws Exception {
		return adminDaoImpl.getTotalUsers();
	}

	@Override
	public int getTotalGroups(Long userId) throws Exception {
		return adminDaoImpl.getTotalGroups(userId);
	}

	@Override
	public int getTotalIncyyte(Long userId) throws Exception {
		return adminDaoImpl.getTotalIncyyte(userId);
	}

	@Override
	public List<User> getTopIncyyteUsers() throws Exception {
		return adminDaoImpl.getTopIncyyteUsers();
	}

	@Override
	public  List<InCyyte> getAllPolls(int offset , int recordsPerPage) throws Exception {
		Logger.debug("Inside getAllPolls():AdminServiceManager");
		List<InCyyte> incyytes = questionDaoImpl.getAllPolls(offset , recordsPerPage );
		for (InCyyte incyyte: incyytes) {
			List<Answer> answers = userDaoImpl.getInCyyteAnswers(incyyte.getId());
			incyyte.setAnswers(answers);
		}
		Logger.debug("InCyytes::" + incyytes);
		return incyytes;
	}

	@Override 
	public void updatePoll(String questionId, String name)throws Exception{
		questionDaoImpl.updateQuestion(questionId, name);
	}
}
