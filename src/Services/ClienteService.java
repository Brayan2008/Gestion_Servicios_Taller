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
        if (lista_cliente.containsKey(cliente.getNroCliente())) {
            return false;
        } else {
            lista_cliente.put(cliente.getNroCliente(), cliente);
            return true;
        }
    }

    @Override
    public boolean put(Cliente cliente, String id) {
        if (lista_cliente.containsKey(id)) {
            lista_cliente.put(id, cliente);
            return true;
        }
        return false;
                
    }

    @Override
    public boolean delete(String id) {
        if (lista_cliente.containsKey(id)) {
            lista_cliente.remove(id);
            return true;
        }
        return false;
    }

    
}
