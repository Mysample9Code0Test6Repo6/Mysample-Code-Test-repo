package com.incyyte.app.dao.core.util;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.service.util.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryHelper {
    private QueryHelper() {
        super();
    }

    public static List doQuery(JdbcTemplate jt, String sql, QueryParameters args, Object mapperOrHandlerObj) {
        if (args == null || args.getParamsCount() == 0) {
            if (mapperOrHandlerObj == null) {
                return (jt.queryForList(sql));
            } else if (mapperOrHandlerObj instanceof RowMapper) {
                return (jt.query(sql, (RowMapper) mapperOrHandlerObj));
            } else if (mapperOrHandlerObj instanceof Class) {
            	return (jt.queryForList(sql, (Class) mapperOrHandlerObj));
            }
        } else {
            if (mapperOrHandlerObj == null) {
                return (jt.queryForList(sql, args.toArray()));
            } else if (mapperOrHandlerObj instanceof RowMapper) {
                return (jt.query(sql, args.toArray(), (RowMapper) mapperOrHandlerObj));
            } else if (mapperOrHandlerObj instanceof Class) {
                return (jt.queryForList(sql, args.toArray(), (Class) mapperOrHandlerObj));
            }
        }
        Logger.warn("Return Empty");
        return (Collections.EMPTY_LIST);
    }

    public static Object doQueryForObject(JdbcTemplate jt, String sql, QueryParameters args, Object mapperOrHandlerObj) {
        if (args == null || args.getParamsCount() == 0) {
            if (mapperOrHandlerObj instanceof RowMapper) {
                return (jt.queryForObject(sql, (RowMapper) mapperOrHandlerObj));
            } else if (mapperOrHandlerObj instanceof Class) {
                return (jt.queryForObject(sql, (Class) mapperOrHandlerObj));
            }
        } else {
            if (mapperOrHandlerObj instanceof RowMapper) {
                return (jt.queryForObject(sql, args.toArray(), (RowMapper) mapperOrHandlerObj));
            } else if (mapperOrHandlerObj instanceof Class) {
                return (jt.queryForObject(sql, args.toArray(), (Class) mapperOrHandlerObj));
            }
        }
        return (new Object());
    }

    public static List doQueryForList(AbstractDao abstractDao, String methodName, String sql, QueryParameters args) {
        JdbcTemplate template = abstractDao.getJdbcTemplate(methodName);
        try {
            return (doQueryForList(template, sql, args));
        } finally {
            abstractDao.freedbpool(template.getDataSource(), methodName);
        }
    }

    public static List doQueryForList(JdbcTemplate jt, String sql, QueryParameters args) {
        if (args == null || args.getParamsCount() == 0) {
            return (jt.queryForList(sql));
        } else {
            return (jt.queryForList(sql, args.toArray()));
        }
    }

    public static int doQueryForInt(JdbcTemplate jt, String sql, QueryParameters args) {
        if (args == null || args.getParamsCount() == 0) {
            return (jt.queryForInt(sql));
        } else {
            return (jt.queryForInt(sql, args.toArray()));
        }
    }

    public synchronized static int doUpdate(JdbcTemplate jt, String sql, QueryParameters args) throws Exception {
        if (args == null || args.getParamsCount() == 0) {
            return (jt.update(sql));
        } else {
            return (jt.update(sql, args.toArray()));
        }
    }

    public static long doQueryForLong(JdbcTemplate jt, String sql, QueryParameters args) {
        if (args == null || args.getParamsCount() == 0) {
            return (jt.queryForLong(sql));
        } else {
            return (jt.queryForLong(sql, args.toArray()));
        }
    }

    public static Long doUpdateForPK(JdbcTemplate jt, final String sql, QueryParameters args) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jt.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        }, keyHolder);
        Long generatedId = new Long(keyHolder.getKey().longValue());

        return (generatedId);
    }

    public static List doQuery(AbstractDao abstractDao, String methodName, String sql, QueryParameters args, Object mapperOrHandlerObj) {
        JdbcTemplate template = abstractDao.getJdbcTemplate(methodName);
        try {
            return doQuery(template, sql, args, mapperOrHandlerObj);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), methodName);
        }
    }

    public static Object doQueryForObject(AbstractDao abstractDao, String methodName, String sql, QueryParameters args, Object mapperOrHandlerObj) {
        JdbcTemplate template = abstractDao.getJdbcTemplate(methodName);
        try {
            return doQueryForObject(template, sql, args, mapperOrHandlerObj);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), methodName);
        }
    }

    public static int doQueryForInt(AbstractDao abstractDao, String methodName, String sql, QueryParameters args) {
        JdbcTemplate template = abstractDao.getJdbcTemplate(methodName);
        try {
            return doQueryForInt(template, sql, args);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), methodName);
        }
    }

    public synchronized static int doUpdate(AbstractDao abstractDao, String methodName, String sql, QueryParameters args) throws Exception {
        JdbcTemplate template = abstractDao.getJdbcTemplate(methodName);
        try {
            return doUpdate(template, sql, args);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), methodName);
        }
    }

    public static long doQueryForLong(AbstractDao abstractDao, String methodName, String sql, QueryParameters args) {
        JdbcTemplate template = abstractDao.getJdbcTemplate(methodName);
        try {
            return doQueryForLong(template, sql, args);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), methodName);
        }
    }

    public static int[] doBatchUpdates(AbstractDao abstractDao, String methodName, String sql, List<QueryParameters> args) {
        JdbcTemplate template = abstractDao.getJdbcTemplate(methodName);
        try {
            return doBatchUpdates(template, sql, args);
        } finally {
            abstractDao.freedbpool(template.getDataSource(), methodName);
        }
    }

    public static int[] doBatchUpdates(JdbcTemplate template, String sql, List<QueryParameters> args) {
        List<Object[]> params = new ArrayList<Object[]>();
        for (QueryParameters param : args) {
            params.add(param.toArray());
        }
        return template.batchUpdate(sql, params);
    }
} //end, class