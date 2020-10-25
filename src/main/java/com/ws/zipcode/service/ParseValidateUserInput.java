package com.ws.zipcode.service;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;

import java.util.Set;

/**
 * Interface for accepting the args from the main class and validates with the given pattern from utils class
 * then creates a unique set
 */
public interface ParseValidateUserInput {
    /**
     * the given args inputs from the user and then returns the distinct pair
     * if it doesn't contain any zip code exceptions
     * @param args passed from main class user's input
     * @return return as a set of distinct and valid pairs
     * @throws ZipCodeException else throws exception
     */
    Set<ZipcodePair> parseAndValidateUserInput(String[] args) throws ZipCodeException;
}
