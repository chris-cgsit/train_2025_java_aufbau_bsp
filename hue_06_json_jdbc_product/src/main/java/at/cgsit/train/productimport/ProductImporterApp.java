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

package at.cgsit.train.productimport;

import at.cgsit.train.productimport.config.AppConfig;
import at.cgsit.train.productimport.config.ConfigurationManager;
import at.cgsit.train.productimport.service.AppService;
import at.cgsit.train.productimport.service.ImportService;
import at.cgsit.train.productimport.service.ListService;


/**
 * Einstiegspunkt der Anwendung.
 */
public class ProductImporterApp {

    public static void main(String[] args) {
        ConfigurationManager configMgr = new ConfigurationManager();
        AppConfig config = configMgr.load(args);

        System.out.println("Starte ProductImporter: MODE=" + config.mode());

        AppService service = selectService(config);
        service.execute(config);
    }

    private static AppService selectService(AppConfig config) {
        return switch (config.mode().toLowerCase()) {
            // das neue case bietet so die Möglichkeit für mehrere cases und eine action
            case "input", "import" -> new ImportService();
            case "list"   -> new ListService();
            default       -> new ListService();
        };
    }
}
