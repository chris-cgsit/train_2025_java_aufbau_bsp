-- READ ALL
select * from test_entity;

-- READ with WHERE Conditon
select * from test_entity where id > 10;


-- CREATE == insert
INSERT INTO test_entity (name, aktiv) VALUES ('TEST COMM', true);

-- UPDATE
UPD