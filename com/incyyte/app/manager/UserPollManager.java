package com.incyyte.app.manager;

import java.util.List;
import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.PagePhoto;
import com.incyyte.app.domain.ReportPoll;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserPollPage;

public interface UserPollManager {

	boolean saveUserPollPageInfo(UserPollPage userPollPage) throws Exception;

	UserPollPage getUserPollPageInfo(long userId, AddressType addressType) throws Exception;
	
	public List<InCyyte> getMyPollPageShareContacts(User user,String sendType) throws Exception;

	void deletePollPagePhoto(PagePhoto pagePhoto) throws Exception;

	String reportPoll(ReportPoll reportPoll) throws Exception;

	List<UserPollPage> getUserPollPagesInformation(long userId,	AddressType addressType) throws Exception;

}