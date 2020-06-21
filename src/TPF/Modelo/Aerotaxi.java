package TPF.Modelo;

import TPF.Menu.Utilidades;

import java.time.LocalDate;
import java.util.*;

public class Aerotaxi {
    private HashSet<Avion> flota;
    private HashSet<Usuario> clientes;
    private ArrayList<Vuelo> vuelos;

    public Aerotaxi() {
        this.flota = new HashSet<Avion>();
        this.clientes = new HashSet<Usuario>();
        this.vuelos = new ArrayList<Vuelo>();
    }

    public Aerotaxi(HashSet<Avion> flota, HashSet<Usuario> clientes, ArrayList<Vuelo> vuelos) {
        this.flota = flota;
        this.clientes = clientes;
        this.vuelos = vuelos;
    }

    public HashSet<Avion> getFlota() {
        return flota;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public HashSet<Usuario> getClientes() {
        return clientes;
    }

    public void setFlota(HashSet<Avion> flota) {
        this.flota = flota;
    }

    public void setClientes(HashSet<Usuario> clientes) {
        this.clientes = clientes;
    }

    public void setVuelos(ArrayList<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }


    public void addAvion(Avion avion) {
        this.flota.add(avion);
    }

    public void addVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
    }

    public void addUsuario(Usuario usuario) {
        this.clientes.add(usuario);
    }

    public void borrarVuelo(Vuelo vuelo) {
        this.vuelos.remove(vuelo);
    }

    public Usuario buscarUsuario(String dni) {
        Usuario buscado = null;
        for (Usuario aux : this.clientes) {
            if (dni.equals(aux.getDni()))
                buscado = aux;
        }
        return buscado; //Retorna el usuario si existe o null si no existe
    }

    public TreeSet<Avion> buscarAvionesDisponibles(LocalDate fecha, int cantPasajeros) {
        TreeSet<Avion> avionesDisponibles = new TreeSet<Avion>();
        if (vuelos != null) {
            HashSet<Avion> avionesOcupados = buscarAvionesPorFecha(fecha);

            for (Avion avion : flota) {
                if (!avionesOcupados.contains(avion) && avion.capacidadMaxPasajeros >= cantPasajeros)
                    avionesDisponibles.add(avion);
            }
        } else {
            for (Avion avion : this.flota) {
                if (cantPasajeros <= avion.capacidadMaxPasajeros)
                    avionesDisponibles.add(avion);
            }
        }

        return avionesDisponibles;
    }

    public HashSet<Avion> buscarAvionesPorFecha(LocalDate fecha) {
        HashSet<Avion> avionesDeLaFecha = new HashSet<Avion>();
        for (Vuelo vuelo : this.vuelos) {
            if (fecha.isEqual(vuelo.getFechaVuelo())) {
                avionesDeLaFecha.add(buscarAvion(vuelo.getAvion()));
            }
        }

        return avionesDeLaFecha;
    }


    public Avion buscarAvion(int id) {
        Avion buscado = null;
        for (Avion avion : this.flota) {
            if (avion.getId() == id)
                buscado = avion;
        }
        return buscado;  //Retorna el avión o false si no está
    }

    public void listarClientes() {
        System.out.println("\n******************************* Clientes de AEROTAXI ******************************");
        for (Usuario user : clientes)
            System.out.println(user.toString());
    }

    public void listarVuelosPorFecha(LocalDate fecha) {
        boolean flag = false;
        double costo = 0;
        int i = 0;

        for (Vuelo v : this.vuelos) {
            if (fecha.isEqual(v.getFechaVuelo())) {
                System.out.println(v.mostrarVuelo(this.buscarAvion(v.getAvion())));
                i++;
                flag = true;
                costo += v.costoTotal(this.buscarAvion(v.getAvion()), v.getRecorrido());
            }
        }
        System.out.println("\nCANTIDAD DE VUELOS " + fecha + ": " + i + " - TOTAL: $ " + costo+"\n");

        if (!flag)
            System.out.println("No hay vuelos para la fecha");
    }

    public void listarVuelosUser(Usuario usuario) {
        if (usuario.getVuelos().size() == 0)
            System.out.println("El usuario no tiene vuelos contratados");
        else {
            for (Integer aux : usuario.getVuelos())
                System.out.println(buscarVuelo(aux).mostrarVuelo(this.buscarAvion(buscarVuelo(aux).getAvion())));
        }
    }

