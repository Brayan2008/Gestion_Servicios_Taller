USE TALLER
GO
SET DATEFORMAT YMD
go

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
	if @Fecha_Entrega < @Fecha_Orden BEGIN
		RAISERROR('La fecha de entrega no puede ser antes que la de orden', 16,1)
		RETURN @@ERROR END

    if (not exists(SELECT *
        FROM CLIENTE as C
        Where C.Nro_Cliente = @Nro_Cliente_FK AND C.Tipo_Documento = @Tipo_documento_FK) OR
        not exists (SELECT *
        FROM MECANICO as M
        WHERE M.Cod_Mecanico = @Cod_Mecanico_FK) OR
        not exists(SELECT *
        FROM VEHICULOS AS V
        WHERE V.Placa_Vehiculo = @Placa_Vehiculo_FK ) OR
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
--EXEC PA_Orden_Agregar 'AR0004', '2025/06/03', 3000, '2024/06/03',600,'Sin obser',0,0,1,1,0,0,0,'71332431',1,'44332211','AB-1111'
--GO


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
	if @Fecha_Entrega < @Fecha_Orden BEGIN
		RAISERROR('La fecha de entrega no puede ser antes que la de orden', 16,1)
		RETURN @@ERROR END

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
--EXEC PA_Orden_Update 'AR0003', '2025/06/03', 3000, '2025/06/04',600,'Paco pndj',0,1,1,1,0,0,1,'71332430',1,'44332211','AB-1111'
--GO

CREATE OR ALTER PROCEDURE PA_Orden_Eliminar --Elimna la orden junto a sus accesorio y servicios
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
--EXEC PA_Orden_Eliminar 'AR0004' 
--go


CREATE OR ALTER VIEW Vw_Orden_Mostrar
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
    FROM Vw_Orden_Mostrar
END
GO

CREATE OR ALTER VIEW Vw_Orden_Filtradas
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


CREATE OR ALTER PROC PA_Orden_Filtrar
    (@cadena varchar (50))
AS
BEGIN
    SELECT *
    FROM Vw_Orden_Filtradas -- 112 -> yymmdd
    WHERE (Codigo+CONVERT(varchar,Registrado,112) + [Observacion general]+
                    CONVERT(varchar,Entregado,112) + CAST(KM AS VARCHAR)+ ISNULL([DNI Mecanico], '') + 
                    ISNULL([Numero Cliente], '') + ISNULL(Placa, '')) LIKE '%'+@cadena+'%'
    END
GO

CREATE OR ALTER PROC PA_Orden_Estado
(    @Cod_Orden CHAR(6),
    @NuevoEstado TINYINT)  -- 0 = Pendiente, 1 = Proceso, 2 = Finalizado, 3 = Cancelado 
AS
BEGIN
    DECLARE @EstadoActual TINYINT;

	IF NOT EXISTS(SELECT 1 FROM ORDEN WHERE Cod_Orden = @Cod_Orden)
	BEGIN 
		RAISERROR('La orden no existe',16,1);
		RETURN @@ERROR
	END 

    -- Obtener estado actual
    SELECT @EstadoActual = Estado_Orden FROM ORDEN WHERE Cod_Orden = @Cod_Orden;
	
    -- Validar transiciones inválidas
    IF @EstadoActual IN (0) AND @NuevoEstado = 2
    BEGIN
        RAISERROR('No se puede cambiar de pendiente a finalizado.', 16, 1);
        RETURN @@ERROR;
    END
	ELSE IF @EstadoActual IN(1) AND @NuevoEstado = 0
	BEGIN 
		RAISERROR('No se puede cambiar de proceso a pendiente',16,1);
		RETURN @@ERROR
	END
	ELSE IF @EstadoActual IN(2) AND @NuevoEstado IN(3,0,1)
	BEGIN 
		RAISERROR('No se puede cambiar de finalizado a cancelado, pendiente o proceso',16,1);
		RETURN @@ERROR
	END 
	ELSE IF @EstadoActual IN(3) AND @NuevoEstado IN(2,1,0)
	BEGIN 
		RAISERROR('No se puede cambiar de CANCELADO a finalizado, pendiente o proceso',16,1);
		RETURN @@ERROR
	END 
    -- Actualizar estado
    UPDATE ORDEN
    SET Estado_Orden = @NuevoEstado
    WHERE Cod_Orden = @Cod_Orden;
END
GO