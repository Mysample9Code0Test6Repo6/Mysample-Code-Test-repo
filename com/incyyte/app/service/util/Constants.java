package com.incyyte.app.service.util;

public interface Constants {

	//postcode Anywhere constants
	public final static String PCA_KEY = "TR92-BA12-XB55-XX73";
	public final static String PCA_USERNAME = "INDIV60776";

	public final static String DEFAULT_LOGGER = "Logger";
	public final static String USER_ADDRESS_ACTIVATION_MSG = "Pin Exists for this Address: Enter PIN";
	public final static String USER_ADDRESS_EMAIL_PIN_MSG = "Pin has been Emailed to your personal email address";
	public final static int DEFAULT_PIN_LENGTH = 7;
	public final static int DEFAULT_CODE_LENGTH = 7;
	public final static String EMAIL_DOMAIN = "incyyte.com";
	public final static String DELIMITER = "-";
	public final static String APP_CTX_LOCATION = "test/java/inCyyte-servlet.xml";
	public final static String TXN_MANAGER = "transactionManager";


	public static enum IS_MASTERPIN {Y, N};
	public static enum USER_ADDR_PIN_STATUS {ACTIVE, NON_ACTIVE, SUSPENDED};
	public static enum GRP_TYPE {PRIVATE, PRIVILGE, OPEN};
	public static enum INCYYTE_CREATION_TYPE {EMAIL, GROUP, REGION, POSTSCRIPT};

	public final static String MIME_GIF = "image/gif";
	public final static String MIME_JPG = "image/jpeg";
	public final static String MIME_PDF = "application/pdf";
	public final static String MIME_DOC = "application/msword";
	public final static String MIME_SWF = "application/x-shockwave-flash";
	public final static String MIME_TXT = "text/plain";

	public final static String DEFAULT_EVENT = "";
	public final static String DEFAULT_PAGE_TITLE = "InCyyte";
	public final static String APP_USER = "USER";

	public final static String DEFAULT_MODULE_NAME = "InCyyteCore";

	// Default name of the forward, to be re-directed to, in case
	// of an unexpected condition
	public static final String DEFAULT_APP_REDIRECT = "error";
	public static final String ERROR_FWD_NAME   = "error";

	// Default session timeout interval in minutes
	public static final String DEFAULT_SESSION_TIMEOUT_MINS = "15";
	public static final String EMPTY_STRING = "";

	public static final String PROPERTIES_NOT_FOUND_ERROR = "incyyte.properties.not.found.error";
	public static final String PROPERTIES_NOT_FOUND_MSG   =
					"The application properties resource could not be found and the " +
					"application could not be initialized.";

	public static final String PROPERTIES_LOADING_ERROR = "incyyte.properties.loading.error";
	public static final String INTERNAL_PROGRAM_ERROR = "incyyte.internal.program.error";
	public static final String USER_LOGIN_INIT_ERROR   = "incyyte.user.login.error";

	public static final String EXPIRED_SESSION_ERROR  = "incyyte.expired.session.error";
	public static final String EXPIRED_SESSION_ERRMSG =
		"Your session has timed out or expired and has been re-created for you. " +
		"You may be re-directed to another page from where you may continue." ;

	public static final String INVALID_LOGIN_ERROR  = "incyyte.user.login.error";
	public static final String INVALID_LOGIN_ERRMSG =
		"The username/password entered was invalid. " +
		"Please try again or report the problem.";

	public static final String UNAUTH_ACCESS_ERROR  = "incyyte.unauth.access.error";
	public static final String UNAUTH_ACCESS_ERRMSG =
		"Your request could not be authorized. Please check that the URL " +
		"you are using is correct, or that you have logged-in and have access " +
		"for to this application-function.";

	public static final String SESSION_EXP = "sessionError";

	public static final String SEND_POSTSCRIPT_EMAIL = "POSTSCRIPT";
	public static final String SEND_WELCOME_EMAIL = "WELCOME";
	public static final String SEND_CONTACT_US_EMAIL = "CONTACT_US";
    public static final String SEND_POLL_COMMENTS_NOTIFICATION = "POLL_COMMENTS_NOTIFICATION";
    public static final String SEND_POLL_VOTES_NOTIFICATION = "POLL_VOTES_NOTIFICATION";

