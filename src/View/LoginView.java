package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import View.utils.Colors;
import View.utils.ImagePanel;
import View.utils.JViews;
import View.utils.RButton;
import View.utils.RPanel;
import View.utils.RPasswordField;
import View.utils.RTextField;

public class LoginView extends ImagePanel implements JViews {

    Timer timer1;

    public LoginView() {
        super("/Login/taller.jpeg");
        init();
    }

    @Override
    public void init() {
        agregarComponentes();
    }

    @Override
    public void agregarComponentes() {
        setLayout(null);
        RPanel cardLayout = new RPanel(230, 360, Colors.LIGHT_GRAY).Posicion(40, 40);
        cardLayout.setLayout(new GridBagLayout());        
        add(cardLayout);
        animacionSubir(cardLayout, 320, cardLayout.getY());
    
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;

        JLabel titulo = new JLabel("BIENVENIDO");
        titulo.setFont(Colors.Titles);
        constraint.insets = new Insets(2, 0, 10, 0);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setPreferredSize(new Dimension(200,40));   
        cardLayout.add(titulo,constraint);
        
        JLabel usuarioLabel = new JLabel("Ingrese su usuario");
        usuarioLabel.setFont(Colors.FieldText);
        usuarioLabel.setHorizontalAlignment(SwingUtilities.LEFT);
        usuarioLabel.setPreferredSize(new Dimension(200,20));   
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 3, 5, 0);
        cardLayout.add(usuarioLabel,constraint);
        
        RTextField usuario = new RTextField(200, 25, Color.WHITE);
        usuario.setFont(Colors.FieldText);
        usuario.setToolTipText("Ingrese su usuario");
        constraint.insets = JViews.sinMargin;
        constraint.gridy=2;
        cardLayout.add(usuario,constraint);
        
        JLabel passwordLabel = new JLabel("Ingrese su usuario");
        passwordLabel.setFont(Colors.FieldText);
        passwordLabel.setHorizontalAlignment(SwingUtilities.LEFT);
        passwordLabel.setPreferredSize(new Dimension(200,20));   
        constraint.gridy = 3;
        constraint.insets = new Insets(10, 3, 5, 0);
        cardLayout.add(passwordLabel,constraint);
        
        RPasswordField contraseña = new RPasswordField(200, 25, Color.WHITE);
        contraseña.setFont(Colors.FieldText);
        contraseña.setToolTipText("Escriba su contraseña");
        constraint.insets = JViews.sinMargin;
        constraint.gridy = 4;
        cardLayout.add(contraseña,constraint);
        
        RButton ingresarButton = new RButton("Ingresar").setColor(Color.GREEN, Color.BLACK);
        constraint.gridy = 5;
        constraint.insets = new Insets(12, 0, 0, 12);
        cardLayout.add(ingresarButton,constraint);

        RButton registraRButton = new RButton("Registrate").setColor(Color.CYAN, Color.BLUE);
        constraint.gridy = 6;
        constraint.insets = new Insets(12, 0, 0, 12);
        cardLayout.add(registraRButton,constraint);
        
        JLabel creditos = new JLabel("v 0.1");
        creditos.setFont(new Font("Cambria Math", Font.PLAIN, 10));
        creditos.setHorizontalAlignment(SwingUtilities.LEFT);
        creditos.setPreferredSize(new Dimension(200,20));   
        constraint.gridy = 7;
        constraint.insets = new Insets(60, 3, 5, 0);
        cardLayout.add(creditos,constraint);
    }
    
    public void animacionSubir(JComponent component, int startY, int endY) {
        timer1 = new Timer(4, new ActionListener() {

            int copyY = startY;

            @Override
            public void actionPerformed(ActionEvent e) {
                copyY -= 15;
                component.setLocation(component.getX(), copyY);
                repaint();
                if (copyY<endY) timer1.stop();
            }

        });

        timer1.start();
    }

    public void addPanelOscuro() {
        JPanel panel_d = new JPanel();
        panel_d.setBackground(new Color(0,0,0,140));
        panel_d.setVisible(true);
        panel_d.setBounds(300,0, 300, 500);
        add(panel_d);
    }

}