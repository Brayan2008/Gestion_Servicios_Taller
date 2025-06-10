package View.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class RPanel extends JPanel {

    boolean flag = false;
    int width, heigth;
    Color color;
    Graphics2D g2d;

    public RPanel(int width, int heigth, Color color) {
        this.width = width;
        this.heigth = heigth;
        this.color = color;
        setPreferredSize(new Dimension(width, heigth));
        setOpaque(false); // Transparente pero no para el paintComponent -> border redondeados
    }

    public RPanel Posicion(int x, int y) {
        setBounds(x, y, width, heigth);
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
        if (flag) {
            // Sombra
            System.out.println("Se activo");
            g2d.setColor(new Color(255, 255, 255, 20));
            g2d.fill(new RoundRectangle2D.Float(5, 5, getWidth(), getHeight(), 20, 20));
        }
    }

    public RPanel ActivarSombra() {
        flag = true;
        return this;
    }

}