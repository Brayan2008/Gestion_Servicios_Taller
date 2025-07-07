package Services.templates;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de conectar a una base de datos.
 */
public abstract class Service{
   
    private static final String CONNECTION_URL = ConnectionBD.URL;
    private static final String USERNAME = ConnectionBD.USER;
    private static final String PASSWORD = ConnectionBD.PASSWORD;
 
    public static Connection puntero = null;
    
    public static void getConnection() {
        try {
            puntero = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            puntero.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}