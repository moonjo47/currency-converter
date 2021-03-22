DROP SCHEMA IF EXISTS converter_schema CASCADE;
CREATE SCHEMA converter_schema;

SET
search_path = converter_schema;

DROP TABLE IF EXISTS currencies;
CREATE TABLE currencies
(
    id       bigint     NOT NULL PRIMARY KEY,
    numCode  varchar(3) NOT NULL,
    charCode varchar(3) NOT NULL,
    name     varchar,
    nominal  int,
    value    numeric,
    date     date
);

DROP TABLE IF EXISTS history;
CREATE TABLE history
(
    id              bigint NOT NULL PRIMARY KEY,
    currency_from   bigint NOT NULL,
    currency_to     bigint NOT NULL,
    original_amount numeric,
    received_amount numeric,
    date            date,
    CONSTRAINT FK_CURRENCY_FROM_ID FOREIGN KEY (currency_from)
        REFERENCES currencies (id),
    CONSTRAINT FK_CURRENCY_TO_ID FOREIGN KEY (currency_to)
        REFERENCES currencies (id)
);

CREATE SEQUENCE currency_seq START 1 INCREMENT 50;
CREATE SEQUENCE history_seq START 1 INCREMENT 50;


