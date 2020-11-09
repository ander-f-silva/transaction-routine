CREATE TABLE transactions
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    account_id        INT            NOT NULL,
    operation_type_id INT            NOT NULL,
    amount            DECIMAL(10, 2) NOT NULL,
    event_date        TIMESTAMP      NOT NULL,

    FOREIGN KEY (operation_type_id) REFERENCES operations_types (id),
    FOREIGN KEY (account_id) REFERENCES accounts (id)
);