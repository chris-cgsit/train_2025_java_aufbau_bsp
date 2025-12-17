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

/**
 * bei genertischen typen ist immer extends zu verwenden egal ob
 * es sich auf eine Klasse oder ein Interface bezieht.
 * das objekt das da verwendet wird muss das Interface implmeentieren
 * oder die Klasse erwetern/extends.
 *
 * @param <T> siehe text
 */
public class Club<T extends PersonInterface> {

    private final T member;

    public Club(T member) {
        this.member = member;
    }

    public void printName() {

        this.member.getName();
        System.out.println(member.getName());

    }
}
