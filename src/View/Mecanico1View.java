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

import Services.Mecanico1Service;

import View.utils.Colors;
import View.utils.JViews;
import View.utils.RButton;
import View.utils.RTextField;

public class Mecanico1View extends JPanel implements JViews {

    public static final Mecanico1Service css = new Mecanico1Service();//---- cambiar a controller

    public RButton btnAdd = new RButton("Agregar")
                                .setTamaño(120,25)
                                .setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
    public RButton btnMod = new RButton("Modificar")
                                .setTamaño(120, 25);
    public RButton btnDel = new RButton("Eliminar")
                                .setTamaño(120, 25);
    public JPanel PCentral = new JPanel();
    public RTextField buscar;
    public JTable tablaMecanicos; // tabla para mostrar servicios

    public static void main(String[] args) {
        JFrame prueba = new JFrame("Gestión de Accesorios");
        ClienteView vista = new ClienteView();

        prueba.add(vista);
        prueba.setBounds(100, 100, 700, 600);
        prueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prueba.setVisible(true);
    }

    public Mecanico1View() {
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
        
        JLabel titulo = new JLabel("LISTA DE MECANICOS");
        titulo.setFont(Colors.Titles);
        titulo.setBounds(50, 20, 250, 40);
        add(titulo, gbc);
        
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMod.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        tablaMecanicos = new JTable(css.listarMecanicos());
        tablaMecanicos.setGridColor(Colors.LIGHT_GRAY);
        tablaMecanicos.setRowHeight(23);
        tablaMecanicos.setRowMargin(5);
        tablaMecanicos.getTableHeader().setBackground(Colors.FONDO_1);
        tablaMecanicos.getTableHeader().setForeground(Color.WHITE);
        tablaMecanicos.getTableHeader().setFont(Colors.ButtonText1);
        tablaMecanicos.setShowHorizontalLines(true);
        tablaMecanicos.setShowVerticalLines(false);

        DefaultTableCellRenderer tb = new DefaultTableCellRenderer();
        tb.setHorizontalAlignment(JLabel.CENTER);
        tablaMecanicos.getColumnModel().getColumn(0).setCellRenderer(tb);
        tablaMecanicos.getColumnModel().getColumn(1).setCellRenderer(tb);
        
        JScrollPane scrollPane = new JScrollPane(tablaMecanicos);
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
        buscar.setToolTipText("Buscar Mecanico");
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

    public JTable getTablaMecanicos() {
        return tablaMecanicos;
    }
    
}