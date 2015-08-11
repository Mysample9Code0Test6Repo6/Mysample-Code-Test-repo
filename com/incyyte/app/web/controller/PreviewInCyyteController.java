package com.incyyte.app.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.incyyte.app.service.util.RefData;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.IncyyteModel;

@Controller
@RequestMapping("/preview_incyyte.cyt")
public class PreviewInCyyteController {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private RefData referenceData;

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {		
		// return form view
		return "preview_incyyte";
	}

	@RequestMapping(params = "Send", method = RequestMethod.POST)
	public String processSubmit(HttpServletRequest request, 
			@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
			BindingResult result, SessionStatus status, HttpSession session) {		
		// return form success view
		return "redirect:send_question.cyt";
	}
	
	@RequestMapping(params = "Create", method = RequestMethod.POST)
	public ModelAndView processCreate(HttpServletRequest request, 
			@ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
			BindingResult result, SessionStatus status, HttpSession session, ModelMap model) {

		incyyteModel = (IncyyteModel)session.getAttribute(SessionKeys.INCYYTE_MODEL);
		
		// command object
		model.addAttribute("inCyyteForm", incyyteModel);
		model.put("categories",referenceData.getInCyyteCategories());
		
		return new ModelAndView("/create_question", model); 
	}	

}
