package at.cgsit.a_jfxintro.person.core_model;

import java.time.LocalDate;


// Dieses Objekt POJO oder JPA Entity wird im Service layer verwendet
// der Service layer ist der layer den die GUI verwendet um mit der geschäftslogik zu reden
// GUI klassen und objekte dürfen NICHT in den Service layer übergeben werden.
// denn wenn sich die GUI ädnert wäre der service layer kaputt.
// die GUI soll ausgetascht werden können auf z.b. Swing, JSF oder gar Angluar.JS
// @Entity
// @Table(name = "person")
public class PersonEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;
    private String email;

    // Getter/Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVorname() { return vorname; }
    public void setVorname(String vorname) { this.vorname = vorname; }

    public String getNachname() { return nachname; }
    public void setNachname(String nachname) { this.nachname = nachname; }

    public LocalDate getGeburtsdatum() { return geburtsdatum; }
    public void setGeburtsdatum(LocalDate geburtsdatum) { this.geburtsdatum = geburtsdatum; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
