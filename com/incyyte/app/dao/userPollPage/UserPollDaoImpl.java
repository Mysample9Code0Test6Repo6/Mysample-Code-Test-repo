package com.incyyte.app.dao.userPollPage;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.dao.user.rowmapper.InCyyteIDRowMapper;
import com.incyyte.app.dao.user.rowmapper.InCyyteRowMapper;
import com.incyyte.app.dao.user.rowmapper.PollPageRowMapper;
import com.incyyte.app.dao.user.rowmapper.UserAddressesRowMapper;
import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.PagePhoto;
import com.incyyte.app.domain.PollPage;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddresses;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.service.util.Logger;

public class UserPollDaoImpl implements UserPollDao {
    @Autowired
    private AbstractDao abstractDao;

    @Override
    public boolean insertPollPageInfo(PollPage pollPage, JdbcTemplate template) throws Exception, AccessException {
        Logger.debug("poll pageinfo:::::"+pollPage);
        long pageId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("POLL_PAGE_ID_PK", false);
        pollPage.setPageId(pageId);
        String insertPollPageInfo = " INSERT INTO USER_POLL_PAGE " +
                " (POLL_PAGE_ID,USER_ID,PAGE_HEADER,CONTACT_EMAIL,WEBSITE_URL,CONTACT_PHONE1,CONTACT_PHONE2,INFORMATION) " +
                " VALUES(?,?,?,?,?,?,?,?)";
        QueryParameters params = new QueryParameters();
        params.addParam(pollPage.getPageId());
        params.addParam(pollPage.getUserId());
        params.addParam(pollPage.getPageHeader());
        params.addParam(pollPage.getContactEmail());
        params.addParam(prefixLink(pollPage.getWebsiteUrl()));
        params.addParam(pollPage.getContactPhone1());
        params.addParam(pollPage.getContactPhone2());
        params.addParam(pollPage.getInformation());
        try {
            QueryHelper.doUpdate(template, insertPollPageInfo, params);
        } catch (SQLException se) {
            Logger.error("Exception", se);
            throw new Exception(se);
        }
        return true;
    }

    @Override
    public boolean updatePollPageInfo(PollPage pollPage, JdbcTemplate template) throws Exception {
        String insertPollPageInfo = " UPDATE USER_POLL_PAGE " +
                " SET PAGE_HEADER = ?" +
                " ,CONTACT_EMAIL = ?" +
                " ,WEBSITE_URL = ?" +
                " ,CONTACT_PHONE1 = ?" +
                " ,CONTACT_PHONE2 = ?" +
                " ,INFORMATION = ? " +
                " WHERE POLL_PAGE_ID = ? ";
        QueryParameters params = new QueryParameters();
        params.addParam(pollPage.getPageHeader());
        params.addParam(pollPage.getContactEmail());
        params.addParam(prefixLink(pollPage.getWebsiteUrl()));
        params.addParam(pollPage.getContactPhone1());
        params.addParam(pollPage.getContactPhone2());
        params.addParam(pollPage.getInformation());
        params.addParam(pollPage.getPageId());
        try {
            QueryHelper.doUpdate(template, insertPollPageInfo, params);
        } catch (SQLException se) {
            Logger.error("Exception", se);
            throw new Exception(se);
        }
        return true;
    }

    @Override
    public PollPage getPollPageInfo(long userId) throws Exception {

        QueryParameters params = new QueryParameters();
        params.addParam(userId);

        String getPollPageInfo = " SELECT POLL_PAGE_ID,USER_ID,PAGE_HEADER,CONTACT_EMAIL,WEBSITE_URL,CONTACT_PHONE1,CONTACT_PHONE2,INFORMATION " +
                " FROM USER_POLL_PAGE" +
                " WHERE USER_ID=? ";

        List<PollPage> pollPages = null;
        try {            
            pollPages = QueryHelper.doQuery(abstractDao, "getPollPageInfo", getPollPageInfo, params, new PollPageRowMapper());
        } catch (EmptyResultDataAccessException er) {
            Logger.warn("No Records exists and hence returning empty for user:" + userId);
        }
        return ((pollPages !=null && pollPages.size() > 0) ? pollPages.get(0) : null);
    }
    
    @Override
    public List<PollPage> getPollPagesInformation(long userId) throws Exception {

        QueryParameters params = new QueryParameters();
        params.addParam(userId);

        String getPollPageInfo = " SELECT POLL_PAGE_ID,USER_ID,PAGE_HEADER,CONTACT_EMAIL,WEBSITE_URL,CONTACT_PHONE1,CONTACT_PHONE2,INFORMATION " +
                " FROM USER_POLL_PAGE" +
                " WHERE USER_ID=? ";       
        try {
            return QueryHelper.doQuery(abstractDao, "getPollPageInfo", getPollPageInfo, params, new PollPageRowMapper());
            
        } catch (EmptyResultDataAccessException er) {
            Logger.warn("No Records exists and hence returning empty for user:" + userId);
        }
        return null;
    }

