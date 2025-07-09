package View;

import Services.ClienteService;

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

        tablaClientes = new JTable(new ClienteService().listarClientes());
        add(new JScrollPane(tablaClientes), BorderLayout.CENTER);
        setLocationRelativeTo(parent);

        btnSeleccionar = new JButton("Seleccionar");
        add(btnSeleccionar, BorderLayout.SOUTH);
    }
}