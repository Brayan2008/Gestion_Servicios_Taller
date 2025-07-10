--Creación de la base de datos
CREATE DATABASE TALLER3
GO

--Uso de la base de datos
USE TALLER3
GO

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

-------------------- DROPS ------------------------
DELETE FROM ACCESORIOS
GO

DELETE FROM CLIENTE
GO

DELETE FROM VEHICULOS
GO

DELETE FROM CATALOGO_SERVICIOS
GO

DELETE FROM MECANICO
GO

DELETE FROM CLIENTE
GO

DELETE FROM ORDEN
GO

---------------- INSERTS ------------------------
INSERT INTO ACCESORIOS VALUES ('A1', 'Aire acondicionado');
INSERT INTO ACCESORIOS VALUES ('A2', 'Alfombrillas');
INSERT INTO ACCESORIOS VALUES ('A3', 'Asiento calefactable');
INSERT INTO ACCESORIOS VALUES ('A4', 'Antena');
INSERT INTO ACCESORIOS VALUES ('A5', 'Alerón');
INSERT INTO ACCESORIOS VALUES ('A6', 'Amortiguadores');
INSERT INTO ACCESORIOS VALUES ('A7', 'Alarma antirrobo');
INSERT INTO ACCESORIOS VALUES ('A8', 'Adaptador USB');
INSERT INTO ACCESORIOS VALUES ('A9', 'Arranque remoto');

INSERT INTO CATALOGO_SERVICIOS VALUES ('T1', 'Cambio de aceite sintético', 135.00);
INSERT INTO CATALOGO_SERVICIOS VALUES ('T2', 'Lavado premium', 55.00);
INSERT INTO CATALOGO_SERVICIOS VALUES ('T3', 'Pulido de pintura', 160.00);
INSERT INTO CATALOGO_SERVICIOS VALUES ('T4', 'Revisión técnica', 65.00);
INSERT INTO CATALOGO_SERVICIOS VALUES ('T5', 'Cambio de amortiguadores', 480.00);
INSERT INTO CATALOGO_SERVICIOS VALUES ('T6', 'Limpieza de inyectores', 110.00);
INSERT INTO CATALOGO_SERVICIOS VALUES ('T7', 'Revisión de gases', 50.00);
INSERT INTO CATALOGO_SERVICIOS VALUES ('T8', 'Mantenimiento de frenos ABS', 150.00);
INSERT INTO CATALOGO_SERVICIOS VALUES ('T9', 'Pintura de techo', 300.00);

INSERT INTO Mecanico (Cod_Mecanico, Nom_Mecanico, Telef_Mecanico, Direc_Mecanico) VALUES ('12345678', 'Luis Ramírez', '987654321', 'Av. Perú 123');
INSERT INTO Mecanico (Cod_Mecanico, Nom_Mecanico, Telef_Mecanico, Direc_Mecanico) VALUES ('87654321', 'Carlos Gómez', '912345678', 'Jr. Ayacucho 456');
INSERT INTO Mecanico (Cod_Mecanico, Nom_Mecanico, Telef_Mecanico, Direc_Mecanico) VALUES ('11223344', 'José Torres', '923456781', 'Av. Brasil 789');
INSERT INTO Mecanico (Cod_Mecanico, Nom_Mecanico, Telef_Mecanico, Direc_Mecanico) VALUES ('44332211', 'Miguel Díaz', '934567892', 'Calle Lima 101');
INSERT INTO Mecanico (Cod_Mecanico, Nom_Mecanico, Telef_Mecanico, Direc_Mecanico) VALUES ('55667788', 'Andrés Vera', '945678903', 'Av. Javier Prado 222');
INSERT INTO Mecanico (Cod_Mecanico, Nom_Mecanico, Telef_Mecanico, Direc_Mecanico) VALUES ('88776655', 'Pedro Castillo', '956789014', 'Jr. Amazonas 321');
INSERT INTO Mecanico (Cod_Mecanico, Nom_Mecanico, Telef_Mecanico, Direc_Mecanico) VALUES ('99887766', 'Raúl Ríos', '967890125', 'Calle Puno 110');
INSERT INTO Mecanico (Cod_Mecanico, Nom_Mecanico, Telef_Mecanico, Direc_Mecanico) VALUES ('66778899', 'César López', '978901236', 'Av. Grau 333');


