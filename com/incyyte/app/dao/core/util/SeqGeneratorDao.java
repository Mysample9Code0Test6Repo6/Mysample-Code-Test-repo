package com.incyyte.app.dao.core.util;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.service.Exceptions.AccessException;
import com.incyyte.app.service.util.Logger;
import org.springframework.dao.CannotAcquireLockException;

public class SeqGeneratorDao {
    AbstractDao abstractDao;
    /**
     * Attribute Declarations
     */
    private static final String NEXTVAL = "NEXTVAL";
    private static final String ERR_SEQUENCE_VALUE_NOT_FOUND = "err.sequence.value.not.found";
    private static SeqGeneratorDao _instance = null;
    //The isProcessing boolean flag is used for avoiding contention between threads trying to execute the getSequenceNextVal() method
    private static boolean isProcessing = false;

    private SeqGeneratorDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    public static SeqGeneratorDao getInstance(AbstractDao abstractDao) {
        if (_instance == null) {
            _instance = new SeqGeneratorDao(abstractDao);
        }
        return (_instance);
    }

    /**
     * Returns the next value for the specified sequence
     *
     * @param sequenceName
     * @return
     * @throws AccessException
     */
    public long getSequenceNextVal(String sequenceName, boolean withinTxn) throws AccessException {
        return getSequenceNextVal(sequenceName, 1, withinTxn);
    }

    /**
     * Returns the next value for the specified sequence
     *
     * @param sequenceName
     * @return
     * @throws AccessException
     */
    private long getSequenceNextVal(String sequenceName, int seq_val, boolean withinTxn) throws AccessException {
        Logger.debug("Thread " + Thread.currentThread().getName() + "getSequenceNextVal>>>sequenceName>>" + sequenceName);
        long nextVal = -1;
        String sql = SequenceQueries.getSequenceNextMultipleValueQuery();
        QueryParameters params = new QueryParameters();
        params.addParam(sequenceName);
        params.addParam(NEXTVAL);
        params.addParam(seq_val);
        int nRetries = 0;
        int MAX_RETRIES = 7;
        while (nRetries < MAX_RETRIES) {
            try {
                Logger.debug("Thread " + Thread.currentThread().getName() + " has started trans" + sequenceName);
                nextVal = QueryHelper.doQueryForInt(abstractDao, "getSequenceNextVal", sql, params);
                Logger.debug("Thread " + Thread.currentThread().getName() + " fetched sequenceNo: " + nextVal);
                Logger.debug("Thread " + Thread.currentThread().getName() + " committed : " + nextVal);
                break;
            } catch (Exception e) {
                Logger.error(Thread.currentThread().getName() + " sequence rollback >> ", e);
                if (e instanceof CannotAcquireLockException) {
                    ++nRetries;
                } else {
                    throw new AccessException(e);
                }
            }
        }
        if (nextVal == -1) {
            throw new AccessException(ERR_SEQUENCE_VALUE_NOT_FOUND);
        }
        Logger.debug("Thread " + Thread.currentThread().getId() + "Returning " + sequenceName + ".nextVal = " + nextVal);
        return (nextVal);
    }
}