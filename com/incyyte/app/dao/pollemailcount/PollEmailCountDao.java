package com.incyyte.app.dao.pollemailcount;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;

public interface PollEmailCountDao {

    void storePollEmailCount(User user, InCyyte incyyte, int sharedCount) throws Exception;

    int getPollEmailCountInfo(User user) throws Exception;
}