package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Model.Usuarios;
import Services.UsuariosService;
import View.App2;
import View.LoginView;
import View.principal;
import View.utils.Animation;
import View.utils.RButton;
import View.utils.RPanel;
import View.utils.RPasswordField;
import View.utils.RTextField;

public class LoginViewController implements Animation {

    private UsuariosService us;
    private LoginView vs;

    // JFrame
    JFrame punteroWindow;

    // Botones del frame
    JLabel salir_ventana;
    JLabel minimizar_ventana;

    // LoginView
    LoginView puntero;

    // Paneles
    RPanel login;
    RPanel register;

    // Campos login
    RTextField user;
    RPasswordField password;
    RButton iniciarSesion;
    RButton registraRButton;

    // Campos Registrar
    RTextField nombreNew;
    RPasswordField createPassword;
    RPasswordField createPassword2;
    RButton registrase;
    RButton salir;

    public LoginViewController(LoginView vs) {
        this.vs = vs;
        this.us = new UsuariosService();

        salir_ventana = vs.getCerrar();
        minimizar_ventana = vs.getMinimizar();

        puntero = vs.getLogin();
        punteroWindow = App2.getPunteroFrame();

        login = vs.getCardLayout();
        register = vs.getPanel_registro();

        user = vs.getUsuario();
        password = vs.getContraseña();
        iniciarSesion = vs.getIngresarButton();
        registraRButton = vs.getRegistraRButton();

        nombreNew = vs.getNombreUsuarioRegistrar();
        createPassword = vs.getContraseñaUsuarioRegistrar();
        createPassword2 = vs.getContraseña2UsuarioRegistrar();
        registrase = vs.getRegistrarseButton();
        salir = vs.getSalirButton();

        addEvents();
    }

    public void addEvents() {
        iniciarSesion.addActionListener(e -> this.IniciarSesion());
        registraRButton.addActionListener(e -> this.EntrarRegistro());
        salir.addActionListener(e -> this.SalirRegistro());
        registrase.addActionListener(e -> Registrarse());
        minimizar_ventana.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {    
            punteroWindow.setState(JFrame.ICONIFIED);
            }
        });

        salir_ventana.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                FadeWindowsOut(punteroWindow);
            }

        });
    }

    public void IniciarSesion() {
        String user = vs.getUsuario().getText();
        String password = String.valueOf(vs.getContraseña().getPassword());
        Usuarios usuario = new Usuarios(user, password);
        if (usuario != null) {
            if (us.validarLogin(usuario)) {
                JOptionPane.showMessageDialog(null, "Inicio correcto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                FadeWindowsOut(punteroWindow);
                new principal(user);
            } else{
                JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void EntrarRegistro() {
        animacionSubir(login, login.getY(), login.getY() - 400);
        animacionBajar(register, register.getY(), register.getY() + 400);
    }
    
    public void SalirRegistro() {
        animacionSubir(register, register.getY(), register.getY() - 400);
        animacionBajar(login, login.getY(), login.getY() + 400);
    }

    public void Registrarse() {
        String nombre = nombreNew.getText();
        String contra = String.valueOf(createPassword.getPassword());
        String contra2 = String.valueOf(createPassword2.getPassword());

        if (contra.equals(contra2)) {
            System.out.println("SI entra");
            Usuarios b = new Usuarios(nombre, contra);
            us.create(nombre, b);
        }
    }
}
