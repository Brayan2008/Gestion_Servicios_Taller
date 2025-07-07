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
    WHERE (Nro_Cliente+Nom_Cliente) LIKE ('%'+@Cadena +'%')
END
GO

