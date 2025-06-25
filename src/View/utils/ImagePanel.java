package View.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    BufferedImage image;

    public ImagePanel(String imagen) {
        try {
            this.image = ImageIO.read(new File("src/resources/"+imagen));
        } catch (Exception e) {
            System.out.println("Error al intentar cargar la imagen" + e);
        }
    }

    //Viene de JComponent que es padre de JPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
     }
    
}

