package at.cgsit.a_jfxintro.person.repository;


import at.cgsit.a_jfxintro.person.core_model.PersonEntity;

import java.time.LocalDate;

public class InMemoryPersonRepository implements PersonRepository {

    private PersonEntity stored;

    public InMemoryPersonRepository() {
        // Demo-Daten
        stored = new PersonEntity();
        stored.setId(1L);
        stored.setVorname("Max");
        stored.setNachname("Mustermann");
        stored.setGeburtsdatum(LocalDate.of(1990, 1, 1));
        stored.setEmail("max@example.com");
    }

    @Override
    public PersonEntity loadById(long id) {
        // InMemory simuliert nur ein einziges Objekt
        if (stored.getId() == id) {
            return clonePerson(stored); // wichtig: KEIN direkter Rückgabe-Ref
        }
        return null;
    }

    @Override
    public PersonEntity save(PersonEntity person) {
        // z. B. ID generieren (Simulation)
        if (person.getId() == null) {
            person.setId(1L);
        }
        // InMemory: Clone speichern, um Mutationen zu vermeiden
        stored = clonePerson(person);
        return clonePerson(stored);
    }

    private PersonEntity clonePerson(PersonEntity p) {
        PersonEntity c = new PersonEntity();
        c.setId(p.getId());
        c.setVorname(p.getVorname());
        c.setNachname(p.getNachname());
        c.setGeburtsdatum(p.getGeburtsdatum());
        c.setEmail(p.getEmail());
        return c;
    }
}