INSERT INTO VEHICULOS (Placa_Vehiculo, Marca_Vehiculo, Modelo_Vehiculo, Mod_Chasis, Num_Motor, Año_Vehiculo, Color_Vehiculo)
VALUES
('ABC1234', 'Toyota', 'Corolla', 'Sedán', 'MTR123456789', 2020, 'Blanco'),
('XYZ5678', 'Ford', 'Ranger', 'Pickup', 'MTR987654321', 2022, 'Rojo'),
('DEF9012', 'Volkswagen', 'Golf', 'Hatchback', 'MTR456123789', 2019, 'Azul'),
('GHI3456', 'Chevrolet', 'Onix', 'Sedán', 'MTR789456123', 2021, 'Negro'),
('JKL7890', 'Hyundai', 'Tucson', 'SUV', 'MTR321654987', 2023, 'Gris'),
('MNO1234', 'Nissan', 'Sentra', 'B17N1E23456', 'MR20DE34567', 2022, 'Blanco perlado'),
('PQR5678', 'Kia', 'Sportage', 'SLXK2M78901', 'G4FJ890123', 2021, 'Plata'),
('STU9012', 'Mazda', 'CX-5', 'JM3KFBCL123', 'PYVPS12345', 2023, 'Rojo intenso'),
('VWX3456', 'Renault', 'Duster', 'HZBJA45678', 'H4M789012', 2020, 'Gris grafito'),
('YZA7890', 'Fiat', 'Cronos', 'ZFA25000012', 'E.torQ78901', 2022, 'Azul marine'),
('BCD2345', 'Jeep', 'Compass', 'ZFA25000034', 'Tigershark56', 2023, 'Negro azabache')
GO

INSERT INTO CLIENTE VALUES ('15678901', 1, 'Juan Pérez', '987654321', 'Av. Lima 123', 'Miraflores');
INSERT INTO CLIENTE VALUES ('23456789012', 0, 'María García', '976543218', 'Jr. Huancavelica 456', 'San Isidro');
INSERT INTO CLIENTE VALUES ('37890123', 1, 'Carlos López', '965432187', 'Calle Las Begonias 789', 'Surco');
INSERT INTO CLIENTE VALUES ('45678901234', 0, 'Ana Rodríguez', '954321876', 'Av. Arequipa 1011', 'Lince');
INSERT INTO CLIENTE VALUES ('57012345', 1, 'Luis Martínez', '943218765', 'Jr. de la Unión 1213', 'Cercado de Lima');
INSERT INTO CLIENTE VALUES ('67890123456', 0, 'Sofía Sánchez', '932187654', 'Av. Javier Prado 1415', 'San Borja');
INSERT INTO CLIENTE VALUES ('78234567', 1, 'Pedro Gómez', '921876543', 'Calle Los Pinos 1617', 'La Molina');
INSERT INTO CLIENTE VALUES ('89012345678', 0, 'Elena Díaz', '910876542', 'Av. Brasil 1819', 'Pueblo Libre');
INSERT INTO CLIENTE VALUES ('90456789', 1, 'Jorge Ruiz', '900765431', 'Jr. Ayacucho 2021', 'Breña');
INSERT INTO CLIENTE VALUES ('01234567890', 0, 'Lucía Torres', '989654320', 'Av. Tacna 2223', 'Rímac');

SELECT * from ORDEN

INSERT INTO ORDEN VALUES
 ('OR0001', '2025-05-01', 1234.56, '2025-05-05', 45.50, 'Sin observaciones', 1, 0, 1, 1, 0, 0, 1, '15678901', 1, '12345678', 'ABC1234'),
 ('OR0002', '2025-05-02', 987.34, '2025-05-07', 30.00, 'Cliente solicita revisión', 0, 1, 0, 0, 1, 0, 0, '23456789012', 1, '12345678', 'JKL7890'),
 ('OR0003', '2025-05-03', 4321.12, '2025-05-08', 60.75, 'Cambio de aceite', 1, 0, 1, 2, 0, 1, 0, '23456789012', 1, '12345678', 'MNO1234'),
 ('OR0004', '2025-05-04', 7890.00, '2025-05-09', 10.20, 'Frenos desgastados', 1, 1, 1, 0, 1, 1, 0, '90456789',0, '87654321', 'PQR5678'),
 ('OR0005', '2025-05-05', 2345.67, '2025-05-10', 99.99, 'Revisión completa', 0, 0, 0, 1, 0, 0, 0, '78234567',0, '87654321', 'VWX3456'),
 ('OR0006', '2025-05-06', 3456.78, '2025-05-11', 55.55, 'Alineación y balanceo', 1, 1, 1, 2, 0, 0, 0, '89012345678',1, '87654321', 'BCD2345'),
 ('OR0007', '2025-05-07', 876.45, '2025-05-12', 15.25, 'Cliente espera llamada', 0, 1, 0, 1, 1, 1, 1, '37890123',0, '87654321', 'BCD2345')
 GO