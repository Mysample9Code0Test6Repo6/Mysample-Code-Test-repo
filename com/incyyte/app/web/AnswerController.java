package com.incyyte.app.web;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.incyyte.app.service.AnswerService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.AnswerModel;
import com.incyyte.app.web.model.IncyyteModel;

@Controller
public class AnswerController  
{
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	
	@Autowired
	private AnswerService answersrv;
	
	@RequestMapping(value = "/storeAnswers", method = RequestMethod.POST)
	public String storeAnswers(HttpServletRequest request, 
			SessionStatus status, HttpSession session)throws Exception{
	    IncyyteModel incyyteModel= (IncyyteModel) session.getAttribute(SessionKeys.INCYYTE_MODEL);
		Iterator<AnswerModel> itr = incyyteModel.getAnswers().iterator();
		
		try{
			long answersSuccessCount=0;
	            while (itr.hasNext()) {
	            	AnswerModel answerModel=(AnswerModel)itr.next();
                    Logger.debug("answerModel::"+answerModel);
	               	if(StringUtils.isNotBlank(answerModel.getAnswerOption())){
	               		answersSuccessCount=answersrv.storeAnswerInfo(answerModel);
	            	}
	        }
         Logger.debug(answersSuccessCount+" Answers Inserted SuccessFully");
		}catch(Exception e){
			Logger.error("Exception::",e);
			return "error";
		}
		return "success";
	}
}