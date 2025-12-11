package at.cgsit.train.java.patterns.sealed.bsp;

public class MainSealedDemo {

  static void main() {

  }

  static int calc(Operation op) {
    return switch (op) {
      case Add a -> a.a() + a.b();
      case Multiply m -> m.a() * m.b();
    };
  }

}
