package com.incyyte.app.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PagePhoto {
	private Long pageId;
	private ImageType imageType;
	private String uploadName;
	private String uploadLocation;
	private String cdnFileName;
	private String imageLink;

	private CommonsMultipartFile Image;
	
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	public ImageType getImageType() {
		return imageType;
	}
	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public String getUploadLocation() {
		return uploadLocation;
	}
	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}
	public String getCdnFileName() {
		return cdnFileName;
	}
	public void setCdnFileName(String cdnFileName) {
		this.cdnFileName = cdnFileName;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public CommonsMultipartFile getImage() {
		return Image;
	}
	public void setImage(CommonsMultipartFile image) {
		Image = image;
	}
	@Override
	public String toString() {
		return "PagePhoto [pageId=" + pageId + ", imageType=" + imageType
				+ ", uploadName=" + uploadName + ", uploadLocation="
				+ uploadLocation + ", cdnFileName=" + cdnFileName
				+ ", imageLink=" + imageLink + ", Image=" + Image + "]";
	}
}