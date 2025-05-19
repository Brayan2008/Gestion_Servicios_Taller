package Model;

public abstract class Persona {
    private String Nombre;    
    private String Telefono;
    private String Direccion;
    
    public Persona(String nombre, String telefono, String direccion) {
        Nombre = nombre;
        Telefono = telefono;
        Direccion = direccion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

}
