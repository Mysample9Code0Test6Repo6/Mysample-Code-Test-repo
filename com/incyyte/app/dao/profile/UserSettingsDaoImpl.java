package com.incyyte.app.dao.profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameterFactory;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.UserSettingsModel;

/**
 * @author Dev1
 */
@Repository
public class UserSettingsDaoImpl implements UserSettingsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AbstractDao abstractDao;

    @Override
    public UserSettingsModel getUserSettings(Long userId) throws EmptyResultDataAccessException {
        String query = UserSettingsQueries.getSelectUserSettingsQuery();
        Object[] args = {userId};
        try {
            UserSettingsModel userSettingsModel = jdbcTemplate.queryForObject(query, args, new RowMapper<UserSettingsModel>() {
                @Override
                public UserSettingsModel mapRow(ResultSet rs, int arg1) throws SQLException {
                    UserSettingsModel userSettingsModel = new UserSettingsModel();

                    userSettingsModel.setDisplayRating(rs.getString("display_rating"));
                    userSettingsModel.setDisableComments(rs.getString("DISABLE_POLL_COMMENTS"));
                    userSettingsModel.setDisbaleIncyytes(rs.getString("disable_incyytes"));
                    userSettingsModel.setRestrictComments(rs.getString("restrict_comments"));
                    userSettingsModel.setOptInPriceTag(rs.getString("opt_in"));
                    userSettingsModel.setNotifypolls(rs.getString("notify_poll"));
                    userSettingsModel.setNotifyFollower(rs.getString("notify_follower"));
                    userSettingsModel.setNotifyNews(rs.getString("notify_news"));
                    userSettingsModel.setUniquePageName(rs.getString("unique_page_name"));
                    userSettingsModel.setPassword(rs.getString("password"));
                    userSettingsModel.setUserId(rs.getInt("userid"));
                    return userSettingsModel;
                }
            });
            return userSettingsModel;
        } catch (EmptyResultDataAccessException e) {
            Logger.warn("User settings not configured for userid:" + userId);
        } catch (Exception e) {
            Logger.error("Getting User setting details is failed::", e);
        }
        return null;
    }

    @Override
    public int saveUserPassword(String newPassword, String password, Long userId) {
        String query = UserSettingsQueries.getUserUpdatePasswordQuery();
        Object[] args = {QueryParameterFactory.encryptPwd(newPassword), userId, QueryParameterFactory.encryptPwd(password)};
        int updateCount = jdbcTemplate.update(query, args);
        return updateCount;
    }

    @Override
    public String getUniquePageName(Long userId) {
        StringBuilder uniquePageNameQuery = new StringBuilder();
        uniquePageNameQuery.append("SELECT us.unique_page_name ");
        uniquePageNameQuery.append(" FROM user_settings us,users user ");
        uniquePageNameQuery.append(" WHERE user.STATUS = 'ACTIVE' ");
        uniquePageNameQuery.append("   AND us.userid = user.userid ");
        uniquePageNameQuery.append("   AND user.userid = ? ");
        String pageName = null;
        List<String> pageNames = new ArrayList<String>();
        QueryParameters param = new QueryParameters();
        param.addParam(userId);
        try {
            pageNames = QueryHelper.doQuery(abstractDao, "getUniquePageName", uniquePageNameQuery.toString(), param, String.class);
            Logger.debug("pageName::" + pageName);
        } catch (Exception e) {
            Logger.error("getUniquePageName failed ", e);
        }
        return ((pageNames != null && pageNames.size() > 0) ? pageNames.get(0) : null);
    }

    @Override
    public int saveUniquePageName(String pageName, Long userId) throws Exception {
        String query = UserSettingsQueries.getUpdateUniquePageQuery();
        Object[] args = {pageName, userId};
        try {
            int updateCount = jdbcTemplate.update(query, args);
            return updateCount;
        } catch (DataAccessException se) {
            Logger.warn("Data Access::"+ se.getMessage());
            return 0;
        }
    }

    @Override
    public int saveDisplayRating(String ch, Long userId) {

        String query = UserSettingsQueries.getUpdateRatingQuery();

        Object[] args = {ch, userId};
        int updateCount = jdbcTemplate.update(query, args);
        return updateCount;
    }

    @Override
    public int saveDisplayPollComments(String ch, Long userId) {
        Logger.debug("chch " + ch);
        Logger.debug("userId " + userId);

        String query = UserSettingsQueries.getUpdateDisablePollQuery();

        Object[] args = {ch, userId};
        int updateCount = jdbcTemplate.update(query, args);
        return updateCount;
    }

    @Override
    public int saveDisplayRecentIncyytes(String ch, Long userId) {

        String query = UserSettingsQueries.getUpdateIncyytesQuery();

        Object[] args = {ch, userId};
        int updateCount = jdbcTemplate.update(query, args);
        return updateCount;
    }

    @Override
    public int saveRetrictComments(String ch, Long userId) {

        String query = UserSettingsQueries.getUpdateRestrictCommentsQuery();

        Object[] args = {ch, userId};
        int updateCount = jdbcTemplate.update(query, args);
        return updateCount;
    }

    @Override
    public int savePriceTagPolling(String ch, Long userId) {

        String query = UserSettingsQueries.getUpdateOptinQuery();

        Object[] args = {ch, userId};
        int updateCount = jdbcTemplate.update(query, args);
        return updateCount;
    }

    @Override
    public int saveNotifyPoll(String ch, Long userId) {

        String query = UserSettingsQueries.getUpdateNotifyPollQuery();

        Object[] args = {ch, userId};
        int updateCount = jdbcTemplate.update(query, args);
        return updateCount;
    }

    @Override
    public int saveNotifyFollower(String ch, Long userId) {

        String query = UserSettingsQueries.getUpdateNotifyFollowerQuery();

        Object[] args = {ch, userId};
        int updateCount = jdbcTemplate.update(query, args);
        return updateCount;
    }

    @Override
    public int saveNotifyNews(String ch, Long userId) {

        String query = UserSettingsQueries.getUpdateNotifyNewsQuery();

        Object[] args = {ch, userId};
        int updateCount = jdbcTemplate.update(query, args);
        return updateCount;
    }

    @Override
    public int deactivateAccount(Long userId) throws Exception {
        Logger.debug("userId ::" + userId);
        String query = UserSettingsQueries.getUpdateDeactivateQuery();
        Logger.debug("query ::" + query);
        Object[] args = {userId};
        try {
            int updateCount = jdbcTemplate.update(query, args);
            Logger.debug("updateCount ::" + updateCount);
            return updateCount;
        } catch (Exception e) {
            Logger.error("Deactivating is Failed", e);
            return 0;
        }
    }

    @Override
    public boolean isPageNameUnique(String pageName) throws Exception {
    	String usernameExistsQuery = "SELECT (SELECT COUNT(username) FROM users WHERE username = ?) + " +
    							     "(SELECT COUNT(unique_page_name) from user_settings WHERE unique_page_name = ?) as count" ;
        QueryParameters params = new QueryParameters();
        params.addParam(pageName);
        params.addParam(pageName);
        int i = QueryHelper.doQueryForInt(abstractDao, "usernameExists", usernameExistsQuery, params);
        Logger.debug("i value::"+i);
        if (i == 0) {
            return true;
        }
        return false;
    }
}
