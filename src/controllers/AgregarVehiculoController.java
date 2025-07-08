package controllers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Services.VehiculoService;
import View.AgregarVehiculoView;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RTextField;

public class AgregarVehiculoController {
    private final VehiculoService veh;

    AgregarVehiculoView av;
    JTable tabla;
    JLabel tittle;
    RButton btnGuardar, btnCancelar;
    RTextField txtPlaca, txtModelo, txtMarca, txtChasis, txtMotor, txtAño,txtColor;
    int opcion;

    public AgregarVehiculoController (VehiculoService veh, int opcion){
        this.av = new AgregarVehiculoView(null);

        this.av.setVisible(false);
        this.av.setModal(false);

        this.veh = veh;

        this.tittle = av.titleLabel;
        this.btnCancelar = av.btnCancelar;
        this.btnGuardar = av.btnGuardar;
        this.tabla = VehiculoViewController.table;
        this.txtPlaca = av.txtPlaca;
        this.txtModelo = av.txtModelo;
        this.txtMarca = av.txtMarca;
        this.txtChasis = av.txtChasis;
        this.txtMotor = av.txtMotor;
        this.txtAño = av.txtAño;
        this.txtColor = av.txtColor;
        this.opcion = opcion;
        addListener();

        this.av.setModal(true); // Establece como Modal y luego bloquea
        this.av.setVisible(true);
    }
    protected void addListener() {
        btnGuardar.addActionListener(e -> guardarVehiculo());
        btnCancelar.addActionListener(e -> av.dispose());

        if (opcion == 2 || opcion == 1) {
            txtPlaca.setText(tabla.getValueAt(VehiculoViewController.row, 0).toString().trim());
            txtPlaca.setFont(Colors.SubTitles);
            txtPlaca.setEditable(false);

            txtMarca.setText(tabla.getValueAt(VehiculoViewController.row, 1).toString());
            txtModelo.setText(tabla.getValueAt(VehiculoViewController.row, 2).toString());
            txtChasis.setText(tabla.getValueAt(VehiculoViewController.row, 2).toString());
            txtMotor.setText(tabla.getValueAt(VehiculoViewController.row, 2).toString());
            txtAño.setText(tabla.getValueAt(VehiculoViewController.row, 2).toString());
            txtColor.setText(tabla.getValueAt(VehiculoViewController.row, 2).toString());

            switch (opcion) {
                case 1:
                    tittle.setText("EDITAR");
                    break;
                case 2:
                    tittle.setText("ELIMINAR");
                    btnGuardar.setColor(Colors.TEMA_BUTTONS_ROJO, Colors.BUTTONS_FONDO_ROJO);
                    btnGuardar.setText("Eliminar");
                    btnCancelar.setColor(Colors.TEMA_BUTTONS2, Colors.BUTTONS_FONDO_2);
                    txtMarca.setEditable(false);
                    txtModelo.setEditable(false);
                    txtChasis.setEditable(false);
                    txtMotor.setEditable(false);
                    txtAño.setEditable(false);
                    txtColor.setEditable(false);
                    btnGuardar.removeActionListener(btnGuardar.getActionListeners()[0]);
                    btnGuardar.addActionListener(e -> eliminarVehiculo());
                default:
                    break;
            }
        }
    }

    public void eliminarVehiculo() {
        var placaigo = tabla.getValueAt(VehiculoViewController.row, 1).toString().trim();
        try {
            veh.eliminarVehiculo(placaigo); 
            JOptionPane.showMessageDialog(av, "Vehiculo eliminado correctamente.");
            veh.buscarVehiculo("");
            av.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(av, "Error en la eliminacion");
        }
    }

    protected void guardarVehiculo() {
        String placa = txtPlaca.getText().trim();
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String chasis = txtChasis.getText().trim();
        String motor = txtMotor.getText().trim();
        String añoStr = txtAño.getText().trim();
        String color = txtColor.getText().trim();

        // validaciones
        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() ||chasis.isEmpty()||motor.isEmpty()||añoStr.isEmpty()||color.isEmpty()) {
            JOptionPane.showMessageDialog(av, "Todos los campos son obligatorios");
            return;
        }

        if (placa.length() != 7) {
            JOptionPane.showMessageDialog(av, "La placa debe tener exactamente 7 caracteres");
            return;
        }

        try {
            int año = Integer.parseInt(añoStr);
            // llamamos al Vehiculo (1.- Editar)
            if (opcion == 1) {
                veh.actualizarVehiculo(placa, marca, modelo, chasis, motor, año, color);
                JOptionPane.showMessageDialog(av, "Vehiculo actualizado correctamente.");
            } else {
                veh.insertarVehiculo(placa, marca, modelo, chasis, motor, año, color);
                JOptionPane.showMessageDialog(av, "Vehiculo agregado correctamente.");
            }
            veh.buscarVehiculo("");
            av.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(av, "El año debe ser un número válido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(av, "Error: " + ex.getMessage());
        }
    }
}
