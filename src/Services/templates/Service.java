package Services.templates;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Clase que implementa un CRUD y se encarga de la logica comun de crear,
 * obtener y
 * actualizar y eliminar valores. Ademas crea una paquete por cada clase hija
 * del que sea
 * extendida
 */
public abstract class Service<T> implements CRUD<T> {

    protected T t;
   
    private static final String CONNECTION_URL = "jdbc:sqlserver://DESKTOP-CTAAIJA\\SQLMISSAEL:1433;databaseName=BIBLIOTECA;encrypt=false";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456789";
    private static Connection puntero = null;

   protected String nombreTabla;
   protected Field[] campos; //El id de cada entidad debe ser el primer atributo declarado

    public void init() {
        nombreTabla = t.getClass().getSimpleName();
        campos = t.getClass().getDeclaredFields();
    }
    
    protected static void getConnection() {
        try {
            puntero = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void closeConnection() {
        try {
            puntero.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getByID(String id) {
        String id_object = campos[0].getName();
        String query = "SELECT * FROM " + nombreTabla + " WHERE " + id_object + "= ?";
        try (PreparedStatement paquete = puntero.prepareStatement(query)) {
            paquete.setString(1, id);
            ResultSet result = paquete.executeQuery();
            
            if (result.next()) {
                T objeto = (T) t.getClass().getConstructor().newInstance(); //Creo una instancia del objeto
                for (Field field : campos) {
                    field.setAccessible(true); //Los private se hacen public xd
                    field.set(objeto, result.getObject(field.getName())); // Cada campo con su campo de la tabla
                }
                return objeto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<T> getList() {
        String query = "SELECT * FROM " + nombreTabla;
            ArrayList<T> lista = new ArrayList<>();

            try (PreparedStatement paquete = puntero.prepareStatement(query)) {
                var result = paquete.executeQuery();

                while (result.next()) {
                    T objeto = (T) t.getClass().getDeclaredConstructor().newInstance();
                    for (Field campo : campos) {
                        campo.setAccessible(true);
                        campo.set(objeto, result.getObject(campo.getName())); 
                    }
                    lista.add(objeto); 
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return lista; 
    }

    @Override
    public boolean create(String id, T TA) {
        if (getByID(id) != null) {return false;}
        
        Field[] copy_campos = campos;
        Arrays.sort(copy_campos, Comparator.comparing((c)-> c.getName()));

        String query = "INSERT INTO " + nombreTabla +"(";
        for (int i = 0; i < copy_campos.length; i++) {
            if (i == copy_campos.length-1) {
                query += copy_campos[i].getName() + ") VALUES (" + "?,".repeat(copy_campos.length-1) + "?)";
                break;
            }
            query += copy_campos[i].getName() + ",";
        }

        try (PreparedStatement paquete = puntero.prepareStatement(query)) {

            Method[] metodos = TA.getClass().getDeclaredMethods();
            Arrays.sort(metodos, Comparator.comparing((m)-> m.getName())); // getter > setter

            for (int i = 1; i <= copy_campos.length; i++) {
                copy_campos[i-1].setAccessible(true);
                Object valor = copy_campos[i-1].get(TA);
                String tipo = metodos[i-1].getReturnType().getSimpleName();
                System.out.println(metodos[i-1].toString());
                System.out.println(tipo);
                switch (tipo.toLowerCase()) {
                    case "string" -> paquete.setString(i, valor.toString());
                    case "integer" -> paquete.setInt(i, Integer.parseInt(valor.toString()));
                    case "datetime" -> paquete.setDate(i, Date.valueOf(LocalDate.parse(valor.toString())));
                    case "float" -> paquete.setFloat(i, Float.parseFloat(valor.toString()));
                    case "boolean" -> paquete.setBoolean(i, Boolean.getBoolean(valor.toString())); 
                    default -> throw new SQLDataException("Tipo de dato no soportado");
                }
            
            }

            return paquete.executeUpdate() == 1;                         

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    
    //aun en desarrollo
    @Override
    public boolean put(T T, String id) {
        var object = getByID(id); 
        if (object == null) return false;
        Field[] campos_copy = campos;
        Arrays.sort(campos_copy,Comparator.comparing((v) -> v.getName()));
        
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (getByID(id) == null) return false;
        String sql = "DELETE FROM " + nombreTabla + " WHERE " + campos[0].getName() + "= ?";
        try (PreparedStatement paquete = puntero.prepareStatement(sql)) {
            paquete.setInt(1, Integer.parseInt(id));
            paquete.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;

    }
}
