package com.incyyte.app.service;

import java.util.List;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.manager.AdminManager;
import com.incyyte.app.manager.RegistrationManager;
import com.incyyte.app.service.util.Logger;

public class AdminServiceImpl implements AdminService{

	AdminManager adminMgr;
	RegistrationManager registrationMgr;

	public List<InCyyte> getInCyyteByCreatedBy(String createdby, String questionType) {
		return adminMgr.getInCyyteByCreatedBy(createdby, questionType);
	}

	public List<User> getUsersByEmailOrUsername(String email, String username) {
		return registrationMgr.getUsersByEmailOrUsername(email, username);
	}

	public void setAdminMgr(AdminManager adminMgr) {
		this.adminMgr = adminMgr;
	}

	public void setRegistrationMgr(RegistrationManager registrationMgr) {
		this.registrationMgr = registrationMgr;
	}

	@Override
	public int getTotalContacts(Long userId) throws Exception {
		return adminMgr.getTotalContacts(userId);
	}

	@Override
	public int getTotalUsers() throws Exception {
		return adminMgr.getTotalUsers();
	}

	@Override
	public int getTotalGroups(Long userId) throws Exception {
		return adminMgr.getTotalGroups(userId);
	}

	@Override
	public int getTotalIncyyte(Long userId) throws Exception {
		return adminMgr.getTotalIncyyte(userId);
	}

	@Override
	public List<User> getTopIncyyteUsers() throws Exception {
		return adminMgr.getTopIncyyteUsers();
	}
	@Override
	public List<InCyyte> getAllPolls(int offset , int recordsPerPage) throws Exception {
		Logger.debug("Inside getAllPolls():AdminServiceImpl");
		return adminMgr.getAllPolls(offset , recordsPerPage );
	}
	@Override
	public void updatePoll(String questionId, String name)throws Exception{
		Logger.info("Inside updateQuestion():AdminServiceImpl");
		adminMgr.updatePoll(questionId, name);
	}

}