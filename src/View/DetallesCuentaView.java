package View;

import java.awt.Color;

import javax.swing.JPanel;

import View.utils.JViews;
import View.utils.RButton;
import View.utils.RPasswordField;
import View.utils.RTextField;

public class DetallesCuentaView extends JPanel implements JViews {
    
    public RTextField idUser, nomUser;
    public RPasswordField contraseña;
    public RButton btnMod, btnDel;

    public DetallesCuentaView() {
        setLayout(null);
        setBackground(Color.RED);
    }

    @Override
    public void init() {
        
    }

    @Override
    public void agregarComponentes() {

    }



}
