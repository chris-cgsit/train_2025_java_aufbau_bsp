package at.cgsit.javafx.tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonModel {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty firstName = new SimpleStringProperty();
    private final SimpleStringProperty lastName = new SimpleStringProperty();
    private final SimpleIntegerProperty age = new SimpleIntegerProperty();

    public PersonModel(int id, String firstName, String lastName, int age) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.age.set(age);
    }

    public int getId() { return id.get(); }
    public SimpleIntegerProperty idProperty() { return id; }

    public String getFirstName() { return firstName.get(); }
    public SimpleStringProperty firstNameProperty() { return firstName; }

    public String getLastName() { return lastName.get(); }
    public SimpleStringProperty lastNameProperty() { return lastName; }

    public int getAge() { return age.get(); }
    public SimpleIntegerProperty ageProperty() { return age; }
}
