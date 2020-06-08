--NO SE COMO SE IMPLEMENTAN LAS LLAVES ALTERNAS
--LAS LLAVES ALTERNAS NO DEBEN SER NULAS NI REPETIRSE



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
DBCC CHECKIDENT (orden, RESEED,0)

--Actualiza datos de las tablas
UPDATE persona 
    SET nombreC= 'Damaris'
	WHERE cedula=504330070;

--Borra la base de datos
drop database ventaRepuestos
--Borra tablas
drop table fabricante

create database ventaRepuestos
use ventaRepuestos

create table detalle  --TABLA 1
(
codigo int identity not null,
precio decimal(10,2) not null,
nombreP varchar(30) not null,
cantidadV int,
CONSTRAINT PKDET PRIMARY KEY(codigo),
)
  
create table orden
(
numeroC int identity not null,
fecha date not null,
montoVenta decimal(10,2),
montoIVA decimal (10,2),
montoTotal as montoVenta+montoIVA,--montoVenta+ ((montoIVA* montoVenta) /100), 
nombreC varchar(50) not null,

CONSTRAINT PKORD PRIMARY KEY(numeroC),
)
create table cliente  -- TABLA 3
(
id int identity,
estado varchar(30),
CONSTRAINT PKCLI PRIMARY KEY(id),
)
create table realizaOrden
(
idC int not null,
numeroCO int not null,
CONSTRAINT PKREAOR PRIMARY KEY(idC,numeroCO),
CONSTRAINT FKCLIEN FOREIGN KEY(idC) REFERENCES cliente(id)
ON UPDATE CASCADE,
CONSTRAINT FKORDE FOREIGN KEY(numeroCO) REFERENCES orden(numeroC)

)
create table organizacion  --TABLA 4
(
cedulaJ decimal(10) not null,
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
UNIQUE (nombre),
CONSTRAINT FKUNION FOREIGN KEY(idC) REFERENCES cliente(id)
ON DELETE SET NULL

)
create table persona  --TABLA 5
(
cedula decimal(9) not null,
nombreC varchar(50) not null,
direccionE varchar(50) not null,
ciudad varchar(15) not null,
idC int,
cedulaJO decimal(10),
CONSTRAINT PKPER PRIMARY KEY(cedula),
UNIQUE (idC),
UNIQUE (nombreC),
CONSTRAINT FKUNI FOREIGN KEY(idC) REFERENCES cliente(id)

ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT FKPERT FOREIGN KEY(cedulaJO) REFERENCES organizacion(cedulaJ)
ON DELETE SET NULL

)

create table telefonosPersona  --TABLA 6
(
numero int not null,
cedulaP decimal(9) not null,

CONSTRAINT PKTEL PRIMARY KEY(numero),
CONSTRAINT FKPER FOREIGN KEY(cedulaP) REFERENCES persona(cedula)

ON UPDATE CASCADE,
)-- aQUI BORRE ALGO

create table fabricantePartes   --TABLA 7
(
nombre varchar(30) not null,
CONSTRAINT PKFABP PRIMARY KEY(nombre),
)


create table fabricanteAutomovil -- TABLA 8
(
nombre varchar(30) not null,
CONSTRAINT PKFABA PRIMARY KEY(nombre),
)

create table automovil  --TABLA 9
(
codigo int not null,
modelo varchar(40) not null,
añoF int not null,
detalleA varchar(100) not null,
nombreF varchar(30),


CONSTRAINT PKAUT PRIMARY KEY(codigo),
CONSTRAINT FKELA FOREIGN KEY(nombreF) REFERENCES fabricanteAutomovil(nombre)
ON DELETE SET NULL
ON UPDATE CASCADE,
)

create table parte --TABLA 10
(
nombreE varchar(40) not null,
marca varchar(30) not null,
--automovilC varchar(50) not null,

nombreF varchar(30) not null,
codigoA int,


CONSTRAINT PKPAR PRIMARY KEY(nombreE,marca,nombreF),
CONSTRAINT FKCRE FOREIGN KEY(nombreF) REFERENCES fabricantePartes(nombre)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT FKEST FOREIGN KEY(codigoA) REFERENCES automovil(codigo)
ON DELETE  CASCADE,
--ON UPDATE CASCADE,

)
create table relacionRegistroPartes
(
numeroCO int not null,
nombreEP varchar(40) not null,
codigoD int not null,
marcaP varchar(30),
nombreFP varchar(30),
CONSTRAINT PKRELRP PRIMARY KEY(numeroCO,nombreEP,codigoD),
CONSTRAINT FKTIE FOREIGN KEY(codigoD) REFERENCES detalle(codigo),
CONSTRAINT FKORD FOREIGN KEY(numeroCO) REFERENCES orden(numeroC)
ON UPDATE CASCADE,
CONSTRAINT FKNOMEP FOREIGN KEY(nombreEP,marcaP,nombreFP) REFERENCES parte(nombreE,marca,nombreF)
ON UPDATE CASCADE
)
create table empresaProveedora   --TABLA 12
(
nombre varchar(30) not null,
direccion varchar(50) not null,
ciudad varchar(15) not null,
nombreCont varchar(50) not null,
telefono int,
CONSTRAINT PKEMP PRIMARY KEY(nombre)
)

