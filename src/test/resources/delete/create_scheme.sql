CREATE TABLE IF NOT EXISTS managers
(
    id         uuid PRIMARY KEY,
    first_name varchar(32),
    last_name  varchar(32),
    status     varchar(32),
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS products
(
    id              uuid PRIMARY KEY,
    manager_id      uuid,
    name            varchar(32),
    status          varchar(32),
    currency_code   varchar(3),
    interest_rate   numeric(19, 4),
    product_limit   numeric(19, 4),
    created_at      timestamp,
    updated_at      timestamp,
    FOREIGN KEY (manager_id) REFERENCES managers (id)
);

CREATE TABLE IF NOT EXISTS clients
(
    id          uuid PRIMARY KEY,
    manager_id  uuid,
    status      varchar(32),
    tax_code    varchar(32),
    first_name  varchar(32),
    last_name   varchar(32),
    email       varchar(32),
    pass        varchar(60),
    address     varchar(128),
    phone       varchar(32),
    created_at  timestamp,
    updated_at  timestamp,
    FOREIGN KEY (manager_id) REFERENCES managers (id)
);

CREATE TABLE IF NOT EXISTS accounts
(
    id              uuid PRIMARY KEY,
    client_id       uuid,
    name            varchar(32),
    type            varchar(32),
    status          varchar(32),
    balance         numeric(15, 2),
    currency_code   varchar(3),
    created_at      timestamp,
    updated_at      timestamp,
    FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE IF NOT EXISTS agreements
(
    id              uuid PRIMARY KEY,
    client_id       uuid,
    account_id      uuid,
    product_id      uuid,
    interest_rate   numeric(6, 4),
    status          varchar(32),
    amount          numeric(15, 2),
    created_at      timestamp,
    updated_at      timestamp,
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (account_id) REFERENCES accounts (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE IF NOT EXISTS transactions
(
    id                  uuid PRIMARY KEY,
    debit_account_id    uuid,
    credit_account_id   uuid,
    type                varchar(32),
    amount              numeric(15, 2),
    description         varchar(256),
    created_at          timestamp,
    FOREIGN KEY (debit_account_id) REFERENCES accounts (id),
    FOREIGN KEY (credit_account_id) REFERENCES accounts (id)
);