package com.incyyte.app.web.model;

import java.util.ArrayList;

public class UserContactlist {

	
	private ArrayList<UserContactModel>  userContactlist;
	
	 private int noOfRecords;

	public void setUserContactlist(ArrayList<UserContactModel> userContactlist) {
		this.userContactlist = userContactlist;
	}

	public ArrayList<UserContactModel> getUserContactlist() {
		return userContactlist;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	@Override
	public String toString() {
		return "UserContactlist [userContactlist=" + userContactlist
				+ ", noOfRecords=" + noOfRecords + "]";
	}
	
}
