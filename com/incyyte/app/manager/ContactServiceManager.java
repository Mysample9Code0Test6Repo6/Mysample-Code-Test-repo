package com.incyyte.app.manager;

import java.util.List;

import org.brickred.socialauth.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.incyyte.app.dao.contacts.ContactDao;
import com.incyyte.app.dao.login.RegisterDao;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;

public class ContactServiceManager implements ContactManager {

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private EmailManager emailMgr;

    @Override
    public long addContact(UserContactModel user, long userid) throws Exception {
        return contactDao.addContact(user, userid);
    }

    @Override
    public int modifyContact(UserContactModel user, long userid) throws Exception {
        return contactDao.modifyContact(user, userid);
    }

    @Override
    public int DeletContact(UserContactModel user, long userid) throws Exception {
        return contactDao.DeletContact(user, userid);
    }

    @Override
    public UserContactlist getUserContacts(long userid, int i, int recordsPerPage, String param, String criteria) {
        return contactDao.getUserContacts(userid, i, recordsPerPage, param, criteria);
    }
    
   @Override
    public UserContactlist getUserAndGrpContacts(long userid, long grpid, int i, int recordsPerPage, String param, String criteria) {
        return contactDao.getUserAndGrpContacts(userid, grpid, i, recordsPerPage, param, criteria);
    }

    @Override
    public UserContactlist getUserContactsFromSearch(int offSet, int recordsPerPage, long userid, String criteria, String param) {
        return contactDao.getUserContactsFromSearch(offSet, recordsPerPage, userid, criteria , param);
    }

    @Override
    public void makeHiddenContactsActive(List<UserContactModel> hiddenContacts) {
        contactDao.makeHiddenContactsActive(hiddenContacts);
    }

    @Override
    public UserContactModel getContact(long userid) {
        return contactDao.getContact(userid);
    }

    @Override
    public int BlockContact(UserContactModel user, long userid) throws Exception {
        return contactDao.BlockContact(user, userid);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public int SendInvite(UserContactModel usercontact, long userid) throws Exception {

        try {
            Logger.debug("usercontact::" + usercontact);
            usercontact = contactDao.SendInvite(usercontact, userid);
            User user = registerDao.getUser(userid);
            sendInviteEmail(usercontact, user);
            return 0;
        } catch (Exception e) {
            Logger.error("SendInvite Failed::" + e);
            throw e;
        }
    }

    @Override
    public UserContactlist SearchContact(UserContactModel usercontModel, long userid, String param, int offset, int noOfRecords, String criteria) {
        return contactDao.SearchContact(usercontModel, userid, param, offset, noOfRecords, criteria);
    }

    private void sendInviteEmail(UserContactModel cuser, User user) {
        emailMgr.setUser(user);
        emailMgr.setCuser(cuser);
        emailMgr.setRunFlag(Constants.SEND_INVITE_EMAIL);
        new Thread(emailMgr).start();
    }

    @Override
    public UserContactModel getContactByInv(String invitationId) {
        return contactDao.getContactByInv(invitationId);
    }

    @Override
    public void acceptInvitation(String invitationId) throws Exception {
        contactDao.acceptInvitation(invitationId);
    }

    @Override
    public User getContactProfile(String email) {
        return contactDao.getContactProfile(email);
    }

    @Override
    public int UnBlockContact(UserContactModel user, long userid) throws Exception {
        return contactDao.UnBlockContact(user, userid);
    }

    @Override
    public List<String> getMemberListByName(long id) {
        return contactDao.getMemberListByName(id);
    }

    @Override
    public List<String> getInvitedContactsList(long id) {
        return contactDao.getInvitedContactsList(id);
    }

    @Override
    public int DeleteMultiple(UserContactModel usercModel, long id) throws Exception {
        return contactDao.DeleteMultiple(usercModel, id);
    }

    @Override
    public int BlockMultiple(UserContactModel usercModel, long id) throws Exception {
        return contactDao.BlockMultiple(usercModel, id);
    }

    @Override
    public int InviteMultiple(long id, UserContactModel usercModel) throws Exception {
        //sendInviteEmail(usercModel) ;
        return contactDao.InviteMultiple(id, usercModel);
    }

    @Override
    public long contactExistForUser(Long userId, String contactEmail) {
        return contactDao.contactExistForUser(userId, contactEmail);
    }

    @Override
    public int updateContact(String contactEmail, long userId) throws Exception {
        return contactDao.updateContact(contactEmail, userId);
    }

    @Override
    public List<Contact> getuserContactListEmailids(long id) {
        // TODO Auto-generated method stub
        return contactDao.getuserContactListEmailids(id);
    }

    @Override
    public void addAsContact(String invitationId) throws Exception {
        contactDao.acceptInvitation(invitationId);
    }

	@Override
	public int getContactExist(UserContactModel cm, long inviterId) {
		return contactDao.getContactExist(cm,inviterId);
	}

	@Override
	public void updateContact(UserContactModel cm, long inviterId) throws Exception {
		contactDao.updateContact(cm,inviterId);
	}

	@Override
	public int getUserContactCount(long userid) {
		return  contactDao.getUserContactCount(userid);
	}

	@Override
	public int getnoOfRecords(Long userid) {
		return  contactDao.getnoOfRecords(userid);
	}
	
	@Override
	public UserContactModel personLinkedToUser(String personUsername, String UserEmail) {
		return contactDao.personLinkedToUser(personUsername, UserEmail);
}
	@Override
	public boolean isPersonLinkedToUser(String personUsername, String UserEmail) {
		return contactDao.verifyPersonLinkedToUser(personUsername, UserEmail);
	}

	@Override
	public long isPersonLinkedToUserButDeleted(String personUsername, String UserEmail) {
		return contactDao.verifyPersonLinkedToUserButDeleted(personUsername, UserEmail);
	}

	@Override
	public void reactivateDeletedContact(long contactsID){
		contactDao.reactivateDeletedContact(contactsID);
	}

    @Override
    public UserContactModel contactOfUser(String email, Long userId) throws Exception {
        return contactDao.contactOfUser(email, userId);
    }

	@Override
	public String getInvitationCode() {
		 return contactDao.getInvitationCode();
	}
}
