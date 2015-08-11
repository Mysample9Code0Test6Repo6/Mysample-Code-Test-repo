/**
 *
 */
package com.incyyte.app.service;

import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.Group;
import com.incyyte.app.service.Exceptions.CreateGroupException;
import com.incyyte.app.web.model.UserContactlist;
import org.springframework.dao.DataAccessException;

import java.io.OutputStream;
import java.util.List;

/**
 * @author prakash
 */
public interface GroupService {

    /**
     * Adds a new group to the User.
     *
     * @param userId
     * @param grp
     * @throws Exception
     * @throws CreateGroupException
     */
    public void addGroup(Long userId, Group grp) throws Exception;

    /**
     * Amends group details for the User.
     *
     * @param userId
     * @param grp
     * @throws Exception
     */
    public void editGroup(Long userId, Group grp) throws Exception;

    /**
     * Amends group details for the User.
     *
     * @param userId
     * @param groupId
     * @throws Exception
     */
    public void removeGroup(Long userId, Long groupId) throws Exception;

    /**
     * Returns a Group Object. Retrieves a particular group information
     *
     * @param userId
     * @param groupId
     */
    public Group getGroup(Long userId, Long groupId) throws DataAccessException;

    /**
     * Returns a List of Group Objects for the user based on user id.
     *
     * @param userId
     */
    public List<Group> getUserGroups(Long userId) throws DataAccessException;

    /**
     * @param userId
     * @return
     */
    public List<Contact> getUserContacts(Long userId) throws DataAccessException;

    /**
     * Returns a List of Group Objects for the User based on group name.
     *
     * @param userID
     * @param groupName
     * @return
     */
    public List<Group> searchByGroupName(Long userID, String groupName) throws DataAccessException;

    /**
     * Returns a List of Group Objects for the User based on postcode.
     *
     * @param userID
     * @param postcode
     * @return
     */
    public List<Group> searchByGroupPostcode(Long userID, String postcode) throws DataAccessException;

    /**
     * @param userId
     * @param groupId
     * @param contentStream
     */
    public void streamGroupImage(Long userId, Long groupId, OutputStream contentStream) throws DataAccessException;

    public String getUserGroupNames(Long id);

    public List<Group> getUserGroups(Long id, int i, int recordsPerPage,
                                     java.lang.String param, java.lang.String search, boolean limitFlag);

    public int DeleteMultiple(Group group, long id);

    public UserContactlist getUserContactsLst(long id, int i, int recordsPerPage, String param, String search, boolean limitFlag);
    
    public UserContactlist editContacts(long id, int i, int recordsPerPage, String param, String search, boolean limitFlag);

    public List<String> getMemberListByName(long id);

    public UserContactlist getUserContactsLst(long userid);

    public boolean hasGroupBeenPolled(Long groupId);

    public List<Contact> getUserShareContacts(long quesId, long sharerId);

}
