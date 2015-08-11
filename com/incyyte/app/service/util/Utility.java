package com.incyyte.app.service.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    protected final Log logger = LogFactory.getLog(getClass());

    public static String generateAddrPin(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghiklmnopqrstuvwxyz";
        int str_length = len;
        String randomstring = "";
        for (int i = 0; i < str_length; i++) {
            int rnum = new Double(Math.floor(Math.random() * chars.length())).intValue();
            randomstring += chars.substring(rnum, rnum + 1);
        }
        return "AD-" + randomstring;
    }

    public static String generateInCyyteCode(int len) {
        String chars = "3456789ABCDEFGHJKLMNPQRSTUVWXYabcdefghiklmnpqrstuvwxyz";
        int str_length = len;
        String randomstring = "";
        for (int i = 0; i < str_length; i++) {
            int rnum = new Double(Math.floor(Math.random() * chars.length())).intValue();
            randomstring += chars.substring(rnum, rnum + 1);
        }
        return "VW-" + randomstring;
    }

    public static String generateActivationCode() {
        String chars = "3456789ABCDEFGHJKLMNPQRSTUVWXY";
        int str_length = 7;
        String randomstring = "";
        for (int i = 0; i < str_length; i++) {
            int rnum = new Double(Math.floor(Math.random() * chars.length())).intValue();
            randomstring += chars.substring(rnum, rnum + 1);
        }
        return randomstring;
    }

    public static String generateNewPassword() {
        String chars = "3456789ABCDEFGHJKLMNPQRSTUVWXYabcdefghiklmnpqrstuvwxyz";
        int str_length = 8;
        String randomstring = "";
        for (int i = 0; i < str_length; i++) {
            int rnum = new Double(Math.floor(Math.random() * chars.length())).intValue();
            randomstring += chars.substring(rnum, rnum + 1);
        }
        return randomstring;
    }

    public static String lPad(String s, int len, char pad) {
        while (s.length() < len) {
            s = pad + s;
        }
        return s;
    }

    public static String replaceChar(String s) {
        String comma = "'";
        String repComma = "`";
        return s.replace(comma.charAt(0), repComma.charAt(0));
    }

    public static String rPad(String s, int len, char pad) {
        while (s.length() < len) {
            s += pad;
        }
        return s;
    }

    public static String resize(String s, int len) {
        if (s.length() > len) {
            return s.substring(0, len);
        } else {
            return rPad(s, len, ' ');
        }
    }

    public static String removeSpace(String s) {
        if (s != null && !s.equals("")) {
            s = s.replaceAll("\\s+", "");
        }
        return s;
    }

    public static String resizeNumeric(String s, int len) {
        if (s.length() > len) {
            return s.substring(0, len);
        } else {
            return lPad(s, len, '0');
        }
    }


    public static boolean validateDigits(String param) {
        for (int i = 0; i < param.length(); i++) {
            char val = param.charAt(i);
            Character mychar = new Character(val);
            if (mychar.isDigit(val) == false)
                return false;
        }
        return true;
    }


    public static boolean validEmail(String email) {

        //Set the email pattern string
        Pattern p = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}");

        //Match the given string with the pattern
        Matcher m = p.matcher(email.trim().toUpperCase());

        //check whether match is found
        boolean matchFound = m.matches();

        return matchFound;
    }

    /*
          *This covers BS7666 plus BFPO addresses.
          *
          *Valid formats (where A=alpha, 9=digit):
         *
         *A9 9AA, A99 9AA, A9A 9AA, AA9 9AA, AA99 9AA, AA9A 9AA,
         *BFPO 9, BFPO 99, BFPO 999, BFPO 9999, GIR 0AA
         *
         *The only way to ensure that your app is 100% accurate is to subscribe to
         *the daily PAF update from The Royal Mail.
         *However, I believe that most of us would be happy with a quarterly feed.
         *
         */
    public static boolean validPostcode(String postcode) {

        //Set the postcode pattern string
        Pattern p_withSpace = Pattern.compile("(GIR 0AA|[A-PR-UWYZ]([0-9]{1,2}|([A-HK-Y][0-9]|[A-HK-Y][0-9]([0-9]|[ABEHMNPRV-Y]))|[0-9][A-HJKS-UW])_[0-9][ABD-HJLNP-UW-Z]{2})");
        Pattern p_withNoSpace = Pattern.compile("(GIR 0AA|[A-PR-UWYZ]([0-9]{1,2}|([A-HK-Y][0-9]|[A-HK-Y][0-9]([0-9]|[ABEHMNPRV-Y]))|[0-9][A-HJKS-UW])[0-9][ABD-HJLNP-UW-Z]{2})");

        //Match the given string with the pattern
        Matcher m1 = p_withSpace.matcher(postcode.trim().toUpperCase());
        //check whether match is found
        boolean matchFound = m1.matches();

        //if match not found check withoutspace
        if (!matchFound) {
            Logger.debug("check postcode with no space");
            Matcher m2 = p_withNoSpace.matcher(postcode.trim().toUpperCase());
            //check whether match is found
            matchFound = m2.matches();
        }

        return matchFound;
    }

    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

    public static String formatDate(java.util.Date date) {
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss ");
        //DateFormat dateformat = DateFormat.getInstance();
        String format = dateformat.format(date);
        //Date dt = dateformat.parse(format);
        return format;
    }

    public static String formatTime(java.util.Date date) {
        DateFormat dateformat = new SimpleDateFormat("hh:mm");
        //DateFormat dateformat = DateFormat.getInstance();
        String format = dateformat.format(date);
        //Date dt = dateformat.parse(format);
        return format;
    }

    public static int formatYear() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date currentDate = new Date();
        String format = df.format(currentDate);

        FieldPosition pos = new FieldPosition(DateFormat.YEAR_FIELD);
        //Logger.debug("YEAR: "+format.substring(format.length()-4));
        return Integer.parseInt(format.substring(format.length() - 4));
    }

    public static int formatMidYear(int offset) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        Date currentDate = new Date();
        String format = df.format(currentDate);

        FieldPosition pos = new FieldPosition(DateFormat.YEAR_FIELD);
        //Logger.debug("YEAR: "+format.substring(format.length()-4));
        return Integer.parseInt(format.substring(format.length() - 4)) - offset;
    }

    public static String splitStringFirstPart(String str) {
        java.util.StringTokenizer st = new java.util.StringTokenizer(str);
        String build = st.nextToken();
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (nonNumericStr(s))
                return build;
            else
                build += "_" + s;
        }

        return build;

    }

    public static boolean nonNumericStr(String str) {
        Pattern p = Pattern.compile("[A-Za-z]*");
        return p.matcher(str).matches();
    }

    public String getMIMEType(String ext) {
        if (ext.equals(".gif"))
            return Constants.MIME_GIF;
        else if (ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".jpe"))
            return Constants.MIME_JPG;
        else if (ext.equals(".pdf"))
            return Constants.MIME_PDF;
        else if (ext.equals(".doc"))
            return Constants.MIME_DOC;
        else if (ext.equals(".swf"))
            return Constants.MIME_SWF;
        else if (ext.equals(".txt") || ext.equals(".html"))
            return Constants.MIME_TXT;
        else
            return "Invalid file upload";
    }

    public static void main(String[] args) {
        Logger.debug(replace("131A grifon road", "fon", "ton"));
        //Logger.debug(nonNumericStr("surrey"));
    }

    public static boolean isWindows() {

        String os = System.getProperty("os.name").toLowerCase();
        //windows
        return (os.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        String os = System.getProperty("os.name").toLowerCase();
        //Mac
        return (os.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        String os = System.getProperty("os.name").toLowerCase();
        //linux or unix
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);

    }

    public static String getServerURL(HttpServletRequest req) {
        String url = null;
        Logger.debug("url: " + req.getRequestURL());
        if (req.getRequestURL() != null) url = req.getRequestURL().toString();

        // 
        // Getting servlet request query string. 
        // 
        String queryString = req.getQueryString();

        // 
        // Getting request information without the hostname. 
        // 
        String uri = req.getRequestURI();

        // 
        // Below we extract information about the request object path 
        // information. We extract the protocol user, server and and its  
        // assigned port number. We extract our application context path,  
        // servlet path, path info and the query string information. If we  
        // combaine all the information below we'll get someting equals to  
        // the request.getRequestURL(). 
        // 
        String scheme = req.getScheme();
        String serverName = req.getServerName();
        int portNumber = req.getServerPort();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String pathInfo = req.getPathInfo();
        String query = req.getQueryString();

        Logger.debug("url			--------->" + url);
        Logger.debug("queryString	--------->" + queryString);
        Logger.debug("uri			--------->" + uri);
        Logger.debug("scheme		--------->" + scheme);
        Logger.debug("serverName	--------->" + serverName);
        Logger.debug("portNumber	--------->" + portNumber);
        Logger.debug("contextPath	--------->" + contextPath);
        Logger.debug("servletPath	--------->" + servletPath);
        Logger.debug("pathInfo	--------->" + pathInfo);
        Logger.debug("query		--------->" + query);

        String incyyteRootURL = scheme + "://" + serverName + ":" + portNumber + contextPath;
        
        if(serverName != null && !serverName.equalsIgnoreCase("localhost")){
        	incyyteRootURL = scheme + "://" + serverName + contextPath;
        }        
        Logger.debug("incyyteRootURL		--------->" + incyyteRootURL);
        
        return incyyteRootURL;
    }

    public static Date verifyDateFormat(String date) throws ParseException {
        try {
            DateFormat dfm = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date a = dfm.parse(date);
            return a;
        } catch (ParseException e) {
            DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date a = dfm.parse(date);
            return a;
        }
    }

    //Generate Activation Code
    public static String getRandomActivationCd(int aLowerLimit, int aUpperLimit) {
        String newPassword = null;

        final String DIGITS = "0123456789";
        final String LOCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
        final String UPCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String printable_characters = DIGITS + LOCASE_CHARACTERS + DIGITS + LOCASE_CHARACTERS + DIGITS;// + UPCASE_CHARACTERS;

        try {
            newPassword = generate(printable_characters, pickNumberInRange(aLowerLimit, aUpperLimit));

        } catch (Exception e) {
            Logger.error(e);
        }

        return newPassword;
    }

    /**
     * This method generates the password randomly.
     *
     * @param chars      contains characters allowed for the valid password.
     * @param passLength contains length of the password.
     * @return String It returns newly generated password.
     * @throws Exception
     */
    private static String generate(String chars, int passLength) throws Exception {
        Random m_generator = new Random();

        if (passLength > chars.length()) {
            throw new Exception("Password generation is impossible");
        }

        char[] availableChars = chars.toCharArray();
        int availableCharsLeft = availableChars.length;
        StringBuffer temp = new StringBuffer(passLength);

        for (int i = 0; i < passLength; i++) {
            int pos = (int) (availableCharsLeft * m_generator.nextDouble());
            temp.append(availableChars[pos]);
            availableChars[pos] = availableChars[availableCharsLeft - 1];
            --availableCharsLeft;
        }
        return String.valueOf(temp);
    }


    /**
     * This method picks any number randomly which falls
     * between lower limit and upper limit.
     *
     * @param aLowerLimit is the minimum length for password.
     * @param aUpperLimit is the maximum length for the password.
     * @return int It returns password length.
     */
    private static int pickNumberInRange(int aLowerLimit, int aUpperLimit) {
        if (aLowerLimit > aUpperLimit) {
            StringBuffer message = new StringBuffer();
            message.append("Lower limit (");
            message.append(aLowerLimit);
            message.append(") must be lower than Upper limit (");
            message.append(aUpperLimit);
            message.append(")");
            throw new IllegalArgumentException(message.toString());
        }

        Random generator = new Random();
        // get the range, casting to long to avoid overflow problems
        long range = (long) aUpperLimit - (long) aLowerLimit + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * generator.nextDouble());
        return (int) (fraction + aLowerLimit);
    }

    public static String getDate(java.util.Date date) {
        DateFormat dateformat = new SimpleDateFormat("dd MMM yyyy | hh:mm a");
        String formattedDate = dateformat.format(date);
        return formattedDate;
    }
   
    
    /**
     * Formats the time elapsed between fromDate and now.
     * 
     * @param fromDate the start time, a unix epoch datetime stamp.
     * @return the formatted time elapsed.
     */
    public static String formatElapsedTime(long fromDate) {
        int SECOND = 1;
        int MINUTE = 60 * SECOND;
        int HOUR = 60 * MINUTE;
        int DAY = 24 * HOUR;
        int MONTH = 30 * DAY;

        long now = (new Date()).getTime();        
        //Logger.debug("NOW: "+ now);
        
        long delta = (now / 1000) - fromDate;

        //Logger.debug("FROM DATE: "+ fromDate);
        //Logger.debug("DIFFERENCE: "+ delta);

        if (delta < 1 * MINUTE) {
            return delta == 1 ? "one second ago" : delta + " seconds ago";
        }
        if (delta < 2 * MINUTE) {
            return "a minute ago";
        }
        if (delta < 45 * MINUTE) {
            return (delta / MINUTE) + " minutes ago";
        }
        if (delta < 90 * MINUTE) {
            return "an hour ago";
        }
        if (delta < 24 * HOUR) {
            return (delta / HOUR) + " hours ago";
        }
        if (delta < 48 * HOUR) {
            return "yesterday";
        }
        if (delta < 30 * DAY) {
            return (delta / DAY) + " days ago";
        }
        if (delta < 12 * MONTH) {
            int months = (int)(Math.floor((double) ((delta / DAY) / 30)));
            return months <= 1 ? "one month ago" : months + " months ago";
        } else {
            int years = (int)(Math.floor((double) (delta / DAY) / 365));
            return years <= 1 ? "one year ago" : years + " years ago";
        }
    }
    public static long getCommentTimeinSecs(String dt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(dt);
        long timeInMillisSinceEpoch = date.getTime() / 1000;
        //long timeInMinutesSinceEpoch = timeInMillisSinceEpoch / (60 * 1000);
        return timeInMillisSinceEpoch;
    }

}