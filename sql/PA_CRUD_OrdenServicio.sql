USE TALLER3
go

--CREATE
CREATE OR ALTER PROCEDURE PA_Crear_OrdenServicio (
	@codServicio AS CHAR(2),
	@codOrden AS CHAR(6),
	@observaciones AS VARCHAR (50)
	)
AS 
BEGIN 
	IF NOT EXISTS (SELECT * FROM CATALOGO_SERVICIOS WHERE Cod_Servicio = @codServicio)
		BEGIN
			RAISERROR('El servicio no existe en el catálogo', 16, 1)
			RETURN @@ERROR
    END

	IF NOT EXISTS (SELECT * FROM ORDEN WHERE Cod_Orden = @codOrden)
        BEGIN
            RAISERROR('El número de orden no existe', 16, 1)
            RETURN @@ERROR
    END

	 IF EXISTS (SELECT * FROM SERVICIOS_ORDEN WHERE Cod_Servicio = @codServicio AND Cod_Orden = @codOrden)
        BEGIN
            RAISERROR('Este servicio ya está registrado en la orden', 16, 1)
            RETURN @@ERROR
     END

	 INSERT INTO SERVICIOS_ORDEN (Cod_Servicio, Cod_Orden, Obser_Especifico)
        VALUES (@codServicio, @codOrden, @observaciones)
END
GO

--OBTENER esto no es necesario copiarlo
CREATE OR ALTER PROCEDURE PA_Listar_OrdenServicio(
	@codOrden AS CHAR(6)
)
AS
BEGIN
	IF NOT EXISTS (SELECT * FROM ORDEN WHERE Cod_Orden = @codOrden)
		BEGIN
			RAISERROR('El número de orden no existe', 16, 1)
			RETURN @@ERROR
		END

	IF NOT EXISTS (SELECT * FROM SERVICIOS_ORDEN WHERE Cod_Orden = @codOrden)
		BEGIN
			RAISERROR('La orden no tiene servicios', 16, 1)
			RETURN @@ERROR
		END

	SELECT CS.Nom_Servicio AS 'Servicio', SO.Obser_Especifico AS 'Oservaciones',CS.Precio_Servicio AS 'Precio'
	FROM SERVICIOS_ORDEN AS SO, CATALOGO_SERVICIOS AS CS
	WHERE (SO.Cod_Orden = @codOrden) AND (SO.Cod_Servicio = CS.Cod_Servicio)
END
GO

--ACTUALIZAR 
CREATE OR ALTER PROCEDURE PA_Actualizar_OrdenServicio (
	@codOrden AS CHAR(6),
	@codServicio AS CHAR(2),
	@nuevoServicio AS CHAR(2),
	@nuevaObservacion AS VARCHAR(50)
	)
AS 
BEGIN 
	IF NOT EXISTS (SELECT * FROM CATALOGO_SERVICIOS WHERE Cod_Servicio = @nuevoServicio)
		BEGIN
			RAISERROR('El servicio no existe en el catálogo', 16, 1)
			RETURN @@ERROR
		END

	IF NOT EXISTS (SELECT * FROM ORDEN WHERE Cod_Orden = @codOrden)
        BEGIN
            RAISERROR('El número de orden no existe', 16, 1)
            RETURN @@ERROR
		END

	IF NOT EXISTS (SELECT * FROM SERVICIOS_ORDEN WHERE (Cod_Servicio = @codServicio) AND (Cod_Orden = @codOrden))
		BEGIN
			RAISERROR('El servicio que busca no pertenece a la orden', 16, 1)
			RETURN @@ERROR
		END

	UPDATE SERVICIOS_ORDEN
	SET Cod_Servicio = @nuevoServicio,
		Obser_Especifico = @nuevaObservacion
	WHERE (Cod_Orden = @codOrden) AND (Cod_Servicio = @codServicio)
END
GO

--Eliminar
CREATE OR ALTER PROCEDURE PA_Eliminar_OrdenServicio (
	@codOrden AS CHAR(6),
	@codServicio AS CHAR(2)
)
AS
BEGIN
	IF NOT EXISTS (SELECT * FROM ORDEN WHERE Cod_Orden = @codOrden)
        BEGIN
            RAISERROR('El número de orden no existe', 16, 1)
            RETURN @@ERROR
		END

	IF NOT EXISTS (SELECT * FROM SERVICIOS_ORDEN WHERE (Cod_Servicio = @codServicio) AND (Cod_Orden = @codOrden))
			BEGIN
				RAISERROR('El servicio no pertenece a la orden', 16, 1)
				RETURN @@ERROR
			END

	DELETE FROM SERVICIOS_ORDEN
	WHERE (Cod_Orden = @codOrden) AND (Cod_Servicio = @codServicio)
END
GO

--Vista de los servicios y orden
CREATE OR ALTER VIEW Vw_Lista_Servicios_Orden
AS
	SELECT	Cod_Orden AS 'Número de orden',
			Cod_Servicio AS 'Código de servicio',
			Obser_Especifico AS 'Observaciones especificas'
	FROM SERVICIOS_ORDEN
GO

--LISTAR
CREATE OR ALTER PROCEDURE PA_Listar_Servicio_Orden
	AS
	BEGIN
	SELECT* FROM Vw_Lista_Servicios_Orden
	END
GO


SELECT * FROM CATALOGO_SERVICIOS
SELECT * FROM ORDEN

EXEC PA_Crear_OrdenServicio 'T1', 'OR0001', 'Nada por el momento'
EXEC PA_Actualizar_OrdenServicio 'OR0001', 'T1', 'T2', 'Todo bien'
EXEC PA_Eliminar_OrdenServicio 'OR0001', 'T2'
EXEC PA_Listar_OrdenServicio 'OR0001'