USE TALLER
GO

CREATE OR ALTER PROC PA_CRUD_InsertarAccesorio
    (@codacc AS CHAR(2),
    @nomacc AS  VARCHAR(50))
AS
BEGIN

    IF EXISTS(SELECT*
    FROM ACCESORIOS
    WHERE Cod_Accesorio = @codacc) 
BEGIN
        RAISERROR('Codigo YA Existe en la Tabla Accesorios ',16,1)
        RETURN @@ERROR
    END

    INSERT INTO dbo.ACCESORIOS
        (Cod_Accesorio, Nom_Accesorio)
    VALUES
        (@codacc, @nomacc)
END 
GO


CREATE OR ALTER PROCEDURE PA_CRUD_ModificarAccesorio
    (@codacc AS CHAR(2),
    @nomacc AS  VARCHAR(50))
AS
BEGIN
    IF NOT EXISTS(SELECT *
    FROM ACCESORIOS
    WHERE Cod_Accesorio = @codacc) BEGIN
        RAISERROR('Codigo NO Existe en la Tabla Accesorios',16,1)
        RETURN @@ERROR
    END

    UPDATE dbo.ACCESORIOS
   SET Nom_Accesorio = @nomacc
WHERE Cod_Accesorio=@codacc

END
GO

CREATE OR ALTER PROCEDURE PA_CRUD_EliminarAccesorio
    (@codacc AS CHAR(2))
AS
BEGIN

    IF NOT EXISTS(SELECT *
    FROM ACCESORIOS
    WHERE Cod_Accesorio = @codacc) BEGIN
        RAISERROR('Codigo NO Existe en la Tabla Accesorios ',16,1)
        RETURN @@ERROR
    END

    IF EXISTS(SELECT *
    FROM ORDEN_ACCESORIOS
    WHERE Cod_Accesorio=@codacc) BEGIN
        RAISERROR('No se puede Eliminar por que ya esta relacionado con Orden Accesorios',16,1)
        RETURN @@ERROR
    END
    DELETE FROM ACCESORIOS
WHERE Cod_Accesorio=@codacc
END
GO

CREATE OR ALTER PROCEDURE PA_CRUD_ListarAccesorios
AS
BEGIN
    SELECT *
    FROM Vw_ListarAccesorios
END
GO


CREATE OR ALTER VIEW Vw_ListarAccesorios
AS
    SELECT Cod_Accesorio AS 'Codigo', Nom_Accesorio AS 'Nombre del Accesorio'
    FROM ACCESORIOS
GO