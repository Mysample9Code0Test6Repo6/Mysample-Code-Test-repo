/**
 *
 */
package com.incyyte.app.web.controller.dashboard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.InCyyteChart;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.Exceptions.InCyyteExceptions;
import com.incyyte.app.service.GroupService;
import com.incyyte.app.service.HomeService;
import com.incyyte.app.service.ProfileService;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.RefData;
import com.incyyte.app.web.SessionKeys;
import com.incyyte.app.web.model.MandatoryInfoModel;
import com.incyyte.app.web.model.UserContactModel;
import com.incyyte.app.web.model.UserContactlist;

/**
 * @author RemiOseni
 */
public class DashboardBasePage implements Constants {

    final static int OFFSET = 10;

    @Autowired
    private HomeService homeSrv;

    @Autowired
    private QuickStartService quickStartSrv;
    
    @Autowired
	private ProfileService userProfileService;
    
    @Autowired
    private GroupService groupService;

    private int incommingCount = 0;
    private int sentCount = 0;
    private int pollCount = 0;
    private int regionCount = 0;
	

	@Autowired
	private RefData referenceData;
		
    public List<InCyyteChart> getMyInCyytes(HttpSession session) {
        User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        List<InCyyteChart> myInCyytes = null;
        if (user != null && user.getId() != null) {
            try {
                List<InCyyteChart> incyytes = homeSrv.getMyInCyytes(user.getEmail(), OFFSET);
                myInCyytes = getSentCount(incyytes);
            } catch (Exception e) {
                session.setAttribute("sentCount", 0);
            }
        }
        session.setAttribute("sentCount", sentCount);
        session.setAttribute("mySentInCyytes", myInCyytes);
        
        int noOfRecords = myInCyytes != null ? myInCyytes.size() : 0;
        int recordsPerPage = referenceData.getPageLimit();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); 
        
        session.setAttribute(SessionKeys.SENT_NO_OF_PAGE, noOfPages); 
        
