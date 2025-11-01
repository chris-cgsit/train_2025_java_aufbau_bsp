package at.cgsit.train.java.mv.personen;

/**
 * Subklasse Mitarbeiter, leitet sich von Person ab.
 * Enthält zusätzliche Felder für die Beschäftigungsart.
 */
public class Mitarbeiter extends Person {
    public enum Beschaeftigungsart { VOLLZEIT, TEILZEIT, FREELANCER }

    private Beschaeftigungsart beschaeftigungsart; // Beschäftigungsart (Enum)
    private double gehalt; // Monatliches Gehalt

    // Konstruktor
    public Mitarbeiter(String vorname, String nachname, String email, 
                        Beschaeftigungsart beschaeftigungsart, double gehalt) {
        super(vorname, nachname, email);
        this.beschaeftigungsart = beschaeftigungsart;
        this.gehalt = gehalt;
    }

    // Getter und Setter
    public Beschaeftigungsart getBeschaeftigungsart() {
        return beschaeftigungsart;
    }

    public void setBeschaeftigungsart(Beschaeftigungsart beschaeftigungsart) {
        this.beschaeftigungsart = beschaeftigungsart;
    }

    public double getGehalt() {
        return gehalt;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    // Überschriebene toString-Methode 
    @Override
    public String toString() {
        return super.toString() + String.format(", Beschäftigungsart: %s, Gehalt: %.2f", 
                 beschaeftigungsart, gehalt);
    }
}
