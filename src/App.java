import View.AccesorioView;
import View.ClienteView;
import View.LoginView;
import View.MecanicoView;
import View.UsuarioAdminView;
import View.VehiculoView;
import View.utils.ConsoleViews;
import View.CatalogoServiciosView;

public class App extends ConsoleViews<App> {

    LoginView l1 = new LoginView();
    ClienteView c1 = new ClienteView();
    AccesorioView A1 = new AccesorioView();
    VehiculoView v1 = new VehiculoView();
    UsuarioAdminView u1 = new UsuarioAdminView();
    MecanicoView m1 = new MecanicoView();
    CatalogoServiciosView cs1 = new CatalogoServiciosView();
    
    // Para pruebas aqui

    public static void main(String[] args) throws Exception {
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

            //Cierra session con 5
            while (!opcion.equals("7")) {
                imprimirMenu();
                opcion = lector.nextLine();
                switch (opcion) {
                    case "1" -> c1.init();
                    case "2" -> A1.init();
                    case "3" -> v1.init(); 
                    case "4" -> m1.init();
                    case "5" -> cs1.init();
                    case "6" -> {u1.init();
                                if(u1.eliminacion) {
                                    System.out.println("Saliste si eliminaste");
                                    estado = false;
                                    l1.salir = false;
                                    opcion = "7";
                                }
                                }
                    case "7" -> {printlnInfo("SESION CERRADA");
                                estado = false; 
                                l1.salir = false;
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
        System.out.println("2.- Modulo de Accesorios");
        System.out.println("3.- Modulo de Vehiculos");
        System.out.println("4.- Modulo de Mecanicos");
        System.out.println("5.- Modulo de Servicios");
        System.out.println("6.- Configurar mi perfil");
        System.out.println("7.- Cerrar Sesion" + DEFAULT + "\n\nSeleccione una opcion" + "\n".repeat(20));    
    }
}
