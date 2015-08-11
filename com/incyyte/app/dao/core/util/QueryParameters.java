
package com.incyyte.app.dao.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class QueryParameters
{
    List params = null;
    
    /**
     * 
     */
    public QueryParameters()
    {
        super();
    }

    public void addParam(Object o)
    {
        getParams().add(o);
    }
    
    public List getParams()
    {
        if (params == null)
            params = new ArrayList();
        
        return params;
    }
    
    public void setParams(List params)
    {
        this.params = params;
    }
    

    public String asString()
    {
        return (getParams() == null ? "<null>" : getParams().toString());
    }
    

    public Object[] toArray()
    {
        return (getParams().toArray());
    }
    

    public int getParamsCount()
    {
        return (getParams().size());
    }

    public void addParams(List newParams){
    	if(newParams!=null){
    		Iterator it = newParams.iterator();
    		while(it.hasNext()){
    			getParams().add(it.next());
    		}
    	}
    }
    
} //end, class CpSrvQueryParameters