    @Override
    public boolean insertUserAddressesInfo(UserAddresses userAddresses, JdbcTemplate template) throws Exception, AccessException {
        String insertUserAddressInfo = " INSERT INTO USER_ADDRESSES " +
                " (USER_ID,ADDRESS_TYPE,ADDRESS1,ADDRESS2,CITY,POSTCODE,COUNTRY,COMPANY_NAME) " +
                " VALUES(?,?,?,?,?,?,?,?)";
        QueryParameters params = new QueryParameters();
        params.addParam(userAddresses.getUserId());
        params.addParam(userAddresses.getAddressType().toString());
        params.addParam(userAddresses.getAddress1());
        params.addParam(userAddresses.getAddress2());
        params.addParam(userAddresses.getCity());
        params.addParam(userAddresses.getPostcode());
        params.addParam(userAddresses.getCountry());
        params.addParam(userAddresses.getCompanyName());
        try {
            QueryHelper.doUpdate(template, insertUserAddressInfo, params);
        } catch (SQLException se) {
            Logger.error("Exception", se);
            throw new Exception(se);
        }
        return true;
    }

    @Override
    public boolean updateUserAddressesInfo(UserAddresses userAddresses, JdbcTemplate template) throws Exception {
        String updateUserAddressInfo = " UPDATE USER_ADDRESSES " +
                " SET ADDRESS1 = ?" +
                " ,ADDRESS2 = ?" +
                " ,CITY = ?" +
                " ,POSTCODE = ?" +
                " ,COUNTRY = ? " +
                " ,COMPANY_NAME = ? " +
                "  WHERE USER_ID = ?" +
                "  AND ADDRESS_TYPE = ?";
        QueryParameters params = new QueryParameters();
        params.addParam(userAddresses.getAddress1());
        params.addParam(userAddresses.getAddress2());
        params.addParam(userAddresses.getCity());
        params.addParam(userAddresses.getPostcode());
        params.addParam(userAddresses.getCountry());
        params.addParam(userAddresses.getCompanyName());
        params.addParam(userAddresses.getUserId());
        params.addParam(userAddresses.getAddressType().toString());
        try {
            QueryHelper.doUpdate(template, updateUserAddressInfo, params);
        } catch (SQLException se) {
            Logger.error("Exception", se);
            throw new Exception(se);
        }
        return true;
    }

    @Override
    public UserAddresses getUserAddressesInfo(long userId, AddressType addressType) throws Exception {
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(addressType.toString());
        String getUserAddressesInfo = " SELECT USER_ID,ADDRESS_TYPE,ADDRESS1,ADDRESS2,CITY,POSTCODE,COUNTRY, COMPANY_NAME " +
                " FROM USER_ADDRESSES" +
                " WHERE USER_ID=? AND ADDRESS_TYPE = ? ";
        UserAddresses userAddresses = null;
        try {
            userAddresses = (UserAddresses) QueryHelper.doQueryForObject(abstractDao, "getUserAddressesInfo", getUserAddressesInfo, params, new UserAddressesRowMapper());
            Logger.debug("inside getUserAddress InfoInfo :::::");
        } catch (EmptyResultDataAccessException er) {
            Logger.warn("No Records exists and hence returning empty for User:"+ userId + " :Address Type:" + addressType);
        }
        return userAddresses;
    }

