package com.incyyte.app.service.Exceptions;


public class AccountExistException extends RuntimeException {
    public AccountExistException() { }
    public AccountExistException(String message) { super(message); }
    public AccountExistException(Throwable cause) { super(cause); } 

}
