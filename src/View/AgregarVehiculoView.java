package View;

import View.utils.JViews;
import View.utils.RButton;
import View.utils.Colors;
import View.utils.RTextField;

import javax.swing.*;
import java.awt.*;

public class AgregarVehiculoView extends JDialog implements JViews {

    public RTextField txtPlaca;
    public RTextField txtMarca;
    public RTextField txtModelo;
    public RTextField txtChasis;
    public RTextField txtMotor;
    public RTextField txtAño;
    public RTextField txtColor;
    public JLabel titleLabel;
    public RButton btnGuardar, btnCancelar;

    public AgregarVehiculoView(JFrame parent) {
        super(parent, "Vehiculos");
        agregarComponentes();

        this.setSize(450, 600);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
        this.setLayout(new BorderLayout(15, 15));
    }

    @Override
    public void init() {
    }

    @Override
    public void agregarComponentes() {
        int y = 10; // Coordenada Y
        int x = 20; // Coordenada x
        int h = 30; // Altura

        JPanel panelCampos = new JPanel(null);
        panelCampos.setPreferredSize(new Dimension(400, 700)); // Altura suficiente para todos los campos

        titleLabel = new JLabel("AGREGAR VEHÍCULO");
        titleLabel.setFont(Colors.Titles);
        titleLabel.setBounds(x, y, 300, 35);
        animacionDerecha(titleLabel, x - 170, x);
        panelCampos.add(titleLabel);

        //PLACA
        JLabel placa_Label = new JLabel("Placa:");
        placa_Label.setFont(Colors.FieldBoldText);
        placa_Label.setBounds(x, y = y + 50, 200, 25);
        animacionDerecha(placa_Label, x - 120, x);
        panelCampos.add(placa_Label);

        txtPlaca = new RTextField(150, 25, Color.WHITE);
        txtPlaca.setBounds(x, y = y + 30, 380, h);
        animacionDerecha(txtPlaca, x - 150, x);
        panelCampos.add(txtPlaca);
        
        //MARCA
        JLabel marca_Label = new JLabel("Marca del Vehículo:");
        marca_Label.setFont(Colors.FieldBoldText);
        marca_Label.setBounds(x, y=y+40, 270, 25);
        animacionDerecha(marca_Label, x-170, x);
        panelCampos.add(marca_Label);

        txtMarca = new RTextField(150,25,Color.WHITE);
        txtMarca.setBounds(x, y= y+30, 380, h);
        animacionDerecha(txtMarca, x-190,x);
        panelCampos.add(txtMarca);

        //MODELO
        JLabel modelo_Label = new JLabel("Modelo del Vehículo:");
        modelo_Label.setFont(Colors.FieldBoldText);
        modelo_Label.setBounds(x, y=y+40, 270, 25);
        animacionDerecha(modelo_Label, x-170, x);
        panelCampos.add(modelo_Label);

        txtModelo = new RTextField(150,25,Color.WHITE);
        txtModelo.setBounds(x, y= y+30, 380, h);
        animacionDerecha(txtModelo, x-190,x);
        panelCampos.add(txtModelo);

        //CHASIS
        JLabel chasis_Label = new JLabel("Modelo de chasis:");
        chasis_Label.setFont(Colors.FieldBoldText);
        chasis_Label.setBounds(x, y=y+40, 270, 25);
        animacionDerecha(chasis_Label, x-170, x);
        panelCampos.add(chasis_Label);

        txtChasis = new RTextField(150,25,Color.WHITE);
        txtChasis.setBounds(x, y= y+30, 380, h);
        animacionDerecha(txtChasis, x-190,x);
        panelCampos.add(txtChasis);

        //MOTOR
        JLabel motor_Label = new JLabel("Numero de motor:");
        motor_Label.setFont(Colors.FieldBoldText);
        motor_Label.setBounds(x, y=y+40, 270, 25);
        animacionDerecha(motor_Label, x-170, x);
        panelCampos.add(motor_Label);
        
        txtMotor = new RTextField(150,25,Color.WHITE);
        txtMotor.setBounds(x, y= y+30, 380, h);
        animacionDerecha(txtMotor, x-190,x);
        panelCampos.add(txtMotor);

        //AÑO
        JLabel año_Label = new JLabel("Año del Vehiculo:");
        año_Label.setFont(Colors.FieldBoldText);
        año_Label.setBounds(x, y=y+40, 270, 25);
        animacionDerecha(año_Label, x-170, x);
        panelCampos.add(año_Label);
        
        txtAño = new RTextField(150,25,Color.WHITE);
        txtAño.setBounds(x, y= y+30, 380, h);
        animacionDerecha(txtAño, x-190,x);
        panelCampos.add(txtAño);

        //COLOR
        JLabel color_Label = new JLabel("Color del Vehiculo:");
        color_Label.setFont(Colors.FieldBoldText);
        color_Label.setBounds(x, y=y+40, 270, 25);
        animacionDerecha(color_Label, x-170, x);
        panelCampos.add(color_Label);
        
        txtColor = new RTextField(150,25,Color.WHITE);
        txtColor.setBounds(x, y= y+30, 380, h);
        animacionDerecha(txtColor, x-190,x);
        panelCampos.add(txtColor);

        //BOTONES DEL PANEL
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        btnGuardar = new RButton("Guardar").setTamaño(120, 35).setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
        btnCancelar = new RButton("Cancelar").setTamaño(120, 35).setColor(Colors.TEMA_BUTTONS_ROJO, Colors.BUTTONS_FONDO_ROJO);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
    
        // Crear JScrollPane para hacer scrolleable el panel de campos
        JScrollPane scrollPane = new JScrollPane(panelCampos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);
    }
}
