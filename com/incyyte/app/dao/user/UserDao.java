package com.incyyte.app.dao.user;

import com.incyyte.app.domain.*;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.service.Exceptions.CreateInCyyteException;
import com.incyyte.app.service.Exceptions.CreateUserException;
import com.incyyte.app.service.Exceptions.InCyyteExceptions;
import com.incyyte.app.web.model.CommentsModel;
import com.incyyte.app.web.model.PaymentModel;
import com.incyyte.app.web.model.UserContactModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserDao {
    //Adds a user InCyyte to DB
    public int addUser(User user) throws CreateUserException;

    public Long addUserAddress(UserAddress userAddr);

    public void addInCyyteXmlString(InCyyte incyyte) throws InCyyteExceptions;

    public void addXmlToInCyyte(InCyyte incyyte);

    public void updateUsersName(String email, String firstname, String lastname);

    public Long getPollMemberId(String UserEmail, long questionID);

    public Long checkPollShared(String UserEmail, long questionID);

    //Creating an incyyte types
    public Long addInCyyte(InCyyte incyyte) throws CreateInCyyteException;

    public Long addInCyyteByEmails(InCyyte incyyte) throws CreateInCyyteException;

    public Long addInCyyteByGroup(InCyyte incyyte, long grpId) throws CreateInCyyteException;

    public Long addInCyyteByPosting(InCyyte incyyte) throws CreateInCyyteException;

    public Long addInCyyteByPostRegion(InCyyte incyyte) throws CreateInCyyteException;

    // DO NOT USE: THIS FUNCTION IS INCOMPLETE
    public Long addInCyyteByRegion(InCyyte incyyte, String postalRegion) throws CreateInCyyteException;

    public void logFailedEmails(User user, String emailType);

    public void incrementResponse(long incyyteID, long memberId);

    public void incrementGeneralResponse(long incyyteID);

    public void closeInCyyte(long incyyteId, long groupId);

    public List<Contact> getInCyyteMailList(long incyyteID);

    public List<Response> getResponses(long incyyteId);

    public List<Response> getRegionalResponses(long incyyteId);

    public List<Response> getPostedResponses(long incyyteId);

    public List<Response> getAllResponses();

    public List<InCyyte> getInCyyteByCreatedBy(String createdby, String questionType);

    public List<InCyyte> getUserInCyytes(String email, String type, boolean showArchivedPolls);

    public List<InCyyte> getUserInCyytes(String email, int offset, int noOfRecords, String param, String criteria, boolean showArchivedPolls, String type, String sendType);

    public InCyyte getInCyyte(long incyyteID);

    public InCyyte getInCyyte(long incyyteID, boolean getAnswers);
    
    public InCyyte getInCyyte(String incyyteCode, boolean getAnswers);

    public InCyyte getInCyyte(long incyyteID, boolean getAnswers, boolean getContacts);
    
    public InCyyte getInCyyte(String incyytecode, boolean getAnswers, boolean getContacts);

    public InCyyte getInCyyte(String incyytecode);

    public boolean usernameExists(String username);

    public boolean passwordExists(String password);

    public boolean isContactResponded(long incyyteID, long memberId);

    public boolean isRegionalContactResponded(long incyyteID, long memberId);

    public boolean isSharedContactResponded(long incyyteID, long sharedId);

    public boolean isRecipientResponded(long incyyteID, long userId);

    public UserAddress findUserAddress(String cyyteHomeEmail);

    public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType) throws InCyyteExceptions;

    public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType, int offset, int noOfRecords, String param, String criteria) throws InCyyteExceptions;

    /*
      * @param email
      * @param type (INCOMING, SENT, COMPLETED, PETITIONS)
      */
    public int getUserDash(String email, String type);

    public void logFailedEmails(UserContactModel cuser2);

    public void deleteIncyyte(long incyyteId);

    public void updateincyyteType(long incyyteId, String value);

    public void editClosingDateTime(long incyyteId, Date closureDate);

    public void updatePublishPoll(String value, long incyyteId);

    public void updateUsersMandatoryInfo(User user) throws CreateUserException;

    public InCyyte checkIfInCyyteExist(String incyytecode);

    public void insertBatchAnswers(final long questionId, final List<Answer> answers, JdbcTemplate template) throws AccessException;

    public Long[] insertBatchContacts(final long userId, final List<Contact> contacts, JdbcTemplate template) throws AccessException;

    public Long[] insertNewContacts(long userId, List<Contact> contacts);

    public void addMailingList(InCyyte incyyte);

    public Long add(Object entity);

    public void addCollection(Set objs);

    public Object load(Class clz, long id);

    public long contactExistForUser(long userId, String contactEmail);

    public List<Contact> getGroupContacts(Long userId, Long groupId, JdbcTemplate template) throws DataAccessException;

    public List<User> getRegionalRecipients(String postalRegion);

    public List<CommentsModel> getPollComments(long code, long incyyteId);

    public boolean isPostResponded(long incyyteID, long memberId);

    public void insertDeletedIncyyte(Long questionId, Long userId);

    public void updateDeletedIncyyte(Long questionId);

    public boolean isIncyyteDeleted(final Integer questionId, final Integer userId);

    public boolean verifyContactalreadyMember(String cemail);

    public void insertUserDomain(long userId, String postcode, String countryCode);

    public UserDomain getUserDomain(long userId);

    public Share getShareRecord(long questionID, String contactEmail);

    public boolean updateUsersFrmPayment(PaymentModel paymentModel, long userId) throws Exception;

    public boolean updateUsersEmails(PaymentModel paymentModel, long userId) throws Exception;

    public boolean updateUserType(User user, UserType business_Sliver) throws Exception;

    void shareInCyyteToGroupContacts(InCyyte incyyte) throws Exception;
    
    List<Answer> getInCyyteAnswers(long inCyyteId) throws Exception;

	List<Long> getAlreadyVotedPolls(String email);

}
