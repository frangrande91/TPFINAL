package TPF;

import TPF.Menu.Menu;
import TPF.Modelo.*;


public class Main {

    public static void main(String[] args) {

        Aerotaxi aeroTaxi = new Aerotaxi();

        Menu menu = new Menu(aeroTaxi);
        menu.menuPrincipal();
    }
}




