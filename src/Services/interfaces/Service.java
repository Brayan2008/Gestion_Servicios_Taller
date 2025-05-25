package Services.interfaces;

import java.util.HashMap;

import Model.Usuarios;

/**
 * Clase que implementa un CRUD y se encarga de la logica comun de crear, obtener y
 * actualizar y eliminar valores. Ademas crea una lista por cada clase hija del que sea
 * extendida
 */
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

    public boolean IDvacia(Usuarios user) {
        if (user.getID().isBlank() || user.getID().isEmpty()) {
            return true;   
        }
        return false;   
    }

    
}
