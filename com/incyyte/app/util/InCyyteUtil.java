package com.incyyte.app.util;

import com.incyyte.app.domain.GroupContact;
import com.incyyte.app.domain.GroupSharedInCyyte;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.IncyyteProperties;
import com.incyyte.app.service.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InCyyteUtil {

    public static List<GroupSharedInCyyte> buildGroupSharedInCyyte(String userName, Long questionId, List<Long> memberIds) {
        List<GroupSharedInCyyte> recs = new ArrayList<GroupSharedInCyyte>();
        Logger.debug("buildGroupSharedInCyyte:Start");
        Logger.debug("buildGroupSharedInCyyte:userName:" + userName);
        Logger.debug("buildGroupSharedInCyyte:questionId:" + questionId);
        Logger.debug("buildGroupSharedInCyyte:memberIds:" + memberIds);
        for (long memberId : memberIds) {
            GroupSharedInCyyte rec = new GroupSharedInCyyte();
            rec.setFkMemberId(memberId);
            rec.setFkQuestionId(questionId);
            rec.setCreatedBy(userName);
            rec.setLastUpdatedBy(userName);
            Logger.debug("buildGroupSharedInCyyte:Record:" + rec);
            recs.add(rec);
        }
        Logger.debug("buildGroupSharedInCyyte:Records:" + recs);
        return recs;
    }

    public static List<GroupSharedInCyyte> buildSharedInCyyte(String userId, Long questionId, List<GroupContact> groupContacts) {
        List<GroupSharedInCyyte> groupSharedInCyytes = new ArrayList<GroupSharedInCyyte>();
        for (GroupContact groupContact : groupContacts) {
            GroupSharedInCyyte sharedInCyyte = new GroupSharedInCyyte();
            sharedInCyyte.setFkMemberId(groupContact.getMemberId());
            sharedInCyyte.setFkQuestionId(questionId);
            sharedInCyyte.setCreatedBy(userId);
            sharedInCyyte.setLastUpdatedBy(userId);
            groupSharedInCyytes.add(sharedInCyyte);
        }
        return groupSharedInCyytes;
    }

    public static String getWebURL() {
        try {
            IncyyteProperties ip = new IncyyteProperties(null);
            ConfigManager icfg = ConfigManager.getInstance();
            icfg.setIncyyteProperties(ip);
            return icfg.getProperty(ConfigProperties.WEBSITE_URL);
        } catch (ConfigurationException e) {
            Logger.error("Not able to access Website URL", e);
        }
        return null;
    }
    
    public static Map<String, String> getPollsByCategory(Map<String, Map<String, String>> pollCategories) {
    	Map<String, String> pollsByCategory = new HashMap<String, String>();
    	List<String> categories = new ArrayList<String>(pollCategories.keySet());
    	
    	for (int i = 0; i < categories.size(); i++) {
			pollsByCategory.put(categories.get(i), pollCategories.get(categories.get(i)).get("LATEST_POLL"));
    	}
    	Logger.debug("pollCategories::" + pollCategories);
    	return pollsByCategory;
    }
}