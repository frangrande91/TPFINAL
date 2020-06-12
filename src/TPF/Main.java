package TPF;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import static java.lang.Math.*;


// salir del menu principal no funciona

public class Main {

    public static void main(String[] args) {
        Aerotaxi aeroTaxi = new Aerotaxi();
        Usuario user = new Usuario("Pepe", "Argento", 12, 45);
        aeroTaxi.addUsuario(user);
        autoCrearVuelos(aeroTaxi, 55,15);
        Menu menu = new Menu(aeroTaxi);
        menu.menuPrincipal();
    }




    //crea vuelos con todos los param aleatorios //tambien genera los aviones - se agrega tutti a las listas de la empresa
    public static void autoCrearVuelos(Aerotaxi aero, int cantidadVuelos, int cantAviones) {
        int numeroVuelo = 1;
        Avion deJuguete = new Bronze(1, 1, 1, 1, Propulsion.REAC.toString(), true);
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


}