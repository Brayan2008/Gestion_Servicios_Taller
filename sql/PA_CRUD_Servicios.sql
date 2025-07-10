USE TALLER3
GO

CREATE OR ALTER PROC PA_CRUD_InsertarServicio
    (@codser AS CHAR(2),
    @nomser AS  VARCHAR(50),
    @precser AS decimal (5,2))
AS
BEGIN

    IF EXISTS(SELECT*
    FROM CATALOGO_SERVICIOS
    WHERE Cod_Servicio = @codser) BEGIN
        RAISERROR('Codigo YA Existe en la Tabla Catalogo de Servicios',16,1)
        RETURN @@ERROR
    END

    INSERT INTO dbo.CATALOGO_SERVICIOS
        (Cod_Servicio, Nom_Servicio,
        Precio_Servicio)
    VALUES
        (@codser, @nomser, @precser)
END 
GO

CREATE OR ALTER PROCEDURE PA_CRUD_ModificarServicio
    (@codser AS CHAR(2),
    @nomser AS  VARCHAR(50),
    @precser AS decimal (5,2))
AS
BEGIN

    IF NOT EXISTS(SELECT *
    FROM CATALOGO_SERVICIOS
    WHERE Cod_Servicio = @codser) BEGIN
        RAISERROR('Codigo NO Existe en la Tabla Catalogo de Servicios',16,1)
        RETURN @@ERROR
    END

    UPDATE dbo.CATALOGO_SERVICIOS
   SET Nom_Servicio = @nomser, Precio_Servicio = @precser
WHERE Cod_Servicio=@codser

END
GO

CREATE OR ALTER PROCEDURE PA_CRUD_EliminarServicio
    (@codser AS CHAR(2))
AS
BEGIN

    IF NOT EXISTS(SELECT *
    FROM CATALOGO_SERVICIOS
    WHERE Cod_Servicio=@codser) BEGIN
        RAISERROR('Codigo NO Existe en la Tabla Catalogo de Servicios',16,1)
        RETURN @@ERROR
    END

    IF EXISTS(SELECT *
    FROM SERVICIOS_ORDEN
    WHERE Cod_Servicio=@codser) BEGIN
        RAISERROR('No se puede Eliminar por que ya esta relacionado con servicios orden',16,1)
        RETURN @@ERROR
    END

    DELETE FROM CATALOGO_SERVICIOS
WHERE Cod_Servicio=@codser
END
GO

CREATE OR ALTER VIEW Vw_ListarServicios
AS
    SELECT Cod_Servicio as 'Codigo', Nom_Servicio AS 'Nombre del Servicio',
        Precio_Servicio AS 'Precio del servicio'
    FROM CATALOGO_SERVICIOS
GO

CREATE OR ALTER PROCEDURE PA_CRUD_ListarServicios
AS
BEGIN
    SELECT *
    FROM Vw_ListarServicios
END
GO