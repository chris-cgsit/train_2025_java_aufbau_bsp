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
 * Gemeinsames Interface für alle Anwendungs-Services.
 *
 * Lernziel:
 * - Polymorphie über eine einheitliche execute(AppConfig)-Methode.
 */
public interface AppService {

    /**
     * Führt die Service-Logik mit der übergebenen Konfiguration aus.
     */
    void execute(AppConfig config);
}

