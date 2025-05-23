package Services;

import Model.Cliente;
import Services.interfaces.Service;
import View.utils.ConsoleTools;

public class ClienteService extends Service<Cliente> implements ConsoleTools{  
      
    public String RegistrarID(int tipo_documento) {
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
