package com.incyyte.app.dao.core.util;

import com.incyyte.app.dao.user.DesEncrypter;
import com.incyyte.app.domain.Group;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserLocation;
import com.incyyte.app.service.Exceptions.ConfigurationException;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.IncyyteProperties;
import com.incyyte.app.web.model.UserContactModel;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class QueryParameterFactory {

    public static QueryParameters getRegionalPostcodeParams(String postcode) {
        QueryParameters params = new QueryParameters();
        params.addParam(postcode);
        return params;
    }

    public static QueryParameters getRegionalCountyParams(String countyName) {
        QueryParameters params = new QueryParameters();
        params.addParam(countyName);
        return params;
    }

    public static QueryParameters getRegionalParams(String postcode) {
        QueryParameters params = new QueryParameters();
        params.addParam(postcode);
        params.addParam(postcode);
        return params;
    }

    public static QueryParameters getRegionalAllParams(String countyName, String postcode, String param) {
        QueryParameters params = new QueryParameters();
        params.addParam(countyName);
        params.addParam(postcode);
        params.addParam(postcode);
        params.addParam(postcode);

        if (param != null && StringUtils.isNotEmpty(param))
            params.addParam("%" + param + "%");

        return params;
    }


    public static QueryParameters getUserParams(User user) {
        QueryParameters params = new QueryParameters();
        params.addParam(user.getId());
        params.addParam(user.getFirstname());
        params.addParam(encryptPwd(user.getPassword()));
        params.addParam(user.getEmail());
        params.addParam(user.getMobile());
        params.addParam(user.getStatus());
        params.addParam(user.getGender());
        params.addParam(user.getAgeGroup());
        params.addParam(user.getAcceptTerms());
        params.addParam("ADMIN");
        return params;
    }

    public static QueryParameters getSignupParams(User user) {
        QueryParameters params = new QueryParameters();
        params.addParam(user.getId());
        params.addParam(user.getUsername());
        params.addParam(user.getEmail());
        params.addParam(null != user.getPassword() ? encryptPwd(user.getPassword()) : null);
        params.addParam(null != user.getResetPwdFlag() ? user.getResetPwdFlag() : "N");
        params.addParam(user.getStatus());
        params.addParam(user.getActivationCode());
        params.addParam("ADMIN");
        params.addParam("Music,Business,Motoring,Sports,Shopping,Internet,Religion,Health,Entertainment,Politics,Beauty,Others,Community,Travel,Relationships,Food");
        params.addParam(user.getGender());
        return params;
    }

    public static QueryParameters getSignupParamsSN(User user) {
        QueryParameters params = new QueryParameters();
        params.addParam(user.getId());
        params.addParam(user.getUsername());
        params.addParam(user.getEmail());
        params.addParam(null != user.getPassword() ? encryptPwd(user.getPassword()) : null);

        params.addParam(user.getStatus());
        params.addParam(user.getActivationCode());
        params.addParam("ADMIN");
        if (null != user.getSignupmode() && user.getSignupmode().length() > 2)
            params.addParam(user.getSignupmode());
        else
            params.addParam("DIRECT");
        params.addParam(user.getFirstname());
        params.addParam(user.getLastname());

        params.addParam(user.getGender());
        params.addParam(user.getBirthYear());
        params.addParam("Music,Business,Motoring,Sports,Shopping,Internet,Religion,Health,Entertainment,Politics,Beauty,Others,Community,Travel,Relationships,Food");
        params.addParam("I use inCyyte to get opinions on my thoughts and questions from groups of friends, workmates & my communities");
        params.addParam(user.getProfilePicture());
        return params;
    }

    public static QueryParameters getQuParams(InCyyte incyyte) {
        QueryParameters params = new QueryParameters();
        ConfigManager icfg = ConfigManager.getInstance();
        try {
            icfg.setIncyyteProperties(new IncyyteProperties(null));
            params.addParam(incyyte.getId());
            params.addParam(incyyte.getUserId());
            params.addParam(incyyte.getIncyyte());
            params.addParam(incyyte.getCategory());
            params.addParam(incyyte.getBackgroundId());
            params.addParam(incyyte.getIncyyteCode());
            params.addParam(incyyte.getCreatedBy());
            params.addParam(incyyte.getUpload());
            params.addParam(incyyte.getUpload_name());
            params.addParam(incyyte.getUpload_ext());
            params.addParam(incyyte.getUpload_type());
            params.addParam(incyyte.getContent_type());
            params.addParam(incyyte.getQuesType());
            params.addParam(incyyte.getUploadLocation());
            params.addParam(incyyte.getLink());
            params.addParam(incyyte.isAnonymity() ? 1 : 0);
            params.addParam(incyyte.getPageName());
            params.addParam(incyyte.getSendType());
            params.addParam(incyyte.getPublic_poll());

            params.addParam(incyyte.getAllowComment());
            params.addParam(incyyte.getProtectPage());
            params.addParam(incyyte.getStrapline());
            params.addParam(incyyte.getUpload_logo_location());
            params.addParam(incyyte.getAccess_code());
            params.addParam(incyyte.getCdnFileName());
            params.addParam(incyyte.getPollResultHidden());
            params.addParam(incyyte.getTemplateId());
            params.addParam(incyyte.getYoutubeUrl());
        } catch (ConfigurationException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return params;
    }

    public static QueryParameters getGrpParams(InCyyte incyyte) {
        QueryParameters params = new QueryParameters();
        params.addParam(incyyte.getGroup().getGroupId());
        params.addParam(incyyte.getUserId());
        params.addParam(incyyte.getGroup().getGroupName());
        params.addParam(incyyte.getGroup().getGroupType());
        params.addParam(incyyte.getGroup().getDescription());
        return params;
    }

    public static QueryParameters getGrpParams(InCyyte incyyte, String postalRegion) {
        QueryParameters params = new QueryParameters();
        params.addParam(incyyte.getGroup().getGroupId());
        params.addParam(incyyte.getUserId());
        params.addParam(incyyte.getGroup().getGroupName());
        params.addParam(incyyte.getGroup().getGroupType());
        params.addParam(incyyte.getGroup().getDescription());
        params.addParam(postalRegion);
        return params;
    }

    public static QueryParameters getInCyyteParams(long grpId, long incyyteId, int contacts, Date closureDate) {
        QueryParameters params = new QueryParameters();
        params.addParam(grpId);
        params.addParam(incyyteId);
        params.addParam(contacts);
        params.addParam(closureDate);
        return params;
    }

    public static QueryParameters getInCyyteRegionParams(long grpId, long incyyteId, Date closureDate, InCyyte incyyte) {
        QueryParameters params = new QueryParameters();
        params.addParam(grpId);
        params.addParam(incyyteId);
        params.addParam(incyyte.getTotalInCyyted());
        params.addParam(closureDate);
        params.addParam(incyyte.getCountry());
        params.addParam(incyyte.getSendMethod());
        params.addParam(incyyte.getSendZone());
        return params;
    }

    public static QueryParameters getUserIdParams(Long userId) {
        QueryParameters params = new QueryParameters();
        params.addParam(userId);
        return params;
    }

    public static QueryParameters getSULocatorParams(Long userId, UserLocation location) {
        QueryParameters params = new QueryParameters();

        params.addParam(userId);
        params.addParam(location.getIpAddress());
        params.addParam(location.getCountryCode());
        params.addParam(location.getCountryName());
        params.addParam(location.getRegion());
        params.addParam(location.getRegionName());
        params.addParam(location.getCity());
        params.addParam(location.getPostalCode());
        params.addParam(location.getLatitude());
        params.addParam(location.getLongitude());
        return params;
    }

    public static QueryParameters getActivationParams(String email) {
        QueryParameters params = new QueryParameters();
        params.addParam(email);
        return params;
    }

    public static QueryParameters getResetPasswordParams(String newPassword, String email) {
        QueryParameters params = new QueryParameters();
        params.addParam(encryptPwd(newPassword));
        params.addParam(email);
        return params;
    }

    public static QueryParameters getResetNewPasswordParams(String newPassword, Long userid) {
        QueryParameters params = new QueryParameters();
        params.addParam(encryptPwd(newPassword));
        params.addParam(userid);
        return params;
    }

    public static QueryParameters getFailedEmailParams(User user, String emailType) {
        QueryParameters params = new QueryParameters();
        params.addParam(user.getId());
        params.addParam(emailType);
        params.addParam(Constants.LOG_EMAIL_ERROR);
        params.addParam(1);
        return params;
    }

    public static QueryParameters getXmlStringParams(InCyyte incyyte) {
        QueryParameters params = new QueryParameters();
        params.addParam(incyyte.getXmlString());
        params.addParam(incyyte.getId());
        return params;
    }

    public static QueryParameters getCloseInCyyteParams(long incyyteId, long groupId) {
        QueryParameters params = new QueryParameters();
        params.addParam(groupId);
        params.addParam(incyyteId);
        return params;
    }

    public static QueryParameters getIncrementResponseParams(long incyyteID) {
        QueryParameters params = new QueryParameters();
        params.addParam(incyyteID);
        return params;
    }

    public static QueryParameters getIncrementGenralResponseParams(long incyyteID) {
        QueryParameters params = new QueryParameters();
        params.addParam(incyyteID);
        return params;
    }

    public static QueryParameters getIncomingParams(String email) {
        QueryParameters params = new QueryParameters();
        params.addParam(email);
        params.addParam(email);
        return params;
    }

    public static String encryptPwd(String pwd) {
        DesEncrypter encrypter = new DesEncrypter("My Pass Phrase");
        return encrypter.encrypt(pwd);
    }

    public static QueryParameters getaddContactParams(UserContactModel user, long userid) {
        QueryParameters params = new QueryParameters();
        params.addParam(user.getContactid());
        params.addParam(userid);
        params.addParam(user.getNickname());
        params.addParam(user.getFirstname());
        params.addParam(user.getLastname());
        params.addParam(user.getEmail());
        params.addParam(user.getMobile());
        params.addParam(userid);
        params.addParam(userid);
        params.addParam(user.getSent_invite());
        params.addParam(user.getNote());
        params.addParam(user.getInvitationid());
        if ((user.getAccept_inv()).equals("Y"))
            params.addParam("Y");//Accepted Invitation
        else
            params.addParam("N");

        if (null != user.getSn_id() && user.getSn_id().length() > 2)
            params.addParam(user.getSn_id());
        else
            params.addParam(user.getContactid());

        if (null != user.getSn_site() && user.getSn_site().length() > 2)
            params.addParam(user.getSn_site());
        else
            params.addParam("DIRECT");
        params.addParam(user.getStatus());
        params.addParam(StringUtils.defaultString(user.getHidden(), "N"));
        return params;
    }

    public static QueryParameters getaddContactParamsAccept(UserContactModel user) {
        QueryParameters params = new QueryParameters();
        params.addParam(user.getContactid());
        params.addParam(user.getUserid());
        params.addParam(user.getNickname());
        params.addParam(user.getFirstname());
        params.addParam(user.getLastname());
        params.addParam(user.getEmail());
        params.addParam(user.getMobile());
        params.addParam(user.getUserid());
        params.addParam(user.getUserid());
        params.addParam("Y");
        params.addParam(user.getNote());
        params.addParam(user.getInvitationid());
        params.addParam("Y");//Accepted Invitation
        if (null != user.getSn_id() && user.getSn_id().length() > 2)
            params.addParam(user.getSn_id());
        else
            params.addParam(user.getContactid());

        if (null != user.getSn_site() && user.getSn_site().length() > 2)
            params.addParam(user.getSn_site());
        else
            params.addParam("DIRECT");
        params.addParam(user.getStatus());
        params.addParam(StringUtils.defaultString(user.getHidden(), "N"));
        return params;
    }

    public static QueryParameters getValidateGroupParams(Group grp) {
        QueryParameters params = new QueryParameters();
        params.addParam(grp.getUserId());
        params.addParam(grp.getGroupName());
        return params;
    }

    public static QueryParameters getupdateContactParams(UserContactModel user, long userid) {

        QueryParameters params = new QueryParameters();

        params.addParam(userid);
        params.addParam(user.getContactid());
        params.addParam(userid);
        return params;
    }

    public static QueryParameters getUsersNameParams(String email, String firstname, String lastname) {
        QueryParameters params = new QueryParameters();
        params.addParam(firstname);
        params.addParam(lastname);
        params.addParam(email);
        return params;
    }
}