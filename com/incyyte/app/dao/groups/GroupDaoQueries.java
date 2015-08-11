package com.incyyte.app.dao.groups;

public class GroupDaoQueries {

    private static StringBuffer validateGroupQuery = new StringBuffer()
            .append("SELECT COUNT(name) FROM GROUPS ")
            .append("WHERE fk_userid = ? AND name = ? AND active_ind = 'A' and description <> 'AUTO_GEN' ");

    private static StringBuffer addGroupQuery = new StringBuffer()
            .append("INSERT INTO GROUPS ")
            .append("(groupid,fk_userid,name,type,post_code,description,incyyte_dir_flag,auto_join,group_image,image_name,content_type,created_date,created_by,last_updated,updated_by,active_ind) ")
            .append("VALUES ")
            .append("(?,?,?,?,?,?,?,?,?,?,?,sysdate(),?,sysdate(),?,'A')");

    private static StringBuffer editGroupQuery = new StringBuffer()
            .append("UPDATE GROUPS ")
            .append("SET name = ?,type = ?,description = ?,post_code = ?,incyyte_dir_flag = ?,auto_join = ?,group_image = ?,image_name = ?,content_type = ?,last_updated = sysdate(),updated_by = ?  ")
            .append("WHERE ")
            .append("groupid = ? and fk_userid = ? AND active_ind = 'A'");

    private static StringBuffer removeGroupQuery = new StringBuffer()
            .append("UPDATE GROUPS ")
            .append("SET active_ind = 'D',last_updated = sysdate(),updated_by = ?  ")
            .append("WHERE ")
            .append("groupid = ? and fk_userid = ? AND active_ind = 'A'");

    private static StringBuffer removeIncyyteQuery = new StringBuffer()
            .append("DELETE FROM INCYYTE ")
            .append("WHERE ")
            .append("fk_groupid = ?");

    private static StringBuffer removeAllGrpContactsQuery = new StringBuffer()
            .append("DELETE FROM GRP_CONTACTS ")
            .append("WHERE ")
            .append("fk_groupid = ?");

    private static StringBuffer searchGroupsQuery = new StringBuffer()
            .append("SELECT groupid,fk_userid,name,type,post_code,incyyte_dir_flag,auto_join,group_image,description ,(select count(distinct fk_contactid) from grp_contacts where fk_groupid =groupid and active_ind='A' and fk_contactid in (select contactid from contacts where active_ind='A')) count ")
            .append("FROM groups WHERE ")
            .append("(fk_userid = ? AND active_ind = 'A' and description <> 'AUTO_GEN' ) ");

    private static StringBuffer userGroupQuery = new StringBuffer()
            .append("SELECT groupid,fk_userid,name,type,post_code,incyyte_dir_flag,auto_join,image_name,content_type,description FROM GROUPS ")
            .append("WHERE ")
            .append("(fk_userid = ? AND groupId = ? AND active_ind = 'A')");

    private static StringBuffer userContactsQuery = new StringBuffer()
            .append("SELECT contactid,fk_userid,nickname,firstname,lastname,email FROM CONTACTS ")
            .append("WHERE ")
            .append("fk_userid = ? AND active_ind = 'A' AND accept_inv = 'Y'  ");

    private static StringBuffer userGroupContactsQuery = new StringBuffer()
            .append("SELECT contactid,fk_userid,nickname,firstname,lastname,email FROM CONTACTS ")
            .append("WHERE ")
            .append("fk_userid = ? AND active_ind = 'A' AND hidden = 'N' ")
            .append("AND contactid IN (SELECT fk_contactid FROM GRP_CONTACTS WHERE fk_groupid = ?)");

    private static StringBuffer userPolledGroupContactsQuery = new StringBuffer()
            .append("SELECT contactid,fk_userid,nickname,firstname,lastname,email FROM CONTACTS ")
            .append("WHERE ")
            .append("fk_userid = ? AND active_ind = 'A' AND hidden = 'N' ")
            .append("AND contactid IN (SELECT fk_contactid FROM GRP_CONTACTS WHERE fk_groupid = ?)")
            .append("OR contactid IN (SELECT fk_contactid FROM SHARED_INCYYTE WHERE fk_original_groupid = 813)");

    private static StringBuffer searchByGroupNameQuery1 = new StringBuffer()
            .append("SELECT groupid,fk_userid,name,type,post_code,incyyte_dir_flag,auto_join,group_image,description FROM GROUPS ")
            .append("WHERE ")
            .append("(fk_userid = ? AND name like ");


    private static StringBuffer Groupnames = new StringBuffer()
            .append("SELECT name FROM GROUPS ")
            .append("WHERE ")
            .append("fk_userid = ?  and description <> 'AUTO_GEN'");


