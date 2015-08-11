package com.incyyte.app.dao.profile;

import java.sql.Date;
import java.util.List;

import com.incyyte.app.domain.User;
import com.incyyte.app.web.model.OccupationModel;
import com.incyyte.app.web.model.PaymentModel;
import com.incyyte.app.web.model.ReligionModel;
import com.incyyte.app.web.model.UserModel;


/**
 * @author Dev1
 *
 */
public interface ProfileDao {
	
	/**
	 * @param userName
	 * @return
	 */
	public UserModel getUserProfile(String userName);
	
	/**
	 * @param userName
	 * @return
	 */
	public List<String> getUserEmails(String userName);
	
	/**
	 * @param userName
	 * @param opinion
	 * @return
	 */
	public int saveOpinion(String userName, String opinion); 
	
	/**
	 *
     * @param user
     * @param updatedUserName
     * @return
	 */
	public int saveUserName(User user, String updatedUserName);
	
	/**
	 * @param userName
	 * @param firstName
	 * @return
	 */
	public int saveFirstName(String userName, String firstName); 
	
	/**
	 * @param userName
	 * @param lastName
	 * @return
	 */
	public int saveLastName(String userName, String lastName);
	
	/**
	 * @param userName
	 * @param countryCode
	 * @return
	 */
	public int saveCountry(Long userId, String countryCode);
	
	/**
	 * @param userName
	 * @param location
	 * @return
	 */
	public int saveLocation(String userName, String location);
	
	/**
	 * @param userName
	 * @param postCode
	 * @return
	 */
	public int savePostCode(Long userId, String postCode);
	
	/**
	 * @param userName
	 * @param mobile
	 * @return
	 */
	public int saveMobile(String userName, String mobile);
	
	/**
	 * @param user
	 * @param email
	 * @param emailNum
	 * @return
	 */
	public int saveEmail(User user, String email, int emailNum);
	
	/**
	 * @param userName
	 * @param emailNum
	 * @return
	 */
	public int deleteEmail(String userName, int emailNum);
	
	/**
	 * @param userName
	 * @param gender
	 * @return
	 */
	public int saveGender(String userName, String gender); 
	
	/**
	 *
     * @param userId
     * @param userName
     * @return
	 */
	public int getUserCount(long userId, String userName);
	
	/**
	 * @param email
	 * @param userName
	 * @return
	 */
	public int getUserEmailCount(String email, String userName, int whichEmail);

	/**
	 * @param userName
	 * @param occupation
	 * @return
	 */
	public int saveOccupation(String userName, String occupation);

	/**
	 * @param userName
	 * @param sexuality
	 * @return
	 */
	public int saveSexuality(String userName, String sexuality);

	/**
	 * @param userName
	 * @param religion
	 * @return
	 */
	public int saveReligion(String userName, String religion);

	/**
	 * @return
	 */
	public List<OccupationModel> getOccupations();

	/**
	 * @return
	 */
	public List<ReligionModel> getReligions();

	/**
	 * @param birthDate
	 * @param userName
	 * @return
	 */
	public int saveDob(Date birthDate, int birthYear, String userName);
	
	/**
	 * @param userName
	 * @return
	 */
	public String getIncyyteCategories(String userName);

	/**
	 * @param category
	 * @param userName
	 * @return
	 */
	public int saveIncyyteCategory(String category, String userName);

	/**
	 * @param cdnLogoUrl
	 * @param userName
	 * @return
	 */
	public int saveCdnLogoUrl(UserModel userModel, String userName);

	/**
	 * @param userName
	 * @return
	 */
	public int deleteCdnLogoUrl(String userName);

	/**
	 * @param whichEmail
	 * @param userName
	 * @return
	 */
	public int makeDefault(int whichEmail, String userName);

	/**
	 * @param income
	 * @param userName
	 * @return
	 */
	public int saveIncome(int income, String userName);
	
	public int saveEthnicity(int ethnicity, String userName);
	public int saveEducationLevel(int educationLevel, String userName);
	public int saveAdultsInHouseHold(String adultsInHouseHold, String userName);
	public int saveChildrenInHouseHold(String childrenInHouseHold, String userName);

	public String getDefaultEmail(User user) throws Exception;
	
	public boolean  saveUsersEmailFrmPayent(User user, PaymentModel paymentModel) throws Exception;

}
