package at.cgsit.train.java.generics.a05_interfaces;

public class InterfacesMain {

  static void main() {

    Repository<Person> repo = new PersonRepository();
    Person p = repo.findById(1L);
  }

}
