package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import Services.templates.Service;
import View.utils.Colors;
import View.utils.JViews;
import View.utils.RButton;
import controllers.AccesorioViewController;
import controllers.ClienteViewController;
import controllers.ConsultaViewController;
import controllers.DetallesCuentaController;
import controllers.Mecanico1ViewController;
import controllers.ServicioViewController;
import controllers.VehiculoViewController;
import controllers.OrdenViewController;

public class principal extends JFrame implements JViews {

    String nombre;
    JLabel icon_config;
    RButton orden, accesorios, Servicios, vehiculos, Mecanicos, Clientes, salir_Button, consultas;
    JPanel lado_derecho;

    public static principal puntero;

    public static void main(String[] args) {
        Service.getConnection();
        new principal("Pruebas");
    }

    public principal(String nombre) {
        this.nombre = nombre;
        puntero = this;
        init();
        agregarComponentes();
        addListeners();
    }

    @Override
    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 1020, 650);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());
    }

    @Override
    public void agregarComponentes() {
        lado_derecho = new JPanel();
        lado_derecho.setLayout(new GridLayout());
        lado_derecho.setBackground(Color.BLUE);
        add(lado_derecho, BorderLayout.CENTER);

        JPanel dash = new JPanel(null);
        dash.setBackground(Colors.FONDO_1);
        dash.setPreferredSize(new Dimension(220, HEIGHT));
        add(dash, BorderLayout.WEST);

        JLabel logo = new JLabel(new ImageIcon("src/resources/principal/logoTaller2.png"));
        logo.setBounds(10, 15, 170, 90);
        dash.add(logo);
        animacionDerecha(logo, -120, logo.getX());

        JLabel icon = new JLabel(new ImageIcon("src/resources/principal/perfilDefault.png"));
        icon.setBounds(10, 110, 40, 40);
        dash.add(icon);
        animacionDerecha(icon, -100, icon.getX());

        JLabel usuario = new JLabel(nombre.toUpperCase());
        usuario.setFont(Colors.SubTitles);
        usuario.setForeground(Color.WHITE);
        usuario.setBounds(62, 121, 120, 20);
        dash.add(usuario);
        FadeComponentIn(usuario);

        icon_config = new JLabel(new ImageIcon("src/resources/principal/config.png"));
        icon_config.setBounds(178, 121, 24, 24);
        icon_config.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dash.add(icon_config);
        FadeComponentIn(icon_config);

        int y = 150; // Distribuir cada "y" px
        int x = -100; // Para las animaciones

        JLabel transacciones = new JLabel("▬▬  Transacciones  ");
        transacciones.setBounds(-15, y = y + 20, 212, 25);
        transacciones.setFont(Colors.FieldBoldText);
        transacciones.setForeground(Color.WHITE);
        animacionDerecha(transacciones, x, transacciones.getX());
        dash.add(transacciones);

        orden = new RButton("    Orden de servicio")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        orden.setBounds(-18, y = y + 25, 212, 30);
        orden.setForeground(Color.WHITE);
        orden.setIcon(new ImageIcon("src/resources/principal/orden.png"));
        orden.setFont(Colors.ButtonText1);
        orden.addMouseListener(addFocus(orden));
        animacionDerecha(orden, x, orden.getX());
        dash.add(orden);

        JLabel mantenimiento = new JLabel("▬▬  Mantenimiento  ");
        mantenimiento.setBounds(-15, y = y + 40, 212, 25);
        mantenimiento.setFont(Colors.FieldBoldText);
        mantenimiento.setForeground(Color.WHITE);
        animacionDerecha(mantenimiento, x, mantenimiento.getX());
        dash.add(mantenimiento);

        accesorios = new RButton("   Accesorios          ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        accesorios.setBounds(-18, y = y + 35, 212, 30);
        accesorios.setForeground(Color.WHITE);
        accesorios.setIcon(new ImageIcon("src/resources/principal/accesorios.png"));
        accesorios.setFont(Colors.ButtonText1);
        accesorios.addMouseListener(addFocus(accesorios));
        animacionDerecha(accesorios, x -= 125, accesorios.getX());
        dash.add(accesorios);

        Servicios = new RButton("  Servicios            ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        Servicios.setBounds(-18, y = y + 40, 212, 30);
        Servicios.setForeground(Color.WHITE);
        Servicios.setIcon(new ImageIcon("src/resources/principal/services.png"));
        Servicios.setFont(Colors.ButtonText1);
        Servicios.addMouseListener(addFocus(Servicios));
        animacionDerecha(Servicios, x -= 125, Servicios.getX());
        dash.add(Servicios);

        vehiculos = new RButton("   Vehiculos           ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        vehiculos.setBounds(-18, y = y + 40, 212, 30);
        vehiculos.setIcon(new ImageIcon("src/resources/principal/vehiculos.png"));
        vehiculos.setForeground(Color.WHITE);
        vehiculos.setFont(Colors.ButtonText1);
        vehiculos.addMouseListener(addFocus(vehiculos));
        animacionDerecha(vehiculos, x -= 125, vehiculos.getX());
        dash.add(vehiculos);
        
        Mecanicos = new RButton("   Mecanicos           ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
                Mecanicos.setBounds(-18, y = y + 40, 212, 30);
        Mecanicos.setForeground(Color.WHITE);
        Mecanicos.setFont(Colors.ButtonText1);
        Mecanicos.setIcon(new ImageIcon("src/resources/principal/mecanicos.png"));
        Mecanicos.addMouseListener(addFocus(Mecanicos));
        animacionDerecha(Mecanicos, x -= 125, Mecanicos.getX());
        dash.add(Mecanicos);

        Clientes = new RButton("   Clientes             ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        Clientes.setBounds(-18, y = y + 40, 212, 30);
        Clientes.setForeground(Color.WHITE);
        Clientes.setFont(Colors.ButtonText1);
        Clientes.setIcon(new ImageIcon("src/resources/principal/clientes.png"));
        animacionDerecha(Clientes, x -= 125, Clientes.getX());
        Clientes.addMouseListener(addFocus(Clientes));
        dash.add(Clientes);
        
        consultas = new RButton("   Consultas            ")
        .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
        .setTextColor(Color.white, Color.white);
        consultas.setBounds(-18, y = y + 90, 212, 30);
        consultas.setFont(Colors.FieldBoldText);
        consultas.setIcon(new ImageIcon("src/resources/principal/consultas.png"));
        consultas.setForeground(Color.WHITE);
        consultas.addMouseListener(addFocus(consultas));
        animacionDerecha(consultas, x-= 125, consultas.getX());
        dash.add(consultas);
        
        salir_Button = new RButton("   Salir                    ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        salir_Button.setBounds(-18, y = y + 40, 212, 30);
        salir_Button.setForeground(Color.WHITE);
        salir_Button.setFont(Colors.ButtonText1);
        salir_Button.setIcon(new ImageIcon("src/resources/principal/salir.png"));
        animacionDerecha(salir_Button, x -= 125, salir_Button.getX());
        salir_Button.addMouseListener(addFocus(salir_Button));
        dash.add(salir_Button);
    }

    // #region Listeners
    public MouseAdapter addFocus(JComponent component) {
        return new MouseAdapter() {
            Timer a = animacionCrecer(component, 8);
            Timer b = animacionEncoger(component, 8);

            @Override
            public void mouseEntered(MouseEvent e) {
                b.stop();
                a.restart();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                a.stop();
                b.restart();
            }
        };
    }

    public void addListeners() {
        icon_config.addMouseListener(abrirConfig());
        accesorios.addActionListener(e -> abrirAccesorios());
        Clientes.addActionListener(e -> abrirCliente());
        Mecanicos.addActionListener(e -> abrirMecanicos());
        orden.addActionListener(e -> abrirOrden());
        Servicios.addActionListener(e -> abrirServicios());
        vehiculos.addActionListener(e -> abrirVehiculos());
        consultas.addActionListener(e -> abrirConsultas());
        salir_Button.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de salir?",
                    "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                this.dispose();
                SwingUtilities.invokeLater(() -> new App2());
            }
        });
    }

    public MouseAdapter abrirConfig() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lado_derecho.removeAll();
                var user_config = new DetallesCuentaController();
                lado_derecho.add(user_config.getVw());
                revalidate();
                repaint();
            }
        };
    }

    public void abrirAccesorios() {
        lado_derecho.removeAll();
        var accesorio = new AccesorioViewController();
        lado_derecho.add(accesorio.getVw());
        revalidate();
        repaint();
    }

    public void abrirCliente() {
        lado_derecho.removeAll();
        var cliente = new ClienteViewController();
        lado_derecho.add(cliente.getVw());
        revalidate();
        repaint();
    }

    public void abrirMecanicos() {
        lado_derecho.removeAll();
        lado_derecho.setBackground(Color.black);
        var mecanico = new Mecanico1ViewController();
        lado_derecho.add(mecanico.getVw());
        revalidate();
        repaint();
    }

    public void abrirOrden() {
        lado_derecho.removeAll();
        var orden = new OrdenViewController();
        lado_derecho.add(orden.getVw());
        revalidate();
        repaint();
    }

    public void abrirServicios() {
        lado_derecho.removeAll();
        var servicio = new ServicioViewController();
        lado_derecho.add(servicio.getVw());
        revalidate();
        repaint();
    }

    public void abrirVehiculos() {
        lado_derecho.removeAll();
        var vehiculo = new VehiculoViewController();
        lado_derecho.add(vehiculo.getVw());
        revalidate();
        repaint();
    }

    public void abrirConsultas() {
        lado_derecho.removeAll();
        var consultas = new ConsultaViewController();
        lado_derecho.add(consultas.getVw());
        revalidate();
        repaint(); 
    }
    // #endregion
}