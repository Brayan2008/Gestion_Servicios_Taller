package Model;

public class Cliente extends Persona{
    
    private int tipoDocumento;
    private String nroCliente;
    private String distritoCliente;
 
    public Cliente(String nroCliente,String nombre, String telefono, String direccion, int tipoDocumento, String distritoCliente) {
        super(nombre, telefono, direccion);
        this.nroCliente = nroCliente;
        this.tipoDocumento = tipoDocumento;
        this.distritoCliente = distritoCliente;
    }

    public String getNroCliente() {
        return nroCliente;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    } //A eliminar 

    public String getDistritoCliente() {
        return distritoCliente;
    }

    public void setDistritoCliente(String distritoCliente) {
        this.distritoCliente = distritoCliente;
    }
    
}