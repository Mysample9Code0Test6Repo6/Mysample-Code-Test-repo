package com.incyyte.app.service;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;

public interface InCyyteService {

    String processPublicPoll(User user, String incyyteId, long selectedAnswerId) throws Exception;

    InCyyte processNonMemberVote(User user, boolean isShared, long incyyteId, long memberId, long selectedAnswerId) throws Exception;
}
