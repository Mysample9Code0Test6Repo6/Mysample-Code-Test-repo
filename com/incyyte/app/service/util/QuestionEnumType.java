/**
 * 
 */
package com.incyyte.app.service.util;

/**
 * @author Remi Oseni
 *
 */
public enum QuestionEnumType {
	QType_Q(Constants.QUESTION_TYPE_Q),
	QType_P(Constants.QUESTION_TYPE_P),
	QType_G(Constants.QUESTION_TYPE_G);
	
	private String type;
	
	QuestionEnumType(String type){
		this.type=type;
	}

	public String getType() {
		return type;
	}	

}
