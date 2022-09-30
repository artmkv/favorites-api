package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.AbstractMVCTest;

import java.util.UUID;

public abstract class AbstractServiceTest extends AbstractMVCTest {

    /**
     * UserId in type UUID
     */
    protected UUID userIdUUID;

    /**
     * Constructor
     */
    protected AbstractServiceTest() {

        this.userIdUUID = UUID.fromString(stringUserId);
    }
}
