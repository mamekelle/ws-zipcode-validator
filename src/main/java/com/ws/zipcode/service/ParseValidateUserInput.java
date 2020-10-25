package com.ws.zipcode.service;

import com.ws.zipcode.domain.ZipcodePair;

import java.util.Set;

public interface ParseValidateUserInput {
    Set<ZipcodePair> parseAndValidateUserInput(String[] args);
}
