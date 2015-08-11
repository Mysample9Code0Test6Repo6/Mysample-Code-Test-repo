package com.incyyte.app.dao.profile;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.IncyyteProperties;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.model.OccupationModel;
import com.incyyte.app.web.model.PaymentModel;
import com.incyyte.app.web.model.ReligionModel;
import com.incyyte.app.web.model.UserModel;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * @author Dev1
 */
@Repository
public class ProfileDaoImpl implements ProfileDao {

    private AbstractDao abstractDao;

    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    /*  @Autowired
        private JdbcTemplate jdbcTemplate;
    */
    /* (non-Javadoc)
      * @see com.incyyte.app.dao.profile.ProfileDao#saveOpinion(java.lang.String, java.lang.String)
      */
    public int saveOpinion(String userName, String opinion) {
        String query = ProfileQueries.getUpdateOpinionQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(opinion);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveOpinion", query, params);
        } catch (Exception e) {
            Logger.error("unable to save opinion" ,e);
        }
        return updateCount;
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.dao.profile.ProfileDao#getUserProfile(java.lang.String)
      */
    @Override
    public UserModel getUserProfile(String userName) throws IncorrectResultSizeDataAccessException {
        String query = ProfileQueries.getSelectUserProfileQuery();
        UserModel userModel = null;
        QueryParameters params = new QueryParameters();
        params.addParam(userName);
        try {
            userModel = (UserModel) QueryHelper.doQueryForObject(abstractDao, "getUserProfile", query, params, new RowMapper<UserModel>() {
                @Override
                public UserModel mapRow(ResultSet rs, int arg1) throws SQLException {
                    UserModel userModel = new UserModel();
                    try {
                        userModel.setAc_country(rs.getString("country_code"));
                        userModel.setUsername(rs.getString("username"));
                        userModel.setActivate_act(rs.getString("activationID"));
                        userModel.setCyytePersonalEmail(rs.getString("cyyte_personal_mail"));
                        userModel.setFirstname(rs.getString("firstname"));
                        userModel.setLogin_email(rs.getString("cyyte_personal_mail"));
                        userModel.setLastname(rs.getString("lastname"));
                        userModel.setPostcode(rs.getString("postcode"));
                        userModel.setMobile(rs.getString("mobile"));
                        userModel.setOpinion(rs.getString("defining_question"));
                        userModel.setStatus(rs.getString("status"));
                        userModel.setUser_email(rs.getString("email"));
                        userModel.setPostal_area(rs.getString("tmp_location"));
                        java.sql.Date date = rs.getDate("dob");
                        if (date != null) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTimeInMillis(date.getTime());
                            userModel.setBirthDay(cal.get(Calendar.DATE));
                            userModel.setBirthMonth(cal.get(Calendar.MONTH));
                            userModel.setBirthYear(cal.get(Calendar.YEAR));
                        }else{
                        	userModel.setBirthYear(rs.getInt("birthYear"));
                        }
                        userModel.setGender(rs.getString("gender"));
                        userModel.setSexuality(rs.getString("sexuality"));
                        userModel.setCategory(rs.getString("categories"));
                        String religion = rs.getString("religion");
                        userModel.setReligion((religion == null) ? "" : religion);
                        String occupation = rs.getString("occupation");
                        userModel.setOccupation((occupation == null) ? "" : occupation);
                        userModel.setIncome(rs.getInt("income"));
                                                
                        userModel.setProfilePicture(FileManagementUtil.getFileURL(rs.getString("UPLOAD_LOCATION"), rs.getString("CDN_FILE_NAME")));
                       if(null== userModel.getProfilePicture() )
                    	   userModel.setProfilePicture(rs.getString("sn_profile_url")) ;
                       
                       userModel.setEthnicity(rs.getInt("ETHNICITY"));
                       userModel.setEducationLevel(rs.getInt("EDUCATION_LEVEL"));
                       userModel.setAdultsInHouseHold(rs.getString("ADULTS_IN_HOUSEHOLD"));
                       userModel.setChildrenInHouseHold(rs.getString("CHILDREN_IN_HOUSEHOLD"));
                       
                    } catch (Exception e) {
                        throw new SQLException("Exception:", e);
                    }
                    return userModel;
                }
            });
        } catch (Exception e) {
            Logger.error("Unable to get user profile",e);
        }
        return userModel;
    }

    @Override
    public int getUserCount(long userId, String userName) {
        String query = ProfileQueries.getUserCountQuery(userId);
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        if (userId != 0) {
            params.addParam(userName);
            params.addParam(userId);
        } else {
            params.addParam(userName);
        }
        try {
            updateCount = QueryHelper.doQueryForInt(abstractDao, "getUserCount", query, params);
        } catch (Exception e) {
            Logger.error("Unable to get count",e);
        }
        return updateCount;
    }

    @Override
    public int saveUserName(User user, String updatedUserName) {
        String query = ProfileQueries.getUpdateUserNameQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(updatedUserName);
        params.addParam(user.getId());
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveUserName", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save username",e);
        }
        return updateCount;
    }

    @Override
    public int saveFirstName(String userName, String firstName) {
        String query = ProfileQueries.getUpdateFirstNameQuery();
        QueryParameters params = new QueryParameters();
        int updateCount = 0;
        params.addParam(firstName);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveFirstName", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save firstname",e);
        }
        return updateCount;
    }

    @Override
    public int saveLastName(String userName, String lastName) {
        String query = ProfileQueries.getUpdateLastNameQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(lastName);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveLastName", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save lastname",e);
        }
        return updateCount;
    }

    @Override
    public int saveCountry(Long userId, String countryCode) {
        String query = ProfileQueries.getUpdateCountryQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(countryCode);
        params.addParam(userId);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveCountry", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save country", e);
        }
        return updateCount;
    }

    @Override
    public int saveLocation(String userName, String location) {
        String query = ProfileQueries.getUpdateLocationQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(location);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveLocation", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save location",e);
        }
        return updateCount;
    }

    @Override
    public int savePostCode(Long userId, String postCode) {
        String query = ProfileQueries.getUpdatePostCodeQuery();
        
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(postCode);
        params.addParam(userId);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "savePostCode", query, params);
            
            query = ProfileQueries.getUpdatePostCodeDateQuery();
            params = new QueryParameters();
            params.addParam(userId);
            updateCount = QueryHelper.doUpdate(abstractDao, "savePostCode", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save postcode" ,e);
        }
        return updateCount;
    }

    @Override
    public int saveMobile(String userName, String mobile) {
        String query = ProfileQueries.getUpdateMobileQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(mobile);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveMobile", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save mobile",e);
        }
        return updateCount;
    }

    @Override
    public int saveEmail(User user, String email, int emailNum) {
        String query = ProfileQueries.getInsertUserEmailQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(email);
        params.addParam(emailNum);
        params.addParam(user.getUsername());
        params.addParam(user.getId());
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveEmail", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save email",e);
        }
        return updateCount;
    }

    @Override
    public int deleteEmail(String userName, int emailNum) {
        String query = ProfileQueries.getDeleteUserEmailQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(emailNum);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "deleteEmail", query, params);
        } catch (Exception e) {
            Logger.error("Unable to deleted email",e);
        }
        return updateCount;
    }

    @Override
    public int saveGender(String userName, String gender) {
        String query = ProfileQueries.getUpdateGenderQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(gender);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveGender", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save gender" ,e);
        }
        return updateCount;
    }

    @Override
    public int saveOccupation(String userName, String occupation) {
        String query = ProfileQueries.getUpdateOccupationQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(occupation);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveOccupation", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save occupation",e);
        }
        return updateCount;
    }

    @Override
    public int saveSexuality(String userName, String sexuality) {
        String query = ProfileQueries.getUpdateSexualityQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(sexuality);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveSexuality", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save sexuality:", e);
        }
        return updateCount;
    }

    @Override
    public int saveReligion(String userName, String religion) {
        String query = ProfileQueries.getUpdateReligionQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(religion);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveReligion", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save religion",e);
        }
        return updateCount;
    }

    @Override
    public List<OccupationModel> getOccupations() {
        String query = ProfileQueries.getSelectOccupationsQuery();
        JdbcTemplate template = abstractDao.getJdbcTemplate("getOccupations");
        List<OccupationModel> occupations = null;
        try {
            occupations = template.query(query, new RowMapper<OccupationModel>() {
                @Override
                public OccupationModel mapRow(ResultSet rs, int arg1) throws SQLException {
                    OccupationModel model = new OccupationModel();
                    model.setId(rs.getString("id"));
                    model.setOccupation(rs.getString("occupation"));
                    return model;
                }
            });
        } catch (Exception e) {
            Logger.error("Unable to get occupations" ,e);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "getOccupations");
        }
        return occupations;
    }

    @Override
    public List<ReligionModel> getReligions() {
        String query = ProfileQueries.getSelectReligionsQuery();
        JdbcTemplate template = abstractDao.getJdbcTemplate("getReligions");
        List<ReligionModel> religions = null;
        try {
            religions = template.query(query, new RowMapper<ReligionModel>() {

                @Override
                public ReligionModel mapRow(ResultSet rs, int arg1)
                        throws SQLException {
                    ReligionModel model = new ReligionModel();
                    model.setId(rs.getString("id"));
                    model.setName(rs.getString("name"));

                    return model;
                }

            });
        } catch (Exception e) {

            Logger.error("Unable to get religions",e);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "getReligions");
        }
        return religions;
    }

    @Override
    public int saveDob(Date birthDate,int birthYear, String userName) {
        String query = ProfileQueries.getUpdateDobQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(birthDate);
        params.addParam(birthYear);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveDob", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save dob ", e);
        }
        return updateCount;
    }

    @Override
    public String getIncyyteCategories(String userName) {
        String query = ProfileQueries.getSelectCategoriesQuery();
        String categories = null;
        QueryParameters params = new QueryParameters();
        params.addParam(userName);
        try {
            categories = (String) QueryHelper.doQueryForObject(abstractDao, "getIncyyteCategories", query, params, String.class);
        } catch (Exception e) {
            Logger.error("Unable to get incyyte categories " , e);
        }
        return categories;
    }

    @Override
    public int saveIncyyteCategory(String category, String userName) {
        String query = ProfileQueries.getUpdateCategoriesQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(category);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveIncyyteCategory", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save  incyyte categories ", e);
        }
        return updateCount;
    }

    @Override
    public int saveCdnLogoUrl(UserModel userModel, String userName) {
        String query = ProfileQueries.getUpdateLogoUrlQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        ConfigManager icfg = ConfigManager.getInstance();
        try {
            icfg.setIncyyteProperties(new IncyyteProperties(null));
            params.addParam(userModel.getUploadLocation());
            params.addParam(userModel.getCdnFileName());
            params.addParam(userModel.getUploadName());
            params.addParam(userName);
            updateCount = QueryHelper.doUpdate(abstractDao, "saveCdnLogoUrl", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save   CdnLogoUrl " ,e);
        }
        return updateCount;
    }

    @Override
    public int deleteCdnLogoUrl(String userName) {
        String query = ProfileQueries.getDeleteLogoUrlQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "deleteCdnLogoUrl", query, params);
        } catch (Exception e) {
            Logger.error("Unable to delete   CdnLogoUrl " , e);
        }
        return updateCount;
    }

    @Override
    public int makeDefault(int whichEmail, String userName) {
        String query = ProfileQueries.getDefaultEmailQuery();
        int updateCount = 0;
        try {
            QueryParameters params = new QueryParameters();
            params.addParam(userName);
            String defaultEmail = (String) QueryHelper.doQueryForObject(abstractDao, "makeDefault", query, params, new RowMapper<String>() {

                @Override
                public String mapRow(ResultSet rs, int arg1) throws SQLException {
                    return rs.getString("email");
                }
            });
            query = ProfileQueries.getMakeUnDefaultQuery();
            QueryParameters params1 = new QueryParameters();
            params1.addParam(whichEmail);
            params1.addParam(userName);
            updateCount = QueryHelper.doUpdate(abstractDao, "makeDefault", query, params1);

            QueryParameters params2 = new QueryParameters();
            params2.addParam(whichEmail);
            params2.addParam(userName);
            params2.addParam(defaultEmail);
            query = ProfileQueries.getMakeDefaultQuery();
            updateCount = QueryHelper.doUpdate(abstractDao, "makeDefault", query, params2);
        } catch (Exception e) {
            Logger.error("Unable to delete   CdnLogoUrl " ,e);
        }
        return updateCount;
    }

    @Override
    public int getUserEmailCount(String email, String userName, int whichEmail) {
        String query = ProfileQueries.getUserEmailCountQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(whichEmail);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doQueryForInt(abstractDao, "getUserEmailCount", query, params);
        } catch (Exception e) {
            Logger.error("Unable to get email count",e);
        }
        return updateCount;
    }

    @Override
    public List<String> getUserEmails(String userName) {
        String query = ProfileQueries.getSelectUserEmailsQuery();
        List<String> emails = null;
        QueryParameters params = new QueryParameters();
        params.addParam(userName);
        try {
            emails = QueryHelper.doQuery(abstractDao, "getUserEmails", query, params, new RowMapper<String>() {

                @Override
                public String mapRow(ResultSet rs, int arg1)
                        throws SQLException {

                    return rs.getString("email");
                }

            });
        } catch (Exception e) {

            Logger.error("Unable to get emails" ,e);
        }
        return emails;
    }

    @Override
    public int saveIncome(int income, String userName) {
        String query = ProfileQueries.getUpdateIncomeQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(income);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveIncome", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save income " ,e);
        }
        return updateCount;
    }
    
    
    @Override
    public int saveEthnicity(int ethnicity, String userName) {
        String query = ProfileQueries.getUpdateEthnicityQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(ethnicity);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveEthnicity", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save ethnicity ",e);
        }
        return updateCount;
    }
    
    @Override
    public int saveEducationLevel(int educationLevel, String userName) {
        String query = ProfileQueries.getUpdateEducationLevelQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(educationLevel);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveEducationLevel", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save educationLevel ",e);
        }
        return updateCount;
    }
    
    @Override
    public int saveAdultsInHouseHold(String adultsInHouseHold, String userName) {
        String query = ProfileQueries.getUpdateAdultsInHouseHoldQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(adultsInHouseHold);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "saveAdultsInHouseHold", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save adultsInHouseHold " , e);
        }
        return updateCount;
    }
    
    @Override    
    public int saveChildrenInHouseHold(String childrenInHouseHold, String userName) {
        String query = ProfileQueries.getUpdateChildrenInHouseHoldQuery();
        int updateCount = 0;
        QueryParameters params = new QueryParameters();
        params.addParam(childrenInHouseHold);
        params.addParam(userName);
        try {
            updateCount = QueryHelper.doUpdate(abstractDao, "savChildrenInHouseHold", query, params);
        } catch (Exception e) {
            Logger.error("Unable to save childrenInHouseHold " ,e );
        }
        return updateCount;
    }

	@Override
	public String getDefaultEmail(User user) throws Exception {
		
    	String getDefaultEmailForPayment = "SELECT email FROM USERS_EMAILS WHERE default_email = 1 AND fk_userid = ? ";
    	JdbcTemplate template = null;
    	String email = null;
    	QueryParameters updateParam = new QueryParameters();
    	updateParam.addParam(user.getId());
    	try {
    		template = abstractDao.getJdbcTemplate("getDefaultEmail");
    	   email = (String) QueryHelper.doQueryForObject(template, getDefaultEmailForPayment, updateParam, String.class);
    		Logger.debug("email::"+email);
    		return email;
    	} catch (Exception e) {
    		Logger.error("getDefaultEmail failed ", e);
    		throw e;
    	} finally {
    		abstractDao.freedbpool(template.getDataSource(), "getDefaultEmail");
    	}		
	}

	@Override
	public boolean saveUsersEmailFrmPayent(User user, PaymentModel paymentModel) throws Exception {

		String updateUsers_emailsFrmPayment = "update users_emails set email = ? where  fk_userid = ? AND default_email = 1";
		JdbcTemplate template = null;
		QueryParameters updateParam = new QueryParameters();
		updateParam.addParam(paymentModel.getEmail());
		updateParam.addParam(user.getId());
		try {
			template = abstractDao.getJdbcTemplate("updateUserEmailsFrmPayment");
			QueryHelper.doUpdate(template, updateUsers_emailsFrmPayment, updateParam);
			return true;
		} catch (Exception e) {
			Logger.error("Updateing the users emails : Failed " , e);
			throw e;
		} finally {
			abstractDao.freedbpool(template.getDataSource(), "updateUserEmailsFrmPayment");
		}
	}
}
