/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.a_jfxintro.person;

import at.cgsit.a_jfxintro.person.core_model.PersonEntity;
import at.cgsit.a_jfxintro.person.exceptions.ValidationException;
import at.cgsit.a_jfxintro.person.service.PersonService;
import javafx.beans.property.*;

import java.time.LocalDate;

public class PersonViewModel {

    private final PersonService service;
    private PersonEntity entity; // Single Source of Truth

    // JavaFX Properties
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty vorname = new SimpleStringProperty();
    private final StringProperty nachname = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> geburtsdatum = new SimpleObjectProperty<>();
    private final StringProperty email = new SimpleStringProperty();

    private final StringProperty statusMessage = new SimpleStringProperty();

    public PersonViewModel(PersonService service) {
        this.service = service;
        this.entity = new PersonEntity(); // initial empty entity
    }

    // Property getters for UI binding
    public LongProperty idProperty() { return id; }
    public StringProperty vornameProperty() { return vorname; }
    public StringProperty nachnameProperty() { return nachname; }
    public ObjectProperty<LocalDate> geburtsdatumProperty() { return geburtsdatum; }
    public StringProperty emailProperty() { return email; }
    public ReadOnlyStringProperty statusMessageProperty() { return statusMessage; }


    public void setStatusMessage(String msg) {
      statusMessage.set(msg);
    }

    // --- Entity -> Properties ---
    public void fromEntity(PersonEntity e) {
        this.entity = e;

        id.set(e.getId() != null ? e.getId() : 0);
        vorname.set(e.getVorname());
        nachname.set(e.getNachname());
        geburtsdatum.set(e.getGeburtsdatum());
        email.set(e.getEmail());
    }

    // --- Properties -> Entity ---
    public void toEntity() {
        entity.setId(id.get() == 0 ? null : id.get());
        entity.setVorname(vorname.get());
        entity.setNachname(nachname.get());
        entity.setGeburtsdatum(geburtsdatum.get());
        entity.setEmail(email.get());
    }


    // --- Business-Actions ---

    public void load(long personId) {
        entity = service.load(personId);
        fromEntity(entity);
        statusMessage.set("Person #" + personId + " geladen.");
    }

    public void save() {
        try {
            toEntity();
            entity = service.save(entity); // might return updated entity
            fromEntity(entity); // sync again
            statusMessage.set("Person gespeichert.");
        } catch (ValidationException ve) {
            statusMessage.set("Fehler: " + String.join(" | ", ve.getErrors()));
        } catch (Exception ex) {
            statusMessage.set("Unerwarteter Fehler: " + ex.getMessage());
        }
    }
}
