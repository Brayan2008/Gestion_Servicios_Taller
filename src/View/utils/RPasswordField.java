package View.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class RPasswordField extends JPasswordField {
    
    int width, heigth;
    Color color, copy_color;
 
    public RPasswordField(int width, int heigth, Color color) {
        this.width = width;
        this.heigth = heigth;
        this.color = color;
        this.copy_color = color;
        setPreferredSize(new Dimension(width, heigth));
        setOpaque(false);
        setBorder(new EmptyBorder(0, 9, 0, 9));
        addFocusListener(focus);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = ((Graphics2D) g);
        g2d.setColor(this.color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
        super.paintComponent(g);
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
