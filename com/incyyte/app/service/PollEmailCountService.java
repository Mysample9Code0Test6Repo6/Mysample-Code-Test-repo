package com.incyyte.app.service;

import java.util.List;

import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;

public interface PollEmailCountService {

    void storePollEmailCount(User user, InCyyte incyyte, int sharedCount) throws Exception;

    public int getPollEmailCountInfo(User user) throws Exception;
    
    List<String> validatePollEmails(List<Contact> contacts, boolean anonymous, User user);

}