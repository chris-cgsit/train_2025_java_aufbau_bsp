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

package at.cgsit.train.java.mv.firma.schnittstellen;

import at.cgsit.train.java.mv.personen.Abteilung;
import at.cgsit.train.java.mv.personen.Mitarbeiter;

import java.util.List;
import java.util.Map;

/**
 * Interface für Mitarbeiter-bezogene Verwaltungsoperationen.
 */
public interface MitarbeiterManagement {

    /**
     * Listet alle Mitarbeiter einer bestimmten Abteilung auf.
     *
     * @param abteilung@return Eine Liste von Mitarbeitern der Abteilung
     */
    List<Mitarbeiter> mitarbeiterEinerAbteilung(Abteilung abteilung);

    /**
     * Berechnet das durchschnittliche Gehalt aller Mitarbeiter.
     *
     * @return Der Durchschnitt der monatlichen Gehälter aller Mitarbeiter.
     */
    double durchschnittsGehalt();

    /**
     * Zählt die Anzahl der Mitarbeiter pro Abteilung.
     *
     * @return Eine Map, die die Anzahl pro Abteilung enthält.
     */
    public Map<Abteilung, Long>  anzahlMitarbeiterProAbteilung();
}
