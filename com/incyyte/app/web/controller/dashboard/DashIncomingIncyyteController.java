/**
 *
 */
package com.incyyte.app.web.controller.dashboard;

import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.HomeService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.MandatoryInfoModel;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.VoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author RemiOseni
 */
@Controller
public class DashIncomingIncyyteController extends DashboardBasePage {

    final static int OFFSET = 10;

    @Autowired
    private HomeService homeSrv;

    @Autowired
    private QuickStartService quickStartSrv;

    private User user;

    @Autowired
    private RefData referenceData;

    @Autowired
    private UserDao userDao;

    //@SuppressWarnings("unchecked")
    @RequestMapping(value = "/dashincomming.cyt", method = RequestMethod.GET)
    public String dashincomming(ModelMap model, HttpSession session, HttpServletRequest request) {
        UserContactModel userModel = (UserContactModel) request.getSession().getAttribute(SessionKeys.POLL_MESSAGE_FORM);
        if (userModel != null) {
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

        if (param == null)
            param = search;
         boolean showArchivedPolls = Boolean.valueOf(request.getParameter("flag"));
        //Code for displaying pagination
        try {
            List<InCyyte> totalIncyytes = userDao.getUserInCyytes(user.getEmail(), Constants.INCYYTE_RESULTS, showArchivedPolls);
            int countOfNewIncyytes = totalIncyytes.size();
            Logger.debug("countOfNewIncyytes::"+countOfNewIncyytes);
            int noOfPages = (int) Math.ceil(countOfNewIncyytes * 1.0 / recordsPerPage);
            session.setAttribute(SessionKeys.INCOME_NO_OF_PAGE, noOfPages);
            //Get the Non voted polls count
            List<Long> votedInCyytes = quickStartSrv.getAlreadyVotedPolls(user.getEmail());
            int nonVoteCount = 0;
            for (InCyyte incyyte : totalIncyytes) {
            	if (!votedInCyytes.contains(incyyte.getId())) {
            		nonVoteCount++;
            	}
            }
            session.setAttribute("incomeCount" , nonVoteCount);
            Logger.info("nonVoteCount::" + nonVoteCount);
            
        } catch (Exception e) {
            Logger.error("Fetching NewIncyytes count : Failed ", e);
        }
        
        List<InCyyteChart> myInCyytes = getInCyyteResults((page - 1) * recordsPerPage, recordsPerPage, param, search, showArchivedPolls);
        model.put("showArchivedPolls", (showArchivedPolls?"true":"false"));
        model.put("inCyyteResults", myInCyytes);
        model.put("page", "incomming");
        model.put("inCyyteForm", new IncyyteModel());
        model.put("voteForm", new VoteModel());
        model.put("userId", user.getId());
        model.addAttribute("currentPage", page);
        model.addAttribute("text", param);
        return "dashboard/dashboard";
    }

    public List<InCyyteChart> getInCyyteResults(int offset, int recordsPerPage, String param, String criteria, boolean showArchivedPolls) {
        List<InCyyteChart> chart = null;
        try {
            List<InCyyteChart> incyytes = homeSrv.getInCyyteResults(user.getEmail(), offset, recordsPerPage, param, criteria, showArchivedPolls);
            if (incyytes != null && !incyytes.isEmpty()) {
                chart = checkIfAlreadyVoted(incyytes, user.getId(), user.getEmail());
            }
        } catch (Exception e) {
            Logger.error(e);
        }
        return chart;
    }

    @RequestMapping(value = "/deleteincommingincyyte.cyt", method = RequestMethod.GET)
    @ResponseBody
    public String deleteIncomeIncyyteForm(ModelMap model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        if (user != null && user.getId() != null) {
            quickStartSrv.insertDeletedIncyyte(Integer.valueOf(code), user.getId().intValue());
            session.setAttribute("myIncomeinCyyte", null);
            session.removeAttribute("myIncomeinCyyte");
        }
        return "success";
    }

    @RequestMapping(value = "/displayIncommingIncyyte", method = RequestMethod.GET)
    public String displayIncommingIncyyte(ModelMap model, HttpSession session) throws Exception {

        model.put("page", "incomming");
        model.put("inCyyteForm", new IncyyteModel());
        model.put("detailsform", new MandatoryInfoModel());
        model.put("voteForm", new VoteModel());
        model.put("userId", user.getId());
        return "dashboard/dash_load_income";
    }

    /*@SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value = "/loadIncommingIncyyte", method = RequestMethod.POST)
    @ResponseBody
    public String loadIncommingIncyyte(
            @ModelAttribute("inCyyteForm") IncyyteModel incyyteModel,
            BindingResult result, SessionStatus status, HttpServletRequest request) {
        try {

            logger.info("$$$$$$ loadIncommingIncyyte $$$$$$");
            HttpSession session = request.getSession();

            user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
            if (user != null && user.getId() != null) {

                List<InCyyteChart> incyytes = homeSrv.getInCyyteResults(user.getEmail(), OFFSET);
                List<InCyyteChart> myInCyytes = checkIfAlreadyVoted(incyytes, user.getId(), user.getEmail());
                //List<InCyyteChart> myInCyytes = getInCyyteResults(session);
                List<InCyyteChart> cyytes = new ArrayList<InCyyteChart>();

                int max = OFFSET;

                if (session.getAttribute("INCOME_ROW_MAX") != null) {
                    max = (Integer) session.getAttribute("INCOME_ROW_MAX");
                }

                if (myInCyytes != null && myInCyytes.size() > max) {

                    int rownum = 2;
                    if (session.getAttribute("INCOME_ROW_NUM") != null) {
                        rownum = (Integer) session.getAttribute("INCOME_ROW_NUM");
                    }

                    max = rownum * OFFSET;

                    logger.info("$$$$$$ loadIncommingIncyyte - user");

                    if (myInCyytes.size() > OFFSET) {

                        int min = max - OFFSET;

                        logger.info("$$$$$$ loadIncommingIncyyte - myInCyytes.size() > OFFSET - " + myInCyytes.size());
                        if (myInCyytes.size() <= max) max = myInCyytes.size();

                        if (min <= OFFSET) min = OFFSET;

                        logger.info("$$$$$$ loadIncommingIncyyte - min - " + min + "  max - " + max);

                        for (int x = min; x < max; x++) {
                            cyytes.add(myInCyytes.get(x));
                        }
                        session.setAttribute("myLoadIncomeinCyyte", cyytes);
                        session.setAttribute("INCOME_ROW_NUM", ++rownum);
                        session.setAttribute("INCOME_ROW_MAX", max);

                        List<InCyyteChart> myIncomeinCyyte = (ArrayList) session.getAttribute("myIncomeinCyyte");
                        if (myIncomeinCyyte == null) myIncomeinCyyte = new ArrayList<InCyyteChart>();

                        myIncomeinCyyte.addAll(cyytes);
                        session.setAttribute("myIncomeinCyyte", myIncomeinCyyte);

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
