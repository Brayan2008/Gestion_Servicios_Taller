package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import View.OrdenView;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RTextField;

public class OrdenViewController {
    protected OrdenView vw;
    private RTextField text;
    public static JTable table;
    public static int row;
    private RButton btnAdd, btnMod, btnDel;

    public OrdenViewController() {
        this.vw = new OrdenView();
        text = vw.buscar;
        table = OrdenView.tablaOrdenes;
        btnAdd = vw.btnAdd;       
        btnMod = vw.btnMod;       
        btnDel = vw.btnDel;
        btnDel.setColor(Colors.GRAY, Colors.GRAY);
        btnMod.setColor(Colors.GRAY, Colors.GRAY);
        btnDel.setEnabled(false);     
        btnMod.setEnabled(false);      
        addListeners();
    }
    
    public OrdenView getVw() {
        return vw;
    }

    public void desactivar() {
        row = table.getSelectedRow();
        btnMod.setEnabled(false);
        btnDel.setEnabled(false);
        btnMod.setColor(Colors.GRAY, Colors.GRAY);
        btnDel.setColor(Colors.GRAY, Colors.GRAY);
    }

    public void addListeners() {
        text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                desactivar();
            }
        });
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    vw.os.buscarOrden(text.getText());
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
            public void focusLost(FocusEvent e) {}
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Entró a listener de Añadir");
                desactivar();
                new AgregarOrdenController(vw.os, 0);
            }
        });

        btnMod.addActionListener(e -> {
            System.out.print("Entró a listener de modificar");
            desactivar();
            new AgregarOrdenController(vw.os, 1);
        });

        btnDel.addActionListener(e -> {
            desactivar();
            new AgregarOrdenController(vw.os, 2);
        });
    }
}
