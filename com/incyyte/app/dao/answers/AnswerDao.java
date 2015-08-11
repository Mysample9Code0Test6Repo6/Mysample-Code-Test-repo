package com.incyyte.app.dao.answers;

import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.web.model.AnswerModel;

public interface AnswerDao {

	long storeAnswerInfo(AnswerModel answerModel) throws AccessException;
}
