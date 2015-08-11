package com.incyyte.app.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.ImageType;
import com.incyyte.app.domain.PagePhoto;
import com.incyyte.app.domain.PollPage;
import com.incyyte.app.domain.UserAddresses;
import com.incyyte.app.domain.UserPollPage;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserPollPageModel;

public class UserPollUtil {

	public static UserPollPageModel convertUserPollToModel(Long userId, UserPollPage userPollPage) {
		UserPollPageModel pollPageModel = new UserPollPageModel();
		pollPageModel.setUserId(userId);
		if (userPollPage.getPollPage() != null) {
			pollPageModel.setPageId(userPollPage.getPollPage().getPageId());
			pollPageModel.setTemplateId(userPollPage.getPollPage().getPageId().toString());
			pollPageModel.setUserId(userPollPage.getPollPage().getUserId());
			pollPageModel.setPageHeader(userPollPage.getPollPage().getPageHeader());
			pollPageModel.setContactEmail(userPollPage.getPollPage().getContactEmail());
			pollPageModel.setWebsiteUrl(userPollPage.getPollPage().getWebsiteUrl());
			pollPageModel.setContactPhone1(userPollPage.getPollPage().getContactPhone1());
			pollPageModel.setContactPhone2(userPollPage.getPollPage().getContactPhone2());
			pollPageModel.setInformation(userPollPage.getPollPage().getInformation());
		}
		if (userPollPage.getUserAddress() != null) { 
			pollPageModel.setUserId(userPollPage.getUserAddress().getUserId());
			pollPageModel.setAddressType(userPollPage.getUserAddress().getAddressType().toString());
			pollPageModel.setAddress1(userPollPage.getUserAddress().getAddress1());
			pollPageModel.setAddress2(userPollPage.getUserAddress().getAddress2());
			pollPageModel.setCity(userPollPage.getUserAddress().getCity());
			pollPageModel.setPostcode(userPollPage.getUserAddress().getPostcode());
			pollPageModel.setCountry(userPollPage.getUserAddress().getCountry());
		}
		if (userPollPage.getPagePhotos() != null) {
			for (PagePhoto pagePhoto : userPollPage.getPagePhotos()) {
				if (pagePhoto.getImageType() == ImageType.LOGO) {
					pollPageModel.setLogoLink(pagePhoto.getImageLink());
					pollPageModel.setLogoImageName(pagePhoto.getUploadName());
					pollPageModel.setLogoCdnFileName(pagePhoto.getCdnFileName());
				} else if (pagePhoto.getImageType() == ImageType.BANNER) {
					pollPageModel.setBannerLink(pagePhoto.getImageLink());
					pollPageModel.setBannerImageName(pagePhoto.getUploadName());
					pollPageModel.setBannerCdnFileName(pagePhoto.getCdnFileName());
				} else if (pagePhoto.getImageType() == ImageType.IMAGE1) {
					pollPageModel.setImageLink1(pagePhoto.getImageLink());
					pollPageModel.setImageName1(pagePhoto.getUploadName());
					pollPageModel.setImage1CdnFileName(pagePhoto.getCdnFileName());
				}
				else if (pagePhoto.getImageType() == ImageType.IMAGE2) {
					pollPageModel.setImageLink2(pagePhoto.getImageLink());
					pollPageModel.setImageName2(pagePhoto.getUploadName());
					pollPageModel.setImage2CdnFileName(pagePhoto.getCdnFileName());
				}
				else if (pagePhoto.getImageType() == ImageType.IMAGE3) {
					pollPageModel.setImageLink3(pagePhoto.getImageLink());
					pollPageModel.setImageName3(pagePhoto.getUploadName());
					pollPageModel.setImage3CdnFileName(pagePhoto.getCdnFileName());
				}
				else if (pagePhoto.getImageType() == ImageType.IMAGE4) {
					pollPageModel.setImageLink4(pagePhoto.getImageLink());
					pollPageModel.setImageName4(pagePhoto.getUploadName());
					pollPageModel.setImage4CdnFileName(pagePhoto.getCdnFileName());
				}
				else if (pagePhoto.getImageType() == ImageType.IMAGE5) {
					pollPageModel.setImageLink5(pagePhoto.getImageLink());
					pollPageModel.setImageName5(pagePhoto.getUploadName());
					pollPageModel.setImage5CdnFileName(pagePhoto.getCdnFileName());
				}
				else if (pagePhoto.getImageType() == ImageType.IMAGE6) {
					pollPageModel.setImageLink6(pagePhoto.getImageLink());
					pollPageModel.setImageName6(pagePhoto.getUploadName());
					pollPageModel.setImage6CdnFileName(pagePhoto.getCdnFileName());
				}
				else if (pagePhoto.getImageType() == ImageType.IMAGE7) {
					pollPageModel.setImageLink7(pagePhoto.getImageLink());
					pollPageModel.setImageName7(pagePhoto.getUploadName());
					pollPageModel.setImage7CdnFileName(pagePhoto.getCdnFileName());
				}
				else if (pagePhoto.getImageType() == ImageType.IMAGE8) {
					pollPageModel.setImageLink8(pagePhoto.getImageLink());
					pollPageModel.setImageName8(pagePhoto.getUploadName());
					pollPageModel.setImage8CdnFileName(pagePhoto.getCdnFileName());
				}
				else if (pagePhoto.getImageType() == ImageType.IMAGE9) {
					pollPageModel.setImageLink9(pagePhoto.getImageLink());
					pollPageModel.setImageName9(pagePhoto.getUploadName());
					pollPageModel.setImage9CdnFileName(pagePhoto.getCdnFileName());
				} else if (pagePhoto.getImageType() == ImageType.IMAGE10) {
					pollPageModel.setImageLink10(pagePhoto.getImageLink());
					pollPageModel.setImageName10(pagePhoto.getUploadName());
					pollPageModel.setImage10CdnFileName(pagePhoto.getCdnFileName());
				}
			}
		}
		return pollPageModel;

	}

