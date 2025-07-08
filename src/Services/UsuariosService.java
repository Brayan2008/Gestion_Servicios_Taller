package Services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.table.DefaultTableModel;

import Services.templates.Service;;

public class UsuariosService extends Service {

    public static DefaultTableModel modelo;
    private String[] head = {"ID", "Nombre"};
    public String contra, nombre;

    public UsuariosService() {
        ExecutorService hilo = Executors.newFixedThreadPool(2);
        hilo.submit(() -> {
            Service.getConnection();
            System.out.println( Thread.currentThread() + " " + System.currentTimeMillis());
        });
        hilo.shutdown();
    
        System.out.println( Thread.currentThread() + " " + System.currentTimeMillis());
    }
    

    public DefaultTableModel listarMecanicos() {
        // Creamos un DefaultTableModel anónimo
        modelo = new DefaultTableModel(head, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // <- esto evita la edición en toda la tabla
            }
        };

        try (CallableStatement cs = puntero.prepareCall("{call PA_CRUD_Listar_Usuarios}")) {

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("Codigo");
                String nombre = rs.getString("Nombre");

                modelo.addRow(new Object[] {codigo, nombre});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public void insertarUsuario(String idUser, String nombre, String contraseña) throws Exception {
        String sql = "{call PA_Crear_Usuario(?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, idUser);
            cs.setString(2, nombre);
            cs.setString(3, contraseña);

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public void buscarUsuario(String cadena) throws Exception {
        String sql = "{call PA_Buscar_Usuario(?)}";
        
        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, cadena);
            cs.execute();
            
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                contra = rs.getString("Contraseña");
                nombre = rs.getString("Nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(String idUser, String nombre, String Contraseña) throws SQLException {
        String sql = "{call PA_Actualizar_Usuario(?,?,?)}";
        
        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, idUser);
            cs.setString(2, nombre);
            cs.setString(3, Contraseña);
            
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }

    }

    public void eliminarUsuario(String idUser) throws SQLException {
        String sql = "{call PA_Eliminar_Usuario(?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, idUser);
            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }
}
