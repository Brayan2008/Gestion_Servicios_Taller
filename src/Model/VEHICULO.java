package Model;

public class Vehiculo {
    //ID
    private String PlacaVehiculo; 
    private String MarcaVehiculo;
    private String ModeloVehiculo;
    private String ModChasis;
    private String NumMotor;
    private int AñoVehiculo;
    private String ColorVehiculo;

    public Vehiculo(String PlacaVehiculo, String MarcaVehiculo, String ModeloVehiculo, String ModChasis,String NumMotor, int AñoVehiculo, String ColorVehiculo) {
        this.PlacaVehiculo = PlacaVehiculo;
        this.MarcaVehiculo = MarcaVehiculo;
        this.ModeloVehiculo = ModeloVehiculo;
        this.ModChasis = ModChasis;
        this.NumMotor = NumMotor;
        this.AñoVehiculo = AñoVehiculo;
        this.ColorVehiculo = ColorVehiculo;
    }

    public Vehiculo() {
    }

    public String getID() {
        return PlacaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        PlacaVehiculo = placaVehiculo;
    }

    public String getMarcaVehiculo() {
        return MarcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        MarcaVehiculo = marcaVehiculo;
    }

    public String getModeloVehiculo() {
        return ModeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        ModeloVehiculo = modeloVehiculo;
    }

    public String getModChasis() {
        return ModChasis;
    }

    public void setModChasis(String modChasis) {
        ModChasis = modChasis;
    }

    public String getNumMotor() {
        return NumMotor;
    }

    public void setNumMotor(String numMotor) {
        NumMotor = numMotor;
    }

    public int getAñoVehiculo() {
        return AñoVehiculo;
    }

    public void setAñoVehiculo(int añoVehiculo) {
        AñoVehiculo = añoVehiculo;
    }

    public String getColorVehiculo() {
        return ColorVehiculo;
    }

    public void setColorVehiculo(String colorVehiculo) {
        ColorVehiculo = colorVehiculo;
    }

    @Override
    public String toString() {
        return "Vehiculo [PlacaVehiculo=" + PlacaVehiculo + ", MarcaVehiculo=" + MarcaVehiculo + ", ModeloVehiculo="
                + ModeloVehiculo + ", ModChasis=" + ModChasis + ", NumMotor=" + NumMotor + ", AñoVehiculo="
                + AñoVehiculo + ", ColorVehiculo=" + ColorVehiculo + "]";
    }
    
    
}
