package com.incyyte.app.web.validators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.incyyte.app.domain.Postcode;
import com.incyyte.app.service.util.Utility;

public class PostcodeValidator implements Validator {

	private final Log logger = LogFactory.getLog(getClass());

	public boolean supports(Class clazz) {
		return clazz.equals(Postcode.class);
	}

	public void validate(Object obj, Errors errors) {
		Postcode postcode = (Postcode) obj;
		logger.debug("entered postcode: " + postcode.getCode());
		String code = Utility.removeSpace(postcode.getCode());

		logger.debug("Validating user credentials for: START");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code",
				"required.code", "postcode is required");

		if (!Utility.validPostcode(code))
			errors.rejectValue("code", "error.main.invalid-code", null,
					"Invalid postcode entered..");

	}

}
