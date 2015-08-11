package com.incyyte.app.dao.core.txn;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.incyyte.app.service.util.Logger;

public class TxnHelper implements ITxnHelper
{
    private PlatformTransactionManager txnManager = null;
    
    private static ThreadLocal<TransactionStatus> txnStatus 		  = new ThreadLocal<TransactionStatus>();
    private TransactionDefinition txnDefinition   = null;    
    
    
    /**
     * 
     */
    public TxnHelper()
    {
        super();
        initialize();
    }
    
    
    protected void initialize()
    {
    	//SaaSBeanConfigFactory beanFactory = SaaSBeanConfigFactory.getInstance();
    	//txnManager = (PlatformTransactionManager)(beanFactory.getBean("transactionManager"));
    	//txnManager = (PlatformTransactionManager)BeanFactory.getInstance().getTxnManager(Constants.TXN_MANAGER);		

        this.txnDefinition = getTransactionDefinition();
        showTransactionDefinition(txnDefinition);        
    }
       
    /**
     * Wrapper, that will create the transaction
     *
     */
    public void beginTxn()
    {
    	
    	TransactionStatus status = getTxnManager().getTransaction(getTxnDefinition());
    	Logger.debug("Executor beginTrans>> " + Thread.currentThread().getName()  + " >> transactionStatus:: " + status.hashCode() + " >> " +status.toString() );
    	
        setTxnStatus(status);
    }
    
    /**
     * Wrapper, that will commit the transaction
     *
     */
    public void commitTxn()
    {
    	TransactionStatus status = getTxnStatus();
    	Logger.debug("Executor commitTrans  >> " + Thread.currentThread().getName() + " >> transactionStatus:: " + status.hashCode() + " >> " +status.toString() );
        getTxnManager().commit(status);
    }
    
    /**
     * Wrapper, that will rollback the transaction
     *
     */
    public void rollbackTxn()
    {
    	TransactionStatus status = getTxnStatus();
    	Logger.debug("Executor rollbackTrans  >> " + Thread.currentThread().getName() + " >> transactionStatus:: " + status.hashCode() + " >> " +status.toString() );
        getTxnManager().rollback(status);
    }
    
    /**
     * Returns a default TransactionDefinition; this default TransactionDefinition
     * could be modified specific to the application needs.
     * 
     * @return TransactionDefinition - default TransactionDefinition
     */
    protected TransactionDefinition getTransactionDefinition()
    {
        TransactionDefinition td = new DefaultTransactionDefinition();
        
        ((DefaultTransactionDefinition)td).setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);     
        
		// set the transaction isolation level to REPEATABLE_READ
		//((DefaultTransactionDefinition)td).setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);  
		Logger.debug("setting the transation isolation level to " + TransactionDefinition.ISOLATION_READ_COMMITTED);
        return (td);
    }
    
    public TransactionDefinition getTxnDefinition()
    {
        return txnDefinition;
    }
    
    public void setTxnDefinition(TransactionDefinition txnDefinition)
    {
        this.txnDefinition = txnDefinition;
    }
    
    public PlatformTransactionManager getTxnManager()
    {
        return txnManager;
    }
    
    public void setTxnManager(PlatformTransactionManager aTxnManager)
    {
        this.txnManager = aTxnManager;
    }
    
    public TransactionStatus getTxnStatus()
    {
    	Logger.debug("Executor getTxnStatus  >> " + Thread.currentThread().getName() + " >> transactionStatus:: " + txnStatus.get().hashCode() );
        return txnStatus.get();
    }
    
    public void setTxnStatus(TransactionStatus txnStatus)
    {
    	Logger.debug("Executor setTxnStatus  >> " + Thread.currentThread().getName() + " >> transactionStatus:: " + txnStatus.hashCode() );
        this.txnStatus.set(txnStatus);
    }
    
    private void showTransactionDefinition(TransactionDefinition aTxnDefinition)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(" ===== TransactionDefinition Settings ====");
        if (aTxnDefinition != null)
        {
            sb.append("    Isolation Level = ").append(aTxnDefinition.getIsolationLevel());        
	        sb.append("    Propogation Behavior = ").append(aTxnDefinition.getPropagationBehavior());
	        sb.append("    Name = ").append(aTxnDefinition.getName());
	        sb.append("    Timeout = ").append(aTxnDefinition.getTimeout());
        }
    
        sb.append("\n");
	        
        Logger.debug(sb.toString());
    }
} //end, class TxnHelper
