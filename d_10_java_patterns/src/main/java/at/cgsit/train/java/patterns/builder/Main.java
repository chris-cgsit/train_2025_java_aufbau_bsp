package at.cgsit.train.java.patterns.builder;

public class Main {

  static void main() {

    User user = new User.Builder()
        .name("Chris")
        .email("cgs@example.com")
        .age(40)
        .newsletter(true)
        .build();

  }
}
