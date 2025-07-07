package controllers;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Services.CatalogoServiciosService;
import View.AgregarClienteView;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RTextField;

public class AgregarClienteController {

    private final CatalogoServiciosService ser;

    AgregarClienteView av;
    JLabel title;
    RButton btnGuardar, btnCancelar;
    RTextField txtCodigo, txtNombre, txtPrecio;
    JTable tabla;

    int opcion;

    public AgregarClienteController(CatalogoServiciosService ser, int opcion) {
        this.av = new AgregarClienteView(null);

        this.av.setVisible(false); // Permite cargar todo antes de bloquear como Modal
        this.av.setModal(false);

        this.ser = ser;

        this.title = av.titleLabel;
        this.btnCancelar = av.btnCancelar;
        this.tabla = ClienteViewController.table;
        this.btnGuardar = av.btnGuardar;
        this.txtCodigo = av.txtCodigo;
        this.txtPrecio = av.txtPrecio;
        this.txtNombre = av.txtNombre;
        this.opcion = opcion; // true <- agregar; false <- editar
        addListener();

        this.av.setModal(true); // Establece como Modal y luego bloquea
        this.av.setVisible(true);
    }

    protected void addListener() {
        btnGuardar.addActionListener(e -> guardarServicio());
        btnCancelar.addActionListener(e -> av.dispose());

        if (opcion == 2 || opcion == 1) {
            txtCodigo.setText(tabla.getValueAt(ClienteViewController.row, 0).toString().trim());
            txtCodigo.setFont(Colors.SubTitles);
            txtCodigo.setEditable(false);

            txtNombre.setText(tabla.getValueAt(ClienteViewController.row, 1).toString());
            txtPrecio.setText(tabla.getValueAt(ClienteViewController.row, 2).toString());

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
                    txtPrecio.setEditable(false);
                    btnGuardar.removeActionListener(btnGuardar.getActionListeners()[0]);
                    btnGuardar.addActionListener(e -> eliminarServicio());
                default:
                    break;
            }
        }
    }

    public void eliminarServicio() {
        var codigo = tabla.getValueAt(ClienteViewController.row, 0).toString().trim();
        try {
            ser.eliminarCliente(codigo);
            JOptionPane.showMessageDialog(av, "Servicio eliminado correctamente.");
            ser.buscarCliente("");
            av.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(av, "Error en la eliminacion");
        }
    }

    protected void guardarServicio() {
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
            precioStr = precioStr.replace(',', '.');
            double precio = Double.parseDouble(precioStr);
            if (precio <= 0) {
                JOptionPane.showMessageDialog(av, "El precio debe ser mayor a cero");
                return;
            }
            // llamamos al servicio (1.- Editar)
            if (opcion == 1) {
                System.out.println("Aaaaa");
                ser.actualizarCliente(cod, nombre, precio);
                JOptionPane.showMessageDialog(av, "Servicio actualizado correctamente.");
            } else {
                ser.insertarServicio(cod, nombre, precio);
                JOptionPane.showMessageDialog(av, "Servicio agregado correctamente.");
            }
            ser.buscarCliente("");
            av.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(av, "El precio debe ser un número válido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(av, "Error: " + ex.getMessage());
        }
    }

}