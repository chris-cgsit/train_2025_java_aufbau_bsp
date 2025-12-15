package at.cgsit.train.java.generics.a03_person;

import java.util.UUID;

public class PersonMain {

  static void main() {


    Person<Long, String> p1 = new Person<>(1L, "Max Mustermann");

    // oder
    Person<UUID, Integer> p2 = new Person<>(UUID.randomUUID(), 42);



  }

}
