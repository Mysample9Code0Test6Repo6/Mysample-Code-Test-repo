package com.incyyte.app.web.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.incyyte.app.domain.Group;

/**
 * 
 */

/**
 * @author prakash
 *
 */
public class GroupValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		return Group.class.isAssignableFrom(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "groupName", "", "Group name is required");
		ValidationUtils.rejectIfEmpty(errors, "postCode", "", "Post code is required");
		ValidationUtils.rejectIfEmpty(errors, "description", "", "Description is required");

	}

}
