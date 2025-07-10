package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import View.Mecanico1View;
import View.utils.Colors;
import View.utils.RButton;
import View.utils.RTextField;

public class Mecanico1ViewController {

    protected Mecanico1View vw;
    private RTextField text;
    public static JTable table;
    public static int row;
    private RButton btnAdd, btnMod, btnDel;

    public Mecanico1ViewController() {
        this.vw = new Mecanico1View();
        text = vw.buscar;
        table = Mecanico1View.tablaMecanicos;
        btnAdd = vw.btnAdd;
        btnMod = vw.btnMod;
        btnDel = vw.btnDel;
        btnDel.setColor(Colors.GRAY, Colors.GRAY);
        btnMod.setColor(Colors.GRAY, Colors.GRAY);
        btnDel.setEnabled(false);
        btnMod.setEnabled(false);
        addListeners();
    }

    public Mecanico1View getVw() {
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
                    Mecanico1View.css.buscarMecanico(text.getText());
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
                new AgregarMecanico1Controller(Mecanico1View.css,0); // (1 -> Editar, 2-> Eliminar, 0 -> Guardar)
            }
        });

        btnMod.addActionListener(e -> {
            desactivar();
            new AgregarMecanico1Controller(Mecanico1View.css, 1); // 1
        });
        
        btnDel.addActionListener(e -> {
            desactivar();
            new AgregarMecanico1Controller(Mecanico1View.css, 2);
        });
    }
}