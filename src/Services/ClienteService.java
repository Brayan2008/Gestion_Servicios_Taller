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

    public void ValidarBusqueda(Cliente cliente, String dni_busqueda) {
        if (cliente != null) {

            printlnInfo("\nCliente encontrado");
            printlnInstruction("-".repeat(20) + "ID: " + cliente.getID() + "-".repeat(20));
            
            printInstruction("Nombre (Actual): " + cliente.getNombre() + "\tNombre (Modificacion): ");
            String nombre = lector.nextLine();
            
            printInstruction("Telefono (Actual): " + cliente.getTelefono() + "\tTelefono (Modificacion): ");
            String tel = lector.nextLine();
            
            printInstruction("Direccion (Actual): " + cliente.getDireccion() + "\tDireccion (Modificacion): ");
            String direccion = lector.nextLine();
            
            printInstruction("Distrito (Actual): " + cliente.getDistritoCliente() + "\tDistrito (Modificacion): ");
            String distrito = lector.nextLine();
            
            printlnInfo("Presione Enter para guardar");
           
            Cliente cliente_actualizado = new Cliente(dni_busqueda, nombre, tel, direccion,
                    cliente.getTipoDocumento(), distrito);
            put(cliente_actualizado, cliente.getID()); //Siempre devolvera verdadero 
            printlnInfo("Se modifico correctamente");
        } else {
            printlnError("El usuario no existe, intentelo de nuevo");
            lector.nextLine();
        }
    }
}
