DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, price int, title VARCHAR(255), PRIMARY KEY(id));
INSERT INTO products (title, price) VALUES ('angelSport', 60), ('bullyingWhiteBoys', 10), ('deepThroat', 224);