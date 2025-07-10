package controllers;

import View.ConsultasView;
import View.SeleccionarClienteView;
import View.SeleccionarMecanicoView;
import View.SeleccionarVehiculoView;
import View.principal;
import View.utils.RButton;
import View.utils.RTextField;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import Services.templates.Service;

public class ConsultaViewController {

    ConsultasView vw;

    JTable tabla;

    RButton btnGenerar, btn_filtrar, btn_Cliente, btn_mecanico, btn_vehiculo;
    RTextField nro_field, fecha_gen, fecha_entrega, cliente_orden, mecanico_orden, vehiculo_orden;
    JComboBox<String> estado_orden;

    public ConsultaViewController() {

        this.vw = new ConsultasView();

        this.btnGenerar = vw.generar;
        this.btn_filtrar = vw.btn_filtrar;
        this.btn_Cliente = vw.buscar_cliente;
        this.btn_vehiculo = vw.buscar_vehiculo;
        this.btn_mecanico = vw.buscar_mecanico;

        this.nro_field = vw.nro_field;
        this.fecha_gen = vw.fecha_generada;
        this.fecha_entrega = vw.fecha_entrega;
        this.cliente_orden = vw.cliente_orden;
        this.vehiculo_orden = vw.vehiculo_orden;
        this.mecanico_orden = vw.mecanico_orden;
        this.estado_orden = vw.estado_orden;

        addListeners();

        this.vw.setVisible(true);
    }

    protected void addListeners() { // Modificar quiza
        btnGenerar.addActionListener(e -> {
            try {
                String rutaReporte = "src/report/Prueba.jasper";

                JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(rutaReporte));

                String cadena = nro_field.getText().trim() + fecha_gen.getText().trim()
                        + fecha_entrega.getText().trim() + cliente_orden.getText().trim()
                        + mecanico_orden.getText().trim() + vehiculo_orden.getText().trim();

                Map<String, Object> parametros = new HashMap<>();
                parametros.put("cadena", cadena);

                JasperPrint print = JasperFillManager.fillReport(reporte, parametros, Service.puntero);

                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setTitle("Vista de Reporte");
                viewer.setVisible(true);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        btn_Cliente.addActionListener(e -> {
            SeleccionarClienteView seleccionarCliente = new SeleccionarClienteView(principal.puntero);
            seleccionarCliente.btnSeleccionar.addActionListener(ev -> {
                int fila = seleccionarCliente.tablaClientes.getSelectedRow();
                if (fila != -1) {
                    String nroDoc = seleccionarCliente.tablaClientes.getValueAt(fila, 0).toString();

                    cliente_orden.setText(nroDoc);

                    seleccionarCliente.dispose();
                } else {
                    JOptionPane.showMessageDialog(seleccionarCliente, "Debe seleccionar una fila.");
                }
            });
            seleccionarCliente.setVisible(true);
        });
        btn_mecanico.addActionListener(e -> {
            SeleccionarMecanicoView selecionarMecanico = new SeleccionarMecanicoView(principal.puntero);
            selecionarMecanico.btnSeleccionar1.addActionListener(ev -> {
                int fila = selecionarMecanico.tabla_Mecanicos.getSelectedRow();
                if (fila != -1) {
                    String CodMeca = selecionarMecanico.tabla_Mecanicos.getValueAt(fila, 0).toString();

                    mecanico_orden.setText(CodMeca);
                    selecionarMecanico.dispose();
                } else {
                    JOptionPane.showMessageDialog(selecionarMecanico, "Debe seleccionar una fila.");
                }
            });
            selecionarMecanico.setVisible(true);
        });

        btn_vehiculo.addActionListener(e -> {
            SeleccionarVehiculoView seleccionarVehiculo = new SeleccionarVehiculoView(principal.puntero);
            seleccionarVehiculo.btnSeleccionar2.addActionListener(ev -> {
                int fila = seleccionarVehiculo.tablaVehiculos.getSelectedRow();
                if (fila != 1) {
                    String CodVehi = seleccionarVehiculo.tablaVehiculos.getValueAt(fila, 0).toString();

                    vehiculo_orden.setText(CodVehi);
                    seleccionarVehiculo.dispose();
                } else {
                    JOptionPane.showMessageDialog(seleccionarVehiculo, "Debe seleccionar una fila.");
                }
            });
            seleccionarVehiculo.setVisible(true);
        });

        btn_filtrar.addActionListener(e -> {
            try {
                ConsultasView.os.buscarOrden(nro_field.getText().trim() + fecha_gen.getText().trim()
                        + fecha_entrega.getText().trim() + cliente_orden.getText().trim()
                        + mecanico_orden.getText().trim() + vehiculo_orden.getText().trim());
                JOptionPane.showMessageDialog(vw, "Los datos fueron filtrados");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vw, "Error al filtrar orden: " + ex.getMessage());
            }
        });

    }

    public ConsultasView getVw() {
        return vw;
    }
}