package TPF;

import TPF.Menu.Menu;
import TPF.Menu.Utilidades;
import TPF.Modelo.*;
import TPF.Persistencia.PersistenciaAvionesBronze;
import TPF.Persistencia.PersistenciaAvionesGold;
import TPF.Persistencia.PersistenciaAvionesSilver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
//        Usuario user1 = new Usuario("Pepe", "Argento", "12", 44);
//        Usuario user2 = new Usuario("Juan", "Perez", "10", 33);
//        Usuario user3 = new Usuario("Ana", "Gonzalez", "15", 22);
//        Usuario user4 = new Usuario("Gabriel", "Mendoza", "11", 35);
//        Usuario user5 = new Usuario("Juana", "Hernandez", "8", 37);
//
////
////
//        Gold avion1 = new Gold(1, 2000, 300, 10, 300, "Motor a hélice", true, true);
//        Gold avion2 = new Gold(2, 1800, 280, 9, 280, "Motor a hélice", true, false);
//        Silver avion3 = new Silver(3, 1600, 250, 8, 260, "Motor de pistones", true);
//        Silver avion4 = new Silver(4, 1400, 220, 7, 240, "Motor de pistones", true);
//        Bronze avion5 = new Bronze(5, 1200, 190, 6, 220, "Motor a reaccion", false);
//        Bronze avion6 = new Bronze(6, 1200, 150, 5, 200, "Motor a reaccion", false);

//        Recorridos e = new Recorridos();
//        e.imprimirMatriz();
//        Utilidades.pausar();



        Aerotaxi aeroTaxi = new Aerotaxi();
//
//       HashSet<Gold> avionesGold = new HashSet<Gold>();
//        avionesGold.add(avion1);
//        avionesGold.add(avion2);
//
//        HashSet<Silver> avionesSilver = new HashSet<Silver>();
//        avionesSilver.add(avion3);
//        avionesSilver.add(avion4);
//
//        HashSet<Bronze> avionesBronze = new HashSet<Bronze>();
//        avionesBronze.add(avion5);
//        avionesBronze.add(avion6);
//
//        PersistenciaAvionesGold.persistirAvionesGold(avionesGold);
//        PersistenciaAvionesSilver.persistirAvionesSilver(avionesSilver);
//        PersistenciaAvionesBronze.persistirAvionesBronze(avionesBronze);

//
//        aeroTaxi.addUsuario(user1);
//        aeroTaxi.addUsuario(user2);
//        aeroTaxi.addUsuario(user3);
//        aeroTaxi.addUsuario(user4);
//        aeroTaxi.addUsuario(user5);

        Menu menu = new Menu(aeroTaxi);
        menu.menuPrincipal();
    }

}




