/**
 * <p>Title: T</p>
 * <p>Description: </p>
 * This service interface provides services to the QuickStart process
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 01 Feb 2011
 */
package com.incyyte.app.service;

import com.incyyte.app.domain.*;
import com.incyyte.app.service.Exceptions.CreateInCyyteException;
import com.incyyte.app.service.Exceptions.CreateUserException;
import com.incyyte.app.service.Exceptions.InCyyteExceptions;
import com.incyyte.app.service.Exceptions.ResponderException;
import com.incyyte.app.web.model.UserContactModel;

import javax.servlet.ServletContext;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;


public interface QuickStartService {

    public Long createUserInCyyte(long userId, InCyyte incyyte) throws CreateInCyyteException;

    public void updateUsersName(String email, String firstname, String lastname);

    public void updateUsersMandatoryInfo(User user) throws CreateUserException;

    public void sendInCyyte(User user, boolean anonymous, long incyyteId, String serverURL, ServletContext servletContext);

    public void sendInCyyteToRegion(User user, boolean anonymous, long incyyteId, List<Contact> contacts, ServletContext servletContext, String sendZone);

    public boolean shareInCyyte(User user, InCyyte inCyyte, Hashtable contacts, boolean incyyteClosed) throws Exception;

    public void addXmlToInCyyte(InCyyte incyyte) throws InCyyteExceptions;

    /*
      * This service will return all the information required by the Chart Info
      * such has the InCyyte Question, the set of answers, the file uploaded with
      * this incyyte param incyyteId return InCyyte
      */
    public InCyyte initChart(long incyyteId);

    public InCyyte initChart(String incyyteCode);
    /*
      * Each individual Answer link will be tagged with a member ID (this ID is
      * mapped to the Contact & GroupID) A selected Answer, is classed as a response. This
      * service will return all responses to connected with this incyyte
      */
    public InCyyteChart updateChart(long incyyteId, long memberId, long selectedAnswerId, boolean shared) throws ResponderException;

    public InCyyteChart updatePostedChart(long incyyteId, long userId, long selectedAnswerId, String gender, String ageGroup, Date responseDate) throws ResponderException;

    public InCyyteChart updateRegionalChart(long incyyteId, long userId, long selectedAnswerId) throws ResponderException;

    public InCyyteChart getInCyyteResponse(String incyyteCode);

    public InCyyteChart getInCyyteRegionalResponse(String incyyteCode);

    public InCyyteChart getPostedInCyyteResponse(String incyyteCode);

    public boolean isContactResponded(long incyyteId, long memberId);

    public boolean isRegionalContactResponded(long incyyteId, long userId);

    public boolean isSharedContactResponded(long incyyteId, long sharedId);

    public boolean isPostResponded(long incyyteID, long memberId);

    public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType) throws InCyyteExceptions;
    
    public List<InCyyte> getUserInCyytesByUserId(long userID, String sendType, int offset, int recordsPerPage, String param, String criteria) throws InCyyteExceptions;

    public Long addInCyyteByEmails(InCyyte incyyte) throws CreateInCyyteException;

    public Long addInCyyteByGroup(InCyyte incyyte, long grpId) throws CreateInCyyteException;

    public Long addInCyyteByPosting(InCyyte incyyte) throws CreateInCyyteException;

    public Long addInCyyteByPostRegion(InCyyte incyyte) throws CreateInCyyteException;

    public Long getPollMemberId(String UserEmail, long questionID);

    public Long checkPollShared(String UserEmail, long questionID);

    public void insertDeletedIncyyte(final Integer questionId, final Integer userId);

    public boolean isIncyyteDeleted(final Integer questionId, final Integer userId);

    public boolean verifyContactalreadyMember(String cemail);

    public String getInvitationCode();

    public void insertUserDomain(long userId, String postcode, String countryCode);

    public UserDomain getUserDomain(long userId);

    void shareUserPollPageQues(User user, InCyyte inCyyte, List<Contact> contacts);
    
    void sendEmailForReportThisPoll(ReportPoll reportPoll);
    
    Long processArea(User user, InCyyte incyyte, ServletContext servletContext) throws Exception;

	public void sendMessageToPollRecipients(User user, UserContactModel usercModel, InCyyteChart chart);
	
	public void sendContactUsMessage(User user, String messageText);

	public void shareNewsLetter(User user, InCyyte inCyyte, List<Contact> contacts);
	
	List<Long> getAlreadyVotedPolls(String email);
}