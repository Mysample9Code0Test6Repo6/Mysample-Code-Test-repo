package com.incyyte.app.service.util;

public class ConfigProperties {

    public static final String PROP_SESSION_TIMEOUT_MINS = "incyyte.session.timeout.mins";

    //Properties for the Email Server
    public static final String PROP_EMAIL_SMTP_HOST = "incyyte.email.smtp.host";
    public static final String PROP_EMAIL_SMTP_PORT = "incyyte.email.smtp.port";
    public static final String PROP_EMAIL_SMTP_USER = "incyyte.email.smtp.username";
    public static final String PROP_EMAIL_SMTP_PSWD = "incyyte.email.smtp.password";
    public static final String PROP_EMAIL_FORGOTTEN_PASS_SUBJECT = "incyyte.email.forgotten.pass.subject";
    public static final String PROP_EMAIL_FROM = "incyyte.email.from";
    public static final String PROP_EMAIL_RESETPASSWORD_MSG1 = "incyyte.email.resetpassword.message1";
    public static final String PROP_EMAIL_RESETPASSWORD_MSG2 = "incyyte.email.resetpassword.message2";
    public static final String PROP_EMAIL_RESETPASSWORD_MSG3 = "incyyte.email.resetpassword.message3";

    public static final String CONTACT_US_MAIL_TO = "incyyte.email.contactus.to";
    public static final String CONTACT_US_MAIL_SUBJECT = "incyyte.email.contactus.subject";
    public static final String MESSAGE_MAIL_SUBJECT = "incyyte.email.message.subject";
    public static final String MESSAGE_EMAIL_FROM = "incyyte.email.message.from";
    public static final String WELCOME_MAIL_SUBJECT = "incyyte.email.welcome.subject";
    public static final String WELCOME_EMAIL_FROM = "incyyte.email.welcome.from";
    public static final String INCYYTE_MAIL_SUBJECT = "incyyte.email.subject";
    public static final String PAYMENT_EMAIL_SUBJECT = "incyyte.payment.email.subject";
    public static final String PROP_CDN_IMAGES_URL = "incyyte.cdn.images.url";
    public static final String PROP_EMAIL_INVITE_SUBJECT = "incyyte.email.invite.subject";
    public static final String PROP_EMAIL_OPINION_SUBJECT = "incyyte.email.opinion.subject";
    public static final String PROP_EMAIL_MEMBER_INVITE_SUBJECT = "incyyte.email.member.invite.subject";
    public static final String REACTIVATION_EMAIL_SUBJECT = "incyyte.reactivation.subject";
    public static final String INCYYTE_DEFAULY_AVATAR_URL = "incyyte.default.avatar";
    public static final String INCYYTE_ANONYMOUS_AVATAR_URL = "incyyte.anonymous.avatar";
    public static final String INCYYTE_VOTING_URL = "incyyte.voting.url";
    public static final String INCYYTE_NONMEMBER_VOTING_URL = "incyyte.nonmember.voting.url";
    public static final String INCYYTE_REGION_VOTING_URL = "incyyte.region.voting.url";
    public static final String CREATE_ACCOUNT_URL = "incyyte.createaccount.url";
    public static final String CONTEXT_PATH = "incyyte.contextPath";

    public static final String POSTSCRIPT_MAIL_SUBJECT = "incyyte.email.postscript.subject";

    public static final String ACTIVATION_URL = "incyyte.activation.url";
    public static final String INVITATION_URL = "incyyte.invitation.url";
    public static final String REPORT_COUNTER = "incyyte.report.counter.limit";
    public static final String POLL_EMAIL_COUNT_LIMIT = "incyyte.pollEmail.counter.limit";

    public static final String NOTIFICATION_MAIL_SUBJECT = "incyyte.email.notify.subject";
    public static final String PROP_EMAIL_FROM_NOTIFY = "incyyte.email.notify.from";

    public static final String INCYYTE_QUESTION_INVITE_EMAIL_FROM = "incyyte.email.question.invite.from";
    public static final String INCYYTE_QUESTION_POLL_EMAIL_FROM = "incyyte.email.question.poll.from";
    public static final String INCYYTE_QUESTION_INVITE_EMAIL_SUBJECT = "incyyte.email.question.invite.subject";

    public static final String REPORT_POLL_FROM = "incyyte.report.poll.from";
    public static final String REPORT_POLL_TO = "incyyte.report.poll.to";
    public static final String REPORT_POLL_SUBJECT = "incyyte.report.poll.subject";

    public static final String INCYYTE_ANONYMOUS_NON_MEMBER_MSG = "incyyte.anonymous.nonmember.msg";
    public static final String SN_STATUSUPDATE_FLAG = "incyyte.sn.status.msg.flag";
    //This is used to link the Amazon WS URL which will be pre-fixed for all files stored on Server
    public static final String PROVIDER_NAME = "provider.name";
    public static final String PROVIDER_FILE_URL = "provider.file.url";

    public static final String RACKSPACE_IMAGE_URL = "rackspace.image.url";
    public static final String RACKSPACE_VIDEO_URL = "rackspace.video.url";
    public static final String RACKSPACE_APPL_URL = "rackspace.application.url";
    public static final String RACKSPACE_TEXT_URL = "rackspace.text.url";

    public static final String PAYMENT_GATEWAY_URL = "payment.gateway.url";
    public static final String WEBSITE_URL = "website.url";
    public static final String SHA_IN_ID = "shain.ID";

    //public static final String SHA_IN_ID = getProperty("shain.ID");
    private static String getProperty(String propertyId) {
        try {
            IncyyteProperties ip = new IncyyteProperties(null);
            ConfigManager icfg = ConfigManager.getInstance();
            icfg.setIncyyteProperties(ip);
            return icfg.getProperty(propertyId);
        } catch (Exception e) {
            Logger.error("Failed to load property:" + propertyId, e);
        }
        return null;
    }

    public static final int NOTIFY_COMMENTS_INTERVAL = Integer.parseInt(getProperty("notify.poll.comments.interval"));
    public static final int NOTIFY_VOTES_INTERVAL = Integer.parseInt(getProperty("notify.poll.votes.interval"));
    public static final int NOTIFY_COMMENTS_LIMIT = Integer.parseInt(getProperty("notify.poll.comments.limit"));
    public static final int NOTIFY_VOTES_LIMIT = Integer.parseInt(getProperty("notify.poll.votes.limit"));
}