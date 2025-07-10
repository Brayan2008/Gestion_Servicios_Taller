USE TALLER3
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
        Where C.Nro_Cliente = @Nro_Cliente_FK AND C.Tipo_Documento = @Tipo_documento_FK) 
        OR not exists (SELECT *
        FROM MECANICO as M
        WHERE M.Cod_Mecanico = @Cod_Mecanico_FK) 
        OR not exists(SELECT *
        FROM VEHICULOS AS V
        WHERE V.Placa_Vehiculo = @Placa_Vehiculo_FK ) 
        OR exists (SELECT *
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
        Where C.Nro_Cliente = @Nro_Cliente_FK AND C.Tipo_Documento = @Tipo_documento_FK) OR
        not exists (SELECT *
        FROM MECANICO as M
        WHERE M.Cod_Mecanico = @Cod_Mecanico_FK) OR
        not exists(SELECT *
        FROM VEHICULOS AS V
        WHERE V.Placa_Vehiculo = @Placa_Vehiculo_FK ) OR
        not exists (SELECT *
        FROM ORDEN AS O
        WHERE O.Cod_Orden = @Cod_Orden)) begin
        RAISERROR('Error al actualizar valores, la orden no existe o hay claves foraneas inexistentes', 4,16)
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

CREATE OR ALTER PROCEDURE PA_Orden_Eliminar
    (@Cod_Orden CHAR(6))
AS
BEGIN
    DECLARE @Estado TINYINT;

    -- Validar existencia y obtener el estado
    
	IF NOT EXISTS(SELECT 1 FROM ORDEN WHERE Cod_Orden = @Cod_Orden)
    BEGIN
        RAISERROR('La orden no existe.', 16, 1);
        RETURN @@ERROR;
    END

    SELECT @Estado = Estado_Orden FROM ORDEN WHERE Cod_Orden = @Cod_Orden;

    -- Verificar que no esté finalizada
    IF @Estado = 2
    BEGIN
        RAISERROR('No se puede eliminar una orden en estado Finalizado.', 16, 1);
        RETURN;
    END

    -- Eliminar accesorios relacionados
    DELETE FROM ORDEN_ACCESORIOS WHERE Cod_Orden = @Cod_Orden;

    -- Eliminar servicios relacionados
    DELETE FROM SERVICIOS_ORDEN WHERE Cod_Orden = @Cod_Orden;

    -- Eliminar la orden
    DELETE FROM ORDEN WHERE Cod_Orden = @Cod_Orden;
END
GO

CREATE OR ALTER VIEW Vw_MostrarOrden
AS
    SELECT Cod_Orden AS 'Codigo',
        Fecha_Orden AS 'Registrado',
        Fecha_Entrega AS 'Entregado',
        kilometraje AS 'KM',
        Num_Combustible AS 'Combustible',
        Obser_General 'Observacion general',
        T_Prob AS 'Tarjeta',
        Manual_Prob AS 'Manual',
        Llave,
        Estado_Orden AS 'Estado de la Orden',
        Quiñado,
        Rayado,
        Abollado,
        O.Cod_Mecanico AS 'DNI Mecanico',
        O.Nro_Cliente AS 'Numero Cliente',
        O.Placa_Vehiculo AS 'Placa'
    FROM ORDEN AS O INNER JOIN MECANICO AS M ON (O.Cod_Mecanico = M.Cod_Mecanico) -- LEFT -> INNER  si solo quieres ver las fk
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

