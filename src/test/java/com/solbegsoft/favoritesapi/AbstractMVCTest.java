package com.solbegsoft.favoritesapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Abstract Controller Validation Test
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class AbstractMVCTest {

    /**
     * @see MockMvc
     */
    @Autowired
    protected MockMvc mockMvc;

    /**
     * UserId before Authentication
     */
    protected static final String stringUserId = "d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4";
}