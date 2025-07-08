package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import View.ClienteView;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RTextField;

public class ClienteViewController {

    ClienteView vw;
    private RTextField text;
    public static JTable table;
    public static int row;
    private RButton btnAdd, btnMod, btnDel;

    public ClienteViewController() {
        this.vw = new ClienteView();
        text = vw.buscar;
        table = vw.tablaServicios;
        btnAdd = vw.btnAdd;
        btnMod = vw.btnMod;
        btnDel = vw.btnDel;
        btnDel.setColor(Colors.GRAY, Colors.GRAY);
        btnMod.setColor(Colors.GRAY, Colors.GRAY);
        btnDel.setEnabled(false);
        btnMod.setEnabled(false);
        addListeners();
    }

    public ClienteView getVw() {
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
                    ClienteView.css.buscarCliente(text.getText());
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

            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                new ClienteCRUDController(ClienteView.css, 0); // (1 -> Editar, 2-> Eliminar, 0 -> Guardar)
            }
        });

        btnMod.addActionListener(e -> {
            desactivar();
            new ClienteCRUDController(ClienteView.css, 1); // 1
        });

        btnDel.addActionListener(e -> {
            desactivar();
            new ClienteCRUDController(ClienteView.css, 2);
        });
    }
}