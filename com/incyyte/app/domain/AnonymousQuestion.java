/**
 * 
 */
package com.incyyte.app.domain;

import java.util.List;

/**
 * @author prakash
 *
 */
public class AnonymousQuestion {
	
	Long questionId;
	Long userId;
	String question;
	
	List<String> comments = null;
	
	/**
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return
	 */
	public List<String> getComments() {
		return comments;
	}
	/**
	 * @param comments
	 */
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
}
