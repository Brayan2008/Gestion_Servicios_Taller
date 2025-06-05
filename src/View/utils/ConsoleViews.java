package View.utils;

/**
 * Clase Abstracta que implementa ConsoleTools, ademas 
 * incluye metodos predeterminados que pueden ser sobreescritos
 */

public abstract class ConsoleViews<T> implements ConsoleTools{
    
    protected T t;

    public void init(){
        String opcion;

        do {
            limpiarPantalla();
            imprimirMenu();
            opcion = lector.nextLine();
            switch (opcion) {
                case "1" -> {
                    limpiarPantalla();
                    verLista();
                }
                case "2" -> registrar();
                case "3" -> cambiarDatos();
                case "4" -> eliminarDatos();
                case "5" -> System.out.println("Goodbye!");
                default -> {printlnError("OPCION NO VALIDA\n\n");
                            lector.nextLine();}
            }
        } while (!opcion.equals("5"));
        
        limpiarPantalla();
        printlnInfo("Saliendo del modulo Cliente ...");
    };
        
    protected void imprimirMenu(){
        String nombreClase = t.getClass().getSimpleName(); 
        System.out.println(BOLD + CURVE + "--------------- GESTION DE " + nombreClase.toUpperCase() + " ------------------- ");
        System.out.println(" 1. Ver lista de " + nombreClase.toLowerCase());
        System.out.println(" 2. Registrar un nuevo " + nombreClase.toLowerCase());
        System.out.println(" 3. Modificar un " + nombreClase.toLowerCase());
        System.out.println(" 4. Eliminar un " + nombreClase.toLowerCase());
        System.out.println(" 5. Salir del modulo (exit) " + nombreClase.toLowerCase());
        System.out.println("\nSeleccione una opcion valida:" + DEFAULT + "\n".repeat(10));
    };

    protected void verLista(){
        System.out.println("No implementado");
    };
    
    protected void registrar(){
        System.out.println("No implementado");
    };
    
    protected void cambiarDatos(){
        System.out.println("No implementado");
    };

    protected void eliminarDatos(){
        System.out.println("No implementado");
    };
}

