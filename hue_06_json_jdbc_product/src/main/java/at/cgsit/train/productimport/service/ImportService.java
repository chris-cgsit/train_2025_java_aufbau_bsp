package at.cgsit.train.productimport.service;

import at.cgsit.train.productimport.config.AppConfig;
import at.cgsit.train.productimport.db.DatabaseConnectionFactory;
import at.cgsit.train.productimport.db.ProductRepository;
import at.cgsit.train.productimport.file.ProductFileImporter;
import at.cgsit.train.productimport.model.Product;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
* Service, der den Import-Prozess orchestriert:
* - JSON-Datei einlesen
* - DB-Verbindung öffnen
* - Produkte speichern
*/
class ImportService implements AppService {


private final DatabaseConnectionFactory connectionFactory;
private final ProductFileImporter fileImporter;


public ImportService() {
this.connectionFactory = new DatabaseConnectionFactory();
this.fileImporter = new ProductFileImporter();
}


// Für Tests/Erweiterungen
public ImportService(DatabaseConnectionFactory connectionFactory, ProductFileImporter fileImporter) {
this.connectionFactory = connectionFactory;
this.fileImporter = fileImporter;
}


@Override
public void execute(AppConfig config) {
System.out.println("=== ImportService gestartet ===");
System.out.println("Konfiguration: " + config);


if (config.inputFile() == null || config.inputFile().isBlank()) {
System.err.println("Fehler: --input=<datei> ist für mode=import erforderlich.");
return;
}


Path file = Path.of(config.inputFile());


try (Connection conn = connectionFactory.createConnection(config)) {
ProductRepository repository = new ProductRepository(conn);


// 1. JSON einlesen
List<Product> products = fileImporter.readProducts(file);
System.out.println("Es wurden " + products.size() + " Produkte aus der Datei gelesen.");


// 2. In DB speichern
repository.insertAll(products);
System.out.println("Produkte wurden in die Datenbank importiert.");


// Optional: Transaktion manuell steuern, falls AutoCommit=false
// conn.commit();


} catch (SQLException e) {
System.err.println("Datenbankfehler beim Import: " + e.getMessage());
e.printStackTrace(System.err);
} catch (RuntimeException e) {
System.err.println("Fehler beim Import: " + e.getMessage());
e.printStackTrace(System.err);
}
}
}