package com.incyyte.app.service.Exceptions;

/**
 * @author Dev1
 *
 */
public class UnavailableUserNameException extends IncyyteRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnavailableUserNameException(String message) {
		super(message);
	}
	
	public UnavailableUserNameException(String message, Throwable t) {
		super(message, t);
	}
}
