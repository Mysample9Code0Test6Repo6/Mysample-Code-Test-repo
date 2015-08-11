package com.incyyte.app.dao.businessaccount;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.core.util.SeqGeneratorDao;
import com.incyyte.app.dao.user.rowmapper.BusinessAccountRowMapper;
import com.incyyte.app.domain.BusinessAccount;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.service.util.Logger;
import org.apache.commons.lang.StringUtils;

import java.sql.SQLException;

public class BusinessAccountDaoImpl implements BusinessAccountDao {

    private AbstractDao abstractDao;

    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public boolean storeBussinessAccountInfo(BusinessAccount bussinessAccount) throws Exception, AccessException {
        Logger.debug("inside BusinessAccountController businessAccount:::::" + bussinessAccount);
        BusinessAccount getBusinessUserInfo = getBussinessAccountInfo(bussinessAccount.getUserid());
        Logger.debug("inside BusinessAccountController getBusinessUserInfo:::::" + getBusinessUserInfo);
        if (getBusinessUserInfo == null || StringUtils.isBlank(String.valueOf(getBusinessUserInfo.getUserid()))) {
            Logger.debug("storeBusinessAccountInfo::");
            long id = SeqGeneratorDao.getInstance(abstractDao).getSequenceNextVal("BUSINESSACCT_PK", false);
            String businessAccountInfoQuery = BusinessAccountDaoQueries.getInsertBusinessAccountQuery().toString();
            Logger.debug("businessAccountInfoQuery::::" + businessAccountInfoQuery);
            QueryParameters params = new QueryParameters();
            params.addParam(id);
            params.addParam(bussinessAccount.getUserid());
            params.addParam(bussinessAccount.getCompanyName());
            params.addParam(bussinessAccount.getAddress1());
            params.addParam(bussinessAccount.getAddress2());
            params.addParam(bussinessAccount.getCity());
            params.addParam(bussinessAccount.getPostalCode());
            params.addParam(bussinessAccount.getCountry());
            params.addParam(bussinessAccount.getContactEmail());
            params.addParam(bussinessAccount.getWebsiteUrl());
            params.addParam(bussinessAccount.getPhone());
            params.addParam(bussinessAccount.getCompanyLogoUrl());
            params.addParam(bussinessAccount.getCompanyInfoPara1());
            params.addParam(bussinessAccount.getCompanyInfoPara2());
            params.addParam(bussinessAccount.getBannerUrl());
            try {
                QueryHelper.doUpdate(abstractDao, "storeBusinessAccountInfo", businessAccountInfoQuery, params);
            } catch (SQLException se) {
                throw new Exception("Invalid SQL Statement");
            }
            return true;
        } else {
            boolean updateResult = updateBusinessAccountInfo(bussinessAccount);
            Logger.debug("inside BusinessAccountController updateResult:::::" + updateResult);
            return updateResult;
        }
    }

    @Override
    public BusinessAccount getBussinessAccountInfo(long userid) {
        String getBussinessAccInfoQuery = BusinessAccountDaoQueries.getBusinessAccountQuery().toString();
        Logger.debug("getBussinessAccInfoQuery:::" + getBussinessAccInfoQuery);
        BusinessAccount getBusinessInfo = null;
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        try {
            getBusinessInfo = (BusinessAccount) QueryHelper.doQueryForObject(abstractDao, "getBussinessAccountInfo", getBussinessAccInfoQuery, params, new BusinessAccountRowMapper());
            Logger.debug("inside getBussinessAccountInfo :::::" + getBusinessInfo);
        } catch (Exception e) {
            Logger.error("getBussinessAccountInfo:exception::" , e);
        }
        return getBusinessInfo;
    }

    @Override
    public boolean updateBusinessAccountInfo(BusinessAccount bussinessAccount) throws Exception {
        String updateBusinessAccInfoQuery = BusinessAccountDaoQueries.updateBusinessAccountInfo();
        Logger.debug("updateBusinessAccInfoQuery:::" + updateBusinessAccInfoQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(bussinessAccount.getCompanyName());
        params.addParam(bussinessAccount.getAddress1());
        params.addParam(bussinessAccount.getAddress2());
        params.addParam(bussinessAccount.getCity());
        params.addParam(bussinessAccount.getPostalCode());
        params.addParam(bussinessAccount.getCountry());
        params.addParam(bussinessAccount.getContactEmail());
        params.addParam(bussinessAccount.getWebsiteUrl());
        params.addParam(bussinessAccount.getPhone());
        params.addParam(bussinessAccount.getCompanyInfoPara1());
        params.addParam(bussinessAccount.getCompanyInfoPara2());
        params.addParam(bussinessAccount.getCompanyLogoUrl());
        params.addParam(bussinessAccount.getBannerUrl());
        params.addParam(bussinessAccount.getUserid());

        try {
            QueryHelper.doUpdate(abstractDao, "updateBusinessAccountInfo", updateBusinessAccInfoQuery, params);
            Logger.debug("Updated the Info :::::");
        } catch (Exception e) {
            Logger.error("getBusinessAccountInfo:exception::", e);
            return false;
        }
        return true;
    }

    @Override
    public void updateUserType(long userid) throws Exception {
        Logger.debug("updateUserType:: - > " + userid);
        String updateUserTypeQuery = BusinessAccountDaoQueries.updateUserTypeQuery().toString();
        Logger.debug("updateUserTypeQuery: " + updateUserTypeQuery);
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        QueryHelper.doUpdate(abstractDao, "updateUserType", updateUserTypeQuery, params);
    }

    @Override
    public String getUserType(long userid) {
        String getUserTypeQuery = BusinessAccountDaoQueries.getUserTypeQuery().toString();
        Logger.debug("getUserTypeQuery:::" + getUserTypeQuery);
        String getUserType = null;
        QueryParameters params = new QueryParameters();
        params.addParam(userid);
        try {
            getUserType = (String) QueryHelper.doQueryForObject(abstractDao, "getUserType", getUserTypeQuery, params, String.class);
        } catch (Exception e) {
            Logger.error("getUserTypeQuery:exception::", e);
        }
        return getUserType;
    }
}
