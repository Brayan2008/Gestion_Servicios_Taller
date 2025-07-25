package controllers;

import Services.OrdenService;
import View.AgregarOrdenView;
import View.SeleccionarClienteView;
import View.SeleccionarMecanicoView;
import View.SeleccionarVehiculoView;
import View.utils.RButton;
import View.utils.RTextField;

import javax.swing.*;

public class AgregarOrdenController {
    private final OrdenService os;

    AgregarOrdenView view;
    int opcion; // 0=agregar, 1=modificar, 2=eliminar
    JTable tabla;
    JLabel tittle;
    RButton btnGuardar, btnCancelar, btnBuscarCliente, btnBuscarMecanico, btnBuscarVehiculo;
    RTextField txtOrden, txtFecha, txtKilometraje, txtFechaEntrega, txtCombustible, txtObservacion, txtDocumento, txtNroDocumento, txtMecanico, txtPlaca;
    JCheckBox chkTarjeta, chkManual, chkLlave, chkQuiniado, chkRayado, chkAbollado;
    JComboBox<String> cmbEstado;

    public AgregarOrdenController(OrdenService os, int opcion) {
        
        this.view = new AgregarOrdenView(null);

        this.view.setModal(false);
        this.view.setVisible(false);
        
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


    protected void addListeners() { //Modificar quiza
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

        if (opcion == 2 || opcion == 1) {
            txtOrden.setText(tabla.getValueAt(OrdenViewController.row, 1).toString());
            txtFecha.setText(tabla.getValueAt(OrdenViewController.row, 2).toString());
            txtKilometraje.setText(tabla.getValueAt(OrdenViewController.row, 3).toString());
            txtFechaEntrega.setText(tabla.getValueAt(OrdenViewController.row, 4).toString());
            txtCombustible.setText(tabla.getValueAt(OrdenViewController.row, 5).toString());
            txtObservacion.setText(tabla.getValueAt(OrdenViewController.row, 6).toString());
        }

        // Evento seleccionar Cliente
        view.btnBuscarCliente.addActionListener(e -> {
            SeleccionarClienteView seleccionarCliente = new SeleccionarClienteView(view);
            seleccionarCliente.btnSeleccionar.addActionListener(ev -> {
                int fila = seleccionarCliente.tablaClientes.getSelectedRow();
                if (fila != -1) {
                    String tipoDoc = seleccionarCliente.tablaClientes.getValueAt(fila, 1).toString(); 
                    String nroDoc = seleccionarCliente.tablaClientes.getValueAt(fila, 0).toString();  

                    txtDocumento.setText(tipoDoc);
                    txtNroDocumento.setText(nroDoc);

                    seleccionarCliente.dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(seleccionarCliente, "Debe seleccionar una fila.");
                    }
                });
            seleccionarCliente.setVisible(true);
        });

        // Boton seleccionar Mecanico
        view.btnBuscarMecanico.addActionListener(e-> {
            SeleccionarMecanicoView selecionarMecanico = new SeleccionarMecanicoView(view);
            selecionarMecanico.btnSeleccionar1.addActionListener(ev -> {
                int fila = selecionarMecanico.tabla_Mecanicos.getSelectedRow();
                if (fila != - 1){
                    String CodMeca = selecionarMecanico.tabla_Mecanicos.getValueAt(fila, 0).toString();

                    txtMecanico.setText(CodMeca);
                    selecionarMecanico.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(selecionarMecanico, "Debe seleccionar una fila.");
                }
            });
            selecionarMecanico.setVisible(true);
        });

        // Boton seleccionar Vehiculos
        view.btnBuscarVehiculo.addActionListener(e-> {
            SeleccionarVehiculoView seleccionarVehiculo = new SeleccionarVehiculoView(view);
            seleccionarVehiculo.btnSeleccionar2.addActionListener(ev -> {
                int fila = seleccionarVehiculo.tablaVehiculos.getSelectedRow();
                if (fila != 1){
                    String CodVehi = seleccionarVehiculo.tablaVehiculos.getValueAt(fila, 0).toString();

                    txtPlaca.setText(CodVehi);
                    seleccionarVehiculo.dispose();
                } else{
                    JOptionPane.showMessageDialog(seleccionarVehiculo, "Debe seleccionar una fila.");
                }
            });
            seleccionarVehiculo.setVisible(true);
        });
   
    }

    private void guardarOrden() {
        try {
            os.insertarOrden(
                txtOrden.getText(),
                txtFecha.getText(),
                Double.parseDouble(txtKilometraje.getText().trim()),
                txtFechaEntrega.getText(),
                Double.parseDouble(txtCombustible.getText().trim()),
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