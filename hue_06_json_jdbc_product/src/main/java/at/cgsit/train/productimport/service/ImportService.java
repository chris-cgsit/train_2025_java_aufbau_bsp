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
import java.util.Optional;

/**
 * Service, der den Import-Prozess orchestriert:
 * - JSON-Datei einlesen
 * - DB-Verbindung öffnen
 * - Produkte speichern
 */
public class ImportService implements AppService {


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

            int insertCount = 0;
            int updateCount = 0;

            // 1. JSON einlesen
            List<Product> products = fileImporter.readProducts(file);
            System.out.println("Es wurden " + products.size() + " Produkte aus der Datei gelesen.");


            // 2. In DB speichern
            Integer records = repository.countNumberOfRecords();
            if(records == 0) {
                repository.insertAll(products);
                insertCount = products.size();
                System.out.println("Datenbank war leer. Alle " + insertCount + " Produkte wurden eingefügt.");
            } else {
                // Fall B: Tabelle enthält Daten wir merge: Insert ODER Update)
                System.out.println("Datenbank enthält " + records + " Datensätze. Starte Merge-Vorgang...");

                for (Product productFromFile : products) {
                    // 2.1. Existenzprüfung (Suchen anhand der ID)
                    Optional<Product> existingProductOpt = repository.findById(productFromFile.getId());
                    if (existingProductOpt.isPresent()) {
                        // update
                        repository.update(productFromFile);
                        updateCount++;

                    } else {
                        // insert wenn noch nicht vorhanden
                        repository.insert(productFromFile);
                        insertCount++;
                    }
                }

                System.out.println("Merge abgeschlossen: " + insertCount + " eingefügt, " + updateCount + " aktualisiert.");

            }
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