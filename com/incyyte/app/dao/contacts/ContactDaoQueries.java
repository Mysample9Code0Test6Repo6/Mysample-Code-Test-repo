package com.incyyte.app.dao.contacts;

public class ContactDaoQueries {

    private static StringBuffer addContact = new StringBuffer(
            "INSERT INTO CONTACTS " +
                    "(CONTACTID, FK_USERID, NICKNAME, FIRSTNAME, LASTNAME, EMAIL, MOBILE, CREATED_DATE, MODIFIED_DATE, CREATED_BY, MODIFIED_BY, SENT_INVITE, NOTE, INVITATIONID, ACCEPT_INV,SN_ID,SN_FROM,STATUS,HIDDEN) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, sysdate(), sysdate(), ?, ?, ?, ?, ?, ?,?,?,?,?)"
    );

    private static StringBuffer updateContact = new StringBuffer(
            "UPDATE contacts  SET " +
                    "nickname =?, firstname = ?, lastname = ?, email = ?, mobile = ?,  modified_date = SYSDATE(),  modified_by =?, sent_invite = ?, note = ? " +
                    "WHERE contactid = ? and fk_userid = ?  ");

    private static StringBuffer removeContact = new StringBuffer(
            "UPDATE contacts  SET  active_ind='D' , modified_date = SYSDATE(),  modified_by =?  WHERE contactid in (?)  and fk_userid = ?  ");


    private static StringBuffer contactprofileimg = new StringBuffer(
            "SELECT  UPLOAD_LOCATION " +
                    ",UPLOAD_NAME " +
                    ",CDN_FILE_NAME " +
                    " FROM users  where email = ?  ");

    private static StringBuffer UserContacts = new StringBuffer(
            " SELECT contacts.contactid,contacts.fk_userid,ifnull(contacts.nickname, users.nickname) nickname, ifnull(contacts.firstname, users.firstname) firstname, ifnull(contacts.lastname, users.lastname) lastname, " +
                    " user_domain.postcode, contacts.email, contacts.mobile, contacts.sent_invite, contacts.note,contacts.status,contacts.accept_inv,contacts.postcode,contacts.blocked, " +
                    " contacts.ActivationDate, contacts.invitationid, users.username,users.userid, users.created_date, users.defining_question, contacts.active_ind, users.upload_location, users.cdn_file_name " +
                    " FROM contacts " +
                    " left outer join users on contacts.email = users.email " +
                    " left join user_domain on users.userid = user_domain.user_id " +
                    " where contacts.fk_userid = ? " +
                    " and contacts.active_ind != 'D' ");
    
    private static StringBuffer UserAndGrpContacts = new StringBuffer(
            " SELECT contacts.contactid,contacts.fk_userid,ifnull(contacts.nickname, users.nickname) nickname, ifnull(contacts.firstname, users.firstname) firstname, ifnull(contacts.lastname, users.lastname) lastname, " +
                    " user_domain.postcode, contacts.email, contacts.mobile, contacts.sent_invite, contacts.note,contacts.status,contacts.accept_inv,contacts.postcode,contacts.blocked, " +
                    " contacts.ActivationDate, contacts.invitationid, users.username,users.userid, users.created_date, users.defining_question, contacts.active_ind, users.upload_location, users.cdn_file_name, gc.memberid " +
                    " FROM contacts " +
                    " left outer join users on contacts.email = users.email " +
                    " left outer join grp_contacts gc on contacts.contactid = gc.fk_contactid and gc.fk_groupid = ? " +
                    " left join user_domain on users.userid = user_domain.user_id " +
                    " where contacts.fk_userid = ? " +
                    " and contacts.active_ind != 'D' ");
    
    private static StringBuffer EditContacts = new StringBuffer(
    		" SELECT contacts.contactid, contacts.fk_userid, ifnull(contacts.nickname, users.nickname) nickname, " +
                     " ifnull(contacts.firstname, users.firstname) firstname, " +
                     " ifnull(contacts.lastname, users.lastname) lastname, " +
                     " contacts.email, contacts.mobile, contacts.sent_invite, contacts.note,contacts.status, "+
                     " contacts.accept_inv,contacts.postcode, contacts.blocked, " +
                     " contacts.ActivationDate, contacts.invitationid, users.username,users.userid, " +
                     " users.created_date, users.defining_question, contacts.active_ind, grp_contacts.fk_contactid, " +
                     " users.upload_location, users.cdn_file_name " +
                     " FROM contacts " +
                     " left outer join users on contacts.email = users.email " +
                     " STRAIGHT_JOIN grp_contacts on contacts.contactid = grp_contacts.fk_contactid " +
                     " where contacts.contactid = grp_contacts.fk_contactid " +
                     " and grp_contacts.active_ind = 'A' " +
                     " and grp_contacts.fk_groupid = ? ");

