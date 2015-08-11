package com.incyyte.app.dao.question;

import java.util.List;

import com.incyyte.app.domain.PeopleFilter;
import com.incyyte.app.domain.Person;

public interface PollDao {

	public	List<Person> getMembersByPostcode(String postcode, PeopleFilter filter);
	public	List<Person> getMembersByRegion(String postalRegion, PeopleFilter filter);
	public	List<Person> getMembersByCounty(String county, PeopleFilter filter);
	
	public	Long countMembersByPostcode(String postcode, PeopleFilter filter);
	public	Long countMembersByRegion(String postalRegion, PeopleFilter filter);
	public	Long countMembersByCounty(String county, PeopleFilter filter);
}
