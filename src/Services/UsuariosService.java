package Services;

import Model.Usuarios;
import Services.templates.Service;;


public class UsuariosService extends Service<Usuarios> {
    /**
     * Valida el ingreso de datos en el "Iniciar Sesion"
     * @param usuario El usuario ingresado
     * @return <code> true</code> si los campos no son vacios y acerto la contraseña, 
     * de lo contrario <code> false </code>
     */
    public boolean validarLogin(Usuarios usuario) {
        if (CamposVacios(usuario)) {
            return false;    
        }

        var usuario_find = getByID(usuario.getID());
        

        if (usuario_find != null && usuario_find.getContraseña().equals(usuario.getContraseña())) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CamposVacios(Usuarios user) {
        if (user.getContraseña().isEmpty() || user.getContraseña().isBlank() || IDvacia(user)) {
            return true;
        }
        return false;
    }


    //------------------
    public boolean IDvacia(Usuarios user) {
        if (user.getID().isBlank() || user.getID().isEmpty()) {
            return true;   
        }
        return false;   
    }

}
