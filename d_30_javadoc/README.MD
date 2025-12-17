# Javadoc mit Maven – Kurzanleitung
Dieses Dokument beschreibt die gängigsten Maven-Befehle zur Erzeugung von
Javadoc-Dokumentation für Java-Projekte.

---

## Voraussetzungen

- Java JDK installiert
- Maven (`mvn`) verfügbar
- Projekt mit `pom.xml`

---

## 1. Standard-Javadoc erzeugen (schnellster Weg)

```bash
mvn javadoc:javadoc
````

**Ergebnis:**

```
target/site/apidocs/index.html
```

Öffne die Datei im Browser, um die Javadoc anzuzeigen.

---

## 2. Javadoc über den Maven Site Lifecycle

```bash
mvn site
```

Erzeugt:

* Javadoc
* Projektinformationen
* weitere Reports

**Javadoc-Pfad:**

```
target/site/apidocs/index.html
```

---

## 3. Javadoc für ein einzelnes Modul (Multi-Module)

```bash
mvn -pl <modulname> javadoc:javadoc
```

Beispiel:

```bash
mvn -pl core javadoc:javadoc
```

---

## 4. Javadoc-Fehler ignorieren (empfohlen für Trainingsprojekte)

```bash
mvn javadoc:javadoc -DfailOnError=false
```

---

## 5. Encoding explizit setzen (UTF-8)

```bash
mvn javadoc:javadoc -Dencoding=UTF-8
```

Empfohlen bei Umlauten oder Sonderzeichen.

---

## 6. Javadoc als JAR erzeugen

```bash
mvn javadoc:jar
```

Erzeugt:

```
target/<artifactId>-<version>-javadoc.jar
```

Typischer Einsatz:

* interne Artefakt-Repositories
* Schulungs- oder API-Verteilung

---

## 7. Kombination häufiger Optionen

```bash
mvn javadoc:javadoc \
  -DfailOnError=false \
  -Dencoding=UTF-8
```

---

## 8. Typische Probleme

### Javadoc schlägt fehl wegen fehlender Kommentare

→ `-DfailOnError=false` verwenden

### Sonderzeichen falsch dargestellt

→ Encoding auf `UTF-8` setzen

---

## 9. Merksätze

* Standard-Ausgabe: `target/site/apidocs`
* Schnellster Befehl: `mvn javadoc:javadoc`
* Für Distribution: `mvn javadoc:jar`

---

## 10. Weiterführende Links

* Maven Javadoc Plugin
  [https://maven.apache.org/plugins/maven-javadoc-plugin/](https://maven.apache.org/plugins/maven-javadoc-plugin/)

* Oracle Java Javadoc Tool
  [https://docs.oracle.com/en/java/javase/21/javadoc/javadoc.html](https://docs.oracle.com/en/java/javase/21/javadoc/javadoc.html)

```
