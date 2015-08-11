/**
 * 
 */
package com.incyyte.app.web.model;

import java.util.Date;

import com.incyyte.app.service.util.Utility;


/**
 * @author Remi Oseni
 *
 */
public class VoteModel {
	
	private long incyyteId;
	private long userId;
	private String selectedAnswer;
	private String gender;
	private String ageGroup;
	private Date responseDate;

	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public Date getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
		   
	}

    public long getIncyyteId() {
		return incyyteId;
	}
	public void setIncyyteId(long incyyteId) {
		this.incyyteId = incyyteId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getSelectedAnswer() {
		return selectedAnswer;
	}
	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	@Override
	public String toString() {
		return "VoteModel [incyyteId=" + incyyteId + ", userId=" + userId
				+ ", selectedAnswer=" + selectedAnswer + ", gender=" + gender
				+ ", ageGroup=" + ageGroup + ", responseDate=" + responseDate
				+ "]";
	}
	
	
}
