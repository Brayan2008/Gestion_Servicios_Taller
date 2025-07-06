/*package View;

import Services.CatalogoServiciosService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaAgregarServicio extends JDialog {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private final CatalogoServiciosService serviciosService;
    private final CatalogoServiciosView vistaPadre;

    public VentanaAgregarServicio(JFrame parent, CatalogoServiciosView vistaPadre, CatalogoServiciosService serviciosService) {
        super(parent, "Agregar Servicio", true);
        this.serviciosService = serviciosService;
        this.vistaPadre = vistaPadre;


        // panel campos
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 10));

        panelCampos.add(new JLabel("Código (2 caracteres):"));
        txtCodigo = new JTextField();
        panelCampos.add(txtCodigo);

        panelCampos.add(new JLabel("Nombre del Servicio:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelCampos.add(txtPrecio);

        // panel botones
        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        btnGuardar.addActionListener(this::guardarServicio);
        btnCancelar.addActionListener(e -> dispose());

        this.setLayout(new BorderLayout(10, 10));
        this.add(panelCampos, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void guardarServicio(ActionEvent e) {
        String cod = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();

        // validaciones
        if (cod.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }
        if (cod.length() != 2) {
            JOptionPane.showMessageDialog(this, "El código debe tener exactamente 2 caracteres");
            return;
        }

        try {
            precioStr = precioStr.replace(',','.');
            double precio = Double.parseDouble(precioStr);

            if (precio <= 0) {
            JOptionPane.showMessageDialog(this, "El precio debe ser mayor a cero");
            return;
        }

            // llamamos al servicio
            serviciosService.insertarServicio(cod, nombre, precio);
            JOptionPane.showMessageDialog(this, "Servicio agregado correctamente.");
            //funcion para refrescar la tabla del view 
            if(getParent() instanceof CatalogoServiciosView){
                ((CatalogoServiciosView)getParent()).cargarDatosServicios();
            }
            vistaPadre.cargarDatosServicios(); //recarga la tabla

            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }
}
*/