    @Override
    public boolean insertPagePhotoInfo(PagePhoto pagePhoto, JdbcTemplate template) throws Exception, AccessException {
        Logger.debug("insertPagePhotoInfo");
        String insertPagePhotoInfo = " INSERT INTO POLL_PAGE_PHOTOS " +
                " (POLL_PAGE_ID,IMAGE_TYPE,UPLOAD_LOCATION,UPLOAD_NAME,CDN_FILE_NAME,IMAGE_LINK) " +
                " VALUES(?,?,?,?,?,?)";
        QueryParameters params = new QueryParameters();
        params.addParam(pagePhoto.getPageId());
        params.addParam(pagePhoto.getImageType().toString());
        params.addParam(pagePhoto.getUploadLocation());
        params.addParam(pagePhoto.getUploadName());
        params.addParam(pagePhoto.getCdnFileName());
        params.addParam(pagePhoto.getImageLink());
        try {
            QueryHelper.doUpdate(template, insertPagePhotoInfo, params);
        } catch (DataIntegrityViolationException di) {
            if (StringUtils.isNotBlank(pagePhoto.getUploadLocation())) {
                Logger.debug("UpdatePagePhotoInfo");
                String updatePagePhotoInfo = " UPDATE poll_page_photos SET " +
                        " UPLOAD_LOCATION = ?" +
                        " ,UPLOAD_NAME = ?" +
                        " ,CDN_FILE_NAME = ?" +
                        " ,IMAGE_LINK = ? " +
                        " WHERE poll_page_id = ? " +
                        "   AND image_type = ?";
                QueryParameters updateParams = new QueryParameters();
                updateParams.addParam(pagePhoto.getUploadLocation());
                updateParams.addParam(pagePhoto.getUploadName());
                updateParams.addParam(pagePhoto.getCdnFileName());
                updateParams.addParam(pagePhoto.getImageLink());
                updateParams.addParam(pagePhoto.getPageId());
                updateParams.addParam(pagePhoto.getImageType().toString());
                try {
                    QueryHelper.doUpdate(template, updatePagePhotoInfo, updateParams);
                } catch (SQLException se) {
                    Logger.error("Exception", se);
                    throw new Exception(se);
                }
            }
        } catch (SQLException se) {
            Logger.error("Exception", se);
            throw new Exception(se);
        }
        return true;
    }
    
    @Override
    public List<InCyyte> getMyPollPageShareContacts(User user,String sendType) {
    	Logger.debug("IncyyteChart:::::"+sendType);
    	Logger.debug("user:::::"+user);
        String myPollPageShareQuery ="select q.questionid, q.fk_userid, question, category,"+  
				"upload, upload_type, upload_name, upload_location, content_type, randomize, "+  
                "multi_selection, xmlString,  "+
                "background, viewchart_code, link, total_incyyted, total_responded, anonymity, page_name, type,   "+
                "allow_comment, upload_logo_location, strapline, protectPage, public_poll, access_code, q.cdn_file_name, q.poll_Result_Hidden, q.youtube_url,  "+
                "sent_date, closure_date, delete_ind, publish_ind, q.created_by, fk_groupid, send_method, send_zone, sendType, q.poll_page_template  "+
                "from questions q,incyyte i,shared_incyyte si , contacts c where i.fk_questionid = q.questionid   "+
				"and si.fk_questionid = q.questionid and q.sendtype = ? and si.fk_contactid =  c.contactid  "+
                "and c.email = ? ";
        QueryParameters params = new QueryParameters();
        params.addParam(sendType);
        params.addParam(user.getEmail());
        return QueryHelper.doQuery(abstractDao, "getMyPollPageShareContacts", myPollPageShareQuery, params,  new InCyyteRowMapper());
    }

    @Override
    public boolean deletePagePhotoInfo(PagePhoto pagePhoto, JdbcTemplate template) throws Exception {

        String deletePagePhotoInfo = " DELETE FROM poll_page_photos " +
                " WHERE poll_page_id = ? " +
                "   AND image_type = ? ";
        QueryParameters deleteParams = new QueryParameters();
        deleteParams.addParam(pagePhoto.getPageId());
        deleteParams.addParam(pagePhoto.getImageType().toString());
        try {
            QueryHelper.doUpdate(template, deletePagePhotoInfo, deleteParams);
        } catch (SQLException se) {
            Logger.error("Exception", se);
            throw new Exception(se);
        }
        return true;
    }

    @Override
    public List<PagePhoto> getPagePhotoInfo(long pageId) throws Exception {

        QueryParameters params = new QueryParameters();
        params.addParam(pageId);

        String getPagePhotoInfo = " SELECT POLL_PAGE_ID,IMAGE_TYPE,UPLOAD_LOCATION,UPLOAD_NAME,CDN_FILE_NAME,IMAGE_LINK " +
                " FROM POLL_PAGE_PHOTOS" +
                " WHERE POLL_PAGE_ID=?";

        List<PagePhoto> pagePhotos = null;
        try {
            pagePhotos = (List<PagePhoto>) QueryHelper.doQuery(abstractDao, "getPagePhotoInfo", getPagePhotoInfo, params, new com.incyyte.app.dao.user.rowmapper.PagePhotoRowMapper());
            Logger.debug("inside getPagePhotoInfoInfo :::::");
        } catch (EmptyResultDataAccessException er) {
            Logger.warn("No Records exists and hence returning empty for pageId:" +pageId);
        }
        return pagePhotos;
    }
    
	private String prefixLink(String elink) {
		if (StringUtils.isNotBlank(elink)) {
			if (elink.indexOf("http://") == -1) {
				elink = "http://" + elink;
			}
		}
		return elink;
	}
}