    private static StringBuffer UserContactsCount = new StringBuffer(" select count(1) from contacts where fk_userid = ? " +
            " and sent_invite = 'Y' and accept_inv = 'Y' and status='M' and active_ind!='D' ");
    private static StringBuffer noOfRecords = new StringBuffer(" select count(1) from contacts where fk_userid = ? " +
            " and active_ind!='D' ");

    private static StringBuffer UserContactsforsn = new StringBuffer(
            "SELECT contactid, fk_userid, nickname, firstname, lastname, email, mobile,  sent_invite, note ,status ,accept_inv , postcode,blocked,ActivationDate, invitationid" +
                    " FROM contacts where fk_userid = ? ");


    private static StringBuffer MembersByName = new StringBuffer(
            "SELECT  firstname   FROM contacts where    active_ind !='D'  and fk_userid = ?  and firstname IS NOT NULL   ");

    private static StringBuffer getContactExist = new StringBuffer(
            "SELECT  count(*)   FROM contacts where fk_userid = ?  and email=? ");

    public static StringBuffer getContactExist() {
        return getContactExist;
    }

    private static StringBuffer updateContactExisted = new StringBuffer(
            " update contacts set active_ind= 'A', sent_invite='Y',accept_inv ='Y',modified_date = SYSDATE() where fk_userid = ?  and email=? ");

    public static StringBuffer updateContact() {
        return updateContactExisted;
    }

    private static StringBuffer InvitedContacts = new StringBuffer(
            "SELECT  email" +
                    " FROM contacts where fk_userid = ?  and  active_ind !='D' and sent_invite='Y' and accept_inv !='Y' ");

    private static StringBuffer blockMultiple = new StringBuffer(
            "UPDATE contacts  SET  blocked='Y' , modified_date = SYSDATE(),  modified_by =?  WHERE fk_userid = ?  and  contactid in  ");

    private static StringBuffer inviteMultiple = new StringBuffer(
            "UPDATE contacts  SET  sent_invite='Y',  active_ind='A' , modified_date = SYSDATE(),  modified_by =?  WHERE fk_userid = ?  and  email in  ");

    private static StringBuffer DeleteMultiple = new StringBuffer(
            "UPDATE contacts  SET  active_ind='D' , modified_date = SYSDATE(),  modified_by =?  WHERE  fk_userid = ? and  contactid in  ");

    private static StringBuffer usercontactsize = new StringBuffer(
            "SELECT count(*) " +
                    " FROM contacts where fk_userid = ?  and  active_ind !='D'");

    private static StringBuffer groupContactSize = new StringBuffer(
    		"SELECT count(*) from grp_contacts where fk_groupid = ? and active_ind !='D'");

    private static StringBuffer Contactsdtls = new StringBuffer(
            " SELECT c.*, u.username, u.upload_location, u.cdn_file_name " +
                    "	FROM contacts  c " +
                    " join users u on  c.fk_userid=u.userid " +
                    " where contactid = ? ");

    private static StringBuffer Contactsdtlsbyemail = new StringBuffer(
            " SELECT c.*, u.username ,u.upload_location, u.cdn_file_name" +
                    " FROM contacts c " +
                    " join users u on c.fk_userid = u.userid " +
                    " where c.email = ? ");

    private static StringBuffer addContactsToGroup = new StringBuffer(
            "");

    private static StringBuffer blockContact = new StringBuffer(
            "UPDATE contacts  SET  blocked='Y' , modified_date = SYSDATE(),  modified_by =?  WHERE contactid = ? and fk_userid = ? and accept_inv = 'Y' ");

    private static StringBuffer unblockContact = new StringBuffer(
            "UPDATE contacts  SET  blocked='N' , modified_date = SYSDATE(),  modified_by =?  WHERE contactid = ? and fk_userid = ?  ");

