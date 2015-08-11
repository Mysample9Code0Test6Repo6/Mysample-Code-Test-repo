/**
 * 
 */
package com.incyyte.app.domain;

/**
 * @author prakash
 *
 */
public class Forum {
	
	private Long forumId;
	private Long questionId;
	private String comment;
	
	/**
	 * @return the forumId
	 */
	public Long getForumId() {
		return forumId;
	}
	/**
	 * @param forumId the forumId to set
	 */
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
}
