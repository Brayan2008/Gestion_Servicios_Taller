package View;

import Model.Accesorios;
import Services.AccesoriosService;
import View.utils.ConsoleViews;

public class AccesorioView extends ConsoleViews<Accesorios> {

    private final AccesoriosService as = new AccesoriosService();

    public AccesorioView() {
        t = new Accesorios();
    }

    @Override
    protected void registrar() {
        limpiarPantalla();
        printlnTitle_Cyan("************* BIENVENIDO AL MODULO PARA REGISTRAR ACCESORIOS ************* ");
        printlnInfo("\nPresione Enter para continuar");
        lector.nextLine();
        printlnInstruction("Ingrese el Codigo del Accesorio ");
        String codigo = lector.nextLine();
        printlnInstruction("Ingrese el Nombre del Accesorio ");
        String nombre = lector.nextLine();        

        Accesorios ac= new Accesorios(codigo, nombre);
        
        if (!as.IDvacia(ac) && as.create(codigo,ac)) {
            printlnInfo("\n¡El registro fue exitoso!\n");
        } else {
            printlnInfo("Ups, ya existe un Accesorio con el ID ingresado");
        }
        lector.nextLine();    
    }

    @Override
    protected void verLista() {
        printlnTitle_Green("************ Lista de Accesorios *************");
        if (as.getList().isEmpty()) {
            System.out.println("No hay datos aún\n");
        }
        as.getList().forEach((key, map) -> System.out.println(map.toString()));
        printlnInfo("Presione Enter para continuar");
        lector.nextLine();
    }
    
    @Override
    protected void cambiarDatos() {
        limpiarPantalla();
        printlnTitle_Cyan("******** BIENVENIDO AL MODULO PARA MODIFICAR ACCESORIOS ********");
        printlnInstruction("A continuacion se mostrara la lista de accesorios registrados: \n");
        verLista();
        printlnInfo("Seleccione un accesorio por su ID: ");
        String accesorio_buscar = lector.nextLine();

        Accesorios accesorio_find = as.getByID(accesorio_buscar);
        if (accesorio_find != null) {

            printlnInfo("\nAccesorio encontrado");
            printlnInstruction("-".repeat(20) + "ID: " + accesorio_find.getID() + "-".repeat(20));
            
            printInstruction("Nombre (Actual): " + accesorio_find.getNombreAccesorio() + "\tNombre (Modificacion): ");
            String nombre = lector.nextLine(); 
            
            printlnInfo("Presione Enter para guardar");
        
            Accesorios accesorio_actualizado = new Accesorios(accesorio_buscar, nombre);
            as.put(accesorio_actualizado, accesorio_find.getID()); //Siempre devolvera verdadero 
            printlnInfo("Se modifico correctamente");
        } else {
            printlnError("El accesorio no existe, intentelo de nuevo");
            lector.nextLine();
        }
        // ---
    }
    
    @Override
    protected void eliminarDatos() {
        limpiarPantalla();
        printlnTitle_Red("****** WARMING: USTED A ENTRADO AL MODULO PARA ELIMINAR ACCESORIOS *******");
        printlnInstruction("Presione 1 si desea continuar");
        String button = lector.nextLine();
        if (button.equals("1")) {
            printlnInstruction("A continuacion se muestra la lista de accesorios: \n");
            verLista();
            printlnInfo("Seleccione a un accesorio por su ID para " + BOLD + BG_RED + " ELIMINARLO: ");
            String accesorio_buscar = lector.nextLine();
            if (as.delete(accesorio_buscar)) {
                printlnError(BOLD + "\nSe elimino correctamente");
            } else {
                printlnError("\nEl accesorio no existe");
            }
        }
        printlnInfo("\nPresione Enter para volver al menu");
        lector.nextLine();

    }  
}
