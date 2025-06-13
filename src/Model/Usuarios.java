package Model;

public class Usuarios {
    private String idUser;
    private String contraseña;
    
    public Usuarios() {

    }

    public Usuarios(String idUser, String contraseña) {
        this.idUser = idUser;
        this.contraseña = contraseña;
    }
    

    public String getID() {
        return idUser;
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
            "-".repeat(40);
    }  
}
