package View.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class RButton extends JButton implements Animation {

    int width, heigth;
    String text;

    Color color = Color.PINK,
            copy_color = color,
            color2 = Colors.BLUE_BLACK,
            textColor = Color.BLACK,
            copy_text = textColor,
            textColor2 = Color.WHITE;

    public RButton(String text) {
        this.text = text;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(2, 10, 2, 10));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setText(text);
        setFocusPainted(false);
        addMouseListener(mouseactions);
    }

    public RButton setTamaño(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
        setPreferredSize(new Dimension(width, heigth));
        return this;
    }

    public RButton setColor(Color color, Color color2) {
        this.color = color;
        this.color2 = color2;
        this.copy_color = color;
        return this;
    }

    public RButton setTextColor(Color color1, Color color2) {
        this.textColor = color1;
        this.textColor2 = color2;
        this.copy_text = color1;
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = ((Graphics2D) g);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));

        super.paintComponent(g);
    }

    public void inFocus() {
        color = color2;
        textColor = textColor2;
        setForeground(textColor);
        repaint();
    }
    
    public void noFocus() {
        color = copy_color;
        textColor = copy_text;
        setForeground(textColor);
        repaint();
    }

    MouseAdapter mouseactions = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            inFocus();
        }

        @Override
        public void mouseExited(MouseEvent e) {            
           noFocus();
        }
    };

    FocusAdapter focus = new FocusAdapter() {
        
        @Override
        public void focusGained(FocusEvent e) {
            inFocus();
        }

        @Override
        public void focusLost(FocusEvent e) {
            noFocus();
        }
    };

}