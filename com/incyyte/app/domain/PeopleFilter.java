/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class represents a People filter object add new parameters to filter
 * Data passed through the setters must be directly from the refData
 * This will be used to directly the database.
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

public class PeopleFilter {
	
	private String ageGroup;
	private String gender;
	private String category;
	private Integer minAge;
	private Integer maxAge;
	
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	public Integer getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

}
