package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Services.OrdenService;
import View.utils.Colors;
import View.utils.JViews;
import View.utils.RButton;
import View.utils.RTextField;

public class OrdenView extends JPanel implements JViews {
    public final OrdenService os = new OrdenService();

    public RButton btnAdd = new RButton("Agregar")
                                .setTamaño(120, 25)
                                .setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
    public RButton btnMod = new RButton("Modificar")
                                .setTamaño(120, 25);
    public RButton btnDel = new RButton("Eliminar")
                                .setTamaño(120, 25);
    public JPanel PCentral = new JPanel();
    public RTextField buscar;
    public JTable tablaOrdenes;


    public OrdenView() {
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

        JLabel titulo = new JLabel("LISTA DE ÓRDENES");
        titulo.setFont(Colors.Titles);
        titulo.setBounds(50, 20, 250, 40);
        add(titulo, gbc);

        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMod.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tablaOrdenes = new JTable(os.listarOrdenes());
        tablaOrdenes.setGridColor(Colors.LIGHT_GRAY);
        tablaOrdenes.setRowHeight(23);
        tablaOrdenes.setRowMargin(5);
        tablaOrdenes.getTableHeader().setBackground(Colors.FONDO_1);
        tablaOrdenes.getTableHeader().setForeground(Color.WHITE);
        tablaOrdenes.getTableHeader().setFont(Colors.ButtonText1);
        tablaOrdenes.setShowHorizontalLines(true);
        tablaOrdenes.setShowVerticalLines(false);

        DefaultTableCellRenderer tb = new DefaultTableCellRenderer();
        tb.setHorizontalAlignment(JLabel.CENTER);
        tablaOrdenes.getColumnModel().getColumn(0).setCellRenderer(tb);
        tablaOrdenes.getColumnModel().getColumn(2).setCellRenderer(tb);

        JScrollPane scrollPane = new JScrollPane(tablaOrdenes);
        scrollPane.setBorder(new EmptyBorder(5,5, 5, 5));
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 20, 30, 20);
        gbc.gridwidth = 4;
        gbc.fill = 2;  
        add(scrollPane, gbc);

        JLabel buscar_label = new JLabel("Buscar: ");
        buscar_label.setFont(Colors.SubTitles);
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 30, 10, 2);
        add(buscar_label,gbc);

        buscar = new RTextField(2, 30, Color.WHITE);
        buscar.setToolTipText("Buscar Orden");
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 120, 10, 80);
        gbc.fill = 1;
        add(buscar, gbc);

        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = 0;
        gbc.insets = new Insets(0, 32, 10, 30);
        add(btnAdd,gbc);

        gbc.fill = 1;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 30, 10, 5);
        add(btnMod,gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 10, 32);
        add(btnDel,gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 30, 10, 80);
        add(Box.createHorizontalStrut(50),gbc);
    }

    public JPanel getPCentral() {
        return PCentral;
    }

    public JTable getTablaOrdenes() {
        return tablaOrdenes;
    }
}
