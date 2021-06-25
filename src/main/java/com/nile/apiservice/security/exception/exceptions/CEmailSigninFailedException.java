package com.nile.apiservice.security.exception.exceptions;

public class CEmailSigninFailedException extends RuntimeException {
    public CEmailSigninFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CEmailSigninFailedException(String msg) {
        super(msg);
    }

    public CEmailSigninFailedException() {
        super();
    }
}