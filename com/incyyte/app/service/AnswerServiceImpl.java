package com.incyyte.app.service;

import com.incyyte.app.manager.AnswerManager;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.AnswerModel;

public class AnswerServiceImpl implements AnswerService{
	
	private AnswerManager answerManager;

    public void setAnswerManager(AnswerManager answerManager) {
        this.answerManager = answerManager;
    }

	@Override
	public long storeAnswerInfo(AnswerModel answerModel) throws AccessException{
        Logger.debug("AnswerServiceImpl------------->" + answerModel);
		return answerManager.storeAnswerInfo(answerModel);
	}

}
