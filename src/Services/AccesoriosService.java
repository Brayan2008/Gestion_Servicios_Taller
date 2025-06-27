package Services;

import Model.Accesorios;
import Services.templates.Service;

/*public class AccesoriosService extends Service<Accesorios> {
    
    public boolean IDvacia(Accesorios user) {
        if (user.getID().isBlank() || user.getID().isEmpty()) {
            return true;   
        }
        return false;   
    }

}
*/
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import Model.Accesorios; // Asegúrate de que esta ruta coincide con tu paquete real


public class AccesoriosService {
    private Connection con;

    public AccesoriosService() {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://ANDY-DESKTOP\\SQLEXPRESS:1433;databaseName=TALLER;encrypt=false",
            "sa", "sa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Accesorios> obtenerAccesorios() {
        List<Accesorios> lista = new ArrayList<>();
        try {
            CallableStatement cs = con.prepareCall("{call SP_Accesorios (?, ?, ?)}");
            cs.setNull(1, Types.NVARCHAR);
            cs.setNull(2, Types.NVARCHAR);
            cs.setString(3, "mostrar");

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Accesorios a = new Accesorios();
                a.setID(rs.getString("Cod_Accesorio"));
                a.setNombreAccesorio(rs.getString("Nom_Accesorio"));
                lista.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void guardarAccesorio(Accesorios a) {
        try {
            CallableStatement cs = con.prepareCall("{call SP_Accesorios (?, ?, ?)}");
            cs.setString(1, a.getID());
            cs.setString(2, a.getNombreAccesorio());
            cs.setString(3, "guardar");
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

