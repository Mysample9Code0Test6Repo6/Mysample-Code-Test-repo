package com.incyyte.app.dao.comments;

import com.incyyte.app.domain.AnonymousQuestion;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.web.model.CommentsModel;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author prakash
 */
public interface CommentDao {

    /**
     * @param userId
     * @param questionID
     * @param comment
     */
    public void addComment(Long userId, Long questionID, String comment) throws Exception;

    /**
     * @param userId
     * @return
     */
    public List<AnonymousQuestion> getQuestions(Long userId) throws DataAccessException;

    /**
     *
     * @param questionId
     * @param includeOwnerComments
     * @return
     */
    public List<String> getQuestionComments(Long questionId, boolean includeOwnerComments) throws DataAccessException;

    void updateComment(CommentsModel comment) throws Exception;

    public CommentsModel getCommentDetails(CommentsModel commentsModel);
}
