DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, price int, title VARCHAR(255), PRIMARY KEY(id));
INSERT INTO products (title, price) VALUES ('black deactivated', 2200), ('deactivated', 2200), ('stonks', 2300);


DROP TABLE customers IF EXISTS;
CREATE TABLE IF NOT EXISTS customers (id bigserial, name VARCHAR(255), PRIMARY KEY(id));
INSERT INTO customers (name) VALUES ('Andrew'), ('Lew'), ('Maxim');

DROP TABLE customers_products IF EXISTS;
CREATE TABLE IF NOT EXIST customers_products (customer_id bigserial, product id bigserial, FOREIGN KEY(customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO customers_products (customer_id, product_id) VALUES (1, 1), (1, 2), (2, 3), (3, 3), (3, 1);
