package com.incyyte.app.dao.businessaccount;

public class BusinessAccountDaoQueries {
	
	private static StringBuffer insertBusinessAccountQuery = new StringBuffer(
			" INSERT INTO company_acct " +
			" (id,fk_userid,company_name,address_1,address_2,city,postcode,country,contact_email,website_url,phone,company_logo_url,company_info_para1,company_info_para2,banner_url) " +
			" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
			);
	
	private static StringBuffer businessAccountQuery = new StringBuffer(
			" SELECT * " +
		    " FROM company_acct" +
		    " WHERE fk_userid=? "			
			);
	public static String updateBusinessAccountInfo(){
		StringBuilder updateBusinessAcctInfo = new StringBuilder();
		
		updateBusinessAcctInfo.append(" UPDATE company_acct ");
		updateBusinessAcctInfo.append(" SET ");
	    updateBusinessAcctInfo.append(" company_name=? ");
	    updateBusinessAcctInfo.append(" ,address_1=? ");
	    updateBusinessAcctInfo.append(" ,address_2=?");
	    updateBusinessAcctInfo.append(" ,city=?");
	    updateBusinessAcctInfo.append(" ,postcode=? ");
	    updateBusinessAcctInfo.append(" ,country=? ");
	  
	    updateBusinessAcctInfo.append(" ,contact_email=? ");
	    updateBusinessAcctInfo.append(" ,website_url=? ");
	    updateBusinessAcctInfo.append(" ,phone=?");
	    updateBusinessAcctInfo.append(" ,company_info_para1=?");
	    updateBusinessAcctInfo.append(" ,company_info_para2=? ");
	    updateBusinessAcctInfo.append(" ,company_logo_url=? ");
	    updateBusinessAcctInfo.append(" ,banner_url=? ");
	    updateBusinessAcctInfo.append(" WHERE fk_userid=?");
	    
	    return updateBusinessAcctInfo.toString();
	 }
	private static StringBuffer updateUserTypeQuery = new StringBuffer(			
			" UPDATE users " + 
			" SET user_type = 'BUSINESS' " +
			" WHERE userid=? "  
			);
	private static StringBuffer getUserTypeQuery = new StringBuffer(			
			" SELECT user_type " + 
			" from users " +
			" WHERE userid=? "  
			);
	public static StringBuffer updateUserTypeQuery() {
		return updateUserTypeQuery;
	}
	public static StringBuffer getUserTypeQuery() {
		return getUserTypeQuery;
	}
	public static StringBuffer getInsertBusinessAccountQuery(){
		return insertBusinessAccountQuery;
	}
	public static StringBuffer getBusinessAccountQuery(){
		return businessAccountQuery;
	}

	

}
