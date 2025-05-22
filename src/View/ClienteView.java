package View;

import java.util.Scanner;

import Model.Cliente;
import Services.ClienteService;
import View.interfaces.ConsoleTools;

public class ClienteView implements ConsoleTools {

    private static final ClienteService cs = new ClienteService();
    private static Scanner lector = new Scanner(System.in);

    public ClienteView() {
        String opcion;
        do {
            limpiarPantalla();
            imprimirMenu();
            opcion = lector.nextLine();
            switch (opcion) {
                case "1" -> {
                    limpiarPantalla();
                    verListaClientes();
                }
                case "2" -> registrarCliente();
                case "3" -> cambiarDatosCliente();
                case "4" -> eliminarDatos();
                case "5" -> System.out.println("Goodbye!");
                default -> System.out.println("Opcion no valida");
            }
        } while (!opcion.equals("5"));
        limpiarPantalla();
        printlnInfo("Saliendo del modulo Cliente ...");
    }

    private void imprimirMenu() {
        System.out.println(BOLD + CURVE + "--------------- GESTION DE CLIENTES ------------------- ");
        System.out.println(" 1. Ver lista de clientes ");
        System.out.println(" 2. Registrar un nuevo cliente ");
        System.out.println(" 3. Modificar un cliente");
        System.out.println(" 4. Eliminar un cliente");
        System.out.println(" 5. Salir del modulo (exit) ");
        System.out.println("\nSeleccione una opcion valida:" + DEFAULT + "\n".repeat(10));
    }

    public void verListaClientes() {
        printlnTitle_Green("************ Lista de Clientes *************");
        if (cs.getList().isEmpty()) {
            System.out.println("No hay datos aun\n");
        }
        cs.getList().forEach((a, b) -> imprimirCliente(a, b));
        printlnInfo("Presione Enter para continuar");
        lector.nextLine();
    }

    public void registrarCliente() {
        limpiarPantalla();
        printlnTitle_Cyan("************* BIENVENIDO AL MODULO PARA REGISTRAR CLIENTES ************* ");
        printlnInfo("\nPresione Enter para continuar");
        lector.nextLine();
        
        printlnInstruction("Ingrese el Tipo de documento\n1.- DNI \n2.- RUC\nSeleccione solo una opcion (1-2) o 3 para salir");
        int tip_documento = lector.nextInt();
        lector.nextLine();

        String ID = RegistrarID(lector, tip_documento);

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
        if (cs.create(new Cliente(ID, nombre, telefono, direccion, tip_documento, distrito))) {
            printlnInfo("\n¡El registro fue exitoso!\n");
        } else {
            printlnInfo("Ups, ya existe un cliente con el ID ingresado");
        }
        lector.nextLine();
    }

    public void cambiarDatosCliente() {
        limpiarPantalla();
        printlnTitle_Cyan("******** BIENVENIDO AL MODULO PARA MODIFICAR CLIENTES ********");
        printlnInstruction("A continuacion se mostrara la lista de clientes registrados: \n");
        verListaClientes();
        printlnInfo("Seleccione a un cliente por su ID: ");
        String dni_buscar = lector.nextLine();
        // ---
        Cliente cliente_find = cs.getByID(dni_buscar);
        if (cliente_find != null) {
            printlnInfo("\nCliente encontrado");
            printlnInstruction("-".repeat(20) + "ID: " + cliente_find.getNroCliente() + "-".repeat(20));
            printInstruction("Nombre (Actual): " + cliente_find.getNombre() + "\tNombre (Modificacion): ");
            String nombre = lector.nextLine();
            printInstruction("Telefono (Actual): " + cliente_find.getTelefono() + "\tTelefono (Modificacion): ");
            String tel = lector.nextLine();
            printInstruction("Direccion (Actual): " + cliente_find.getDireccion() + "\tDireccion (Modificacion): ");
            String direccion = lector.nextLine();
            printInstruction("Distrito (Actual): " + cliente_find.getDistritoCliente() + "\tDistrito (Modificacion): ");
            String distrito = lector.nextLine();
            printlnInfo("Presione Enter para guardar");
            Cliente cliente_actualizado = new Cliente(dni_buscar, nombre, tel, direccion,
                    cliente_find.getTipoDocumento(), distrito);
            cs.put(cliente_actualizado, cliente_find.getNroCliente());
            printlnInfo("Se modifico correctamente");
        } else {
            printlnError("El usuario no existe, intentelo de nuevo");
            lector.nextLine();
        }
        // ---
    }

    public void eliminarDatos() {
        limpiarPantalla();
        printlnTitle_Red("****** WARMING: USTED A ENTRADO AL MODULO PARA ELIMINAR CLIENTES *******");
        printlnInstruction("Presione 1 si desea continuar");
        String button = lector.nextLine();
        if (button.equals("1")) {
            printlnInstruction("A continuacion se muestra la lista de clientes: \n");
            verListaClientes();
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

    public void imprimirCliente(String a, Cliente b) {
        System.out.println("-".repeat(40));
        System.out.println("ID: " + a);
        System.out.println("Tipo de documento: " + b.getTipoDocumento());
        System.out.println("Nombre: " + b.getNombre());
        System.out.println("Telefono: " + b.getTelefono());
        System.out.println("Direccion: " + b.getDireccion());
        System.out.println("Distrito: " + b.getDistritoCliente());
        System.out.println("-".repeat(40) + "\n");
    }

    public String RegistrarID(Scanner lector,int tipo_documento) {
        String id = "";
        int i = 2;
        do {
            switch (tipo_documento) {
                case 1:
                    printlnInstruction("Ingrese el DNI (8 digitos): ");
                    id = lector.nextLine();
                    if (id.length() != 8 || !id.matches("[0-9]{8}")) {
                        printlnError(RED + "El DNI ingresado no es correcto. (" + i + " de 3 opotunidades.)" + DEFAULT);
                        id = ""; 
                        i--;
                    } else {
                        i = -1;
                    }
                    break;
                case 2:
                    printlnInstruction("Ingrese el RUC (11 digitos): ");
                    id = lector.nextLine();
                    if (id.length() != 11 || !id.matches("[0-9]{11}")) {
                        printlnError(RED + "El RUC ingresado no es correcto. (" + i + " de 3 opotunidades.)" + DEFAULT);
                        id = "";
                        i--;
                    } else {
                        i = -1;
                    }
                    break;
                case 3:
                    i = -1;
                    break;
                default:
                    printlnError("Error, tipo de documento no soportado");
                    i = -1;
                    break;
            }
        } while (i != -1);
        return id;
    }

}
