package com.incyyte.app.dao.answers;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.AnswerModel;
import org.springframework.jdbc.core.JdbcTemplate;

public class AnswerDaoImpl implements AnswerDao {

    private AbstractDao abstractDao;

    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public long storeAnswerInfo(AnswerModel answerModel) throws AccessException {
        long answerid = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("ANSWERS_PK", false);
        Logger.debug("AnswerDaoImpl------------->" + answerModel);
        JdbcTemplate template = abstractDao.getJdbcTemplate("storeAnswerInfo");
        String answerQuery = AnswerDaoQueries.storeAnswerInfo();
        Logger.debug("answerQuery------------->" + answerQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(answerid);
        params.addParam(answerModel.getQuesId());
        params.addParam(answerModel.getAnswerChoice());
        params.addParam(answerModel.getAnswerType());
        params.addParam(answerModel.getAnswerFileName());
        params.addParam(answerModel.getAnswerUploadExt());
        params.addParam(answerModel.getAnswerUploadedUrl());
        params.addParam(answerModel.getCdnFileName());
        params.addParam(answerModel.getAnswerlink());
        try {
            return QueryHelper.doUpdate(template, answerQuery, params);
        } catch (Exception e) {
            Logger.error("Exception::", e);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "storeAnswerInfo");
        }
        return 0;
    }
}