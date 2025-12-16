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

package at.cgsit.train.java.patterns.builder;

public class User {
    private final String name;
    private final String email;
    private final int age;
    private final boolean newsletter;

    private User(Builder b) {
        this.name = b.name;
        this.email = b.email;
        this.age = b.age;
        this.newsletter = b.newsletter;
    }

    public static class Builder {
        private String name;
        private String email;
        private int age = 0;
        private boolean newsletter = false;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        public Builder newsletter(boolean newsletter) {
            this.newsletter = newsletter;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
