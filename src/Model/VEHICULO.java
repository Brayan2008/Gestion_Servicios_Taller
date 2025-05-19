package Model;

public class Vehiculo {
    private String PlacaVehiculo; 
    private String MarcaVehiculo;
    private String ModeloVehiculo;
    private String ModChasis;
    private String NumMotor;
    private int AñoVehiculo;
    private String ColorVehiculo;

    public Vehiculo(String placaVehiculo, String marcaVehiculo, String modeloVehiculo, String modChasis,String numMotor, int añoVehiculo, String colorVehiculo) {
        PlacaVehiculo = placaVehiculo;
        MarcaVehiculo = marcaVehiculo;
        ModeloVehiculo = modeloVehiculo;
        ModChasis = modChasis;
        NumMotor = numMotor;
        AñoVehiculo = añoVehiculo;
        ColorVehiculo = colorVehiculo;
    }

    public String getPlacaVehiculo() {
        return PlacaVehiculo;
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

    
}
