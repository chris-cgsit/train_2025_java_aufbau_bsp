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


