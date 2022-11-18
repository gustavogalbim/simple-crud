Simples exemplo de um cadastro de pessoa.

Tabela

CREATE TABLE pessoa
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(255),
    cpf VARCHAR(255),
   	data_nascimento VARCHAR(255)
);