    private static StringBuffer inviteContacts = new StringBuffer(
            "UPDATE contacts  " +
                    "SET  sent_invite='Y' , active_ind = 'A', modified_date = SYSDATE(),  modified_by =?, invitationid =?, accept_inv =?  " +
                    "WHERE contactid in ( ? )  and fk_userid = ?  ");

    private static StringBuffer acceptInvitation = new StringBuffer(
            "UPDATE contacts    " +
                    "SET  modified_date = SYSDATE(), accept_inv ='Y'   " +
                    "WHERE invitationid =?   ");
    private static StringBuffer failedInviteMail = new StringBuffer(
            "UPDATE contacts    " +
                    "SET  modified_date = SYSDATE(), sent_invite ='N'   " +
                    "WHERE contactid =?   ");

    private static StringBuffer searchContacts = new StringBuffer(
            "SELECT contactid, fk_userid, nickname, firstname, lastname, email, mobile,  sent_invite, note ,status ,accept_inv , postcode,blocked,ActivationDate ,invitationid  " +
                    "FROM CONTACTS WHERE FK_USERID = ? ");


    private static StringBuffer searchContactsize = new StringBuffer(
            "SELECT count(contactid)   " +
                    "FROM CONTACTS WHERE FK_USERID = ? ");

    private static StringBuffer importContacts = new StringBuffer(
            "");

    private static StringBuffer uniqueInvitationCodeQuery = new StringBuffer(
            "SELECT COUNT(invitationid) FROM CONTACTS WHERE invitationid=? ");

    private static StringBuffer reactivateDeletedContact = new StringBuffer(
            " UPDATE Contacts " +
                    " SET ACTIVE_IND = 'A' " +
                    " WHERE contactid = ?");

    private static StringBuffer emailexists = new StringBuffer(
            "SELECT COUNT(email) FROM users WHERE email=? ");

    private static StringBuffer contactemail = new StringBuffer(
            "SELECT email FROM contacts WHERE contactid = ? ");


    private static StringBuffer contactsdtlsByInv = new StringBuffer(
            "SELECT contactid, fk_userid, email, active_ind, sent_invite, note, status, invitationid, accept_inv, blocked " +
                    "FROM contacts WHERE invitationid =?");

    private static StringBuffer userProfileQuery = new StringBuffer(
            "SELECT userid, username, firstname, lastname, nickname, " +
                    "email, gender, status, mobile, photo, ageGroup, defining_question " +
                    "FROM users " +
                    "WHERE status='ACTIVE' " +
                    "AND email = ? ");

    private static StringBuffer contactExistForUser = new StringBuffer(
            " SELECT contactid FROM contacts  " +
                    " WHERE email=? " +
                    " AND fk_userid =? ");


    public static StringBuffer getAcceptInitationQuery() {
        return acceptInvitation;
    }


    public static StringBuffer getContactsdtlsByInv() {
        return contactsdtlsByInv;
    }


    public static StringBuffer getUniqueInvitationCodeQuery() {
        return uniqueInvitationCodeQuery;
    }

    public static StringBuffer getReactivateDeletedContact() {
        return reactivateDeletedContact;
    }


    public static StringBuffer getAddContact() {
        return addContact;
    }

    public static StringBuffer getUpdateContact() {
        return updateContact;
    }

    public static StringBuffer getRemoveContact() {
        return removeContact;
    }

    public static StringBuffer getUserContacts() {
        return UserContacts;
    }
    
    public static StringBuffer getUseAndGrpContacts() {
        return UserAndGrpContacts;
    }
    
    
    
    public static StringBuffer getEditContactsList(){
    	return EditContacts;
    }

    public static StringBuffer getUserContactsCount() {
        return UserContactsCount;
    }

    public static StringBuffer getAddContactsToGroup() {
        return addContactsToGroup;
    }

    public static StringBuffer getBlockContact() {
        return blockContact;
    }

    public static StringBuffer getInviteContacts() {
        return inviteContacts;
    }

    public static StringBuffer getSearchContacts() {
        return searchContacts;
    }

    public static StringBuffer getImportContacts() {
        return importContacts;
    }

    public static void setContactsdtls(StringBuffer contactsdtls) {
        Contactsdtls = contactsdtls;
    }

    public static StringBuffer getContactsdtls() {
        return Contactsdtls;
    }

    public static StringBuffer getUserProfileQuery() {
        return userProfileQuery;
    }


