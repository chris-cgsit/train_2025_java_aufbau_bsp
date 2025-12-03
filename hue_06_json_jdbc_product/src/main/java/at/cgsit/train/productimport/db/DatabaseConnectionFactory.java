package at.cgsit.train.productimport.db;

import at.cgsit.train.productimport.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Factory-Klasse zum Erzeugen von JDBC-Verbindungen.
 *
 * Lernziele:
 * - zentraler Ort für DriverManager.getConnection(...)
 * - Trennung von Verbindungsaufbau und Business-Logik
 */
public class DatabaseConnectionFactory {

    /**
     * Erstellt eine neue JDBC-Connection basierend auf der AppConfig.
     *
     * @param config Konfiguration mit DB-Url, User, Passwort
     * @return geöffnete JDBC-Verbindung
     * @throws SQLException falls der Verbindungsaufbau fehlschlägt
     */
    public Connection createConnection(AppConfig config) throws SQLException {
      String url = config.dbUrl();
      String user = config.dbUser();
      String password = config.dbPassword();

      // wir gehen davon aus, dass der configuraiton manager nur gültige configs bereit stellt oder excpetion
      // geworfen hätte
      if(url==null){
          throw new NullPointerException("url must be specfifid");
      }


        // JDBC 4 lädt den Treiber i.d.R. automatisch über SPI.
        // Für H2/Postgres ist kein explizites Class.forName(...) mehr nötig.
        Connection conn = DriverManager.getConnection(url, user, password);

        // Optional: Auto-Commit ausschalten und Transaktionen manuell steuern
        // conn.setAutoCommit(false);

        return conn;
    }

    /**
     * Optionales Gerüst für ein Schema-Initialisierung:
     * könnte z.B. ein schema.sql einlesen und ausführen.
     *
     * Diese Methode ist bewusst noch nicht implementiert und kann
     * von den Schüler:innen als Bonus-Aufgabe ausgefüllt werden.
     */
    public void initializeSchemaIfNeeded(Connection conn) {
        throw new UnsupportedOperationException("Schema-Init ist noch nicht implementiert.");
    }
}
