/**
 *
 */
package com.incyyte.app.web.controller.dashboard;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.InCyyteExceptions;
import com.incyyte.app.service.*;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.IncyyteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author RemiOseni
 */
@Controller
public class DashPostIncyyteController extends DashboardBasePage implements Constants {

    private User user;

    @Autowired
    private RefData referenceData;

    @Autowired
    private QuickStartService quickStartSrv;

    @Autowired
    private HomeService homeSrv;

    @Autowired
    private UserSettingsService userSettingsService;

    @Autowired
    private UserPollService userPollSrv;
    @Autowired
    private RegistrationService registrationSrv;

    //@SuppressWarnings("unchecked")
    @RequestMapping(value = "/dashpost.cyt", method = RequestMethod.GET)
    public String initPostForm(ModelMap model, HttpServletRequest request) throws Exception {

        user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER);
        String activationCode = request.getParameter("actcode");
        String email = request.getParameter("email");
        if (user != null && activationCode != null) {
            user = registrationSrv.getUserDetailsByEmailAndCode(email, activationCode);
            request.getSession().setAttribute(SessionKeys.LOGIN_USER, user);
        }

        if (user == null || user.getId() == null) {
            Logger.debug("%%%%%%%%%%%% Error: user not in session - return to login page");
            return "redirect:login.cyt";
        }

        model.put("detailsform", getMandatoryInfoModel(user));

        ModelMap map = checkMandatoryFields(user);
        if (map != null) {
            model.addAllAttributes(map);
            return "dashboard/mandatory_info";
        }

        int page = 1;
        String param = "";
        String search = "";
        int recordsPerPage = referenceData.getPageLimit();

        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        if (request.getParameter("search") != null)
            search = request.getParameter("search");

        if (request.getParameter("param") != null)
            param = request.getParameter("param");

        List<InCyyte> myInCyytes = getMyPollInCyytes((page - 1) * recordsPerPage, recordsPerPage, param, search);

        String userDomainPageName = userSettingsService.getUniquePageName(user.getId());
        if (userDomainPageName == null || userDomainPageName.isEmpty()) {
            userDomainPageName = user.getUsername();
        }

        //fetching the shared poll info from database
        String sendType = "post";
        List<InCyyte> mySharedPollPages = userPollSrv.getMyPollPageShareContacts(user, sendType);
        Logger.debug("mysharePollPage::::::::::::::::" + mySharedPollPages);
        Logger.debug("myPollPages::::::::::::::::" + myInCyytes);

        model.put("userDomainPageName", userDomainPageName);
        model.put("myPollPages", myInCyytes);
        model.put("mySharedPollPages", mySharedPollPages);
        model.put("page", "post");
        model.put("inCyyteForm", new IncyyteModel());
        model.addAttribute("currentPage", page);
        model.addAttribute("text", param);
        return "dashboard/dashboard";
    }

    public List<InCyyte> getMyPollInCyytes(int offset, int recordsPerPage, String param, String criteria) {
        try {
            List<InCyyte> incyytes = quickStartSrv.getUserInCyytesByUserId(user.getId(), SEND_BY_POST, offset, recordsPerPage, param, criteria);
            getPollCount(incyytes);
            return incyytes;
        } catch (InCyyteExceptions e) {
            Logger.error("Exception:", e);
        }
        return null;
    }

    @RequestMapping(value = "/publishPoll.cyt", method = RequestMethod.GET)
    public String publishPoll(ModelMap model, HttpServletRequest request) {
        String code = request.getParameter("code");
        if (code != null) {
            homeSrv.updatePublishPoll("Y", Long.valueOf(code));
        }
        return "redirect:dashpost.cyt";
    }

    @RequestMapping(value = "/unpublishPoll.cyt", method = RequestMethod.GET)
    public String unPublishPoll(ModelMap model, HttpServletRequest request) {
        String code = request.getParameter("code");
        if (code != null) {
            homeSrv.updatePublishPoll("N", Long.valueOf(code));
        }
        return "redirect:dashpost.cyt";
    }

    @RequestMapping(value = "/previewpoll.cyt", method = RequestMethod.GET)
    public String previewPoll(ModelMap model, HttpServletRequest request) {
        String code = request.getParameter("code");
        if (code != null) {
            InCyyte cyyte = quickStartSrv.initChart(Long.valueOf(code));
            model.addAttribute("myPollPage", cyyte);
            return "dashboard/dash_my_preview_poll";
        }
        return "redirect:dashpost.cyt";
    }
}