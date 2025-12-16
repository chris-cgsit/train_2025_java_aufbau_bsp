package at.cgsit.train.productimport.file;

import at.cgsit.train.productimport.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Liest eine JSON-Datei ein und wandelt sie in eine Liste von Product-Objekten um.
 *
 * Lernziele:
 * - Datei-I/O
 * - Arbeiten mit Jackson ObjectMapper
 * - Typreferenzen für generische Listen
 */
public class ProductFileImporter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Liest Produkte aus einer JSON-Datei.
     * Erwartetes Format:
     * [
     *   { "id": 1, "name": "Produkt1", "price": 9.99, "active": true },
     *   { "id": 2, "name": "Produkt2", "price": 19.99, "active": false }
     * ]
     *
     * @param file Pfad zur JSON-Datei
     * @return Liste der eingelesenen Produkte
     */
    public List<Product> readProducts(Path file) {

        // 1. REGISTRIEREN DES JSR310-MODULS
        // Dies fügt die Handler für Instant, LocalDate, LocalDateTime etc. hinzu.
        objectMapper.registerModule(new JavaTimeModule());

        try {
            if (!Files.exists(file)) {
                throw new RuntimeException("Datei nicht gefunden: " + file);
            }

            // Dies ist Teil der Java NIO.2 API (seit Java 7) und die empfohlene Art,
            // eine gesamte Datei als String in einem Zug zu lesen (UTF-8).
            // Es ist besser als die älteren BufferedReader/FileReader-Methoden
            String json = Files.readString(file);
            System.out.printf( "INputfile gefunden mit länge : [%s] \n",json.length());

            // JSON -> Liste<Product>
            return objectMapper.readValue(json, new TypeReference<List<Product>>() {});

        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Einlesen der JSON-Datei: " + file, e);
        }
    }
}
