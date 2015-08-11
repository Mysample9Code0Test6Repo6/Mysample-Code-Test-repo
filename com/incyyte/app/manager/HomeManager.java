/**
 * <p>Title: T</p>
 * <p>Description: </p>
 * The manager interface provides access to the implementer class that
 * manages the actual process
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 30 Dec 2010
 */
package com.incyyte.app.manager;

import com.incyyte.app.domain.Dashboard;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.User;
import com.incyyte.app.web.model.CommentsModel;

import java.util.Date;
import java.util.List;

public interface HomeManager {

    Dashboard updateUserDashboard(String email);

    void scheduleClosure(long incyyteId, long groupId, Date closureDate);

    List<InCyyteChart> getMyInCyytes(String email, int offset, String type) throws Exception;
    
    List<InCyyteChart> getMyInCyytes(String email, int offset, int recordsPerPage, String param, String criteria, boolean showArchivedPolls, String type, String sendType) throws Exception;

    List<InCyyteChart> getMyPostedInCyytes(String email, int offset, String type);

    void deleteIncyyte(long incyyteId);

    void editClosingDateTime(long incyyteId, Date closureDate);

    void updatePublishPoll(String value, long incyyteId);

    List<CommentsModel> getPollComments(long code, long incyyteId);

    void addComments(CommentsModel cmntModel, User user) throws Exception;

    InCyyteChart getMyInCyyte(long inCyyteId) throws Exception;

    void updateincyyteType(long incyyteId, String value);

    List<InCyyteChart> getChartFromInCyyte(List<InCyyte> inCyytes, String type) throws Exception;

    int getCountOfIncyytes(String email, String type) throws Exception;
	
    List<InCyyteChart> getChartFromInCyyte(List<InCyyte> inCyytes, String type, boolean getResponses) throws Exception;

}
