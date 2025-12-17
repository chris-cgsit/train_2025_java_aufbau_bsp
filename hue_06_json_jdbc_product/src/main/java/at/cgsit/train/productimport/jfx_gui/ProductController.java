/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *  Any copying, redistribution, modification, or use
 *  beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.train.productimport.jfx_gui;

import at.cgsit.train.productimport.config.AppConfig;
import at.cgsit.train.productimport.db.DatabaseConnectionFactory;
import at.cgsit.train.productimport.db.ProductRepository;
import at.cgsit.train.productimport.model.Product;              // CORE / Domain Model
import at.cgsit.train.productimport.jfx_gui.model.ProductModel; // JavaFX GUI Model
import at.cgsit.train.productimport.service.ImportService;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ProductController
 * ------------------------------------------------------------
 * Rolle im MVC:
 *  - View  : productview.fxml
 *  - Model : ProductModel (JavaFX Properties)
 *  - Core  : Product (Domain / DB)
 *
 * WICHTIG FÜR SCHÜLER:
 *  - Der Controller kennt BOTH Welten:
 *      GUI (JavaFX)  <-->  Domain (Core Model)
 *  - Das Mapping passiert bewusst HIER
 *  - initialize() ist der Ort für Observer / Bindings
 */
public class ProductController {

    // ------------------------------------------------------------
    // FXML UI Komponenten (werden nach FXML-Laden injiziert)
    // ------------------------------------------------------------

    @FXML private TableView<ProductModel> productTable;
    @FXML private TableColumn<ProductModel, Long> idColumn;
    @FXML private TableColumn<ProductModel, String> nameColumn;
    @FXML private TableColumn<ProductModel, BigDecimal> priceColumn;
    @FXML private TableColumn<ProductModel, Boolean> activeColumn;
    @FXML private TableColumn<ProductModel, Instant> createdAtColumn;

    @FXML private TextField filePathField;

    @FXML private Button loadFileButton;
    @FXML private Button cleanDbButton;
    @FXML private Button exportDbButton;

    // ------------------------------------------------------------
    // Controller-interne Daten
    // ------------------------------------------------------------

    /**
     * ObservableList = ZENTRALER OBSERVER-MECHANISMUS
     *
     * - TableView beobachtet diese Liste. Jede Änderung (add/remove/clear)
     *   aktualisiert automatisch die GUI
     *   aber NICHT : Änderungen innerhalb eines ProductModel oder Änderungen an Properties
     */
    private final ObservableList<ProductModel> productData = FXCollections.observableArrayList();

    /**
     * Konfiguration wird vom App-Starter gesetzt
     * (Controller wird von JavaFX erzeugt!)
     */
    private AppConfig appConfig = JfxMainApp.APPCONFIG;

    /**
     * Aktuell ausgewählte Import-Datei
     * (Controller hält UI-Zustand)
     */
    private File selectedImportFile;

    public void setAppConfig(AppConfig config) {
        this.appConfig = config;
    }

    // ------------------------------------------------------------
    // JavaFX Lifecycle Callback
    // ------------------------------------------------------------