create table telefonosEmpresa  --TABLA 13
(
numero int not null,

direccionE varchar(50),
nombreE varchar(30),

CONSTRAINT PKTELE PRIMARY KEY(numero),
CONSTRAINT FKTELE FOREIGN KEY(nombreE)REFERENCES empresaProveedora(nombre)
ON DELETE SET NULL
ON UPDATE CASCADE,

)
create table venta    --TABLA 14
(
codigo int  identity not null,
precioP decimal(10,2),
precioC decimal(10,2) not null,
porcentajeG as ((precioP-precioC)/precioC)*100,

nombreE varchar(30),
--direccionE varchar(50) ,
nombreEP varchar(40),
marcaParte varchar(30),
nomFab varchar(30),

CONSTRAINT PKVEN PRIMARY KEY(codigo),
CONSTRAINT FKVENO FOREIGN KEY(nombreE) REFERENCES empresaProveedora(nombre)
ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT FKFP FOREIGN KEY(nombreEP,marcaParte,nomFab) REFERENCES parte(nombreE,marca,nombreF)
ON UPDATE CASCADE,
)
create table vehiculosPartes   --TABLA 15
(
codigoVehiculo int  not null,
nombreParte varchar(40),
marcaParte varchar(30),
fabricanteParte varchar(30)

CONSTRAINT PKVP PRIMARY KEY (codigoVehiculo,nombreParte,marcaParte,fabricanteParte),
CONSTRAINT FKCodeV FOREIGN KEY(codigoVehiculo) REFERENCES automovil(codigo)

ON UPDATE CASCADE,
CONSTRAINT FKnomParte FOREIGN KEY(nombreParte,marcaParte,fabricanteParte) REFERENCES parte(nombreE,marca,nombreF)
ON DELETE CASCADE 
ON UPDATE CASCADE,


)
--AGREGAR RESTRICCIONES


--PRUEBAS IGNORE EL LUGAR DONDE LAS HAGOO

insert into fabricanteAutomovil(nombre) values('Toyota');
insert into automovil(codigo,modelo,añoF,detalleA,nombreF) values(1,'2019',2020,'sin luces','Toyota');
insert into fabricantePartes(nombre) values('simple');
insert into fabricantePartes(nombre) values('compuesto');
insert into parte(nombreE, marca,nombreF) values('aro','yiyo','compuesto');
insert into empresaProveedora(nombre,direccion,ciudad,nombreCont) values('motociclo','100m oeste esc san pedro','Heredia','Juanito Perez Mesa');
insert into parte(nombreE, marca,nombreF) values('llanta','yiyo','simple');
insert into orden(fecha,nombreC,montoIVA) values ('2020/05/12','Liseth Gonzalez',13);

insert into venta(precioP,precioC,nombreE,direccionE,nombreEP) values(2000.00,3000.00,'Motociclo','500m oeste Banco Nacional','llanta');
insert into detalle(precio,nombreP,cantidadV) values(1000.00,'motociclo',2);
SELECT * FROM fabricantePartes
delete from orden
SELECT * FROM orden
SELECT * FROM orden where nombreC= 'Juanita Perez' 
SELECT * FROM empresaProveedora
SELECT estado
	FROM persona,cliente
	WHERE persona.idC=cliente.id AND persona.nombreC='liseth gonzalez'
	SELECT cliente.estado,persona.idC FROM persona,cliente WHERE persona.idC=cliente.id AND persona.nombreC='liseth gonzalez';
	   
	UPDATE cliente SET estado='SUSPENDIDO' FROM persona,cliente WHERE persona.idC=cliente.id AND persona.cedula=504330070;
              
SELECT * FROM orden
SELECT * FROM venta WHERE nombreE ='motociclo' AND nombreEP='llanta';
DELETE FROM realizaOrden
DELETE FROM relacionRegistroPartes
DELETE FROM detalle
DELETE FROM parte where nombreE = 'Puerta' AND marca = 'Marca' AND nombreF = 'AJS Auto Parts';
DELETE FROM orden
SELECT nombreParte,marcaParte,fabricanteParte FROM (automovil INNER JOIN vehiculosPartes ON  codigo = codigoVehiculo AND codigo = 1);

