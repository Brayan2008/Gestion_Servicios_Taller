USE TALLER
GO

create or alter procedure PA_Mecanico_Agregar
(@Cod_Mecanico as char(8),
@Nom_Mecanico as varchar(50),
@Telef_Mecanico as char(9),
@Direc_Mecanico as varchar(50))
as
begin 
IF EXISTS(SELECT * FROM MECANICO WHERE Cod_Mecanico=@Cod_Mecanico) BEGIN
	RAISERROR('El mecanico ya está registrado',16,1)
	RETURN @@ERROR
END

insert into dbo.MECANICO(Cod_Mecanico,Nom_Mecanico,Telef_Mecanico,Direc_Mecanico)
values (@Cod_Mecanico,@Nom_Mecanico,@Telef_Mecanico,@Direc_Mecanico)
end
GO
--EXEC PA_Mecanico_Agregar '00998877','Luis','123456789','TARAPOTO' GO

CREATE OR ALTER PROCEDURE PA_Mecanico_Update
(@Cod_Mecanico as char(8),
@Nom_Mecanico as varchar(50),
@Telef_Mecanico as char(9),
@Direc_Mecanico as varchar(50))
AS
BEGIN

IF NOT EXISTS(SELECT * FROM MECANICO WHERE Cod_Mecanico=@Cod_Mecanico) BEGIN
	RAISERROR('Codigo NO Existe en la Tabla Mecanico',16,1)
	RETURN @@ERROR
END

UPDATE dbo.MECANICO
   SET Nom_Mecanico = @Nom_Mecanico,
		Telef_Mecanico = @Telef_Mecanico,
		Direc_Mecanico = @Direc_Mecanico
WHERE Cod_Mecanico=@Cod_Mecanico
END
GO
--EXEC PA_Mecanico_Update '00998877','Luis','123456789','TARAPOTO' GO

CREATE OR ALTER PROCEDURE PA_Mecanico_Eliminar
(@Cod_Mecanico as char(8))
AS
BEGIN

IF NOT EXISTS(SELECT * FROM MECANICO WHERE Cod_Mecanico=@Cod_Mecanico) BEGIN
	RAISERROR('Mecanico no registrado',16,1)
	RETURN @@ERROR
END

IF EXISTS(SELECT * FROM ORDEN WHERE Cod_Mecanico=@Cod_Mecanico) BEGIN
	RAISERROR('No se puede Eliminar por que ya esta relacionado con la orden',16,1)
	RETURN @@ERROR
END

DELETE FROM MECANICO
WHERE Cod_Mecanico=@Cod_Mecanico
END
GO
--EXEC PA_Mecanico_Eliminar '10000076'

CREATE OR ALTER view Vw_Mecanico_Mostrar
as
select Cod_Mecanico as 'Codigo',Nom_Mecanico as 'Nombre Completo', Telef_Mecanico as 'telefono', Direc_Mecanico as 'Direccion'  from MECANICO;
GO

CREATE OR ALTER PROCEDURE PA_Mecanico_Obtener
AS
BEGIN
SELECT * FROM Vw_Mecanico_Mostrar
END
GO

CREATE OR ALTER VIEW Vw_Mecanico_Filtrados AS
	SELECT Cod_Mecanico, Nom_Mecanico, Telef_Mecanico, Direc_Mecanico FROM MECANICO;
GO

Create or ALTER PROC PA_Mecanico_Filtrar (@Cadena varchar(50)) AS
	BEGIN 
		SELECT * FROM Vw_Mecanico_Filtrados 
		WHERE (Cod_Mecanico+Nom_Mecanico+Telef_Mecanico+Direc_Mecanico) LIKE ('%'+@Cadena +'%')
	END
GO

