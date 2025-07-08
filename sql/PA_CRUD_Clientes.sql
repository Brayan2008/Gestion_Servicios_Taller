USE TALLER
GO

CREATE OR ALTER PROCEDURE PA_CREAR_CLIENTE(@Nro_Cliente varchar(11),
    @Tipo_Documento bit,
    @Nombre varchar(50),
    @Telefono varchar (9),
    @Direccion varchar(50),
    @Distrito varchar(20))
as
begin

    IF EXISTS (SELECT *
    FROM CLIENTE AS C
    WHERE C.Nro_Cliente = @Nro_Cliente and C.Tipo_Documento = @Tipo_Documento) begin
        raiserror('Ya existe un cliente con ese numero', 16,1)
        return @@ERROR
    end

    INSERT INTO CLIENTE
    VALUES
        (@Nro_Cliente, @Tipo_Documento, @Nombre, @Telefono, @Direccion, @Distrito)

end
go

CREATE OR ALTER PROCEDURE PA_ALTERAR_CLIENTE(@Nro_Cliente varchar(11),
    @Tipo_Documento bit,
    @Nombre varchar(50),
    @Telefono varchar (9),
    @Direccion varchar(50),
    @Distrito varchar(20))
as
begin

    IF NOT EXISTS (SELECT *
    FROM CLIENTE AS C
    WHERE C.Nro_Cliente = @Nro_Cliente AND C.Tipo_Documento = @Tipo_Documento) begin
        raiserror('No existe un cliente con ese numero', 16,1)
        return @@ERROR
    end

    UPDATE CLIENTE
	SET  Nom_Cliente = @Nombre, Telf_Cliente = @Telefono, Direc_Cliente = @Direccion, Distri_Cliente = @Distrito
	WHERE Nro_Cliente = @Nro_Cliente and Tipo_Documento = @Tipo_Documento

end
go

CREATE OR ALTER procedure PA_ELIMINAR_CLIENTE
    (@Nro_Cliente varchar(11),
    @Tipo_Documento bit)
as
begin

    IF NOT EXISTS (SELECT *
    FROM CLIENTE AS C
    WHERE C.Nro_Cliente = @Nro_Cliente AND C.Tipo_Documento = @Tipo_Documento) begin
        raiserror('No existe un cliente con ese numero', 16,1)
        return @@ERROR
    end

    IF NOT EXISTS (SELECT *
    FROM ORDEN AS O
    WHERE O.Nro_Cliente = @Nro_Cliente AND O.Tipo_Documento = @Tipo_Documento) begin
        raiserror('No existe un cliente con ese numero', 16,1)
        return @@ERROR
    end

    DELETE FROM CLIENTE WHERE Nro_Cliente = @Nro_Cliente
end
go

CREATE OR ALTER VIEW Vw_MostrarCliente
AS
    SELECT Nro_Cliente AS 'Codigo', 
           Tipo_Documento as 'Tipo Documento', 
           Nom_Cliente as 'Nombre', 
           Telf_Cliente as 'Telefono', 
           Direc_Cliente AS 'Direccion', 
           Distri_Cliente as 'Distrito'
    FROM Cliente
GO

CREATE OR ALTER proc PA_Listar_Cliente
as
BEGIN
    SELECT *
    FROM Vw_MostrarCliente
END
GO