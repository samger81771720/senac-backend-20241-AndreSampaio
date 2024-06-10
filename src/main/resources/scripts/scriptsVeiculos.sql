create schema if not exists VEICULOS;

create table if not exists VEICULOS.MONTADORA(
	id INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(250) NOT NULL,
    paisFundacao VARCHAR(100),
    nomePresidente VARCHAR(250),
	dataFundacao date not null,
    CONSTRAINT MONTADORA_pk PRIMARY KEY (id)
); 


create table if not exists VEICULOS.CARRO(
	id integer auto_increment not null,
	idMontadora integer not null,
	modelo VARCHAR(100),
	placa VARCHAR(50),
	ano INT not null,
	valor DOUBLE not null,
	constraint CARRO_pk primary key(id),
	constraint MONTADORA_CARRO_fk foreign key(idMontadora) references VEICULOS.MONTADORA(id)
); 


INSERT INTO VEICULOS.MONTADORA (nome, paisFundacao, nomePresidente, dataFundacao)
VALUES 
('Toyota', 'Japan', 'Akio Toyoda', '1937-08-28'),
('Ford', 'USA', 'Jim Farley', '1903-06-16'),
('Volkswagen', 'Germany', 'Herbert Diess', '1937-05-28');



INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (1, 'Corolla', 'ABC1234', 2020, 20000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (1, 'Camry', 'DEF5678', 2021, 25000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (1, 'RAV4', 'GHI9012', 2019, 30000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (1, 'Prius', 'JKL3456', 2022, 24000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (1, 'Yaris', 'MNO7890', 2018, 15000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (2, 'Fiesta', 'PQR1234', 2020, 18000.00);


INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (2, 'Mustang', 'STU5678', 2021, 55000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values(2, 'Explorer', 'VWX9012', 2019, 35000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (2, 'Fusion', 'YZA3456', 2022, 27000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values(2, 'Escape', 'BCD7890', 2018, 22000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (3, 'Golf', 'EFG1234', 2020, 21000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (3, 'Passat', 'HIJ5678', 2021, 28000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (3, 'Tiguan', 'KLM9012', 2019, 32000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values(3, 'Jetta', 'NOP3456', 2022, 26000.00);

INSERT INTO VEICULOS.CARRO (idMontadora, modelo, placa, ano, valor)
values (3, 'Polo', 'QRS7890', 2018, 17000.00);