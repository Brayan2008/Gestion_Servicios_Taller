package controllers;

import Services.VehiculoService;
import View.AgregarVehiculoView;
import View.utils.RButton;
import View.utils.RTextField;

public class AgregarVehiculoController {
    private final VehiculoService veh;

    AgregarVehiculoView av;
    RButton btnGuardar, btnCancelar;
    RTextField txtPlaca, txtModelo, txtMarca, txtChasis, txtMotor, txtAño,txtColor;
    public AgregarVehiculoController (VehiculoService veh){
        this.av = new AgregarVehiculoView(null);

        this.av.setVisible(false);
        this.av.setModal(false);

        this.veh = veh;
        this.btnCancelar = av.btnCancelar;
        this.btnGuardar = av.btnGuardar;
        this.txtPlaca = av.txtPlaca;
        this.txtModelo = av.txtModelo;
        this.txtMarca = av.txtMarca;
    }
}
