package Listas;
import java.util.ArrayList;
import Model.Cliente;

public class ListaClientes {
    ArrayList<Cliente> tablaCliente = new ArrayList<Cliente>();

    void setCliente(String nombre, String telefono, String direccion, String nroCliente, int tipoDocumento, String distritoCliente){
        tablaCliente.add(new Cliente(nombre, telefono, direccion, nroCliente, tipoDocumento, distritoCliente));
    }

    void getCliente(){
        for (int i = 0; i < tablaCliente.size(); i++){
            System.out.println(tablaCliente.get(i));
        }
    }
}
