public class Cliente {
    private String nroCliente;
    private int tipoDocumento;
    private String nombreCliente;
    private String telefonoCliente;
    private String direccionCliente;
    private String distritoCliente;

    public Cliente(String nroCliente, int tipoDocumento, String nombreCliente, String telefonoCliente, String direccionCliente, String distritoCliente) {
        this.nroCliente = nroCliente;
        this.tipoDocumento = tipoDocumento;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccionCliente = direccionCliente;
        this.distritoCliente = distritoCliente;
    }
}