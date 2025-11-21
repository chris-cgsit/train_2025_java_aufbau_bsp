# **Hausübung: Produkt-Importeur mit JDBC & JSON**

## **1. Ziel der Aufgabe**

Ihr entwickelt ein kleines Java-Kommandozeilenprogramm, das:

* Eine JSON-Datei mit Daten (z. B. Produkte) einliest
* Die Daten per JDBC in eine Datenbank-Tabelle importiert
* Über Konsolenbefehle Daten aus der Datenbank anzeigt
* Konfiguration über Programm-Argumente, Umgebungsvariablen und optional eine Property-Datei erhält

Der Umfang ist so gedacht, dass man einige Tage damit arbeiten kann (Planung, Implementierung, Tests, Dokumentation).

---

## **2. Fachliches Szenario**

Ihr baut einen kleinen „Produktkatalog“-Importer.

Es gibt eine JSON-Datei mit einer Liste von Produkten.

Jedes Produkt hat mindestens:

* `id` (String oder long)
* `name` (String)
* `price` (BigDecimal)
* `active` (boolean)

Optional: beliebige weitere Felder (category, description, …).

Beispiel **products.json**:

```json
[
  {
    "id": 1,
    "name": "Klettergurt Basic",
    "price": 49.99,
    "active": true
  },
  {
    "id": 2,
    "name": "Sicherungsggerät Pro",
    "price": 89.5,
    "active": false
  }
]
```

---

## **3. Technische Anforderungen**

### **3.1 Datenbank**

Verwendet reines **JDBC** (kein ORM wie JPA/Hibernate).

Empfehlung: **H2** oder **PostgreSQL**.

SQL-Beispiel:

```sql
CREATE TABLE product (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### **3.2 Java-Projekt**

* Java 17+ (oder Version aus dem Kurs)
* Maven-Projekt
* Erlaubte Libraries:

    * JDBC-Treiber (H2/PostgreSQL)
    * Jackson oder andere JSON-Library

---

## **4. Konfiguration (Args & Environment)**

Das Programm liest Konfiguration aus:

### **Umgebungsvariablen**

* `DB_URL` – z. B. `jdbc:h2:./testdb`
* `DB_USER`
* `DB_PASSWORD`

### **Kommandozeilen-Argumente**

* `--input=path/to/products.json`
* `--mode=import` oder `--mode=list`

### **Optional: Property-Datei (app.properties)**

Kann Standardwerte liefern:

```
DB_URL=...
DB_USER=...
DB_PASSWORD=...
```

### **Prioritäten-Regeln**

1. Umgebungsvariable überschreibt Property-Datei
2. Pflichtangaben müssen vorhanden sein → sonst Fehlermeldung + Abbruch

---

## **5. Programmfunktionen**

### **Modus: import**

Aufruf:

```
java -jar product-importer.jar --mode=import --input=./data/products.json
```

Ablauf:

1. DB-Verbindung aufbauen
2. JSON-Datei einlesen
3. JSON in `List<Product>` parsen
4. Produkte einfügen (INSERT oder MERGE/UPSERT)
5. Optional: Batch-Insert
6. Commit / Rollback

---

### **Modus: list**

Aufruf:

```
java -jar product-importer.jar --mode=list
```

Ablauf:

* Alle Produkte aus DB lesen und anzeigen:

```
ID=1 | name=Klettergurt Basic | price=49.99 | active=true
ID=2 | name=Sicherungsgerät Pro | price=89.50 | active=false
```

Optional CLI-Filter:

* `--only-active=true`
* `--min-price=50`

---

## **6. Technische Teilaufgaben**

### **A. Datenmodell**

Klasse `Product` mit Feldern, Konstruktor, Getter/Setter, `toString()`.

### **B. JSON-Einlesen**

* Datei per `Files.readString(...)`
* Parsing mit Jackson in `List<Product>`

### **C. JDBC-Layer**

**DatabaseConfig:**

* liest DB_URL, DB_USER, DB_PASSWORD
* Reihenfolge: Property → Env → CLI

**Product Repository:**

* `void insertAll(List<Product> list)`
* `List<Product> findAll()`
* Optional:

    * `List<Product> findActive()`
    * `List<Product> findByMinPrice(BigDecimal price)`

### **D. CLI Parsing**

* Einfaches Parsen von `--key=value`
* Map mit Parametern erstellen

### **E. Fehlerbehandlung**

Beispiele:

* JSON-Datei nicht gefunden
* DB-Verbindungsfehler
* SQL-Fehler beim Insert

Das Programm soll **verständliche Fehlermeldungen** liefern.

---

## **7. Abgabe**

Abzugeben ist ein:

### **Git-Repository oder ZIP mit:**

* Vollständigem Maven-Projekt
* Java-Quellcode
* Beispiel-JSON (`products.json`)
* SQL-Skript (`schema.sql`)
* README.md (1–2 Seiten):

    * Startanleitung
    * benötigte Umgebungsvariablen
    * Beispielaufrufe
    * bekannte Einschränkungen

---

## **8. Bonus-Ideen**

Für schnellere Schüler:innen:

* **UPDATE statt INSERT** bei vorhandener ID
* **Transaktionen steuerbar** via `--transactional=true`
* **JUnit-Tests**
* **Logging** statt `System.out.println`
