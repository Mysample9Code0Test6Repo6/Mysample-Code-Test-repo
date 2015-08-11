package com.incyyte.app.service;

import java.util.List;

import com.incyyte.app.domain.User;

import com.incyyte.app.manager.ProfileManager;
import com.incyyte.app.service.Exceptions.UnavailableUserNameException;
import com.incyyte.app.service.Exceptions.UserNotFoundException;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.UserEmailEnum;
import com.incyyte.app.web.model.OccupationModel;
import com.incyyte.app.web.model.ReligionModel;
import com.incyyte.app.web.model.UserModel;

/**
 * @author Dev1
 */
public class ProfileServiceImpl implements ProfileService {

    
    private ProfileManager profileManager;
    
    public void setProfileManager(ProfileManager profileManager){
    	this.profileManager=profileManager;
    	
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#saveOpinion(java.lang.String, java.lang.String)
      */
    @Override
    public boolean saveOpinion(String userName, String opinion) {
        return profileManager.saveOpinion(userName, opinion);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#getUserProfile(java.lang.String)
      */
    @Override
    public UserModel getUserProfile(String userName)
            throws UserNotFoundException {
        return profileManager.getUserProfile(userName);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#saveUserName(java.lang.String, java.lang.String)
      */
    @Override
    public boolean saveUserName(User user, String updatedUserName) throws UnavailableUserNameException {
        return profileManager.saveUserName(user, updatedUserName);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#saveFirstName(java.lang.String, java.lang.String)
      */
    @Override
    public boolean saveFirstName(String userName, String firstName) {
        return profileManager.saveFirstName(userName, firstName);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#saveLastName(java.lang.String, java.lang.String)
      */
    @Override
    public boolean saveLastName(String userName, String lastName) {
        return profileManager.saveLastName(userName, lastName);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#saveCountry(java.lang.String, java.lang.String)
      */
    @Override
    public boolean saveCountry(Long userId, String countryCode) {
        return profileManager.saveCountry(userId, countryCode);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#saveLocation(java.lang.String, java.lang.String)
      */
    @Override
    public boolean saveLocation(String userName, String location) {
        return profileManager.saveLocation(userName, location);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#savePostCode(java.lang.String, java.lang.String)
      */
    @Override
    public boolean savePostCode(Long userId, String postCode) {
        return profileManager.savePostCode(userId, postCode);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#saveMobile(java.lang.String, java.lang.String)
      */
    @Override
    public boolean saveMobile(String userName, String mobile) {
        return profileManager.saveMobile(userName, mobile);
    }

    /* (non-Javadoc)
      * @see com.incyyte.app.service.ProfileService#saveEmail(java.lang.String, java.lang.String)
      */
    @Override
    public boolean saveEmail(User user, String email, UserEmailEnum whichEmail) {
        return profileManager.saveEmail(user, email, whichEmail);
    }

    @Override
    public boolean saveGender(String userName, String gender) {
        return profileManager.saveGender(userName, gender);
    }

    @Override
    public boolean saveOccupation(String userName, String occupation) {
        return profileManager.saveOccupation(userName, occupation);
    }

    @Override
    public boolean saveSexuality(String userName, String sexuality) {
        return profileManager.saveSexuality(userName, sexuality);
    }

    @Override
    public boolean saveReligion(String userName, String religion) {
        return profileManager.saveReligion(userName, religion);
    }

    @Override
    public List<OccupationModel> getOccupations() {
        return profileManager.getOccupations();
    }

    @Override
    public List<ReligionModel> getReligions() {
        return profileManager.getReligions();
    }

    @Override
    public boolean saveDob(int birthDay, int birthMonth, int birthYear, String userName) {
        return profileManager.saveDob(birthDay, birthMonth, birthYear, userName);
    }

    @Override
    public boolean saveIncyyteCategory(String category,
                                       String userName) {
        return profileManager.saveIncyyteCategory(category, userName);
    }

    @Override
    public boolean saveCdnLogoUrl(UserModel userModel, String userName) {
        return profileManager.saveCdnLogoUrl(userModel, userName);
    }

    @Override
    public boolean deleteCdnLogoUrl(String userName) {
        return profileManager.deleteCdnLogoUrl(userName);
    }

    @Override
    public boolean makeDefault(int whichEmail, String userName) {
        return profileManager.makeDefault(whichEmail, userName);
    }

    @Override
    public boolean saveIncome(int income, String userName) {
        return profileManager.saveIncome(income, userName);
    }

	@Override
	public boolean saveEthnicity(int ethnicity, String userName) {
		 return profileManager.saveEthnicity(ethnicity, userName);
	}

	@Override
	public boolean saveEducationLevel(int educationLevel, String userName) {
		 return profileManager.saveEducationLevel(educationLevel, userName);
	}

	@Override
	public boolean saveAdultsInHouseHold(String adultsInHouseHold, String userName) {
		 return profileManager.saveAdultsInHouseHold(adultsInHouseHold, userName);
	}

	@Override
	public boolean saveChildrenInHouseHold(String childrenInHouseHold, String userName) {
		 return profileManager.saveChildrenInHouseHold(childrenInHouseHold, userName);
	}

}
