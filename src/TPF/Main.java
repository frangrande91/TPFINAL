package TPF;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Aerotaxi aeroTaxi = new Aerotaxi();
        Gold Boeing = new Gold(123,1024.5,300,700,Propulsion.REAC.toString(),true,true);
        Vuelo vuelo1 = new Vuelo(LocalDate.of(2020, 10, 15), TipoVuelo.UNO, Boeing);
        Usuario user = new Usuario("Pepe", "Argento", "12000000", 45);
        aeroTaxi.addAvion(Boeing);
        aeroTaxi.addVuelo(vuelo1);
        aeroTaxi.addUsuario(user);

        Menu menu = new Menu(aeroTaxi);
        menu.menuPrincipal();



    }
}
