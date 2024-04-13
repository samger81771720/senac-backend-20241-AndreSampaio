create schema if not exists VACINACAO;

use VACINACAO;

create table if not exists VACINACAO.PAIS(
	id_Pais integer auto_increment not null,
	nome varchar(100),
	sigla varchar(100),
	constraint PAIS_pk primary key(id_Pais)
);

create table if not exists VACINACAO.PESSOA(
	id_Pessoa integer auto_increment not null,
	id_Pais integer not null,
	tipo integer not null comment '1 - Pesquisador, 2- Volunt�rio, 3 - P�blico Geral',
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
    estagio_Da_Pesquisa integer not null comment '1 - Inicial, 2 - Testes, 3 - Aplica��o em Massa',
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
	avaliacaoDaReacao integer not null comment '1 = P�ssima � 5 = �tima',
	constraint APLICACAO_pk primary key(id_Aplicacao),
	constraint PESSOA_fk foreign key(id_Pessoa) references VACINACAO.PESSOA(id_Pessoa),
	constraint VACINA_fk foreign key(id_Vacina) references VACINACAO.VACINA(id_Vacina)
);

ALTER TABLE VACINACAO.VACINA ADD COLUMN mediaVacina decimal(5,2);

INSERT INTO VACINACAO.pais (nome, sigla) VALUES
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


INSERT INTO VACINACAO.PESSOA (id_Pais, tipo, nome, dataNascimento, sexo, cpf) VALUES
(1, 1, 'Jo�o Silva', '1990-05-15', 'M', '12345678901'),
(2, 2, 'Maria Santos', '1985-10-25', 'F', '05425789653'),
(3, 3, 'ANDR� SAMPAIO', '1980-11-27', 'm', '05157892756'),
(4, 1, 'Amarante Jord�o', '1992-05-15', 'M', '12345678909'),
(5, 2, 'JO�O MEDEIROS', '1981-12-26', 'm', '98765432102');
 


insert into VACINACAO.VACINA (id_Pesquisador,id_Pais,nome,estagio_Da_Pesquisa,dataInicioDaPesquisa)values
(1,1,'VACINA 1',1,'2024-03-02'),
(4,2,'VACINA 2',2,'2024-03-01'),
(1,3,'VACINA 3',3,'2024-01-12'),
(4,4,'VACINA 4',2,'1900-09-09'),
(1,5,'VACINA 5',1,'1903-03-03');


INSERT INTO VACINACAO.APLICACAO_VACINA (id_Pessoa, id_Vacina, dataAplicacao, avaliacaoDaReacao) VALUES
(1, 1, '2024-03-01', 5),
(2, 2, '2024-03-02', 4),
(3, 3, '2024-03-03', 3),
(4, 4, '2024-03-04', 2),
(5, 5, '2024-03-05', 1);


