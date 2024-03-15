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
