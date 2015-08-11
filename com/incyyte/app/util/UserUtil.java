package com.incyyte.app.util;

import org.apache.commons.lang.StringUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UserUtil {

    public static boolean isValidUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return false;
        }
        String specialChars = "!@#$%^&*() +=_`~-[]\\\';,./{}|\":<>?";
        for (int i = 0; i < specialChars.length(); i++) {
            if (StringUtils.contains(userName, specialChars.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidBirthYear(Integer birthYear, Integer limit) {
        if (birthYear == null) return false;
        if (birthYear == 0 ) return false;
        if (birthYear < 1905) return false;
        Calendar calendar = new GregorianCalendar();
        int ageLimit = calendar.get(Calendar.YEAR) - limit;
        if (birthYear < ageLimit) return false;
        return true;
    }

}