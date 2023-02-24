DROP DATABASE IF EXISTS ferragem;

CREATE DATABASE ferragem;

\c ferragem;

CREATE TABLE produto (
    id serial primary key,
    descricao text not null,
    preco real,
    estoque integer
);

INSERT INTO produto (descricao, preco, estoque) VALUES
('teste1', 100.0, 1000);

INSERT INTO produto (descricao, preco, estoque) VALUES
('teste2', 100.0, 1000);

CREATE TABLE venda (
    id serial primary key,
    data_hora timestamp default current_timestamp   
);

CREATE TABLE item (
    id serial primary key,
    produto_id integer references produto (id),
    venda_id integer references venda (id),
    quantidade integer
);
