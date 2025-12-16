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

package at.cgsit.train.java.mv.personen;

/**
 * Subklasse Kunde, abgeleitet von Person.
 * Enthält zusätzliche Felder für Kundennummer und Jahresumsatz.
 */
public class Kunde extends Person {

    /**
     * Eindeutige Kundennummer des Kunden, z.B. "K-2025-001".
     */
    private String kundenNummer;

    /**
     * Erwarteter Jahresumsatz des Kunden in Euro.
     */
    private double jahresUmsatzErwartet;

    // Konstruktor
    public Kunde(String vorname, String nachname, String email, 
                 String kundenNummer, double jahresUmsatzErwartet) {
        super(vorname, nachname, email);
        this.kundenNummer = kundenNummer;
        this.jahresUmsatzErwartet = jahresUmsatzErwartet;
    }

    // Getter und Setter
    public String getKundenNummer() {
        return kundenNummer;
    }

    public void setKundenNummer(String kundenNummer) {
        this.kundenNummer = kundenNummer;
    }

    public double getJahresUmsatzErwartet() {
        return jahresUmsatzErwartet;
    }

    public void setJahresUmsatzErwartet(double jahresUmsatzErwartet) {
        this.jahresUmsatzErwartet = jahresUmsatzErwartet;
    }

    // Überschriebene toString-Methode
    @Override
    public String toString() {
        return super.toString() + String.format(", Kundennummer: %s, Jahresumsatz: %.2f", 
                 kundenNummer, jahresUmsatzErwartet);
    }
}
