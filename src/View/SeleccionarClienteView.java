package View;

import View.utils.Colors;

import javax.swing.*;
import java.awt.*;

public class SeleccionarClienteView extends JDialog {
    public JTable tablaClientes;
    public JButton btnSeleccionar;

    public SeleccionarClienteView(Window parent) {
        super(parent);
        setModal(true);
        setSize(800, 400);
        setLayout(new BorderLayout());

        JLabel lista_label = new JLabel("Lista de Clientes");
        lista_label.setFont(Colors.Titles);
        add(lista_label, BorderLayout.NORTH);

        new ClienteView();

        tablaClientes = ClienteView.tablaServicios;
        add(new JScrollPane(tablaClientes), BorderLayout.CENTER);
        setLocationRelativeTo(parent);

        btnSeleccionar = new JButton("Seleccionar");
        add(btnSeleccionar, BorderLayout.SOUTH);
    }
}