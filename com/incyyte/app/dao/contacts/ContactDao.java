package com.incyyte.app.dao.contacts;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.CreateUserException;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;
import org.brickred.socialauth.Contact;

import java.util.List;

public interface ContactDao {

    public void acceptInvitation(String invitationId) throws Exception;

    public long addContact(UserContactModel user, long userid) throws Exception;

    public int modifyContact(UserContactModel user, long userid) throws Exception;

    public int DeletContact(UserContactModel user, long userid) throws Exception;

    public int BlockContact(UserContactModel user, long userid) throws Exception;

    public UserContactModel SendInvite(UserContactModel usercontact, long userid) throws Exception;

    public UserContactlist getUserContacts(long userid, int i, int recordsPerPage, String param, String criteria);

    public UserContactlist SearchContact(UserContactModel usercontModel, long userid, String param, int offset, int noOfRecords, String criteria);

    public UserContactModel getContact(long userid);

    public UserContactModel getContactByInv(String invitationId);

    public User getContactProfile(String email);

    public int UnBlockContact(UserContactModel user, long userid) throws CreateUserException, Exception;

    public List<String> getMemberListByName(long id);

    public List<String> getInvitedContactsList(long id);

    public int DeleteMultiple(UserContactModel usercModel, long id) throws Exception;

    public int BlockMultiple(UserContactModel usercModel, long id) throws Exception;

    public int InviteMultiple(long id, UserContactModel usercModel) throws Exception;

    boolean verifyContactalreadyMember(String cemail, long contactid, String mode);

    boolean verifyUniqueInvitationCode(String invCode);

    boolean verifyPersonLinkedToUser(String personUsername, String UserEmail);

    UserContactModel personLinkedToUser(String personUsername, String UserEmail);

    long verifyPersonLinkedToUserButDeleted(String personUsername, String UserEmail);

    public void reactivateDeletedContact(long contactID);

    UserContactModel getContactbyemail(String emailid);

    long contactExistForUser(long userId, String contactEmail);

    public int updateContact(String contactEmail, long userId) throws Exception;

    public List<Contact> getuserContactListEmailids(long id);

    public String getProfileImgURL(String id);

    public int getContactExist(UserContactModel cm, long inviterId);

    public void updateContact(UserContactModel cm, long inviterId) throws Exception;

    public void linkUserContacts(String userEmail) throws Exception;

    public void linkUserContact(String userEmail) throws Exception;

    public int getUserContactCount(long userid);

    UserContactModel contactOfUser(String email, Long userId) throws Exception;

    public String getInvitationCode();

    public int getnoOfRecords(Long userid);

    void makeHiddenContactsActive(List<UserContactModel> hiddenContacts);

    public UserContactlist getUserContactsFromSearch(int offSet, int recordsPerPage, long userid, String criteria, String param);

	public UserContactlist getUserAndGrpContacts(long userid,long grpid, int offset,	int noOfRecords, String param, String criteria);
}