	public static UserPollPage convertUserPollFromModel(UserPollPageModel userPollPageModel) throws Exception {
		UserPollPage userPollPage = new UserPollPage();

		PollPage pollPage = new PollPage();

		pollPage.setPageId(userPollPageModel.getPageId());
		pollPage.setUserId(userPollPageModel.getUserId());
		pollPage.setPageHeader(userPollPageModel.getPageHeader()); 
		pollPage.setContactEmail(userPollPageModel.getContactEmail());
		pollPage.setWebsiteUrl(userPollPageModel.getWebsiteUrl());
		pollPage.setContactPhone1(userPollPageModel.getContactPhone1());
		pollPage.setContactPhone2(userPollPageModel.getContactPhone2());
		pollPage.setInformation(userPollPageModel.getInformation());

		userPollPage.setPollPage(pollPage);

		UserAddresses userAddresses = new UserAddresses();
		userAddresses.setUserId(userPollPageModel.getUserId());
		if(StringUtils.isNotBlank(userPollPageModel.getAddressType())) {
			userAddresses.setAddressType(AddressType.valueOf(userPollPageModel.getAddressType()));
		}
		userAddresses.setAddress1(userPollPageModel.getAddress1());
		userAddresses.setAddress2(userPollPageModel.getAddress2());
		userAddresses.setCity(userPollPageModel.getCity());
		userAddresses.setPostcode(userPollPageModel.getPostcode());
		userAddresses.setCountry(userPollPageModel.getCountry());

		userPollPage.setUserAddress(userAddresses);

		List<PagePhoto> pagePhotos = new ArrayList<PagePhoto>();
		PagePhoto pagePhoto;
		if (StringUtils.isNotBlank(userPollPageModel.getLogoUrl())) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType.LOGO);
			pagePhoto.setCdnFileName(userPollPageModel.getLogoCdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getLogoLink());
			if (userPollPageModel.getLogoImage() != null) {
				pagePhoto.setUploadName(userPollPageModel.getLogoImage().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getLogoImage().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getLogoImageName());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		}if (StringUtils.isNotBlank(userPollPageModel.getBannerUrl())) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType. BANNER);
			pagePhoto.setCdnFileName(userPollPageModel.getBannerCdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getBannerLink());
			if (userPollPageModel.getBannerImage() != null) {
				pagePhoto.setUploadName(userPollPageModel.getBannerImage().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getBannerImage().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getBannerImageName());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		} if (StringUtils.isNotBlank(userPollPageModel.getImageURL1())) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType.IMAGE1);
			pagePhoto.setCdnFileName(userPollPageModel.getImage1CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink1());
			if(userPollPageModel.getImage1() !=  null){
				pagePhoto.setUploadName(userPollPageModel.getImage1().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage1().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getImageName1());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		}  if (StringUtils.isNotBlank(userPollPageModel.getImageURL2())) {
			Logger.debug("while saving file 2 name"+userPollPageModel.getImageName2());
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType.IMAGE2);
			pagePhoto.setCdnFileName(userPollPageModel.getImage2CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink2());
			if(userPollPageModel.getImage2() != null){
				pagePhoto.setUploadName(userPollPageModel.getImage2().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage2().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getImageName2());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		} if (StringUtils.isNotBlank(userPollPageModel.getImageURL3())) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType.IMAGE3);
			pagePhoto.setCdnFileName(userPollPageModel.getImage3CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink3());
			if(userPollPageModel.getImage3() != null){
				pagePhoto.setUploadName(userPollPageModel.getImage3().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage3().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getImageName3());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		} if (StringUtils.isNotBlank(userPollPageModel.getImageURL4())) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType. IMAGE4);
			pagePhoto.setCdnFileName(userPollPageModel.getImage4CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink4());
			if(userPollPageModel.getImage4() != null){
				pagePhoto.setUploadName(userPollPageModel.getImage4().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage4().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getImageName4());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		} if (StringUtils.isNotBlank(userPollPageModel.getImageURL5() )) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType. IMAGE5);
			pagePhoto.setCdnFileName(userPollPageModel.getImage5CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink5());
			if(userPollPageModel.getImage5() != null){
				pagePhoto.setUploadName(userPollPageModel.getImage5().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage5().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getImageName5());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		} if (StringUtils.isNotBlank(userPollPageModel.getImageURL6())) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType. IMAGE6);
			pagePhoto.setCdnFileName(userPollPageModel.getImage6CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink6());
			if(userPollPageModel.getImage6() != null){
				pagePhoto.setUploadName(userPollPageModel.getImage6().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage6().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getImageName6());
				pagePhoto.setUploadLocation("image/jpeg");
			} 
			pagePhotos.add(pagePhoto);
		} if (StringUtils.isNotBlank(userPollPageModel.getImageURL7())) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType. IMAGE7);
			pagePhoto.setCdnFileName(userPollPageModel.getImage7CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink7());
			if(userPollPageModel.getImage7() != null){
				pagePhoto.setUploadName(userPollPageModel.getImage7().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage7().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getImageName7());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		} if (StringUtils.isNotBlank(userPollPageModel.getImageURL8() )) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType. IMAGE8);
			pagePhoto.setCdnFileName(userPollPageModel.getImage8CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink8());
			if(userPollPageModel.getImage8() != null){
				pagePhoto.setUploadName(userPollPageModel.getImage8().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage8().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getImageName8());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		} if (StringUtils.isNotBlank(userPollPageModel.getImageURL9())) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType. IMAGE9);
			pagePhoto.setCdnFileName(userPollPageModel.getImage9CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink9());
			if(userPollPageModel.getImage9() != null){
				pagePhoto.setUploadName(userPollPageModel.getImage9().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage9().getContentType());
			}else {
				pagePhoto.setUploadName(userPollPageModel.getImageName9());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		} if (StringUtils.isNotBlank(userPollPageModel.getImageURL10() )) {
			pagePhoto = new PagePhoto();
			pagePhoto.setPageId(userPollPageModel.getPageId());
			pagePhoto.setImageType(ImageType. IMAGE10);
			pagePhoto.setCdnFileName(userPollPageModel.getImage10CdnFileName());
			pagePhoto.setImageLink(userPollPageModel.getImageLink10());
			if(userPollPageModel.getImage10() != null){
				pagePhoto.setUploadName(userPollPageModel.getImage10().getOriginalFilename());
				pagePhoto.setUploadLocation(userPollPageModel.getImage10().getContentType());
			} else {
				pagePhoto.setUploadName(userPollPageModel.getImageName10());
				pagePhoto.setUploadLocation("image/jpeg");
			}
			pagePhotos.add(pagePhoto);
		}	
		userPollPage.setPagePhotos(pagePhotos);
		Logger.debug("userPollPage:" + userPollPage);
		return userPollPage;
	}

	public static List<PagePhoto> getDeleteEligiblePhotos(List<PagePhoto> uiPhotos, List<PagePhoto> dbPhotos){
		List<PagePhoto> deletePhotos = new ArrayList<PagePhoto>();
		boolean recordExists;
		for (PagePhoto dbPhoto : dbPhotos) {
			recordExists = false;
			for (PagePhoto pagePhoto : uiPhotos) {
				if(dbPhoto.getImageType() == pagePhoto.getImageType()){
					recordExists = true;
					break;
				}
			}
			if (!recordExists) {
				deletePhotos.add(dbPhoto);	
			}
		}
		return deletePhotos;
	}
	
	public static PagePhoto getPagePhoto(UserPollPage userPollPage, ImageType imageType) {
		if (userPollPage == null || userPollPage.getPagePhotos() == null || userPollPage.getPagePhotos().size() <= 0) {
			return null;
		}
		for (PagePhoto photo : userPollPage.getPagePhotos()) {
			if (imageType == photo.getImageType()) {
				return photo;
			}
		}
		return null;
	}
}