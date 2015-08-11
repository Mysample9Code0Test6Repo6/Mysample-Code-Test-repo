/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class represents a response from a Cyytee
 * For example:
 * <pre>
 * 		131 Grifon Road, Chafford Hundred Grays. RM16 6RL
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.domain;

import java.io.Serializable;

public class CyyteResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long answerId;
	private String answer;
	private double response;

	public CyyteResponse(Long id, String answer, double response) {
		this.answerId = id;
		this.answer = answer;
		this.response = response;
	}

	/**
	 * @return Returns the answer.
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            The answer to set.
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return Returns the response.
	 */
	public double getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            The response to set.
	 */
	public void setResponse(double response) {
		this.response = response;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	@Override
	public String toString() {
		return "CyyteResponse [answerId=" + answerId + ", answer=" + answer
				+ ", response=" + response + "]";
	}

}
