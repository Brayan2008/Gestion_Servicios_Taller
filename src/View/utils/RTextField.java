package View.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RTextField extends JTextField {

    int width, heigth;
    Color color, copy_color;
    Graphics2D g2d;

    String text;

    public RTextField(int width, int heigth, Color color) {
        this.width = width;
        this.heigth = heigth;
        this.color = color;
        this.copy_color = color;
        setPreferredSize(new Dimension(width, heigth));
        setOpaque(false);
        setBorder(new EmptyBorder(3, 9, 3, 9));
        addFocusListener(focus);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = ((Graphics2D) g);
        g2d.setColor(this.color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
        super.paintComponent(g);
    }

    public void setPlaceholder(String text) {
        this.text = text;
        setText(text);
    }

    FocusAdapter focus = new FocusAdapter() {

        @Override
        public void focusGained(FocusEvent e) {
            color = Colors.GRAY;
            repaint();
        }
        
        @Override
        public void focusLost(FocusEvent e) {
            color = copy_color;
            repaint();
        }

    };

}