    public static final String SEND_REACTIVATION_EMAIL="RE_ACTIVATE";
	public static final String SEND_EMAIL = "SENTMAIL";
	public static final String SEND_ACTIVATION_EMAIL = "ACTIVATE";
	public static final String SEND_INVITE_EMAIL = "INVITE";
	public static final String SEND_INVITE_WITH_INCYYTE_EMAIL = "INVITE_WITH_INCYYTE";
	public static final String SEND_INCYYTE_EMAIL = "INCYYTE";
	public static final String SEND_SHARE_POLL_PAGE_EMAIL = "POLLPAGE";
	public static final String SEND_REPORT_THIS_POLL_EMAIL = "REPORT_THIS_POLL";
	public static final String SEND_SHARE_NEWS_LETTER_EMAIL = "NEWS_LETTER";

    public static final String LOG_EMAIL_ERROR = "ERROR";
	public static final String LOG_EMAIL_SENT = "SENT";

	public static final String DASH_INCOMING = "incoming";
	public static final String DASH_SENT = "sent";
	public static final String DASH_COMPLETED = "completed";
	public static final String DASH_PETITIONS = "petitions";

	public static final String INCYYTE_SENT = "my-Incyytes";
	public static final String INCYYTE_RESULTS = "participated-Incyytes";
	public static final String INCYYTE_POST = "posted-Incyytes";
	public static final String INCYYTE_REGIONS = "regional";
	public static final String INCYYTE_REGIONS_ALL = "regional all Incyytes";
	public static final String INCYYTE_REGIONS_COUNTY = "regional county Incyytes";
	public static final String INCYYTE_REGIONS_POSTCODE = "regional postcode Incyytes";
	public static final String INCYYTE_REGIONS_REGION = "regional region Incyytes";

	public static final String OLD_AGE = "old_age";
	public static final String YOUNG_AGE = "young_age";

	public static final String CREATED_BY_ADMIN = "ADMIN";
	public static final String CREATED_BY_USER = "USER";

	public static final String QUESTION_TYPE_Q = "Q";
	public static final String QUESTION_TYPE_P = "P";
	public static final String QUESTION_TYPE_G = "G";

	public static final String SEND_BY_MAIL = "mail";
	public static final String SEND_BY_POST = "post";
	public static final String SEND_BY_AREA = "area";

	public static final String SEARCHABLE_PUBLIC  = "PUBLIC";
	public static final String SEARCHABLE_PRIVATE = "PRIVATE";

	public static final String GET_BY_MAIL = "mail";
	public static final String GET_BY_CODE = "code";
	public static final String GET_BY_CONTACT_ID = "contactId";

	public static final String CONTACT_MEMBER = "M";
	public static final String CONTACT_NOT_MEMBER = "NM";
	public static final String CONTACT_INVITATION_ACCEPTED = "Y";
	public static final String CONTACT_INVITATION_NOT_ACCEPTED = "N";

	public static final int CLOSURE_DAYS = 10;

	public static final String ANONYMOUS = "ANONYMOUS";

	public static final String UPLOAD_TYPE_VIDEO = "Video";
	public static final String UPLOAD_TYPE_DOC = "Doc";
	public static final String UPLOAD_TYPE_IMAGE = "Image";

	public static final String GENDER_MALE = "Male";
	public static final String GENDER_FEMALE = "Female";
	public static final String GENDER_NOT_SURE = "NULL";

	public static final String POST_TO_POSTCODE = "POSTCODE";
	public static final String POST_TO_REGION = "REGION";
	public static final String POST_TO_COUNTY = "COUNTY";

    public static final String ALL_MEMEBER_GROUP = "ALL MEMBERS";

    public static final String SHARE = "SHR";
    
    //These constants are used in payment process
    public static final String POLL_SECTION = "POLL";
    public static final String BUSINESS_SECTION = "BUSINESS";
    public static final String BUSINESS_SLIVER = "BUSINESS_SLIVER";
    
	public static final String SEND_POLL_MESSAGE = "POLL_MESSAGE";
	public static final String SEND_PAYMENT_EMAIL = "PAYMENT_EMAIL";

	public static final String GRP_AUTO_GEN = "AUTO_GEN";
	
}