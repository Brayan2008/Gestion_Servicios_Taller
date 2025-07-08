package Services;

import java.sql.*;

import javax.swing.table.DefaultTableModel;


import Services.templates.Service;

public class Mecanico1Service extends Service {

    public static DefaultTableModel modelo;
    private String[] head = { "Codigo", "Nombre Completo", "Telefono", "Direccion"};

    public DefaultTableModel listarMecanicos() {
        // Creamos un DefaultTableModel anónimo
        modelo = new DefaultTableModel(head, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // <- esto evita la edición en toda la tabla
            }
        };

        try (CallableStatement cs = puntero.prepareCall("{call PA_CRUD_ListarMecanico}")) {

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("Codigo");
                String nombre = rs.getString("Nombre Completo");
                String telefono = rs.getString("Telefono");
                String direccion = rs.getString("Direccion");

                modelo.addRow(new Object[] { codigo, nombre, telefono, direccion});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public void insertarMecanico(String codmec, String nommec, String telemec, String dirmec) throws Exception {
        String sql = "{call PA_CRUD_InsertarMecanico(?, ?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {

            cs.setString(1, codmec);
            cs.setString(2, nommec);
            cs.setString(3, telemec);
            cs.setString(4, dirmec);

            cs.execute();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    public void buscarMecanico(String cadena) throws Exception {
        String sql = "{call PA_FiltrarMecanico(?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, cadena);
            cs.execute();

            ResultSet rs = cs.executeQuery();
            modelo.setRowCount(0);

            while (rs.next()) {
                String codigo = rs.getString("Cod_Mecanico");
                String nombre = rs.getString("Nom_Mecanico");
                String telefono = rs.getString("Telef_Mecanico");
                String direccion = rs.getString("Direc_Mecanico");
                modelo.addRow(new Object[] { codigo, nombre, telefono, direccion});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarMecanico(String codmec, String nommec, String telemec, String dirmec) throws SQLException {
        String sql = "{call PA_CRUD_ModificarMecanico(?, ?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, codmec);
            cs.setString(2, nommec);
            cs.setString(3, telemec);
            cs.setString(4, dirmec);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }

    }

    public void eliminarMecanico(String codmec) throws SQLException {
        String sql = "{call PA_CRUD_EliminarMecanico(?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, codmec);
            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }
}
