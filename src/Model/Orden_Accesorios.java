package Model;

public class Orden_Accesorios {
    
    private Accesorios accesorio;
    private Orden orden; 

    private int CantAccesorio;
    private String ObserAccesorio;
    private boolean EstadoAccesorio;

    public Orden_Accesorios(Accesorios accesorio, Orden orden, int cantAccesorio, String obserAccesorio,
        boolean estadoAccesorio) {
        this.accesorio = accesorio;
        this.orden = orden;
        this.CantAccesorio = cantAccesorio;
        this.ObserAccesorio = obserAccesorio;
        this.EstadoAccesorio = estadoAccesorio;
    }
    
    public Accesorios getAccesorio() {
        return accesorio;
    }
    public void setAccesorio(Accesorios accesorio) {
        this.accesorio = accesorio;
    }
    public Orden getOrden() {
        return orden;
    }
    public void setOrden(Orden orden) {
        this.orden = orden;
    }
    public int getCantAccesorio() {
        return CantAccesorio;
    }
    public void setCantAccesorio(int cantAccesorio) {
        CantAccesorio = cantAccesorio;
    }
    public String getObserAccesorio() {
        return ObserAccesorio;
    }
    public void setObserAccesorio(String obserAccesorio) {
        ObserAccesorio = obserAccesorio;
    }
    public boolean isEstadoAccesorio() {
        return EstadoAccesorio;
    }
    public void setEstadoAccesorio(boolean estadoAccesorio) {
        EstadoAccesorio = estadoAccesorio;
    }
    
}
