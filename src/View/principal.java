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
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import View.utils.Colors;
import View.utils.JViews;
import View.utils.RButton;

public class principal extends JFrame implements JViews {

    String nombre;

    JLabel icon_config;
    RButton orden, accesorios, Servicios, vehiculos, Mecanicos, Clientes;
    JPanel lado_derecho;

    public static void main(String[] args) {
        new principal("Pruebas");
    }

    public principal(String nombre) {
        this.nombre = nombre;
        init();
        agregarComponentes();
        addListeners();
    }

    @Override
    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 700, 550);
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

        int y = 170; // Distribuir cada 120 px
        int x = -100; // Para las animaciones

        orden = new RButton("▬▬ Orden de sercicio")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        orden.setBounds(-18, y, 212, 50);
        orden.setForeground(Color.WHITE);
        orden.setFont(Colors.ButtonText1);
        orden.addMouseListener(addFocus(orden));
        animacionDerecha(orden, x, orden.getX());
        dash.add(orden);

        accesorios = new RButton("▬▬ Accesorios           ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        accesorios.setBounds(-18, y = y + 55, 212, 50);
        accesorios.setForeground(Color.WHITE);
        accesorios.setFont(Colors.ButtonText1);
        accesorios.addMouseListener(addFocus(accesorios));
        animacionDerecha(accesorios, x -= 125, accesorios.getX());
        dash.add(accesorios);

        Servicios = new RButton("▬▬ Servicios              ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        Servicios.setBounds(-18, y = y + 55, 212, 50);
        Servicios.setForeground(Color.WHITE);
        Servicios.setFont(Colors.ButtonText1);
        Servicios.addMouseListener(addFocus(Servicios));
        animacionDerecha(Servicios, x -= 125, Servicios.getX());
        dash.add(Servicios);

        vehiculos = new RButton("▬▬ Vehiculos              ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        vehiculos.setBounds(-18, y = y + 55, 212, 50);
        vehiculos.setForeground(Color.WHITE);
        vehiculos.setFont(Colors.ButtonText1);
        vehiculos.addMouseListener(addFocus(vehiculos));
        animacionDerecha(vehiculos, x -= 125, vehiculos.getX());
        dash.add(vehiculos);

        Mecanicos = new RButton("▬▬ Mecanicos            ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        Mecanicos.setBounds(-18, y = y + 55, 212, 50);
        Mecanicos.setForeground(Color.WHITE);
        Mecanicos.setFont(Colors.ButtonText1);
        Mecanicos.addMouseListener(addFocus(Mecanicos));
        animacionDerecha(Mecanicos, x -= 125, Mecanicos.getX());
        dash.add(Mecanicos);

        Clientes = new RButton("▬▬ Clientes               ")
                .setColor(Colors.FONDO_1, Colors.TEMA_BUTTONS)
                .setTextColor(Color.white, Color.white);
        Clientes.setBounds(-18, y = y + 50, 212, 50);
        Clientes.setForeground(Color.WHITE);
        Clientes.setFont(Colors.ButtonText1);
        animacionDerecha(Clientes, x -= 125, Clientes.getX());
        Clientes.addMouseListener(addFocus(Clientes));
        dash.add(Clientes);

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
    }

    public MouseAdapter abrirConfig() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lado_derecho.setBackground(Color.CYAN);
            }
        };
    }

    public void abrirAccesorios() {
        lado_derecho.add(new AccesorioView());
        revalidate();
        repaint();
    }

    public void abrirCliente() {
        lado_derecho.setBackground(Color.orange);
    }

    public void abrirMecanicos() {
        lado_derecho.setBackground(Color.black);
    }

    public void abrirOrden() {
        lado_derecho.setBackground(Color.white);
    }

    public void abrirServicios() {
        lado_derecho.setBackground(Color.GRAY);
    }

    public void abrirVehiculos() {
        lado_derecho.setBackground(Color.YELLOW);
    }
    // #endregion

}