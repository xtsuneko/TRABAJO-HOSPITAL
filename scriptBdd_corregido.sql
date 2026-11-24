DROP DATABASE IF EXISTS veterinaria;
CREATE DATABASE veterinaria;
USE veterinaria;

DROP TABLE IF EXISTS horario_laboral;
CREATE TABLE horario_laboral (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	descripcion VARCHAR(100) NOT NULL
);


INSERT INTO horario_laboral (descripcion) VALUES('diurno');
INSERT INTO horario_laboral (descripcion) VALUES('vespertino');
INSERT INTO horario_laboral (descripcion) VALUES('nocturno');

DROP TABLE IF EXISTS persona;
CREATE TABLE persona (
  dni VARCHAR(10) PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  telefono VARCHAR(100) NOT NULL,
  activo BOOLEAN DEFAULT TRUE
);

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  pass VARCHAR(100) NOT NULL,
  dni_persona VARCHAR(10),
  activo BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (dni_persona) REFERENCES persona(dni)
);

DROP TABLE IF EXISTS rol;
CREATE TABLE rol (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  descripcion VARCHAR(100) NOT NULL
);


INSERT INTO rol (descripcion) VALUES ('Administrador');
INSERT INTO rol (descripcion) VALUES ('Veterinario');
INSERT INTO rol (descripcion) VALUES ('Recepcionista');

DROP TABLE IF EXISTS personal;
CREATE TABLE personal (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  sueldo DOUBLE UNSIGNED NOT NULL,
  horario_laboral_id INT UNSIGNED,
  id_usuario INT UNSIGNED,
  activo BOOLEAN DEFAULT TRUE,
  id_rol INT UNSIGNED,
  foreign key (id_rol) REFERENCES rol(id),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id),
  FOREIGN KEY (horario_laboral_id) REFERENCES horario_laboral(id)
);


