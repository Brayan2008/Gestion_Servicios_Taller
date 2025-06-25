package View;

import Services.AccesoriosService;
import View.utils.Colors;
import View.utils.JViews;
import controllers.AccesorioViewController;

import java.awt.*;
import javax.swing.*;

public class AccesorioView extends JPanel implements JViews {

    private final AccesoriosService as = new AccesoriosService();
    public JButton btnAgregar = new JButton("Agregar Accesorio");
    public JButton btnListar = new JButton("Listar Accesorio");
    public JButton btnModificar = new JButton("Modificar Accesorio");
    public JButton btnEliminar = new JButton("Eliminar Accesorio");
    public JPanel panelCentral = new JPanel();

public static void main(String[] args) {
    JFrame prueba = new JFrame("Gestión de Accesorios");
    AccesorioView vista = new AccesorioView();
    new AccesorioViewController(vista); // ← Controlador conectado

    prueba.add(vista);
    prueba.setBounds(100, 100, 700, 500);
    prueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    prueba.setVisible(true);

}

    public AccesorioView() {
        init();
        this.setBackground(Colors.GRAY);
        agregarComponentes();
         // Establecer el layout general del panel principal
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY); // Puedes usar Colors.GRAY si usas una clase de colores personalizada

        // Crear el panel lateral derecho
        JPanel PanelDerecho = new JPanel();
        PanelDerecho.setLayout(new BoxLayout(PanelDerecho, BoxLayout.Y_AXIS));
        PanelDerecho.setBackground(Color.DARK_GRAY);
        PanelDerecho.setPreferredSize(new Dimension(170, 0)); 

        /* JButton btnAgregar = new JButton(); 
        JButton btnListar = new JButton();
        JButton btnModificar = new JButton();
        JButton btnEliminar = new JButton(); */

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

        // Método agregarComponentes() (o directamente en el constructor)
        panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);

        this.add(panelCentral, BorderLayout.CENTER);
        
    }

    @Override
    public void init() {
        
    }

    @Override
    public void agregarComponentes() {
    
    }

}
