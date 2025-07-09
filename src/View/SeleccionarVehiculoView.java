package View;

import Services.VehiculoService;

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

        tablaVehiculos = new JTable(new VehiculoService().listarVehiculos());
        add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);
        setLocationRelativeTo(parent);

        btnSeleccionar2 = new JButton("Seleccionar");
        add(btnSeleccionar2, BorderLayout.SOUTH);
    }
    
}
