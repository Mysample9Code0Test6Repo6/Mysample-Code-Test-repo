package com.incyyte.app.service;

import java.util.List;

import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.manager.PollEmailCountManager;
import org.springframework.beans.factory.annotation.Autowired;

public class PollEmailCountServiceImpl implements PollEmailCountService {

    @Autowired
    PollEmailCountManager pollEmailCountMgr;

    @Override
    public void storePollEmailCount(User user, InCyyte incyyte, int sharedCount) throws Exception {
        pollEmailCountMgr.storePollEmailCount(user, incyyte, sharedCount);
    }
    @Override
    public int getPollEmailCountInfo(User user) throws Exception {
        return pollEmailCountMgr.getPollEmailCountInfo(user);

    }
    @Override
	public List<String> validatePollEmails(List<Contact> contacts, boolean anonymous, User user) {
		return pollEmailCountMgr.validatePollEmails(contacts, anonymous, user);
	}
}