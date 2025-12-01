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
            case "import" -> new ImportService();
            case "list"   -> new ListService();
            default       -> new ListService();
        };
    }
}
