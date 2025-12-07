package at.cgsit.train.productimport.service;

import at.cgsit.train.productimport.config.AppConfig;
import at.cgsit.train.productimport.db.DatabaseConnectionFactory;
import at.cgsit.train.productimport.db.ProductRepository;
import at.cgsit.train.productimport.model.Product;
import at.cgsit.train.productimport.util.ProductFormatter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
* Service, der die in der Datenbank gespeicherten Produkte listet.
*/
public class ListService implements AppService {


private final DatabaseConnectionFactory connectionFactory;
private final ProductFormatter formatter;


public ListService() {
this.connectionFactory = new DatabaseConnectionFactory();
this.formatter = new ProductFormatter();
}


// Für Tests/Erweiterungen
public ListService(DatabaseConnectionFactory connectionFactory, ProductFormatter formatter) {
this.connectionFactory = connectionFactory;
this.formatter = formatter;
}


@Override
public void execute(AppConfig config) {
System.out.println("=== ListService gestartet ===");
System.out.println("Konfiguration: " + config);


try (Connection conn = connectionFactory.createConnection(config)) {
ProductRepository repository = new ProductRepository(conn);


List<Product> products;
if (Boolean.TRUE.equals(config.onlyActive())) {
products = repository.findActive();
} else {
products = repository.findAll();
}


if (products.isEmpty()) {
System.out.println("Keine Produkte in der Datenbank gefunden.");
return;
}


System.out.println("Gefundene Produkte: " + products.size());
for (Product p : products) {
System.out.println(formatter.format(p));
}


} catch (SQLException e) {
System.err.println("Datenbankfehler beim List-Service: " + e.getMessage());
e.printStackTrace(System.err);
}
}
}