package at.cgsit.a_jfxintro.person;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

public class Person {

    private final StringProperty vorname = new SimpleStringProperty();
    private final StringProperty nachname = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> geburtsdatum = new SimpleObjectProperty<>();
    private final StringProperty email = new SimpleStringProperty();

    public StringProperty vornameProperty() { return vorname; }
    public String getVorname() { return vorname.get(); }
    public void setVorname(String s) { vorname.set(s); }

    public StringProperty nachnameProperty() { return nachname; }
    public String getNachname() { return nachname.get(); }
    public void setNachname(String s) { nachname.set(s); }

    public ObjectProperty<LocalDate> geburtsdatumProperty() { return geburtsdatum; }
    public LocalDate getGeburtsdatum() { return geburtsdatum.get(); }
    public void setGeburtsdatum(LocalDate d) { geburtsdatum.set(d); }

    public StringProperty emailProperty() { return email; }
    public String getEmail() { return email.get(); }
    public void setEmail(String s) { email.set(s); }
}