        return myInCyytes;
    }
    
    public List<InCyyteChart> getSentCount(List<InCyyteChart> incyytes) {
        sentCount = 0;
        if (incyytes != null && !incyytes.isEmpty()) {
            List<InCyyteChart> validIncyytes = new ArrayList<InCyyteChart>();
            for (InCyyteChart chart : incyytes) {
                if (!chart.getIncyyte().isDeleted()) {
                    if (!chart.isIncyyteClosed()) sentCount++;
                    validIncyytes.add(chart);
                }
            }
            if (!validIncyytes.isEmpty()) {
            	return validIncyytes;
            }
        }
        return null;
    }

    public List<InCyyte> getMyPollInCyytes(HttpSession session){
    	User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
    	List<InCyyte> incyytes = null;
        if (user != null && user.getId() != null) {
            try {
                incyytes = quickStartSrv.getUserInCyytesByUserId(user.getId(), SEND_BY_POST);
                getPollCount(incyytes);
            } catch (InCyyteExceptions e) {
               Logger.error("InCyyteExceptions",e);
            }
        }
        	
        session.setAttribute("pollCount", pollCount);
	    session.setAttribute("myPostInCyytes", incyytes);                
	
	    int noOfRecords = incyytes != null ? incyytes.size() : 0;
	    int recordsPerPage = referenceData.getPageLimit();
	    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); 
	    
	    session.setAttribute(SessionKeys.MY_POLL_NO_OF_PAGE, noOfPages);
        
        return incyytes;
    }

    public void getPollCount(List<InCyyte> incyytes) {
        pollCount = 0;
        if (incyytes != null && !incyytes.isEmpty()) {
            for (InCyyte cyyte : incyytes) {
                if (!cyyte.isDeleted()) pollCount++;
            }
        }

    }

    public List<InCyyteChart> getInCyyteResults(HttpSession session) {
    	User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        List<InCyyteChart> chart = null;
        if (user != null && user.getId() != null) {
            try {
                List<InCyyteChart> incyytes = homeSrv.getInCyyteResults(user.getEmail(), OFFSET);
                if (incyytes != null && !incyytes.isEmpty()) {
                    chart = checkIfAlreadyVoted(incyytes, user.getId(), user.getEmail());
                }
            } catch (Exception e) {
               Logger.error(e);
            }
        }
        session.setAttribute("incomeCount", incommingCount);
        session.setAttribute("myIncomeinCyyte", chart);
        int noOfRecords = chart != null ? chart.size() : 0;
        int recordsPerPage = referenceData.getPageLimit();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); 
        session.setAttribute(SessionKeys.INCOME_NO_OF_PAGE, noOfPages);
        return chart;
    }

    public List<InCyyteChart> checkIfAlreadyVoted(List<InCyyteChart> incyytes, Long userId, String email) {

        List<InCyyteChart> newIncyytes = new ArrayList<InCyyteChart>();
        incommingCount = 0;
        List<Long> votedInCyytes = quickStartSrv.getAlreadyVotedPolls(email);
        Logger.debug("votedInCyytes::" + votedInCyytes);
        for (InCyyteChart chart : incyytes) {
        	boolean deletedFlag = quickStartSrv.isIncyyteDeleted(chart.getIncyyteId().intValue(), userId.intValue());
            if (!deletedFlag) {
                Logger.debug("InCyyte::" + chart.getIncyyte().getId());
            	if (votedInCyytes.contains(chart.getIncyyte().getId())) {
                    chart.setVoted(true);
            	}
                newIncyytes.add(chart);
            }
        }

        return newIncyytes;
    }


    public List<InCyyteChart> getRegionInCyytes(HttpSession session) {
    	User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);
        List<InCyyteChart> chart = null;

        if (user != null && user.getId() != null) {
            try {
                List<InCyyteChart> incyytes = homeSrv.getAllRegionalInCyyteResults(user.getEmail(), OFFSET);

                if (incyytes != null && !incyytes.isEmpty()) {
                    chart = checkRegionVote(incyytes, user.getId(), user.getEmail());
                }
            } catch (Exception e) {
               Logger.error(e);
            }
        }
        session.setAttribute("regionCount", regionCount);
        session.setAttribute("myRegionInCyytes", chart);
        
        int noOfRecords = chart != null ? chart.size() : 0;
        int recordsPerPage = referenceData.getPageLimit();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); 
        
        session.setAttribute(SessionKeys.MY_REGION_NO_OF_PAGE, noOfPages);
        
        return chart;
    }

    public List<InCyyteChart> checkRegionVote(List<InCyyteChart> incyytes, Long userId, String email) {

        List<InCyyteChart> newIncyytes = new ArrayList<InCyyteChart>();
        regionCount = 0;
        for (InCyyteChart chart : incyytes) {

            boolean deletedFlag = quickStartSrv.isIncyyteDeleted(chart.getIncyyteId().intValue(), userId.intValue());

            if (!deletedFlag) {

                if (!chart.isIncyyteClosed()) {

                    boolean votedFlag = quickStartSrv.isRegionalContactResponded(chart.getIncyyteId(), userId);

                    if (!votedFlag) regionCount++;

                    chart.setVoted(votedFlag);
                }
                newIncyytes.add(chart);
            }
        }

        return newIncyytes;
    }
    
    public MandatoryInfoModel getMandatoryInfoModel(User user){
    	
    	MandatoryInfoModel infoModel = new MandatoryInfoModel();
        infoModel.setUsername(user.getUsername());
        infoModel.setSu_password(user.getPassword());
        infoModel.setFirstname(user.getFirstname());
        infoModel.setLastname(user.getLastname());
        
        if(user.getBirthYear() != null && !user.getBirthYear().equals(0))
        	infoModel.setBirthYear(user.getBirthYear().toString()); 
        
        infoModel.setGender(user.getGender());
        infoModel.setPostalCodeArea(user.getPostalCodeArea());
        infoModel.setLocation(user.getCountyName());
        infoModel.setUserId(user.getId());
        infoModel.setEmail(user.getEmail());
        infoModel.setOccupation(user.getOccupation());
        infoModel.setIncome(user.getIncome());
        
        infoModel.setEthnicity(user.getEthnicity());
        infoModel.setEducationLevel(user.getEducationLevel());
        infoModel.setAdultsInHouseHold(user.getAdultsInHouseHold());
        infoModel.setChildrenInHouseHold(user.getChildrenInHouseHold());
       
        if(user.getBirthYear() != null && user.getBirthYear() != 0)
        	 infoModel.setBirthYearVal(user.getBirthYear()); 
        
        if(user.getIncome() != null)
        	infoModel.setIncomeVal(getIncomes().get(String.valueOf(user.getIncome())));
        
        if(user.getCountryCode() == null && user.getLocation() != null && user.getLocation().getCountryCode() != null)
			infoModel.setCountryCode(user.getLocation().getCountryCode());
		else
			infoModel.setCountryCode(user.getCountryCode());
        
        if(infoModel.getCountryCode() != null){
        	 String country = getCountries().get(infoModel.getCountryCode());
        	 infoModel.setCountry(country);
        }
        
        return infoModel;
    }
    
    public ModelMap checkMandatoryFields(User user){
    	ModelMap model = null;
    	if(StringUtils.isEmpty(user.getCountryCode()) || (user.getCountryCode().equals("GB") && StringUtils.isEmpty(user.getPostalCodeArea()))                
				|| StringUtils.isEmpty(user.getUsername()) || (user.getBirthYear() == null )  
				|| (user.getBirthYear() != null && user.getBirthYear() == 0))  {    
    		model = new ModelMap();
			Calendar calendar = new GregorianCalendar();
			int ageLimit = calendar.get(Calendar.YEAR) - referenceData.getBirthYearLimit();
			model.put("birthYearLimit", ageLimit); 
			model.put("ethnicities", referenceData.getEthnicity()); 
			model.put("educationLevels", referenceData.getEducation_level()); 
			model.put("adultsInHouseHolds", referenceData.getAdults_in_houseHold()); 
			model.put("childrenInHouseHolds", referenceData.getChildren_in_houseHold()); 			
		}
    	return model;
    }
    
    public  Map<String, String>  getCountries(){
    	return referenceData.getCountries();
    }
    
    public Map<String, String> getIncomes() {
		return referenceData.getIncomes();
	}
}
