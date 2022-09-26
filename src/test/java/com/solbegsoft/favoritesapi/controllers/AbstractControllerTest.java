package com.solbegsoft.favoritesapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

/**
 * Abstract class for Controller Test
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class AbstractControllerTest {

    /**
     * @see MockMvc
     */
    @Autowired
    protected MockMvc mockMvc;

    /**
     * @see ObjectMapper
     */
    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * UserId before Authentication
     */
    protected String stringUserId;

    /**
     * UserId in type UUID
     */
    protected UUID userIdUUID;

    /**
     * Constructor
     *
     * @param stringUserId string User Id
     */
    protected AbstractControllerTest(String stringUserId) {
        this.stringUserId = stringUserId;
        this.userIdUUID = UUID.fromString(stringUserId);
    }
}
