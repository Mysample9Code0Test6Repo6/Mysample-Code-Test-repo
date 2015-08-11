package com.incyyte.app.dao.pollemailcount;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Calendar;
import java.util.Date;

public class PollEmailCountDaoImpl implements PollEmailCountDao {
    @Autowired
    private AbstractDao abstractDao;

    @Override
    public void storePollEmailCount(User user, InCyyte incyyte, int sharedCount) throws Exception {
        Logger.debug("user object" + user);
        Logger.debug("incyyte " + incyyte);
        Logger.debug("sharedCount" + sharedCount);
        JdbcTemplate template = abstractDao.getJdbcTemplate("storePollEamilCount");
        String emailCountQuery = "INSERT INTO POLL_EMAIL_COUNT(USER_ID,QUESTION_ID,SHARED_DATE,SHARED_COUNT) VALUES(?,?,?,?)";
        Logger.debug("emailCountQuery------------->" + emailCountQuery);
        Date date = new Date();
        QueryParameters params = new QueryParameters();
        params.addParam(user.getId());
        params.addParam(incyyte.getId());
        params.addParam(date);
        params.addParam(sharedCount);
        try {
            QueryHelper.doUpdate(template, emailCountQuery, params);
        } catch (Exception e) {
            Logger.error("Exception--------::", e);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), "storePollEamilCount");
        }
    }

    @Override
    public int getPollEmailCountInfo(User user) {
        Logger.debug("user values in get info" + user);
        int getShareCount = 0;
        String query = "SELECT SUM(SHARED_COUNT) AS SHARED_COUNT FROM POLL_EMAIL_COUNT WHERE (YEAR(SHARED_DATE)= ? AND MONTH(SHARED_DATE) = ?) AND USER_ID = ?";
        QueryParameters params = new QueryParameters();
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        //Calendar month starts from 0 (0 for Jan and 11 for Dec)
        //Hence adding + 1 to the month value
        int month = date.get(Calendar.MONTH) + 1;
        Logger.debug("year...." + year);
        Logger.debug("current month...." + month);
        params.addParam(year);
        params.addParam(month);
        params.addParam(user.getId());
        try {
            getShareCount = QueryHelper.doQueryForInt(abstractDao, "getPollEmailCount", query, params);
            Logger.debug("getShareCount::" + getShareCount);
        } catch (Exception e) {
            Logger.error("getPollEmailCountInfo:exception::", e);
        }
        return getShareCount;
    }
}