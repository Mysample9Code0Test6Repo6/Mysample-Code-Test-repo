package com.incyyte.app.web.controller.profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.incyyte.app.service.util.IncyyteCategory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.incyyte.app.domain.User;
import com.incyyte.app.service.ProfileService;
import com.incyyte.app.service.Exceptions.UserNotFoundException;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.service.util.UserEmailEnum;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.controller.BaseContoller;
import com.incyyte.app.web.model.OccupationModel;
import com.incyyte.app.web.model.Photo;
import com.incyyte.app.web.model.ReligionModel;
import com.incyyte.app.web.model.UserModel;

/**
 * @author Dev1
 */
@Controller
// @RequestMapping("/profile")
public class UserProfileController extends BaseContoller {

	/**
	 * Logger for this class and subclasses
	 */
	private Long IMAGE_MAX_SIZE = 2097152L;

	@Autowired
	private RefData referenceData;

	@Autowired
	private ProfileService userProfileService;

	@RequestMapping("/editProfile")
	public String editProfilePage(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session, Model model)
			throws UserNotFoundException {
		Logger.debug("Edit user profile...");

		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("profile controller::" + user);
		userModel = userProfileService.getUserProfile(user.getUsername());

		Logger.debug("Username: " + userModel.getUsername());
		Logger.debug("fname: " + userModel.getFirstname());
		Logger.debug("lname: " + userModel.getLastname());

		model.addAttribute("editProfileForm", userModel);

		return "settings/profile_edit";
	}

	@RequestMapping(value = "/uploadlogo", method = RequestMethod.POST)
	@ResponseBody
	public String uploadLogo(HttpServletRequest request,
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, SessionStatus status, HttpSession session,
			Model model) throws Exception {

		Logger.debug("Uploading logo...");
		Logger.debug("userModel::" + userModel);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("user::" + user);
		Logger.debug("userModel.getUploadedLogo()::" + userModel.getUploadedLogo());
		Logger.debug("userModel.getSearchedFileNameProfile()::" + userModel.getSearchedFileNameProfile());
		Logger.debug("userModel.getUploadedLogo()::" + userModel.getUploadedLogo());
		if (!userModel.getUploadedLogo().isEmpty()
				|| StringUtils.isNotBlank(userModel.getSearchedFileNameProfile())) {
			CommonsMultipartFile multipartFile = userModel.getUploadedLogo();
			if (multipartFile != null
					&& multipartFile.getSize() > 0
					&& multipartFile.getSize() < IMAGE_MAX_SIZE
					&& StringUtils.isEmpty(userModel.getSearchedFileNameProfile())) {
				Logger.debug("Uploading logo:: " + multipartFile.getOriginalFilename());

				String remoteFile = generateFileName(multipartFile.getOriginalFilename());
				String type = getType(multipartFile.getContentType());
				String storageURL = FileManagementUtil.uploadFile(type, remoteFile, multipartFile, null);
				userModel.setProfilePicture(type);
				userModel.setUploadLocation(type);
				userModel.setCdnFileName(remoteFile);
				userModel.setUploadName(multipartFile.getOriginalFilename());
				boolean updatedProfileImage = userProfileService.saveCdnLogoUrl(userModel, user.getUsername());
				if (updatedProfileImage) {
					user.setFileName(remoteFile);
					user.setProfilePicture(storageURL);
				}
				model.addAttribute("editProfileForm", userModel);
				return "settings/profile_edit";
			} else if (StringUtils.isNotBlank(userModel.getSearchedFileNameProfile())) {
				Logger.debug("userModel:" + userModel);

				// Downloads the file and keeps in temp location
				URL url = new URL(userModel.getSearchedFileURLProfile());
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + userModel.getSearchedFileNameProfile();
				File file = new File(path);
				FileUtils.copyURLToFile(url, file);
				// Build photo object for uploading the image
				Photo searchedImage = new Photo();
				searchedImage.setData(new FileInputStream(file));
				searchedImage.setFileName(file.getName());
				String fileType = FileManagementUtil.getMimeType(userModel.getSearchedFileURLProfile());
				searchedImage.setContentType(fileType);
				searchedImage.setContainerName(fileType);
				searchedImage.setSize(file.getTotalSpace());
				Logger.debug("%Searched Image%" + searchedImage);

				String remoteFile = generateFileName(searchedImage.getFileName());
				String type = getType(searchedImage.getContentType());
				searchedImage.setFileName(remoteFile);
				searchedImage.setContentType(type);
				String uploadURL = FileManagementUtil.uploadFile(type, remoteFile, null, searchedImage);
				Logger.debug("remoteFile url - " + uploadURL);
				Logger.debug("userPollPageModel- " + userModel);

				userModel.setProfilePicture(type);
				userModel.setUploadLocation(type);
				userModel.setCdnFileName(remoteFile);
				userModel.setUploadName(userModel.getUploadName());
				boolean updatedProfileImage = userProfileService.saveCdnLogoUrl(userModel, user.getUsername());
				if (updatedProfileImage) {
					user.setFileName(remoteFile);
					user.setProfilePicture(uploadURL);
				}
				model.addAttribute("editProfileForm", userModel);
				Logger.debug("userModel::after uploading from search"
						+ userModel);
				return "settings/profile_edit";
			} else {
				Logger.error("%%%%%%%%%%%%%%% uploadMultipartFile - upload is empty or too big");
			}
		}
		return "failure";
	}

