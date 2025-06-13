package View.utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public interface Animation {

    default void animacionSubir(JComponent component, int startY, int endY) {
        new Timer(5, new ActionListener() {
            
            int copyY = startY;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                copyY -= 15;
                component.setLocation(component.getX(), copyY);
                var b = component.getParent();
                b.repaint();
                if (copyY < endY){
                    ((Timer) e.getSource()).stop();
                    component.setLocation(component.getY(), copyY);
                }
            }
            
        }).start();
    }

    default void animacionBajar(JComponent component, int startY, int endY) {
       new Timer(5, new ActionListener() {

            int copyY = startY;

            @Override
            public void actionPerformed(ActionEvent e) {
                copyY += 15;
                component.setLocation(component.getX(), copyY);
                var b = component.getParent();
                b.repaint();
                if (copyY > endY){
                    ((Timer) e.getSource()).stop();
                    component.setLocation(component.getY(), copyY);
                }
            }

        }).start();
    }

    default void animacionDerecha(JComponent component, int startX, int endX) {
        new Timer(5, new ActionListener() {

            int copyT = startX;

            @Override
            public void actionPerformed(ActionEvent e) {
                copyT += 15;
                component.setLocation(copyT, component.getY());
                var b = component.getParent();
                b.repaint();
                if (copyT > endX){
                    ((Timer) e.getSource()).stop();
                    component.setLocation(endX, component.getY());
                }
                
            }
            
        }).start();
    }

    default void animacionIzquierda(JComponent component, int startX, int endX) {
       new Timer(5, new ActionListener() {

            int copyX = startX;

            @Override
            public void actionPerformed(ActionEvent e) {
                copyX -= 15;
                component.setLocation(copyX, component.getY());
                var b = component.getParent();
                b.repaint();
                if (copyX < endX){
                    ((Timer) e.getSource()).stop();
                    component.setLocation(endX, component.getY());
                }
            }

        }).start();
    }

    default Timer animacionCrecer(JComponent component, int tamaño) {
        Timer a = new Timer(5, new ActionListener() {

            int tamaño_alcanzar = component.getWidth() + tamaño;
            int tamaño_alcanzar2 = component.getHeight() + tamaño;
            int w = component.getWidth();
            int h = component.getHeight();
            
            @Override
            public void actionPerformed(ActionEvent e) {
                component.setSize(w += 2, h += 2);
                var b = component.getParent();
                b.repaint();
                if (component.getWidth() >= tamaño_alcanzar){
                    component.setSize(tamaño_alcanzar,tamaño_alcanzar2);
                    ((Timer) e.getSource()).stop();
                }
                
            }
        });
        
        a.setRepeats(true);

        return a;        
    }
    
    default Timer animacionEncoger(JComponent component, int tamaño) {
        Timer a = new Timer(5, new ActionListener() {

            int tamaño_alcanzar = component.getWidth() - tamaño;
            int tamaño_alcanzar2 = component.getHeight() - tamaño;
            int w = component.getWidth();
            int h = component.getHeight();
            
            @Override
            public void actionPerformed(ActionEvent e) {
                component.setSize(w -= 2, h -= 2);
                var b = component.getParent();
                b.repaint();
                if (component.getWidth() <= tamaño_alcanzar ){
                    component.setSize(tamaño_alcanzar,tamaño_alcanzar2);
                    ((Timer) e.getSource()).stop();
                }
            }
            
        });
        
        a.setRepeats(true);
    
        return a;        

    }

    default void FadeComponentIn(JComponent component) {
        int r = component.getForeground().getRed();
        int g = component.getForeground().getGreen();
        int b = component.getForeground().getBlue();
        int r2 = component.getBackground().getRed();
        int g2 = component.getBackground().getGreen();
        int b2 = component.getBackground().getBlue();
        
       new Timer(1, new ActionListener() {
            
            int opacidad = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacidad += 15;
                component.setForeground(new Color(r, g, b, opacidad));
                component.setBackground(new Color(r2, g2, b2, opacidad));
                if (opacidad > 250) {
                    ((Timer) e.getSource()).stop();
                    component.setForeground(new Color(r, g, b, 250));
                    component.setBackground(new Color(r2, g2, b2, 250));
                }
            }
            
        }).start();
    };

    default void FadeComponentOut(JComponent component) {
        int r = component.getForeground().getRed();
        int g = component.getForeground().getGreen();
        int b = component.getForeground().getBlue();
        int r2 = component.getBackground().getRed();
        int g2 = component.getBackground().getGreen();
        int b2 = component.getBackground().getBlue();

         new Timer(1, new ActionListener() {

            int opacidad = 250;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacidad -= 15;
                component.setForeground(new Color(r, g, b, opacidad));
                component.setBackground(new Color(r2, g2, b2, opacidad));
                if (opacidad < 10) {
                    ((Timer) e.getSource()).stop();
                    component.setForeground(new Color(r, g, b, 0));
                    component.setBackground(new Color(r2, g2, b2, 0));
                }
            }

        }).start();
    };

    // #region Para ventanas

    default void FadeWindowsOut(JFrame jFrame) {
        jFrame.setOpacity(1f);

       new Timer(1, new ActionListener() {

            float opacidad = 1f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacidad -= 0.02f;
                jFrame.setOpacity(opacidad);
                if (opacidad < 0.1f) {
                    ((Timer) e.getSource()).stop();
                    jFrame.setOpacity(0);
                    jFrame.dispose();
                }
            }

        }).start();
    };

    default void FadeWindowsIn(JFrame jFrame) {
        jFrame.setOpacity(0f);

        new Timer(1, new ActionListener() {

            float opacidad = 0.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacidad += 0.02f;
                jFrame.setOpacity(opacidad);
                if (opacidad > 0.9f) {
                    ((Timer) e.getSource()).stop();
                    jFrame.setOpacity(1);
                }
            }

        }).start();
    };

    // #endregion
}
