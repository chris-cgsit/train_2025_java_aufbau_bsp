/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.train.productimport.jfx_gui;

import at.cgsit.train.productimport.config.AppConfig;
import at.cgsit.train.productimport.config.ConfigurationManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class JfxMainApp extends Application {

    public static AppConfig APPCONFIG = null;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Lade FXML-Datei
            FXMLLoader loader = new FXMLLoader();
            URL resource = JfxMainApp.class.getResource("/view/productview.fxml");
            loader.setLocation(resource);
            VBox rootLayout = loader.load();

            // Zeige die Szene an
            Scene scene = new Scene(rootLayout, 800, 600);
            
            // Fügen Sie hier ggf. die CSS-Datei hinzu, falls Sie eine verwenden
            // scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            primaryStage.setTitle("JavaFX Product Management");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ConfigurationManager configMgr = new ConfigurationManager();
        AppConfig config = configMgr.load(args);

        APPCONFIG = config;

        launch(args);
    }
}