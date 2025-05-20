package View;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Inicio extends JFrame {
    
    VistaCliente b = new VistaCliente();

    public Inicio() {
        this.add(b);
        this.setLocationRelativeTo(null);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) pantalla.getWidth()/2,(int) pantalla.getWidth()/2);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    

}
