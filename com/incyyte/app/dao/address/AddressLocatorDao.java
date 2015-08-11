package com.incyyte.app.dao.address;

import java.util.Collection;
import java.util.List;

import com.incyyte.app.domain.Address;
import com.incyyte.app.domain.Postcode;


public interface AddressLocatorDao {
	
    public Long addAddress(Address address); 
    public void addAddresses(Collection<Address> addresses); 
    public Long addPostcode(Postcode postcode);
    public Postcode getPostcodeById(Long id);    
    public List<Address> getAddressById(Long id);    
    public Postcode isPostcodeInCyyte(String postcode);
    

}
