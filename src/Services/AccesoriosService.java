package Services;

import Model.Accesorios;
import Services.interfaces.Service;

public class AccesoriosService extends Service<Accesorios> {

    
    public boolean IDvacia(Accesorios user) {
        if (user.getID().isBlank() || user.getID().isEmpty()) {
            return true;   
        }
        return false;   
    }

}
