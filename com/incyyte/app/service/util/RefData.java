package com.incyyte.app.service.util;

import java.util.Map;

public class RefData {
	
	private Map<String,String> inCyyteType;
	private Map<String,String> gender;
	private Map<String,String> yesNo;
	private Map<String,String> ageGroup;
	private Map<String,String> birthYear;
	private Map<String,String> styleOptions;
	private Map<String,String> anonymity;
	private Map<String,String> inCyyteCategories;
	private Map<String,String> countries;
	private Map<String,String> groupType;
	private Map<String,String> sexualities;
	private Map<String,String> months;
	private Map<String,String> incomes;
	private int PageLimit;
	private int SubPageLimit;
	private int birthYearLimit;
	private Map<String,String> tenantDetails;
	private Map<String,String> ageMin;
	private Map<String,String> ageMax;

    private Map<String,String> ethnicity;
    private Map<String,String> education_level;
    private Map<String,String> adults_in_houseHold;
    private Map<String,String> children_in_houseHold;

    public Map<String, String> getInCyyteType() {
		return inCyyteType;
	}
	public void setInCyyteType(Map<String, String> inCyyteType) {
		this.inCyyteType = inCyyteType;
	}
	public Map<String, String> getGender() {
		return gender;
	}
	public void setGender(Map<String, String> gender) {
		this.gender = gender;
	}
	public Map<String, String> getYesNo() {
		return yesNo;
	}
	public void setYesNo(Map<String, String> yesNo) {
		this.yesNo = yesNo;
	}
	public Map<String, String> getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(Map<String, String> ageGroup) {
		this.ageGroup = ageGroup;
	}
	public Map<String, String> getStyleOptions() {
		return styleOptions;
	}
	public void setStyleOptions(Map<String, String> styleOptions) {
		this.styleOptions = styleOptions;
	}
	public Map<String, String> getAnonymity() {
		return anonymity;
	}
	public void setAnonymity(Map<String, String> anonymity) {
		this.anonymity = anonymity;
	}
	public Map<String, String> getInCyyteCategories() {
		return inCyyteCategories;
	}
	public void setInCyyteCategories(Map<String, String> inCyyteCategories) {
		this.inCyyteCategories = inCyyteCategories;
	}
	public Map<String, String> getCountries() {
		return countries;
	}
	public void setCountries(Map<String, String> countries) {
		this.countries = countries;
	}
	public Map<String, String> getGroupType() {
		return groupType;
	}
	public void setGroupType(Map<String, String> groupType) {
		this.groupType = groupType;
	}
	public void setPageLimit(int pageLimit) {
		PageLimit = pageLimit;
	}
	public int getPageLimit() {
		return PageLimit;
	}
	
	public void setSubPageLimit(int pageLimit) {
		SubPageLimit = pageLimit;
	}
	public int getSubPageLimit() {
		return SubPageLimit;
	}
	
	public Map<String, String> getSexualities() {
		return sexualities;
	}
	public void setSexualities(Map<String, String> sexualities) {
		this.sexualities = sexualities;
	}
	public Map<String, String> getMonths() {
		return months;
	}
	public void setMonths(Map<String, String> months) {
		this.months = months;
	}
	public Map<String, String> getIncomes() {
		return incomes;
	}
	public void setIncomes(Map<String, String> incomes) {
		this.incomes = incomes;
	}
	public Map<String, String> getTenantDetails() {
		return tenantDetails;
	}
	public void setTenantDetails(Map<String, String> tenantDetails) {
		this.tenantDetails = tenantDetails;
	}
	public Map<String, String> getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(Map<String, String> birthYear) {
		this.birthYear = birthYear;
	}
	public Map<String, String> getAgeMin() {
		return ageMin;
	}
	public void setAgeMin(Map<String, String> ageMin) {
		this.ageMin = ageMin;
	}
	public Map<String, String> getAgeMax() {
		return ageMax;
	}
	public void setAgeMax(Map<String, String> ageMax) {
		this.ageMax = ageMax;
	}
	public int getBirthYearLimit() {
		return birthYearLimit;
	}
	public void setBirthYearLimit(int birthYearLimit) {
		this.birthYearLimit = birthYearLimit;
	}

    public Map<String, String> getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Map<String, String> ethnicity) {
        this.ethnicity = ethnicity;
    }

    public Map<String, String> getEducation_level() {
        return education_level;
    }

    public void setEducation_level(Map<String, String> education_level) {
        this.education_level = education_level;
    }

    public Map<String, String> getAdults_in_houseHold() {
        return adults_in_houseHold;
    }

    public void setAdults_in_houseHold(Map<String, String> adults_in_houseHold) {
        this.adults_in_houseHold = adults_in_houseHold;
    }

    public Map<String, String> getChildren_in_houseHold() {
        return children_in_houseHold;
    }

    public void setChildren_in_houseHold(Map<String, String> children_in_houseHold) {
        this.children_in_houseHold = children_in_houseHold;
    }
}
