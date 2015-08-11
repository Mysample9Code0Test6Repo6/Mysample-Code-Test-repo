/**
 * 
 */
package com.incyyte.app.service.Exceptions;

import org.springframework.dao.DataAccessException;

/**
 * @author prakash
 *
 */
public class IncyyteRuntimeException extends DataAccessException {
	
	/**
	 * default UID
	 */
	private static final long serialVersionUID = 1L;

	public IncyyteRuntimeException(String message) {
		super(message);
	}
	
    public IncyyteRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
	

}
