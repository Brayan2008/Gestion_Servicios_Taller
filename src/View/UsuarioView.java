package View;

import Services.UsuariosService;
import View.interfaces.ConsoleTools;

public class UsuarioView implements ConsoleTools{
    
    private static final UsuariosService us = new UsuariosService();

    public UsuarioView(){
        printlnInfo("Hola desde usuario");
    }


}
