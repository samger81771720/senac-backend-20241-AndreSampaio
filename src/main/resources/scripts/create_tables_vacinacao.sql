create schema if not exists VACINACAO;

create table if not exists VACINACAO.PAIS(
	id_Pais integer auto_increment not null,
	nome varchar(100),
	sigla varchar(100),
	constraint PAIS_pk primary key(id_Pais)
);

create table if not exists VACINACAO.PESSOA(
	id_Pessoa integer auto_increment not null,
	id_Pais integer not null,
	tipo integer not null comment '1 - Pesquisador, 2- Voluntário, 3 - Público Geral',
	nome varchar(255) not null,
	dataNascimento date not null,
	sexo char(1) not null,
	cpf varchar(11) unique not null,
	constraint PESSOA_pk primary key(id_Pessoa),
	constraint PAIS_fk foreign key(id_Pais) references VACINACAO.PAIS(id_Pais)
);

create table if not exists VACINACAO.VACINA(
    id_Vacina integer auto_increment not null,
    id_Pesquisador integer not null,
    id_Pais integer not null,
    nome varchar(255) not null,
    estagio_Da_Pesquisa integer not null comment '1 - Inicial, 2 - Testes, 3 - Aplicação em Massa',
    dataInicioDaPesquisa date not null,
    constraint VACINA_pk primary key(id_Vacina),
    constraint PESQUISADOR_fk foreign key(id_Pesquisador) references VACINACAO.PESSOA(id_Pessoa),
    constraint PAIS2_fk foreign key(id_Pais) references VACINACAO.PAIS(id_Pais)
);

create table if not exists VACINACAO.APLICACAO_VACINA(
	id_Aplicacao integer auto_increment not null,
	id_Pessoa integer not null,
	id_Vacina integer not null,
	dataAplicacao date not null,
	avaliacaoDaReacao integer not null comment '1 = Péssima à 5 = Ótima',
	constraint APLICACAO_pk primary key(id_Aplicacao),
	constraint PESSOA_fk foreign key(id_Pessoa) references VACINACAO.PESSOA(id_Pessoa),
	constraint VACINA_fk foreign key(id_Vacina) references VACINACAO.VACINA(id_Vacina)
);

ALTER TABLE VACINACAO.VACINA ADD COLUMN mediaVacina decimal(5,2);


INSERT INTO VACINACAO.PAIS (nome, sigla) VALUES
('Brazil', 'BR'),
('United States', 'US'),
('Canada', 'CA'),
('United Kingdom', 'UK'),
('Germany', 'DE'),
('France', 'FR'),
('Italy', 'IT'),
('Spain', 'ES'),
('Japan', 'JP'),
('Australia', 'AU');


-- Inserir 50 registros de pessoas
INSERT INTO VACINACAO.PESSOA (id_Pais, tipo, nome, dataNascimento, sexo, cpf)
VALUES
-- Pesquisadores
(1, 1, 'Ana Silva', '1990-03-15', 'F', '12345678901'),
(2, 1, 'Carlos Oliveira', '1985-07-20', 'M', '23456789012'),
(3, 1, 'Maria Santos', '1978-05-10', 'F', '34567890123'),
(4, 1, 'Pedro Costa', '1982-09-28', 'M', '45678901234'),
(5, 1, 'Luisa Pereira', '1995-11-03', 'F', '56789012345'),

-- Voluntários
(6, 2, 'João Souza', '1992-02-25', 'M', '67890123456'),
(7, 2, 'Camila Oliveira', '1989-08-12', 'F', '78901234567'),
(8, 2, 'Miguel Santos', '1975-04-19', 'M', '89012345678'),
(9, 2, 'Rafaela Costa', '1987-06-08', 'F', '90123456789'),
(10, 2, 'Gabriel Lima', '1998-12-30', 'M', '01234567890'),

