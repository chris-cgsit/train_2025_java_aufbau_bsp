package at.cgsit.train.java.mv;

import at.cgsit.train.java.mv.firma.Firma;
import at.cgsit.train.java.mv.firma.implementierung.FirmaImplIterator;
import at.cgsit.train.java.mv.firma.implementierung.FirmaImplStream;
import at.cgsit.train.java.mv.personen.Abteilung;
import at.cgsit.train.java.mv.personen.Kunde;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import at.cgsit.train.java.mv.personen.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {

  // Helper-Methode zum Erstellen von Beispieldaten
  private static List<Person> erstelleBeispieldaten() {
    List<Person> personen = new ArrayList<>();
    // Annahme: Person ist eine Klasse mit getVorname() und getNachname()
    // Mitarbeiter und Kunden erstellen
    Mitarbeiter m1 = new Mitarbeiter("Anna", "Schmidt", "anna.schmidt@example.com",
        Mitarbeiter.Beschaeftigungsart.VOLLZEIT, 4000);
    Mitarbeiter m2 = new Mitarbeiter("Peter", "Müller", "peter.mueller@example.com",
        Mitarbeiter.Beschaeftigungsart.TEILZEIT, 2000);
    Kunde k1 = new Kunde("Franz", "Kaiser", "franz.kaiser@example.com", "K-2025-001", 50000);
    Kunde k2 = new Kunde("Sabine", "Meier", "sabine.meier@example.com", "K-2025-002", 75000);

    personen.add(m1);
    personen.add(m2);
    personen.add(k1);
    personen.add(k2);

    return personen;
  }


  public static void main(String[] args) {

    // 1. Umgebungsvariable auslesen und normalisieren. setze das beim programm aufruf
    // System.getenv() liest OS-Umgebungsvariablen
    String implAuswahl = System.getenv("FIRMA_IMPL");

    // Konvertiere zu Großbuchstaben und verwende 'S' als Standard, falls null oder leer
    String auswahl = (implAuswahl != null && !implAuswahl.trim().isEmpty())
        ? implAuswahl.trim().toUpperCase()
        : "S"; // S = Stream ist der Standard (Default)

    Firma firma;

    // 2. Implementierung basierend auf der Auswahl initialisieren
    if (auswahl.equals("I")) {
      // Annahme: FirmaImplIterator existiert
      firma = new FirmaImplIterator("Testfirma Iterator Impl");
      System.out.println("Implementierung: Iterator (basierend auf FIRMA_IMPL=I)");
    } else {
      // Dies deckt 'S' und alle anderen ungültigen/fehlenden Werte ab (Stream-Default)
      firma = new FirmaImplStream("Testfirma Stream Impl");
      System.out.println("Implementierung: Stream (basierend auf FIRMA_IMPL=" + auswahl + " oder Default)");
    }

    // füge initale testdaten in die Firma ein die von der Hilfsmetode generiert wurden
    List<Person> people = erstelleBeispieldaten();
    for (Person person : people) {
       firma.addPerson(person);
    }


    // Beispielhafte Abfragen:
    System.out.println("Durchschnittsgehalt: " + firma.durchschnittsGehalt());

    // System.out.println("Gesamtumsatz der Kunden: " + firma.gesamtUmsatzKunden());

    System.out.println("Personen mit 'Meier': " + firma.findByNachname("Meier"));
    System.out.println("Mitarbeiter (Teilzeit): " + firma.mitarbeiterEinerAbteilung(Abteilung.IT));
  }
}