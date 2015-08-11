package com.incyyte.app.manager;

import java.util.Date;

public interface ResponseManager {

    void addPostedResponse(long incyyteID, long userId, long selectedAnswerId, String gender, String ageGroup, Date responseDate);

    void addRegionalResponse(long incyyteID, long userId, long selectedAnswerId);

    void addResponse(long incyyteID, long memberId, long selectedAnswerId, boolean shared);
}
