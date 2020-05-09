--NO SE COMO SE IMPLEMENTAN LAS LLAVES ALTERNAS
--LAS LLAVES ALTERNAS NO DEBEN SER NULAS NI REPETIRSE


create database ventaRepuestos
use ventaRepuestos

--Veo los clientes registrados de X tabla
SELECT * FROM persona
--Agarra la persona con valor mayor de cedula, sirve para cliente y para siempre obtener el ultimo cliente mediante ID
SELECT TOP (2) * FROM cliente ORDER BY id DESC;
--Insertar datos en x tabla
insert into persona(cedula,nombreC,direccionE,ciudad) values(504330650,'Maria liseth gonzalez Flores','150m oeste de xxxxxx xxxxx xxxxx ','Cartago');
insert into cliente(estado) values('ACTIVO');

--Borra todos los datos de la tabla persona, o sea las personas registradas
DELETE FROM telefonosPersona

--Resetea el identity de X tabla
DBCC CHECKIDENT (cliente, RESEED,0)

--Actualiza datos de las tablas
UPDATE persona 
    SET nombreC= 'Damaris'
	WHERE cedula=504330070;

--Borra la base de datos
drop database ventaRepuestos
--Borra tablas
drop table fabricante

create table persona
(
cedula int not null,
nombreC varchar(50) not null,
direccionE varchar(50) not null,
ciudad varchar(15) not null,
idC int,
cedulaJO int,
CONSTRAINT PKPER PRIMARY KEY(cedula),
UNIQUE (idC),
CONSTRAINT FKUNI FOREIGN KEY(idC) REFERENCES cliente(id)

ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT FKPERT FOREIGN KEY(cedulaJO) REFERENCES organizacion(cedulaJ)
ON DELETE SET NULL

)

create table telefonosPersona
(
numero int not null,
cedulaP int not null,

CONSTRAINT PKTEL PRIMARY KEY(numero,cedulaP),
CONSTRAINT FKPER FOREIGN KEY(cedulaP) REFERENCES persona(cedula)
ON DELETE SET NULL
ON UPDATE CASCADE,
)

create table organizacion
(
cedulaJ int not null,
nombre varchar(40) not null,
direccionE varchar(50) not null,
ciudad varchar(15) not  null,
--Datos de contacto
nombreCC varchar(50),
cargoC varchar(15),
telefonoC int,

idC int,
CONSTRAINT PKORG PRIMARY KEY(cedulaJ),
UNIQUE (idC),
CONSTRAINT FKUNION FOREIGN KEY(idC) REFERENCES cliente(id)
ON DELETE SET NULL

)


create table cliente
(
id int identity,
estado varchar(30),
numeroCO int,
CONSTRAINT PKCLI PRIMARY KEY(id),
CONSTRAINT FKREA FOREIGN KEY(numeroCO) REFERENCES orden(numeroC)
ON DELETE SET NULL
ON UPDATE CASCADE,
)


create table detalle
(
codigo int identity not null,
precio int not null,
nombreP varchar(30) not null,
cantidadV int,
CONSTRAINT PKDET PRIMARY KEY(codigo),
)
create table orden
(
numeroC int identity not null,
fecha dateTime not null,
montoVenta int not null,
montoIVA int,
montoTotal as montoVenta + montoIVA, --Así se expresa un derivado? 

nombreC varchar(50) not null,
codigoD int,
CONSTRAINT PKORD PRIMARY KEY(numeroC),
CONSTRAINT FKTIE FOREIGN KEY(codigoD) REFERENCES detalle(codigo)
ON DELETE SET NULL
ON UPDATE CASCADE,
)
create table parte
(
nombreE varchar(40) not null,
marca varchar(30) not null,
automovilC varchar(50) not null,

nombreF varchar(30),
codigoA int,
codigoD int,

CONSTRAINT PKPAR PRIMARY KEY(nombreE),
CONSTRAINT FKCRE FOREIGN KEY(nombreF) REFERENCES fabricantePartes(nombre)
ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT FKEST FOREIGN KEY(codigoA) REFERENCES automovil(codigo)
ON DELETE SET NULL,
--ON UPDATE CASCADE,
CONSTRAINT FKDET FOREIGN KEY(codigoD) REFERENCES detalle(codigo)
)
create table relacionRegistroPartes
(
numeroCO int not null,
nombreEP varchar(40) not null,
CONSTRAINT PKRELRP PRIMARY KEY(numeroCO,nombreEP),
CONSTRAINT FKORD FOREIGN KEY(numeroCO) REFERENCES orden(numeroC)
ON UPDATE CASCADE,
CONSTRAINT FKNOMEP FOREIGN KEY(nombreEP) REFERENCES parte(nombreE)

ON UPDATE CASCADE
)


create table fabricantePartes
(
nombre varchar(30) not null,
CONSTRAINT PKFABP PRIMARY KEY(nombre),
)


create table fabricanteAutomovil
(
nombre varchar(30) not null,
CONSTRAINT PKFABA PRIMARY KEY(nombre),
)

create table automovil
(
codigo int  not null,
modelo varchar(40) not null,
añoF int not null,
detalleA varchar(100) not null,
nombreF varchar(30),


CONSTRAINT PKAUT PRIMARY KEY(codigo),
--En este caso dice que el automovil debe tener nombre del fabricante
--¿Sirve el que uso para la relacion?

CONSTRAINT FKELA FOREIGN KEY(nombreF) REFERENCES fabricanteAutomovil(nombre)
ON DELETE SET NULL
ON UPDATE CASCADE,
)


create table venta
(
codigo int  identity not null,
precioP int,
precioC int not null,
porcentajeG as precioC-precioP,

nombreE varchar(30),
direccionE varchar(50),
nombreEP varchar(40),

CONSTRAINT PKVEN PRIMARY KEY(codigo),
CONSTRAINT FKVEND FOREIGN KEY(nombreE,direccionE)REFERENCES empresaProveedora(nombre,direccion)
ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT FKFP FOREIGN KEY(nombreEP) REFERENCES parte(nombreE)
ON DELETE SET NULL
ON UPDATE CASCADE,
)

create table empresaProveedora
(
nombre varchar(30) not null,
direccion varchar(50) not null,
ciudad varchar(15) not null,
nombreCont varchar(50) not null,

CONSTRAINT PKEMP PRIMARY KEY(nombre,direccion),
)

create table telefonoEmpresa
(
numero int not null,

direccionE varchar(50),
nombreE varchar(30),

CONSTRAINT PKTELE PRIMARY KEY(numero),
CONSTRAINT FKTELE FOREIGN KEY(nombreE,direccionE)REFERENCES empresaProveedora(nombre,direccion)
ON DELETE SET NULL
ON UPDATE CASCADE,

)