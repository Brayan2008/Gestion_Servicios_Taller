package View.utils;

import java.util.Scanner;

/**
 * Interfaz de utilidad para decorar texto e implementa colores, esto gracias 
 * a los caracteres <code>UNICODE</code> que reconoce Java
 * <div>Ademas contiene un <code>Scanner</code> universal para todas las vistas</div>
 */
public interface ConsoleTools {
 
    static final Scanner lector = new Scanner(System.in);

    //Colores de texto
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String BLUE = "\u001B[34m";
    static final String YELLOW = "\u001B[33m";
    static final String WHITE = "\u001B[37m";
    static final String DEFAULT = "\u001B[0m";
    //Estilos de texto
    static final String BOLD = "\u001B[1m";
    static final String SHADOW = "\u001B[2m";
    static final String CURVE = "\u001B[3m";
    //Backgrounds
    static final String BG_YELLOW = "\u001B[43m";
    static final String BG_WHITE = "\u001B[47m";
    static final String BG_BLACK = "\u001B[40m";
    static final String BG_GREEN = "\u001B[42m";
    static final String BG_CYAN = "\u001B[46m";
    static final String BG_RED = "\u001B[41m";
    
    /**
     * Imprime un mensaje de color verde por pantalla, seguido de un salto de linea
     * <div>Derivado de <pre> System.out.println("Mensaje en color verde")</pre></div>
     * @param mensaje El mensaje que se imprimira
     */
    default void printlnInstruction(String mensaje) {
        System.out.println(GREEN + mensaje + DEFAULT);
    }
    
    /**
     * Imprime un mensaje de color VERDE por pantalla. SIN SALTO DE LINEA
     * <div>Derivado de 
     * <pre> System.out.print("Mensaje en color verde sin salto de linea")</pre>
     * </div>
     * @param mensaje El mensaje que se imprimira
     */
    default void printInstruction(String mensaje) {
        System.out.print(GREEN + mensaje + DEFAULT);
    }
    
    /**
     * Imprime un mensaje de color ROJO por pantalla, seguido de un salto de linea
     * <div>Derivado de 
     * <pre> System.out.println("Mensaje en color rojo")</pre>
     * </div>
     * @param mensaje El mensaje que se imprimira
     */
    default void printlnError(String mensaje) {
        System.out.println(RED + mensaje + DEFAULT);
    }
    
    /**
     * Imprime un mensaje de color AZUL por pantalla, seguido de un salto de linea
     * <div>Derivado de 
     * <pre> System.out.println("Mensaje en color AZUL")</pre>
     * </div>
     * @param mensaje El mensaje que se imprimira
     */
    default void printlnInfo(String mensaje) {
        System.out.println(BLUE + mensaje + DEFAULT);
    }

    //Para titulos
    default void printlnTitle_Cyan(String mensaje){
          System.out.println(BG_CYAN + BOLD + mensaje + DEFAULT); 
    }

    default void printlnTitle_Green(String mensaje){
          System.out.println(BG_GREEN + BOLD + mensaje + DEFAULT); 
    }
    
    default void printlnTitle_Red(String mensaje){
          System.out.println(BG_RED + BOLD + WHITE + mensaje + DEFAULT); 
    }

    /**
     * Repite un salto de linea cuarenta veces, esto con el fin de simular
     * una limpieza de consola.
     */
    default void limpiarPantalla(){
        System.out.println("\n".repeat(40));
    }

}
