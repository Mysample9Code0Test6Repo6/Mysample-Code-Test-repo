package com.incyyte.app.service.Exceptions;

public class AuthenticationException extends InCyyteExceptions {
    public AuthenticationException() { }
    public AuthenticationException(String message) { super(message); }
    public AuthenticationException(Throwable cause) { super(cause); } 
}
