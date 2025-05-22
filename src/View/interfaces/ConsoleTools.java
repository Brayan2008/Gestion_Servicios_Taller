package View.interfaces;

import java.util.Scanner;

public interface ConsoleTools {
 
    static final Scanner lector = new Scanner(System.in);

    //Colores de texto
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[37m";
    public static final String DEFAULT = "\u001B[0m";
    //Estilos de texto
    public static final String BOLD = "\u001B[1m";
    public static final String SHADOW = "\u001B[2m";
    public static final String CURVE = "\u001B[3m";
    //Backgrounds
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_WHITE = "\u001B[47m";
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_RED = "\u001B[41m";
    
    default void printlnInstruction(String mensaje) {
        System.out.println(GREEN + mensaje + DEFAULT);
    }

    default void printInstruction(String mensaje) {
        System.out.print(GREEN + mensaje + DEFAULT);
    }

    default void printlnError(String mensaje) {
        System.out.println(RED + mensaje + DEFAULT);
    }

    default void printlnInfo(String mensaje) {
        System.out.println(BLUE + mensaje + DEFAULT);
    }

    default void printlnTitle_Cyan(String mensaje){
          System.out.println(BG_CYAN + BOLD + mensaje + DEFAULT); 
    }

    default void printlnTitle_Green(String mensaje){
          System.out.println(BG_GREEN + BOLD + mensaje + DEFAULT); 
    }
    
    default void printlnTitle_Red(String mensaje){
          System.out.println(BG_RED + BOLD + WHITE + mensaje + DEFAULT); 
    }

    default void limpiarPantalla(){
        System.out.println("\n".repeat(40));
    }

}
