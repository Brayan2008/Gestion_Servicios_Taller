package Services;
import java.util.ArrayList;
import Model.Catalogo_Servicios;

public class ServicesServicios {
    public static void validarExitencia(ArrayList<Catalogo_Servicios> lista, String servicio){
        for(Catalogo_Servicios existente : lista) {
            if (existente.getNomServicio().equals(servicio)) {
                System.out.println("El servicio: " + servicio + " ya existe");
            }
        }
    }
    
}
