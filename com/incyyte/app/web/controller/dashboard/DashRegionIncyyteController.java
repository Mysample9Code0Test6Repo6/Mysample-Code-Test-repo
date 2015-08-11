package com.incyyte.app.web.controller.dashboard;

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
import com.incyyte.app.web.model.VoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RemiOseni
 */
@Controller
public class DashRegionIncyyteController extends DashboardBasePage implements Constants {

    @Autowired
    private RefData referenceData;

    @Autowired
    private QuickStartService quickStartSrv;

    @Autowired
    private HomeService homeSrv;

    private User user;

    @RequestMapping(value = "/dashregion.cyt", method = RequestMethod.GET)
    public String initRegionForm(ModelMap model, HttpServletRequest request, HttpSession session) {
        user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
        if (user == null || user.getId() == null) {
            Logger.debug("Error: user not in session - return to login page");
            return "redirect:welcome.cyt";
        }

        ModelMap map = checkMandatoryFields(user);
        if (map != null) {
            model.put("detailsform", getMandatoryInfoModel(user));
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

        //Code for displaying pagination
        try {
            int countOfNewIncyytes = homeSrv.getCountOfIncyytes(user.getEmail(), Constants.INCYYTE_REGIONS_ALL);
            int noOfPages = (int) Math.ceil(countOfNewIncyytes * 1.0 / recordsPerPage);
            session.setAttribute(SessionKeys.MY_REGION_NO_OF_PAGE, noOfPages);
        } catch (Exception e) {
            Logger.error("Fetching NewIncyytes count : Failed ", e);
        }

        List<InCyyteChart> myRegionInCyytes = getRegionInCyytes((page - 1) * recordsPerPage, recordsPerPage, param, search);
        model.put("regionIncyytes", myRegionInCyytes);
        model.put("page", "region");
        model.put("inCyyteForm", new IncyyteModel());
        model.put("voteForm", new VoteModel());
        model.put("userId", user.getId());
        model.addAttribute("currentPage", page);
        model.addAttribute("text", param);
        return "dashboard/dashboard";
    }

    public List<InCyyteChart> getRegionInCyytes(int offset, int recordsPerPage, String param, String criteria) {
        List<InCyyteChart> chart = null;
        try {
            List<InCyyteChart> incyytes = homeSrv.getAllRegionalInCyyteResults(user.getEmail(), offset, recordsPerPage, param, criteria);

            if (incyytes != null && !incyytes.isEmpty()) {
                chart = checkRegionVote(incyytes, user.getId(), user.getEmail());
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return chart;
    }

    @RequestMapping(value = "/deleteRegionIncyyte.cyt", method = RequestMethod.GET)
    @ResponseBody
    public String deleteRegionIncyyteForm(ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        if (user != null && user.getId() != null) {
            //TODO - need delete function
            quickStartSrv.insertDeletedIncyyte(Integer.valueOf(code), user.getId().intValue());
            session.setAttribute("myRegionInCyytes", null);
            session.removeAttribute("myRegionInCyytes");
        }
        return "success";
    }

    @RequestMapping(value = "/displayRegionIncyyte", method = RequestMethod.GET)
    public String displayRegionIncyyte(ModelMap model, HttpSession session) throws Exception {

        model.put("page", "region");
        model.put("inCyyteForm", new IncyyteModel());
        MandatoryInfoModel infoModel = new MandatoryInfoModel();

        if (user.getBirthYear() != null && !user.getBirthYear().equals(0))
            infoModel.setBirthYear(user.getBirthYear().toString());

        infoModel.setCountryCode(user.getCountryCode());
        infoModel.setGender(user.getGender());
        infoModel.setPostalCodeArea(user.getPostalCodeArea());
        infoModel.setUserId(user.getId());
        model.put("detailsform", infoModel);
        model.put("voteForm", new VoteModel());
        model.put("userId", user.getId());
        return "dashboard/dash_load_region";
    }

    @RequestMapping(value = "/loadMyRegionPolls", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> loadMyRegionPolls(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
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

        List<InCyyteChart> myRegionInCyytes = getRegionInCyytes((page - 1) * recordsPerPage, recordsPerPage, param, search);
        if (myRegionInCyytes == null || myRegionInCyytes.size() == 0) {
            modelMap.put("endOfFile", "TRUE");
        } else {
            modelMap.put("endOfFile", "FALSE");
        }
        List<Map<String, Object>> regionPolls = new ArrayList<Map<String, Object>>();
        if (myRegionInCyytes != null) {
            for (InCyyteChart regionInCyyte : myRegionInCyytes) {
                regionPolls.add(regionInCyyte.toJSONMap());
            }
            modelMap.put("regionPolls", regionPolls);
        } else {
            modelMap.put("regionPolls", "");
        }
        return modelMap;
    }
}