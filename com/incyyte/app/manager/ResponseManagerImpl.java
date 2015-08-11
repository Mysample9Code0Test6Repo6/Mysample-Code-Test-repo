package com.incyyte.app.manager;

import com.incyyte.app.dao.ResponseDao;
import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameterFactory;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.user.UserDaoQueries;
import com.incyyte.app.dao.user.rowmapper.AddedResponseInfoRowMapper;
import com.incyyte.app.domain.QuestionType;
import com.incyyte.app.domain.Response;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.QuestionModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseManagerImpl implements ResponseManager {

    @Autowired
    private AbstractDao abstractDao;

    @Autowired
    private ResponseDao responseDao;

    @Autowired
    private RegistrationServiceManager registrationServiceManager;

    @Autowired
    private QuestionManager quesMgr;

    @Autowired
    private QuickStartServiceManager quickStartServiceManager;

    @Value("${incyyte.notification.url}")
    private String incyyteURL;

    @Value("${website.url}")
    private String websiteURL;

    @Override
    public void addPostedResponse(long incyyteID, long userId, long selectedAnswerId, String gender, String ageGroup, Date responseDate) {
        Logger.debug("addPostedResponse: insert New Response::" + responseDate + "incyyteID::::" + incyyteID);
        JdbcTemplate template = abstractDao.getJdbcTemplate("addPostedResponse");
        String incrementInCyyteResponseQuery = UserDaoQueries.getIncrementInCyyteResponseQuery().toString();
        Logger.debug("incrementInCyyteResponseQuery: " + incrementInCyyteResponseQuery);
        String userGender;
        String userAgeGroup;
        try {
            //Get the User details for Gender and Age group information
            User user = new User();
            if (StringUtils.isBlank(gender) || StringUtils.isBlank(ageGroup)) {
                user = registrationServiceManager.getUser(userId);
                Logger.debug("User:" + user);
            }
            userGender = StringUtils.defaultString(gender, user.getGender());
            userAgeGroup = StringUtils.defaultString(ageGroup, user.getAgeGroup());
            //Build the response object to insert the posted response
            Response response = new Response();
            response.setQuestionId(incyyteID);
            response.setUserId(userId);
            response.setAnswerId(selectedAnswerId);
            response.setGender(userGender);
            response.setAgeGroup(userAgeGroup);

            //Begin the Transaction
            abstractDao.getTxnHelper().beginTxn();
            responseDao.addPostedResponse(response, template);
            QueryHelper.doUpdate(template, incrementInCyyteResponseQuery, QueryParameterFactory.getIncrementResponseParams(incyyteID));
            abstractDao.getTxnHelper().commitTxn();
            //Trigger Vote notification
            triggerResponsesNotification(incyyteID, QuestionType.POST);
        } catch (Exception e) {
            Logger.error("addResponse: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addPostedResponse");
        }
    }

    @Override
    public void addRegionalResponse(long incyyteID, long userId, long selectedAnswerId) {
        Logger.debug("addResponse: insert New Regional Response");
        JdbcTemplate template = abstractDao.getJdbcTemplate("addRegionalResponse");
        String incrementInCyyteResponseQuery = UserDaoQueries.getIncrementInCyyteResponseQuery().toString();

        try {
            //get additional info
            User user = registrationServiceManager.getUser(userId);
            Logger.debug("User:" + user);
            Response response = new Response();
            response.setAnswerId(selectedAnswerId);
            response.setUserId(userId);
            response.setQuestionId(incyyteID);
            response.setGender(user.getGender());
            response.setAgeGroup(user.getAgeGroup());

            abstractDao.getTxnHelper().beginTxn();
            responseDao.addRegionalResponse(response, template);
            QueryHelper.doUpdate(template, incrementInCyyteResponseQuery, QueryParameterFactory.getIncrementResponseParams(incyyteID));
            abstractDao.getTxnHelper().commitTxn();
            //Trigger Vote notification
            triggerResponsesNotification(incyyteID, QuestionType.AREA);
        } catch (Exception e) {
            Logger.error("addResponse: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addRegionalResponse");
        }
    }

    @Override
    public void addResponse(long incyyteID, long memberId, long selectedAnswerId, boolean shared) {
        Logger.debug("addResponse: insert New Response");
        JdbcTemplate template = abstractDao.getJdbcTemplate("addResponse");
        String dataFromMember = " SELECT a.gender, a.ageGroup FROM users a, contacts c, grp_contacts b " +
                " WHERE b.fk_contactid = c.contactid AND c.email = a.email AND b.memberid = ? ";
        String dataFromShare = " SELECT a.gender, a.ageGroup FROM users a, contacts c,  shared_incyyte b " +
                " WHERE b.fk_contactid = c.contactid AND c.email = a.email AND b.share_id = ? ";
        String addlInfo = shared ? dataFromShare : dataFromMember;
        Logger.debug("addlInfo::::;" + addlInfo);
        String incrementInCyyteResponseQuery = UserDaoQueries.getIncrementInCyyteResponseQuery().toString();

        QueryParameters params = new QueryParameters();
        params.addParam(String.valueOf(memberId));
        try {
            //get additional info
            List<Response> responderInfo = QueryHelper.doQuery(abstractDao, "getResponderInfo", addlInfo, params, new AddedResponseInfoRowMapper());
            Logger.debug("responderInfo:::add responses in response " + responderInfo);
            Response response = new Response();
            response.setAnswerId(selectedAnswerId);
            if (responderInfo != null && responderInfo.size() > 0) {
            	response.setGender(responderInfo.get(0).getGender());
            	response.setAgeGroup(responderInfo.get(0).getAgeGroup());
            }
            response.setQuestionId(incyyteID);

            abstractDao.getTxnHelper().beginTxn();
            if (shared) {
                response.setSharedId(memberId);
                responseDao.addSharedResponse(response, template);
            } else {
                response.setMemberId(memberId);
                responseDao.addMemberResponse(response, template);
            }
            QueryHelper.doUpdate(template, incrementInCyyteResponseQuery, QueryParameterFactory.getIncrementResponseParams(incyyteID));
            abstractDao.getTxnHelper().commitTxn();
            //Trigger Vote notification
            triggerResponsesNotification(incyyteID, QuestionType.MAIL);
        } catch (DuplicateKeyException dke) {
            Logger.warn("You have voted already so unable to vote again.");
        } catch (Exception e) {
            Logger.error("addResponse: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addResponse");
        }
    }

    private void triggerResponsesNotification(long questionId, QuestionType type) {
        List<Response> pollResponses = new ArrayList<Response>();
        String pollResultLink = "";
        try {
            switch (type) {
                case MAIL:
                    pollResponses = responseDao.getResponses(questionId);
                    break;
                case POST:
                    pollResponses = responseDao.getPostedResponses(questionId);
                    break;
                case AREA:
                    pollResponses = responseDao.getRegionalResponses(questionId);
            }
            //Trigger notification to the Poll owner based on the interval of comment notification
            int responseNotifyInterval = ConfigProperties.NOTIFY_VOTES_INTERVAL;
            int responseNotifyLimit = ConfigProperties.NOTIFY_VOTES_LIMIT;
            //Get the user details who is the owner of the poll
            if (((pollResponses.size() % responseNotifyInterval) == 0)
                    && (pollResponses.size() <= (responseNotifyInterval * responseNotifyLimit))) {
                QuestionModel ques = quesMgr.getQuestionsdtls(String.valueOf(questionId));
                //Get the email address of the owner of the poll
                User pollOwner = registrationServiceManager.getUser(Long.valueOf(ques.getFk_userid()));
                String pollOwnerUsername = "";
                String pollPagename = "";
                if (pollOwner != null) {
                    pollOwnerUsername = pollOwner.getUsername();
                }
                if (ques != null) {
                    pollPagename = ques.getPageName();
                }
                //Send a Email notification
                switch (type) {
                    case MAIL:
                        pollResultLink = websiteURL + incyyteURL + "?" + "code=" + ques.getViewchart_code();
                        break;
                    case POST:
                        pollResultLink = websiteURL + pollOwnerUsername + "/" + pollPagename + ".cyt";
                        break;
                    case AREA:
                        pollResultLink = websiteURL + incyyteURL + "?" + "code=" + ques.getViewchart_code();
                        break;
                }
                ques.setLink(pollResultLink);
                quickStartServiceManager.sendVotesNotification(pollOwner, ques);
            }
        } catch (Exception e) {
            //Suppress the exception, will not trigger any notification
            Logger.warn("Exception:", e);
        }
    }
}