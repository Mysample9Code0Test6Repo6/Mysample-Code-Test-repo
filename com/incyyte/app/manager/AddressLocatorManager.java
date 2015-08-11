/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * The manager interface provides access to the implementer class that
 * manages the actual process
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 30 Dec 2010
 */
package com.incyyte.app.manager;

import java.util.List;

import com.incyyte.app.domain.Address;
import com.incyyte.app.service.Exceptions.PostcodeNotFoundException;

public interface AddressLocatorManager {
	
	public List<Address> getAddressList(String postcode) throws PostcodeNotFoundException;


}
