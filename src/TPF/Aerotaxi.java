package TPF;

import java.time.LocalDate;
import java.util.*;

public class Aerotaxi {
    private HashSet<Avion> flota;
    private Set<Usuario> clientes;
    private List<Vuelo> vuelos;

    public Aerotaxi() {
        this.flota = new HashSet<Avion>();
        this.clientes = new HashSet<Usuario>();
        this.vuelos = new ArrayList<Vuelo>();
    }

    public Aerotaxi(HashSet<Avion> flota, Set<Usuario> clientes, List<Vuelo> vuelos) {
        this.flota = flota;
        this.clientes = clientes;
        this.vuelos = vuelos;
    }

    public Set<Avion> getFlota() {
        return flota;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public Set<Usuario> getClientes() {
        return clientes;
    }

    public void setFlota(HashSet<Avion> flota) {
        this.flota = flota;
    }

    public void setClientes(Set<Usuario> clientes) {
        this.clientes = clientes;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public void addAvion(Avion avion) {
        this.flota.add(avion);
    }
    public void addVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
//        vuelo.getCliente().agregarVuelo(vuelo);  //Agrego el id del vuelo a la lista de vuelos(id) del cliente
//        vuelo.getCliente().mejorAvionContratado(vuelo.getAvion());  //Comparto el vuelo para identificar el mejor avion contratado por cliente
//        vuelo.getCliente().setTotalGastado(vuelo.getCliente().getTotalGastado() + vuelo.costoTotal()); //Sumo el costo del vuelo al total gastado por el cliente
    }
    public void addUsuario(Usuario usuario) {
        this.clientes.add(usuario);
    }
    public void borrarVuelo(Vuelo vuelo){ this.vuelos.remove(vuelo); }

    public Usuario buscarUsuario(int dni) {
        Usuario buscado = null;
        for (Usuario aux : this.clientes) {
            if (dni == aux.getDni())
                buscado = aux;
        }
        return buscado; //Retorna el usuario si existe o null si no existe
    }


    public HashSet<Avion> avionesDisponibles(LocalDate fecha, int cantPasajeros){
        HashSet<Avion> avionesDisponibles = new HashSet<Avion>();
        if(this.vuelos.size() > 0){            //Si hay vuelos contratados..
            for(Avion avion : this.flota){             //Recorro la lista de aviones
                for(Vuelo aux : this.vuelos){            //Recorro la lista de vuelos
                    if(!buscarAvionEnListaDeVuelos(avion.id))     //Si el avion no está en ningun vuelo..
                        avionesDisponibles.add(avion);            //Lo agrego a la lista de aviones disponibles
                    else if(avion.equals(aux.getAvion())){    //Si el avión está en algún vuelo.. me fijo si el avion de la lista de aviones es igual al avion del vuelo..
                        if((!fecha.isEqual(aux.getFechaVuelo())) && (avion.capacidadMaxPasajeros >= cantPasajeros))   //Comparo la fecha que desea viajar el cliente con la fecha de cada vuelo y que la cantidad max de pasajeros sea suficiente
                            avionesDisponibles.add(avion);     //Agrego a la lista de aviones disponibles los aviones que no se usan esa fecha y tienen capacidad de pasajeros para el viaje
                    }
                }
            }
        }
        else{       //Si no hay vuelos contratados directamente agrego al set los aviones cuya capacidad de pasajeros alcance
            for(Avion avion : this.flota){
                if(cantPasajeros <= avion.capacidadMaxPasajeros)
                    avionesDisponibles.add(avion);
            }
        }

        return avionesDisponibles; //Retorna un set de los aviones disponibles
    }

    public boolean buscarAvionEnListaDeVuelos(int id){
            boolean rta = false;
            for(Vuelo aux : this.vuelos){
                if(aux.getId() == id)
                    rta = true;
            }
            return rta;  //Retorna true si el avion está en la lista de vuelos y false si no está
    }

    public void listarClientes() {
        System.out.println("\n******************************* Clientes de AEROTAXI ******************************");
        for (Usuario user : clientes)
            System.out.println(user.toString());
    }

    public void listarVuelosPorFecha(LocalDate fecha) {
        boolean flag = false;
        for (Vuelo v : this.vuelos) {
            if (fecha.isEqual(v.getFechaVuelo()))
                System.out.println(v.toString());
                flag = true;
        }
        if(!flag)
            System.out.println("No hay vuelos para la fecha");
    }

    public void listarVuelosUser(Usuario usuario) {
        if (usuario.getVuelos().size() == 0)
            System.out.println("El usuario no tiene vuelos contratados");
        else {
            for (Long aux : usuario.getVuelos())
                System.out.println(buscarVuelo(aux));
        }
    }

    public Vuelo buscarVuelo(long id){
        Vuelo buscado = null;
        for(Vuelo vuelo : this.vuelos){
            if(id == vuelo.getId())
                buscado = vuelo;
        }
        return buscado; //Retorna el vuelo buscado o null si no existe
    }

    public void listarAviones(){
        System.out.println("\n******************************* Aviones de AEROTAXI ******************************");
        for (Avion avion : this.flota){
            System.out.println(avion.toString());
        }
    }

}