    public Vuelo buscarVuelo(long id) {
        Vuelo buscado = null;
        for (Vuelo vuelo : this.vuelos) {
            if (id == vuelo.getId())
                buscado = vuelo;
        }
        return buscado; //Retorna el vuelo buscado o null si no existe
    }

    public void listarAviones() {
        System.out.println("\n******************************* Aviones de AEROTAXI ******************************");
        TreeSet<Avion> flotaTreeSet = new TreeSet<Avion>();
        for (Avion avion : this.flota)
            flotaTreeSet.add(avion);          //Paso los aviones a un TreeSet para mostrarlos ordenados por id

        for (Avion avion : flotaTreeSet)
            System.out.println(avion.toString());
    }


    public void listarVuelos() {
        int i = 0;
        System.out.println("\n****************************** Vuelos de AEROTAXI ******************************\n");
        if (this.getVuelos().isEmpty()) {
            System.out.println("\nNo hay vuelos reservados\n");
        } else {
            double costo = 0;
            for (Vuelo v : this.getVuelos()) {
                System.out.println(v.mostrarVuelo(this.buscarAvion(v.getAvion())));
                i++;
                if (i % 5 == 0)
                    Utilidades.pausar();
                costo += v.costoTotal(this.buscarAvion(v.getAvion()), v.getRecorrido());
            }
            System.out.println("\nCANTIDAD DE VUELOS: " + i + " - TOTAL: $ " + costo+"\n");

        }
    }

    public void listarVuelosDesde(int i) {
        double costo = 0;
        int cant = 0;
        for (Vuelo v : this.getVuelos()) {
            int[] aux = v.getRecorrido();
            if (aux[0] == i) {
                cant++;
                System.out.println(v.mostrarVuelo(this.buscarAvion(v.getAvion())));
                costo += v.costoTotal(this.buscarAvion(v.getAvion()), v.getRecorrido());
            }
        }
        System.out.println("\nCANTIDAD DE VUELOS: " + cant + " - TOTAL: $ " + costo+"\n");
    }

    public void listarVuelosHacia(int i) {
        int cant = 0;
        double costo = 0;
        for (Vuelo v : this.getVuelos()) {
            int[] aux = v.getRecorrido();
            if (aux[1] == i) {
                cant++;
                System.out.println(v.mostrarVuelo(this.buscarAvion(v.getAvion())));
                costo += v.costoTotal(this.buscarAvion(v.getAvion()), v.getRecorrido());
            }
        }
        System.out.println("\nCANTIDAD DE VUELOS: " + cant + " - TOTAL: $ " + costo+"\n");

    }
}

    /*
        public HashSet<Avion> avionesDisponibles(LocalDate fecha, int cantPasajeros) {
            HashSet<Avion> avionesDisponibles = new HashSet<Avion>();
            if (this.vuelos.size() > 0) {            //Si hay vuelos contratados..
                for (Avion avion : this.flota) {             //Recorro la lista de aviones
                    if (!buscarAvionEnListaDeVuelos(avion))     //Si el avion no está en ningun vuelo..
                        if()
                            avionesDisponibles.add(avion);            //Lo agrego a la lista de aviones disponibles
                    else {
                        for (Vuelo aux : this.vuelos) {            //Recorro la lista de vuelos
                            if (avion.equals(aux.getAvion())) {    //Si el avión está en algún vuelo.. me fijo si el avion de la lista de aviones es igual al avion del vuelo..
                                if ((!fecha.isEqual(aux.getFechaVuelo())) && ((aux.getCantPasajeros() + cantPasajeros) <= avion.capacidadMaxPasajeros))   //Comparo la fecha que desea viajar el cliente con la fecha de cada vuelo y que la cantidad max de pasajeros sea suficiente
                                    avionesDisponibles.add(avion);     //Agrego a la lista de aviones disponibles los aviones que no se usan esa fecha y tienen capacidad de pasajeros para el viaje
                            }
                        }
                    }
                }
            } else {       //Si no hay vuelos contratados directamente agrego al HashSet los aviones cuya capacidad de pasajeros alcance
                for (Avion avion : this.flota) {
                    if (cantPasajeros <= avion.capacidadMaxPasajeros)
                        avionesDisponibles.add(avion);
                }
            }

            return avionesDisponibles; //Retorna un hashSet de los aviones disponibles
        }

     */

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


