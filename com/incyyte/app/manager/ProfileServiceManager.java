/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class implements the profile services
 * This class will delegate services to the service manager
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 29 Nov 2010
 */
package com.incyyte.app.manager;

import com.incyyte.app.dao.profile.ProfileDao;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.UnavailableUserNameException;
import com.incyyte.app.service.Exceptions.UserNotFoundException;
import com.incyyte.app.service.util.IncyyteCategory;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.UserEmailEnum;
import com.incyyte.app.web.model.OccupationModel;
import com.incyyte.app.web.model.ReligionModel;
import com.incyyte.app.web.model.UserModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileServiceManager implements ProfileManager {

    /**
     * Logger for this class and subclasses
     */
    protected final Log log = LogFactory.getLog(getClass());


    private ProfileDao profileDao;

    public void setProfileDao(ProfileDao profileDao) {
        this.profileDao = profileDao;

    }

    public boolean saveOpinion(String userName, String opinion) {
        boolean updated = false;

        int updateCount = profileDao.saveOpinion(userName, opinion);

        if (updateCount > 0) {
            updated = true;
        }
        return updated;
    }

    @Override
    public UserModel getUserProfile(String userName) throws UserNotFoundException {
        UserModel userModel = null;
        try {
            userModel = profileDao.getUserProfile(userName);

            List<String> emails = profileDao.getUserEmails(userName);

            int emailSize = emails.size();

            if (emailSize == 1) {
                userModel.setSu_email(emails.get(0));
            }

            if (emailSize == 2) {
                userModel.setSu_email(emails.get(0));
                userModel.setEmail1(emails.get(1));
            }

            if (emailSize == 3) {
                userModel.setSu_email(emails.get(0));
                userModel.setEmail1(emails.get(1));
                userModel.setEmail2(emails.get(2));
            }

            String categories = userModel.getCategory();

            if (categories != null) {
                String[] catArr = categories.split(",");
                List<String> categoryList = new LinkedList<String>(Arrays.asList(catArr));
                userModel.setCategories(categoryList);
            } else {
                userModel.setCategories(new LinkedList<String>());
            }
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }
        return userModel;
    }

    @Override
    @Transactional
    public boolean saveUserName(User user, String updatedUserName) throws UnavailableUserNameException {
        boolean updated = false;
        Logger.debug("Updating username..." + updatedUserName);
        int userNameCount = profileDao.getUserCount(user.getId(), updatedUserName);
        Logger.debug("Updating username:userNameCount:" + userNameCount);
        if (userNameCount > 0) {
            updated = false;
            //	throw new UnavailableUserNameException("selected username already exists");
        } else {
            updated = true;
        }
        int updateCount = 0;
        if (updated) {
            updateCount = profileDao.saveUserName(user, updatedUserName);
        }

        if (updateCount > 0) {
            updated = true;
        } else {
            updated = false;
        }

        return updated;
    }

    @Override
    public boolean saveFirstName(String userName, String firstName) {
        boolean updated = false;

        int updateCount = profileDao.saveFirstName(userName, firstName);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean saveLastName(String userName, String lastName) {
        boolean updated = false;

        int updateCount = profileDao.saveLastName(userName, lastName);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean saveCountry(Long userId, String countryCode) {
        boolean updated = false;

        int updateCount = profileDao.saveCountry(userId, countryCode);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean saveLocation(String userName, String location) {
        boolean updated = false;

        int updateCount = profileDao.saveLocation(userName, location);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean savePostCode(Long userId, String postCode) {
        boolean updated = false;

        int updateCount = profileDao.savePostCode(userId, postCode);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean saveMobile(String userName, String mobile) {
        boolean updated = false;

        int updateCount = profileDao.saveMobile(userName, mobile);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    @Transactional
    public boolean saveEmail(User user, String email, UserEmailEnum whichEmail) {
        boolean updated = false;
        int emailNum = '0';

        switch (whichEmail) {
            case One:
                emailNum = 1;
                break;
            case Two:
                emailNum = 2;
                break;
            case Three:
                emailNum = 3;
                break;
        }
        int userEmailCount = profileDao.getUserEmailCount(email, user.getUsername(), emailNum);
        Logger.debug("userEmailCount::"+userEmailCount);
        int updateCount = 0;
        if (userEmailCount > 0) {
            if (email.isEmpty()) {
                updateCount = profileDao.deleteEmail(user.getUsername(), emailNum);
            } else {
                //TODO: Need to update the email rather than delete and re-insert
            	updateCount = profileDao.deleteEmail(user.getUsername(), emailNum);
                updateCount = profileDao.saveEmail(user, email, emailNum);
            }

        } else {
            updateCount = profileDao.saveEmail(user, email, emailNum);
        }

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean saveGender(String userName, String gender) {
        boolean updated = false;

        int updateCount = profileDao.saveGender(userName, gender);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean saveOccupation(String userName, String occupation) {
        boolean updated = false;

        int updateCount = profileDao.saveOccupation(userName, occupation);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean saveSexuality(String userName, String sexuality) {
        boolean updated = false;

        int updateCount = profileDao.saveSexuality(userName, sexuality);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean saveReligion(String userName, String religion) {
        boolean updated = false;

        int updateCount = profileDao.saveReligion(userName, religion);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public List<OccupationModel> getOccupations() {
        List<OccupationModel> occupations = profileDao.getOccupations();
        return occupations;
    }

    @Override
    public List<ReligionModel> getReligions() {
        List<ReligionModel> religions = profileDao.getReligions();
        return religions;
    }

    @Override
    public boolean saveDob(int birthDay, int birthMonth, int birthYear,
                           String userName) {
        boolean updated = false;

        Calendar cal = Calendar.getInstance();

        //Clear all fields
        cal.clear();

        cal.set(Calendar.YEAR, birthYear);
        cal.set(Calendar.MONTH, birthMonth);
        cal.set(Calendar.DATE, birthDay);

        Date birthDate = new Date(cal.getTimeInMillis());

        log.info("BirthDate generated:: " + birthDate);

        int updateCount = profileDao.saveDob(birthDate, birthYear, userName);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    @Transactional
    public boolean saveIncyyteCategory(String category,
                                       String userName) {
        boolean updated = false;

        String categories = profileDao.getIncyyteCategories(userName);

        String updatedCategories = null;

        if (categories != null && !categories.isEmpty()) {

            String[] categoriesArr = categories.split(",");

            List<String> categoriesList = new LinkedList<String>(Arrays.asList(categoriesArr));

            if (categoriesList.containsAll(Arrays.asList(IncyyteCategory.values()))) {
                updatedCategories = "ALL";
            } else {
                if (categoriesList.contains(category)) {
                    categoriesList.remove(category);
                } else {
                    categoriesList.add(category);
                }

                StringBuffer sb = new StringBuffer();

                for (int i = 0; i < categoriesList.size(); i++) {
                    sb.append(categoriesList.get(i));

                    if (i < (categoriesList.size() - 1)) {
                        sb.append(",");
                    }
                }

                updatedCategories = sb.toString();
            }
        } else {
            updatedCategories = category;
        }

        int updateCount = profileDao.saveIncyyteCategory(updatedCategories.toString(), userName);

        if (updateCount > 0) {
            updated = true;
        }

        return updated;
    }

    @Override
    public boolean saveCdnLogoUrl(UserModel userModel, String userName) {
        int updateCount = profileDao.saveCdnLogoUrl(userModel, userName);
        //Return true in case of value exists else false
        return (updateCount > 0);
    }

    @Override
    public boolean deleteCdnLogoUrl(String userName) {
        int updateCount = profileDao.deleteCdnLogoUrl(userName);
        //Return true in case of value exists else false
        return (updateCount > 0);
    }

    @Override
    @Transactional
    public boolean makeDefault(int whichEmail, String userName) {
        int updateCount = profileDao.makeDefault(whichEmail, userName);
        //Return true in case of value exists else false
        return (updateCount > 0);
    }

    @Override
    public boolean saveIncome(int income, String userName) {
        int updateCount = profileDao.saveIncome(income, userName);
        //Return true in case of value exists else false
        return (updateCount > 0);
    }

	@Override
	public boolean saveEthnicity(int ethnicity, String userName) {
		int updateCount = profileDao.saveEthnicity(ethnicity, userName);
        //Return true in case of value exists else false
        return (updateCount > 0);
	}

	@Override
	public boolean saveEducationLevel(int educationLevel, String userName) {
		int updateCount = profileDao.saveEducationLevel(educationLevel, userName);
        //Return true in case of value exists else false
        return (updateCount > 0);
	}

	@Override
	public boolean saveAdultsInHouseHold(String adultsInHouseHold, String userName) {
		int updateCount = profileDao.saveAdultsInHouseHold(adultsInHouseHold, userName);
        //Return true in case of value exists else false
        return (updateCount > 0);
	}

	@Override
	public boolean saveChildrenInHouseHold(String childrenInHouseHold, String userName) {
		int updateCount = profileDao.saveChildrenInHouseHold(childrenInHouseHold, userName);
        //Return true in case of value exists else false
        return (updateCount > 0);
	}
}