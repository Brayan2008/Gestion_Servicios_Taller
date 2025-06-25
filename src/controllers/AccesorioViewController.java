package controllers;

import javax.swing.*;
import javax.swing.text.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Services.AccesoriosService;
import View.AccesorioView;

public class AccesorioViewController {
    private AccesoriosService as;
    private AccesorioView av;

    public AccesorioViewController(AccesorioView av) {
        this.av = av;
        this.as = new AccesoriosService();

        // Asociar eventos a los botones
        this.av.btnAgregar.addActionListener(e -> mostrarFormulario("agregar"));
        this.av.btnListar.addActionListener(e -> mostrarFormulario("listar"));
        this.av.btnModificar.addActionListener(e -> mostrarFormulario("modificar"));
        this.av.btnEliminar.addActionListener(e -> mostrarFormulario("eliminar"));
    }

    private void mostrarFormulario(String accion) {
        av.panelCentral.removeAll(); // Limpiar contenido anterior

        String mensaje = switch (accion) {
            case "agregar" -> "Bienvenido al registro de Accesorios";
            case "listar" -> "Lista de Accesorios";
            case "modificar" -> "Modificación de Accesorios";
            case "eliminar" -> "Eliminación de Accesorios";
            default -> "";
        };

        JLabel titulo = new JLabel(mensaje);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        av.panelCentral.add(Box.createVerticalStrut(10));
        av.panelCentral.add(titulo);

        // Solo para agregar, modificar
        if (accion.equals("agregar") || accion.equals("modificar")) {

            // Panel de formulario con GridBagLayout
            JPanel panelFormulario = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5); // Márgenes entre componentes
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;

            // Etiquetas y campos
            JLabel lblId = new JLabel("ID:");
            JTextField txtId = new JTextField(15);

            JLabel lblNombre = new JLabel("Nombre:");
            JTextField txtNombre = new JTextField(15);

            // Fila 1 - ID
            gbc.gridx = 0;
            gbc.gridy = 0;
            panelFormulario.add(lblId, gbc);

            gbc.gridx = 1;
            panelFormulario.add(txtId, gbc);

            // Fila 2 - Nombre
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelFormulario.add(lblNombre, gbc);

            gbc.gridx = 1;
            panelFormulario.add(txtNombre, gbc);

            // Agregar al panel central
            av.panelCentral.add(Box.createVerticalStrut(15));
            av.panelCentral.add(panelFormulario);

            // Botón Confirmar
            JButton btnConfirmar = new JButton("Confirmar");
            btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
            av.panelCentral.add(Box.createVerticalStrut(10));
            av.panelCentral.add(btnConfirmar);

            btnConfirmar.addActionListener(e -> {
                int opcion = JOptionPane.showConfirmDialog(
                        av,
                        "¿Estás seguro que deseas " + accion + " este accesorio?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    String id = txtId.getText();
                    String nombre = txtNombre.getText();

                    JOptionPane.showMessageDialog(av,
                            "Accesorio " + (accion.equals("agregar") ? "registrado" : "modificado") +
                                    ":\nID: " + id + "\nNombre: " + nombre);

                    // Aquí puedes llamar al servicio: as.agregar(new Accesorio(...))
                }
            });
        }

        av.panelCentral.revalidate();
        av.panelCentral.repaint();
    }
}
