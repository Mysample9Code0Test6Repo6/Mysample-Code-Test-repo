package com.incyyte.app.dao.groups;

import com.incyyte.app.domain.*;
import com.incyyte.app.web.model.UserContactlist;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.OutputStream;
import java.util.Hashtable;
import java.util.List;

public interface GroupDao {

    public boolean validateGroupName(Group grp) throws Exception;

    public void addGroup(Group grp, JdbcTemplate template) throws Exception;

    public void addGroupContacts(Group grp, JdbcTemplate template) throws Exception;

    public List<Contact> addShareGroupContacts(Group grp, Hashtable shareContacts, Long questionId, JdbcTemplate template) throws Exception;

    public List<Contact> addShareGroupContacts(Group grp, Hashtable shareContacts, Long questionId) throws DataAccessException;

    public void editGroup(Group grp, JdbcTemplate template) throws Exception;

    public void removeGroup(Long userId, Long grpID, JdbcTemplate template) throws Exception;

    public void removeGroupIncyytes(Long userId, Long groupId, JdbcTemplate template) throws Exception;

    public Group getGroup(Long userId, Long groupId) throws DataAccessException;

    public List<Contact> getGroupContacts(Long userId, Long groupId) throws DataAccessException;

    public List<Group> getUserGroups(Long userId) throws DataAccessException;

    public List<Group> searchByGroupName(Long userID, String groupName) throws DataAccessException;

    public List<Group> searchByGroupPostcode(Long userID, String postcode) throws DataAccessException;

    public List<Contact> getUserContacts(Long userId) throws DataAccessException;

    public void streamGroupImage(Long userId, Long groupId, OutputStream contentStream) throws DataAccessException;

    public String UserGroupNames(Long userID);

    List<Group> getUserGroups(Long userId, int i, int recordsPerPage, String param, String search, boolean limitFlag);

    public int DeleteMultiple(Group group, long id);

    public List<String> getMemberListByName(long id);

    public UserContactlist getUserContactsLst(long id, int i, int recordsPerPage, String param, String search, boolean limitFlag);
    
    public UserContactlist editContactsList(long id, int i, int recordsPerPage, String param, String search, boolean limitFlag);

    void removeAllGroupContacts(Long userId, Long groupId, JdbcTemplate template) throws Exception;

    public UserContactlist getUserContactsLst(long userid);

    public boolean hasGroupBeenPolled(Long groupId);

    public List<Contact> shareInCyyte(Share share);

    public void incrementTotalIncyyted(long grpId, long questionId, int total);

    public List<Contact> getUserShareContacts(long quesId, long sharerId);

    void insertGrpSharedInCyyte(List<GroupSharedInCyyte> sharedInCyyte) throws Exception;

    List<GroupContact> getGroupContactMembers(Long groupId) throws Exception;

    List<Long> insertBatchGrpContacts(List<GroupContact> groupContacts) throws Exception;
    
    Long getMemberId( long contactId , long grpId)throws Exception;

    List<Long> insertBatchGrpContacts(List<GroupContact> groupContacts, JdbcTemplate template) throws Exception;

    void deleteGrpContacts(Long groupId,List<GroupContact> deleteList, JdbcTemplate template) throws Exception;

}