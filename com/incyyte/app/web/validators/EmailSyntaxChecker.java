package com.incyyte.app.web.validators;

import java.util.StringTokenizer;

/**
 * @author Remi Oseni
 */
public class EmailSyntaxChecker implements EmailAddressSyntaxValidator {
	private static final String EMAIL_SYMBOL = "@";

	public static final String EMAIL_APPROVED_CHAS =
		".!#$%'*+&-/=?^_`{}|~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public static final String SUBDOMAIN_APPROVED_CHAS =
		"abcdefghijklmnopqrstuvwxyz1234567890-";

	public static final String EXTENSION_APPROVED_CHAS =
		"abcdefghijklmnopqrstuvwxyz";

	public boolean isValidSyntax(String emailAddress) {
		return !isBadEmailSyntax(emailAddress);
	}

	/**
	 * the email format should be in the form <value>@<value>.<value>,if not
	 * it is a bad email syntax.
	 */
	public static boolean isBadEmailSyntax(String email) {

		boolean badEmailSyntax = false;
		int i = email.indexOf(EMAIL_SYMBOL);
		String ss = null;
		// check to see if @ appears more than once in the email string
		if (i >= 0) {
			ss = email.substring(email.indexOf(EMAIL_SYMBOL) + 1);
		}
		if (ss != null && (ss.indexOf(EMAIL_SYMBOL) > 0)) {
			// There's another @, thats illegal
			badEmailSyntax = true;
		} else {
			StringTokenizer st = new StringTokenizer(email, EMAIL_SYMBOL);
			if (st.countTokens() > 1) {
				String emailFirstPart = st.nextToken();
				String emailSecondPart = st.nextToken();
				// <value1>@<value2>.<value3>,check to see if the value1 is
				// having any illegal
				// or invalid chars
				if (!CharacterValidator.checkValidCharacters(emailFirstPart,
						EMAIL_APPROVED_CHAS)) {
					badEmailSyntax = true;
				} else {
					badEmailSyntax = false;
				}
				if (!badEmailSyntax) {
					// check to see if value after @ symbol contains . more than
					// once
					// Eg: value1@value2..value3 is a invalid syntax
					if (!(emailSecondPart.indexOf("..") < 0)) {
						badEmailSyntax = true;
					} else if (!(emailSecondPart.indexOf(".") < 0)) {
						// if nothing is there after ".",its not a valid email
						// syntax
						// eg: value1@value2.
						StringTokenizer st1 =
							new StringTokenizer(emailSecondPart, ".");
						if (st1.countTokens() <= 1) {
							badEmailSyntax = true;
						} else {
							int count = st1.countTokens();
							// email=account@domain,domain=subdomain.subdomain.....extension
							// check to see that subdomain contains valid
							// characters
							for (int j = 0; j < (count - 1); j++) {
								String subdomain =
									st1.nextToken().toLowerCase();
								if (!CharacterValidator.checkValidCharacters(
										subdomain, SUBDOMAIN_APPROVED_CHAS)) {
									badEmailSyntax = true;
								} else {
									badEmailSyntax = false;
								}
							}
							// check to see that extension contains valid
							// characters
							if (!badEmailSyntax) {
								String extension =
									st1.nextToken().toLowerCase();
								if (!CharacterValidator.checkValidCharacters(
										extension, EXTENSION_APPROVED_CHAS)) {
									badEmailSyntax = true;
								} else {
									badEmailSyntax = false;
								}
							}
						}
					} else {
						// if nothing is there after ".",its a bad email syntax
						// eg:value1@value2. or value1.value2@value3.
						badEmailSyntax = true;
					}
				}
			} else {
				// if nothing is there after @ symbol,its a bad email syntax
				// eg: value1@ or @
				badEmailSyntax = true;
			}
		}

		return badEmailSyntax;
	}

}
