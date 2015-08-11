package com.incyyte.app.web.controller.admin;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.AdminService;
import com.incyyte.app.service.QuestionService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.QuestionEnumType;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.controller.BaseContoller;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.UserModel;
import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
public class AdminPageController extends BaseContoller {

    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());

    private User user;
    @Autowired
    private QuestionService questionSrv;

    @Autowired
    private AdminService adminServiceImpl;

    @Autowired
    private AdminService adminSrv;

    @Autowired
    private RefData referenceData;

    private List<User> userList;

    @RequestMapping(value = "/admin/admin", method = RequestMethod.GET)
    public ModelAndView initForm(ModelMap model, HttpServletRequest request, HttpSession session) {

        ModelAndView mav = null;
        user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        if (user == null || user.getId() == null) {
            Logger.debug("Error: user not in session - return to login page");
            return new ModelAndView("login");
        }

        List<InCyyte> incyyteList = adminSrv.getInCyyteByCreatedBy(Constants.CREATED_BY_ADMIN, Constants.QUESTION_TYPE_Q);
        userList = adminSrv.getUsersByEmailOrUsername(null, null);
        Logger.debug("initForm userList size - > " + userList.size());
        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "d-");
        if (paramMap.size() == 0) {
            WebUtils.setSessionAttribute(request, "userList", userList);
            WebUtils.setSessionAttribute(request, "incyyteList", incyyteList);
        }

        mav = new ModelAndView("admin");
        mav.addObject("sendInCyyteForm", new IncyyteModel());
        mav.addObject("searchUserForm", new UserModel());
        mav.addObject("searchIncyyteForm", new IncyyteModel());
        mav.addObject("editIncyyteForm", new IncyyteModel());

        // return form success view
        return mav;
    }

    @RequestMapping(value = "/admin/send_gen", method = RequestMethod.POST)
    public String processSubmit(HttpServletRequest request,
                                @ModelAttribute("sendInCyyteForm") IncyyteModel incyyteModel,
                                BindingResult result, SessionStatus status, HttpSession session) throws Exception {

        incyyteModel.setSendType(Constants.SEND_BY_POST);
        incyyteModel.setCreatedBy(Constants.CREATED_BY_ADMIN);
        incyyteModel.setQuesType(QuestionEnumType.QType_G.getType());
        incyyteModel = uploadFile(incyyteModel);

        prefixLink(incyyteModel);

        InCyyte incyyte = MapUserProperty.copyInCyyteDetails(incyyteModel);
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);

        if (user != null) {
            try {
                addIncyyte(user, incyyte, request);
            } catch (Exception e) {
                throw new ExecutionException(e);
            }
        }
        // return form success view
        return "redirect:admin.cyt";
    }

    @RequestMapping(value = "/admin/searchIncyyte", method = RequestMethod.POST)
    public ModelAndView searchIncyyte(HttpServletRequest request,
                                      @ModelAttribute("searchIncyyteForm") IncyyteModel incyyteModel,
                                      BindingResult result, SessionStatus status, HttpSession session) throws Exception {

        List<InCyyte> incyyteList = adminSrv.getInCyyteByCreatedBy(incyyteModel.getCreatedBy(), incyyteModel.getQuesType());
        WebUtils.setSessionAttribute(request, "incyyteList", incyyteList);

        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("sendInCyyteForm", new IncyyteModel());
        mav.addObject("searchUserForm", new UserModel());
        mav.addObject("searchIncyyteForm", incyyteModel);
        mav.addObject("editIncyyteForm", new IncyyteModel());

        // return form success view
        return mav;
    }

    @RequestMapping(value = "/admin/searchUser", method = RequestMethod.POST)
    public ModelAndView searchUser(HttpServletRequest request,
                                   @ModelAttribute("searchUserForm") UserModel userModel,
                                   BindingResult result, SessionStatus status, HttpSession session) throws Exception {

        userList = adminSrv.getUsersByEmailOrUsername(StringUtils.isNotBlank(userModel.getUser_email()) ? "%" + userModel.getUser_email() + "%" : null
                , StringUtils.isNotBlank(userModel.getUsername()) ? "%" + userModel.getUsername() + "%" : null);

        Logger.debug("searchUser userList size - > " + userList.size());
        WebUtils.setSessionAttribute(request, "userList", userList);

        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("sendInCyyteForm", new IncyyteModel());
        mav.addObject("searchUserForm", userModel);
        mav.addObject("searchIncyyteForm", new IncyyteModel());
        mav.addObject("editIncyyteForm", new IncyyteModel());

        // return form success view
        return mav;
    }

    @RequestMapping(value = "/adminPanel.cyt", method = RequestMethod.GET)
    public String getPolls(ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {
        Logger.info("Inide getPolls():AdminPageController");
        if (session.getAttribute(SessionKeys.LOGIN_ADMIN_USER) == null) {
            return "redirect:admin.cyt";
        }
        //When we set 0 for Offset and records per page we will get all records
        // That count can be used to define the Page Numbers
        List<InCyyte> allPollsCount = adminServiceImpl.getAllPolls(0, 0);
        //Define the PageCount attribute
        int recordsPerPage = referenceData.getPageLimit();
        int pageCount = (int) Math.ceil(allPollsCount.size() * 1.0 / recordsPerPage);
        model.addAttribute("publicPollsPageCount", pageCount);

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            Logger.debug("Page:" + page);
        }
        List<InCyyte> allPolls = adminServiceImpl.getAllPolls((page - 1) * recordsPerPage, recordsPerPage);
        Logger.info("All Polls" + allPolls);
        model.put("allPolls", allPolls);
        model.addAttribute("currentPage", page);
        return "admin/adminPanel";
    }

    @RequestMapping(value = "/updateQuestion.cyt", method = {RequestMethod.GET, RequestMethod.POST})
    public String updatePoll(ModelMap model, HttpSession session, HttpServletRequest request) throws Exception {
        Logger.info("inside updatePoll():AdminPageController");
        String questionId = request.getParameter("id");
        String name = request.getParameter("name");
        if (StringUtils.equals(name, "DISPLAY")) {
            adminServiceImpl.updatePoll(questionId, "Y");
        } else if (StringUtils.equals(name, "REJECT")) {
            adminServiceImpl.updatePoll(questionId, "N");
        }
        return "redirect:adminPanel.cyt";
    }
}
