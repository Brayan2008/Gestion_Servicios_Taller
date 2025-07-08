package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
    RTextField codigo;
    RPasswordField createPassword;
    RPasswordField createPassword2;
    RButton registrase;
    RButton salir;

    //Cuando ya ingresa se almacenan estos campos
    public static String cod, nombre, contra;

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

        codigo = vs.getCodigo_registro();
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
        String user = vs.getUsuario().getText().trim();
        String password = String.valueOf(vs.getContraseña().getPassword());
        try {
            us.buscarUsuario(user);
            if (us.contra.equals(password)) {
                JOptionPane.showMessageDialog(null, "Inicio correcto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                FadeWindowsOut(punteroWindow);
                cod = user;
                contra = password;
                nombre = us.nombre;
                new principal(us.nombre);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void EntrarRegistro() {
        reiniciarCampos();
        animacionSubir(login, login.getY(), login.getY() - 400);
        animacionBajar(register, register.getY(), register.getY() + 400);
    }
    
    public void SalirRegistro() {
        reiniciarCampos();
        animacionSubir(register, register.getY(), register.getY() - 400);
        animacionBajar(login, login.getY(), login.getY() + 400);
    }

    public void Registrarse() {
        cod = codigo.getText().trim();
        nombre = nombreNew.getText().trim();
        contra = String.valueOf(createPassword.getPassword());
        String contra2 = String.valueOf(createPassword2.getPassword());

        if (!cod.matches("[0-9]{8}") || (contra.isBlank() || cod.isBlank() || nombre.isBlank() || cod.isEmpty()
                || nombre.isEmpty() || contra.isEmpty())) {
            JOptionPane.showMessageDialog(null, "DNI no valido o campos vacios no permitidos", "Informacion",
                    JOptionPane.ERROR_MESSAGE);
        } else if (contra.equals(contra2)) {
            System.out.println("SI entra");
            try {
                us.insertarUsuario(cod, nombre, contra);
                JOptionPane.showMessageDialog(null, "Registro correcto", "Informacion",
                        JOptionPane.INFORMATION_MESSAGE);
                reiniciarCampos();
                SalirRegistro();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Informacion",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public void reiniciarCampos() {
        codigo.setText("");
        nombreNew.setText("");
        createPassword.setText("");
        createPassword2.setText("");

        user.setText("");
        password.setText("");

    }
}
