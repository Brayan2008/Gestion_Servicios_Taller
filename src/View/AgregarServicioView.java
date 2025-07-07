package View;

import View.utils.Colors;
import View.utils.JViews;
import View.utils.RButton;
import View.utils.RTextField;

import javax.swing.*;
import java.awt.*;

public class AgregarServicioView extends JDialog implements JViews {

    public RTextField txtCodigo;
    public RTextField txtNombre;
    public RTextField txtPrecio;
public JLabel titleLabel;
    public RButton btnGuardar, btnCancelar;

    public AgregarServicioView(JFrame parent) {
        super(parent, "Servicios");
        
        agregarComponentes();

        this.setSize(320, 300);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
        this.setLayout(new BorderLayout(10, 10));
    }
    
    @Override
    public void init() {}     
    
    
    @Override
    public void agregarComponentes() {
        int y = 6; // Coordenada Y
        int x = 12; // Coordenada x
        int h = 25; // Altura
        
        JPanel panelCampos = new JPanel(null);
        
        titleLabel = new JLabel("AGREGAR ");
        titleLabel.setFont(Colors.Titles);
        titleLabel.setBounds(x,y,200,30);
        animacionDerecha(titleLabel, x-170, x);
        panelCampos.add(titleLabel);
        
        JLabel codigo_label = new JLabel("Código:");
        codigo_label.setFont(Colors.FieldBoldText);
        codigo_label.setBounds(x, y=y+45, 200, 20);
        animacionDerecha(codigo_label, x-120, x);
        panelCampos.add(codigo_label);
        
        txtCodigo = new RTextField(150,25,Color.WHITE);
        txtCodigo.setBounds(x, y=y+25, 270, h);
        animacionDerecha(txtCodigo, x-150, x);
        panelCampos.add(txtCodigo);
        
        JLabel nombre_label = new JLabel("Nombre del servicio:");
        nombre_label.setFont(Colors.FieldBoldText);
        nombre_label.setBounds(x, y=y+30, 270, 20);
        animacionDerecha(nombre_label, x-170, x);
        panelCampos.add(nombre_label);
        
        txtNombre = new RTextField(150,25,Color.WHITE);
        txtNombre.setBounds(x, y= y+25, 270, h);
        animacionDerecha(txtNombre, x-190,x);
        panelCampos.add(txtNombre);
        
        JLabel precio_label = new JLabel("Precio:");
        precio_label.setFont(Colors.FieldBoldText);
        precio_label.setBounds(x, y= y+30, 270, 20);
        animacionDerecha(precio_label, x-210, x);
        panelCampos.add(precio_label);
        
        txtPrecio = new RTextField(200,25,Color.WHITE);
        txtPrecio.setBounds(x, y =y+25, 270, h);
        animacionDerecha(txtPrecio, x-220,x);
        panelCampos.add(txtPrecio);

        // panel botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new RButton("Guardar").setTamaño(100, 28).setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
        btnCancelar = new RButton("Cancelar").setTamaño(100, 28).setColor(Colors.TEMA_BUTTONS_ROJO, Colors.BUTTONS_FONDO_ROJO);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
    
        this.add(panelCampos, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

    }
}