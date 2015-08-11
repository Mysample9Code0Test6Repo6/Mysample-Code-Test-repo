/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class implements the addressLocator services
 * This class will delegate services to the addressLocator manager
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.service;

import java.util.List;

import com.incyyte.app.domain.Address;
import com.incyyte.app.manager.AddressLocatorManager;
import com.incyyte.app.service.Exceptions.PostcodeNotFoundException;

public class AddressLocatorServiceImpl implements AddressLocatorService {

	AddressLocatorManager addressMgr;
	
	public List<Address> getAddressList(String postcode)throws PostcodeNotFoundException{
		return addressMgr.getAddressList(postcode);
	}

	public void setAddressMgr(AddressLocatorManager addressMgr) {
		this.addressMgr = addressMgr;
	}

}
