USE TALLER
GO
--Insertar
CREATE OR ALTER PROCEDURE PA_Crear_Vehiculo
    (
    @placa AS CHAR(7),
    @marca AS VARCHAR(20),
    @modelo AS VARCHAR(40),
    @chasis AS VARCHAR(17),
    @motor AS VARCHAR(25),
    @año AS SMALLINT,
    @color AS VARCHAR(20)
)
AS
BEGIN
    IF EXISTS (SELECT *
    FROM VEHICULOS
    WHERE Placa_Vehiculo = @placa)
        BEGIN
        RAISERROR('El vehículo ya está registrado', 16, 1)
        RETURN @@ERROR
    END

    -- Insertar el vehículo
    INSERT INTO VEHICULOS
        (Placa_Vehiculo, Marca_Vehiculo, Modelo_Vehiculo, Mod_Chasis, Num_Motor, Año_Vehiculo, Color_Vehiculo)
    VALUES
        (@placa, @marca, @modelo, @chasis, @motor, @año, @color)
END
GO

--ACTUALIZAR
CREATE OR ALTER PROCEDURE PA_Actualizar_Vehiculo(
    @placa AS CHAR(7),
    @marca AS VARCHAR(20),
    @modelo AS VARCHAR(40),
    @chasis AS VARCHAR(17),
    @motor AS VARCHAR(25),
    @año AS SMALLINT,
    @color AS VARCHAR(20)
)
AS
BEGIN
    IF NOT EXISTS (SELECT *
    FROM VEHICULOS
    WHERE Placa_Vehiculo = @placa)
        BEGIN
        RAISERROR('El vehículo no está registrado', 16, 1)
        RETURN @@ERROR
    END
    UPDATE VEHICULOS 
	SET Marca_Vehiculo = @marca,
		Modelo_Vehiculo = @modelo,
		Mod_Chasis = @chasis,
		Num_Motor = @motor,
		Año_Vehiculo = @año,
		Color_Vehiculo = @color
	WHERE (Placa_Vehiculo = @placa)
END
GO

--Eliminar
CREATE OR ALTER PROCEDURE PA_Eliminar_Vehiculo(
    @placa AS VARCHAR(7)
)
AS
BEGIN
    IF NOT EXISTS (SELECT *
    FROM VEHICULOS
    WHERE Placa_Vehiculo = @placa)
        BEGIN
        RAISERROR('El vehículo no está registrado', 16, 1)
        RETURN @@ERROR
    END

    IF EXISTS (SELECT *
    FROM ORDEN
    WHERE Placa_Vehiculo = @placa)
		BEGIN
        RAISERROR('No se puede eliminar un vehículo que ya posee alguna Orden registrada', 16, 1)
        RETURN @@ERROR
    END

    DELETE FROM VEHICULOS WHERE (Placa_Vehiculo = @placa)
END
GO

--Vista de vehiculos
CREATE OR ALTER VIEW Vw_Listar_Vehiculos
AS
    SELECT Placa_Vehiculo AS 'Placa',
        Marca_Vehiculo AS 'Marca',
        Modelo_Vehiculo AS 'Modelo',
        Mod_Chasis AS 'Chasis',
        Num_Motor AS 'Motor',
        Año_Vehiculo AS 'Año',
        Color_Vehiculo AS 'Color'
    FROM VEHICULOS
GO

--Listar Vehiculos 
CREATE OR ALTER PROCEDURE PA_Listar_Vehiculos
AS
BEGIN
    SELECT *
    FROM Vw_Listar_Vehiculos
END

