package controllers;

import Services.OrdenService;
import View.AgregarOrdenView;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RTextField;

import javax.swing.*;

public class AgregarOrdenController {
    private final OrdenService os;
    private final AgregarOrdenView view;
    int opcion; // 0=agregar, 1=modificar, 2=eliminar
    JTable tabla;
    JLabel tittle;
    RButton btnGuardar, btnCancelar;
    RTextField txtOrden, txtFecha, txtKilometraje, txtFechaEntrega, txtCombustible, txtObservacion, txtDocumento, txtNroDocumento, txtMecanico, txtPlaca;
    JCheckBox chkTarjeta, chkManual, chkLlave, chkQuiniado, chkRayado, chkAbollado;
    JComboBox<String> cmbEstado;

    public AgregarOrdenController(OrdenService os, int opcion) {
        this.view = new AgregarOrdenView(null);
        this.os = os;
        this.opcion = opcion;
        this.tittle = view.titleLabel;
        this.btnCancelar = view.btnCancelar;
        this.btnGuardar = view.btnGuardar;
        this.tabla = OrdenViewController.table;
        this.txtOrden = view.txtNroOrden;
        this.txtFecha = view.txtFechaOrden;
        this.txtKilometraje = view.txtKilometraje;
        this.txtFechaEntrega = view.txtFechaEntrega;
        this.txtCombustible = view.txtNumCombustible;
        this.txtObservacion = view.txtObservacion;
        this.txtDocumento = view.txtDocumento;
        this.txtNroDocumento = view.txtNroDocumento;
        this.txtMecanico = view.txtMecanico;
        this.txtPlaca = view.txtPlacaVehiculo;
        this.chkTarjeta = view.chkTarjeta;
        this.chkManual = view.chkManual;
        this.chkLlave = view.chkLlave;
        this.chkQuiniado = view.chkQuiniado;
        this.chkRayado = view.chkRayado;
        this.chkAbollado = view.chkAbollado;
        this.cmbEstado = view.cmbEstado;
        addListeners();
        this.view.setModal(true);
        this.view.setVisible(true);
    }

    public AgregarOrdenController(OrdenService os, int opcion, String[] datos) {
        this(os, opcion);
        System.out.println("AAAQAA");
        if (datos != null) {
            llenarCampos(datos);
            if (opcion == 2) {
                tittle.setText("ELIMINAR");
                btnGuardar.setColor(Colors.TEMA_BUTTONS_ROJO, Colors.BUTTONS_FONDO_ROJO);
                btnGuardar.setText("Eliminar");
                btnCancelar.setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
                txtOrden.setEditable(false);
                txtFecha.setEditable(false);
                txtKilometraje.setEditable(false);
                txtFechaEntrega.setEditable(false);
                txtCombustible.setEditable(false);
                txtObservacion.setEditable(false);
                chkTarjeta.setEnabled(false);
                chkManual.setEnabled(false);
                chkLlave.setEnabled(false);
                cmbEstado.setEnabled(false);
                chkQuiniado.setEnabled(false);
                chkRayado.setEnabled(false);
                chkAbollado.setEnabled(false);
                txtDocumento.setEditable(false);
                txtNroDocumento.setEditable(false);
                txtMecanico.setEditable(false);
                txtPlaca.setEditable(false);
            } else if (opcion == 1) {
                tittle.setText("EDITAR");
                btnGuardar.setText("Guardar Cambios");
            }
        }
    }

    private void llenarCampos(String[] datos) {
        txtOrden.setText(datos[0]);
        txtFecha.setText(datos[1]);
        txtKilometraje.setText(datos[2]);
        txtFechaEntrega.setText(datos[3]);
        txtCombustible.setText(datos[4]);
        txtObservacion.setText(datos[5]);
        chkTarjeta.setSelected("Sí".equals(datos[6]));
        chkManual.setSelected("Sí".equals(datos[7]));
        chkLlave.setSelected("Sí".equals(datos[8]));
        try {
            cmbEstado.setSelectedIndex(Integer.parseInt(datos[9]));
        } catch (Exception e) {
            cmbEstado.setSelectedIndex(0);
        }
        chkQuiniado.setSelected("Sí".equals(datos[10]));
        chkRayado.setSelected("Sí".equals(datos[11]));
        chkAbollado.setSelected("Sí".equals(datos[12]));
        txtDocumento.setText(datos[13]);
        txtNroDocumento.setText(datos[14]);
        txtMecanico.setText(datos[15]);
        txtPlaca.setText(datos[16]);
    }

    protected void addListeners() {
        btnGuardar.addActionListener(e -> {
            if (opcion == 0) {
                guardarOrden();
            } else if (opcion == 1) {
                actualizarOrden();
            } else if (opcion == 2) {
                eliminarOrden();
            }
        });
        btnCancelar.addActionListener(e -> view.dispose());
    }

    private void guardarOrden() {
        try {
            os.insertarOrden(
                txtOrden.getText(),
                txtFecha.getText(),
                Double.parseDouble(txtKilometraje.getText()),
                txtFechaEntrega.getText(),
                Double.parseDouble(txtCombustible.getText()),
                txtObservacion.getText(),
                chkTarjeta.isSelected(),
                chkManual.isSelected(),
                chkLlave.isSelected(),
                cmbEstado.getSelectedIndex(),
                chkQuiniado.isSelected(),
                chkRayado.isSelected(),
                chkAbollado.isSelected(),
                txtNroDocumento.getText(),
                Boolean.parseBoolean(txtDocumento.getText()),
                txtMecanico.getText(),
                txtPlaca.getText()
            );
            JOptionPane.showMessageDialog(view, "Orden guardada correctamente.");
            view.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al guardar la orden: " + ex.getMessage());
        }
    }

    private void actualizarOrden() {
        try {
            os.actualizarOrden(
                txtOrden.getText(),
                txtFecha.getText(),
                Double.parseDouble(txtKilometraje.getText()),
                txtFechaEntrega.getText(),
                Double.parseDouble(txtCombustible.getText()),
                txtObservacion.getText(),
                chkTarjeta.isSelected(),
                chkManual.isSelected(),
                chkLlave.isSelected(),
                cmbEstado.getSelectedIndex(),
                chkQuiniado.isSelected(),
                chkRayado.isSelected(),
                chkAbollado.isSelected(),
                txtNroDocumento.getText(),
                Boolean.parseBoolean(txtDocumento.getText()),
                txtMecanico.getText(),
                txtPlaca.getText()
            );
            JOptionPane.showMessageDialog(view, "Orden actualizada correctamente.");
            view.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al actualizar la orden: " + ex.getMessage());
        }
    }

    private void eliminarOrden() {
        try {
            os.eliminarOrden(txtOrden.getText());
            JOptionPane.showMessageDialog(view, "Orden eliminada correctamente.");
            view.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al eliminar la orden: " + ex.getMessage());
        }
    }
} 