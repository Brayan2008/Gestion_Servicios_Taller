use TALLER3
go

--Procedimiento para insertar
create OR ALTER procedure PA_CRUD_InsertarOrden_Accesorio
    (
    @Cod_Orden as char(6),
    @Cod_Accesorio as char(2),
    @Cant_Accesorio as tinyint,
    @Obser_Accesorio as varchar(50),
    @Estado_Accesorio as bit
)
as
begin
    if not exists (select *
    from ORDEN_ACCESORIOS
    where Cod_Orden= @Cod_Orden) begin
        RAISERROR('Codigo de la orden no existe',16,1)
        RETURN @@ERROR
    end

    if not exists (select *
    from ORDEN_ACCESORIOS
    where Cod_Accesorio= @Cod_Accesorio) begin
        RAISERROR('Codigo del accesorio no existe',16,1)
        RETURN @@ERROR
    end

    if EXISTS(select *
    from ORDEN_ACCESORIOS
    where Cod_Orden= @Cod_Orden and Cod_Accesorio=@Cod_Accesorio) begin
        RAISERROR('Ya existe dentro de la Orden_Accesorio',16,1)
        RETURN @@ERROR
    end

    insert into dbo.ORDEN_ACCESORIOS
        (Cod_Orden, Cod_Accesorio, Cant_Accesorio, Obser_Accesorio, Estado_Accesorio)
    values
        (@Cod_Orden, @Cod_Accesorio, @Cant_Accesorio, @Obser_Accesorio, @Estado_Accesorio)
end
go

--Procedimiento para modificar
CREATE OR ALTER PROCEDURE PA_CRUD_ModificarOrden_Accesorio
    (@Cod_Orden as char(6),
    @Cod_Accesorio as char(2),
    @Cant_Accesorio as tinyint,
    @Obser_Accesorio as varchar(50),
    @Estado_Accesorio as bit
)
as
begin
    IF NOT EXISTS(SELECT *
    FROM ORDEN_ACCESORIOS
    WHERE Cod_Orden=@Cod_Orden and Cod_Accesorio = @Cod_Accesorio) BEGIN
        RAISERROR('NO Existe en la Tabla Orden_Accesorio',16,1)
        RETURN @@ERROR
    END

    IF @Cant_Accesorio is not null
UPDATE dbo.ORDEN_ACCESORIOS
SET Cant_Accesorio = @Cant_Accesorio
WHERE Cod_Orden=@Cod_Orden and Cod_Accesorio = @Cod_Accesorio

    IF @Obser_Accesorio is not null
UPDATE dbo.ORDEN_ACCESORIOS
SET Obser_Accesorio = @Obser_Accesorio
WHERE Cod_Orden=@Cod_Orden and Cod_Accesorio = @Cod_Accesorio

    If @Estado_Accesorio is not null
UPDATE dbo.ORDEN_ACCESORIOS
SET Estado_Accesorio =@Estado_Accesorio
WHERE Cod_Orden=@Cod_Orden and Cod_Accesorio = @Cod_Accesorio

END
go

--Procedimiento para eliminar 

CREATE OR ALTER PROCEDURE PA_CRUD_EliminarOrden_Accesorio
    (@Cod_Orden as char(6),
    @Cod_Accesorio as char(2)
)
AS
BEGIN

    IF NOT EXISTS(SELECT *
    FROM ORDEN_ACCESORIOS
    WHERE Cod_Orden=@Cod_Orden and Cod_Accesorio = @Cod_Accesorio) BEGIN
        RAISERROR('NO Existe en la Tabla Orden_Accesorio',16,1)
        RETURN @@ERROR
    END

    DELETE FROM ORDEN_ACCESORIOS
WHERE Cod_Orden=@Cod_Orden and Cod_Accesorio = @Cod_Accesorio
END
GO

--Procedimiento para leer

CREATE OR ALTER view Vw_ListarOrden_Accesorio
as
    select Cod_Orden as 'Codigo de orden', Cod_Accesorio as 'Codigo de accesorio', Cant_Accesorio as 'Cantidad', Obser_Accesorio as 'Observaciones', Estado_Accesorio as 'Estado'
    from ORDEN_ACCESORIOS
go

CREATE OR ALTER PROCEDURE PA_CRUD_ListarOrden_Accesorio
AS
BEGIN
    SELECT *
    FROM Vw_ListarOrden_Accesorio
END
GO