package View;

import Services.AccesoriosService;
import View.utils.Colors;
import View.utils.JViews;

import java.awt.*;
import javax.swing.*;

public class AccesorioView extends JPanel implements JViews {

    private final AccesoriosService as = new AccesoriosService();

public static void main(String[] args) {
    JFrame prueba = new JFrame();
    prueba.add(new AccesorioView());
    prueba.setVisible(true);
    prueba.setLocale(null);
    prueba.setBounds(new Rectangle(500, 500));
    prueba.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
}

    public AccesorioView() {
        init();
        this.setBackground(Colors.GRAY);
        agregarComponentes();
         // Establecer el layout general del panel principal
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY); // Puedes usar Colors.GRAY si usas una clase de colores personalizada

        // Crear el panel lateral izquierdo
        JPanel PanelDerecho = new JPanel();
        PanelDerecho.setLayout(new BoxLayout(PanelDerecho, BoxLayout.Y_AXIS));
        PanelDerecho.setBackground(Color.DARK_GRAY);
        PanelDerecho.setPreferredSize(new Dimension(170, 0)); 

        // Crear botones
        JButton btnAgregar = new JButton("Agregar Accesorio");
        JButton btnListar = new JButton("Listar Accesorio");
        JButton btnModificar = new JButton("Modificar Accesorio");
        JButton btnEliminar = new JButton("Eliminar Accesorio");

        // Definir dimensiones uniformes
        Dimension dimensionBoton = new Dimension(150, 35);
        btnAgregar.setMaximumSize(dimensionBoton);
        btnListar.setMaximumSize(dimensionBoton);
        btnModificar.setMaximumSize(dimensionBoton);
        btnEliminar.setMaximumSize(dimensionBoton);

        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnListar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar botones al panel izquierdo con espacio entre ellos
        PanelDerecho.add(Box.createVerticalStrut(20)); // espacio superior
        PanelDerecho.add(btnAgregar);
        PanelDerecho.add(Box.createVerticalStrut(10));
        PanelDerecho.add(btnListar);
        PanelDerecho.add(Box.createVerticalStrut(10));
        PanelDerecho.add(btnModificar);
        PanelDerecho.add(Box.createVerticalStrut(10));
        PanelDerecho.add(btnEliminar);

        // Agregar el panel izquierdo al AccesorioView (que usa BorderLayout)
        this.add(PanelDerecho, BorderLayout.EAST);
    

    }

    @Override
    public void init() {
        
    }

    @Override
    public void agregarComponentes() {
    
    }

}
