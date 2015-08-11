/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class represents a unique Person reached by a poll
 * For example:
 * <pre>
 * 		
 * </pre>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ Dec 2012
 */
package com.incyyte.app.domain;

public class Person {

	private Long id; //inCyyte userID
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
