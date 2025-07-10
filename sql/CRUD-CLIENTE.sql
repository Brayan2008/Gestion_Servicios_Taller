create or alter PROCEDURE PA_Cliente_Agregar (@Nro_Cliente varchar(11),
								  @Tipo_Documento bit, 
								  @Nombre varchar(50), 
								  @Telefono varchar (9), 
								  @Direccion varchar(50), 
								  @Distrito varchar(20)) as 
begin

	IF EXISTS (SELECT * FROM CLIENTE AS C WHERE C.Nro_Cliente = @Nro_Cliente and C.Tipo_Documento = @Tipo_Documento) begin
		raiserror('Ya existe un cliente con ese numero', 16,1)
		return @@ERROR
	end

	INSERT INTO CLIENTE VALUES (@Nro_Cliente, @Tipo_Documento, @Nombre, @Telefono, @Direccion, @Distrito) 

end
GO

create or alter PROCEDURE PA_Cliente_Update(@Nro_Cliente varchar(11),
								  @Tipo_Documento bit, 
								  @Nombre varchar(50), 
								  @Telefono varchar (9), 
								  @Direccion varchar(50), 
								  @Distrito varchar(20)) as 
begin

	IF NOT EXISTS (SELECT * FROM CLIENTE AS C WHERE C.Nro_Cliente = @Nro_Cliente AND C.Tipo_Documento = @Tipo_Documento) begin
		raiserror('No existe un cliente con ese numero', 16,1)
		return @@ERROR
	end

	UPDATE CLIENTE
	SET  Nom_Cliente = @Nombre, Telf_Cliente = @Telefono, Direc_Cliente = @Direccion, Distri_Cliente = @Distrito
	WHERE Nro_Cliente = @Nro_Cliente and Tipo_Documento = @Tipo_Documento

end
GO

create or alter procedure PA_Cliente_Eliminar (@Nro_Cliente varchar(11), @Tipo_Documento bit) as begin
	
	IF NOT EXISTS (SELECT * FROM CLIENTE AS C WHERE C.Nro_Cliente = @Nro_Cliente AND C.Tipo_Documento = @Tipo_Documento) begin
		raiserror('No existe un cliente con ese numero', 16,1)
		return @@ERROR
	end

	IF EXISTS (SELECT * FROM ORDEN AS O WHERE O.Nro_Cliente = @Nro_Cliente AND O.Tipo_Documento = @Tipo_Documento) begin
		raiserror('No se puede eliminar un cliente relacionado a una orden', 16,1)
		return @@ERROR
	end
	
	DELETE FROM CLIENTE WHERE Nro_Cliente = @Nro_Cliente
end
GO

CREATE OR ALTER  VIEW Vw_Cliente_Mostrar
AS
    SELECT Nro_Cliente AS 'Codigo', 
           Tipo_Documento as 'Tipo Documento', 
           Nom_Cliente as 'Nombre', 
           Telf_Cliente as 'Telefono', 
           Direc_Cliente AS 'Direccion', 
           Distri_Cliente as 'Distrito'
    FROM Cliente;
GO

CREATE OR ALTER proc PA_Cliente_Obtener
as
BEGIN
    SELECT *
    FROM Vw_Cliente_Mostrar
END
GO 

CREATE OR ALTER VIEW Vw_Cliente_Filtrados AS
	SELECT Nro_Cliente, Nom_Cliente FROM CLIENTE
GO

CREATE OR ALTER PROCEDURE PA_Cliente_Filtrar(@cadena as varchar (50)) AS
	BEGIN
		SELECT * FROM Vw_Cliente_Filtrados WHERE
		(Nro_Cliente+Nom_Cliente) LIKE ('%'+@cadena+'%')
	END
GO
