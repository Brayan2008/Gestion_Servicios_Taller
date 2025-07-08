package controllers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Services.Mecanico1Service;
import View.AgregarMecanico1View;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RTextField;

public class AgregarMecanico1Controller {

    private final Mecanico1Service ser;

    AgregarMecanico1View av;
    JLabel title;
    RButton btnGuardar, btnCancelar;
    RTextField txtCodigo, txtNombre, txtTelefono, txtDireccion;
    JTable tabla;

    int opcion;

    public AgregarMecanico1Controller(Mecanico1Service ser, int opcion) {
        this.av = new AgregarMecanico1View(null);

        this.av.setVisible(false); // Permite cargar todo antes de bloquear como Modal
        this.av.setModal(false);

        this.ser = ser;

        this.title = av.titleLabel;
        this.btnCancelar = av.btnCancelar;
        this.tabla = Mecanico1ViewController.table;
        this.btnGuardar = av.btnGuardar;
        this.txtCodigo = av.txtCodigo;
        this.txtNombre = av.txtNombre;
        this.txtTelefono = av.txtTelefono;
        this.txtDireccion = av.txtDireccion;
        this.opcion = opcion; // true <- agregar; false <- editar
        addListener();

        this.av.setModal(true); // Establece como Modal y luego bloquea
        this.av.setVisible(true);
    }

    protected void addListener() {
        btnGuardar.addActionListener(e -> guardarMecanico());
        btnCancelar.addActionListener(e -> av.dispose());

        if (opcion == 2 || opcion == 1) {
            txtCodigo.setText(tabla.getValueAt(Mecanico1ViewController.row, 0).toString().trim());
            txtCodigo.setFont(Colors.SubTitles);
            txtCodigo.setEditable(false);

            txtNombre.setText(tabla.getValueAt(Mecanico1ViewController.row, 1).toString());
            txtTelefono.setText(tabla.getValueAt(Mecanico1ViewController.row, 2).toString());
            txtDireccion.setText(tabla.getValueAt(Mecanico1ViewController.row, 3).toString());

            switch (opcion) {
                case 1:
                    title.setText("EDITAR");
                    break;
                case 2:
                    title.setText("ELIMINAR");
                    btnGuardar.setColor(Colors.TEMA_BUTTONS_ROJO, Colors.BUTTONS_FONDO_ROJO);
                    btnGuardar.setText("Eliminar");
                    btnCancelar.setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
                    txtNombre.setEditable(false);
                    txtTelefono.setEditable(false);
                    txtDireccion.setEditable(false);
                    btnGuardar.removeActionListener(btnGuardar.getActionListeners()[0]);
                    btnGuardar.addActionListener(e -> eliminarMecanico());
                default:
                    break;
            }
        }
    }

    public void eliminarMecanico() {
        var codigo = tabla.getValueAt(Mecanico1ViewController.row, 0).toString().trim();
        try {
            ser.eliminarMecanico(codigo);
            JOptionPane.showMessageDialog(av, "Mecanico eliminado correctamente.");
            ser.buscarMecanico("");
            av.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(av, "Error en la eliminacion");
        }
    }

    protected void guardarMecanico() {
        String cod = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String Direccion = txtDireccion.getText().trim();

        // validaciones
        if (cod.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(av, "Estos campos son obligatorios");
            return;
        }

        if (!cod.matches("\\d{8}")) { // Verifica que sean exactamente 8 dígitos numéricos
            JOptionPane.showMessageDialog(av, "El DNI debe contener solo números y tener 8 dígitos");
            return;
        }
        if (!telefono.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(av, "El teléfono debe contener solo números y tener 9 dígitos");
            return;
        }

        try {
            // llamamos al servicio (1.- Editar)
            if (opcion == 1) {
                ser.actualizarMecanico(cod, nombre, telefono, Direccion);
                JOptionPane.showMessageDialog(av, "Mecanico actualizado correctamente.");
            } else {
                ser.insertarMecanico(cod, nombre, telefono, Direccion);
                JOptionPane.showMessageDialog(av, "Mecanico agregado correctamente.");
            }
            ser.buscarMecanico("");
            av.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(av, "Error: " + ex.getMessage());
        }
    }

}
