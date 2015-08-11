/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This service interface provides Contact services
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Chandan
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.manager;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.CreateUserException;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;
import org.brickred.socialauth.Contact;

import java.util.List;

public interface ContactManager {

    public void acceptInvitation(String invitationId) throws Exception;

    public long addContact(UserContactModel user, long userid) throws Exception;

    public int modifyContact(UserContactModel user, long userid) throws Exception;

    public int DeletContact(UserContactModel user, long userid) throws Exception;

    public int BlockContact(UserContactModel user, long userid) throws Exception;

    public int SendInvite(UserContactModel usercontact, long userid) throws Exception;

    public UserContactlist getUserContacts(long userid, int i, int recordsPerPage, String param, String criteria);

    public UserContactModel getContact(long userid);

    public UserContactModel getContactByInv(String invitationId);

    public UserContactlist SearchContact(UserContactModel usercontModel, long userid, String param, int offset, int noOfRecords, String criteria);

    public User getContactProfile(String email);

    public int UnBlockContact(UserContactModel user, long userid) throws CreateUserException, Exception;

    public List<String> getMemberListByName(long id);

    public List<String> getInvitedContactsList(long id);

    public int DeleteMultiple(UserContactModel usercModel, long id) throws Exception;

    public int BlockMultiple(UserContactModel usercModel, long id) throws Exception;

    public int InviteMultiple(long id, UserContactModel usercModel) throws Exception;

    public long contactExistForUser(Long id, String email);

    int updateContact(String contactEmail, long userId) throws Exception;

    public List<Contact> getuserContactListEmailids(long id);

    public void addAsContact(String invitationId) throws Exception;

    public int getContactExist(UserContactModel cm, long inviterId);

    public void updateContact(UserContactModel cm, long inviterId) throws Exception;

    public int getUserContactCount(long userid);

    public UserContactModel personLinkedToUser(String personUsername, String UserEmail);

	public boolean isPersonLinkedToUser(String personUsername, String UserEmail);

    public long isPersonLinkedToUserButDeleted(String personUsername, String UserEmail);

    public void reactivateDeletedContact(long contactsID);

    UserContactModel contactOfUser(String email, Long userId) throws Exception;
    
    public String getInvitationCode();

	public int getnoOfRecords(Long userid);

	public UserContactlist getUserContactsFromSearch(int offSet, int recordsPerPage, long userid, String criteria, String param);

    void makeHiddenContactsActive(List<UserContactModel> hiddenContacts);

	public UserContactlist getUserAndGrpContacts(long userid, long grpid, int i, int recordsPerPage, String param, String criteria);

}
