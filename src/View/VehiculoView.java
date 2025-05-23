package View;

import Model.Vehiculo;
import View.utils.ConsoleViews;
import Services.VehiculoService;

public class VehiculoView extends ConsoleViews<Vehiculo>{
    
    private final VehiculoService vs = new VehiculoService();

    public VehiculoView() {
        t = new Vehiculo();
    }
    
    @Override
    protected void registrar() {
        limpiarPantalla();
        printlnTitle_Cyan("************* BIENVENIDO AL MODULO PARA REGISTRAR VEHICULOS ************* ");
        printlnInfo("\nPresione Enter para continuar");
        lector.nextLine();
        printlnInstruction("Ingrese la placa del vehiculo ");
        String codigo = lector.nextLine();
        printlnInstruction("Ingrese el Marca del Vehiculo ");
        String marca = lector.nextLine();
        printlnInstruction("Ingrese el Modelo del Vehiculo ");
        String modelo = lector.nextLine();
        printlnInstruction("Ingrese el Nombre del modelo del chasis del Vehiculo ");
        String modchasis = lector.nextLine();
        printlnInstruction("Ingrese el Numero de motor del Vehiculo ");
        String motor = lector.nextLine();
        printlnInstruction("Ingrese el año del Vehiculo ");
        int anio = lector.nextInt();
        lector .nextLine();
        printlnInstruction("Ingrese el color del Vehiculo ");
        String color = lector.nextLine();

        // --
        if (vs.create(codigo, new Vehiculo(codigo, marca, modelo, modchasis, motor, anio, color))) {
            printlnInfo("\n¡El registro fue exitoso!\n");
        } else {
            printlnInfo("Ups, ya existe un Accesorio con el ID ingresado");
        }
        lector.nextLine();    
    }

    @Override
    protected void verLista() {
        printlnTitle_Green("************ Lista de Vehiculos *************");
        if (vs.getList().isEmpty()) {
            System.out.println("No hay datos aún\n");
        }
        vs.getList().forEach((key, map) -> System.out.println(map.toString()));
        printlnInfo("Presione Enter para continuar");
        lector.nextLine();
    }
    
    @Override
    protected void cambiarDatos() {
        limpiarPantalla();
        printlnTitle_Cyan("******** BIENVENIDO AL MODULO PARA MODIFICAR VEHICULOS ********");
        printlnInstruction("A continuacion se mostrara la lista de vehiculos registrados: \n");
        verLista();
        printlnInfo("Seleccione un vehiculo por su ID: ");
        String vehiculo_buscar = lector.nextLine();

        Vehiculo vehiculo_find = vs.getByID(vehiculo_buscar);
        if (vehiculo_find != null) {

            printlnInfo("\nAccesorio encontrado");
            printlnInstruction("-".repeat(20) + "ID: " + vehiculo_find.getID() + "-".repeat(20));
            
            printInstruction("Nombre (Actual): " + vehiculo_find.getMarcaVehiculo() + "\tNombre (Modificacion): ");
            String marca = lector.nextLine(); 
            printInstruction("Nombre (Actual): " + vehiculo_find.getModeloVehiculo() + "\tNombre (Modificacion): ");
            String modelo = lector.nextLine(); 
            printInstruction("Nombre (Actual): " + vehiculo_find.getModChasis() + "\tNombre (Modificacion): ");
            String modchasis = lector.nextLine(); 
            printInstruction("Nombre (Actual): " + vehiculo_find.getNumMotor() + "\tNombre (Modificacion): ");
            String motor = lector.nextLine(); 
            printInstruction("Nombre (Actual): " + vehiculo_find.getAñoVehiculo() + "\tNombre (Modificacion): ");
            int anio = lector.nextInt();
            lector.nextLine();
            printInstruction("Nombre (Actual): " + vehiculo_find.getColorVehiculo() + "\tNombre (Modificacion): ");
            String color = lector.nextLine(); 

            
            printlnInfo("Presione Enter para guardar");
        
            Vehiculo vehiculo_actualizado = new Vehiculo(vehiculo_buscar, marca, modelo, modchasis, motor, anio, color);
            vs.put(vehiculo_actualizado, vehiculo_find.getID()); //Siempre devolvera verdadero 
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
        printlnTitle_Red("****** WARMING: USTED A ENTRADO AL MODULO PARA ELIMINAR VEHICULOS *******");
        printlnInstruction("Presione 1 si desea continuar");
        String button = lector.nextLine();
        if (button.equals("1")) {
            printlnInstruction("A continuacion se muestra la lista de vehiculos: \n");
            verLista();
            printlnInfo("Seleccione a un vehiculo por su ID para " + BOLD + BG_RED + " ELIMINARLO: ");
            String vehiculo_buscar = lector.nextLine();
            if (vs.delete(vehiculo_buscar)) {
                printlnError(BOLD + "\nSe elimino correctamente");
            } else {
                printlnError("\nEl vehiculo no existe");
            }
        }
        printlnInfo("\nPresione Enter para volver al menu");
        lector.nextLine();

    } 
}
