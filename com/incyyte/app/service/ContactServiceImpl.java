/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class implements the contact services
 * This class will delegate services to the service manager
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 29 Nov 2010
 */
package com.incyyte.app.service;

import java.util.List;

import org.brickred.socialauth.Contact;

import com.incyyte.app.domain.User;
import com.incyyte.app.manager.ContactManager;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactManager contactMgr;

    @Override
    public long addContact(UserContactModel usercontact, long userid) throws Exception {
        return contactMgr.addContact(usercontact, userid);
    }

    @Override
    public int modifyContact(UserContactModel usercontact, long userid) throws Exception {
        return contactMgr.modifyContact(usercontact, userid);
    }

    @Override
    public int DeletContact(UserContactModel usercontact, long userid) throws Exception {
        return contactMgr.DeletContact(usercontact, userid);
    }

    @Override
    public UserContactlist getUserContacts(long userid, int i, int recordsPerPage, String param, String criteria) {
        return contactMgr.getUserContacts(userid, i, recordsPerPage, param, criteria);
    }
    
    @Override
    public UserContactlist getUserAndGrpContacts(long userid, long grpid, int i, int recordsPerPage, String param, String criteria) {
        return contactMgr.getUserAndGrpContacts(userid, grpid, i, recordsPerPage, param, criteria);
    }
    
    @Override
    public UserContactlist getUserContactsFromSearch(int offSet, int recordsPerPage, long userid, String criteria, String param) {
        return contactMgr.getUserContactsFromSearch(offSet, recordsPerPage, userid, criteria , param);
    }

    @Override
    public UserContactModel getContact(long userid) {
        return contactMgr.getContact(userid);
    }

    @Override
    public int BlockContact(UserContactModel usercontact, long userid) throws Exception {
        return contactMgr.BlockContact(usercontact, userid);
    }

    @Override
    public int UnBlockContact(UserContactModel usercontact, long userid) throws Exception {
        return contactMgr.UnBlockContact(usercontact, userid);
    }


    @Override
    public int SendInvite(UserContactModel usercontact, long userid) throws Exception {
        return contactMgr.SendInvite(usercontact, userid);
    }

    @Override
    public UserContactlist SearchContact(UserContactModel usercontModel, long userid, String param, int offset, int noOfRecords, String criteria) {
        return contactMgr.SearchContact(usercontModel, userid, param, offset, noOfRecords, criteria);
    }

    @Override
    public UserContactModel getContactByInv(String invitationId) {
        return contactMgr.getContactByInv(invitationId);
    }

    @Override
    public void acceptInvitation(String invitationId) throws Exception {
        contactMgr.acceptInvitation(invitationId);
    }

    @Override
    public User getContactProfile(String email) {
        return contactMgr.getContactProfile(email);
    }

    @Override
    public List<String> getMemberListByName(long id) {
        return contactMgr.getMemberListByName(id);
    }

    @Override
    public List<Contact> getuserContactListEmailids(long id) {
        return contactMgr.getuserContactListEmailids(id);
    }

    @Override
    public List<String> getInvitedContactsList(long id) {
        return contactMgr.getInvitedContactsList(id);
    }

    @Override
    public int DeleteMultiple(UserContactModel usercModel, long id) throws Exception {
        return contactMgr.DeleteMultiple(usercModel, id);
    }

    @Override
    public int BlockMultiple(UserContactModel usercModel, long id) throws Exception {
        return contactMgr.BlockMultiple(usercModel, id);
    }

    @Override
    public int InviteMultiple(long id, UserContactModel usercModel) throws Exception {
        return contactMgr.InviteMultiple(id, usercModel);
    }

    @Override
    public int updateContact(String email, long userId) throws Exception {
        return contactMgr.updateContact(email, userId);
    }

    @Override
    public long contactExistForUser(Long id, String email) {
        return contactMgr.contactExistForUser(id, email);
    }

    @Override
    public UserContactModel contactOfUser(String email, Long userId) throws Exception {
        return contactMgr.contactOfUser(email, userId);
    }

    @Override
    public void addAsContact(String invitationId) throws Exception {
        contactMgr.addAsContact(invitationId);
    }

    @Override
    public int getContactExist(UserContactModel cm, long inviterId) {
        return contactMgr.getContactExist(cm, inviterId);
    }

    @Override
    public void updateContact(UserContactModel cm, long inviterId) throws Exception {
        contactMgr.updateContact(cm, inviterId);
    }

    @Override
    public int getUserContactCount(long userid) {
        Logger.debug("inside service impl:contacts count::" + userid);
        return contactMgr.getUserContactCount(userid);
    }

    @Override
    public int getnoOfRecords(Long userid) {
        Logger.debug("inside service impl:no of records ::" + userid);
        return contactMgr.getnoOfRecords(userid);
    }

    @Override
    public void makeHiddenContactsActive(List<UserContactModel> hiddenContacts) {
        contactMgr.makeHiddenContactsActive(hiddenContacts);
    }

    @Override
    public void makeHiddenContactsActive(UserContactModel hiddenContact) {
        List<UserContactModel> hiddenContacts = new ArrayList<UserContactModel>();
        hiddenContacts.add(hiddenContact);
        contactMgr.makeHiddenContactsActive(hiddenContacts);
    }

	@Override
    public UserContactModel personLinkedToUser(String personUsername, String UserEmail) {
        return contactMgr.personLinkedToUser(personUsername, UserEmail);
    }

	@Override
	public boolean isPersonLinkedToUser(String personUsername, String UserEmail) {
		return contactMgr.isPersonLinkedToUser(personUsername,UserEmail);
	}
	@Override
	public long isPersonLinkedToUserButDeleted(String personUsername, String UserEmail) {
		return contactMgr.isPersonLinkedToUserButDeleted(personUsername,UserEmail);
	}

    @Override
    public void reactivateDeletedContact(long contactsID) {
        contactMgr.reactivateDeletedContact(contactsID);
    }

    @Override
    public String getInvitationCode() {
        return contactMgr.getInvitationCode();
    }
}