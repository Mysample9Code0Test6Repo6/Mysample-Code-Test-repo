package com.incyyte.app.web.validators;

/**
 * This class provides utility methods for validating characters.
 */

public class CharacterValidator {
	public static final String APPROVED_CHAS =
		"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	// ------------------------------------------------------------
	/**
	 * Ensures that the string 'toValidate' has valid characters
	 * 
	 * @param toValidate
	 *            String to validate
	 * @return true if string only contains valid characters
	 */

	public static boolean checkValidCharacters(String toValidate) {
		for (int i = 0; i < toValidate.length(); i++) {
			String character = toValidate.substring(i, i + 1);

			if (APPROVED_CHAS.indexOf(character) < 0) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsInvalidCharacters(String toValidate) {
		return !checkValidCharacters(toValidate);
	}

	// ------------------------------------------------------------
	/**
	 * Ensures that the string 'toValidate' has valid characters
	 * 
	 * @param toValidate
	 *            String to validate
	 * @param validCharacters
	 *            list of valid characters
	 * @return true if string only contains valid characters
	 */

	public static boolean checkValidCharacters(String toValidate,
			String validCharacters) {
		for (int i = 0; i < toValidate.length(); i++) {
			String character = toValidate.substring(i, i + 1);

			if (validCharacters.indexOf(character) < 0) {
				return false;
			}
		}

		return true;
	}

	public static boolean containsInvalidCharacters(String toValidate,
			String validCharacters) {
		return !checkValidCharacters(toValidate, validCharacters);
	}

	// ------------------------------------------------------------

	public static boolean shorterThan(int maxLength, String toValidate) {
		return toValidate.length() < maxLength;
	}

	public static boolean notShorterThan(int maxLength, String toValidate) {
		return !shorterThan(maxLength, toValidate);
	}

	// ------------------------------------------------------------
	/**
	 * Ensures that the toValidate String is between the specified lengths
	 */

	public static boolean betweenLengths(int min, int max, String toValidate) {
		boolean valid = true;

		if (toValidate.length() < min) {
			valid = false;
		}

		if (toValidate.length() > max) {
			valid = false;
		}

		if (toValidate == null) {
			valid = false;
		}

		return valid;
	}
}
