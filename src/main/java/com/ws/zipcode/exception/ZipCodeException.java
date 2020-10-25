package com.ws.zipcode.exception;

public class ZipCodeException extends Exception {
    public ZipCodeException(){super();}
    public ZipCodeException(Throwable t){super(t);}
    public ZipCodeException(String msg){super(msg);}
}
