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

package at.cgsit.train.java.generics.a04_extends;

public class Club<T extends Person> {

    private final T member;

    public Club(T member) {
        this.member = member;
    }

    public void printName() {
        System.out.println(member.getName());
    }
}
