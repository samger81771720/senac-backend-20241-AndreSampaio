create schema if not exists VACINACAO;

use VACINACAO;

create table if not exists VACINACAO.PESSOA(
	id_Pessoa integer auto_increment not null,
	tipo integer not null comment '1 - Pesquisador, 2- Voluntário, 3 - Público Geral',
	nome varchar(255) not null,
	dataNascimento date not null,
	sexo char(1) not null,
	cpf varchar(11) unique not null,
	constraint PESSOA_pk primary key(id_Pessoa) 
);

-- Registro 1
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (1, 'João Silva', '1990-05-15', 'm', '12345678901');

-- Registro 2
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (2, 'Maria Oliveira', '1985-08-22', 'f', '23456789012');

-- Registro 3
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (3, 'Pedro Santos', '2000-03-10', 'm', '34567890123');

-- Registro 4
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (1, 'Ana Souza', '1978-11-05', 'f', '45678901234');

-- Registro 5
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (2, 'Carlos Pereira', '1995-07-18', 'm', '56789012345');

-- Registro 6
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (3, 'Fernanda Lima', '1980-12-30', 'f', '67890123456');

-- Registro 7
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (1, 'Ricardo Mendes', '1992-09-25', 'm', '78901234567');

-- Registro 8
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (2, 'Lúcia Rocha', '1987-04-14', 'f', '89012345678');

-- Registro 9
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (3, 'Gustavo Almeida', '2005-01-08', 'm', '90123456789');

-- Registro 10
INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf)
VALUES (1, 'Camila Oliveira', '1983-06-20', 'f', '01234567890');


/*
 * -- drop database if exists VACINACAO;

create schema if not exists VACINACAO;

use VACINACAO;

create table if not exists VACINACAO.PESSOA(
	id_Pessoa integer auto_increment not null,
	tipo integer not null comment '1 - Pesquisador, 2- Voluntário, 3 - Público Geral',
	nome varchar(255) not null,
	dataNascimento date not null,
	sexo char(1) not null,
	cpf varchar(11) unique not null,
	constraint PESSOA_pk primary key(id_Pessoa) 
);

create table if not exists VACINACAO.VACINA(
	id_Vacina integer auto_increment not null,
	id_Pessoa integer not null,
	nome varchar(100) not null,
	pais_De_Origem varchar(50) not null,
	pesquisadorResponsavel varchar(255) not null,
	estagioDaVacina integer not null,
	dataInicioPesquisa date not null,
	constraint VACINACAO_pk primary key(id_Vacina),
	constraint PESSOA_fk foreign key (id_Pessoa) references VACINACAO.PESSOA(id_Pessoa)
);*/

