package TPF;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import static java.lang.Math.*;


//hacer funcion q no agregue aviones duplicados!!!
// salir del menu principal no funciona

public class Main {

    public static void main(String[] args) {
        Aerotaxi aeroTaxi = new Aerotaxi();
        Usuario user = new Usuario("Pepe", "Argento", 12, 45);
        // Gold Boeing = new Gold(123, 1024.5, 300, 700, Propulsion.REAC.toString(), true, false);
        // Vuelo vuelo1 = new Vuelo(001, LocalDate.of(2019, 3, 15), TipoVuelo.UNO, Boeing);
        // Vuelo vuelo2 = new Vuelo(006, LocalDate.of(2020, 10, 15), TipoVuelo.UNO, Boeing);
        // aeroTaxi.addAvion(Boeing);
        //     aeroTaxi.addVuelo(vuelo1);
        //     aeroTaxi.addVuelo(vuelo2);
        aeroTaxi.addUsuario(user);
        autoCrearVuelos(aeroTaxi, 55);
        Menu menu = new Menu(aeroTaxi);
        menu.menuPrincipal();
    }


    public static void autoCrearVuelos(Aerotaxi aero, int cant) {   //crea vuelos con todos los param aleatorios
        int numeroVuelo = 1;                      //tambien genera los aviones - tutti se agrega a las listas de la empresa
        int randomDay = 1;
        int randomMonth = 1;
        int randomYear = 1;
        int randomTipoVuelo = 1;
        Avion deJuguete = new Bronze(1, 1, 1, 1, Propulsion.REAC.toString(), true);

        //random fecha
        for (int i = 0; i < cant; i++) {
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

            //random tipovuelo
            Vuelo aux = new Vuelo(12345, LocalDate.now(), TipoVuelo.UNO, deJuguete);
            randomTipoVuelo = (int) (Math.random() * 6) + 1;
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

            //random wifi
            int wifo = 1;
            wifo = (int) (Math.random() * 2) + 1;
            boolean wifi = true;
            if (wifo % 2 == 0)
                wifi = false;

            //random propulsion
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

            //random avion
            Avion auxGold = new Gold(7000, 250, 400, 6500, propulsion, true, wifi);
            Avion auxSilver = new Silver(6200, 200, 300, 5000, propulsion, true);
            Avion auxBronze = new Bronze(5300, 180, 250, 4000, propulsion, false);
            int randomAvion = (int) (Math.random() * 3) + 1;
            switch (randomAvion) {
                case 1:
                    Vuelo n0 = new Vuelo(numeroVuelo, LocalDate.of(randomYear, randomMonth, randomDay), aux.getTipoVuelo(), auxGold);
                    aero.addVuelo(n0);
                    aero.addAvion(auxGold);
                    break;
                case 2:
                    Vuelo n1 = new Vuelo(numeroVuelo, LocalDate.of(randomYear, randomMonth, randomDay), aux.getTipoVuelo(), auxSilver);
                    aero.addVuelo(n1);
                    aero.addAvion(auxSilver);
                    break;
                case 3:
                    Vuelo n2 = new Vuelo(numeroVuelo, LocalDate.of(randomYear, randomMonth, randomDay), aux.getTipoVuelo(), auxBronze);
                    aero.addVuelo(n2);
                    aero.addAvion(auxBronze);
                    break;
                default:
                    break;
            }
            numeroVuelo++;
        }
        autoRenovarVuelos(aero);  //actualiza fechas pasadas
    }


    public static void autoRenovarVuelos(Aerotaxi aero) {  //vuelos q ya pasaron se renuevan una semana mas tarde
        List<Vuelo> auxAgregar = new ArrayList<Vuelo>();                //hasta q la fecha es "futura"
        List<Vuelo> auxBorrar=new ArrayList<Vuelo>();

        for (Vuelo v : aero.getVuelos()) {
            if (v.getFechaVuelo().isBefore(LocalDate.now())) {   //si hay vuelos vencidos...
                auxBorrar.add(v);
                LocalDate vieja = v.getFechaVuelo();
                LocalDate nueva = LocalDate.now();
                Period diferencia = Period.between(vieja, nueva); //calcular diferencia entre fecha original y fecha actual
                int dif = diferencia.getDays() + diferencia.getMonths() * 30 + diferencia.getYears() * 365; //pasar la dif a dias
                Vuelo nuevo = new Vuelo(v.getNumeroDeVuelo(), v.getFechaVuelo().plusDays(dif + 7), v.getTipoVuelo(), v.getAvion());
                auxAgregar.add(nuevo);     //agregar a lista aux con 7 dias mas
            }
        }
        for (Vuelo ve : auxBorrar) {
            aero.getVuelos().remove(ve); //borro vuelos vencidos de aerotaxi-- se hace x pasos x ConcurrentModificationException
        }
        for (Vuelo ve : auxAgregar) {
            aero.addVuelo(ve); //copio vuelos actualizados a aerotaxi
        }
    }


}