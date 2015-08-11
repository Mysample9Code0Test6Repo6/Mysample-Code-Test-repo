package com.incyyte.app.dao.question;

public class PollDaoQueries {

	
	private static StringBuffer membersByPostcodeQuery = new StringBuffer(	
			"SELECT userid, email FROM users, user_domain WHERE userid=user_id AND postcode like ? " );
	
	private static StringBuffer membersByRegionQuery = new StringBuffer(	
			"SELECT userid, email FROM users, user_domain WHERE userid=user_id AND postcode like ? " );
	
	private static StringBuffer membersByCountyQuery = new StringBuffer(	
			"SELECT userid, email FROM users, user_domain WHERE userid=user_id AND county_name like ? " );
	
	private static StringBuffer countMembersByPostcodeQuery = new StringBuffer(	
			"SELECT count(*) FROM users, user_domain WHERE userid=user_id AND postcode like ? " );

	private static StringBuffer countMembersByRegionQuery = new StringBuffer(	
			"SELECT count(*) FROM users, user_domain WHERE userid=user_id AND postcode like ? " );

	private static StringBuffer countMembersByCountyQuery  = new StringBuffer(	
			"SELECT count(*) FROM users, user_domain WHERE userid=user_id AND county_name like ? " );
	
	public static StringBuffer getMembersByPostcodeQuery() {
		return membersByPostcodeQuery;
	}

	public static StringBuffer getMembersByRegionQuery() {
		return membersByRegionQuery;
	}
	public static StringBuffer getCountMembersByPostcodeQuery() {
		return countMembersByPostcodeQuery;
	}
	
	public static StringBuffer getCountMembersByRegionQuery() {
		return countMembersByRegionQuery;
	}
	
	public static StringBuffer getCountMembersByCountyQuery() {
		return countMembersByCountyQuery;
	}
	
	public static StringBuffer getMembersByCountyQuery() {
		return membersByCountyQuery;
	}
	
}
