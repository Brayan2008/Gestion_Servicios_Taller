package Services;

import java.math.BigDecimal;
import java.sql.*;

import javax.swing.table.DefaultTableModel;

public class CatalogoServiciosService {

    private final String cadenaConexion = "jdbc:sqlserver://DESKTOP-CTAAIJA\\SQLMISSAEL:1433;databaseName=TALLER;encrypt=false";
    private final String usuario = "sa"; // cámbialo
    private final String clave = "123456789"; // cámbialo
    public static DefaultTableModel modelo;
    private String[] head = { "Código", "Nombre del Servicio", "Precio del servicio" };

    public DefaultTableModel listarServicios() {
        // Creamos un DefaultTableModel anónimo
        modelo = new DefaultTableModel(head, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // <- esto evita la edición en toda la tabla
            }
        };

        try (Connection con = DriverManager.getConnection(cadenaConexion, usuario, clave);
                CallableStatement cs = con.prepareCall("{call PA_CRUD_ListarServicios}")) {

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("Codigo");
                String nombre = rs.getString("Nombre del Servicio");
                double precio = rs.getDouble("Precio del servicio");
                
                modelo.addRow(new Object[] { codigo, nombre, precio });
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public void insertarServicio(String codser, String nomser, double precser) throws Exception {
        String sql = "{call PA_CRUD_InsertarServicio(?, ?, ?)}";
        
        try (Connection con = DriverManager.getConnection(cadenaConexion, usuario, clave);
        CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, codser);
            cs.setString(2, nomser);
            cs.setBigDecimal(3, BigDecimal.valueOf(precser));
            
            cs.execute();            
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }        
    }
    
    public void buscarCliente(String cadena) throws Exception {
        String sql = "{call PA_FiltrarServicio(?)}";
        
        try (Connection con = DriverManager.getConnection(cadenaConexion, usuario, clave);
        CallableStatement cs = con.prepareCall(sql)) {            
            cs.setString(1, cadena);
            cs.execute();            
            
            ResultSet rs = cs.executeQuery();
            modelo.setRowCount(0);

            while (rs.next()) {
                String codigo = rs.getString("Cod_Servicio");
                String nombre = rs.getString("Nom_Servicio");
                double precio = rs.getDouble("Precio_Servicio");          
                modelo.addRow(new Object[] { codigo, nombre, precio });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
}