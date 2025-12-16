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

package at.cgsit.train.java.generics.a03_person;

public class Person<T, R> {

    private T id;
    private R data;

    public Person(T id, R data) {
        this.id = id;
        this.data = data;
    }

    public T getId() {
        return id;
    }

    public R getData() {
        return data;
    }
}


