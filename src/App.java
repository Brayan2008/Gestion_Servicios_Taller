import Controllers.ControladorServicios;
import java.util.Scanner;
public class App {
    

    public static void main(String[] args) throws Exception {
        ControladorServicios serviciosController = new ControladorServicios();
        System.out.println("Hello, World!");

        System.out.println("Nuevo servicio: ");
        serviciosController.createServicio("01", "Cambio de aceite", 10);
        serviciosController.createServicio("02", "Parchado de llanta", 5);

        serviciosController.readServicio();

    }

}
