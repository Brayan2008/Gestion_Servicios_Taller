package controllers;

import javax.swing.*;
import javax.swing.text.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Services.AccesoriosService;
import View.AccesorioView;
import java.util.List;
import Model.Accesorios;

public class AccesorioViewController {
    private AccesoriosService as;
    private AccesorioView av;
    private JTextField textFieldId;

    public AccesorioViewController(AccesorioView av) {
        this.av = av;
        this.as = new AccesoriosService();

        // Asociar eventos a los botones
        this.av.btnAgregar.addActionListener(e -> mostrarFormulario());
        // this.av.btnListar.addActionListener(e -> mostrarFormulario());
        this.av.btnModificar.addActionListener(e -> mostrarFormulario());
        this.av.btnEliminar.addActionListener(e -> mostrarFormulario());
    }

    private void mostrarFormulario() {
        av.panelCentral.removeAll();

        JLabel titulo = new JLabel("Módulo de Creación de Accesorios");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        av.panelCentral.add(Box.createVerticalStrut(10));
        av.panelCentral.add(titulo);
        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 10, 10));

        textFieldId = new JTextField(20);
        panelCampos.add(new JLabel("Cod Accesorio:"));
        panelCampos.add(textFieldId);

        // Tabla para mostrar los accesorios
        String[] columnas = { "ID", "Nombre" };
        List<Accesorios> accesorios = as.obtenerAccesorios();
        String[][] data = new String[accesorios.size()][2];

        for (int i = 0; i < accesorios.size(); i++) {
            data[i][0] = String.valueOf(accesorios.get(i).getID());
            data[i][1] = accesorios.get(i).getNombreAccesorio();
        }

        JTable tabla = new JTable(data, columnas){
        @Override
        public boolean isCellEditable(int row, int column) {
        return false; // Ninguna celda editable
    }
        };

        JScrollPane scroll = new JScrollPane(
                tabla);
        scroll.setPreferredSize(new Dimension(400, 150));
        av.panelCentral.add(scroll);

        // Formulario inferior
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        panelForm.add(new JLabel("ID:"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);

        av.panelCentral.add(Box.createVerticalStrut(10));
        av.panelCentral.add(panelForm);

        // Botones Agregar / Modificar
        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        panelBotones.add(btnGuardar);
        av.panelCentral.add(panelBotones);

        btnGuardar.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    av,
                    "¿Estás seguro que deseas guardar este accesorio?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                String id = txtId.getText().trim();
                String nombre = txtNombre.getText();
                as.guardarAccesorio(new Accesorios(id, nombre));
                // Validamos que el id y nombre no esten vacios (Primary Key y notnull)
                if (id.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    return;
                }
                if (id.length() != 2) {
                    JOptionPane.showMessageDialog(null, "El código del accesorio debe tener exactamente 2 caracteres.");
                    return;
                }

                JOptionPane.showMessageDialog(av, "Accesorio guardado correctamente");
                mostrarFormulario(); // Recargar vista
            }
        });

        av.panelCentral.revalidate();
        av.panelCentral.repaint();
    }

}
