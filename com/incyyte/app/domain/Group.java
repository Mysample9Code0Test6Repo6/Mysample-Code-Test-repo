/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class represents a group created by a user
 * For example:
 * <pre>
 * 		- student group
 * 		- class group
 * 		- my friend group
 * 		- my office group
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.domain;

import com.incyyte.app.service.util.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.*;


public class Group implements Serializable {

    /**
     * Default UID
     */
    private static final long serialVersionUID = 1L;

    private Long groupId;
    private Long userId;

    private String groupName;
    private String groupType;
    private String description;
    private String logo;
    private String postCode;
    private Boolean displayInGroupDirectory;
    private String autoJoin = "false";
    private byte[] groupImage;
    private CommonsMultipartFile file = null;

    private Contact[] selectedGroupContacts;
    private List<Long> selectedGroupContactsList;
    private List<Long> selectedEditGroupContacts;
    private Long[] selectedGroupContactsArr;
    private List<Long> selectedGroupContactsAdminList;
    private List<Long> selectedGroupIds = new ArrayList<Long>();
    private JoinGroup joinGroup;
    private int groupSize;

    public enum JoinGroup {AUTO, REQUEST}

    private String checked = null;
    private boolean polled;

    private String showAllContacts = "N";
    
	public String getShowAllContacts() {
		return showAllContacts;
	}

	public void setShowAllContacts(String showAllContacts) {
		this.showAllContacts = showAllContacts;
	}

	public boolean isPolled() {
        return polled;
    }

    public void setPolled(boolean polled) {
        this.polled = polled;
    }

    /**
     * @return the groupId
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the groupType
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * @param groupType the groupType to set
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return the postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return the displayInGroupDirectory
     */
    public Boolean getDisplayInGroupDirectory() {
        return displayInGroupDirectory;
    }

    /**
     * @param displayInGroupDirectory the displayInGroupDirectory to set
     */
    public void setDisplayInGroupDirectory(Boolean displayInGroupDirectory) {
        this.displayInGroupDirectory = displayInGroupDirectory;
    }

    /**
     * @return the autoJoin
     */
    public String getAutoJoin() {
        return autoJoin;
    }

    /**
     * @param autoJoin the autoJoin to set
     */
    public void setAutoJoin(String autoJoin) {
        this.autoJoin = autoJoin;
    }

    /**
     * @return the groupImage
     */
    public byte[] getGroupImage() {
        return groupImage;
    }

    /**
     * @param groupImage the groupImage to set
     */
    public void setGroupImage(byte[] groupImage) {
        this.groupImage = groupImage;
    }

    /**
     * @return the selectedGroupContacts
     */
    public Contact[] getSelectedGroupContacts() {
        return selectedGroupContacts;
    }

    /**
     * @param selectedGroupContacts the selectedGroupContacts to set
     */
    public void setSelectedGroupContacts(Contact[] selectedGroupContacts) {
        this.selectedGroupContacts = selectedGroupContacts;
    }

    /**
     * @return the selectedGroupContactsList
     */
    public List<Long> getSelectedGroupContactsList() {
        return selectedGroupContactsList;
    }

    /**
     * @param selectedGroupContactsList the selectedGroupContactsList to set
     */
    public void setSelectedGroupContactsList(List<Long> selectedGroupContactsList) {
        this.selectedGroupContactsList = selectedGroupContactsList;
    }

    /**
     * @return the selectedGroupIs
     */
    public List<Long> getSelectedGroupIds() {
        return selectedGroupIds;
    }

    /**
     * @param selectedGroupIds the selectedGroupIs to set
     */
    public void setSelectedGroupIds(List<Long> selectedGroupIds) {
        this.selectedGroupIds = selectedGroupIds;
    }

    /**
     * @return the joinGroup
     */
    public JoinGroup getJoinGroup() {
        return joinGroup;
    }

    /**
     * @param joinGroup the joinGroup to set
     */
    public void setJoinGroup(JoinGroup joinGroup) {
        this.joinGroup = joinGroup;
    }

    /**
     * @return the file
     */
    public CommonsMultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public void setSelectedGroupContactsAdminList(
            List<Long> selectedGroupContactsAdminList) {
        this.selectedGroupContactsAdminList = selectedGroupContactsAdminList;
    }

    public List<Long> getSelectedGroupContactsAdminList() {
        return selectedGroupContactsAdminList;
    }

    /**
     * @param groupSize the groupSize to set
     */
    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    /**
     * @return the groupSize
     */
    public int getGroupSize() {
        return groupSize;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(String checked) {
        this.checked = checked;
    }

    /**
     * @return the checked
     */
    public String getChecked() {
        return checked;
    }

    /**
     * @return the selectedGroupContactsArr
     */
    public Long[] getSelectedGroupContactsArr() {
        return selectedGroupContactsArr;
    }

    /**
     * @param selectedGroupContactsArr the selectedGroupContactsArr to set
     */
    public void setSelectedGroupContactsArr(Long[] selectedGroupContactsArr) {
        this.selectedGroupContactsArr = selectedGroupContactsArr;
    }

    /**
     * @return the selectedEditGroupContacts
     */
    public List<Long> getSelectedEditGroupContacts() {
        return selectedEditGroupContacts;
    }

    /**
     * @param selectedEditGroupContacts the selectedEditGroupContacts to set
     */
    public void setSelectedEditGroupContacts(List<Long> selectedEditGroupContacts) {
        this.selectedEditGroupContacts = selectedEditGroupContacts;
    }

    @Override
    public String toString() {
        return "Group{" +
                "showAllContacts=" + showAllContacts +
                ",groupId=" + groupId +
                ", userId=" + userId +
                ", groupName='" + groupName + '\'' +
                ", groupType='" + groupType + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", postCode='" + postCode + '\'' +
                ", displayInGroupDirectory=" + displayInGroupDirectory +
                ", autoJoin='" + autoJoin + '\'' +
                ", groupImage=" + groupImage +
                ", file=" + file +
                ", selectedGroupContacts=" + (selectedGroupContacts == null ? null : Arrays.asList(selectedGroupContacts)) +
                ", selectedGroupContactsList=" + selectedGroupContactsList +
                ", selectedEditGroupContacts=" + selectedEditGroupContacts +
                ", selectedGroupContactsArr=" + (selectedGroupContactsArr == null ? null : Arrays.asList(selectedGroupContactsArr)) +
                ", selectedGroupContactsAdminList=" + selectedGroupContactsAdminList +
                ", selectedGroupIds=" + selectedGroupIds +
                ", joinGroup=" + joinGroup +
                ", groupSize=" + groupSize +
                ", checked='" + checked + '\'' +
                ", polled=" + polled +
                '}';
    }

    public Map<String, Object> toJSONMap() {
        Map<String, Object> jsonValues = new HashMap<String, Object>();
        jsonValues.put("groupName",groupName);
        return jsonValues;
    }
}
