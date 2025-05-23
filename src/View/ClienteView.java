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

        String ID = RegistrarID(tip_documento);

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
        if (cliente_find != null) {

            printlnInfo("\nCliente encontrado");
            printlnInstruction("-".repeat(20) + "ID: " + cliente_find.getID() + "-".repeat(20));
            
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
            cs.put(cliente_actualizado, cliente_find.getID()); //Siempre devolvera verdadero 
            printlnInfo("Se modifico correctamente");
        } else {
            printlnError("El usuario no existe, intentelo de nuevo");
            lector.nextLine();
        }
        // ---
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

    private String RegistrarID(int tipo_documento) {
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
