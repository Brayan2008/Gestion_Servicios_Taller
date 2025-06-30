package controllers;
import Services.CatalogoServiciosService;
import View.CatalogoServiciosView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogoServiciosViewController {
    private CatalogoServiciosService css;
    private CatalogoServiciosView csv;

    public CatalogoServiciosViewController(CatalogoServiciosView csv) {
        this.csv = csv;
        this.css = new CatalogoServiciosService();

        // Eventos de botones
        this.csv.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaAgregar();
            }
        });

        this.csv.btnMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaModificar();
            }
        });

        this.csv.btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaEliminar();
            }
        });

        // cargar tabla al inicio
        mostrarListaServicios();
    }

    private void mostrarListaServicios() {
        JTable tabla = css.obtenerTablaServicios();
        JScrollPane scrollPane = new JScrollPane(tabla);
        csv.getPCentral().removeAll();
        csv.getPCentral().add(scrollPane);
        csv.getPCentral().revalidate();
        csv.getPCentral().repaint();
    }

    private void mostrarVentanaAgregar() {
        new VentanaAgregarServicio(SwingUtilities.getWindowAncestor(csv),
        css);
    }

    
}
