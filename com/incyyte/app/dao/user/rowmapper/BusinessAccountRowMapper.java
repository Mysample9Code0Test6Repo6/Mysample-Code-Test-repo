package com.incyyte.app.dao.user.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.BusinessAccount;

public class BusinessAccountRowMapper  implements RowMapper {
	
	public BusinessAccountRowMapper(){
		super();
	}

	@Override
	public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
		BusinessAccount businessAccount = new BusinessAccount();
		
		businessAccount.setId(rs.getInt("ID"));
		businessAccount.setUserid(rs.getLong("FK_USERID"));
		businessAccount.setCompanyName(rs.getString("COMPANY_NAME"));
		businessAccount.setAddress1(rs.getString("ADDRESS_1"));
		businessAccount.setAddress2(rs.getString("ADDRESS_2"));
		businessAccount.setCity(rs.getString("CITY"));
		businessAccount.setPostalCode(rs.getString("POSTCODE"));
		businessAccount.setCountry(rs.getString("COUNTRY"));
		businessAccount.setContactEmail(rs.getString("CONTACT_EMAIL"));
		businessAccount.setWebsiteUrl(rs.getString("WEBSITE_URL"));
		businessAccount.setPhone(rs.getString("PHONE"));
		businessAccount.setCompanyLogoUrl(rs.getString("COMPANY_LOGO_URL"));
		businessAccount.setCompanyInfoPara1(rs.getString("COMPANY_INFO_PARA1"));
		businessAccount.setCompanyInfoPara2(rs.getString("COMPANY_INFO_PARA2"));
		businessAccount.setBannerUrl(rs.getString("BANNER_URL"));
		return businessAccount;
	}

}
