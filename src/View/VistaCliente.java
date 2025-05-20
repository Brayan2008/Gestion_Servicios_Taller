package View;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

import Controllers.ControladorCliente;

public class VistaCliente extends JPanel {

    ControladorCliente sc = new ControladorCliente();

    public VistaCliente() {
        super(new FlowLayout(FlowLayout.CENTER));
        AgregarCampos();
    }

    public void AgregarCampos() {
        JLabel b = new JLabel("Clientes disponibles");
        b.setFont(new Font("Arial Black", 2, 13));
        this.add(b);
    }
    
}