package controllers;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Services.ClienteService;
import View.AgregarClienteView;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RTextField;

public class ClienteCRUDController {

    private final ClienteService ser;

    AgregarClienteView av;

    JLabel title;
    public RTextField txtCodigo, txtNombre, txtTelefono, txtDireccion, txtDistrito;
    JComboBox<String> tipo_doc;
    RButton btnGuardar, btnCancelar;
    JTable tabla;

    boolean tipo_id;
    int opcion;

    public ClienteCRUDController(ClienteService ser, int opcion) {
        this.av = new AgregarClienteView(null);

        this.av.setVisible(false); // Permite cargar todo antes de bloquear como Modal
        this.av.setModal(false);

        this.ser = ser;

        this.title = av.titleLabel;
        this.btnCancelar = av.btnCancelar;
        this.btnGuardar = av.btnGuardar;
        this.tabla = ClienteViewController.table;
        this.tipo_doc = av.tipo_doc;
        this.txtCodigo = av.txtCodigo;
        this.txtNombre = av.txtNombre;
        this.txtTelefono = av.txtTelefono;
        this.txtDireccion = av.txtDireccion;
        this.txtDistrito = av.txtDistrito;

        this.opcion = opcion; // true <- agregar; false <- editar
        addListener();

        this.av.setModal(true); // Establece como Modal y luego bloquea
        this.av.setVisible(true);
    }

    protected void addListener() {
        btnGuardar.addActionListener(e -> guardarCliente());
        btnCancelar.addActionListener(e -> av.dispose());

        if (opcion == 2 || opcion == 1) {
            var tipo_documento = tabla.getValueAt(ClienteViewController.row, 1).toString().trim();
            tipo_id = tipo_documento.equalsIgnoreCase("DNI") ? true : false; //DNI: true, PASSPORT: false

            txtCodigo.setText(tabla.getValueAt(ClienteViewController.row, 0).toString().trim());
            txtCodigo.setFont(Colors.SubTitles);
            tipo_doc.setFont(Colors.SubTitles);
            tipo_doc.setEnabled(false);
            txtCodigo.setEditable(false);

            txtNombre.setText(tabla.getValueAt(ClienteViewController.row, 2).toString());
            txtTelefono.setText(tabla.getValueAt(ClienteViewController.row, 3).toString());
            txtDireccion.setText(tabla.getValueAt(ClienteViewController.row, 4).toString());
            txtDistrito.setText(tabla.getValueAt(ClienteViewController.row, 5).toString());

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
                    txtDistrito.setEditable(false);
                    btnGuardar.removeActionListener(btnGuardar.getActionListeners()[0]);
                    btnGuardar.addActionListener(e -> eliminarCliente());
                default:
                    break;
            }
        }
    }

    public void eliminarCliente() {
        var codigo = tabla.getValueAt(ClienteViewController.row, 0).toString().trim();
        try {
            ser.eliminarCliente(codigo, tipo_id);
            JOptionPane.showMessageDialog(av, "Cliente eliminado correctamente.");
            ser.buscarCliente("");
            av.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(av, "Error en la eliminacion");
        }
    }

    protected void guardarCliente() {
        String tipo = tipo_doc.getSelectedItem().toString().trim();
        String cod = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telf = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String distrito = txtDistrito.getText().trim();

        // validaciones
        if (tipo.isEmpty() || nombre.isEmpty() || nombre.isEmpty() || telf.isEmpty()) {
            JOptionPane.showMessageDialog(av, "Campos vacios permitidos: Direccion y Distrito");
            return;
        }
        tipo_id = tipo.equalsIgnoreCase("DNI") ? true:false;
        if (tipo_id){
            if (!cod.matches("[0-9]{9}")) {
                JOptionPane.showMessageDialog(av, "El código debe tener exactamente 9 caracteres");
                return;
            }
        } else {
            if (!cod.matches("[0-9]{11}")) {
                JOptionPane.showMessageDialog(av, "El código debe tener exactamente 11 caracteres");
                return;
            }
        }

        try {
            // llamamos al servicio (1.- Editar)
            if (opcion == 1) {
                ser.actualizarCliente(cod, tipo_id, nombre, telf, direccion, distrito);
                JOptionPane.showMessageDialog(av, "Servicio actualizado correctamente.");
            } else {
                ser.insertarClientes(cod, tipo_id, nombre, telf, direccion, distrito);
                JOptionPane.showMessageDialog(av, "Servicio agregado correctamente.");
            }
            ser.buscarCliente("");
            av.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(av, "Error: " + ex.getMessage());
        }
    }

}