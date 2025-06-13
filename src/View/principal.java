package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

    public principal(String nombre) {
        this.nombre = nombre;
        init();
        agregarComponentes();
    }

    @Override
    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 600, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());
    }

    @Override
    public void agregarComponentes() {
        JPanel dash = new JPanel(null);
        dash.setBackground(Colors.FONDO_1);
        dash.setPreferredSize(new Dimension(200, HEIGHT));
        add(dash, BorderLayout.WEST);

        JLabel icon = new JLabel(new ImageIcon("src/resources/principal/perfilDefault.png"));
        icon.setBounds(8,10,50,50);
        dash.add(icon);
        animacionDerecha(icon, -100, icon.getX());
        
        JLabel usuario = new JLabel(nombre.toUpperCase());
        usuario.setFont(Colors.Titles);
        usuario.setForeground(Color.WHITE);
        usuario.setBounds(74, 25, 100, 20);
        dash.add(usuario);
        FadeComponentIn(usuario);
        
        int y = 120; //Distribuir cada 120 px
        int x = -100; // Para las animaciones

        RButton orden = new RButton("▬▬ Orden de sercicio")
                        .setColor(Colors.FONDO_1,Colors.TEMA_BUTTONS)
                        .setTextColor(Color.white,Color.white);
        orden.setBounds(-18,y,212,50);
        orden.setForeground(Color.WHITE);
        orden.setFont(Colors.ButtonText1);
        orden.addMouseListener(addFocus(orden));
        animacionDerecha(orden, x, orden.getX());
        dash.add(orden);

        RButton accesorios = new RButton("▬▬ Accesorios           ")
                        .setColor(Colors.FONDO_1,Colors.TEMA_BUTTONS)
                        .setTextColor(Color.white,Color.white);
        accesorios.setBounds(-18,y=y+55,212,50);
        accesorios.setForeground(Color.WHITE);
        accesorios.setFont(Colors.ButtonText1);
        accesorios.addMouseListener(addFocus(accesorios));
        animacionDerecha(accesorios,x-=125, accesorios.getX());
        dash.add(accesorios);
        
        RButton Servicios = new RButton("▬▬ Servicios              ")
        .setColor(Colors.FONDO_1,Colors.TEMA_BUTTONS)
        .setTextColor(Color.white,Color.white);
        Servicios.setBounds(-18,y=y+55,212,50);
        Servicios.setForeground(Color.WHITE);
        Servicios.setFont(Colors.ButtonText1);
        Servicios.addMouseListener(addFocus(Servicios));
        animacionDerecha(Servicios,x-=125, Servicios.getX());
        dash.add(Servicios);
        
        RButton vehiculos = new RButton("▬▬ Vehiculos              ")
                        .setColor(Colors.FONDO_1,Colors.TEMA_BUTTONS)
                        .setTextColor(Color.white,Color.white);
        vehiculos.setBounds(-18,y=y+55,212,50);
        vehiculos.setForeground(Color.WHITE);
        vehiculos.setFont(Colors.ButtonText1);
        vehiculos.addMouseListener(addFocus(vehiculos));
        animacionDerecha(vehiculos,x-=125, vehiculos.getX());
        dash.add(vehiculos);

        RButton Mecanicos = new RButton("▬▬ Mecanicos            ")
                        .setColor(Colors.FONDO_1,Colors.TEMA_BUTTONS)
                        .setTextColor(Color.white,Color.white);
        Mecanicos.setBounds(-18,y=y+55,212,50);
        Mecanicos.setForeground(Color.WHITE);
        Mecanicos.setFont(Colors.ButtonText1);
        Mecanicos.addMouseListener(addFocus(Mecanicos));
        animacionDerecha(Mecanicos,x-=125, Mecanicos.getX());
        dash.add(Mecanicos);

        RButton Clientes = new RButton("▬▬ Clientes               ")
                        .setColor(Colors.FONDO_1,Colors.TEMA_BUTTONS)
                        .setTextColor(Color.white,Color.white);
        Clientes.setBounds(-18,y=y+50,212,50);
        Clientes.setForeground(Color.WHITE);
        Clientes.setFont(Colors.ButtonText1);
        animacionDerecha(Clientes,x-=125, Clientes.getX());
        Clientes.addMouseListener(addFocus(Clientes));
        dash.add(Clientes);

    }

    public MouseAdapter addFocus(JComponent component) {
        return new MouseAdapter() {
                Timer a = animacionCrecer(component, 8);
                Timer b = animacionEncoger(component,8);
            
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

}