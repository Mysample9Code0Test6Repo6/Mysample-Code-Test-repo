/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This service interface provides Address Location services
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
import com.incyyte.app.service.Exceptions.PostcodeNotFoundException;

public interface AddressLocatorService {
	
	public List<Address> getAddressList(String postcode)throws PostcodeNotFoundException;


}
