package com.incyyte.app.dao.question.rowmapper;

import com.incyyte.app.web.model.QuestionModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Questionrowmapper implements RowMapper {
    public Questionrowmapper() {
        super();
    }

    public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
        QuestionModel ques = new QuestionModel();
        ques.setBackground(rs.getString("background"));
        ques.setCategory(rs.getString("category"));
        ques.setContent_type(rs.getString("content_type"));
        ques.setEmailResponses(rs.getString("emailResponses"));
        ques.setFk_userid(rs.getString("fk_userid"));
        ques.setLink(rs.getString("link"));
        ques.setMulti_selection(rs.getString("multi_selection"));
        ques.setQuestion(rs.getString("question"));
        ques.setQuestionid(rs.getString("questionid"));
        ques.setRandomize(rs.getString("randomize"));
        ques.setUpload(rs.getString("upload"));
        ques.setUpload_ext(rs.getString("upload_ext"));
        ques.setViewchart_code(rs.getString("viewchart_code"));
        ques.setXmlString(rs.getString("xmlString"));
        ques.setSendType(rs.getString("sendType"));
        ques.setPageName(rs.getString("page_name"));
        return (ques);
    }
}