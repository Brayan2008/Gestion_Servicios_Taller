package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import View.utils.Colors;
import View.utils.ImagePanel;
import View.utils.JViews;
import View.utils.RButton;
import View.utils.RPanel;
import View.utils.RPasswordField;
import View.utils.RTextField;

public class LoginView extends ImagePanel implements JViews {

    // Campos Botones Frame
    private JLabel cerrar;
    private JLabel minimizar;

    // Campos Login
    private RTextField usuario;
    private RPasswordField contraseña;
    private RButton ingresarButton;
    private RButton registraRButton;

    // Campos Register
    private RPasswordField contraseña2UsuarioRegistrar;
    private RTextField nombreUsuarioRegistrar;
    private RPasswordField contraseñaUsuarioRegistrar;
    private RButton registrarseButton;
    private RButton salirButton;
    private LoginView login;

    // Paneles
    RPanel cardLayout;
    RPanel panel_registro;

    private GridBagConstraints constraint = new GridBagConstraints();

    public LoginView() {
        super("/Login/taller.jpeg");
        init();
        login = this;
    }
    
    @Override
    public void init() {
        agregarComponentes();
        addPanelOscuro();
        addPanelRegistro();
    }

    // #region Componentes
    @Override
    public void agregarComponentes() {
        setLayout(null);
        
        cardLayout = new RPanel(230, 360, Colors.LIGHT_GRAY).Posicion(40, 40);
        cardLayout.setLayout(new GridBagLayout());
        add(cardLayout);

        animacionSubir(cardLayout, 320, cardLayout.getY());

        constraint.gridx = 0;
        constraint.gridy = 0;

        JLabel titulo = new JLabel("BIENVENIDO");
        titulo.setFont(Colors.Titles);
        constraint.insets = new Insets(2, 0, 10, 0);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setPreferredSize(new Dimension(200, 40));
        cardLayout.add(titulo, constraint);

        JLabel usuarioLabel = new JLabel("Ingrese su usuario");
        usuarioLabel.setFont(Colors.FieldText);
        usuarioLabel.setHorizontalAlignment(SwingUtilities.LEFT);
        usuarioLabel.setPreferredSize(new Dimension(200, 20));
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 3, 5, 0);
        cardLayout.add(usuarioLabel, constraint);

        usuario = new RTextField(200, 25, Color.WHITE);
        usuario.setToolTipText("Ingrese su usuario");
        constraint.insets = JViews.sinMargin;
        constraint.gridy = 2;
        cardLayout.add(usuario, constraint);

        JLabel passwordLabel = new JLabel("Ingrese su contraseña");
        passwordLabel.setFont(Colors.FieldText);
        passwordLabel.setHorizontalAlignment(SwingUtilities.LEFT);
        passwordLabel.setPreferredSize(new Dimension(200, 20));
        constraint.gridy = 3;
        constraint.insets = new Insets(10, 3, 5, 0);
        cardLayout.add(passwordLabel, constraint);

        contraseña = new RPasswordField(200, 25, Color.WHITE);
        contraseña.setToolTipText("Escriba su contraseña");
        constraint.insets = JViews.sinMargin;
        constraint.gridy = 4;
        cardLayout.add(contraseña, constraint);

        ingresarButton = new RButton("Ingresar").setColor(Color.GREEN, Color.BLACK);
        constraint.gridy = 5;
        constraint.insets = new Insets(12, 0, 0, 12);
        cardLayout.add(ingresarButton, constraint);

        registraRButton = new RButton("Registrate").setColor(Color.CYAN, Color.black);
        constraint.gridy = 6;
        constraint.insets = new Insets(12, 0, 0, 12);
        cardLayout.add(registraRButton, constraint);

