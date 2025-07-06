package Services;
import Services.templates.ConnectionBD;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AccesoriosServices{

    private final String cadenaConexion = ConnectionBD.URL;
    private final String usuario = ConnectionBD.USER;
    private final String clave = ConnectionBD.PASSWORD;

    public DefaultTableModel listarAccesorios() {
        // Creamos un DefaultTableModel anónimo
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Código", "Nombre del Accesorio"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // <- esto evita la edición en toda la tabla
            }
        };

        try (Connection con = DriverManager.getConnection(cadenaConexion, usuario, clave);
            CallableStatement cs = con.prepareCall("{call PA_CRUD_ListarAccesorios}")) {

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("Codigo");
                String nombre = rs.getString("Nombre del Accesorio");
                modelo.addRow(new Object[]{codigo, nombre});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }
}
