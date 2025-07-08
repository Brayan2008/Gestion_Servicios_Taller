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