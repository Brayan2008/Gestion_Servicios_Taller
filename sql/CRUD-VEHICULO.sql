
CREATE OR ALTER PROCEDURE PA_Vehiculo_Agregar (
	@placa AS CHAR(7),
	@marca AS VARCHAR(20),
	@modelo AS VARCHAR(30),
	@chasis AS VARCHAR(17),
	@numMotor AS VARCHAR(25),
	@anioVehiculo AS SMALLINT,
	@color AS VARCHAR(20)
) 
AS BEGIN 
IF EXISTS (SELECT Placa_Vehiculo FROM VEHICULOS WHERE Placa_Vehiculo = @placa) BEGIN
	RAISERROR('La placa ya se encuentra registrada', 16,1)
	RETURN @@ERROR
	END
ELSE
	INSERT INTO dbo.VEHICULOS(Placa_Vehiculo, Marca_Vehiculo, Modelo_Vehiculo, Mod_Chasis, Num_Motor, Año_Vehiculo, Color_Vehiculo)
	VALUES (@placa, @marca, @modelo, @chasis, @numMotor, @anioVehiculo, @color)
END
GO

CREATE OR ALTER PROCEDURE PA_Vehiculo_Update(
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
	IF NOT EXISTS (SELECT * FROM VEHICULOS WHERE Placa_Vehiculo = @placa)
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

CREATE OR ALTER PROCEDURE PA_Vehiculo_Eliminar(
	@placa AS VARCHAR(7)
)
AS 
BEGIN
	IF NOT EXISTS (SELECT * FROM VEHICULOS WHERE Placa_Vehiculo = @placa)
        BEGIN
            RAISERROR('El vehículo no está registrado', 16, 1)
            RETURN @@ERROR
        END

	IF EXISTS (SELECT * FROM ORDEN WHERE Placa_Vehiculo = @placa)
		BEGIN
			RAISERROR('No se puede eliminar un vehículo que ya posee alguna Orden registrada', 16, 1)
			RETURN @@ERROR
		END

	DELETE FROM VEHICULOS WHERE (Placa_Vehiculo = @placa)
END
GO

CREATE OR ALTER VIEW Vw_Vehiculo_Mostrar
AS
	SELECT	Placa_Vehiculo AS 'Placa',
			Marca_Vehiculo AS 'Marca',
			Modelo_Vehiculo AS 'Modelo',
			Mod_Chasis AS 'Chasis',
			Num_Motor AS 'Motor',
			Año_Vehiculo AS 'Año',
			Color_Vehiculo AS 'Color'
	FROM VEHICULOS;
GO

CREATE or alter PROCEDURE PA_Vehiculo_Obtener
	AS
	BEGIN
	SELECT * FROM Vw_Vehiculo_Mostrar
	END
GO

CREATE OR ALTER VIEW Vw_Vehiculo_Filtrados AS
	SELECT Placa_Vehiculo, Modelo_Vehiculo, Marca_Vehiculo FROM VEHICULOS
GO

CREATE OR ALTER PROC PA_Vehiculo_Filtrar (@Cadena varchar(50)) AS
	BEGIN 
		SELECT * FROM Vw_Vehiculo_Filtrados 
		WHERE (Placa_Vehiculo+Modelo_Vehiculo+Marca_Vehiculo) LIKE ('%'+@Cadena +'%')
	END
GO
