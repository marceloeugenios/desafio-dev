CREATE TABLE arquivo
  (
     id          INTEGER GENERATED BY DEFAULT AS IDENTITY,
     data_upload TIMESTAMP NOT NULL,
     nome        VARCHAR(200) NOT NULL,
     usuario_id  UUID NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE transacao_tipo
  (
     id                 INTEGER NOT NULL,
     descricao          VARCHAR(200) NOT NULL,
     transacao_natureza VARCHAR(50) NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE transacao
  (
     id              BIGINT GENERATED BY DEFAULT AS IDENTITY,
     cartao          VARCHAR(12),
     cpf             VARCHAR(11),
     data_transacao  TIMESTAMP NOT NULL,
     loja_dono       VARCHAR(100) NOT NULL,
     loja_nome       VARCHAR(100) NOT NULL,
     valor           DECIMAL(19, 2) NOT NULL,
     _transacao_tipo INTEGER NOT NULL,
     _arquivo        INTEGER NOT NULL,
     PRIMARY KEY (id),
     CONSTRAINT fk5iybhdf0moubkadd7hr8t7qmi FOREIGN KEY (_arquivo) REFERENCES
     arquivo,
     CONSTRAINT fkdi6uq8ydrlc9lv40sreflni7d FOREIGN KEY (_transacao_tipo)
     REFERENCES transacao_tipo
  );