drop database if exists vacinacao;

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
	id_Pesquisador integer not null,
	nome varchar(255) not null,
	pais_Origem varchar(255) not null,
	estagio_Da_Pesquisa integer not null comment '1 - Inicial, 2 - Testes, 3 - Aplicação em Massa',
	dataInicioDaPesquisa date not null,
	constraint VACINA_pk primary key(id_Vacina),
	constraint PESQUISADOR_fk foreign key(id_Pesquisador) references VACINACAO.PESSOA(id_Pessoa)
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


INSERT INTO VACINACAO.PESSOA (tipo, nome, dataNascimento, sexo, cpf) VALUES 
(2, 'Carlos Eduardo Silveira', '1967-11-13', 'M', '16391459401'),
(1, 'Maria Clara da Cunha', '1980-03-27', 'F', '16391459402'),
(3, 'Kaique da Conceição', '1966-09-11', 'M', '16391459410'),
(1, 'Josué Silveira', '1980-11-07','M', '05469852365'),
(1, 'João Siqueira', '1969-11-01','M','56985632012'),
(2, 'Joana Silva', '1970-05-15', 'F', '16391459403'),
(1, 'Roberto Alves', '1985-07-30', 'M', '16391459404'),
(3, 'Ana Maria', '1975-02-20', 'F', '16391459405');


INSERT INTO VACINACAO.VACINA (id_Pesquisador, nome, pais_Origem, estagio_Da_Pesquisa, dataInicioDaPesquisa) values
(5, 'CoronaVac', 'China', 1, '2022-11-21'),
(4, 'AstraZeneca', 'Reino Unido', 2, '2022-11-22'),
(2, 'Pfizer', 'Estados Unidos', 3, '2022-11-30'),
(6, 'Moderna', 'Estados Unidos', 1, '2023-01-01'),
(7, 'Johnson & Johnson', 'Estados Unidos', 2, '2023-01-02'),
(8, 'Sputnik V', 'Rússia', 3, '2023-01-03');

INSERT INTO VACINACAO.APLICACAO_VACINA (id_Pessoa, id_Vacina, dataAplicacao, avaliacaoDaReacao) VALUES 
(1, 1, '2022-11-21', 1),
(2, 2, '2022-11-22', 2),
(3, 3, '2022-11-30', 5),
(6, 4, '2023-01-01', 3),
(7, 5, '2023-01-02', 4),
(8, 6, '2023-01-03', 5);


