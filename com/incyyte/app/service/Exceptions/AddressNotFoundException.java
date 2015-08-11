package com.incyyte.app.service.Exceptions;

public class AddressNotFoundException extends InCyyteExceptions {
    public AddressNotFoundException() { }
    public AddressNotFoundException(String message) { super(message); }
    public AddressNotFoundException(Throwable cause) { super(cause); } 
}