    private static StringBuffer searchByGroupNameQuery2 = new StringBuffer()
            .append(" AND active_ind = 'A' and description <> 'AUTO_GEN' )");

    private static StringBuffer searchByPostCodeQuery1 = new StringBuffer()
            .append("SELECT groupid,fk_userid,name,type,post_code,incyyte_dir_flag,auto_join,group_image,description FROM GROUPS ")
            .append("WHERE ")
            .append("(fk_userid = ? AND post_code like ");

    private static StringBuffer searchByPostCodeQuery2 = new StringBuffer()
            .append("AND active_ind = 'A' and description <> 'AUTO_GEN' )");

    private static StringBuffer getGroupId = new StringBuffer(
            "SELECT groupid FROM groups WHERE name = ? and fk_userid = ? ");

    private static StringBuffer userShareContacts = new StringBuffer(
    									" SELECT c.CONTACTID, c.FK_USERID, c.NICKNAME, c.FIRSTNAME, c.LASTNAME, c.EMAIL, c.MOBILE, " +
    									" c.ACTIVE_IND, c.CREATED_DATE, c.MODIFIED_DATE, c.CREATED_BY, c.MODIFIED_BY, c.SENT_INVITE, c.NOTE, c.STATUS, " +
    									" c.INVITATIONID, c.ACCEPT_INV, c.BLOCKED " +
    									" FROM contacts c, shared_incyyte s " +
    									" WHERE c.contactid = s.fk_contactid  " +
    									" AND  s.fk_questionid = ? " +
    									" UNION ALL " +
    									" SELECT c.CONTACTID, c.FK_USERID, c.NICKNAME, c.FIRSTNAME, c.LASTNAME, c.EMAIL, c.MOBILE, " +
    									" c.ACTIVE_IND, c.CREATED_DATE, c.MODIFIED_DATE, c.CREATED_BY, c.MODIFIED_BY, c.SENT_INVITE, c.NOTE, c.STATUS, " +
    									" c.INVITATIONID, c.ACCEPT_INV, c.BLOCKED " +
    									" FROM contacts c, grp_contacts gc, grp_shared_incyyte gsi " +
    									" WHERE c.contactid = gc.fk_contactid " +
    									" AND gc.memberid = gsi.fk_member_id " +
    									" AND gsi.fk_question_id = ? ");
    
    public static String getGroupIdQuery() {
        return getGroupId.toString();
    }

    public static String getValidateGroupQuery() {
        return validateGroupQuery.toString();
    }

    public static String getAddGroupQuery() {
        return addGroupQuery.toString();
    }

    public static String getEditGroupQuery() {
        return editGroupQuery.toString();
    }

    public static String getRemoveGroupQuery() {
        return removeGroupQuery.toString();
    }

    private static StringBuffer MembersByName = new StringBuffer("SELECT  name   FROM groups where    active_ind !='D'  and fk_userid = ?  and name IS NOT NULL   ");

    public static String getRemoveIncyyteQuery() {
        return removeIncyyteQuery.toString();
    }

    public static String getRemoveAllGrpContactsQuery() {
        return removeAllGrpContactsQuery.toString();
    }

    public static String getSearchGroupsQuery() {
        return searchGroupsQuery.toString();
    }

    public static String getUserGroupsQuery() {
        return userGroupQuery.toString();
    }

    public static String getUserContactsQuery() {
        return userContactsQuery.toString();
    }

    public static String getUserGroupContactsQuery() {
        return userGroupContactsQuery.toString();
    }

    public static String getUserPolledGroupContactsQuery() {
        return userPolledGroupContactsQuery.toString();
    }


    public static String getSearchByGroupNameQuery1() {
        return searchByGroupNameQuery1.toString();
    }

    public static String getSearchByGroupNameQuery2() {
        return searchByGroupNameQuery2.toString();
    }

    public static String getSearchByPostCodeQuery1() {
        return searchByPostCodeQuery1.toString();
    }

    public static String getSearchByPostCodeQuery2() {
        return searchByPostCodeQuery2.toString();
    }

    public static StringBuffer getGroupnames() {
        return Groupnames;
    }

    /**
     * @return the membersByName
     */
    public static StringBuffer getMembersByName() {
        return MembersByName;
    }

    private static StringBuffer hasGroupBeenPolledQuery = new StringBuffer(
            " select COUNT(*) from incyyte  " +
                    " where fk_groupid = ?  ");

    public static StringBuffer getHasGroupBeenPolledQuery() {
        return hasGroupBeenPolledQuery;
    }

    public static StringBuffer getUserShareContacts() {
        return userShareContacts;
    }
}