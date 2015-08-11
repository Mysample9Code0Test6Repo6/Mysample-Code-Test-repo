package com.incyyte.app.dao.core.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

public interface IDataSourceManager
{
    public abstract DataSource getDataSource(String MethodName);
    public abstract void showDataSourceStatus(DataSource ds,String MethodName);
	public  abstract void  cleanup(DataSource ds,String MethodName) throws SQLException ;
}