package View;

import Model.Mecanico;
import Services.MecanicoService;
import View.utils.ConsoleViews;

public class MecanicoView extends ConsoleViews<Mecanico> {
    
    private final MecanicoService mc = new MecanicoService();

    public MecanicoView() {
        t = new Mecanico();
    }

    @Override
    protected void verLista() {
        printlnTitle_Green("********* Lista de Mecanicos********");
        if (mc.getList().isEmpty()) {
            System.out.println("No se encontraron mecanicos registrados\n");
        }
        mc.getList().forEach((key, map) -> System.out.println(map.toString()));
        printlnInfo("Presione Enter para continuar");
        lector.nextLine();
    }

    @Override
    protected void registrar() {
        limpiarPantalla();
        printlnTitle_Cyan("********* BIEN VENIDO AL MODULO PARA REGISTRAR MECANICOS *********");
        printlnInfo("\nPresione Enter para continuar");

        String ID = mc.RegistrarID(1);

        if (ID.isEmpty()) {
            printlnInfo("Presione Enter para regresar al menu");
            lector.nextLine();
            return;
        }

        printlnInstruction("Ingrese el Nombre Completo del Mecanico ");
        String nombre = lector.nextLine();
        printlnInstruction("Ingrese su Telefono");
        String telefono = lector.nextLine();
        printlnInstruction("Ingrese la direccion del mecanico");
        String direccion = lector.nextLine();

        if (mc.create(ID, new Mecanico(nombre, telefono, direccion, ID))) {
            printlnInfo("\n¡El registro fue exitoso!\n");
        } else {
            printlnInfo("Ups, ya existe un mecanico con el DNI ingresado");
        }
        lector.nextLine();
    }

    @Override
    protected void cambiarDatos() {
        limpiarPantalla();
        printlnTitle_Cyan("******** BIENVENIDO AL MODULO PARA MODIFICAR MECANICOS ********");
        printlnInstruction("A continuacion se mostrara la lista de mecanicos registrados: \n");
        verLista();
        printlnInfo("Seleccione a un mecanico por su ID: ");
        String dni_buscar = lector.nextLine();

        Mecanico mecanico_find = mc.getByID(dni_buscar);
        mc.ValidarBusqueda(mecanico_find, dni_buscar);
    }

}
