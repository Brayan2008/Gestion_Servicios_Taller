package Model;

public class Catalogo_Servicios {
    private String CodServicio;
    private String NomServicio;
    private float PrecioServicio;
    
    public Catalogo_Servicios(String CodServicio, String nomServicio, float precioServicio) {
        this.CodServicio = CodServicio;
        this.NomServicio = nomServicio;
        this.PrecioServicio = precioServicio;
    }
    
    public String getNomServicio() {
        return NomServicio;
    }
    
    public String getCodServicio() {
        return CodServicio;
    }
    
    public float getPrecioServicio() {
        return PrecioServicio;
    }

    @Override
    public String toString() {
        return "Código: " + CodServicio + "; Servicio: " + NomServicio + "; Precio: " + PrecioServicio; 
    }
}
