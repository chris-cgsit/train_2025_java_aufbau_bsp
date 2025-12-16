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

-- ======================================================
-- TEIL 3: ALS MASTER-USER IN DB "java_training_elisabeth"
-- (Database = java_training_elisabeth)
-- ======================================================

-- Trainer + Elisabeth sollen alles im public-Schema dürfen
GRANT ALL PRIVILEGES ON SCHEMA public TO trainer, student_elisabeth;

GRANT ALL PRIVILEGES ON ALL TABLES    IN SCHEMA public TO trainer, student_elisabeth;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO trainer, student_elisabeth;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO trainer, student_elisabeth;

-- Default-Rechte für NEUE Objekte
ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON TABLES    TO trainer, student_elisabeth;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON SEQUENCES TO trainer, student_elisabeth;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON FUNCTIONS TO trainer, student_elisabeth;
