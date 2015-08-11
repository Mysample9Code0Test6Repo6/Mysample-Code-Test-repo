package com.incyyte.app.dao.core;

import com.incyyte.app.dao.core.datasource.DataSourceManager;
import com.incyyte.app.dao.core.datasource.IDataSourceManager;
import com.incyyte.app.dao.core.txn.TxnHelper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * This DAO serves as the base DAO for all DAOs in the inCyyte application.
 * REMOVED abstraction to allow instantiation within AppContext
 */

public class AbstractDao {

    IDataSourceManager dsManager;
    private JdbcTemplate jdbcTemplate;
    private TxnHelper txnHelper;

    public IDataSourceManager getDsManager() {
        return dsManager;
    }

    public void setDsManager(IDataSourceManager aDsManager) {
        this.dsManager = aDsManager;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate(String MethodName) {
        DataSourceManager dsm = (DataSourceManager) getDsManager();
        dsm.showDataSourceStatus(dsm.getDataSource(MethodName), MethodName);
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setTxnHelper(TxnHelper txnHelper) {
        this.txnHelper = txnHelper;
    }

    public TxnHelper getTxnHelper() {
        return txnHelper;
    }

    public void freedbpool(DataSource ds, String MethodName) {
        DataSourceManager dsm = (DataSourceManager) getDsManager();
        try {
            dsm.cleanup(ds, MethodName);
        } catch (SQLException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
} //end, class