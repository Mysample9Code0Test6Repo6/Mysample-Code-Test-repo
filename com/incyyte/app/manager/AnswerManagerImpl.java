package com.incyyte.app.manager;

import com.incyyte.app.dao.answers.AnswerDao;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.AnswerModel;

public class AnswerManagerImpl  implements AnswerManager{
	
	private AnswerDao answerDao;
  
    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }


@Override
public long storeAnswerInfo(AnswerModel answerModel) throws AccessException {
	Logger.debug("AnswerManagerImpl------------->"+answerModel);
	return answerDao.storeAnswerInfo(answerModel);
 }
}
