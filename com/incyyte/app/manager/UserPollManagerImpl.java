package com.incyyte.app.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.question.ReportPollDao;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.dao.userPollPage.UserPollDao;
import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.PagePhoto;
import com.incyyte.app.domain.PollPage;
import com.incyyte.app.domain.ReportPoll;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddresses;
import com.incyyte.app.domain.UserPollPage;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.util.UserPollUtil;
public class UserPollManagerImpl implements UserPollManager {
	@Autowired
	private UserPollDao userPollDao;
	@Autowired
	private AbstractDao abstractDao;
    @Autowired
    private ReportPollDao reportPollDao;
    @Autowired
    private UserDao userDao;

    @Override
	public boolean saveUserPollPageInfo(UserPollPage userPollPage) throws Exception {
		Logger.debug("insertUserPollPageInfo:Manager: ");
		if (userPollPage == null || userPollPage.getPollPage() == null) {
			return false;
		}
		JdbcTemplate template = null;
		try {
			//Begin the transaction
			abstractDao.getTxnHelper().beginTxn();
			template = abstractDao.getJdbcTemplate("insertUserPollPageInfo");
			//Insert into Poll Page
			if (userPollPage.getPollPage().getPageId() == null
					|| userPollPage.getPollPage().getPageId() == 0) {
				userPollDao.insertPollPageInfo(userPollPage.getPollPage(), template);
			} else {
				userPollDao.updatePollPageInfo(userPollPage.getPollPage(), template);
			}
			//Insert into User Address
			if (userPollPage.getUserAddress() != null) {
				UserAddresses dbAddress = userPollDao.getUserAddressesInfo(userPollPage.getPollPage().getUserId(), AddressType.PAGE);
				if (dbAddress == null) {
					userPollDao.insertUserAddressesInfo(userPollPage.getUserAddress(), template);
				} else {
					userPollDao.updateUserAddressesInfo(userPollPage.getUserAddress(), template);
				}
			}
			//Delete Page Photos
			List<PagePhoto> pagePhotosDB = userPollDao.getPagePhotoInfo(userPollPage.getPollPage().getPageId());
			List<PagePhoto> deletePhotos = UserPollUtil.getDeleteEligiblePhotos(userPollPage.getPagePhotos(), pagePhotosDB);
			Logger.debug("PagePhotosUI: " + userPollPage.getPagePhotos());
			Logger.debug("pagePhotosDB: " + pagePhotosDB);
			Logger.debug("deletePhotos: " + deletePhotos);
			for (PagePhoto deletePhoto : deletePhotos){
				userPollDao.deletePagePhotoInfo(deletePhoto, template);
			}

			//Insert Page Photos
			if (userPollPage.getPagePhotos() != null) {
				for (PagePhoto pagePhoto : userPollPage.getPagePhotos()) {
					pagePhoto.setPageId(userPollPage.getPollPage().getPageId());
					userPollDao.insertPagePhotoInfo(pagePhoto, template);
				}
			}
			//If Successful then Commit
			abstractDao.getTxnHelper().commitTxn();
			return true;
		} catch (DuplicateKeyException dke) {
			//Rollback the transaction
			abstractDao.getTxnHelper().rollbackTxn();
			Logger.debug("Duplicate value of pageHeader. "+userPollPage);
			throw dke;
		}catch (Exception e) {
			Logger.error("insertDeletedIncyyte: Failed ", e);
    		//Rollback the transaction
			abstractDao.getTxnHelper().rollbackTxn();
			throw e;
		} finally {
			abstractDao.freedbpool(template.getDataSource(), "insertUserPollPageInfo");
		}
	}

	@Override
	public UserPollPage getUserPollPageInfo(long userId, AddressType addressType) throws Exception {
		UserPollPage userPollPage = new UserPollPage();
		PollPage pollPage = userPollDao.getPollPageInfo(userId);
		userPollPage.setPollPage(pollPage);
		UserAddresses userAddresses = userPollDao.getUserAddressesInfo(userId, addressType);
		userPollPage.setUserAddress(userAddresses);
		//For New users No poll exists hence Null check is required
		if (pollPage != null) {
			List<PagePhoto> pagePhotos = userPollDao.getPagePhotoInfo(pollPage.getPageId());
			userPollPage.setPagePhotos(pagePhotos);
		}
		return userPollPage;
	}
	
	@Override
	public List<UserPollPage> getUserPollPagesInformation(long userId, AddressType addressType) throws Exception {
		List<UserPollPage> userPollPages = new ArrayList<UserPollPage>();
		List<PollPage> pollPages = userPollDao.getPollPagesInformation(userId);
		UserAddresses userAddresses = userPollDao.getUserAddressesInfo(userId, addressType);
		if(pollPages != null && !pollPages.isEmpty()){		
			for(PollPage pollPage : pollPages){
				UserPollPage userPollPage = new UserPollPage();
				userPollPage.setPollPage(pollPage);
				userPollPage.setUserAddress(userAddresses);
				
				//For New users No poll exists hence Null check is required
				if (pollPage != null) {
					List<PagePhoto> pagePhotos = userPollDao.getPagePhotoInfo(pollPage.getPageId());
					userPollPage.setPagePhotos(pagePhotos);
				}
				
				userPollPages.add(userPollPage);
			}
		}		
		return userPollPages;
	}

	@Override
	public void deletePollPagePhoto(PagePhoto pagePhoto) throws Exception {
		Logger.debug("deletePollPagePhoto::pagePhoto" + pagePhoto);
		if (pagePhoto == null) {
			throw new Exception("Object passed for Delete is empty");
		}
		JdbcTemplate template = abstractDao.getJdbcTemplate("deletePollPagePhoto");
		try {
			userPollDao.deletePagePhotoInfo(pagePhoto, template);
		} catch (Exception e) {
			Logger.error("deletePollPagePhoto: Failed ", e);
			throw e;
		} finally {
			abstractDao.freedbpool(template.getDataSource(), "deletePollPagePhoto");
		}
	}

	@Override
	public String reportPoll(ReportPoll reportPoll) throws Exception {
		Logger.debug("reportPoll:" + reportPoll);
		try {
			ConfigManager icfg = ConfigManager.getInstance();
            String insertedPoll = reportPollDao.insertReportPoll(reportPoll);
            if (StringUtils.equals(insertedPoll, "inserted")) {
                int reportsCount = reportPollDao.getReportPollCount(reportPoll.getQuestionId());
                if(reportsCount >= Integer.valueOf(icfg.getProperty(ConfigProperties.REPORT_COUNTER))) {
                    userDao.insertDeletedIncyyte(reportPoll.getQuestionId(), reportPoll.getUserId());
                    userDao.updateDeletedIncyyte(reportPoll.getQuestionId());
                    return "limitReached";
                }
            }
            return insertedPoll;
		} catch (Exception e) {
			Logger.error("reportPoll: Failed ", e);
            throw e;
		}
	}
	
	 @Override
	    public List<InCyyte> getMyPollPageShareContacts(User user,String sendType) throws Exception {
	    	return userPollDao.getMyPollPageShareContacts(user,sendType);
	    }
}