package Listas;
import java.util.HashMap;
import Model.Accesorios;

public class ListaAccesorios {
    HashMap<Integer, Accesorios> tablaAccesorio = new HashMap<Integer, Accesorios>();
    void setAccesorio(Accesorios accesorio){
        tablaAccesorio.put(1, accesorio);
    }

    void getAccesorios(){
        for (int i = 0; i < tablaAccesorio.size(); i++){
            System.out.println(tablaAccesorio.get(i));
        }
    }
    
}
