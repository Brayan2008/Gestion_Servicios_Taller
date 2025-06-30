package Services;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class CatalogoServiciosService {

    private final String cadenaConexion = "jdbc:sqlserver://ANDY-DESKTOP\\SQLEXPRESS:1433;databaseName=TALLER;encrypt=false";
    private final String usuario = "sa"; // cámbialo
    private final String clave = "sa";     // cámbialo

    public DefaultTableModel listarServicios() {
        // Creamos un DefaultTableModel anónimo
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Código", "Nombre del Servicio", "Precio del servicio"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // <- esto evita la edición en toda la tabla
            }
        };

        try (Connection con = DriverManager.getConnection(cadenaConexion, usuario, clave);
            CallableStatement cs = con.prepareCall("{call PA_CRUD_ListarServicios}")) {

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("Codigo");
                String nombre = rs.getString("Nombre del Servicio");
                double precio = rs.getDouble("Precio del servicio");
                modelo.addRow(new Object[]{codigo, nombre, precio});
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
        // para propagar el error del RAISERROR al JOptionPane
        throw new Exception(e.getMessage());
    }
}

}
