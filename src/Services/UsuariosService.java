package Services;

import java.util.HashMap;

import Model.Usuarios;
import Services.interfaces.CRUD;

public class UsuariosService implements CRUD<Usuarios> {

    private static final HashMap<String, Usuarios> lista_users = new HashMap<>();

    @Override
    public Usuarios getByID(String id) {
        return lista_users.get(id);
    }

    @Override
    public HashMap<String, Usuarios> getList() {
        return lista_users;
    }

    @Override
    public boolean create(Usuarios usuarios) {
         if (lista_users.containsKey(usuarios.getNombre())) {
            return false;
        } else {
            lista_users.put(usuarios.getNombre(), usuarios);
            return true;
        }
    }

    @Override
    public boolean put(Usuarios t, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    public boolean delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    

}
