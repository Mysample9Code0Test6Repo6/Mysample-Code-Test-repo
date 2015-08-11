/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This service interface provides registration services
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.service;

import com.incyyte.app.domain.*;
import com.incyyte.app.web.model.CommentsModel;

import java.util.Date;
import java.util.List;

public interface HomeService {
    public Dashboard updateUserDashboard(String email);

    public void scheduleClosure(long incyyteId, long groupId, Date closureDate);

    public List<InCyyteChart> getMyInCyytes(String email, int offset) throws Exception;

    public List<InCyyteChart> getMyPostedInCyytes(String email, int offset);

    public List<InCyyteChart> getInCyyteResults(String email, int offset) throws Exception;

    public List<InCyyteChart> getMyInCyytes(String email, int offset, int recordsPerPage, String param, String criteria, String sendType) throws Exception;

    public List<InCyyteChart> getInCyyteResults(String email, int offset, int recordsPerPage, String param, String criteria, boolean showArchivedPolls ) throws Exception;

    public List<InCyyteChart> getAllRegionalInCyyteResults(String email, int offset, int recordsPerPage, String param, String criteria) throws Exception;

    public List<InCyyteChart> getPostedInCyyteResults(String email, int offset) throws Exception;

    public List<InCyyteChart> getAllRegionalInCyyteResults(String email, int offset) throws Exception;

    public List<InCyyteChart> getPostcodeInCyyteResults(String email, int offset) throws Exception;

    public List<InCyyteChart> getCountyInCyyteResults(String email, int offset) throws Exception;

    public List<InCyyteChart> getRegionInCyyteResults(String email, int offset) throws Exception;

    public List<Contact> getContactsEmails(String grpId);

    public List<InCyyte> getInCyyteByCreatedBy(String createdby, String questionType);

    public void deleteIncyyte(long incyyteId);

    public void editClosingDateTime(long incyyteId, Date closureDate);

    public void updatePublishPoll(String value, long incyyteId);

    public List<CommentsModel> getPollComments(long code, long incyyteId);

    public void addComments(CommentsModel cmntModel, User user) throws Exception;

    InCyyteChart getMyInCyyte(long inCyyteId) throws Exception;

    public void updateincyyteType(long incyyteId, String value);

    int getCountOfIncyytes(String email, String type) throws Exception;
}