import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
      //Solo para ejecutar en JGrasp
      String[] param = {
                "cmd", "/c",
                "start cmd /k \"echo off && cls && title Gestion de Servicios    && cd ../bin && java App\""
        };

        // Ejecutar el comando
        Runtime.getRuntime().exec(param);   
    }
}
