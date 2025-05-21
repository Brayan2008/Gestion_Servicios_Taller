package Services;

import java.util.HashMap;

import Model.Cliente;
import Services.interfaces.CRUD;

public class ClienteService implements CRUD<Cliente>{
    
    public final static HashMap<String, Cliente> lista_cliente = new HashMap<>();
    
    @Override
    public Cliente getByID(String id) {
        return lista_cliente.get(id);
    }

    @Override
    public HashMap<String, Cliente> getList() {
        return lista_cliente;
    }

    @Override
    public boolean create(Cliente cliente) {
        var estado = (lista_cliente.get(cliente.getNroCliente())!=null);
        if (estado) {
            return false;
        } else {
            lista_cliente.put(cliente.getNroCliente(), cliente);
            return true;
        }
    }

    @Override
    public boolean put(Cliente cliente, String id) {
        if (cliente.getNombre() == null ||
                cliente.getDireccion() == null ||
                cliente.getDireccion() == null ||
                cliente.getDistritoCliente() == null ||
                cliente.getTelefono() == null) {
            //System.out.println("Error, faltan campos");
            return false;
        }
        
        if (lista_cliente.get(id).equals(null)) {
           // System.out.println("Error no existe el cliente");
            return false;
        }
        
        lista_cliente.put(id, cliente);
       // System.out.println("El cliente se registro correctamente");
        return true;
    }

    @Override
    public boolean delete(String id) {
        var estado = lista_cliente.get(id).equals(null);
        if (estado) {
         //   System.out.println("Error, ya existe el cliente");
            return false;
        }
        lista_cliente.remove(id);
        return true;
    }

    
}
