package at.cgsit.train.java.generics.a05_interfaces;

public class PersonRepository
        implements Repository<Person> {

    @Override
    public Person findById(long id) {
        return new Person("Max");
    }

    @Override
    public void save(Person entity) {
        // speichern
    }
}
