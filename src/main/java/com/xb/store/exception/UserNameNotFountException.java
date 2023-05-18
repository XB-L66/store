package com.xb.store.exception;

public class UserNameNotFountException extends ServiceException{
    public UserNameNotFountException() {
        super();
    }

    public UserNameNotFountException(String message) {
        super(message);
    }

    public UserNameNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameNotFountException(Throwable cause) {
        super(cause);
    }

    protected UserNameNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
