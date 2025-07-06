package View;

import Services.CatalogoServiciosService;
import View.utils.JViews;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class CatalogoServiciosView extends JPanel implements JViews {

    private final CatalogoServiciosService css = new CatalogoServiciosService();
    public JButton btnAdd = new JButton("Agregar Servicios");
    public JButton btnMod = new JButton("Modificar Servicios");
    public JButton btnDel = new JButton("Eliminar Servicios");
    public JPanel PCentral = new JPanel();
    private JTable tablaServicios; // tabla para mostrar servicios

    public static void main(String[] args) {
        JFrame prueba = new JFrame("Gestión de Servicios");
        CatalogoServiciosView vista = new CatalogoServiciosView();

        prueba.add(vista);
        prueba.setBounds(100, 100, 700, 500);
        prueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prueba.setVisible(true);
    }

    public CatalogoServiciosView() {
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

        // eventos
        btnAdd.addActionListener(e -> mostrarVentanaAgregar());

    }

    private void mostrarVentanaAgregar() {
        new VentanaAgregarServicio(null,this, css);
    }

    @Override
    public void init() {
    }

    @Override
    public void agregarComponentes() {
    }

    public void cargarDatosServicios() {
    try {
        JTable tabla = new JTable(css.listarServicios());
        tabla.setEnabled(false); // no editable

        PCentral.removeAll();
        PCentral.add(new JScrollPane(tabla), BorderLayout.CENTER);
        PCentral.revalidate();
        PCentral.repaint();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error cargando datos: " + ex.getMessage());
    }
}

    public JPanel getPCentral() {
        return PCentral;
    }

    public JTable getTablaServicios() {
        return tablaServicios;
    }
}
