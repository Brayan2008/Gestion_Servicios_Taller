package Model;

public class Servicios_Orden {
    private Catalogo_Servicios servicio;
    private Orden orden;
    private String ObserEspecifica;
    
    public Servicios_Orden(Catalogo_Servicios servicio, Orden orden, String obserEspecifica) {
        this.servicio = servicio;
        this.orden = orden;
        this.ObserEspecifica = obserEspecifica;
    } 

    public Catalogo_Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Catalogo_Servicios servicio) {
        this.servicio = servicio;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public String getObserEspecifica() {
        return ObserEspecifica;
    }

    public void setObserEspecifica(String obserEspecifica) {
        ObserEspecifica = obserEspecifica;
    }

}
