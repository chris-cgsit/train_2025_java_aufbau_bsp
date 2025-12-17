package at.cgsit.train.java.generics.a02_box;


@FunctionalInterface
public interface MyAccept<T> {

    public void accept(T in);

}
