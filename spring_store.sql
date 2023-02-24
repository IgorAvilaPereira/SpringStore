DROP DATABASE IF EXISTS spring_store;

CREATE DATABASE spring_store;

\c spring_store;

CREATE TABLE produto (
    id serial primary key,
    descricao text not null,
    preco double precision,
    estoque double precision
);

CREATE TABLE venda (
    id serial primary key,
    data_hora timestamp default current_timestamp
);

CREATE TABLE item ( 
    id serial primary key,
    quantidade double precision,
    produto_id integer references produto (id),
    venda_id integer references venda (id),
    UNIQUE(produto_id, venda_id)
);

INSERT INTO produto (descricao, preco, estoque) VALUES
('teste1', 100.0, 1000);

INSERT INTO produto (descricao, preco, estoque) VALUES
('teste2', 100.0, 1000);
