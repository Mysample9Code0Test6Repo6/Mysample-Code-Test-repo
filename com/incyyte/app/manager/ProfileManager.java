/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This service interface provides Profile services
 * For example:
 * <pre>
 * 		create, update user profile
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 29 Nov 2010
 */
package com.incyyte.app.manager;

import java.util.List;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.UnavailableUserNameException;
import com.incyyte.app.service.Exceptions.UserNotFoundException;
import com.incyyte.app.service.util.UserEmailEnum;
import com.incyyte.app.web.model.OccupationModel;
import com.incyyte.app.web.model.ReligionModel;
import com.incyyte.app.web.model.UserModel;

public interface ProfileManager {
	
	/**
	 * @param userName
	 * @param opinion
	 * @return
	 */
	public boolean saveOpinion(String userName, String opinion);
	
	/**
	 * @param userName
	 * @return
	 * @throws UserNotFoundException
	 */
	public UserModel getUserProfile(String userName) throws UserNotFoundException;
	
	/**
	 *
     * @param user
     * @param updatedUserName
     * @return
	 */
	public boolean saveUserName(User user, String updatedUserName) throws UnavailableUserNameException;
	
	/**
	 * @param userName
	 * @param firstName
	 * @return
	 */
	public boolean saveFirstName(String userName, String firstName); 
	
	/**
	 * @param userName
	 * @param lastName
	 * @return
	 */
	public boolean saveLastName(String userName, String lastName); 
	
	/**
	 * @param userName
	 * @param lastName
	 * @return
	 */
	public boolean saveCountry(Long userId, String countryCode);
	
	/**
	 * @param userName
	 * @param lastName
	 * @return
	 */
	public boolean saveLocation(String userName, String location);
	
	/**
	 * @param userName
	 * @param lastName
	 * @return
	 */
	public boolean savePostCode(Long userId, String postCode);
	
	/**
	 * @param userName
	 * @param lastName
	 * @return
	 */
	public boolean saveMobile(String userName, String mobile);
	
	/**
	 * @param user
	 * @param email
	 * @param whichEmail 
	 * @return
	 */
	public boolean saveEmail(User user, String email, UserEmailEnum whichEmail); 
	
	/**
	 * @param userName
	 * @param gender
	 * @return
	 */
	public boolean saveGender(String userName, String gender);

	/**
	 * @param userName
	 * @param occupation
	 * @return
	 */
	public boolean saveOccupation(String userName, String occupation);

	/**
	 * @param userName
	 * @param sexuality
	 * @return
	 */
	public boolean saveSexuality(String userName, String sexuality);

	/**
	 * @param userName
	 * @param religion
	 * @return
	 */
	public boolean saveReligion(String userName, String religion);

	/**
	 * @return
	 */
	public List<OccupationModel> getOccupations();

	/**
	 * @return
	 */
	public List<ReligionModel> getReligions();

	/**
	 * @param birthDay
	 * @param birthMonth
	 * @param birthYear
	 * @param userName
	 * @return
	 */
	public boolean saveDob(int birthDay, int birthMonth, int birthYear,
			String userName);

	/**
	 * @param category
	 * @param userName
	 * @return
	 */
	public boolean saveIncyyteCategory(String category, String userName);

	/**
	 * @param userModel
	 * @param userName
	 * @return
	 */
	public boolean saveCdnLogoUrl(UserModel userModel, String userName);

	/**
	 * @param userName
	 * @return
	 */
	public boolean deleteCdnLogoUrl(String userName);

	/**
	 * @param whichEmail
	 * @param userName
	 * @return
	 */
	public boolean makeDefault(int whichEmail, String userName);

	/**
	 * @param income
	 * @param userName
	 * @return
	 */
	public boolean saveIncome(int income, String userName); 
	
	public boolean saveEthnicity(int ethnicity, String userName);
	public boolean saveEducationLevel(int educationLevel, String userName);
	public boolean saveAdultsInHouseHold(String adultsInHouseHold, String userName);
	public boolean saveChildrenInHouseHold(String childrenInHouseHold, String userName);
}
