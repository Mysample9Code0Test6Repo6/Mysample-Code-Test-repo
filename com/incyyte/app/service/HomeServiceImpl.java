/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class implements the registration services
 * This class will delegate services to theservice manager
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
import com.incyyte.app.manager.AdminManager;
import com.incyyte.app.manager.HomeManager;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.web.model.CommentsModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeServiceImpl implements HomeService {

    HomeManager homeMgr;
    AdminManager adminMgr;

    @Override
    public Dashboard updateUserDashboard(String email) {
        return homeMgr.updateUserDashboard(email);
    }

    public void setHomeMgr(HomeManager homeMgr) {
        this.homeMgr = homeMgr;
    }

    public void setAdminMgr(AdminManager adminMgr) {
        this.adminMgr = adminMgr;
    }

    @Override
    public void scheduleClosure(long incyyteId, long groupId, Date closureDate) {
        homeMgr.scheduleClosure(incyyteId, groupId, closureDate);
    }

    @Override
    public List<InCyyteChart> getMyInCyytes(String email, int offset) throws Exception {
        return homeMgr.getMyInCyytes(email, offset, Constants.INCYYTE_SENT);
    }

    @Override
    public List<InCyyteChart> getMyInCyytes(String email, int offset, int recordsPerPage, String param, String criteria, String sendType) throws Exception {
    	boolean showArchivedPolls = false;
    	return homeMgr.getMyInCyytes(email, offset, recordsPerPage, param, criteria, showArchivedPolls, Constants.INCYYTE_SENT, sendType);
    }

    @Override
    public List<InCyyteChart> getMyPostedInCyytes(String email, int offset) {
        return homeMgr.getMyPostedInCyytes(email, offset, Constants.INCYYTE_SENT);
    }

    public List<InCyyteChart> getInCyyteResults(String email, int offset, int recordsPerPage, String param, String criteria, boolean showArchivedPolls) throws Exception {
        return homeMgr.getMyInCyytes(email, offset, recordsPerPage, param, criteria, showArchivedPolls, Constants.INCYYTE_RESULTS, null);
    }

    public List<InCyyteChart> getInCyyteResults(String email, int offset) throws Exception {
        return homeMgr.getMyInCyytes(email, offset, Constants.INCYYTE_RESULTS);
    }

    public List<InCyyteChart> getPostedInCyyteResults(String email, int offset) throws Exception {
        return homeMgr.getMyInCyytes(email, offset, Constants.INCYYTE_POST);
    }

    @Override
    public List<InCyyteChart> getAllRegionalInCyyteResults(String email, int offset) throws Exception {
        return homeMgr.getMyInCyytes(email, offset, Constants.INCYYTE_REGIONS_ALL);
    }

    public List<InCyyteChart> getAllRegionalInCyyteResults(String email, int offset, int recordsPerPage, String param, String criteria) throws Exception {
    	boolean showArchivedPolls = false;
    	return homeMgr.getMyInCyytes(email, offset, recordsPerPage, param, criteria, showArchivedPolls, Constants.INCYYTE_REGIONS_ALL, null);
    }

    @Override
    public List<InCyyteChart> getPostcodeInCyyteResults(String email, int offset) throws Exception {
        return homeMgr.getMyInCyytes(email, offset, Constants.INCYYTE_REGIONS_POSTCODE);
    }

    @Override
    public List<InCyyteChart> getCountyInCyyteResults(String email, int offset) throws Exception {
        return homeMgr.getMyInCyytes(email, offset, Constants.INCYYTE_REGIONS_COUNTY);
    }

    @Override
    public List<InCyyteChart> getRegionInCyyteResults(String email, int offset) throws Exception {
        return homeMgr.getMyInCyytes(email, offset, Constants.INCYYTE_REGIONS_REGION);
    }

    public List<Contact> getContactsEmails(String grpId) {
        List<Contact> list = new ArrayList<Contact>();
        Contact contact;
        if (grpId.equals("grp1")) {
            contact = new Contact();
            contact.setEmail("RemiOseni@gmail.com");
            list.add(contact);
        } else if (grpId.equals("grp2")) {
            contact = new Contact();
            contact.setEmail("tboboye@hotmail.com");
            list.add(contact);
        } else if (grpId.equals("grp3")) {
            contact = new Contact();
            contact.setEmail("jnnodim@cyyte.com");
            list.add(contact);
        } else if (grpId.equals("grp4")) {
            contact = new Contact();
            contact.setEmail("deleboboye@hotmail.com");
            list.add(contact);
        }
        return list;
    }

    public List<InCyyte> getInCyyteByCreatedBy(String createdby, String questionType) {
        return adminMgr.getInCyyteByCreatedBy(createdby, questionType);
    }

    @Override
    public void deleteIncyyte(long incyyteId) {
        homeMgr.deleteIncyyte(incyyteId);
    }

    @Override
    public void updateincyyteType(long incyyteId, String value) {
        homeMgr.updateincyyteType(incyyteId, value);
    }

    @Override
    public void editClosingDateTime(long incyyteId, Date closureDate) {
        homeMgr.editClosingDateTime(incyyteId, closureDate);
    }

    @Override
    public void updatePublishPoll(String value, long incyyteId) {
        homeMgr.updatePublishPoll(value, incyyteId);
    }

    @Override
    public List<CommentsModel> getPollComments(long code, long incyyteId) {
        return homeMgr.getPollComments(code, incyyteId);
    }

    @Override
    public void addComments(CommentsModel cmntModel, User user) throws Exception {
        homeMgr.addComments(cmntModel, user);
    }

    @Override
    public InCyyteChart getMyInCyyte(long inCyyteId) throws Exception {
        return homeMgr.getMyInCyyte(inCyyteId);
    }

   @Override
    public int getCountOfIncyytes(String email, String type) throws Exception {
        return homeMgr.getCountOfIncyytes(email, type);
    }
}