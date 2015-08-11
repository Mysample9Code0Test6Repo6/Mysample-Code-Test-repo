package com.incyyte.app.manager;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.groups.GroupDao;
import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.domain.*;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.util.InCyyteUtil;
import com.incyyte.app.web.model.UserContactModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InCyyteManagerImpl implements InCyyteManager {

    @Autowired
    private AbstractDao abstractDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ContactManager contactMgr;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private RegistrationManager registrationMgr;

    @Autowired
    private QuickStartService quickStartSrv;

    @Override
    public String processPublicPoll(User user, String incyyteId, long selectedAnswerId) throws Exception {
        //Create a contact entry (hidden contact)
        //Add the contact to the Poll group
        //Add the contact to group shared incyyte
        InCyyte incyyte = userDao.getInCyyte(Long.valueOf(incyyteId));
        long memberId = ShareInCyyte(user, incyyte);
        if (quickStartSrv.isContactResponded(Long.valueOf(incyyteId), memberId)) {
            return "<span>Oops!</span> Sorry you have already voted on this poll.";
        }
        //Create the user account
        //Add the user to the support group
        //Vote the Poll (same as vote a non member process)
        processNonMemberVote(user, false, Long.valueOf(incyyteId), memberId, selectedAnswerId);
        return "<span>Hooray!</span> Thanks for voting";
    }

    @Override
    public InCyyte processNonMemberVote(User user, boolean isShared, long incyyteId, long memberId, long selectedAnswerId) throws Exception {
        User userCreated;
        boolean exists = false;
        try {
            exists = registrationMgr.emailExists(user.getEmail());
        } catch (Exception e) {
            Logger.error("Exception:", e);
        }
        if (!exists) {
            //New signup - first time round
            registrationMgr.signUpUser(user);
            Logger.info("user::::" + user);
        }
        userCreated = registrationMgr.getUserDetails(user.getEmail(), Constants.GET_BY_MAIL);
        Logger.info("userCreated::::" + userCreated);
        InCyyte cyyte = quickStartSrv.initChart(incyyteId);
        return placeVote(isShared, incyyteId, memberId, selectedAnswerId, cyyte);
    }

    private long ShareInCyyte(User user, InCyyte incyyte) throws Exception {
        Logger.info("Create a New InCyyte." + incyyte);
        long contactId = insertContact(incyyte.getUserId(), user.getEmail());
        GroupContact groupContact = buildGroupContact("PublicPoll", incyyte.getGrpId(), contactId);
        Long groupMemberId =groupDao.getMemberId(contactId , incyyte.getGrpId());
         if (groupMemberId != null) {
            return groupMemberId;
        }

        List<GroupContact> groupContacts = new ArrayList<GroupContact>();
        groupContacts.add(groupContact);
        List<Long> groupMembers = groupDao.insertBatchGrpContacts(groupContacts);
        Logger.info("groupMembers:" + groupMembers);
        List<GroupSharedInCyyte> sharedInCyytes = InCyyteUtil.buildGroupSharedInCyyte(String.valueOf(incyyte.getUserId()), incyyte.getId(), groupMembers);
        Logger.info("sharedInCyytes:" + sharedInCyytes);
        groupDao.insertGrpSharedInCyyte(sharedInCyytes);
        Logger.info("Transaction finished ");
        return groupMembers.get(0);
        //CLOSED TXN
    }

    public long insertContact(final long userId, final String contactEmail) throws Exception {
        long contactId = userDao.contactExistForUser(userId, contactEmail);
        Logger.info(" Contact ID : " + contactId);
        if (contactId != 0) {
            return contactId;
        }
        UserContactModel contact = buildPublicContact(contactEmail);
        return contactMgr.addContact(contact, userId);
    }

    private UserContactModel buildPublicContact(String email) {
        UserContactModel contact = new UserContactModel();
        contact.setEmail(email);
        contact.setHidden("Y");
        contact.setSent_invite("N");
        contact.setSn_site("PUBLIC");
        return contact;
    }

    private GroupContact buildGroupContact(String userId, Long groupId, long contactId) {
        GroupContact groupContact = new GroupContact();
        groupContact.setContactId(contactId);
        groupContact.setGroupId(groupId);
        groupContact.setCreatedBy(userId);
        groupContact.setLastUpdatedBy(userId);
        groupContact.setRole("Member");
        return groupContact;
    }

    private InCyyte placeVote(boolean isShared, long incyyteId, long memberId, long selectedAnswerId, InCyyte cyyte) throws Exception {
        if (!((isShared && quickStartSrv.isSharedContactResponded(incyyteId, memberId)) || quickStartSrv.isContactResponded(incyyteId, memberId))) {
            Date closureDate = null;
            Calendar currentDate = null;

            if (cyyte != null && cyyte.getClosureDate() != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                closureDate = format.parse(cyyte.getClosureDate());
                currentDate = Calendar.getInstance();
                currentDate.setTime(new Date());
            }
            if (!(closureDate != null && closureDate.before(currentDate.getTime()))) {
                InCyyteChart cyyteChart = quickStartSrv.updateChart(incyyteId, memberId, selectedAnswerId, isShared);
                return cyyteChart.getIncyyte();
            }
        }
        return cyyte;
    }
}