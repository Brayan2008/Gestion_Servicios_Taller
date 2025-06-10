package View;

import Model.Catalogo_Servicios;
import View.utils.JViews;
import Services.CatalogoServiciosService;

public class CatalogoServiciosView extends JViews<Catalogo_Servicios> {

    private final CatalogoServiciosService css = new CatalogoServiciosService();

    public CatalogoServiciosView() {
        t = new Catalogo_Servicios();
    }

    @Override
    protected void registrar() {
        limpiarPantalla();
        printlnTitle_Cyan("************* BIENVENIDO AL MODULO PARA REGISTRAR SERVICIOS ************* ");
        printlnInfo("\nPresione Enter para continuar");
        lector.nextLine();
        printlnInstruction("Ingrese el codigo del Servicio ");
        String codigo = lector.nextLine();
        printlnInstruction("Ingrese el Nombre del Servicio ");
        String nombre = lector.nextLine();
        printlnInstruction("Ingrese el Precio del Servicio ");
        float precio = lector.nextFloat();
        lector.nextLine();

        // --
        if (css.create(codigo, new Catalogo_Servicios(codigo, nombre, precio))) {
            printlnInfo("\n¡El registro fue exitoso!\n");
        } else {
            printlnInfo("Ups, ya existe un Servicio con el ID ingresado");
        }
        lector.nextLine();    
    }

    @Override
    protected void verLista() {
        printlnTitle_Green("************ Lista de Servicios *************");
        if (css.getList().isEmpty()) {
            System.out.println("No hay datos aún\n");
        }
        css.getList().forEach((key, map) -> System.out.println(map.toString()));
        printlnInfo("Presione Enter para continuar");
        lector.nextLine();
    }
    
    @Override
    protected void cambiarDatos() {
        limpiarPantalla();
        printlnTitle_Cyan("******** BIENVENIDO AL MODULO PARA MODIFICAR SERVICIOS ********");
        printlnInstruction("A continuacion se mostrara la lista de servicios registrados: \n");
        verLista();
        printlnInfo("Seleccione un servicio por su ID: ");
        String cservicio_buscar = lector.nextLine();

        Catalogo_Servicios cservicio_find = css.getByID(cservicio_buscar);
        if (cservicio_find != null) {

            printlnInfo("\nServicio encontrado");
            printlnInstruction("-".repeat(20) + "ID: " + cservicio_find.getID() + "-".repeat(20));
        
            printInstruction("Nombre (Actual): " + cservicio_find.getNomServicio() + "\tNombre (Modificacion): ");
            String nombre = lector.nextLine(); 
            printInstruction("Nombre (Actual): " + cservicio_find.getPrecioServicio() + "\tNombre (Modificacion): ");
            float anio = lector.nextFloat();
            lector.nextLine();
            
            printlnInfo("Presione Enter para guardar");
        
            Catalogo_Servicios cservicio_actualizado = new Catalogo_Servicios(cservicio_buscar, nombre, anio);
            css.put(cservicio_actualizado, cservicio_find.getID()); //Siempre devolvera verdadero 
            printlnInfo("Se modifico correctamente");
        } else {
            printlnError("El servicio no existe, intentelo de nuevo");
            lector.nextLine();
        }
        // ---
    }
    
    @Override
    protected void eliminarDatos() {
        limpiarPantalla();
        printlnTitle_Red("****** WARMING: USTED A ENTRADO AL MODULO PARA ELIMINAR SERVICIOS *******");
        printlnInstruction("Presione 1 si desea continuar");
        String button = lector.nextLine();
        if (button.equals("1")) {
            printlnInstruction("A continuacion se muestra la lista de servicios: \n");
            verLista();
            printlnInfo("Seleccione a un servicio por su ID para " + BOLD + BG_RED + " ELIMINARLO: ");
            String cservicio_buscar = lector.nextLine();
            if (css.delete(cservicio_buscar)) {
                printlnError(BOLD + "\nSe elimino correctamente");
            } else {
                printlnError("\nEl servicio no existe");
            }
        }
        printlnInfo("\nPresione Enter para volver al menu");
        lector.nextLine();

    }     
}
