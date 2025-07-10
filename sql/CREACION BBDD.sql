--Creación de la base de datos
CREATE DATABASE TALLER

--Uso de la base de datos
USE TALLER

--Creación de tablas independientes
CREATE TABLE CLIENTE(
Nro_Cliente varchar(11) not null,
Tipo_Documento bit not null,
Nom_Cliente varchar(50) not null,
Telf_Cliente char(9) not null,
Direc_Cliente varchar(50), 
Distri_Cliente varchar(50),
CONSTRAINT PK_CLIENTE	
	PRIMARY KEY(Nro_Cliente,Tipo_Documento)
)
GO

CREATE TABLE MECANICO(
Cod_Mecanico char(8) not null,
Nom_Mecanico varchar(50) not null,
Telef_Mecanico char(9) not null,
Direc_Mecanico varchar(50),
CONSTRAINT PK_MECANICO 
	PRIMARY KEY(Cod_Mecanico)
)
GO

CREATE TABLE CATALOGO_SERVICIOS(
Cod_Servicio char(2) not null,
Nom_Servicio varchar(50) not null,
Precio_Servicio decimal(5,2) not null,
CONSTRAINT PK_CATALOGO_SERVICIOS
	PRIMARY KEY(Cod_Servicio)
)
GO

CREATE TABLE ACCESORIOS(
Cod_Accesorio char(2) not null,
Nom_Accesorio varchar(30) not null,
CONSTRAINT PK_ACCESORIOS
	PRIMARY KEY(Cod_Accesorio)
)
GO

CREATE TABLE VEHICULOS(
Placa_Vehiculo char(7) not null,
Marca_Vehiculo varchar(20) not null,
Modelo_Vehiculo varchar(40) not null,
Mod_Chasis varchar(17) not null,
Num_Motor varchar(25) not null,
Año_Vehiculo smallint not null,
Color_Vehiculo varchar(20)
CONSTRAINT PK_VEHICULOS
	PRIMARY KEY(Placa_Vehiculo) 
)
GO

--Creacion de la tabla principal ORDEN
CREATE TABLE ORDEN(
Cod_Orden char(6) not null,
Fecha_Orden date not null,
kilometraje decimal(6,2) not null,
Fecha_Entrega date not null,
Num_Combustible decimal(5,2) not null,
Obser_General varchar(50) not null,
T_Prob bit not null,
Manual_Prob bit not null,
Llave bit not null,
Estado_Orden tinyint not null,
Quiñado bit not null,
Rayado bit not null, 
Abollado bit not null,
Nro_Cliente varchar(11),
Tipo_Documento bit,
Cod_Mecanico char(8),
Placa_Vehiculo char(7),
CONSTRAINT PK_ORDEN
	PRIMARY KEY(Cod_Orden),
CONSTRAINT FK_CLIENTE_ORDEN	
	FOREIGN KEY(Nro_Cliente,Tipo_Documento)
		REFERENCES CLIENTE(Nro_Cliente,Tipo_Documento),
CONSTRAINT FK_MECANICO_ORDEN
	FOREIGN KEY(Cod_Mecanico)
		REFERENCES MECANICO(Cod_Mecanico),
CONSTRAINT FK_VEHICULOS_ORDEN
	FOREIGN KEY(Placa_Vehiculo)
		REFERENCES VEHICULOS(Placa_Vehiculo)
)
GO

--Creacion de tablas intermedias para relaciones muchos a muchos 
CREATE TABLE SERVICIOS_ORDEN(
Cod_Servicio char(2) not null,
Cod_Orden char(6) not null,
Obser_Especifico varchar(50),
CONSTRAINT PK_SERVICIOS_ORDEN 
	PRIMARY KEY (Cod_Servicio,Cod_Orden),
CONSTRAINT FK_CATALOGO_SERVICIOS_SERVICIOS_ORDEN
	FOREIGN KEY(Cod_Servicio)
		REFERENCES CATALOGO_SERVICIOS(Cod_Servicio),
CONSTRAINT FK_ORDEN_SERVICIOS_ORDEN
	FOREIGN KEY(Cod_Orden)
		REFERENCES ORDEN(Cod_Orden)
)
GO

CREATE TABLE ORDEN_ACCESORIOS(
Cod_Orden char(6) not null,
Cod_Accesorio char(2) not null,
Cant_Accesorio tinyint not null,
Obser_Accesorio varchar(50),
Estado_Accesorio bit not null,
CONSTRAINT PK_ORDEN_ACCESORIOS	
	PRIMARY KEY(Cod_Orden,Cod_Accesorio),
CONSTRAINT FK_ORDEN_ORDEN_ACCESORIOS
	FOREIGN KEY(Cod_Orden)
		REFERENCES ORDEN(Cod_Orden),
CONSTRAINT FK_ACCESORIOS_ORDEN_ACCESORIOS
	FOREIGN KEY(Cod_Accesorio)
		REFERENCES ACCESORIOS(Cod_Accesorio)
)
GO

CREATE TABLE Usuarios(
    idUser char(8) not null,
    Contraseña varchar(50) not null,
    Nombre varchar(50) not null,
    CONSTRAINT PK_Usuario PRIMARY KEY (idUser) 
)
GO