    /*
    public void listarAvionesPorFecha(LocalDate fechaElegida, int cantAcomp) {
        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getFechaVuelo().equals(fechaElegida)) {
                boolean wifi = false;
                if (vuelo.getAvion().getClass().getSimpleName().equals("Gold")) {
                    Gold aux = new Gold((Gold) vuelo.getAvion());   //si es gold, se clona para poder acceder al metodo isWifi
                    wifi = aux.isWifi();
                }
                System.out.println(vuelo.getNumeroDeVuelo() + " - Avion " + vuelo.getAvion().getClass().getSimpleName() + " - [" + vuelo.getTipoVuelo().getOrigen() + "-" + vuelo.getTipoVuelo().getDestino() + "] - AsientosDisponibles: " + (vuelo.getAvion().getCapacidadMaxPasajeros() - vuelo.getCantPasajeros()) + " - Catering: " + vuelo.getAvion().isCatering() + " - Wifi: " + wifi + " - Costo: $" + vuelo.costoTotal(cantAcomp));
            }
        }
    }

    public void listarAvionesPorRecorrido(TipoVuelo tv, int cantAcomp) {
        System.out.println("VUELOS "+tv.getOrigen()+"-"+ tv.getDestino());
        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getTipoVuelo().getOrigen().equals(tv.getOrigen()) && vuelo.getTipoVuelo().getDestino().equals(tv.getDestino())) {
                boolean wifi = false;
                if (vuelo.getAvion().getClass().getSimpleName().equals("Gold")) {
                    Gold aux = new Gold((Gold) vuelo.getAvion());   //si es gold, se clona para poder acceder al metodo isWifi
                    wifi = aux.isWifi();
                }
                System.out.println(vuelo.getNumeroDeVuelo() + " - Avion " + vuelo.getAvion().getClass().getSimpleName() + " - Fecha salida: " + vuelo.getFechaVuelo() + " - Asientos disponibles: " + (vuelo.getAvion().getCapacidadMaxPasajeros() - vuelo.getCantPasajeros()) + " - Catering: " + vuelo.getAvion().isCatering() + " - Wifi: " + wifi + " - Costo total: $" + vuelo.costoTotal(cantAcomp));
            }
        }
    }



    public void listarVuelosPorDatos(LocalDate fecha, TipoVuelo tipo, Avion avion) {
        for (Vuelo vuelo : this.vuelos) {
            if ((vuelo.getFechaVuelo().equals(fecha)) && (vuelo.getTipoVuelo().equals(tipo)) && (vuelo.getAvion().equals(avion)))
                System.out.println(vuelo.toString());    //Muestro todos los vuelos de esa fecha, con ese origen y destino y ese avion
        }
    }


    public void listarVuelosDisponibles(Scanner scan) {
        int i = 0;
        System.out.println("VUELOS DISPONIBLES AEROTAXI\n");
        for (Vuelo v : this.getVuelos()) {
            boolean wifi = false;
            if (v.getAvion().getClass().getSimpleName().equals("Gold")) {
                Gold aux = new Gold((Gold) v.getAvion());   //si es gold, se clona para poder acceder al metodo isWifi
                wifi = aux.isWifi();
            }
            System.out.println(v.getNumeroDeVuelo() + " - Avion " + v.getAvion().getClass().getSimpleName() + " [" + v.getTipoVuelo().getOrigen() + "-" + v.getTipoVuelo().getDestino() + "] - Fecha salida: " + v.getFechaVuelo() + " - Asientos disponibles: "+(v.getAvion().getCapacidadMaxPasajeros()-v.getCantPasajeros())+ " - Catering: " + v.getAvion().isCatering() + " - Wifi: " + wifi );
            i++;
            if (i % 5 == 0) {
                String c;
                System.out.println("Presione 'c' para continuar");
                do {
                    c = scan.nextLine();
                } while (!c.equals("c"));
            }
        }
    }



    public void listarFlota(Scanner scan) {
        int i = 0;
        Iterator<Avion> it = this.getFlota().iterator();
        System.out.println("FLOTA AEROTAXI\n");
        while (it.hasNext()) {
            System.out.println(i+1 + " - " + it.next());
            i++;
            if (i % 5 == 0) {
                String c;
                System.out.println("Presione 'c' para continuar");
                do {
                    c = scan.nextLine();
                } while (!c.equals("c"));
            }
        }
    }



    public int getIndexVuelo(int numVuelo) {    //con el numero de vuelo obtengo el indice del arreglo
        int index = -1;
        for (Vuelo vuelo : this.vuelos) {
            if (vuelo.getNumeroDeVuelo() == numVuelo)
                index = this.getVuelos().indexOf(vuelo);
        }
        return index;
    }



    public boolean existenVuelos(TipoVuelo tipo, int cantPasajeros) {
        for (Vuelo v : this.vuelos) {
            if (v.getTipoVuelo().equals(tipo) && (cantPasajeros <= v.getAvion().getCapacidadMaxPasajeros() - v.getCantPasajeros()))
                return true;
        }
        return false;
    }

    public boolean existenVuelos(LocalDate fecha, int cantPasajeros) {
        for (Vuelo v : this.vuelos) {
            if (v.getFechaVuelo().equals(fecha) && (cantPasajeros <= v.getAvion().getCapacidadMaxPasajeros() - v.getCantPasajeros()))
                return true;
        }
        return false;
    }

     */


