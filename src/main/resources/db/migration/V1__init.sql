/*create table if not exists customers (id bigserial primary key not null, name varchar(255));
create table if not exists customers_products (customer_id bigint not null, product_id bigint not null);
create table if not exists products (price integer, id bigserial not null, title varchar(255), primary key (id));
alter table if exists customers_products add constraint product_foreign_key foreign key (product_id) references products;
alter table if exists customers_products add constraint customer_foreign_key foreign key (customer_id) references customers;

INSERT INTO products (title, price) VALUES ('black deactivated', 2200), ('deactivated', 2200), ('stonks', 2300);

INSERT INTO customers (name) VALUES ('Andrew'), ('Lew'), ('Maxim');

INSERT INTO customers_products (customer_id, product_id) VALUES (1, 1), (1, 2), (2, 3), (3, 3), (3, 1);
*/

create table if not exists products (id bigserial primary key, title varchar(255), price int);

insert into products (title, price)
values
    ('black deactivated', 2200),
    ('deactivated', 2200),
    ('stonks', 2300);

create table if not exists users (
    id bigserial primary key,
    username varchar(36) not null,
    password varchar(80) not null,
    email varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
create table roles (
  id bigserial primary key,
  name varchar(50) not null,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp
);

create table users_roles (
    user_id bigint not null references users(id),
    role_id bigint not null references roles(id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', '1@ru'), ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', '2@ru');

insert into users_roles (user_id, role_id) VALUES (1, 1), (2, 2);

create table users_products (
                             user_id bigint not null references users(id),
                             product_id bigint not null references products(id),
                             primary key (user_id, product_id)
);
insert into users_products (user_id, product_id) VALUES (1, 1), (1, 2), (2, 2), (2, 3);

create table t_deactivated_token
(
    id           uuid primary key,
    c_keep_until timestamp not null check ( c_keep_until > now() )
);

