package com.incyyte.app.dao;

import com.incyyte.app.domain.Response;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface ResponseDao {

    void addPostedResponse(Response response, JdbcTemplate template) throws Exception;

    void addRegionalResponse(Response response, JdbcTemplate template) throws Exception;

    void addMemberResponse(Response response, JdbcTemplate template) throws Exception;

    void addSharedResponse(Response response, JdbcTemplate template) throws Exception;

    List<Response> getPostedResponses(long questionId) throws Exception;

    List<Response> getRegionalResponses(long questionId) throws Exception;

    List<Response> getResponses(long questionId) throws Exception;
}