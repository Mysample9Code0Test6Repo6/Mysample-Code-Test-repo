package com.incyyte.app.service;

import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.web.model.AnswerModel;


public interface AnswerService {

	long storeAnswerInfo(AnswerModel answerModel) throws AccessException;

}
