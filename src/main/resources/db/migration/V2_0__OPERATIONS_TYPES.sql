CREATE TABLE operations_types
(
    id          INT PRIMARY KEY NOT NULL,
    description VARCHAR(20)     NOT NULL
);

INSERT INTO operations_types (id, description)
VALUES (1, 'BUY THE CASH');
INSERT INTO operations_types (id, description)
VALUES (2, 'INSTALLED PURCHASE');
INSERT INTO operations_types (id, description)
VALUES (3, 'WITHDRAW');
INSERT INTO operations_types (id, description)
VALUES (4, 'PAYMENT');