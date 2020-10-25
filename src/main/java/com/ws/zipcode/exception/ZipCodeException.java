package com.ws.zipcode.exception;

/**
 * Custom exception class by extending the root exception class and
 * overriding its basic constructors
 */
public class ZipCodeException extends Exception {
    public ZipCodeException(){super();}
    public ZipCodeException(Throwable t){super(t);}
    public ZipCodeException(String msg){super(msg);}
}
