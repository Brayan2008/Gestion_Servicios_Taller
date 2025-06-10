import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import View.LoginView;
import View.utils.JViews;
import java.awt.*;

public class App extends JFrame implements JViews {

    private LoginView l1 = new LoginView();
    /*
     * private ClienteView c1 = new ClienteView();
     * private AccesorioView A1 = new AccesorioView();
     * private VehiculoView v1 = new VehiculoView();
     * private MecanicoView m1 = new MecanicoView();
     * private CatalogoServiciosView cs1 = new CatalogoServiciosView();
     */

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> new App());
    }

    public App() {
        super("Gestion de servicios de un taller de autos");
        init();
        setResizable(true);
    }

    // #region Metodos
    @Override
    public void init() {
        setBounds(new Rectangle(600, 450));
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        agregarComponentes();
    }

    @Override
    public void agregarComponentes() {
        add(l1);
    }
    // #endregion

}
