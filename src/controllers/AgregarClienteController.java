package controllers;

import javax.swing.JOptionPane;

import Services.CatalogoServiciosService;
import View.AgregarClienteView;
import View.utils.RButton;
import View.utils.RTextField;

public class AgregarClienteController {
    private final CatalogoServiciosService ser;
    
    AgregarClienteView av;
    RButton btnGuardar, btnCancelar;
    RTextField txtCodigo, txtNombre, txtPrecio;

    public AgregarClienteController(CatalogoServiciosService ser ) {
        this.av = new AgregarClienteView(null);
       
        this.av.setVisible(false);  //Permite cargar todo antes de bloquear como Modal
        this.av.setModal(false);
       
        this.ser = ser;
        this.btnCancelar = av.btnCancelar;
        this.btnGuardar = av.btnGuardar;
        this.txtCodigo = av.txtCodigo;
        this.txtPrecio = av.txtPrecio;
        this.txtNombre = av.txtNombre;
        addListener();
        
        this.av.setModal(true); // Establece como Modal y luego bloquea
        this.av.setVisible(true);
    }
    
    public void addListener() {
        btnGuardar.addActionListener(e-> guardarServicio());
        btnCancelar.addActionListener(e -> av.dispose());
    }
    
    private void guardarServicio() {
        String cod = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();

        // validaciones
        if (cod.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(av, "Todos los campos son obligatorios");
            return;
        }
        if (cod.length() != 2) {
            JOptionPane.showMessageDialog(av, "El código debe tener exactamente 2 caracteres");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);

            // llamamos al servicio
            ser.insertarServicio(cod, nombre, precio);

            JOptionPane.showMessageDialog(av, "Servicio agregado correctamente.");
            av.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(av, "El precio debe ser un número válido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(av, "Error al guardar: " + ex.getMessage());
        }
    }

}
