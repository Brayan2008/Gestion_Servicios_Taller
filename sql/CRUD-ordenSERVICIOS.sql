CREATE OR ALTER PROCEDURE PA_oServicio_Agregar (
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

	 IF EXISTS (SELECT 1 FROM SERVICIOS_ORDEN WHERE Cod_Servicio = @codServicio AND Cod_Orden = @codOrden)
        BEGIN
            RAISERROR('Este servicio ya está registrado en la orden', 16, 1)
            RETURN @@ERROR
     END

	 INSERT INTO SERVICIOS_ORDEN (Cod_Servicio, Cod_Orden, Obser_Especifico)
        VALUES (@codServicio, @codOrden, @observaciones)
END
GO

CREATE or ALTER PROCEDURE PA_oServicio_Update (
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

CREATE OR ALTER PROCEDURE PA_oServicio_Eliminar (
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

CREATE OR ALTER VIEW Vw_oServicio_Mostrar
AS
	SELECT	Cod_Orden AS 'Número de orden',
			Cod_Servicio AS 'Código de servicio',
			Obser_Especifico AS 'Observaciones especificas'
	FROM SERVICIOS_ORDEN;
GO

CREATE OR ALTER PROCEDURE PA_oServicio_Obtener
	AS
	BEGIN
	SELECT* FROM Vw_oServicio_Mostrar
	END
GO

CREATE OR ALTER VIEW Vw_oServicio_Filtrados AS
	SELECT Cod_Orden, Cod_Servicio, Obser_Especifico FROM SERVICIOS_ORDEN
GO

CREATE OR ALTER PROC PA_oServicio_Filtrar (@cadena as varchar (50)) AS
BEGIN
	SELECT * FROM Vw_oServicio_Filtrados WHERE (Cod_Servicio+Cod_Orden+Obser_Especifico) LIKE '%'+@cadena+'%'
END
GO