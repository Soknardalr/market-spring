create table if not exists customers (id bigserial primary key not null, name varchar(255));
create table if not exists customers_products (customer_id bigint not null, product_id bigint not null);
create table if not exists products (price integer, id bigserial not null, title varchar(255), primary key (id));
alter table if exists customers_products add constraint product_foreign_key foreign key (product_id) references products;
alter table if exists customers_products add constraint customer_foreign_key foreign key (customer_id) references customers;

INSERT INTO products (title, price) VALUES ('black deactivated', 2200), ('deactivated', 2200), ('stonks', 2300);

INSERT INTO customers (name) VALUES ('Andrew'), ('Lew'), ('Maxim');

INSERT INTO customers_products (customer_id, product_id) VALUES (1, 1), (1, 2), (2, 3), (3, 3), (3, 1);
