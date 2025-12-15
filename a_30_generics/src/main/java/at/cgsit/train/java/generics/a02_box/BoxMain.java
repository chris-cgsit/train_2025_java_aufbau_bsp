package at.cgsit.train.java.generics.a02_box;

public class BoxMain {

  static void main() {

    Box<String> stringBox = new Box<>();
    stringBox.set("Hello");

    String s = stringBox.get(); // kein Cast nötig

    Box<Integer> intBox = new Box<>();
    intBox.set(42);

    Integer i = intBox.get();

  }

}
