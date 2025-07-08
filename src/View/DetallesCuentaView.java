package View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import View.utils.Colors;
import View.utils.JViews;
import View.utils.RButton;
import View.utils.RPasswordField;
import View.utils.RTextField;

public class DetallesCuentaView extends JPanel implements JViews {

    public RButton btnMod, btnDel;

    public RTextField idUser;
    public RTextField nomUser;
    public RPasswordField contraseña;
    public RButton btnGuardar, btnCancelar;

    public DetallesCuentaView() {
        this.setLayout(new BorderLayout());
        agregarComponentes();
        this.setVisible(true);
    }

    @Override
    public void init() {
    }

    @Override
    public void agregarComponentes() {
        int y = 12; // Coordenada Y
        int x = 40; // Coordenada x
        int h = 25; // Altura

        JPanel panelCampos = new JPanel(null);
        panelCampos.setVisible(true);

        JLabel titleLabel = new JLabel("Configuracion de cuenta");
        titleLabel.setFont(Colors.Titles);
        titleLabel.setBounds(x, y, 300, 30);
        animacionDerecha(titleLabel, x - 170, x);
        panelCampos.add(titleLabel);
     
        JLabel info_label = new JLabel("Estos son tus datos: ");
        info_label.setFont(Colors.SubTitles);
        info_label.setBounds(x, y = y + 35, 300, 30);
        animacionDerecha(info_label, x - 170, x);
        panelCampos.add(info_label);

        JLabel codigo_label = new JLabel("Código:");
        codigo_label.setFont(Colors.FieldBoldText);
        codigo_label.setBounds(x = x + 10, y = y + 45, 200, 20);
        animacionDerecha(codigo_label, x - 120, x);
        panelCampos.add(codigo_label);

        idUser = new RTextField(150, 25, Color.WHITE);
        idUser.setBounds(x, y = y + 25, 270, h);
        animacionDerecha(idUser, x - 150, x);
        panelCampos.add(idUser);

        JLabel nombre_label = new JLabel("Nombre:");
        nombre_label.setFont(Colors.FieldBoldText);
        nombre_label.setBounds(x, y = y + 30, 270, 20);
        animacionDerecha(nombre_label, x - 170, x);
        panelCampos.add(nombre_label);

        nomUser = new RTextField(150, 25, Color.WHITE);
        nomUser.setBounds(x, y = y + 25, 270, h);
        animacionDerecha(nomUser, x - 190, x);
        panelCampos.add(nomUser);

        JLabel precio_label = new JLabel("Contraseña:");
        precio_label.setFont(Colors.FieldBoldText);
        precio_label.setBounds(x, y = y + 30, 270, 20);
        animacionDerecha(precio_label, x - 210, x);
        panelCampos.add(precio_label);

        contraseña = new RPasswordField(200, 25, Color.WHITE);
        contraseña.setBounds(x, y = y + 25, 270, h);
        animacionDerecha(contraseña, x - 220, x);
        panelCampos.add(contraseña);

        btnMod = new RButton("Modificar")
                .setTamaño(120, 25);
        btnMod.setBounds(x, y = y + 50, 120, 25);
        animacionDerecha(btnMod, x - 220, x);
        panelCampos.add(btnMod);

        btnDel = new RButton("Eliminar")
                .setTamaño(120, 25);
        btnDel.setBounds(x = x + 150, y, 120, 25);
        animacionDerecha(btnDel, x - 220, x);
        panelCampos.add(btnDel);

        JLabel logo = new JLabel(new ImageIcon("src/resources/principal/perfilDefaultGrande.png"));
        logo.setBounds(370, 30, 370, 290);
        panelCampos.add(logo);
        animacionIzquierda(logo, logo.getX() + 400, logo.getX());

        this.add(panelCampos, BorderLayout.CENTER);

    }

}
