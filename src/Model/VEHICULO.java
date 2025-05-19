package Model;

public class VEHICULO {
    private String PlacaVehiculo; 
    private String MarcaVehiculo;
    private String ModeloVehiculo;
    private String ModChasis;
    private String NumMotor;
    private int AñoVehiculo;
    private String ColorVehiculo;

    public VEHICULO(String placaVehiculo, String marcaVehiculo, String modeloVehiculo, String modChasis,String numMotor, int añoVehiculo, String colorVehiculo) {
        PlacaVehiculo = placaVehiculo;
        MarcaVehiculo = marcaVehiculo;
        ModeloVehiculo = modeloVehiculo;
        ModChasis = modChasis;
        NumMotor = numMotor;
        AñoVehiculo = añoVehiculo;
        ColorVehiculo = colorVehiculo;
    }
 
}
