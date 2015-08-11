package com.incyyte.app.manager;

import java.util.List;

import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;

public interface PollEmailCountManager {

    void storePollEmailCount(User user, InCyyte incyyte, int sharedCount) throws Exception;

    public int getPollEmailCountInfo(User user) throws Exception;
    
    List<String> validatePollEmails(List<Contact> contacts, boolean anonymous, User user);


}