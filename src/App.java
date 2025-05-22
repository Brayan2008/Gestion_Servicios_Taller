import java.util.Scanner;

import View.ClienteView;
import View.UserView;
import View.UsuarioView;

public class App {

    //Para pruebas aqui
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
        ClienteView c1;
        UsuarioView u1;
        Scanner sc = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("Bienvenido");
            System.out.println("1.- Cliente");
            System.out.println("2.- Usuarios");
            System.out.println("3.- Salir");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1" -> c1 = new ClienteView();            
                case "2" -> u1 = new UsuarioView();            
                default -> System.out.println("Error");
            }
        } while (!opcion.equals("3"));
    
    
    }

}
