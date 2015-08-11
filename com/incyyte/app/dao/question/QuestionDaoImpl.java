package com.incyyte.app.dao.question;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.question.rowmapper.Questionrowmapper;
import com.incyyte.app.dao.user.rowmapper.InCyyteRowMapper;
import com.incyyte.app.domain.Answer;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.ContactService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.QuestionModel;
import com.incyyte.app.web.model.Questionlist;
import com.incyyte.app.web.model.UserContactModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    private AbstractDao abstractDao;

    @Autowired
    private ContactService contactsSrv;

    @SuppressWarnings("unchecked")
    @Override
    public Questionlist searchQuest(QuestionModel quesModel, Long id) {
        String searchquery = QuestionDaoQueries.getSearchquery().toString();
        QueryParameters qp = new QueryParameters();
        qp.addParam(quesModel.getQuestion());
        Questionlist searchlist = new Questionlist();
        try {
            List<QuestionModel> queslist = QueryHelper.doQuery(abstractDao, "searchQuest" , searchquery, qp, new Questionrowmapper());
            searchlist.setQueslist((ArrayList<QuestionModel>) queslist);
        } catch (Exception e) {
            Logger.error("getUserDetails: Failed ", e);
        }
        return searchlist;
    }

    @Override
    public QuestionModel getQuestionsdtls(String questionId) {
        String dtlsquery = QuestionDaoQueries.getDtlsquery().toString();
        QuestionModel quedtls = new QuestionModel();
        QueryParameters qp = new QueryParameters();
        qp.addParam(questionId);
        try {
            List<QuestionModel> queslist = QueryHelper.doQuery(abstractDao, "getQuestionsdtls" , dtlsquery, qp, new Questionrowmapper());
            quedtls = queslist.get(0);
        } catch (Exception e) {
            Logger.error("getUserDetails: Failed ", e);
        }
        return quedtls;
    }

    @Override
    public List<Long> getQuestionsByGroupId(Long groupId) throws Exception {
        Logger.debug("Inside : getQuestionsByGroupId");
        Logger.debug("groupId: " + groupId);
        String questionsByGroupQuery = "select fk_questionid from incyyte where fk_groupid = ? ";
        QueryParameters param = new QueryParameters();
        param.addParam(groupId);
        List<Long> questions = QueryHelper.doQuery(abstractDao, "getQuestionsByGroupId" , questionsByGroupQuery, param, Long.class);
        Logger.debug("questions::" + questions);
        return questions;
    }

    @Override
    public Long getRecipientsCount(Long questionId) throws Exception {
        Logger.debug("questionId: " + questionId);
        String recipientCountQuery = "select total_incyyted  from incyyte where fk_questionid = ? ";
        QueryParameters param = new QueryParameters();
        param.addParam(questionId);
        Long recipientCount = QueryHelper.doQueryForLong(abstractDao, "getRecipientsCount" , recipientCountQuery, param);
        Logger.debug("recipientCount::" + recipientCount);
        return recipientCount;
    }

    @Override
    public void getRecipientsGenderCount(InCyyte incyyte, User loggedInUser) throws Exception {
        Logger.debug("incyyte: " + incyyte);
        List<Answer> answers = incyyte.getAnswers();
        Logger.debug("answers: " + answers);
        String recipientCountQuery = null;
        if (StringUtils.equals(incyyte.getSendType(), "post")) {
            recipientCountQuery = "select gender, count(*) cnt from posted_responses where fk_answerid = ? group by gender ";
        } else if (StringUtils.equals(incyyte.getSendType(), "area")) {
            recipientCountQuery = "select gender, count(*) cnt from regional_responses where fk_answerid = ? group by gender ";
        } else if (StringUtils.equals(incyyte.getSendType(), "mail")) {
            recipientCountQuery = "select gender, count(*) cnt from responses where fk_answerid = ? group by gender ";
        }
        Logger.debug("recipientCountQuery" + recipientCountQuery);
        for (Answer answer : answers) {
            Logger.debug("answerId: " + answer);
            QueryParameters param = new QueryParameters();
            param.addParam(answer.getId());
            List<Map<String, String>> recipientsGenderCount = QueryHelper.doQueryForList(abstractDao, "getRecipientsGenderCount" , recipientCountQuery, param);
            Logger.debug("recipientsGenderCount" + recipientsGenderCount);
            for (Map<String, String> recipientGenderCount : recipientsGenderCount) {
                if (StringUtils.equals(recipientGenderCount.get("GENDER"), "Male")) {
                    answer.setMaleCount(String.valueOf(recipientGenderCount.get("CNT")));
                }
                if (StringUtils.equals(recipientGenderCount.get("GENDER"), "Female")) {
                    answer.setFemaleCount(String.valueOf(recipientGenderCount.get("CNT")));
                }
                if (StringUtils.isBlank(recipientGenderCount.get("GENDER"))) {
                    answer.setUnspecifiedCount(String.valueOf(recipientGenderCount.get("CNT")));
                }
            }
        }
        String votedRecipients = null;
        String votedMailRecipients = null;
        if (StringUtils.equals(incyyte.getSendType(), "post")) {
            votedRecipients = "SELECT u.username, u.email, ud.postcode,u.gender,u.cdn_file_name " +
                              "  FROM posted_responses pr "+
                              "  LEFT OUTER JOIN users u ON pr.fk_userid = u.userid " +
                              "  LEFT OUTER JOIN user_domain ud ON u.userid = ud.user_id " +
                              "  WHERE pr.fk_answerid = ?";
        } else if (StringUtils.equals(incyyte.getSendType(), "area")) {
            votedRecipients = "SELECT u.username, u.email, ud.postcode,u.gender,u.cdn_file_name " +
                              "  FROM regional_responses rr "+
                              "  LEFT OUTER JOIN users u ON rr.fk_userid = u.userid " +
                              "  LEFT OUTER JOIN user_domain ud ON u.userid = ud.user_id " +
                              "  WHERE rr.fk_answerid = ? ";
        } else if (StringUtils.equals(incyyte.getSendType(), "mail")) {
            votedMailRecipients = "SELECT u.username, u.email, ud.postcode,u.gender,u.cdn_file_name " +
                    "FROM (SELECT si.fk_contactid FROM responses r ,shared_incyyte si " +
                    "       WHERE r.fk_sharedid = si.share_id AND r.fk_answerid = ? " +
                    "     UNION" +
                    "      SELECT gc.fk_contactid FROM responses r,grp_contacts gc " +
                    "       WHERE gc.memberid = r.fk_memberid AND r.fk_answerid = ?) tmp" +
                    " STRAIGHT_JOIN contacts c ON tmp.fk_contactid = c.contactid " +
                    " LEFT OUTER JOIN users u ON c.email = u.email " +
                    " LEFT OUTER JOIN user_domain ud ON u.userid = ud.user_id ";
        }
        Logger.debug("votedRecipients" + votedRecipients);
        try {
            for (Answer answer : answers) {
                Logger.debug("answerId: " + answer);
                QueryParameters param = new QueryParameters();
                param.addParam(answer.getId());
                if (StringUtils.isNotBlank(votedMailRecipients)) {
                    votedRecipients = votedMailRecipients;
                    param.addParam(answer.getId());
                }

                List<User> users = QueryHelper.doQuery(abstractDao, "getRecipientsGenderCount" , votedRecipients, param, new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int arg1) throws SQLException {
                        User user = new User();
                        user.setUsername(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                        user.setPostalCodeArea(rs.getString("postcode"));
                        user.setGender(rs.getString("gender"));
                        user.setProfilePicture(rs.getString("cdn_file_name"));
                        return user;
                    }
                });
                Logger.debug("votedRecipients" + users);
                List<User> maleUsers = new ArrayList<User>();
                List<User> femaleUsers = new ArrayList<User>();
                List<User> unSpecifiedUsers = new ArrayList<User>();
                UserContactModel contact;
                for (User user : users) {
                    contact = contactsSrv.contactOfUser(user.getEmail(), loggedInUser.getId());
                    if (contact != null && StringUtils.equals(contact.getActive_ind(), "A")) {
                        user.setIsContact("Y");
                    } else {
                        user.setIsContact("N");
                    }
                    if (StringUtils.equalsIgnoreCase(user.getGender(), "Male")) {
                        maleUsers.add(user);
                    }
                    if (StringUtils.equalsIgnoreCase(user.getGender(), "Female")) {
                        femaleUsers.add(user);
                    }
                    if (StringUtils.isBlank(user.getGender())) {
                        unSpecifiedUsers.add(user);
                    }
                }
                Logger.debug("maleusers" + maleUsers);
                Logger.debug("femaleUsers" + femaleUsers);
                Logger.debug("unspecifiedUsers" + unSpecifiedUsers);
                answer.setMaleRecord(maleUsers);
                answer.setFemaleRecord(femaleUsers);
                answer.setUnSpecifiedRecord(unSpecifiedUsers);
            }
        } catch (Exception e) {
            Logger.error("while fetching voted recipients" , e);
        }
    }

    @Override
    public Map<String, Map<String, String>> getPublicPolls() throws Exception {
    	Logger.info("Inside getPublicPolls():QuestionDaoImpl");
        StringBuilder publicPollsQuery = new StringBuilder();
        publicPollsQuery.append("select q.category ");
        publicPollsQuery.append("      ,GROUP_CONCAT(q.viewchart_code ORDER BY q.questionid DESC) polls ");
        publicPollsQuery.append("      ,MAX(q.viewchart_code) latest_poll ");
        publicPollsQuery.append("      ,count(1) cnt ");
        publicPollsQuery.append("  from questions q ");
        publicPollsQuery.append("      ,incyyte i ");
        publicPollsQuery.append(" where 1 = 1 ");
        publicPollsQuery.append(" and q.questionid = i.fk_questionid ");
        publicPollsQuery.append(" and q.public_poll = 'Y' ");
        publicPollsQuery.append(" and q.delete_ind = 'N' ");
        publicPollsQuery.append(" and q.display_public_poll = 'Y' ");
        publicPollsQuery.append(" and q.category  != '' ");
        publicPollsQuery.append(" and (q.cdn_file_name is not null or q.youtube_url is not null) ");
        publicPollsQuery.append(" and i.closure_date >= sysdate() ");
        publicPollsQuery.append(" group by q.category ");
        publicPollsQuery.append(" having count(1) > 0 ");
        publicPollsQuery.append(" order by cnt desc");
        JdbcTemplate template = abstractDao.getJdbcTemplate("getPublicPolls");

        Map<String, Map<String, String>> values = (Map<String, Map<String, String>>) template.query(publicPollsQuery.toString(), new ResultSetExtractor() {
            public Map<String, Map<String, String>> extractData(ResultSet rs) throws SQLException {
                Map<String, Map<String, String>> map = new LinkedHashMap<String, Map<String, String>>();
                while (rs.next()) {
                    String key = rs.getString("CATEGORY");
                    Map<String, String> categoryValues = new HashMap<String, String>();
                    categoryValues.put("POLLS" , rs.getString("POLLS"));
                    categoryValues.put("LATEST_POLL" , rs.getString("LATEST_POLL"));
                    categoryValues.put("COUNT" , rs.getString("CNT"));
                    map.put(key, categoryValues);
                }
                return map;
            }
        });
        return values;
    }

    @Override
    public List<InCyyte> getPublicPolls(int offset, int recordsPerPage, String searchString) throws Exception {
    	Logger.debug("Search string::::" + searchString);
        StringBuilder query = new StringBuilder();
        query.append(" SELECT q.questionid, q.fk_userid, q.question, q.category, q.upload, q.upload_type, q.upload_name, ");
        query.append(" q.upload_location, q.content_type, q.randomize, q.multi_selection, q.xmlString, q.background, ");
        query.append(" q.viewchart_code, q.link, i.total_incyyted, i.total_responded, q.anonymity, q.page_name, q.type, ");
        query.append(" q.allow_comment, q.upload_logo_location, q.strapline, q.protectPage, q.public_poll, q.access_code, ");
        query.append(" q.cdn_file_name, q.poll_Result_Hidden ,q.youtube_url, i.sent_date, i.closure_date, q.delete_ind, ");
        query.append(" q.publish_ind, q.created_by , i.fk_groupid, i.send_method ,i.send_zone, q.sendType, q.poll_page_template ");
        query.append(" from questions q ");
        query.append(" ,incyyte i ");
        query.append(" ,users u ");
        query.append(" where 1 = 1 ");
        query.append(" and q.questionid = i.fk_questionid ");
        query.append(" and q.public_poll = 'Y' ");
        query.append(" and q.delete_ind = 'N' ");
        query.append(" and q.display_public_poll = 'Y' ");
        query.append(" and q.category  != '' ");
        query.append(" and (q.cdn_file_name is not null or q.youtube_url is not null) ");
        query.append(" and q.fk_userid = u.userid ");
        query.append(" and (q.question like ? ");
        query.append(" or u.username like ? ");
        query.append(" or u.firstname like ? ");
        query.append(" or u.lastname like ? ");
        query.append(" or u.email like ?) ");
        query.append(" and i.closure_date >= sysdate() ");
        query.append(" order by q.created_date desc ");
        if (recordsPerPage != 0) {
            String limit = " limit " + recordsPerPage + " offset " + offset;
            query = query.append(limit);
        }
        QueryParameters params = new QueryParameters();
        String searchValue = "%" + searchString + "%";
        params.addParam(searchValue);
        params.addParam(searchValue);
        params.addParam(searchValue);
        params.addParam(searchValue);
        params.addParam(searchValue);
        Logger.debug("query::" + query.toString());
        Logger.debug("Search String::" + searchValue);
        List<InCyyte> incyytes = QueryHelper.doQuery(abstractDao, "getPublicPollsSearched" , query.toString(), params, new InCyyteRowMapper());
        Logger.debug("incyytes::" + incyytes);
        return incyytes;
    }

    @Override
    public List<InCyyte> getPollsByCategory(int offset, int recordsPerPage, String category) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT q.questionid, q.fk_userid, q.question, q.category, q.upload, q.upload_type, q.upload_name, ");
        query.append(" q.upload_location, q.content_type, q.randomize, q.multi_selection, q.xmlString, q.background, ");
        query.append(" q.viewchart_code, q.link, i.total_incyyted, i.total_responded, q.anonymity, q.page_name, q.type, ");
        query.append(" q.allow_comment, q.upload_logo_location, q.strapline, q.protectPage, q.public_poll, q.access_code, ");
        query.append(" q.cdn_file_name, q.poll_Result_Hidden ,q.youtube_url,q.poll_page_template, i.sent_date, i.closure_date, q.delete_ind, ");
        query.append(" q.publish_ind, q.created_by , i.fk_groupid, i.send_method ,i.send_zone, q.sendType ");
        query.append(" from questions q ");
        query.append(" ,incyyte i ");
        query.append(" where 1 = 1 ");
        query.append(" and q.questionid = i.fk_questionid ");
        query.append(" and q.public_poll = 'Y' ");
        query.append(" and q.display_public_poll = 'Y' ");
        query.append(" and q.delete_ind = 'N' ");
        query.append(" and q.category  != '' ");
        query.append(" and q.cdn_file_name is not null ");
        query.append(" and q.upload_type = 'image' ");
        query.append(" and i.closure_date >= sysdate() ");
        if (StringUtils.isNotBlank(category)) {
            query.append(" and q.category = ? ");
        }
        query.append(" order by q.created_date desc ");
        if (recordsPerPage != 0) {
            String limit = " limit " + recordsPerPage + " offset " + offset;
            query = query.append(limit);
        }
        List<InCyyte> incyytes;
        if (StringUtils.isNotBlank(category)) {
            QueryParameters params = new QueryParameters();
            params.addParam(category);
            incyytes = QueryHelper.doQuery(abstractDao, "getLatestPolls" , query.toString(), params, new InCyyteRowMapper());
        } else {
            incyytes = QueryHelper.doQuery(abstractDao, "getLatestPolls" , query.toString(), null, new InCyyteRowMapper());
        }
        return incyytes;
    }

    @Override
    public List<InCyyte> getAllPolls(int offset , int recordsPerPage) throws Exception {
        Logger.info("Inside getAllPolls():QuestionDaoImpl");
        StringBuilder allPolls = new StringBuilder();
        allPolls.append(" SELECT q.questionid, q.fk_userid, q.question, q.category, q.upload, q.upload_type, q.upload_name, ");
        allPolls.append(" q.upload_location, q.content_type, q.randomize, q.multi_selection, q.xmlString, q.background,  ");
        allPolls.append(" q.viewchart_code, q.link, i.total_incyyted, i.total_responded, q.anonymity, q.page_name, q.type,  ");
        allPolls.append(" q.allow_comment, q.upload_logo_location, q.strapline, q.protectPage, q.public_poll, q.access_code,  ");
        allPolls.append(" q.cdn_file_name, q.poll_Result_Hidden ,q.youtube_url, i.sent_date, i.closure_date, q.delete_ind, ");
        allPolls.append(" q.publish_ind, q.created_by , i.fk_groupid, i.send_method ,i.send_zone, q.sendType, q.poll_page_template, ");
        allPolls.append(" q.display_public_poll ");
        allPolls.append(" from questions q ");
        allPolls.append(" ,incyyte i ");
        allPolls.append(" where 1 = 1 ");
        allPolls.append(" and q.display_public_poll is null ");
        allPolls.append(" and q.questionid = i.fk_questionid ");
        allPolls.append(" and q.public_poll = 'Y' ");
        allPolls.append(" and q.delete_ind = 'N' ");
        allPolls.append(" and (q.cdn_file_name is not null or q.youtube_url is not null) ");
        allPolls.append(" and i.closure_date >= sysdate() ");       
        Logger.info("allPolls"+allPolls);
        if (recordsPerPage != 0) {
            String limit = " limit " + recordsPerPage + " offset " + offset;
            allPolls = allPolls.append(limit);
        }
        /*QueryParameters params = new QueryParameters();        
        List<InCyyte> incyytes = QueryHelper.doQuery(abstractDao, "getAllPolls" , allPolls.toString(), params, new InCyyteRowMapper());
        Logger.debug("incyytes::" + incyytes);
        return incyytes;*/        
        List<InCyyte> incyytes = QueryHelper.doQuery(abstractDao, "getAllPolls" , allPolls.toString(), null, new InCyyteRowMapper());
        Logger.info("incyytes:" + incyytes);
        return incyytes;
    }

	@Override
	public void updateQuestion(String questionId, String name) throws Exception {
		String updateQuestionQuery = "update questions set display_public_poll = ? where questionid = ?";
		Logger.info("updateQuestionQuery:" + updateQuestionQuery);
		JdbcTemplate template = abstractDao.getJdbcTemplate("updateQuestion");		 
		try {
			QueryParameters	params=new QueryParameters();
			params.addParam(name);
			params.addParam(questionId);
			QueryHelper.doUpdate(template, updateQuestionQuery, params);
		} catch (Exception e) {
			Logger.error("Exception", e);
			throw e;
		}
	}
}