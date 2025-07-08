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

        this.view.setVisible(false);
        this.view.setModal(false);

        this.os = os;

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
        this.view.setModal(true); // Establece como Modal y luego bloquea
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
        
        if ((opcion == 2 || opcion == 1) && tabla != null) {
            int row = tabla.getSelectedRow();
            if (row >= 0) {
                txtOrden.setText(tabla.getValueAt(row, 0).toString().trim());
                txtOrden.setFont(Colors.SubTitles);
                txtOrden.setEditable(false);
                txtFecha.setText(tabla.getValueAt(row, 1).toString());
                txtKilometraje.setText(tabla.getValueAt(row, 2).toString());
                txtFechaEntrega.setText(tabla.getValueAt(row, 3).toString());
                txtCombustible.setText(tabla.getValueAt(row, 4).toString());
                txtObservacion.setText(tabla.getValueAt(row, 5).toString());
                chkTarjeta.setSelected("Sí".equals(tabla.getValueAt(row, 6).toString()));
                chkManual.setSelected("Sí".equals(tabla.getValueAt(row, 7).toString()));
                chkLlave.setSelected("Sí".equals(tabla.getValueAt(row, 8).toString()));
                cmbEstado.setSelectedIndex(Integer.parseInt(tabla.getValueAt(row, 9).toString()));
                chkQuiniado.setSelected("Sí".equals(tabla.getValueAt(row, 10).toString()));
                chkRayado.setSelected("Sí".equals(tabla.getValueAt(row, 11).toString()));
                chkAbollado.setSelected("Sí".equals(tabla.getValueAt(row, 12).toString()));
                txtDocumento.setText(tabla.getValueAt(row, 13).toString());
                txtNroDocumento.setText(tabla.getValueAt(row, 14).toString());
                txtMecanico.setText(tabla.getValueAt(row, 15).toString());
                txtPlaca.setText(tabla.getValueAt(row, 16).toString());
                
                switch (opcion) {
                    case 1:
                        tittle.setText("EDITAR");
                        break;
                    case 2:
                        tittle.setText("ELIMINAR");
                        btnGuardar.setColor(Colors.TEMA_BUTTONS_ROJO, Colors.BUTTONS_FONDO_ROJO);
                        btnGuardar.setText("Eliminar");
                        btnCancelar.setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
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
                        btnGuardar.removeActionListener(btnGuardar.getActionListeners()[0]);
                        btnGuardar.addActionListener(e -> eliminarOrden());
                    default:
                        break;
                }
            }
        }
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
                txtDocumento.getText(),
                true, // Suponiendo tipo documento true (ajustar según lógica)
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