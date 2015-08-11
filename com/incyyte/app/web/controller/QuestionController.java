package com.incyyte.app.web.controller;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.QuestionService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.QuestionModel;
import com.incyyte.app.web.model.Questionlist;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller

public class QuestionController {

    /**
     * Logger for this class and subclasses
     */
    protected final Log logger = LogFactory.getLog(getClass());


    private Questionlist queslist;


    @Autowired
    private QuestionService questnSrv;


    @RequestMapping("/questions")
    public String questionshomepage
            (
                    @ModelAttribute("QuestionModel") QuestionModel quesmodel, BindingResult result, HttpSession session, Model model) {


        // command object

        model.addAttribute("searchQuesForm", quesmodel);


        // return form view
        return "questions";
    }


    @RequestMapping(value = "/questions/submit.cyt", method = RequestMethod.POST)
    @ResponseBody
    public String processSubmit(HttpServletRequest request,
                                @ModelAttribute("searchQuesForm") QuestionModel QuesModel,
                                BindingResult result, SessionStatus status, HttpSession session, Model model) {


        User user = (User) session.getAttribute("user");
        try {
            queslist = questnSrv.searchQuest(QuesModel, user.getId());


        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        model.addAttribute("searchQuesForm", QuesModel);
        model.addAttribute("searchresult", queslist.getQueslist());

        return "questions";
    }


    @RequestMapping(value = "/questions/SearchResult")

    public String processSearchresult(HttpServletRequest request,
                                      @ModelAttribute("searchQuesForm") QuestionModel QuesModel,
                                      BindingResult result, SessionStatus status, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        QuestionModel quesmodel = new QuestionModel();

        model.addAttribute("searchQuesForm", QuesModel);
        model.addAttribute("searchresult", queslist.getQueslist());
        Logger.debug("searchresult" + queslist.getQueslist().size());


        return "questions";
    }


    @RequestMapping(value = "/questions/getdtls.cyt", method = RequestMethod.GET)
    @ResponseBody
    public String displaydtls(HttpServletRequest request,
                              @ModelAttribute("searchQuesForm") QuestionModel QuesModel,
                              BindingResult result, SessionStatus status, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        try {
            QuesModel = questnSrv.getQuestionsdtls(QuesModel.getQuestionid());
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        model.addAttribute("searchQuesForm", QuesModel);
        return "quesdtls";
    }


    /**
     * @param queslist the queslist to set
     */
    public void setQueslist(Questionlist queslist) {
        this.queslist = queslist;
    }


    /**
     * @return the queslist
     */
    public Questionlist getQueslist() {
        return queslist;
    }
}