DROP TABLE IF EXISTS cliente;
CREATE TABLE cliente (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  direccion VARCHAR(100) NOT NULL,
  id_usuario INT UNSIGNED,
  activo BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

DROP TABLE IF EXISTS turno;
CREATE TABLE turno (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  id_cliente int UNSIGNED,
  id_personal int UNSIGNED,
  fecha date,
  horario time,
  motivo_consulta VARCHAR(500) NOT NULL,
  activo BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (id_personal) REFERENCES personal(id),
  FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

DROP TABLE IF EXISTS revision;
CREATE TABLE revision (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  id_turno int UNSIGNED,
  diagnostico VARCHAR(500) NOT NULL,
  tratamiento VARCHAR(500) NOT NULL,
  activo BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (id_turno) REFERENCES turno(id)
);


INSERT INTO persona(dni, nombre, apellido, telefono) VALUES
('43629947', 'Lucia',     'Gallo',     '2233456495'),
('42664467', 'Geronimo',  'Zampini',   '2233030484'),
('41629947', 'Victoria',  'Gutierrez', '2233078995'),
('44655947', 'Marcos',    'Monje',     '2233123456'),
('45432578', 'Matias',    'Escarso',   '2235100011'),
('46277456', 'Franco',    'Calli',     '2235100011'),
('40234890', 'Leonel',    'Flores',    '2235100011'),
('35222001', 'Laura',     'Gimenez',   '2235100101'),
('35222002', 'Pablo',     'Navarro',   '2235100102'),
('35333001', 'Mariana',   'Suarez',    '2235100201'),
('35333002', 'Federico',  'Ramos',     '2235100202'),
('35333003', 'Daniela',   'Aguirre',   '2235100203'),
('35333004', 'Sebastian', 'Vega',      '2235100204'),
('35333005', 'Paula',     'Cabrera',   '2235100205'),
('35333006', 'Diego',     'Mendoza',   '2235100206');


INSERT INTO persona(dni, nombre, apellido, telefono) VALUES
('42629947', 'Claudia',   'Ferreyra',  '2233030495'),
('42649956', 'Juan',      'Perez',     '2232030495'),
('42629247', 'Pedro',     'Gomez',     '2233530495'),
('42229947', 'Nicolas',   'Fernanez',  '2231030495'),
('42629957', 'Maria',     'Lopez',     '2233536495'),
('38111011', 'Franco',    'Ruiz',      '2235100011'),
('46876345', 'Camila',    'Gonzalez',  '2235100011'),
('48345123', 'Claudio',   'Rodriguez', '2235100011'),
('36345789', 'Nicolas',   'Paz',       '2235100011'),
('39111001', 'Valentina', 'Sosa',      '2235100301'),
('39111002', 'Mateo',     'Romero',    '2235100302'),
('39111003', 'Sofia',     'Diaz',      '2235100303'),
('39111004', 'Tomas',     'Acosta',    '2235100304'),
('39111005', 'Julieta',   'Torres',    '2235100305'),
('39111006', 'Agustina',  'Castro',    '2235100306');


INSERT INTO usuario(email, pass, dni_persona) VALUES
('canmonje1@gmail.com',          'cande123', '43629947'),  
('admin@gmail.com',              'admin',    '42664467'),  
('victoria.gutierrez@gmail.com', 'rec12345', '41629947'),  
('marcos.monje@gmail.com',       'vet12345', '44655947'),  
('matias.escarso@gmail.com',     'vet12345', '45432578'),  
('franco.calli@gmail.com',       'rec12345', '46277456'),  
('leonel.flores@gmail.com',      'vet12345', '40234890'),  
('laura.gimenez@gmail.com',      'rec12345', '35222001'),  
('pablo.navarro@gmail.com',      'rec12345', '35222002'), 
('mariana.suarez@gmail.com',     'vet12345', '35333001'),  
('federico.ramos@gmail.com',     'vet12345', '35333002'),  
('daniela.aguirre@gmail.com',    'vet12345', '35333003'), 
('sebastian.vega@gmail.com',     'vet12345', '35333004'),  
('paula.cabrera@gmail.com',      'vet12345', '35333005'),  
('diego.mendoza@gmail.com',      'vet12345', '35333006');  


INSERT INTO usuario(email, pass, dni_persona) VALUES
('claudia.ferreyra@gmail.com',   'cli12345', '42629947'),  
('juan.perez@gmail.com',         'cli12345', '42649956'),  
('pedro.gomez@gmail.com',        'cli12345', '42629247'),  
('nicolas.fernanez@gmail.com',   'cli12345', '42229947'),  
('maria.lopez@gmail.com',        'cli12345', '42629957'),  
('franco.ruiz@gmail.com',        'cli12345', '38111011'),  
('camila.gonzalez@gmail.com',    'cli12345', '46876345'),  
('claudio.rodriguez@gmail.com',  'cli12345', '48345123'),  
('nicolas.paz@gmail.com',        'cli12345', '36345789'),  
('valentina.sosa@gmail.com',     'cli12345', '39111001'),  
('mateo.romero@gmail.com',       'cli12345', '39111002'), 
('sofia.diaz@gmail.com',         'cli12345', '39111003'),  
('tomas.acosta@gmail.com',       'cli12345', '39111004'),  
('julieta.torres@gmail.com',     'cli12345', '39111005'), 
('agustina.castro@gmail.com',    'cli12345', '39111006');  



INSERT INTO personal(sueldo, horario_laboral_id, id_usuario, id_rol) VALUES
(20000.0, 1,  1, 2),  
(80000.0, 1,  2, 1),  
(15000.0, 1,  3, 3), 
(20000.0, 2,  4, 2), 
(20000.0, 3,  5, 2),  
(15000.0, 2,  6, 3),  
(20000.0, 1,  7, 2),  
(15000.0, 3,  8, 3), 
(15000.0, 1,  9, 3),  
(20000.0, 2, 10, 2),  
(20000.0, 3, 11, 2),  
(20000.0, 1, 12, 2),  
(20000.0, 2, 13, 2), 
(20000.0, 3, 14, 2),  
(20000.0, 1, 15, 2);  


INSERT INTO cliente(direccion, id_usuario) VALUES
('Av. Colon 1234',        16),  
('Calle Rivadavia 2450',  17), 
('Av. Independencia 980', 18), 
('Calle San Martin 3100', 19), 
('Calle Belgrano 1575',   20), 
('Av. Luro 4020',         21), 
('Calle Cordoba 2210',    22), 
('Calle Mitre 875',       23), 
('Av. Juan B. Justo 560', 24),  
('Calle Alberti 1890',    25),  
('Calle Gascon 2735',     26), 
('Calle Olavarria 1420',  27), 
('Av. Constitucion 3650', 28),  
('Calle Dorrego 1110',    29),  
('Calle Brown 2980',      30);  


select u.email, u.pass, r.descripcion 
from personal pl
inner join usuario u ON u.id = pl.id_usuario
inner join rol r ON r.id = pl.id_rol;

select u.email, u.pass
from cliente c
inner join usuario u ON u.id = c.id_usuario;

select * from persona;
select * from usuario;
select * from personal;
select * from cliente;

