import View.ClienteView;
import View.LoginView;
import View.UsuarioAdminView;
import View.interfaces.ConsoleViews;

public class App extends ConsoleViews<App> {

    LoginView l1 = new LoginView();
    ClienteView c1 = new ClienteView();
    UsuarioAdminView u1 = new UsuarioAdminView();
    
    // Para pruebas aqui

    public static void main(String[] args) throws Exception {
        /*
         * ControladorServicios serviciosController = new ControladorServicios();
         * System.out.println("Hello, World!");
         * 
         * System.out.println("Nuevo servicio: ");
         * serviciosController.createServicio("01", "Cambio de aceite", 10);
         * serviciosController.createServicio("02", "Parchado de llanta", 5);
         * 
         * serviciosController.readServicio();
         */

        App a1 = new App();
        a1.init();        
        lector.close();
    }
    
    @Override
    public void init() {

        String opcion = "";
        boolean estado = false;
        //No saldra hasta que seleccion tres
        while (true) {        

            //Iniciar sesion
            while (!estado) {
                l1.init();
                estado = l1.salir;
            }
            
            //Si selecciona 3 ("Salir") termina el programa
            if (l1.opcion.equals("3")) break;   
            
            //Menu principal
            opcion = ""; //Reiniciamos la opcion

            //Cierra secion con 3
            while (!opcion.equals("3")) {
                imprimirMenu();
                opcion = lector.nextLine();
                switch (opcion) {
                    case "1" -> c1.init();
                    case "2" -> u1.init();
                    case "3" -> {printlnInfo("SESION CERRADA");
                                estado = false;
                                lector.nextLine();}
                    default -> {printlnError("ERROR");
                                lector.nextLine();}
                }
            }

        }
    }
    
    @Override
    public void imprimirMenu() {
        limpiarPantalla();
        System.out.println(CURVE + BOLD + "-".repeat(40) + "BIENVENIDO: " + "-".repeat(40) +"\n");
        System.out.println("1.- Modulo de Cliente");
        System.out.println("2.- Modulo de Usuarios");
        System.out.println("3.- Cerrar Sesion" + DEFAULT + "\n\nSeleccione una opcion" + "\n".repeat(20));    
    }
    



}