    /**
     * initialize()
     * --------------------------------------------------------
     * Wird von JavaFX AUTOMATISCH aufgerufen, nachdem:
     *  1) FXML geladen wurde
     *  2) alle @FXML Felder injiziert sind
     *
     * ------------------------------------------------------------
     * JavaFX Lifecycle-Methode (wird automatisch aufgerufen).
     *
     * <p><b>Wann?</b></p>
     * <ul>
     *   <li>Nach dem Laden des FXML</li>
     *   <li>Nachdem alle @FXML Felder injiziert wurden</li>
     * </ul>
     *
     * <p><b>Was passiert hier architektonisch?</b></p>
     * <ol>
     *   <li>Die TableView wird mit der ObservableList {@code productData} verbunden
     *       → TableView beobachtet Änderungen der LISTE (add/remove/clear).</li>
     *
     *   <li>Jede TableColumn erhält eine CellValueFactory
     *       → diese liefert ein {@link javafx.beans.value.ObservableValue}
     *       (typischerweise eine JavaFX Property).</li>
     *
     *   <li>Beim Rendern der Tabelle registrieren sich die TableCells
     *       automatisch als Observer an diesen Properties.</li>
     * </ol>
     *
     * <p><b>Ergebnis (automatisch durch JavaFX):</b></p>
     * <ul>
     *   <li>{@code productData.add(model)} → neue Tabellenzeile erscheint</li>
     *   <li>{@code model.setName(\"X\")}   → betroffene Zelle aktualisiert sich</li>
     * </ul>
     */
    @FXML
    private void initialize() {

        // ----------------------------------------------------
        // Mapping: TableColumn -> ProductModel Properties
        // ----------------------------------------------------
        // Jede Spalte registriert sich als OBSERVER
        // auf der jeweiligen Property des ProductModel

        idColumn.setCellValueFactory(cell ->
                cell.getValue().idProperty().asObject());

        nameColumn.setCellValueFactory(cell ->
                cell.getValue().nameProperty());

        priceColumn.setCellValueFactory(cell ->
                cell.getValue().priceProperty());

        activeColumn.setCellValueFactory(cell ->
                cell.getValue().activeProperty().asObject());

        createdAtColumn.setCellValueFactory(cell ->
                cell.getValue().createdAtProperty());

        // ----------------------------------------------------
        // Custom CellFactory = Darstellung (nicht Daten!)
        // ----------------------------------------------------
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm")
                .withZone(ZoneId.systemDefault());

        createdAtColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Instant item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : formatter.format(item));
            }
        });

        // ----------------------------------------------------
        // TableView beobachtet die ObservableList
        // ----------------------------------------------------
        productTable.setItems(productData);
    }

    // ------------------------------------------------------------
    // Button Handler (UI -> Controller -> Service)
    // ------------------------------------------------------------

    @FXML
    private void handleLoadFile() {
        if (appConfig == null) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "AppConfig nicht gesetzt");
            return;
        }
        if (selectedImportFile == null) {
            showAlert(
                Alert.AlertType.WARNING,
                "Datei fehlt",
                "Bitte zuerst eine Import-Datei auswählen."
            );
            return;
        }

        setControlsDisabled(true);

        // Hintergrundarbeit mit JavaFX Task
        Task<List<Product>> importTask = new Task<>() {
            @Override
            protected List<Product> call() throws Exception {
                ImportService service = new ImportService();

                // service.execute(appConfig);
                service.execute(appConfig, selectedImportFile);

                try (Connection conn = new DatabaseConnectionFactory().createConnection(appConfig)) {
                    return new ProductRepository(conn).findAll();
                }
            }

            @Override
            protected void succeeded() {
                setControlsDisabled(false);

                // Domain -> FX Model Mapping
                List<ProductModel> fxModels = convertToFXModels(getValue());

                // ObservableList Änderung -> TableView updated automatisch
                productData.setAll(fxModels);

                showAlert(Alert.AlertType.INFORMATION, "Erfolg",
                        fxModels.size() + " Produkte geladen");
            }

            @Override
            protected void failed() {
                setControlsDisabled(false);
                showAlert(Alert.AlertType.ERROR, "Fehler", getException().getMessage());
            }
        };

        new Thread(importTask).start();
    }

    @FXML
    private void handleCleanDB() {
        try (Connection conn = new DatabaseConnectionFactory().createConnection(appConfig)) {
            new ProductRepository(conn).deleteAllProducts();
            productData.clear();
            showAlert(Alert.AlertType.INFORMATION, "OK", "DB gelöscht");
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Fehler", ex.getMessage());
        }
    }

    @FXML
    private void handleExportDB() {
        showAlert(Alert.AlertType.INFORMATION, "Info", "Export folgt");
    }

    // ------------------------------------------------------------
    // Helper
    // ------------------------------------------------------------

    private List<ProductModel> convertToFXModels(List<Product> entities) {
        if (entities == null) return Collections.emptyList();

        return entities.stream()
                .map(e -> new ProductModel(
                        e.getId(),
                        e.getName(),
                        e.getPrice(),
                        e.isActive(),
                        e.getCreatedAt()))
                .collect(Collectors.toList());
    }

    private void setControlsDisabled(boolean disabled) {
        Platform.runLater(() -> {
            loadFileButton.setDisable(disabled);
            cleanDbButton.setDisable(disabled);
            exportDbButton.setDisable(disabled);
        });
    }

    @FXML
    private void handleChooseFile() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import-Datei auswählen");

        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter(
                "CSV Dateien", "*.json"
            )
        );

        // ------------------------------------------------------------
        // Initial Directory bestimmen
        // ------------------------------------------------------------
        File initialDir = null;

        // 1) Falls im TextField bereits ein Pfad steht nehmen wir DEN
        String currentPath = filePathField.getText();

        if (currentPath != null && !currentPath.isBlank()) {
            File currentFile = new File(currentPath);

            // Wenn es eine Datei ist, dann parent dir nehmen
            if (currentFile.isFile()) {
                initialDir = currentFile.getParentFile();
            }
            // Wenn es ein Verzeichnis ist → direkt verwenden
            else if (currentFile.isDirectory()) {
                initialDir = currentFile;
            }
        }

        // Fallback: aktuelles Working Directory der JVM
        if (initialDir == null || !initialDir.exists()) {
            initialDir = new File(System.getProperty("user.dir"));
        }

        // Nur dann setzen, wenn gültig (sonst Exception)
        if (initialDir.exists() && initialDir.isDirectory()) {
            fileChooser.setInitialDirectory(initialDir);
        }


        File file = fileChooser.showOpenDialog(
            productTable.getScene().getWindow()
        );

        if (file != null) {
            selectedImportFile = file;
            filePathField.setText(file.getAbsolutePath());
        }
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
}
