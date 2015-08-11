/**
 *
 */
package com.incyyte.app.service;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.dao.groups.GroupDao;
import com.incyyte.app.domain.Contact;
import com.incyyte.app.domain.Group;
import com.incyyte.app.domain.GroupContact;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.util.GroupUtil;
import com.incyyte.app.web.model.UserContactlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.OutputStream;
import java.util.List;

/**
 * @author prakash
 */
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private AbstractDao abstractDao;

    @Override
    public void addGroup(Long userId, Group group) throws Exception {
        //pass the user id to group
        group.setUserId(userId);
        //validate the group name for user id
        // If Group is not valid this will raise an exception
        groupDao.validateGroupName(group);
        JdbcTemplate template = abstractDao.getJdbcTemplate("addGroup");
        try {
            //BEGIN TXN
            abstractDao.getTxnHelper().beginTxn();
            long groupId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("GROUPS_PK", true);
            group.setGroupId(groupId);
            groupDao.addGroup(group, template);
            groupDao.addGroupContacts(group, template);
            abstractDao.getTxnHelper().commitTxn();
            //CLOSED TXN
        } catch (DataAccessException e) {
            Logger.error("Exception:", e);
            abstractDao.getTxnHelper().rollbackTxn();
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addGroup");
        }
    }

    @Override
    public void editGroup(Long userId, Group group) throws Exception {
        JdbcTemplate template = abstractDao.getJdbcTemplate("editGroup");
        try {
            //pass the user id to group
            group.setUserId(userId);
            abstractDao.getTxnHelper().beginTxn();
          //Edit the Group information
            groupDao.editGroup(group, template);
            //Delete records if any is unselected (Removing from group list)
            List<Long> uiRecords = group.getSelectedGroupContactsList();
            List<GroupContact> dbRecords =  groupDao.getGroupContactMembers(group.getGroupId());
            List<GroupContact> deleteRecords = GroupUtil.getDeleteEligibleGrpContacts(uiRecords, dbRecords);
            Logger.debug("uiRecords::"+uiRecords);
            Logger.debug("dbRecords::"+dbRecords);
            Logger.debug("deleteRecords::"+deleteRecords);
            groupDao.deleteGrpContacts(group.getGroupId(), deleteRecords,template);
            
            //Add new contacts if any selected
            groupDao.addGroupContacts(group, template);

            abstractDao.getTxnHelper().commitTxn();
        } catch (DataAccessException e) {
            Logger.error("Exception:", e);
            abstractDao.getTxnHelper().rollbackTxn();
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "editGroup");
        }
    }

    @Override
    public void removeGroup(Long userId, Long groupId) throws Exception {
        JdbcTemplate template = abstractDao.getJdbcTemplate("removeGroup");
        try {
            abstractDao.getTxnHelper().beginTxn();

            groupDao.removeGroupIncyytes(userId, groupId, template);
            groupDao.removeAllGroupContacts(userId, groupId, template);
            groupDao.removeGroup(userId, groupId, template);

            abstractDao.getTxnHelper().commitTxn();
        } catch (DataAccessException e) {
            Logger.error("Exception:", e);
            abstractDao.getTxnHelper().rollbackTxn();
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "removeGroup");
        }
    }

    @Override
    public List<Group> getUserGroups(Long userId) throws DataAccessException {
        List<Group> userGroups = null;
        try {
            userGroups = groupDao.getUserGroups(userId);
        } catch (DataAccessException e) {
            Logger.error("DataAccessException::", e);
        }
        return userGroups;
    }

    @Override
    public Group getGroup(Long userId, Long groupId) throws DataAccessException {
        Group group = new Group();
        try {
            group = groupDao.getGroup(userId, groupId);
            List<Contact> groupContacts = groupDao.getGroupContacts(userId, groupId);

            Logger.debug("groupContacts.size:: " + groupContacts.size());
            Contact[] contactArr = new Contact[groupContacts.size()];
            contactArr = groupContacts.toArray(contactArr);

            group.setSelectedGroupContacts(contactArr);
        } catch (DataAccessException e) {
            Logger.error("Exception:", e);
        }
        return group;
    }

    @Override
    public List<Contact> getUserContacts(Long userId) throws DataAccessException {
        List<Contact> contacts = null;
        try {
            contacts = groupDao.getUserContacts(userId);
        } catch (DataAccessException e) {
            Logger.error("Exception:", e);
        }
        return contacts;
    }

    @Override
    public void streamGroupImage(Long userId, Long groupId, OutputStream contentStream) throws DataAccessException {
        groupDao.streamGroupImage(userId, groupId, contentStream);
    }

    @Override
    public List<Group> searchByGroupName(Long userID, String groupName) throws DataAccessException {
        List<Group> groups = null;
        try {
            groups = groupDao.searchByGroupName(userID, groupName);
        } catch (DataAccessException e) {
            Logger.error("Exception:", e);
        }
        return groups;
    }

    @Override
    public List<Group> searchByGroupPostcode(Long userID, String postcode) throws DataAccessException {
        List<Group> groups = null;
        try {
            groups = groupDao.searchByGroupPostcode(userID, postcode);
        } catch (DataAccessException e) {
            Logger.error("Exception:", e);
        }
        return groups;
    }

    @Override
    public String getUserGroupNames(Long userID) {
        String name = "";
        try {
            name = groupDao.UserGroupNames(userID);
        } catch (DataAccessException e) {
            Logger.error("Exception:", e);
        }
        return name;
    }

    @Override
    public List<Group> getUserGroups(Long userId, int i, int recordsPerPage, String param, String search, boolean limitFlag) {
        List<Group> userGroups = null;
        try {
            userGroups = groupDao.getUserGroups(userId, i, recordsPerPage, param, search, limitFlag);
        } catch (DataAccessException e) {
            Logger.error("Exception:", e);
        }
        return userGroups;
    }

    @Override
    public int DeleteMultiple(Group group, long id) {
        return groupDao.DeleteMultiple(group, id);
    }

    @Override
    public List<String> getMemberListByName(long id) {
        return groupDao.getMemberListByName(id);
    }

    @Override
    public UserContactlist getUserContactsLst(long id, int i, int recordsPerPage, String param, String search, boolean limitFlag) {
        return groupDao.getUserContactsLst(id, i, recordsPerPage, param, search, limitFlag);
    }
    
    @Override
    public UserContactlist editContacts(long id, int i, int recordsPerPage, String param, String search, boolean limitFlag) {
        return groupDao.editContactsList(id, i, recordsPerPage, param, search, limitFlag);
    }
    

    @Override
    public UserContactlist getUserContactsLst(long userid) {
        return groupDao.getUserContactsLst(userid);
    }

    @Override
    public boolean hasGroupBeenPolled(Long groupId) {
        return groupDao.hasGroupBeenPolled(groupId);
    }

    @Override
    public List<Contact> getUserShareContacts(long quesId, long sharerId) {
        return groupDao.getUserShareContacts(quesId, sharerId);
    }
}