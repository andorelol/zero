create database andre;
use andre;
CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DOUBLE NOT NULL,
    estoque INT NOT NULL
);

CREATE TABLE vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_venda DATE NOT NULL,
    vendedor VARCHAR(255) NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

CREATE TABLE vendedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

select*from produtos;
insert into produtos (nome, preco, estoque) values ("detergente","3","500");

select*from vendedores;
insert into vendedores (nome) values ("douglas");

select*from vendas;

INSERT INTO vendas (data_venda, vendedor, produto_id, quantidade) VALUES ('2024-01-01', 'guto', 2, 5);
