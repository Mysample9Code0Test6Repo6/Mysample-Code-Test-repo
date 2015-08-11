package com.incyyte.app.dao.contacts;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.Dao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameterFactory;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.dao.login.RegisterDaoQueries;
import com.incyyte.app.dao.user.rowmapper.*;
import com.incyyte.app.domain.User;
import com.incyyte.app.manager.EmailManager;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.util.*;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;
import org.apache.commons.lang.StringUtils;
import org.brickred.socialauth.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ContactDaoImpl extends Dao implements ContactDao {

	@Autowired
    private AbstractDao abstractDao;

    @Autowired
    private EmailManager emailMgr;

    @Override
    public long addContact(UserContactModel user, long userid) throws Exception {
        String addContactQuery = ContactDaoQueries.getAddContact().toString();
        Logger.debug("addContactQuery: " + addContactQuery);
        long contactid = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("CONTACTS_PK", false);

        if (user.getInvitationid() == null)
            user.setInvitationid(getInvitationCode());

        user.setContactid(contactid);

        if (user.getStatus() == null) {
            String status = "NM";
            if (verifyContactalreadyMember(user.getEmail(), contactid, "email")) {
                status = "M";
            }
            user.setStatus(status);
        }

        if (user.getAccept_inv() == null) {
            user.setAccept_inv("N");
        }

        if (StringUtils.isBlank(user.getSent_invite()) || StringUtils.equals(user.getSent_invite(), "Y")) {
            if (null != user.getSn_id() && user.getSn_id().length() > 2) {
                user.setSent_invite("N");
            } else {
                user.setSent_invite("Y");
            }
        }
        try {
            QueryHelper.doUpdate(abstractDao, "addContact", addContactQuery, QueryParameterFactory.getaddContactParams(user, userid));
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return contactid;
    }

    public String getInvitationCode() {
        UIDCodeGenerator generator = new UIDCodeGenerator();
        generator.setDao(this);
        return generator.getInvitationCode();
    }

    @Override
    public int updateContact(String email, long userId) throws Exception {
        String query = "update contacts set active_ind = 'A', blocked = 'N', hidden = 'N' where email = ?  and fk_userid = ?";
        QueryParameters params = new QueryParameters();
        params.addParam(email);
        params.addParam(userId);
        QueryHelper.doUpdate(abstractDao, "updateContact", query, params);
        return 0;
    }

    @Override
    public int modifyContact(UserContactModel user, long userid) throws Exception {
        String deleteContactQuery = ContactDaoQueries.getRemoveContact().toString();
        QueryHelper.doUpdate(abstractDao, "modifyContact", deleteContactQuery, QueryParameterFactory.getupdateContactParams(user, userid));
        return 0;
    }

    @Override
    public int DeletContact(UserContactModel user, long userid) throws Exception {
        String DeletContactQuery = ContactDaoQueries.getRemoveContact().toString();
        Logger.debug("DeletContact: " + DeletContactQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        params.addParam(user.getContactid());
        params.addParam(userid);
        QueryHelper.doUpdate(abstractDao, "deleteContact", DeletContactQuery, params);
        return 0;
    }

    @Override
    public int BlockContact(UserContactModel user, long userid) throws Exception {
        String BlockContactQuery = ContactDaoQueries.getBlockContact().toString();
        Logger.debug("BlockContactQuery: " + BlockContactQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        params.addParam(user.getContactid());
        params.addParam(userid);
        return QueryHelper.doUpdate(abstractDao, "BlockContact", BlockContactQuery, params);
    }

    @Override
    public int UnBlockContact(UserContactModel user, long userid) throws Exception {
        String BlockContactQuery = ContactDaoQueries.getUnblockContact().toString();
        Logger.debug("unBlockContactQuery: " + BlockContactQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        params.addParam(user.getContactid());
        params.addParam(userid);
        QueryHelper.doUpdate(abstractDao, "UnBlockContact", BlockContactQuery, params);
        return 0;
    }

    @Override
    public UserContactlist getUserContacts(long userid, int offset, int noOfRecords, String param, String criteria) {
        String getUserContacts = ContactDaoQueries.getUserContacts().toString();
        if (noOfRecords > 0) {
            getUserContacts = getUserContacts + "limit " + offset + ", " + noOfRecords;
        }
        if (StringUtils.isNotBlank(param))
            getUserContacts = ContactDaoQueries.getUserContacts().toString()
                    + " And (   ifnull(contacts.firstname, users.firstname)  like '" + param
                    + "%' or  ifnull(contacts.lastname, users.lastname)  like '" + param
                    + "%' or contacts.email like '" + param + "%'  ) "
                    + " limit " + offset + ", " + noOfRecords;
        if (StringUtils.isNotBlank(criteria))
            getUserContacts = ContactDaoQueries.getUserContacts().toString()
                    + "And (  ifnull(contacts.firstname, users.firstname)  like '" + criteria
                    + "%' or  ifnull(contacts.lastname, users.lastname) like '" + criteria
                    + "%' or  users.username like '" + criteria
                    + "%' or contacts.email like '" + criteria + "%'  ) "
                    + " limit " + offset + ", " + noOfRecords;

        String getUserContactssize = ContactDaoQueries.getUsercontactsize().toString();
        if (StringUtils.isNotBlank(param))
            getUserContactssize = ContactDaoQueries.getUsercontactsize().toString()
                    + " And (firstname like '" + param
                    + "%' or lastname like '" + param
                    + "%' or email like '" + param + "%'  ) ";
        if (StringUtils.isNotBlank(criteria))
            getUserContactssize = ContactDaoQueries.getUsercontactsize().toString()
                    + "And (firstname like '" + criteria
                    + "%' or lastname like '" + criteria
                    + "%' or email like '" + criteria + "%'  ) ";
        List<UserContactModel> contactList = null;
        UserContactlist userContactlst = new UserContactlist();
        Logger.debug("getUserContacts: " + getUserContacts);
        QueryParameters qp = new QueryParameters();
        ContactDetailRowMapper rowMapper = new ContactDetailRowMapper();
        qp.addParam(userid);
        contactList = QueryHelper.doQuery(abstractDao, "getUserContacts", getUserContacts, qp, rowMapper);
        userContactlst.setUserContactlist((ArrayList<UserContactModel>) contactList);
        int noofrecords = QueryHelper.doQueryForInt(abstractDao, "getUserContacts", getUserContactssize, qp);
        userContactlst.setNoOfRecords(noofrecords);
        Logger.debug("UserContactlist" + contactList.size());
        return userContactlst;
    }
    
    
    @Override
    public UserContactlist getUserAndGrpContacts(long userid, long grpid, int offset, int noOfRecords, String param, String criteria) {
        String getUserContacts = ContactDaoQueries.getUseAndGrpContacts().toString();
        if (noOfRecords > 0) {
            getUserContacts = getUserContacts + "limit " + offset + ", " + noOfRecords;
        }
        if (StringUtils.isNotBlank(param))
            getUserContacts = ContactDaoQueries.getUseAndGrpContacts().toString()
                    + " And (   ifnull(contacts.firstname, users.firstname)  like '" + param
                    + "%' or  ifnull(contacts.lastname, users.lastname)  like '" + param
                    + "%' or contacts.email like '" + param + "%'  ) "
                    + " limit " + offset + ", " + noOfRecords;
        if (StringUtils.isNotBlank(criteria))
            getUserContacts = ContactDaoQueries.getUseAndGrpContacts().toString()
                    + "And (  ifnull(contacts.firstname, users.firstname)  like '" + criteria
                    + "%' or  ifnull(contacts.lastname, users.lastname) like '" + criteria
                    + "%' or  users.username like '" + criteria
                    + "%' or contacts.email like '" + criteria + "%'  ) "
                    + " limit " + offset + ", " + noOfRecords;

        String getUserContactssize = ContactDaoQueries.getUsercontactsize().toString();
        if (StringUtils.isNotBlank(param))
            getUserContactssize = ContactDaoQueries.getUsercontactsize().toString()
                    + " And (firstname like '" + param
                    + "%' or lastname like '" + param
                    + "%' or email like '" + param + "%'  ) ";
        if (StringUtils.isNotBlank(criteria))
            getUserContactssize = ContactDaoQueries.getUsercontactsize().toString()
                    + "And (firstname like '" + criteria
                    + "%' or lastname like '" + criteria
                    + "%' or email like '" + criteria + "%'  ) ";
        List<UserContactModel> contactList = null;
        UserContactlist userContactlst = new UserContactlist();
        Logger.debug("getUserContacts: " + getUserContacts);
        QueryParameters qp = new QueryParameters();
        ContactAndGrpDetailRowMapper rowMapper = new ContactAndGrpDetailRowMapper();
        qp.addParam(grpid);
        qp.addParam(userid);
        contactList = QueryHelper.doQuery(abstractDao, "getUserContacts", getUserContacts, qp, rowMapper);
        userContactlst.setUserContactlist((ArrayList<UserContactModel>) contactList);
        QueryParameters qp1 = new QueryParameters();
        qp1.addParam(userid);
        int noofrecords = QueryHelper.doQueryForInt(abstractDao, "getUserContacts", getUserContactssize, qp1);
        userContactlst.setNoOfRecords(noofrecords);
        Logger.debug("UserContactlist" + contactList.size());
        return userContactlst;
    }
    
    @Override
    public UserContactlist getUserContactsFromSearch(int offSet, int recordsPerPage, long userid, String criteria, String param) {
        String getUserContacts = ContactDaoQueries.getUserContacts().toString();
        
        if (recordsPerPage > 0) {
            getUserContacts = getUserContacts + "limit " + offSet + ", " + recordsPerPage;
        }
        if (StringUtils.isNotBlank(param))
            getUserContacts = ContactDaoQueries.getUserContacts().toString()
                    + " And (   ifnull(contacts.firstname, users.firstname)  like '" + param
                    + "%' or  ifnull(contacts.lastname, users.lastname)  like '" + param
                    + "%' or contacts.email like '" + param + "%'  ) "
                    + " limit " + offSet + ", " + recordsPerPage;
        
        if (StringUtils.isNotBlank(criteria))
            getUserContacts = ContactDaoQueries.getUserContacts().toString()
                    + "And (  ifnull(contacts.firstname, users.firstname)  like '" + criteria
                    + "%' or  ifnull(contacts.lastname, users.lastname) like '" + criteria
                    + "%' or  users.username like '" + criteria
                    + "%' or contacts.email like '" + criteria + "%'  ) "
                    + " limit " + offSet + ", " + recordsPerPage;


        String getUserContactssize = ContactDaoQueries.getUsercontactsize().toString();
        
        if (StringUtils.isNotBlank(param))
            getUserContactssize = ContactDaoQueries.getUsercontactsize().toString()
                    + " And (firstname like '" + param
                    + "%' or lastname like '" + param
                    + "%' or email like '" + param + "%'  ) ";
        if (StringUtils.isNotBlank(criteria))
            getUserContactssize = ContactDaoQueries.getUsercontactsize().toString()
                    + "And (firstname like '" + criteria
                    + "%' or lastname like '" + criteria
                    + "%' or email like '" + criteria + "%'  ) ";
       
        List<UserContactModel> contactList = null;
        UserContactlist userContactlst = new UserContactlist();
        Logger.debug("getUserContacts: " + getUserContacts);
        QueryParameters qp = new QueryParameters();
        ContactDetailRowMapper rowMapper = new ContactDetailRowMapper();
        qp.addParam(userid);
        contactList = QueryHelper.doQuery(abstractDao, "getUserContacts", getUserContacts, qp, rowMapper);
        userContactlst.setUserContactlist((ArrayList<UserContactModel>) contactList);
        int noofrecords = QueryHelper.doQueryForInt(abstractDao, "getUserContacts", getUserContactssize, qp);
        userContactlst.setNoOfRecords(noofrecords);
        Logger.debug("UserContactlist" + contactList.size());
        return userContactlst;
    }

    @Override
    public void makeHiddenContactsActive(List<UserContactModel> hiddenContacts) {
        Logger.debug("makeHiddenContactsActive: Start");
        String updateContact = "update contacts set hidden = 'N' where contactid = ? ";
        for (UserContactModel contact : hiddenContacts) {
            QueryParameters params = new QueryParameters();
            params.addParam(contact.getContactid());
            try {
                QueryHelper.doUpdate(abstractDao, "makeHiddenContactsActive", updateContact, params);
            } catch (Exception e) {
                Logger.error("Unable to update Hidden contact:", e);
            }
        }
    }

    @Override
    public String getProfileImgURL(String emailAddress) {
        QueryParameters qp = new QueryParameters();
        qp.addParam(emailAddress);
        IncyyteProperties ip = null;
        try {
            ip = new IncyyteProperties(null);
        } catch (ConfigurationException e1) {
            Logger.error("Exception:", e1);
        }
        ConfigManager icfg = ConfigManager.getInstance();
        icfg.setIncyyteProperties(ip);
        String profileURL = icfg.getProperty(ConfigProperties.INCYYTE_DEFAULY_AVATAR_URL);
        String contextPath = icfg.getProperty(ConfigProperties.CONTEXT_PATH);
        profileURL = contextPath + profileURL;
        try {
            List<Map<String, String>> contactProfileValue = QueryHelper.doQueryForList(abstractDao, "Getprofileimgurl", ContactDaoQueries.getContactprofileimg().toString(), qp);
            if (contactProfileValue.size() > 0) {
                profileURL = FileManagementUtil.getProfileURL(contactProfileValue.get(0).get("UPLOAD_LOCATION"), contactProfileValue.get(0).get("CDN_FILE_NAME"));
            }
        } catch (Exception e) {
            Logger.error("Exception:" + e);
        }
        return profileURL;
    }

    @Override
    public UserContactModel getContact(long userid) {
        String getUserContacts = ContactDaoQueries.getContactsdtls().toString();
        List<UserContactModel> UserContact = null;
        Logger.debug("getContact: " + getUserContacts + " " + userid);
        QueryParameters qp = new QueryParameters();
        qp.addParam(userid);
        Logger.debug("UserContact::in getContact" + userid);
        UserContact = QueryHelper.doQuery(abstractDao, "getContact", getUserContacts, qp, new UserContactRowMapper());
        return (UserContactModel) UserContact.get(0);
    }

    @Override
    public UserContactModel getContactbyemail(String emailid) {
    	Logger.debug("emailid: " + emailid);
        String getUserContacts = ContactDaoQueries.getContactsdtlsbyemail().toString();
        Logger.debug("getContact: " + getUserContacts);
        QueryParameters qp = new QueryParameters();
        qp.addParam(emailid);
        List<UserContactModel> UserContact = QueryHelper.doQuery(abstractDao, "getContactbyemail", getUserContacts, qp, new UserContactRowMapper());
        return (UserContactModel) UserContact.get(0);
    }

    @Override
    public UserContactModel SendInvite(UserContactModel usercontact, long userid) throws Exception {
        String SendInviteQuery = ContactDaoQueries.getInviteContacts().toString();
        //get InvitationId for the contacts.
        usercontact = getContact(usercontact.getContactid());
        Logger.debug("usercontact::" + usercontact);
        String invitationCode = null;
        if (StringUtils.isBlank(usercontact.getInvitationid())) {
            UIDCodeGenerator generator = new UIDCodeGenerator();
            generator.setDao(this);
            invitationCode = generator.getInvitationCode();
            Logger.debug("for who dont have invitation Id::invitationid::" + invitationCode);
        } else {
            invitationCode = usercontact.getInvitationid();
            Logger.debug("invitationid::" + invitationCode);
        }
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        params.addParam(invitationCode);
        params.addParam("N");// accepted Invite
        params.addParam(usercontact.getContactid());
        params.addParam(userid);
        QueryHelper.doUpdate(abstractDao, "SendInvite", SendInviteQuery, params);
        usercontact = getContact(usercontact.getContactid());

        //set contact membership
        if (verifyContactalreadyMember(usercontact.getEmail(), usercontact.getContactid(), "email")) {
            activateUserMembership(usercontact.getEmail());
        }
        return usercontact;
    }

    @Override
    public boolean verifyUniqueInvitationCode(String invCode) {
        String invCodeQuery = ContactDaoQueries.getUniqueInvitationCodeQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(invCode);
        int i = QueryHelper.doQueryForInt(abstractDao, "verifyUniqueInvitationCode", invCodeQuery, params);
        if (i == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean verifyContactalreadyMember(String cemail, long contactid, String mode) {
        String emailquery = ContactDaoQueries.getContactemail().toString();
        String emailexist = ContactDaoQueries.getEmailexists().toString();
        String email = "";
        if (!mode.equals("email")) {
            QueryParameters params = new QueryParameters();
            params.addParam(contactid);
            email = (String) QueryHelper.doQueryForObject(abstractDao, "verifyContactalreadyMember", emailquery, params, String.class);
        } else {
            email = cemail;
        }
        QueryParameters params2 = new QueryParameters();
        params2.addParam(email);
        int i = QueryHelper.doQueryForInt(abstractDao, "verifyContactalreadyMember", emailexist, params2);
        if (i == 1)
            return true;
        else
            return false;
    }

    public void activateUserMembership(String email) {
        String activateUserByEmailQuery = RegisterDaoQueries.getActivateMemberByEmailQuery().toString();
        try {
            //activate Account
            Logger.debug("getActivateMemberByEmailQuery: " + activateUserByEmailQuery);
            QueryHelper.doUpdate(abstractDao, "activateUserMembership", activateUserByEmailQuery, QueryParameterFactory.getActivationParams(email));
        } catch (Exception e) {
            Logger.error("activateUserByEmail: Failed:", e);
        }
    }

    @Override
    public long contactExistForUser(long userId, String contactEmail) {
        String contactExistForUserQuery = ContactDaoQueries.getContactExistForUserQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(contactEmail);
        params.addParam(userId);
        Logger.error("contactEmail and userid" + contactEmail + "  " + userId);
        return (Long) QueryHelper.doQueryForObject(abstractDao, "contactExistForUser", contactExistForUserQuery, params, Long.class);
    }

    @Override
    public UserContactlist SearchContact(UserContactModel usercontModel,
                                         long userid, String param, int offset, int noOfRecords,
                                         String criteria) {
        String SearchContact = ContactDaoQueries.getUserContacts().toString();
        String SearchContactsize = ContactDaoQueries.getSearchContactsize().toString();
        UserContactlist userContactlst = new UserContactlist();
        boolean flag = false;
        Logger.debug("SearchContact: " + SearchContact);
        QueryParameters qp = new QueryParameters();
        ContactDetailRowMapper rowMapper = new ContactDetailRowMapper();
        qp.addParam(userid);

        if (StringUtils.isNotBlank(usercontModel.getFirstname())) {
            SearchContact = SearchContact + "AND" + " contacts.firstname LIKE '%"
                    + usercontModel.getFirstname().trim() + "%'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.firstname LIKE '%"
                    + usercontModel.getFirstname().trim() + "%'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(criteria)) {
            SearchContact = SearchContact + "AND" + " contacts.firstname = '"
                    + usercontModel.getFirstname().trim() + "'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.firstname = '"
                    + usercontModel.getFirstname().trim() + "'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(usercontModel.getLastname())) {
            SearchContact = SearchContact + "AND" + " contacts.lastname LIKE '%"
                    + usercontModel.getLastname().trim() + "%'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.lastname LIKE '%"
                    + usercontModel.getLastname().trim() + "%'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(usercontModel.getEmail())) {
            SearchContact = SearchContact + "AND" + " contacts.email LIKE '%"
                    + usercontModel.getEmail().trim() + "%'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.email LIKE '%" + usercontModel.getEmail().trim()
                    + "%'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(usercontModel.getNickname())) {
            SearchContact = SearchContact + "AND" + " contacts.nickname LIKE '%"
                    + usercontModel.getNickname().trim() + "%'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.nickname LIKE '%"
                    + usercontModel.getNickname().trim() + "%'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(usercontModel.getCountry())) {
            SearchContact = SearchContact + "AND" + " contacts.country LIKE '%"
                    + usercontModel.getCountry().trim() + "%'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.country LIKE '%"
                    + usercontModel.getCountry().trim() + "%'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(usercontModel.getCity())) {
            SearchContact = SearchContact + "AND" + " contacts.city LIKE '%"
                    + usercontModel.getCity().trim() + "%'  ";
            SearchContactsize = SearchContactsize + "AND" + " contacts.city LIKE '%"
                    + usercontModel.getCity().trim() + "%'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(usercontModel.getPostalcode())) {
            SearchContact = SearchContact + "AND" + " contacts.postcode LIKE '%"
                    + usercontModel.getPostalcode().trim() + "%'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.postcode LIKE '%"
                    + usercontModel.getPostalcode().trim() + "%'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(usercontModel.getMobile())) {
            SearchContact = SearchContact + "AND" + " contacts.mobile LIKE '%"
                    + usercontModel.getMobile().trim() + "%'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.mobile LIKE '%" + usercontModel.getMobile().trim()
                    + "%'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(usercontModel.getGender())) {
            SearchContact = SearchContact + "AND" + " contacts.gender LIKE '%"
                    + usercontModel.getGender().trim() + "%'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.gender LIKE '%" + usercontModel.getGender().trim()
                    + "%'  ";
            flag = true;
        }
        if (StringUtils.isNotBlank(param)) {
            SearchContact = SearchContact + "AND" + " contacts.firstname LIKE '"
                    + param + "%'  ";
            SearchContactsize = SearchContactsize + "AND"
                    + " contacts.firstname LIKE '" + param + "%'  ";
            flag = true;
        }
        String keywords = "";
        if (StringUtils.isNotBlank(usercontModel.getKeywords())) {
            keywords = usercontModel.getKeywords();
            if (flag) {
                SearchContact = SearchContact + " OR ("
                        + " contacts.firstname LIKE '" + keywords + "%'  "
                        + " OR  contacts.city LIKE '" + keywords + "%'  "
                        + " OR  contacts.postcode LIKE '" + keywords + "%'  "
                        + " OR contacts.gender LIKE '" + keywords + "%'  "
                        + " OR  contacts.nickname LIKE '" + keywords + "%' "
                        + " OR contacts.email LIKE '" + keywords + "%'  "
                        + " OR contacts.lastname LIKE '" + keywords + "%'     )";
                SearchContactsize = SearchContactsize + " OR ("
                        + " contacts.firstname LIKE '" + keywords + "%'  "
                        + " OR contacts.city LIKE '" + keywords + "%'  "
                        + " OR contacts.postcode LIKE '" + keywords + "%'  "
                        + " OR contacts.gender LIKE '" + keywords + "%'  "
                        + " OR contacts.nickname LIKE '" + keywords + "%' "
                        + " OR contacts.email LIKE '" + keywords + "%'  "
                        + " OR contacts.lastname LIKE '" + keywords + "%'     )";
            } else {
                SearchContact = SearchContact + "AND ("
                        + " contacts.firstname LIKE '" + keywords + "%'  "
                        + " OR  contacts.city LIKE '" + keywords + "%'  "
                        + " OR  contacts.postcode LIKE '" + keywords + "%'  "
                        + " OR contacts.gender LIKE '" + keywords + "%'  "
                        + " OR  contacts.nickname LIKE '" + keywords + "%' "
                        + " OR contacts.email LIKE '" + keywords + "%'  "
                        + " OR contacts.lastname LIKE '" + keywords + "%'     )";
                SearchContactsize = SearchContactsize + "AND ("
                        + " contacts.firstname LIKE '" + keywords + "%'  "
                        + " OR contacts.city LIKE '" + keywords + "%'  "
                        + " OR contacts.postcode LIKE '" + keywords + "%'  "
                        + " OR contacts.gender LIKE '" + keywords + "%'  "
                        + " OR contacts.nickname LIKE '" + keywords + "%' "
                        + " OR contacts.email LIKE '" + keywords + "%'  "
                        + " OR contacts.lastname LIKE '" + keywords + "%'     )";
            }
        }
        SearchContact = SearchContact + "limit " + offset + ", " + noOfRecords;
        Logger.debug("SearchContact query formed is" + SearchContact);
        List<UserContactModel> UserContactlist = QueryHelper.doQuery(abstractDao, "SearchContact", SearchContact, qp, rowMapper);
        userContactlst.setUserContactlist((ArrayList<UserContactModel>) UserContactlist);
        Logger.debug("searchlist" + UserContactlist.size());
        int noofrecords = QueryHelper.doQueryForInt(abstractDao, "SearchContact", SearchContactsize, qp);
        Logger.debug("searchlist  noofrecords  " + noofrecords);
        userContactlst.setNoOfRecords(noofrecords);
        return userContactlst;
    }

    @Override
    public UserContactModel getContactByInv(String invitationId) {
        String getUserContacts = ContactDaoQueries.getContactsdtlsByInv().toString();
        QueryParameters qp = new QueryParameters();
        UserContactInvRowMapper rowMapper = new UserContactInvRowMapper();
        qp.addParam(invitationId);
        List<UserContactModel> UserContact = QueryHelper.doQuery(abstractDao, "getContactByInv", getUserContacts, qp, rowMapper);
        return UserContact.get(0);
    }

    @Override
    public void acceptInvitation(String invitationId) throws Exception {
        String acceptInviteQuery = ContactDaoQueries.getAcceptInitationQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(invitationId);
        QueryHelper.doUpdate(abstractDao, "acceptInvitation", acceptInviteQuery, params);
    }

    @Override
    public User getContactProfile(String email) {
        String getUserProfile = ContactDaoQueries.getUserProfileQuery().toString();
        Logger.debug("getProfile: " + getUserProfile);
        QueryParameters qp = new QueryParameters();
        qp.addParam(email);
        List<User> user = QueryHelper.doQuery(abstractDao, "getContactProfile", getUserProfile, qp, new UserRegRowMapper());
        return user.get(0);
    }

    @Override
    public List<String> getMemberListByName(long id) {
        String memberbynamequery = ContactDaoQueries.getMembersByName().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(id);

        List<String> contacts = QueryHelper.doQuery(abstractDao, "getMemberListByName", memberbynamequery, params,
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int i)
                            throws SQLException {
                        return resultSet.getString(1);
                    }
                });
        return contacts;
    }

    @Override
    public List<String> getInvitedContactsList(long id) {
        String getInvitedContactsListQuery = ContactDaoQueries.getInvitedContacts().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(new Long(id));
        return QueryHelper.doQueryForList(abstractDao, "getInvitedContactsList", getInvitedContactsListQuery, params);
    }

    @Override
    public int DeleteMultiple(UserContactModel usercModel, long id) throws Exception {
        String deletequery = ContactDaoQueries.getDeleteMultiple().toString();
        StringBuilder ids = new StringBuilder();
        StringTokenizer st = new StringTokenizer(usercModel.getChecked(), ",");
        while (st.hasMoreTokens()) {
            if (ids.length() == 0)
                ids = ids.append(st.nextToken().replace("'", ""));
            else
                ids = ids.append(",").append(st.nextToken().replace("'", ""));
        }
        deletequery = deletequery + "( " + ids + "  )";
        Logger.debug("DeleteMultiple--Query -- : " + deletequery);
        QueryParameters params = new QueryParameters();
        params.addParam(id);
        params.addParam(id);
        QueryHelper.doUpdate(abstractDao, "DeleteMultiple", deletequery, params);
        return 0;
    }

    @Override
    public int BlockMultiple(UserContactModel usercModel, long id) throws Exception {
        String blockmultiple = ContactDaoQueries.getBlockMultiple().toString();
        StringBuilder ids = new StringBuilder();
        StringTokenizer st = new StringTokenizer(usercModel.getChecked(), ",");
        while (st.hasMoreTokens()) {
            if (ids.length() == 0)
                ids = ids.append(st.nextToken().replace("'", ""));
            else
                ids = ids.append(",").append(st.nextToken().replace("'", ""));
        }
        blockmultiple = blockmultiple + "( " + ids + "  )";
        Logger.debug("DeleteMultiple--Query -- : " + blockmultiple);
        QueryParameters params = new QueryParameters();
        params.addParam(id);
        params.addParam(id);
        QueryHelper.doUpdate(abstractDao, "BlockMultiple", blockmultiple, params);
        return 0;
    }

    @Override
    public int InviteMultiple(long id, UserContactModel usercModel) throws Exception {
        String invmultiple = ContactDaoQueries.getInviteMultiple().toString();
        StringBuilder ids = new StringBuilder();
        StringBuilder emailList = new StringBuilder();
        StringTokenizer st = new StringTokenizer(usercModel.getChecked(), ",");
        while (st.hasMoreTokens()) {
            try {
                String email = st.nextToken();
                if (ids.length() == 0) {
                    ids = ids.append(email);
                    email = email.replace("'", "");
                    
                } else {
                    ids = ids.append(",").append(email);
                    email = email.replace("'", "");                   
                }
                
                if(StringUtils.isNumeric(email)){
                	long contactId =  Long.parseLong(email);
                	usercModel = getContact(contactId);
                } else {
                	usercModel = getContactbyemail(email);
                }

                if (usercModel != null && usercModel.getAccept_inv().equals("N")) {
                    sendInviteEmail(usercModel);

                    if (emailList.length() == 0) {
                        emailList = emailList.append("'" + usercModel.getEmail() + "'");
                    } else {
                        emailList = emailList.append(",").append("'" + usercModel.getEmail() + "'");
                    }
                }

            } catch (Exception e) {
                Logger.error("email is not sent to contact:", e);
            }
        }

        if (emailList != null && emailList.length() > 0) {
            invmultiple = invmultiple + "( " + emailList + "  )";
            Logger.debug("InviteMultiple query" + invmultiple);
            QueryParameters params = new QueryParameters();
            params.addParam(id);
            params.addParam(id);
            QueryHelper.doUpdate(abstractDao, "InviteMultiple", invmultiple, params);
        }
        return 0;
    }

    private void sendInviteEmail(UserContactModel cuser) {
        emailMgr.setCuser(cuser);
        emailMgr.setRunFlag(Constants.SEND_INVITE_EMAIL);
        new Thread(emailMgr).start();
    }

    @Override
    public List<Contact> getuserContactListEmailids(long id) {
        String getUserContacts = ContactDaoQueries.getUserContactsforsn().toString();
        QueryParameters qp = new QueryParameters();
        qp.addParam(id);
        return QueryHelper.doQuery(abstractDao, "getuserContactListEmailids", getUserContacts, qp, new SnContactRowMapper());
    }

    @Override
    public int getContactExist(UserContactModel cm, long inviterId) {
        String getUserContacts = ContactDaoQueries.getContactExist().toString();
        QueryParameters qp = new QueryParameters();
        qp.addParam(inviterId);
        qp.addParam(cm.getEmail());
        return QueryHelper.doQueryForInt(abstractDao, "getContactExist", getUserContacts, qp);
    }

    @Override
    public void updateContact(UserContactModel cm, long inviterId) throws Exception {
        String updateUserContacts = ContactDaoQueries.updateContact().toString();
        QueryParameters qp = new QueryParameters();
        qp.addParam(inviterId);
        qp.addParam(cm.getEmail());
        QueryHelper.doUpdate(abstractDao, "updateContact", updateUserContacts, qp);
    }

    @Override
    public void linkUserContacts(String userEmail) throws Exception {
        //1.locate where this user is a contact
        //  retrieve all userids (ContactModel) from contact table where userEmail = contact email
        //2.Add these userids as this user's contacts
        //  iterate through userids
        //		if userid does not already exist as contact
        //			add userid as contact
        //			(settings ==>)
        //			Sent_invite  (if SI1=Y & AI1=Y ==> SI2=Y)
        //			Sent_invite  (if SI1=N         ==> SI2=N)
        //			Status = M
        //			Accept_invite  (if SI2=Y  ==> AI2=Y)
        //			Accept_invite  (if SI2=N  ==> AI2=N)

    }

    @Override
    public void linkUserContact(String userEmail) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public int getUserContactCount(long userid) {
        Logger.debug("inside contacts count::");
        String userContactsCountQuery = ContactDaoQueries.getUserContactsCount().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        int count = QueryHelper.doQueryForInt(abstractDao, "getUserContactCount", userContactsCountQuery, params);
        Logger.debug("contact count:ContactDaoImpl::" + count);
        return count;
    }

    @Override
    public int getnoOfRecords(Long userid) {
        Logger.debug("inside no of records ::");
        String noofrecords = ContactDaoQueries.getnoOfRecords().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        int count = QueryHelper.doQueryForInt(abstractDao, "getUserContactCount", noofrecords, params);
        Logger.debug("contact count:ContactDaoImpl::" + count);
        return count;
    }

    @Override
    public UserContactModel contactOfUser(String email, Long userId) throws Exception {
        StringBuilder contactInfo = new StringBuilder();
        contactInfo.append("SELECT c.*, u.username, u.upload_location, u.cdn_file_name  ");
        contactInfo.append("  FROM contacts c ");
        contactInfo.append("  JOIN users u on c.fk_userid = u.userid ");
        contactInfo.append(" WHERE c.email = ? ");
        contactInfo.append("   AND c.fk_userid = ? ");

        try {
            QueryParameters params = new QueryParameters();
            params.addParam(email);
            params.addParam(userId);
            UserContactModel model = (UserContactModel) QueryHelper.doQueryForObject(abstractDao, "contactOfUser",
                    contactInfo.toString(), params, new UserContactRowMapper());
            return model;

        } catch (final EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public UserContactModel personLinkedToUser(String personUsername, String UserEmail) {
        String personLinkedToUserQuery = " select b.*, a.username from users a, contacts b " +
                " where a.userid = b.fk_userid " +
                " and a.email = ? " + // user email address
                " and b.email =  " +
                " (select email from users where (username = ? or email = ?) ) ";
        Logger.debug("personLinkedToUserQuery:: " + personLinkedToUserQuery);

        QueryParameters params = new QueryParameters();
        params.addParam(UserEmail);
        params.addParam(personUsername);
        params.addParam(personUsername);
        UserContactModel model = (UserContactModel) QueryHelper.doQueryForObject(abstractDao, "verifyPersonLinkedToUser", personLinkedToUserQuery, params, new UserContactRowMapper());
        return model;
    }

    @Override
    public boolean verifyPersonLinkedToUser(String personUsername, String UserEmail) {
        String personLinkedToUserQuery = " select COUNT(contactid) from users a, contacts b " +
                " where a.userid = b.fk_userid " +
                " and a.email = ? " + // user email address
                " and b.active_ind <> 'D' " +
                " and b.email =  " +
                " (select email from users where (username = ? or email = ?) ) ";
        Logger.debug("personLinkedToUserQuery:: " + personLinkedToUserQuery);

        QueryParameters params = new QueryParameters();
        params.addParam(UserEmail);
        params.addParam(personUsername);
        params.addParam(personUsername);
        int i = QueryHelper.doQueryForInt(abstractDao, "verifyPersonLinkedToUser", personLinkedToUserQuery, params);
        if (i == 1)
            return true;
        else
            return false;
    }

    @Override
    public long verifyPersonLinkedToUserButDeleted(String personUsername, String UserEmail) {
        String personLinkedToUserButDeletedQuery = " select contactid from users a, contacts b " +
                " where a.userid = b.fk_userid " +
                " and a.email = ? " + // user email address
                " and b.active_ind = 'D' " +
                " and b.email =  " +
                " (select email from users where (username = ? or email = ?)) "; //person username
        Logger.debug("personLinkedToUserButDeletedQuery:: " + personLinkedToUserButDeletedQuery);

        QueryParameters params = new QueryParameters();
        params.addParam(UserEmail);
        params.addParam(personUsername);
        params.addParam(personUsername);
        long i = 0;
        try {
            i = QueryHelper.doQueryForInt(abstractDao, "verifyPersonLinkedToUserButDeleted", personLinkedToUserButDeletedQuery, params);
        } catch (Exception e) {
            Logger.error("ERROR: there is no contacts found:");
        }
        return i;
    }

    @Override
    public void reactivateDeletedContact(long contactID) {
        String activateDeletedContact = ContactDaoQueries.getReactivateDeletedContact().toString();
        QueryParameters qp = new QueryParameters();
        qp.addParam(contactID);
        try {
            QueryHelper.doUpdate(abstractDao, "reactivateDeletedContact", activateDeletedContact, qp);
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }

    }
}