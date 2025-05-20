package Controllers;
import java.util.ArrayList;
import Model.Catalogo_Servicios;
import Services.ServicesServicios;

public class ControladorServicios {
    ArrayList<Catalogo_Servicios> listaServicios = new ArrayList<Catalogo_Servicios>();
    
    public void createServicio(String codigo, String servicio, float precio) {
        ServicesServicios.validarExitencia(listaServicios, servicio);
        listaServicios.add(new Catalogo_Servicios(codigo, servicio, precio));
    }

    public void readServicio(){
       System.out.println(listaServicios);
    }


    public void updateServicio(String id){

    }
}
