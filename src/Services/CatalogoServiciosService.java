package Services;

import java.math.BigDecimal;
import java.sql.*;

import javax.swing.table.DefaultTableModel;

import Services.templates.Service;

public class CatalogoServiciosService extends Service {

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

        try (CallableStatement cs = puntero.prepareCall("{call PA_CRUD_ListarServicios}")) {
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

        try (CallableStatement cs = puntero.prepareCall(sql)) {

            cs.setString(1, codser);
            cs.setString(2, nomser);
            cs.setBigDecimal(3, BigDecimal.valueOf(precser));

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public void buscarCliente(String cadena) throws Exception {
        String sql = "{call PA_FiltrarServicio(?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, cadena);
            cs.execute();

            ResultSet rs = cs.executeQuery();
            modelo.setRowCount(0); // Reinicia la tabla

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

    public void actualizarCliente(String codser, String nomser, double precser) throws SQLException {
        String sql = "{call PA_CRUD_ModificarServicio(?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, codser);
            cs.setString(2, nomser);
            cs.setDouble(3, precser);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }

    }
    
    public void eliminarCliente(String codser) throws SQLException {
        String sql = "{call PA_CRUD_EliminarServicio(?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, codser);
            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

}