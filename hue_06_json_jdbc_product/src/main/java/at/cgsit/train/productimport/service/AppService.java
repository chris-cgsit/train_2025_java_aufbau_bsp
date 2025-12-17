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

package at.cgsit.train.productimport.service;

import at.cgsit.train.productimport.config.AppConfig;
import at.cgsit.train.productimport.db.DatabaseConnectionFactory;
import at.cgsit.train.productimport.db.ProductRepository;
import at.cgsit.train.productimport.file.ProductFileImporter;
import at.cgsit.train.productimport.model.Product;

import java.io.File;
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

  /**
   * for GUI to load the selected file via GUI
   *
   * @param appConfig config / TODO should be set via constructor
   * @param selectedImportFile file to improt
   */
  void execute(AppConfig appConfig, File selectedImportFile);
}

