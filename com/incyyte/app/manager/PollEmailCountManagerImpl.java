package com.incyyte.app.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.dao.pollemailcount.PollEmailCountDao;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.LoginService;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserSettingsModel;

public class PollEmailCountManagerImpl implements PollEmailCountManager {
	@Autowired
	PollEmailCountDao pollEmailCountDao;
	@Autowired
	LoginService loginSrv;

	@Override
	public void storePollEmailCount(User user, InCyyte incyyte, int sharedCount) throws Exception {
		pollEmailCountDao.storePollEmailCount(user, incyyte, sharedCount);
	}

	@Override
	public int getPollEmailCountInfo(User user) throws Exception {
		return pollEmailCountDao.getPollEmailCountInfo(user);
	}

	@Override
	public List<String> validatePollEmails(List<Contact> contacts, boolean anonymous, User user) {
		int count = 0;	
		List<String> validEmails = new ArrayList<String>();
		ArrayList<String> blockedEmails = new ArrayList<String>();
		ArrayList<String> nonMembers = new ArrayList<String>();
		ArrayList<String> deactivatedEmails = new ArrayList<String>();
		ArrayList<String> notNotifyEmails = new ArrayList<String>();
		ArrayList<String> notexistedEmails = new ArrayList<String>();

		try {
			for(Contact contact : contacts){
				//Emails which are Blocked ny this user
				if (contact.getBlocked() != null && contact.getBlocked().equals("Y")){
					blockedEmails.add(contact.getEmail());
					continue; 
				}
				//Emails which are NonMenbers and for Anonymous Poll
				if ((contact.getStatus() == null || contact.getStatus().equals("NM")) && anonymous){
					nonMembers.add(contact.getEmail());
					continue; 
				}
				final User contactDetail = loginSrv.getUserDetailByEmailOrUsername(contact.getEmail(), null);
				Logger.debug("Recepients contactDetail " + contactDetail);
				//Emails which are deactivated
				if (contactDetail != null && StringUtils.equalsIgnoreCase(contactDetail.getStatus(), "DEACTIVATED")) {
					deactivatedEmails.add(contactDetail.getEmail());
					continue;
				}else if (contactDetail == null){
					Logger.debug("No user Exists with this contact : email: " + contact.getEmail());
				}
				//Emails which are NonMembers and Invited with question Email while sending poll
				if (contact.getStatus() == null || contact.getStatus().equals("NM") && !anonymous) {
					validEmails.add(contact.getEmail());
				} else {
					try {
						UserSettingsModel userSettings = loginSrv.getUserSettingsDetails(contactDetail.getId());
						Logger.debug("userSettings::" + userSettings);
						if (StringUtils.equals("Y", userSettings.getNotifypolls())) {
							Logger.debug("email is sending to::" + contact.getEmail());
							validEmails.add(contact.getEmail());
						}else{
							//Emails which Dont have notify Polls as 'Y'
							notNotifyEmails.add(contact.getEmail());
						}
					}
					catch (Exception e) {
						//The Exception can be raised in case of User doesn't exist in the system
						notexistedEmails.add(contact.getEmail());
					}
				}
			}
		}catch (Exception e) {
			Logger.debug("Exception::", e);
		}
		Logger.debug("Valid Emails::"+validEmails);
		Logger.debug("::Valid Emails::"+validEmails.size());
		return validEmails;
	}
}