-- ============================================
-- TEIL 1: ALS MASTER-USER IN DB "postgres"
-- (z.B. masteruser, postgres)
-- ============================================
-- 1) User anlegen
CREATE USER trainer
    WITH PASSWORD 'xxxxxx';

CREATE USER student_elisabeth
    WITH PASSWORD 'xxxxxx';

CREATE USER student_susi
    WITH PASSWORD 'xxxxxx';

CREATE USER student_madeha
    WITH PASSWORD 'FAD1231dddlaZTR';


-- 2) Datenbanken anlegen
-- Owner bleibt der Master-User (RDS-Standard),
-- NICHT OWNER trainer, das würde auf RDS scheitern.
CREATE DATABASE trainerdb;
CREATE DATABASE java_training_elisabeth;
CREATE DATABASE java_training_susi;
CREATE DATABASE java_training_madeha;

-- 3) Rechte auf Datenbankebene vergeben

-- Trainer-DB (nur Trainer)
GRANT ALL PRIVILEGES ON DATABASE trainerdb
    TO trainer;

-- DB für Elisabeth (Trainer + Elisabeth)
GRANT ALL PRIVILEGES ON DATABASE java_training_elisabeth
    TO trainer;
GRANT ALL PRIVILEGES ON DATABASE java_training_elisabeth
    TO student_elisabeth;

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

-- DB für Susi (Trainer + Susi)
GRANT ALL PRIVILEGES ON DATABASE java_training_susi
    TO trainer;
GRANT ALL PRIVILEGES ON DATABASE java_training_susi
    TO student_susi;

