import java.io.IOException;

import javax.swing.SwingUtilities;

import View.App2;

public class Main {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(()->new App2());        
    }
}
