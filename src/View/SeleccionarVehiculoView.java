package View;

import View.utils.Colors;

import javax.swing.*;
import java.awt.*;

public class SeleccionarVehiculoView extends JDialog {
    public JTable tablaVehiculos;
    public JButton btnSeleccionar2;

    public SeleccionarVehiculoView(Window parent) {
        super(parent);
        setModal(true);
        setSize(800, 400);
        setLayout(new BorderLayout());

        JLabel lista_label = new JLabel("Lista de Vehiculos");
        lista_label.setFont(Colors.Titles);
        add(lista_label, BorderLayout.NORTH);

        new VehiculoView();

        tablaVehiculos = VehiculoView.tablaVehiculos;
        add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);
        setLocationRelativeTo(parent);

        btnSeleccionar2 = new JButton("Seleccionar");
        add(btnSeleccionar2, BorderLayout.SOUTH);
    }
    
}
