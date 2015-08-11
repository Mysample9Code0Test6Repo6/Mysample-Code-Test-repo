package com.incyyte.app.dao.groups;

import com.incyyte.app.dao.contacts.ContactDaoQueries;
import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameterFactory;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.dao.user.UserDaoQueries;
import com.incyyte.app.dao.user.rowmapper.ContactNewRowMapper;
import com.incyyte.app.dao.user.rowmapper.GroupContactRowMapper;
import com.incyyte.app.dao.user.rowmapper.UserContactRowMapper;
import com.incyyte.app.domain.*;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.IncyyteProperties;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author prakash
 */
public class GroupDaoImpl implements GroupDao {

    private AbstractDao abstractDao;
    private LobHandler lobHandler;

    /**
     * @return the abstractDao
     */
    public AbstractDao getAbstractDao() {
        return abstractDao;
    }

    /**
     * @param abstractDao the abstractDao to set
     */
    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    /**
     * @return the lobHandler
     */
    public LobHandler getLobHandler() {
        return lobHandler;
    }

    /**
     * @param lobHandler the lobHandler to set
     */
    public void setLobHandler(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.dao.groups.GroupDao#validateGroupName()
      */
    @Override
    public boolean validateGroupName(Group grp) throws Exception {
        Logger.debug("Validating group name:: " + grp.getGroupName());
        int groupNameCount = QueryHelper.doQueryForInt(abstractDao, "validateGroupName", GroupDaoQueries.getValidateGroupQuery(), QueryParameterFactory.getValidateGroupParams(grp));
        Logger.debug("groupNameCount:: " + groupNameCount);
        if (groupNameCount > 0) {
            throw new Exception("Group name already exists");
        }
        return true;
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.dao.groups.GroupDao#addGroup(java.lang.Long, com.incyyte.app.domain.Group)
      */
    @Override
    public void addGroup(Group group, JdbcTemplate template) throws Exception {

        QueryParameters params = new QueryParameters();
        params.addParam(group.getGroupId());
        params.addParam(group.getUserId());
        params.addParam(group.getGroupName());
        params.addParam(group.getGroupType());
        params.addParam(group.getPostCode());
        params.addParam(group.getDescription());
        params.addParam(group.getDisplayInGroupDirectory());
        params.addParam(group.getAutoJoin());
        params.addParam(group.getGroupImage());
        params.addParam("");
        params.addParam("");
        params.addParam(group.getUserId());
        params.addParam(group.getUserId());
        QueryHelper.doUpdate(abstractDao, "addGroup", GroupDaoQueries.getAddGroupQuery(), params);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.dao.groups.GroupDao#addGroupContacts(com.incyyte.app.domain.Group)
      */
    @Override
    public void addGroupContacts(Group grp, JdbcTemplate template) throws Exception {
        Logger.debug("Creating group contacts for group:: " + grp);
        grp.setGroupType("Dummy");
        List<Long> contactsList = grp.getSelectedGroupContactsList();
        List<GroupContact> groupContacts = new ArrayList<GroupContact>();
        if (!grp.getGroupType().equals("Privilge")) {
            for (Long contactId : contactsList) {
                Logger.debug("c.getContactId():: " + contactId);
                GroupContact groupContact = new GroupContact();
                groupContact.setContactId(contactId);
                groupContact.setGroupId(grp.getGroupId());
                groupContact.setCreatedBy(String.valueOf(grp.getUserId()));
                groupContact.setLastUpdatedBy(String.valueOf(grp.getUserId()));
                groupContact.setRole("Member");
                groupContacts.add(groupContact);
            }
        } else {
            for (Long contactId : contactsList) {
                Logger.debug("c.getContactId():: " + contactId);
                for (Long contactIds : contactsList) {
                    GroupContact groupContact = new GroupContact();
                    groupContact.setContactId(contactId);
                    groupContact.setGroupId(grp.getGroupId());
                    groupContact.setCreatedBy(String.valueOf(grp.getUserId()));
                    groupContact.setLastUpdatedBy(String.valueOf(grp.getUserId()));
                    if (!contactIds.equals(contactId)) {
                        groupContact.setRole("Member");
                    } else {
                        groupContact.setRole("Admin");
                    }
                    groupContacts.add(groupContact);
                }
            }
        }
        insertBatchGrpContacts(groupContacts, template);
    }

    @Override
    public List<Contact> addShareGroupContacts(Group grp, Hashtable shareContacts, Long questionId) throws DataAccessException {
        JdbcTemplate template = abstractDao.getJdbcTemplate("addShareGroupContacts");
        try {
            return addShareGroupContacts(grp, shareContacts, questionId, template);
        } catch (DataAccessException e) {
            Logger.error("DataAccessException:", e);
            abstractDao.getTxnHelper().rollbackTxn();
        } catch (Exception e) {
            Logger.error("Exception:", e);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addShareGroupContacts");
        }
        return null;
    }

    @Override
    public List<Contact> addShareGroupContacts(Group grp, Hashtable shareContacts, Long questionId, JdbcTemplate template) throws Exception {
        Logger.debug("Creating group contacts for group:: " + grp.getGroupName());
        List<Contact> validSharedGrpContacts = new ArrayList<Contact>();
        List<Long> contactsList = grp.getSelectedGroupContactsList();
        List<GroupContact> grpContacts = new ArrayList<GroupContact>();
        for (Long contactId : contactsList) {
            if (!checkContactHasBeenPolled((Long) contactId, questionId.longValue())) {
                Logger.debug("c.getContactId():: " + contactId);
                GroupContact grpContact = new GroupContact();
                grpContact.setGroupId(grp.getGroupId());
                grpContact.setContactId(contactId);
                grpContact.setCreatedBy(String.valueOf(grp.getUserId()));
                grpContact.setLastUpdatedBy(String.valueOf(grp.getUserId()));
                grpContact.setRole("Member");
                grpContacts.add(grpContact);
                validSharedGrpContacts.add((Contact) shareContacts.get(contactId));
            }
        }
        if (!grpContacts.isEmpty()) {
            List<Long> updates = insertBatchGrpContacts(grpContacts, template);
            Logger.debug("updates:: " + updates.size());
        }
        return validSharedGrpContacts;
    }

    @Override
    public void editGroup(Group group, JdbcTemplate template) throws Exception {
        Logger.debug("Editing group with name:: " + group);
        group.setGroupType("Dummy");
        Logger.debug("group.getGroupType() type >> " + group.getGroupType());

        QueryParameters params = new QueryParameters();
        params.addParam(group.getGroupName());
        params.addParam(group.getGroupType());
        params.addParam(group.getDescription());
        params.addParam(group.getPostCode());
        params.addParam(group.getDisplayInGroupDirectory());
        params.addParam(group.getAutoJoin());
        params.addParam(group.getGroupImage());
        params.addParam("");
        params.addParam("");
        params.addParam(group.getUserId());
        params.addParam(group.getGroupId());
        params.addParam(group.getUserId());
        QueryHelper.doUpdate(abstractDao, "editGroup", GroupDaoQueries.getEditGroupQuery(), params);
    }

    @Override
    public void removeGroup(Long userId, Long grpID, JdbcTemplate template) throws Exception {
        Logger.debug("Removing user group...");

        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(grpID);
        params.addParam(userId);
        QueryHelper.doUpdate(abstractDao, "removeGroup", GroupDaoQueries.getRemoveGroupQuery(), params);
    }

    @Override
    public void removeGroupIncyytes(Long userId, Long grpID, JdbcTemplate template) throws Exception {
        Logger.debug("Removing user group contacts...");
        QueryParameters params = new QueryParameters();
        params.addParam(grpID);
        QueryHelper.doUpdate(abstractDao, "removeGroupIncyytes", GroupDaoQueries.getRemoveIncyyteQuery(), params);
    }

    @Override
    public Group getGroup(Long userId, Long grpID) throws DataAccessException {
        Logger.debug("Retrieving user group...");
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(grpID);
        Group group = (Group) QueryHelper.doQueryForObject(abstractDao, "getGroup", GroupDaoQueries.getUserGroupsQuery(), params, new RowMapper<Group>() {

            @Override
            public Group mapRow(ResultSet rs, int arg1) throws SQLException {
                Group group = new Group();
                group.setGroupId(rs.getLong("groupId"));
                group.setGroupName(rs.getString("name"));
                group.setGroupType(rs.getString("type"));
                group.setDescription(rs.getString("description"));
                group.setUserId(rs.getLong("fk_userid"));
                group.setAutoJoin(rs.getString("auto_join"));
                group.setDisplayInGroupDirectory(rs.getBoolean("incyyte_dir_flag"));
                String imageName = rs.getString("image_name");
                group.setLogo(imageName);
                group.setPostCode(rs.getString("post_code"));

                return group;
            }
        });

        return group;
    }

    @Override
    public List<Contact> getGroupContacts(Long userId, Long groupId) throws DataAccessException {
        Logger.debug("Retrieving group contacts...");
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(groupId);

        Logger.debug("Query --->" + GroupDaoQueries.getUserGroupContactsQuery());

        List<Contact> contacts = QueryHelper.doQuery(abstractDao, "getGroupContacts", GroupDaoQueries.getUserGroupContactsQuery(), params, new RowMapper<Contact>() {

            @Override
            public Contact mapRow(ResultSet rs, int arg1) throws SQLException {
                Contact contact = new Contact();
                contact.setContactId(rs.getLong("contactid"));
                contact.setNickname(rs.getString("nickname"));
                contact.setFirstName(rs.getString("firstname"));
                contact.setLastName(rs.getString("lastname"));
                contact.setEmail(rs.getString("email"));
                contact.setUserId(rs.getLong("fk_userid"));

                return contact;
            }
        });

        return contacts;
    }

    @Override
    public List<Group> getUserGroups(Long userId) throws DataAccessException {
        Logger.debug("Retrieving groups for user...");
        String groupsQuery = "SELECT groupid,fk_userid,name,type,post_code,incyyte_dir_flag,auto_join,group_image,description " +
        		"  FROM groups " +
        		" WHERE fk_userid = ? " +
        		"   AND active_ind = 'A' " +
        		"   AND description <> 'AUTO_GEN' ";
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        List<Group> groups = QueryHelper.doQuery(abstractDao, "getUserGroups", groupsQuery, params, new RowMapper<Group>() {
            @Override
            public Group mapRow(ResultSet rs, int arg1) throws SQLException {
                Group group = new Group();
                group.setGroupId(rs.getLong("groupId"));
                group.setGroupName(rs.getString("name"));
                group.setGroupType(rs.getString("type"));
                group.setDescription(rs.getString("description"));
                group.setUserId(rs.getLong("fk_userid"));
                group.setAutoJoin(rs.getString("auto_join"));
                group.setDisplayInGroupDirectory(rs.getBoolean("incyyte_dir_flag"));
                group.setGroupImage(rs.getBytes("group_image"));
                group.setPostCode(rs.getString("post_code"));
                group.setGroupSize(rs.getInt("count"));
                return group;
            }
        });
        return groups;
    }

    @Override
    public List<Group> searchByGroupName(Long userId, String groupName) throws DataAccessException {
        Logger.debug("Searching groups by name...");
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        String query = GroupDaoQueries.getSearchByGroupNameQuery1() + "'%" + groupName + "%'" + GroupDaoQueries.getSearchByGroupNameQuery2();

        Logger.debug("Group Search query >> " + query);
        List<Group> groups = QueryHelper.doQuery(abstractDao, "searchByGroupName", query, params, new RowMapper<Group>() {

            @Override
            public Group mapRow(ResultSet rs, int arg1) throws SQLException {
                Group group = new Group();
                group.setGroupId(rs.getLong("groupId"));
                group.setGroupName(rs.getString("name"));
                group.setGroupType(rs.getString("type"));
                group.setDescription(rs.getString("description"));
                group.setUserId(rs.getLong("fk_userid"));
                group.setAutoJoin(rs.getString("auto_join"));
                group.setDisplayInGroupDirectory(rs.getBoolean("incyyte_dir_flag"));
                group.setGroupImage(rs.getBytes("group_image"));
                group.setPostCode(rs.getString("post_code"));

                Logger.debug("Group name >> " + group.getGroupName());
                return group;
            }
        });

        Logger.debug("Groups found >> " + groups.size());

        return groups;
    }

    @Override
    public List<Group> searchByGroupPostcode(Long userId, String postcode) throws DataAccessException {
        Logger.debug("Searching groups by post code...");

        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        String query = GroupDaoQueries.getSearchByPostCodeQuery1() + "'%" + postcode + "%'" + GroupDaoQueries.getSearchByPostCodeQuery2();
        Logger.debug("Post code Search query >> " + query);

        List<Group> groups = QueryHelper.doQuery(abstractDao, "searchByGroupPostcode", query, params, new RowMapper<Group>() {

            @Override
            public Group mapRow(ResultSet rs, int arg1) throws SQLException {
                Group group = new Group();
                group.setGroupId(rs.getLong("groupId"));
                group.setGroupName(rs.getString("name"));
                group.setGroupType(rs.getString("type"));
                group.setDescription(rs.getString("description"));
                group.setUserId(rs.getLong("fk_userid"));
                group.setAutoJoin(rs.getString("auto_join"));
                group.setDisplayInGroupDirectory(rs.getBoolean("incyyte_dir_flag"));
                group.setGroupImage(rs.getBytes("group_image"));
                group.setPostCode(rs.getString("post_code"));
                return group;
            }
        });
        Logger.debug("Groups found >> " + groups.size());
        return groups;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Contact> getUserContacts(Long userId) throws DataAccessException {
        Logger.debug("Retrieving user group...");

        QueryParameters params = new QueryParameters();
        params.addParam(userId);

        List<Contact> contacts = QueryHelper.doQuery(abstractDao, "getUserContacts", GroupDaoQueries.getUserContactsQuery(), params, new RowMapper<Contact>() {
            @Override
            public Contact mapRow(ResultSet rs, int arg1) throws SQLException {
                Contact contact = new Contact();
                contact.setId(rs.getLong("contactid"));
                contact.setContactId(rs.getLong("contactid"));
                contact.setMemberId(rs.getLong("fk_userid"));
                contact.setNickname(rs.getString("nickname"));
                contact.setFirstName(rs.getString("firstname"));
                contact.setLastName(rs.getString("lastname"));
                contact.setUserId(rs.getLong("fk_userid"));
                contact.setEmail(rs.getString("email"));
                return contact;
            }
        });
        return contacts;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void streamGroupImage(Long userId, Long groupId, final OutputStream contentStream) throws DataAccessException {
        Logger.debug("Streaming group image...");
        Logger.debug("userId::" + userId);
        Logger.debug("groupId::" + groupId);
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(groupId);
        QueryHelper.doQuery(abstractDao, "streamGroupImage", "SELECT group_image FROM groups WHERE fk_userid = ? AND groupid = ? AND active_ind = 'A'", params,
                new AbstractLobStreamingResultSetExtractor() {

                    public void streamData(ResultSet rs) throws SQLException, IOException {
                        InputStream is = lobHandler.getBlobAsBinaryStream(rs, "group_image");
                        if (is != null) {
                            FileCopyUtils.copy(is, contentStream);
                        }
                    }
                }
        );
    }

    @Override
    public String UserGroupNames(Long userID) {
        JdbcTemplate template = abstractDao.getJdbcTemplate("UserGroupNames");
        String name = "";

        try {
            Object[] obj = {userID};
            List names = template.queryForList(GroupDaoQueries.getGroupnames().toString(), obj);

            //QueryParameters params = new QueryParameters();
            //params.addParam(userID);
            //List<String> names = QueryHelper.doQueryForList(abstractDao, "UserGroupNames", GroupDaoQueries.getGroupnames().toString(), params);
            Logger.debug("names" + names.size());
            for (int i = 0; i < names.size(); i++)
                name = name + "," + names.get(i);

            return name;

        } catch (Exception e) {
            Logger.error("addResponse: Failed ", e);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "UserGroupNames");
        }
        return name;

    }

    @Override
    public List<Group> getUserGroups(Long userId, int i, int recordsPerPage, String param, String search, boolean limitFlag) {
        Logger.debug("Retrieving groups for user...");
        QueryParameters params = new QueryParameters();
        params.addParam(userId);

        String query = GroupDaoQueries.getSearchGroupsQuery() + " order by groupid desc limit " + i + ", " + recordsPerPage;
        if (null != param && !param.equals(""))
            query = GroupDaoQueries.getSearchGroupsQuery() + " and name like '%" + param + "%' " + " order by groupid desc limit " + i + ", " + recordsPerPage;
        return QueryHelper.doQuery(abstractDao, "getUserGroups", query, params, new RowMapper<Group>() {
            @Override
            public Group mapRow(ResultSet rs, int arg1) throws SQLException {
                Group group = new Group();
                JdbcTemplate jdbcTemplate = abstractDao.getJdbcTemplate();
                group.setGroupId(rs.getLong("groupId"));
                group.setGroupName(rs.getString("name"));
                group.setGroupType(rs.getString("type"));
                group.setDescription(rs.getString("description"));
                group.setUserId(rs.getLong("fk_userid"));
                group.setAutoJoin(rs.getString("auto_join"));
                group.setDisplayInGroupDirectory(rs.getBoolean("incyyte_dir_flag"));
                group.setGroupImage(rs.getBytes("group_image"));
                group.setPostCode(rs.getString("post_code"));
                group.setGroupSize(rs.getInt("count"));
                return group;
            }
        });

    }

    @Override
    public void removeAllGroupContacts(Long userId, Long groupId, JdbcTemplate template) throws Exception {
        Logger.debug("Removing user group contacts...");

        JdbcTemplate jdbcTemplate = abstractDao.getJdbcTemplate();
        QueryParameters params = new QueryParameters();
        params.addParam(groupId);

        Logger.debug("groupId:: " + groupId);
        Logger.debug("userId:: " + userId);
        int updateCount = QueryHelper.doUpdate(abstractDao, "removeAllGroupContacts", GroupDaoQueries.getRemoveAllGrpContactsQuery(), params);


        Logger.debug("updateCount" + updateCount);
    }

    @Override
    public int DeleteMultiple(Group group, long id) {
        String deleteQuery = "UPDATE groups  SET  active_ind='D' , last_updated = SYSDATE(),  updated_by =?  WHERE  fk_userid = ? and  groupid in  ";
        StringBuilder ids = new StringBuilder();
        StringTokenizer st = new StringTokenizer(group.getChecked(), ",");
        while (st.hasMoreTokens()) {
            if (ids.length() == 0)
                ids = ids.append(st.nextToken().replace("'", ""));
            else
                ids = ids.append(",").append(st.nextToken().replace("'", ""));
        }
        deleteQuery = deleteQuery + "( " + ids + "  )";
        try {
            QueryParameters params = new QueryParameters();
            params.addParam(id);
            params.addParam(id);
            QueryHelper.doUpdate(abstractDao, "DeleteMultiple", deleteQuery, params);
        } catch (Exception e) {
            Logger.error("DeleteMultiple: Failed ", e);
        }
        return 0;
    }

    @Override
    public List<String> getMemberListByName(long id) {
        String memberbynamequery = GroupDaoQueries.getMembersByName().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(id);
        return QueryHelper.doQuery(abstractDao, "getMemberListByName", memberbynamequery, params, new RowMapper() {
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        });
    }

    @SuppressWarnings({})
    @Override
    public UserContactlist getUserContactsLst(long userid, int offset, int noOfRecords, String param, String criteria, boolean limitFlag) {
        String getUserContacts = ContactDaoQueries.getUserContacts().toString();
        if (StringUtils.isNotBlank(param))
            getUserContacts = ContactDaoQueries.getUserContacts().toString()
                    + " And ( contacts.firstname like '" + param
                    + "%' or  contacts.lastname like '" + param + "%' or contacts.email like '"
                    + param + "%' )";
        if (StringUtils.isNotBlank(criteria))
            getUserContacts = ContactDaoQueries.getUserContacts().toString()
                    + "And ( contacts.firstname like '" + criteria
                    + "%' or  contacts.lastname like '" + criteria
                    + "%' or contacts.email like '" + criteria + "%'  ) ";
        if (limitFlag)
            getUserContacts = getUserContacts + " LIMIT " + offset;

        UserContactlist userContactlst = new UserContactlist();
        QueryParameters qp = new QueryParameters();
        qp.addParam(userid);
        List UserContactlist = QueryHelper.doQuery(abstractDao, "getUserContactsLst", getUserContacts, qp, new UserContactRowMapper());
        Logger.debug("UserContactList: " + UserContactlist);
        Logger.debug("CCCCC_ss_" + UserContactlist.size());
        userContactlst.setUserContactlist((ArrayList<UserContactModel>) UserContactlist);
        String getUserContactssize = ContactDaoQueries.getUsercontactsize().toString();
        if (StringUtils.isNotBlank(param))
            getUserContactssize = ContactDaoQueries.getUsercontactsize().toString()
                    + " And ( contacts.firstname like '" + param
                    + "%' or  contacts.lastname like '" + param
                    + "%' or contacts.email like '"
                    + param + "%'  ) ";
        if (StringUtils.isNotBlank(criteria))
            getUserContactssize = ContactDaoQueries.getUsercontactsize().toString()
                    + "And ( contacts.firstname like '" + criteria
                    + "%' or  contacts.lastname like '" + criteria
                    + "%' or contacts.email like '" + criteria + "%'  ) ";
        Logger.info("getUserContactsLst::002");
        int noofrecords = QueryHelper.doQueryForInt(abstractDao, "getUserContactsLst", getUserContactssize, qp);
        userContactlst.setNoOfRecords(noofrecords);
        Logger.debug("UserContactlist" + UserContactlist.size());
        return userContactlst;
    }
   
    @Override
    public UserContactlist editContactsList(long grpid, int offset, int noOfRecords, String param, String criteria, boolean limitFlag) {
        String getdContacts = ContactDaoQueries.getEditContactsList().toString();
        if (StringUtils.isNotBlank(param))
        	getdContacts = ContactDaoQueries.getEditContactsList().toString()
                    + " And ( contacts.firstname like '" + param
                    + "%' or  contacts.lastname like '" + param + "%' or contacts.email like '"
                    + param + "%' )";
        if (StringUtils.isNotBlank(criteria))
        	getdContacts = ContactDaoQueries.getUserContacts().toString()
                    + "And ( contacts.firstname like '" + criteria
                    + "%' or  contacts.lastname like '" + criteria
                    + "%' or contacts.email like '" + criteria + "%'  ) ";
        if (limitFlag)
        	getdContacts = getdContacts + " LIMIT " + offset;

        UserContactlist editContactlst = new UserContactlist();
        QueryParameters qp = new QueryParameters();
        qp.addParam(grpid);
        Logger.info("grpid::"+grpid);
	try{
        List UserContactlist = QueryHelper.doQuery(abstractDao, "editContactsList", getdContacts, qp, new UserContactRowMapper());
        editContactlst.setUserContactlist((ArrayList<UserContactModel>) UserContactlist);
        String getGroupContactsSize = ContactDaoQueries.getGroupContactsSize().toString();
        if (StringUtils.isNotBlank(param))
        	getGroupContactsSize = ContactDaoQueries.getGroupContactsSize().toString()
                    + " And ( contacts.firstname like '" + param
                    + "%' or  contacts.lastname like '" + param
                    + "%' or contacts.email like '"
                    + param + "%'  ) ";
        if (StringUtils.isNotBlank(criteria))
        	getGroupContactsSize = ContactDaoQueries.getGroupContactsSize().toString()
                    + "And ( contacts.firstname like '" + criteria
                    + "%' or  contacts.lastname like '" + criteria
                    + "%' or contacts.email like '" + criteria + "%'  ) ";
        int noofrecords = QueryHelper.doQueryForInt(abstractDao, "editContactsList", getGroupContactsSize, qp);
        editContactlst.setNoOfRecords(noofrecords);
        Logger.info("UserContactList: " + UserContactlist);
	return editContactlst;
    	}catch  (Exception e){
    		Logger.error("Exception:", e);
    	}
        return editContactlst;
    }
    
    

    @SuppressWarnings("unchecked")
    @Override
    public UserContactlist getUserContactsLst(long userid) {
        String getUserContacts = ContactDaoQueries.getUserContacts().toString();
        UserContactlist userContactlst = new UserContactlist();
        Logger.debug("getUserContacts: " + getUserContacts);
        QueryParameters qp = new QueryParameters();
        qp.addParam(userid);

        Logger.info("getUserContactsLst::003");
        List<UserContactModel> UserContactlist = QueryHelper.doQuery(abstractDao, "getUserContactsLst", getUserContacts, qp, new UserContactRowMapper());
        Logger.debug("CCCCC_ss_" + UserContactlist.size());
        userContactlst.setUserContactlist((ArrayList<UserContactModel>) UserContactlist);
        return userContactlst;
    }

    @Override
    public boolean hasGroupBeenPolled(Long groupId) {
        String hasGroupBeenPolledQuery = GroupDaoQueries.getHasGroupBeenPolledQuery().toString();
        Logger.debug("hasGroupBeenPolledQuery:: " + hasGroupBeenPolledQuery);

        QueryParameters params = new QueryParameters();
        params.addParam(groupId);
        int i = QueryHelper.doQueryForInt(abstractDao, "hasGroupBeenPolled", hasGroupBeenPolledQuery, params);
        if (i >= 1)
            return true;
        else
            return false;
    }

    @Override
    public List<Contact> shareInCyyte(Share share) {
        Logger.debug("Creating shared inCyyte group ");
        List<QueryParameters> parameters = new ArrayList<QueryParameters>();
        List<Contact> validSharedContacts = new ArrayList<Contact>();
        QueryParameters params = null;
        Hashtable shareContacts = share.getShareContacts();
        String insertSharedInCyyte = "INSERT INTO shared_incyyte (fk_questionid , fk_original_groupid , fk_contactid , poll_owner_userid , " +
                " poll_sharer_userid , created_date , created_by)  VALUES (?, ?, ?, ?, ?, ?, ?) ";
        for (Long contactId : share.getSelectedShareContactsList()) {
            //CHECK INCYYTE HAS BEEN SHARED
            if (!checkContactHasBeenPolled(contactId, share.getQuestionId())) {
                params = new QueryParameters();
                params.addParam(share.getQuestionId());
                params.addParam(share.getGroupId());
                params.addParam(contactId);
                params.addParam(share.getOwnerUserId());
                params.addParam(share.getSharerUserId());
                params.addParam(share.getCreatedDate());
                params.addParam(share.getCreatedBy());
                parameters.add(params);
                if (shareContacts != null) {
                    validSharedContacts.add((Contact) shareContacts.get(contactId));
                }
            }
        }
        if (!parameters.isEmpty()) {
            int count = 0;
            for (QueryParameters param : parameters) {
                try {
                    count += QueryHelper.doUpdate(abstractDao, "shareInCyyte", insertSharedInCyyte, param);
                    //Each transaction is treated as an independent and commit the same.
                } catch (Exception e) {
                    //This block will suppress individual failures and will trace in log for reference
                    Logger.warn("Sharing of a Poll Failed to user:" + param);
                }
            }
            Logger.debug("updates:: " + count);
        }
        Logger.debug("valid ContactList shared size: " + validSharedContacts.size());
        return validSharedContacts;
    }

    private boolean checkContactHasBeenPolled(long contactId, long questionId) {
        Logger.debug("checkContactHasBeenPolled: for shared Poll ");
        int num = -1;
        String query = UserDaoQueries.getAlreadyPolledQuery().toString();
        QueryParameters param = new QueryParameters();
        param.addParam(contactId);
        param.addParam(questionId);
        param.addParam(contactId);
        param.addParam(questionId);
        Logger.debug("Query: " + query);
        try {
            num = QueryHelper.doQueryForInt(abstractDao, "checkContactHasBeenPolled", query, param);
        } catch (Exception e) {
            Logger.error("checkContactHasBeenPolled: Failed for contactId " + contactId + " and QuestionId : " + questionId + " - ", e);
        }
        boolean poolled = num > 0 ? true : false;
        Logger.debug("checkContactHasBeenPolled: " + num + " - " + poolled);
        return poolled;
    }

    @Override
    public void incrementTotalIncyyted(long grpId, long questionId, int total) {
        Logger.debug("incrementSharedInCyytedQuery: grpId - > " + grpId);
        String incrementSharedInCyytedQuery = UserDaoQueries.getIncrementSharedInCyytedQuery().toString();
        Logger.debug("incrementSharedInCyytedQuery: " + incrementSharedInCyytedQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(total);
        params.addParam(grpId);
        params.addParam(questionId);
        try {
            QueryHelper.doUpdate(abstractDao, "incremenSharedIncyyted", incrementSharedInCyytedQuery, params);
        } catch (Exception e) {
            Logger.error("incrementSharedIncyyted: Failed ", e);
        }
    }

    @Override
    public List<Contact> getUserShareContacts(long quesId, long sharerId) {
        String userShareContacts = GroupDaoQueries.getUserShareContacts().toString();
        QueryParameters qp = new QueryParameters();
        qp.addParam(quesId);
        qp.addParam(quesId);
        return QueryHelper.doQuery(abstractDao, "getUserShareContacts", userShareContacts, qp, new ContactNewRowMapper());
    }

    @Override
    public void insertGrpSharedInCyyte(List<GroupSharedInCyyte> sharedInCyyte) throws Exception {
        String insertStmt = "INSERT INTO GRP_SHARED_INCYYTE (fk_member_id, fk_question_id, created_by, creation_date, last_updated_by, last_update_date) " +
                "VALUES (?, ?, ?, sysdate(), ?, sysdate())";
        List<QueryParameters> params = new ArrayList<QueryParameters>();
        for (GroupSharedInCyyte sharedRec : sharedInCyyte) {
            QueryParameters param = new QueryParameters();
            param.addParam(sharedRec.getFkMemberId());
            param.addParam(sharedRec.getFkQuestionId());
            param.addParam(sharedRec.getCreatedBy());
            param.addParam(sharedRec.getLastUpdatedBy());
            params.add(param);
        }
        Logger.debug("params::" + params);
        for (QueryParameters param : params) {
            try {
                QueryHelper.doUpdate(abstractDao, "insertGrpSharedInCyyte", insertStmt, param);
            } catch (DataIntegrityViolationException e) {
                Logger.debug("Record already exists hence ignored:" + param);
            }
        }
    }

    @Override
    public List<GroupContact> getGroupContactMembers(Long groupId) throws Exception {
        Logger.debug("getGroupContactMembers:Start");
        Logger.debug("getGroupContactMembers:groupId:" + groupId);
        String queryStmt = "SELECT gc.MEMBERID ,gc.FK_CONTACTID ,gc.FK_GROUPID ,gc.ACTIVE_IND ,gc.ROLE  " +
                " FROM GRP_CONTACTS gc, contacts c " +
                " WHERE gc.fk_contactid = c.contactid " +
                "   AND c.hidden = 'N' " +
                "   AND gc.fk_groupid = " + groupId;
        Logger.debug("getGroupContactMembers:queryStmt:" + queryStmt);
        return QueryHelper.doQuery(abstractDao, "getGroupContactMembers", queryStmt, null, new GroupContactRowMapper());
    }

    private String getProfileImgURL(String emailAddress) {
        Logger.debug("getProfileImgURL:EmailAddress:" + emailAddress);
        QueryParameters qp = new QueryParameters();
        qp.addParam(emailAddress);
        Logger.debug("getProfileImgURL:QP:" + qp);
        IncyyteProperties ip = null;
        try {
            ip = new IncyyteProperties(null);
        } catch (ConfigurationException e1) {
            Logger.error("Exception:", e1);
        }
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        String profileURL = icfg.getProperty(ConfigProperties.INCYYTE_DEFAULY_AVATAR_URL);
        profileURL = profileURL;

        Logger.debug("getProfileImgURL:Profile URL:" + profileURL);
        try {
            List<Map<String, String>> contactProfileValue = QueryHelper.doQueryForList(abstractDao, "Getprofileimgurl", ContactDaoQueries.getContactprofileimg().toString(), qp);
            Logger.debug("Profile Image values:" + contactProfileValue);
            Logger.debug("Values:" + contactProfileValue.size());
            if (contactProfileValue.size() > 0) {
                profileURL = FileManagementUtil.getProfileURL(contactProfileValue.get(0).get("UPLOAD_LOCATION"), contactProfileValue.get(0).get("CDN_FILE_NAME"));
            }
            Logger.debug("Profile URL:" + profileURL);
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return profileURL;
    }

    @Override
    public List<Long> insertBatchGrpContacts(List<GroupContact> groupContacts) throws Exception {
        JdbcTemplate template = abstractDao.getJdbcTemplate("insertBatchGrpContacts");
        return insertBatchGrpContacts(groupContacts, template);
    }
    @Override
    public Long getMemberId( long contactId, long grpId) throws Exception {                         
    	String query="SELECT  MEMBERID "+
                                " FROM GRP_CONTACTS  gc "+
    			                "WHERE  gc.fk_contactid =? " +
    			                " AND gc.fk_groupid =? ";
    	QueryParameters params = new QueryParameters();
    	 params.addParam(contactId);
         params.addParam(grpId);
         try {
        	 Long   memberId = (Long) QueryHelper.doQueryForObject(abstractDao, "getMemberId", query, params, Long.class);    		
        	 return memberId;
         } catch (EmptyResultDataAccessException e) {
             return null;
         } catch (Exception e) {
        	 Logger.error("Exception::", e);
        	 throw e;
         }
    }

    @Override
    public List<Long> insertBatchGrpContacts(List<GroupContact> groupContacts, JdbcTemplate template) throws Exception {
        String sql = "INSERT INTO GRP_CONTACTS (memberid, fk_contactid, fk_groupid, created_date, created_by, last_updated, updated_by, active_ind, role) " +
                " VALUES (?, ?, ?, sysdate(), ?, sysdate(), ?, 'A', ?)";
        List<Long> memberIds = new ArrayList<Long>();
        List<QueryParameters> params = new ArrayList<QueryParameters>();
        for (GroupContact contact : groupContacts) {
            Long memberId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("GRP_CONTACTS_PK", true);
            QueryParameters param = new QueryParameters();
            memberIds.add(memberId);
            param.addParam(memberId);
            param.addParam(contact.getContactId());
            param.addParam(contact.getGroupId());
            param.addParam(contact.getCreatedBy());
            param.addParam(contact.getCreatedBy());
            param.addParam(contact.getRole());
            params.add(param);
        }
        for (QueryParameters param : params) {
            try {
                QueryHelper.doUpdate(template, sql, param);
            } catch (DuplicateKeyException e) {
                Logger.warn("Record already exists hence ignored:" + param);
            } catch (Exception e) {
                Logger.error("Exception: ", e);
                throw e;
            }
        }
        return memberIds;
    }

    @Override
    public void deleteGrpContacts(Long groupId, List<GroupContact> deleteList, JdbcTemplate template) throws Exception {

        String deleteGroupContacts = " DELETE FROM GRP_CONTACTS " +
                " WHERE fk_contactid = ?  AND fk_groupid = ?";
        for (GroupContact deleteContact : deleteList) {
            QueryParameters deleteGrpContParams = new QueryParameters();
            deleteGrpContParams.addParam(deleteContact.getContactId());
            deleteGrpContParams.addParam(groupId);
            try {
                QueryHelper.doUpdate(template, deleteGroupContacts, deleteGrpContParams);
            } catch (SQLException se) {
                Logger.error("Exception", se);
                throw new Exception(se);
            }
        }
    }
}