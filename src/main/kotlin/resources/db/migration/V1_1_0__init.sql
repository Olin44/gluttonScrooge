CREATE TABLE IF NOT EXISTS recipes (id BIGSERIAL PRIMARY KEY, title VARCHAR(30) NOT NULL);
INSERT INTO recipes (title) VALUES ('First recipe');