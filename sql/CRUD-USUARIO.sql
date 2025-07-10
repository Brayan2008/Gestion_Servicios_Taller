
	CREATE TABLE Usuarios(
    idUser char(8) not null,
    Contrase�a varchar(50) not null,
    Nombre varchar(50) not null,
    CONSTRAINT PK_Usuario PRIMARY KEY (idUser) 
)
GO

CREATE OR ALTER PROCEDURE PA_Buscar_Usuario(@idUser char(8))
AS
BEGIN
    IF NOT EXISTS(SELECT *
    from USUARIOS
    WHERE idUser = @idUser) BEGIN
        RAISERROR('Error en las credenciales',16,1)
        RETURN @@ERROR
    END

    SELECT Contrase�a, Nombre
    FROM USUARIOS
    WHERE idUser = @idUser
END 
GO

--Insertar
CREATE OR ALTER PROCEDURE PA_Crear_Usuario
    (@idUser char(8),
    @Nombre varchar(50),
    @Contrase�a varchar(50)
)
AS
BEGIN
    IF EXISTS (SELECT *
    FROM USUARIOS
    WHERE idUser = @idUser)
        BEGIN
        RAISERROR('El Cliente ya se encuentra registrado', 16, 1)
        RETURN @@ERROR
    END

    -- Insertar el veh�culo
    INSERT INTO USUARIOS
    VALUES
        (@idUser, @Contrase�a, @Nombre)
END
GO
--ACTUALIZAR
CREATE OR ALTER PROCEDURE PA_Actualizar_Usuario(
    @idUser char(8),
    @Nombre varchar(50),
    @Contrase�a VARCHAR(50)
)
AS
BEGIN
    IF NOT EXISTS (SELECT *
    FROM USUARIOS
    WHERE idUser = @iduser)
        BEGIN
        RAISERROR('Usuario no encontrado', 16, 1)
        RETURN @@ERROR
    END

    UPDATE USUARIOS
    SET Contrase�a = @Contrase�a, Nombre = @Nombre
    WHERE idUser = @idUser

END
GO

--Eliminar
CREATE OR ALTER PROCEDURE PA_Eliminar_Usuario(
    @iduser CHAR(8)
)
AS
BEGIN
    IF NOT EXISTS (SELECT *
    FROM USUARIOS
    WHERE idUser = @iduser)
        BEGIN
        RAISERROR('Usuario no encontrado', 16, 1)
        RETURN @@ERROR
    END

    DELETE FROM USUARIOS WHERE (idUser = @iduser)
END
GO

--Vista de vehiculos
CREATE OR ALTER VIEW Vw_Listar_Usuarios
AS
    SELECT Nombre AS 'Nombre',
        idUser AS 'Codigo'
    FROM USUARIOS
GO

--Listar Vehiculos 
CREATE OR ALTER PROCEDURE PA_Listar_Usuarios
AS
BEGIN
    SELECT *
    FROM Vw_Listar_Usuarios
END