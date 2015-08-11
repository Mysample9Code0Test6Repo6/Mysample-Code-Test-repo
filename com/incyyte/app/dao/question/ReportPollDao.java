package com.incyyte.app.dao.question;

import com.incyyte.app.domain.ReportPoll;

public interface ReportPollDao {

    String insertReportPoll(ReportPoll reportPoll) throws Exception;

    int getReportPollCount(Long questionId) throws Exception;

}