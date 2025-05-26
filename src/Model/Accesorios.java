package Model;

public class Accesorios {
    

    private String CodAccesorio;
    private String NombreAccesorio;
    
    public Accesorios() {

    }
    
    public Accesorios(String codAccesorio, String nombreAccesorio) {
        this.CodAccesorio = codAccesorio;
        this.NombreAccesorio = nombreAccesorio;
    }

    public String getID() {
        return CodAccesorio;
    }

    public String getNombreAccesorio() {
        return NombreAccesorio;
    }

    public void setNombreAccesorio(String nombreAccesorio) {
        NombreAccesorio = nombreAccesorio;
    }
    
    @Override
    public String toString() {
        return "-".repeat(40)                    + 
            "\nCodigo del accesorio: " + CodAccesorio                   +
            "\nNombre del accesorio: " + NombreAccesorio + "\n"   +
            "-".repeat(40);
    }
    
    //
}
