create database ventaRepuestos

use ventaRepuestos

create table persona
(
cedula int not null,
nombreC varchar(50) not null,
direccionE varchar(50) not null,
ciudad varchar(15) not null,
idC int,
cedulaJO int,
CONSTRAINT PKPER PRIMARY KEY(cedula),
CONSTRAINT FKUNI FOREIGN KEY(idC) REFERENCES cliente(id)
ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT FKPERT FOREIGN KEY(cedulaJO) REFERENCES organizacion(cedulaJ)
ON DELETE SET NULL
--ON UPDATE CASCADE,se genera redundancia en teoría ¿Cómo corregirlo?
)
SELECT * FROM persona
SELECT * FROM parte
create table telefonosPersonas
(
idTelefonos int identity not null,
numero int not null,
cedulaP int,
CONSTRAINT PKTEL PRIMARY KEY(idTelefonos),
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
nombreCC varchar(50),
cargoC varchar(15),
telefonoC int,
idC int,
CONSTRAINT PKORG PRIMARY KEY(cedulaJ),
CONSTRAINT FKUNION FOREIGN KEY(idC) REFERENCES cliente(id)
ON DELETE SET NULL
ON UPDATE CASCADE,
)

create table contactoO
(
nombreC varchar(50) not null,
telefonos int not null,
cargo varchar(30) not null,
CONSTRAINT PKCONT PRIMARY KEY(nombreC),
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
drop database ventaRepuestos

create table detalle
(
precio int not null,
nombreP varchar(30) not null,
cantidadV int,
codigo int identity not null,
CONSTRAINT PKDET PRIMARY KEY(codigo),
)
create table orden
(
numeroC int identity not null,
fecha dateTime not null,
montoVenta int not null,
montoIVA int,
montoTotal int not null,
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
numeroCO int,
nombreF varchar(30),
codigoA int,
CONSTRAINT PKPAR PRIMARY KEY(nombreE),

CONSTRAINT FKPART FOREIGN KEY(numeroCO) REFERENCES orden(numeroC)
ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT FKCRE FOREIGN KEY(nombreF) REFERENCES fabricante(nombre)
ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT FKEST FOREIGN KEY(codigoA) REFERENCES automovil(codigo)
ON DELETE SET NULL
--ON UPDATE CASCADE,

)
select * from partes
create table fabricante
(
nombre varchar(30) not null,
CONSTRAINT PKFAB PRIMARY KEY(nombre),
)
drop table fabricante
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
CONSTRAINT FKELA FOREIGN KEY(nombreF) REFERENCES fabricante(nombre)
ON DELETE SET NULL
ON UPDATE CASCADE,
)
create table venta
(
codigo int  identity not null,
precioP int,
porcentajeG int,
precioC int not null,
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
drop table venta

create table empresaProveedora
(
nombre varchar(30) not null,
direccion varchar(50) not null,
ciudad varchar(15) not null,
nombreCont varchar(50) not null,
telefonos int,
--Para hacer la referencia de relacion como hago si es la union de dos llaves
CONSTRAINT PKEMP PRIMARY KEY(nombre,direccion),
)

