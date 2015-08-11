package com.incyyte.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.PagePhoto;
import com.incyyte.app.domain.ReportPoll;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserPollPage;
import com.incyyte.app.manager.UserPollManager;

public class UserPollServiceImpl implements UserPollService {
    @Autowired
    private UserPollManager userPollMgr;

    @Override
    public boolean saveUserPollPageInfo(UserPollPage userPollPage) throws Exception {
        return userPollMgr.saveUserPollPageInfo(userPollPage);
    }

    public void deletePollPagePhoto(PagePhoto pagePhoto) throws Exception {
        userPollMgr.deletePollPagePhoto(pagePhoto);
    }

    @Override
    public UserPollPage getUserPollPageInfo(long userId, AddressType addressType) throws Exception {
        return userPollMgr.getUserPollPageInfo(userId, addressType);
    }
    
    @Override
    public List<UserPollPage> getUserPollPagesInformation(long userId, AddressType addressType) throws Exception {
        return userPollMgr.getUserPollPagesInformation(userId, addressType);
    }
    
    @Override
    public List<InCyyte> getMyPollPageShareContacts(User user,String sendType) throws Exception {
    	return userPollMgr.getMyPollPageShareContacts(user,sendType);
    }

    @Override
    public String reportPoll(ReportPoll reportPoll) throws Exception {
        return userPollMgr.reportPoll(reportPoll);
    }
}