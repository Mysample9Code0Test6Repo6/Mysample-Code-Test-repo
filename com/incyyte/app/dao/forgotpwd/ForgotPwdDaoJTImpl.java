package com.incyyte.app.dao.forgotpwd;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameterFactory;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.service.util.Logger;

public class ForgotPwdDaoJTImpl implements ForgotPwdDao {
    AbstractDao abstractDao;

    public boolean isValidUserEmail(String email) {
        String validEmailQuery = ForgotPwdDaoQueries.isValidEmailQuery().toString();
        try {
            Logger.debug("validEmailQuery: " + validEmailQuery);
            QueryParameters param = new QueryParameters();
            param.addParam(email);
            int i = QueryHelper.doQueryForInt(abstractDao, "isValidUserEmail", validEmailQuery, param);
            if (i == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Logger.error("isValidUserEmail: Failed ", e);
            return false;
        }
    }

    public boolean isAccountActive(String email) {
        String accountActiveQuery = ForgotPwdDaoQueries.getAccountActiveQuery().toString();
        try {
            Logger.debug("accountActiveQuery: " + accountActiveQuery);
            QueryParameters param = new QueryParameters();
            param.addParam(email);
            int i = QueryHelper.doQueryForInt(abstractDao, "isAccountActive", accountActiveQuery, param);
            if (i == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Logger.error("isAccountActive: Failed ", e);
            return false;
        }
    }

    public boolean resetUserPassword(String email, String newPassword) {
        boolean success = false;
        String resetPasswordQuery = ForgotPwdDaoQueries.getResetPasswordQuery().toString();
        try {
            Logger.debug("resetPasswordQuery: " + resetPasswordQuery);
            QueryParameters params = QueryParameterFactory.getResetPasswordParams(newPassword, email);
            QueryHelper.doUpdate(abstractDao, "resetUserPassword", resetPasswordQuery, params);
            success = true;
        } catch (Exception e) {
            Logger.error("resetUserPassword: Failed ", e);
        }
        return success;
    }

    public boolean isValidOriginalPassword(String userid, String oldPassword) {
        String validOriginalPwdQuery = ForgotPwdDaoQueries.isValidOriginalPwdQuery().toString();
        try {
            Logger.debug("validOriginalPwdQuery: " + validOriginalPwdQuery);
            QueryParameters params = new QueryParameters();
            params.addParam(userid);
            params.addParam(QueryParameterFactory.encryptPwd(oldPassword));
            int i = QueryHelper.doQueryForInt(abstractDao, "isValidOriginalPassword", validOriginalPwdQuery, params);
            if (i == 0) return false;
            return true;
        } catch (Exception e) {
            Logger.error("isValidOriginalPwd: Failed ", e);
            return false;
        }
    }

    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public boolean resetNewUserPassword(Long userid, String newPassword) {
        String resetNewPasswordQuery = ForgotPwdDaoQueries.getResetNewPasswordQuery().toString();
        try {
            Logger.debug("resetNewPasswordQuery: " + resetNewPasswordQuery);
            QueryParameters params = QueryParameterFactory.getResetNewPasswordParams(newPassword, userid);
            QueryHelper.doUpdate(abstractDao, "resetNewUserPassword", resetNewPasswordQuery, params);
            return true;
        } catch (Exception e) {
            Logger.error("resetNewUserPassword: Failed ", e);
        }
        return false;
    }
}