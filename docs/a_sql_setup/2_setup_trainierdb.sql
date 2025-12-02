-- ============================================
-- TEIL 2: ALS MASTER-USER IN DB "trainerdb"
-- (im IntelliJ-Dropdown Database = trainerdb)
-- ============================================

-- Bestehende Objekte im public-Schema
GRANT ALL PRIVILEGES ON SCHEMA public TO trainer;

GRANT ALL PRIVILEGES ON ALL TABLES    IN SCHEMA public TO trainer;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO trainer;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO trainer;

-- Default-Rechte für NEUE Objekte (Tabellen, Sequenzen, Funktionen)
ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON TABLES    TO trainer;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON SEQUENCES TO trainer;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON FUNCTIONS TO trainer;
