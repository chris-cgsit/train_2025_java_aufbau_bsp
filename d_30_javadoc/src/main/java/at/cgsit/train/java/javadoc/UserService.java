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
 * Service for {@link User} management.
 */
public class UserService {

    /**
     * Prints user information.
     *
     * @param user user instance
     */
    public void print(User user) {
        System.out.println(user.getUsername());
    }
}
