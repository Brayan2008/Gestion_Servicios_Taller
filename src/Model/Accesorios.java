package Model;

public class Accesorios {
    private String CodAccesorio;
    private String NombreAccesorio;
    
    public Accesorios(String codAccesorio, String nombreAccesorio) {
        this.CodAccesorio = codAccesorio;
        this.NombreAccesorio = nombreAccesorio;
    }

    public String getCodAccesorio() {
        return CodAccesorio;
    }

    public void setCodAccesorio(String codAccesorio) {
        CodAccesorio = codAccesorio;
    }

    public String getNombreAccesorio() {
        return NombreAccesorio;
    }

    public void setNombreAccesorio(String nombreAccesorio) {
        NombreAccesorio = nombreAccesorio;
    }

    
}
