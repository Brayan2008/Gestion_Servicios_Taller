package View;

import View.utils.JViews;
import View.utils.RButton;
import View.utils.Colors;
import View.utils.RTextField;

import javax.swing.*;
import java.awt.*;

public class AgregarOrdenView extends JDialog implements JViews {
    public RTextField txtNroOrden;
    public RTextField txtFechaOrden;
    public RTextField txtKilometraje;
    public RTextField txtFechaEntrega;
    public RTextField txtNumCombustible;
    public RTextField txtObservacion;
    public JCheckBox chkTarjeta;
    public JCheckBox chkManual;
    public JCheckBox chkLlave;
    public JComboBox<String> cmbEstado;
    public JCheckBox chkQuiniado;
    public JCheckBox chkRayado;
    public JCheckBox chkAbollado;
    public RTextField txtDocumento;
    public RTextField txtNroDocumento;
    public RTextField txtMecanico;
    public RTextField txtPlacaVehiculo;
    public JLabel titleLabel;
    public RButton btnGuardar, btnCancelar;

    public AgregarOrdenView(JFrame parent) {
        super(parent, "Agregar/Editar Orden", true);
        agregarComponentes();
        this.setSize(500, 700);
        this.setLocationRelativeTo(parent);
        this.setLayout(new BorderLayout(15, 15));
        // this.setVisible(true); // Quitado para evitar mostrar la ventana antes de tiempo
    }

    @Override
    public void init() {}

    @Override
    public void agregarComponentes() {
        int y = 10;
        int x = 20;
        int h = 28;

        JPanel panelCampos = new JPanel(null);
        panelCampos.setPreferredSize(new Dimension(450, 900));

        titleLabel = new JLabel("AGREGAR ORDEN");
        titleLabel.setFont(Colors.Titles);
        titleLabel.setBounds(x, y, 350, 35);
        panelCampos.add(titleLabel);

        // Nro. Orden
        JLabel nroOrdenLabel = new JLabel("Nro. Orden:");
        nroOrdenLabel.setFont(Colors.FieldBoldText);
        nroOrdenLabel.setBounds(x, y += 50, 200, 20);
        panelCampos.add(nroOrdenLabel);
        txtNroOrden = new RTextField(150, 25, Color.WHITE);
        txtNroOrden.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtNroOrden);

        // Fecha Orden
        JLabel fechaOrdenLabel = new JLabel("Fecha Orden (YYYY-MM-DD):");
        fechaOrdenLabel.setFont(Colors.FieldBoldText);
        fechaOrdenLabel.setBounds(x, y += 40, 250, 20);
        panelCampos.add(fechaOrdenLabel);
        txtFechaOrden = new RTextField(150, 25, Color.WHITE);
        txtFechaOrden.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtFechaOrden);

        // Kilometraje
        JLabel kilometrajeLabel = new JLabel("Kilometraje:");
        kilometrajeLabel.setFont(Colors.FieldBoldText);
        kilometrajeLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(kilometrajeLabel);
        txtKilometraje = new RTextField(150, 25, Color.WHITE);
        txtKilometraje.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtKilometraje);

        // Fecha Entrega
        JLabel fechaEntregaLabel = new JLabel("Fecha Entrega (YYYY-MM-DD):");
        fechaEntregaLabel.setFont(Colors.FieldBoldText);
        fechaEntregaLabel.setBounds(x, y += 40, 250, 20);
        panelCampos.add(fechaEntregaLabel);
        txtFechaEntrega = new RTextField(150, 25, Color.WHITE);
        txtFechaEntrega.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtFechaEntrega);

        // Número de Combustible
        JLabel numCombustibleLabel = new JLabel("Número de Combustible:");
        numCombustibleLabel.setFont(Colors.FieldBoldText);
        numCombustibleLabel.setBounds(x, y += 40, 250, 20);
        panelCampos.add(numCombustibleLabel);
        txtNumCombustible = new RTextField(150, 25, Color.WHITE);
        txtNumCombustible.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtNumCombustible);

        // Observación
        JLabel observacionLabel = new JLabel("Observación:");
        observacionLabel.setFont(Colors.FieldBoldText);
        observacionLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(observacionLabel);
        txtObservacion = new RTextField(150, 25, Color.WHITE);
        txtObservacion.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtObservacion);

        // Tarjeta
        chkTarjeta = new JCheckBox("Tarjeta de Propiedad");
        chkTarjeta.setBounds(x, y += 40, 200, 25);
        panelCampos.add(chkTarjeta);

        // Manual
        chkManual = new JCheckBox("Manual de Propietario");
        chkManual.setBounds(x + 220, y, 200, 25);
        panelCampos.add(chkManual);

        // Llave
        chkLlave = new JCheckBox("Llave del Vehículo");
        chkLlave.setBounds(x, y += 35, 200, 25);
        panelCampos.add(chkLlave);

        // Estado
        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setFont(Colors.FieldBoldText);
        estadoLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(estadoLabel);
        cmbEstado = new JComboBox<>(new String[]{"Pendiente", "En Proceso", "Finalizada", "Cancelada"});
        cmbEstado.setBounds(x, y += 25, 200, h);
        panelCampos.add(cmbEstado);

        // Quiñado
        chkQuiniado = new JCheckBox("Quiñado");
        chkQuiniado.setBounds(x, y += 40, 100, 25);
        panelCampos.add(chkQuiniado);
        // Rayado
        chkRayado = new JCheckBox("Rayado");
        chkRayado.setBounds(x + 120, y, 100, 25);
        panelCampos.add(chkRayado);
        // Abollado
        chkAbollado = new JCheckBox("Abollado");
        chkAbollado.setBounds(x + 240, y, 100, 25);
        panelCampos.add(chkAbollado);

        // Documento
        JLabel documentoLabel = new JLabel("Documento:");
        documentoLabel.setFont(Colors.FieldBoldText);
        documentoLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(documentoLabel);
        txtDocumento = new RTextField(150, 25, Color.WHITE);
        txtDocumento.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtDocumento);

        // Nro. Documento
        JLabel nroDocumentoLabel = new JLabel("Nro. Documento:");
        nroDocumentoLabel.setFont(Colors.FieldBoldText);
        nroDocumentoLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(nroDocumentoLabel);
        txtNroDocumento = new RTextField(150, 25, Color.WHITE);
        txtNroDocumento.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtNroDocumento);

        // Mecánico
        JLabel mecanicoLabel = new JLabel("Mecánico:");
        mecanicoLabel.setFont(Colors.FieldBoldText);
        mecanicoLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(mecanicoLabel);
        txtMecanico = new RTextField(150, 25, Color.WHITE);
        txtMecanico.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtMecanico);

        // Placa Vehículo
        JLabel placaVehiculoLabel = new JLabel("Placa Vehículo:");
        placaVehiculoLabel.setFont(Colors.FieldBoldText);
        placaVehiculoLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(placaVehiculoLabel);
        txtPlacaVehiculo = new RTextField(150, 25, Color.WHITE);
        txtPlacaVehiculo.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtPlacaVehiculo);

        // BOTONES
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        btnGuardar = new RButton("Guardar").setTamaño(120, 35).setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
        btnCancelar = new RButton("Cancelar").setTamaño(120, 35).setColor(Colors.TEMA_BUTTONS_ROJO, Colors.BUTTONS_FONDO_ROJO);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        JScrollPane scrollPane = new JScrollPane(panelCampos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);
    }
}
