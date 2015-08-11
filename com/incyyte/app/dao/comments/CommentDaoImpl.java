package com.incyyte.app.dao.comments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.dao.user.rowmapper.CommentsModelRowMapper;
import com.incyyte.app.domain.AnonymousQuestion;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.CommentsModel;

/**
 * @author prakash
 */
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private AbstractDao abstractDao;

    @Override
    public void addComment(Long userId, Long questionId, String comment) throws Exception {
        String cmntQuery = "INSERT INTO forums(forumid, fk_questionid, comment, created_date, created_by, active_ind ) 	VALUES (?, ?, ?, Sysdate(), ?, 'A')";
        long forumId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("FORUMS_PK", true);
        QueryParameters params = new QueryParameters();
        params.addParam(forumId);
        params.addParam(questionId);
        params.addParam(comment);
        params.addParam(userId);
        QueryHelper.doUpdate(abstractDao, "addComments", cmntQuery, params);
    }

    @Override
    public List<AnonymousQuestion> getQuestions(Long userId) throws DataAccessException {
        Logger.debug("Getting comments:: ");
        JdbcTemplate jdbcTemplate = abstractDao.getJdbcTemplate();

        Object[] args = {userId};
        List<AnonymousQuestion> questions = jdbcTemplate.query(CommentDaoQueries.getQuestionsQuery(), args, new RowMapper<AnonymousQuestion>() {
            @Override
            public AnonymousQuestion mapRow(ResultSet rs, int arg1) throws SQLException {
                AnonymousQuestion anonymQuestion = new AnonymousQuestion();
                Long userId = rs.getLong("fk_userid");
                anonymQuestion.setUserId(userId);

                Long questionId = rs.getLong("questionid");
                Logger.debug("questionId >> " + questionId);
                anonymQuestion.setQuestionId(questionId);

                String question = rs.getString("question");
                anonymQuestion.setQuestion(question);
                return anonymQuestion;
            }
        });

        Logger.debug("questionId.size >> " + questions.size());
        return questions;
    }

    public List<String> getQuestionComments(Long questionId, boolean includeOwnerComments) throws DataAccessException {
        Logger.debug("Getting comments for questions...");
        JdbcTemplate jdbcTemplate = abstractDao.getJdbcTemplate();
        Object[] args = {questionId};
        StringBuilder commentsQuery = new StringBuilder();
        commentsQuery.append("SELECT f.forumid ,f.fk_questionid,f.comment,f.created_date,f.created_by,f.active_ind ");
        commentsQuery.append("  FROM forums f ");
        commentsQuery.append("      ,questions q ");
        commentsQuery.append(" WHERE f.fk_questionid = ? ");
        commentsQuery.append("   AND f.active_ind = 'A' ");
        commentsQuery.append("   AND f.fk_questionid = q.questionid ");
        if (!includeOwnerComments) {
            commentsQuery.append("   AND f.created_by <> q.fk_userid ");
        }
        List<String> comments = jdbcTemplate.query(commentsQuery.toString(), args, new RowMapper<String>() {
        	@Override
        	public String mapRow(ResultSet rs, int arg1) throws SQLException {
        		String comment = rs.getString("comment");
        		comment = StringUtils.replace(comment, "\n", "<br>");
        		comment = comment.replaceAll("(\\r|\\n)", "");
        		return comment;
        	}
        });
        Logger.debug("comments.size():: " + comments.size());
        return comments;
    }

    @Override
    public void updateComment(CommentsModel comment) throws Exception {
        Logger.debug("CommentsModel::" + comment);
        String updateCommentQuery = "UPDATE forums " +
                " SET upload_type = ? " +
                "    ,upload_location = ? " +
                "    ,cdn_file_name = ? " +
                "    ,youtube_url = ? " +
                " WHERE forumid = ? " ;

        Logger.debug("updateCommentQuery::" + updateCommentQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(comment.getUploadCommentType());
        params.addParam(comment.getUploadCommentLocation());
        params.addParam(comment.getUploadCommentCdnFileName());
        params.addParam(comment.getYoutubeCommentVideoURL());
        params.addParam(comment.getCommentid());
        QueryHelper.doUpdate(abstractDao, "updateComment", updateCommentQuery, params);
    }

    @Override
    public CommentsModel getCommentDetails(CommentsModel commentsModel) {
    	Logger.debug("forumid ::"+commentsModel);
    	String getCommentDetailsQuery = "select f.forumid , f.fk_questionid, f.comment, f.created_date,f.created_by, f.active_ind, f.upload_type,  f.upload_location COMMENT_UPLOAD_LOCATION, f.cdn_file_name COMMENT_CDN_FILE_NAME, f.youtube_url youtube_url, u.UPLOAD_LOCATION , u.CDN_FILE_NAME  from forums f, users u where  f.created_by = u.userid and f.forumid =  ?";
    	QueryParameters params = new QueryParameters();
    	params.addParam(commentsModel.getCommentid());
    	try {
    		commentsModel = (CommentsModel) QueryHelper.doQueryForObject(abstractDao, "getCommentDetails", getCommentDetailsQuery, params, new CommentsModelRowMapper());
    		return commentsModel ;
    	} catch (EmptyResultDataAccessException er) {
    		Logger.warn("No Records exists and hence returning empty for comentId:" + commentsModel);
    	}
    	return null;
    }
}