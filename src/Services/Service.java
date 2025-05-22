package Services;

import java.util.HashMap;

import Services.interfaces.CRUD;

public abstract class Service<T> implements CRUD<T>{
    
    public final HashMap<String, T> lista = new HashMap<>();
    
    @Override
    public T getByID(String id) {
        return lista.get(id);
    }

    @Override
    public HashMap<String, T> getList() {
        return lista;
    }

    @Override
    public boolean create(String id, T T) {
        if (lista.containsKey(id)) {
            return false;
        } else {
            lista.put(id, T);
            return true;
        }
    }

    @Override
    public boolean put(T T, String id) {
        if (lista.containsKey(id)) {
            lista.put(id, T);
            return true;
        }
        return false;
                
    }

    @Override
    public boolean delete(String id) {
        if (lista.containsKey(id)) {
            lista.remove(id);
            return true;
        }
        return false;
    }

    
}
