package at.cgsit.a_jfxintro.table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty firstName = new SimpleStringProperty();
    private final SimpleStringProperty lastName = new SimpleStringProperty();
    private final SimpleIntegerProperty age = new SimpleIntegerProperty();

    public Person(String firstName, String lastName, int age) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.age.set(age);
    }

    public String getFirstName() { return firstName.get(); }
    public SimpleStringProperty firstNameProperty() { return firstName; }

    public String getLastName() { return lastName.get(); }
    public SimpleStringProperty lastNameProperty() { return lastName; }

    public int getAge() { return age.get(); }
    public SimpleIntegerProperty ageProperty() { return age; }

    @Override
    public String toString() {
        return "Person{" +
                "firstName=" + firstName.get() +
                ", lastName=" + lastName.get() +
                ", age=" + age.get() +
                '}';
    }
}
