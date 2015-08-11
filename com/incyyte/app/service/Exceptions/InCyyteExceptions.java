package com.incyyte.app.service.Exceptions;


/*
 * Supper Class InCyyte Exceptions
 * All Exceptions must extend this Class
 */
public class InCyyteExceptions extends Exception {
   
	private static final long serialVersionUID = -8612781878260166424L;
	public InCyyteExceptions() { }
    public InCyyteExceptions(String message) { super(message); }
    public InCyyteExceptions(Throwable cause) { super(cause); } 
    public InCyyteExceptions(String message, Throwable cause) { super(message, cause); } 
}
