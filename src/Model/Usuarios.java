package Model;

public class Usuarios {
    private String idUser;
    private String nombre; 
    private String contraseña;
    
    public Usuarios() {
   
    }

    public Usuarios(String idUser, String nombre, String contraseña) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }
    

    public String getID() {
        return idUser;
    }
    
    public void setID(String idUser) {
        this.idUser = idUser;
    }
    
    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contaseña) {
        this.contraseña = contaseña;
    }

    @Override
    public String toString() {
        return "-".repeat(40)                  + 
               "\nID: " + getID()                      +
               "\nNombre: " + getNombre() + "\n"     +
               "-".repeat(40);
    }
    
}
