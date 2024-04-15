-- liquibase formatted sql

-- changeset YuriPetukhov:1

CREATE TABLE metrics (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(80) NOT NULL CHECK (name !~ '^\s*$'),
  value DOUBLE PRECISION,
  timestamp TIMESTAMP
);

