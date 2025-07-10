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
    public RButton btnBuscarCliente, btnBuscarMecanico, btnBuscarVehiculo;

    public AgregarOrdenView(JFrame parent) {
        super(parent, "Agregar/Editar Orden");
        agregarComponentes();
        this.setSize(700, 700);
        this.setLocationRelativeTo(parent);
        setVisible(true);
        this.setLayout(new BorderLayout(15, 15));
    }

    @Override
    public void init() {
    }

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
        animacionDerecha(titleLabel, x - 170, x);
        panelCampos.add(titleLabel);

        // Nro. Orden
        JLabel nroOrdenLabel = new JLabel("Nro. Orden:");
        nroOrdenLabel.setFont(Colors.FieldBoldText);
        nroOrdenLabel.setBounds(x, y += 50, 200, 20);
        panelCampos.add(nroOrdenLabel);

        JLabel documentoLabel = new JLabel("Documento:");
        documentoLabel.setFont(Colors.FieldBoldText);
        documentoLabel.setBounds(x + 230, y, 200, 20);
        panelCampos.add(documentoLabel);

        txtNroOrden = new RTextField(150, 25, Color.WHITE);
        txtNroOrden.setBounds(x, y += 25, 200, h);
        panelCampos.add(txtNroOrden);

        txtDocumento = new RTextField(150, 25, Color.WHITE);
        txtDocumento.setBounds(x + 230, y, 200, h);
        txtDocumento.setEditable(false);
        panelCampos.add(txtDocumento);

        // Fecha Orden
        JLabel fechaOrdenLabel = new JLabel("Fecha Orden (YYYY-MM-DD):");
        fechaOrdenLabel.setFont(Colors.FieldBoldText);
        fechaOrdenLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(fechaOrdenLabel);

        // Nro. Documento
        JLabel nroDocumentoLabel = new JLabel("Nro. Documento:");
        nroDocumentoLabel.setFont(Colors.FieldBoldText);
        nroDocumentoLabel.setBounds(x + 230, y, 200, 20);
        panelCampos.add(nroDocumentoLabel);

        txtFechaOrden = new RTextField(150, 25, Color.WHITE);
        txtFechaOrden.setBounds(x, y += 25, 200, h);
        panelCampos.add(txtFechaOrden);

        txtNroDocumento = new RTextField(150, 25, Color.WHITE);
        txtNroDocumento.setBounds(x + 230, y, 200, h);
        txtNroDocumento.setEditable(false);
        panelCampos.add(txtNroDocumento);

        // Boton Agregar Clientes
        btnBuscarCliente = new RButton("Agregar Cliente")
                .setTamaño(140, h)
                .setColor(Colors.BUTTONS_FONDO_3, Colors.BUTTONS_FONDO_2);
        btnBuscarCliente.setBounds(x + 450, y - 30, 140, h);
        panelCampos.add(btnBuscarCliente);

        // Kilometraje
        JLabel kilometrajeLabel = new JLabel("Kilometraje:");
        kilometrajeLabel.setFont(Colors.FieldBoldText);
        kilometrajeLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(kilometrajeLabel);

        // Mecánico
        JLabel mecanicoLabel = new JLabel("Mecánico:");
        mecanicoLabel.setFont(Colors.FieldBoldText);
        mecanicoLabel.setBounds(x + 230, y, 200, 20);
        panelCampos.add(mecanicoLabel);

        txtKilometraje = new RTextField(150, 25, Color.WHITE);
        txtKilometraje.setBounds(x, y += 25, 200, h);
        panelCampos.add(txtKilometraje);

        txtMecanico = new RTextField(150, 25, Color.WHITE);
        txtMecanico.setBounds(x + 230, y, 200, h);
        txtMecanico.setEditable(false);
        panelCampos.add(txtMecanico);

        // Boton Agregar Mecanico
        btnBuscarMecanico = new RButton("Agregar Mecanico")
                .setTamaño(140, h)
                .setColor(Colors.BUTTONS_FONDO_3, Colors.BUTTONS_FONDO_2);
        btnBuscarMecanico.setBounds(x + 450, y, 140, h);
        panelCampos.add(btnBuscarMecanico);

        // Fecha Entrega
        JLabel fechaEntregaLabel = new JLabel("Fecha Entrega (YYYY-MM-DD):");
        fechaEntregaLabel.setFont(Colors.FieldBoldText);
        fechaEntregaLabel.setBounds(x, y += 40, 210, 20);
        panelCampos.add(fechaEntregaLabel);

        JLabel placaVehiculoLabel = new JLabel("Placa Vehículo:");
        placaVehiculoLabel.setFont(Colors.FieldBoldText);
        placaVehiculoLabel.setBounds(x + 230, y, 200, 20);
        panelCampos.add(placaVehiculoLabel);

        txtFechaEntrega = new RTextField(150, 25, Color.WHITE);
        txtFechaEntrega.setBounds(x, y += 25, 200, h);
        panelCampos.add(txtFechaEntrega);

        // Placa Vehículo
        txtPlacaVehiculo = new RTextField(150, 25, Color.WHITE);
        txtPlacaVehiculo.setBounds(x + 230, y, 200, h);
        txtPlacaVehiculo.setEditable(false);
        panelCampos.add(txtPlacaVehiculo);

        // Boton Agregar Vehiculo
        btnBuscarVehiculo = new RButton("Agregar Vehiculo")
                .setTamaño(140, h)
                .setColor(Colors.BUTTONS_FONDO_3, Colors.BUTTONS_FONDO_2);
        btnBuscarVehiculo.setBounds(x + 450, y, 140, h);
        panelCampos.add(btnBuscarVehiculo);
        // -----------------------

        // Número de Combustible
        JLabel numCombustibleLabel = new JLabel("Número de Combustible:");
        numCombustibleLabel.setFont(Colors.FieldBoldText);
        numCombustibleLabel.setBounds(x, y += 40, 250, 20);
        panelCampos.add(numCombustibleLabel);
        txtNumCombustible = new RTextField(150, 25, Color.WHITE);
        txtNumCombustible.setBounds(x, y += 25, 250, h);
        panelCampos.add(txtNumCombustible);

        // Quiñado
        chkQuiniado = new JCheckBox("Quiñado");
        chkQuiniado.setBounds(x + 290, y + 8, 100, 25);
        panelCampos.add(chkQuiniado);
        // Rayado
        chkRayado = new JCheckBox("Rayado");
        chkRayado.setBounds(x + 410, y + 8, 100, 25);
        panelCampos.add(chkRayado);
        // Abollado
        chkAbollado = new JCheckBox("Abollado");
        chkAbollado.setBounds(x + 530, y + 8, 100, 25);
        panelCampos.add(chkAbollado);

        // Observación
        JLabel observacionLabel = new JLabel("Observación:");
        observacionLabel.setFont(Colors.FieldBoldText);
        observacionLabel.setBounds(x, y += 40, 200, 20);
        panelCampos.add(observacionLabel);
        
        // Estado
        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setFont(Colors.FieldBoldText);
        estadoLabel.setBounds(x + 420, y, 200, 20);
        panelCampos.add(estadoLabel);

        txtObservacion = new RTextField(150, 25, Color.WHITE);
        txtObservacion.setBounds(x, y += 25, 400, h);
        panelCampos.add(txtObservacion);

        cmbEstado = new JComboBox<>(new String[] { "Pendiente", "En Proceso", "Finalizada", "Cancelada" });
        cmbEstado.setBounds(x + 420, y, 200, h);
        panelCampos.add(cmbEstado);

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

        // ------------------------ Terminacion de todo
        // --------------------------------------------------------------------
        // Documento

        // BOTONES
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        btnGuardar = new RButton("Guardar").setTamaño(120, 35).setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
        btnCancelar = new RButton("Cancelar").setTamaño(120, 35).setColor(Colors.TEMA_BUTTONS_ROJO,
                Colors.BUTTONS_FONDO_ROJO);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        JScrollPane scrollPane = new JScrollPane(panelCampos);
        scrollPane.getVerticalScrollBar().setUnitIncrement(18);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);
    }
}
