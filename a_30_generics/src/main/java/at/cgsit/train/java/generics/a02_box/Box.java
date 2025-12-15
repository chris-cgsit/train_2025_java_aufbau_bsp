package at.cgsit.train.java.generics.a02_box;

/**
 * Box mit generic type T
 * @param <T>
 */
public class Box<T> {

    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

