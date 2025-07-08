USE TALLER
GO
-- Vistas
CREATE or ALTER view Vw_ClientesFiltrados
AS
    SELECT Nro_Cliente , Tipo_Documento, Nom_Cliente, Telf_Cliente, Direc_Cliente, Distri_Cliente
    FROM CLIENTE
GO

CREATE or ALTER VIEW Vw_ServiciosFiltrados
AS
    SELECT Cod_Servicio, Nom_Servicio, Precio_Servicio
    FROM CATALOGO_SERVICIOS
GO

-- PA
alter PROC PA_FiltrarServicio
    (@Cadena varchar(50))
AS
	BEGIN
    SELECT *
    FROM Vw_ServiciosFiltrados
    WHERE (Cod_Servicio+Nom_Servicio) LIKE ('%'+@Cadena +'%')
END
GO

CREATE or ALTER PROC PA_FiltrarCliente
    (@Cadena varchar(50))
AS
BEGIN
    SELECT *
    FROM Vw_ClientesFiltrados
    WHERE (Nro_Cliente+Nom_Cliente + Nom_Cliente + Telf_Cliente + Direc_Cliente + Distri_Cliente) LIKE ('%'+@Cadena +'%')
END
GO

CREATE OR ALTER VIEW Wv_MecanicosFiltrados
AS
    SELECT Cod_Mecanico, Nom_Mecanico,
        Telef_Mecanico, Direc_Mecanico
    FROM MECANICO
GO

CREATE OR ALTER PROC PA_FiltrarMecanico (@Cadena varchar(50))
AS
BEGIN
    SELECT *
    FROM Wv_MecanicosFiltrados
    WHERE (Cod_Mecanico + Nom_Mecanico + Telef_Mecanico + Direc_Mecanico) LIKE ('%'+@Cadena +'%')
END
GO

Create or alter VIEW Vw_AccesoriosFiltrados AS
	SELECT Cod_Accesorio, Nom_Accesorio FROM ACCESORIOS
GO

Create or alter PROC PA_FiltrarAccesorio (@Cadena varchar(50)) AS
	BEGIN 
		SELECT * FROM Vw_AccesoriosFiltrados 
		WHERE (Cod_Accesorio+Nom_Accesorio) LIKE ('%'+@Cadena +'%')
	END
GO

CREATE OR ALTER VIEW Vw_VehiculosFiltrados AS
	SELECT Placa_Vehiculo, Modelo_Vehiculo, Marca_Vehiculo, Mod_Chasis, Num_Motor, Año_Vehiculo, Color_Vehiculo FROM VEHICULOS
GO

CREATE OR ALTER PROC PA_FiltrarVehiculo (@Cadena varchar(50)) AS
	BEGIN 
		SELECT * FROM Vw_VehiculosFiltrados 
		WHERE (Placa_Vehiculo+Modelo_Vehiculo+Marca_Vehiculo + Mod_Chasis + Num_Motor + Color_Vehiculo) LIKE ('%'+@Cadena +'%')
	END
GO

CREATE OR ALTER VIEW Vw_OrdenFiltradas AS
	SELECT Cod_Orden, Estado_Orden, Nro_Cliente, Tipo_Documento, Cod_Mecanico, Placa_Vehiculo FROM ORDEN
GO

CREATE OR ALTER PROC PA_FiltrarOrden (@cadena varchar (50)) AS
	BEGIN
		SELECT* FROM Vw_OrdenFiltradas 
		WHERE (Cod_Orden+Estado_Orden+Nro_Cliente+Tipo_Documento+Cod_Mecanico+Placa_Vehiculo) LIKE ('%'+@cadena+'%')
	END
GO
