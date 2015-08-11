package com.incyyte.app.util;

import com.incyyte.app.domain.GroupContact;
import com.incyyte.app.domain.GroupSharedInCyyte;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.Share;
import com.incyyte.app.service.util.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupUtil {

    public static List<GroupContact> getDeleteEligibleGrpContacts(List<Long> uiRecords, List<GroupContact> dbRecords) {
        List<GroupContact> deleteRecords = new ArrayList<GroupContact>();
        boolean recordExists;
        for (GroupContact dbRecord : dbRecords) {
            recordExists = false;
            for (Long record : uiRecords) {
                if (dbRecord.getContactId().equals(record)) {
                    recordExists = true;
                    break;
                }
            }
            if (!recordExists) {
                deleteRecords.add(dbRecord);
            }
        }
        return deleteRecords;
    }

    public static List<GroupSharedInCyyte> getGroupSharedInCyytes(String user, List<Long> memberIds, List<Long> questionIds) {
        List<GroupSharedInCyyte> sharedInCyytes = new ArrayList<GroupSharedInCyyte>();
        GroupSharedInCyyte sharedInCyyte;
        Logger.debug("memberIds:" + memberIds);
        Logger.debug("questionIds:" + questionIds);
        for (Long memberId : memberIds) {
            for (Long questionId : questionIds) {
                sharedInCyyte = new GroupSharedInCyyte();
                sharedInCyyte.setFkMemberId(memberId);
                sharedInCyyte.setFkQuestionId(questionId);
                sharedInCyyte.setCreatedBy(user);
                sharedInCyyte.setLastUpdatedBy(user);
                sharedInCyytes.add(sharedInCyyte);
            }
        }
        Logger.debug("SharedInCyytes:" + sharedInCyytes);
        return sharedInCyytes;
    }

    public static Share getSharedPollFromGroupContacts(InCyyte inCyyte, List<GroupContact> groupContacts) {
        Share share = new Share();
        share.setQuestionId(inCyyte.getId());
        share.setGroupId(inCyyte.getGrpId());
        share.setOwnerUserId(inCyyte.getUserId());
        share.setSharerUserId(inCyyte.getUserId());
        share.setCreatedDate(new Date());
        share.setCreatedBy(String.valueOf(inCyyte.getUserId()));
        List<Long> contactIds = new ArrayList<Long>();
        for (GroupContact contact : groupContacts) {
            contactIds.add(contact.getContactId());
        }
        share.setSelectedShareContactsList(contactIds);
        Logger.debug("sharedContacts::" + share);
        return share;
    }
}