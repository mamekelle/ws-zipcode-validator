package com.ws.zipcode.exception;

/**
 * Custom exception class by extending the root exception class and
 * overriding its basic constructors for now I just used the message method of exception class
 */
public class ZipCodeException extends Exception {
    public ZipCodeException(String msg){super(msg);}
}
