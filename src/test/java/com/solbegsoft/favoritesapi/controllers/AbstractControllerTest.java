package com.solbegsoft.favoritesapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.favoritesapi.AbstractMVCTest;
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
     * UserId in type UUID
     */
    protected UUID userIdUUID;

    /**
     * Constructor
     */
    protected AbstractControllerTest() {
        this.userIdUUID = UUID.fromString(stringUserId);
    }

    /**
     * Get End Point
     *
     * @return String
     */
    protected abstract String getEndPoint();

    /**
     * Get End Point with "/"
     * @param id ID in URl
     * @return String
     */
    protected String getEndPointWithBeerId(UUID id) {
        return getEndPoint() + "/" + id;
    }
}
