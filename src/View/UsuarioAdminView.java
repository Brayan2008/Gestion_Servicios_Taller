package View;

import javax.swing.JPanel;

import Services.UsuariosService;
import View.utils.JViews;

public class UsuarioAdminView extends JPanel implements JViews {

    protected static final UsuariosService us = new UsuariosService();

    public String opcion = "";
    public boolean eliminacion;

    public UsuarioAdminView() {
        init();
        agregarComponentes();
    }

    @Override
    public void init() {
        setLayout();
    }

    @Override
    public void agregarComponentes() {

    }

}