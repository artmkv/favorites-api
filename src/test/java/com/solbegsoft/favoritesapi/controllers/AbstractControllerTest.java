package com.solbegsoft.favoritesapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Abstract class for Controller Test
 */
public abstract class AbstractControllerTest extends AbstractMVCTest {

    /**
     * @see ObjectMapper
     */
    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * UserId before Authentication
     */
    protected static final String stringUserId = "d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4";

    /**
     * UserId in type UUID
     */
    protected UUID userIdUUID;

    /**
     * Constructor
     */
    protected AbstractControllerTest() {
        this.userIdUUID = UUID.fromString(stringUserId);
    }
}
