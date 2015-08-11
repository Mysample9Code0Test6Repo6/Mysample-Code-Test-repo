package com.incyyte.app.manager;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;

public interface InCyyteManager {

   String  processPublicPoll(User user, String incyyteId, long selectedAnswerId) throws Exception;

    InCyyte processNonMemberVote(User user, boolean isShared, long incyyteId, long memberId, long selectedAnswerId) throws Exception;
}
