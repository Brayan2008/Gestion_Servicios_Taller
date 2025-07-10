use TALLER3
GO

--Procedimito PA_CRUD_InsertarMecanico
CREATE OR ALTER procedure PA_CRUD_InsertarMecanico
    (@Cod_Mecanico as char(8),
    @Nom_Mecanico as varchar(50),
    @Telef_Mecanico as char(9),
    @Direc_Mecanico as varchar(50))
as
begin
    IF EXISTS(SELECT *
    FROM MECANICO
    WHERE Cod_Mecanico=@Cod_Mecanico) BEGIN
        RAISERROR('Codigo YA Existe en la Tabla Mecanico',16,1)
        RETURN @@ERROR
    END

    insert into dbo.MECANICO
        (Cod_Mecanico,Nom_Mecanico,Telef_Mecanico,Direc_Mecanico)
    values
        (@Cod_Mecanico, @Nom_Mecanico, @Telef_Mecanico, @Direc_Mecanico)
end
go

--Procedimito PA_CRUD_ModificarMecanico

CREATE OR ALTER PROCEDURE PA_CRUD_ModificarMecanico
    (@Cod_Mecanico as char(8),
    @Nom_Mecanico as varchar(50),
    @Telef_Mecanico as char(9),
    @Direc_Mecanico as varchar(50))
AS
BEGIN

    IF NOT EXISTS(SELECT *
    FROM MECANICO
    WHERE Cod_Mecanico=@Cod_Mecanico) BEGIN
        RAISERROR('Codigo NO Existe en la Tabla Mecanico',16,1)
        RETURN @@ERROR
    END

    UPDATE dbo.MECANICO
   SET Nom_Mecanico = @Nom_Mecanico
WHERE Cod_Mecanico=@Cod_Mecanico
END
go


--Procedimiento PA_CRUD_EliminarMecanico

CREATE OR ALTER PROCEDURE PA_CRUD_EliminarMecanico
    (@Cod_Mecanico as char(8))
AS
BEGIN

    IF NOT EXISTS(SELECT *
    FROM MECANICO
    WHERE Cod_Mecanico=@Cod_Mecanico) BEGIN
        RAISERROR('Codigo NO Existe en la Tabla Mecanico',16,1)
        RETURN @@ERROR
    END

    IF EXISTS(SELECT *
    FROM ORDEN
    WHERE Cod_Mecanico=@Cod_Mecanico) BEGIN
        RAISERROR('No se puede Eliminar por que ya esta relacionado con la orden',16,1)
        RETURN @@ERROR
    END

    DELETE FROM MECANICO
WHERE Cod_Mecanico=@Cod_Mecanico
END
GO

--Vista_Mecanico
CREATE OR ALTER view Vw_ListarMecanicos
as
    select Cod_Mecanico as 'Codigo', Nom_Mecanico as 'Nombre Completo', Telef_Mecanico as 'telefono', Direc_Mecanico as 'Direccion'
    from MECANICO
go

--Procedimiento PA_CRUD_ListarMecanico

CREATE OR ALTER PROCEDURE PA_CRUD_ListarMecanico
AS
BEGIN
    SELECT *
    FROM Vw_ListarMecanicos
END
GO