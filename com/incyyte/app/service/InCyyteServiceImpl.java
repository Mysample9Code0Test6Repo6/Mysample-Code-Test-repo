package com.incyyte.app.service;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.manager.InCyyteManager;
import com.incyyte.app.service.util.Logger;

import org.springframework.beans.factory.annotation.Autowired;

public class InCyyteServiceImpl implements InCyyteService {

    @Autowired
    private InCyyteManager inCyyteManager;

    @Override
    public String processPublicPoll(User user, String incyyteId, long selectedAnswerId) throws Exception {
       return  inCyyteManager.processPublicPoll(user, incyyteId, selectedAnswerId);
    }

    @Override
    public InCyyte processNonMemberVote(User user, boolean isShared, long incyyteId, long memberId, long selectedAnswerId) throws Exception {
        return inCyyteManager.processNonMemberVote(user, isShared, incyyteId, memberId, selectedAnswerId);
    }
}
