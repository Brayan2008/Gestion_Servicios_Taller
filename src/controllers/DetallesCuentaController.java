package controllers;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Services.UsuariosService;
import View.App2;
import View.DetallesCuentaView;
import View.principal;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RPasswordField;
import View.utils.RTextField;

public class DetallesCuentaController {

    DetallesCuentaView vw;
    UsuariosService uServ;

    private RButton btnMod, btnDel;
    RTextField cod, nom;
    RPasswordField pass;

    boolean flag = true;
    String codigo, nombre, contraseña;

    public DetallesCuentaController() {
        this.vw = new DetallesCuentaView();
        this.uServ = new UsuariosService();

        codigo = LoginViewController.cod;
        nombre = LoginViewController.nombre;
        contraseña = LoginViewController.contra;

        btnMod = vw.btnMod;
        btnDel = vw.btnDel;
        cod = vw.idUser;
        nom = vw.nomUser;
        pass = vw.contraseña;

        cod.setText(codigo);
        nom.setText(nombre);
        pass.setText(contraseña);

        btnDel.setColor(Colors.TEMA_BUTTONS_ROJO, Colors.BUTTONS_FONDO_ROJO);
        btnMod.setColor(Colors.GRAY, Colors.GRAY);
        cod.setEditable(false);
        nom.setEditable(false);
        pass.setEditable(false);
        
        
        
        addListeners();
    }
    
    public DetallesCuentaView getVw() {
        return vw;
    }

    public void reiniciar() {
        flag = true;
        nom.setEditable(false);
        pass.setEditable(false);        
        btnMod.setColor(Colors.GRAY, Colors.GRAY);
    }

    public void addListeners() {
        btnMod.addActionListener(e -> {
            if (flag) {
                btnMod.setColor(Colors.TEMA_BUTTONS3, Colors.BUTTONS_FONDO_3);
                nom.setEditable(true);
                pass.setEditable(true);
                flag = false;
                return;
            }
            if (nom.getText().isEmpty() || nom.getText().isBlank() || String.valueOf(pass.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(vw, "No se aceptan campos vacios");
            } else {
                try {
                    uServ.actualizarUsuario(cod.getText().trim(), nom.getText().trim(),
                            String.valueOf(pass.getPassword()));
                    JOptionPane.showMessageDialog(vw, "Datos actualizados");
                } catch (SQLException f) {
                    JOptionPane.showMessageDialog(vw, "Error al actualizar" + f.getMessage());
                } finally{
                    reiniciar();
                }
            }
        });
        btnDel.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(vw,"¿Estás seguro de eliminar tu cuenta? Esto no se puede deshacer.",
                    "Advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    uServ.eliminarUsuario(cod.getText().trim());
                    JOptionPane.showMessageDialog(vw, "Tu cuenta ha sido eliminada correctamente.", "Cuenta eliminada",
                    JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(()->new App2());        
                    principal.puntero.dispose();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(vw, "Ups. Ocurrio un error", "Cuenta eliminada",
                    JOptionPane.INFORMATION_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });

    }
}