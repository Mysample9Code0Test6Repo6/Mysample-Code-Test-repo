package com.incyyte.app.dao.user.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.incyyte.app.domain.ImageType;
import com.incyyte.app.domain.PagePhoto;

public class PagePhotoRowMapper  implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
		PagePhoto pagePhoto = new PagePhoto();
		pagePhoto.setPageId(rs.getLong("POLL_PAGE_ID"));
		pagePhoto.setImageType(ImageType.valueOf(rs.getString("IMAGE_TYPE")));
		pagePhoto.setUploadLocation(rs.getString("UPLOAD_LOCATION"));
		pagePhoto.setUploadName(rs.getString("UPLOAD_NAME"));
		pagePhoto.setCdnFileName(rs.getString("CDN_FILE_NAME"));
		pagePhoto.setImageLink(rs.getString("IMAGE_LINK"));
		
		return pagePhoto;
	}

}