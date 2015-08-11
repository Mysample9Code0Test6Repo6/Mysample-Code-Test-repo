package com.incyyte.app.service.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

public class BeanFactory 
{
	private static BeanFactory _instance = null;
	private ApplicationContext ctx = new ClassPathXmlApplicationContext(Constants.APP_CTX_LOCATION);
	
    protected BeanFactory(){
        super();
    }
    
    public static BeanFactory getInstance(){
    	if (_instance == null){
    		_instance = new BeanFactory();
    	}
    	return (_instance);
    }
    
	public PlatformTransactionManager getTxnManager(String beanName){
		return (PlatformTransactionManager) ctx.getBean(beanName);
	}


}
