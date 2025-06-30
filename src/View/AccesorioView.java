package View;

import Services.AccesoriosService;
import Services.CatalogoServiciosService;
import View.utils.JViews;


import javax.swing.*;
import java.awt.*;

public class AccesorioView extends JPanel implements JViews {

    private final AccesoriosService css = new AccesoriosService() 
    public JButton btnAdd = new JButton("Agregar Accesorios");
    public JButton btnMod = new JButton("Modificar Accesorios");
    public JButton btnDel = new JButton("Eliminar Accesorios");
    public JPanel PCentral = new JPanel();
    private JTable tablaServicios; // tabla para mostrar Accesorios

    public static void main(String[] args) {
        JFrame prueba = new JFrame("Gestión de Accesorios");
        AccesorioView vista = new AccesorioView();

        prueba.add(vista);
        prueba.setBounds(100, 100, 700, 500);
        prueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prueba.setVisible(true);
    }

    public AccesorioView() {
        init();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);

        // Panel lateral derecho
        JPanel PanelDerecho = new JPanel();
        PanelDerecho.setLayout(new BoxLayout(PanelDerecho, BoxLayout.Y_AXIS));
        PanelDerecho.setBackground(Color.DARK_GRAY);
        PanelDerecho.setPreferredSize(new Dimension(170, 0));

        Dimension dimensionBoton = new Dimension(150, 35);
        btnAdd.setMaximumSize(dimensionBoton);
        btnMod.setMaximumSize(dimensionBoton);
        btnDel.setMaximumSize(dimensionBoton);

        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMod.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDel.setAlignmentX(Component.CENTER_ALIGNMENT);

        PanelDerecho.add(Box.createVerticalStrut(20));
        PanelDerecho.add(btnAdd);
        PanelDerecho.add(Box.createVerticalStrut(10));
        PanelDerecho.add(btnMod);
        PanelDerecho.add(Box.createVerticalStrut(10));
        PanelDerecho.add(btnDel);

        this.add(PanelDerecho, BorderLayout.EAST);

        // Panel central
        PCentral.setLayout(new BorderLayout());
        PCentral.setBackground(Color.WHITE);

        tablaServicios = new JTable(css.listarServicios());
        JScrollPane scrollPane = new JScrollPane(tablaServicios);
        PCentral.add(scrollPane, BorderLayout.CENTER);

        this.add(PCentral, BorderLayout.CENTER);
    }

    @Override
    public void init() {}

    @Override
    public void agregarComponentes() {}

    public JPanel getPCentral() {
        return PCentral;
    }

    public JTable getTablaServicios() {
        return tablaServicios;
    }
}