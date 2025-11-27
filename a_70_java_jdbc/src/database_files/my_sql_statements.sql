-- Anderung am 26.11
-- standard operations are CRUD - create, read, update and delete

-- READ ALL
select * from test_entity;

-- READ with WHERE Conditon
select * from test_entity where id > 10;
SELECT * from test_entity where name LIKE '%one';
SELECT * from test_entity where created_at > '2025-11-21 00:00:00';
SELECT * from test_entity where created_at::date >  '2025-11-20';


-- CREATE == insert
INSERT INTO test_entity (name, aktiv) VALUES ('TEST COMM', true);

-- UPDATE
UPDATE test_entity SET name = 'updated neu', aktiv = true WHERE id = 2;

-- delete
DELETE from test_entity WHERE id = 19;