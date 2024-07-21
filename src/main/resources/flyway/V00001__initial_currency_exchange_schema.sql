BEGIN;

-- schema
CREATE SCHEMA IF NOT EXISTS currency_exchange;

SET search_path TO currency_exchange;

-- tables
-- Table: account
CREATE TABLE account (
    uuid UUID PRIMARY KEY,
    balance DECIMAL(19, 4) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    user_uuid UUID NOT NULL,
    FOREIGN KEY (user_uuid) REFERENCES app_user(uuid) ON DELETE CASCADE
);


-- Table: app_user
CREATE TABLE app_user (
    uuid UUID PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    pesel VARCHAR(11) NOT NULL UNIQUE
);


COMMIT;
