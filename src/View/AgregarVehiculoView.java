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

        this.setSize(320, 300);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
        this.setLayout(new BorderLayout(10, 10));
    }

    @Override
    public void init() {
    }

    @Override
    public void agregarComponentes() {
        int y = 6; // Coordenada Y
        int x = 12; // Coordenada x
        int h = 25; // Altura

        JPanel panelCampos = new JPanel(null);

        titleLabel = new JLabel("AGREGAR ");
        titleLabel.setFont(Colors.Titles);
        titleLabel.setBounds(x, y, 200, 30);
        animacionDerecha(titleLabel, x - 170, x);
        panelCampos.add(titleLabel);

        JLabel placa_Label = new JLabel("Placa:");
        placa_Label.setFont(Colors.FieldBoldText);
        placa_Label.setBounds(x, y = y + 45, 200, 20);
        animacionDerecha(placa_Label, x - 120, x);
        panelCampos.add(placa_Label);

        txtPlaca = new RTextField(150, 25, Color.WHITE);
        txtPlaca.setBounds(x, y = y + 25, 270, h);
        animacionDerecha(txtPlaca, x - 150, x);
        panelCampos.add(txtPlaca);

    }
}
