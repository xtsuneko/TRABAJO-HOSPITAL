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

CREATE TABLE IF NOT EXISTS products (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    name                VARCHAR(100) NOT NULL,
    price               DECIMAL(10,2) NOT NULL,
    stock               INT NOT NULL DEFAULT 0,
    active              BOOLEAN NOT NULL DEFAULT TRUE,
    type                VARCHAR(20) NOT NULL,   -- 'PET_FOOD' o 'ACCESSORY'
    -- columnas de PetFood
    pet_type            VARCHAR(20) NULL,
    weight_kg           DOUBLE NULL,
    discount_percentage DOUBLE NULL,
    -- columnas de Accessory
    material            VARCHAR(50) NULL,
    size                VARCHAR(20) NULL
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
  -- LISTA MASCOSTAS??
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


INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42629947', 'Claudia', 'Ferreyra', 2233030495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42649956', 'Juan', 'Perez', 2232030495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42629247', 'Pedro', 'Gomez', 2233530495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42229947', 'Nicolas', 'Fernanez', 2231030495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42629957', 'Maria', 'Lopez', 2233536495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('43629947', 'Lucia', 'Gallo', 2233456495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('41629947', 'Victoria', 'Gutierrez', 2233078995);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('44655947', 'Marcos', 'Monje', 2233123456);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42664467', 'Geronimo', 'Zampini', 2233030484);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('38111011', 'Franco', 'Ruiz', '2235100011'),
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('46876345', 'Camila', 'Gonzalez', '2235100011'),
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('45432578', 'Matias', 'Escarso', '2235100011'),
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('48345123', 'Claudio', 'Rodriguez', '2235100011'),
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('46277456', 'Franco', 'Calli', '2235100011'),
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('36345789', 'Nicolas', 'Paz', '2235100011'),
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('40234890', 'Leonel', 'Flores', '2235100011');

NSERT INTO usuario(email, pass, dni_persona) VALUES
('claudia.ferreyra@gmail.com', 'cli12345', '42629947'),
('juan.perez@gmail.com', 'cli12345', '42649956'),
('pedro.gomez@gmail.com', 'cli12345', '42629247'),
('nicolas.fernanez@gmail.com', 'cli12345', '42229947'),
('maria.lopez@gmail.com', 'cli12345', '42629957'),
('franco.ruiz@gmail.com', 'cli12345', '38111011'),
('camila.gonzalez@gmail.com', 'cli12345', '46876345'),
('claudio.rodriguez@gmail.com', 'cli12345', '48345123'),
('nicolas.paz@gmail.com', 'cli12345', '36345789'),
('valentina.sosa@gmail.com', 'cli12345', '39111001'),
('mateo.romero@gmail.com', 'cli12345', '39111002'),
('sofia.diaz@gmail.com', 'cli12345', '39111003'),
('tomas.acosta@gmail.com', 'cli12345', '39111004'),
('julieta.torres@gmail.com', 'cli12345', '39111005'),
('agustina.castro@gmail.com', 'cli12345', '39111006'),
('lucia.gallo@gmail.com', 'vet12345', '43629947'),
('victoria.gutierrez@gmail.com', 'rec12345', '41629947'),
('marcos.monje@gmail.com', 'vet12345', '44655947'),
('geronimo.zampini@gmail.com', 'adm12345', '42664467'),
('matias.escarso@gmail.com', 'vet12345', '45432578'),
('franco.calli@gmail.com', 'rec12345', '46277456'),
('leonel.flores@gmail.com', 'vet12345', '40234890'),
('laura.gimenez@gmail.com', 'rec12345', '35222001'),
('pablo.navarro@gmail.com', 'rec12345', '35222002'),
('mariana.suarez@gmail.com', 'vet12345', '35333001'),
('federico.ramos@gmail.com', 'vet12345', '35333002'),
('daniela.aguirre@gmail.com', 'vet12345', '35333003'),
('sebastian.vega@gmail.com', 'vet12345', '35333004'),
('paula.cabrera@gmail.com', 'vet12345', '35333005'),
('diego.mendoza@gmail.com', 'vet12345', '35333006');






INSERT INTO products (name, price, stock, type, pet_type, weight_kg, discount_percentage, material, size) VALUES
('Alimento Balanceado Adulto Perro 15kg',   48000, 20, 'PET_FOOD', 'PERRO',  15,  0,  NULL, NULL),
('Alimento Cachorro Razas Pequeñas 3kg',    18500, 25, 'PET_FOOD', 'PERRO',  3,   10, NULL, NULL),
('Alimento Gato Adulto Pescado 7.5kg',      32000, 18, 'PET_FOOD', 'GATO',   7.5, 0,  NULL, NULL),
('Alimento Gatito Pollo 1.5kg',             11500, 30, 'PET_FOOD', 'GATO',   1.5, 5,  NULL, NULL),
('Mezcla de Semillas para Canarios 1kg',     4200, 40, 'PET_FOOD', 'AVE',    1,   0,  NULL, NULL),
('Alimento Hámster y Cobayo 800g',           3800, 35, 'PET_FOOD', 'ROEDOR', 0.8, 0,  NULL, NULL),
('Pellets para Tortugas 500g',               5600, 22, 'PET_FOOD', 'REPTIL', 0.5, 15, NULL, NULL),
('Snack Dental Perro 500g',                  7900, 50, 'PET_FOOD', 'PERRO',  0.5, 0,  NULL, NULL),

('Correa Retráctil 5m',                     12500, 15, 'ACCESSORY', NULL, NULL, NULL, 'Nylon',            'M'),
('Collar Ajustable Reflectivo',              6800, 40, 'ACCESSORY', NULL, NULL, NULL, 'Nylon',            'L'),
('Cama Ortopédica',                         38000,  8, 'ACCESSORY', NULL, NULL, NULL, 'Espuma',           'XL'),
('Plato Doble Acero Inoxidable',             9500, 25, 'ACCESSORY', NULL, NULL, NULL, 'Acero inoxidable', 'M'),
('Rascador para Gatos 60cm',                21000, 12, 'ACCESSORY', NULL, NULL, NULL, 'Sisal',            'M'),
('Transportadora Rígida',                   27500, 10, 'ACCESSORY', NULL, NULL, NULL, 'Plástico',         'L'),
('Pelota de Goma con Sonido',                3200, 60, 'ACCESSORY', NULL, NULL, NULL, 'Goma',             'S'),
('Jaula para Aves con Comedero',            45000,  6, 'ACCESSORY', NULL, NULL, NULL, 'Metal',            'L');

select * from persona;
select * from usuario;
select * from cliente;