-- Público Geral
(1, 3, 'Juliana Ferreira', '1980-01-05', 'F', '10123456789'),
(2, 3, 'Felipe Almeida', '1973-10-14', 'M', '20123456789'),
(3, 3, 'Carolina Rocha', '1991-07-22', 'F', '30123456789'),
(4, 3, 'Rodrigo Martins', '1988-04-11', 'M', '40123456789'),
(5, 3, 'Isabela Oliveira', '1979-03-02', 'F', '50123456789'),
(6, 3, 'Lucas Fernandes', '1983-09-18', 'M', '60123456789'),
(7, 3, 'Amanda Gonçalves', '1996-11-28', 'F', '70123456789'),
(8, 3, 'Diego Silva', '1981-08-07', 'M', '80123456789'),
(9, 3, 'Bruna Pereira', '1990-05-26', 'F', '90123456780'),
(10, 3, 'Fernando Carvalho', '1984-12-09', 'M', '90123456781'),
(1, 3, 'Marcela Santos', '1977-02-17', 'F', '90123456782'),
(2, 3, 'Thiago Oliveira', '1986-06-29', 'M', '90123456783'),
(3, 3, 'Vanessa Souza', '1994-10-08', 'F', '90123456784'),
(4, 3, 'Luciana Costa', '1989-07-15', 'F', '90123456785'),
(5, 3, 'Guilherme Almeida', '1982-03-28', 'M', '90123456786'),
(6, 3, 'Marina Lima', '1987-05-13', 'F', '90123456787'),
(7, 3, 'Rafael Oliveira', '1993-08-20', 'M', '90123456788'),
(8, 3, 'Fernanda Pereira', '1985-01-29', 'F', '90123456790'),
(9, 3, 'André Santos', '1991-04-06', 'M', '90123456791'),
(10, 3, 'Patrícia Silva', '1988-11-14', 'F', '90123456792'),
(1, 3, 'Ricardo Almeida', '1979-09-23', 'M', '90123456793'),
(2, 3, 'Ana Carolina', '1984-12-30', 'F', '90123456794'),
(3, 3, 'Renato Lima', '1997-03-05', 'M', '90123456795'),
(4, 3, 'Laura Oliveira', '1980-06-12', 'F', '90123456796'),
(5, 3, 'José Pereira', '1972-08-19', 'M', '90123456797'),
(6, 3, 'Aline Costa', '1990-01-26', 'F', '90123456798'),
(7, 3, 'Gabriela Fernandes', '1988-04-02', 'F', '90123456799'),
(8, 3, 'Daniel Souza', '1976-07-11', 'M', '90123456800'),
(9, 3, 'Cristina Santos', '1995-10-18', 'F', '90123456801'),
(10, 3, 'Roberto Oliveira', '1983-02-27', 'M', '90123456802'),
(1, 3, 'Mariana Costa', '1974-05-04', 'F', '90123456803'),
(2, 3, 'Eduardo Almeida', '1993-07-21', 'M', '90123456804'),
(3, 3, 'Tatiane Pereira', '1986-09-28', 'F', '90123456805'),
(4, 3, 'Vinícius Silva', '1981-11-06', 'M', '90123456806'),
(5, 3, 'Renata Santos', '1990-03-15', 'F', '90123456807'),
(6, 3, 'Marcelo Oliveira', '1988-06-22', 'M', '90123456808'),
(7, 3, 'Juliana Lima', '1977-08-29', 'F', '90123456809'),
(8, 3, 'Thales Costa', '1994-10-05', 'M', '90123456810'),
(9, 3, 'Vanessa Almeida', '1983-12-12', 'F', '90123456811'),
(10, 3, 'Leonardo Pereira', '1978-02-18', 'M', '90123456812');




insert into VACINACAO.VACINA (id_Pesquisador,id_Pais,nome,estagio_Da_Pesquisa,dataInicioDaPesquisa)values
(1,1,'VACINA 1',1,'2021-01-01'),
(4,2,'VACINA 2',2,'2022-02-02'),
(1,3,'VACINA 3',3,'2023-03-03'),
(4,4,'VACINA 4',2,'2024-04-04'),
(1,6,'VACINA 5',1,'2025-05-05'),
(1,7,'VACINA 6',2,'2026-06-06'),
(4,8,'VACINA 7',1,'2027-07-07');

INSERT INTO VACINACAO.VACINA (id_Pesquisador, id_Pais, nome, estagio_Da_Pesquisa, dataInicioDaPesquisa) VALUES
(1, 1, 'Vacina X', 1, '2026-01-01'),
(2, 2, 'Vacina Y', 2, '2027-02-02'),
(3, 3, 'Vacina Z', 1, '2028-03-03'),
(4, 4, 'Vacina W', 3, '2029-04-04'),
(5, 5, 'Vacina V', 2, '2030-05-05'),
(1, 6, 'Vacina U', 1, '2031-06-06'),
(2, 7, 'Vacina T', 2, '2032-07-07'),
(3, 8, 'Vacina S', 1, '2033-08-08'),
(4, 9, 'Vacina R', 2, '2034-09-09'),
(5, 10, 'Vacina Q', 1, '2035-10-10'),
(1, 10, 'Vacina P', 3, '2036-11-11'),
(2, 9, 'Vacina O', 2, '2037-12-12'),
(3, 2, 'Vacina N', 1, '2038-01-13'),
(4, 3, 'Vacina M', 2, '2039-02-14'),
(5, 4, 'Vacina L', 1, '2040-03-15'),
(1, 5, 'Vacina K', 2, '2041-04-16'),
(2, 6, 'Vacina J', 1, '2042-05-17'),
(3, 7, 'Vacina I', 2, '2043-06-18'),
(4, 8, 'Vacina H', 1, '2044-07-19'),
(5, 9, 'Vacina G', 2, '2045-08-20');


INSERT INTO VACINACAO.APLICACAO_VACINA (id_Pessoa, id_Vacina, dataAplicacao, avaliacaoDaReacao) VALUES
(1, 1, '2024-03-01', 5),
(2, 2, '2024-03-02', 4),
(3, 3, '2024-03-03', 3),
(4, 4, '2024-03-04', 2),
(5, 5, '2024-03-05', 1);

