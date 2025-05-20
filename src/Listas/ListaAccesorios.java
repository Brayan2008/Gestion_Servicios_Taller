package Listas;
import java.util.ArrayList;
import Model.Accesorios;

public class ListaAccesorios {
    ArrayList<Accesorios> tablaAccesorio = new ArrayList<Accesorios>();

    void setAccesorio(String id, String accesorio){
        tablaAccesorio.add(new Accesorios(id, accesorio));
    }

    void getAccesorios(){
        for (int i = 0; i < tablaAccesorio.size(); i++){
            System.out.println(tablaAccesorio.get(i));
        }
    }
    
}
