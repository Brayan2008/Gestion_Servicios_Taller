package Services;

import Services.templates.ConnectionBD;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import Services.templates.Service;

public class OrdenService extends Service{
    private final String cadenaConexion = ConnectionBD.URL;
    private final String usuario = ConnectionBD.USER;
    private final String clave = ConnectionBD.PASSWORD;
    public static DefaultTableModel modelo;
    private String[] head = { "Nro. Orden", "Fecha", "Kilometraje","Fecha entregada","Numero de combustible", "Observacion", "Tarjeta", "Manual", "Llave", "Estado", "Quiñado", "Rayado", "Abollado","Documento", "Nro. Documento", "Mecánico", "Placa Vehiculo" };

    public DefaultTableModel listarOrdenes() {
        modelo = new DefaultTableModel(head, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try (CallableStatement cs = puntero.prepareCall("{call PA_Orden_Obtener}")) {
            
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String codigoOrden = rs.getString("codigo de orden");
                String fechaOrden = rs.getString("Fecha de la Orden");
                double kilometraje = rs.getDouble("kilometraje");
                String numCombustible = rs.getString("cantidad de combustible");
                String observacion = rs.getString("observacion general");
                boolean tarjeta = rs.getBoolean("Tarjeta de propiedad");
                boolean manual = rs.getBoolean("Manuel de propietario");
                boolean llave = rs.getBoolean("llave del vehiculo");
                int estado = rs.getInt("Estado de la orden");
                boolean quiniado = rs.getBoolean("Quiñado");
                boolean rayado = rs.getBoolean("Rayado");
                boolean abollado = rs.getBoolean("Abollado");
                String dniMecanico = rs.getString("DNI Mecanico");
                String nroCliente = rs.getString("Doc. Cliente");
                String placaVehiculo = rs.getString("Placa");

                modelo.addRow(new Object[] { 
                    codigoOrden, fechaOrden, kilometraje, "", numCombustible, observacion, 
                    tarjeta ? "Sí" : "No", manual ? "Sí" : "No", llave ? "Sí" : "No", 
                    estado, quiniado ? "Sí" : "No", rayado ? "Sí" : "No", abollado ? "Sí" : "No",
                    "", nroCliente, dniMecanico, placaVehiculo
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public void insertarOrden(String codOrden, String fechaOrden, double kilometraje, 
                             String fechaEntrega, double numCombustible, String observacion,
                             boolean tarjeta, boolean manual, boolean llave, int estadoOrden,
                             boolean quiniado, boolean rayado, boolean abollado,
                             String nroCliente, boolean tipoDocumento, String codMecanico, 
                             String placaVehiculo) throws Exception {
        String sql = "{call PA_Orden_Agregar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, codOrden);
            cs.setDate(2, java.sql.Date.valueOf(fechaOrden));
            cs.setDouble(3, kilometraje);
            cs.setDate(4, java.sql.Date.valueOf(fechaEntrega));
            cs.setDouble(5, numCombustible);
            cs.setString(6, observacion);
            cs.setBoolean(7, tarjeta);
            cs.setBoolean(8, manual);
            cs.setBoolean(9, llave);
            cs.setInt(10, estadoOrden);
            cs.setBoolean(11, quiniado);
            cs.setBoolean(12, rayado);
            cs.setBoolean(13, abollado);
            cs.setString(14, nroCliente);
            cs.setBoolean(15, tipoDocumento);
            cs.setString(16, codMecanico);
            cs.setString(17, placaVehiculo);

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public void actualizarOrden(String codOrden, String fechaOrden, double kilometraje, 
                               String fechaEntrega, double numCombustible, String observacion,
                               boolean tarjeta, boolean manual, boolean llave, int estadoOrden,
                               boolean quiniado, boolean rayado, boolean abollado,
                               String nroCliente, boolean tipoDocumento, String codMecanico, 
                               String placaVehiculo) throws SQLException {
        String sql = "{call PA_Orden_Update(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, codOrden);
            cs.setDate(2, java.sql.Date.valueOf(fechaOrden));
            cs.setDouble(3, kilometraje);
            cs.setDate(4, java.sql.Date.valueOf(fechaEntrega));
            cs.setDouble(5, numCombustible);
            cs.setString(6, observacion);
            cs.setBoolean(7, tarjeta);
            cs.setBoolean(8, manual);
            cs.setBoolean(9, llave);
            cs.setInt(10, estadoOrden);
            cs.setBoolean(11, quiniado);
            cs.setBoolean(12, rayado);
            cs.setBoolean(13, abollado);
            cs.setString(14, nroCliente);
            cs.setBoolean(15, tipoDocumento);
            cs.setString(16, codMecanico);
            cs.setString(17, placaVehiculo);
            
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

    public void eliminarOrden(String codOrden) throws SQLException {
        String sql = "{call PA_Orden_Eliminar(?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, codOrden);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

    public void buscarOrden(String cadena) throws Exception {
        String sql = "{call PA_FiltrarOrden(?)}";
        modelo = new DefaultTableModel(head, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, cadena);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next()) {
                String codigoOrden = rs.getString("codigo de orden");
                String fechaOrden = rs.getString("Fecha de la Orden");
                double kilometraje = rs.getDouble("kilometraje");
                String numCombustible = rs.getString("cantidad de combustible");
                String observacion = rs.getString("observacion general");
                boolean tarjeta = rs.getBoolean("Tarjeta de propiedad");
                boolean manual = rs.getBoolean("Manuel de propietario");
                boolean llave = rs.getBoolean("llave del vehiculo");
                int estado = rs.getInt("Estado_Orden");
                boolean quiniado = rs.getBoolean("Quiñado");
                boolean rayado = rs.getBoolean("Rayado");
                boolean abollado = rs.getBoolean("Abollado");
                String dniMecanico = rs.getString("DNI Mecanico");
                String nroCliente = rs.getString("Nro_Cliente");
                String placaVehiculo = rs.getString("Placa_Vehiculo");

                modelo.addRow(new Object[] { 
                    codigoOrden, fechaOrden, kilometraje, "", numCombustible, observacion, 
                    tarjeta ? "Sí" : "No", manual ? "Sí" : "No", llave ? "Sí" : "No", 
                    estado, quiniado ? "Sí" : "No", rayado ? "Sí" : "No", abollado ? "Sí" : "No",
                    "", nroCliente, dniMecanico, placaVehiculo
                });
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }
}
