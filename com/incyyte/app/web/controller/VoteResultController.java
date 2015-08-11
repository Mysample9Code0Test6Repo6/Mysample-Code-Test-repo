package com.incyyte.app.web.controller;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.User;
import com.incyyte.app.security.InCyyteSecurity;
import com.incyyte.app.service.Exceptions.AuthenticationException;
import com.incyyte.app.service.Exceptions.ExceptionMessages;
import com.incyyte.app.service.Exceptions.ResponderException;
import com.incyyte.app.service.ForgotPasswordService;
import com.incyyte.app.service.InCyyteService;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.RegistrationService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.UserModel;
import com.incyyte.app.web.model.VoteModel;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class VoteResultController {

	/**
	 * Logger for this class and subclasses
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private QuickStartService quickStartSrv;

	@Autowired
	private LoginService loginSrv;

	@Autowired
	private ForgotPasswordService forgotPwdSrv;

	@Autowired
	private RegistrationService registrationSrv;

	@Autowired
	private InCyyteService inCyyteService;

	final static int OFFSET = 5;

	@RequestMapping(value = "/voteajaxincome.cyt", method = RequestMethod.POST)
	public String voteAjaxIncome(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logger.info("Inside voteajaxincome.cyt");
		VoteModel voteModel = new VoteModel();
		int incyyteId = Integer.parseInt(request.getParameter("incyyteId"));
		User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
		voteModel.setIncyyteId(incyyteId);
		voteModel.setUserId(user.getId());

		String answerId = "selectedAnswerID"+incyyteId;
		Logger.info("answerId::::::" + answerId);
		Logger.info("selectedAnsVal::::::" + request.getParameter(answerId));

		voteModel.setSelectedAnswer(request.getParameter(answerId));

		Logger.info("votemadel::::::" + voteModel);
		Logger.info("Vote - incyyteId - " + incyyteId);
		if (user != null && user.getId() != null) {
			try {
				if (voteModel != null && voteModel.getSelectedAnswer() != null) {
					long selectedAnswerId = Long.parseLong(voteModel.getSelectedAnswer());
					Logger.info("Vote - selectedAnswerId - " + selectedAnswerId);
					Long memberId = quickStartSrv.getPollMemberId(user.getEmail(), incyyteId);
					Logger.info("Vote - memberId - " + memberId);
					InCyyteChart cyyteChart = null;
					boolean isShared = false;
					if (memberId == null) {
						Long sharedInCyyteID = quickStartSrv.checkPollShared(user.getEmail(), incyyteId);
						if (sharedInCyyteID != null) {
							isShared = true;
							cyyteChart = quickStartSrv.updateChart(incyyteId, sharedInCyyteID, selectedAnswerId, isShared);
						}
					} else
						cyyteChart = quickStartSrv.updateChart(incyyteId, memberId, selectedAnswerId, isShared);

					if (cyyteChart != null && cyyteChart.getIncyyte() != null) {
						cyyteChart.getIncyyte().getIncyyteCode();
						//reset incoming incyytes
						request.getSession().setAttribute("myIncomeinCyyte", null);
						request.getSession().removeAttribute("myIncomeinCyyte");
					}
				}
			} catch (ResponderException re) {
				//user already responded so ignored
			} catch (Exception e) {
				Logger.error("Exception:", e);
			}
		}
		return "redirect:dash.cyt";
	}


	@RequestMapping(value = "/voteincome.cyt", method = RequestMethod.POST)
	public String voteincome(@ModelAttribute("voteForm") VoteModel voteModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logger.debug("votemadel::::::"+voteModel);
		int incyyteId = Integer.parseInt(request.getParameter("incyyteId"));
		Logger.debug("Vote - incyyteId - " + incyyteId);
		User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
		if (user != null && user.getId() != null) {
			try {
				if (voteModel != null && voteModel.getSelectedAnswer() != null) {
					long selectedAnswerId = Long.parseLong(voteModel.getSelectedAnswer());
					Logger.debug("Vote - selectedAnswerId - " + selectedAnswerId);
					Long memberId = quickStartSrv.getPollMemberId(user.getEmail(), incyyteId);
					Logger.debug("Vote - memberId - " + memberId);
					InCyyteChart cyyteChart = null;
					boolean isShared = false;
					if (memberId == null) {
						Long sharedInCyyteID = quickStartSrv.checkPollShared(user.getEmail(), incyyteId);
						if (sharedInCyyteID != null) {
							isShared = true;
							cyyteChart = quickStartSrv.updateChart(incyyteId, sharedInCyyteID, selectedAnswerId, isShared);
						}
					} else
						cyyteChart = quickStartSrv.updateChart(incyyteId, memberId, selectedAnswerId, isShared);

					if (cyyteChart != null && cyyteChart.getIncyyte() != null) {
						cyyteChart.getIncyyte().getIncyyteCode();
						//reset incoming incyytes
						request.getSession().setAttribute("myIncomeinCyyte", null);
						request.getSession().removeAttribute("myIncomeinCyyte");
					}
				}
			} catch (ResponderException re) {
				//user already responded so ignored
			} catch (Exception e) {
				Logger.error("Exception:", e);
			}
		}
		return "redirect:dash.cyt";
	}

	@RequestMapping(value = "/voteregion.cyt", method = RequestMethod.POST)
	public String voteRegion(@ModelAttribute("voteForm") VoteModel voteModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int incyyteId = Integer.parseInt(request.getParameter("incyyteId"));
		Logger.debug("Vote - incyyteId - " + incyyteId);
		User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
		if (user != null && user.getId() != null) {
			try {
				if (voteModel != null && voteModel.getSelectedAnswer() != null) {
					long selectedAnswerId = Long.parseLong(voteModel.getSelectedAnswer());
					Logger.debug("Vote - selectedAnswerId - " + selectedAnswerId);

					InCyyteChart cyyteChart = quickStartSrv.updateRegionalChart(incyyteId, user.getId(), selectedAnswerId);
					if (cyyteChart != null && cyyteChart.getIncyyte() != null) {
						cyyteChart.getIncyyte().getIncyyteCode();
						//reset incoming incyytes
						request.getSession().setAttribute("myRegionInCyytes", null);
						request.getSession().removeAttribute("myRegionInCyytes");
					}
				}
			} catch (Exception e) {
				Logger.error("Exception:", e);
			}
		}
		return "redirect:dashregion.cyt";
	}

	@RequestMapping(value = "/votepoll.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String votepoll(@ModelAttribute("voteForm") VoteModel voteModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logger.info(" Inside votepoll controller");
		Logger.info("Vote - incyyteId - " + voteModel.getIncyyteId() + "  memberId - " + voteModel.getUserId() + "  answer - " + voteModel.getSelectedAnswer());
		User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
		if (user != null && user.getId() != null) {
			voteModel.setUserId(user.getId());
			voteModel.setGender(user.getGender());
			Logger.debug("votemodel" + voteModel);
			return updateIncyyteChart(voteModel);
		}
		request.getSession().setAttribute("voteModel", voteModel);
		Logger.debug("Vote Model from ViewUserPoll:" + request.getSession().getAttribute("voteModel"));
		return "notLogin";
	}

	@RequestMapping(value = "/voteNonMember.cyt", method = {RequestMethod.GET})
	public ModelAndView processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logger.info("inside voteNonMember.cyt");
		UserModel userModel = new UserModel();
		ModelAndView mav = new ModelAndView("main/VoteConfirmation");
		//Retrieve all params
		long incyyteId = Integer.parseInt(request.getParameter("incyyteId"));
		String memberIdParam = request.getParameter("memberId");
		int selectedAnswerId = Integer.parseInt(request.getParameter("selectedAnswerId"));
		String email = request.getParameter("email");
		int memberId;
		boolean isShared = false;

		if (memberIdParam.contains(Constants.SHARE)) {
			memberId = Integer.parseInt(memberIdParam.substring(3));
			isShared = true;
		} else
			memberId = Integer.parseInt(memberIdParam);
		//If New member, Signup
		boolean exists = false;
		try {
			exists = registrationSrv.emailExists(email);
		} catch (Exception e) {
			Logger.error("Exception:", e);
		}
		InCyyte cyyte = null;
		User userCreated = null;

		if (!exists) {
			//New signup - first time round
			userModel.setSu_email(email);

			User user = registrationSrv.signUpUser(MapUserProperty.copyUserSignUpDetails(userModel, request));
			Logger.debug("user::::" + user);
			userCreated = loginSrv.getUserDetails(userModel.getSu_email(), Constants.GET_BY_MAIL);
			Logger.debug("userCreated::::" + userCreated);
			if (null != userCreated) {
				//Place VOTE
				cyyte = quickStartSrv.initChart(incyyteId);
				placeVote( mav,  isShared, incyyteId,  memberId,  selectedAnswerId,  cyyte);
			}
		} else {// Place VOTE
			userCreated = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
			cyyte = quickStartSrv.initChart(incyyteId);
			placeVote( mav,  isShared, incyyteId,  memberId,  selectedAnswerId,  cyyte);
		}
		if (cyyte != null) {
			Logger.debug("incyyteCode  - " + cyyte);
			mav.addObject("activationCode", userCreated.getActivationCode());
			mav.addObject("email", email);
			mav.addObject("incyyteCode", cyyte.getIncyyteCode());
			request.getSession().setAttribute(SessionKeys.INCYYTE_CODE, cyyte.getIncyyteCode());
			request.getSession().setAttribute(SessionKeys.INCYYTE, cyyte);

			IncyyteModel incyyteModel = new IncyyteModel();
			// command object
			mav.addObject("inCyyteForm", incyyteModel);
		} else {
			Logger.debug("systemError");
			mav = new ModelAndView("systemError");
		}
		Logger.debug("mavmav::" + mav);
		return mav;
	}

	private synchronized void placeVote(ModelAndView mav, boolean isShared,long incyyteId, int memberId, int selectedAnswerId, InCyyte cyyte)throws Exception{
		if ((isShared && quickStartSrv.isSharedContactResponded(incyyteId, memberId)) || quickStartSrv.isContactResponded(incyyteId, memberId)) {
			mav.addObject("voteMsg", "Sorry ... You have already voted!");
		} else {
			Date closureDate = null;
			Calendar currentDate = null;

			if (cyyte != null && cyyte.getClosureDate() != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				closureDate = format.parse(cyyte.getClosureDate());
				currentDate = Calendar.getInstance();
				currentDate.setTime(new Date());
			}

			if (closureDate != null && closureDate.before(currentDate.getTime())) {
				mav.addObject("voteMsg", "Sorry ... The poll is now expired!");
			} else {
				mav.addObject("voteMsg", "Thanks for voting");
				InCyyteChart cyyteChart = quickStartSrv.updateChart(incyyteId, memberId, selectedAnswerId, isShared);
				cyyte = cyyteChart.getIncyyte();
			}
		}

	}

	@RequestMapping(value = "/vote.cyt", method = RequestMethod.GET)
	public ModelAndView initForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logger.info("inside vote.cyt");
		ModelAndView mav = new ModelAndView("main/VoteConfirmation");
		long incyyteId = Integer.parseInt(request.getParameter("incyyteId"));
		String memberIdParam = request.getParameter("memberId");
		int selectedAnswerId = Integer.parseInt(request.getParameter("selectedAnswerId"));
		String activationCode = request.getParameter("actcode");
		String email = request.getParameter("email");
		int memberId;
		boolean isShared = false;

		if (memberIdParam.contains(Constants.SHARE)) {
			memberId = Integer.parseInt(memberIdParam.substring(3));
			isShared = true;
		} else
			memberId = Integer.parseInt(memberIdParam);

		InCyyte cyyte = quickStartSrv.initChart(incyyteId);
		placeVote( mav,  isShared, incyyteId,  memberId,  selectedAnswerId,  cyyte);

		if (cyyte != null) {
			Logger.debug("incyyteCode  - " + cyyte);
			mav.addObject("activationCode", activationCode);
			mav.addObject("email", email);
			mav.addObject("incyyteCode", cyyte.getIncyyteCode());
			request.getSession().setAttribute(SessionKeys.INCYYTE_CODE, cyyte.getIncyyteCode());
			request.getSession().setAttribute(SessionKeys.INCYYTE, cyyte);

			IncyyteModel incyyteModel = new IncyyteModel();
			// command object
			mav.addObject("inCyyteForm", incyyteModel);
		} else {
			mav = new ModelAndView("systemError");
		}
		Logger.debug("mavmav::" + mav);
		return mav;
	}

	@RequestMapping(value = "/regionemailvote.cyt", method = RequestMethod.GET)
	public ModelAndView regionalEmailVote(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logger.info("inside regionemailvote.cyt");
		ModelAndView mav = new ModelAndView("main/VoteConfirmation");
		long incyyteId = Integer.parseInt(request.getParameter("incyyteId"));
		int userId = Integer.parseInt(request.getParameter("memberId"));
		int selectedAnswerId = Integer.parseInt(request.getParameter("selectedAnswerId"));
		String activationCode = request.getParameter("actcode");
		String email = request.getParameter("email");

		InCyyte cyyte = quickStartSrv.initChart(incyyteId);
		if (quickStartSrv.isRegionalContactResponded(incyyteId, userId)) {
			mav.addObject("voteMsg", "Sorry ... You have already voted!");
		} else {
			Date closureDate = null;
			Calendar currentDate = null;

			if (cyyte != null && cyyte.getClosureDate() != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				closureDate = format.parse(cyyte.getClosureDate());
				currentDate = Calendar.getInstance();
				currentDate.setTime(new Date());
			}

			if (closureDate != null && closureDate.before(currentDate.getTime())) {
				mav.addObject("voteMsg", "Sorry ... The poll is now expired!");
			} else {
				mav.addObject("voteMsg", "Thanks for voting");
				InCyyteChart cyyteChart = quickStartSrv.updateRegionalChart(incyyteId, userId, selectedAnswerId);
				cyyte = cyyteChart.getIncyyte();
			}
		}

		if (cyyte != null) {
			Logger.debug("incyyteCode  - " + cyyte.getIncyyteCode());
			mav.addObject("activationCode", activationCode);
			mav.addObject("email", email);
			mav.addObject("incyyteCode", cyyte.getIncyyteCode());
			request.getSession().setAttribute(SessionKeys.INCYYTE_CODE, cyyte.getIncyyteCode());
			request.getSession().setAttribute(SessionKeys.INCYYTE, cyyte);

			IncyyteModel incyyteModel = new IncyyteModel();
			// command object
			mav.addObject("inCyyteForm", incyyteModel);
		} else {
			mav = new ModelAndView("systemError");
		}
		return mav;
	}

	/*@RequestMapping(params = "enter", method = RequestMethod.POST)
     public ModelAndView processCreate(HttpServletRequest request,
             @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
             BindingResult result, SessionStatus status, HttpSession session) {
         Map<Object, Object> model = new HashMap<Object, Object>();
         session.setAttribute(SessionKeys.INCYYTE_MODEL, incyyteModel);
         model.put("inCyyteForm", incyyteModel);
         model.put("categories",referenceData.getInCyyteCategories());
         // return form success view
         return new ModelAndView("/create_question", (Map)model);
     }*/

	@RequestMapping(value = "/votelogin.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String processAjaxLogin(@ModelAttribute("loginForm") UserModel userModel, HttpServletRequest request, HttpSession session) throws Exception {
		String email = userModel.getLogin_email();
		String password = userModel.getLogin_pwd();
		Logger.debug("usermodel" + userModel);
		Logger.debug("Login Email - " + email + "  password - " + password);
		try {
			Logger.debug("getLoginemail" + userModel);
			if (StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(password)) {
				User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
				if (user != null && user.getStatus().equals("DEACTIVATED")) {
					Logger.debug("Account is Deactivated");
					throw new AuthenticationException(ExceptionMessages.LI_DEACTIVED_ACCT_MSG);
				} else if (user != null && user.getStatus().equals("NON_ACTIVE")) {
					Logger.debug("Account is NON_ACTIVE");
					throw new AuthenticationException(ExceptionMessages.LI_INACTIVE_ACT_MSG);
				}
				User authenticatedUser =loginSrv.authenticateUserLogin(email, password);
				if (authenticatedUser != null) {
					Logger.debug("user::" + user);
					session.setAttribute(SessionKeys.LOGIN_USER, user);
					if (null != user && user.getResetPwdFlag().equalsIgnoreCase("N")) {
						Logger.debug("Session Id before - " + session.getId());
						session = InCyyteSecurity.getInstance().changeSessionIdentifier(request);
						Logger.debug("Session Id after - " + session.getId());
						session.setAttribute(SessionKeys.LOGIN_USER, user);
						VoteModel voteModel = (VoteModel) session.getAttribute("voteModel");
						voteModel.setUserId(user.getId());
						return updateIncyyteChart(voteModel);
					}
				}
			}
		} catch (AuthenticationException e) {
			Logger.error("authenticateUserLogin: Failed ", e);
			return "notLogin" + e.getMessage();
		}
		session.removeAttribute("user");
		// return form success view
		return "notLogin";
	}

	@RequestMapping(value = "/filteredvotelogin.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String filteredprocessAjaxLogin(@ModelAttribute("loginForm") UserModel userModel, HttpServletRequest request, HttpSession session) throws Exception {
		String email = userModel.getLogin_email();
		String password = userModel.getLogin_pwd();
		Logger.debug("usermodel" + userModel);
		Logger.debug("Login Email - " + email + "  password - ");
		String questionId = request.getParameter("qid");
		String selectedAns = request.getParameter("ans");
		Logger.debug("question::" + questionId);
		Logger.debug("selectedAns::" + selectedAns);
		try {
			User user = loginSrv.getUserDetails(email, Constants.GET_BY_MAIL);
			if (user != null && user.getStatus().equals("DEACTIVATED")) {
				Logger.debug("Account is Deactivated");
				throw new AuthenticationException(ExceptionMessages.LI_DEACTIVED_ACCT_MSG);
			} else if (user != null && user.getStatus().equals("NON_ACTIVE")) {
				Logger.debug("Account is NON_ACTIVE");
				throw new AuthenticationException(ExceptionMessages.LI_INACTIVE_ACT_MSG);
			}
			User authenticatedUser =loginSrv.authenticateUserLogin(email, password);
			if (authenticatedUser != null) {
				Logger.debug("user::" + user);
				session.setAttribute(SessionKeys.LOGIN_USER, user);
				if (null != user && user.getResetPwdFlag().equalsIgnoreCase("N")) {
					Logger.debug("Session Id before - " + session.getId());
					session = InCyyteSecurity.getInstance().changeSessionIdentifier(request);
					Logger.debug("Session Id after - " + session.getId());
					session.setAttribute(SessionKeys.LOGIN_USER, user);
					return inCyyteService.processPublicPoll(user, questionId, Long.valueOf(selectedAns));
				}
			}
		} catch (AuthenticationException e) {
			Logger.error("authenticateUserLogin: Failed ", e);
			return "notLogin" + e.getMessage();
		}
		session.removeAttribute("user");
		// return form success view
		return "notLogin";
	}

	@RequestMapping(value = "/filteredvoteloggedinUser.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String filteredvoteloggedinUser(@ModelAttribute("voteForm") VoteModel voteModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		Logger.info("Process the filteredvoteloggedinUser");
		try {
			User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
			if (user == null || user.getId() == null) {
				return "notLogin";
			}

			Logger.debug(" Inside votepoll controller");
			Logger.info("VoteModel - " + voteModel);
			voteModel.setUserId(user.getId());
			voteModel.setGender(user.getGender());
			Logger.info("votemodel" + voteModel);
        	return inCyyteService.processPublicPoll(user, String.valueOf(voteModel.getIncyyteId()), Long.valueOf(voteModel.getSelectedAnswer()));
		}
		catch (AuthenticationException e) {
			Logger.error("authenticateUserLogin: Failed ", e);
			return "notLogin" + e.getMessage();
		}
	}



	@RequestMapping(value = "/singlePagevoteloggedinUser.cyt", method = {RequestMethod.POST})
	@ResponseBody
	public String singlePageVoteLoggedinUser(@ModelAttribute("voteForm") VoteModel voteModel, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		Logger.info("Process the singlePagevoteloggedinUser");
		try {
			User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
			Logger.info("User::" + user);
			if (user == null || user.getId() == null) {
				return "notLogin";
			}
			String incyyteId = StringUtils.defaultString(request.getParameter("incyyteId"));
			String ansId = StringUtils.defaultString(request.getParameter("ansId"));
			Logger.info(" Inside votepoll controller");
			Logger.info("VoteModel - " + voteModel);
			voteModel.setUserId(user.getId());
			voteModel.setGender(user.getGender());
			voteModel.setIncyyteId(Long.valueOf(incyyteId));
			voteModel.setSelectedAnswer(ansId);

			Logger.info("votemodel" + voteModel);
			return inCyyteService.processPublicPoll(user, String.valueOf(voteModel.getIncyyteId()), Long.valueOf(voteModel.getSelectedAnswer()));
		} catch (Exception e) {
			Logger.error("singlePagevoteloggedinUser: Failed ", e);
			return "notLogin" + e.getMessage();
		}
	}


	@RequestMapping(value = "/fpcreateAcctPollPage.cyt", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public String processSubmit(HttpServletRequest request, @ModelAttribute("signUpForm") UserModel userModel, BindingResult result, SessionStatus status, HttpSession session) {
		Logger.info("Inside filter create account page controller " );
		Logger.debug("userModel::" + userModel);
		String questionId = request.getParameter("qid");
		String selectedAns = request.getParameter("ans");
		try {
			//Logger.debug("Username: " + userModel);
			boolean exists = false;
			try {
				exists = registrationSrv.emailExists(userModel.getSu_email());
			} catch (Exception e) {
				Logger.error("Exception:", e);
			}

			if (!exists) {
				//signup the user first
				User user = registrationSrv.signUpUser(MapUserProperty.copyUserSignUpDetails(userModel, request));
				Logger.debug("user::::" + user);
				User userCreated = loginSrv.getUserDetails(userModel.getSu_email(), Constants.GET_BY_MAIL);
				Logger.debug("userCreated::::" + userCreated);
				if (userCreated != null && userCreated.getId() != null) {

					inCyyteService.processPublicPoll(user, questionId, Long.valueOf(selectedAns));
					Logger.debug("Success data");
					return "success";
				}
			} else {
				return "userAlreadyExist";
			}
		} catch (Exception e) {
			Logger.error("Exception:", e);

		}
		Logger.info("outside createAcct.cyt");
		return "failure";
	}



	@RequestMapping(value = "/votepassword.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String processForgotPassword(@ModelAttribute("loginForm") UserModel userModel) {
		try {
			User user = loginSrv.getUserDetails(userModel.getUser_email(), Constants.GET_BY_MAIL);
			if (user != null && user.getEmail() != null) {
				forgotPwdSrv.requestNewPassword(userModel.getUser_email());
				return "success";
			}
		} catch (Exception e) {
			Logger.error("Exception:", e);
		}
		// return form success view
		return "failure";
	}

	private String updateIncyyteChart(VoteModel voteModel) {
		try {
			Logger.debug("Inside updateIncyyteChart method");
			long incyyteId = voteModel.getIncyyteId();
			long memberId = voteModel.getUserId();
			int selectedAnswerId = Integer.parseInt(voteModel.getSelectedAnswer());
			String gender = voteModel.getGender();
			String ageGroup = voteModel.getAgeGroup();
			Date responseDate = voteModel.getResponseDate();
			Logger.info("vote result controller:::responseDate######" + responseDate + "incyyteId::" + incyyteId);

			InCyyte cyyte = quickStartSrv.initChart(incyyteId);
			if (quickStartSrv.isContactResponded(incyyteId, memberId)) {
				return "<span>Oops!</span> Sorry you have already voted on this poll.";
			} else {
				Date closureDate = null;
				Calendar currentDate = null;
				if (cyyte != null && cyyte.getClosureDate() != null) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					closureDate = format.parse(cyyte.getClosureDate());
					currentDate = Calendar.getInstance();
					currentDate.setTime(new Date());
				}
				if (closureDate != null && closureDate.before(currentDate.getTime())) {
					return "Sorry ... The poll is now expired!";
				} else {
					InCyyteChart cyyteChart = quickStartSrv.updatePostedChart(incyyteId, memberId, selectedAnswerId, gender, ageGroup, responseDate);
					cyyte = cyyteChart.getIncyyte();
					Logger.debug("$$$$$$4voteresult controller::::cyyte$$$$$" + cyyteChart);
					return "<span>Hooray!</span> Thanks for voting";
				}
			}
		} catch (ResponderException Re) {
			Logger.debug("ResponderException::User already voted. " + voteModel);
			return "<span>Oops!</span> Sorry you have already voted on this poll.";
		} catch (Exception e) {
			Logger.error("Exception:", e);
		}
		return "failure";
	}
}