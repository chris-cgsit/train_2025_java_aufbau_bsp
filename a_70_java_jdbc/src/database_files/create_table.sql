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

 CREATE TABLE test_entity (
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(200) NOT NULL,
     aktiv BOOLEAN,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE buecher (
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(200) NOT NULL,
     aktiv BOOLEAN,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 );


