package at.cgsit.train.productimport.jfx_gui;

import at.cgsit.train.productimport.config.AppConfig;
import at.cgsit.train.productimport.db.DatabaseConnectionFactory;
import at.cgsit.train.productimport.db.ProductRepository;
import at.cgsit.train.productimport.model.Product; // Das CORE/DB-Model (Domain-Entität)
import at.cgsit.train.productimport.jfx_gui.model.ProductModel; // Das GUI/FX-Model
import at.cgsit.train.productimport.service.ImportService; // Angenommen Service-Klasse

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ProductController {

    // --- FXML UI Komponenten ---
    
    @FXML private TableView<ProductModel> productTable;
    @FXML private TableColumn<ProductModel, Long> idColumn;
    @FXML private TableColumn<ProductModel, String> nameColumn;
    @FXML private TableColumn<ProductModel, BigDecimal> priceColumn;
    @FXML private TableColumn<ProductModel, Boolean> activeColumn;
    @FXML private TableColumn<ProductModel, Instant> createdAtColumn;
    
    // Buttons, die während des Imports deaktiviert werden
    @FXML private Button loadFileButton;
    @FXML private Button cleanDbButton;
    @FXML private Button exportDbButton;


    // --- Controller Interne Daten ---
    
    // Die Datenliste für die Tabelle (MUSS ProductModel verwenden)
    private ObservableList<ProductModel> productData = FXCollections.observableArrayList();
    
    // Die Konfiguration (Muss vor dem Import gesetzt werden, z.B. durch den App-Starter)
    private AppConfig appConfig = JfxMainApp.APPCONFIG;

    // --- Initialisierung ---
    
    public void setAppConfig(AppConfig config) {
        this.appConfig = config;
    }
    
    @FXML
    private void initialize() {
        // Verbindet die Tabellenspalten mit den Properties im ProductModel
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        activeColumn.setCellValueFactory(cellData -> cellData.getValue().activeProperty().asObject());
        createdAtColumn.setCellValueFactory(cellData -> cellData.getValue().createdAtProperty());

        // Formatierung für den Zeitstempel (für Instant/TIMESTAMP)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());
        createdAtColumn.setCellFactory(column -> new TableCell<ProductModel, Instant>() {
            @Override
            protected void updateItem(Instant item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatter.format(item));
                }
            }
        });

        // Fügt die Datenliste in die Tabelle ein
        productTable.setItems(productData);
        
        // Optional: Demo-Daten laden (entfernen, sobald Import funktioniert)
        // loadDemoData();
    }
    
    // --- Button-Handler ---

    @FXML
    private void handleLoadFile() {
        if (appConfig == null) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "AppConfig nicht gesetzt. Import nicht möglich.");
            return;
        }
        
        // 1. Buttons deaktivieren
        setControlsDisabled(true);

        // SIEHE auch alternative mit Completable Future klassisch

        // 2. Task für die Hintergrundarbeit erstellen
        // Rückgabetyp ist das CORE/DB-Model: List<Product>
        Task<List<Product>> importTask = new Task<>() {
            
            @Override
            protected List<Product> call() throws Exception {
                // HINWEIS: Hier wird die Core/Domain-Logik ausgeführt
                ImportService service = new ImportService();
                // service.execute gibt nichts zurück derzeit wir lesen danach aus
                updateMessage("Importiere Daten...");
                service.execute(appConfig);

                try (Connection conn = new DatabaseConnectionFactory().createConnection(JfxMainApp.APPCONFIG)) {
                    ProductRepository repository = new ProductRepository(conn);
                    List<Product> allProducts = repository.findAll();
                    return allProducts;
                }

            }

            @Override
            protected void failed() {
                super.failed();
                // 3. Callback (Im UI-Thread): Buttons wieder aktivieren
                setControlsDisabled(false);
                Throwable e = getException();
                // UI-Update: Fehlermeldung anzeigen
                showAlert(Alert.AlertType.ERROR, "Fehler beim Import", "Import fehlgeschlagen: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                // 3. Callback (Im UI-Thread): Buttons wieder aktivieren
                setControlsDisabled(false);

                // 4. Konvertierung und UI-Aktualisierung
                List<Product> importedEntities = getValue();
                
                // Konvertiere Core-Entities in FX-Model-Objekte
                List<ProductModel> fxModels = convertToFXModels(importedEntities);
                
                // Fülle die Tabelle neu
                productData.clear();
                productData.addAll(fxModels);

                showAlert(Alert.AlertType.INFORMATION, "Erfolg", importedEntities.size() + " Produkte erfolgreich geladen und importiert.");
            }
        };

        // 5. Starte den Task in einem neuen Thread
        new Thread(importTask).start();

    }
    
    @FXML
    private void handleCleanDB() {
        try {
            try (Connection conn = new DatabaseConnectionFactory().createConnection(JfxMainApp.APPCONFIG)) {
                ProductRepository repository = new ProductRepository(conn);
                repository.deleteAllProducts();
            }
            productData.clear();

            showAlert(Alert.AlertType.INFORMATION, "Aktion", "Datenbank wurde gelöscht");
        } catch ( Exception ex) {
            showAlert(Alert.AlertType.INFORMATION, "Aktion", "Fehler" + ex.getMessage());
        }
    }

    @FXML
    private void handleExportDB() {
        showAlert(Alert.AlertType.INFORMATION, "Aktion", "Datenbank-Exportlogik wird hier implementiert.");
    }
    
    // --- Hilfsmethoden ---

    /**
     * Deaktiviert/Aktiviert alle Steuerungs-Buttons (läuft im JavaFX-Thread).
     */
    private void setControlsDisabled(boolean disabled) {
        Platform.runLater(() -> {
            loadFileButton.setDisable(disabled);
            cleanDbButton.setDisable(disabled);
            exportDbButton.setDisable(disabled);
        });
    }

    /**
     * Konvertiert Core/Domain Product-Entitäten in das JavaFX ProductModel.
     */
    private List<ProductModel> convertToFXModels(List<Product> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(entity -> new ProductModel(
                        entity.getId(),
                        entity.getName(),
                        entity.getPrice(),
                        entity.isActive(),
                        entity.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
    
    private void loadDemoData() {
        productData.add(new ProductModel(1, "Klettergurt Basic", new BigDecimal("49.99"), true, Instant.now()));
        productData.add(new ProductModel(2, "Laptop Pro X", new BigDecimal("1299.00"), false, Instant.now().minusSeconds(3600)));
    }
    
    private void showAlert(Alert.AlertType type, String title, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }


    private void handleLoadFileCompletableFuture_E() {
        setControlsDisabled(true);

        CompletableFuture.supplyAsync(() -> {
            try {
                // HIER: Lange Dauernde Arbeit im Worker-Thread (wird oft vom ForkJoinPool ausgeführt)
                ImportService service = new ImportService();
                service.execute(appConfig);
                try (Connection conn = new DatabaseConnectionFactory().createConnection(JfxMainApp.APPCONFIG)) {
                    ProductRepository repository = new ProductRepository(conn);
                    List<Product> allProducts = repository.findAll();

                    // supplier liefert ein ergebnis
                    return allProducts;
                }
            } catch (Exception ex) {
                System.out.println("exectpon in load" + ex.getMessage());
            }
            return null;
        }).thenAccept(entities -> {
            // HIER: Führt den Callback-Code aus, aber oft IMMER NOCH im Worker-Thread!

            // ZWANGSWEISE Thread-Wechsel für UI-Aktualisierung:
            // das hier muss sein, damit wir im GUI Thread das update durchführen lassen.
            Platform.runLater(() -> {
                try {
                    List<ProductModel> fxModels = convertToFXModels(entities);
                    productData.clear();
                    productData.addAll(fxModels);
                    showAlert(Alert.AlertType.INFORMATION, "Erfolg", "Geladen via CompletableFuture.");
                } finally {
                    setControlsDisabled(false); // UI freigeben
                }
            });

        }).exceptionally(e -> {
            // BEI FEHLER: ZWANGSWEISE Thread-Wechsel für UI-Aktualisierung:
            Platform.runLater(() -> {
                setControlsDisabled(false); // UI freigeben
                showAlert(Alert.AlertType.ERROR, "Fehler", "Fehlgeschlagen via CompletableFuture: " + e.getMessage());
            });
            return null;
        });
    }


}