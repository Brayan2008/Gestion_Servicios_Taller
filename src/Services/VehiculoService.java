package Services;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import Services.templates.Service;

public class VehiculoService extends Service {
    public static DefaultTableModel modelo;
    private String[] head = { "Placa", "Marca", "Modelo","Chasis","Numero de motor", "Año", "Color" };

    public DefaultTableModel listarVehiculos() {
        modelo = new DefaultTableModel(head, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try (CallableStatement cs = puntero.prepareCall("{call PA_Vehiculo_Obtener}")) {
            
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String placa = rs.getString("Placa");
                String marca = rs.getString("Marca");
                String modeloVehiculo = rs.getString("Modelo");
                String chasis = rs.getString("Chasis");
                String numMotor = rs.getString("Motor");
                int anio = rs.getInt("Año");
                String color = rs.getString("Color");

                modelo.addRow(new Object[] { placa, marca, modeloVehiculo, chasis, numMotor, anio, color});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public void insertarVehiculo(String placa, String marca, String modelo, String chasis, String motor, int año, String color) throws Exception {
        String sql = "{call PA_Vehiculo_Agregar(? ,?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)){
            cs.setString(1, placa);
            cs.setString(2, marca);
            cs.setString(3, modelo);
            cs.setString(4, chasis);
            cs.setString(5, motor);
            cs.setInt(6, año);
            cs.setString(7, color);

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public void buscarVehiculo(String cadena) throws Exception {
        String sql = "{call PA_Vehiculo_Filtrar(?)}";
        
        try (CallableStatement cs = puntero.prepareCall(sql)) {            
            cs.setString(1, cadena);
            cs.execute();            
            
            ResultSet rs = cs.executeQuery();
            modelo.setRowCount(0); // Limpiar tabla

            while (rs.next()) {
                String placa = rs.getString("Placa_Vehiculo");
                String marca = rs.getString("Marca_Vehiculo");
                String modeloVehiculo = rs.getString("Modelo_Vehiculo");
                String chasis = rs.getString("Mod_Chasis");
                String numMotor = rs.getString("Num_motor");
                int anio = rs.getInt("Año_Vehiculo");
                String color = rs.getString("Color_Vehiculo");
                
                modelo.addRow(new Object[] { placa, marca, modeloVehiculo, chasis, numMotor, anio, color });
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    public void actualizarVehiculo(String placa, String marca, String modelo, String chasis, String motor, int año, String color) throws SQLException {
        String sql = "{call PA_Vehiculo_Update(?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, placa);
            cs.setString(2, marca);
            cs.setString(3, modelo);
            cs.setString(4, chasis);
            cs.setString(5, motor);
            cs.setInt(6, año);
            cs.setString(7, color);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

    public void eliminarVehiculo(String placaVeh) throws SQLException {
        String sql = "{call PA_Vehiculo_Eliminar(?)}";

        try (CallableStatement cs = puntero.prepareCall(sql)) {
            cs.setString(1, placaVeh);
            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }
}
