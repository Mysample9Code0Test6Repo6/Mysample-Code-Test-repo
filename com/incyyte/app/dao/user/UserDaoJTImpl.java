package com.incyyte.app.dao.user;

import com.incyyte.app.dao.answers.AnswerDaoQueries;
import com.incyyte.app.dao.contacts.ContactDao;
import com.incyyte.app.dao.contacts.ContactDaoQueries;
import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.Dao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameterFactory;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.dao.groups.GroupDao;
import com.incyyte.app.dao.groups.GroupDaoQueries;
import com.incyyte.app.dao.user.rowmapper.*;
import com.incyyte.app.domain.*;
import com.incyyte.app.manager.QuickStartManager;
import com.incyyte.app.manager.scheduler.ScheduleClosure;
import com.incyyte.app.service.Exceptions.*;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.Utility;
import com.incyyte.app.util.GroupUtil;
import com.incyyte.app.util.InCyyteUtil;
import com.incyyte.app.web.model.CommentsModel;
import com.incyyte.app.web.model.PaymentModel;
import com.incyyte.app.web.model.UserContactModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDaoJTImpl extends Dao implements UserDao {
    AbstractDao abstractDao;
    private final int SUCCESS = 0;
    private final int FAILED = 1;
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    Calendar cal = Calendar.getInstance();

    @Autowired
    GroupDao groupDao;

    @Autowired
    ContactDao contactDao;

    @Autowired
    private QuickStartManager quickStartManager;

    @Override
    public int addUser(User user) throws CreateUserException {
        String regUserQuery = UserDaoQueries.getRegisterUserQuery().toString();
        long userId = 0;
        try {
            //get ID
            userId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("USERS_PK", false);
            user.setId(new Long(userId));
            Logger.debug("regUserQuery: " + regUserQuery);
            QueryHelper.doUpdate(abstractDao, "addUser", regUserQuery, QueryParameterFactory.getUserParams(user));
            return SUCCESS;

        } catch (Exception e) {
            Logger.error("addUser: Failed:", e);
            return FAILED;
        }
    }

    @Override
    public void insertBatchAnswers(final long questionId, final List<Answer> answers, final JdbcTemplate template) {
        String sql = AnswerDaoQueries.storeAnswerInfo();
        template.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Answer answer = answers.get(i);
                long anwId = -1;
                try {
                    anwId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("ANSWERS_PK", true);
                } catch (AccessException e) {
                    Logger.error("insertBatchAnswers: get answer PK.");
                }
                ps.setLong(1, anwId);
                ps.setLong(2, questionId);
                ps.setString(3, answer.getAnswerOption());
                ps.setString(4, answer.getUploadType());
                ps.setString(5, answer.getUploadName());
                ps.setString(6, answer.getUploadExt());
                ps.setString(7, answer.getUploadCDN_url());
                ps.setString(8, answer.getCdnFileName());
                ps.setString(9, answer.getYoutubeURLAnswer());
                ps.setString(10, answer.getUrlLink());
            }

            public int getBatchSize() {
                return answers.size();
            }
        });
    }

    @Override
    public Long[] insertBatchContacts(final long userId, final List<Contact> contacts, JdbcTemplate template) {
        final Long[] ids = new Long[contacts.size()];
        List<Long> existingIds = new ArrayList<Long>();
        //filter contacts, remove existing user contacts from list
        List<Contact> filteredContacts = new ArrayList<Contact>();
        List<UserContactModel> hiddenContacts = new ArrayList<UserContactModel>();
        for (Contact c : contacts) {
            UserContactModel contact = contactDao.getContactbyemail(c.getEmail());
            Logger.debug(" Contact:" + contact);
            if (contact == null) {
                filteredContacts.add(c);
            } else {
                existingIds.add(contact.getContactid());
                if (StringUtils.equals(contact.getHidden(), "Y")) {
                    hiddenContacts.add(contact);
                }
            }
        }
        final List<Contact> insertNewContacts = filteredContacts;
        final String sql = UserDaoQueries.getInsertNewContactQuery().toString();
        template.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Contact contact = insertNewContacts.get(i);
                long cntId = -1;
                try {
                    cntId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("CONTACTS_PK", true);
                } catch (Exception e) {
                    Logger.error("Exception:", e);
                }
                ps.setLong(1, cntId);
                ps.setLong(2, userId);
                ps.setString(3, contact.getNickname());
                ps.setString(4, contact.getFirstName());
                ps.setString(5, contact.getLastName());
                ps.setString(6, contact.getEmail());
                ps.setString(7, contact.getMobile());
                ps.setLong(8, userId);
                ps.setLong(9, userId);
                ps.setString(10, contact.getSent_invite());
                ps.setString(11, contact.getNote());

                if (contact.getInvitationid() != null) {
                    ps.setString(12, contact.getInvitationid());
                } else {
                    String invitationCode = contactDao.getInvitationCode();
                    ps.setString(12, invitationCode);
                }
                ps.setString(13, contact.getAccept_inv());
                ps.setLong(14, cntId);
                ps.setString(15, "DIRECT");
                ps.setString(16, verifyContactalreadyMember(contact.getEmail()) ? "M" : "NM");

                ids[i] = cntId;
            }

            public int getBatchSize() {
                return insertNewContacts.size();
            }
        });
        int maxFileterdItem = insertNewContacts.size();
        //Add the remaining existing IDs
        for (Long extID : existingIds) {
            ids[maxFileterdItem] = extID;
            maxFileterdItem++;
        }
        //Convert hidden contacts to actual contacts
        Logger.debug("hiddenContacts:" + hiddenContacts);
        contactDao.makeHiddenContactsActive(hiddenContacts);
        return ids;
    }

    public boolean verifyContactalreadyMember(String cemail) {
        String emailexist = ContactDaoQueries.getEmailexists().toString();
        String email = cemail;

        QueryParameters params2 = new QueryParameters();
        params2.addParam(email);
        int i = QueryHelper.doQueryForInt(abstractDao, "verifyContactalreadyMember", emailexist, params2);
        if (i == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean usernameExists(String username) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean passwordExists(String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public UserAddress findUserAddress(String cyyteHomeEmail) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long addUserAddress(UserAddress userAddr) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
      * (non-Javadoc)
      * 	Insert Question ==> QID
      *	Batch Insert Answers (include QID)
      *	Insert Group ==>GID
      *	Loop Insert Contacts (include UID) ==> CID
      *	Batch Insert Grp_Contacts (Include GID,CID)
      *	Insert InCyyte (include GID,QID) ==> IID
      *
      * @see com.incyyte.app.dao.user.UserDao#addInCyyte(com.incyyte.app.domain.InCyyte)
      */
    @Override
    public Long addInCyyte(InCyyte incyyte) {
        Logger.debug("addInCyyte: Create a New InCyyte:" + incyyte);
        JdbcTemplate template = null;
        //get Queries
        String questionQuery = UserDaoQueries.getInsertQuestionQuery().toString();
        Logger.debug("addInCyyte: Create a New InCyyte22." + questionQuery);
        String groupQuery = UserDaoQueries.getInsertGroupQuery().toString();
        Logger.debug("addInCyyte: Create a New InCyyte33. " + groupQuery);
        String inCyyteQuery = UserDaoQueries.getInsertInCyyteQuery().toString();
        Logger.debug("addInCyyte: Create a New InCyyte44. " + inCyyteQuery);
        //get IDs
        long qId = 0;
        long grpId = 0;
        Date closeDate = null;
        try {
            //BEGIN TXN
            abstractDao.getTxnHelper().beginTxn();

            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: Transaction begins ");
            if (incyyte.getClosureDate() != null) {
                Logger.debug("closure date -> " + incyyte.getClosureDate());
                closeDate = Utility.verifyDateFormat(incyyte.getClosureDate());
            }
            qId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("QUESTIONS_PK", true);
            incyyte.setId(new Long(qId));
            grpId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("GROUPS_PK", true);

            template = abstractDao.getJdbcTemplate("addInCyyte");
            if (incyyte.getGroup() == null) {
                Group grp = new Group();
                grp.setGroupId(new Long(grpId));
                String question = incyyte.getIncyyte();//.replaceAll("\\s+", "_");
                grp.setGroupName(question.length() < 20 ? question : question.substring(0, 19) + "... ID " + grpId);

                incyyte.setGroup(grp);
            } else {
                incyyte.getGroup().setGroupId(new Long(grpId));
            }
            QueryHelper.doUpdate(template, questionQuery, QueryParameterFactory.getQuParams(incyyte));
            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: Insert Question completed");
            insertBatchAnswers(qId, incyyte.getAnswers(), template);
            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: Insert Batch Answer completed");
            int contact_no = 0;
            if (incyyte.getContacts() != null) {
                contact_no = incyyte.getContacts().size();
            }
            Long[] contactIds = insertBatchContacts(incyyte.getUserId(), incyyte.getContacts(), template);
            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: Insert  contacts  completed");
            QueryHelper.doUpdate(template, groupQuery, QueryParameterFactory.getGrpParams(incyyte));
            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: Insert Group  completed");
            List<GroupContact> groupContacts = buildGroupContacts("PollPage", grpId, Arrays.asList(contactIds));
            groupDao.insertBatchGrpContacts(groupContacts, template);
            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: Insert  group contacts  completed");
            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: Insert Incyyte started");
            QueryHelper.doUpdate(template, inCyyteQuery, QueryParameterFactory.getInCyyteParams(grpId, qId, contact_no, closeDate));
            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: Insert Incyyte  completed");
            abstractDao.getTxnHelper().commitTxn();
            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: Transaction finished ");
            //CLOSED TXN


            //schedule closure job
            if (incyyte.getClosureDate() != null) {
                new ScheduleClosure(qId, grpId, closeDate, this);
            }
            Logger.debug("%%%%%%%%%%%%%%% addInCyyte: returning Id - " + incyyte.getId());
            return incyyte.getId();
        } catch (ParseException e) {
            Logger.error("addInCyyte: Invalid format passed");
            return null;
        } catch (Exception e) {
            Logger.error("addInCyyte: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
            return null;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addInCyyte");
        }
    }

    @Override
    public void addMailingList(InCyyte incyyte) {
        // TODO Auto-generated method stub
    }

    @Override
    public Long add(Object entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addCollection(Set objs) {
        // TODO Auto-generated method stub
    }

    @Override
    public Object load(Class clz, long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InCyyte getInCyyte(long incyyteID) {
        return getInCyyte(incyyteID, false);
    }

    @Override
    public InCyyte getInCyyte(long incyyteID, boolean getAnswers) {
        return getInCyyte(incyyteID, getAnswers, false);
    }


    @Override
    public InCyyte getInCyyte(String incyyteCode, boolean getAnswers) {
        return getInCyyte(incyyteCode, getAnswers, false);
    }

    @Override
    public InCyyte getInCyyte(long incyyteID, boolean getAnswers, boolean getContacts) {
        Logger.info("Entering into getInCyyte():UserDaoJTImpl");
        QueryParameters params = new QueryParameters();
        params.addParam(incyyteID);
        InCyyte incyyte = null;
        try {
            String inCyyteQuery = UserDaoQueries.getInCyyteQuery().toString();
            Logger.debug("inCyyteQuery: " + inCyyteQuery);
            Logger.debug("Params:" + incyyteID);
            incyyte = (InCyyte) QueryHelper.doQueryForObject(abstractDao, "getInCyyte", inCyyteQuery, params, new IncyyteBusinessRowMapper());

            if (getAnswers) {
                //set answers
                String answerQuery = UserDaoQueries.getAnswerQuery().toString();
                Logger.debug("answerQuery: " + answerQuery);
                List<Answer> answers = QueryHelper.doQuery(abstractDao, "getInCyyte", answerQuery, params, new AnswerRowMapper());
                incyyte.setAnswers(answers);
            }

            if (getContacts) {
                JdbcTemplate template = abstractDao.getJdbcTemplate("getInCyyte");
                List<Contact> contacts = getInCyyteMappedGrpContacts(incyyte.getId(), incyyte.getUserId(), incyyte.getGrpId(), template);
                incyyte.setContacts(contacts);
            }
        } catch (Exception e) {
            Logger.error("getInCyyte: Failed ", e);
        }
        Logger.info("Exiting from getInCyyte():UserDaoJTImpl");
        return incyyte;
    }


    @Override
    public InCyyte getInCyyte(String incyyteCode, boolean getAnswers, boolean getContacts) {
        Logger.info("Entering into getInCyyte():UserDaoJTImpl");
        QueryParameters params = new QueryParameters();
        params.addParam(incyyteCode);
        InCyyte incyyte = null;
        try {
            String inCyyteQuery = UserDaoQueries.getInCyyteQueryByCode().toString();
            Logger.debug("inCyyteQuery: " + inCyyteQuery);
            Logger.debug("Params:" + incyyteCode);
            incyyte = (InCyyte) QueryHelper.doQueryForObject(abstractDao, "getInCyyte", inCyyteQuery, params, new IncyyteBusinessRowMapper());

            if (getAnswers) {
                //set answers
                String answerQuery = UserDaoQueries.getAnswerQuery().toString();
                Logger.debug("answerQuery: " + answerQuery);
                QueryParameters ansParams = new QueryParameters();
                ansParams.addParam(incyyte.getId());
                List<Answer> answers = QueryHelper.doQuery(abstractDao, "getInCyyte", answerQuery, ansParams, new AnswerRowMapper());
                incyyte.setAnswers(answers);
            }

            if (getContacts) {
                JdbcTemplate template = abstractDao.getJdbcTemplate("getInCyyte");
                List<Contact> contacts = getInCyyteMappedGrpContacts(incyyte.getId(), incyyte.getUserId(), incyyte.getGrpId(), template);
                incyyte.setContacts(contacts);
            }
        } catch (Exception e) {
            Logger.error("getInCyyte: Failed ", e);
        }
        Logger.info("Exiting from getInCyyte():UserDaoJTImpl");
        return incyyte;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType) throws InCyyteExceptions {
        List<InCyyte> incyytes = null;
        String query = UserDaoQueries.getUserInCyyteQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(sendType);
        params.addParam(userID);
        try {
            Logger.debug("myInCyytesQuery: " + query);
            incyytes = QueryHelper.doQuery(abstractDao, "getUserInCyytesByUserId", query, params, new InCyyteRowMapper());
            Logger.debug("InCyyte Ids: " + incyytes.size());
        } catch (Exception e) {
            Logger.error("getUserInCyytesByUserId: Failed ", e);
            throw new InCyyteExceptions(e.getMessage(), e);
        }
        return incyytes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType, int offset, int noOfRecords, String param, String criteria) throws InCyyteExceptions {
        List<InCyyte> incyytes = null;
        String query = UserDaoQueries.getUserInCyyteQuery().toString() + " limit " + noOfRecords + " offset " + offset;
        QueryParameters params = new QueryParameters();
        params.addParam(sendType);
        params.addParam(userID);
        try {
            Logger.debug("myInCyytesQuery: " + query);
            incyytes = QueryHelper.doQuery(abstractDao, "getUserInCyytesByUserId", query, params, new InCyyteRowMapper());
            Logger.debug("InCyyte Ids: " + incyytes.size());
        } catch (Exception e) {
            Logger.error("getUserInCyytesByUserId: Failed ", e);
            throw new InCyyteExceptions(e.getMessage(), e);
        }
        return incyytes;
    }


    @Override
    public List<InCyyte> getUserInCyytes(String email, int offset, int noOfRecords, String param, String criteria, boolean showArchivedPolls, String type, String sendType) {
        Logger.debug("getUserIncyytes:::email:" + email + "offset::" + offset + " noOfRecords::" + noOfRecords + " param::" + param + " criteria::" + criteria + " type::" + type);
        QueryParameters params = new QueryParameters();
        params.addParam(email);
        try {
            if (type.equals(Constants.INCYYTE_SENT)) {
                Logger.debug("This is for the sent incyytes which are sent by the user.");
                String myInCyytesQuery = null;
                if (param != null && StringUtils.isNotEmpty(param)) {
                    myInCyytesQuery = UserDaoQueries.getMyInCyytesCriteriaQuery().toString() + " limit " + noOfRecords + " offset " + offset;
                    params.addParam("%" + param + "%");
                } else {
                    myInCyytesQuery = UserDaoQueries.getMyInCyytesQuery().toString() + " limit " + noOfRecords + " offset " + offset;
                    params.addParam(sendType);
                }
                Logger.debug("myInCyytesQuery: " + myInCyytesQuery);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", myInCyytesQuery, params, new InCyyteSentIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_RESULTS)) {
                Logger.debug("This is for All Incoming incyytes for particular user");
                String viewResultsQuery = null;
                if (param != null && StringUtils.isNotEmpty(param)) {
                    showArchivedPolls = false;
                    viewResultsQuery = UserDaoQueries.getViewResultsQuery(showArchivedPolls);
                    viewResultsQuery += " where question like ? ";
                    viewResultsQuery += " limit " + noOfRecords + " offset " + offset;
                    Logger.debug("viewResultsQuery::" + viewResultsQuery);
                    params.addParam(email);
                    params.addParam("%" + param + "%");
                    return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewResultsQuery, params, new InCyyteIDRowMapper());
                } else {
                    viewResultsQuery = UserDaoQueries.getViewResultsQuery(showArchivedPolls) + " limit " + noOfRecords + " offset " + offset;
                    params.addParam(email);
                    Logger.debug("viewResultsQuery: " + viewResultsQuery);
                    return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewResultsQuery, params, new InCyyteIDRowMapper());
                }
            } else if (type.equals(Constants.INCYYTE_POST)) {
                String viewPostedResultsQuery = UserDaoQueries.getViewPostedResultsQuery().toString();
                Logger.debug("viewResultsQuery: " + viewPostedResultsQuery);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewPostedResultsQuery, params, new InCyyteIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_REGIONS_ALL)) {
                Logger.debug("This is for polls in my region sent by any of the user including the same .");
                String viewRegionalAllQuery = null;
                if (param != null && StringUtils.isNotEmpty(param))
                    viewRegionalAllQuery = UserDaoQueries.getViewRegionalAllCriteriaQuery().toString() + " limit " + noOfRecords + " offset " + offset;
                else
                    viewRegionalAllQuery = UserDaoQueries.getViewRegionalAllQuery().toString() + " limit " + noOfRecords + " offset " + offset;

                Logger.debug("viewRegionalAllQuery: " + viewRegionalAllQuery);
                UserDomain udomain = getUserDomain(email);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewRegionalAllQuery, QueryParameterFactory.getRegionalAllParams(udomain.getCountyName(), udomain.getPostalCode(), param), new InCyyteIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_REGIONS_POSTCODE)) {
                String viewRegionalPostcodeQuery = UserDaoQueries.getViewRegionalPostcodeQuery().toString();
                Logger.debug("viewRegionalPostcodeQuery: " + viewRegionalPostcodeQuery);
                UserDomain udomain = getUserDomain(email);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewRegionalPostcodeQuery, QueryParameterFactory.getRegionalPostcodeParams(udomain.getPostalCode()), new InCyyteIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_REGIONS_COUNTY)) {
                String viewRegionalCountyQuery = UserDaoQueries.getViewRegionalCountyQuery().toString();
                Logger.debug("viewRegionalCountyQuery: " + viewRegionalCountyQuery);
                UserDomain udomain = getUserDomain(email);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewRegionalCountyQuery, QueryParameterFactory.getRegionalCountyParams(udomain.getCountyName()), new InCyyteIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_REGIONS_REGION)) {
                String viewRegionalQuery = UserDaoQueries.getViewRegionalQuery().toString();
                Logger.debug("viewRegionalQuery: " + viewRegionalQuery);
                UserDomain udomain = getUserDomain(email);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewRegionalQuery, QueryParameterFactory.getRegionalParams(udomain.getPostalCode()), new InCyyteIDRowMapper());
            }
        } catch (Exception e) {
            Logger.error("getUserInCyytes: Failed ", e);
        }
        return null;
    }

    @Override
    public List<InCyyte> getUserInCyytes(String email, String type, boolean showArchivedPolls) {
        Logger.debug("getUserIncyytes:::email:" + email + "type::" + type);
        QueryParameters params = new QueryParameters();
        params.addParam(email);
        try {
            if (type.equals(Constants.INCYYTE_SENT)) {
                String myInCyytesQuery = UserDaoQueries.getMyInCyytesQuery().toString();
                Logger.debug("myInCyytesQuery: " + myInCyytesQuery);
                params.addParam(type);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", myInCyytesQuery, params, new InCyyteSentIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_RESULTS)) {
                String viewResultsQuery = UserDaoQueries.getViewResultsQuery(showArchivedPolls);
                params.addParam(email);
                Logger.debug("viewResultsQuery: " + viewResultsQuery);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewResultsQuery, params, new InCyyteIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_POST)) {
                String viewPostedResultsQuery = UserDaoQueries.getViewPostedResultsQuery().toString();
                Logger.debug("viewResultsQuery: " + viewPostedResultsQuery);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewPostedResultsQuery, params, new InCyyteIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_REGIONS_ALL)) {
                String viewRegionalAllQuery = UserDaoQueries.getViewRegionalAllQuery().toString();
                Logger.debug("viewRegionalAllQuery: " + viewRegionalAllQuery);
                UserDomain udomain = getUserDomain(email);
                if (udomain != null && StringUtils.isNotBlank(udomain.getCountyName()) && StringUtils.isNotBlank(udomain.getPostalCode())) {
                    return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewRegionalAllQuery, QueryParameterFactory.getRegionalAllParams(udomain.getCountyName(), udomain.getPostalCode(), null), new InCyyteIDRowMapper());
                } else {
                    return null;
                }
            } else if (type.equals(Constants.INCYYTE_REGIONS_POSTCODE)) {
                String viewRegionalPostcodeQuery = UserDaoQueries.getViewRegionalPostcodeQuery().toString();
                Logger.debug("viewRegionalPostcodeQuery: " + viewRegionalPostcodeQuery);
                UserDomain udomain = getUserDomain(email);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewRegionalPostcodeQuery, QueryParameterFactory.getRegionalPostcodeParams(udomain.getPostalCode()), new InCyyteIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_REGIONS_COUNTY)) {
                String viewRegionalCountyQuery = UserDaoQueries.getViewRegionalCountyQuery().toString();
                Logger.debug("viewRegionalCountyQuery: " + viewRegionalCountyQuery);
                UserDomain udomain = getUserDomain(email);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewRegionalCountyQuery, QueryParameterFactory.getRegionalCountyParams(udomain.getCountyName()), new InCyyteIDRowMapper());
            } else if (type.equals(Constants.INCYYTE_REGIONS_REGION)) {
                String viewRegionalQuery = UserDaoQueries.getViewRegionalQuery().toString();
                Logger.debug("viewRegionalQuery: " + viewRegionalQuery);
                UserDomain udomain = getUserDomain(email);
                return QueryHelper.doQuery(abstractDao, "getUserInCyytes", viewRegionalQuery, QueryParameterFactory.getRegionalParams(udomain.getPostalCode()), new InCyyteIDRowMapper());
            }
        } catch (Exception e) {
            Logger.error("getUserInCyytes: Failed ", e);
        }
        return null;
    }

    @Override
    public List<Response> getAllResponses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addXmlToInCyyte(InCyyte incyyte) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Answer> getInCyyteAnswers(long inCyyteId) throws Exception {
        String answerQuery = UserDaoQueries.getAnswerQuery().toString();
        QueryParameters params = new QueryParameters();
        Logger.debug("answerQuery: " + answerQuery);
        params.addParam(inCyyteId);
        List<Answer> answers = QueryHelper.doQuery(abstractDao, "getInCyyte", answerQuery, params, new AnswerRowMapper());
        return answers;
    }

    @Override
    public InCyyte getInCyyte(String incyytecode) {
        try {
            String inCyyteByCodeQuery = UserDaoQueries.getInCyyteByCodeQuery().toString();
            QueryParameters params = new QueryParameters();
            params.addParam(incyytecode);
            InCyyte incyyte = (InCyyte) QueryHelper.doQueryForObject(abstractDao, "getInCyyte", inCyyteByCodeQuery, params, new InCyyteRowMapper());
            //set answers
            String answerQuery = UserDaoQueries.getAnswerQuery().toString();
            QueryParameters params2 = new QueryParameters();
            Logger.debug("answerQuery: " + answerQuery);
            params2.addParam(incyyte.getId());
            List<Answer> answers = QueryHelper.doQuery(abstractDao, "getInCyyte", answerQuery, params2, new AnswerRowMapper());
            incyyte.setAnswers(answers);

            JdbcTemplate template = abstractDao.getJdbcTemplate("getInCyyte");
            List<Contact> contacts = getInCyyteMappedGrpContacts(incyyte.getId(), incyyte.getUserId(), incyyte.getGrpId(), template);
            incyyte.setContacts(contacts);
            return incyyte;
        } catch (Exception e) {
            Logger.error("getInCyyte: Failed ", e);
        }
        return null;
    }

    @Override
    public boolean isContactResponded(long incyyteID, long memberId) {
        String isContactRespondedQuery = UserDaoQueries.getIsContactRespondedQuery().toString();
        try {
            Logger.debug("IsContactRespondedQuery: " + isContactRespondedQuery);
            QueryParameters params = new QueryParameters();
            params.addParam(incyyteID);
            params.addParam(memberId);
            int i = QueryHelper.doQueryForInt(abstractDao, "isContactResponded", isContactRespondedQuery, params);
            Logger.debug("i value for is user voted already:::" + i);
            if (i == 0) return false;
            return true;
        } catch (Exception e) {
            Logger.error("isContactResponded: Failed ", e);
            return false;
        }
    }

    @Override
    public boolean isRegionalContactResponded(long incyyteID, long userId) {
        String isRegionalContactRespondedQuery = UserDaoQueries.getIsRegionalContactRespondedQuery().toString();
        try {
            Logger.debug("isRegionalContactRespondedQuery: " + isRegionalContactRespondedQuery);
            QueryParameters params = new QueryParameters();
            params.addParam(incyyteID);
            params.addParam(userId);
            int i = QueryHelper.doQueryForInt(abstractDao, "isRegionalContactResponded", isRegionalContactRespondedQuery, params);
            if (i == 0) return false;
            return true;
        } catch (Exception e) {
            Logger.error("isRegionalContactResponded: Failed ", e);
            return false;
        }
    }

    @Override
    public boolean isSharedContactResponded(long incyyteID, long sharedId) {
        String isSharedContactRespondedQuery = UserDaoQueries.getIsSharedContactRespondedQuery().toString();
        try {
            Logger.debug("isSharedContactRespondedQuery: " + isSharedContactRespondedQuery);
            QueryParameters params = new QueryParameters();
            params.addParam(incyyteID);
            params.addParam(sharedId);
            int i = QueryHelper.doQueryForInt(abstractDao, "isSharedContactResponded", isSharedContactRespondedQuery, params);
            if (i == 0) return false;
            return true;
        } catch (Exception e) {
            Logger.error("isSharedContactResponded: Failed ", e);
            return false;
        }
    }


    @Override
    public boolean isPostResponded(long incyyteID, long memberId) {
        String isPostRespondedQuery = UserDaoQueries.getIsRecipientRespondedQuery().toString();
        try {
            Logger.debug("isPostResponded: " + isPostRespondedQuery);
            QueryParameters params = new QueryParameters();
            params.addParam(incyyteID);
            params.addParam(memberId);
            int i = QueryHelper.doQueryForInt(abstractDao, "isPostResponded", isPostRespondedQuery, params);
            Logger.debug("ispostedrespone:::::::I value" + i);
            if (i == 0) return false;
            return true;
        } catch (Exception e) {
            Logger.error("isPostResponded: Failed ", e);
            return false;
        }
    }

    @Override
    public boolean isRecipientResponded(long incyyteID, long userId) {
        String isRecipientRespondedQuery = UserDaoQueries.getIsRecipientRespondedQuery().toString();
        try {
            Logger.debug("isRecipientRespondedQuery: " + isRecipientRespondedQuery);
            QueryParameters params = new QueryParameters();
            params.addParam(incyyteID);
            params.addParam(userId);
            int i = QueryHelper.doQueryForInt(abstractDao, "isRecipientResponded", isRecipientRespondedQuery, params);
            if (i == 0) return false;
            return true;
        } catch (Exception e) {
            Logger.error("isRecipientResponded: Failed ", e);
            return false;
        }
    }

    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public List<Contact> getInCyyteMailList(long incyyteID) {
        String mailListQuery = UserDaoQueries.getMailListQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(new Long(incyyteID).toString());
        Logger.debug("%%%%%%%% mailListQuery: " + mailListQuery);
        List<Contact> mailList = null;
        try {
            mailList = QueryHelper.doQuery(abstractDao, "getInCyyteMailList", mailListQuery, params, new ContactRowMapper());
        } catch (Exception e) {
            Logger.error("mailListQuery: Failed ", e);
        }
        return mailList;
    }

    @Override
    public void logFailedEmails(User user, String emailType) {
        //User Email Failed, Log into DB for Auto Resend
        Logger.debug("logMailError2DB: Log Email Error");
        //get Queries
        String logFailedEmailQuery = UserDaoQueries.getLogFailedEmailQuery().toString();
        Logger.debug("logFailedEmailQuery: " + logFailedEmailQuery);
        int insertCount1 = 0;
        try {
            insertCount1 = QueryHelper.doUpdate(abstractDao, "logFailedEmails", logFailedEmailQuery, QueryParameterFactory.getFailedEmailParams(user, emailType));
        } catch (Exception e) {
            Logger.error("logMailError2DB: Failed ", e);
        }
        Logger.debug("logFailedEmails::" + insertCount1);
    }

    @Override
    public void addInCyyteXmlString(InCyyte incyyte) throws InCyyteExceptions {
        if (incyyte.getId() == null)
            throw new InCyyteExceptions(ExceptionMessages.IC8_MISSING_ID_MSG);
        if (incyyte.getXmlString() == null)
            throw new InCyyteExceptions(ExceptionMessages.IC8_MISSING_XMLSTRING_MSG);
        Logger.debug("addInCyyteXmlString: update InCyyte xmlString");
        //get Queries
        String updateXmlStringQuery = UserDaoQueries.getUpdateXmlStringQuery().toString();
        Logger.debug("updateXmlStringQuery: " + updateXmlStringQuery);
        try {
            QueryHelper.doUpdate(abstractDao, "addInCyyteXmlString", updateXmlStringQuery, QueryParameterFactory.getXmlStringParams(incyyte));
        } catch (Exception e) {
            Logger.error("addInCyyteXmlString: Failed ", e);
        }
    }

    @Override
    public List<Response> getResponses(long incyyteId) {
        String inCyyteResponsesQuery = UserDaoQueries.getInCyyteResponsesQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(new Long(incyyteId).toString());
        Logger.debug("inCyyteResponsesQuery: " + inCyyteResponsesQuery);
        List<Response> responses = null;
        try {
            responses = QueryHelper.doQuery(abstractDao, "getResponses", inCyyteResponsesQuery, params, new ResponseRowMapper());
        } catch (Exception e) {
            Logger.error("inCyyteResponsesQuery: Failed ", e);
        }
        return responses;
    }

    @Override
    public List<Response> getRegionalResponses(long incyyteId) {
        String inCyyteRegionalResponsesQuery = UserDaoQueries.getInCyyteRegionalResponsesQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(new Long(incyyteId).toString());
        Logger.debug("inCyyteRegionalResponsesQuery: " + inCyyteRegionalResponsesQuery);
        List<Response> responses = null;
        try {
            responses = QueryHelper.doQuery(abstractDao, "getRegionalResponses", inCyyteRegionalResponsesQuery, params, new RegionalResponseRowMapper());
        } catch (Exception e) {
            Logger.error("inCyyteRegionalResponsesQuery: Failed ", e);
        }
        return responses;
    }

    @Override
    public List<Response> getPostedResponses(long incyyteId) {
        String inCyytePostedResponsesQuery = UserDaoQueries.getInCyytePostedResponsesQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(new Long(incyyteId).toString());
        Logger.debug("inCyytePostedResponsesQuery: " + inCyytePostedResponsesQuery);
        List<Response> responses = null;
        try {
            responses = QueryHelper.doQuery(abstractDao, "getPostedResponses", inCyytePostedResponsesQuery, params, new PostedResponseRowMapper());
        } catch (Exception e) {
            Logger.error("inCyyteResponsesQuery: Failed ", e);
        }
        return responses;
    }

    @Override
    public void incrementResponse(long incyyteID, long memberId) {
        Logger.debug("incrementResponse: update InCyyte ");
        String incrementInCyyteResponseQuery = UserDaoQueries.getIncrementInCyyteResponseQuery().toString();
        Logger.debug("incrementInCyyteResponseQuery: " + incrementInCyyteResponseQuery);
        try {
            QueryHelper.doUpdate(abstractDao, "incrementResponse", incrementInCyyteResponseQuery, QueryParameterFactory.getIncrementResponseParams(incyyteID));
        } catch (Exception e) {
            Logger.error("incrementResponse: Failed ", e);
        }
    }

    @Override
    public void incrementGeneralResponse(long incyyteID) {
        Logger.debug("incrementResponse: update InCyyte ");
        String incrementInCyyteResponseQuery = UserDaoQueries.getIncrementGeneralInCyyteResponseQuery().toString();
        Logger.debug("incrementInCyyteResponseQuery: " + incrementInCyyteResponseQuery);
        try {
            QueryHelper.doUpdate(abstractDao, "incrementGeneralResponse", incrementInCyyteResponseQuery, QueryParameterFactory.getIncrementGenralResponseParams(incyyteID));
        } catch (Exception e) {
            Logger.error("incrementResponse: Failed ", e);
        }
    }

    @Override
    public int getUserDash(String email, String type) {
        Logger.debug("getUserDash: for dashboard " + type);
        int num = -1;
        String query = null;
        QueryParameters param = new QueryParameters();
        param.addParam(email);
        Logger.debug("Query: " + query);
        try {
            if (type.equals(Constants.DASH_INCOMING)) {
                query = UserDaoQueries.getUserIncomingQuery().toString();
                num = QueryHelper.doQueryForInt(abstractDao, "getUserDash", query, QueryParameterFactory.getIncomingParams(email));
            } else if (type.equals(Constants.DASH_SENT)) {
                query = UserDaoQueries.getUserSentQuery().toString();
                num = QueryHelper.doQueryForInt(abstractDao, "getUserDash", query, param);
            } else if (type.equals(Constants.DASH_COMPLETED)) {
                query = UserDaoQueries.getUserCompletedQuery().toString();
                num = QueryHelper.doQueryForInt(abstractDao, "getUserDash", query, param);
            } else if (type.equals(Constants.DASH_PETITIONS)) {
                query = UserDaoQueries.getUserPetitionQuery().toString();
                num = QueryHelper.doQueryForInt(abstractDao, "getUserDash", query, param);
            }
        } catch (Exception e) {
            Logger.error("getUserDash: Failed for " + type + " - ", e);
        }
        return num;
    }

    @Override
    public void closeInCyyte(long incyyteId, long groupId) {
        Logger.debug("closeInCyyte: setClosed flag");
        String closeInCyyteQuery = UserDaoQueries.getCloseInCyyteQuery().toString();
        Logger.debug("closeInCyyteQuery: " + closeInCyyteQuery);
        try {
            QueryHelper.doUpdate(abstractDao, "closeInCyyte", closeInCyyteQuery, QueryParameterFactory.getCloseInCyyteParams(incyyteId, groupId));
        } catch (Exception e) {
            Logger.error("closeInCyyte: Failed ", e);
        }
    }

    @Override
    public List<InCyyte> getInCyyteByCreatedBy(String createdby, String questionType) {
        List<InCyyte> incyytes = null;
        String inCyyteQuery = UserDaoQueries.getInCyyteByCreatedBy().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(createdby);
        params.addParam(questionType);
        try {
            Logger.debug("inCyyteQuery: " + inCyyteQuery);
            incyytes = QueryHelper.doQuery(abstractDao, "getInCyyteByCreatedBy", inCyyteQuery, params, new InCyyteRowMapper());
        } catch (Exception e) {
            Logger.error("getInCyyteByCreatedBy: Failed ", e);
        }
        return incyytes;
    }

    @Override
    public void logFailedEmails(UserContactModel cuser2) {
        String failedinviteQuery = ContactDaoQueries.getFailedInviteMail().toString();
        Logger.debug("logFailedEmails: " + failedinviteQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(cuser2.getContactid());
        try {
            QueryHelper.doUpdate(abstractDao, "logFailedEmails", failedinviteQuery, params);
        } catch (Exception e) {
            Logger.error("logFailedEmails: Failed ", e);
        }
    }

    @Override
    public void updateUsersName(String email, String firstname, String lastname) {
        Logger.debug("updateUsersName: update User ");
        String updateUsersNameQuery = UserDaoQueries.getUpdateUsersNameQuery().toString();
        Logger.debug("updateUsersNameQuery: " + updateUsersNameQuery);
        try {
            QueryHelper.doUpdate(abstractDao, "updateUsersName", updateUsersNameQuery, QueryParameterFactory.getUsersNameParams(email, firstname, lastname));
        } catch (Exception e) {
            Logger.error("updateUsersName: Failed ", e);
        }
    }

    @Override
    public void deleteIncyyte(long incyyteId) {
        Logger.debug("deleteIncyyte: incyyteId - > " + incyyteId);
        String deleteIncyyteQuery = UserDaoQueries.getDeleteIncyyteQuery().toString();
        Logger.debug("deleteIncyyteQuery: " + deleteIncyyteQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(incyyteId);
        try {
            QueryHelper.doUpdate(abstractDao, "deleteIncyyte", deleteIncyyteQuery, params);
        } catch (Exception e) {
            Logger.error("deleteIncyyte: Failed ", e);
        }
    }

    @Override
    public void updateincyyteType(long incyyteId, String value) {
        Logger.debug("updateincyyteType: incyyteId - > " + incyyteId);
        String updtIncyyteQuery = UserDaoQueries.getUpdateIncyyteQuery().toString();
        Logger.debug("updtIncyyteQuery: " + updtIncyyteQuery);
        QueryParameters params = new QueryParameters();
        if (null != value && value.equalsIgnoreCase("PU")) {
            params.addParam("Y");
            params.addParam("N");
        } else {
            params.addParam("N");
            params.addParam("Y");

        }
        params.addParam(incyyteId);
        try {
            QueryHelper.doUpdate(abstractDao, "updtIncyyteQuery", updtIncyyteQuery, params);
        } catch (Exception e) {
            Logger.error("updtIncyyteQuery: Failed ", e);
        }
    }


    @Override
    public void editClosingDateTime(long incyyteId, Date closureDate) {
        Logger.debug("editClosingDateTime: incyyteId - > " + incyyteId);
        String updateClosureDateTimeQuery = UserDaoQueries.getUpdateClosureDateTimeQuery().toString();
        Logger.debug("editClosingDateTime: " + updateClosureDateTimeQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(closureDate);
        params.addParam(incyyteId);
        try {
            QueryHelper.doUpdate(abstractDao, "editClosingDateTime", updateClosureDateTimeQuery, params);
        } catch (Exception e) {
            Logger.error("editClosingDateTime: Failed ", e);
        }
    }

    @Override
    public void updatePublishPoll(String value, long incyyteId) {
        Logger.debug("updatePublishPoll: incyyteId - > " + incyyteId);
        String publishPollQuery = UserDaoQueries.getPublishPollQuery().toString();
        Logger.debug("publishPollQuery: " + publishPollQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(value);
        params.addParam(incyyteId);
        try {
            QueryHelper.doUpdate(abstractDao, "updatePublishPoll", publishPollQuery, params);
        } catch (Exception e) {
            Logger.error("updatePublishPoll: Failed ", e);
        }
    }

    @Override
    public void updateUsersMandatoryInfo(User user) throws CreateUserException {
        Logger.debug("updateUsersMandatoryInfo: update User ");
        StringBuilder updateMandateInfo = new StringBuilder();
        StringBuilder setClause = new StringBuilder();
        QueryParameters params = new QueryParameters();

        if (StringUtils.isNotBlank(user.getUsername())) {
            setClause.append(" username = ? ,");
            params.addParam(user.getUsername());
        }
        if (StringUtils.isNotBlank(user.getFirstname())) {
            setClause.append(" firstname = ? ,");
            params.addParam(user.getFirstname());
        }
        if (StringUtils.isNotBlank(user.getLastname())) {
            setClause.append(" lastname = ? ,");
            params.addParam(user.getLastname());
        }
        if (StringUtils.isNotBlank(user.getGender())) {
            setClause.append(" gender = ? ,");
            params.addParam(user.getGender());
        }
        if (user.getBirthYear() != null) {
            setClause.append(" birthYear = ? ,");
            params.addParam(user.getBirthYear());
        }
        if (user.getOccupation() != null) {
            setClause.append(" occupation = ? ,");
            params.addParam(user.getOccupation());
        }
        if (user.getIncome() != null) {
            setClause.append(" income = ? ,");
            params.addParam(user.getIncome());
        }
        if (user.getEthnicity() != null) {
            setClause.append(" ethnicity = ?,");
            params.addParam(user.getEthnicity());
        }
        if (user.getEducationLevel() != null) {
            setClause.append(" education_level = ?,");
            params.addParam(user.getEducationLevel());
        }
        if (StringUtils.isNotBlank(user.getAdultsInHouseHold())) {
            setClause.append(" adults_in_houseHold = ?,");
            params.addParam(user.getAdultsInHouseHold());
        }
        if (StringUtils.isNotBlank(user.getChildrenInHouseHold())) {
            setClause.append(" children_in_houseHold = ?,");
            params.addParam(user.getChildrenInHouseHold());
        }
        params.addParam(user.getId());

        updateMandateInfo.append(" UPDATE users SET ");
        updateMandateInfo.append(StringUtils.stripEnd(setClause.toString(), ","));
        updateMandateInfo.append(" WHERE userid = ? ");
        Logger.debug("updateMandateInfo:: " + updateMandateInfo.toString());
        try {
            QueryHelper.doUpdate(abstractDao, "updateUsersMandatoryInfo", updateMandateInfo.toString(), params);
        } catch (Exception e) {
            Logger.error("updateUsersMandatoryInfo: Failed ", e);
            throw new CreateUserException(e.getMessage());
        }
    }

    public UserDomain getPostalCounty(String postcode) {
        Logger.debug("getPostalCounty: update User ");
        String postalCountyQuery = UserDaoQueries.getPostalCountyQuery().toString();
        Logger.debug("postalCountyQuery : " + postalCountyQuery);

        QueryParameters params = new QueryParameters();
        params.addParam(postcode);

        try {
            List<UserDomain> userDomains = QueryHelper.doQuery(abstractDao, "getPostalCounty", postalCountyQuery, params, new UserDomainRowMapper());
            if (userDomains != null && !userDomains.isEmpty())
                return userDomains.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public void insertUserDomain(long userId, String postcode, String countryCode) {
        Logger.debug("insertUserDomain");
        UserDomain domain = null;
        if (postcode != null) {
            String[] pCode = postcode.split(" ");
            domain = getPostalCounty(pCode[0]);
        }
        Logger.debug("domain::" + domain);
        QueryParameters params = new QueryParameters();
        params.addParam(countryCode);
        if (domain != null)
            params.addParam(domain.getCountyName());
        else
            params.addParam("");
        params.addParam(postcode);
        params.addParam(userId);

        Logger.debug("params::" + params);
        UserDomain domainDB = getUserDomain(userId);
        Logger.debug("domainDB::" + domainDB);
        try {
            if (domainDB == null) {
                String insertUserDomainQuery = "INSERT INTO user_domain (country_code, county_name, postcode, user_id) VALUES (?, ?, ?, ?)";
                Logger.debug("insertUserDomainQuery : " + insertUserDomainQuery);
                QueryHelper.doUpdate(abstractDao, "insertUserDomain", insertUserDomainQuery, params);
                Logger.debug("After Insert");
            } else {
                String updateUserDomainQuery = "UPDATE user_domain SET country_code = ?, county_name = ?, postcode = ? WHERE user_id = ? ";
                Logger.debug("updateUserDomainQuery : " + updateUserDomainQuery);
                QueryHelper.doUpdate(abstractDao, "updateUserDomainQuery", updateUserDomainQuery, params);
                Logger.debug("After Update");
            }
        } catch (Exception e) {
            Logger.error("Exception::", e);
        } finally {
            //increment new User's incyyted region
            Logger.debug("Increment incyytes for Regional Domain: UserID " + userId);
            UserDomain udomain = getUserDomain(userId);
            if (udomain != null) incrementRegionalTotalIncyyted(udomain.getPostalCode(), udomain.getCountyName());
            Logger.debug("After Finally");
        }
    }

    public void incrementRegionalTotalIncyyted(String postcode, String CountyName) {
        Logger.debug("incrementRegionalTotalIncyyted: postcode - > " + postcode);
        String incrementRegionalTotalInCyytedQuery = UserDaoQueries.getIncrementRegionalTotalInCyytedQuery().toString();
        Logger.debug("incrementRegionalTotalInCyytedQuery: " + incrementRegionalTotalInCyytedQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(CountyName);
        params.addParam(postcode);
        params.addParam(postcode);
        params.addParam(postcode);
        try {
            QueryHelper.doUpdate(abstractDao, "incrementRegionalTotalIncyyted", incrementRegionalTotalInCyytedQuery, params);
        } catch (Exception e) {
            Logger.error("incrementRegionalTotalIncyyted: Failed ", e);
        }
    }

    public UserDomain getUserDomain(long userId) {
        String getUserDomainQuery = UserDaoQueries.getUserDomainByIdQuery().toString();
        Logger.debug("UserDomainQuery : " + getUserDomainQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(userId);

        try {
            List<UserDomain> userDomains = QueryHelper.doQuery(abstractDao, "getUserDomain", getUserDomainQuery, params, new UserDomainRowMapper());
            if (userDomains != null && !userDomains.isEmpty())
                return userDomains.get(0);
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return null;
    }

    public UserDomain getUserDomain(String email) {
        String getUserDomainQuery = UserDaoQueries.getUserDomainByEmailQuery().toString();
        Logger.debug("UserDomainQuery : " + getUserDomainQuery);

        QueryParameters params = new QueryParameters();
        params.addParam(email);

        try {
            List<UserDomain> userDomains = QueryHelper.doQuery(abstractDao, "getUserDomain", getUserDomainQuery, params, new UserDomainRowMapper());

            if (userDomains != null && !userDomains.isEmpty()) {
                return userDomains.get(0);
            }
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        return null;
    }

    @Override
    public long contactExistForUser(long userId, String contactEmail) {
        long contactId = 0;
        String contactExistForUserQuery = ContactDaoQueries.getContactExistForUserQuery().toString();
        QueryParameters params = new QueryParameters();
        Logger.debug("userId::" + userId + "contactEmail: " + contactEmail);
        params.addParam(contactEmail);
        params.addParam(userId);
        try {
            contactId = (Long) QueryHelper.doQueryForObject(abstractDao, "contactExistForUser", contactExistForUserQuery, params, Long.class);
        } catch (EmptyResultDataAccessException erdae) {
            Logger.debug("Contact Not exists:" + "userId::" + userId + "contactEmail: " + contactEmail);
        } catch (Exception e) {
            Logger.error("ERROR : contactExistForUser:  ", e);
        }
        return contactId;
    }

    /*
      * (non-Javadoc)
      * 	Insert Question ==> QID
      *	Batch Insert Answers (include QID)
      *	Insert Group ==>GID
      *	Loop Insert Contacts (include UID) ==> CID
      *	Batch Insert Grp_Contacts (Include GID,CID)
      *	Insert InCyyte (include GID,QID) ==> IID
      *
      * @see com.incyyte.app.dao.user.UserDao#addInCyyteByEmails(com.incyyte.app.domain.InCyyte)
      */
    @Override
    public Long addInCyyteByEmails(InCyyte incyyte) throws CreateInCyyteException {
        Logger.debug("addInCyyteByEmails: Create a New InCyyte." + incyyte);
        JdbcTemplate template = null;
        Logger.debug("addInCyyte: Create a New InCyyte11.");
        //get Queries
        String questionQuery = UserDaoQueries.getInsertQuestionQuery().toString();
        String groupQuery = UserDaoQueries.getInsertGroupQuery().toString();
        String inCyyteQuery = UserDaoQueries.getInsertInCyyteQuery().toString();
        //get IDs
        long qId = 0;
        long grpId = 0;
        Date closeDate = null;
        try {
            //BEGIN TXN
            abstractDao.getTxnHelper().beginTxn();
            if (incyyte.getClosureDate() != null) {
                Logger.debug("closure date -> " + incyyte.getClosureDate());
                closeDate = Utility.verifyDateFormat(incyyte.getClosureDate());
            }
            qId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("QUESTIONS_PK", true);
            incyyte.setId(new Long(qId));
            grpId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("GROUPS_PK", true);
            template = abstractDao.getJdbcTemplate("addInCyyteByEmails");
            if (incyyte.getGroup() == null) {
                Group grp = new Group();
                grp.setGroupId(new Long(grpId));
                String question = incyyte.getIncyyte();
                grp.setGroupName(question.length() < 20 ? question : question.substring(0, 19) + "... ID " + grpId);
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                //grp.setDescription("You sent a poll to this group on " + dateFormat.format(date) + ". You can change the name of this group by typing in new details");
                grp.setDescription(Constants.GRP_AUTO_GEN);
                incyyte.setGroup(grp);
            } else {
                incyyte.getGroup().setGroupId(new Long(grpId));
            }
            QueryHelper.doUpdate(template, questionQuery, QueryParameterFactory.getQuParams(incyyte));
            insertBatchAnswers(qId, incyyte.getAnswers(), template);
            int contact_no = 0;
            if (incyyte.getContacts() != null) {
                contact_no = incyyte.getContacts().size();
            }
            Logger.debug("contact_no::" + contact_no);
            Logger.debug("contacts::" + incyyte.getContacts());
            Long[] contactIds = insertBatchContacts(incyyte.getUserId(), incyyte.getContacts(), template);
            QueryHelper.doUpdate(template, groupQuery, QueryParameterFactory.getGrpParams(incyyte));
            List<GroupContact> groupContacts = buildGroupContacts("PollPage", grpId, Arrays.asList(contactIds));
            List<Long> groupMembers = groupDao.insertBatchGrpContacts(groupContacts, template);
            Logger.debug("groupMembers:" + groupMembers);
            List<GroupSharedInCyyte> sharedInCyytes = InCyyteUtil.buildGroupSharedInCyyte(String.valueOf(incyyte.getUserId()), qId, groupMembers);
            Logger.debug("sharedInCyytes:" + sharedInCyytes);
            groupDao.insertGrpSharedInCyyte(sharedInCyytes);
            QueryHelper.doUpdate(template, inCyyteQuery, QueryParameterFactory.getInCyyteParams(grpId, qId, contact_no, closeDate));
            abstractDao.getTxnHelper().commitTxn();
            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByEmails: Transaction finished ");
            //CLOSED TXN
            //schedule closure job
            if (incyyte.getClosureDate() != null)
                new ScheduleClosure(qId, grpId, closeDate, this);
            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByEmails: returning Id - " + incyyte.getId());
            return incyyte.getId();
        } catch (ParseException e) {
            Logger.error("addInCyyteByEmails: Invalid format passed:", e);
            return null;
        } catch (Exception e) {
            Logger.error("addInCyyteByEmails: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
            return null;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addInCyyteByEmails");
        }
    }

    /*
      * (non-Javadoc)
      * 	Insert Question ==> QID
      *	Batch Insert Answers (include QID)
      *	Insert InCyyte (include GID,QID) ==> IID
      *
      * @see com.incyyte.app.dao.user.UserDao#addInCyyteByGroup(com.incyyte.app.domain.InCyyte)
      */
    @Override
    public Long addInCyyteByGroup(InCyyte incyyte, long grpId) throws CreateInCyyteException {
        Logger.debug("addInCyyteByGroup: Create a New InCyyte." + incyyte.toString());
        JdbcTemplate template = null;
        incyyte.setGrpId(new Long(grpId));
        //get Queries
        String questionQuery = UserDaoQueries.getInsertQuestionQuery().toString();
        String inCyyteQuery = UserDaoQueries.getInsertInCyyteQuery().toString();
        //get IDs
        long qId = 0;
        Date closeDate = null;
        try {
            if (incyyte.getClosureDate() != null) {
                Logger.debug("closure date -> " + incyyte.getClosureDate());
                closeDate = Utility.verifyDateFormat(incyyte.getClosureDate());
            }
            //BEGIN TXN
            abstractDao.getTxnHelper().beginTxn();
            qId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("QUESTIONS_PK", true);
            incyyte.setId(new Long(qId));
            //1. Insert Question
            template = abstractDao.getJdbcTemplate("addInCyyteByGroup");
            QueryHelper.doUpdate(template, questionQuery, QueryParameterFactory.getQuParams(incyyte));
            //2. Insert Answers
            insertBatchAnswers(qId, incyyte.getAnswers(), template);
            List<Contact> contacts = getGroupContacts(incyyte.getUserId(), grpId, template);
            incyyte.setContacts(contacts);
            int contact_no = 0;
            if (incyyte.getContacts() != null) {
                contact_no = incyyte.getContacts().size();
            }
            //2. Insert inCyyte
            QueryHelper.doUpdate(template, inCyyteQuery, QueryParameterFactory.getInCyyteParams(grpId, qId, contact_no, closeDate));
            //3. Insert into Group Shared InCyyte table
            List<GroupContact> groupContacts = groupDao.getGroupContactMembers(grpId);
            List<GroupSharedInCyyte> groupSharedInCyytes = InCyyteUtil.buildSharedInCyyte(String.valueOf(incyyte.getUserId()), qId, groupContacts);
            groupDao.insertGrpSharedInCyyte(groupSharedInCyytes);
            abstractDao.getTxnHelper().commitTxn();
            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByGroup: Transaction finished ");
            //CLOSED TXN
            //schedule closure job
            if (incyyte.getClosureDate() != null)
                new ScheduleClosure(qId, grpId, closeDate, this);
            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByGroup: returning Id - " + incyyte.getId());
            return incyyte.getId();
        } catch (ParseException e) {
            Logger.error("addInCyyteByGroup: Invalid format passed:", e);
            return null;
        } catch (Exception e) {
            Logger.error("addInCyyteByGroup: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
            return null;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addInCyyteByGroup");
        }
    }


    @Override
    public void shareInCyyteToGroupContacts(InCyyte incyyte) throws Exception {
        Logger.debug("shareInCyyteToGroupContacts:" + incyyte);
        //Insert into Group Shared InCyyte table
        List<GroupContact> groupContacts = groupDao.getGroupContactMembers(incyyte.getGrpId());
        Logger.debug("groupContacts::" + groupContacts);
        Share sharedPoll = GroupUtil.getSharedPollFromGroupContacts(incyyte, groupContacts);
        Logger.debug("sharedPoll::" + sharedPoll);
        groupDao.shareInCyyte(sharedPoll);
        //Send Email to the User with the Poll information
        InCyyte inCyyte = getInCyyte(incyyte.getId());
        quickStartManager.sharePollToGroup(inCyyte, groupContacts);
        Logger.debug("shareInCyyteToGroupContacts:End:");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Contact> getGroupContacts(Long userId, Long groupId, JdbcTemplate template) throws DataAccessException {
        Logger.debug("Retrieving group contacts...");
        String sql = GroupDaoQueries.getUserPolledGroupContactsQuery();
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(groupId);
        List<Contact> contacts = QueryHelper.doQuery(template, sql, params, new RowMapper<Contact>() {
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

    private List<Contact> getInCyyteMappedGrpContacts(Long questionId, Long userId, Long groupId, JdbcTemplate template) throws DataAccessException {
        Logger.debug("Retrieving group contacts...");
        StringBuilder sqlStmt = new StringBuilder();
        sqlStmt.append("SELECT contactid,fk_userid,nickname,firstname,lastname,email ");
        sqlStmt.append(" FROM CONTACTS ");
        sqlStmt.append(" WHERE fk_userid = ? ");
        sqlStmt.append("   AND active_ind = 'A' ");
        sqlStmt.append("   AND contactid IN (SELECT gc.fk_contactid FROM GRP_CONTACTS gc,GRP_SHARED_INCYYTE gsi ");
        sqlStmt.append("                      WHERE gc.fk_groupid = ? AND gc.memberid = gsi.fk_member_id AND gsi.fk_question_id = ? )");
        sqlStmt.append("OR contactid IN (SELECT fk_contactid FROM SHARED_INCYYTE WHERE fk_original_groupid = 813)");

        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        params.addParam(groupId);
        params.addParam(questionId);
        List<Contact> contacts = QueryHelper.doQuery(template, sqlStmt.toString(), params, new RowMapper<Contact>() {
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

    /*
      * (non-Javadoc)
      * 	Insert Question ==> QID
      *	Batch Insert Answers (include QID)
      *	Insert Group ==>GID + postalRegion
      *	Update Recipients with Grp ID
      *	Insert InCyyte (include GID,QID) ==> IID
      *
      * @see com.incyyte.app.dao.user.UserDao#addInCyyteByRegion(com.incyyte.app.domain.InCyyte)
      */
    @Override
    public Long addInCyyteByRegion(InCyyte incyyte, String postalRegion) throws CreateInCyyteException {
        Logger.debug("addInCyyteByRegion: Create a New InCyyte." + incyyte.toString());
        JdbcTemplate template = null;
        //get Queries
        String questionQuery = UserDaoQueries.getInsertQuestionQuery().toString();
        String groupQuery = UserDaoQueries.getInsertGroupRegionQuery().toString();
        String inCyyteQuery = UserDaoQueries.getInsertInCyyteQuery().toString();
        String regionalRecipientQuery = UserDaoQueries.getRegionalRecipientQuery().toString();
        //get IDs
        long qId = 0;
        long grpId = 0;
        Date closeDate = null;
        try {
            if (incyyte.getClosureDate() != null) {
                Logger.debug("closure date -> " + incyyte.getClosureDate());
                closeDate = Utility.verifyDateFormat(incyyte.getClosureDate());
            }

            //BEGIN TXN
            abstractDao.getTxnHelper().beginTxn();

            qId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("QUESTIONS_PK", true);
            incyyte.setId(new Long(qId));
            grpId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("GROUPS_PK", true);
            //TODO
            template = abstractDao.getJdbcTemplate("addInCyyteByRegion");
            if (incyyte.getGroup() == null) {
                Group grp = new Group();
                grp.setGroupId(new Long(grpId));
                grp.setGroupName("RGN_" + dateFormat.format(cal.getTime()) + "_" + grpId);
                incyyte.setGroup(grp);
            } else {
                incyyte.getGroup().setGroupId(new Long(grpId));
            }
            //1. Add Question
            QueryHelper.doUpdate(template, questionQuery, QueryParameterFactory.getQuParams(incyyte));
            //2. Add Answers
            insertBatchAnswers(qId, incyyte.getAnswers(), template);
            //3. Add Group + Postal Region
            QueryHelper.doUpdate(template, groupQuery, QueryParameterFactory.getGrpParams(incyyte, postalRegion));
            Logger.debug("regionalRecipientQuery: " + regionalRecipientQuery);
            QueryParameters qp = new QueryParameters();
            UserRegRowMapper rowMapper = new UserRegRowMapper();
            qp.addParam(postalRegion);
            List<User> users = QueryHelper.doQuery(template, regionalRecipientQuery, qp, rowMapper);
            int recipients = 0;
            if (users != null) {
                recipients = users.size();
            }
            //4. update recipients
            //5. Add inCyyte
            QueryHelper.doUpdate(template, inCyyteQuery, QueryParameterFactory.getInCyyteParams(grpId, qId, recipients, closeDate));
            abstractDao.getTxnHelper().commitTxn();
            //CLOSED TXN

            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByRegion: Transaction finished ");
            //schedule closure job
            if (incyyte.getClosureDate() != null)
                new ScheduleClosure(qId, grpId, closeDate, this);
            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByRegion: returning Id - " + incyyte.getId());
            return incyyte.getId();
        } catch (ParseException e) {
            Logger.error("addInCyyteByRegion: Invalid format passed:", e);
            return null;
        } catch (Exception e) {
            Logger.error("addInCyyteByRegion: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
            return null;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addInCyyteByRegion ");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getRegionalRecipients(String postalRegion) {
        String regionalRecipientQuery = UserDaoQueries.getRegionalRecipientQuery().toString();
        List<User> users = null;
        QueryParameters qp = new QueryParameters();
        qp.addParam(postalRegion);
        try {
            Logger.debug("regionalRecipientQuery: " + regionalRecipientQuery);
            users = QueryHelper.doQuery(abstractDao, "getRegionalRecipients", regionalRecipientQuery, qp, new UserRegRowMapper());
            return users;
        } catch (Exception e) {
            Logger.error("getRegionalRecipients: Failed ", e);
            return null;
        }
    }

    /*
      * (non-Javadoc)
      * 	Insert Question ==> QID
      *	Batch Insert Answers (include QID)
      *	Insert InCyyte (include GID,QID) ==> IID
      *
      * @see com.incyyte.app.dao.user.UserDao#addInCyyteByPosting(com.incyyte.app.domain.InCyyte)
      */
    @Override
    public Long addInCyyteByPosting(InCyyte incyyte) throws CreateInCyyteException {
        Logger.debug("addInCyyteByPosting: Create a New InCyyte." + incyyte.toString());
        JdbcTemplate template = null;
        //get Queries
        String questionQuery = UserDaoQueries.getInsertQuestionQuery().toString();
        String inCyyteQuery = UserDaoQueries.getInsertInCyyteQuery().toString();
        //get IDs
        long qId = 0;
        long grpId = 0;
        Date closeDate = null;
        try {
            if (incyyte.getClosureDate() != null) {
                Logger.debug("closure date -> " + incyyte.getClosureDate());
                closeDate = Utility.verifyDateFormat(incyyte.getClosureDate());
            }

            //BEGIN TXN
            abstractDao.getTxnHelper().beginTxn();
            qId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("QUESTIONS_PK", true);
            incyyte.setId(new Long(qId));
            grpId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("GROUPS_PK", true);
            //TODO
            template = abstractDao.getJdbcTemplate("addInCyyteByPosting");
            if (incyyte.getGroup() == null) {
                Group grp = new Group();
                grp.setGroupId(new Long(grpId));
                grp.setGroupName("POST_" + dateFormat.format(cal.getTime()) + "_" + grpId);
                incyyte.setGroup(grp);
            } else {
                incyyte.getGroup().setGroupId(new Long(grpId));
            }
            //1. Add Question
            QueryHelper.doUpdate(template, questionQuery, QueryParameterFactory.getQuParams(incyyte));
            //2. Add Answers
            insertBatchAnswers(qId, incyyte.getAnswers(), template);
            //3. Add inCyyte
            QueryHelper.doUpdate(template, inCyyteQuery, QueryParameterFactory.getInCyyteParams(grpId, qId, 0, closeDate));
            abstractDao.getTxnHelper().commitTxn();
            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByPosting: Transaction finished ");
            //CLOSED TXN

            //schedule closure job
            if (incyyte.getClosureDate() != null)
                new ScheduleClosure(qId, grpId, closeDate, this);
            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByPosting: returning Id - " + incyyte.getId());
            return incyyte.getId();
        } catch (ParseException e) {
            Logger.error("addInCyyteByPosting: Invalid format passed", e);
            return null;
        } catch (Exception e) {
            Logger.error("addInCyyteByPosting: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
            return null;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addInCyyteByPosting");
        }
    }

    @Override
    public Long addInCyyteByPostRegion(InCyyte incyyte)
            throws CreateInCyyteException {

        Logger.debug("addInCyyteByPostRegion: Create a New InCyyte." + incyyte.toString());
        JdbcTemplate template = null;

        //get Queries
        String questionQuery = UserDaoQueries.getInsertQuestionQuery().toString();
        String inCyyteQuery = UserDaoQueries.getInsertInCyyteRegionQuery().toString();
        //get IDs
        long qId = 0;
        long grpId = 0;
        Date closeDate = null;
        try {
            if (incyyte.getClosureDate() != null) {
                Logger.debug("closure date -> " + incyyte.getClosureDate());
                closeDate = Utility.verifyDateFormat(incyyte.getClosureDate());
            }

            //BEGIN TXN
            abstractDao.getTxnHelper().beginTxn();
            Logger.debug("ques : id : " + incyyte.getId());
            if (incyyte.getId() == null) {
                qId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("QUESTIONS_PK", true);
                incyyte.setId(new Long(qId));
            }
            grpId = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("GROUPS_PK", true);
            //TODO
            template = abstractDao.getJdbcTemplate("addInCyyteByPosting");
            if (incyyte.getGroup() == null) {
                Group grp = new Group();
                grp.setGroupId(new Long(grpId));
                grp.setGroupName("POST_" + dateFormat.format(cal.getTime()) + "_" + grpId);
                incyyte.setGroup(grp);
            } else {
                incyyte.getGroup().setGroupId(new Long(grpId));
            }
            //1. Add Question
            QueryHelper.doUpdate(template, questionQuery, QueryParameterFactory.getQuParams(incyyte));
            //2. Add Answers
            insertBatchAnswers(incyyte.getId(), incyyte.getAnswers(), template);
            //3. Add inCyyte
            QueryHelper.doUpdate(template, inCyyteQuery, QueryParameterFactory.getInCyyteRegionParams(grpId, incyyte.getId(), closeDate, incyyte));
            abstractDao.getTxnHelper().commitTxn();
            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByPosting: Transaction finished ");
            //CLOSED TXN

            //schedule closure job
            if (incyyte.getClosureDate() != null)
                new ScheduleClosure(incyyte.getId(), grpId, closeDate, this);
            Logger.debug("%%%%%%%%%%%%%%% addInCyyteByPosting: returning Id - " + incyyte.getId());
            return incyyte.getId();
        } catch (ParseException e) {
            Logger.error("addInCyyteByPosting: Invalid format passed", e);
            return null;
        } catch (Exception e) {
            Logger.error("addInCyyteByPosting: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
            return null;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "addInCyyteByPosting");
        }
    }

    @Override
    public InCyyte checkIfInCyyteExist(String incyytecode) {
        String inCyyteByCodeQuery = UserDaoQueries.getCheckIfinCyyteExistQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(incyytecode);
        InCyyte incyyte = null;
        try {
            Logger.debug("checkIfInCyyteExistQuery: " + inCyyteByCodeQuery);
            incyyte = (InCyyte) QueryHelper.doQueryForObject(abstractDao, "checkIfInCyyteExist", inCyyteByCodeQuery, params, new InCyyteRowMapper());
        } catch (EmptyResultDataAccessException e) {
            //TODO: This is expected during Create InCyyte when sending the Poll to a email group
            //TODO: This will be triggered during Create Account and Login process
        } catch (Exception e) {
            Logger.error("checkIfInCyyteExist:", e);
        }
        return incyyte;
    }

    @Override
    public Long getPollMemberId(String UserEmail, long questionID) {
        String getPollMemberIdQuery = UserDaoQueries.getPollMemberIdQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(UserEmail);
        params.addParam(questionID);
        Long id = null;
        try {
            Logger.debug("getPollMemberIdQuery: " + getPollMemberIdQuery);
            id = (Long) QueryHelper.doQueryForLong(abstractDao, "getPollMemberId", getPollMemberIdQuery, params);
        } catch (Exception e) {
            Logger.debug("getPollMemberIdQuery: No MemberID exist with this questionID - " + questionID);
        }
        return id;
    }

    @Override
    public List<Long> getAlreadyVotedPolls(String email) {
        String getPollMemberIdQuery = UserDaoQueries.getAlreadyVotedPollsQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(email);
        params.addParam(email);
        //Long id = null;
        List<Long> id = null;
        try {
            id = QueryHelper.doQuery(abstractDao, "getAlreadyVotedPolls", getPollMemberIdQuery, params, Long.class);
        } catch (Exception e) {
            Logger.error(e);
        }
        return id;
    }

    @Override
    public Long checkPollShared(String UserEmail, long questionID) {
        String getCheckPollSharedQuery = UserDaoQueries.getCheckPollSharedQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(UserEmail);
        params.addParam(questionID);
        Long id = null;
        try {
            Logger.debug("getCheckPollSharedQuery: " + getCheckPollSharedQuery);
            id = (Long) QueryHelper.doQueryForLong(abstractDao, "checkPollShared", getCheckPollSharedQuery, params);
        } catch (Exception e) {
            Logger.error("getCheckPollSharedQuery: No ShareID exist with this questionID - " + questionID, e);
        }
        return id;
    }

    @Override
    public List<CommentsModel> getPollComments(long code, long incyyteId) {
        String commentQuery = "SELECT a.forumid " +
                "   ,a.fk_questionid " +
                "   ,a.comment " +
                "   ,a.created_date " +
                "   ,IFNULL(b.username, b.email) as created_by " +
                "   ,a.active_ind " +
                "   ,a.upload_location comment_upload_location " +
                "   ,a.cdn_file_name comment_cdn_file_name " +
                "   ,a.youtube_url youtube_url " +
                "   ,b.upload_location " +
                "   ,b.cdn_file_name " +
                "FROM forums a " +
                "    ,users b " +
                "WHERE fk_questionid = ?  " +
                "  and a.created_by = b.userid " +
                "ORDER BY created_date desc ";
        QueryParameters params = new QueryParameters();
        params.addParam(code);
        try {
            Logger.debug("commentQuery: " + commentQuery);
            List<CommentsModel> contactList = QueryHelper.doQuery(abstractDao, "getPollComments", commentQuery, params, new CommentsModelRowMapper());
            return contactList;
        } catch (Exception e) {
            Logger.debug("commentQuery: No comments exist with this questionID - ");
        }
        return null;
    }

    @Override
    public void insertDeletedIncyyte(Long questionId, Long userId) {
        String sql = "INSERT INTO DELETED_INCYYTE (questionId, userId) VALUES (?, ?)";
        JdbcTemplate template = null;
        QueryParameters insertParam = new QueryParameters();
        insertParam.addParam(questionId);
        insertParam.addParam(userId);
        try {
            abstractDao.getTxnHelper().beginTxn();
            template = abstractDao.getJdbcTemplate("insertDeletedIncyyte");
            QueryHelper.doUpdate(template, sql, insertParam);
            abstractDao.getTxnHelper().commitTxn();
        } catch (Exception e) {
            Logger.error("insertDeletedIncyyte: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "insertDeletedIncyyte");
        }
    }

    @Override
    public void updateDeletedIncyyte(Long questionId) {
        String updateQuestions = " UPDATE questions SET delete_ind = 'Y' where questionid = ? ";
        JdbcTemplate template = null;
        QueryParameters updateParam = new QueryParameters();
        updateParam.addParam(questionId);
        try {
            abstractDao.getTxnHelper().beginTxn();
            template = abstractDao.getJdbcTemplate("updateDeletedIncyyte");
            QueryHelper.doUpdate(template, updateQuestions, updateParam);
            abstractDao.getTxnHelper().commitTxn();
        } catch (Exception e) {
            Logger.error("updateDeletedIncyyte: Failed ", e);
            abstractDao.getTxnHelper().rollbackTxn();
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "updateDeletedIncyyte");
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean isIncyyteDeleted(final Integer questionId, final Integer userId) {
        String sql = UserDaoQueries.getIsDeletedIncyyteQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(questionId);
        params.addParam(userId);
        List<Integer> questionIds = QueryHelper.doQuery(abstractDao, "isIncyyteDeleted", sql, params, new RowMapper() {
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        });

        if (questionIds != null && !questionIds.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Long[] insertNewContacts(long userId, List<Contact> contacts) {
        Logger.debug("insertNewContacts: Adds new contacts if missing in users contact List");
        JdbcTemplate template = abstractDao.getJdbcTemplate("insertNewContacts");
        return insertBatchContacts(userId, contacts, template);
    }

    @Override
    public Share getShareRecord(long questionID, String contactEmail) {
        String sql = UserDaoQueries.getShareIDQuery().toString();
        QueryParameters params = new QueryParameters();
        params.addParam(questionID);
        params.addParam(contactEmail);
        Share share = null;
        Logger.debug("getShareRecord Query: " + sql);
        Object obj = QueryHelper.doQueryForObject(abstractDao, "getShareRecord", sql, params, new SharedInCyyteRowMapper());
        if (obj != null) {
            share = (Share) obj;
            Logger.debug("Share Details:  FMTID -" + share.getFmtId() + " Shared questionID: " + share.getQuestionId());
        }
        Logger.debug("getShareRecord Data: " + share);
        return share;
    }

    private List<GroupContact> buildGroupContacts(String userId, Long groupId, List<Long> contacts) {
        List<GroupContact> groupContacts = new ArrayList<GroupContact>();
        for (Long contact : contacts) {
            GroupContact groupContact = new GroupContact();
            groupContact.setContactId(contact);
            groupContact.setGroupId(groupId);
            groupContact.setCreatedBy(userId);
            groupContact.setLastUpdatedBy(userId);
            groupContact.setRole("Member");
            groupContacts.add(groupContact);
        }
        return groupContacts;
    }

    @Override
    public boolean updateUsersFrmPayment(PaymentModel paymentModel, long userId) throws Exception {

        Logger.debug("paymentModel::::" + paymentModel);
        Logger.debug("userId::::" + userId);

        String updateUsersFrmPayment = " UPDATE users  SET  firstname=? , lastname = ? , mobile = ?  where userid = ? ";
        JdbcTemplate template = null;
        QueryParameters updateParam = new QueryParameters();
        updateParam.addParam(paymentModel.getPaymentFirstName());
        updateParam.addParam(paymentModel.getPaymentLastName());
        updateParam.addParam(paymentModel.getMobileNumber());
        updateParam.addParam(userId);
        try {
            template = abstractDao.getJdbcTemplate("updateUsersFrmPayment");
            QueryHelper.doUpdate(template, updateUsersFrmPayment, updateParam);
            return true;
        } catch (Exception e) {
            Logger.error("update the users : Failed ", e);
            throw e;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "updateUsersFrmPayment");
        }
    }

    @Override
    public boolean updateUsersEmails(PaymentModel paymentModel, long userId) throws Exception {

        Logger.debug("paymentModel:" + paymentModel);
        Logger.debug("userId:" + userId);

        String updateUsers_emailsFrmPayment = "UPDATE users_emails SET  email = ?, DEFAULT_EMAIL = ? where fk_userid = ? ";
        JdbcTemplate template = null;
        QueryParameters updateParam = new QueryParameters();
        updateParam.addParam(paymentModel.getEmail());
        updateParam.addParam(1);
        updateParam.addParam(userId);
        try {
            template = abstractDao.getJdbcTemplate("updateUsersEmails");
            QueryHelper.doUpdate(template, updateUsers_emailsFrmPayment, updateParam);
            return true;
        } catch (Exception e) {
            Logger.error("Updateing the users emails : Failed ", e);
            throw e;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "updateUsersEmails");
        }
    }

    @Override
    public boolean updateUserType(User user, UserType business) throws Exception {

        Logger.debug("User:" + user);
        Logger.debug("business_Sliver:" + business);
        String updateUsers_type = "UPDATE users SET  user_type = ? where userid = ? ";
        JdbcTemplate template = null;
        QueryParameters updateParam = new QueryParameters();
        updateParam.addParam(business.toString());
        updateParam.addParam(user.getId());
        try {
            template = abstractDao.getJdbcTemplate("updateUserType");
            QueryHelper.doUpdate(template, updateUsers_type, updateParam);
            return true;
        } catch (Exception e) {
            Logger.error("Updateing the users type : Failed ", e);
            throw e;
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "updateUserType");
        }
    }
}