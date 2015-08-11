package com.incyyte.app.dao.question;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.domain.ReportPoll;
import com.incyyte.app.service.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

public class ReportPollDaoImpl implements ReportPollDao {
    @Autowired
    AbstractDao abstractDao;

    @Override
    public String insertReportPoll(ReportPoll reportPoll) throws Exception {
        String insertStmt = "INSERT INTO REPORT_POLLS (REPORT_ID, FK_USER_ID, FK_QUESTION_ID, REPORT_TYPE, COMMENTS) " +
                " VALUES (?, ?, ?, ?, ?)";
        QueryParameters qp = new QueryParameters();
        Long reportId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("REPORT_ID_PK", true);
        reportPoll.setReportId(reportId);
        qp.addParam(reportPoll.getReportId());
        qp.addParam(reportPoll.getUserId());
        qp.addParam(reportPoll.getQuestionId());
        qp.addParam(reportPoll.getReportType());
        qp.addParam(reportPoll.getComments());
        try {
            QueryHelper.doUpdate(abstractDao, "insertReportPoll", insertStmt, qp);
            return "inserted";
        } catch (DuplicateKeyException dke) {
            //Logger.warn("User already polled and hence ignoring Polling" + dke.getMessage());
            return "alreadyReported";
        } catch (Exception e) {
            Logger.error("insertReportPoll: Failed ", e);
            throw e;
        }
    }

    @Override
    public int getReportPollCount(Long questionId) throws Exception {
        String countQuery = "SELECT count(1) FROM REPORT_POLLS WHERE fk_question_id = ? ";
        QueryParameters param = new QueryParameters();
        param.addParam(questionId);
        return QueryHelper.doQueryForInt(abstractDao, "getReportPollCount", countQuery, param);
    }
}