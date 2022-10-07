package com.solbegsoft.favoritesapi.services.impl;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

/**
 * Abstract Service Test
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class AbstractServiceTest {

    /**
     * UserId in type UUID
     */
    protected UUID userIdUUID;

    /**
     * UserId before Authentication
     */
    protected static final String stringUserId = "d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4";

    /**
     * Constructor
     */
    protected AbstractServiceTest() {
        this.userIdUUID = UUID.fromString(stringUserId);
    }
}
