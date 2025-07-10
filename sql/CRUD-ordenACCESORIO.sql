create OR ALTER procedure PA_oAccesorio_Agregar
(@Cod_Orden as char(6),
@Cod_Accesorio as char(2),
@Cant_Accesorio as tinyint,  
@Obser_Accesorio as varchar(50),
@Estado_Accesorio as bit
)
as
begin 
IF NOT EXISTS (SELECT * FROM ORDEN WHERE Cod_Orden = @Cod_Orden)BEGIN
RAISERROR('La orden no existe', 16, 1)
RETURN @@ERROR
END

if not exists (select * from ACCESORIOS where Cod_Accesorio= @Cod_Accesorio) begin
RAISERROR('El accesorio no existe',16,1)
RETURN @@ERROR
end

if EXISTS(select * from ORDEN_ACCESORIOS where Cod_Orden= @Cod_Orden and Cod_Accesorio=@Cod_Accesorio) begin
RAISERROR('Ya existe dentro de la Orden_Accesorio',16,1)
RETURN @@ERROR
end 

insert into dbo.ORDEN_ACCESORIOS(Cod_Orden, Cod_Accesorio, Cant_Accesorio, Obser_Accesorio, Estado_Accesorio)
values (@Cod_Orden, @Cod_Accesorio, @Cant_Accesorio, @Obser_Accesorio,@Estado_Accesorio)
end
GO

CREATE or ALTER PROCEDURE PA_oAccesorio_Update
(@Cod_Orden as char(6),
@Cod_Accesorio as char(2),
@Cant_Accesorio as tinyint, 
@Obser_Accesorio as varchar(50),
@Estado_Accesorio as bit
)
as
begin 
IF NOT EXISTS(SELECT * FROM ORDEN_ACCESORIOS WHERE Cod_Orden=@Cod_Orden and Cod_Accesorio = @Cod_Accesorio) BEGIN
RAISERROR('El accesorio no existe en la orden',16,1)
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
GO

CREATE OR ALTER PROCEDURE PA_oAccesorio_Eliminar
(@Cod_Orden as char(6),
@Cod_Accesorio as char(2)
)
AS
BEGIN

IF NOT EXISTS(SELECT * FROM ORDEN_ACCESORIOS WHERE Cod_Orden=@Cod_Orden and Cod_Accesorio = @Cod_Accesorio) BEGIN
	RAISERROR('El accesorio no existe en la orden',16,1)
	RETURN @@ERROR
END

DELETE FROM ORDEN_ACCESORIOS
WHERE Cod_Orden=@Cod_Orden and Cod_Accesorio = @Cod_Accesorio
END
GO

CREATE OR ALTER view Vw_oAccesorio_Mostrar
as
select Cod_Orden as 'Codigo de orden',Cod_Accesorio as 'Codigo de accesorio', Cant_Accesorio as 'Cantidad', Obser_Accesorio as 'Observaciones', Estado_Accesorio as 'Estado'
from ORDEN_ACCESORIOS;
GO

CREATE OR ALTER PROCEDURE PA_oAccesorio_Obtener
AS
BEGIN
SELECT * FROM Vw_oAccesorio_Mostrar
END
GO

CREATE OR ALTER VIEW Vw_oAccesorio_Filtrados AS
	SELECT Cod_Orden, Cod_Accesorio, Obser_Accesorio FROM ORDEN_ACCESORIOS
GO

CREATE OR ALTER PROCEDURE PA_oAccesorio_Filtrar (@cadena as varchar (50)) AS
BEGIN 
	SELECT * FROM Vw_oAccesorio_Filtrados WHERE (Cod_Accesorio+Cod_Orden+Obser_Accesorio) LIKE '%'+@cadena+'%'
END
GO