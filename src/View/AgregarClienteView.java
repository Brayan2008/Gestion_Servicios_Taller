package View;

import View.utils.Colors;
import View.utils.JViews;
import View.utils.RButton;
import View.utils.RTextField;

import javax.swing.*;
import java.awt.*;

public class AgregarClienteView extends JDialog implements JViews {

    public JComboBox<String> tipo_doc;
    public RTextField txtCodigo,txtNombre,txtTelefono,txtDireccion,txtDistrito;
    public JLabel titleLabel;
    public RButton btnGuardar, btnCancelar;
    public AgregarClienteView(JFrame parent) {
        super(parent, "Clientes");
        
        agregarComponentes();

        this.setSize(320, 480);
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
        
        JLabel tipo_doc_label = new JLabel("Tipo de documento:");
        tipo_doc_label.setFont(Colors.FieldBoldText);
        tipo_doc_label.setBounds(x, y=y+45, 200, 20);
        animacionDerecha(tipo_doc_label, x-120, x);
        panelCampos.add(tipo_doc_label);
 
        String[] tipos = {"DNI", "RUC"};

        tipo_doc = new JComboBox<>(tipos);
        tipo_doc.setBounds(x, y=y+25, 270, h);
        animacionDerecha(tipo_doc, x-150, x);
        panelCampos.add(tipo_doc);
        
        JLabel codigo_label = new JLabel("Nro. Documento:");
        codigo_label.setFont(Colors.FieldBoldText);
        codigo_label.setBounds(x, y=y+30, 270, 20);
        animacionDerecha(codigo_label, x-170, x);
        panelCampos.add(codigo_label);
        
        txtCodigo = new RTextField(150,25,Color.WHITE);
        txtCodigo.setBounds(x, y= y+25, 270, h);
        animacionDerecha(txtCodigo, x-190,x);
        panelCampos.add(txtCodigo);
        
        JLabel nombre_label = new JLabel("Nombre: ");
        nombre_label.setFont(Colors.FieldBoldText);
        nombre_label.setBounds(x, y= y+30, 270, 20);
        animacionDerecha(nombre_label, x-210, x);
        panelCampos.add(nombre_label);
        
        txtNombre = new RTextField(200,25,Color.WHITE);
        txtNombre.setBounds(x, y =y+25, 270, h);
        animacionDerecha(txtNombre, x-220,x);
        panelCampos.add(txtNombre);
      
        JLabel telefono_label = new JLabel("Telefono: ");
        telefono_label.setFont(Colors.FieldBoldText);
        telefono_label.setBounds(x, y= y+30, 270, 20);
        animacionDerecha(telefono_label, x-230, x);
        panelCampos.add(telefono_label);
        
        txtTelefono = new RTextField(200,25,Color.WHITE);
        txtTelefono.setBounds(x, y =y+25, 270, h);
        animacionDerecha(txtTelefono, x-240,x);
        panelCampos.add(txtTelefono);
      
        JLabel direccion_label = new JLabel("Direccion: ");
        direccion_label.setFont(Colors.FieldBoldText);
        direccion_label.setBounds(x, y= y+30, 270, 20);
        animacionDerecha(direccion_label, x-250, x);
        panelCampos.add(direccion_label);
        
        txtDireccion = new RTextField(200,25,Color.WHITE);
        txtDireccion.setBounds(x, y =y+25, 270, h);
        animacionDerecha(txtDireccion, x-260,x);
        panelCampos.add(txtDireccion);
      
        JLabel distrito_label = new JLabel("Distrito: ");
        distrito_label.setFont(Colors.FieldBoldText);
        distrito_label.setBounds(x, y= y+30, 270, 20);
        animacionDerecha(distrito_label, x-270, x);
        panelCampos.add(distrito_label);
        
        txtDistrito = new RTextField(200,25,Color.WHITE);
        txtDistrito.setBounds(x, y =y+25, 270, h);
        animacionDerecha(txtDistrito, x-280,x);
        panelCampos.add(txtDistrito);

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