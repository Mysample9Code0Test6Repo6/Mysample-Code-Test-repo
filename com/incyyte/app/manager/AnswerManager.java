package com.incyyte.app.manager;

import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.web.model.AnswerModel;

public interface AnswerManager {
	long storeAnswerInfo(AnswerModel answerModel) throws AccessException;

}
