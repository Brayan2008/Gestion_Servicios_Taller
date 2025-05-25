package View;

import Model.Usuarios;
import Services.UsuariosService;
import View.utils.ConsoleViews;

public class LoginView extends ConsoleViews<Usuarios>{
    
    UsuariosService us = UsuarioAdminView.us; //Solo puede haber una instancia de Usuarios Service (SOLO UNA LISTA DE USUARIOS)
    public boolean salir = false;
    public String opcion = "";
    public static String token_ID = "";
    
    public LoginView() {
        t = new Usuarios();
    }

    @Override
    public void init() {
        while (!opcion.equals("3")) {
            limpiarPantalla();
            imprimirMenu(); 
            opcion =lector.nextLine();   
            switch (opcion) {
                case "1" -> iniciarSesion();
                case "2" -> registrar();
                case "3" -> {printlnInfo("GOODBYE");
                            lector.nextLine();
                            salir = true;}
                default -> {printlnError("Opcion no valida");
                            lector.nextLine();
                            }
            }
            if(salir) break;
        }
    }

    @Override
    public void imprimirMenu() {
        System.out.println(BOLD + CURVE + "-".repeat(30) + " SISTEMA DE GESTION DE SERVICIOS " + "-".repeat(40));
        System.out.println("1. Iniciar Sesion ");
        System.out.println("2. Registrate ");
        System.out.println("3. Salir del programa" + DEFAULT + "\n".repeat(18));
    }

    @Override
    public void registrar() {
        limpiarPantalla();
        printlnTitle_Cyan("*".repeat(6) + "REGISTRATE" + "*".repeat(6));
        printlnInstruction("\nEscriba su ID de usuario");
        String ID_user = lector.nextLine();
        printlnInstruction("Ingrese su Nombre");
        String nombre = lector.nextLine();
        printlnInstruction("Escriba su contraseña");
        String password = lector.nextLine();
        printlnInstruction("Escriba de nuevo su contraseña");
        String password2 = lector.nextLine();

        if(!password.equals(password2)) {
            printlnError("\nLas contraseñas no coinciden"); 
            lector.nextLine();
            return;
        }

        Usuarios new_user = new Usuarios(ID_user,nombre, password);
        if (!us.CamposVacios(new_user) && us.create(ID_user, new_user)) {
            printlnInfo("Se creo correctamente");  
        } else{
            printlnError("ERROR: Los campos estan vacios, o el cliente ya existe. Intentelo de nuevo" );
        }
        printlnInfo("Presione ENTER para continuar");
        lector.nextLine();
    }

    public void iniciarSesion() {
        limpiarPantalla();

        printInstruction("Ingrese su usuario: ");
        String user_id = lector.nextLine();

        printInstruction("Ingrese su contraseña: ");
        String password = lector.nextLine();

        Usuarios user = new Usuarios(user_id, null, password);
        
        if(us.validarLogin(user)){
            printlnInfo("\nINGRESASTE CORRECTAMENTE\n");
            token_ID = user_id;
            salir = true;    
        } else {
            printlnError("Error en las credenciales");
        }
        
        printlnInfo("Presione Enter para continuar");
        lector.nextLine();
    }

}