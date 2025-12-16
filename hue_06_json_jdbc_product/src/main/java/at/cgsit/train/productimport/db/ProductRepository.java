package at.cgsit.train.productimport.db;

import at.cgsit.train.productimport.model.Product;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository/DAO für die Tabelle PRODUCT.
 *
 * Diese Version enthält eine komplette JDBC-Implementierung.
 *
 * Lernziele:
 * - PreparedStatement & ResultSet verwenden
 * - CRUD-Operationen kapseln
 * - JDBC-Ressourcen sicher schließen (try-with-resources)
 */
public class ProductRepository {

    private final Connection connection;

    // SQL-Texte
    private static final String SQL_INSERT =
            "INSERT INTO product (id, name, price, active) VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE product SET name = ?, price = ?, active = ? WHERE id = ?";

    private static final String SQL_SELECT_ALL =
            "SELECT id, name, price, active, created_at FROM product";

    private static final String SQL_SELECT_ACTIVE =
            SQL_SELECT_ALL + " WHERE active = TRUE";

    private static final String SQL_SELECT_BY_ID =
            SQL_SELECT_ALL + " WHERE id = ?";

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Fügt ein einzelnes Produkt ein.
     */
    public void insert(Product product) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            ps.setLong(1, product.getId());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setBoolean(4, product.isActive());
            ps.executeUpdate();
        }
    }

    public void deleteAllProducts() throws SQLException {
        String deleteStatement = "DELETE from product";
        try (PreparedStatement ps = connection.prepareStatement(deleteStatement)) {
            ps.executeUpdate();
        }
    }


    /**
     * Fügt mehrere Produkte ein.
     * Für Schüler verständlich: einfach insert() mehrfach aufrufen.
     * Alternativ: Batch-Insert (Bonus).
     */
    public void insertAll(List<Product> products) throws SQLException {
        for (Product p : products) {
            insert(p);
        }
    }

    /**
     * Aktualisiert ein Produkt anhand der ID.
     */
    public void update(Product product) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setBoolean(3, product.isActive());
            ps.setLong(4, product.getId());

            ps.executeUpdate();
        }
    }

    /**
     * Liest alle Produkte aus der Tabelle.
     */
    public List<Product> findAll() throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            List<Product> result = new ArrayList<>();

            while (rs.next()) {
                Product p = mapRow(rs);
                result.add(p);
            }

            return result;
        }
    }

    /**
     * Liest nur aktive Produkte (active = TRUE).
     */
    public List<Product> findActive() throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ACTIVE);
             ResultSet rs = ps.executeQuery()) {

            List<Product> result = new ArrayList<>();

            while (rs.next()) {
                Product p = mapRow(rs);
                result.add(p);
            }

            return result;
        }
    }

    /**
     * Sucht ein Produkt per ID.
     */
    public Optional<Product> findById(long id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product p = mapRow(rs);
                    return Optional.of(p);
                }
                return Optional.empty();
            }
        }
    }

    /**
     * Hilfsmethode, um eine ResultSet-Zeile in ein Product-Objekt zu mappen.
     */
    private Product mapRow(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        var price = rs.getBigDecimal("price");
        boolean active = rs.getBoolean("active");

        Timestamp ts = rs.getTimestamp("created_at");
        Instant createdAt = ts != null ? ts.toInstant() : null;

        return new Product(id, name, price, active, createdAt);
    }

    public Integer countNumberOfRecords() {
        // SQL-Statement: Zählt die Zeilenanzahl
        String countStatement = "SELECT count(*) FROM product";

        // // ResultSet wird direkt im try-with-resources geöffnet
        try (PreparedStatement ps = connection.prepareStatement(countStatement);
             ResultSet resultSet = ps.executeQuery()) {

            // 1. ZUM ERSTEN ERGEBNIS WECHSELN:
            // Die Abfrage COUNT(*) gibt immer genau eine Zeile zurück,
            // daher muss man zu dieser ersten Zeile wechseln
            if (resultSet.next()) {
                // Da COUNT(*) die erste Spalte (Index 1) des ResultSet ist,
                // wird sie als Integer ausgelesen.
                return resultSet.getInt(1);
            }

            // Sollte nie erreicht werden, da COUNT(*) immer 1 Zeile liefert
            return 0;
            } catch (SQLException e) {
                System.out.println( "error" + e);
                return 0;
            }
    }
}
