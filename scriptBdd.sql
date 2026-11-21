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
  descripcion VARCHAR(100) NOT NULL,
  horario_laboral_id INT UNSIGNED, 
  id_usuario INT UNSIGNED,
  activo BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id),
  FOREIGN KEY (horario_laboral_id) REFERENCES horario_laboral(id)
);

DROP TABLE IF EXISTS personal;
CREATE TABLE personal (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  sueldo DOUBLE UNSIGNED NOT NULL,
  horario_laboral_id INT UNSIGNED, 
  id_usuario INT UNSIGNED,
  activo BOOLEAN DEFAULT TRUE,
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




INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42629947', 'Claudia', 'Ferreyra', 2233030495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42649956', 'Juan', 'Perez', 2232030495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42629247', 'Pedro', 'Gomez', 2233530495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42229947', 'Nicolas', 'Fernanez', 2231030495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42629957', 'Maria', 'Lopez', 2233536495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('43629947', 'Lucia', 'Gallo', 2233456495);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('41629947', 'Victoria', 'Gutierrez', 2233078995);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('44655947', 'Marcos', 'Monje', 2233123456);
INSERT INTO persona(dni, nombre, apellido, telefono) VALUES('42664467', 'Geronimo', 'Zampini', 2233030484);


select * from persona;
select * from usuario;
select * from cliente;






