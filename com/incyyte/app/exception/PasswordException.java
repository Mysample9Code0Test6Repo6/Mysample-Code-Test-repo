package com.incyyte.app.exception;

public class PasswordException extends Exception {
    private static final long serialVersionUID = 1L;

    public PasswordException() {
    }

    public PasswordException(String message) {
        super(message);
    }

    public PasswordException(String errorCode, String exception) {
        super(errorCode + " : " + exception);
    }

    public PasswordException(Throwable cause) {
        super(cause);
    }

    public PasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}

