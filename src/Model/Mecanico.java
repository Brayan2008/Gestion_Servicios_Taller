package Model;

import Model.templates.Persona;

public class Mecanico extends Persona{
    
    private String codMecánico;

    public Mecanico(String nombre, String telefono, String direccion, String codMecánico) {
        super(nombre, telefono, direccion);
        this.codMecánico = codMecánico;
    }

    public String getID() {
        return codMecánico;
    }
    
}