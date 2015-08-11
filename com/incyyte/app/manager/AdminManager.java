package com.incyyte.app.manager;

import java.util.List;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;

public interface AdminManager {
	public List<InCyyte> getInCyyteByCreatedBy(String createdby, String questionType);

	public int getTotalContacts(Long userId)throws Exception;

	public int getTotalUsers()throws Exception;

	public int getTotalGroups(Long userId)throws Exception;

	public int getTotalIncyyte(Long userId)throws Exception;

	public List<User> getTopIncyyteUsers()throws Exception;

	public List<InCyyte> getAllPolls(int offset , int recordsPerPage) throws Exception;

	public void updatePoll(String questionId, String name)throws Exception;

}
