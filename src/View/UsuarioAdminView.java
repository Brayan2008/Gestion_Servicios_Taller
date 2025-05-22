package View;

import Model.Usuarios;
import Services.UsuariosService;
import View.interfaces.ConsoleViews;

public class UsuarioAdminView extends ConsoleViews<Usuarios>{
    
    private static final UsuariosService us = new UsuariosService();

    public UsuarioAdminView() {
        t = new Usuarios();
    }
    
    @Override
    public void init() {
        String opcion = "";
        while (!opcion.equals("3")) {
            limpiarPantalla();
            imprimirMenu();
            opcion = lector.nextLine();
            switch (opcion) {
                case "1" -> {
                    limpiarPantalla();
                    verLista();
                }
                case "2" -> eliminarDatos();
                case "3" -> {printlnInfo("SALIO DE USUARIO ADMIN");
                            lector.nextLine();}
                default -> {printlnError("ERROR: Opcion no valida");
                            lector.nextLine();}
            }
        }
    }

    @Override
    public void imprimirMenu() {
        System.out.println(BOLD + CURVE + "--------------- GESTION DE USUARIOS ------------------- \n");
        System.out.println(" 1. Ver lista de usuarios");
        System.out.println(" 2. Eliminar un usuario");
        System.out.println(" 3. Salir del modulo (exit) ");
        System.out.println("\nSeleccione una opcion valida:" + DEFAULT + "\n".repeat(10));
    }

    @Override
    public void verLista() {
        printlnTitle_Green("************ Lista de Usuarios *************");
        if (us.getList().isEmpty()) {
            System.out.println("No hay datos aun\n");
        }

        us.getList().forEach((a, b) -> imprimirObjeto(a, b));

        printlnInfo("Presione Enter para continuar");
        lector.nextLine();        
    }
 

    @Override
    public void imprimirObjeto(String key, Usuarios map) {
        System.out.println("-".repeat(30));
        System.out.println("ID: " + key);
        System.out.println("Nombre: " + map.getNombre());
        System.out.println("-".repeat(30));
    }
    
}