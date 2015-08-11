package com.incyyte.app.dao;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.domain.Response;
import com.incyyte.app.service.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ResponseDaoImpl implements ResponseDao {

    @Autowired
    private AbstractDao abstractDao;

    @Override
    public void addPostedResponse(Response response, JdbcTemplate template) throws Exception {
        String insertPostedResponse = "INSERT INTO posted_responses (fk_questionid, fk_userid, fk_answerid,gender,ageGroup,responseDate) VALUES(?,?,?,?,?,SYSDATE())";
        Logger.debug("insertPostedResponse: " + insertPostedResponse);
        QueryParameters params = new QueryParameters();
        params.addParam(response.getQuestionId());
        params.addParam(response.getUserId());
        params.addParam(response.getAnswerId());
        params.addParam(response.getGender());
        params.addParam(response.getAgeGroup());
        try {
            QueryHelper.doUpdate(template, insertPostedResponse, params);
        } catch (Exception e) {
            Logger.error("addPostedResponse:", e);
        }
    }

    @Override
    public void addRegionalResponse(Response response, JdbcTemplate template) throws Exception {
        Logger.debug("addResponse: insert New Regional Response");
        String insertRegionalResponse = "INSERT INTO regional_responses(fk_answerid, fk_userid, fk_questionid,gender,ageGroup, responseDate) VALUES(?,?,?,?,?,SYSDATE())";
        try {
            QueryParameters params = new QueryParameters();
            params.addParam(response.getAnswerId());
            params.addParam(response.getUserId());
            params.addParam(response.getQuestionId());
            params.addParam(response.getGender());
            params.addParam(response.getAgeGroup());
            QueryHelper.doUpdate(template, insertRegionalResponse, params);
        } catch (Exception e) {
            Logger.error("addRegionalResponse:", e);
        }
    }

    @Override
    public void addMemberResponse(Response response, JdbcTemplate template) throws Exception {    	
        String insertResponse = "INSERT INTO responses (fk_memberid, fk_answerid,fk_questionid,gender,ageGroup, responseDate)" +
        		"VALUES(?,?,?,?,?,SYSDATE())";
        QueryParameters params = new QueryParameters();
        params.addParam(response.getMemberId());
        params.addParam(response.getAnswerId());
        params.addParam(response.getQuestionId());
        params.addParam(response.getGender());
        params.addParam(response.getAgeGroup());
        try {
            QueryHelper.doUpdate(template, insertResponse, params);
        } catch (Exception e) {
            Logger.error("addResponse: Failed ", e);
        }
    }

    @Override
    public void addSharedResponse(Response response, JdbcTemplate template) throws Exception {
        String insertResponse = "INSERT INTO responses (fk_sharedid, fk_answerid,fk_questionid,gender,ageGroup, responseDate) VALUES(?,?,?,?,?,SYSDATE())";
        QueryParameters params = new QueryParameters();
        params.addParam(response.getSharedId());
        params.addParam(response.getAnswerId());
        params.addParam(response.getQuestionId());
        params.addParam(response.getGender());
        params.addParam(response.getAgeGroup());
        try {
            QueryHelper.doUpdate(template, insertResponse, params);
        } catch (Exception e) {
            Logger.error("addSharedResponse: Failed ", e);
        }
    }

    @Override
    public List<Response> getPostedResponses(long questionId) throws Exception {
        String selectResponse = "SELECT * FROM posted_responses WHERE fk_questionid = ?";
        QueryParameters param = new QueryParameters();
        param.addParam(questionId);
        return QueryHelper.doQuery(abstractDao, "getPostedResponses", selectResponse, param, new PostedResponseRowMapper());
    }

    @Override
    public List<Response> getRegionalResponses(long questionId) throws Exception {
        String selectResponse = "SELECT * FROM regional_responses WHERE fk_questionid = ?";
        QueryParameters param = new QueryParameters();
        param.addParam(questionId);
        return QueryHelper.doQuery(abstractDao, "getRegionalResponses", selectResponse, param, new RegionalResponseRowMapper());
    }

    @Override
    public List<Response> getResponses(long questionId) throws Exception {
        String selectResponse = "SELECT * FROM responses WHERE fk_questionid = ?";
        QueryParameters param = new QueryParameters();
        param.addParam(questionId);
        return QueryHelper.doQuery(abstractDao, "getPostedResponses", selectResponse, param, new ResponseRowMapper2());
    }

    private class PostedResponseRowMapper implements RowMapper<Response> {
        public Response mapRow(ResultSet rs, int currentRow) throws SQLException {
            Response response = new Response();
            response.setPostResponseId(rs.getLong("POSTID"));
            response.setQuestionId(rs.getLong("FK_QUESTIONID"));
            response.setUserId(rs.getLong("FK_USERID"));
            response.setAnswerId(rs.getLong("FK_ANSWERID"));
            response.setGender(rs.getString("GENDER"));
            response.setAgeGroup(rs.getString("AGEGROUP"));
            return (response);
        }
    }

    private class RegionalResponseRowMapper implements RowMapper<Response> {
        public Response mapRow(ResultSet rs, int currentRow) throws SQLException {
            Response response = new Response();
            response.setRegionResponseId(rs.getLong("REGIONAL_RESPID"));
            response.setQuestionId(rs.getLong("FK_QUESTIONID"));
            response.setUserId(rs.getLong("FK_USERID"));
            response.setAnswerId(rs.getLong("FK_ANSWERID"));
            response.setGender(rs.getString("GENDER"));
            response.setAgeGroup(rs.getString("AGEGROUP"));
            return (response);
        }
    }

    private class ResponseRowMapper2 implements RowMapper<Response> {
        public Response mapRow(ResultSet rs, int currentRow) throws SQLException {
            Response response = new Response();
            response.setMemberId(rs.getLong("FK_MEMBERID"));
            response.setSharedId(rs.getLong("FK_SHAREDID"));
            response.setAnswerId(rs.getLong("FK_ANSWERID"));
            response.setGender(rs.getString("GENDER"));
            response.setAgeGroup(rs.getString("AGEGROUP"));
            return (response);
        }
    }
}