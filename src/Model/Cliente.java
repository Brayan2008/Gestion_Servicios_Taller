package Model;

import Model.templates.Persona;

public class Cliente extends Persona{
    
    private int tipoDocumento;
    private String nroCliente;
    private String distritoCliente;
 
    public Cliente() {
        super();
    }

    public Cliente(String nroCliente,String nombre, String telefono, String direccion, int tipoDocumento, String distritoCliente) {
        super(nombre, telefono, direccion);
        this.nroCliente = nroCliente;
        this.tipoDocumento = tipoDocumento;
        this.distritoCliente = distritoCliente;
    }

    public String getID() {
        return nroCliente;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    } 

    public String getDistritoCliente() {
        return distritoCliente;
    }

    public void setDistritoCliente(String distritoCliente) {
        this.distritoCliente = distritoCliente;
    }

    @Override
    public String toString() {
        return "-".repeat(40)                    + 
               "\nID: " + nroCliente                   +
               "\nTipo de documento: " + tipoDocumento +
               "\nNombre: " + getNombre()              +
               "\nTelefono: " + getTelefono()          +
               "\nDireccion: " + getDireccion()          +
               "\nDistrito: " + distritoCliente + "\n"   +
               "-".repeat(40);
    }
    
    
}