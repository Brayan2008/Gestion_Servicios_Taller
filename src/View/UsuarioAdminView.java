package View;

import Model.Usuarios;
import Services.UsuariosService;
import View.utils.ConsoleViews;

public class UsuarioAdminView extends ConsoleViews<Usuarios> {

    protected static final UsuariosService us = new UsuariosService();

    public String opcion = "";
    public boolean eliminacion;

    public UsuarioAdminView() {
        t = new Usuarios();
    }

    @Override
    public void init() {
        opcion = "";
        eliminacion = false;
        while (!opcion.equals("4")) {
            limpiarPantalla();
            imprimirMenu();
            opcion = lector.nextLine();
            switch (opcion) {
                case "1" -> {
                    limpiarPantalla();
                    verLista();
                }
                case "2" -> eliminarDatos();
                case "3" -> cambiarDatos();
                case "4" -> {
                    printlnInfo("SALIO DE USUARIO ADMIN");
                    lector.nextLine();
                }
                default -> {
                    printlnError("ERROR: Opcion no valida");
                    lector.nextLine();
                }
            }
        }
    }

    @Override
    public void imprimirMenu() {
        System.out.println(BOLD + CURVE + "--------------- GESTION DE USUARIOS ------------------- \n");
        System.out.println(" 1. Ver mi usuario (Perfil)");
        System.out.println(" 2. Eliminar mi usuario");
        System.out.println(" 3. Modificar mi usuario");
        System.out.println(" 4. Salir del modulo (exit) ");
        System.out.println("\nSeleccione una opcion valida:" + DEFAULT + "\n".repeat(10));
    }

    @Override
    public void verLista() {
        printlnTitle_Green("************ MI USUARIO *************");
        System.out.println(us.getByID(LoginView.token_ID).toString());
        printlnInfo("\nPresione Enter para continuar");
        lector.nextLine();
    }

    @Override
    protected void eliminarDatos() {
        limpiarPantalla();
        printlnTitle_Red("****** WARMING: USTED A ENTRADO AL MODULO PARA ELIMINAR SU USUARIO *******");
        printlnInstruction("Presione 1 si desea continuar");
        String button = lector.nextLine();
        if (button.equals("1")) {
            printlnTitle_Red("Esto cerrará su sesión. Presione 1 nuevamente si esta seguro");
            button = lector.nextLine();
            if (button.equals("1")) {
                    printlnTitle_Red("SE ELIMINO CORRECTAMENTE");
                    us.delete(LoginView.token_ID);
                    lector.nextLine();
                    eliminacion = true; //Cierra todo en el App
                    opcion = "4"; //Sale de este menu
                    return;
            }
        }

        printlnInfo("\nPresione ENTER para volver");
        lector.nextLine();

    }

    @Override
    protected void cambiarDatos() {
        limpiarPantalla();
        printlnTitle_Cyan("******** BIENVENIDO AL MODULO PARA MODIFICAR SU USUARIO ********");
        printlnInfo("\n Presione ENTER para continuar");
        lector.nextLine();

        Usuarios user_find = us.getByID(LoginView.token_ID);

        while (true) {
            printlnInstruction("\nSeleccione un campo que desea modificar");
            System.out.println(CURVE + "\n1. Nombre de Usuario");
            System.out.println("2. Contraseña\n" + DEFAULT);
            String campos = lector.nextLine();
            switch (campos) {
                case "1":
                    printlnInstruction("\nIngrese su nuevo nombre: ");
                    String nombre = lector.nextLine();
                    user_find.setNombre(nombre);
                    break;
                case "2":
                    printlnInstruction("\nIngrese su nueva contraseña: ");
                    String contraseña = lector.nextLine();
                    if (contraseña.isBlank() || contraseña.isEmpty()) {
                        printlnError("La contraseña no puede estar vacia");
                        break;
                    }
                    user_find.setContraseña(contraseña);
                    break;
                default:
                    printlnError("OPCION NO VALIDA");
                    break;
            }
            printInstruction("Desea salir (s/n): ");
            String opcion = lector.nextLine();
            if (opcion.equalsIgnoreCase("s")) {
                printlnInfo("Presione ENTER para salir");
                lector.nextLine();
                break;
            }
        }
    }

}