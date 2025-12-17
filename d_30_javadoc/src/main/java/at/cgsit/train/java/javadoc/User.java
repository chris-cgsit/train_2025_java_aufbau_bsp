/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.train.java.javadoc;

/**
 * User entity.
 *
 * @see UserService
 */
public class User {

    private final String username;

    /**
     * Creates a new user.
     *
     * @param username unique username
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }
}
