package com.incyyte.app.domain;

import java.util.List;

public class UserPollPage{
	
	private PollPage pollPage;
	private UserAddresses userAddress;
	private List<PagePhoto> pagePhotos;
	
	public PollPage getPollPage() {
		return pollPage;
	}
	public void setPollPage(PollPage pollPage) {
		this.pollPage = pollPage;
	}
	public List<PagePhoto> getPagePhotos() {
		return pagePhotos;
	}
	public void setPagePhotos(List<PagePhoto> pagePhotos) {
		this.pagePhotos = pagePhotos;
	}
	public UserAddresses getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddresses userAddress) {
		this.userAddress = userAddress;
	}
	@Override
	public String toString() {
		return "UserPollPage [pollPage=" + pollPage + ", userAddress="
				+ userAddress + ", pagePhotos=" + pagePhotos + "]";
	}
}