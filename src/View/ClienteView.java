package View;

import Model.Cliente;
import Services.ClienteService;
import View.utils.ConsoleViews;

public class ClienteView extends ConsoleViews<Cliente>{
    
    private final ClienteService cs = new ClienteService();

    public ClienteView() {
        t = new Cliente();
    }

    @Override
    protected void verLista() {
        printlnTitle_Green("************ Lista de Clientes *************");
        if (cs.getList().isEmpty()) {
            System.out.println("No hay datos aun\n");
        }
        cs.getList().forEach((key, map) -> System.out.println(map.toString()));
        printlnInfo("Presione Enter para continuar");
        lector.nextLine();
    }

    @Override
    protected void registrar() {
        limpiarPantalla();
        printlnTitle_Cyan("************* BIENVENIDO AL MODULO PARA REGISTRAR CLIENTES ************* ");
        printlnInfo("\nPresione Enter para continuar");
        lector.nextLine();
        
        printlnInstruction("Ingrese el Tipo de documento\n1.- DNI \n2.- RUC\nSeleccione solo una opcion (1-2) o 3 para salir");
        int tip_documento = lector.nextInt();
        lector.nextLine();

        String ID = cs.RegistrarID(tip_documento);

        if (ID.isEmpty()) {
            printlnInfo("Presione Enter para regresar al menu");
            lector.nextLine();
            return;
        }

        printlnInstruction("Ingrese el Nombre Completo del Cliente ");
        String nombre = lector.nextLine();
        printlnInstruction("Ingrese su Telefono");
        String telefono = lector.nextLine();
        printlnInstruction("Ingrese la direccion del cliente");
        String direccion = lector.nextLine();
        printlnInstruction("Ingrese el distrito del cliente");
        String distrito = lector.nextLine();

        // --
        if (cs.create(ID, new Cliente(ID, nombre, telefono, direccion, tip_documento, distrito))) {
            printlnInfo("\n¡El registro fue exitoso!\n");
        } else {
            printlnInfo("Ups, ya existe un cliente con el ID ingresado");
        }
        lector.nextLine();
    }

    @Override
    protected void cambiarDatos() {
        limpiarPantalla();
        printlnTitle_Cyan("******** BIENVENIDO AL MODULO PARA MODIFICAR CLIENTES ********");
        printlnInstruction("A continuacion se mostrara la lista de clientes registrados: \n");
        verLista();
        printlnInfo("Seleccione a un cliente por su ID: ");
        String dni_buscar = lector.nextLine();

        Cliente cliente_find = cs.getByID(dni_buscar);
        cs.ValidarBusqueda(cliente_find, dni_buscar);
    }

    @Override
    protected void eliminarDatos() {
        limpiarPantalla();
        printlnTitle_Red("****** WARMING: USTED A ENTRADO AL MODULO PARA ELIMINAR CLIENTES *******");
        printlnInstruction("Presione 1 si desea continuar");
        String button = lector.nextLine();
        if (button.equals("1")) {
            printlnInstruction("A continuacion se muestra la lista de clientes: \n");
            verLista();
            printlnInfo("Seleccione a un cliente por su ID para " + BOLD + BG_RED + " ELIMINARLO: ");
            String dni_buscar = lector.nextLine();
            if (cs.delete(dni_buscar)) {
                printlnError(BOLD + "\nSe elimino correctamente");
            } else {
                printlnError("\nEl cliente no existe");
            }
        }
        printlnInfo("\nPresione Enter para volver al menu");
        lector.nextLine();
    }
}
