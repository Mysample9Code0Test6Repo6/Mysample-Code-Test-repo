package com.incyyte.app.dao.core.datasource;

import com.incyyte.app.service.util.Logger;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;


public class DataSourceManager implements IDataSourceManager {

    private DataSource dataSource = null;

    protected DataSourceManager() {
        super();
    }


    public DataSourceManager(DataSource aDataSource) {
        this.dataSource = (ComboPooledDataSource) aDataSource;
    }

    public DataSource getDataSource(String MethodName) {
        showDataSourceStatus(dataSource, MethodName);
        return this.dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Utility method that will log the status of the pooled datasource on every
     * get/release of a connection
     *
     * @param ds
     */
    public void showDataSourceStatus(DataSource ds, String MethodName) {
        StringBuffer sb = new StringBuffer();

        if (ds != null) {
            ComboPooledDataSource cpds = null;
            if (ds instanceof ComboPooledDataSource) {
                cpds = ((ComboPooledDataSource) ds);

                sb.append(" ========== DataSource ")
                        .append(cpds.getDescription())
                        .append(" ==========");

                try {
                    sb.append("    No. connections:");
                    sb.append(cpds.getNumConnections());
                    sb.append("    No. busy connections:");
                    sb.append(cpds.getNumBusyConnections());
                    sb.append("    No. Idle connections:");
                    sb.append(cpds.getNumIdleConnections());
                    sb.append("    MaxConnectionAge:");
                    sb.append(cpds.getMaxConnectionAge());
                    sb.append("    Method Name:");
                    sb.append(MethodName);
                } catch (SQLException ignore) {
                    Logger.error(ignore);
                    sb.append("    No. connections:");
                    sb.append("N/A");
                    sb.append("    No. busy connections:");
                    sb.append("N/A");
                    sb.append("    No. Idle connections:");
                    sb.append("N/A");
                }
            } //end, if ComboPooledDataSource
        }
        Logger.debug(sb.toString());
    }


    public void cleanup(DataSource ds, String MethodName) throws SQLException {
        StringBuffer sb = new StringBuffer();
        if (ds != null)
            if (ds instanceof ComboPooledDataSource) {
                ComboPooledDataSource cpds = (ComboPooledDataSource) ds;
                cpds.getConnection().close();

                sb.append(" ========== DataSource ")
                        .append(cpds.getDescription())
                        .append(" ==========");

                try {
                    sb.append("    No. connections:");
                    sb.append(cpds.getNumConnections());
                    sb.append("    No. busy connections:");
                    sb.append(cpds.getNumBusyConnections());
                    sb.append("    No. Idle connections:");
                    sb.append(cpds.getNumIdleConnections());
                    sb.append("    MaxConnectionAge:");
                    sb.append(cpds.getMaxConnectionAge());
                    sb.append("    Method Name:");
                    sb.append(MethodName);
                } catch (SQLException ignore) {
                    Logger.error(ignore);
                    sb.append("    No. connections:");
                    sb.append("N/A");
                    sb.append("    No. busy connections:");
                    sb.append("N/A");
                    sb.append("    No. Idle connections:");
                    sb.append("N/A");
                }

                Logger.debug(sb.toString());
            } else
                Logger.error("Not a c3p0 PooledDataSource!");
    }
} //end, class DataSourceManager