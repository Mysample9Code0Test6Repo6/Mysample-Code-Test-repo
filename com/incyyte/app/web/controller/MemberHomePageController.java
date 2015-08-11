package com.incyyte.app.web.controller;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.incyyte.app.service.util.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.incyyte.app.service.HomeService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.AnswerModel;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.User;

@Controller
public class MemberHomePageController extends BaseContoller{

	final static int OFFSET = 5;
	
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private HomeService homeSrv;
	@Autowired
	private QuickStartService quickStartSrv;
	
	private User user;
	
	@RequestMapping(value = "/dash", method = RequestMethod.GET)
	public String initForm(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		user = (User)session.getAttribute(SessionKeys.LOGIN_USER);
		
		if(user == null || user.getId() == null){
            Logger.debug("%%%%%%%%%%%% Error: user not in session - return to login page");
			return "redirect:welcome.cyt";
		}
		
		if(request.getParameter("code") != null){
			String cyyteCode = request.getParameter("code");
			model.put("chartCode", cyyteCode);		
			session.setAttribute("incyyteCode", cyyteCode);
		}
		else if(session.getAttribute(SessionKeys.INCYYTE_CODE) != null){
			String cyyteCode = (String)session.getAttribute(SessionKeys.INCYYTE_CODE);			
			model.put("chartCode", cyyteCode);		
			session.setAttribute("incyyteCode", cyyteCode);
			session.removeAttribute(SessionKeys.INCYYTE_CODE);
		}
		else if(session.getAttribute(SessionKeys.INCYYTE_ID) != null){
			long cyyteId = (Long)session.getAttribute(SessionKeys.INCYYTE_ID);
			InCyyte cyyte = quickStartSrv.initChart(cyyteId);
			model.put("questionCode", cyyte.getIncyyteCode());	
			model.put("question", cyyte.getIncyyte());	
			session.setAttribute("incyyteCode", cyyte.getIncyyteCode());
			session.removeAttribute(SessionKeys.INCYYTE);
			session.removeAttribute(SessionKeys.INCYYTE_ID);
		}
		else{
            Logger.debug("%%%%%%%%%%%% here");
			String cyyteCode = getRandomIncyyteCode();
			if(cyyteCode != null){
				model.put("generalChartCode", cyyteCode);		
				session.setAttribute("incyyteCode", cyyteCode);
				session.setAttribute("memberId", user.getId().toString());
			}
		}
				
		model.put("sendInCyyteForm", new IncyyteModel());
		// return form success view
		return "main/home";
	}
	

	private String loadEmails(String grpIds){
		String emailArr = null;
		String[] grpIdArr = grpIds.split("\\|");
		for (int x=0; x<grpIdArr.length; x++){
            Logger.debug(grpIdArr[x]);
			List<Contact> contacts = homeSrv.getContactsEmails(grpIdArr[x]);  			
			for(Contact contact : contacts){
				emailArr += contact.getEmail() + "|";
			}
		}	     
		return emailArr;
	}
	
	private String getRandomIncyyteCode(){
		
		List<InCyyte> list = homeSrv.getInCyyteByCreatedBy(Constants.CREATED_BY_ADMIN, Constants.QUESTION_TYPE_G);
		
		if(list != null && !list.isEmpty()){ 			
			// compute a fraction of the range, 0 <= frac < range
	    	long randomNumber = (long)((list.size()) * (new Random()).nextDouble());
            Logger.debug("%%%%%%%%% Generated : " + randomNumber);
	    	InCyyte cyyte = list.get((int)randomNumber);
            Logger.debug("%%%%%%%%% Incyyte : " + cyyte.getIncyyte());
	      	return cyyte.getIncyyteCode();
		}
        Logger.debug("%%%%%%%%% getRandomIncyyteCode return null ");
		return null;
	}

}
