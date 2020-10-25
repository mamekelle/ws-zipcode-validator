package com.ws.zipcode.service;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;

import java.util.Set;

public interface ParseValidateUserInput {
    Set<ZipcodePair> parseAndValidateUserInput(String[] args) throws ZipCodeException;
}
