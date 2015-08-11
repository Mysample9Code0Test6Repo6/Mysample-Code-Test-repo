package com.incyyte.app.dao.address;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.incyyte.app.domain.Address;
import com.incyyte.app.domain.Postcode;
import com.incyyte.app.service.util.Logger;

public class AddressLocatorDaoImpl extends HibernateDaoSupport implements AddressLocatorDao{


    public Long addAddress(Address address) {
    	Long id = (Long) getHibernateTemplate().save(address);	    	
    	Logger.debug("New Address ID: "+ id.toString());
		return id;
	}

    public void addAddresses(Collection<Address> addresses){
    	getHibernateTemplate().saveOrUpdateAll(addresses);	    	
	}

    public Postcode getPostcodeById(Long id) {
    	Logger.debug("getPostcode by ID: "+ id.toString());
		return (Postcode) getHibernateTemplate().get(Postcode.class, id);	
	}
    
    public Long addPostcode(Postcode postcode) {
		Long id = (Long)getHibernateTemplate().save(postcode);	
		Logger.debug("New postcode ID: "+ id.toString());
		return id;
	}

    public Collection loadAddresses(String postcode) throws DataAccessException {
    	return getHibernateTemplate().find("from test.Product product where product.category=?", postcode);
    }

	public Postcode isPostcodeInCyyte(String postcode)throws DataAccessException {		
		List postcodes = getHibernateTemplate().find("FROM Postcode where code=?", postcode);
		if (postcodes.size() == 0)
			return null;
		else
			return (Postcode)postcodes.get(0);
	} 
	
	public List<Address> getAddressById(Long id)throws DataAccessException {
		List<Address> addresses = getHibernateTemplate().find("FROM Address where fk_postcodeid=?", id);
		return addresses;   
	}

}
