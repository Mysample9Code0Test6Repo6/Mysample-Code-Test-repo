package com.incyyte.app.dao.user.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.UserAddresses;

public class UserAddressesRowMapper  implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
		UserAddresses userAddresses = new UserAddresses();
		userAddresses.setUserId(rs.getLong("USER_ID"));
		userAddresses.setAddressType(AddressType.valueOf(rs.getString("ADDRESS_TYPE")));
		userAddresses.setAddress1(rs.getString("ADDRESS1"));
		userAddresses.setAddress2(rs.getString("ADDRESS2"));
		userAddresses.setCity(rs.getString("CITY"));
		userAddresses.setPostcode(rs.getString("POSTCODE"));
		userAddresses.setCountry(rs.getString("COUNTRY"));
		userAddresses.setCompanyName(rs.getString("COMPANY_NAME"));		
		return userAddresses;
	}

}