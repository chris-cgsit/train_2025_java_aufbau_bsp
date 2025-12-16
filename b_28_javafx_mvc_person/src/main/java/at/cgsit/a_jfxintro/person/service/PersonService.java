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

package at.cgsit.a_jfxintro.person.service;


import at.cgsit.a_jfxintro.person.core_model.PersonEntity;
import at.cgsit.a_jfxintro.person.exceptions.ValidationException;
import at.cgsit.a_jfxintro.person.repository.PersonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    private final PersonRepository repo;

    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    public PersonEntity load(long id) {
        return repo.loadById(id);
    }

    public PersonEntity save(PersonEntity entity) {
        List<String> errors = validate(entity);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        return repo.save(entity);
    }

    public List<String> validate(PersonEntity p) {
        List<String> errors = new ArrayList<>();

        if (p.getVorname() == null || p.getVorname().isBlank())
            errors.add("Vorname darf nicht leer sein.");

        if (p.getNachname() == null || p.getNachname().isBlank())
            errors.add("Nachname darf nicht leer sein.");

        if (p.getGeburtsdatum() == null)
            errors.add("Geburtsdatum ist erforderlich.");
        else if (p.getGeburtsdatum().isAfter(LocalDate.now()))
            errors.add("Geburtsdatum darf nicht in der Zukunft liegen.");

        if (p.getEmail() == null || p.getEmail().isBlank())
            errors.add("Email darf nicht leer sein.");
        else if (!p.getEmail().contains("@"))
            errors.add("Ungültige Email-Adresse.");

        return errors;
    }
}