        JLabel creditos = new JLabel("v 0.1");
        creditos.setFont(new Font("Cambria Math", Font.PLAIN, 10));
        creditos.setHorizontalAlignment(SwingUtilities.LEFT);
        creditos.setPreferredSize(new Dimension(200, 20));
        constraint.gridy = 7;
        constraint.insets = new Insets(60, 3, 5, 0);
        cardLayout.add(creditos, constraint);

    }

    public void addPanelOscuro() {
        JPanel panel_oscuro = new JPanel();
        panel_oscuro.setLayout(null);
        panel_oscuro.setBackground(new Color(0, 0, 0, 190));
        panel_oscuro.setVisible(true);
        panel_oscuro.setBounds(300, 0, 300, 500);
        add(panel_oscuro);
        addToPanelOscuro(panel_oscuro);

        animacionIzquierda(panel_oscuro, 500, 300);

        repaint();
    }

    public void addToPanelOscuro(JPanel panel) {       
        minimizar = new JLabel(new ImageIcon("src/resources/Login/mini.png"));
        minimizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizar.setBounds(210, -2, 35, 35);
        panel.add(minimizar);
        cerrar = new JLabel(new ImageIcon("src/resources/Login/close_icon.png"));
        cerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cerrar.setBounds(255, 12, 20, 20);
        panel.add(cerrar);
        String[] title = { "SISTEMA", "DE GESTION ", "DE TALLER ", "DE AUTOS" };
        Color[] colors = { Color.RED, Color.CYAN, Color.GREEN, Color.ORANGE };
        int x = 8;
        int i = 0;
        for (String string : title) {
            x += 50;
            JLabel tit = new JLabel(string);
            tit.setBounds(12, x, 300, 40);
            tit.setForeground(colors[i++]);
            tit.setFont(new Font("Arial Black", Font.BOLD, 39));
            panel.add(tit);
        }
    }

    // #region Panel Registro
    public void addPanelRegistro() {

        constraint.insets = JViews.sinMargin;
        constraint.gridwidth = 2;

        panel_registro = new RPanel(230, 360, Colors.LIGHT_GRAY).Posicion(40, -380);
        panel_registro.setLayout(new GridBagLayout());
        add(panel_registro);

        JLabel titulo_registro = new JLabel("REGISTRATE");
        titulo_registro.setFont(Colors.Titles);
        constraint.gridx = 0;
        constraint.gridy = 0;
        panel_registro.add(titulo_registro, constraint);

        JLabel nombre_usuario = new JLabel("Ingrese su nombre de usuario");
        nombre_usuario.setFont(Colors.FieldText);
        nombre_usuario.setHorizontalAlignment(SwingUtilities.LEFT);
        nombre_usuario.setPreferredSize(new Dimension(200, 20));
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 4, 4, 4);
        panel_registro.add(nombre_usuario, constraint);

        nombreUsuarioRegistrar = new RTextField(200, 25, Color.WHITE);
        constraint.gridy = 2;
        constraint.insets = JViews.sinMargin;
        panel_registro.add(nombreUsuarioRegistrar, constraint);

        JLabel contraseñaRegistrar = new JLabel("Ingrese su contraseña");
        contraseñaRegistrar.setFont(Colors.FieldText);
        contraseñaRegistrar.setHorizontalAlignment(SwingUtilities.LEFT);
        contraseñaRegistrar.setPreferredSize(new Dimension(200, 20));
        constraint.insets = new Insets(10, 4, 4, 4);
        constraint.gridy = 3;
        panel_registro.add(contraseñaRegistrar, constraint);

        contraseñaUsuarioRegistrar = new RPasswordField(200, 25, Color.WHITE);
        constraint.gridy = 4;
        constraint.insets = JViews.sinMargin;
        panel_registro.add(contraseñaUsuarioRegistrar, constraint);

        JLabel contraseña2Registrar = new JLabel("Ingrese la contraseña de nuevo");
        contraseña2Registrar.setFont(Colors.FieldText);
        contraseña2Registrar.setHorizontalAlignment(SwingUtilities.LEFT);
        contraseña2Registrar.setPreferredSize(new Dimension(200, 20));
        constraint.insets = new Insets(10, 4, 4, 4);
        constraint.gridy = 5;
        panel_registro.add(contraseña2Registrar, constraint);

        contraseña2UsuarioRegistrar = new RPasswordField(200, 25, Color.WHITE);
        constraint.gridy = 6;
        constraint.insets = JViews.sinMargin;
        panel_registro.add(contraseña2UsuarioRegistrar, constraint);

        constraint.gridwidth = 1;

        salirButton = new RButton("Salir").setColor(Color.GREEN, Color.RED).setTamaño(60, 20);
        constraint.gridy = 7;
        constraint.insets = new Insets(40, 20, 20, 0);
        panel_registro.add(salirButton, constraint);

        registrarseButton = new RButton("Registrarse").setColor(Color.CYAN, Color.BLACK).setTamaño(90, 20);
        constraint.gridy = 7;
        constraint.gridx = 1;
        constraint.insets = new Insets(40, 0, 20, 0);
        panel_registro.add(registrarseButton, constraint);

    }
    // #endregion

    // #endregion

    // #region Getters
    public RTextField getUsuario() {
        return usuario;
    }

    public RPasswordField getContraseña() {
        return contraseña;
    }

    public RButton getIngresarButton() {
        return ingresarButton;
    }

    public RButton getRegistraRButton() {
        return registraRButton;
    }

    public RPasswordField getContraseña2UsuarioRegistrar() {
        return contraseña2UsuarioRegistrar;
    }

    public RTextField getNombreUsuarioRegistrar() {
        return nombreUsuarioRegistrar;
    }

    public RPasswordField getContraseñaUsuarioRegistrar() {
        return contraseñaUsuarioRegistrar;
    }

    public RButton getRegistrarseButton() {
        return registrarseButton;
    }

    public RButton getSalirButton() {
        return salirButton;
    }

    public RPanel getCardLayout() {
        return cardLayout;
    }

    public RPanel getPanel_registro() {
        return panel_registro;
    }

    public LoginView getLogin() {
        return login;
    }

    public JLabel getCerrar() {
        return cerrar;
    }

    public JLabel getMinimizar() {
        return minimizar;
    }

}