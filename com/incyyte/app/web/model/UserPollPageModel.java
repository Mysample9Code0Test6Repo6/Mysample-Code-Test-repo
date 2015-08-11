package com.incyyte.app.web.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UserPollPageModel {

    private Long pageId;
    private Long userId;
    private String pageHeader;
    private String contactEmail;
    private String websiteUrl;
    private String contactPhone1;
    private String contactPhone2;
    private String information;
    private String addressType;
    private String address1;
    private String address2;
    private String city;
    private String postcode;
    private String country;

    private String uploadedFileName;
    private String uploadedFileType;


    private String logoImageName;//not used now . required when displaying filename in ui
    private CommonsMultipartFile logoImage;
    private String logoLink;

    private String logoCdnFileName;
    private String logoUrl;

    private String bannerImageName;//not used now . required when displaying filename in ui
    private CommonsMultipartFile bannerImage;
    private String bannerLink;

    private String bannerCdnFileName;
    private String bannerUrl;
    private String searchedFileURLPollPage;
    private String searchedFileNamePollPage;
    
    private String templateId;
    private Map<String, String> templates = new HashMap<String, String>();

    public String getSearchedFileURLPollPage() {
		return searchedFileURLPollPage;
	}

	public void setSearchedFileURLPollPage(String searchedFileURLPollPage) {
		this.searchedFileURLPollPage = searchedFileURLPollPage;
	}

	public String getSearchedFileNamePollPage() {
		return searchedFileNamePollPage;
	}

	public void setSearchedFileNamePollPage(String searchedFileNamePollPage) {
		this.searchedFileNamePollPage = searchedFileNamePollPage;
	}

	public String getLogoUrl() {
		return StringUtils.defaultString(logoUrl);
	}

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBannerUrl() {
		return StringUtils.defaultString(bannerUrl);
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    private String imageName1;
    private CommonsMultipartFile image1;
    private String imageLink1;

    private String imageName2;
    private CommonsMultipartFile image2;
    private String imageLink2;

    private String imageName3;
    private CommonsMultipartFile image3;
    private String imageLink3;

    private String imageName4;
    private CommonsMultipartFile image4;
    private String imageLink4;

    private String imageName5;
    private CommonsMultipartFile image5;
    private String imageLink5;

    private String imageName6;
    private CommonsMultipartFile image6;
    private String imageLink6;

    private String imageName7;
    private CommonsMultipartFile image7;
    private String imageLink7;

    private String imageName8;
    private CommonsMultipartFile image8;
    private String imageLink8;

    private String imageName9;
    private CommonsMultipartFile image9;
    private String imageLink9;

    private String imageName10;
    private CommonsMultipartFile image10;
    private String imageLink10;

    private String imageURL1;
    private String image1CdnFileName;

    private String imageURL2;
    private String image2CdnFileName;

    private String imageURL3;
    private String image3CdnFileName;

    private String imageURL4;
    private String image4CdnFileName;

    private String imageURL5;
    private String image5CdnFileName;

    private String imageURL6;
    private String image6CdnFileName;

    private String imageURL7;
    private String image7CdnFileName;

    private String imageURL8;
    private String image8CdnFileName;

    private String imageURL9;
    private String image9CdnFileName;

    private String imageURL10;
    private String image10CdnFileName;

    //This can be commonly used to upload info
    private CommonsMultipartFile commonImage;

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPageHeader() {
		return StringUtils.defaultString(pageHeader);
    }

    public void setPageHeader(String pageHeader) {
        this.pageHeader = pageHeader;
    }

    public String getContactEmail() {
		return StringUtils.defaultString(contactEmail);
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

	public String getWebsiteUrl() {
		return StringUtils.defaultString(websiteUrl);
	}

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getContactPhone1() {
        return StringUtils.defaultString(contactPhone1);
    }

    public void setContactPhone1(String contactPhone1) {
        this.contactPhone1 = contactPhone1;
    }

    public String getContactPhone2() {
        return StringUtils.defaultString(contactPhone2);
    }

    public void setContactPhone2(String contactPhone2) {
        this.contactPhone2 = contactPhone2;
    }

	public String getInformation() {
		return StringUtils.defaultString(information);
	}

    public void setInformation(String information) {
        this.information = information;
    }

	public String getAddressType() {
		return StringUtils.defaultString(addressType);
	}

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

	public String getAddress1() {
		return StringUtils.defaultString(address1);
	}

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

	public String getAddress2() {
		return StringUtils.defaultString(address2);
	}

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

	public String getCity() {
		return StringUtils.defaultString(city);
	}

    public void setCity(String city) {
        this.city = city;
    }

	public String getPostcode() {
		return StringUtils.defaultString(postcode);
	}

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

	public String getCountry() {
		return StringUtils.defaultString(country);
	}

    public void setCountry(String country) {
        this.country = country;
    }

	public String getUploadedFileName() {
		return StringUtils.defaultString(uploadedFileName);
	}

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

	public String getUploadedFileType() {
		return StringUtils.defaultString(uploadedFileType);
	}

    public void setUploadedFileType(String uploadedFileType) {
        this.uploadedFileType = uploadedFileType;
    }

	public String getLogoImageName() {
		return StringUtils.defaultString(logoImageName);
	}

    public void setLogoImageName(String logoImageName) {
        this.logoImageName = logoImageName;
    }

    public CommonsMultipartFile getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(CommonsMultipartFile logoImage) {
        this.logoImage = logoImage;
    }

	public String getLogoLink() {
		return StringUtils.defaultString(logoLink);
	}

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

	public String getBannerImageName() {
		return StringUtils.defaultString(bannerImageName);
	}

    public void setBannerImageName(String bannerImageName) {
        this.bannerImageName = bannerImageName;
    }

    public CommonsMultipartFile getBannerImage() {
        return bannerImage;
    }

	public String getLogoCdnFileName() {
		return StringUtils.defaultString(logoCdnFileName);
	}

    public void setLogoCdnFileName(String logoCdnFileName) {
        this.logoCdnFileName = logoCdnFileName;
    }

	public String getBannerCdnFileName() {
		return StringUtils.defaultString(bannerCdnFileName);
	}

    public void setBannerCdnFileName(String bannerCdnFileName) {
        this.bannerCdnFileName = bannerCdnFileName;
    }

    public void setBannerImage(CommonsMultipartFile bannerImage) {
        this.bannerImage = bannerImage;
    }

	public String getBannerLink() {
		return StringUtils.defaultString(bannerLink);
	}

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }

	public String getImageName1() {
		return StringUtils.defaultString(imageName1);
	}

    public void setImageName1(String imageName1) {
        this.imageName1 = imageName1;
    }

    public CommonsMultipartFile getImage1() {
        return image1;
    }

    public void setImage1(CommonsMultipartFile image1) {
        this.image1 = image1;
    }

	public String getImageLink1() {
		return StringUtils.defaultString(imageLink1);
	}

    public void setImageLink1(String imageLink1) {
        this.imageLink1 = imageLink1;
    }

	public String getImageName2() {
		return StringUtils.defaultString(imageName2);
	}

    public void setImageName2(String imageName2) {
        this.imageName2 = imageName2;
    }

    public CommonsMultipartFile getImage2() {
        return image2;
    }

    public void setImage2(CommonsMultipartFile image2) {
        this.image2 = image2;
    }

	public String getImageLink2() {
		return StringUtils.defaultString(imageLink2);
	}

    public void setImageLink2(String imageLink2) {
        this.imageLink2 = imageLink2;
    }

	public String getImageName3() {
		return StringUtils.defaultString(imageName3);
	}

    public void setImageName3(String imageName3) {
        this.imageName3 = imageName3;
    }

    public CommonsMultipartFile getImage3() {
        return image3;
    }

    public void setImage3(CommonsMultipartFile image3) {
        this.image3 = image3;
    }

	public String getImageLink3() {
		return StringUtils.defaultString(imageLink3);
	}

    public void setImageLink3(String imageLink3) {
        this.imageLink3 = imageLink3;
    }

	public String getImageName4() {
		return StringUtils.defaultString(imageName4);
	}

    public void setImageName4(String imageName4) {
        this.imageName4 = imageName4;
    }

    public CommonsMultipartFile getImage4() {
        return image4;
    }

    public void setImage4(CommonsMultipartFile image4) {
        this.image4 = image4;
    }

	public String getImageLink4() {
		return StringUtils.defaultString(imageLink4);
	}

    public void setImageLink4(String imageLink4) {
        this.imageLink4 = imageLink4;
    }

	public String getImageName5() {
		return StringUtils.defaultString(imageName5);
	}

    public void setImageName5(String imageName5) {
        this.imageName5 = imageName5;
    }

    public CommonsMultipartFile getImage5() {
        return image5;
    }

    public void setImage5(CommonsMultipartFile image5) {
        this.image5 = image5;
    }

	public String getImageLink5() {
		return StringUtils.defaultString(imageLink5);
	}

    public void setImageLink5(String imageLink5) {
        this.imageLink5 = imageLink5;
    }

	public String getImageName6() {
		return StringUtils.defaultString(imageName6);
	}

    public void setImageName6(String imageName6) {
        this.imageName6 = imageName6;
    }

    public CommonsMultipartFile getImage6() {
        return image6;
    }

    public void setImage6(CommonsMultipartFile image6) {
        this.image6 = image6;
    }

	public String getImageLink6() {
		return StringUtils.defaultString(imageLink6);
	}

    public void setImageLink6(String imageLink6) {
        this.imageLink6 = imageLink6;
    }

	public String getImageName7() {
		return StringUtils.defaultString(imageName7);
	}

    public void setImageName7(String imageName7) {
        this.imageName7 = imageName7;
    }

    public CommonsMultipartFile getImage7() {
        return image7;
    }

    public void setImage7(CommonsMultipartFile image7) {
        this.image7 = image7;
    }

	public String getImageLink7() {
		return StringUtils.defaultString(imageLink7);
	}

    public void setImageLink7(String imageLink7) {
        this.imageLink7 = imageLink7;
    }

	public String getImageName8() {
		return StringUtils.defaultString(imageName8);
	}

    public void setImageName8(String imageName8) {
        this.imageName8 = imageName8;
    }

    public CommonsMultipartFile getImage8() {
        return image8;
    }

    public void setImage8(CommonsMultipartFile image8) {
        this.image8 = image8;
    }

	public String getImageLink8() {
		return StringUtils.defaultString(imageLink8);
	}

    public void setImageLink8(String imageLink8) {
        this.imageLink8 = imageLink8;
    }

	public String getImageName9() {
		return StringUtils.defaultString(imageName9);
	}

    public void setImageName9(String imageName9) {
        this.imageName9 = imageName9;
    }

    public CommonsMultipartFile getImage9() {
        return image9;
    }

    public void setImage9(CommonsMultipartFile image9) {
        this.image9 = image9;
    }

	public String getImageLink9() {
		return StringUtils.defaultString(imageLink9);
	}

    public void setImageLink9(String imageLink9) {
        this.imageLink9 = imageLink9;
    }

	public String getImageName10() {
		return StringUtils.defaultString(imageName10);
	}

    public void setImageName10(String imageName10) {
        this.imageName10 = imageName10;
    }

    public CommonsMultipartFile getImage10() {
        return image10;
    }

    public void setImage10(CommonsMultipartFile image10) {
        this.image10 = image10;
    }

	public String getImageLink10() {
		return StringUtils.defaultString(imageLink10);
	}

    public void setImageLink10(String imageLink10) {
        this.imageLink10 = imageLink10;
    }

	public String getImageURL1() {
		return StringUtils.defaultString(imageURL1);
	}

    public void setImageURL1(String imageURL1) {
        this.imageURL1 = imageURL1;
    }

	public String getImage1CdnFileName() {
		return StringUtils.defaultString(image1CdnFileName);
	}

    public void setImage1CdnFileName(String image1CdnFileName) {
        this.image1CdnFileName = image1CdnFileName;
    }

	public String getImageURL2() {
		return StringUtils.defaultString(imageURL2);
	}

    public void setImageURL2(String imageURL2) {
        this.imageURL2 = imageURL2;
    }

	public String getImage2CdnFileName() {
		return StringUtils.defaultString(image2CdnFileName);
	}

    public void setImage2CdnFileName(String image2CdnFileName) {
        this.image2CdnFileName = image2CdnFileName;
    }

	public String getImageURL3() {
		return StringUtils.defaultString(imageURL3);
	}

    public void setImageURL3(String imageURL3) {
        this.imageURL3 = imageURL3;
    }

	public String getImage3CdnFileName() {
		return StringUtils.defaultString(image3CdnFileName);
	}

    public void setImage3CdnFileName(String image3CdnFileName) {
        this.image3CdnFileName = image3CdnFileName;
    }

	public String getImageURL4() {
		return StringUtils.defaultString(imageURL4);
	}

    public void setImageURL4(String imageURL4) {
        this.imageURL4 = imageURL4;
    }

	public String getImage4CdnFileName() {
		return StringUtils.defaultString(image4CdnFileName);
	}

    public void setImage4CdnFileName(String image4CdnFileName) {
        this.image4CdnFileName = image4CdnFileName;
    }

	public String getImageURL5() {
		return StringUtils.defaultString(imageURL5);
	}

    public void setImageURL5(String imageURL5) {
        this.imageURL5 = imageURL5;
    }

	public String getImage5CdnFileName() {
		return StringUtils.defaultString(image5CdnFileName);
	}

    public void setImage5CdnFileName(String image5CdnFileName) {
        this.image5CdnFileName = image5CdnFileName;
    }

	public String getImageURL6() {
		return StringUtils.defaultString(imageURL6);
	}

    public void setImageURL6(String imageURL6) {
        this.imageURL6 = imageURL6;
    }

	public String getImage6CdnFileName() {
		return StringUtils.defaultString(image6CdnFileName);
	}

    public void setImage6CdnFileName(String image6CdnFileName) {
        this.image6CdnFileName = image6CdnFileName;
    }

	public String getImageURL7() {
		return StringUtils.defaultString(imageURL7);
	}

    public void setImageURL7(String imageURL7) {
        this.imageURL7 = imageURL7;
    }

	public String getImage7CdnFileName() {
		return StringUtils.defaultString(image7CdnFileName);
	}

    public void setImage7CdnFileName(String image7CdnFileName) {
        this.image7CdnFileName = image7CdnFileName;
    }

	public String getImageURL8() {
		return StringUtils.defaultString(imageURL8);
	}

    public void setImageURL8(String imageURL8) {
        this.imageURL8 = imageURL8;
    }

	public String getImage8CdnFileName() {
		return StringUtils.defaultString(image8CdnFileName);
	}

    public void setImage8CdnFileName(String image8CdnFileName) {
        this.image8CdnFileName = image8CdnFileName;
    }

	public String getImageURL9() {
		return StringUtils.defaultString(imageURL9);
	}

    public void setImageURL9(String imageURL9) {
        this.imageURL9 = imageURL9;
    }

	public String getImage9CdnFileName() {
		return StringUtils.defaultString(image9CdnFileName);
	}

    public void setImage9CdnFileName(String image9CdnFileName) {
        this.image9CdnFileName = image9CdnFileName;
    }

	public String getImageURL10() {
		return StringUtils.defaultString(imageURL10);
	}

    public void setImageURL10(String imageURL10) {
        this.imageURL10 = imageURL10;
    }

	public String getImage10CdnFileName() {
		return StringUtils.defaultString(image10CdnFileName);
	}

    public void setImage10CdnFileName(String image10CdnFileName) {
        this.image10CdnFileName = image10CdnFileName;
    }

    public CommonsMultipartFile getCommonImage() {
        return commonImage;
    }

    public void setCommonImage(CommonsMultipartFile commonImage) {
        this.commonImage = commonImage;
    }

    @Override
	public String toString() {
		return "UserPollPageModel [pageId=" + pageId + ", userId=" + userId
				+ ", pageHeader=" + pageHeader + ", contactEmail="
				+ contactEmail + ", websiteUrl=" + websiteUrl
				+ ", contactPhone1=" + contactPhone1 + ", contactPhone2="
				+ contactPhone2 + ", information=" + information
				+ ", addressType=" + addressType + ", address1=" + address1
				+ ", address2=" + address2 + ", city=" + city + ", postcode="
				+ postcode + ", country=" + country + ", uploadedFileName="
				+ uploadedFileName + ", uploadedFileType=" + uploadedFileType
				+ ", logoImageName=" + logoImageName + ", logoImage="
				+ logoImage + ", logoLink=" + logoLink + ", logoCdnFileName="
				+ logoCdnFileName + ", logoUrl=" + logoUrl
				+ ", bannerImageName=" + bannerImageName + ", bannerImage="
				+ bannerImage + ", bannerLink=" + bannerLink
				+ ", bannerCdnFileName=" + bannerCdnFileName + ", bannerUrl="
				+ bannerUrl + ", searchedFileURLPollPage="
				+ searchedFileURLPollPage + ", searchedFileNamePollPage="
				+ searchedFileNamePollPage + ", imageName1=" + imageName1
				+ ", image1=" + image1 + ", imageLink1=" + imageLink1
				+ ", imageName2=" + imageName2 + ", image2=" + image2
				+ ", imageLink2=" + imageLink2 + ", imageName3=" + imageName3
				+ ", image3=" + image3 + ", imageLink3=" + imageLink3
				+ ", imageName4=" + imageName4 + ", image4=" + image4
				+ ", imageLink4=" + imageLink4 + ", imageName5=" + imageName5
				+ ", image5=" + image5 + ", imageLink5=" + imageLink5
				+ ", imageName6=" + imageName6 + ", image6=" + image6
				+ ", imageLink6=" + imageLink6 + ", imageName7=" + imageName7
				+ ", image7=" + image7 + ", imageLink7=" + imageLink7
				+ ", imageName8=" + imageName8 + ", image8=" + image8
				+ ", imageLink8=" + imageLink8 + ", imageName9=" + imageName9
				+ ", image9=" + image9 + ", imageLink9=" + imageLink9
				+ ", imageName10=" + imageName10 + ", image10=" + image10
				+ ", imageLink10=" + imageLink10 + ", imageURL1=" + imageURL1
				+ ", image1CdnFileName=" + image1CdnFileName + ", imageURL2="
				+ imageURL2 + ", image2CdnFileName=" + image2CdnFileName
				+ ", imageURL3=" + imageURL3 + ", image3CdnFileName="
				+ image3CdnFileName + ", imageURL4=" + imageURL4
				+ ", image4CdnFileName=" + image4CdnFileName + ", imageURL5="
				+ imageURL5 + ", image5CdnFileName=" + image5CdnFileName
				+ ", imageURL6=" + imageURL6 + ", image6CdnFileName="
				+ image6CdnFileName + ", imageURL7=" + imageURL7
				+ ", image7CdnFileName=" + image7CdnFileName + ", imageURL8="
				+ imageURL8 + ", image8CdnFileName=" + image8CdnFileName
				+ ", imageURL9=" + imageURL9 + ", image9CdnFileName="
				+ image9CdnFileName + ", imageURL10=" + imageURL10
				+ ", image10CdnFileName=" + image10CdnFileName
				+ ", commonImage=" + commonImage + "]";
	}

    public void copyValues(UserPollPageModel model) {
        this.pageId = model.getPageId();
        this.userId = model.getUserId();
        this.pageHeader = model.getPageHeader();
        this.contactEmail = model.getContactEmail();
        this.websiteUrl = model.getWebsiteUrl();
        this.contactPhone1 = model.getContactPhone1();
        this.contactPhone2 = model.getContactPhone2();
        this.information = model.getInformation();
        this.addressType = model.getAddressType();
        this.address1 = model.getAddress1();
        this.address2 = model.getAddress2();
        this.city = model.getCity();
        this.postcode = model.getPostcode();
        this.country = model.getCountry();
    }

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the templates
	 */
	public Map<String, String> getTemplates() {
		return templates;
	}

	/**
	 * @param templates the templates to set
	 */
	public void setTemplates(Map<String, String> templates) {
		this.templates = templates;
	}
}