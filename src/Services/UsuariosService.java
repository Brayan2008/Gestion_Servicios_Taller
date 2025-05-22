package Services;

import java.util.HashMap;

import Model.Usuarios;
import Services.interfaces.CRUD;

public class UsuariosService implements CRUD<Usuarios> {

    private static final HashMap<String, Usuarios> lista_users = new HashMap<>();

    @Override
    public Usuarios getByID(String id) {
        if (lista_users.containsKey(id)) {
            return lista_users.get(id);
        } else {
            return null;
        }
    }

    @Override
    public HashMap<String, Usuarios> getList() {
        return lista_users;
    }

    @Override
    public boolean create(String id, Usuarios usuarios) {
        if (usuarios.getID().isEmpty() || usuarios.getContraseña().isEmpty() || 
            usuarios.getID().isBlank() || usuarios.getContraseña().isEmpty()) {
            return false;
        }

         if (lista_users.containsKey(usuarios.getID())) {
            return false;
        } else {
            lista_users.put(usuarios.getID(), usuarios);
            return true;
        }
    }
    

    @Override
    public boolean put(Usuarios t, String id) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (lista_users.containsKey(id)) {
            lista_users.remove(id);
            return true;
        }
        return false;
    }

    public boolean validar(Usuarios usuario) {
        var usuario_find = getByID(usuario.getID());
        
        if (usuario.getID().isEmpty() || usuario.getContraseña().isEmpty() ||
            usuario.getID().isBlank() || usuario.getContraseña().isBlank()) {
            return false;
        }

        if (usuario_find != null && usuario_find.getContraseña().equals(usuario.getContraseña())) {
            return true;
        } else {
            return false;
        }

    }

}
