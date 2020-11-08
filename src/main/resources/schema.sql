CREATE TABLE IF NOT EXISTS accounts
(
    id              INT         NOT NULL,
    document_number VARCHAR(16) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS operations_types
(
    id          INT         NOT NULL,
    description VARCHAR(20) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS transactions
(
    id                INT            NOT NULL,
    account_id        INT            NOT NULL,
    operation_type_id INT            NOT NULL,
    amount            DECIMAL(10, 2) NOT NULL,
    event_date        TIMESTAMP      NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (operation_type_id) REFERENCES operations_types (id),
    FOREIGN KEY (account_id) REFERENCES accounts (id)
);

