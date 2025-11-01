package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.personen.Kunde;
import at.cgsit.train.java.mv.personen.Mitarbeiter;
import at.cgsit.train.java.mv.personen.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Klasse Firma verwaltet eine Liste von Personen (Mitarbeiter und Kunden).
 * Ermöglicht das Hinzufügen, Entfernen, Suchen und Auswerten von Personen.
 */
public class Firma implements PersonManager {
    private final List<Person> personen = new ArrayList<>(); // Liste von Personen

    // Fügt eine neue Person zur Liste hinzu
    public void addPerson(Person p) {
        personen.add(p);
    }

    /**
     * Entfernt eine Person anhand ihrer ID mit der Verwendung von Streams.
     *
     * @param id Die ID der Person, die entfernt werden soll.
     * @return true, wenn die Person erfolgreich entfernt wurde, ansonsten false.
     */
    public boolean removeByIdWithStreams(String id) {
        int initialSize = personen.size();
        personen.removeIf(person -> person.getId().equals(id));
        return initialSize > personen.size();
    }

    /**
     * Entfernt eine Person anhand ihrer ID mit einem Iterator.
     *
     * @param id Die ID der Person, die entfernt werden soll.
     * @return true, wenn die Person erfolgreich entfernt wurde, ansonsten false.
     */
    public boolean removeByIdWithIterator(String id) {
        Iterator<Person> iterator = personen.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Entfernt eine Person anhand ihrer ID durch manuelles Durchlaufen der Liste.
     *
     * @param id Die ID der Person, die entfernt werden soll.
     * @return true, wenn die Person erfolgreich entfernt wurde, ansonsten false.
     */
    public boolean removeByIdWithManualIteration(String id) {
        for (int i = 0; i < personen.size(); i++) {
            if (personen.get(i).getId().equals(id)) {
                personen.remove(i);
                return true;
            }
        }
        return false;
    }

    // Sucht eine Person anhand ihrer ID
    public Optional<Person> findById(String id) {
        return personen.stream()
                       .filter(person -> person.getId().equals(id))
                       .findFirst();
    }

    // Sucht Personen anhand eines Teilstrings im Namen
    public List<Person> findByName(String teil) {
        return personen.stream()
                       .filter(person -> (person.getVorname() + " " + person.getNachname())
                                         .toLowerCase()
                                         .contains(teil.toLowerCase()))
                       .collect(Collectors.toList());
    }

    // Listet alle Mitarbeiter einer bestimmten Abteilung auf
    public List<Mitarbeiter> mitarbeiterNachAbteilung(Mitarbeiter.Beschaeftigungsart art) {
        return personen.stream()
                       .filter(person -> person instanceof Mitarbeiter)
                       .map(person -> (Mitarbeiter) person)
                       .filter(mitarbeiter -> mitarbeiter.getBeschaeftigungsart() == art)
                       .collect(Collectors.toList());
    }

    // Berechnet das durchschnittliche Gehalt aller Mitarbeiter
    public double durchschnittsGehalt() {
        return personen.stream()
                       .filter(person -> person instanceof Mitarbeiter)
                       .mapToDouble(person -> ((Mitarbeiter) person).getGehalt())
                       .average()
                       .orElse(0.0);
    }

    // Zählt die Anzahl von Mitarbeitern pro Beschäftigungsart
    public Map<Mitarbeiter.Beschaeftigungsart, Long> anzahlMitarbeiterProAbteilung() {
        return personen.stream()
                       .filter(person -> person instanceof Mitarbeiter)
                       .map(person -> (Mitarbeiter) person)
                       .collect(Collectors.groupingBy(Mitarbeiter::getBeschaeftigungsart, Collectors.counting()));
    }

    // Berechnet den gesamten Umsatz der Kunden
    public double gesamtUmsatzKunden() {
        return personen.stream()
                       .filter(person -> person instanceof Kunde)
                       .mapToDouble(person -> ((Kunde) person).getJahresUmsatzErwartet())
                       .sum();
    }
}
