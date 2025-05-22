package View.interfaces;

public abstract class ConsoleViews<T> implements ConsoleTools{
    
    protected T t;

    public abstract void init();
    
    public void imprimirMenu(){
        String nombreClase = t.getClass().getSimpleName(); 
        System.out.println(BOLD + CURVE + "--------------- GESTION DE " + nombreClase.toUpperCase() + " ------------------- ");
        System.out.println(" 1. Ver lista de " + nombreClase.toLowerCase());
        System.out.println(" 2. Registrar un nuevo " + nombreClase.toLowerCase());
        System.out.println(" 3. Modificar un " + nombreClase.toLowerCase());
        System.out.println(" 4. Eliminar un " + nombreClase.toLowerCase());
        System.out.println(" 5. Salir del modulo (exit) " + nombreClase.toLowerCase());
        System.out.println("\nSeleccione una opcion valida:" + DEFAULT + "\n".repeat(10));
    };

    public void verLista(){
        System.out.println("No implementado");
    };
    
    public void registrar(){
        System.out.println("No implementado");
    };
    
    public void cambiarDatos(){
        System.out.println("No implementado");
    };

    public void eliminarDatos(){
        System.out.println("No implementado");
    };
    
    public void imprimirObjeto(String key, T map){
        System.out.println("No implementado");
    }

}