    public static void setFailedInviteMail(StringBuffer failedInviteMail) {
        ContactDaoQueries.failedInviteMail = failedInviteMail;
    }


    public static StringBuffer getFailedInviteMail() {
        return failedInviteMail;
    }


    public static void setUnblockContact(StringBuffer unblockContact) {
        ContactDaoQueries.unblockContact = unblockContact;
    }


    public static StringBuffer getUnblockContact() {
        return unblockContact;
    }


    public static void setUsercontactsize(StringBuffer usercontactsize) {
        ContactDaoQueries.usercontactsize = usercontactsize;
    }


    public static StringBuffer getUsercontactsize() {
        return usercontactsize;
    }
    
    public static StringBuffer getGroupContactsSize() {
        return groupContactSize;
    }
    


    public static void setSearchContactsize(StringBuffer searchContactsize) {
        ContactDaoQueries.searchContactsize = searchContactsize;
    }


    public static StringBuffer getSearchContactsize() {
        return searchContactsize;
    }


    /**
     * @param membersByName the membersByName to set
     */
    public static void setMembersByName(StringBuffer membersByName) {
        MembersByName = membersByName;
    }


    /**
     * @return the membersByName
     */
    public static StringBuffer getMembersByName() {
        return MembersByName;
    }


    /**
     * @param invitedContacts the invitedContacts to set
     */
    public static void setInvitedContacts(StringBuffer invitedContacts) {
        InvitedContacts = invitedContacts;
    }


    /**
     * @return the invitedContacts
     */
    public static StringBuffer getInvitedContacts() {
        return InvitedContacts;
    }


    /**
     * @param blockMultiple the blockMultiple to set
     */
    public static void setBlockMultiple(StringBuffer blockMultiple) {
        ContactDaoQueries.blockMultiple = blockMultiple;
    }


    /**
     * @return the blockMultiple
     */
    public static StringBuffer getBlockMultiple() {
        return blockMultiple;
    }


    /**
     * @param deleteMultiple the deleteMultiple to set
     */
    public static void setDeleteMultiple(StringBuffer deleteMultiple) {
        DeleteMultiple = deleteMultiple;
    }


    /**
     * @return the deleteMultiple
     */
    public static StringBuffer getDeleteMultiple() {
        return DeleteMultiple;
    }


    /**
     * @param inviteMultiple the inviteMultiple to set
     */
    public static void setInviteMultiple(StringBuffer inviteMultiple) {
        ContactDaoQueries.inviteMultiple = inviteMultiple;
    }


    /**
     * @return the inviteMultiple
     */
    public static StringBuffer getInviteMultiple() {
        return inviteMultiple;
    }


    /**
     * @param emailexists the emailexists to set
     */
    public static void setEmailexists(StringBuffer emailexists) {
        ContactDaoQueries.emailexists = emailexists;
    }


    /**
     * @return the emailexists
     */
    public static StringBuffer getEmailexists() {
        return emailexists;
    }


    /**
     * @param contactemail the contactemail to set
     */
    public static void setContactemail(StringBuffer contactemail) {
        ContactDaoQueries.contactemail = contactemail;
    }


    /**
     * @return the contactemail
     */
    public static StringBuffer getContactemail() {
        return contactemail;
    }


    /**
     * @param contactsdtlsbyemail the contactsdtlsbyemail to set
     */
    public static void setContactsdtlsbyemail(StringBuffer contactsdtlsbyemail) {
        Contactsdtlsbyemail = contactsdtlsbyemail;
    }


    /**
     * @return the contactsdtlsbyemail
     */
    public static StringBuffer getContactsdtlsbyemail() {
        return Contactsdtlsbyemail;
    }


    public static StringBuffer getContactExistForUserQuery() {
        return contactExistForUser;
    }


    public static void setContactprofileimg(StringBuffer contactprofileimg) {
        ContactDaoQueries.contactprofileimg = contactprofileimg;
    }


    public static StringBuffer getContactprofileimg() {
        return contactprofileimg;
    }


    public static StringBuffer getUserContactsforsn() {
        return UserContactsforsn;
    }


    public static void setUserContactsforsn(StringBuffer userContactsforsn) {
        UserContactsforsn = userContactsforsn;
    }


    public static StringBuffer getnoOfRecords() {
        return noOfRecords;
    }


}
