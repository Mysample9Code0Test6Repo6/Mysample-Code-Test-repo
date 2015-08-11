/**
 * 
 */
package com.incyyte.app.web.controller.dashboard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.HomeService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.UserContactModel;

/**
 * @author RemiOseni
 *
 */
@Controller
public class DashSentIncyyteController extends DashboardBasePage{

	//final static int OFFSET = 10;

	@Autowired
	private HomeService homeSrv;

	private User user;

	@Autowired
	private RefData referenceData;

	//@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dashsent.cyt", method = RequestMethod.GET)
	public String initSentForm(ModelMap model,HttpSession session, HttpServletRequest request) {	
		UserContactModel userModel = (UserContactModel) request.getSession().getAttribute(SessionKeys.POLL_MESSAGE_FORM);
		if(userModel != null){
			userModel.setPollMessageContent(null);
			userModel.setUploadedFileLocation(null);
			userModel.setChecked(null);
			session.setAttribute(SessionKeys.POLL_MESSAGE_FORM, userModel);
		}

		user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);

		if (user == null || user.getId() == null) {
			Logger.debug("%%%%%%%%%%%% Error: user not in session - return to login page");
			return "redirect:welcome.cyt";
		}
		model.put("detailsform",getMandatoryInfoModel(user));

		ModelMap map = checkMandatoryFields(user);
		if(map != null){
			model.addAllAttributes(map);
			return "dashboard/mandatory_info";
		}

		int page = 1;
		String param = null;
		String search = "";
		int recordsPerPage = referenceData.getPageLimit();

		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		if (request.getParameter("search") != null)
			search = request.getParameter("search");

		if (request.getParameter("param") != null)
			param = request.getParameter("param");

		if(param == null)
			param = search;

		List<InCyyteChart> myGroupInCyytes =  new ArrayList<InCyyteChart>();
		String sendType = "mail";
		myGroupInCyytes = getMyInCyytes((page - 1) * recordsPerPage, recordsPerPage, param, search, sendType);
		model.put("myGroupSentInCyytes", myGroupInCyytes);

		sendType = "area";
		List<InCyyteChart> myRegionSentInCyytes =  new ArrayList<InCyyteChart>();
		myRegionSentInCyytes = getMyInCyytes((page - 1) * recordsPerPage, recordsPerPage, param, search, sendType);
		model.put("myRegionSentInCyytes", myRegionSentInCyytes);

		model.put("page", "sent");
		model.put("inCyyteForm", new IncyyteModel());

		model.addAttribute("currentPage", page);
		model.addAttribute("text", param);

		return "dashboard/dashboard";
	}

	@RequestMapping(value = "/displaySentIncyyte", method = RequestMethod.GET)
	public String displaySentIncyyte(ModelMap model, HttpSession session) throws Exception {        
		return "dashboard/dash_load_sent";
	}

	public List<InCyyteChart> getMyInCyytes(int offset, int recordsPerPage, String param, String criteria, String sendType) {

		try {
			List<InCyyteChart> incyytes = homeSrv.getMyInCyytes(user.getEmail(), offset, recordsPerPage, param, criteria, sendType);
			List<InCyyteChart> myInCyytes = getSentCount(incyytes);
			return myInCyytes;
		} catch (Exception e) {
			Logger.error("Exception:", e);
		}
		return null;
	}

	/*@SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value = "/loadSentIncyyte", method = RequestMethod.POST)
    @ResponseBody
    public String loadSentIncyyte(
            @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
            BindingResult result, SessionStatus status, HttpServletRequest request) {
        try {

            logger.info("$$$$$$ loadSentIncyyte $$$$$$");
            HttpSession session = request.getSession();

            user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
            if (user != null && user.getId() != null) {

                List<InCyyteChart> incyytes = homeSrv.getMyInCyytes(user.getEmail(), OFFSET);
                List<InCyyteChart> myInCyytes = getSentCount(incyytes);
                //List<InCyyteChart> myInCyytes = getMyInCyytes(session);
                List<InCyyteChart> cyytes = new ArrayList<InCyyteChart>();

                int max = OFFSET;

                if (session.getAttribute("SENT_ROW_MAX") != null) {
                    max = (Integer) session.getAttribute("SENT_ROW_MAX");
                }

                if (myInCyytes != null && myInCyytes.size() > max) {


                    int rownum = 2;
                    if (session.getAttribute("SENT_ROW_NUM") != null) {
                        rownum = (Integer) session.getAttribute("SENT_ROW_NUM");
                    }

                    max = rownum * OFFSET;

                    logger.info("$$$$$$ loadSentIncyyte - user");

                    if (myInCyytes.size() > OFFSET) {

                        int min = max - OFFSET;

                        logger.info("$$$$$$ loadSentIncyyte - myInCyytes.size() > OFFSET - " + myInCyytes.size());
                        if (myInCyytes.size() <= max) max = myInCyytes.size();

                        if (min <= OFFSET) min = OFFSET;

                        logger.info("$$$$$$ loadSentIncyyte - min - " + min + "  max - " + max);

                        for (int x = min; x < max; x++) {
                            cyytes.add(myInCyytes.get(x));
                        }
                        session.setAttribute("myLoadSentInCyytes", cyytes);
                        session.setAttribute("SENT_ROW_NUM", ++rownum);
                        session.setAttribute("SENT_ROW_MAX", max);

                        List<InCyyteChart> mySentInCyytes = (ArrayList) session.getAttribute("mySentInCyytes");
                        if (mySentInCyytes == null) mySentInCyytes = new ArrayList<InCyyteChart>();

                        mySentInCyytes.addAll(cyytes);
                        session.setAttribute("mySentInCyytes", mySentInCyytes);

                        return "success";
                    }
                }
            }

        } catch (Exception e) {
            Logger.error(e);
        }
        return "failure";
    }*/


}
