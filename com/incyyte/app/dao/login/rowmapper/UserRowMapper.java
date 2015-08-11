package com.incyyte.app.dao.login.rowmapper;

import com.incyyte.app.domain.User;
import com.incyyte.app.util.FileManagementUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timi Boboye
 *         <p/>
 *         A RowMapper implementation for mapping to User Registered.
 *         <p/>
 *         Note: This RowMapper is reusable i.e. is used in different contexts; therefore
 *         please be cautious if modifying this class!
 */

@SuppressWarnings("rawtypes")
public class UserRowMapper implements RowMapper {
    /**
     *
     */
    public UserRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    public Object mapRow(ResultSet rs, int currentRow) throws SQLException {
        User user = new User();
        try {
            user.setId(rs.getLong("USERID"));
            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setFirstname(rs.getString("FIRSTNAME"));
            user.setLastname(rs.getString("LASTNAME"));
            user.setNickname(rs.getString("NICKNAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setGender(rs.getString("GENDER"));
            user.setCyytePersonalEmail(rs.getString("CYYTE_PERSONAL_MAIL"));
            user.setSecondaryPin(rs.getString("SECONDARY_PIN"));
            user.setStatus(rs.getString("STATUS"));
            user.setMobile(rs.getString("MOBILE"));
            user.setFileName(rs.getString("UPLOAD_NAME"));
            user.setAgeGroup(rs.getString("AGEGROUP"));

            user.setAcceptTerms(rs.getString("ACCEPTTERMS"));
            user.setPostalCodeArea(rs.getString("POSTCODE"));
            user.setCountryCode(rs.getString("COUNTRY_CODE"));
            user.setCountyName(rs.getString("COUNTY_NAME"));
            user.setActivationCode(rs.getString("ACTIVATIONID"));
            user.setDefiningQuestion(rs.getString("DEFINING_QUESTION"));
            user.setResetPwdFlag(rs.getString("RESET_PWD_FLAG"));
            user.setProfilePicture(FileManagementUtil.getFileURL(rs.getString("UPLOAD_LOCATION"), rs.getString("CDN_FILE_NAME")));
            user.setCreatedDate(rs.getDate("CREATED_DATE"));
            user.setPostCodeDate(rs.getDate("POSTCODE_DATE"));
            user.setUserType(rs.getString("USER_TYPE"));
            if(null!=rs.getString("sn_profile_url") )
            	user.setProfilePicture(rs.getString("sn_profile_url")) ; 
            user.setBirthYear(rs.getInt("BIRTHYEAR"));
            
            user.setOccupation(rs.getInt("OCCUPATION"));
            user.setIncome(rs.getInt("INCOME"));
           
            user.setEthnicity(rs.getInt("ETHNICITY"));
            user.setEducationLevel(rs.getInt("EDUCATION_LEVEL"));
            user.setAdultsInHouseHold(rs.getString("ADULTS_IN_HOUSEHOLD"));
            user.setChildrenInHouseHold(rs.getString("CHILDREN_IN_HOUSEHOLD"));
            user.setDisplayCheckList(rs.getString("DISPLAY_CHECKLIST"));
        } catch (Exception e) {
            throw new SQLException("Exception:", e);
        }
        return (user);
    }
} //end, 
