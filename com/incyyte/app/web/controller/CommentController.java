package com.incyyte.app.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.incyyte.app.domain.AnonymousQuestion;
import com.incyyte.app.domain.Forum;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.CommentService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.SessionKeys;

/**
 * @author prakash
 *
 */
@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/addcomment.cyt", method=RequestMethod.POST)
	public String addComment(@ModelAttribute("forum") Forum forum, HttpSession session,Model model,HttpServletRequest request) {
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("Adding comment >> " + forum);
		Long questionId = forum.getQuestionId();
		try {
			commentService.addComment(user.getId(), questionId, forum.getComment());
		} catch (Exception e) {
			Logger.error("unable to add comments "+e);
		}
		List<AnonymousQuestion> questions = commentService.getQuestionComments(user.getId(), true);
		if(questions.size() == 0) {
			request.setAttribute("emptyResult", true);
		}
		model.addAttribute("questions", questions);
		model.addAttribute("forum", forum);
		return "showcommentspage";
	}
}