CREATE OR ALTER VIEW Vw_OrdenFiltradas
AS
    SELECT Cod_Orden AS 'Codigo',
        Fecha_Orden AS 'Registrado',
        Fecha_Entrega AS 'Entregado',
        kilometraje AS 'KM',
        Num_Combustible AS 'Combustible',
        Obser_General 'Observacion general',
        T_Prob AS 'Tarjeta',
        Manual_Prob AS 'Manual',
        Llave,
        Estado_Orden AS 'Estado de la Orden',
        Quiñado,
        Rayado,
        Abollado,
        O.Cod_Mecanico AS 'DNI Mecanico',
        O.Nro_Cliente AS 'Numero Cliente',
        O.Placa_Vehiculo AS 'Placa'
    FROM ORDEN AS O LEFT JOIN MECANICO AS M ON (O.Cod_Mecanico = M.Cod_Mecanico) -- LEFT -> INNER  si solo quieres ver los varios a varios
        LEFT JOIN CLIENTE AS C ON (C.Nro_Cliente = O.Nro_Cliente)
        LEFT JOIN VEHICULOS AS V ON (V.Placa_Vehiculo = O.Placa_Vehiculo)
GO

CREATE OR ALTER PROC PA_FiltrarOrden
    (@cadena varchar (50))
AS
BEGIN
    SELECT *
    FROM Vw_OrdenFiltradas -- 112 -> yymmdd
    WHERE (Codigo+CONVERT(varchar,Registrado,112) + [Observacion general]+
                    CONVERT(varchar,Entregado,112) + CAST(KM AS VARCHAR)+ ISNULL([DNI Mecanico], '') + 
                    ISNULL([Numero Cliente], '') + ISNULL(Placa, '')) LIKE '%'+@cadena+'%'
    END
GO

CREATE OR ALTER VIEW Vw_ReporteOrden AS 
    SELECT * FROM ORDEN
GO

CREATE OR ALTER PROC PA_FiltrarOrden_Reporte
    (@cadena varchar (50))
AS
BEGIN
    SELECT *
    FROM Vw_ReporteOrden -- 112 -> yymmdd
    WHERE (Cod_Orden+CONVERT(varchar,Fecha_Orden,112) + Obser_General +
                    CONVERT(varchar,Fecha_Entrega,112) + CAST(kilometraje AS VARCHAR)+ ISNULL(Cod_Mecanico, '') + 
                    ISNULL(Nro_Cliente, '') + ISNULL(Placa_Vehiculo, '')) LIKE '%'+@cadena+'%'
    END
GO

EXEC PA_FiltrarOrden_Reporte 'a'
EXEC PA_Orden_Update 'OR0001', '2025-06-01', 1234.56, '2025-05-05', 45.50, 'Sin observaciones', 1, 0, 1, 1, 0, 0, 1, '90456789', 1, '12345678', 'ABC1234';
EXEC PA_Orden_Agregar 'OR0002', '2025-05-02', 987.34, '2025-05-07', 30.00, 'Cliente solicita revisión', 0, 1, 0, 0, 1, 0, 0, '23456789012', 0, '12345678', 'JKL7890';
EXEC PA_Orden_Agregar 'OR0003', '2025-05-03', 4321.12, '2025-05-08', 60.75, 'Cambio de aceite', 1, 0, 1, 2, 0, 1, 0, '23456789012', 0, '12345678', 'MNO1234';
EXEC PA_Orden_Agregar 'OR0004', '2025-05-04', 7890.00, '2025-05-09', 10.20, 'Frenos desgastados', 1, 1, 1, 0, 1, 1, 0, '90456789',1, '87654321', 'PQR5678';
EXEC PA_Orden_Agregar 'OR0005', '2025-05-05', 2345.67, '2025-05-10', 99.99, 'Revisión completa', 0, 0, 0, 1, 0, 0, 0, '78234567',1, '87654321', 'VWX3456';
EXEC PA_Orden_Agregar 'OR0006', '2025-05-06', 3456.78, '2025-05-11', 55.55, 'Alineación y balanceo', 1, 1, 1, 2, 0, 0, 0, '89012345678',0, '87654321', 'BCD2345';
EXEC PA_Orden_Agregar 'OR0007', '2025-05-07', 876.45, '2025-05-12', 15.25, 'Cliente espera llamada', 0, 1, 0, 1, 1, 1, 1, '37890123',1, '87654321', 'BCD2345';