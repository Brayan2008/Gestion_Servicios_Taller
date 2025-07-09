package View;
import Services.Mecanico1Service;

import javax.swing.*;
import java.awt.*;

public class SeleccionarMecanicoView extends JDialog {
    public JTable tablaMecanicos;
    public JButton btnSeleccionar1;

    public SeleccionarMecanicoView(Window parent) {
        super(parent);
        setModal(true);
        setSize(800, 400);
        setLayout(new BorderLayout());

        tablaMecanicos = new JTable(new Mecanico1Service().listarMecanicos());
        add(new JScrollPane(tablaMecanicos), BorderLayout.CENTER);
        setLocationRelativeTo(parent);

        btnSeleccionar1 = new JButton("Seleccionar");
        add(btnSeleccionar1, BorderLayout.SOUTH);
    }
}
