package Services;
import java.sql.*;

import javax.swing.table.DefaultTableModel;

import Services.templates.ConnectionBD;

public class AccesoriosService {

    private final String cadenaConexion = ConnectionBD.URL;
    private final String usuario = ConnectionBD.USER;
    private final String clave = ConnectionBD.PASSWORD;
    public static DefaultTableModel modelo;
    private String[] head = { "Código", "Nombre del Accesorio"};

    public DefaultTableModel listarServicios() {
        // Creamos un DefaultTableModel anónimo
        modelo = new DefaultTableModel(head, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // <- esto evita la edición en toda la tabla
            }
        };

        try (Connection con = DriverManager.getConnection(cadenaConexion, usuario, clave);
                CallableStatement cs = con.prepareCall("{call PA_CRUD_ListarAccesorios}")) {

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("Codigo");
                String nombre = rs.getString("Nombre del Accesorio");
                
                
                modelo.addRow(new Object[] { codigo, nombre});
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public void insertarServicio(String codser, String nomser) throws Exception {
        String sql = "{call PA_CRUD_InsertarAccesorio(?, ?)}";
        
        try (Connection con = DriverManager.getConnection(cadenaConexion, usuario, clave);
        CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, codser);
            cs.setString(2, nomser);
            
            cs.execute();            
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }        
    }
    
    public void buscarCliente(String cadena) throws Exception {
        String sql = "{call PA_FiltrarAccesorio(?)}";
        
        try (Connection con = DriverManager.getConnection(cadenaConexion, usuario, clave);
        CallableStatement cs = con.prepareCall(sql)) {            
            cs.setString(1, cadena);
            cs.execute();            
            
            ResultSet rs = cs.executeQuery();
            modelo.setRowCount(0);

            while (rs.next()) {
                String codigo = rs.getString("Cod_Accesorio");
                String nombre = rs.getString("Nom_Accesorio");          
                modelo.addRow(new Object[] { codigo, nombre});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
}
