package com.incyyte.app.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.UserPollService;
import com.incyyte.app.web.model.EmailModel;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.UserModel;


@Controller
public class FooterController {
	
	@Autowired
	private UserPollService userPollSrv;
	
	@Autowired
	private QuickStartService quickStartSrv;

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value="/maintenance.cyt", method = RequestMethod.GET)
	public String holding(ModelMap model) {setModel(model); return "main/footer/holding";}

	@RequestMapping(value="/anonymity.cyt", method = RequestMethod.GET)
	public String anonymity(ModelMap model) {setModel(model); return "main/footer/anonymity";}

	@RequestMapping(value="/contactUs.cyt", method = RequestMethod.GET)
	public String contactUs(ModelMap model) {
		setModel(model);
		model.put("contactForm", new EmailModel()); 
		return "main/footer/contact_us";
	}
	
	@RequestMapping(value="/sendcontactemail.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String sendContactEmail(HttpServletRequest request, @ModelAttribute("contactForm") EmailModel emailModel) {
		try{
			User user = new User();
			user.setEmail(emailModel.getEmailFrom());
			user.setUsername(emailModel.getSenderName());
			user.setMobile(emailModel.getTelephone());
			String messageText = emailModel.getMessage().replaceAll("\n", " <br> ");
			messageText += " <br><br> " + emailModel.getSenderName() + " <br> " + emailModel.getTelephone();
			quickStartSrv.sendContactUsMessage(user, messageText);
			return "success";
			
		}catch(Exception e){}
		
		return "fail";
	}
	

    @RequestMapping(value="/voteConfirmation.cyt", method = RequestMethod.GET)
    public String voteConfirmation(ModelMap model) {setModel(model); return "main/VoteConfirmation";}

    @RequestMapping(value="/faq.cyt", method = RequestMethod.GET)
	public String faq(ModelMap model) {setModel(model); return "main/footer/faq";}

	@RequestMapping(value="/privacy.cyt", method = RequestMethod.GET)
	public String privacy(ModelMap model) {setModel(model); return "main/footer/privacy_policy";}

	@RequestMapping(value="/redcard.cyt", method = RequestMethod.GET)
	public String redCard(ModelMap model) {setModel(model); return "main/footer/red_card_system";}

    @RequestMapping(value="/incyyteBusiness.cyt", method = RequestMethod.GET)
    public String incyyteBusiness(ModelMap model) {setModel(model); return "main/footer/incyyteBusiness";}

	@RequestMapping(value="/tour.cyt", method = RequestMethod.GET)
	public String tour(ModelMap model) {setModel(model); return "main/footer/tour";}
	
	@RequestMapping(value="/reactiveAcct.cyt", method = RequestMethod.GET)
	public String ReactiveAcct(ModelMap model) {setModel(model); return "main/ReactiveAcct";}
	
	@RequestMapping(value="/createAccountConfirmation.cyt", method = RequestMethod.GET)
	public String SuccessMsg(ModelMap model) {setModel(model); return "main/createAccountConfirmation";}

    @RequestMapping(value="/inCyyte.cyt", method = RequestMethod.GET)
	public String whatIsInCyyte(ModelMap model) {setModel(model); return "main/footer/what_is_inCyyte";}
	
	@RequestMapping(value="/termsUse.cyt", method = RequestMethod.GET)
	public String termsUse(ModelMap model) {setModel(model); return "main/footer/terms_use";}
	
	@RequestMapping(value="/termsConditions.cyt", method = RequestMethod.GET)
	public String termsConditions(ModelMap model) {setModel(model); return "main/footer/terms_conditions";}
	
	private void setModel(ModelMap model){
		UserModel userModel = new UserModel();
		IncyyteModel incyyteModel = new IncyyteModel();
		// command object
		model.put("inCyyteForm", incyyteModel);
		model.put("signUpForm", userModel);
		model.put("loginForm", userModel);

	}

}
