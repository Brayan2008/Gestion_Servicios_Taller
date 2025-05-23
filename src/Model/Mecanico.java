package Model;

import Model.templates.Persona;

public class Mecanico extends Persona{
    
    private String codMecanico;

    public Mecanico() {
        super();
    }

    public Mecanico(String nombre, String telefono, String direccion, String codMecanico) {
        super(nombre, telefono, direccion);
        this.codMecanico = codMecanico;
    }

    public String getID() {
        return codMecanico;
    }

    @Override
    public String toString() {
        return "-".repeat(40)              +
        "\nID: " + codMecanico                   +
        "\nTNombre" + getNombre()                +
        "\nTelefono: " + getTelefono()           +
        "\n Direccion: " + getDireccion() + "\n" +
        "-".repeat(40);
    }
    
}