package com.incyyte.app.service;

import com.incyyte.app.dao.comments.CommentDao;
import com.incyyte.app.domain.AnonymousQuestion;
import com.incyyte.app.web.model.CommentsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author prakash
 */
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    /* (non-Javadoc)
     * @see com.incyyte.app.service.CommentService#addComment(java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public void addComment(Long userId, Long questionID, String comment) throws Exception {
        commentDao.addComment(userId, questionID, comment);
    }

    @Override
    public List<AnonymousQuestion> getQuestionComments(Long userId, boolean includeOwnerComments) throws DataAccessException {
        List<AnonymousQuestion> questions = commentDao.getQuestions(userId);
        for (AnonymousQuestion question : questions) {
            List<String> comments = commentDao.getQuestionComments(question.getQuestionId(), includeOwnerComments);
            question.setComments(comments);
        }
        return questions;
    }

    @Override
    public List<String> getPollComments(Long pollId, boolean includeOwnerComments) throws Exception {
        return commentDao.getQuestionComments(pollId, includeOwnerComments);
    }

    @Override
    public void updateComment(CommentsModel comment) throws Exception {
        commentDao.updateComment(comment);
    }

    @Override
    public CommentsModel getCommentDetails(CommentsModel commentsModel) {
        return commentDao.getCommentDetails(commentsModel);
    }
}