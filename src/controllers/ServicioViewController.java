package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import View.AgregarServicioView;
import View.CatalogoServiciosView;
import View.principal;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RTextField;

public class ServicioViewController {

    CatalogoServiciosView vw;
    private RTextField text; 
    private JTable table;
    private RButton btnAdd,btnMod,btnDel;

    public ServicioViewController() {
        this.vw = new CatalogoServiciosView();
        text = vw.buscar;
        table = vw.tablaServicios;
        btnAdd = (RButton) vw.btnAdd;       
        btnMod = (RButton) vw.btnMod;       
        btnDel = (RButton) vw.btnDel;
        btnDel.setColor(Colors.GRAY, Colors.GRAY);
        btnMod.setColor(Colors.GRAY, Colors.GRAY);
        btnDel.setEnabled(false);     
        btnMod.setEnabled(false);      
        addListeners();
    }
    
    public CatalogoServiciosView getVw() {
        return vw;
    }

    public void addListeners() {
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    vw.css.buscarCliente(text.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        table.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                btnMod.setColor(Colors.TEMA_BUTTONS3, Colors.BUTTONS_FONDO_3);
                btnDel.setColor(Colors.TEMA_BUTTONS_ROJO, Colors.BUTTONS_FONDO_ROJO);
                btnMod.setEnabled(true);               
                btnDel.setEnabled(true);                               
            }
            @Override
            public void focusLost(FocusEvent e) {
                btnDel.setColor(Colors.GRAY, Colors.GRAY);
                btnMod.setColor(Colors.GRAY, Colors.GRAY);
                btnMod.setEnabled(false);               
                btnDel.setEnabled(false);                               
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarServicioController(vw.css);
            }
        });
    }
}