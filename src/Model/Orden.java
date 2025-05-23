package Model;

public class Orden {
    private String CodOrden;
    private String FechaOrden;
    private String FechaEntrega;
    private double Kilometrage;
    private double NumCombustible;
    private String ObserGeneral;
    private String EstadoOrden;
    private boolean TProb;
    private boolean ManualProb;
    private boolean Llave;
    private boolean Quiñado;
    private boolean Abollado;
    private boolean Rayado;

    public Orden(String CodOrden, String FechaOrden, String FechaEntrega, double Kilometrage, double NumCombustible, String ObserGeneral, String EstadoOrden, boolean TProb, boolean ManualProb, boolean Llave, boolean Quiñado, boolean Abollado, boolean Rayado){
        this.CodOrden = CodOrden;
        this.FechaOrden = FechaOrden;
        this.FechaEntrega = FechaEntrega;
        this.Kilometrage = Kilometrage;
        this.NumCombustible = NumCombustible;
        this.ObserGeneral = ObserGeneral;
        this.EstadoOrden = EstadoOrden;
        this.TProb = TProb;
        this.ManualProb = ManualProb;
        this.Llave = Llave;
        this.Quiñado = Quiñado;
        this.Abollado = Abollado;
        this.Rayado = Rayado;
    }

    public String getID() {
        return CodOrden;
    }

    //Faltan las fk
    @Override
    public String toString() {
        return "-".repeat(40)                      + 
               "\nID: " +  getID()                       +
               "\nFecha Orden: " + FechaOrden            +
               "\nFecha Entrega " + FechaEntrega         +
               "\nKilometraje: " + Kilometrage           +
               "\nNumCombustible " + NumCombustible      +
               "\nObservacion General: " + ObserGeneral  +
               "\nEstado Orden: " + EstadoOrden          +
               "\nTitulo de Propiedad: " + TProb         +
               "\nManual de Propiedad: " + ManualProb    +
               "\nDejo llave: " + Llave                  +
               "\nVehiculo Abollado: " + Abollado        +
               "\nVehiculo Quiñado: " + Quiñado          +
               "\nVehiculo Rayado: " + Rayado +   "\n"   +
               "-".repeat(40);
    }

    

}
