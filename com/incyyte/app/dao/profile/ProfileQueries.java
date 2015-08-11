package com.incyyte.app.dao.profile;

/**
 * @author Dev1
 */
public class ProfileQueries {

    private static StringBuffer updateOpinionQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("defining_question = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer selectUserProfileQuery = new StringBuffer()
            .append("SELECT ")
            .append("userid, username, password, ")
            .append("firstname, lastname, nickname, ")
            .append("email, gender, cyyte_personal_mail, ")
            .append("secondary_pin, status, mobile, ")
            .append("cdn_file_name, ageGroup, acceptTerms, ")
            .append("user_domain.postcode, user_domain.country_code, ")
            .append("activationID, defining_question, ")
            .append("reset_pwd_flag, DOB, birthYear, tmp_location,categories,sexuality,income,upload_location, ")
            .append("occupation_master.occupation as occupation, ")
            .append("religion_master.name as religion , sn_profile_url, ")
            .append("ethnicity, ")
            .append("education_level, ")
            .append("adults_in_houseHold, ")
            .append("children_in_houseHold ")
            .append("FROM users ")
            .append("left join user_domain on users.userid = user_domain.user_id ")
            .append("left outer join occupation_master ")
            .append("on users.occupation = occupation_master.id ")
            .append("left outer join religion_master ")
            .append("on users.religion = religion_master.id ")
            .append("WHERE username = ? AND STATUS = 'ACTIVE'");

    private static StringBuffer userCountQuery = new StringBuffer(
            "SELECT COUNT(username) FROM USERS WHERE username = ? ");

    private static StringBuffer userEmailCountQuery = new StringBuffer(
            "SELECT COUNT(email) FROM USERS_EMAILS WHERE default_email = ? AND username= ?");

    private static StringBuffer updateUserNameQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("username = ? ")
            .append("WHERE userid = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateFirstNameQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("firstname = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateLastNameQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("lastname = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateCountryQuery = new StringBuffer()
            .append("UPDATE USER_DOMAIN ")
            .append("SET ")
            .append("country_code = ? ")
            .append("WHERE user_id = ? ");
            //.append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateLocationQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("tmp_location = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updatePostCodeQuery = new StringBuffer()
            .append("UPDATE USER_DOMAIN ")
            .append("SET ")
            .append("postcode = ? ")
            .append("WHERE user_id = ? ");
    
    private static StringBuffer updatePostCodeDateQuery = new StringBuffer()
		    .append("UPDATE USERS ")
		    .append("SET ")    
		    .append("postcode_date = sysdate() ")
		    .append("WHERE userid = ? ")
		    .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateMobileQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("mobile = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer deleteUserEmailsQuery = new StringBuffer()
            .append("DELETE FROM USERS_EMAILS ")
            .append("WHERE ")
            .append("default_email = ? ")
            .append("AND username = ? ");

    private static StringBuffer insertUserEmailsQuery = new StringBuffer()
            .append("INSERT INTO USERS_EMAILS ")
            .append("(email, default_email,username,fk_userid) VALUES (?, ?, ?, ?) ");

    private static StringBuffer updateGenderQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("gender = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateSexualityQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("sexuality = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateOccupationQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("occupation = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateReligionQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("religion = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer selectOccupationsQuery = new StringBuffer()
            .append("SELECT id, occupation ")
            .append("FROM OCCUPATION_MASTER ");

    private static StringBuffer selectReligionsQuery = new StringBuffer()
            .append("SELECT id, name ")
            .append("FROM ")
            .append("RELIGION_MASTER ");

    private static StringBuffer updateDobQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("dob = ? , birthYear = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateCategoriesQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("categories = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer selectCategoriesQuery = new StringBuffer(
            "SELECT categories FROM USERS WHERE username= ?");

    private static StringBuffer updateLogoUrlQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("upload_location = ? ,cdn_file_name = ? , upload_name = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer deleteLogoUrlQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("upload_location = NULL , cdn_file_name = NULL, upload_name = NULL ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer makeUnDefaultQuery = new StringBuffer()
            .append("UPDATE USERS_EMAILS ")
            .append("SET ")
            .append("default_email = ? ")
            .append("WHERE default_email = 1 AND username = ? ");

    private static StringBuffer getDefaultEmailQuery = new StringBuffer()
            .append("SELECT EMAIL FROM USERS_EMAILS ")
            .append("WHERE ")
            .append("default_email = 1 ")
            .append("AND username = ? ");

    private static StringBuffer makeDefaultQuery = new StringBuffer()
            .append("UPDATE USERS_EMAILS ")
            .append("SET ")
            .append("default_email = 1 ")
            .append("WHERE default_email = ? AND username = ? AND EMAIL <> ? ");

    private static StringBuffer userEmailQuery = new StringBuffer()
            .append("SELECT email FROM USERS_EMAILS ")
            .append("WHERE ")
            .append("username = ? ")
            .append("ORDER BY default_email ASC  ");

    private static StringBuffer updateIncomeQuery = new StringBuffer()
            .append("UPDATE USERS ")
            .append("SET ")
            .append("income = ? ")
            .append("WHERE username = ? ")
            .append("AND STATUS = 'ACTIVE' ");

    private static StringBuffer updateEthnicityQuery = new StringBuffer()
    .append("UPDATE USERS ")
    .append("SET ")
    .append("ethnicity = ? ")
    .append("WHERE username = ? ")
    .append("AND STATUS = 'ACTIVE' ");
    
    private static StringBuffer updateEducationLevelQuery = new StringBuffer()
    .append("UPDATE USERS ")
    .append("SET ")
    .append("education_level = ? ")
    .append("WHERE username = ? ")
    .append("AND STATUS = 'ACTIVE' ");
    
    private static StringBuffer updateAdultsInHouseHoldQuery = new StringBuffer()
    .append("UPDATE USERS ")
    .append("SET ")
    .append("adults_in_houseHold = ? ")
    .append("WHERE username = ? ")
    .append("AND STATUS = 'ACTIVE' ");
    
    private static StringBuffer updateChildrenInHouseHoldQuery = new StringBuffer()
    .append("UPDATE USERS ")
    .append("SET ")
    .append("children_in_houseHold = ? ")
    .append("WHERE username = ? ")
    .append("AND STATUS = 'ACTIVE' ");

    public static String getUpdateOpinionQuery() {
        return updateOpinionQuery.toString();
    }

    public static String getSelectUserProfileQuery() {
        return selectUserProfileQuery.toString();
    }

    public static String getUserCountQuery(long userId) {
        if (userId != 0) {
            return userCountQuery.toString() + "and userid <> ? ";
        } else {
            return userCountQuery.toString();
        }
    }

    public static String getUpdateUserNameQuery() {
        return updateUserNameQuery.toString();
    }

    public static String getUpdateFirstNameQuery() {
        return updateFirstNameQuery.toString();
    }

    public static String getUpdateLastNameQuery() {
        return updateLastNameQuery.toString();
    }

    public static String getUpdateCountryQuery() {
        return updateCountryQuery.toString();
    }

    public static String getUpdateLocationQuery() {
        return updateLocationQuery.toString();
    }

    public static String getUpdatePostCodeQuery() {
        return updatePostCodeQuery.toString();
    }

    public static String getUpdateMobileQuery() {
        return updateMobileQuery.toString();
    }

    public static String getDeleteUserEmailQuery() {
        return deleteUserEmailsQuery.toString();
    }

    public static String getInsertUserEmailQuery() {
        return insertUserEmailsQuery.toString();
    }

    public static String getUpdateGenderQuery() {
        return updateGenderQuery.toString();
    }

    public static String getUpdateSexualityQuery() {
        return updateSexualityQuery.toString();
    }

    public static String getUpdateOccupationQuery() {
        return updateOccupationQuery.toString();
    }

    public static String getUpdateReligionQuery() {
        return updateReligionQuery.toString();
    }

    public static String getSelectOccupationsQuery() {
        return selectOccupationsQuery.toString();
    }

    public static String getSelectReligionsQuery() {
        return selectReligionsQuery.toString();
    }

    public static String getUpdateDobQuery() {
        return updateDobQuery.toString();
    }

    public static String getUpdateCategoriesQuery() {
        return updateCategoriesQuery.toString();
    }

    public static String getSelectCategoriesQuery() {
        return selectCategoriesQuery.toString();
    }

    public static String getUpdateLogoUrlQuery() {
        return updateLogoUrlQuery.toString();
    }

    public static String getDeleteLogoUrlQuery() {
        return deleteLogoUrlQuery.toString();
    }

    public static String getMakeDefaultQuery() {
        return makeDefaultQuery.toString();
    }

    public static String getMakeUnDefaultQuery() {
        return makeUnDefaultQuery.toString();
    }

    public static String getDefaultEmailQuery() {
        return getDefaultEmailQuery.toString();
    }

    public static String getUserEmailCountQuery() {
        return userEmailCountQuery.toString();
    }

    public static String getSelectUserEmailsQuery() {
        return userEmailQuery.toString();
    }

    public static String getUpdateIncomeQuery() {
        return updateIncomeQuery.toString();
    }

	/**
	 * @return the updateEthnicityQuery
	 */
	public static String getUpdateEthnicityQuery() {
		return updateEthnicityQuery.toString();
	}

	/**
	 * @return the updateEducationLevelQuery
	 */
	public static String getUpdateEducationLevelQuery() {
		return updateEducationLevelQuery.toString();
	}

	/**
	 * @return the updateAdultsInHouseHoldQuery
	 */
	public static String getUpdateAdultsInHouseHoldQuery() {
		return updateAdultsInHouseHoldQuery.toString();
	}

	/**
	 * @return the updateChildrenInHouseHoldQuery
	 */
	public static String getUpdateChildrenInHouseHoldQuery() {
		return updateChildrenInHouseHoldQuery.toString();
	}

	/**
	 * @return the updatePostCodeDateQuery
	 */
	public static String getUpdatePostCodeDateQuery() {
		return updatePostCodeDateQuery.toString();
	}
}
