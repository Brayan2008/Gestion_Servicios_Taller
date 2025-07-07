package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Services.CatalogoServiciosService;
import View.utils.Colors;
import View.utils.JViews;
import View.utils.RButton;
import View.utils.RTextField;

public class CatalogoServiciosView extends JPanel implements JViews {

    public static final CatalogoServiciosService css = new CatalogoServiciosService();//---- cambiar a controller

    public RButton btnAdd = new RButton("Agregar")
                                .setTamaño(120,25)
                                .setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
    public RButton btnMod = new RButton("Modificar")
                                .setTamaño(120, 25);
    public RButton btnDel = new RButton("Eliminar")
                                .setTamaño(120, 25);
    public JPanel PCentral = new JPanel();
    public RTextField buscar;
    public JTable tablaServicios; // tabla para mostrar servicios

    public static void main(String[] args) {
        JFrame prueba = new JFrame("Gestión de Servicios");
        ClienteView vista = new ClienteView();

        prueba.add(vista);
        prueba.setBounds(100, 100, 700, 600);
        prueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prueba.setVisible(true);
    }

    public CatalogoServiciosView() {
        init();
        agregarComponentes();
    }

    @Override
    public void init() {
        setLayout(new GridBagLayout());
    }

    @Override
    public void agregarComponentes() {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridwidth = 4;
        
        JLabel titulo = new JLabel("LISTA DE SERVICIOS");
        titulo.setFont(Colors.Titles);
        titulo.setBounds(50, 20, 250, 40);
        add(titulo, gbc);
        
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMod.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        tablaServicios = new JTable(css.listarServicios());
        tablaServicios.setGridColor(Colors.LIGHT_GRAY);
        tablaServicios.setRowHeight(23);
        tablaServicios.setRowMargin(5);
        tablaServicios.getTableHeader().setBackground(Colors.FONDO_1);
        tablaServicios.getTableHeader().setForeground(Color.WHITE);
        tablaServicios.getTableHeader().setFont(Colors.ButtonText1);
        tablaServicios.setShowHorizontalLines(true);
        tablaServicios.setShowVerticalLines(false);

        DefaultTableCellRenderer tb = new DefaultTableCellRenderer();
        tb.setHorizontalAlignment(JLabel.CENTER);
        tablaServicios.getColumnModel().getColumn(0).setCellRenderer(tb);
        tablaServicios.getColumnModel().getColumn(2).setCellRenderer(tb);
        
        JScrollPane scrollPane = new JScrollPane(tablaServicios);
        scrollPane.setBorder(new EmptyBorder(5,5, 5, 5));
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 50, 30, 50);
        gbc.gridwidth = 4;
        gbc.fill = 2;  
        add(scrollPane, gbc);
        
        JLabel buscar_label = new JLabel("Buscar: ");
        buscar_label.setFont(Colors.SubTitles);
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 60, 10, 2);
        add(buscar_label,gbc);
        
        buscar = new RTextField(2, 30, Color.WHITE);
        buscar.setToolTipText("Buscar Servicio");
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 150, 10, 100);
        gbc.fill = 1;
        add(buscar, gbc);
        
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = 0;
        gbc.insets = new Insets(0, 52, 10, 50);
        add(btnAdd,gbc);
        
        gbc.fill = 1;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 50, 10, 5);
        add(btnMod,gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 20, 10, 52);
        add(btnDel,gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 30, 10, 100);
        add(Box.createHorizontalStrut(50),gbc);
    }

    public JPanel getPCentral() {
        return PCentral;
    }

    public JTable getTablaServicios() {
        return tablaServicios;
    }
    
}