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

INSERT INTO usuario(email, pass, dni_persona) VALUES('canmonje1@gmail.com', 'cande123', '43629947');
INSERT INTO personal(sueldo, horario_laboral_id, id_usuario, id_rol) VALUES(20000.0, 1, 1, 2);

INSERT INTO usuario(email, pass, dni_persona) VALUES('admin@gmail.com', 'admin', '42664467');
INSERT INTO personal(sueldo, horario_laboral_id, id_usuario, id_rol) VALUES(80000.0, 1, 3, 1);

SELECT c.id, c.direccion, c.id_usuario, p.nombre, p.apellido, p.telefono, p.dni
FROM cliente c
inner join usuario u ON c.id_usuario = u.id
inner join persona p ON p.dni = u.dni_persona
WHERE c.id_usuario = ? AND c.activo = TRUE;

SELECT c.id, c.direccion, c.id_usuario, p.nombre, p.apellido, p.telefono, p.dni
FROM cliente c
inner join usuario u ON c.id_usuario = u.id
inner join persona p ON p.dni = u.dni_persona 
WHERE c.id = ? AND c.activo = TRUE;

SELECT c.id, c.direccion, c.id_usuario, p.nombre, p.apellido, p.telefono, p.dni
FROM cliente c
inner join usuario u ON c.id_usuario = u.id
inner join persona p ON p.dni = u.dni_persona 
WHERE c.activo = TRUE;

SELECT pl.id, pl.sueldo, pl.horario_laboral_id, pl.id_usuario, pl.id_rol, p.nombre, p.apellido, p.telefono, p.dni
FROM personal pl
inner join usuario u ON pl.id_usuario = u.id
inner join persona p ON p.dni = u.dni_persona 
WHERE pl.activo = TRUE;

SELECT pl.id, pl.sueldo, pl.horario_laboral_id, pl.id_usuario, pl.id_rol, p.nombre, p.apellido, p.telefono, p.dni
FROM personal pl
inner join usuario u ON pl.id_usuario = u.id
inner join persona p ON p.dni = u.dni_persona
WHERE pl.id_usuario = ? AND pl.activo = TRUE;

SELECT pl.id, pl.sueldo, pl.horario_laboral_id, pl.id_usuario, pl.id_rol, p.nombre, p.apellido, p.telefono, p.dni
FROM personal pl
inner join usuario u ON pl.id_usuario = u.id
inner join persona p ON p.dni = u.dni_persona
WHERE pl.id = ? AND pl.activo = TRUE;

SELECT t.id_cliente, t.id_personal, t.fecha, t.horario, t.motivo_consulta, 
c.direccion, c.id_usuario AS id_usuario_cliente, 
pl.sueldo, pl.horario_laboral_id, pl.id_rol, pl.id_usuario AS id_usuario_personal
FROM turno t
inner join cliente c ON t.id_cliente = c.id
inner join usuario uc ON uc.id = c.id_usuario
inner join persona pc ON pc.dni = uc.dni_persona
inner join personal pl ON t.id_personal = pl.id
inner join usuario upl ON upl.id = pl.id_usuario
inner join persona ppl ON ppl.dni = upl.dni_persona
WHERE t.activo = TRUE;

select * from persona;
select * from personal;
select * from usuario;
select * from cliente;
SELECT id, direccion, id_usuario FROM cliente WHERE activo = TRUE;
DELETE from usuario WHERE id=5; 
SELECT id, direccion, id_usuario FROM personal WHERE id_usuario = 1 AND activo = TRUE;
UPDATE personal set activo = TRUE WHERE id= 3;
UPDATE turno set activo = TRUE WHERE id>0;
Select * from rol;

SELECT id_turno, diagnostico, tratamiento 
FROM revision r
inner join turnos t  ON r.id_turno = t.id_turno 
WHERE id_personal = ? AND activo = TRUE;

SELECT dni, nombre, apellido, telefono 
FROM persona p
inner join usuario u ON p.dni = u.dni_persona
WHERE u.id = ? AND activo = TRUE;

select * from turno;