/*
create schema if not EXISTS EXEMPLOS;
 
use EXEMPLOS;
 
CREATE table if not EXISTS exemplos.CARTA (
	ID INTEGER auto_increment NOT NULL,
	NOME varchar(255) NOT NULL,
	FORCA INTEGER NOT NULL,
	INTELIGENCIA INTEGER NOT NULL,
	VELOCIDADE INTEGER NOT NULL,
	DATA_CADASTRO DATE NOT NULL,
	CONSTRAINT CARTA_pk PRIMARY KEY (ID)
);
 
-- V2: criação da tabela JOGADOR
CREATE table if not EXISTS exemplos.JOGADOR (
	ID INTEGER auto_increment NOT NULL,
	NOME varchar(255) NOT NULL,
	EMAIL varchar(255) NOT NULL,
	DATA_NASCIMENTO DATE NOT NULL,
	TOTAL_PARTIDAS INTEGER NOT NULL,
	PERCENTUAL_VITORIAS DOUBLE NOT NULL,
	CONSTRAINT CARTA_pk PRIMARY KEY (ID)
);
 
-- V3: criação das tabelas PARTIDA e CARTA_PARTIDA
CREATE table if not EXISTS exemplos.PARTIDA (
	ID INTEGER auto_increment NOT NULL,
	ID_JOGADOR INTEGER NOT NULL,
	ROUNDS_VENCIDOS_JOGADOR INTEGER NOT NULL DEFAULT 0,
	ROUNDS_VENCIDOS_CPU INTEGER NOT NULL DEFAULT 0,
	ROUNDS_EMPATADOS INTEGER NOT NULL DEFAULT 0,
	RESULTADO varchar(255),
	DATA DATE NOT NULL,
	FORCA_UTILIZADA BOOLEAN,
	INTELIGENCIA_UTILIZADA BOOLEAN,
	VELOCIDADE_UTILIZADA BOOLEAN,
	CONSTRAINT PARTIDA_PK PRIMARY KEY (ID),
	CONSTRAINT ID_JOGADOR foreign key (ID_JOGADOR) references exemplos.JOGADOR(ID)
);
 
CREATE table if not EXISTS exemplos.CARTA_PARTIDA (
	ID INTEGER auto_increment NOT NULL,
	ID_PARTIDA INTEGER NOT NULL,
	ID_CARTA INTEGER NOT NULL,
	DO_JOGADOR BOOLEAN,
	UTILIZADA BOOLEAN,
	CONSTRAINT CARTA_PARTIDA_PK PRIMARY KEY (ID),
	CONSTRAINT ID_PARTIDA foreign key (ID_PARTIDA) references exemplos.PARTIDA(ID),
	CONSTRAINT ID_CARTA foreign key (ID_CARTA) references exemplos.CARTA(ID)
);
[09:05] TATIANA SAKUMA
-- CARTAS
INSERT INTO exemplos.carta
(NOME, FORCA, INTELIGENCIA, VELOCIDADE, DATA_CADASTRO)
VALUES('Popó', 5, 2, 2, curdate());
 
INSERT INTO exemplos.carta
(NOME, FORCA, INTELIGENCIA, VELOCIDADE, DATA_CADASTRO)
VALUES('Rocky', 3, 5, 2, curdate());
 
INSERT INTO exemplos.carta
(NOME, FORCA, INTELIGENCIA, VELOCIDADE, DATA_CADASTRO)
VALUES('Ronaldinho Gaúcho', 2, 5, 3, curdate());
 
INSERT INTO exemplos.carta
(NOME, FORCA, INTELIGENCIA, VELOCIDADE, DATA_CADASTRO)
VALUES('Severino', 4, 1, 1, curdate());
 
INSERT INTO exemplos.carta
(NOME, FORCA, INTELIGENCIA, VELOCIDADE, DATA_CADASTRO)
VALUES('Iron Man', 3, 4, 2, curdate());
 
INSERT INTO exemplos.carta
(NOME, FORCA, INTELIGENCIA, VELOCIDADE, DATA_CADASTRO)
VALUES('Batman', 5, 5, 5, curdate());
 
INSERT INTO exemplos.carta
(NOME, FORCA, INTELIGENCIA, VELOCIDADE, DATA_CADASTRO)
VALUES('Superman', 5, 2, 4, curdate());
 
INSERT INTO exemplos.carta
(NOME, FORCA, INTELIGENCIA, VELOCIDADE, DATA_CADASTRO)
VALUES('Flash', 1, 3, 5, curdate());
 
-- JOGADORES
INSERT INTO exemplos.jogador
(ID, NOME, EMAIL, DATA_NASCIMENTO, TOTAL_PARTIDAS, PERCENTUAL_VITORIAS)
VALUES(1, 'Edson Arantes do Nascimento', 'pele10@gmail.com', '1940-10-23', 0, 0);
 
INSERT INTO exemplos.jogador
(ID, NOME, EMAIL, DATA_NASCIMENTO, TOTAL_PARTIDAS, PERCENTUAL_VITORIAS)
VALUES(2, 'Carlos Caetano Verri', 'dunga5@gmail.com', '1963-10-31', 0, 0);
 
INSERT INTO exemplos.jogador
(ID, NOME, EMAIL, DATA_NASCIMENTO, TOTAL_PARTIDAS, PERCENTUAL_VITORIAS)
VALUES(3, 'Marcos André Batista', 'vampeta8@gmail.com', '1974-03-13', 0, 0);
 
-- PARTIDAS (exemplo vazio)
INSERT INTO exemplos.partida
(ID, ID_JOGADOR, ROUNDS_VENCIDOS_JOGADOR, ROUNDS_VENCIDOS_CPU, ROUNDS_EMPATADOS, RESULTADO, `DATA`, FORCA_UTILIZADA, INTELIGENCIA_UTILIZADA, VELOCIDADE_UTILIZADA)
VALUES(0, 0, 0, 0, 0, '', '', 0, 0, 0);
 
 
 
 *
 **/
