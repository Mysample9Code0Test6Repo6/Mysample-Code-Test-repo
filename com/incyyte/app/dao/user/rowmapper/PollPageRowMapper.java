package com.incyyte.app.dao.user.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.PollPage;

public class PollPageRowMapper  implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
		PollPage pollPage = new PollPage();
		pollPage.setPageId(rs.getLong("POLL_PAGE_ID"));
		pollPage.setUserId(rs.getLong("USER_ID"));
		pollPage.setPageHeader(rs.getString("PAGE_HEADER"));
		pollPage.setContactEmail(rs.getString("CONTACT_EMAIL"));
		pollPage.setWebsiteUrl(rs.getString("WEBSITE_URL"));
		pollPage.setContactPhone1(rs.getString("CONTACT_PHONE1"));
		pollPage.setContactPhone2(rs.getString("CONTACT_PHONE2"));
		pollPage.setInformation(rs.getString("INFORMATION"));
		return pollPage;
	}

}