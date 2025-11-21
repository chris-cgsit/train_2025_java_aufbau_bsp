@startuml
Title Produkt-Importer – Klassendiagramm mit Service-Interface und Beschreibungen

' =======================
' Domain-Klasse
' =======================
class Product {
- long id
- String name
- BigDecimal price
- boolean active
- Instant createdAt
  }

note right of Product
Zentrales Daten Domain-Objekt für ein Produkt im Katalog.

Verantwortung:
- Repraesentiert einen Datensatz in der DB-Tabelle PRODUCT.
- Wird aus JSON eingelesen und in der DB gespeichert.
- Dient als Rückgabe- und Eingabe-Typ im Repository.

Lernziel:
- Verstaendnis von einfachen POJOs / Records.
- Umgang mit BigDecimal und Instant.
  end note


' =======================
' Main & Config
' =======================
class ProductImporterApp {
+ main(String[] args) : void
  }

note right of ProductImporterApp
Einstiegspunkt der Anwendung.

Verantwortung:
- Liest die Kommandozeilenargumente.
- Delegiert das Konfig-Laden an ConfigurationManager.
- Entscheidet anhand von config.mode, welcher Service ausgeführt wird.

Lernziel:
- Aufbau eines sauberen main-Programms,
  das nur steuert und nicht die ganze Logik enthält.
  end note

class ConfigurationManager {
+ load(String[] args) : AppConfig
- parseArgs(String[] args) : Map<String,String>
- loadEnv() : Map<String,String>
- loadProperties() : Map<String,String>
- merge(...) : AppConfig
  }

note right of ConfigurationManager
Zentrale Klasse für Konfiguration.

Verantwortung:
- CLI-Argumente (--mode, --input, ...) parsen.
- Umgebungsvariablen laden (DB_URL, DB_USER, DB_PASSWORD, ...).
- Optional Properties-Datei (app.properties) laden.
- Alles zu einer AppConfig zusammenführen
  (Priorität: Env > Properties > Defaults).

Lernziel:
- Trennung von Konfigurationslogik und Business-Logik.
- Arbeiten mit System.getenv(), Properties, String[] args.
  end note

class AppConfig {
+ String dbUrl
+ String dbUser
+ String dbPassword
+ String mode
+ String inputFile
+ Boolean onlyActive
+ BigDecimal minPrice
  }

note right of AppConfig
Immutable Konfigurationsdaten der Anwendung
(gerne auch als java record - als immutable objekt).

Verantwortung:
- Enthält alle Einstellungen, die für Laufzeit benötigt werden:
    - DB-Verbindungsdaten
    - Ausführungsmodus (import / list)
    - Input-Datei und optionale Filter.

Lernziel:
- Konfigurationsobjekt als zentrales "Contract"-Objekt
  zwischen Main, Services und DB-Layer.
  end note


' =======================
' DB & JDBC
' =======================
class DatabaseConnectionFactory {
+ createConnection(AppConfig config) : Connection
  }

note right of DatabaseConnectionFactory
Factory für JDBC-Verbindungen.

Verantwortung:
- Aus AppConfig die JDBC-URL & Credentials lesen.
- Verbindung zur Datenbank aufbauen.
- Optional: AutoCommit setzen, Schema-Init ausführen.

Lernziel:
- Umgang mit JDBC-Connection.
- Trennung von Verbindungsaufbau und Business-Logik.
  end note

class ProductRepository {
+ insertAll(List<Product> products) : void
+ findAll() : List<Product>
+ findActive() : List<Product>
+ update(Product p) : void
  }

note right of ProductRepository
Datenzugriffs-Schicht (DAO/Repository) für Product.

Verantwortung:
- SQL-Statements ausführen (INSERT, UPDATE, SELECT).
- ResultSet in Product-Objekte umwandeln.
- Kein Wissen über CLI, JSON oder Config.

Lernziel:
- Reines JDBC (PreparedStatement, ResultSet).
- Trennung von DB-Zugriff und Fachlogik.
  end note


' =======================
' File / JSON
' =======================
class ProductFileImporter {
+ readProducts(Path jsonFile) : List<Product>
  }

note right of ProductFileImporter
Einlesen der JSON-Datei mit Produktdaten.

Verantwortung:
- Öffnen der Datei.
- JSON-Parsing (z.B. mit Jackson) in List<Product>.
- Validierung z.B. auf Pflichtfelder.

Lernziel:
- Datei-I/O in Java.
- Trennung von Dateiformat und Importlogik.
  end note


' =======================
' Gemeinsames Service-Interface
' =======================
interface AppService {
+ execute(AppConfig config) : void
  }

note right of AppService
Gemeinsames Interface für alle Services.

Verantwortung:
- Definiert eine einheitliche Einstiegsmethode ("execute")
  unabhängig davon, ob der Service importiert, listet oder
  später erweitert wird (z.B. DeleteService, UpdateService).

Lernziel:
- Einführung in Interfaces und Polymorphie.
- Ermöglicht saubere Austauschbarkeit der Services.
  end note


' =======================
' Services (Implementierungen)
' =======================
class ImportService {
+ execute(AppConfig config) : void
  }

note right of ImportService
Implementiert AppService (execute).

Verantwortung:
- JSON-Datei via ProductFileImporter lesen.
- DB-Connection aufbauen.
- Produkte via ProductRepository speichern.
- Transaktionen steuern (Commit/Rollback).

Lernziel:
- Service-Schicht als Koordinator.
- Transaktionen und Fehlerbehandlung.
  end note

class ListService {
+ execute(AppConfig config) : void
  }

note right of ListService
Implementiert AppService (execute).

Verantwortung:
- Verbindung zur DB aufbauen.
- Produkte über Repository lesen.
- Ausgabe der Daten (optional via Formatter).

Lernziel:
- Polymorphie über das AppService-Interface.
- Implementierung von Leselogik ohne Geschäftslogik.
  end note

class ProductFormatter {
+ format(Product p) : String
  }

note right of ProductFormatter
Hilfsklasse zur Formatierung für die Konsole.

Verantwortung:
- Einheitliches Ausgabeformat.
- Trennung von Darstellung und Logik.

Lernziel:
- Single-Responsibility-Prinzip.
  end note


' =======================
' Beziehungen
' =======================
ProductImporterApp --> ConfigurationManager
ConfigurationManager --> AppConfig

ProductImporterApp --> AppService : "führt execute() aus"

ImportService -|> AppService
ListService -|> AppService

ImportService --> ProductFileImporter
ImportService --> DatabaseConnectionFactory
ImportService --> ProductRepository

ListService --> DatabaseConnectionFactory
ListService --> ProductRepository
ListService --> ProductFormatter

ProductRepository --> Product

@enduml
