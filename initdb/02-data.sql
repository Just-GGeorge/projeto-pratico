-- CIDADES
INSERT INTO cidade (cid_nome, cid_uf) VALUES
('Cuiabá', 'MT'),
('Várzea Grande', 'MT');

-- ENDEREÇOS
INSERT INTO endereco (end_tipo_logradouro, end_logradouro, end_numero, end_bairro, cid_id) VALUES
('Rua', 'Rua A', 100, 'Centro', 1),
('Avenida', 'Av. B', 200, 'Bairro Novo', 2);

-- UNIDADES
INSERT INTO unidade (unid_nome, unid_sigla) VALUES
('Secretaria de Educação', 'SED'),
('Secretaria de Saúde', 'SES');

-- UNIDADE_ENDERECO
INSERT INTO unidade_endereco (unid_id, end_id) VALUES
(1, 1),
(2, 2);

-- PESSOAS
INSERT INTO pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES
-- Efetivos Unidade 1
('Carlos Martins', '1980-01-01', 'Masculino', 'Ana Martins', 'Pedro Martins'),
('Luana Ribeiro', '1982-02-02', 'Feminino', 'Maria Ribeiro', 'José Ribeiro'),
('Thiago Souza', '1985-03-03', 'Masculino', 'Clara Souza', 'João Souza'),
('Fernanda Dias', '1987-04-04', 'Feminino', 'Elaine Dias', 'Paulo Dias'),
('Rodrigo Pinto', '1990-05-05', 'Masculino', 'Lucia Pinto', 'Marcos Pinto'),
-- Temporários Unidade 1
('Daniela Freitas', '1991-06-06', 'Feminino', 'Ivone Freitas', 'Lucas Freitas'),
('Vinícius Rocha', '1988-07-07', 'Masculino', 'Vera Rocha', 'Carlos Rocha'),
('Amanda Costa', '1993-08-08', 'Feminino', 'Regina Costa', 'Ronaldo Costa'),
('Henrique Lima', '1994-09-09', 'Masculino', 'Patrícia Lima', 'Francisco Lima'),
('Tatiane Alves', '1989-10-10', 'Feminino', 'Isabel Alves', 'Roberto Alves'),
-- Efetivos Unidade 2
('Bruna Mendes', '1992-11-11', 'Feminino', 'Raquel Mendes', 'Jorge Mendes'),
('Marcos Teixeira', '1986-12-12', 'Masculino', 'Selma Teixeira', 'André Teixeira'),
('Carolina Nunes', '1995-01-13', 'Feminino', 'Teresa Nunes', 'Caio Nunes'),
('Anderson Farias', '1993-02-14', 'Masculino', 'Joana Farias', 'Gilberto Farias'),
('Juliana Pires', '1990-03-15', 'Feminino', 'Eliane Pires', 'Bruno Pires'),
-- Temporários Unidade 2
('Gustavo Silveira', '1987-04-16', 'Masculino', 'Helena Silveira', 'Fábio Silveira'),
('Michele Castro', '1988-05-17', 'Feminino', 'Fabiana Castro', 'Alberto Castro'),
('Leonardo Vieira', '1991-06-18', 'Masculino', 'Daniela Vieira', 'Edson Vieira'),
('Sabrina Ramos', '1994-07-19', 'Feminino', 'Juliana Ramos', 'Renato Ramos'),
('Felipe Cardoso', '1989-08-20', 'Masculino', 'Rosana Cardoso', 'Marcelo Cardoso');

-- PESSOA_ENDERECO
INSERT INTO pessoa_endereco (pes_id, end_id) VALUES
(1, 1), (2, 2), (3, 1), (4, 2), (5, 1),
(6, 2), (7, 1), (8, 2), (9, 1), (10, 2),
(11, 1), (12, 2), (13, 1), (14, 2), (15, 1),
(16, 2), (17, 1), (18, 2), (19, 1), (20, 2);

-- SERVIDORES EFETIVOS
INSERT INTO servidor_efetivo (pes_id, se_matricula) VALUES
(1, 'EFT0001'), (2, 'EFT0002'), (3, 'EFT0003'), (4, 'EFT0004'), (5, 'EFT0005'),
(11, 'EFT0006'), (12, 'EFT0007'), (13, 'EFT0008'), (14, 'EFT0009'), (15, 'EFT0010');

-- SERVIDORES TEMPORÁRIOS
INSERT INTO servidor_temporario (pes_id, st_data_admissao, st_data_demissao) VALUES
(6, '2022-01-01', NULL), (7, '2022-02-01', NULL), (8, '2022-03-01', NULL),
(9, '2022-04-01', NULL), (10, '2022-05-01', NULL),
(16, '2022-06-01', NULL), (17, '2022-07-01', NULL), (18, '2022-08-01', NULL),
(19, '2022-09-01', NULL), (20, '2022-10-01', NULL);

-- LOTAÇÕES UNIDADE 1
INSERT INTO lotacao (pes_id, unid_id, lot_data_lotacao, lot_data_remocao, lot_portaria) VALUES
(1, 1, '2023-01-01', NULL, 'Portaria 101'),
(2, 1, '2023-01-01', NULL, 'Portaria 102'),
(3, 1, '2023-01-01', NULL, 'Portaria 103'),
(4, 1, '2023-01-01', NULL, 'Portaria 104'),
(5, 1, '2023-01-01', NULL, 'Portaria 105'),
(6, 1, '2023-02-01', NULL, 'Portaria 106'),
(7, 1, '2023-02-01', NULL, 'Portaria 107'),
(8, 1, '2023-02-01', NULL, 'Portaria 108'),
(9, 1, '2023-02-01', NULL, 'Portaria 109'),
(10, 1, '2023-02-01', NULL, 'Portaria 110');

-- LOTAÇÕES UNIDADE 2
INSERT INTO lotacao (pes_id, unid_id, lot_data_lotacao, lot_data_remocao, lot_portaria) VALUES
(11, 2, '2023-01-15', NULL, 'Portaria 201'),
(12, 2, '2023-01-15', NULL, 'Portaria 202'),
(13, 2, '2023-01-15', NULL, 'Portaria 203'),
(14, 2, '2023-01-15', NULL, 'Portaria 204'),
(15, 2, '2023-01-15', NULL, 'Portaria 205'),
(16, 2, '2023-03-01', NULL, 'Portaria 206'),
(17, 2, '2023-03-01', NULL, 'Portaria 207'),
(18, 2, '2023-03-01', NULL, 'Portaria 208'),
(19, 2, '2023-03-01', NULL, 'Portaria 209'),
(20, 2, '2023-03-01', NULL, 'Portaria 210');
