CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    "password" VARCHAR(255),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS pessoa (
    pes_id SERIAL PRIMARY KEY,
    pes_nome VARCHAR(200),
    pes_data_nascimento DATE,
    pes_sexo VARCHAR(9),
    pes_mae VARCHAR(200),
    pes_pai VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS unidade (
    unid_id SERIAL PRIMARY KEY,
    unid_nome VARCHAR(200),
    unid_sigla VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS cidade (
    cid_id SERIAL PRIMARY KEY,
    cid_nome VARCHAR(200),
    cid_uf CHAR(2)
);

CREATE TABLE IF NOT EXISTS endereco (
    end_id SERIAL PRIMARY KEY,
    end_tipo_logradouro VARCHAR(50),
    end_logradouro VARCHAR(200),
    end_numero INT,
    end_bairro VARCHAR(100),
    cid_id INT REFERENCES cidade(cid_id)
);

CREATE TABLE IF NOT EXISTS foto_pessoa (
    fp_id SERIAL PRIMARY KEY,
    pes_id INT REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    fp_data DATE,
    fp_bucket VARCHAR(50),
    fp_hash VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS pessoa_endereco (
    pes_id INT REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    end_id INT REFERENCES endereco(end_id) ON DELETE CASCADE,
    PRIMARY KEY (pes_id, end_id)
);

CREATE TABLE IF NOT EXISTS servidor_temporario (
    pes_id INT PRIMARY KEY REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    st_data_admissao DATE,
    st_data_demissao DATE
);

CREATE TABLE IF NOT EXISTS servidor_efetivo (
    pes_id INT PRIMARY KEY REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    se_matricula VARCHAR(20) UNIQUE
);

CREATE TABLE IF NOT EXISTS lotacao (
    lot_id SERIAL PRIMARY KEY,
    pes_id INT REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    unid_id INT REFERENCES unidade(unid_id) ON DELETE CASCADE,
    lot_data_lotacao DATE,
    lot_data_remocao DATE,
    lot_portaria VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS unidade_endereco (
    unid_id INT REFERENCES unidade(unid_id) ON DELETE CASCADE,
    end_id INT REFERENCES endereco(end_id) ON DELETE CASCADE,
    PRIMARY KEY (unid_id, end_id)
);
