package Services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Services.templates.Service;

public class ClienteService extends Service {

    public static DefaultTableModel modelo;
    private String[] head = { "Codigo", "Tipo de documento", "Nombre", "Telefono", "Dirección", "Distrito" };

    public DefaultTableModel listarClientes() {
        modelo = new DefaultTableModel(head, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try (CallableStatement cs = puntero.prepareCall("{call PA_Listar_Cliente}")) {

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String Nro = rs.getString("Codigo");
                Boolean Tipo = rs.getBoolean("Tipo Documento");
                String Nombre = rs.getString("Nombre");
                String telefono = rs.getString("Telefono");
                String direccion = rs.getString("Direccion");
                String distrito = rs.getString("Distrito");
                if (Tipo == true) {
                    modelo.addRow(new Object[] { Nro, "DNI", Nombre, telefono, direccion, distrito });
                } else {
                    modelo.addRow(new Object[] { Nro, "RUC", Nombre, telefono, direccion, distrito });
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public void insertarClientes(String Nro_Cliente, Boolean Tipo_Documento, String Nombre, String Telefono,
            String Direccion, String Distrito) throws Exception {
        String sql = "{call PA_CREAR_CLIENTE(?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {

            cs.setString(1, Nro_Cliente);
            cs.setBoolean(2, Tipo_Documento);
            cs.setString(3, Nombre);
            cs.setString(4, Telefono);
            cs.setString(5, Direccion);
            cs.setString(6, Distrito);

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public void buscarCliente(String cadena) throws Exception {
        String sql = "{call PA_FiltrarCliente(?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, cadena);
            cs.execute();

            ResultSet rs = cs.executeQuery();
            modelo.setRowCount(0);

            while (rs.next()) {
                String Nro = rs.getString("Nro_Cliente");
                Boolean Tipo = rs.getBoolean("Tipo_Documento");
                String Nombre = rs.getString("Nom_Cliente");
                String telefono = rs.getString("Telf_Cliente");
                String direccion = rs.getString("Direc_Cliente");
                String distrito = rs.getString("Distri_Cliente");
                if (Tipo == true) {
                    modelo.addRow(new Object[] { Nro, "DNI", Nombre, telefono, direccion, distrito });
                } else {
                    modelo.addRow(new Object[] { Nro, "RUC", Nombre, telefono, direccion, distrito });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCliente(String nro_cliente, 
                                  boolean tipo_documento, 
                                  String nombre, 
                                  String telefono,
                                  String direccion,
                                  String distrito) throws SQLException {
        
        String sql = "{call PA_ALTERAR_CLIENTE(?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, nro_cliente);
            cs.setBoolean(2, tipo_documento);
            cs.setString(3, nombre);
            cs.setString(4, telefono);
            cs.setString(5, direccion);
            cs.setString(6, distrito);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }

    }

    public void eliminarCliente(String nro_cliente, boolean tipo_documento) throws SQLException {
        String sql = "{call PA_ELIMINAR_CLIENTE(?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, nro_cliente);
            cs.setBoolean(2, tipo_documento);
            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

}
