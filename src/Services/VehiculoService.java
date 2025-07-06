package Services;
import Services.templates.ConnectionBD;
import javax.swing.table.DefaultTableModel;
public class VehiculoService  {
    private final String cadenaConexion = ConnectionBD.URL;
    private final String usuario = ConnectionBD.USER;
    private final String clave = ConnectionBD.PASSWORD;
    public static DefaultTableModel modelo;
    private String[] head = { "Placa", "Marca", "Modelo", "Color","Chasis","Numero de motor", "Año", "Color" };

}
