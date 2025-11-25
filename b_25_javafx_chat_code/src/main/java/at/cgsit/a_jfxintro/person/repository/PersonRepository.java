package at.cgsit.a_jfxintro.person.repository;


import at.cgsit.a_jfxintro.person.core_model.PersonEntity;

public interface PersonRepository {

    PersonEntity loadById(long id);

    /**
     * Speichert die Person und gibt die gespeicherte/aktualisierte
     * Entity zurück (z.B. um generierte IDs zurückzugeben).
     */
    PersonEntity save(PersonEntity person);
}
