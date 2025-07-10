package View;

import javax.swing.*;

import View.utils.Colors;

import java.awt.*;

public class SeleccionarMecanicoView extends JDialog {
    public JTable tabla_Mecanicos;
    public JButton btnSeleccionar1;

    public SeleccionarMecanicoView(Window parent) {
        super(parent);
        setModal(true);
        setSize(800, 400);
        setLayout(new BorderLayout());

        JLabel lista_label = new JLabel("Lista de Mecanicos Disponibles");
        lista_label.setFont(Colors.Titles);
        add(lista_label, BorderLayout.NORTH);


        new Mecanico1View();

        tabla_Mecanicos = Mecanico1View.tablaMecanicos;
        add(new JScrollPane(tabla_Mecanicos), BorderLayout.CENTER);
        setLocationRelativeTo(parent);

        btnSeleccionar1 = new JButton("Seleccionar");
        add(btnSeleccionar1, BorderLayout.SOUTH);
    }
}
