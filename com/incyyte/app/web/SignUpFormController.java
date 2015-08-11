package com.incyyte.app.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.incyyte.app.web.model.UserModel;

public class SignUpFormController extends SimpleFormController {

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		UserModel userForm = (UserModel) super.formBackingObject(request);

		return userForm;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		// UserModel user = (UserModel) command;

		return new ModelAndView(getSuccessView());
	}

}
