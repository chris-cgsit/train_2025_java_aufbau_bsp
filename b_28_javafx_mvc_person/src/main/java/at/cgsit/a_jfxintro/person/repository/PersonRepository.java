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
