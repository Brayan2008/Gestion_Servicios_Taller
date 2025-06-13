package View;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import View.utils.JViews;
import controllers.LoginViewController;

import java.awt.*;

public class App2 extends JFrame implements JViews {

    public static JFrame punteroFrame;
    
    public static JFrame getPunteroFrame() {
        return punteroFrame;
    }

    private LoginView l1 = new LoginView();
    /*
     * private ClienteView c1 = new ClienteView();
     * private AccesorioView A1 = new AccesorioView();
     * private VehiculoView v1 = new VehiculoView();
     * private MecanicoView m1 = new MecanicoView();
     * private CatalogoServiciosView cs1 = new CatalogoServiciosView();
     */

    public App2() {
        super("Gestion de servicios de un taller de autos");
        punteroFrame = this;
        setUndecorated(true); //Undecorated sin
        init();
        setResizable(true);
    }
    
    // #region Metodos
    @Override
    public void init() {
        setBounds(new Rectangle(590, 450));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        agregarComponentes();
        FadeWindowsIn(this);
        setVisible(true);
    }

    @Override
    public void agregarComponentes() {
        add(l1);
        new LoginViewController(l1);        
    }
    // #endregion

}