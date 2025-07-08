USE TALLER
GO

CREATE OR ALTER proc PA_Orden_Agregar(@Cod_Orden char(6),
    @Fecha_Orden date,
    @kilometraje decimal(6,2),
    @Fecha_Entrega	date,
    @Num_Combustible decimal(5,2),
    @Obser_General varchar(50),
    @T_Prob bit,
    @Manual_Prob bit,
    @Llave bit,
    @Estado_Orden tinyint,
    @Quiñado bit,
    @Rayado bit,
    @Abollado bit,
    @Nro_Cliente_FK varchar(11),
    @Tipo_documento_FK bit,
    @Cod_Mecanico_FK char(8),
    @Placa_Vehiculo_FK char(7))
as
begin
    if (not exists(SELECT *
        FROM CLIENTE as C
        Where C.Nro_Cliente = @Nro_Cliente_FK AND C.Tipo_Documento = @Tipo_documento_FK) AND
        not exists (SELECT *
        FROM MECANICO as M
        WHERE M.Cod_Mecanico = @Cod_Mecanico_FK) AND
        not exists(SELECT *
        FROM VEHICULOS AS V
        WHERE V.Placa_Vehiculo = @Placa_Vehiculo_FK ) AND
        exists (SELECT *
        FROM ORDEN AS O
        WHERE O.Cod_Orden = @Cod_Orden)) begin
        RAISERROR('Error al insertar valores, la clave principal ya existe o hay claves foraneas inexistentes', 4,16)
        return @@ERROR
    end
    INSERT INTO ORDEN
    VALUES
        (@Cod_Orden,
            @Fecha_Orden,
            @kilometraje,
            @Fecha_Entrega,
            @Num_Combustible,
            @Obser_General,
            @T_Prob,
            @Manual_Prob,
            @Llave,
            @Estado_Orden,
            @Quiñado,
            @Rayado,
            @Abollado,
            @Nro_Cliente_FK,
            @Tipo_documento_FK,
            @Cod_Mecanico_FK,
            @Placa_Vehiculo_FK)
end
go


CREATE OR ALTER proc PA_Orden_Update(@Cod_Orden char(6),
    @Fecha_Orden date,
    @kilometraje decimal(6,2),
    @Fecha_Entrega	date,
    @Num_Combustible decimal(5,2),
    @Obser_General varchar(50),
    @T_Prob bit,
    @Manual_Prob bit,
    @Llave bit,
    @Estado_Orden tinyint,
    @Quiñado bit,
    @Rayado bit,
    @Abollado bit,
    @Nro_Cliente_FK varchar(11),
    @Tipo_documento_FK bit,
    @Cod_Mecanico_FK char(8),
    @Placa_Vehiculo_FK char(7)
)
as
begin
    if (not exists(SELECT *
        FROM CLIENTE as C
        Where C.Nro_Cliente = @Nro_Cliente_FK AND C.Tipo_Documento = @Tipo_documento_FK) AND
        not exists (SELECT *
        FROM MECANICO as M
        WHERE M.Cod_Mecanico = @Cod_Mecanico_FK) AND
        not exists(SELECT *
        FROM VEHICULOS AS V
        WHERE V.Placa_Vehiculo = @Placa_Vehiculo_FK ) AND
        not exists (SELECT *
        FROM ORDEN AS O
        WHERE O.Cod_Orden = @Cod_Orden)) begin
        RAISERROR('Error al insertar valores, la clave principal ya existe o hay claves foraneas inexistentes', 4,16)
        return @@ERROR
    end
    UPDATE ORDEN
			SET Fecha_Orden = @Fecha_Orden, kilometraje = @kilometraje, Fecha_Entrega = @Fecha_Entrega,
							  Num_Combustible = @Num_Combustible, Obser_General = @Obser_General, T_Prob = @T_Prob,
							  Manual_Prob = @Manual_Prob, Llave = @Llave, Estado_Orden = @Estado_Orden, Quiñado = @Quiñado,
							  Rayado = @Rayado, Abollado = @Abollado, Nro_Cliente = @Nro_Cliente_FK, Tipo_Documento = @Tipo_documento_FK,
							  Cod_Mecanico = @Cod_Mecanico_FK, Placa_Vehiculo = @Placa_Vehiculo_FK
			WHERE Cod_Orden = @Cod_Orden

end
GO

CREATE OR ALTER proc PA_Orden_Eliminar(@CodOrden char(6))
as
begin
    if not exists (Select *
    from Orden as O
    Where O.Cod_Orden = @CodOrden) begin
        RAISERROR('No existe la orden', 16,4)
        return @@ERROR
    end
    SELECT *
    FROM ORDEN
    UPDATE ORDEN
	SET Estado_Orden = 2
	WHERE Cod_Orden = @CodOrden
end
GO

CREATE OR ALTER VIEW Vw_MostrarOrden
AS
    SELECT Cod_Orden AS 'Codigo de orden',
        Fecha_Orden AS 'Fecha de la Orden',
        kilometraje,
        Num_Combustible AS 'cantidad de combustible',
        Obser_General 'observacion general',
        T_Prob AS 'Tarjeta de propiedad',
        Manual_Prob AS 'Manuel de propietario',
        Llave AS 'llave del vehiculo',
        Estado_Orden,
        Quiñado,
        Rayado,
        Abollado,
        O.Cod_Mecanico AS 'DNI Mecanico',
        O.Nro_Cliente,
        O.Placa_Vehiculo
    FROM ORDEN AS O INNER JOIN MECANICO AS M ON (O.Cod_Mecanico = M.Cod_Mecanico)
        INNER JOIN CLIENTE AS C ON (C.Nro_Cliente = O.Nro_Cliente)
        INNER JOIN VEHICULOS AS V ON (V.Placa_Vehiculo = O.Placa_Vehiculo)
GO

CREATE OR ALTER proc PA_Orden_Obtener
as
BEGIN
    SELECT *
    FROM Vw_MostrarOrden
END
GO

CREATE OR ALTER VIEW Vw_OrdenFiltradas AS
	SELECT Cod_Orden, Estado_Orden, Nro_Cliente, Tipo_Documento, Cod_Mecanico, Placa_Vehiculo FROM ORDEN
GO

CREATE OR ALTER PROC PA_FiltrarOrden (@cadena varchar (50)) AS
	BEGIN
		SELECT* FROM Vw_OrdenFiltradas 
		WHERE (Cod_Orden+Estado_Orden+Nro_Cliente+Tipo_Documento+Cod_Mecanico+Placa_Vehiculo) LIKE ('%'+@cadena+'%')
	END
GO