	@RequestMapping(value = "/deletelogo", method = RequestMethod.POST)
	@ResponseBody
	public String deleteLogo(HttpServletRequest request,
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, SessionStatus status, HttpSession session,
			Model model) throws Exception {
		Logger.debug("deleting logo..." + userModel.toString());
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		Logger.debug("deleting logo..." + String.valueOf(user.getFileName()));

		FileManagementUtil.deleteRemoteFile("image", user.getFileName());
		boolean deletedProfileImage = userProfileService.deleteCdnLogoUrl(user.getUsername());
		userModel.setProfilePicture(null);
		if (deletedProfileImage) {
			user.setProfilePicture(null);
		}
		model.addAttribute("editProfileForm", userModel);
		return "settings/profile_edit";
	}

	@Value("${opinion.success}")
	private String opinionSuccess;

	@Value("${opinion.error}")
	private String opinionError;

	@RequestMapping(value = "/saveopinion", method = RequestMethod.POST)
	@ResponseBody
	public String saveOpinion(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {

		String opinion = userModel.getOpinion();
		Logger.debug("Updating opinion..." + opinion);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		boolean opinionUpdated = userProfileService.saveOpinion(user.getUsername(), opinion);
		if (opinionUpdated) {
			return opinionSuccess;
		}

		return opinionError;

	}

	@Value("${updateuser.success}")
	private String updateSuccess;

	@Value("${update.error}")
	private String userUpdateError;

	@Value("${update.err}")
	private String userUpdateErr;

	@RequestMapping(value = "/saveusername.cyt", method = RequestMethod.POST)
	@ResponseBody
	public String saveUserName(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating username..." + userModel.getUsername());
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String updatedUserName = userModel.getUsername();
		if (updatedUserName == null)
			return userUpdateErr;

		boolean userNameUpdated = userProfileService.saveUserName(user, updatedUserName);
		if (userNameUpdated) {
			user.setUsername(userModel.getUsername());
			session.setAttribute(SessionKeys.LOGIN_USER, user);
			return updateSuccess;
		}
		return userUpdateError;
	}

	@Value("${firstname.success}")
	private String updateFirstName;
	@Value("${firstname.error}")
	private String firstNameUpdateError;
	@Value("${firstname.err}")
	private String firstNameUpdateErr;

	@RequestMapping(value = "/savefirstname", method = RequestMethod.POST)
	@ResponseBody
	public String saveFirstName(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		String firstName = userModel.getFirstname();
		if (firstName == null) {
			return firstNameUpdateErr;
		}
		Logger.debug("Updating firstname..." + firstName);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		boolean firstNameUpdated = userProfileService.saveFirstName(user.getUsername(), firstName);
		if (firstNameUpdated) {
			user.setFirstname(userModel.getFirstname());
			session.setAttribute(SessionKeys.LOGIN_USER, user);
			return updateFirstName;
		}
		return firstNameUpdateError;
	}

	@Value("${lastname.success}")
	private String updateLaststName;
	@Value("${lastname.error}")
	private String lastNameUpdateError;

	@Value("${lastname.err}")
	private String lastNameUpdateErr;

	@RequestMapping(value = "/savelastname", method = RequestMethod.POST)
	@ResponseBody
	public String saveLastName(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating lastname...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String lastName = userModel.getLastname();
		boolean lastNameUpdated = userProfileService.saveLastName(
				user.getUsername(), lastName);

		if (lastName == null) {
			return lastNameUpdateErr;

		}
		if (lastNameUpdated) {
			user.setLastname(userModel.getLastname());
			session.setAttribute(SessionKeys.LOGIN_USER, user);
			return updateLaststName;
		}

		return lastNameUpdateError;
	}

	@Value("${countryCode.success}")
	private String updateCountryCode;

	@Value("${countryCode.error}")
	private String countryCodeUpdateError;

	@Value("${countryCode.err}")
	private String countryCodeUpdateErr;

	@RequestMapping(value = "/savecountry", method = RequestMethod.POST)
	@ResponseBody
	public String saveCountry(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating country...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String countryCode = userModel.getCountry();
		if (countryCode == null) {
			return countryCodeUpdateErr;
		}
		boolean countryCodeUpdated = userProfileService.saveCountry(
				user.getId(), countryCode);

		if (countryCodeUpdated) {
			return updateCountryCode;
		}
		return countryCodeUpdateError;
	}

	@Value("${location.success}")
	private String updateLocation;
	@Value("${country.error}")
	private String countryUpdateErr;
	@Value("${country.err}")
	private String locationUpdateErr;

	@RequestMapping(value = "/savelocation", method = RequestMethod.POST)
	@ResponseBody
	public String saveLocation(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating location...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String location = userModel.getPostal_area();
		if (location == null) {
			return locationUpdateErr;
		}
		boolean locationUpdated = userProfileService.saveLocation(
				user.getUsername(), location);

		if (locationUpdated) {
			return updateLocation;
		}
		return countryUpdateErr;

	}

	@Value("${postcode.success}")
	private String updatePostcode;
	@Value("${postcode.error}")
	private String postcodeUpdateError;
	@Value("${postcode.err}")
	private String postcodeUpdateErr;
	@Value("${postcode.lockedErr}")
	private String postcodeLockedErr;

	@RequestMapping(value = "/savepostcode", method = RequestMethod.POST)
	@ResponseBody
	public String savePostCode(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating postcode...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String postCode = userModel.getPostcode();
		long postCodeDate = verifyPostCodeDate(user.getPostCodeDate());
		Logger.debug("postcode createdDate::::" + postCodeDate);
		if (postCode == null) {
			return postcodeUpdateErr;
		}
		if (postCodeDate >= 90) {
			Logger.debug("compare postcodedate ::::" + postCodeDate);
			boolean postCodeUpdated = userProfileService.savePostCode(
					user.getId(), postCode);
			if (postCodeUpdated) {
				user.setPostalCodeArea(postCode);
				user.setPostCodeDate(new Date());
				session.setAttribute(SessionKeys.LOGIN_USER, user);
				return updatePostcode;
			}
		} else {
			return postcodeLockedErr;
		}
		return postcodeUpdateError;
		// return new ModelAndView("redirect:edit.cyt","editProfileForm",
		// userModel) ;
	}

	private long verifyPostCodeDate(Date postCodeDate) {
		Date currentDate = new Date();
		if (postCodeDate != null) {
			long diffInDays = (currentDate.getTime() - postCodeDate.getTime())
					/ (24 * 60 * 60 * 1000);
			Logger.debug("postcode diffInDays::::" + diffInDays);
			return diffInDays;
		} else {
			return 91;// we are returning a value 91 to considered as more than
						// 3 months and allow postcode update
		}

	}

	@Value("${mobile.error}")
	private String mobileUpdateError;

	@Value("${mobile.success}")
	private String updateMobile;

	@Value("${mobile.err}")
	private String mobileUpdateErr;

	@RequestMapping(value = "/savemobile", method = RequestMethod.POST)
	@ResponseBody
	public String saveMobile(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating mobile...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String mobile = userModel.getMobile();
		if (mobile == null) {
			return mobileUpdateErr;
		}
		boolean mobileUpdated = userProfileService.saveMobile(
				user.getUsername(), mobile);

		if (mobileUpdated) {
			user.setMobile(userModel.getMobile());
			session.setAttribute(SessionKeys.LOGIN_USER, user);
			return updateMobile;
		}
		return mobileUpdateError;

	}

	@Value("${email.success}")
	private String updateEmail;

	@Value("${email.err}")
	private String emailUpdateErr;

	@Value("${email.error}")
	private String emailUpdateError;

	@RequestMapping(value = "/saveemail1", method = RequestMethod.POST)
	@ResponseBody
	public String saveEmail1(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating email1...............");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String email1 = userModel.getSu_email();
		if (email1 == null) {

			return emailUpdateError;

		}
		// Don't allow to change primary email
		// TODO add error message
		/*
		 * if (email1.equals(user.getEmail())) { return "success"; }
		 */
		if (email1.equals(userModel.getEmail1())
				|| email1.equals(userModel.getEmail2())) {
			return emailUpdateErr;
		}
		try {
			boolean emailUpdated = userProfileService.saveEmail(user, email1,
					UserEmailEnum.One);
			if (emailUpdated) {
				return updateEmail;
			}
			return emailUpdateErr;
		} catch (Exception e) {
			return emailUpdateErr;
		}
	}

	@RequestMapping(value = "/saveemail2", method = RequestMethod.POST)
	@ResponseBody
	public String saveEmail2(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating email2...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String email2 = userModel.getEmail1();
		if (email2 == null) {

			return emailUpdateError;

		}
		// Don't allow to change primary email
		// TODO add error message
		if (email2.equals(userModel.getSu_email())
				|| email2.equals(userModel.getEmail2())) {
			return emailUpdateErr;
		}
		try {
			Logger.debug("Inside try email2...");
			boolean emailUpdated = userProfileService.saveEmail(user, email2,
					UserEmailEnum.Two);
			Logger.debug("After insert..." + emailUpdated);
			/*
			 * if (Logger.isDebugEnabled()) { Logger.debug("Email2 :: " + email2
			 * + "updated successfully" + emailUpdated); }
			 */

			if (emailUpdated) {
				return updateEmail;
			}
			return emailUpdateErr;

		} catch (Exception e) {
			return emailUpdateErr;
		}
		// return new ModelAndView("redirect:edit.cyt", "editProfileForm",
		// userModel);
	}

	@RequestMapping(value = "/saveemail3", method = RequestMethod.POST)
	@ResponseBody
	public String saveEmail3(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating email3...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String email3 = userModel.getEmail2();
		if (email3 == null) {
			return emailUpdateError;

		}
		// Don't allow to change primary email
		// TODO add error message
		if (email3.equals(user.getEmail())
				|| email3.equals(userModel.getEmail1())) {
			// return new ModelAndView("redirect:edit.cyt", "editProfileForm",
			// userModel);
			return emailUpdateErr;
		}

		try {
			Logger.debug("Inside try email3...");
			boolean emailUpdated = userProfileService.saveEmail(user, email3,
					UserEmailEnum.Three);

			/*
			 * if (Logger.isDebugEnabled()) { Logger.debug("Email3 :: " + email3
			 * +"updated successfully" + emailUpdated); }
			 */
			if (emailUpdated) {
				return updateEmail;
			}
			return emailUpdateErr;
		} catch (Exception e) {
			return emailUpdateErr;
		}
		// return new ModelAndView("redirect:edit.cyt", "editProfileForm",
		// userModel);
	}

	@RequestMapping(value = "/makedefault", method = RequestMethod.POST)
	@ResponseBody
	public String makeDefault(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating default email...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		int whichEmail = userModel.getMakeDefault();
		Logger.debug(" whichEmail..." + whichEmail);
		boolean emailUpdated = userProfileService.makeDefault(whichEmail,
				user.getUsername());

		if (emailUpdated) {

			return updateEmail;
		}
		return emailUpdateErr;
		// return new ModelAndView("redirect:edit.cyt", "editProfileForm",
		// userModel);
	}

	@Value("${gender.success}")
	private String updateGender;

	@Value("${gender.err}")
	private String genderUpdateErr;

	@RequestMapping(value = "/savegender", method = RequestMethod.POST)
	@ResponseBody
	public String saveGender(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating gender...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String gender = userModel.getGender();

		boolean genderUpdated = userProfileService.saveGender(
				user.getUsername(), gender);

		if (genderUpdated) {
			return updateGender;
		}
		return genderUpdateErr;
	}

	@Value("${occupation.success}")
	private String updateoccupation;

	@Value("${occupation.error}")
	private String occupationUpdateError;

	@Value("${occupation.err}")
	private String ocuupationUpdateErr;

	@RequestMapping(value = "/saveoccupation", method = RequestMethod.POST)
	@ResponseBody
	public String saveOccupation(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session,
			HttpServletRequest request) {
		Logger.debug("Updating occupation...");
		String occId = request.getParameter("occId");
		Logger.debug("occId:: " + occId);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String occupation = userModel.getOccupation();
		if (occupation == null) {
			return ocuupationUpdateErr;
		}
		boolean occupationUpdated = userProfileService.saveOccupation(
				user.getUsername(), occId);

		if (occupationUpdated) {
			return updateoccupation;

		}

		return occupationUpdateError;
	}

	@Value("${religion.success}")
	private String updatereligion;

	@Value("${religion.error}")
	private String religionUpdateError;

	@Value("${religion.err}")
	private String religionUpdateErr;

	@RequestMapping(value = "/savereligion", method = RequestMethod.POST)
	@ResponseBody
	public String saveReligion(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session,
			HttpServletRequest request) {
		Logger.debug("Updating religion...");
		String religionId = request.getParameter("religionId");
		Logger.debug("religionId:: " + religionId);
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String religion = userModel.getReligion();
		if (religion == null) {
			return religionUpdateErr;
		}
		boolean religionUpdated = userProfileService.saveReligion(
				user.getUsername(), religionId);

		if (religionUpdated) {
			return updatereligion;
		}

		return religionUpdateError;
	}

	@Value("${sexuality.success}")
	private String updateSexuality;

	@Value("${sexuality.error}")
	private String sexualityUpdateError;

	@RequestMapping(value = "/savesexuality", method = RequestMethod.POST)
	@ResponseBody
	public String saveSexuality(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating sexuality...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String sexuality = userModel.getSexuality();

		boolean sexualityUpdated = userProfileService.saveSexuality(
				user.getUsername(), sexuality);

		if (sexualityUpdated) {
			return updateSexuality;
		}

		return sexualityUpdateError;
	}

	@Value("${dob.success}")
	private String dobUpdate;

	@Value("${dob.error}")
	private String dobUpdateError;

	@RequestMapping(value = "/savedob", method = RequestMethod.POST)
	@ResponseBody
	public String saveDob(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating dob...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		int birthDay = userModel.getBirthDay();
		int birthMonth = userModel.getBirthMonth();
		int birthYear = userModel.getBirthYear();

		Logger.debug("BirthDay:: " + birthDay);
		Logger.debug("BirthMonth:: " + birthMonth);
		Logger.debug("BirthYear:: " + birthYear);

		boolean dobUpdated = userProfileService.saveDob(birthDay, birthMonth,
				birthYear, user.getUsername());

		if (dobUpdated) {
			return dobUpdate;
		}
		return dobUpdateError;
	}

	@Value("${category.success}")
	private String categoryUpdate;

	@Value("${category.error}")
	private String categoryUpdateError;

	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	@ResponseBody
	public String saveIncyyteCategories(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating categories...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		IncyyteCategory category = userModel.getIncyyteCategory();

		Logger.debug("Updating category:: " + category);

		boolean updated = userProfileService.saveIncyyteCategory(
				category.toString(), user.getUsername());

		if (updated) {
			return categoryUpdate;
		}
		return categoryUpdateError;
	}

	@Value("${income.success}")
	private String incomeUpdate;

	@Value("${income.error}")
	private String incomeUpdateError;

	@RequestMapping(value = "/saveincome", method = RequestMethod.POST)
	@ResponseBody
	public String saveIncome(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating income...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		int income = userModel.getIncome();

		Logger.debug("Updating income:: " + income);

		boolean updated = userProfileService.saveIncome(income,
				user.getUsername());

		if (updated) {
			return incomeUpdate;
		}
		return incomeUpdateError;
	}

	@Value("${ethnicity.success}")
	private String ethnicityUpdate;

	@Value("${ethnicity.error}")
	private String ethnicityUpdateError;

	@RequestMapping(value = "/saveEthnicity", method = RequestMethod.POST)
	@ResponseBody
	public String saveEthnicity(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating ethnicity...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		int ethnicity = userModel.getEthnicity();

		Logger.debug("Updating ethnicity:: " + ethnicity);

		boolean updated = userProfileService.saveEthnicity(ethnicity,
				user.getUsername());

		if (updated) {
			return ethnicityUpdate;
		}
		return ethnicityUpdateError;
	}

	@Value("${educationLevel.success}")
	private String educationLevelUpdate;

	@Value("${educationLevel.error}")
	private String educationLevelUpdateError;

	@RequestMapping(value = "/saveEducationLevel", method = RequestMethod.POST)
	@ResponseBody
	public String saveEducationLevel(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating educationLevel...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		int educationLevel = userModel.getEducationLevel();

		Logger.debug("Updating educationLevel:: " + educationLevel);

		boolean updated = userProfileService.saveEducationLevel(educationLevel,
				user.getUsername());

		if (updated) {
			return educationLevelUpdate;
		}
		return educationLevelUpdateError;
	}

	@Value("${adultsInHouseHold.success}")
	private String adultsInHouseHoldUpdate;

	@Value("${adultsInHouseHold.error}")
	private String adultsInHouseHoldUpdateError;

	@RequestMapping(value = "/saveAdultsInHouseHold", method = RequestMethod.POST)
	@ResponseBody
	public String saveAdultsInHouseHold(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating adultsInHouseHold...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String adultsInHouseHold = userModel.getAdultsInHouseHold();

		Logger.debug("Updating adultsInHouseHold:: " + adultsInHouseHold);

		boolean updated = userProfileService.saveAdultsInHouseHold(
				adultsInHouseHold, user.getUsername());

		if (updated) {
			return adultsInHouseHoldUpdate;
		}
		return adultsInHouseHoldUpdateError;
	}

	@Value("${childrenInHouseHold.success}")
	private String childrenInHouseHoldUpdate;

	@Value("${childrenInHouseHold.error}")
	private String childrenInHouseHoldUpdateError;

	@RequestMapping(value = "/saveChildrenInHouseHold", method = RequestMethod.POST)
	@ResponseBody
	public String saveChildrenInHouseHold(
			@ModelAttribute("editProfileForm") UserModel userModel,
			BindingResult result, HttpSession session) {
		Logger.debug("Updating childrenInHouseHold...");
		User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
		String childrenInHouseHold = userModel.getChildrenInHouseHold();

		Logger.debug("Updating childrenInHouseHold:: " + childrenInHouseHold);

		boolean updated = userProfileService.saveChildrenInHouseHold(
				childrenInHouseHold, user.getUsername());

		if (updated) {
			return childrenInHouseHoldUpdate;
		}
		return childrenInHouseHoldUpdateError;
	}

	@ModelAttribute("genders")
	public Map<String, String> populateGenderList() {
		return referenceData.getGender();
	}

	@ModelAttribute("sexualities")
	public Map<String, String> populateSexualityList() {
		return referenceData.getSexualities();
	}

	@ModelAttribute("ethnicities")
	public Map<String, String> populateEthnicitiesList() {
		return referenceData.getEthnicity();
	}

	@ModelAttribute("educationLevels")
	public Map<String, String> populateEducationLevelsList() {
		return referenceData.getEducation_level();
	}

	@ModelAttribute("adultsInHouseHolds")
	public Map<String, String> populateAdultsInHouseHoldsList() {
		return referenceData.getAdults_in_houseHold();
	}

	@ModelAttribute("childrenInHouseHolds")
	public Map<String, String> populateChildrenInHouseHoldsList() {
		return referenceData.getChildren_in_houseHold();
	}

	@RequestMapping(value = "/occupations", method = RequestMethod.GET, headers = "Accept=*/*")
	public void getOccupations(HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException {
		Logger.debug("Got the occupations!!!");
		List<OccupationModel> occupations = userProfileService.getOccupations();

		JSONArray jsonArray = new JSONArray();
		for (OccupationModel occupation : occupations) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("id", occupation.getId());
			jsonObject.put("occupation", occupation.getOccupation());

			jsonArray.put(jsonObject);
		}
		JSONObject jsonObjectMain = new JSONObject();
		jsonObjectMain.put("occupations", jsonArray);

		response.setContentType("application/json");
		response.getWriter().print(jsonObjectMain);
	}

	@RequestMapping(value = "/religions", method = RequestMethod.GET, headers = "Accept=*/*")
	public void getReligions(HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException {
		Logger.debug("Got the religions!!!");
		List<ReligionModel> religions = userProfileService.getReligions();

		JSONArray jsonArray = new JSONArray();
		for (ReligionModel religion : religions) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("id", religion.getId());
			jsonObject.put("name", religion.getName());

			jsonArray.put(jsonObject);
		}
		JSONObject jsonObjectMain = new JSONObject();
		jsonObjectMain.put("religions", jsonArray);

		response.setContentType("application/json");
		response.getWriter().print(jsonObjectMain);
	}

	@RequestMapping(value = "/incomes", method = RequestMethod.GET, headers = "Accept=*/*")
	public void getIncomes(HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException {
		Logger.debug("Got the incomes!!!");
		Map<String, String> incomes = referenceData.getIncomes();

		JSONArray jsonArray = new JSONArray();
		for (Map.Entry<String, String> entry : incomes.entrySet()) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("id", entry.getKey());
			jsonObject.put("income", entry.getValue());

			jsonArray.put(jsonObject);
		}

		JSONObject jsonObjectMain = new JSONObject();
		jsonObjectMain.put("incomes", jsonArray);

		response.setContentType("application/json");
		response.getWriter().print(jsonObjectMain);
	}

	@RequestMapping(value = "/birthyears", method = RequestMethod.GET, headers = "Accept=*/*")
	public void getBirthYear(HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException {
		Logger.debug("Got the birthYear!!!");

		Calendar calendar = new GregorianCalendar();
		int ageLimit = calendar.get(Calendar.YEAR)
				- referenceData.getBirthYearLimit();

		JSONArray jsonArray = new JSONArray();
		for (int i = 1900; i < ageLimit; i++) {

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("id", String.valueOf(i));
			jsonObject.put("birthYear", String.valueOf(i));

			jsonArray.put(jsonObject);
		}

		JSONObject jsonObjectMain = new JSONObject();
		jsonObjectMain.put("birthyears", jsonArray);

		response.setContentType("application/json");
		response.getWriter().print(jsonObjectMain);
	}

	@RequestMapping(value = "/countries", method = RequestMethod.GET, headers = "Accept=*/*")
	public void getCountries(HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException {
		Logger.debug("Got the countries!!!");
		Map<String, String> countries = referenceData.getCountries();

		JSONArray jsonArray = new JSONArray();
		for (Map.Entry<String, String> entry : countries.entrySet()) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("id", entry.getKey());
			jsonObject.put("country", entry.getValue());

			jsonArray.put(jsonObject);
		}

		JSONObject jsonObjectMain = new JSONObject();
		jsonObjectMain.put("countries", jsonArray);

		response.setContentType("application/json");
		response.getWriter().print(jsonObjectMain);
	}

	@ModelAttribute("currentYear")
	public int currentYear() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR);
	}

	@ModelAttribute("months")
	public Map<String, String> months() {
		return referenceData.getMonths();
	}

	@ModelAttribute("incomes")
	public Map<String, String> incomes() {
		return referenceData.getIncomes();
	}

	@ModelAttribute("countries")
	public Map<String, String> getCountries() {
		return referenceData.getCountries();
	}

	@ModelAttribute("birthYearLimit")
	public int birthYearLimit() {
		Calendar calendar = new GregorianCalendar();
		int ageLimit = calendar.get(Calendar.YEAR) - referenceData.getBirthYearLimit();
		return ageLimit;
	}
}
