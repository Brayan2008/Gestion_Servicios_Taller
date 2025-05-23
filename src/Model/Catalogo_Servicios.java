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

    public Catalogo_Servicios() {
    }

    public String getID() {
        return CodServicio;
    }

    public void setCodServicio(String codServicio) {
        CodServicio = codServicio;
    }
    
    public String getNomServicio() {
        return NomServicio;
    }

    public void setNomServicio(String nomServicio) {
        NomServicio = nomServicio;
    }
    
    public float getPrecioServicio() {
        return PrecioServicio;
    }
    
    public void setPrecioServicio(float precioServicio) {
        PrecioServicio = precioServicio;
    }

    @Override
    public String toString() {
        return "Código: " + CodServicio + "; Servicio: " + NomServicio + "; Precio: " + PrecioServicio; 
    }
}

    