/*


      public static void autoCrearVuelos(Aerotaxi aero, int cantidadVuelos, int cantAviones) {
        int numeroVuelo = 1;
        Avion deJuguete = new Bronze(1, 1, 1, 1, 1, Propulsion.REAC, true);
        List<Avion> aeroplanes=new ArrayList<Avion>();

        //random aviones
        aeroplanes=randomAviones(cantAviones);
        aero.setFlota(aeroplanes);

        for (int i = 0; i < cantidadVuelos; i++) {

            //random fecha
            LocalDate fecha = randomFecha();

            //random tipovuelo
            Vuelo aux = new Vuelo(12345, LocalDate.now(), TipoVuelo.UNO, deJuguete);
            randomTipoVuelo(aux);

            //elegir avion de lista
            boolean disponible=false;
            int randomAvion=-1;
            while(!disponible) {                            //chequear que el avion no tenga un vuelo ese mismo dia
                randomAvion = (int) (Math.random() * cantAviones) + 1;
                disponible = chequearDisponibilidadAvion(aero, fecha, aero.getFlota().get(randomAvion-1));
            }

            //agregar vuelo a empresa
            Vuelo n0 = new Vuelo(numeroVuelo, fecha, aux.getTipoVuelo(), aeroplanes.get(randomAvion-1));
            aero.addVuelo(n0);

            numeroVuelo++;
        }
        autoRenovarVuelos(aero);  //actualiza fechas pasadas
    }

    public static List<Avion> randomAviones(int cantAviones){
        List<Avion> avi=new ArrayList<Avion>();

        for(int i=0;i<cantAviones;i++) {

            //random wifi
            boolean wifo = randomWifi();

            //random propulsion
            String propulsion = randomPropulsion();

            Avion auxGold = new Gold(7000, 20, 400, 920, propulsion, true, wifo);
            Avion auxSilver = new Silver(6200, 16, 300, 840, propulsion, true);
            Avion auxBronze = new Bronze(5300, 14, 250, 700, propulsion, false);

            int randomAvion = (int) (Math.random() * 3) + 1;
            switch (randomAvion) {
                case 1:
                    avi.add(auxGold);
                     break;
                case 2:
                    avi.add(auxSilver);
                    break;
                case 3:
                    avi.add(auxBronze);
                    break;
                default:
                    break;
            }
        }
        return avi;
    }

    public static boolean chequearDisponibilidadAvion(Aerotaxi aerotaxi, LocalDate fechaVueloNuevo,Avion avion){
        boolean disponible=true;
        for(Vuelo v: aerotaxi.getVuelos()){
            if(v.getFechaVuelo()==fechaVueloNuevo && v.getAvion().equals(avion)){
                disponible=false;
                break;
            }
        }return disponible;
    }

    public static LocalDate randomFecha() {
        int randomDay = 1;
        int randomMonth = 1;
        int randomYear = 1;
        LocalDate fecha = null;

        randomYear = (int) (Math.random() * 2) + 2020;
        randomMonth = (int) (Math.random() * 12) + 1;
        switch (randomMonth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                randomDay = (int) (Math.random() * 31) + 1;
                break;
            case 2:
                randomDay = (int) (Math.random() * 28) + 1;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                randomDay = (int) (Math.random() * 30) + 1;
                break;
            default:
                break;
        }
        return LocalDate.of(randomYear, randomMonth, randomDay);
    }

    public static void randomTipoVuelo(Vuelo aux){
        int randomTipoVuelo = (int) (Math.random() * 6) + 1;
        switch (randomTipoVuelo) {
            case 1:
                aux.setTipoVuelo(TipoVuelo.UNO);
                break;
            case 2:
                aux.setTipoVuelo(TipoVuelo.DOS);
                break;
            case 3:
                aux.setTipoVuelo(TipoVuelo.TRES);
                break;
            case 4:
                aux.setTipoVuelo(TipoVuelo.CUATRO);
                break;
            case 5:
                aux.setTipoVuelo(TipoVuelo.CINCO);
                break;
            case 6:
                aux.setTipoVuelo(TipoVuelo.SEIS);
                break;
            default:
                break;
        }
    }


    public static String randomPropulsion() {
        int propulsionRandom = 1;
        String propulsion = "";
        propulsionRandom = (int) (Math.random() * 3) + 1;
        switch (propulsionRandom) {
            case 1:
                propulsion = Propulsion.REAC.toString();
                break;
            case 2:
                propulsion = Propulsion.HEL.toString();
                break;
            case 3:
                propulsion = Propulsion.PIST.toString();
                break;
            default:
                break;
        }
        return propulsion;
    }

    public static boolean randomWifi(){
        int wifo = (int) (Math.random() * 2) + 1;
        boolean wifi = true;
        if (wifo % 2 == 0)
            wifi = false;
        return wifi;
    }


    public static void autoRenovarVuelos(Aerotaxi aero) {  //vuelos q ya pasaron se renuevan una semana mas tarde
        for (Vuelo v : aero.getVuelos()) {                                  //hasta q la fecha es "futura"
            if (v.getFechaVuelo().isBefore(LocalDate.now())) {
                LocalDate vieja = v.getFechaVuelo();
                LocalDate nueva = LocalDate.now();
                Period diferencia = Period.between(vieja, nueva); //calcular diferencia entre fecha original y fecha actual
                int dif = diferencia.getDays() + diferencia.getMonths() * 30 + diferencia.getYears() * 365; //pasar la dif a dias
                aero.getVuelos().get(aero.getIndexVuelo(v.getNumeroDeVuelo())).setFechaVuelo(vieja.plusDays(dif + 7));//agregar a lista dif con 7 dias mas
            }
        }
    }



 */
