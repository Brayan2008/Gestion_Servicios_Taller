package Services;

import Model.Mecanico;
import Services.templates.Service;
import View.utils.ConsoleTools;

public class MecanicoService extends Service<Mecanico> implements ConsoleTools{    

    public String RegistrarID(int tipo_documento) {
        String id = "";
        int i = 2;
        do {
            printlnInstruction("Ingrese el DNI (8 digitos): ");
            id = lector.nextLine();
            if (!id.matches("[0-9]{8}")) {
            printlnError(RED + "El DNI ingresado no es correcto. (" + i + " de 3 opotunidades.)" + DEFAULT);
            id = ""; 
            i--;
        } else {
                i = -1;
            }
        } while (i != -1);
        return id;
    }

    public void ValidarBusqueda(Mecanico mecanico, String dni_buscar){
        if (mecanico != null) {

            printlnInfo("\nMecanico encontrado");
            printlnInstruction("-".repeat(20) + "ID: " + mecanico.getID() + "-".repeat(20));
            
            printInstruction("Nombre (Actual): " + mecanico.getNombre() + "\tNombre (Modificacion): ");
            String nombre = lector.nextLine();
            
            printInstruction("Telefono (Actual): " + mecanico.getTelefono() + "\tTelefono (Modificacion): ");
            String tel = lector.nextLine();
            
            printInstruction("Direccion (Actual): " + mecanico.getDireccion() + "\tDireccion (Modificacion): ");
            String direccion = lector.nextLine();
            
            printlnInfo("Presione Enter para guardar");
           
            Mecanico mecanico_actualizado = new Mecanico(nombre, tel, direccion,dni_buscar);
            put(mecanico_actualizado, mecanico.getID()); //Siempre devolvera verdadero 
            printlnInfo("Se modifico correctamente");
        } else {
            printlnError("El usuario no existe, intentelo de nuevo");
            lector.nextLine();
        }
    }
}
