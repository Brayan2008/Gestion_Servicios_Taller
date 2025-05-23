package Services;

import Model.Usuarios;
import Services.interfaces.Service;;


public class UsuariosService extends Service<Usuarios> {
    /**
     * Valida el ingreso de datos en el "Iniciar Sesion"
     * @param usuario El usuario ingresado
     * @return <code> true</code> si los campos no son vacios y acerto la contraseña, 
     * de lo contrario <code> false </code>
     */
    public boolean validarLogin(Usuarios usuario) {
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
