package com.incyyte.app.service;

import com.incyyte.app.domain.AnonymousQuestion;
import com.incyyte.app.web.model.CommentsModel;

import java.util.List;

/**
 * @author prakash
 */
public interface CommentService {

    /**
     * @param userId
     * @param questionID
     * @param comment
     */
    public void addComment(Long userId, Long questionID, String comment) throws Exception;

    /**
     *
     * @param userId
     * @param includeOwnerComments
     * @return
     */
    public List<AnonymousQuestion> getQuestionComments(Long userId, boolean includeOwnerComments);

    List<String> getPollComments(Long pollId, boolean includeOwnerComments) throws Exception;

    void updateComment(CommentsModel comment) throws Exception;

    public CommentsModel getCommentDetails(CommentsModel commentsModel);
}