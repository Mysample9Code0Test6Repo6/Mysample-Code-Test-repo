package com.incyyte.app.dao.core.txn;


public interface ITxnHelper
{
    /**
     * Wrapper, that will create the transaction
     *
     */
    public abstract void beginTxn();

    /**
     * Wrapper, that will commit the transaction
     *
     */
    public abstract void commitTxn();

    /**
     * Wrapper, that will rollback the transaction
     *
     */
    public abstract void rollbackTxn();
}