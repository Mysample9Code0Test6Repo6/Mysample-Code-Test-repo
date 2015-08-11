package com.incyyte.app.web.validators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.incyyte.app.web.model.UserModel;

@SuppressWarnings("unchecked")
public class QuickStartValidator implements Validator {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	public boolean supports(Class clazz) {
		// just validate the User instances
		return UserModel.class.isAssignableFrom(clazz);
	}

	// validate page 1, userName
	public void validatePage1Form(Object command, Errors errors) {

		UserModel user = (UserModel) command;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname",
				"required.firstname", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname",
				"required.lastname", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile",
				"required.mobile", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"required.email", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmEmail",
				"required.confirmEmail", "Field name is required.");

		
		if (user.getGender().equals("null")) {
			errors.rejectValue("gender", "required.gender",
					"Field name is required.");
		}

		if (user.getAgeGroup().equals("0")) {
			errors
					.rejectValue("age", "required.age",
							"Field name is required.");
		}

	}

	// validate page 2, password
	public void validatePage2Form(Object command, Errors errors) {

		UserModel user = (UserModel) command;

		if (!user.getUploadedFile().isEmpty()) {
			if (user.getUploadedFile().getSize() > 99999) {
				errors.rejectValue("uploadedFile", "required.uploadedFile",
						"File is too big.");
			}
		}
	}

	// validate page 3, remark
	public void validatePage3Form(Object target, Errors errors) {
	}

	public void validate(Object target, Errors errors) {
		validatePage1Form(target, errors);
		validatePage2Form(target, errors);
		validatePage3Form(target, errors);
	}

}
