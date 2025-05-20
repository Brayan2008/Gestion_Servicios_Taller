package Controllers;

import java.util.HashMap;

import Controllers.interfaces.CRUD;
import Model.Cliente;

public class ControladorCliente implements CRUD<Cliente> {

    HashMap<String, Cliente> lista_cliente = new HashMap<>();

    @Override
    public void create(Cliente cliente) {
        var estado = (lista_cliente.get(cliente.getNroCliente()).equals(null));
        if (estado) {
            System.out.println("Error, ya existe el cliente");
            return;
        } else{
            lista_cliente.put(cliente.getNroCliente(), cliente);    
            System.out.println("Se agrego el cliente exitosamente");
            return;
        }
    }
    
    @Override
    public void put(Cliente cliente, String id) {
        if (cliente.getNombre() == null || 
        cliente.getDireccion() == null || 
        cliente.getDireccion() == null ||
        cliente.getDistritoCliente() == null ||
        cliente.getTelefono() == null) {   
            System.out.println("Error, faltan campos");
            return;
        }       
        if (lista_cliente.get(id).equals(null)) {
            System.out.println("Error no existe el cliente");
            return;
        }
        lista_cliente.put(id, cliente);
        System.out.println("El cliente se registro correctamente");
    }

    @Override
    public boolean delete(String id) {
        var estado = lista_cliente.get(id).equals(null);
        if (estado) {
            System.out.println("Error, ya existe el cliente");
            return false;
        }
        lista_cliente.remove(id);
        return true;
    }


}
