/**
 * <p>Title: T</p>
 * <p>Description: </p>
 * This class implements the addressLocatorManager
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 30 Dec 2010
 */
package com.incyyte.app.manager;

import com.incyyte.app.dao.login.RegisterDao;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.*;
import com.incyyte.app.manager.scheduler.ScheduleClosure;
import com.incyyte.app.service.CommentService;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.CommentsModel;
import com.incyyte.app.web.model.QuestionModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeServiceManager implements HomeManager {
    private UserDao userDaoImpl;
    private RegisterDao registerDao;

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionManager quesMgr;

    @Autowired
    private RegistrationServiceManager registrationServiceManager;

    @Autowired
    private QuickStartServiceManager quickStartServiceManager;
    
    @Value("${incyyte.notification.url}")
    private String incyyteURL;

    @Value("${website.url}")
    private String websiteURL;

    @Override
    public Dashboard updateUserDashboard(String email) {
        Dashboard dash = new Dashboard();
        dash.setIncoming(userDaoImpl.getUserDash(email, Constants.DASH_INCOMING));
        dash.setSent(userDaoImpl.getUserDash(email, Constants.DASH_SENT));
        dash.setCompleted(userDaoImpl.getUserDash(email, Constants.DASH_COMPLETED));
        dash.setPetitions(userDaoImpl.getUserDash(email, Constants.DASH_PETITIONS));
        return dash;
    }

    public void setUserDaoImpl(UserDao userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    public void setRegisterDao(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    @Override
    public void scheduleClosure(long incyyteId, long groupId, Date closureDate) {
        new ScheduleClosure(incyyteId, groupId, closureDate, null);
    }


    @Override
    public List<InCyyteChart> getMyInCyytes(String email, int offset, int recordsPerPage, String param, String criteria, boolean showArchivedPolls, String type, String sendType) throws Exception {
    	List<InCyyte> incyyteIds = userDaoImpl.getUserInCyytes(email, offset, recordsPerPage, param, criteria, showArchivedPolls, type, sendType);
        return getChartFromInCyyte(incyyteIds, type);
    }

    @Override
    public List<InCyyteChart> getMyInCyytes(String email, int offset, String type) throws Exception {
    	boolean showArchivedPolls = false;
        List<InCyyte> incyyteIds = userDaoImpl.getUserInCyytes(email, type, showArchivedPolls);
        return getChartFromInCyyte(incyyteIds, type);
    }

    @Override
    public InCyyteChart getMyInCyyte(long inCyyteId) throws Exception {
        List<Response> responses = userDaoImpl.getResponses(inCyyteId);
        InCyyte incyyte = userDaoImpl.getInCyyte(inCyyteId);
        User user = registerDao.getUser(incyyte.getUserId());
        incyyte.setSenderProfilePic(user.getProfilePicture());
        incyyte.setTotalInCyyted(incyyte.getTotalInCyyted());
        incyyte.setTotalResponded(incyyte.getTotalResponded());
        InCyyteChart chart = new InCyyteChart(incyyte, responses);
        return chart;
    }

    @Override
    public List<InCyyteChart> getMyPostedInCyytes(String email, int offset, String type) {
    	boolean showArchivedPolls = false;    	
    	List<InCyyte> incyyteIds = userDaoImpl.getUserInCyytes(email, type, showArchivedPolls);
        List<InCyyteChart> charts = new ArrayList<InCyyteChart>();
        if (incyyteIds != null && !incyyteIds.isEmpty()) {
            for (InCyyte ic : incyyteIds) {
                List<Response> responses = userDaoImpl.getPostedResponses(ic.getId());
                if (responses == null) continue;
                InCyyte incyyte = userDaoImpl.getInCyyte(ic.getId());
                incyyte.setTotalInCyyted(ic.getTotalInCyyted());
                incyyte.setTotalResponded(ic.getTotalResponded());
                InCyyteChart chart = new InCyyteChart(incyyte, responses);
                charts.add(chart);
            }
        }
        return charts;
    }


    @Override
    public void deleteIncyyte(long incyyteId) {
        userDaoImpl.deleteIncyyte(incyyteId);
    }

    @Override
    public void updateincyyteType(long incyyteId, String value) {
        userDaoImpl.updateincyyteType(incyyteId, value);
    }

    @Override
    public void editClosingDateTime(long incyyteId, Date closureDate) {
        userDaoImpl.editClosingDateTime(incyyteId, closureDate);
    }

    @Override
    public void updatePublishPoll(String value, long incyyteId) {
        userDaoImpl.updatePublishPoll(value, incyyteId);
    }

    @Override
    public List<CommentsModel> getPollComments(long code, long incyyteId) {
        return userDaoImpl.getPollComments(code, incyyteId);
    }

    @Override
    public void addComments(CommentsModel cmntModel, User user) throws Exception {
        commentService.addComment(user.getId(), Long.valueOf(cmntModel.getQuesid()), cmntModel.getComment());
        //Trigger notification to the Poll owner based on the interval of comment notification
        int commentNotifyInterval = ConfigProperties.NOTIFY_COMMENTS_INTERVAL;
        int commentNotifyLimit = ConfigProperties.NOTIFY_COMMENTS_LIMIT;
        //Get the list of comments on the Question
        List<String> comments = commentService.getPollComments(Long.valueOf(cmntModel.getQuesid()), false);
        //Get the user details who is the owner of the poll
        //Get the email address of the owner of the poll
        QuestionModel ques = quesMgr.getQuestionsdtls(cmntModel.getQuesid());
        User pollOwner = registrationServiceManager.getUser(Long.valueOf(ques.getFk_userid()));
        if (comments.size() > 0
                && ((comments.size() % commentNotifyInterval) == 0)
                && (comments.size() <= (commentNotifyInterval * commentNotifyLimit))
                && !(user.getId().equals(pollOwner.getId()))) {
            //Send a Email notification
            String  commentResultLink = "" ;
            String pollOwnerUsername = "" ;
            String pollPagename = "" ;
            if(pollOwner != null) {
                pollOwnerUsername = pollOwner.getUsername();
            }
            if(ques != null) {
            	pollPagename = ques.getPageName();
            }
            if(StringUtils.equals(ques.getSendType() , "post" )) {
            	commentResultLink =  websiteURL + pollOwnerUsername + "/" + pollPagename + ".cyt" ;
            }else if(StringUtils.equals(ques.getSendType() , "mail") || StringUtils.equals(ques.getSendType() , "area")){
            	commentResultLink =  websiteURL + incyyteURL + "?" + "code=" + ques.getViewchart_code() +"&frm=sent&actcode="+pollOwner.getActivationCode()+"&email="+pollOwner.getEmail();
            }
           ques.setLink(commentResultLink);
           quickStartServiceManager.sendCommentsNotification(pollOwner, ques);
        }
    }

    @Override
    public List<InCyyteChart> getChartFromInCyyte(List<InCyyte> inCyytes, String type) throws Exception {
    	boolean getResponses = false;
    	if (StringUtils.contains(type, Constants.INCYYTE_SENT)){
    		getResponses = true;
    	}
    	return getChartFromInCyyte(inCyytes, type, getResponses);
    }

    @Override
    public List<InCyyteChart> getChartFromInCyyte(List<InCyyte> inCyytes, String type, boolean getResponses) throws Exception {
    	Logger.debug("inCyytes::" + inCyytes);
    	List<InCyyteChart> charts = new ArrayList<InCyyteChart>();
    	if (inCyytes != null && !inCyytes.isEmpty()) {
    		for (InCyyte ic : inCyytes) {
    			InCyyte incyyte = userDaoImpl.getInCyyte(ic.getId(), true);
    			User user = registerDao.getUser(incyyte.getUserId());
    			incyyte.setSenderProfilePic(user.getProfilePicture());
    			incyyte.setTotalInCyyted(ic.getTotalInCyyted());
    			incyyte.setTotalResponded(ic.getTotalResponded());
    			
				List<Response> responses = null;
    			if(getResponses) {
    				if (StringUtils.contains(type, Constants.INCYYTE_REGIONS))
    					responses = userDaoImpl.getRegionalResponses(ic.getId());
    				else {
    					if (ic.getSendMethod() != null)
    						responses = userDaoImpl.getRegionalResponses(ic.getId());
    					else
    						responses = userDaoImpl.getResponses(ic.getId());
    				}
    			}
    			InCyyteChart chart = new InCyyteChart(incyyte, responses);
    			charts.add(chart);
    		}
    	}
    	Logger.debug("charts::" + charts);
    	return charts;

    }

    @Override
    public int getCountOfIncyytes(String email, String type) throws Exception {
    	boolean showArchivedPolls = false;
    	List<InCyyte> incyytes = userDaoImpl.getUserInCyytes(email, type, showArchivedPolls);
        return incyytes.size();
    }
}