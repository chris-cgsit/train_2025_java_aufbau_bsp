-- =============================================
-- PostgreSQL Setup Script for Product Importer
-- Für Flyway-Migrationsordner: src/sql oder src/main/resources/db/migration
-- =============================================

-- ---------------------------------------------
-- 1) Datenbank anlegen (manuell ausführen, nicht durch Flyway)
-- ---------------------------------------------
-- Hinweis: Flyway verwaltet *Schema*, aber erstellt keine *Datenbank*.
-- Diesen Befehl führt man nur einmal als Admin aus.
--
-- CREATE DATABASE product_importer_db
--     WITH OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'en_US.utf8'
--     LC_CTYPE = 'en_US.utf8'
--     TEMPLATE = template0;


-- ---------------------------------------------
-- 2) Schema anlegen (Flyway-Versionierung möglich)
-- Dateiname z. B.: V1__create_product_table.sql
-- ---------------------------------------------

-- Optional ein eigenes Schema benutzen:
-- CREATE SCHEMA IF NOT EXISTS product;
-- SET search_path TO product;


-- ---------------------------------------------
-- Tabelle PRODUCT
-- ---------------------------------------------

CREATE TABLE IF NOT EXISTS product (
                                       id          BIGINT PRIMARY KEY,
                                       name        VARCHAR(255) NOT NULL,
                                       price       NUMERIC(12,2) NOT NULL,
                                       active      BOOLEAN NOT NULL,
                                       created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexe (optional, aber sinnvoll)
CREATE INDEX IF NOT EXISTS idx_product_active ON product(active);
CREATE INDEX IF NOT EXISTS idx_product_price ON product(price);


-- ---------------------------------------------
-- Beispiel-Testdaten (optional, nicht in V1 einbauen)
-- ---------------------------------------------
-- INSERT INTO product(id, name, price, active) VALUES
-- (1, 'Testprodukt A', 9.99, TRUE),
-- (2, 'Testprodukt B', 19.99, FALSE);
