package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Services.OrdenService;
import View.utils.Colors;
import View.utils.JViews;
import View.utils.RButton;
import View.utils.RTextField;

public class ConsultasView extends JPanel implements JViews {

    public static final OrdenService os = new OrdenService();

    JTable tabla_consultas;
    JPanel panel;
    JPanel tablaPanel;

    public RTextField nro_field, fecha_generada, fecha_entrega, cliente_orden, vehiculo_orden, mecanico_orden;
    public JComboBox<String> estado_orden;
    public RButton buscar_cliente, buscar_mecanico, buscar_vehiculo, generar, btn_filtrar;

    public ConsultasView() {
        init();
    }

    @Override
    public void init() {
        setLayout(new GridBagLayout());
        new OrdenView();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel consultas_label = new JLabel("Consultas y reportes");
        consultas_label.setFont(Colors.Titles);
        add(consultas_label, gbc);

        agregarComponentes();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(panel, gbc);

        tablaPanel = new JPanel(null);
        this.tabla_consultas = OrdenView.tablaOrdenes;
        JScrollPane tab = new JScrollPane(tabla_consultas);
        tab.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        tab.setBounds(0, 5, 1080, 400);
        tablaPanel.add(tab);
        gbc.gridy = 3;
        gbc.weighty = 2;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 10, 5, 10);

        add(tablaPanel, gbc);
    }

    @Override
    public void agregarComponentes() {

        int y = 0;
        int h = 40;

        panel = new JPanel(null);

        JLabel nro_label = new JLabel("Numero de Orden: ");
        nro_label.setBounds(8, y = y + h, 150, 20);
        nro_label.setFont(Colors.SubTitles);
        panel.add(nro_label);

        nro_field = new RTextField(150, 20, Color.WHITE);
        nro_field.setBounds(160, y + 2, 200, 20);
        panel.add(nro_field);

        btn_filtrar = new RButton("Filtrar").setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
        btn_filtrar.setBounds(460,y,80,20);
        panel.add(btn_filtrar);

        generar = new RButton("Generar PDF").setColor(Colors.TEMA_BUTTONS3, Colors.BUTTONS_FONDO_3);
        generar.setBounds(560,y,100,20);
        panel.add(generar);

        JLabel fecha_label_1 = new JLabel("Fecha generada: ");
        fecha_label_1.setBounds(8, y += h, 150, 20);
        fecha_label_1.setFont(Colors.SubTitles);
        panel.add(fecha_label_1);

        fecha_generada = new RTextField(150, 20, Color.WHITE);
        fecha_generada.setBounds(150, y + 2, 200, 20);
        panel.add(fecha_generada);

        JLabel fecha_label_2 = new JLabel("Fecha de entrega: ");
        fecha_label_2.setBounds(370, y, 150, 20);
        fecha_label_2.setFont(Colors.SubTitles);
        panel.add(fecha_label_2);

        fecha_entrega = new RTextField(150, 20, Color.WHITE);
        fecha_entrega.setBounds(530, y + 2, 200, 20);
        panel.add(fecha_entrega);

        JLabel estado_label = new JLabel("Estado de la orden:");
        estado_label.setBounds(8, y += h, 160, 20);
        estado_label.setFont(Colors.SubTitles);
        panel.add(estado_label);

        String[] estados = { "0.- Pendiente", "1.- En proceso ", "2.- Finalizado ", "3. - Cancelado" };
        estado_orden = new JComboBox<String>(estados);
        estado_orden.setBounds(180, y, 150, 20);
        panel.add(estado_orden);

        JLabel mecanico_label = new JLabel("Mecanico:");
        mecanico_label.setBounds(390, y, 100, 20);
        mecanico_label.setFont(Colors.SubTitles);
        panel.add(mecanico_label);

        mecanico_orden = new RTextField(150, 20, Color.WHITE);
        mecanico_orden.setBounds(480, y + 2, 150, 20);
        panel.add(mecanico_orden);

        buscar_mecanico = new RButton("Buscar").setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
        buscar_mecanico.setBounds(640, y, 120, 20);
        panel.add(buscar_mecanico);

        JLabel cliente_label = new JLabel("Cliente:");
        cliente_label.setBounds(8, y += h, 80, 20);
        cliente_label.setFont(Colors.SubTitles);
        panel.add(cliente_label);

        cliente_orden = new RTextField(150, 20, Color.WHITE);
        cliente_orden.setBounds(95, y, 150, 20);
        panel.add(cliente_orden);

        buscar_cliente = new RButton("Buscar").setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
        buscar_cliente.setBounds(260, y, 120, 20);
        panel.add(buscar_cliente);

        JLabel vehiculo_label = new JLabel("Vehiculo:");
        vehiculo_label.setBounds(390, y, 100, 20);
        vehiculo_label.setFont(Colors.SubTitles);
        panel.add(vehiculo_label);

        vehiculo_orden = new RTextField(150, 20, Color.white);
        vehiculo_orden.setBounds(480, y, 150, 20);
        panel.add(vehiculo_orden);

        buscar_vehiculo = new RButton("Buscar").setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
        buscar_vehiculo.setBounds(640, y, 120, 20);
        panel.add(buscar_vehiculo);

        animacionDerecha(nro_label, nro_label.getX() - 100, nro_label.getX());
        animacionDerecha(nro_field, nro_field.getX() - 100, nro_field.getX());
        animacionDerecha(fecha_label_1, fecha_label_1.getX() - 100, fecha_label_1.getX());
        animacionDerecha(fecha_generada, fecha_generada.getX() - 100, fecha_generada.getX());
        animacionDerecha(fecha_label_2, fecha_label_2.getX() - 100, fecha_label_2.getX());
        animacionDerecha(fecha_entrega, fecha_entrega.getX() - 100, fecha_entrega.getX());
        animacionDerecha(estado_label, estado_label.getX() - 100, estado_label.getX());
        animacionDerecha(estado_orden, estado_orden.getX() - 100, estado_orden.getX());
        animacionDerecha(mecanico_label, mecanico_label.getX() - 100, mecanico_label.getX());
        animacionDerecha(mecanico_orden, mecanico_orden.getX() - 100, mecanico_orden.getX());
        animacionDerecha(buscar_mecanico, buscar_mecanico.getX() - 100, buscar_mecanico.getX());
        animacionDerecha(cliente_label, cliente_label.getX() - 100, cliente_label.getX());
        animacionDerecha(cliente_orden, cliente_orden.getX() - 100, cliente_orden.getX());
        animacionDerecha(buscar_cliente, buscar_cliente.getX() - 100, buscar_cliente.getX());
        animacionDerecha(vehiculo_label, vehiculo_label.getX() - 100, vehiculo_label.getX());
        animacionDerecha(vehiculo_orden, vehiculo_orden.getX() - 100, vehiculo_orden.getX());
        animacionDerecha(buscar_vehiculo, buscar_vehiculo.getX() - 100, buscar_vehiculo.getX());
    }

    public ConsultasView getVw() {
        return this;
    }
}
